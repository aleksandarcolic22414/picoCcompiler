package main;

import antlr.picoCLexer;
import antlr.picoCParser;
import antlr.TranslationVisitor;
import antlr.TranslationListener;
import compilationControlers.Writers;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

/**
 *
 * @author Aleksandar Colic
 */
public class Main 
{
    /* Path to current directory */
    public static final String CURRENT_DIRECTORY = System.getProperty("user.dir");
    
    /* Path to output file */
    public static StringBuilder
    PATH_TO_OUTPUT_FILE = new StringBuilder(CURRENT_DIRECTORY).append("//");
    /* Path to input file */
    public static StringBuilder 
    PATH_TO_INPUT_FILE = new StringBuilder(CURRENT_DIRECTORY).append("//");
    
    /* Compilation commands */
    public static StringBuilder nasm  = new StringBuilder("nasm -f elf64 ");
    public static StringBuilder gcc   = new StringBuilder("gcc -m64 ");
    public static StringBuilder clean = new StringBuilder("rm ");
    
    /* File names */
    public static String outputFileName = "out.s";
    public static String inputFileName = null;
    public static String rawFileName = null;
    
    /* Options flags */
    public static int options = 0x0;
    
    /* Position of options in options flags */
    public static final int OUTPUT_FILE_SPECIFIED = 0x1;
    public static final int INPUT_FILE_SPECIFIED  = 0x2;
    public static final int COMPILE_ONLY          = 0x4;
    public static final int NO_DELETE             = 0x8;
    
    public static void main(String[] args) 
    {
        Main.scanArgs(args);
        Writers.init();
        
        try {
            InputStream is = new FileInputStream(Writers.inputFile);
            ANTLRInputStream in = new ANTLRInputStream(is);
            picoCLexer lexer = new picoCLexer(in);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            picoCParser parser = new picoCParser(tokens);
            ParseTree tree = parser.compilationUnit();
                        
            ParseTreeWalker walker = new ParseTreeWalker();
            TranslationListener listener = new TranslationListener();
            TranslationVisitor visitor = new TranslationVisitor();
            
            walker.walk(listener, tree);
            visitor.visit(tree);
            
        } catch (FileNotFoundException ex) {
            System.err.println("Error: " + inputFileName + ": No such file or directory");
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Main.assembleAndLink();
    }

    /* Function assembles file generated by compiler and links object files
        with gcc's linker    . */
    private static void assembleAndLink() 
    {
        /* If -S option is specified than assemble and link are not done */
        if (isCompileOnly())
            return ;
        try {
            File pathToDirectory = new File(CURRENT_DIRECTORY);
            Runtime runtime = Runtime.getRuntime();
            /* Assemble by invoking nasm */
            Process p = runtime.exec(nasm.toString(), null, pathToDirectory);
            p.waitFor();
            /* Link object files with gcc */
            p = runtime.exec(gcc.toString(), null, pathToDirectory);
            /* Delete object and assembly files if needed */
            if (!isNoDelete()) {
                p.waitFor();
                runtime.exec(clean.toString(), null, pathToDirectory);
            }
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /* Function that manage args */
    private static void scanArgs(String[] args) 
    {
        int i = 0;
        
        if (args.length == 0) {
            System.err.println("No input file specified.");
            System.out.println("Type --help for help");
            System.exit(0);
        } 
        
        while (i < args.length) {
            /* If end of command line arguments is reached, that is 
                name of input file */
            if (!args[i].equals("--help") && i == args.length - 1) {
                inputFileName = args[i];
                setInputFileSpecified();
                ++i;
                continue;
            }
            
            switch (args[i]) {
                case "-o" :
                    if (args[i+1] == null) break;
                    outputFileName = args[i+1];
                    setOutputFileSpecified();
                    i += 2;
                    break;
                case "-S" :
                    setCompileOnly();
                    ++i;
                    break;
                case "-no-delete" :
                    Main.setNoDelete();
                    ++i;
                    break;
                case "--help" :
                    Main.displayHelp();
                    break;
                default :
                    Main.unrecognizedCommand(args[i]);
                    break;
            }
        }
        Main.setOptions();
    }

    /* Set specified compiler options  */
    private static void setOptions() 
    {
        if (!isInputFileSpecified()) {
            System.err.println("No input files specified.");
            System.exit(0);
        } else if (!inputFileName.endsWith(".c")) {
            System.err.println("Trying to compile non-c file.");
            System.exit(0);
        } else
            PATH_TO_INPUT_FILE.append(inputFileName);
        /* rawFileName will be name of the input file without extension.
            If input file is "program.c", raw will be "program" */
        rawFileName = inputFileName.substring(0, inputFileName.lastIndexOf("."));
        
        if (isCompileOnly()) {  // compile only
            if (isOutputFileSpecified()) {
                PATH_TO_OUTPUT_FILE.append(outputFileName);  // only assembly like program.s
            } else {
                PATH_TO_OUTPUT_FILE.append(rawFileName);  // only assembly like program.s
                PATH_TO_OUTPUT_FILE.append(".s");
            }
        } else {  // compile, assemble and link
            if (isOutputFileSpecified()) {
                PATH_TO_OUTPUT_FILE.append(rawFileName);   // not important (in between name)
                
                nasm.append("-o out.o ");
                nasm.append(rawFileName);
                
                gcc.append("-o ");
                gcc.append(outputFileName);
                gcc.append(" out.o");
                
                clean.append("out.o ");
                clean.append(rawFileName);
            } else {
                PATH_TO_OUTPUT_FILE.append(rawFileName); // in between name program.s
                PATH_TO_OUTPUT_FILE.append(".s");
                
                nasm.append("-o out.o ");
                nasm.append(rawFileName);
                nasm.append(".s"); // assemble program.s
                
                gcc.append("-o ");
                gcc.append(rawFileName);    // raw run file like "program"
                gcc.append(" out.o");   
                
                clean.append("out.o ");
                clean.append(rawFileName);
                clean.append(".s");  // clear object and assembly file
            }
        }
        
    }

    /* Display compiler's options */
    private static void displayHelp() 
    {
        System.out.println("Usage: acc [options] file...");
        System.out.println("Options:");
        System.out.println("--help               Display this information");
        System.out.println("-o <file>            Place the output into <file>");
        System.out.println("-S                   Compile only; do not assemble or link");
        System.out.println("-no-delete           Do not delete any files created by compiler or assembler");
        System.exit(0);
    }
     
    public static void setOutputFileSpecified()
    {
        options |= OUTPUT_FILE_SPECIFIED;
    }
    
    public static void setInputFileSpecified()
    {
        options |= INPUT_FILE_SPECIFIED;
    }
    
    public static void setCompileOnly()
    {
        options |= COMPILE_ONLY;
    }
    
    private static void setNoDelete() 
    {
        options |= NO_DELETE;
    }
    
    private static boolean isInputFileSpecified() 
    {
        return (options & INPUT_FILE_SPECIFIED) != 0;
    }

    private static boolean isOutputFileSpecified() 
    {
        return (options & OUTPUT_FILE_SPECIFIED) != 0;
    }

    private static boolean isCompileOnly() 
    {
        return (options & COMPILE_ONLY) != 0;
    }

    private static boolean isNoDelete() 
    {
        return (options & NO_DELETE) != 0;
    }
    
    private static void unrecognizedCommand(String arg) 
    {
        System.err.println("error: unrecognized command line option " + arg);
        System.exit(0);
    }
    
}
