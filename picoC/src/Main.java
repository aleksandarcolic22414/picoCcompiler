/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


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
 * @author aleksandar
 */
public class Main 
{
  
    public static void main(String[] args) 
    {
        try {
            InputStream is = new FileInputStream(Constants.PATH_TO_INPUT_FILE);
            ANTLRInputStream in = new ANTLRInputStream(is);
            picoCLexer lexer = new picoCLexer(in);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            picoCParser parser = new picoCParser(tokens);
            
            ParseTree tree = parser.compilationUnit();
            System.out.println(tree.toStringTree(parser));
            
            ParseTreeWalker walker = new ParseTreeWalker();
            TranslationVisitor visitor = new TranslationVisitor();
            TranslationListener listener = new TranslationListener();
            
            walker.walk(listener, tree);
            visitor.visit(tree);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
