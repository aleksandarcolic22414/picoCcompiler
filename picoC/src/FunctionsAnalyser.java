
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author aleksandar
 */
public class FunctionsAnalyser 
{
    
    /* Represents function name that are curently in process */
    private static String inProcess = null;
    
    /* Determines wheather walker is in function context */
    private static boolean functionInProcess = false;
    
    /* Function name */
    private String functionName;
    
    /* Pairs K->varName, V->Variable object */
    private Map<String, Variable> localVariables;
    
    /* Number of parameters */
    private int numberOfParameters = 0;
    
    /* Number of integer variables that are pushed on the stack */
    private int localVariablesCounter = 0;
    
    /* Space on stack used for local variables */
    private int stackVariablesDisplacement = 0;
    
    /* One of memory class identifiers used for return value */
    private MemoryClassEnumeration memoryClass = MemoryClassEnumeration.VOID;

    /* Wheather function has return statement */
    private boolean hasReturn = false;
    
    /* Represents memory class for declarationList */
    private MemoryClassEnumeration currentDeclaratorType = MemoryClassEnumeration.VOID;
    
    public FunctionsAnalyser(String functionName) {
        this.localVariables = new HashMap<>();
        this.functionName = functionName;
    }
    
    public static String getInProcess() {
        return inProcess;
    }

    public static boolean isFunctionInProcess() {
        return functionInProcess;
    }

    
    public static void setInProcess(String inProcess) {
        FunctionsAnalyser.inProcess = inProcess;
    }

    public static void setFunctionInProcess(boolean functionInProcess) {
        FunctionsAnalyser.functionInProcess = functionInProcess;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public void setLocalVariables(Map<String, Variable> localVariables) {
        this.localVariables = localVariables;
    }

    public void setNumberOfParameters(int numberOfParameters) {
        this.numberOfParameters = numberOfParameters;
    }

    public void setLocalVariablesCounter(int localVariablesCounter) {
        this.localVariablesCounter = localVariablesCounter;
    }

    public void setStackVariablesDisplacement(int stackVariablesDisplacement) {
        this.stackVariablesDisplacement = stackVariablesDisplacement;
    }

    public void setMemoryClass(MemoryClassEnumeration memoryClass) {
        this.memoryClass = memoryClass;
    }

    public void setHasReturn(boolean hasReturn) {
        this.hasReturn = hasReturn;
    }

    public String getFunctionName() {
        return functionName;
    }

    public Map<String, Variable> getLocalVariables() {
        return localVariables;
    }

    public int getNumberOfParameters() {
        return numberOfParameters;
    }

    public int getLocalVariablesCounter() {
        return localVariablesCounter;
    }

    public int getStackVariablesDisplacement() {
        return stackVariablesDisplacement;
    }

    public MemoryClassEnumeration getMemoryClass() {
        return memoryClass;
    }

    public boolean isHasReturn() {
        return hasReturn;
    }

    public MemoryClassEnumeration getCurrentDeclaratorType() {
        return currentDeclaratorType;
    }

    public void setCurrentDeclaratorType(MemoryClassEnumeration currentDeclaratorType) {
        this.currentDeclaratorType = currentDeclaratorType;
    }

    
    
    public static MemoryClassEnumeration getMemoryClass(String memclass)
    {
        switch (memclass) {
            case "int":
                return MemoryClassEnumeration.INT;
            case "void":
                return MemoryClassEnumeration.VOID;
            default:
                return null;
        }
    }

    public String declareNew(MemoryClassEnumeration type) {
        /* Increase number of local variables */
        ++localVariablesCounter;
        
        int sizeofvar = NasmTools.getSize(type);
        /* Calculate new stack displacement */
        stackVariablesDisplacement += sizeofvar;
        /* TODO: Determine witch cast should be used */
        return "dword [rbp-" + Integer.toString(stackVariablesDisplacement) + "]";
    }
    
    
}
