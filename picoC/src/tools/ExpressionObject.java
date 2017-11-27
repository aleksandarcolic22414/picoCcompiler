package tools;

import antlr.picoCParser;
import compilationControlers.Checker;
import compilationControlers.CompilationControler;
import compilationControlers.Writers;
import constants.Constants;
import constants.MemoryClassEnum;
import java.util.LinkedList;
import nasm.NasmTools;

/**
 *
 * @author aleksandar
 */
public class ExpressionObject 
{
    /* Holds text representation of current evaluated expression */
    private String text = null;
    
    /* Internal informations */
    private int flags = 0x0;
    
    /* Memory class of expression evaluated */
    private MemoryClassEnum type = MemoryClassEnum.VOID;
    
    /* Type of array members if expression is an array */
    private MemoryClassEnum arrayType = null;
    
    /* Shows if variable was in relation or equality context */
    private boolean compared = false;
    
    /* Shows if variable is dereferenced */
    private boolean dereferenced = false;
    
    /* Size of var */
    private int size;
    
    /* Stack displacement for stack variables */
    private String stackDisp = null;
    
    /* If expression is variable, this represents it's name */
    private String name = null;
    
    /* Pointers information. This variable operates as stack. The top of
        stack is current memory type that this variable points to.
        If some variable is pointer to: pointer to a char, than this
        list will contain head->MemoryClassEnum.POINTER->MemoryClassEnum.CHAR  */
    private LinkedList<Pointer> pointerTo = new LinkedList<>();
    
    /* Position of specific information in flags */
    public static final int REGISTER =           0x1;
    public static final int VAR_STACK =          0x2;
    public static final int VAR_EXTERN =         0x4;
    public static final int CONSTANT =           0x8;
    public static final int STRING_LITERAL =     0x10;
    
    /* Constructor */
    public ExpressionObject
    (String text, MemoryClassEnum type, int info) 
    {
        this.text = text;
        this.type = type;
        flags |= info;
        setSize(type);
        if ((info & VAR_STACK) != 0) {
            this.text = castStackVar(text, type);
            stackDisp = text;
        } 
    }

    /* Constructor to complex pointer */
    /* If variable on stack is created, than input text is given as stack
        displacement. Something like: rbp-12 */
    public ExpressionObject
    (String ntext, MemoryClassEnum type, int info, 
     LinkedList<Pointer> pointer) 
    {
        this.text = ntext;
        this.type = type;
        flags |= info;
        setSize(type);
        /* If it is stack variable, cast text and set stack displacement */
        if ((info & VAR_STACK) != 0) {
            this.text = castStackVar(ntext, type);
            stackDisp = ntext;
        } 
        PointerTools.copyPointerList(pointerTo, pointer);
    }
    
    /*  Constructor to a simple pointer 
        If variable on stack is created, than input text is given as stack
        displacement. Something like: rbp-12 */
    public ExpressionObject
    (String ntext, MemoryClassEnum type, int info, MemoryClassEnum pointer) 
    {
        this.text = ntext;
        this.type = type;
        flags |= info;
        setSize(type);
        /* If it is stack variable, cast text and set stack displacement */
        if ((info & VAR_STACK) != 0) {
            this.text = castStackVar(ntext, type);
            stackDisp = ntext;
        } 
        insertPointerType(pointer);
    }
    
    /* Construct expression object from variable 
        If variable is an array, than text will hold it's stack displacement.
        Otherwise, text will hold stack displacement and proper cast in order
        to access this object in assembly. Something like this:
        Array:          [rbp-256]
        Integer:        dword[rbp-256]
        Pointer         qword[rbp-256]
    */
    public ExpressionObject(Variable var)
    {
        this.text = "[" + var.getStackPosition() + "]";
        this.type = var.getTypeSpecifier();
        this.name = var.getName();
        this.stackDisp = var.getStackPosition();
        this.size = var.getSize();
        this.arrayType = var.getArrayType();        // null is default
        this.text = castStackVar(this.stackDisp, this.type);
        
        flags |= VAR_STACK;
        
        if (var.isExtern())
            flags |= VAR_EXTERN;
        
        if (!var.getPointerTo().isEmpty())
            PointerTools.copyPointerList(pointerTo, var.getPointerTo());
    }
    
    /* If variable is array, it needs to be moved to register in order to
       access it's elements. */
    public void initArray()
    {
        String nextFreeTemp, help;
        nextFreeTemp = NasmTools.getNextFreeTempStr(MemoryClassEnum.POINTER);
        /* If variable is not currently declaring, than it needs to be moved
            to register in order to do stack calculations */
        help = "[" + this.stackDisp + "]";
        Writers.emitInstruction("lea", nextFreeTemp, help);
        
        /* Set new properties to variable */
        this.text = nextFreeTemp;
        this.stackDisp = nextFreeTemp;
        flags = ExpressionObject.REGISTER;
    }
    
    public String getText() 
    {
        return text;
    }

    public void setText(String text) 
    {
        this.text = text;
    }

    public MemoryClassEnum getType() 
    {
        return type;
    }

    public void setType(MemoryClassEnum type) 
    {
        this.type = type;
        /* Set proper size */
        setSize(type);
    }

    public void setCompared(boolean compared) 
    {
        this.compared = compared;
    }

    public int getSize() 
    {
        return size;
    }

    public final void setSize(int size) 
    {
        this.size = size;
    }

    public boolean isDereferenced() 
    {
        return dereferenced;
    }

    public void setDereferenced() 
    {
        this.dereferenced = true;
        this.flags = ExpressionObject.VAR_STACK;
    }

    public LinkedList<Pointer> getPointerTo() 
    {
        return pointerTo;
    }

    public void setPointerTo(LinkedList<Pointer> pointerTo) 
    {
        this.pointerTo = pointerTo;
    }
    
    /* Check if comparison is done and cast variable if it is */
    public void comparisonCheck() 
    {
        if (!isCompared())
            return ;
        /* If it is variable on stack or integer, move it to register */
        if (isStackVariable() || isInteger()) 
            putInRegister();
        
        castVariable(MemoryClassEnum.CHAR);
        Emitter.SetCCInstruction(this.text, RelationHelper.getRelation());
        setCompared(false);
    }

    public MemoryClassEnum getArrayType() 
    {
        return arrayType;
    }

    public void setArrayType(MemoryClassEnum arrayType) 
    {
        this.arrayType = arrayType;
    }

    public String getName() 
    {
        return name;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public boolean isRegister() 
    {
        return (flags & ExpressionObject.REGISTER) != 0;
    }
    
    public boolean isArray() 
    {
        return arrayType != null;
    }
    
    public boolean isStackVariable() 
    {
        return (flags & ExpressionObject.VAR_STACK) != 0;
    }

    public boolean isInteger() 
    {
        return (flags & ExpressionObject.CONSTANT) != 0;
    }
    
    public boolean isExternVariable()
    {
        return (flags & VAR_EXTERN) != 0;
    }
    
    public boolean isCompared() 
    {
        return compared;
    }
    
    public String getStackDisp() 
    {
        return stackDisp;
    }

    public void setStackDisp(String stackDisp) 
    {
        this.stackDisp = stackDisp;
    }

    /* Cast variable to specific size and return wheather it is casted in */
    public boolean castVariable(MemoryClassEnum type) 
    {
        /* Check if cast is needed */
        if (this.type == type)
            return false;
        if (!this.isRegister())
            this.putInRegister();
        this.type = type;
        int sizeOfVar = NasmTools.getSize(type);
        this.size = sizeOfVar;
        this.text = NasmTools.castVariable(text, sizeOfVar);
        return true;
    }

    public void freeRegister() 
    {
        NasmTools.free(this);
    }

    public void setToRegister() 
    {
        this.flags = ExpressionObject.REGISTER;
    }
    
    /* Function casts variables to bigger size of two. Also, the
        bigger size is returned. If one or both parameters are integers
        0 is returned and no casting is done. */
    public static int castVariablesToMaxSize
    (ExpressionObject left, ExpressionObject right) 
    {
        String casted;
        if (left.isInteger() || right.isInteger())
            return 0;
        /* If sizes of variables already match, than nothing is done */
        if (left.getType() == right.getType())
            return NasmTools.getSize(left.getType());
        /* Check if variable types matches */
        Checker.checkVarMatch(left, right);
        int maxSizeOfVars = left.size < right.size ? right.size : left.size;
        if (left.size == maxSizeOfVars) {  // right needs to be casted
            right.putInRegister();
            casted = NasmTools.castVariable(right.getText(), maxSizeOfVars);
            right.setText(casted);
            right.setType(left.getType());
        } else {  // left needs to be casted
            left.putInRegister();
            casted = NasmTools.castVariable(left.getText(), maxSizeOfVars);
            left.setText(casted);
            left.setType(right.getType());
        }
        return maxSizeOfVars;
    }
    
    /* Set size of variable */
    public final void setSize(MemoryClassEnum type) 
    {
        switch (type) {
            case CHAR:
                this.size = Constants.SIZE_OF_CHAR;
                break;
            case INT:
                this.size = Constants.SIZE_OF_INT;
                break;
            case POINTER:
                this.size = Constants.SIZE_OF_POINTER;
                break;
            default:
                this.size = -1;
        }
    }    

    public static ExpressionObject freeOneOfTwo(ExpressionObject left, ExpressionObject right) 
    {
        if (left.isRegister()) {
            if (right.isRegister())
                right.freeRegister();
            return left;
        } 
        if (right.isRegister()) {
            if (left.isRegister())
                left.freeRegister();
            return right;
        }
        return left;
    }

    /* Function puts variable in register if there is any free registers */
    public boolean putInRegister() 
    {
        String reloadedReg, nextFreeTemp;
        int register;
        
        if (isRegister())
            return false;
        if (isArray() || isDereferenced()) {
            /* Do not use new register, but instead use current one */
            register = NasmTools.stringToRegister(stackDisp);
            reloadedReg = NasmTools.registerToString(register, this.type);
            Writers.emitInstruction("mov", reloadedReg, this.text);
            this.setToRegister();
            this.setText(reloadedReg);
            return true;
        }
        nextFreeTemp = NasmTools.getNextFreeTempStr(this.type);
        if (!NasmTools.isRegister(nextFreeTemp))
            CompilationControler.errorOcured(null, null, "Out of registers!");
        
        Writers.emitInstruction("mov", nextFreeTemp, this.text);
        this.setToRegister();
        this.setText(nextFreeTemp);
        return true;
    }

    /* Check wheather text is "a" register */
    public boolean isRegisterA() 
    {
        return NasmTools.stringToRegister(this.text) == NasmTools.AREG;
    }

    /* Comparing with zero is used for expression like: if (a) which is equal
        to: if (a != 0) */
    public void compareWithZero() 
    {
        if (isInteger())
            putInRegister();
        Writers.emitInstruction("cmp", getText(), "0");
        RelationHelper.setRelation(picoCParser.NOT_EQUAL);
    }

    /* Function cast's variable and emits proper instruction for negation */
    public void setNegation() 
    {
        if (isInteger() || isStackVariable())
            putInRegister();
        
        castVariable(MemoryClassEnum.CHAR);
        Emitter.SetCCInstruction(this.text, picoCParser.EQUAL);
        setCompared(false);
    }

    private static String castStackVar(String stackPosition, MemoryClassEnum type) 
    {
        String cast = NasmTools.getCast(type);
        return cast + " [" + stackPosition + "]";
    }
    
    public final void insertPointerType(MemoryClassEnum type)
    {
        Pointer ptr = new Pointer(type);
        pointerTo.push(ptr);
    }
    
    public Pointer getPointer()
    {
        return pointerTo.peek();
    }

    public boolean isPointer() 
    {
        return !pointerTo.isEmpty();
    }

    /* Function dereferences pointer.   */
    public void dereference() 
    {
        String cast, newText;
        Pointer ptr;
        
        if (!isDereferenced() && !isArray()) {
            putInRegister();
            this.stackDisp = this.text;     // rax for example...
        } else {
            /* If variable is a pointer to a complex type like int (*)[10][5]
            which is multidimensional array, than it can't be dereferenced.
            In normal case, dereference it. */
            if (!isArray())
                Writers.emitInstruction("mov", this.stackDisp, this.text);
            else {
                /* If it is and array, see if it is like simple pointer
                   (Pointer to simple type. Like: int *). If it is not, no
                    casting or referencing is done. */
                if (!PointerTools.isSimplePointer(this)) {
                    removePointerType();  // force poping to get next type of ptr
                    return;
                }
            }
        }
        /* pop last pointer and calculate it's type and do proper cast */
        ptr = removePointerType();    
        cast = NasmTools.getCast(ptr.getType());
        newText = cast + " [" + this.stackDisp + "]";
        setType(ptr.getType());
        setText(newText);
        setDereferenced();
    }

    /* Function casts variable to a specific size. 
        Cast is done only if variable is in register. */
    public void castRegisterTo(MemoryClassEnum newType) 
    {
        if (!isRegister())
            return;
        setType(newType);
        int newSize = NasmTools.getSize(newType);
        this.text = NasmTools.castVariable(this.text, newSize);
    }

    public boolean isStringLiteral() 
    {
        return (flags & ExpressionObject.STRING_LITERAL) != 0;
    }

    private void setStackVariable() 
    {
        this.flags = ExpressionObject.VAR_STACK;
    }

    /* Simply insert minus prefix if there isn't any, and if there is
        remove it. */
    public ExpressionObject insertIntMinusPrefix() 
    {
        if (this.text.startsWith("-"))
            this.text = this.text.substring(1);
        else
            this.text = "-" + this.text;
        return this;
    }

    /* In order to switch array calculation to normal calculation, whenever
        array is dereferenced it is checked if there are any left "array" 
        types in pointerTo list. If there is not, this expression becomes
        normal variable. */
    public Pointer removePointerType() 
    {
        Pointer ptr = pointerTo.pop();
        if (pointerTo.peek() == null || pointerTo.peek().getSizes().isEmpty())
            this.arrayType = null;
        return ptr;
    }

    /* In this case expr is in register or it is an integer.
        If variable that needs to be subsripted is integer, simple 
        caluclation is done like: cast[this.register + sizeoftype*expr].
        If variable is register     */
    public void simpleSubscript(ExpressionObject expr) 
    {
        MemoryClassEnum typeofptr;
        /* Increment size represents size of bytes that needs to be added
            to start of the source(array) for increment of 1. */
        String cast, sizeOfElement, disp;
        /* Let's do calculation */
        /* First get size that needs to be multiplyed with element number */
        sizeOfElement = Integer.toString(PointerTools.getByteIncrement(this));
        typeofptr = getPointer().getType();
        /* Get proper cast */
        cast = NasmTools.getCast(typeofptr);
        /* If expression is integer, directly caluculate it's position on stack
            like cast [start + index * sizeofelement] */
        if (expr.isInteger()) {
            disp = cast + " [" + this.text + "+" + expr.getText() + "*" + sizeOfElement + "]";
        } else {
            /* If it is not, expr is in register, so first multiply it with
                proper size to get displacement and add it to the start of the
                array (source)  */
            Writers.emitInstruction("imul", expr.getText(), sizeOfElement);
            Writers.emitInstruction("add", this.text, expr.getText());
            disp = cast + " [" + this.text + "]";
        }
        setText(disp);
        setType(typeofptr);
        setStackVariable();
        /* Remove pointer type and clear taken register */
        removePointerType();
        if (expr.isRegister())
            expr.freeRegister();
    }

    public void complexSubscript(ExpressionObject expr) 
    {
        MemoryClassEnum typeofptr;
        /* Increment size represents size of bytes that needs to be added
            to start of the source(array) for increment of 1. */
        String cast, incrementSize, disp;
        /* Let's do calculation */
        /* First get size that needs to be multiplyed with element index */
        incrementSize = Integer.toString(PointerTools.getByteIncrement(this));
        typeofptr = getPointer().getType();
        
        /* If expression is integer, directly caluculate it's position on stack
            like [start + index * sizeofelement] and mov that address to
            source with lea instruction. */
        if (expr.isInteger()) {
            disp = "[" + this.text + "+" + expr.getText() + "*" + incrementSize + "]" ;
            Writers.emitInstruction("lea", this.text, disp);
        } else {
            /* If it is not, expr is in register, so first multiply it with
                proper size to get displacement and add it to the start of the
                array (source)  */
            Writers.emitInstruction("imul", expr.getText(), incrementSize);
            Writers.emitInstruction("add", this.text, expr.getText());
        }
        
        setType(typeofptr);
        
        /* Remove pointer type and clear taken register */
        removePointerType();
        if (expr.isRegister())
            expr.freeRegister();
    }

    
    /* Put variable's address in register and cast that variable to register */
    public void putAddressInRegister() 
    {
        String nextFreeTemp, stackPos;
        
        nextFreeTemp = NasmTools.getNextFreeTempStr(this.type);
        stackPos = "[" + this.stackDisp + "]";
        /* If variable about to be subsripted is pointer, than address that
            that pointer points to needs to be load in register. 
            In case that variable is array and parameter than it is threater
            like pointer, so extra check for stack variable is needed.
            If variable is an array, than address of the 
            array is load into register. */
        if (this.isArray() && !this.isStackVariable())
            Writers.emitInstruction("lea", nextFreeTemp, stackPos);
        else 
            Writers.emitInstruction("mov", nextFreeTemp, this.text);
        
        this.setToRegister();
        this.setText(nextFreeTemp);
    }

}
