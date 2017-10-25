/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import antlr.picoCParser;
import compilationControlers.Writers;
import constants.MemoryClassEnumeration;
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
        /* Chech wheather leftExpr is register. If it's not then it needs to be
            moved to one and then multiplied */
        if (!leftExpr.isRegister()) {
            nextFreeTemp = NasmTools.getNextFreeTemp4Bytes();
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
        
        if (leftExpr.getText().equals("eax")) {
            if (NasmTools.isTakenRegisterDREG()) {
                /* Never true, but stil */
                nextFreeTemp = NasmTools.getNextFreeTemp4Bytes();
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
                s1 = NasmTools.getNextFreeTemp4Bytes();
                Writers.emitInstruction("mov", s1, "eax");
                /* save eax value into s1 */
                s2 = NasmTools.getNextFreeTemp4Bytes();
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
    
    public static void assign(ExpressionObject expr, String stackPos) 
    {
        if (expr.isRegister() || expr.isInteger()) {
            Writers.emitInstruction("mov", stackPos, expr.getText());
        } else {
            String temp = NasmTools.getNextFreeTemp4Bytes();
            Writers.emitInstruction("mov", temp, expr.getText());
            Writers.emitInstruction("mov", stackPos, temp);
            NasmTools.free(temp);
        }
    }

    public static void assignAdd(ExpressionObject expr, String stackPos) 
    {
        if (expr.isRegister()) {
            Writers.emitInstruction("add", stackPos, expr.getText());
            NasmTools.free(expr.getText());
        } else if (expr.isInteger()) {
            Writers.emitInstruction("add", stackPos, expr.getText());
        } else {
            String temp = NasmTools.getNextFreeTemp4Bytes();
            Writers.emitInstruction("mov", temp, expr.getText());
            Writers.emitInstruction("add", stackPos, temp);
            NasmTools.free(temp);
        }
    }

    public static void assignSub(ExpressionObject expr, String stackPos) 
    {
        if (expr.isRegister()) {
            Writers.emitInstruction("sub", stackPos, expr.getText());
            NasmTools.free(expr.getText());
        } else if (expr.isInteger()) {
            Writers.emitInstruction("sub", stackPos, expr.getText());
        } else {
            String temp = NasmTools.getNextFreeTemp4Bytes();
            Writers.emitInstruction("mov", temp, expr.getText());
            Writers.emitInstruction("sub", stackPos, temp);
            NasmTools.free(temp);
        }
    }

    public static void assignMul(ExpressionObject expr, String stackPos) 
    {
        /* Make expression object that contains var from stack 
            and put it in register */
        ExpressionObject var = new ExpressionObject
            (stackPos, MemoryClassEnumeration.INT, ExpressionObject.VAR_STACK);
        var.putInRegister();
        /* Emitter multiply will return same value pased as first argument, 
            but in case that that change in a future, let's store it in var */
        var = Emitter.multiply(var, expr);   
        Writers.emitInstruction("mov", stackPos, var.getText());
        var.freeRegister();
    }
    
    public static void assignDivMod
    (ExpressionObject expr, String stackPos, int operation) 
    {
        /* Make expression object that contain var from stack 
            and put var in register */
        ExpressionObject var = new ExpressionObject
            (stackPos, MemoryClassEnumeration.INT, ExpressionObject.VAR_STACK);
        var.putInRegister();
        /* Emitter divideOrModulo will return same value pased as first argument, 
            but in case that that change in a future, let's store it in var */
        var = Emitter.divideOrModulo(var, expr, operation);
        Writers.emitInstruction("mov", stackPos, var.getText());
        var.freeRegister();
    }

    
}
