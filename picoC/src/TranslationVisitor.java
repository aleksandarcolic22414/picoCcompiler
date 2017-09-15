/**
 *
 * @author aleksandar
 */
public class TranslationVisitor extends picoCBaseVisitor<String> 
{

    @Override
    public String visitExpression(picoCParser.ExpressionContext ctx) {
        /* Writers.emitInstruction("xor", "eax", "eax");
            doesn't need to be done, because for expression
            the first instruction is  always "mov", and eax register shoud be saved
            before enterance if it hold some significant value. */
        return visit(ctx.simpleExpression());
    }

    @Override
    public String visitAddSub(picoCParser.AddSubContext ctx) 
    {
        String leftExpr = visit(ctx.simpleExpression(0));
        String rightExpr = visit(ctx.simpleExpression(1));
        String nextFreeTemp;
        String operation = NasmTools.getOperation(ctx.op.getType());
        
        /* If left operand is not register, then it needs to be moved to one.
            It's moved to eax, but first eax is saved on stack. */
        if (!NasmTools.isRegister(leftExpr)) {
            /* Testing nextFreeTemp2 */
            nextFreeTemp = NasmTools.getNextFreeTemp();
            Writers.emitInstruction("mov", nextFreeTemp, "eax");
            Writers.emitInstruction("mov", "eax", leftExpr);
            Writers.emitInstruction(operation, "eax", rightExpr);
            Writers.emitInstruction("mov", leftExpr, "eax");
            Writers.emitInstruction("mov", "eax", nextFreeTemp);
            /* Testing free */
            NasmTools.free(nextFreeTemp);
        } else
            Writers.emitInstruction(operation, leftExpr, rightExpr);
        /* Testing free */
        NasmTools.free(rightExpr);
        return leftExpr;
    }

    @Override
    public String visitMulDiv(picoCParser.MulDivContext ctx) 
    {
        boolean fake = false;
        String leftExpr = visit(ctx.simpleExpression(0));
        String rightExpr = visit(ctx.simpleExpression(1));
        String nextFreeTemp;
        String s1, s2;
        String fakelyTaken = null;
        if (ctx.op.getType() == picoCParser.MUL) {
            /* Chech wheather leftExpr is register. If it's not then it needs to be
                moved to one and then multiplied */
            if (!NasmTools.isRegister(leftExpr)) {
                /* Testing getNextFreeTemp */
                nextFreeTemp = NasmTools.getNextFreeTemp();
                Writers.emitInstruction("mov", nextFreeTemp, "eax");
                Writers.emitInstruction("mov", "eax", leftExpr);
                Writers.emitInstruction("imul", "eax", rightExpr);
                Writers.emitInstruction("mov", leftExpr, "eax");
                Writers.emitInstruction("mov", "eax", nextFreeTemp);
                /* Testing free */
                NasmTools.free(nextFreeTemp);
            } else
                Writers.emitInstruction("imul", leftExpr, rightExpr);
        } else if (ctx.op.getType() == picoCParser.DIV) {
            if (leftExpr.equals("eax")) {
                if (NasmTools.isTakenRegisterEDX()) { /* Never true, but stil */
                    /* Testing getNextFreeTemp */
                    nextFreeTemp = NasmTools.getNextFreeTemp();
                    Writers.emitInstruction("mov", nextFreeTemp, "edx");
                    Writers.emitInstruction("cdq");
                    Writers.emitInstruction("idiv", rightExpr);
                    Writers.emitInstruction("mov", "edx", nextFreeTemp);
                    /* Testing free */
                    NasmTools.free(nextFreeTemp);
                } else {
                    Writers.emitInstruction("cdq");
                    Writers.emitInstruction("idiv", rightExpr);
                }
            } else {    /* leftExpr operand is not eax */
                /* edx needs to be fakely taken, so that getNextFreeTemp3
                    would not save some register in edx, because it is needed
                    for remainder of division. */
                
                /* Testing isTakenRegisterEDX */
                if (!NasmTools.isTakenRegisterEDX()) {
                    fake = true;
                /* Testing getNextFreeTemp */
                    fakelyTaken = NasmTools.getNextFreeTemp();
                }
                /* Always true -> */
                
                /* Testing isTakenRegisterEAX && EDX2 */
                if (NasmTools.isTakenRegisterEAX() && NasmTools.isTakenRegisterEDX()) {
                    /* Testing getNextFreeTemp */
                    s1 = NasmTools.getNextFreeTemp();
                    Writers.emitInstruction("mov", s1, "eax");  /* save eax value into s1 */
                    /* Testing getNextFreeTemp */
                    s2 = NasmTools.getNextFreeTemp();
                    Writers.emitInstruction("mov", s2, "edx");  /* save edx value into s2 */
                    
                    
                    Writers.emitInstruction("mov", "eax", leftExpr); /* setting eax and edx for div */
                    Writers.emitInstruction("cdq");
                    
                    /* If right operand od division is edx, than eax needs
                        to be divided by moved edx, witch is s2 */
                    if (!rightExpr.equals("edx"))
                        Writers.emitInstruction("idiv", rightExpr);
                    else
                        Writers.emitInstruction("idiv", s2);
                    
                    Writers.emitInstruction("mov", leftExpr, "eax"); /* restoring values */
                    Writers.emitInstruction("mov", "eax", s1);
                    Writers.emitInstruction("mov", "edx", s2);
                    /* Testing free */
                    NasmTools.free(s2);
                    NasmTools.free(s1);
                }
                /* Testing free */
                if (fake == true) {
                    NasmTools.free(fakelyTaken);                    
                }
            }
        }
        /* Testing free */
        NasmTools.free(rightExpr); /* rightExpr is cleared anyway */
        return leftExpr;    /* leftExpr register is returned */
    }

    

    @Override
    public String visitId(picoCParser.IdContext ctx) 
    {
        System.out.println("Context: visitInt Val: " + ctx.getText());
        /* TODO: Determine displacement of variable ID */
        
        
        return null;
    }
    
    @Override
    public String visitInt(picoCParser.IntContext ctx) 
    {
        System.out.println("Context: visitInt Val: " + ctx.getText());
        String val = ctx.INT().getText();
        /* Testing getNextFreeTemp */
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
