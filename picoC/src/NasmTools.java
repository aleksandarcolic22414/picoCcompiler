
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Aleksandar Colic
 */
public class NasmTools 
{
    /* Number of general purpose registers */
    public static final int NUMBER_OF_REGISTERS = 14;
    
    /* This variable contains information about free registers  */
    public static int flags = 0;
    
    /* This array represent stack of flags. It it used for function calls.
        Before every function call, if some registers holds 
        significant information, they need to be saved on stack.
        Every element in an array holds information about registers that are
        saved for function call. */
    private static final int savedRegisters[] = new int[256];
    
    /* This variable represents maping for arguments of function. */
    public static final Map<Integer, Map<MemoryClassEnumeration, String>> registersPicker;
    
    /* This array holds information about registers
        that are used by picker (during argument calculations). 
        Because arguments could be functions, than, picker needs to store his
        state because it provides registers in order: rdi, rsi or edi, esi ect..
        So for every new function call, those registers also need to be saved on
        stack, and new state for picking register is initializes by moving
        registerPickerCountersTop and regPickFlagsTop to next index in array 
        witch represents new function call context.
    */
    private static final int registerPikcersFlags[] = new int[256];
    /* This variable holds information in witch function call contex program is */
    private static int regPickFlagsTop = -1;
    
    /* Helps during argument calculation and it works with registersPicker.
        It holds information in every function call, how many registers are
        taken by the picker */
    public static int registersPickerCounters[] = new int [256];
    /* This variable holds information in witch function call contex program is */
    public static int registerPikcerCountersTop = -1;
    
    /* Variable represents number of function calls. It is used with savedRegisters
        to save and restore informations for registers before and after function call  */
    private static int functionCalls = 0;
    
    /* Next variables represents possition of registers in flags.
        eax register is the right most bit, ebx is one on the left, and so on... */
    
    /* Represents number of saved registers on stack for computation. */
    public static int pushedRegistersOnStack = 0;
    
    /* String to registers mapping for lower 4 bytes of registers */
    private static final Map<String, Integer> storMap4Bytes;
    
    /* String to registers mapping whole 8 bytes of registers */
    private static final Map<String, Integer> storMap8Bytes;
    
    /* Registers to string mapping for lower 4 bytes of registers */
    private static final Map<Integer, String> rtosMap4Bytes;
    
    /* Registers to string mapping whole 8 bytes of registers */
    private static final Map<Integer, String> rtosMap8Bytes;
    
    public static final int AREG = 0x1;
    public static final int BREG = 0x2;
    public static final int CREG = 0x4;
    public static final int DREG = 0x8;
    public static final int SIREG = 0x10;
    public static final int DIREG = 0x20;
    public static final int R8REG = 0x40;
    public static final int R9REG = 0x80;
    public static final int R10REG = 0x100;
    public static final int R11REG = 0x200;
    public static final int R12REG = 0x400;
    public static final int R13REG = 0x800;
    public static final int R14REG = 0x1000;
    public static final int R15REG = 0x2000;
    
    /* Next variables is string representation of 4 bite parts of registers */
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
    
    /* Next variables is string representation of whole 8 bytes of registers */
    public static final String STRING_RAX = "rax";
    public static final String STRING_RBX = "rbx";
    public static final String STRING_RCX = "rcx";
    public static final String STRING_RDX = "rdx";
    public static final String STRING_RSI = "rsi";
    public static final String STRING_RDI = "rdi";
    public static final String STRING_R8 = "r8";
    public static final String STRING_R9 = "r9";
    public static final String STRING_R10 = "r10";
    public static final String STRING_R11 = "r11";
    public static final String STRING_R12 = "r12";
    public static final String STRING_R13 = "r13";
    public static final String STRING_R14 = "r14";
    public static final String STRING_R15 = "r15";
    
    /* Initialize mapping */
    static {
        storMap4Bytes = new HashMap<>();
        storMap4Bytes.put(STRING_EAX, AREG);
        storMap4Bytes.put(STRING_EBX, BREG);
        storMap4Bytes.put(STRING_ECX, CREG);
        storMap4Bytes.put(STRING_EDX, DREG);
        storMap4Bytes.put(STRING_ESI, SIREG);
        storMap4Bytes.put(STRING_EDI, DIREG);
        storMap4Bytes.put(STRING_R8D, R8REG);
        storMap4Bytes.put(STRING_R9D, R9REG);
        storMap4Bytes.put(STRING_R10D, R10REG);
        storMap4Bytes.put(STRING_R11D, R11REG);
        storMap4Bytes.put(STRING_R12D, R12REG);
        storMap4Bytes.put(STRING_R13D, R13REG);
        storMap4Bytes.put(STRING_R14D, R14REG);
        storMap4Bytes.put(STRING_R15D, R15REG);
        
        storMap8Bytes = new HashMap<>();
        storMap8Bytes.put(STRING_RAX, AREG);
        storMap8Bytes.put(STRING_RBX, BREG);
        storMap8Bytes.put(STRING_RCX, CREG);
        storMap8Bytes.put(STRING_RDX, DREG);
        storMap8Bytes.put(STRING_RSI, SIREG);
        storMap8Bytes.put(STRING_RDI, DIREG);
        storMap8Bytes.put(STRING_R8, R8REG);
        storMap8Bytes.put(STRING_R9, R9REG);
        storMap8Bytes.put(STRING_R10, R10REG);
        storMap8Bytes.put(STRING_R11, R11REG);
        storMap8Bytes.put(STRING_R12, R12REG);
        storMap8Bytes.put(STRING_R13, R13REG);
        storMap8Bytes.put(STRING_R14, R14REG);
        storMap8Bytes.put(STRING_R15, R15REG);
        
        
        rtosMap4Bytes = new HashMap<>();
        rtosMap4Bytes.put(AREG, STRING_EAX);
        rtosMap4Bytes.put(BREG, STRING_EBX);
        rtosMap4Bytes.put(CREG, STRING_ECX);
        rtosMap4Bytes.put(DREG, STRING_EDX);
        rtosMap4Bytes.put(SIREG, STRING_ESI);
        rtosMap4Bytes.put(DIREG, STRING_EDI);
        rtosMap4Bytes.put(R8REG, STRING_R8D);
        rtosMap4Bytes.put(R9REG, STRING_R9D);
        rtosMap4Bytes.put(R10REG, STRING_R10D);
        rtosMap4Bytes.put(R11REG, STRING_R11D);
        rtosMap4Bytes.put(R12REG, STRING_R12D);
        rtosMap4Bytes.put(R13REG, STRING_R13D);
        rtosMap4Bytes.put(R14REG, STRING_R14D);
        rtosMap4Bytes.put(R15REG, STRING_R15D);
        
        rtosMap8Bytes = new HashMap<>();
        rtosMap8Bytes.put(AREG, STRING_RAX);
        rtosMap8Bytes.put(BREG, STRING_RBX);
        rtosMap8Bytes.put(CREG, STRING_RCX);
        rtosMap8Bytes.put(DREG, STRING_RDX);
        rtosMap8Bytes.put(SIREG, STRING_RSI);
        rtosMap8Bytes.put(DIREG, STRING_RDI);
        rtosMap8Bytes.put(R8REG, STRING_R8);
        rtosMap8Bytes.put(R9REG, STRING_R9);
        rtosMap8Bytes.put(R10REG, STRING_R10);
        rtosMap8Bytes.put(R11REG, STRING_R11);
        rtosMap8Bytes.put(R12REG, STRING_R12);
        rtosMap8Bytes.put(R13REG, STRING_R13);
        rtosMap8Bytes.put(R14REG, STRING_R14);
        rtosMap8Bytes.put(R15REG, STRING_R15);
        
        /* Register picker intialization ****************************/
        
        /* Initialize map for rdi register */
        Map<MemoryClassEnumeration, String> diMap = new HashMap<>();
        diMap.put(MemoryClassEnumeration.INT, "edi");
        diMap.put(MemoryClassEnumeration.POINTER, "rdi");
        
        /* Initialize map for rsi register */
        Map<MemoryClassEnumeration, String> siMap = new HashMap<>();
        siMap.put(MemoryClassEnumeration.INT, "esi");
        siMap.put(MemoryClassEnumeration.POINTER, "rsi");
        
        /* Initialize map for rdx register */
        Map<MemoryClassEnumeration, String> dxMap = new HashMap<>();
        dxMap.put(MemoryClassEnumeration.INT, "edx");
        dxMap.put(MemoryClassEnumeration.POINTER, "rdx");
        
        /* Initialize map for rcx register */
        Map<MemoryClassEnumeration, String> cxMap = new HashMap<>();
        cxMap.put(MemoryClassEnumeration.INT, "ecx");
        cxMap.put(MemoryClassEnumeration.POINTER, "rcx");
        
        /* Initialize map for r8 register */
        Map<MemoryClassEnumeration, String> r8Map = new HashMap<>();
        r8Map.put(MemoryClassEnumeration.INT, "r8d");
        r8Map.put(MemoryClassEnumeration.POINTER, "r8");
        
        /* Initialize map for r9 register */
        Map<MemoryClassEnumeration, String> r9Map = new HashMap<>();
        r9Map.put(MemoryClassEnumeration.INT, "r9d");
        r9Map.put(MemoryClassEnumeration.POINTER, "r9");
        
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
        return new String(destination).trim();
    }

    public static String defineStringLiteral(String input) 
    {
        /* Get nasm-style string from c-style string */
        input = NasmTools.convertStringToNasmStringLiteral(input);
        /* Get next free literal name */
        String literalName = DataSegment.getNextStringLiteral();
        Writers.emitDefineDataSegment(literalName, input);
        return literalName;
    }
    
    public static boolean isTakenRegisterEDX() 
    {
        return (flags & DREG) != 0;
    }

    public static boolean isTakenRegisterEAX() 
    {
        return (flags & AREG) != 0;
    }
    

    /* Function determines witch is next position on stack
        that can hold value. */
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

    /* Function determines witch is next position on stack
        that can hold value. */
    private static String showStackDisplacement() {
        /* Get function analyser */
        FunctionsAnalyser fa; 
        fa = TranslationVisitor.functions.get(FunctionsAnalyser.getInProcess());
        /* Calculate taken memory on stack */
        int taken = fa.getSpaceForLocals() + fa.getSpaceForParams();
        
        /* First free location is ebp-4. So every next is calculated by
            multipliing pushedRegisters and sizeof(int).
            Also taken must be added consider already taken place on stack. */
        int disp = 
                taken + 
                Constants.SIZE_OF_INT + 
                Constants.SIZE_OF_INT * pushedRegistersOnStack;
        
        /* [rbp - displacement] is returned casted to dword
            TODO: Determine witch cast should be used  */
        return "dword [rbp-" + Integer.toString(disp) + "]";
    }
    
    public static boolean isRegister(String left) 
    {
        return storMap4Bytes.containsKey(left) || storMap8Bytes.containsKey(left);
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
    /* Return next free register if there is one. If there is no free registers
        function returns first free stack memory */
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
    
    /* Shows next free register if there is one. If there is no free registers
        function returns first free stack memory */
    public static String showNextFreeTemp() 
    {
        int register = -1;
        /* If all registers are taken */
        if (flags == ((1 << NUMBER_OF_REGISTERS) - 1)) 
            return showStackDisplacement();
        /* Look for first free register */
        
        for (int i = 0; i < NUMBER_OF_REGISTERS; i++) {
            if ((flags & (1 << i)) == 0) {
                register = 1 << i;
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
            case AREG:
                return "eax";
            case BREG:
                return "ebx";
            case CREG:
                return "ecx";
            case DREG:
                return "edx";
            case SIREG:
                return "esi";
            case DIREG:
                return "edi";
            case R8REG:
                return "r8d";
            case R9REG:
                return "r9d";
            case R10REG:
                return "r10d";
            case R11REG:
                return "r11d";
            case R12REG:
                return "r12d";
            case R13REG:
                return "r13d";
            case R14REG:
                return "r14d";
            case R15REG:
                return "r15d";
            default:
                break;
        }
        return null;
    }
    
    private static int stringToRegister(String source) 
    {
        Integer res = storMap4Bytes.get(source);
        if (res != null)
            return res;
        return storMap8Bytes.get(source);
    }

    public static int getSize(MemoryClassEnumeration typeSpecifier) 
    {
        switch (typeSpecifier) {
            case INT:
                return Constants.SIZE_OF_INT;
            case POINTER:
                return Constants.SIZE_OF_POINTER;    
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
    /* Function that copyes all parameters on stack for further use 
        It is used in function definition. 
    Warning: 
        If there is more than 6 non floating variables passed to function, than
        they are pushed in reverse order. This case won't work! */
    static void moveArgsToStack(List<picoCParser.ParameterContext> parameters) 
    {
        initializeNewPickers();
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
    
    
    /* Function passes arguments in function call to registers. 
        The order of passing non floating kind of argument is:
        arg1   arg2   arg3   arg4    arg5    arg6   arg7    arg8     arg9 
        rdi    rsi    rdx    rcx      r8      r9   push3 <- push2 <- push1
    Of course, diferent parts of registers is used for different types
    */
    static void moveArgsToRegisters
    (TranslationVisitor visitor, List<picoCParser.ArgumentContext> arguments) 
    {
        /* If there is no arguments */
        if (arguments == null)
            return;
        int lsize = arguments.size();
        String res, reg;
        MemoryClassEnumeration memclass;
        /* Set pickers for next function */
        initializeNewPickers();
        
        for (int i = 0; i < lsize; ++i) {
            /* Visit expression or STRING_LITERAL */
            res = visitor.visit(arguments.get(i));
            /* Get variable size based on register it is stored in */
            memclass = getMemoryClassFromRegister(res);
            /* Get memory class of typeSpecifier and register 
                in witch it is passed to function */
            reg = getNextRegForFuncCall(memclass);

            /* Emit copying from registers to stack memory for arguments setup */
            Writers.emitInstruction("mov", reg, res);
        }
        resetRegisterPicker();
    }
    
    
    static void initializeNewPickers() {
        ++registerPikcerCountersTop;
        ++regPickFlagsTop;
    }
    
    /* This function returns, for some function argument, in witch register
        argument is passed during function call. For example, first int argument
        is passed to edi register, second to esi ect. 
        If no registers is free, function returns next position on stack that
        holds argument value. 
    Warning: 
        If there is more than 6 non floating variables passed to function, than
        they are pushed in reverse order. This case won't work! */
    private static String getNextRegForFuncCall(MemoryClassEnumeration memclass) 
    {
        /* If all registers is taken, than argument is passed on stack */
        if (registersPickerCounters[registerPikcerCountersTop] > 5)
            return pushArgumentOnStack(memclass);
        else {
            String res = 
                    registersPicker.
                            get(registersPickerCounters[registerPikcerCountersTop]++)
                                .get(memclass);
            int register = stringToRegister(res);
            flags |= register;
            registerPikcersFlags[regPickFlagsTop] |= register;
            return res;
        }
    }
    
    /* Resets register counter for further usage and free flags */
    private static void resetRegisterPicker() 
    {
        registersPickerCounters[registerPikcerCountersTop] = 0;
        registerPikcersFlags[regPickFlagsTop] = 0x0;
        --regPickFlagsTop;
        --registerPikcerCountersTop;
    }
    
    
    /* Since result of expression and other operations is always stored in "a"
        register, function checks witch part of a register is used, and
        based on that, return size of variable stored in it */
    private static MemoryClassEnumeration getMemoryClassFromRegister(String res) 
    {
        switch (res) {
            case "rax":
                return MemoryClassEnumeration.POINTER;
            case "eax":
                return MemoryClassEnumeration.INT;
            default:    /* It is string literal name then: */
                return MemoryClassEnumeration.POINTER;
        }
    }
    
    /* This method is used for storing registers that holds some value.
        It is usualy done for function call. Registers are pushed in 
        normal order (of coure normal order in my implementation :) ),
        and later they are restored in reverse order. */
    static void saveRegistersOnStack() 
    {
        int reg;
        String strReg;
        /* Save current flags for some function before function call */
        savedRegisters[functionCalls++] = flags;
        for (int i = 0; i < NUMBER_OF_REGISTERS; i++) {
            /* If register is used, than it needs to be pushed on stack */
            reg = flags & (1 << i);
            if (reg != 0) {
                strReg = rtosMap8Bytes.get(reg);
                Writers.emitInstruction("push", strReg);
                System.out.println("push     " + strReg);
            }
        }
        /* Set flags to 0 */
        freeAllRegisters();
    }
    /* Next function resotores registers that are pushed on stack 
        before function call. Registers are restored after function call. 
        Also, it is done in reversed order of pushing. */
    static void restoreRegisters() 
    {
        int reg;
        String strReg;
        /* Get value of flags for current function (restore state to previous) */
        flags = savedRegisters[--functionCalls];
        for (int i = 0; i < NUMBER_OF_REGISTERS; i++) {
            reg = flags & (1 << (NUMBER_OF_REGISTERS - 1 - i));
            if (reg != 0) {
                strReg = rtosMap8Bytes.get(reg);
                Writers.emitInstruction("pop", strReg);
                System.out.println("pop     " + strReg);
            }
        }
    }

    /* Not implemented yet */
    private static String pushArgumentOnStack(MemoryClassEnumeration memclass) 
    {
        return null;
    }

    /* TODO: Make map of C standard library functions, 
        and just call map.get(fname) != null */
    static boolean isFunctionFromLib(String functionName) 
    {
        switch (functionName) {
            case "printf":
                return true;
            default:
                return false;
        }
    }

    
}
