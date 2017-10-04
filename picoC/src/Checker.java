import java.util.List;

/**
 *
 * @author aleksandar
 */
public class Checker 
{

    public static boolean functionCheck
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
        }
        return true;
    }
    /* This shoud check for all external functions.
        That could be some functions from gcc's standard library */
    private static boolean externalFunctionCheck(String functionName) 
    {
        switch (functionName) {
            case "printf":
                return true;
            default:
                return false;
        }
    }
    
}
