import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aleksandar
 */
public class TranslationVisitor extends picoCBaseVisitor<String> 
{
    /* List that contains informations about all functions
        that are beeing compiled */
    public static Map<String, FunctionsAnalyser> functions;
    
    /* Curent function context.  */
    public static FunctionsAnalyser curFuncAna;

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
        
        String memoryClass = ctx.typeSpecifier().getText();
        MemoryClassEnumeration memclass;
        memclass = FunctionsAnalyser.getMemoryClass(memoryClass);
        
        /* Chech weather function is already defined */
        if (functions.containsKey(name)) {
            String error = "Multiple definitions of " + name;
            System.err.println(error);
            CompilationControler.errorOcured(error);
        } else {
            /* Setup text segment for function definition */
            Writers.emitFunctionSetup(name);
            /* Create new function analyser object, and set it's type specifier
                to some type */
            FunctionsAnalyser fa = new FunctionsAnalyser(name);
            fa.setMemoryClass(memclass);
            
            functions.put(name, fa);       /* Add function to collection */
            
            FunctionsAnalyser.setFunctionInProcess(true); /* Walker in function context */
            FunctionsAnalyser.setInProcess(name); /* Change function that is processed */
            curFuncAna = fa;
        }
        /* Visit rest of the function. (visitChildren) */
        super.visitFunctionDefinition(ctx);
        /* Check for return statement */
        if (curFuncAna.getMemoryClass() != MemoryClassEnumeration.VOID && 
                !curFuncAna.isHasReturn()) 
        {
            CompilationControler.warningOcured
                ("Missing return statement in function " + curFuncAna.getFunctionName());
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
    
    /* Testing new approach */
//    @Override
//    public String visitDeclaration(picoCParser.DeclarationContext ctx) 
//    {
//        System.out.println("visitDeclaration");
//        /* Extern variable declaration */
//        if (!FunctionsAnalyser.isFunctionInProcess()) {
//            DataSegment.DeclareExtern(ctx);
//            return null;
//        } 
//        /* Variable name */
//        String name = ctx.ID().getText();
//        
//        /* Chech if variable is already declared */
//        if (curFuncAna.getLocalVariables().containsKey(name)) {
//            CompilationControler.errorOcured("Multiple declaration of " + name);
//            return null;    
//        }
//        
//        /* Get variable's memory class  */
//        MemoryClassEnumeration typeSpecifier;
//        typeSpecifier = FunctionsAnalyser.getMemoryClass(ctx.typeSpecifier().getText());
//        
//        /* Get stack position */
//        String stackPosition = curFuncAna.declareNew(typeSpecifier);
//        
//        /* Create new variable object */
//        Variable var = new Variable(name, stackPosition, false, typeSpecifier);
//        
//        /* Insert new variable in function analyser */
//        curFuncAna.getLocalVariables().put(name, var);
//        
//        /* Size in bytes */
//        int varSize = NasmTools.getSize(typeSpecifier);
//        
//        if (varSize == -1) {
//            CompilationControler.errorOcured("Void variable not alowed!");
//            return null;
//        }
//        
//        /* Emit assembly declaration */
//        Writers.emitInstruction("sub", "rsp", Integer.toString(varSize));
//        return null;
//    }

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
        if (curFuncAna.getLocalVariables().containsKey(name)) {
            CompilationControler.errorOcured("Multiple declaration of " + name);
            return null;    
        }
        
        /* Get variable's memory class  */
        MemoryClassEnumeration typeSpecifier;
        typeSpecifier = curFuncAna.getCurrentDeclaratorType();
        
        /* Get stack position */
        String stackPosition = curFuncAna.declareNew(typeSpecifier);
        
        /* Create new variable object */
        Variable var = new Variable(name, stackPosition, false, typeSpecifier);
        
        /* Insert new variable in function analyser */
        curFuncAna.getLocalVariables().put(name, var);
        
        /* Size in bytes */
        int varSize = NasmTools.getSize(typeSpecifier);
        
        if (varSize == -1) {
            CompilationControler.errorOcured("Void variable not alowed!");
            return null;
        }
        
        /* Emit assembly declaration */
        Writers.emitInstruction("sub", "rsp", Integer.toString(varSize));
        
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
        if ((var = curFuncAna.getLocalVariables().get(id)) == null) {
            CompilationControler.errorOcured("Variable +" + id + "not declared");
            return null;
        }
        /* Register where expression is calculated */
        String res = visit(ctx.expression());
        
        var.setInitialized(true);
        /* Emit assign.
            getStackPosition returns position of var on stack */
        Writers.emitInstruction("mov", var.getStackPosition(), res);
        
        if (NasmTools.isRegister(res))
            NasmTools.free(res);
        /* Return stack displacement */
        return var.getStackPosition();
    }

    
    
    @Override
    public String visitStatement(picoCParser.StatementContext ctx) {
        super.visitStatement(ctx); //To change body of generated methods, choose Tools | Templates.
        NasmTools.freeAllRegisters();
        return null;
    }
    
    
    @Override
    public String visitReturnStat(picoCParser.ReturnStatContext ctx) 
    {
        System.out.println("visitReturnStat");
        /* TODO: Check for type of return value! */
        String res = visit(ctx.expression());
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
        
        if (functionName.equals("printf")) {
            specialPrintfFunction(ctx, argumentList);
        } else {
            /* Default function implementation. Not done yet. */
            System.out.println("Ulaz u funkciju: " + functionName);
            if (argumentList != null)
                System.out.println("Argumenti: " + argumentList.toString());
        }
        return null;
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
        String leftExpr = visit(ctx.simpleExpression(0));
        String rightExpr = visit(ctx.simpleExpression(1));
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
        String leftExpr = visit(ctx.simpleExpression(0));
        String rightExpr = visit(ctx.simpleExpression(1));
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
        /* TODO: Determine displacement of variable ID */
        String id = ctx.ID().getText();
        /* Position of variable on stack */
        String stackPosition;
        /* Next free register */
        String nextFreeTemp;
        /* Get curent function in process */
        FunctionsAnalyser fa;
        fa = functions.get(FunctionsAnalyser.getInProcess());
        /* Check if variable exist */
        Variable newVar = fa.getLocalVariables().get(id);
        if (newVar == null) {
            CompilationControler.errorOcured("Variable + " + id + "not declared");
            return null;
        }
        /* Check if variable is initialized */
        if (!newVar.isInitialized()) {
            CompilationControler.warningOcured
                ("Variable " + id + " might not been initialized");
        }
        stackPosition = fa.getLocalVariables().get(id).getStackPosition();
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
