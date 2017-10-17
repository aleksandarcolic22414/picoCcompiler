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
        System.err.println
            ("Error in function " + functionName + ": line: " + line + 
                    " pos: " + pos + ": " + error + ";");
    }
    
    /* Handle warnings */
    public static void warningOcured(Token token, String functionName, String warning)
    {
        ++warnings;
        int line = token.getLine();
        int pos = token.getStartIndex();
        System.err.println
            ("Warning in function " + functionName + ": line: " + line + 
                    " pos: " + pos + ": " + warning + ";");
    }        
    
}