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
    
    /* using gcc's C extern functions in text segment */
    public static final String GCC_LIB_CTYPE =  "\textern isalnum\n"
                                              + "\textern isalpha\n"
                                              + "\textern isblank\n"
                                              + "\textern iscntrl\n"
                                              + "\textern isdigit\n"
                                              + "\textern isgraph\n"
                                              + "\textern islower\n"
                                              + "\textern isprint\n"
                                              + "\textern ispunct\n"
                                              + "\textern isspace\n"
                                              + "\textern isupper\n"
                                              + "\textern isxdigit\n"
                                              + "\textern toupper\n"
                                              + "\textern tolower\n"
                                              ;
    
    public static final String GCC_LIB_STDIO =  "\textern clearerr\n"
                                              + "\textern fclose\n"
                                              + "\textern feof\n"
                                              + "\textern ferror\n"
                                              + "\textern fflush\n"
                                              + "\textern fgetc\n"
                                              + "\textern fgetpos\n"
                                              + "\textern fgets\n"
                                              + "\textern fopen\n"
                                              + "\textern fprintf\n"
                                              + "\textern fputc\n"
                                              + "\textern fputs\n"
                                              + "\textern fread\n"
                                              + "\textern freopen\n"
                                              + "\textern fscanf\n"
                                              + "\textern fseek\n"
                                              + "\textern fsetpos\n"
                                              + "\textern ftell\n"
                                              + "\textern fwrite\n"
                                              + "\textern getc\n"
                                              + "\textern getchar\n"
                                              + "\textern gets\n"
                                              + "\textern perror\n"
                                              + "\textern printf\n"
                                              + "\textern putc\n"
                                              + "\textern putchar\n"
                                              + "\textern puts\n"
                                              + "\textern remove\n"
                                              + "\textern rename\n"
                                              + "\textern rewind\n"
                                              + "\textern scanf\n"
                                              + "\textern setbuf\n"
                                              + "\textern setvbuf\n"
                                              + "\textern snprintf\n"
                                              + "\textern sprintf\n"
                                              + "\textern sscanf\n"
                                              + "\textern tmpfile\n"
                                              + "\textern tmpnam\n"
                                              + "\textern ungetc\n"
                                              + "\textern vfprintf\n"
                                              + "\textern vfscanf\n"
                                              + "\textern vprintf\n"
                                              + "\textern vscanf\n"
                                              + "\textern vsnprintf\n"
                                              + "\textern vsprintf\n"
                                              + "\textern vsscanf\n"
                                              ;
    
    public static final String GCC_LIB_STDLIB = "\textern abort\n"
                                              + "\textern abs\n"
                                              + "\textern atexit\n"
                                              + "\textern atof\n"
                                              + "\textern atoi\n"
                                              + "\textern atol\n"
                                              + "\textern atoll\n"
                                              + "\textern at_quick_exit\n"
                                              + "\textern bsearch\n"
                                              + "\textern calloc\n"
                                              + "\textern div\n"
                                              + "\textern exit\n"
                                              + "\textern free\n"
                                              + "\textern getenv\n"
                                              + "\textern labs\n"
                                              + "\textern ldiv\n"
                                              + "\textern llabs\n"
                                              + "\textern lldiv\n"
                                              + "\textern malloc\n"
                                              + "\textern mblen\n"
                                              + "\textern mbstowcs\n"
                                              + "\textern mbtowc\n"
                                              + "\textern qsort\n"
                                              + "\textern quick_exit\n"
                                              + "\textern rand\n"
                                              + "\textern realloc\n"
                                              + "\textern srand\n"
                                              + "\textern strtod\n"
                                              + "\textern strtof\n"
                                              + "\textern strtol\n"
                                              + "\textern strtold\n"
                                              + "\textern strtoll\n"
                                              + "\textern strtoul\n"
                                              + "\textern strtoull\n"
                                              + "\textern system\n"
                                              + "\textern wcstombs\n"
                                              + "\textern wctomb\n"
                                              + "\textern _Exit\n"
                                              + "\textern itoa\n"
                                              ;
    
    public static final String GCC_LIB_STRING = "\textern memchr\n"
                                              + "\textern memcmp\n"
                                              + "\textern memcpy\n"
                                              + "\textern memmove\n"
                                              + "\textern memset\n"
                                              + "\textern strcat\n"
                                              + "\textern strchr\n"
                                              + "\textern strcmp\n"
                                              + "\textern strcoll\n"
                                              + "\textern strcpy\n"
                                              + "\textern strcspn\n"
                                              + "\textern strerror\n"
                                              + "\textern strlen\n"
                                              + "\textern strncat\n"
                                              + "\textern strncmp\n"
                                              + "\textern strncpy\n"
                                              + "\textern strpbrk\n"
                                              + "\textern strrchr\n"
                                              + "\textern strspn\n"
                                              + "\textern strstr\n"
                                              + "\textern strtok\n"
                                              + "\textern strxfrm\n"
                                              ;
    /* setup segments for further compilation + "\textern */
    public static final String ENTER_TEXT_SEGMENT = "\nsegment .text\n";
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
