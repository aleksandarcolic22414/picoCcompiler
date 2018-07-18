package tools;

import antlr.picoCParser;
import constants.Constants;

/**
 *
 * @author aleksandar
 */
public class RelationHelper 
{
    /* Variable represents whether comparison is done or not. */
    private static boolean comparisonDone = false;
    
    /* Variable holds the information about last relation that is done. */
    private static int lastRelation = picoCParser.EQUAL;
    
    /* Return whether comparison is done and set comparisonDone to false for
        further checking. */
    public static boolean isCompared() 
    {
        boolean comp = comparisonDone;
        comparisonDone = false;
        return comp;
    }
    
    /* Set last relation to new relation */
    public static void setRelation(int newRelation) 
    {
        lastRelation = newRelation;
    }
    
    public static void setComparisonDone()
    {
        comparisonDone = true;
    }

    public static String getTrueJump()
    {
        switch (lastRelation) {
            case picoCParser.EQUAL:
                return Constants.JUMP_EQUALS;
            case picoCParser.NOT_EQUAL:
                return Constants.JUMP_NOT_EQUALS;
            case picoCParser.GREATER:
                return Constants.JUMP_GREATER;
            case picoCParser.GREATER_EQUAL:
                return Constants.JUMP_GREATER_EQUALS;
            case picoCParser.LESS:
                return Constants.JUMP_LESS;
            case picoCParser.LESS_EQUAL:
                return Constants.JUMP_LESS_EQUALS;
            default:
                return null;
        }
    }
    
    public static String getFalseJump() 
    {
        switch (lastRelation) {
            case picoCParser.EQUAL:
                return Constants.JUMP_NOT_EQUALS;
            case picoCParser.NOT_EQUAL:
                return Constants.JUMP_EQUALS;
            case picoCParser.GREATER:
                return Constants.JUMP_LESS_EQUALS;
            case picoCParser.GREATER_EQUAL:
                return Constants.JUMP_LESS;
            case picoCParser.LESS:
                return Constants.JUMP_GREATER_EQUALS;
            case picoCParser.LESS_EQUAL:
                return Constants.JUMP_GREATER;
            default:
                return null;
        }
    }

    public static void setComparisonUsed() 
    {
        comparisonDone = false;
    }

    public static int getRelation() 
    {
        return lastRelation;
    }
    
}
