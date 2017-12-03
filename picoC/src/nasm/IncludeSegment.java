package nasm;

/**
 *
 * @author aleksandar
 */
public class IncludeSegment 
{
    /* Variable holds information about included libraries from gcc's lib. */
    public static int includes = 0x0;
    
    /* Positions of libs in flags */
    public static final int STDIO_H  = 0x1;
    public static final int STDLIB_H = 0x2;
    public static final int CTYPE_H  = 0x4;
    public static final int STRING_H = 0x8;
    
    public static boolean isIncludedStdio()
    {
        return (includes & STDIO_H) != 0;
    }
    
    public static boolean isIncludedStdlib()
    {
        return (includes & STDLIB_H) != 0;
    }
    
    public static boolean isIncludedCtype()
    {
        return (includes & CTYPE_H) != 0;
    }
    
    public static boolean isIncludedString()
    {
        return (includes & STRING_H) != 0;
    }
    
    public static void includeStdio()
    {
        includes |= STDIO_H;
    }
    
    public static void includeStdlib()
    {
        includes |= STDLIB_H;
    }
    
    public static void includeCtype()
    {
        includes |= CTYPE_H;
    }
    
    public static void includeString()
    {
        includes |= STRING_H;
    }
}
