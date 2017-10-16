package tools;

/**
 *
 * @author aleksandar
 */
public class LogicalHelper 
{
    /* Variables holds information about next free logical label number */
    public static long logicalTrueCounter = 1;
    public static long logicalFalseCounter = 1;
    public static long logicalAfterFalseCounter = 1;
    
    /* Function return next number for true labels in logical expressions */
    public static String getNextTrueLogicalLabel()
    {
        return "TrueLabel" + Long.toString(logicalTrueCounter++);
    }
    
    /* Function return next number for false labels in logical expressions */
    public static String getNextFalseLogicalLabel()
    {
        return "FalseLabel" + Long.toString(logicalFalseCounter++);
    }
    
    /* Function return next number for after false labels in logical expressions */
    public static String getNextAfterFalseLogicalLabel()
    {
        return "AfterFalseLabel" + Long.toString(logicalAfterFalseCounter++);
    }
}
