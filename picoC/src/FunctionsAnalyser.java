
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author aleksandar
 */
public class FunctionsAnalyser 
{
    /* Represents function name that are curently in process */
    public static String inProcess = null;
    
    /* Function name */
    public String functionName;
    
    /* Pairs K->varName, V->Variable stack displacement */
    public Map<String, Integer> localVariables;
    
    /* Number of parameters */
    public int numberOfParameters = 0;
    
    /* Number of integer variables that are pushed on the stack */
    public int localIntVariables = 0;
    
    /* Space on stack used for local variables */
    public int stackVariablesDisplacement = 0;
    
    /* One of memory class identifiers */
    public int memoryClass = -1;

    /* Wheather function has return statement */
    public boolean hasReturn = false;
    
    public FunctionsAnalyser(String functionName) {
        this.localVariables = new HashMap<>();
        this.functionName = functionName;
    }
    
    
}
