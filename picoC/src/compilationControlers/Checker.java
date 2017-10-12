package compilationControlers;

import antlr.TranslationVisitor;
import antlr.picoCParser;
import tools.FunctionsAnalyser;
import tools.Variable;
import constants.MemoryClassEnumeration;
import java.util.List;

/**
 *
 * @author aleksandar
 */
public class Checker 
{

    public static boolean functionCallCheck
    (picoCParser.FunctionCallContext ctx, List<picoCParser.ArgumentContext> argumentList) 
    {
        int paramCount;
        FunctionsAnalyser funcAnalyser;
        String functionName = ctx.functionName().getText();
        if (Checker.externalFunctionCheck(functionName))
            return true;
        if ((funcAnalyser = TranslationVisitor.functions.get(functionName)) == null) {
            CompilationControler.errorOcured
                (ctx.getStart(), TranslationVisitor.curFuncAna.getFunctionName(),
                        "Function " + functionName + " doesn't exist");
            return false;
        }
        /* Check if number of arguments matches number of parameters */
        if (argumentList != null) {
            if ((paramCount = funcAnalyser.getNumberOfParameters()) > argumentList.size()) {
                CompilationControler.errorOcured
                    (ctx.getStart(),
                            TranslationVisitor.curFuncAna.getFunctionName(), "Too few arguments in function call");
                return false;
            }
            if (paramCount < argumentList.size()) {
                CompilationControler.errorOcured
                    (ctx.getStart(),
                            TranslationVisitor.curFuncAna.getFunctionName(), "Too many arguments in function call");
                return false;
            }
        } else {
            if (funcAnalyser.getNumberOfParameters() > 0) {
                CompilationControler.errorOcured
                    (ctx.getStart(),
                            TranslationVisitor.curFuncAna.getFunctionName(), "Too few arguments in function call");
                return false;
            }
        }
        return true;
    }
    /* This shoud check for all external functions.
        That could be some functions from gcc's standard library */
    public static boolean externalFunctionCheck(String functionName) 
    {
        switch (functionName) {
            case "printf":
                return true;
            default:
                return false;
        }
    }
    /* Check for multiple definitions of function */
    public static boolean funcDefCheck(picoCParser.FunctionDefinitionContext ctx, String name) 
    {
        if (TranslationVisitor.functions.containsKey(name)) {
            String error = "Multiple definitions of " + name;
            System.err.println(error);
            CompilationControler.errorOcured
                (ctx.getStart(), 
                        name,
                            error);
            return false;
        } 
        return true;
    }

    public static void funcRetStatCheck(picoCParser.FunctionDefinitionContext ctx) 
    {
        if (TranslationVisitor.curFuncAna.getMemoryClass() != MemoryClassEnumeration.VOID && 
                !TranslationVisitor.curFuncAna.isHasReturn()) 
        {
            CompilationControler.warningOcured
                (ctx.getStart(), 
                        TranslationVisitor.curFuncAna.getFunctionName(),
                            "Missing return statement in function " + 
                                    TranslationVisitor.curFuncAna.getFunctionName());
        }
        
    }

    public static boolean paramCheck(picoCParser.ParameterContext ctx, String name) 
    {
        if (TranslationVisitor.curFuncAna.getParameterVariables().containsKey(name)) {
            CompilationControler.errorOcured
                (ctx.getStart(), 
                        TranslationVisitor.curFuncAna.getFunctionName(),
                            "Two or more params with the same name: " + name);
            return false;    
        }
        return true;
    }

    public static boolean varSizeCheck(picoCParser.ParameterContext ctx, int varSize) 
    {
        if (varSize == -1) {
            CompilationControler.errorOcured
                (ctx.getStart(),
                        TranslationVisitor.curFuncAna.getFunctionName(),
                            "Void variable not alowed!");
            return false;
        }
        return true;
    }

    
    public static boolean varSizeCheck(picoCParser.DeclarationContext ctx, int varSize) 
    {
        if (varSize == -1) {
            CompilationControler.errorOcured
                (ctx.getStart(),
                        TranslationVisitor.curFuncAna.getFunctionName(),
                            "Void variable not alowed!");
            return false;
        }
        return true;
    }
    
    public static boolean varDeclCheck(picoCParser.DeclarationContext ctx, String name) 
    {
        if (TranslationVisitor.curFuncAna.getLocalVariables().containsKey(name)
                || TranslationVisitor.curFuncAna.getParameterVariables().containsKey(name)) 
        {
            CompilationControler.errorOcured
                (ctx.getStart(), 
                        TranslationVisitor.curFuncAna.getFunctionName(),
                            "Multiple declaration of " + name);
            return false;    
        }
        return true;
    }

    public static boolean varDeclCheck
    (picoCParser.AssignmentContext ctx, String id, Variable var) 
    {
        if (var == null) {
            CompilationControler.errorOcured(
                    ctx.getStart(), 
                        TranslationVisitor.curFuncAna.getFunctionName(),
                            "Variable " + "'" + id + "'" + " not declared");
            return false;
        }
        return true;
    }

    public static boolean varLocalAndParamCheck
    (boolean local, boolean param, picoCParser.IdContext ctx, String id) 
    {
        if (!local && !param) {
            CompilationControler.errorOcured
                (ctx.getStart(), TranslationVisitor.curFuncAna.getFunctionName(),
                        "Variable " + "'" + id  + "'" 
                        + " is not declared");
            return false;
        }
        return true;
    }

    public static void varInitCheck
    (boolean local, Variable newVar, String id, picoCParser.IdContext ctx) 
    {
        if (local && newVar != null && !newVar.isInitialized()) {
            CompilationControler.warningOcured
                (ctx.getStart(), TranslationVisitor.curFuncAna.getFunctionName(),
                        "Variable " + "'" + id  + "'" 
                        + " is used uninitialized");
        }
    }

    
}
