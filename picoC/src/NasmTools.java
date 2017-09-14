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
        If no more registers are available, values are saved on stack. */
    public static String freeRegisters[] = 
        {
         "eax", "ebx", 
         "ecx", "edx", 
         "esi", "edi"
        };
    /* Represents number of saved registers on stack for computation. */
    public static int pushedRegistersOnStack = 0;
    
    /* Top of freeRegisters */
    private static int freeRegistersTop = -1;
    
    private static boolean registersFiled = false;
    
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
        if (hasRegisters()) {
            return freeRegisters[++freeRegistersTop];
        }
        String s = getStackDisplacement();
        return s;
    }

    /* Funtion cleans up memory location that is used for computations. 
        That can be register or some value on stack. */
    public static void free(String source) 
    {
        /* If register is not taken */
        if (pushedRegistersOnStack == 0)
            --freeRegistersTop;
        else {
            String s = Integer.toString(Constants.SIZE_OF_INT);
            Writers.emitInstruction("add", "rsp", s);
            --pushedRegistersOnStack;
        }
    }

    public static boolean isTakenRegisterEDX() 
    {
        return freeRegistersTop >= 3;
    }

    public static boolean isTakenRegisterEAX() 
    {
        return freeRegistersTop >= 0;
    }

    private static boolean hasRegisters() 
    {
        return freeRegistersTop < 5;
    }

    /* Function determines witch is next position on stack
        that can hold value. 
    TODO: Get real displacement when stack frame is not empty! */
    private static String getStackDisplacement() 
    {
        /* Make room for integer */
        Writers.emitInstruction(
                "sub", "rsp", Integer.toString(Constants.SIZE_OF_INT));
        /* First free location is ebp-4. So every next is calculated by
            multipliing pushedRegisters and sizeof(int).
            Here is also needed to determine how much space is already
            taken on stack!!! So real calculation would go something like:
            disp = taken + sizeof(var) + sizeof(var) * pushedRegisters */
        int disp = Constants.SIZE_OF_INT + Constants.SIZE_OF_INT * pushedRegistersOnStack;
        ++pushedRegistersOnStack;
        /* [rbp - displacement] is returned casted to dword
            TODO: Determine witch cast should be used  */
        return "dword [rbp-" + Integer.toString(disp) + "]";
    }

    /* Really stupid check */
    public static boolean isRegister(String left) 
    {
        boolean isRegister;
        isRegister = ( 
                left.equals("rax") 
                ||
                left.equals("rbx") 
                ||
                left.equals("rcx") 
                ||
                left.equals("rdx") 
                ||
                left.equals("eax") 
                ||
                left.equals("ebx") 
                ||
                left.equals("ecx") 
                ||
                left.equals("edx") 
                ||
                left.equals("rsi") 
                ||
                left.equals("rdi") 
                ||
                left.equals("esi") 
                ||
                left.equals("edi")
                ||
                left.equals("rbp")
                ||
                left.equals("rsp")
            );
        return isRegister;
    }

    /* Returns text representation of operation.
        Numbers are taken from generated token numbers. */
    public static String getOperation(int op) 
    {
        switch (op) {
            case picoCParser.ADD:
                return "add";
            case picoCParser.SUB:
                return "sub";
            case picoCParser.MUL:
                return "imul";
            case picoCParser.DIV:
                return "idiv";
            default:
                return null;
        }
    }
    
}
