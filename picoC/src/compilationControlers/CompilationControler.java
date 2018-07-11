package compilationControlers;

import org.antlr.v4.runtime.Token;

/**
 *
 * @author Aleksandar Colic
 */
public class CompilationControler {
    
    /* Number of errors */
    public static int errors = 0;
    
    /* Number of warnings */
    public static int warnings = 0;
    
    /* Handle errors */
    public static void errorOcured(Token token, String functionName, String error) 
    {
        ++errors;
        int line = token.getLine();
        int pos = token.getStartIndex();
        
        if (functionName == null) {
            System.err.println
            ("Error: line " + line + ": " + error + ";");
        } else {
            System.err.println
            ("Error in function " + functionName + 
                    ": line " + line + ": " + error + ";");
        }
    }
    
    /* Handle warnings */
    public static void warningOcured(Token token, String functionName, String warning)
    {
        ++warnings;
        int line = token.getLine();
        int pos = token.getStartIndex();
        
        if (functionName == null) {
            System.err.println
            ("Warning: line " + line + ": " + warning + ";");
        } else {
            System.err.println
            ("Warning in function " + functionName + 
                    ": line " + line + ": " + warning + ";");
        }
    }        
    
}
