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

    /* Memory class of variable */
    private MemoryClassEnum typeSpecifier;
    
    /* If variable is pointer than this list contains type of data that
        this variable points to. */
    private LinkedList<MemoryClassEnum> pointerType = new LinkedList<>();
    
    public Variable
    (String name, String stackPosition, boolean initialized, MemoryClassEnum type) 
    {
        this.name = name;
        this.stackPosition = stackPosition;
        this.initialized = initialized;
        this.typeSpecifier = type;
    }

    public Variable
    (String name, String stackPosition, boolean initialized, MemoryClassEnum type, 
    LinkedList<MemoryClassEnum> curPointer) 
    {
        this.name = name;
        this.stackPosition = stackPosition;
        this.initialized = initialized;
        this.typeSpecifier = type;
        if (!curPointer.isEmpty())
            NasmTools.switchStacks(curPointer, pointerType);
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
}
