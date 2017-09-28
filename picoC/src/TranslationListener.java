
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aleksandar
 */
public class TranslationListener extends picoCBaseListener 
{
    picoCParser parser;
    TranslationVisitor visitor;
    /* List that contains informations about all functions
        that are beeing compiled */
    public static Map<String, FunctionsAnalyser> functions;
    
    /* Curent function context.  */
    public static FunctionsAnalyser curFuncAna;
    
    public TranslationListener(picoCParser parser, TranslationVisitor visitor) 
    {
        this.parser = parser;
        this.visitor = visitor;
        functions = new HashMap<>();
    }


    @Override
    public void exitCompilationUnit(picoCParser.CompilationUnitContext ctx) 
    {
        try {
            Writers writers = new Writers();
            writers.writeOutput();
        } catch (IOException ex) {
            Logger.getLogger(TranslationListener.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    /* This is serviced in enterFunctionDeclaration 
    @Override
    public void enterMain(picoCParser.MainContext ctx) 
    {
        Writers.emitText("    global main");
        Writers.emitText("main:");
        Writers.emitInstruction(Constants.FUNCTION_ENTRY);
    }

    */
    
    /* Set return value to eax register */
    @Override
    public void enterReturnStat(picoCParser.ReturnStatContext ctx) 
    {
        /* Used to be visitor.visitExpression(ctx.expression()); */
        String res = visitor.visit(ctx.expression());
        Writers.emitInstruction("mov", NasmTools.STRING_EAX, res);
    }

    /* Emit unconditional jump to the end of the function */
    @Override
    public void exitReturnStat(picoCParser.ReturnStatContext ctx) 
    {
        Writers.emitJumpToExit(FunctionsAnalyser.getInProcess());
    }

    @Override
    public void enterFunctionCall(picoCParser.FunctionCallContext ctx) 
    {
//        TokenStream tokens = parser.getTokenStream();
        
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
    }

    @Override
    public void exitFunctionCall(picoCParser.FunctionCallContext ctx) 
    {
        
    }

    @Override
    public void enterExpression(picoCParser.ExpressionContext ctx) 
    {
        /* This is commented for testing. */
        /* Set rax to 0 for further computation */
        //System.out.println("enterExpression"); 
                
        /* Writers.emitInstruction("xor", "rax", "rax");
        visitor.visit(ctx.simpleExpression()); */
    }
    
    /* Special printf context that differs from standard function.
        Registers needs to be configured for gcc printf implementation. */
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

    @Override
    public void enterFunctionDefinition
    (picoCParser.FunctionDefinitionContext ctx) 
    {
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
    }

    @Override
    public void exitFunctionDefinition
    (picoCParser.FunctionDefinitionContext ctx) 
    {
        Writers.emitLabelReturn(curFuncAna.getFunctionName());
        Writers.emitText(Constants.FUNCTION_EXIT);
        FunctionsAnalyser.setFunctionInProcess(false);
        FunctionsAnalyser.setInProcess(null);
    }

    @Override
    public void enterDeclaration(picoCParser.DeclarationContext ctx) 
    {
        System.out.println("enterDeclaration");
        /* Extern variable declaration */
        if (!FunctionsAnalyser.isFunctionInProcess()) {
            DataSegment.DeclareExtern(ctx);
            return ;
        }
        
        /* Variable name */
        String name = null;
        
        if (ctx.assignment() == null)
            name = ctx.ID().getText();
        else
            name = ctx.assignment().ID().getText();
        /* Chech if variable is already declared */
        if (curFuncAna.getLocalVariables().containsKey(name)) {
            CompilationControler.errorOcured("Multiple declaration of " + name);
            return ;    
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
            return;
        }
        
        /* Emit assembly declaration */
        Writers.emitInstruction("sub", "rsp", Integer.toString(varSize));
    }

    @Override
    public void enterAssignment(picoCParser.AssignmentContext ctx) {
        System.out.println("enterAssignment");
        /* Get id value */
        String id = ctx.ID().getText();
        /* Get variable context */
        Variable var;
        /* Check if it is declared */
        if ((var = curFuncAna.getLocalVariables().get(id)) == null) {
            CompilationControler.errorOcured("Variable +" + id + "not declared");
            return ;
        }
        /* Register where expression is calculated */
        String res = visitor.visit(ctx.expression());
        
        var.setInitialized(true);
        /* Emit assign.
            getStackPosition returns position of var on stack */
        Writers.emitInstruction("mov", var.getStackPosition(), res);
        NasmTools.free(res);
    }
    
}
