package constants;


/**
 *
 * @author Aleksandar Colic
 */
public class Constants 
{
    /* Represents void size in bytes. Void type actualy has no bytes, but
        in order to separate pointer arithmetic from other types it has
        value of 1. */
    public static int SIZE_OF_VOID = 1;
    /* Represents char size in bytes */
    public static final int SIZE_OF_CHAR = 1;
    /* Represents int size in bytes */
    public static final int SIZE_OF_INT = 4;
    /* Represents pointer size in bytes */
    public static final int SIZE_OF_POINTER = 8;
    /* Represents value of byte in string */
    public static final String STRING_BYTE = "byte";
    /* Represents value of word in string */
    public static final String STRING_WORD = "word";
    /* Represents value of double word in string */
    public static final String STRING_DWORD = "dword";
    /* Represents value of quad word in string */
    public static final String STRING_QWORD = "qword";
    /* path to Desktop for testing puropse. */
    /* public static final String PATH_TO_OUTPUT_FILE = "izlazi//output.asm"; */
    public static final String PATH_TO_OUTPUT_FILE = 
            "//home//aleksandar//Desktop//compilerTesting//compiledProgram.asm";
    /* path to input file */
    public static final String PATH_TO_INPUT_FILE = "ulazi//program18.c";
    
    /* using gcc's C extern functions in text segment */
    public static final String EXTERN_GCC_LIB = "\textern printf\n"
                                              + "\textern scanf\n"
                                              + "\textern malloc\n"
                                              + "\textern rand\n";
    /* setup segments for further compilation */
    public static final String ENTER_TEXT_SEGMENT = "\nsegment .text\n"
                                                   + EXTERN_GCC_LIB;
    
    public static final String ENTER_DATA_SEGMENT = "segment .data\n";
    public static final String ENTER_BSS_SEGMENT = "\n\nsegment .bss\n";
    /* function emits */
    public static final String FUNCTION_ENTRY = "\tpush\trbp"
                                            + "\n\tmov\trbp, rsp\n";
    
    public static final String FUNCTION_EXIT = "\tmov\trsp,rbp"
            + "                                 \n\tpop\trbp"
            + "                                 \n\tret";
    
    /* These are string representations of arithmetic operators */
    public static final String PLUS_SYMBOL = "+";
    public static final String MINUS_SYMBOL = "-";
    public static final String MULTIPLICATION_SYMBOL = "*";
    public static final String DIVISION_SYMBOL = "/";
    public static final String MODULO_SYMBOL = "%";
    
    /* These are string representations of jumps */
    public static final String JUMP_UNCODITIONAL = "jmp";
    public static final String JUMP_LESS = "jl";
    public static final String JUMP_LESS_EQUALS = "jle";
    public static final String JUMP_GREATER = "jg";
    public static final String JUMP_GREATER_EQUALS = "jge";
    public static final String JUMP_EQUALS = "je";
    public static final String JUMP_NOT_EQUALS = "jne";
}
