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
    
    /* Memory class of variable */
    private MemoryClassEnum typeSpecifier;
    
    /* If variable is pointer than this list contains type of data that
        this variable points to. */
    private LinkedList<MemoryClassEnum> pointerType = new LinkedList<>();
    
    /* If variable is array than this list holds it's sizes */
    private LinkedList<Integer> arraySizes = new LinkedList<>();
    
    /* Size of variable in bytes */
    private int size;
    
    public Variable
    (String name, String stackPosition, MemoryClassEnum type, boolean extern) 
    {
        this.name = name;
        this.stackPosition = stackPosition;
        this.initialized = false;
        this.typeSpecifier = type;
        this.extern = extern;
    }

    public Variable
    (String name, String stackPosition, MemoryClassEnum type, 
    LinkedList<MemoryClassEnum> curPointer, 
    LinkedList<Integer> curArray, boolean extern) 
    {
        this.name = name;
        this.stackPosition = stackPosition;
        this.initialized = false;
        this.typeSpecifier = type;
        this.extern = extern;
        if (!curPointer.isEmpty())
            NasmTools.switchStacks(curPointer, pointerType);
        if (!curArray.isEmpty())
            NasmTools.switchArrays(curArray, arraySizes);
        /* Get size of variable's memory class */
        int hsize = NasmTools.getSize(typeSpecifier);  
        /* If variable is not array, than size is the size of Memory class, and
            if it is array, than size is sum of the sizes times size of 
            one element */
        if (arraySizes.isEmpty())
            this.size = hsize;
        else
            this.size = hsize * NasmTools.multiplyList(arraySizes);
    }

    public String getName() 
    {
        return name;
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

    public LinkedList<MemoryClassEnum> getPointerType() {
        return pointerType;
    }

    public void setPointerType(LinkedList<MemoryClassEnum> pointerType) {
        this.pointerType = pointerType;
    }

    public void insertPointer(MemoryClassEnum type)
    {
        pointerType.push(type);
    }
    
    public MemoryClassEnum currentPointer()
    {
        return pointerType.peek();
    }

    public boolean isExtern() {
        return extern;
    }

    public void setExtern(boolean extern) {
        this.extern = extern;
    }

    public LinkedList<Integer> getArraySizes() {
        return arraySizes;
    }

    public void setArraySizes(LinkedList<Integer> arraySizes) {
        this.arraySizes = arraySizes;
    }
    
    public boolean isArray()
    {
        return !arraySizes.isEmpty();
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    
}
