
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author aleksandar
 */
public class Writers 
{
    public static StringBuilder TEXT_SEGMENT;
    public static StringBuilder DATA_SEGMENT;
    public static StringBuilder BSS_SEGMENT;

    
    FileWriter out;
    BufferedWriter buff;
    
    static 
    {
        TEXT_SEGMENT = new StringBuilder(Constants.ENTER_TEXT_SEGMENT);
        DATA_SEGMENT = new StringBuilder(Constants.ENTER_DATA_SEGMENT);
        BSS_SEGMENT = new StringBuilder(Constants.ENTER_BSS_SEGMENT);
    }
    
    public Writers() throws IOException 
    {
        this.out = new FileWriter(Constants.PATH_TO_OUTPUT_FILE);
        this.buff = new BufferedWriter(out);
    }

    public Writers(String pathToOutputFile) throws IOException 
    {
        this.out = new FileWriter(pathToOutputFile);
        this.buff = new BufferedWriter(out);
    }
    
    public static void emitInstruction(String instruction)
    {
        /* Make instructions readable */
        TEXT_SEGMENT.append("    ");
        TEXT_SEGMENT.append(instruction);
        TEXT_SEGMENT.append("\n");
    }
    
    public static void emitInstruction(String instruction, String arg1)
    {
        /* Make instructions readable */
        TEXT_SEGMENT.append("    ");
        TEXT_SEGMENT.append(instruction);
        TEXT_SEGMENT.append("    ");
        TEXT_SEGMENT.append(arg1);
        TEXT_SEGMENT.append("\n");
    }
    
    public static void emitInstruction(String instruction, String arg1, String arg2)
    {
        /* Make instructions readable */
        TEXT_SEGMENT.append("    ");
        TEXT_SEGMENT.append(instruction);
        TEXT_SEGMENT.append("    ");
        TEXT_SEGMENT.append(arg1);
        TEXT_SEGMENT.append(",");
        TEXT_SEGMENT.append(arg2);
        TEXT_SEGMENT.append("\n");
    }
    
    public static void emitInstruction
    (String instruction, String arg1, String arg2, String arg3)
    {
        /* Make instructions readable */
        TEXT_SEGMENT.append("    ");
        TEXT_SEGMENT.append(instruction);
        TEXT_SEGMENT.append("    ");
        TEXT_SEGMENT.append(arg1);
        TEXT_SEGMENT.append(",");
        TEXT_SEGMENT.append(arg2);
        TEXT_SEGMENT.append(",");
        TEXT_SEGMENT.append(arg3);
        TEXT_SEGMENT.append("\n");
    }
    
    public static void emitText(String s)
    {
        TEXT_SEGMENT.append(s);
        TEXT_SEGMENT.append("\n");
    }
    
    public static void emitData(String s)
    {
        DATA_SEGMENT.append(s);
        DATA_SEGMENT.append("\n");
    }
    
    public static void emitBss(String s)
    {
        BSS_SEGMENT.append(s);
        BSS_SEGMENT.append("\n");
    }
    
    public static String defineStringLiteral(String s) 
    {
        String literalName = DataSegment.getNextStringLiteral();
        emitDefineDataSegment(literalName, s);
        return literalName;
    }
    
    /* Emits define instruction without analysing literalValue. */
    private static void emitDefineDataSegment
    (String literalName, String literalValue) 
    {
        /* Do analyse of literalValue for special characters! */
        DATA_SEGMENT.append(literalName);
        DATA_SEGMENT.append(":    db ");
        DATA_SEGMENT.append(literalValue);
        DATA_SEGMENT.append(", 0\n");
    }
    
    /* Set text segment for function definition */
    static void emitFunctionSetup(String name) {
        Writers.emitText("\tglobal    " + name);
        Writers.emitText(name + ":");
        Writers.emitText(Constants.FUNCTION_ENTRY);
    }
    
    /* Function sets format argument for printf function.
        printf takes first argument as a format for other arguments.
        For example: printf("%d", 50) where "%d" is format. */
    public static void emitPrintfFormatSetup(String strFormat) 
    {
        /* Get nasm-style string from c-style string */
        String strLitNasmVal = NasmTools.convertStringToNasmStringLiteral(strFormat);
        /* Get next free3 literal name */
        String dataLiteralName = Writers.defineStringLiteral(strLitNasmVal);
        System.out.println("Converted format: " + strLitNasmVal);
        /* Put literal name as argument into rdi register */
        Writers.emitInstruction("mov", "rdi", dataLiteralName);
    }
    
    /* Function that sets up the arguments for extern printf function. */
    public static void emitPrintfArgumentsSetup
    (String argVal, TokenEnumeration tokenType) 
    {
        String strlitVal, dataLiteralName;
        if (null != tokenType) switch (tokenType) {
            case STRING_LITERAL:
                strlitVal = NasmTools.convertStringToNasmStringLiteral(argVal); /* Get nasm-style string */
                System.out.println("Converted litval: " + strlitVal);
                dataLiteralName = Writers.defineStringLiteral(strlitVal); /* Get next free3 name */
                Writers.emitInstruction("mov", "rsi", dataLiteralName); /* place it into rsi */
                break;
            case INT:
                Writers.emitInstruction("mov", "rsi", argVal); /* Place INT into rsi */
                break;
            case ID:
                /* Implement variable argument. */
                break;
            default:
                break;
        }
    }

    /* Setup and call for extern printf */
    public static void emitPrintfCall() 
    {
        Writers.emitInstruction("mov", "rax", "0");
        Writers.emitInstruction("call", "printf");
    }
    
    /* Function flushes all 3 string builders into output file. */
    void writeOutput() throws IOException 
    {
        buff.write(DATA_SEGMENT.toString());
        buff.write(TEXT_SEGMENT.toString());
        buff.write(BSS_SEGMENT.toString());
        buff.flush();
        System.out.println("Compilation succesful!");
    }    
    
}
