package nasm;

import antlr.picoCParser;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Aleksandar Colic
 */
public class DataSegment 
{
    public static int stringLiteralCounter = 1;
    public static List<String> stringLiteralList;
    
    static 
    {
        stringLiteralList = new ArrayList<>();
    }

    /* Create next string literal and add it to the list.
        Literals are given in format __strlit(next position). 
        New free literal is returned. */
    public static String getNextStringLiteral() 
    {
        String nextStrLit = "__strlit" + Integer.toString(stringLiteralCounter++);
        stringLiteralList.add(nextStrLit);
        return nextStrLit;
    }

    public static void DeclareExtern(picoCParser.DeclarationContext ctx) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
