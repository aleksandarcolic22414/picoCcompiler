package compilationControlers;

import constants.Constants;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Aleksandar Colic
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
    
    /* Function flushes all 3 string builders into output file. */
    public void writeOutput() throws IOException 
    {
        buff.write(DATA_SEGMENT.toString());
        buff.write(TEXT_SEGMENT.toString());
        buff.write(BSS_SEGMENT.toString());
        buff.flush();
    }    
    
    public static void emitInstruction(String instruction)
    {
        /* Make instructions readable */
        TEXT_SEGMENT.append("\t");
        TEXT_SEGMENT.append(instruction);
        TEXT_SEGMENT.append("\n");
    }
    
    public static void emitInstruction(String instruction, String arg1)
    {
        /* Make instructions readable */
        TEXT_SEGMENT.append("\t");
        TEXT_SEGMENT.append(instruction);
        TEXT_SEGMENT.append("\t");
        TEXT_SEGMENT.append(arg1);
        TEXT_SEGMENT.append("\n");
    }
    
    public static void emitInstruction(String instruction, String arg1, String arg2)
    {
        /* Make instructions readable */
        TEXT_SEGMENT.append("\t");
        TEXT_SEGMENT.append(instruction);
        TEXT_SEGMENT.append("\t");
        TEXT_SEGMENT.append(arg1);
        TEXT_SEGMENT.append(", ");
        TEXT_SEGMENT.append(arg2);
        TEXT_SEGMENT.append("\n");
    }
    
    public static void emitInstruction
    (String instruction, String arg1, String arg2, String arg3)
    {
        /* Make instructions readable */
        TEXT_SEGMENT.append("\t");
        TEXT_SEGMENT.append(instruction);
        TEXT_SEGMENT.append("\t");
        TEXT_SEGMENT.append(arg1);
        TEXT_SEGMENT.append(", ");
        TEXT_SEGMENT.append(arg2);
        TEXT_SEGMENT.append(", ");
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
    
    /* Emits define instruction without analysing literalValue. */
    public static void emitDefineDataSegment
    (String literalName, String literalValue) 
    {
        /* Do analyse of literalValue for special characters! */
        DATA_SEGMENT.append(literalName);
        DATA_SEGMENT.append(":    db ");
        DATA_SEGMENT.append(literalValue);
        DATA_SEGMENT.append(", 0\n");
    }
    
    /* Set text segment for function definition */
    public static void emitFunctionSetup(String name) 
    {
        Writers.emitText("\tglobal    " + name);
        Writers.emitText(name + ":");
        Writers.emitText(Constants.FUNCTION_ENTRY);
    }
    
    public static void emitLabelReturn(String inProcess) 
    {
        Writers.emitText(inProcess + "Exit:");
    }
    
    public static void emitJumpToExit(String inProcess) 
    {
        String exit = inProcess + "Exit";
        Writers.emitInstruction("jmp", exit);
    }

    /* Function just emits label, but it is logicaly 
        separated from emitText */
    public static void emitLabel(String label) 
    {
        Writers.emitText(label + ":");
    }
    
}
