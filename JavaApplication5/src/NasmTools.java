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
// 
//    public static void main(String[] args) 
//    {
//        String s = "\"%d\n\"";
//        String d = NasmTools.convertStringToNasmStringLiteral(s);
//        System.out.println(d);
//        System.out.println(d.trim().length());
//    }
}
