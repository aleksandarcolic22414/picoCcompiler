/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import constants.MemoryClassEnum;
import java.util.LinkedList;
import nasm.NasmTools;

/**
 *
 * @author aleksandar
 */
public class PointerTools 
{

    /* Functions inserts pointers from second list to first list.
        Second list represents sizes of the array which needs to be
        represented as pointer. In rest of the text word "sizes" is used to
        represent number of objects that pointer points to. 
        So in declaration: int arr[5][10] arr is int (*)[10] 
        and 10 represents it's size.
        hSizes is useful to remember last sizes
        that last pointer points to. After new pointer is made,
        it's own size is added to the hSizes list in order to allow new pointer to
        get it's size. hSizes has number 1 for the first element which 
        represents that this is not simple pointer but array. */
    public static void insertArrays
    (LinkedList<Pointer> pointer, LinkedList<Integer> arrays, 
     MemoryClassEnum type) 
    {
        LinkedList<Integer> hSizes = new LinkedList<>();
        hSizes.add(1);
        LinkedList<Integer> newSizes;
        Integer last;
        Pointer ptr;
        while (!arrays.isEmpty()) {
            newSizes = new LinkedList<>();
            /* copy previous sizes */
            copyArrayList(newSizes, hSizes);
            /* Get this pointer size which is last element in arrays and add
                it to the hSizes */
            last = arrays.removeLast();
            hSizes.push(last);
            ptr = new Pointer(type, newSizes);
            /* Insert new pointer */
            PointerTools.insertPointer(pointer, ptr);
        }
    }

    /* Function adds sizes from second list to first list */
    public static void addSizes
    (LinkedList<Integer> sizes, LinkedList<Integer> newSizes) 
    {
        if (sizes == null || newSizes == null)
            return;
        int len = newSizes.size();
        
        for (int i = 0; i < len; ++i)
            sizes.addLast(newSizes.get(i));
    }

    /* If there is any pointer in the list, than MemoryClassEnum.POINTER 
        type needs to be inserted instead of real type. */
    public static void insertPointer(LinkedList<Pointer> pointer, Pointer ptr) 
    {
        if (!pointer.isEmpty())
            ptr.setType(MemoryClassEnum.POINTER);
        pointer.push(ptr);
    }
    
    /* Function that inserts all elements from second list to first list */
    public static void copyArrayList
    (LinkedList<Integer> list1, LinkedList<Integer> list2) 
    {
        if (list1 == null || list2 == null)
            return;
        list2.forEach((i) -> {
            list1.addLast(i);
        });
    }
    
    
    /* Function insert new type that current variable points to.
        If list is empty that means that pointer to simple type is inserted.
        If it is not empty that means that pointer to pointer is declared. */
    public static void insertPointerType
    (LinkedList<Pointer> curPointer, MemoryClassEnum type) 
    {
        Pointer ptr;
        if (curPointer.isEmpty())
            ptr = new Pointer(type);
        else
            ptr = new Pointer(MemoryClassEnum.POINTER);
        curPointer.push(ptr);
    }

    /* Switch elements from list1 to list2. Lists are implemented as stacks */
    public static void switchStacks
    (LinkedList<Pointer> list1, LinkedList<Pointer> list2)
    {
        if (list1.isEmpty())
            return;
        Pointer type = list1.pop();
        switchStacks(list1, list2);
        list2.push(type);
    }
    
    /* Switch elements from list1 to list2. Lists are implemented as stacks */
    public static void switchArrays
    (LinkedList<Integer> list1, LinkedList<Integer> list2)
    {
        if (list1.isEmpty())
            return;
        Integer type = list1.pop();
        switchArrays(list1, list2);
        list2.push(type);
    }
    
    /* Function that inserts all elements from second list to first list */
    public static void copyPointerList
    (LinkedList<Pointer> list1, LinkedList<Pointer> list2) 
    {
        if (list1 == null || list2 == null)
            return;
        list2.forEach((i) -> {
            list1.addLast(i);
        });
    }

    public static int getByteIncrement(ExpressionObject leftExpr) 
    {
        int size, times;
        if (!leftExpr.isArray())
             return NasmTools.getSize(leftExpr.getPointer().getType());
        size = NasmTools.getSize(leftExpr.getArrayType());
        times = PointerTools.mulPointerSizes(leftExpr.getPointer());
        
        return size*times;
    }

    /* Sum sizes from pointerS list. */
    public static int mulPointerSizes(Pointer pointer) 
    {
        LinkedList<Integer> sizes = pointer.getSizes();
        int res = 1;
        if (sizes == null || sizes.isEmpty())
            return 0;
        
        for (Integer i : sizes)
            res *= i;
        return res;
    }

    /* Return wheather expression is pointer to a simple type.
        Variable is simple pointer if it is pointer to simple type or
        if it is one dimensional array. This could be weird 
        because one dimensional
        arrays and pointers are threated almost the same. Only diference is
        that array's value can't be changed (READ: array's value, not it's
        members value).
        If variable is one dimensional array, than
        it's next type is a simple pointer (It's sizes are empty).
        So next pointer type needs to be reached and if it has no sizes
        in it, variable is simple pointer. */
    public static boolean isSimplePointer(ExpressionObject expr) 
    {
        LinkedList<Pointer> ptrList;
        Pointer ptr;
        ptrList = expr.getPointerTo();
        /* If there is only one type to point to, than it's simple pointer type  */
        if (ptrList.size() == 1)
            return true;
        /* Let's pop current pointer and analyze next type of pointer.  */
        ptr = ptrList.pop();
        /* If next type is simple pointer (but not array!), than this is simple
            pointer. Little note: If next type is array, than this is complex
            pointer.  */
        if (ptrList.peek().getSizes().isEmpty()) {
            ptrList.push(ptr);
            return true;
        }
        /* Put back type, and return false (complex pointer) */
        ptrList.push(ptr);
        return false;
    }
    
}
