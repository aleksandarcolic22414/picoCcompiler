package antlr;

import compilationControlers.Writers;
import tools.FunctionsAnalyser;
import tools.Variable;
import nasm.DataSegment;
import compilationControlers.Checker;
import compilationControlers.CompilationControler;
import constants.Constants;
import constants.MemoryClassEnumeration;
import nasm.NasmTools;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import tools.LabelsHelper;

/**
 *
 * @author Aleksandar Colic
 */
public class TranslationVisitor extends picoCBaseVisitor<String> 
{
    /* List that contains informations about all functions
        that are beeing compiled */
    public static Map<String, FunctionsAnalyser> functions;
   
    /* Curent function context.  */
    public static FunctionsAnalyser curFuncAna = null;

    public TranslationVisitor() 
    {
        functions = new HashMap<>();
    }
    
    @Override
    public String visitCompilationUnit(picoCParser.CompilationUnitContext ctx) 
    {
        super.visitCompilationUnit(ctx); 
        
        if (CompilationControler.warnings != 0) {
            System.err.println("Warnings : " + CompilationControler.warnings);
        }
        /* Check wheather compilation is successful */
        if (CompilationControler.errors == 0) {
            System.out.println("Compilation successful!");
        } else {
            System.err.println("Errors: " + CompilationControler.errors);
            System.err.println("Compilation failed!");
            /* Just if output is not needed for testing, then -> 
            System.exit(0); */
        }
        try {    
            Writers writers = new Writers();
            writers.writeOutput();
        } catch (IOException ex) {
            Logger.getLogger(TranslationVisitor.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (long i : LabelsHelper.ifElseLabelHelper) {
            System.out.print(i + " ");
        }
        return null;
    }
    
    @Override
    public String visitFunctionDefinition(picoCParser.FunctionDefinitionContext ctx) 
    {
        /* Get name and memory class of function */
        String name = ctx.functionName().getText();
        String memoryClass = ctx.typeSpecifier().getText();
        MemoryClassEnumeration memclass;
        memclass = FunctionsAnalyser.getMemoryClass(memoryClass);
        
        /* Chech weather function is already defined */
        if (!Checker.funcDefCheck(ctx, name))
            return null;
        
        /* Create new function analyser object, and set it's type specifier
        to some type */
        FunctionsAnalyser fa = new FunctionsAnalyser(name);
        fa.setMemoryClass(memclass);
        
        functions.put(name, fa);       /* Add function to collection */
        
        FunctionsAnalyser.setFunctionInProcess(true); /* "In function context" */
        FunctionsAnalyser.setInProcess(name); /* Change function that is processed */
        curFuncAna = fa;
        /* Make room for local variables and arguments.
            Information about whole space on stack needed for
            local variables and parameters is hold within map of functions in class
            TranslationListener. */
        int localsAndArgsSize = 
                TranslationListener.lisFuncAna.get(name).
                    getSpaceForVariables();
                
        String ls = Integer.toString(localsAndArgsSize);
        
        /* Setup text segment for function definition */
        Writers.emitFunctionSetup(name);
        /* Substract number of bytes needed for variables if there is any */
        if (localsAndArgsSize > 0)
            Writers.emitInstruction("sub", "rsp", ls);    
        /* Visit rest of the function. (visitChildren) */
        super.visitFunctionDefinition(ctx);
        
        /* Check for return statement */
        Checker.funcRetStatCheck(ctx);
        
        /* Emit return label and reset stack */
        Writers.emitLabelReturn(curFuncAna.getFunctionName());
        Writers.emitText(Constants.FUNCTION_EXIT);
        /* Set current function context to null */
        curFuncAna = null;
        FunctionsAnalyser.setFunctionInProcess(false);
        FunctionsAnalyser.setInProcess(null);
        
        return null;
    }

    @Override
    public String visitParameterList(picoCParser.ParameterListContext ctx) 
    {
        /* Get list of parameters */
        List<picoCParser.ParameterContext> paramsList = ctx.parameter();
        int numberOfParams = paramsList.size();
        curFuncAna.setNumberOfParameters(numberOfParams);
        /* Visit children */
        super.visitParameterList(ctx);
        /* Copy arguments to stack */
        NasmTools.moveArgsToStack(paramsList);
        /* Free all registers for function body */
        NasmTools.freeAllRegisters();
        return null;
    }

    @Override
    public String visitParameter(picoCParser.ParameterContext ctx) 
    {
        /* Variable name */
        String name;
        name = ctx.ID().getText();
        /* Chech if variable is already declared */
        if (!Checker.paramCheck(ctx, name))
            return null;
        
        /* Get variable's memory class  */
        String type = ctx.typeSpecifier().getText();
        MemoryClassEnumeration typeSpecifier;
        typeSpecifier = FunctionsAnalyser.getMemoryClass(type);
        
        /* Get stack position */
        String stackPosition = curFuncAna.declareParameterVariable(typeSpecifier);
        
        /* Create new variable object */
        Variable var = new Variable(name, stackPosition, false, typeSpecifier);
        
        /* Insert new variable in function analyser */
        curFuncAna.getParameterVariables().put(name, var);
        
        /* Size in bytes */
        int varSize = NasmTools.getSize(typeSpecifier);
        
        if (!Checker.varSizeCheck(ctx, varSize))
            return null;
        
        return super.visitParameter(ctx);
    }

    @Override
    public String visitDeclarationList(picoCParser.DeclarationListContext ctx) 
    {
        /* Get memory class for declaration */
        String memoryClass = ctx.typeSpecifier().getText();
        MemoryClassEnumeration memclass = FunctionsAnalyser.getMemoryClass(memoryClass);
        curFuncAna.setCurrentDeclaratorType(memclass);
        /* Keep declaring rest of the list */
        super.visitDeclarationList(ctx);
        /* Set declarator to void (neutral) */
        curFuncAna.setCurrentDeclaratorType(MemoryClassEnumeration.VOID);
        return null;
    }

    @Override
    public String visitDeclaration(picoCParser.DeclarationContext ctx) 
    {
        /* Extern variable declaration */
        if (!FunctionsAnalyser.isFunctionInProcess()) {
            DataSegment.DeclareExtern(ctx);
            return null;
        }
        /* Variable name */
        String name;
        /* If direct ID is available, then declare it, else go into assignment context
            to snap of variable name for declaration */
        if (ctx.assignmentExpression() == null)
            name = ctx.ID().getText();
        else
            name = ctx.assignmentExpression().getChild(0).getText();
            
        /* Chech if variable is already declared */
        if (!Checker.varDeclCheck(ctx, name))
            return null;
        
        /* Get variable's memory class  */
        MemoryClassEnumeration typeSpecifier;
        typeSpecifier = curFuncAna.getCurrentDeclaratorType();
        
        /* Get stack position */
        String stackPosition = curFuncAna.declareLocalVariable(typeSpecifier);
        
        /* Create new variable object */
        Variable var = new Variable(name, stackPosition, false, typeSpecifier);
        
        /* Insert new variable in function analyser */
        curFuncAna.getLocalVariables().put(name, var);
        
        /* Size in bytes */
        int varSize = NasmTools.getSize(typeSpecifier);
        /* Check size of variable */
        if (!Checker.varSizeCheck(ctx, varSize))
            return null;
        
        return super.visitDeclaration(ctx);
    }

    @Override
    public String visitAssign(picoCParser.AssignContext ctx) {
        /* Get id value */
        String id = ctx.ID().getText();
        /* Get variable context */
        Variable var = curFuncAna.getAnyVariable(id);
        /* Check if it is declared */
        if (!Checker.varDeclCheck(ctx, id, var))
            return null;
        
        /* Register where expression is calculated */
        String res;
        /* Try to recover from error. */
        if ((res = visit(ctx.assignmentExpression())) == null) 
            return null;
        
        var.setInitialized(true);
        
        /* If size of return register doesn't match size of var than it needs
            to be casted, so size of variable is taken and later, if it is register,
            it is casted to proper size */
        int sizeOfVar = NasmTools.getSize(var.getTypeSpecifier());
        /* Get position of variable */
        String stackPos = var.getStackPosition();
        /* Emit assign if right operand is register.
            getStackPosition returns position of var on stack */
        if (NasmTools.isRegister(res)) {
            res = NasmTools.castVariable(res, sizeOfVar);
            Writers.emitInstruction("mov", stackPos, res);
            NasmTools.free(res);
        } else {
            String temp = NasmTools.getNextFreeTemp();
            Writers.emitInstruction("mov", temp, res);
            Writers.emitInstruction("mov", stackPos, temp);
            NasmTools.free(temp);
        }
        /* Return stack displacement */
        return stackPos;
    }
    
    @Override
    public String visitStatement(picoCParser.StatementContext ctx) 
    {
        super.visitStatement(ctx); 
        NasmTools.freeAllRegisters();
        return null;
    }
    
    @Override
    public String visitReturnStat(picoCParser.ReturnStatContext ctx) 
    {
        /* TODO: Check for type of return value! */
        String res;
        /* Try to recover from error */
        if ((res = visit(ctx.expression())) == null)
            return null;
        /* Set function has return  */
        curFuncAna.setHasReturn(true);
        /* mov result to eax for return */
        Writers.emitInstruction("mov", NasmTools.STRING_EAX, res);
        Writers.emitJumpToExit(FunctionsAnalyser.getInProcess());
        /* Free all registers for function exit */
        NasmTools.freeAllRegisters();
        return null;
    }

    
    /* Actual function call goes something like this: *******************/
        /* Save registers if any of them is needed for further calculations */
        /* If there is arguments, pass them through registers and stack */
        /* Emit function call */
        /* Store return value in free memory */
        /* Restore saved registers */
    /* Now the explanation for showNextFreeTemp function.
        It doesn't actually set any flags in flags variable in NasmTools, but
        just check which is next register (or position on stack if registers are filed)
        which can hold value. After that, register's are saved on stack, 
        but before they are restored, real method for getting
        registers is called which is getNextFreeTemp. It can't be done without
        that, because, if getNextFreeTemp is called insted of showNextFreeTemp, then
        restoreRegisters from NasmTools would override it's value, because
        that value would be pushed on stack along with other registers as
        saved registers from function. */
    @Override
    public String visitFunctionCall(picoCParser.FunctionCallContext ctx) 
    {
        List<picoCParser.ArgumentContext> argumentList;
        String functionName = ctx.functionName().getText();
        /* Check if there is arguments */
        if (ctx.argumentList() != null) {
            argumentList = ctx.argumentList().argument();
        } else
            argumentList = null;
        
        /* Check function and try to recover if it's bad call */
        if (!Checker.functionCallCheck(ctx, argumentList))
            return null;
        
        /* Little thing: View which is next free register for further calculation
            and move function's return value to it, to continue calculating */
        String nextFreeTemp = NasmTools.showNextFreeTemp();
        /* Registers are saved and freed for further use */
        NasmTools.saveRegistersOnStack();
        NasmTools.moveArgsToRegisters(this, argumentList);
        /* Special setup */
        if (NasmTools.isFunctionFromLib(functionName))
            Writers.emitInstruction("mov", "eax", "0");
        Writers.emitInstruction("call", functionName);
        /* Avoid unnecessary move */
        if (!nextFreeTemp.equals("eax"))
            Writers.emitInstruction("mov", nextFreeTemp, "eax");
        NasmTools.restoreRegisters();
        nextFreeTemp = NasmTools.getNextFreeTemp();
        
        /* TODO: See in which part of register is return value from function */
        return nextFreeTemp;
    }

    @Override
    public String visitArgumentList(picoCParser.ArgumentListContext ctx) {
        return super.visitArgumentList(ctx);
    }

    @Override
    public String visitArgument(picoCParser.ArgumentContext ctx) 
    {
        /* If it is not string literal, then return value in a register! */
        if (ctx.STRING_LITERAL() == null) {
            String res = visit(ctx.assignmentExpression());
            /* Free "a" register */
            NasmTools.free(res);
            return res;
        }
        String strlit = ctx.STRING_LITERAL().getText();
        String literalName = NasmTools.defineStringLiteral(strlit);
        return literalName;
    }
    
    @Override
    public String visitExpression(picoCParser.ExpressionContext ctx)
    {
        String s = super.visitExpression(ctx);
        /* Writers.emitInstruction("xor", "eax", "eax");
            doesn't need to be done, because for expression
            the first instruction is  always "mov", and eax register shoud be saved
            before enterance if it holds some significant value. */
        return s;
    }

    @Override
    public String visitParens(picoCParser.ParensContext ctx) 
    {
        String s = visit(ctx.expression());
        return s;
    }
    
    @Override
    public String visitNegation(picoCParser.NegationContext ctx) {
        /* Visit rest of expression */
        String res = super.visitNegation(ctx);
        /* Negate it */
        Writers.emitInstruction("neg", res);
        return res;
    }
    
    
    
    @Override
    public String visitAddSub(picoCParser.AddSubContext ctx) 
    {
        String leftExpr;
        String rightExpr;
        /* Try to visit children and recover if error ocured */
        if ((leftExpr = visit(ctx.additiveExpression())) == null)
            return null;
        if ((rightExpr = visit(ctx.multiplicativeExpression())) == null)
            return null;
        String nextFreeTemp;
        String operation = NasmTools.getOperation(ctx.op.getType());
        
        /* If left operand is not register, then it needs to be moved to one.
            It's moved to eax, but first eax is saved on stack. */
        if (!NasmTools.isRegister(leftExpr)) {
            nextFreeTemp = NasmTools.getNextFreeTemp();
            Writers.emitInstruction("mov", nextFreeTemp, "eax");
            Writers.emitInstruction("mov", "eax", leftExpr);
            Writers.emitInstruction(operation, "eax", rightExpr);
            Writers.emitInstruction("mov", leftExpr, "eax");
            Writers.emitInstruction("mov", "eax", nextFreeTemp);
            NasmTools.free(nextFreeTemp);
        } else
            Writers.emitInstruction(operation, leftExpr, rightExpr);
        
        if (NasmTools.isRegister(rightExpr))
            NasmTools.free(rightExpr);
        return leftExpr;
    }

    @Override
    public String visitMulDiv(picoCParser.MulDivContext ctx) 
    {
        boolean fake = false;
        String leftExpr;
        String rightExpr;
        /* Try to visit children and recover if error ocured */
        if ((leftExpr = visit(ctx.multiplicativeExpression())) == null)
            return null;
        if ((rightExpr = visit(ctx.unaryExpression())) == null)
            return null;
       
        String nextFreeTemp;
        String s1, s2;
        String fakelyTaken = null;
        if (ctx.op.getType() == picoCParser.MUL) {
            /* Chech wheather leftExpr is register. If it's not then it needs to be
                moved to one and then multiplied */
            if (!NasmTools.isRegister(leftExpr)) {
                nextFreeTemp = NasmTools.getNextFreeTemp();
                Writers.emitInstruction("mov", nextFreeTemp, "eax");
                Writers.emitInstruction("mov", "eax", leftExpr);
                Writers.emitInstruction("imul", "eax", rightExpr);
                Writers.emitInstruction("mov", leftExpr, "eax");
                Writers.emitInstruction("mov", "eax", nextFreeTemp);
                NasmTools.free(nextFreeTemp);
            } else
                Writers.emitInstruction("imul", leftExpr, rightExpr);
        } else if (ctx.op.getType() == picoCParser.DIV) {
            if (leftExpr.equals("eax")) {
                if (NasmTools.isTakenRegisterDREG()) { /* Never true, but stil */
                    nextFreeTemp = NasmTools.getNextFreeTemp();
                    Writers.emitInstruction("mov", nextFreeTemp, "edx");
                    Writers.emitInstruction("cdq");
                    Writers.emitInstruction("idiv", rightExpr);
                    Writers.emitInstruction("mov", "edx", nextFreeTemp);
                    NasmTools.free(nextFreeTemp);
                } else {
                    Writers.emitInstruction("cdq");
                    Writers.emitInstruction("idiv", rightExpr);
                }
            } else {    /* leftExpr operand is not eax */
                /* edx needs to be fakely taken, so that getNextFreeTemp
                    would not save some register in edx, because it is needed
                    for remainder of division. */
                
                if (!NasmTools.isTakenRegisterDREG()) {
                    fake = true;
                    fakelyTaken = NasmTools.getNextFreeTemp();
                }
                /* Always true -> */
                if (NasmTools.isTakenRegisterAREG() && NasmTools.isTakenRegisterDREG()) {
                    s1 = NasmTools.getNextFreeTemp();
                    Writers.emitInstruction("mov", s1, "eax");  /* save eax value into s1 */
                    s2 = NasmTools.getNextFreeTemp();
                    Writers.emitInstruction("mov", s2, "edx");  /* save edx value into s2 */
                    
                    Writers.emitInstruction("mov", "eax", leftExpr); /* setting eax and edx for div */
                    Writers.emitInstruction("cdq");
                    
                    /* If right operand od division is edx, than eax needs
                        to be divided by moved edx, which is s2 */
                    if (!rightExpr.equals("edx"))
                        Writers.emitInstruction("idiv", rightExpr);
                    else
                        Writers.emitInstruction("idiv", s2);
                    
                    Writers.emitInstruction("mov", leftExpr, "eax"); /* restoring values */
                    Writers.emitInstruction("mov", "eax", s1);
                    Writers.emitInstruction("mov", "edx", s2);
                    
                    NasmTools.free(s2);
                    NasmTools.free(s1);
                }
                
                if (fake == true) {
                    NasmTools.free(fakelyTaken);                    
                }
            }
        }
        
        if (NasmTools.isRegister(rightExpr))
            NasmTools.free(rightExpr);
        return leftExpr;    /* leftExpr register is returned */
    }

    /* TODO: Implement direct stack position to be returned. 
        Division must be changed in weird case of EDXTaken. */
    @Override
    public String visitId(picoCParser.IdContext ctx) 
    {
        /* Just for more readable code */
        boolean local = true, param = true;
        
        String id = ctx.ID().getText();
        /* Position of variable on stack */
        String stackPosition;
        /* Next free register */
        String nextFreeTemp;
        
        /* Check if variable is local */
        Variable newVar = curFuncAna.getLocalVariables().get(id);
        /* if variable is null, then it's sure that it is not local */
        if (newVar == null)
            local = false;
        /* If it is not local, maybe it's parameter */
        if (!local)
            newVar = curFuncAna.getParameterVariables().get(id);
        /* It is not parameter, if it is null */
        if (newVar == null)
            param = false;
        /* TODO: If it is not local and it is not param, than it shoud be checked
            if it is extern */
        if (!Checker.varLocalAndParamCheck(local, param, ctx, id))
            return null;
        
        /* Check if variable is initialized */
        Checker.varInitCheck(local, newVar, id, ctx);
        
        /* Get right stack position */
        stackPosition = curFuncAna.getAnyVariable(id).getStackPosition();
        
        nextFreeTemp = NasmTools.getNextFreeTemp();
        Writers.emitInstruction("mov", nextFreeTemp, stackPosition);
        
        return nextFreeTemp;
    }
    
    @Override
    public String visitInt(picoCParser.IntContext ctx) 
    {
        String val = ctx.INT().getText();
        String nextFreeTemp = NasmTools.getNextFreeTemp();
        Writers.emitInstruction("mov", nextFreeTemp, val);
        
        return nextFreeTemp;
    }
    
    
    /* relation could be '<' '<=' '>' '>=' */
    /* Result of some comparison is stored in least significant part of register
        and in order to perform cmp operation, proper part of registers must be
        compared. For example: if left expression returned eax, and right 
        returned bl, than bl must be converted to ebx (4 byte size) in order
        to do comparison between them */
    @Override
    public String visitRelation(picoCParser.RelationContext ctx) 
    {
        String left, right;
        int maxSizeOfVars;
        /* Visit expressions and try to recover if error ocurs */
        if ((left = visit(ctx.relationalExpression())) == null)
            return null;
        if ((right = visit(ctx.additiveExpression())) == null)
            return null;
        
        /* If sizes of variables doesn't match, than they need to be casted.
            Next three lines does nothing if sizes of variables match.
            Variable could be register or variable on stack. */
        maxSizeOfVars = NasmTools.maxSizeOfVars(left, right);
        left = NasmTools.castVariable(left, maxSizeOfVars);
        right = NasmTools.castVariable(right, maxSizeOfVars);
        
        Writers.emitInstruction("cmp", left, right);
        /* Because result of comparison only can be stored in low byte of register,
            left register needs to be casted to one. */
        left = NasmTools.castVariable(left, Constants.SIZE_OF_CHAR);
        /* Emit result of seting the least significant byte to register. */
        Writers.SetCCInstruction(left, ctx.rel.getType());
        
        NasmTools.free(right);
        return left;
    }
    
    /* rel could be '==' '!=' */
    @Override
    public String visitEquality(picoCParser.EqualityContext ctx) 
    {
        String left, right;
        int maxSizeOfVars;
        /* Visit expressions and try to recover if error ocurs */
        if ((left = visit(ctx.equalityExpression())) == null)
            return null;
        if ((right = visit(ctx.relationalExpression())) == null)
            return null;
        
        /* If sizes of variables doesn't match, than they need to be casted.
            Next three lines does nothing if sizes of variables match.
            Variable could be register or variable on stack. */
        maxSizeOfVars = NasmTools.maxSizeOfVars(left, right);
        left = NasmTools.castVariable(left, maxSizeOfVars);
        right = NasmTools.castVariable(right, maxSizeOfVars);
        
        Writers.emitInstruction("cmp", left, right);
        /* Because result of comparison only can be stored in low byte of register,
            left register needs to be casted to one. */
        left = NasmTools.castVariable(left, Constants.SIZE_OF_CHAR);
        /* Emit result of seting the least significant byte to register. */
        Writers.SetCCInstruction(left, ctx.rel.getType());
        
        NasmTools.free(right);
        return left;
    }

    @Override
    public String visitLogicalAND(picoCParser.LogicalANDContext ctx) 
    {
        String left, right, labelTrue, labelFalse, afterFalseLabel;
        int maxSizeOfVars;
        /* Visit left and right child and try to recover from error */
        if ((left = visit(ctx.logicalAndExpression())) == null)
            return null;
        if ((right = visit(ctx.equalityExpression())) == null) 
            return null;
        System.out.println("LogicalAND: Left: " + left + " right: " + right);
        
        /* If sizes of variables doesn't match, than they need to be casted.
            Next three lines does nothing if sizes of variables match.
            Variable could be register or variable on stack. */
        maxSizeOfVars = NasmTools.maxSizeOfVars(left, right);
        left = NasmTools.castVariable(left, maxSizeOfVars);
        right = NasmTools.castVariable(right, maxSizeOfVars);
        
        /* Call nasm tools to emit standard procedure for evaluating 'AND' expression */
        NasmTools.andExpressionEvaluation(left, right);
        
        /* Free right and return left */
        NasmTools.free(right);
        return left;
    }

    @Override
    public String visitLogicalOR(picoCParser.LogicalORContext ctx) 
    {
        String left, right, labelTrue, labelFalse, afterFalseLabel;
        int maxSizeOfVars;
        /* Visit left and right child and try to recover from error */
        if ((left = visit(ctx.logicalOrExpression())) == null)
            return null;
        if ((right = visit(ctx.logicalAndExpression())) == null) 
            return null;
        System.out.println("LogicalOR: Left: " + left + " right: " + right);
        
        /* If sizes of variables doesn't match, than they need to be casted.
            Next three lines does nothing if sizes of variables match.
            Variable could be register or variable on stack. */
        maxSizeOfVars = NasmTools.maxSizeOfVars(left, right);
        left = NasmTools.castVariable(left, maxSizeOfVars);
        right = NasmTools.castVariable(right, maxSizeOfVars);
        
        /* Call nasm tools to emit standard procedure for evaluating 'OR' expression */
        NasmTools.orExpressionEvaluation(left, right);
        
        /* Free right and return left */
        NasmTools.free(right);
        return left;
    }

    @Override
    public String visitSelectionStatement(picoCParser.SelectionStatementContext ctx) {
        String expr, right, labelIf, labelElse, labelAfterElse;
        long depthIfElse;
        /* Get depth of if else */
        depthIfElse = LabelsHelper.getIfDepth();
        /* Get all tree labels, to keep their counters equal for easier debugging */
        labelIf = LabelsHelper.getNextIfLabel();
        labelElse = LabelsHelper.getNextElseLabel();
        labelAfterElse = LabelsHelper.getNextAfterElseLabel();
        
        /* Visit statement and try to recover if error ocurs */
        if ((expr = visit(ctx.expression())) == null)
            return null;
        /* Free all registers taken by calculating the expression */
        NasmTools.freeAllRegisters();
        /* Here goes emiting  */
        Writers.emitInstruction("cmp", expr, "0");
        Writers.emitInstruction("je", labelElse);
        Writers.emitLabel(labelIf);
        /* Insert code within if statement */
        visit(ctx.statement(0));
        /* Jump to after else label if expression is true */
        Writers.emitInstruction("jmp", labelAfterElse);
        Writers.emitLabel(labelElse);
        /* Insert code within else statement if it is there */
        if (ctx.statement(1) != null)
            visit(ctx.statement(1));
        
        /* If LabelsHelper alow after else label, emit it */
        if (depthIfElse == 0)
            Writers.emitLabel(labelAfterElse);
        
        return null;
    }
    
    
    
}
