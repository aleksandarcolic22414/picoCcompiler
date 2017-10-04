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
        System.out.println("visitCompilationUnit");
        super.visitCompilationUnit(ctx); //To change body of generated methods, choose Tools | Templates.
        
        if (CompilationControler.warnings != 0) {
            System.err.println("Warnings : " + CompilationControler.warnings);
        }
        /* Check wheather compilation is successful */
        if (CompilationControler.errors == 0) {
            System.out.println("Compilation successful!");
        } else {
            System.err.println("Errors: " + CompilationControler.errors);
            System.err.println("Compilation failed!");
            /* Just if output is not needed for testing */
//            System.exit(0);
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
        System.out.println("visitFunctionDefinition");
        String name = ctx.functionName().getText();
        /* Get memory class of function */
        String memoryClass = ctx.typeSpecifier().getText();
        MemoryClassEnumeration memclass;
        memclass = FunctionsAnalyser.getMemoryClass(memoryClass);
        
        /* Chech weather function is already defined */
        if (functions.containsKey(name)) {
            String error = "Multiple definitions of " + name;
            System.err.println(error);
            CompilationControler.errorOcured
                (ctx.getStart(), 
                        name,
                            error);
            /* Try to recover if multiple definitions of function occurs */
            return null;
        } 
        /* Create new function analyser object, and set it's type specifier
        to some type */
        FunctionsAnalyser fa = new FunctionsAnalyser(name);
        fa.setMemoryClass(memclass);
        
        functions.put(name, fa);       /* Add function to collection */
        
        FunctionsAnalyser.setFunctionInProcess(true); /* Walker in function context */
        FunctionsAnalyser.setInProcess(name); /* Change function that is processed */
        curFuncAna = fa;
        /* Make room for local variables and arguments.
            The information about whole space on stack needed for
            local variables and parameters is hold within map of function in class
            TranslationListener. */
        int localsAndArgsSize = 
                TranslationListener.lisFuncAna.get(name).getSpaceForLocals()
                + TranslationListener.lisFuncAna.get(name).getSpaceForParams();
        String ls = Integer.toString(localsAndArgsSize);
        
        /* Setup text segment for function definition */
        Writers.emitFunctionSetup(name);
        /* Substract number of bytes needed for variables if there is any */
        if (localsAndArgsSize > 0)
            Writers.emitInstruction("sub", "rsp", ls);    
        /* Visit rest of the function. (visitChildren) */
        super.visitFunctionDefinition(ctx);
        
        /* Check for return statement */
        if (curFuncAna.getMemoryClass() != MemoryClassEnumeration.VOID && 
                !curFuncAna.isHasReturn()) 
        {
            CompilationControler.warningOcured
                (ctx.getStart(), 
                        curFuncAna.getFunctionName(),
                            "Missing return statement in function " + curFuncAna.getFunctionName());
        }
        
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
    public String visitParameterList(picoCParser.ParameterListContext ctx) {
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
    public String visitParameter(picoCParser.ParameterContext ctx) {
        /* Variable name */
        String name;
        name = ctx.ID().getText();
        /* Chech if variable is already declared */
        if (curFuncAna.getParameterVariables().containsKey(name)) {
            CompilationControler.errorOcured
                (ctx.getStart(), 
                        curFuncAna.getFunctionName(),
                            "Two or more params with the same name: " + name);
            return null;    
        }
        
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
        
        if (varSize == -1) {
            CompilationControler.errorOcured
                (ctx.getStart(),
                        curFuncAna.getFunctionName(),
                            "Void variable not alowed!");
            return null;
        }
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
        System.out.println("enterDeclaration");
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
        if (curFuncAna.getLocalVariables().containsKey(name)
                || curFuncAna.getParameterVariables().containsKey(name)) 
        {
            CompilationControler.errorOcured
                (ctx.getStart(), 
                        curFuncAna.getFunctionName(),
                            "Multiple declaration of " + name);
            return null;    
        }
        
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
        
        if (varSize == -1) {
            CompilationControler.errorOcured
                (ctx.getStart(),
                        curFuncAna.getFunctionName(),
                            "Void variable not alowed!");
            return null;
        }
        
        return super.visitDeclaration(ctx);
    }
    
    
    
    @Override
    public String visitAssignment(picoCParser.AssignmentContext ctx)
    {
        System.out.println("visitAssignment");
        /* Get id value */
        String id = ctx.ID().getText();
        /* Get variable context */
        Variable var;
        /* Check if it is declared */
        if ((var = curFuncAna.getAnyVariable(id)) == null) {
            CompilationControler.errorOcured(
                    ctx.getStart(), 
                        curFuncAna.getFunctionName(),
                            "Variable " + "'" + id + "'" + " not declared");
            return null;
        }
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
        System.out.println("visitReturnStat");
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

    @Override
    public String visitFunctionCall(picoCParser.FunctionCallContext ctx) 
    {
        System.out.println("visitFunctionCall");
        
        String functionName = ctx.functionName().getText();
        List<picoCParser.ArgumentContext> argumentList;
        
        if (ctx.argumentList() != null) {
            argumentList = ctx.argumentList().argument();
        } else
            argumentList = null;
        
        /* Check if function exist and try to recover if it doesn't 
            It is skiped for now. */
//        if ((funcAnalyser = functions.get(functionName)) == null) {
//            CompilationControler.errorOcured
//                (ctx.getStart(), functionName,
//                        "Function " + functionName + " doesn't exist");
//                return null;
//        }

        if (functionName.equals("printf")) {
            specialPrintfFunction(ctx, argumentList);
            return null;
        } else {
            NasmTools.saveRegistersOnStack();
            if (argumentList != null)
                NasmTools.moveArgsToRegisters(argumentList);
            Writers.emitInstruction("call", functionName);
            NasmTools.restoreRegisters();
        }
        /* TODO: See in witch register is return value from function */
        return "eax";
    }
    
    
    @Override
    public String visitExpression(picoCParser.ExpressionContext ctx)
    {
        System.out.println("visitExpression");
        /* Writers.emitInstruction("xor", "eax", "eax");
            doesn't need to be done, because for expression
            the first instruction is  always "mov", and eax register shoud be saved
            before enterance if it holds some significant value. */
        return visit(ctx.simpleExpression());
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
        System.out.println("Context: visitID Val: " + ctx.getText());
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
        if (!local && !param) {
            CompilationControler.errorOcured
                (ctx.getStart(), curFuncAna.getFunctionName(),
                        "Variable " + "'" + id  + "'" 
                        + " is not declared");
            return null;
        }
        /* Check if variable is initialized */
        if (local && !newVar.isInitialized()) {
            CompilationControler.warningOcured
                (ctx.getStart(), curFuncAna.getFunctionName(),
                        "Variable " + "'" + id  + "'" 
                        + " is used uninitialized");
        }
        /* Get right stack position */
        if (local)
            stackPosition = curFuncAna.getLocalVariables().get(id).getStackPosition();
        else
            stackPosition = curFuncAna.getParameterVariables().get(id).getStackPosition();
        
        nextFreeTemp = NasmTools.getNextFreeTemp();
        Writers.emitInstruction("mov", nextFreeTemp, stackPosition);
        
        return nextFreeTemp;
    }
    
    @Override
    public String visitInt(picoCParser.IntContext ctx) 
    {
        System.out.println("Context: visitInt Val: " + ctx.getText());
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
    
    
    private void specialPrintfFunction
    (picoCParser.FunctionCallContext ctx, 
    List<picoCParser.ArgumentContext> arguments)  
    {
        String strFormat, strVal;
        TokenEnumeration tokenType;
        strVal = strFormat = null;
        tokenType = null;
        
        if (arguments == null) {
            System.err.println("Empty printf function!");
            return;
        } else if (arguments.size() > 2) {
            System.err.println("To many arguments in funtion printf; Expected 2 at most..");
            return;
        } else {
            strFormat = arguments.get(0).getText();         /* Take first arg */
            Writers.emitPrintfFormatSetup(strFormat);      /* Pass it for further compilation */
            if (arguments.get(1) != null) {
                if (arguments.get(1).STRING_LITERAL() != null) {  /* STRING_LITERAK token hit */
                    strVal = arguments.get(1).STRING_LITERAL().getText();
                    tokenType = TokenEnumeration.STRING_LITERAL;
                } else if (arguments.get(1).INT() != null) {    /* INT token hit */
                    strVal = arguments.get(1).INT().getText();
                    tokenType = TokenEnumeration.INT;
                } else if (arguments.get(1).ID() != null) { /* ID token hit */
                    strVal = arguments.get(1).ID().getText();
                    tokenType = TokenEnumeration.ID;
                }
                /* Pass argument value for further compilation */
                Writers.emitPrintfArgumentsSetup(strVal, tokenType);
            }
        }    
            
        Writers.emitPrintfCall();
    }
    
}
