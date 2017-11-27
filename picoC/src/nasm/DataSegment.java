package nasm;

import antlr.picoCParser;
import compilationControlers.Writers;
import constants.MemoryClassEnum;
import java.util.ArrayList;
import java.util.List;
import tools.ExpressionObject;
import tools.PointerTools;
import tools.Variable;

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
 
    /* Emit new instruction in data segment that defines new variable in data
        segment. Example:
        C(extern) -> int a;    nasm(data segment) ->  a:      
        Which defines doubleword (8 bytes) variable and set it's value to 0.
    */
    public static void declareExternVariable
    (ExpressionObject var, String value)
    {
        String name = var.getName();
        String inst = getDefineDataInst(var.getType());
        Writers.emitData(name + ":" + "\t" + inst + "\t" + value);
    }

    /* Emit new instruction in data segment that defines new variable in data
        segment. Example:
        C(extern) -> int a;    nasm(data segment) ->  a:     dd    0  
        Which defines doubleword (8 bytes) variable and set it's value to 0.
        If variable is array, than: 
        "times [numberOfElements]  sizeofdefining   value" needs to be emited.
        Something like:
        array:   times    5    dd    0 
    */
    public static void declareExternVariable
    (Variable var, String value, int sizes)
    {
        MemoryClassEnum type;
        String name = var.getName();
        type = PointerTools.getTypeForIncrement(var);
        String inst = getDefineDataInst(type);
        if (var.isArray()) {
            Writers.emitData(name + ":\t times " 
                    + sizes + " " + inst + " " + value);
        } else
            Writers.emitData(name + ":" + "\t" + inst + "\t" + value);
    }
    
    /* Returnes defining istruction for proper size of variable */
    private static String getDefineDataInst(MemoryClassEnum typeSpecifier) 
    {
        switch (typeSpecifier) {
            case CHAR:
                return "db";
            case INT:
                return "dd";
            case POINTER:
                return "dq";
            default:
                return null;
        }
    }
    
}
