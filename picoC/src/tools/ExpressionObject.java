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
    public String text = null;
    /* Internal informations */
    public int flags = 0x0;
    /* Memory class of expression evaluated */
    MemoryClassEnum type = MemoryClassEnum.VOID;
    /* Shows if variable was in relation or equality context */
    private boolean compared = false;
    /* Size of var */
    private int size;
    /* Stack displacement for stack variables */
    private String stackDisp = null;
    /* Pointers information. This variable operates as stack. The top of
        stack is current memory type that this variable points to.
        If some variable is pointer to: pointer to a char, than this
        list will contain head->MemoryClassEnum.POINTER->MemoryClassEnum.CHAR  */
    private LinkedList<MemoryClassEnum> pointerType = new LinkedList<>();
    /* Position of specific information in flags */
    public static final int REGISTER =           0x1;
    public static final int VAR_STACK =          0x2;
    public static final int VAR_EXTERN =         0x4;
    public static final int INTEGER =            0x8;
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

    /* Constructor to simple pointer */
    public ExpressionObject
    (String text, MemoryClassEnum type, int info, MemoryClassEnum pointer) 
    {
        this.text = text;
        this.type = type;
        flags |= info;
        setSize(type);
        if ((info & VAR_STACK) != 0) {
            this.text = castStackVar(text, type);
            stackDisp = text;
        } 
        this.insertPointerType(pointer);
    }
    
    /* Constructor to complex pointer */
    public ExpressionObject
    (String text, MemoryClassEnum type, int info, 
    LinkedList<MemoryClassEnum> pointer) 
    {
        this.text = text;
        this.type = type;
        flags |= info;
        setSize(type);
        if ((info & VAR_STACK) != 0) {
            this.text = castStackVar(text, type);
            stackDisp = text;
        } 
        NasmTools.switchStacks(pointer, pointerType);
    }
    
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public MemoryClassEnum getType() {
        return type;
    }

    public void setType(MemoryClassEnum type) 
    {
        this.type = type;
        /* Set proper size */
        setSize(type);
    }

    public void setCompared(boolean compared) {
        this.compared = compared;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
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

    public boolean isRegister() 
    {
        return (flags & ExpressionObject.REGISTER) != 0;
    }
    
    public boolean isStackVariable() 
    {
        return (flags & ExpressionObject.VAR_STACK) != 0;
    }

    public boolean isInteger() 
    {
        return (flags & ExpressionObject.INTEGER) != 0;
    }
    
    public boolean isExternVariable()
    {
        return (flags & VAR_EXTERN) != 0;
    }
    
    public boolean isCompared() {
        return compared;
    }
    
    public String getStackDisp() {
        return stackDisp;
    }

    public void setStackDisp(String stackDisp) {
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
    
    public static void castVariablesToMaxSize
    (ExpressionObject left, ExpressionObject right) 
    {
        String casted;
        /* If size of variables already match, than nothing is done */
        if (left.getType() == right.getType())
            return ;
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
        if (this.flags == ExpressionObject.REGISTER)
            return false;
        String nextFreeTemp = NasmTools.getNextFreeTempStr(this.type);
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
        pointerType.push(type);
    }
    
    public MemoryClassEnum getTypeOfPointer()
    {
        return pointerType.peek();
    }
    
}
