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
    /* List contains depths of all if else statements in program */
    public static List<Long> ifElseLabelHelper;
    
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
    public static final String FOR_START_LABEL = "FOR_START_LABEL_";
    public static final String FOR_CHECK_LABEL = "FOR_CHECK_LABEL_";
    public static final String FOR_INCREMENT_LABEL = "FOR_INCREMENT_LABEL_";
    public static final String FOR_END_LABEL = "FOR_END_LABEL_";
    public static final String WHILE_START_LABEL = "WHILE_START_LABEL_";
    public static final String WHILE_CHECK_LABEL = "WHILE_CHECK_LABEL_";
    public static final String WHILE_END_LABEL = "WHILE_END_LABEL_";
            
    static {
        ifElseLabelHelper = new LinkedList<>();
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
    
    /* Function returns next free after else label. But this could get tricky
        in case of multiple else if statements. For example:
        
        C like:                 Assembly:           cmp   cond1,0
        if (cond1)                                  je    ELSE_LABEL_1
            code1;              IF_LABEL_1:         code1;   
        else if (cond2)                             jmp   AFTER_ELSE_LABEL_1
            code2;              ELSE_LABEL_1:       cmp   cond2,0
        else if (cond3)                             je    ELSE_LABEL_2 
            code3;              IF_LABEL_2:         code2;
        else                                        jmp   AFTER_ELSE_LABEL_2 -> problem
            code4;              ELSE_LABEL_2:       cmp   cond3,0
        afterElseLabel_1;                       ....
                                                ....
                                                ....
                                AFTER_ELSE_LABEL_3:   -> These are stoped within visitor
                                    ...                 /
                                AFTER_ELSE_LABEL_2:   -
                                    ...
                                AFTER_ELSE_LABEL_1:  
    
        In this example if cond1 is satisfied, program must 
        jump to after else label 1. But if first condition is not satisfied,
        second condition is checked and if it is true, than program must
        execute code2 and jump to afterElseLabel_1 !!! So that initial jump is
        calculated by substracting the depth of condition.
        So now, if cond2 is satisfied, code2 is executed and program jumps to
        afterElseLabel_(labelnumber - depth) which is afterElseLabel_(2 - 1)
        which is finally afterElseLabel_1.
    */
    public static String getNextAfterElseLabel()
    {
        long depth = ifElseLabelHelper.remove(0);
        return AFTER_ELSE_LABEL + Long.toString(logicalAfterElseCounter++ - depth);
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
