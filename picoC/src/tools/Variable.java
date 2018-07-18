package tools;


import constants.MemoryClassEnum;
import java.util.LinkedList;
import nasm.NasmTools;


/**
 *
 * @author Aleksandar Colic
 */
public class Variable 
{
    /* Variable name */
    private String name;

    /* Represents variable displacement on stack. Format is: rbp-X */
    private String stackPosition;

    /* Is variable initialized */
    private boolean initialized;

    /* Is extern variable */
    private boolean extern;
    
    /* Is variable an array */
    private boolean array = false;
    
    /* Memory class of variable */
    private MemoryClassEnum typeSpecifier;
    
    /* Type of array members if variable is an array */
    private MemoryClassEnum arrayType;
    
    /* If variable is pointer than this list contains type of data that
        this variable points to. */
    private LinkedList<Pointer> pointerTo = new LinkedList<>();
    
    /* If variable is array or multidimensional array, 
        than this list holds it's size(s) */
    private LinkedList<Integer> arraySizes = new LinkedList<>();
    
    /* Size of variable in bytes */
    private int size;
    
    /* Constructor to a simple variable */
    public Variable
    (String name, String stackPosition, MemoryClassEnum type, boolean extern) 
    {
        this.name = name;
        this.stackPosition = stackPosition;
        this.initialized = false;
        this.typeSpecifier = type;
        this.extern = extern;
    }

    /* Constructor to a complex variable */
    public Variable
    (String name, String stackPosition, MemoryClassEnum type, 
    LinkedList<Pointer> curPointer, 
    LinkedList<Integer> curArray, boolean extern) 
    {
        this.name = name;
        this.stackPosition = stackPosition;
        this.initialized = false;
        this.extern = extern;
        
        if (!curArray.isEmpty()) {
            this.array = true;
            this.arrayType = type;
        }
        
        if (curPointer.isEmpty() && curArray.isEmpty())
            this.typeSpecifier = type;
        else
            this.typeSpecifier = MemoryClassEnum.POINTER;
        
        if (!curPointer.isEmpty())
            PointerTools.switchStacks(pointerTo, curPointer);
        
        /* Insert array/s as pointer/s if array/s is/are declared */
        if (!curArray.isEmpty())
            PointerTools.insertArrays(pointerTo, curArray, type);
        
        /* Get size of variable's memory class */
        int hsize = NasmTools.getSize(typeSpecifier);  
        this.size = hsize;
    }

    public boolean isArray() 
    {
        return array;
    }
    
    public void setArray(boolean array) 
    {
        this.array = array;
    }

    public String getName() 
    {
        return name;
    }

    public MemoryClassEnum getArrayType() 
    {
        return arrayType;
    }

    public void setArrayType(MemoryClassEnum arrayType) 
    {
        this.arrayType = arrayType;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public String getStackPosition() 
    {
        return stackPosition;
    }

    public void setStackPosition(String stackPosition) 
    {
        this.stackPosition = stackPosition;
    }

    public boolean isInitialized() 
    {
        return initialized;
    }

    public void setInitialized(boolean initialized) 
    {
        this.initialized = initialized;
    }

    public MemoryClassEnum getTypeSpecifier() 
    {
        return typeSpecifier;
    }

    public void setTypeSpecifier(MemoryClassEnum typeSpecifier) 
    {
        this.typeSpecifier = typeSpecifier;
    }

    public LinkedList<Pointer> getPointerTo() 
    {
        return pointerTo;
    }

    public void setPointerTo(LinkedList<Pointer> pointerTo) 
    {
        this.pointerTo = pointerTo;
    }

    public void insertPointer(Pointer type)
    {
        pointerTo.push(type);
    }
    
    public Pointer currentPointer()
    {
        return pointerTo.peek();
    }

    public boolean isExtern() 
    {
        return extern;
    }

    public void setExtern(boolean extern) 
    {
        this.extern = extern;
    }

    public LinkedList<Integer> getArraySizes() 
    {
        return arraySizes;
    }

    public void setArraySizes(LinkedList<Integer> arraySizes) 
    {
        this.arraySizes = arraySizes;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Pointer getPointer() 
    {
        return pointerTo.peek();
    }
    
}
