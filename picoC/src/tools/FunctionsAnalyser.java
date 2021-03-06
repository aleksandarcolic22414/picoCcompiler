package tools;


import antlr.TranslationListener;
import antlr.TranslationVisitor;
import constants.MemoryClassEnum;
import nasm.NasmTools;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 *
 * @author Aleksandar Colic
 */
public class FunctionsAnalyser 
{
    /* Represents function name that are curently in process. */
    private static String inProcess = null;
    
    /* Determines whether walker is in function context. */
    private static boolean functionInProcess = false;
    
    /* Function name. */
    private String functionName;
    
    /* Parameters of function 
       Pairs K->varName, V->Variable object. */
    private Map<String, Variable> parameterVariables;
    
    /* Local variables of function 
       Pairs K->varName, V->Variable object.
       This variable operates like stack.
       Each level of stack represents one compound statement.
       Scope of variable starts at one point in compound statement
       (at the point where it is declared) and last until the end
       of that compound statement. Example:
       
        {
            int i = 0;
            
            {
                int i = 2;
                printf("%d\n", i);  // refers to new i
            }
            printf("%d\n", i);
        }
    
        Output would be:
        2
        0
    */
    private final LinkedList<Map<String, Variable>> localVariables;
    
    /* Number of parameters. */
    private int numberOfParameters = 0;
    
    /* Number of integer variables that are pushed on the stack. */
    private int localVariablesCounter = 0;

    /* Space on stack used for local variables. */
    private int spaceForLocals = 0;
    
    /* Space on stack used for parameter variables. */
    private int spaceForParams = 0;
    
    /* One of memory class identifiers used for return value. */
    private MemoryClassEnum memoryClass = MemoryClassEnum.VOID;

    /* If function returns pointer, this list holds information 
        about that pointer. */
    private LinkedList<Pointer> pointerType;
    
    /* Whether function has return statement. */
    private boolean hasReturn = false;

    /* Represent whether visitor is parameter contex or not. */
    private boolean parameterContext = false;
    
    /* Represent whether visitor is parameter contex or not. */
    private boolean functionContext = false;
    
    /* Holds information about memory classes of extern functions. */
    private static final Map<String, MemoryClassEnum> gccLib;
    
    static
    {
        gccLib = new HashMap<>();
        
        /* ctype.h */
        gccLib.put("isalnum", MemoryClassEnum.INT);
        gccLib.put("isalpha", MemoryClassEnum.INT);
        gccLib.put("isblank", MemoryClassEnum.INT);
        gccLib.put("iscntrl", MemoryClassEnum.INT);
        gccLib.put("isdigit", MemoryClassEnum.INT);
        gccLib.put("isgraph", MemoryClassEnum.INT);
        gccLib.put("islower", MemoryClassEnum.INT);
        gccLib.put("isprint", MemoryClassEnum.INT);
        gccLib.put("ispunct", MemoryClassEnum.INT);
        gccLib.put("isspace", MemoryClassEnum.INT);
        gccLib.put("isupper", MemoryClassEnum.INT);
        gccLib.put("isxdigit", MemoryClassEnum.INT);
        gccLib.put("toupper", MemoryClassEnum.INT);
        gccLib.put("tolower", MemoryClassEnum.INT);
        
        /* stdio.h */
        gccLib.put("fclose", MemoryClassEnum.INT);
        gccLib.put("feof", MemoryClassEnum.INT);
        gccLib.put("ferror", MemoryClassEnum.INT);
        gccLib.put("fflush", MemoryClassEnum.INT);
        gccLib.put("fgetc", MemoryClassEnum.INT);
        gccLib.put("fgetpos", MemoryClassEnum.INT);
        gccLib.put("fgets", MemoryClassEnum.POINTER);
        gccLib.put("fopen", MemoryClassEnum.POINTER);
        gccLib.put("clearerr", MemoryClassEnum.INT);
        gccLib.put("fprintf", MemoryClassEnum.INT);
        gccLib.put("fputc", MemoryClassEnum.INT);
        gccLib.put("fputs", MemoryClassEnum.INT);
        gccLib.put("fread", MemoryClassEnum.INT);
        gccLib.put("freopen", MemoryClassEnum.POINTER);
        gccLib.put("fscanf", MemoryClassEnum.INT);
        gccLib.put("fseek", MemoryClassEnum.INT);
        gccLib.put("fsetpos", MemoryClassEnum.INT);
        gccLib.put("ftell", MemoryClassEnum.INT);
        gccLib.put("fwrite", MemoryClassEnum.INT);
        gccLib.put("getc", MemoryClassEnum.INT);
        gccLib.put("getchar", MemoryClassEnum.INT);
        gccLib.put("gets", MemoryClassEnum.INT);
        gccLib.put("perror", MemoryClassEnum.INT);
        gccLib.put("printf", MemoryClassEnum.INT);
        gccLib.put("putc", MemoryClassEnum.INT);
        gccLib.put("putchar", MemoryClassEnum.INT);
        gccLib.put("puts", MemoryClassEnum.INT);
        gccLib.put("remove", MemoryClassEnum.INT);
        gccLib.put("rename", MemoryClassEnum.INT);
        gccLib.put("rewind", MemoryClassEnum.INT);
        gccLib.put("scanf", MemoryClassEnum.INT);
        gccLib.put("setbuf", MemoryClassEnum.INT);
        gccLib.put("setvbuf", MemoryClassEnum.INT);
        gccLib.put("snprintf", MemoryClassEnum.INT);
        gccLib.put("sprintf", MemoryClassEnum.INT);
        gccLib.put("sscanf", MemoryClassEnum.INT);
        gccLib.put("tmpfile", MemoryClassEnum.POINTER);
        gccLib.put("tmpnam", MemoryClassEnum.POINTER);
        gccLib.put("ungetc", MemoryClassEnum.INT);
        gccLib.put("vfprintf", MemoryClassEnum.INT);
        gccLib.put("vfscanf", MemoryClassEnum.INT);
        gccLib.put("vprintf", MemoryClassEnum.INT);
        gccLib.put("vscanf", MemoryClassEnum.INT);
        gccLib.put("vsnprintf", MemoryClassEnum.INT);
        gccLib.put("vsprintf", MemoryClassEnum.INT);
        gccLib.put("vsscanf", MemoryClassEnum.INT);
        
        /* stdlib.h */
        gccLib.put("abort", MemoryClassEnum.INT);
        gccLib.put("abs", MemoryClassEnum.INT);
        gccLib.put("atexit", MemoryClassEnum.INT);
        gccLib.put("atof", MemoryClassEnum.INT);
        gccLib.put("atoi", MemoryClassEnum.INT);
        gccLib.put("atol", MemoryClassEnum.INT);
        gccLib.put("atoll", MemoryClassEnum.INT);
        gccLib.put("at_quick_exit", MemoryClassEnum.INT);
        gccLib.put("bsearch", MemoryClassEnum.POINTER);
        gccLib.put("calloc", MemoryClassEnum.POINTER);
        gccLib.put("div", MemoryClassEnum.INT);
        gccLib.put("exit", MemoryClassEnum.INT);
        gccLib.put("free", MemoryClassEnum.INT);
        gccLib.put("getenv", MemoryClassEnum.POINTER);
        gccLib.put("labs", MemoryClassEnum.INT);
        gccLib.put("ldiv", MemoryClassEnum.INT);
        gccLib.put("llabs", MemoryClassEnum.INT);
        gccLib.put("lldiv", MemoryClassEnum.INT);
        gccLib.put("malloc", MemoryClassEnum.POINTER);
        gccLib.put("mblen", MemoryClassEnum.INT);
        gccLib.put("mbstowcs", MemoryClassEnum.INT);
        gccLib.put("mbtowc", MemoryClassEnum.INT);
        gccLib.put("qsort", MemoryClassEnum.INT);
        gccLib.put("quick_exit", MemoryClassEnum.INT);
        gccLib.put("rand", MemoryClassEnum.INT);
        gccLib.put("realloc", MemoryClassEnum.POINTER);
        gccLib.put("srand", MemoryClassEnum.INT);
        gccLib.put("strtod", MemoryClassEnum.INT);
        gccLib.put("strtof", MemoryClassEnum.INT);
        gccLib.put("strtol", MemoryClassEnum.INT);
        gccLib.put("strtold", MemoryClassEnum.INT);
        gccLib.put("strtoll", MemoryClassEnum.INT);
        gccLib.put("strtoul", MemoryClassEnum.INT);
        gccLib.put("strtoull", MemoryClassEnum.INT);
        gccLib.put("system", MemoryClassEnum.INT);
        gccLib.put("wcstombs", MemoryClassEnum.INT);
        gccLib.put("wctomb", MemoryClassEnum.INT);
        gccLib.put("_Exit", MemoryClassEnum.INT);
        gccLib.put("itoa", MemoryClassEnum.INT);

        /* string .h */
        gccLib.put("memchr", MemoryClassEnum.POINTER);
        gccLib.put("memcmp", MemoryClassEnum.INT);
        gccLib.put("memcpy", MemoryClassEnum.POINTER);
        gccLib.put("memmove", MemoryClassEnum.POINTER);
        gccLib.put("memset", MemoryClassEnum.POINTER);
        gccLib.put("strcat", MemoryClassEnum.POINTER);
        gccLib.put("strchr", MemoryClassEnum.POINTER);
        gccLib.put("strcmp", MemoryClassEnum.INT);
        gccLib.put("strcoll", MemoryClassEnum.INT);
        gccLib.put("strcpy", MemoryClassEnum.POINTER);
        gccLib.put("strcspn", MemoryClassEnum.INT);
        gccLib.put("strerror", MemoryClassEnum.POINTER);
        gccLib.put("strlen", MemoryClassEnum.INT);
        gccLib.put("strncat", MemoryClassEnum.POINTER);
        gccLib.put("strncmp", MemoryClassEnum.INT);
        gccLib.put("strncpy", MemoryClassEnum.POINTER);
        gccLib.put("strpbrk", MemoryClassEnum.POINTER);
        gccLib.put("strrchr", MemoryClassEnum.INT);
        gccLib.put("strspn", MemoryClassEnum.INT);
        gccLib.put("strstr", MemoryClassEnum.POINTER);
        gccLib.put("strtok", MemoryClassEnum.POINTER);
        gccLib.put("strxfrm", MemoryClassEnum.INT);
    }
    
    public FunctionsAnalyser(String functionName) 
    {
        this.localVariables = new LinkedList<>();
        this.parameterVariables = new HashMap<>();
        this.pointerType = new LinkedList<>();
        this.functionName = functionName;
    }
    
    public static String getInProcess() 
    {
        return inProcess;
    }

    public static boolean isFunctionInProcess() 
    {
        return functionInProcess;
    }

    public static void setInProcess(String inProcess) 
    {
        FunctionsAnalyser.inProcess = inProcess;
    }

    public static void setFunctionInProcess(boolean functionInProcess) 
    {
        FunctionsAnalyser.functionInProcess = functionInProcess;
    }

    public void setFunctionName(String functionName) 
    {
        this.functionName = functionName;
    }

    public void setNumberOfParameters(int numberOfParameters) 
    {
        this.numberOfParameters = numberOfParameters;
    }

    public void setLocalVariablesCounter(int localVariablesCounter) 
    {
        this.localVariablesCounter = localVariablesCounter;
    }
    
    public void setMemoryClass(MemoryClassEnum memoryClass) 
    {
        this.memoryClass = memoryClass;
    }

    public void setHasReturn(boolean hasReturn) 
    {
        this.hasReturn = hasReturn;
    }

    public String getFunctionName() 
    {
        return functionName;
    }

    public LinkedList<Map<String, Variable>> getLocalVariables() 
    {
        return localVariables;
    }

    public int getNumberOfParameters() 
    {
        return numberOfParameters;
    }

    public int getLocalVariablesCounter() 
    {
        return localVariablesCounter;
    }

    public MemoryClassEnum getMemoryClass() 
    {
        return memoryClass;
    }

    public boolean isHasReturn() 
    {
        return hasReturn;
    }

    public Map<String, Variable> getParameterVariables() 
    {
        return parameterVariables;
    }

    public void setParameterVariables(Map<String, Variable> parameterVariables) 
    {
        this.parameterVariables = parameterVariables;
    }

    public int getSpaceForLocals() 
    {
        return spaceForLocals;
    }

    public void setSpaceForLocals(int spaceForLocals) 
    {
        this.spaceForLocals = spaceForLocals;
    }

    public int getSpaceForParams() 
    {
        return spaceForParams;
    }

    public void setSpaceForParams(int spaceForParams) 
    {
        this.spaceForParams = spaceForParams;
    }

    public boolean isParameterContext() 
    {
        return parameterContext;
    }

    public void setParameterContext(boolean parameterContext) 
    {
        this.parameterContext = parameterContext;
    }

    public boolean isFunctionContext() 
    {
        return functionContext;
    }

    public void setFunctionContext(boolean functionContext) 
    {
        this.functionContext = functionContext;
    }
    
    public LinkedList<Pointer> getPointerType() 
    {
        return pointerType;
    }

    public void setPointerType(LinkedList<Pointer> pointerType) 
    {
        this.pointerType = pointerType;
    }
    
    public String declareLocalVariable(MemoryClassEnum type) 
    {
        /* Increase number of local variables */
        ++localVariablesCounter;
        int sizeofvar = NasmTools.getSize(type);
        
        /* Calculate new stack displacement */
        spaceForLocals += sizeofvar;
        return "rbp-" + Integer.toString(spaceForLocals);
    }

    public String declareParameterVariable(MemoryClassEnum type) 
    {
        /* Increase number of parameter variables */
        int sizeofvar = NasmTools.getSize(type);
        
        /* Take function name for further calculation */
        String fname = TranslationVisitor.curFuncAna.getFunctionName();
        
        /* Get taken space on stack for local variables calculated in
            Listener class */
        int taken = TranslationListener.mapFuncAna.get(fname).getSpaceForLocals();
        
        /* Calculate new place on stack */
        spaceForParams += sizeofvar;
        return "rbp-" + Integer.toString(taken + spaceForParams);
    }

    /* Returns variable if it exists. First searching of local variables is done.
        If it is not found in locals, search of parameters is done.
        Search for local variables goes from top of the stack 
        (last compound statement in function) and goes to the bottom 
        (fist compound statement in function). */
    public Variable getAnyVariable(String id) 
    {
        Variable var;
        if ((var = findLocalVariable(id)) != null)
            return var;
        
        if ((var = getParameterVariables().get(id)) != null)
            return var;
        
        return null;
    }

    /* Just return sum of space needed for parameters and local variables */
    public int getSpaceForVariables() 
    {
        return getSpaceForLocals() + getSpaceForParams();
    }

    public String declareLocalArray
    (MemoryClassEnum type, LinkedList<Integer> arrayDecl) 
    {
        /* Increase number of local variables */
        ++localVariablesCounter;
        int sizeofvar = NasmTools.getSize(type);
        int numberOfVariable = NasmTools.multiplyList(arrayDecl);
        
        /* Calculate new stack displacement */
        spaceForLocals += sizeofvar * numberOfVariable;
        return "rbp-" + Integer.toString(spaceForLocals);
    }

    public void initNewCompoundContext(Map<String, Variable> localVarMap) 
    {
        getLocalVariables().push(localVarMap);
    }

    public void deleteCurrentCompoundContext() 
    {
        getLocalVariables().pop().clear();
    }
    
    public Map<String, Variable> getLocalVariablesInLastBlock() 
    {
        return localVariables.peek();
    }

    /* Start from top of the stack (last compount block) 
        and go to the bootom. */
    public Variable findLocalVariable(String id) 
    {
        Variable var = null;
        for (Map<String, Variable> myVars : localVariables) {
            if ((var = myVars.get(id)) != null)
                break;        
        }
        
        return var;
    }
    
    public static MemoryClassEnum getFunctionMemoryClassFromGCCLib(String functionName) 
    {
        return gccLib.get(functionName);
    }
    
}
