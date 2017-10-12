package tools;


import constants.MemoryClassEnumeration;


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
    private MemoryClassEnumeration typeSpecifier;

    public Variable
    (String name, String stackPosition, boolean initialized, MemoryClassEnumeration type) 
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

    public MemoryClassEnumeration getTypeSpecifier() 
    {
        return typeSpecifier;
    }

    public void setTypeSpecifier(MemoryClassEnumeration typeSpecifier) 
    {
        this.typeSpecifier = typeSpecifier;
    }

}
