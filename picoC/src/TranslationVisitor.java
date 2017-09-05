

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aleksandar
 */
public class TranslationVisitor extends picoCBaseVisitor<String> 
{

    @Override
    public String visitAddSub(picoCParser.AddSubContext ctx) 
    {
        String left = visit(ctx.simpleExpression(0));
        String right = visit(ctx.simpleExpression(1));
        if (ctx.op.getType() == picoCParser.ADD)
            Writers.emitInstruction("add", left, right);
        else if (ctx.op.getType() == picoCParser.SUB)
            Writers.emitInstruction("sub", left, right);    
        
        NasmTools.free(right);
        return left;
    }

    @Override
    public String visitMulDiv(picoCParser.MulDivContext ctx) 
    {
        boolean fake = false;
        String left = visit(ctx.simpleExpression(0));
        String right = visit(ctx.simpleExpression(1));
        String nextFreeTemp;
        String s1, s2;
        if (ctx.op.getType() == picoCParser.MUL) {
            Writers.emitInstruction("imul", left, right);
        } else if (ctx.op.getType() == picoCParser.DIV) {
            /* TODO: Are about to be implementerd */;
            /* If left operand is eax, than it needs to be divided in usual way */
            if (left.equals("eax")) {
                if (NasmTools.isTakenRegisterEDX()) {
                    nextFreeTemp = NasmTools.getNextFreeTemp();
                    Writers.emitInstruction("mov", nextFreeTemp, "edx");
                    Writers.emitInstruction("cdq");
                    Writers.emitInstruction("idiv", right);
                    Writers.emitInstruction("mov", "edx", nextFreeTemp);
                    NasmTools.free(nextFreeTemp);
                } else {
                    Writers.emitInstruction("cdq");
                    Writers.emitInstruction("idiv", right);
                }
            } else {    /* left operand is not eax */
                /* edx needs to be fakely taken, so that getNextFreeTemp
                    would not save some register in edx, because it is needed
                    for remainder of division. */
                if (!NasmTools.isTakenRegisterEDX()) {
                    fake = true;
                    NasmTools.getNextFreeTemp();
                }
                if (NasmTools.isTakenRegisterEAX() && NasmTools.isTakenRegisterEDX()) {
                    s1 = NasmTools.getNextFreeTemp();
                    Writers.emitInstruction("mov", s1, "eax");  /* save eax value into s1 */
                 
                    s2 = NasmTools.getNextFreeTemp();
                    Writers.emitInstruction("mov", s2, "edx");  /* save edx value into s2 */
                    
                    Writers.emitInstruction("mov", "eax", left); /* setting eax and edx for div */
                    Writers.emitInstruction("cdq");
                    Writers.emitInstruction("idiv", right);
                    
                    Writers.emitInstruction("mov", left, "eax"); /* restoring values */
                    Writers.emitInstruction("mov", "eax", s1);
                    Writers.emitInstruction("mov", "edx", s2);
                    NasmTools.free(s2);
                    NasmTools.free(s1);
                }
                if (fake == true) {
                    NasmTools.free("fake!");                    
                }
            }
        }
        NasmTools.free(right); /* right is cleared anyway */
        return left;    /* left register is returned */
    }

    

    @Override
    public String visitId(picoCParser.IdContext ctx) 
    {
        System.out.println("Context: visitInt Val: " + ctx.getText());
        /* TODO: Determine displacement of variable ID */
        String val = ctx.ID().getText();
        String nextFreeTemp = NasmTools.getNextFreeTemp();
        Writers.emitInstruction("mov", nextFreeTemp, val);
        
        return nextFreeTemp;
    }
    
    
    

    @Override
    public String visitInt(picoCParser.IntContext ctx) 
    {
        System.out.println("Context: visitInt Val: " + ctx.getText());
        String val = ctx.INT().getText();
        String nextFreeTemp = NasmTools.getNextFreeTemp();
        Writers.emitInstruction("mov", nextFreeTemp, val);
        
        return nextFreeTemp;
    }

    @Override
    public String visitParens(picoCParser.ParensContext ctx) 
    {
        String s = visit(ctx.simpleExpression());
        return s;
    }
    
}
