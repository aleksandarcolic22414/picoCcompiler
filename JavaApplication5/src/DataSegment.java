
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aleksandar
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
}
