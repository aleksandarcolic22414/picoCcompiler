
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Aleksandar Colic
 */
public class NasmTools 
{
    /* This variable represents maping for arguments of function. */
    public static final Map<Integer, Map<MemoryClassEnumeration, String>> registersPicker;
    /* Helps during argument calculation and it works with registersPicker */
    public static int registersPickerCounter = 0;
    /* This variable contains information about free registers  */
    public static int flags = 0;
    
    public static final int NUMBER_OF_REGISTERS = 14;
    
    /* Next variables represents possition of registers in flags.
        eax register is the right most bit, ebx is one on the left, and so on... */
    public static final int EAX = 0x1;
    public static final int EBX = 0x2;
    public static final int ECX = 0x4;
    public static final int EDX = 0x8;
    public static final int ESI = 0x10;
    public static final int EDI = 0x20;
    public static final int R8D = 0x40;
    public static final int R9D = 0x80;
    public static final int R10D = 0x100;
    public static final int R11D = 0x200;
    public static final int R12D = 0x400;
    public static final int R13D = 0x800;
    public static final int R14D = 0x1000;
    public static final int R15D = 0x2000;
    
    /* Mext variables is string representation of registers */
    public static final String STRING_EAX = "eax";
    public static final String STRING_EBX = "ebx";
    public static final String STRING_ECX = "ecx";
    public static final String STRING_EDX = "edx";
    public static final String STRING_ESI = "esi";
    public static final String STRING_EDI = "edi";
    public static final String STRING_R8D = "r8d";
    public static final String STRING_R9D = "r9d";
    public static final String STRING_R10D = "r10d";
    public static final String STRING_R11D = "r11d";
    public static final String STRING_R12D = "r12d";
    public static final String STRING_R13D = "r13d";
    public static final String STRING_R14D = "r14d";
    public static final String STRING_R15D = "r15d";
    
    /* Represents number of saved registers on stack for computation. */
    public static int pushedRegistersOnStack = 0;
    
    private static final Map<String, Integer> registersMap;
    
    static {
        registersMap = new HashMap<>();
        registersMap.put(STRING_EAX, EAX);
        registersMap.put(STRING_EBX, EBX);
        registersMap.put(STRING_ECX, ECX);
        registersMap.put(STRING_EDX, EDX);
        registersMap.put(STRING_ESI, ESI);
        registersMap.put(STRING_EDI, EDI);
        registersMap.put(STRING_R8D, R8D);
        registersMap.put(STRING_R9D, R9D);
        registersMap.put(STRING_R10D, R10D);
        registersMap.put(STRING_R11D, R11D);
        registersMap.put(STRING_R12D, R12D);
        registersMap.put(STRING_R13D, R13D);
        registersMap.put(STRING_R14D, R14D);
        registersMap.put(STRING_R15D, R15D);
        /* Register picker intialization ****************************/
        
        /* Initialize map for rdi register */
        Map<MemoryClassEnumeration, String> diMap = new HashMap<>();
        diMap.put(MemoryClassEnumeration.INT, "edi");
        
        /* Initialize map for rsi register */
        Map<MemoryClassEnumeration, String> siMap = new HashMap<>();
        siMap.put(MemoryClassEnumeration.INT, "esi");
        
        /* Initialize map for rdx register */
        Map<MemoryClassEnumeration, String> dxMap = new HashMap<>();
        dxMap.put(MemoryClassEnumeration.INT, "edx");
        
        /* Initialize map for rcx register */
        Map<MemoryClassEnumeration, String> cxMap = new HashMap<>();
        cxMap.put(MemoryClassEnumeration.INT, "ecx");
        
        /* Initialize map for r8 register */
        Map<MemoryClassEnumeration, String> r8Map = new HashMap<>();
        r8Map.put(MemoryClassEnumeration.INT, "r8d");
        
        /* Initialize map for r9 register */
        Map<MemoryClassEnumeration, String> r9Map = new HashMap<>();
        r9Map.put(MemoryClassEnumeration.INT, "r9d");
        
        registersPicker = new HashMap<>();
        registersPicker.put(0, diMap);
        registersPicker.put(1, siMap);
        registersPicker.put(2, dxMap);
        registersPicker.put(3, cxMap);
        registersPicker.put(4, r8Map);
        registersPicker.put(5, r9Map);
    }
    
    /* Function converts C-style string into nasm-style byte array.
        It's limited version that only replaces line feed chars.
        Other conversions are about to be implemented. */
    public static String convertStringToNasmStringLiteral(String literalValue) 
    {
        System.out.println("Input liteval: " + literalValue);
        char source[] = literalValue.toCharArray();
        char destination[] = new char[source.length * 3];
        int i, c;
        for (i = c = 0; i < source.length; ++i) {
            if (source[i] == '\\' && source[i + 1] == 'n') {
                ++i;
                destination[c++] = '"';
                destination[c++] = ',';
                destination[c++] = ' ';
                destination[c++] = '1';
                destination[c++] = '0';
                if (i < source.length - 2) {
                    destination[c++] = ',';
                    destination[c++] = ' ';
                    destination[c++] = '"';
                } else
                    break;
            } else
                destination[c++] = source[i];
        }
        System.out.println(new String(destination).trim());
        return new String(destination).trim();
    }

    public static boolean isTakenRegisterEDX() 
    {
        return (flags & EDX) != 0;
    }

    public static boolean isTakenRegisterEAX() 
    {
        return (flags & EAX) != 0;
    }
    

    /* Function determines witch is next position on stack
        that can hold value. 
    TODO: Get real displacement when stack frame is not empty! */
    private static String getStackDisplacement() 
    {
        /* Get function analyser */
        FunctionsAnalyser fa; 
        fa = TranslationVisitor.functions.get(FunctionsAnalyser.getInProcess());
        /* Calculate taken memory on stack */
        int taken = fa.getSpaceForLocals() + fa.getSpaceForParams();
        
        /* Make room for integer */
        Writers.emitInstruction(
                "sub", "rsp", Integer.toString(Constants.SIZE_OF_INT));
        /* First free location is ebp-4. So every next is calculated by
            multipliing pushedRegisters and sizeof(int).
            Also taken must be added consider already taken place on stack. */
        int disp = 
                taken + 
                Constants.SIZE_OF_INT + 
                Constants.SIZE_OF_INT * pushedRegistersOnStack;
        
        ++pushedRegistersOnStack;
        /* [rbp - displacement] is returned casted to dword
            TODO: Determine witch cast should be used  */
        return "dword [rbp-" + Integer.toString(disp) + "]";
    }

    /* Really stupid check */
    public static boolean isRegister(String left) 
    {
        return registersMap.containsKey(left);
    }

    /* Returns text representation of operation.
        Numbers are taken from generated token numbers. */
    public static String getOperation(int op) 
    {
        switch (op) {
            case picoCParser.ADD:
                return "add";
            case picoCParser.SUB:
                return "sub";
            case picoCParser.MUL:
                return "imul";
            case picoCParser.DIV:
                return "idiv";
            default:
                return null;
        }
    }
    /* Return next free register if there is one. If there is no free3 registers
        function returns first free3 stack memory */
    public static String getNextFreeTemp() 
    {
        int register = -1;
        /* If all registers are taken */
        if (flags == ((1 << NUMBER_OF_REGISTERS) - 1)) 
            return getStackDisplacement();
        /* Look for first free register */
        
        for (int i = 0; i < NUMBER_OF_REGISTERS; i++) {
            if ((flags & (1 << i)) == 0) {
                register = 1 << i;
                flags |= register;
                break;
            }
        }
        return NasmTools.registerToString(register);
    }
    
    /* Funtion cleans up memory location that is used for computations. 
        That can be register or some value on stack. */
    public static void free(String source) 
    {
        /* If there is no saved values on stack */
        if (pushedRegistersOnStack == 0) {
            int register;
            register = NasmTools.stringToRegister(source);
            flags ^= register;
        } else {
            String s = Integer.toString(Constants.SIZE_OF_INT);
            Writers.emitInstruction("add", "rsp", s);
            --pushedRegistersOnStack;
        }
    }
    
    /* Returns string representation of register */
    private static String registerToString(int register) 
    {
        switch (register) {
            case EAX:
                return "eax";
            case EBX:
                return "ebx";
            case ECX:
                return "ecx";
            case EDX:
                return "edx";
            case ESI:
                return "esi";
            case EDI:
                return "edi";
            case R8D:
                return "r8d";
            case R9D:
                return "r9d";
            case R10D:
                return "r10d";
            case R11D:
                return "r11d";
            case R12D:
                return "r12d";
            case R13D:
                return "r13d";
            case R14D:
                return "r14d";
            case R15D:
                return "r15d";
            default:
                break;
        }
        return null;
    }
    
    private static int stringToRegister(String source) 
    {
        return registersMap.get(source);
    }

    public static int getSize(MemoryClassEnumeration typeSpecifier) 
    {
        switch (typeSpecifier) {
            case INT:
                return Constants.SIZE_OF_INT;
            case VOID:
                return -1;
            default:
                return 0;
        }
    }

    static void freeAllRegisters() 
    {
        /* Clear flags regiser */
        flags = 0x0;
    }
    /* Function that copyes all argumets on stack for further use 
    Warning: 
        If there is more than 6 non floating variables passed to function, than
        they are pushed in reverse order. This case won't work! */
    static void moveArgsToStack(List<picoCParser.ParameterContext> parameters) 
    {
        int lsize = parameters.size();
        String reg, paramName, paramPos;
        MemoryClassEnumeration memclass;
        for (int i = 0; i < lsize; ++i) {
            /* argument name needed for it's position on stack */
            paramName = parameters.get(i).ID().getText();
            /* get argument's position on stack */
            paramPos = TranslationVisitor.curFuncAna.
                        getParameterVariables().get(paramName).getStackPosition();
            /* Get memory class of typeSpecifier and register 
                in witch it is passed to function */
            memclass = FunctionsAnalyser.getMemoryClass(parameters.get(i).typeSpecifier().getText());
            reg = getNextRegForFuncCall(memclass);

            /* Emit copying from registers to stack for argument */
            Writers.emitInstruction("mov", paramPos, reg);
        }
        resetRegisterPicker();
    }
    /* This function returns, for some function argument, in witch register
        it is passed during function call. For example, first int argument
        is passed to edi register, second to esi ect. 
        If no registers is free, function returns next position on stack that
        holds argument value. 
    Warning: 
        If there is more than 6 non floating variables passed to function, than
        they are pushed in reverse order. This case won't work! */
    private static String getNextRegForFuncCall(MemoryClassEnumeration memclass) 
    {
        /* If all registers is taken, than argument is passed on stack */
        if (registersPickerCounter > 5)
            return pushArgumentOnStack(memclass);
        else
            return registersPicker.get(registersPickerCounter++).get(memclass);
    }
    
    /* Resets register counter for further usage */
    private static void resetRegisterPicker() 
    {
        registersPickerCounter = 0;
    }
    
    static void moveArgsToRegisters
    (List<picoCParser.ArgumentContext> arguments) 
    {
        int lsize = arguments.size();
        String reg, argName, argPos;
        Variable var;
        MemoryClassEnumeration memclass;
        for (int i = 0; i < lsize; ++i) {
            /* argument name needed for it's position on stack */
            argName = arguments.get(i).ID().getText();
            /* Get variable object */
            var = TranslationVisitor.curFuncAna.
                            getAnyVariable(argName);
            /* get argument's position on stack */
            argPos = var.getStackPosition();
            /* Get memory class of typeSpecifier and register 
                in witch it is passed to function */
            memclass = var.getTypeSpecifier();
            reg = getNextRegForFuncCall(memclass);

            /* Emit copying from registers to stack for argument */
            Writers.emitInstruction("mov", reg, argPos);
        }
        resetRegisterPicker();
    }
    /* This method is used for storing registers that holds some value.
        It is usualy done for function call */
    /* Not implemented yet */
    static void saveRegistersOnStack() 
    {
        
    }
    /* Not implemented yet */
    static void restoreRegisters() 
    {
        
    }

    /* Not implemented yet */
    private static String pushArgumentOnStack(MemoryClassEnumeration memclass) 
    {
        return null;
    }
    
}
