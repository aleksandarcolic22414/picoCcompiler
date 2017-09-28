/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aleksandar
 */
public class CompilationControler {
    
    /* Number of errors */
    public static int errors = 0;
    
    /* Number of warnings */
    public static int warnings = 0;
    
    /* Handle errors */
    public static void errorOcured(String error) 
    {
        ++errors;
        System.err.println("Error: " + error);
    }
    
    /* Handle warnings */
    public static void warningOcured(String warning)
    {
        ++warnings;
        System.err.println("Warning: " + warning);
    }        
    
}
