package constants;


/**
 *
 * @author Aleksandar Colic
 */
public class Constants 
{
    /* Represents int size in bytes */
    public static final int SIZE_OF_INT = 4;
    /* Represents pointer size in bytes */
    public static final int SIZE_OF_POINTER = 8;
    /* Represents char size in bytes */
    public static final int SIZE_OF_CHAR = 1;
    /* path to Desktop for testing puropse. */
    /* public static final String PATH_TO_OUTPUT_FILE = "izlazi//output.asm"; */
    public static final String PATH_TO_OUTPUT_FILE = 
            "//home//aleksandar//Desktop//compilerTesting//compiledProgram.asm";
    /* path to input file */
    public static final String PATH_TO_INPUT_FILE = "ulazi//program.c";
    /* setup segments for further compilation */
    /* using C extern printf function in text segment */
    public static final String ENTER_TEXT_SEGMENT = "\nsegment .text\n\nextern printf\n";
    public static final String ENTER_DATA_SEGMENT = "segment .data\n";
    public static final String ENTER_BSS_SEGMENT = "\n\nsegment .bss\n";
    /* function emits */
    public static final String FUNCTION_ENTRY = "    push    rbp\n    mov    rbp,rsp\n";
    public static final String FUNCTION_EXIT = "\n    mov    rsp,rbp\n    pop    rbp\n    ret";
    
}
