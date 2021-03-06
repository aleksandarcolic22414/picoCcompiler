
import java.io.IOException;
import java.util.List;
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
    
    
    public TranslationListener(picoCParser parser) 
    {
        this.parser = parser;
    }

    
    @Override
    public void enterCompilationUnit(picoCParser.CompilationUnitContext ctx) 
    {
        
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
    
    @Override
    public void enterMain(picoCParser.MainContext ctx) 
    {
        Writers.emitText("\tglobal main");
        Writers.emitText("main:");
        Writers.emitInstruction(Constants.FUNCTION_ENTRY);
    }

    @Override
    public void exitMain(picoCParser.MainContext ctx) 
    {
        Writers.emitInstruction(Constants.FUNCTION_EXIT);
    }

    /* Similar to main */
    @Override
    public void enterFunctionBody(picoCParser.FunctionBodyContext ctx) 
    {
        
    }

    /* Similar to main */
    @Override
    public void exitFunctionBody(picoCParser.FunctionBodyContext ctx) 
    {
        
    }

    /* Just done for numbers. ID's are about to be done... */
    @Override
    public void enterReturnStat(picoCParser.ReturnStatContext ctx) 
    {
        String val = ctx.INT().getText();
        Writers.emitInstruction("mov", "eax", val);
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

}
