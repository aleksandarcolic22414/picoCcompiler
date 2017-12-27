package tools;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author aleksandar
 */
public class LabelsMaker 
{
    /* Variables holds information about next free logical label number */
    public static long logicalTrueCounter = 1;
    public static long logicalFalseCounter = 1;
    public static long logicalAfterFalseCounter = 1;
    
    /* Variables holds information about next free if-else label number */
    public static long logicalIfCounter = 1;
    public static long logicalElseCounter = 1;
    public static long logicalAfterElseCounter = 1;
    
    /* Variables holds information about next free conditional label number */
    public static long conditionalCounter = 1;
    public static long conditionalIfCounter = 1;
    public static long conditionalElseCounter = 1;
    public static long conditionalAfterElseCounter = 1;
    
    /* Variables holds information about next free 'for' label number */
    public static long forStartCounter = 1;
    public static long forCheckCounter = 1;
    public static long forIncrementCounter = 1;
    public static long forEndCounter = 1;
    
    /* Variables holds information about next free 'while' label number */
    public static long WhileStartCounter = 1;
    public static long WhileCheckCounter = 1;
    public static long WhileEndCounter = 1;
    /* Holds information about current depth of if else statement */
    public static long selectionDepthCounter = 0;
    
    /* These two listes contains information about current for labels
        in order to help with break and continue instructions */
    public static LinkedList<String> currentBreakLabel;
    public static LinkedList<String> currentContinueLabel;
    
    /* Next variables are string representation of labels */
    public static final String TRUE_LABEL = "TRUE_LABEL_";
    public static final String FALSE_LABEL = "FALSE_LABEL_";
    public static final String AFTER_FALSE_LABEL = "AFTER_FALSE_LABEL_";
    public static final String IF_LABEL = "IF_LABEL_";
    public static final String ELSE_LABEL = "ELSE_LABEL_";
    public static final String AFTER_ELSE_LABEL = "AFTER_ELSE_LABEL_";
    public static final String COND_IF_LABEL = "COND_IF_LABEL_";
    public static final String COND_ELSE_LABEL = "COND_ELSE_LABEL_";
    public static final String COND_AFTER_ELSE_LABEL = "COND_AFTER_ELSE_LABEL_";
    public static final String FOR_START_LABEL = "FOR_START_LABEL_";
    public static final String FOR_CHECK_LABEL = "FOR_CHECK_LABEL_";
    public static final String FOR_INCREMENT_LABEL = "FOR_INCREMENT_LABEL_";
    public static final String FOR_END_LABEL = "FOR_END_LABEL_";
    public static final String WHILE_START_LABEL = "WHILE_START_LABEL_";
    public static final String WHILE_CHECK_LABEL = "WHILE_CHECK_LABEL_";
    public static final String WHILE_END_LABEL = "WHILE_END_LABEL_";
    
    
    static {
        currentBreakLabel = new LinkedList<>();
        currentContinueLabel = new LinkedList<>();
    }
    
    /* Function returns next free true label in logical expressions */
    public static String getNextTrueLogicalLabel()
    {
        return TRUE_LABEL + Long.toString(logicalTrueCounter++);
    }
    
    /* Function returns next free false label in logical expressions */
    public static String getNextFalseLogicalLabel()
    {
        return FALSE_LABEL + Long.toString(logicalFalseCounter++);
    }
    
    /* Function returns next free after false label in logical expressions */
    public static String getNextAfterFalseLogicalLabel()
    {
        return AFTER_FALSE_LABEL + Long.toString(logicalAfterFalseCounter++);
    }
    
    /* Function returns next free condtional if label conditional expression */
    public static String getNextCondtionalIfLabel()
    {
        return COND_IF_LABEL + Long.toString(conditionalIfCounter++);
    }
    
    /* Function returns next free condtional else label conditional expression */
    public static String getNextCondtionalElseLabel()
    {
        return COND_ELSE_LABEL + Long.toString(conditionalElseCounter++);
    }
    
    /* Function returns next free condtional after else label conditional expression */
    public static String getNextCondtionalAfterElseLabel()
    {
        return COND_AFTER_ELSE_LABEL + Long.toString(conditionalAfterElseCounter++);
    }
    
    /* Function returns next free if label */
    public static String getNextIfLabel()
    {
        return IF_LABEL + Long.toString(logicalIfCounter++);
    }
    
    /* Function returns next free else label */
    public static String getNextElseLabel()
    {
        return ELSE_LABEL + Long.toString(logicalElseCounter++);
    }
    
    /* Function returns next free after else label */
    public static String getNextAfterElseLabel()
    {
        return AFTER_ELSE_LABEL + Long.toString(logicalAfterElseCounter++);
    }

    public static String getNextForStartLabel() 
    {
        return FOR_START_LABEL + Long.toString(forStartCounter++);
    }

    public static String getNextForCheckLabel() 
    {
        return FOR_CHECK_LABEL + Long.toString(forCheckCounter++);
    }

    public static String getNextForIncerementLabel() 
    {
        return FOR_INCREMENT_LABEL + Long.toString(forIncrementCounter++);
    }
    
    public static String getNextForEndLabel() {
        return FOR_END_LABEL + Long.toString(forEndCounter++);
    }
    
    public static String getNextWhileStartLabel() 
    {
        return WHILE_START_LABEL + Long.toString(WhileStartCounter++);
    }

    public static String getNextWhileCheckLabel() 
    {
        return WHILE_CHECK_LABEL + Long.toString(WhileCheckCounter++);
    }

    public static String getNextWhileEndLabel() 
    {
        return WHILE_END_LABEL + Long.toString(WhileEndCounter++);
    }
    
    public static void setCurrentIterationLabels
    (String iterationIncrementLabel, String iterationEndLabel) 
    {
        currentContinueLabel.push(iterationIncrementLabel);
        currentBreakLabel.push(iterationEndLabel);
    }

    public static void unsetCurrentIterationLabels() 
    {
        currentBreakLabel.pop();
        currentContinueLabel.pop();
    }

    public static String getLastBreakLabel() 
    {
        return currentBreakLabel.peek();
    }
    
    public static String getLastContinueLabel() 
    {
        return currentContinueLabel.peek();
    }

}
