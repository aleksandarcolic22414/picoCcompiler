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
import tools.ExpressionObject;
import tools.LabelsMaker;
import tools.RelationHelper;

/**
 *
 * @author Aleksandar Colic
 */
public class TranslationVisitor extends picoCBaseVisitor<ExpressionObject> 
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
    public ExpressionObject visitCompilationUnit(picoCParser.CompilationUnitContext ctx) 
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
            /* If output is not needed for testing, then -> 
            System.exit(0); */
        }
        try {    
            Writers writers = new Writers();
            writers.writeOutput();
        } catch (IOException ex) {
            Logger.getLogger(TranslationVisitor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    @Override
    public ExpressionObject visitFunctionDefinition(picoCParser.FunctionDefinitionContext ctx) 
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
    public ExpressionObject visitParameterList(picoCParser.ParameterListContext ctx) 
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
    public ExpressionObject visitParameter(picoCParser.ParameterContext ctx) 
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
    public ExpressionObject visitDeclarationList(picoCParser.DeclarationListContext ctx) 
    {
        if (curFuncAna == null) {
            DataSegment.DeclareExtern(null);
            return null;
        }
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
    public ExpressionObject visitDeclaration(picoCParser.DeclarationContext ctx) 
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
    public ExpressionObject visitAssign(picoCParser.AssignContext ctx) 
    {
        /* Expression */
        ExpressionObject expr;
        /* Get id value */
        String id = ctx.ID().getText();
        /* Get variable context */
        Variable var = curFuncAna.getAnyVariable(id);
        /* Check if it is declared */
        if (!Checker.varDeclCheck(ctx, id, var))
            return null;
        
        /* Try to recover from error. */
        if ((expr = visit(ctx.assignmentExpression())) == null) 
            return null;
        expr.comparisonCheck();
        
        
        var.setInitialized(true);
        
        /* If size of return register doesn't match size of var than it needs
            to be casted, so size of variable is taken and later, if it is register,
            it is casted to proper size */
        int sizeOfVar = NasmTools.getSize(var.getTypeSpecifier());
        /* Get position of variable */
        String stackPos = var.getStackPosition();
        /* Emit assign if right operand is register */
            
        if (expr.isRegister()) {
            expr.castVariable(var.getTypeSpecifier());
            Writers.emitInstruction("mov", stackPos, expr.getText());
            NasmTools.free(expr.getText());
        } else if (expr.isInteger()) {
            Writers.emitInstruction("mov", stackPos, expr.getText());
        } else {
            String temp = NasmTools.getNextFreeTemp();
            Writers.emitInstruction("mov", temp, expr.getText());
            Writers.emitInstruction("mov", stackPos, temp);
            NasmTools.free(temp);
        }
        
        /* Return new Object */
        return new ExpressionObject
            (stackPos, 
             var.getTypeSpecifier(), 
             sizeOfVar
            );
    }
    
    @Override
    public ExpressionObject visitStatement(picoCParser.StatementContext ctx) 
    {
        super.visitStatement(ctx); 
        NasmTools.freeAllRegisters();
        return null;
    }
    
    @Override
    public ExpressionObject visitReturn(picoCParser.ReturnContext ctx) 
    {
        ExpressionObject expr;
        /* Try to recover from error */
        if ((expr = visit(ctx.expression())) == null)
            return null;
        expr.comparisonCheck();
        /* Set function has return  */
        curFuncAna.setHasReturn(true);
        /* mov result to eax for return if result is not eax */
        if (!expr.isRegister())
            Writers.emitInstruction("mov", NasmTools.STRING_EAX, expr.getText());
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
    public ExpressionObject visitFunctionCall(picoCParser.FunctionCallContext ctx) 
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
        return new ExpressionObject
            (nextFreeTemp, 
             MemoryClassEnumeration.INT, 
             ExpressionObject.REGISTER
            );
    }

    @Override
    public ExpressionObject visitArgumentList(picoCParser.ArgumentListContext ctx) 
    {
        return super.visitArgumentList(ctx);
    }

    @Override
    public ExpressionObject visitArgument(picoCParser.ArgumentContext ctx) 
    {
        /* If it is not string literal, then return value is register of variable */
        if (ctx.STRING_LITERAL() == null) {
            ExpressionObject expr = visit(ctx.assignmentExpression());
            expr.comparisonCheck();
            
            /* Free "a" if it is register */
            if (expr.isRegister())
                expr.freeRegister();
            return expr;
        }
        String strlit = ctx.STRING_LITERAL().getText();
        String literalName = NasmTools.defineStringLiteral(strlit);
        /* Return string literal */
        return new ExpressionObject
            (literalName, 
             MemoryClassEnumeration.POINTER, 
             ExpressionObject.STRING_LITERAL
            );
    }
    
    @Override
    public ExpressionObject visitExpression(picoCParser.ExpressionContext ctx)
    {
        ExpressionObject expr = super.visitExpression(ctx);
        return expr;
    }

    @Override
    public ExpressionObject visitParens(picoCParser.ParensContext ctx) 
    {
        ExpressionObject expr = visit(ctx.expression());
        return expr;
    }
    
    @Override
    public ExpressionObject visitNegation(picoCParser.NegationContext ctx) 
    {
        /* Visit rest of expression */
        ExpressionObject expr = super.visitNegation(ctx);
        expr.comparisonCheck();
        /* Negate it */
        if (!expr.isRegister() || !expr.isStackVariable())
            expr.putInRegister();
        Writers.emitInstruction("neg", expr.getText());
        
        return expr;
    }
    
    @Override
    public ExpressionObject visitAddSub(picoCParser.AddSubContext ctx) 
    {
        ExpressionObject leftExpr, rightExpr;
        String nextFreeTemp;
        /* Try to visit children and recover if error ocured */
        if ((leftExpr = visit(ctx.additiveExpression())) == null)
            return null;
        leftExpr.comparisonCheck();
        
        if ((rightExpr = visit(ctx.multiplicativeExpression())) == null)
            return null;
        rightExpr.comparisonCheck();
       
      
        /* Before all calculations, if left and right operand are numbers, 
            let java do the calculation and save some program's time */
        if (leftExpr.isInteger() && rightExpr.isInteger()) {
            String res = NasmTools.calculate
                (leftExpr.getText(), rightExpr.getText(), ctx.op.getType(), ctx);
            leftExpr.setText(res);
            return leftExpr;
        }
        /* If there is free registers, and leftExpr is variable, than it needs
            to be moved to one */
        if (!leftExpr.isRegister() && NasmTools.hasFreeRegisters()) {
            nextFreeTemp = NasmTools.getNextFreeTemp();
            Writers.emitInstruction("mov", nextFreeTemp, leftExpr.getText());
            leftExpr.setToRegister();
            leftExpr.setText(nextFreeTemp);
        }
        
        String operation = NasmTools.getOperation(ctx.op.getType());
        
        /* If left operand is not register, then it needs to be moved to one.
            It's moved to eax, but first eax is saved on stack. */
        if (!leftExpr.isRegister()) {
            nextFreeTemp = NasmTools.getNextFreeTemp();
            Writers.emitInstruction("mov", nextFreeTemp, "eax");
            Writers.emitInstruction("mov", "eax", leftExpr.getText());
            Writers.emitInstruction(operation, "eax", rightExpr.getText());
            Writers.emitInstruction("mov", leftExpr.getText(), "eax");
            Writers.emitInstruction("mov", "eax", nextFreeTemp);
            NasmTools.free(nextFreeTemp);
        } else
            Writers.emitInstruction(operation, leftExpr.getText(), rightExpr.getText());
        
        if (rightExpr.isRegister())
            rightExpr.freeRegister();
        
        return leftExpr;
    }

    @Override
    public ExpressionObject visitMulDivMod(picoCParser.MulDivModContext ctx) 
    {
        boolean fake = false;
        String nextFreeTemp;
        ExpressionObject leftExpr, rightExpr;
        int operation = ctx.op.getType();
        /* Try to visit children and recover if error ocured */
        if ((leftExpr = visit(ctx.multiplicativeExpression())) == null)
            return null;
        leftExpr.comparisonCheck();
        if ((rightExpr = visit(ctx.unaryExpression())) == null)
            return null;
        rightExpr.comparisonCheck();
        /* Before all calculations, if left and right operand are numbers, 
            let java do the calculation and save some program's time */
        if (leftExpr.isInteger() && rightExpr.isInteger()) {
            String res = NasmTools.calculate
                (leftExpr.getText(), rightExpr.getText(), operation, ctx);
            leftExpr.setText(res);
            return leftExpr;
        }
        /* If there is free registers, and leftExpr is variable, than it needs
            to be moved to one */
        if (!leftExpr.isRegister() && NasmTools.hasFreeRegisters()) {
            nextFreeTemp = NasmTools.getNextFreeTemp();
            Writers.emitInstruction("mov", nextFreeTemp, leftExpr.getText());
            leftExpr.setToRegister();
            leftExpr.setText(nextFreeTemp);
        }
        /* If right operand is integer number, than it needs to be moved to 
            regiser or stack. */
        if (rightExpr.isInteger()) {
            nextFreeTemp = NasmTools.getNextFreeTemp();
            Writers.emitInstruction("mov", nextFreeTemp, rightExpr.getText());
            rightExpr.setToRegister();
            rightExpr.setText(nextFreeTemp);
        }
        
        
        String s1, s2;
        String fakelyTaken = null;
        if (operation == picoCParser.MUL) {
            /* Chech wheather leftExpr is register. If it's not then it needs to be
                moved to one and then multiplied */
            if (!leftExpr.isRegister()) {
                nextFreeTemp = NasmTools.getNextFreeTemp();
                Writers.emitInstruction("mov", nextFreeTemp, "eax");
                Writers.emitInstruction("mov", "eax", leftExpr.getText());
                Writers.emitInstruction("imul", "eax", rightExpr.getText());
                Writers.emitInstruction("mov", leftExpr.getText(), "eax");
                Writers.emitInstruction("mov", "eax", nextFreeTemp);
                NasmTools.free(nextFreeTemp);
            } else
                Writers.emitInstruction("imul", leftExpr.getText(), rightExpr.getText());
        } else { /* It's div or mod */
            if (leftExpr.equals("eax")) {
                if (NasmTools.isTakenRegisterDREG()) { /* Never true, but stil */
                    nextFreeTemp = NasmTools.getNextFreeTemp();
                    Writers.emitInstruction("mov", nextFreeTemp, "edx");
                    Writers.emitInstruction("cdq");
                    Writers.emitInstruction("idiv", rightExpr.getText());
                    if (operation == picoCParser.MOD) /* If it's mod, move remainder to eax */
                        Writers.emitInstruction("mov", "eax", "edx");
                    Writers.emitInstruction("mov", "edx", nextFreeTemp);
                    NasmTools.free(nextFreeTemp);
                } else {
                    Writers.emitInstruction("cdq");
                    Writers.emitInstruction("idiv", rightExpr.getText());
                    if (operation == picoCParser.MOD) /* If it's mod, move remainder to eax */
                        Writers.emitInstruction("mov", "eax", "edx");
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
                    /* setting eax and edx for div */
                    Writers.emitInstruction("mov", "eax", leftExpr.getText()); 
                    Writers.emitInstruction("cdq");
                    
                    /* If right operand of division is edx, than eax needs
                        to be divided by moved edx, which is s2 */
                    if (!rightExpr.getText().equals("edx"))
                        Writers.emitInstruction("idiv", rightExpr.getText());
                    else
                        Writers.emitInstruction("idiv", s2);
                    /* If it's div operation, move result (eax) to leftExpr, 
                        else, move remainder(edx) to leftExpr */
                    if (ctx.op.getType() == picoCParser.DIV) 
                        Writers.emitInstruction("mov", leftExpr.getText(), "eax");
                    if (ctx.op.getType() == picoCParser.MOD) 
                        Writers.emitInstruction("mov", leftExpr.getText(), "edx");
                     /* restoring values */
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
        
        if (rightExpr.isRegister())
            rightExpr.freeRegister();
        return leftExpr;
    }

    /* TODO: Implement direct stack position to be returned. 
        Division must be changed in weird case of EDXTaken. */
    @Override
    public ExpressionObject visitId(picoCParser.IdContext ctx) 
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
        if (newVar == null)
            return null;
        /* Check if variable is initialized */
        Checker.varInitCheck(local, newVar, id, ctx);
        
        /* Get right stack position */
        stackPosition = newVar.getStackPosition();
        
        return new ExpressionObject
            (stackPosition, 
             newVar.getTypeSpecifier(),    // TODO: Check external variables
             ExpressionObject.VAR_STACK
            );
    }
    
    @Override
    public ExpressionObject visitInt(picoCParser.IntContext ctx) 
    {
        String val = ctx.INT().getText();
        return new ExpressionObject
            (val, 
             MemoryClassEnumeration.INT, 
             ExpressionObject.INTEGER
            );
    }
    
    
    /* relation could be '<' '<=' '>' '>=' */
    /* Result of some comparison is stored in least significant part of register
        and in order to perform cmp operation, proper part of registers must be
        compared. For example: if left expression returned eax, and right 
        returned bl, than bl must be converted to ebx (4 byte size) in order
        to do comparison between them */
    @Override
    public ExpressionObject visitRelation(picoCParser.RelationContext ctx) 
    {
        ExpressionObject left, right;
        int maxSizeOfVars;
        /* Visit expressions and try to recover if error ocurs */
        if ((left = visit(ctx.relationalExpression())) == null)
            return null;
        left.comparisonCheck();
        if ((right = visit(ctx.additiveExpression())) == null)
            return null;
        right.comparisonCheck();
        
        /* If left and right expressions are stack variable, than one need to be
            moved to register in order to do cmp operation */
        if (left.isStackVariable() && right.isStackVariable())
            left.putInRegister();
        
        
        /* If left and right expressions are Integer number, than one need to be
            moved to register in order to do cmp operation */
        if (left.isInteger() && right.isInteger())
            left.putInRegister();
        
        
        /* If sizes of variables doesn't match, than they need to be casted.
            Next line does nothing if sizes of variables match.
            Variable could be register or variable on stack. */
        ExpressionObject.castVariablesToMaxSize(left, right);
        /* Emit comparison */
        Writers.emitInstruction("cmp", left.getText(), right.getText());
        
        left.setCompared(true);
        right.setCompared(true);
        RelationHelper.setComparisonDone();
        RelationHelper.setRelation(ctx.rel.getType());
        
        /* Try to optimize */
        return ExpressionObject.freeOneOfTwo(left, right);
    }
    
    /* rel could be '==' '!=' */
    @Override
    public ExpressionObject visitEquality(picoCParser.EqualityContext ctx) 
    {
        ExpressionObject left, right;
        int maxSizeOfVars;
        /* Visit expressions and try to recover if error ocurs */
        if ((left = visit(ctx.equalityExpression())) == null)
            return null;
        left.comparisonCheck();
        if ((right = visit(ctx.relationalExpression())) == null)
            return null;
        right.comparisonCheck();
        
        /* If left and right expressions are stack variable, than one need to be
            moved to register in order to do cmp operation */
        if (left.isStackVariable() && right.isStackVariable()) {
            String nextFreeTemp = NasmTools.getNextFreeTemp();
            Writers.emitInstruction("mov", nextFreeTemp, left.getText());
            left.setToRegister();
            left.setText(nextFreeTemp);
        }
        /* If left and right expressions are Integer number, than one need to be
            moved to register in order to do cmp operation */
        if (left.isInteger() && right.isInteger()) {
            String nextFreeTemp = NasmTools.getNextFreeTemp();
            Writers.emitInstruction("mov", nextFreeTemp, left.getText());
            left.setToRegister();
            left.setText(nextFreeTemp);
        }
        /* If sizes of variables doesn't match, than they need to be casted.
            Next line does nothing if sizes of variables match.
            Variable could be register or variable on stack. */
        ExpressionObject.castVariablesToMaxSize(left, right);
        /* Emit comparison */
        Writers.emitInstruction("cmp", left.getText(), right.getText());
        
        left.setCompared(true);
        right.setCompared(true);
        RelationHelper.setComparisonDone();
        RelationHelper.setRelation(ctx.rel.getType());
        
        /* Try to optimize */
        return ExpressionObject.freeOneOfTwo(left, right);
    }

    @Override
    public ExpressionObject visitLogicalAND(picoCParser.LogicalANDContext ctx) 
    {
        ExpressionObject left, right;
        String labelTrue, labelFalse, afterFalseLabel;
        int maxSizeOfVars;
        /* Visit left and right child and try to recover from error */
        if ((left = visit(ctx.logicalAndExpression())) == null)
            return null;
        left.comparisonCheck();
        if ((right = visit(ctx.equalityExpression())) == null) 
            return null;
        right.comparisonCheck();
        /* If left expression is not register it needs to be moved to one */
        if (!left.isRegister())
            left.putInRegister();
        
        /* If sizes of variables doesn't match, than they need to be casted.
            Next line does nothing if sizes of variables match.
            Variable could be register or variable on stack. */
        ExpressionObject.castVariablesToMaxSize(left, right);
        
        /* Call nasm tools to emit standard procedure for evaluating 'AND' expression */
        NasmTools.andExpressionEvaluation(left.getText(), right.getText());
        
        /* Free right if it is regiser and return left */
        if (right.isRegister())
            right.freeRegister();
        return left;
    }

    @Override
    public ExpressionObject visitLogicalOR(picoCParser.LogicalORContext ctx) 
    {
        ExpressionObject left, right;
        String labelTrue, labelFalse, afterFalseLabel;
        int maxSizeOfVars;
        /* Visit left and right child and try to recover from error */
        if ((left = visit(ctx.logicalOrExpression())) == null)
            return null;
        left.comparisonCheck();
        if ((right = visit(ctx.logicalAndExpression())) == null) 
            return null;
        right.comparisonCheck();
        /* If left expression is not register it needs to be moved to one */
        if (!left.isRegister())
            left.putInRegister();
        
        /* If sizes of variables doesn't match, than they need to be casted.
            Next three lines does nothing if sizes of variables match.
            Variable could be register or variable on stack. */
        ExpressionObject.castVariablesToMaxSize(left, right);
        
        /* Call nasm tools to emit standard procedure for evaluating 'OR' expression */
        NasmTools.orExpressionEvaluation(left.getText(), right.getText());
        
        /* Free right and return left */
        if (right.isRegister())
            right.freeRegister();
        return left;
    }

    @Override
    public ExpressionObject visitSelectionStatement(picoCParser.SelectionStatementContext ctx) 
    {
        ExpressionObject expr;
        String right, labelIf, labelElse, labelAfterElse, jump;
        long depthIfElse;
        /* Get depth of if else */
        depthIfElse = LabelsMaker.getIfDepth();
        /* Get all tree labels, to keep their counters equal for easier debugging */
        labelIf = LabelsMaker.getNextIfLabel();
        labelElse = LabelsMaker.getNextElseLabel();
        labelAfterElse = LabelsMaker.getNextAfterElseLabel();
        
        /* Visit statement and try to recover if error ocurs */
        if ((expr = visit(ctx.expression())) == null)
            return null;
        if (!expr.isCompared())
            NasmTools.compareWithZero(expr);
        /* Free all registers taken by calculating the expression */
        NasmTools.freeAllRegisters();
        /* Get opposite jump */
        jump = RelationHelper.getFalseJump();
        /* Here goes emiting  */
        Writers.emitInstruction(jump, labelElse);
        Writers.emitLabel(labelIf);
        /* Insert code within if statement */
        visit(ctx.statement(0));
        /* Jump to after else label if expression is true */
        Writers.emitInstruction(Constants.JUMP_UNCODITIONAL, labelAfterElse);
        Writers.emitLabel(labelElse);
        /* Insert code within else statement if it is there */
        if (ctx.statement(1) != null)
            visit(ctx.statement(1));
        
        /* Stop multiple emiting of same label */
        if (depthIfElse == 0)
            Writers.emitLabel(labelAfterElse);
        
        RelationHelper.setComparisonUsed();
        expr.setCompared(false);
        return null;
    }

    @Override
    public ExpressionObject visitPreInc(picoCParser.PreIncContext ctx) 
    {
        ExpressionObject res;
        /* Visit expression and try to recover from error */
        if ((res = visit(ctx.unaryExpression())) == null)   
            return null;
        res.comparisonCheck();
        /* Check if it is variable and add one to result */
        if (!Checker.checkPreInc(res, ctx))
            return null;
        Writers.emitInstruction("add", res.getText(), "1");
        
        return res;
    }

    @Override
    public ExpressionObject visitPreDec(picoCParser.PreDecContext ctx) 
    {
        ExpressionObject res;
        /* Visit expression and try to recover from error */
        if ((res = visit(ctx.unaryExpression())) == null)   
            return null;
        res.comparisonCheck();
        /* Check if it is variable and add one to result */
        if (!Checker.checkPreDec(res, ctx))
            return null;
        Writers.emitInstruction("sub", res.getText(), "1");
        
        return res;
    }
    
    /* All that needs to be done for post incrementation is to first 
        put variable's value in some register for further calculation, 
        and than increment that variable. */
    @Override
    public ExpressionObject visitPostInc(picoCParser.PostIncContext ctx) 
    {
        ExpressionObject res;
        String stackPosition;
        /* Visit expression and try to recover from error */
        if ((res = visit(ctx.postfixExpression())) == null)   
            return null;
        res.comparisonCheck();
        /* Check if it is variable */
        if (!Checker.checkPostInc(res, ctx))
            return null;
        
        /* Get next free register or stack position */
        stackPosition = res.getText();
        res.putInRegister();
        Writers.emitInstruction("add", stackPosition, "1");
        
        return res;
    }

    /* All that needs to be done for post decrementation is to first 
        put variable's value in some register for further calculation, 
        and than decrement that variable. */
    @Override
    public ExpressionObject visitPostDec(picoCParser.PostDecContext ctx) 
    {
        ExpressionObject res;
        String stackPosition;
        /* Visit expression and try to recover from error */
        if ((res = visit(ctx.postfixExpression())) == null)   
            return null;
        res.comparisonCheck();
        /* Check if it is variable */
        if (!Checker.checkPostDec(res, ctx))
            return null;
        
        /* Get next free register or stack position */
        stackPosition = res.getText();
        res.putInRegister();
        Writers.emitInstruction("sub", stackPosition, "1");
        
        return res;
    }

    @Override
    public ExpressionObject visitIterationStatement
    (picoCParser.IterationStatementContext ctx) 
    {
        ExpressionObject expr, condition;
        String jump; 
        String forStartLabel, forCheckLabel, forIncrementLabel, forEndLabel;
        forStartLabel = LabelsMaker.getNextForStartLabel();
        forCheckLabel = LabelsMaker.getNextForCheckLabel();
        forIncrementLabel = LabelsMaker.getNextForIncerementLabel();
        forEndLabel = LabelsMaker.getNextForEndLabel();
        expr = condition = null;
        LabelsMaker.setCurrentForLabels(forIncrementLabel, forEndLabel);
        /* Do first expression witch is "initialization" (it could be 
            any expression off course) */
        if (ctx.expression(0) != null)
            expr = visit(ctx.expression(0));
        if (expr != null)
            expr.comparisonCheck();
        /* Jump to check condition */
        Writers.emitInstruction(Constants.JUMP_UNCODITIONAL, forCheckLabel);
        /* Loop start */
        Writers.emitLabel(forStartLabel);
        /* Visit for body */
        if (ctx.statement() != null)
            visit(ctx.statement());
        /* Increment label */
        Writers.emitLabel(forIncrementLabel);
        if (ctx.expression(2) != null)
            visit(ctx.expression(2));
        /* Check label */
        Writers.emitLabel(forCheckLabel);
        /* Default jump */
        jump = Constants.JUMP_UNCODITIONAL;        
        /* If comparison is not done, than result of visiting must be
            compared to 0. Something like for (i = 100; i; --i); */
        if (ctx.expression(1) != null) {
            condition = visit(ctx.expression(1));
            if (!condition.isCompared()) {
                NasmTools.compareWithZero(condition);
                jump = RelationHelper.getFalseJump();
            } else
                jump = RelationHelper.getTrueJump();
        }
        Writers.emitInstruction(jump, forStartLabel);
        Writers.emitLabel(forEndLabel);
        LabelsMaker.unsetCurrentForLabels(forIncrementLabel, forEndLabel);
        return null;
    }    

    @Override
    public ExpressionObject visitBreak(picoCParser.BreakContext ctx) 
    {
        /* break instruction is just uncodition jump to the end of loop*/
        String label = LabelsMaker.getLastForEndLabel();
        Writers.emitInstruction("jmp", label);
        return null;
    }

    @Override
    public ExpressionObject visitContinue(picoCParser.ContinueContext ctx) 
    {
        /* continue instruction is just uncodition jump to the incrementation of loop*/
        String label = LabelsMaker.getLastForIncrementLabel();
        Writers.emitInstruction("jmp", label);
        return null;
    }
    
    
    
}
