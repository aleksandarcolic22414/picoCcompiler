package tools;


import constants.MemoryClassEnum;


/**
 *
 * @author Aleksandar Colic
 */
public class Variable 
{

    /* Variable name */
    private String name;

    /* Represents variable displacement on stack. Format is:
            dword [rbp-X] */
    private String stackPosition;

    /* Is variable initialized */
    private boolean initialized;

    /* Memory class of variable */
    private MemoryClassEnum typeSpecifier;

    public Variable
    (String name, String stackPosition, boolean initialized, MemoryClassEnum type) 
    {
        this.name = name;
        this.stackPosition = stackPosition;
        this.initialized = initialized;
        this.typeSpecifier = type;
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

}
