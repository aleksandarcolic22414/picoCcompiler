/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aleksandar
 */
public class NasmTools 
{
    /* For integer computation e%x (%=(a,b,c,d)) register are used.
        Even in complex integer computaton, no more than 4 registers can 
        be used, but esi and edi registers are available still... */
    public static String freeRegisters[] = 
        {
         "eax", "ebx", 
         "ecx", "edx", 
         "esi", "edi"
        };
    
    /* Top of freeRegisters stack */
    private static int freeRegistersTop = -1;
    
    /* Function converts C-style string into nasm-style byte array.
        It's limited version that only replaces line feed chars.
        Other conversions are about to be implemented. */
    public static String convertStringToNasmStringLiteral(String literalValue) 
    {
        System.out.println("Input liteval: " + literalValue);
        char source[] = literalValue.toCharArray();
        char destination[] = new char[source.length * 3];
        int i, c;
        for (i = c = 0; i < source.length; ++i) {
            if (source[i] == '\\' && source[i + 1] == 'n') {
                ++i;
                destination[c++] = '"';
                destination[c++] = ',';
                destination[c++] = ' ';
                destination[c++] = '1';
                destination[c++] = '0';
                if (i < source.length - 2) {
                    destination[c++] = ',';
                    destination[c++] = ' ';
                    destination[c++] = '"';
                } else
                    break;
            } else
                destination[c++] = source[i];
        }
        System.out.println(new String(destination).trim());
        return new String(destination).trim();
    }

    public static String getNextFreeTemp() 
    {
        return freeRegisters[++freeRegistersTop];
    }

    static void free(String source) {
        if (freeRegistersTop > 0)
            --freeRegistersTop;
        else System.err.println("freeRegisters stack empty!");
    }

    public static boolean isTakenRegisterEDX() {
        return freeRegistersTop >= 3;
    }

    public static boolean isTakenRegisterEAX() {
        return freeRegistersTop >= 0;
    }
    
}
