package tools;

import antlr.picoCParser;
import constants.TokenEnumeration;

/**
 *
 * @author aleksandar
 */
public class RelationHelper 
{
    /* Variable represents wheather comparison is done or not */
    private static boolean comparisonDone = false;
    /* Variable holds the information about last relation done */
    private static int lastRelation = picoCParser.EQUAL;
    
    /* Return wheather comparison is done and set comparisonDone to false for
        further checking */
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
                return "je";
            case picoCParser.NOT_EQUAL:
                return "jne";
            case picoCParser.GREATER:
                return "jg";
            case picoCParser.GREATER_EQUAL:
                return "jge";
            case picoCParser.LESS:
                return "jl";
            case picoCParser.LESS_EQUAL:
                return "jle";
        }
        return null;
    }
    
    public static String getFalseJump() 
    {
        switch (lastRelation) {
            case picoCParser.EQUAL:
                return "jne";
            case picoCParser.NOT_EQUAL:
                return "je";
            case picoCParser.GREATER:
                return "jle";
            case picoCParser.GREATER_EQUAL:
                return "jl";
            case picoCParser.LESS:
                return "jge";
            case picoCParser.LESS_EQUAL:
                return "jg";
        }
        return null;
    }
}
