
package tools;

import antlr.picoCParser;
import compilationControlers.Checker;
import compilationControlers.Writers;
import constants.MemoryClassEnum;
import nasm.NasmTools;

/**
 *
 * @author aleksandar
 */
public class Emitter 
{

    /* This function represent set of steps needed for calculating
        multiply expression */
    public static ExpressionObject multiply
    (ExpressionObject leftExpr, ExpressionObject rightExpr) 
    {
        String nextFreeTemp;
        ExpressionObject.castVariablesToMaxSize(leftExpr, rightExpr);
        /* Chech wheather leftExpr is register. If it's not then it needs to be
            moved to one and then multiplied */
        if (!leftExpr.isRegister()) {
            nextFreeTemp = NasmTools.getNextFreeTempStr(MemoryClassEnum.INT);
            Writers.emitInstruction("mov", nextFreeTemp, "eax");
            Writers.emitInstruction("mov", "eax", leftExpr.getText());
            Writers.emitInstruction("imul", "eax", rightExpr.getText());
            Writers.emitInstruction("mov", leftExpr.getText(), "eax");
            Writers.emitInstruction("mov", "eax", nextFreeTemp);
            NasmTools.free(nextFreeTemp);
        } else {
            Writers.emitInstruction("imul", leftExpr.getText(), rightExpr.getText());
        }
        return leftExpr;
    }
    
    /* This function represent set of steps needed for calculating
        division or modulo expression */
    public static ExpressionObject divideOrModulo
    (ExpressionObject leftExpr, ExpressionObject rightExpr, int operation) 
    {
        String nextFreeTemp, s1, s2, fakelyTaken = null;
        boolean fake = false;
        ExpressionObject.castVariablesToMaxSize(leftExpr, rightExpr);
        
        if (leftExpr.getText().equals("eax")) {
            if (NasmTools.isTakenRegisterDREG()) {
                /* Never true, but stil */
                nextFreeTemp = NasmTools.getNextFreeTempStr(MemoryClassEnum.INT);
                Writers.emitInstruction("mov", nextFreeTemp, "edx");
                Writers.emitInstruction("cdq");
                Writers.emitInstruction("idiv", rightExpr.getText());
                if (operation == picoCParser.MOD) /* If it's mod, move remainder to eax */ {
                    Writers.emitInstruction("mov", "eax", "edx");
                }
                Writers.emitInstruction("mov", "edx", nextFreeTemp);
                NasmTools.free(nextFreeTemp);
            } else {
                Writers.emitInstruction("cdq");
                Writers.emitInstruction("idiv", rightExpr.getText());
                if (operation == picoCParser.MOD) /* If it's mod, move remainder to eax */ {
                    Writers.emitInstruction("mov", "eax", "edx");
                }
            }
        } else {
                /* leftExpr operand is not eax */
                /* edx needs to be fakely taken, so that getNextFreeTemp4Bytes
                    would not save some register in edx, because it is needed
                    for remainder of division. */

            if (!NasmTools.isTakenRegisterDREG()) {
                fake = true;
                fakelyTaken = NasmTools.get4ByteDRegister();
            }
            /* Always true -> */
            if (NasmTools.isTakenRegisterAREG() && NasmTools.isTakenRegisterDREG()) {
                s1 = NasmTools.getNextFreeTempStr(MemoryClassEnum.INT);
                Writers.emitInstruction("mov", s1, "eax");
                /* save eax value into s1 */
                s2 = NasmTools.getNextFreeTempStr(MemoryClassEnum.INT);
                Writers.emitInstruction("mov", s2, "edx");
                /* save edx value into s2 */
                /* setting eax and edx for div */
                Writers.emitInstruction("mov", "eax", leftExpr.getText());
                Writers.emitInstruction("cdq");

                /* If right operand of division is edx, than eax needs
                        to be divided by moved edx, which is s2.
                       If right operand of division is eax, than eax needs
                        to be divided by moved eax, which is s1. */
                if (rightExpr.getText().equals("edx")) {
                    Writers.emitInstruction("idiv", s2);
                } else if (rightExpr.getText().equals("eax")) {
                    Writers.emitInstruction("idiv", s1);
                } else {
                    Writers.emitInstruction("idiv", rightExpr.getText());
                }
                /* If it's div operation, move result (eax) to leftExpr, 
                        else, move remainder(edx) to leftExpr */
                if (operation == picoCParser.DIV) {
                    Writers.emitInstruction("mov", leftExpr.getText(), "eax");
                }
                if (operation == picoCParser.MOD) {
                    Writers.emitInstruction("mov", leftExpr.getText(), "edx");
                }
                /* restoring values */
                Writers.emitInstruction("mov", "eax", s1);
                Writers.emitInstruction("mov", "edx", s2);

                NasmTools.free(s2);
                NasmTools.free(s1);
            }

            if (fake == true) {
                NasmTools.free(fakelyTaken);
            }
            
        }
        return leftExpr;
    }
    
    public static void andExpressionEvaluation(String left, String right) 
    {
        String labelTrue, labelFalse, afterFalseLabel;
        /* Get labels */
        labelTrue = LabelsMaker.getNextTrueLogicalLabel();
        labelFalse = LabelsMaker.getNextFalseLogicalLabel();
        afterFalseLabel = LabelsMaker.getNextAfterFalseLogicalLabel();
        
        /* Emit compare with zero, and jump if it is true */
        Writers.emitInstruction("cmp", left, "0");
        Writers.emitInstruction("je", labelFalse);
        /* Now compare right with 0 and jump if it is true */
        Writers.emitInstruction("cmp", right, "0");
        Writers.emitInstruction("je", labelFalse);
        /* Emit true label and store 1 to left register, meaning evaluated: true */
        Writers.emitLabel(labelTrue);
        Writers.emitInstruction("mov", left, "1");
        Writers.emitInstruction("jmp", afterFalseLabel);
        /* Emit false label, and store 0 to left register, meaning evaluated: false */
        Writers.emitLabel(labelFalse);
        Writers.emitInstruction("mov", left, "0");
        /* Emit label for rest of the code */
        Writers.emitLabel(afterFalseLabel);
    }

    public static void orExpressionEvaluation(String left, String right) 
    {
        String labelTrue, labelFalse, afterFalseLabel;
        /* Get labels */
        labelTrue = LabelsMaker.getNextTrueLogicalLabel();
        labelFalse = LabelsMaker.getNextFalseLogicalLabel();
        afterFalseLabel = LabelsMaker.getNextAfterFalseLogicalLabel();
        
        /* Emit compare with zero, and jump if it is false */
        Writers.emitInstruction("cmp", left, "0");
        Writers.emitInstruction("jne", labelTrue);
        /* Now compare right with 0 and jump if it is true */
        Writers.emitInstruction("cmp", right, "0");
        Writers.emitInstruction("je", labelFalse);
        /* Emit true label and store 1 to left register, meaning evaluated: true */
        Writers.emitLabel(labelTrue);
        Writers.emitInstruction("mov", left, "1");
        Writers.emitInstruction("jmp", afterFalseLabel);
        /* Emit false label */
        Writers.emitLabel(labelFalse);
        /* Actually, mov left 0 doesn't need to be done, because left will always
            be 0, if "OR" condition is evaluated false. But it is there for  
            easier debugging */
        Writers.emitInstruction("mov", left, "0");

        /* Emit label for rest of the code */
        Writers.emitLabel(afterFalseLabel);
    }

    
    /* This function represent set of steps needed for calculating
        assignment expression */
    public static void assign
    (ExpressionObject expr1, ExpressionObject expr2) 
    {
        MemoryClassEnum type = expr1.getType();
        
        if (expr2.isRegister() || expr2.isInteger()) {
            Writers.emitInstruction("mov", expr1.getText(), expr2.getText());
        } else {
            String temp = NasmTools.getNextFreeTempStr(type);
            Writers.emitInstruction("mov", temp, expr2.getText());
            Writers.emitInstruction("mov", expr1.getText(), temp);
            NasmTools.free(temp);
        }
    }

    /* This function represent set of steps needed for calculating
        assignment-add expression */
    public static void assignAdd
    (ExpressionObject expr1, ExpressionObject expr2) 
    {
        MemoryClassEnum type = expr1.getType();
        
        if (expr2.isRegister()) {
            Writers.emitInstruction("add", expr1.getText(), expr2.getText());
            NasmTools.free(expr2.getText());
        } else if (expr2.isInteger()) {
            Writers.emitInstruction("add", expr1.getText(), expr2.getText());
        } else {
            String temp = NasmTools.getNextFreeTempStr(type);
            Writers.emitInstruction("mov", temp, expr2.getText());
            Writers.emitInstruction("add", expr1.getText(), temp);
            NasmTools.free(temp);
        }
    }
   
    /* This function represent set of steps needed for calculating
        assignment-sub expression */
    public static void assignSub
    (ExpressionObject expr1, ExpressionObject expr2) 
    {
        MemoryClassEnum type = expr1.getType();
        
        if (expr2.isRegister()) {
            Writers.emitInstruction("sub", expr1.getText(), expr2.getText());
            NasmTools.free(expr2.getText());
        } else if (expr2.isInteger()) {
            Writers.emitInstruction("sub", expr1.getText(), expr2.getText());
        } else {
            String temp = NasmTools.getNextFreeTempStr(type);
            Writers.emitInstruction("mov", temp, expr2.getText());
            Writers.emitInstruction("sub", expr1.getText(), temp);
            NasmTools.free(temp);
        }
    }
    
    /* This function represent set of steps needed for calculating
        assignment-mul expression */
    public static void assignMul
    (ExpressionObject expr1, ExpressionObject expr2) 
    {
        String stackPosition = expr1.getText();
        expr1.putInRegister();
        if (expr2.isInteger())
            expr2.putInRegister();
        /* Emitter multiply will return same value pased as first argument, 
            but in case that that change in a future, let's store it in var */
        expr1 = Emitter.multiply(expr1, expr2);   
        Writers.emitInstruction("mov", stackPosition, expr1.getText());
        expr1.freeRegister();
    }
    
    /* This function represent set of steps needed for calculating
        assignment-div/mod expression */
    public static void assignDivMod
    (ExpressionObject expr1, ExpressionObject expr2, int operation) 
    {
        String stackPos;
        stackPos = expr1.getText();
        expr1.putInRegister();
        if (expr2.isInteger())
            expr2.putInRegister();
        /* Emitter divideOrModulo will return same value passed as first argument, 
            but in case that that could change in a future, let's store it in var */
        expr1 = Emitter.divideOrModulo(expr1, expr2, operation);
        Writers.emitInstruction("mov", stackPos, expr1.getText());
        expr1.freeRegister();
    }

    public static void SetCCInstruction(String left, int typeOfRelation) 
    {
        switch (typeOfRelation) {
            case picoCParser.LESS :
                Writers.emitInstruction("setl", left);
                break;
            case picoCParser.LESS_EQUAL :
                Writers.emitInstruction("setle", left);
                break;
            case picoCParser.GREATER :
                Writers.emitInstruction("setg", left);
                break;
            case picoCParser.GREATER_EQUAL :
                Writers.emitInstruction("setge", left);
                break;
            case picoCParser.EQUAL :
                Writers.emitInstruction("sete", left);
                break;
            case picoCParser.NOT_EQUAL :
                Writers.emitInstruction("setne", left);
                break;
        }
    }

    /* Function checks which operator is used, and based on that, 
        emits set of instructions */
    public static void decideAssign
    (ExpressionObject expr1, ExpressionObject expr2, int operation) 
    {
        switch (operation) {
            case picoCParser.ASSIGN :
                Emitter.assign(expr1, expr2);
                break;
            case picoCParser.ASSIGN_ADD :
                Emitter.assignAdd(expr1, expr2);
                break;
            case picoCParser.ASSIGN_SUB :
                Emitter.assignSub(expr1, expr2);
                break;
            case picoCParser.ASSIGN_MUL :
                Emitter.assignMul(expr1, expr2);
                break;
            case picoCParser.ASSIGN_DIV :
                Emitter.assignDivMod(expr1, expr2, operation);
                break;
            case picoCParser.ASSIGN_MOD :
                Emitter.assignDivMod(expr1, expr2, operation);
                break;
        }
    }

    /* Function emits conditional move operation based on previous
        comparation. It always emits opposite of true condition. */
    public static void setConditionalMoveOperator
    (ExpressionObject expr2, ExpressionObject expr3) 
    {
        int typeOfRelation = RelationHelper.getRelation();
        switch (typeOfRelation) {
            case picoCParser.LESS :
                Writers.emitInstruction("cmovge", expr2.getText(), expr3.getText());
                break;
            case picoCParser.LESS_EQUAL :
                Writers.emitInstruction("cmovg", expr2.getText(), expr3.getText());
                break;
            case picoCParser.GREATER :
                Writers.emitInstruction("cmovle", expr2.getText(), expr3.getText());
                break;
            case picoCParser.GREATER_EQUAL :
                Writers.emitInstruction("cmovl", expr2.getText(), expr3.getText());
                break;
            case picoCParser.EQUAL :
                Writers.emitInstruction("cmovne", expr2.getText(), expr3.getText());
                break;
            case picoCParser.NOT_EQUAL :
                Writers.emitInstruction("cmove", expr2.getText(), expr3.getText());
                break;
        }
    }
    
    /* Function emits calculation of add/sub expression */
    public static void emitAddSub
    (ExpressionObject leftExpr, ExpressionObject rightExpr, String operation) 
    {
        Writers.emitInstruction(operation, leftExpr.getText(), rightExpr.getText());
    }

    /* Correct operations with pointers are:
        pointer + value
        pointer - value    
        pointer - pointer     
        value   + pointer -> this is switched with
        -> pointer + value, before 
        this function call.
        Any other operation is prevented by Checker.
    */
    public static void emitPointersAddSub
    (ExpressionObject leftExpr, ExpressionObject rightExpr, String operation) 
    {
        String shifting;
        MemoryClassEnum memclass;
        int size, value;
        
        /* If both are pointers, then sub is only valid operation */
        if (leftExpr.isPointer() && rightExpr.isPointer()) {
            Writers.emitInstruction(operation, leftExpr.getText(), rightExpr.getText());
            return;
        } 
        /* In other cases left is pointer and right one is not. */
        memclass = leftExpr.getPointer().getType();
        size = PointerTools.getByteIncrement(leftExpr);
        
        if (rightExpr.isInteger()) {    // if it is int, let java calculate
            value = Integer.parseInt(rightExpr.getText());
            value *= size;
            rightExpr.setText(Integer.toString(value));
        } else {
            if (!leftExpr.isArray()) {
                shifting = NasmTools.getShiftForPointer(memclass);
                Writers.emitInstruction("shl", rightExpr.getText(), shifting);
            } else {
                Writers.emitInstruction("imul", rightExpr.getText(), Integer.toString(size));
            }
        }
        Writers.emitInstruction(operation, leftExpr.getText(), rightExpr.getText());
    }

    /* Decide which method will be caled based on the given operation */
    public static void decideAssignPointers
    (ExpressionObject newVariable, ExpressionObject expr, int operation) 
    {
        switch (operation) {
            case picoCParser.ASSIGN:
                Emitter.assign(newVariable, expr);
                break;
            case picoCParser.ASSIGN_ADD:
                Emitter.assignAddSubPointers(newVariable, expr, operation);
                break;
            case picoCParser.ASSIGN_SUB:
                Emitter.assignAddSubPointers(newVariable, expr, operation);
                break;    
        }
    }

    private static void assignAddSubPointers
    (ExpressionObject leftExpr, ExpressionObject rightExpr, int operation) 
    {
        String shifting, op;
        MemoryClassEnum memclass;
        int size, value;
        op = NasmTools.getOperation(operation);
        
        memclass = leftExpr.getPointer().getType();
        size = NasmTools.getSize(memclass);
        
        /* If both are pointers, then sub them */
        if (rightExpr.isPointer()) {
            rightExpr.putInRegister();
            Writers.emitInstruction(op, leftExpr.getText(), rightExpr.getText());
            rightExpr.freeRegister();
            return;
        } 
        
        if (rightExpr.isInteger()) {    // if it is int, let java calculate
            value = Integer.parseInt(rightExpr.getText());
            value *= size;
            rightExpr.setText(Integer.toString(value));
        } else {
            rightExpr.putInRegister();
            shifting = NasmTools.getShiftForPointer(memclass);
            Writers.emitInstruction("shl", rightExpr.getText(), shifting);
        }
        /* Emitting */
        Emitter.emitMoveToStack(leftExpr, rightExpr, op);
        
    }

    private static void emitMoveToStack
    (ExpressionObject leftExpr, ExpressionObject rightExpr, String op) 
    {
        if (rightExpr.isRegister()) {
            Writers.emitInstruction(op, leftExpr.getText(), rightExpr.getText());
            NasmTools.free(rightExpr.getText());
        } else if (rightExpr.isInteger()) {
            Writers.emitInstruction(op, leftExpr.getText(), rightExpr.getText());
        } else {
            String temp = NasmTools.getNextFreeTempStr(leftExpr.getType());
            Writers.emitInstruction(op, temp, rightExpr.getText());
            Writers.emitInstruction(op, leftExpr.getText(), temp);
            NasmTools.free(temp);
        }
    }

    public static void emitAnd
    (ExpressionObject leftExpr, ExpressionObject rightExpr) 
    {
        
    }
   
    
}
