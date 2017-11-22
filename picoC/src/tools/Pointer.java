package tools;

import constants.MemoryClassEnum;
import java.util.LinkedList;

/**
 *
 * @author aleksandar
 */
public class Pointer 
{
    /* Represents memory class that this pointer points to */
    private MemoryClassEnum type;
    
    /* Number of objects that this pointer points to.
        If variable is int arr[5][2][3], than arr is int (*)[2][3]
        and sizes will hold 2 and 3.
        Default value is empty list. 
        That doesn't mean that this pointer points to 0 objects, but
        that this pointer doesn't point to consecutive objects in memory */
    private LinkedList<Integer> sizes = new LinkedList<>();

    /* Constructor with memory class only */
    public Pointer(MemoryClassEnum type) 
    {
        this.type = type;
    }

    /* Simple constructor */
    public Pointer(MemoryClassEnum type, LinkedList<Integer> newSizes) 
    {
        this.type = type;
        PointerTools.addSizes(this.sizes, newSizes);
    }

    public MemoryClassEnum getType() 
    {
        return type;
    }

    public void setType(MemoryClassEnum type) 
    {
        this.type = type;
    }

    public LinkedList<Integer> getSizes() 
    {
        return sizes;
    }

    public void setSizes(LinkedList<Integer> sizes) 
    {
        this.sizes = sizes;
    }

}
