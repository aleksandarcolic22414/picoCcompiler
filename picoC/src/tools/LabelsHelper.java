package tools;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author aleksandar
 */
public class LabelsHelper 
{
    /* Variables holds information about next free logical label number */
    public static long logicalTrueCounter = 1;
    public static long logicalFalseCounter = 1;
    public static long logicalAfterFalseCounter = 1;
    
    /* Variables holds information about next free ifelse label number */
    public static long logicalIfCounter = 1;
    public static long logicalElseCounter = 1;
    public static long logicalAfterElseCounter = 1;
    
    public static long lastGivenDepth = 0;
    public static long selectionDepthCounter = 0;
    public static List<Long> ifElseLabelHelper;
    
    static {
        ifElseLabelHelper = new LinkedList<>();
    }
    
    /* Function returns next free true label in logical expressions */
    public static String getNextTrueLogicalLabel()
    {
        return "TrueLabel" + Long.toString(logicalTrueCounter++);
    }
    
    /* Function returns next free false label in logical expressions */
    public static String getNextFalseLogicalLabel()
    {
        return "FalseLabel" + Long.toString(logicalFalseCounter++);
    }
    
    /* Function returns next free after false label in logical expressions */
    public static String getNextAfterFalseLogicalLabel()
    {
        return "AfterFalseLabel" + Long.toString(logicalAfterFalseCounter++);
    }
    
    /* Function returns next free if label */
    public static String getNextIfLabel()
    {
        return "IfLabel" + Long.toString(logicalIfCounter++);
    }
    
    /* Function returns next free else label */
    public static String getNextElseLabel()
    {
        return "ElseLabel" + Long.toString(logicalElseCounter++);
    }
    
    /* Function returns next free after else label, if it differs */
    public static String getNextAfterElseLabel()
    {
        long depth = ifElseLabelHelper.remove(0);
        return "AfterElseLabel" + Long.toString(logicalAfterElseCounter++ - depth);
    }

    /* Insert depth at the end of ifelselabelhelper list */
    public static void insertDepth() 
    {
        ifElseLabelHelper.add(selectionDepthCounter);
    }
    /* Resets selectionDeptCounter */
    public static void resetSelectionDepthCounter() 
    {
        selectionDepthCounter = 0;
    }
    /* Increasses depth by 1 */
    public static void increaseDepth() 
    {
        ++selectionDepthCounter;
    }
    /* Get current if depth */
    public static long getIfDepth()
    {
        return ifElseLabelHelper.get(0);
    }
    
}
