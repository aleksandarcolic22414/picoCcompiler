import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        if (ctx.assignment() == null)
            name = ctx.ID().getText();
        else
            name = ctx.assignment().ID().getText();
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
    public String visitAssignment(picoCParser.AssignmentContext ctx)
    {
        /* Get id value */
        String id = ctx.ID().getText();
        /* Get variable context */
        Variable var;
        /* Check if it is declared */
        if (!Checker.varDeclCheck(ctx, id, var = curFuncAna.getAnyVariable(id)))
            return null;
        
        /* Register where expression is calculated */
        String res;
        /* Try to recover from error. */
        if ((res = visit(ctx.expression())) == null) 
            return null;
        
        var.setInitialized(true);
        /* Emit assign if right operand is register.
            getStackPosition returns position of var on stack */
        if (NasmTools.isRegister(res)) {
            Writers.emitInstruction("mov", var.getStackPosition(), res);
            NasmTools.free(res);
        } else {
            String temp = NasmTools.getNextFreeTemp();
            Writers.emitInstruction("mov", temp, res);
            Writers.emitInstruction("mov", var.getStackPosition(), temp);
            NasmTools.free(temp);
        }
        /* Return stack displacement */
        return var.getStackPosition();
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
        just check witch is next register (or position on stack if registers are filed)
        witch can hold value. After that, register's are saved on stack, 
        but before they are restored, real method for getting
        registers is called witch is getNextFreeTemp. It can't be done without
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
        
        /* Little thing: Get next free register for further calculation
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
        
        /* TODO: See in witch part of register is return value from function */
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
            String res = visit(ctx.expression());
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
        /* Writers.emitInstruction("xor", "eax", "eax");
            doesn't need to be done, because for expression
            the first instruction is  always "mov", and eax register shoud be saved
            before enterance if it holds some significant value. */
        return visit(ctx.simpleExpression());
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
        if ((leftExpr = visit(ctx.simpleExpression(0))) == null)
            return null;
        if ((rightExpr = visit(ctx.simpleExpression(1))) == null)
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
        if ((leftExpr = visit(ctx.simpleExpression(0))) == null)
            return null;
        if ((rightExpr = visit(ctx.simpleExpression(1))) == null)
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
                if (NasmTools.isTakenRegisterEDX()) { /* Never true, but stil */
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
                
                if (!NasmTools.isTakenRegisterEDX()) {
                    fake = true;
                    fakelyTaken = NasmTools.getNextFreeTemp();
                }
                /* Always true -> */
                if (NasmTools.isTakenRegisterEAX() && NasmTools.isTakenRegisterEDX()) {
                    s1 = NasmTools.getNextFreeTemp();
                    Writers.emitInstruction("mov", s1, "eax");  /* save eax value into s1 */
                    s2 = NasmTools.getNextFreeTemp();
                    Writers.emitInstruction("mov", s2, "edx");  /* save edx value into s2 */
                    
                    Writers.emitInstruction("mov", "eax", leftExpr); /* setting eax and edx for div */
                    Writers.emitInstruction("cdq");
                    
                    /* If right operand od division is edx, than eax needs
                        to be divided by moved edx, witch is s2 */
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
    
    @Override
    public String visitParens(picoCParser.ParensContext ctx) 
    {
        String s = visit(ctx.simpleExpression());
        return s;
    }
}
