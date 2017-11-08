package nasm;


import antlr.picoCParser;
import compilationControlers.Writers;
import antlr.TranslationVisitor;
import compilationControlers.Checker;
import tools.FunctionsAnalyser;
import constants.Constants;
import constants.MemoryClassEnum;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import tools.Emitter;
import tools.ExpressionObject;
import tools.LabelsMaker;
import tools.RelationHelper;

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
    public static final Map<Integer, Map<MemoryClassEnum, String>> registersPicker;
    
    /* This array holds information about registers
        that are used by picker (during argument calculations). 
        Because arguments could be functions, than, picker needs to store his
        state because it provides registers in order: rdi, rsi or edi, esi ect..
        So for every new function call, those registers also need to be saved on
        stack, and new state for picking register is initializes by moving
        registerPickerCountersTop and regPickFlagsTop to next index in array 
        which represents new function call context.
    */
    private static final int registerPikcersFlags[] = new int[256];
    /* This variable holds information in which function call contex program is */
    private static int regPickFlagsTop = -1;
    
    /* Helps during argument calculation and it works with registersPicker.
        It holds information in every function call, how many registers are
        taken by the picker */
    public static int registersPickerCounters[] = new int [256];
    /* This variable holds information in which function call contex program is */
    public static int registerPikcerCountersTop = -1;
    
    /* Variable represents number of function calls. It is used with savedRegisters
        to save and restore informations for registers before and after function call  */
    private static int functionCalls = 0;
    
    /* Next variables represents possition of registers in flags.
        eax register is the right most bit, ebx is one on the left, and so on... */
    
    /* Represents number of saved registers on stack for computation. */
    public static int pushedRegistersOnStack = 0;
    
    /* List represents sizes of saved registers on stack for computation.
        It works as stack. */
    public static LinkedList<Integer> pushedRegistersSizes;
    
    /* Represents total size of saved registers on stack for computation */
    public static int sizeOfPushedRegisters = 0;
    
    /* String to registers mapping for least significant byte of registers */
    private static final Map<String, Integer> storMap1Bytes;
    
    /* String to registers mapping for lower 4 bytes of registers */
    private static final Map<String, Integer> storMap4Bytes;
    
    /* String to registers mapping whole 8 bytes of registers */
    private static final Map<String, Integer> storMap8Bytes;
    
    /* Registers to string mapping for least significant byte of registers */
    private static final Map<Integer, String> rtosMap1Bytes;
    
    /* Registers to string mapping for lower 4 bytes of registers */
    private static final Map<Integer, String> rtosMap4Bytes;
    
    /* Registers to string mapping whole 8 bytes of registers */
    private static final Map<Integer, String> rtosMap8Bytes;
    
    /* Positions of registers in registers flags */
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
    
    /* Next variables is string representation of 4 byte parts of registers */
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
    
    /* Next variables is string representation of the 
        least significant byte in registers (right most byte)   */
    public static final String STRING_AL = "al";
    public static final String STRING_BL = "bl";
    public static final String STRING_CL = "cl";
    public static final String STRING_DL = "dl";
    public static final String STRING_SIL = "sil";
    public static final String STRING_DIL = "dil";
    public static final String STRING_R8B = "r8b";
    public static final String STRING_R9B = "r9b";
    public static final String STRING_R10B = "r10b";
    public static final String STRING_R11B = "r11b";
    public static final String STRING_R12B = "r12b";
    public static final String STRING_R13B = "r13b";
    public static final String STRING_R14B = "r14b";
    public static final String STRING_R15B = "r15b";
    
    /* Initialize mapping and pushed registers list */
    static {
        pushedRegistersSizes = new LinkedList<>();
        
        storMap1Bytes = new HashMap<>();
        storMap1Bytes.put(STRING_AL, AREG);
        storMap1Bytes.put(STRING_BL, BREG);
        storMap1Bytes.put(STRING_CL, CREG);
        storMap1Bytes.put(STRING_DL, DREG);
        storMap1Bytes.put(STRING_SIL, SIREG);
        storMap1Bytes.put(STRING_DIL, DIREG);
        storMap1Bytes.put(STRING_R8B, R8REG);
        storMap1Bytes.put(STRING_R9B, R9REG);
        storMap1Bytes.put(STRING_R10B, R10REG);
        storMap1Bytes.put(STRING_R11B, R11REG);
        storMap1Bytes.put(STRING_R12B, R12REG);
        storMap1Bytes.put(STRING_R13B, R13REG);
        storMap1Bytes.put(STRING_R14B, R14REG);
        storMap1Bytes.put(STRING_R15B, R15REG);
        
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
        
        rtosMap1Bytes = new HashMap<>();
        rtosMap1Bytes.put(AREG, STRING_AL);
        rtosMap1Bytes.put(BREG, STRING_BL);
        rtosMap1Bytes.put(CREG, STRING_CL);
        rtosMap1Bytes.put(DREG, STRING_DL);
        rtosMap1Bytes.put(SIREG, STRING_SIL);
        rtosMap1Bytes.put(DIREG, STRING_DIL);
        rtosMap1Bytes.put(R8REG, STRING_R8B);
        rtosMap1Bytes.put(R9REG, STRING_R9B);
        rtosMap1Bytes.put(R10REG, STRING_R10B);
        rtosMap1Bytes.put(R11REG, STRING_R11B);
        rtosMap1Bytes.put(R12REG, STRING_R12B);
        rtosMap1Bytes.put(R13REG, STRING_R13B);
        rtosMap1Bytes.put(R14REG, STRING_R14B);
        rtosMap1Bytes.put(R15REG, STRING_R15B);
        
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
        Map<MemoryClassEnum, String> diMap = new HashMap<>();
        diMap.put(MemoryClassEnum.CHAR, "dil");
        diMap.put(MemoryClassEnum.INT, "edi");
        diMap.put(MemoryClassEnum.POINTER, "rdi");
        
        /* Initialize map for rsi register */
        Map<MemoryClassEnum, String> siMap = new HashMap<>();
        siMap.put(MemoryClassEnum.CHAR, "sil");
        siMap.put(MemoryClassEnum.INT, "esi");
        siMap.put(MemoryClassEnum.POINTER, "rsi");
        
        /* Initialize map for rdx register */
        Map<MemoryClassEnum, String> dxMap = new HashMap<>();
        dxMap.put(MemoryClassEnum.CHAR, "dl");
        dxMap.put(MemoryClassEnum.INT, "edx");
        dxMap.put(MemoryClassEnum.POINTER, "rdx");
        
        /* Initialize map for rcx register */
        Map<MemoryClassEnum, String> cxMap = new HashMap<>();
        cxMap.put(MemoryClassEnum.CHAR, "cl");
        cxMap.put(MemoryClassEnum.INT, "ecx");
        cxMap.put(MemoryClassEnum.POINTER, "rcx");
        
        /* Initialize map for r8 register */
        Map<MemoryClassEnum, String> r8Map = new HashMap<>();
        r8Map.put(MemoryClassEnum.CHAR, "r8b");
        r8Map.put(MemoryClassEnum.INT, "r8d");
        r8Map.put(MemoryClassEnum.POINTER, "r8");
        
        /* Initialize map for r9 register */
        Map<MemoryClassEnum, String> r9Map = new HashMap<>();
        r9Map.put(MemoryClassEnum.CHAR, "r9b");
        r9Map.put(MemoryClassEnum.INT, "r9d");
        r9Map.put(MemoryClassEnum.POINTER, "r9");
        
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
    
    public static boolean isTakenRegisterAREG() 
    {
        return (flags & AREG) != 0;
    }
    
    public static boolean isTakenRegisterBREG() 
    {
        return (flags & BREG) != 0;
    }
    
    public static boolean isTakenRegisterCREG() 
    {
        return (flags & CREG) != 0;
    }
    
    public static boolean isTakenRegisterDREG() 
    {
        return (flags & DREG) != 0;
    }

    

    /* Function determines which is next position on stack
        that can hold value and returns Expression object with that information. */
    public static ExpressionObject getStackDisp(MemoryClassEnum type) 
    {
        /* Get function analyser */
        FunctionsAnalyser fa; 
        fa = TranslationVisitor.functions.get(FunctionsAnalyser.getInProcess());
        /* Calculate taken memory on stack */
        int taken = fa.getSpaceForLocals() + fa.getSpaceForParams();
        int size = NasmTools.getSize(type);
        /* Make room for integer */
        Writers.emitInstruction(
                "sub", "rsp", Integer.toString(size));
        /* New location on stack in calculated by adding taken memory on
            stack for local variables and parameters, 
            and memory for pushed registers. */
        sizeOfPushedRegisters += size;
        int disp = taken + sizeOfPushedRegisters;
        pushedRegistersSizes.push(size);
        ++pushedRegistersOnStack;
        /* Cast variable to proper type */
        String cast = NasmTools.getCast(type);
        String text = cast + " [rbp-" + Integer.toString(disp) + "]";
        return new ExpressionObject(text, type, ExpressionObject.VAR_STACK);
    }

    public static String getStackDispStr(MemoryClassEnum type) 
    {
        /* Get function analyser */
        FunctionsAnalyser fa; 
        fa = TranslationVisitor.functions.get(FunctionsAnalyser.getInProcess());
        /* Calculate taken memory on stack */
        int taken = fa.getSpaceForLocals() + fa.getSpaceForParams();
        int size = NasmTools.getSize(type);
        /* Make room for integer */
        Writers.emitInstruction(
                "sub", "rsp", Integer.toString(size));
        /* New location on stack in calculated by adding taken memory on
            stack for local variables and parameters, 
            and memory for pushed registers. */
        sizeOfPushedRegisters += size;
        int disp = taken + sizeOfPushedRegisters;
        pushedRegistersSizes.push(size);
        ++pushedRegistersOnStack;
        /* Cast variable to proper type */
        String cast = NasmTools.getCast(type);
        String text = cast + " [rbp-" + Integer.toString(disp) + "]";
        return text;
    }
 
    public static String showStackDispl(MemoryClassEnum type) 
    {
        /* Get function analyser */
        FunctionsAnalyser fa; 
        fa = TranslationVisitor.functions.get(FunctionsAnalyser.getInProcess());
        /* Calculate taken memory on stack */
        int taken = fa.getSpaceForLocals() + fa.getSpaceForParams();
        int size = NasmTools.getSize(type);
        int disp = taken + sizeOfPushedRegisters;
        
        /* Cast variable to proper type */
        String cast = NasmTools.getCast(type);
        String text = cast + " [rbp-" + Integer.toString(disp) + "]";
        return text;
    }
    
    
    public static boolean isRegister(String left) 
    {
        return storMap1Bytes.containsKey(left)
                || storMap4Bytes.containsKey(left) 
                || storMap8Bytes.containsKey(left);
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
            case picoCParser.ASSIGN_ADD:
                return "add";
            case picoCParser.ASSIGN_SUB:
                return "sub";
            default:
                return null;
        }
    }
    
    /* Shows next free register if there is one. If there is no free registers
        function returns first free stack memory */
    public static String showNextFreeTemp(MemoryClassEnum type) 
    {
        int register = -1;
        /* If all registers are taken */
        if (flags == ((1 << NUMBER_OF_REGISTERS) - 1)) 
            return showStackDispl(type);
        /* Else, look for first free register */
        for (int i = 0; i < NUMBER_OF_REGISTERS; i++) {
            if ((flags & (1 << i)) == 0) {
                register = 1 << i;
                break;
            }
        }
        return NasmTools.registerToString(register, type);
    }
    
    /* Funtion cleans up memory location that is used for computations. 
        That can be register or some value on stack. */
    public static void free(ExpressionObject expr) 
    {
        /* If there is no saved values on stack */
        if (expr.isRegister()) {
            int register;
            register = NasmTools.stringToRegister(expr.getText());
            flags ^= register;
        } else {
            String s = Integer.toString(expr.getSize());
            Writers.emitInstruction("add", "rsp", s);
            --pushedRegistersOnStack;
            sizeOfPushedRegisters -= pushedRegistersSizes.pop();
        }
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
            int size = NasmTools.sizeOf(source);
            String s = Integer.toString(size);
            Writers.emitInstruction("add", "rsp", s);
            --pushedRegistersOnStack;
            sizeOfPushedRegisters -= pushedRegistersSizes.pop();
        }
    }
    
    /* Returns string representation of 1 byte register */
    public static String registerToString1Bytes(int register) 
    {
        return rtosMap1Bytes.get(register);
    }
    
    /* Returns string representation of 4 byte register */
    public static String registerToString4Bytes(int register) 
    {
        return rtosMap4Bytes.get(register);
    }
    
    /* Returns string representation of 8 byte register */
    public static String registerToString8Bytes(int register) 
    {
        return rtosMap8Bytes.get(register);
    }
    
    /* Returns string representation of register if input string is register.
        Otherwise, -1 is returned */
    public static int stringToRegister(String stringRegister) 
    {
        Integer register;
        if ((register = storMap1Bytes.get(stringRegister)) != null)
            return register;
        if ((register = storMap4Bytes.get(stringRegister)) != null)
            return register;
        if ((register = storMap8Bytes.get(stringRegister)) != null)
            return register;
        return -1;
    }

    /* Returns size of type specifier */
    public static int getSize(MemoryClassEnum typeSpecifier) 
    {
        switch (typeSpecifier) {
            case CHAR:
                return Constants.SIZE_OF_CHAR;
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

    public static void freeAllRegisters() 
    {
        /* Clear flags regiser */
        flags = 0x0;
    }
    /* Function that copyes all parameters on stack for further use 
        It is used in function definition. 
    Warning: 
        Function may contain 6 parameters at most! */
    public static void moveArgsToStack
    (List<picoCParser.ParameterContext> parameters, ExpressionObject arg) 
    {
        int lsize = parameters.size();
        String reg, paramName, stackPos, paramPos, cast;
        MemoryClassEnum memclass;
        initializeNewPickers();
        for (int i = 0; i < lsize; ++i) {
            /* argument name needed for it's position on stack */
            paramName = arg.getName();
            /* get argument's position on stack */
            stackPos = arg.getStackDisp();
            /* Get memory class of typeSpecifier and register 
                in which it is passed to function */
            memclass = arg.getTypeOfPointer();
            cast = NasmTools.getCast(memclass);
            paramPos = cast + " [" + stackPos + "]";
            reg = getNextRegForFuncCall(memclass);
            
            /* Emit copying from registers to stack for arguments */
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
    public static void moveArgsToRegisters
    (TranslationVisitor visitor, List<picoCParser.ArgumentContext> arguments) 
    {
        /* If there is no arguments */
        if (arguments == null)
            return;
        int lsize = arguments.size();
        ExpressionObject res;
        String reg;
        MemoryClassEnum type;
        /* Set pickers for next function */
        initializeNewPickers();
        
        for (int i = 0; i < lsize; ++i) {
            /* Visit expression or STRING_LITERAL */
            res = visitor.visit(arguments.get(i));
            res.comparisonCheck();
            /* Get variable size based on register it is stored in.
                Minimum size for argument is 4 bytes which is INT type */
            type = res.getType();
            if (type == MemoryClassEnum.CHAR || type == MemoryClassEnum.INT) {
                reg = getNextRegForFuncCall(MemoryClassEnum.INT);
            } else {
                /* TODO: Pointer types */
                reg = getNextRegForFuncCall(type);
            }
            
            /* Emit copying from memory/registers to registers for arguments setup.
                If argument is char type than movsx instruction is emited. */
            if (type == MemoryClassEnum.CHAR)
                Writers.emitInstruction("movsx", reg, res.getText());
            else
                Writers.emitInstruction("mov", reg, res.getText());
            
            if (res.isRegister())
                res.freeRegister();
        }
        resetRegisterPicker();
    }
    
    
    public static void initializeNewPickers() 
    {
        ++registerPikcerCountersTop;
        ++regPickFlagsTop;
    }
    
    /* This function returns, for some function argument, in which register
        argument is passed during function call. For example, first int argument
        is passed to edi register, second to esi ect. 
        If no registers is free, function returns next position on stack that
        holds argument value. 
    Warning: 
        If there is more than 6 non floating variables passed to function, than
        they are pushed in reverse order. This case won't work! */
    public static String getNextRegForFuncCall(MemoryClassEnum memclass) 
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
    public static void resetRegisterPicker() 
    {
        registersPickerCounters[registerPikcerCountersTop] = 0;
        registerPikcersFlags[regPickFlagsTop] = 0x0;
        --regPickFlagsTop;
        --registerPikcerCountersTop;
    }
    
    public static MemoryClassEnum getMemoryClassFromSize(int size) 
    {
        switch (size) {
            case Constants.SIZE_OF_CHAR:
                return MemoryClassEnum.CHAR;
            case Constants.SIZE_OF_INT:
                return MemoryClassEnum.INT;
            case Constants.SIZE_OF_POINTER:    /* It is string literal name then: */
                return MemoryClassEnum.POINTER;
            default:
                return MemoryClassEnum.VOID;
        }
    }
    
    /* This method is used for storing registers that holds some value.
        It is usualy done for function call. Registers are pushed in 
        normal order (of coure normal order in my implementation :) ),
        and later they are restored in reverse order. */
    public static void saveRegistersOnStack() 
    {
        int reg;
        String strReg;
        /* Save current flags for some function before function call */
        savedRegisters[functionCalls++] = flags;
        for (int i = 0; i < NUMBER_OF_REGISTERS; i++) {
            /* If register is used, than it needs to be pushed on stack */
            reg = flags & (1 << i);
            if (reg != 0) {
                /* Always store whole register */
                strReg = rtosMap8Bytes.get(reg);
                Writers.emitInstruction("push", strReg);
            }
        }
        /* Set flags to 0 */
        freeAllRegisters();
    }
    /* Next function resotores registers that are pushed on stack 
        before function call. Registers are restored after function call. 
        Also, it is done in reversed order of pushing. */
    public static void restoreRegisters() 
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
            }
        }
    }

    /* Not implemented yet */
    public static String pushArgumentOnStack(MemoryClassEnum memclass) 
    {
        return null;
    }

    /* Function determines wheather 4 general purpose registers a, b, c and are
        available or not */
    public static boolean isTakenRegisterMightyFour() 
    {
        return isTakenRegisterAREG()
                && isTakenRegisterBREG()
                && isTakenRegisterCREG()
                && isTakenRegisterDREG();
    }

    /* Function returns size of variable or register in bytes */
    public static int sizeOf(String var) 
    {
        /* If var only contains digit, than it is integer number */
        if (isInteger(var))
            return Constants.SIZE_OF_INT;
        /* If there is mapping for 1 byte vars, size is 1 */
        if (storMap1Bytes.containsKey(var))
            return Constants.SIZE_OF_CHAR;
        /* If there is mapping for 4 byte vars, size is 4 */
        if (storMap4Bytes.containsKey(var))
            return Constants.SIZE_OF_INT;
        /* If there is mapping for 8 byte vars, size is 8 */
        if (storMap8Bytes.containsKey(var))
            return Constants.SIZE_OF_POINTER;
        /* If it is not register, then check stack position for variable */
        /* If var has prefix cast to byte, then size is 1 */
        if (var.startsWith(Constants.STRING_BYTE))
            return Constants.SIZE_OF_CHAR;
        /* If var has prefix cast to double word, then size is 4 */
        if (var.startsWith(Constants.STRING_DWORD))
            return Constants.SIZE_OF_INT;
        /* If var has prefix cast to double word, then size is 4 */
        if (var.startsWith(Constants.STRING_QWORD))
            return Constants.SIZE_OF_POINTER;
        /* Default return for string literals */
        return Constants.SIZE_OF_POINTER;
    }

    /* Cast variable to proper size */
    public static String castVariable(String var, int size) 
    {
        /* If variable is register */
        if (NasmTools.isRegister(var)) {
            if (size == Constants.SIZE_OF_CHAR)
                return castRegisterToChar(var);
            if (size == Constants.SIZE_OF_INT)
                return castRegisterToInt(var);
            if (size == Constants.SIZE_OF_POINTER)
                return castRegisterToPointer(var);
        } 
        return null;
    }

    /* Function takes register, and converts it to it's 1 byte representation.
        If register is already 1 byte register, than function returns 
        input register. If register has more than 1 byte, than instructions
        doesn't need to be emited to file, because function will just
        return 1 byte representation of register. This is usually used for
        converting 4 byte register representation to 1 byte register
        representation for evaluating comparison of some kind. */
    public static String castRegisterToChar(String inputRegister) 
    {
        int sizeOfInputRegister = NasmTools.sizeOf(inputRegister);
        if (sizeOfInputRegister == Constants.SIZE_OF_CHAR)
            return inputRegister;
        int register = stringToRegister(inputRegister);
        String oneByteRegister = rtosMap1Bytes.get(register);
        return oneByteRegister;
    }
    
    /* Function takes register, and converts it to it's 4 byte representation.
        If register is already 4 byte register, than function returns 
        input register. Function also emits converting instruction if size
        of input register is less than 4 bytes */
    public static String castRegisterToInt(String inputRegister) 
    {
        int sizeOfInputRegister = NasmTools.sizeOf(inputRegister);
        if (sizeOfInputRegister == Constants.SIZE_OF_INT)
            return inputRegister;
        int register = stringToRegister(inputRegister);
        String fourByteRegister = rtosMap4Bytes.get(register);
        /* Zero extend register if it input register is one byte */
        if (sizeOfInputRegister == Constants.SIZE_OF_CHAR)
            Writers.emitInstruction("movsx", fourByteRegister, inputRegister);
        return fourByteRegister;
    }
    
    /* Function takes register, and converts it to it's 8 byte representation.
        If register is already 8 byte register, than function returns 
        input register */
    public static String castRegisterToPointer(String inputRegister) 
    {
        int sizeOfInputRegister = NasmTools.sizeOf(inputRegister);
        if (sizeOfInputRegister == Constants.SIZE_OF_POINTER)
            return inputRegister;
        int register = stringToRegister(inputRegister);
        String eightByteRegister = rtosMap8Bytes.get(register);
        /* Zero extend register if it input register is one byte or 4 bytes */
        if (sizeOfInputRegister == Constants.SIZE_OF_CHAR
                || sizeOfInputRegister == Constants.SIZE_OF_INT)
            Writers.emitInstruction("movsx", eightByteRegister, inputRegister);
        return eightByteRegister;
    }

    /* Calculates size of input variables (or register(s)) and returns
        greater one */
    public static int maxSizeOfVars(String var1, String var2)
    {
        int sizeOfLeft, sizeOfRight;
        sizeOfLeft = NasmTools.sizeOf(var1);
        sizeOfRight = NasmTools.sizeOf(var2);
        return sizeOfLeft < sizeOfRight ? sizeOfRight : sizeOfLeft;
    }

    /* Function casts input variable to char variable. If input variable is
       already a char variable, then input variable is returned */
    public static String castVarToChar(String inputVar) 
    {
        int sizeOfInputVar = NasmTools.sizeOf(inputVar);
        if (sizeOfInputVar == Constants.SIZE_OF_CHAR)
            return inputVar;
        return NasmTools.castVarToSizeOf(inputVar, Constants.SIZE_OF_CHAR);
    }
    /* Function casts input variable to int variable. If input variable is
       already a int variable, then input variable is returned */
    public static String castVarToInt(String inputVar) 
    {
        int sizeOfInputVar = NasmTools.sizeOf(inputVar);
        if (sizeOfInputVar == Constants.SIZE_OF_INT)
            return inputVar;
        return NasmTools.castVarToSizeOf(inputVar, Constants.SIZE_OF_INT);
    }

    /* Function casts input variable to pointer variable. If input variable is
       already a pointer variable, then input variable is returned */
    public static String castVarToPointer(String inputVar) 
    {
        int sizeOfInputVar = NasmTools.sizeOf(inputVar);
        if (sizeOfInputVar == Constants.SIZE_OF_POINTER)
            return inputVar;
        return NasmTools.castVarToSizeOf(inputVar, Constants.SIZE_OF_POINTER);
    }
    
    /* Function casts variable to proper size. */
    public static String castVarToSizeOf(String inputVar, int size) 
    {
        String newVar = inputVar.substring(inputVar.indexOf("["));
        switch (size) {
            case Constants.SIZE_OF_CHAR :
                newVar = Constants.STRING_BYTE + " " + newVar;
                break;
            case Constants.SIZE_OF_INT :
                newVar = Constants.STRING_DWORD + " " + newVar;
                break;
            case Constants.SIZE_OF_POINTER :
                newVar = Constants.STRING_QWORD + " " + newVar;
                break;
            default :
                return inputVar;
        }
        return newVar;
    }
    
    /* Function determines is there any free registers left */
    public static boolean hasFreeRegisters() 
    {
        return flags != ((1 << NUMBER_OF_REGISTERS) - 1);
    }

    /* Function check wheather var is integer */
    public static boolean isInteger(String var) 
    {
        return var.matches("\\d+");
    }

    /* Function check wheather var is Stack Variable */
    public static boolean isStackVariable(String var) 
    {
        return var.matches(".?word( )*\\[.*\\]");
    }
    
    /* Function calculates: left operation right, based on type of
        operation  */
    public static String calculate
    (String left, String right, int typeOfOperation, picoCParser.MulDivModContext ctx) 
    {
        int a = Integer.parseInt(left);
        int b = Integer.parseInt(right);
        int res = 0;
        if (typeOfOperation == picoCParser.DIV || typeOfOperation == picoCParser.MOD)
            if (!Checker.checkDivisionByZero(a, b, ctx))
                return null;
        
        switch(typeOfOperation) {
            case picoCParser.MUL:
                res = a*b;
                break;
            case picoCParser.DIV:
                res = a/b;
                break;
            case picoCParser.MOD:
                res = a%b;
                break;
        }
        return Integer.toString(res);
    }
  
    /* Function calculates: left operation right, based on type of
        operation  */
    public static String calculate
    (String left, String right, int typeOfOperation, picoCParser.AddSubContext ctx) 
    {
        int a = Integer.parseInt(left);
        int b = Integer.parseInt(right);
        int res = 0;
        
        switch(typeOfOperation) {
            case picoCParser.ADD:
                res = a+b;
                break;
            case picoCParser.SUB:
                res = a-b;
                break;
        }
        return Integer.toString(res);
    } 

    /* Function tries to optimize further calculation by returning register
        from relation if there is any */
    public static ExpressionObject chooseReturnRelation
    (ExpressionObject left, ExpressionObject right) 
    {
        if (left.isInteger()) {
            if (right.isRegister())
                right.freeRegister();
            return left;
        } 
        if (right.isInteger()) {
            if (left.isRegister())
                left.freeRegister();
            return right;
        }
        return left;
    }

    public static String get4ByteDRegister() 
    {
        flags |= DREG;
        return rtosMap4Bytes.get(DREG);
    }

    public static MemoryClassEnum getTypeOfVar(int tokenType) 
    {
        switch (tokenType) {
            case picoCParser.VOIDTYPE :
                return MemoryClassEnum.VOID;
            case picoCParser.CHARTYPE :
                return MemoryClassEnum.CHAR;
            case picoCParser.INTTYPE :
                return MemoryClassEnum.INT;
            case picoCParser.STRING_LITERAL : 
                return MemoryClassEnum.POINTER;
        }
        return null;
    }

    /* Gets cast that should be used for casting pointer when accesing
        variable on stack. For example, var could be int variable:
        dword [rbp-4], so this function is responsible for "dword" cast  */
    public static String getCast(MemoryClassEnum type) 
    {
        switch (type) {
            case CHAR :
                return Constants.STRING_BYTE;
            case INT :
                return Constants.STRING_DWORD;
            case POINTER :
                return Constants.STRING_QWORD;
        }
        return null;
    }

    public static ExpressionObject getNextFreeTempObj(MemoryClassEnum type) 
    {
        int register = -1;
        /* If all registers are taken */
        if (!hasFreeRegisters()) 
            return getStackDisp(type);
        /* Look for first free register */
        
        for (int i = 0; i < NUMBER_OF_REGISTERS; i++) {
            if ((flags & (1 << i)) == 0) {
                register = 1 << i;
                flags |= register;
                break;
            }
        }
        String text = NasmTools.registerToString(register, type);
        return new ExpressionObject(text, type, ExpressionObject.REGISTER);
    }

    public static String getNextFreeTempStr(MemoryClassEnum type) 
    {
        int register = -1;
        /* If all registers are taken */
        if (!hasFreeRegisters()) 
            return getStackDispStr(type);
        /* Look for first free register */
        
        for (int i = 0; i < NUMBER_OF_REGISTERS; i++) {
            if ((flags & (1 << i)) == 0) {
                register = 1 << i;
                flags |= register;
                break;
            }
        }
        String text = NasmTools.registerToString(register, type);
        return text;
    }
    
    public static String registerToString(int register, MemoryClassEnum type) 
    {
        switch (type) {
            case VOID :
                return registerToString4Bytes(register);
            case CHAR :
                return registerToString1Bytes(register);
            case INT :
                return registerToString4Bytes(register);
            case POINTER :
                return registerToString8Bytes(register);
        }
        return null;
    }

    public static boolean isRegisterA(String strRegister) 
    {
        int register = stringToRegister(strRegister);
        return register == AREG;
    }

    /* Function insert new type that current variable points to.
        If list is empty that means that pointer to simple type is inserted.
        If it is not empty that means that pointer to pointer is declared. */
    public static void insertPointerType(LinkedList<MemoryClassEnum> curPointer, MemoryClassEnum type) 
    {
        if (curPointer.isEmpty())
            curPointer.push(type);
        else
            curPointer.push(MemoryClassEnum.POINTER);
    }

    public static void switchStacks
    (LinkedList<MemoryClassEnum> curPointer, LinkedList<MemoryClassEnum> pointerType)
    {
        if (curPointer.isEmpty())
            return;
        MemoryClassEnum type = curPointer.pop();
        switchStacks(curPointer, pointerType);
        pointerType.push(type);
    }
    
    public static void copyPointerList
    (LinkedList<MemoryClassEnum> list1, LinkedList<MemoryClassEnum> list2) 
    {
        list2.forEach((i) -> {
            list1.addLast(i);
        });
    }

    public static String getShiftForPointer(MemoryClassEnum memclass) 
    {
        switch (memclass) {
            case CHAR:
                return "0";
            case INT:
                return "2";
            case POINTER:
                return "3";
        }
        return null;
    }
 
    public static void main(String[] args) 
    {
        String s = "\t";
        s = getConstantCharValue(s);
        System.out.println(s);
    }

    /* Converts char constant such as 'A', '\t' ect. into integer value.
        String representation of that integer value is returned. 
        Since, input string is given literally as 
        \'.\' or \'\\.\' where . is any character, characters with backslash
        whithin input string needs to be freed from backslashes. 
        Only legal characters with backslash character before them 
        are: '\n', '\r' and '\t' */
    public static String getConstantCharValue(String charSeq) 
    {
        int res;
        /* remove \' from input string */
        charSeq = charSeq.replaceAll("\\\'", "");
        if (charSeq.length() == 1)
            res = charSeq.codePointAt(0);
        else {
            /* remove \\ from input string */
            charSeq = charSeq.replaceAll("\\\\", "");
            char ch = charSeq.charAt(0);
            switch (ch) {
                case 'n':
                    res = '\n';
                    break;
                case 'r':
                    res = '\r';
                    break;
                case 't':
                    res = '\t';
                    break;
                default:
                    res = -1;
                    break;
            }
        }
        return Integer.toString(res);
    }
    
}
