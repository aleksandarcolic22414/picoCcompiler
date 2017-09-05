
import java.util.List;

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
    public String visitAdd(picoCParser.AddContext ctx) {
        System.out.println("Context: visitAdd Val: " + ctx.getText());
        visit(ctx.simpleExpression(0));
        visit(ctx.simpleExpression(1));
        return null;
    }

    @Override
    public String visitSub(picoCParser.SubContext ctx) {
        System.out.println("Context: visitSub Val: " + ctx.getText());
        visit(ctx.simpleExpression(0));
        visit(ctx.simpleExpression(1));
        return null;
    }
    
    @Override
    public String visitMul(picoCParser.MulContext ctx) {
        System.out.println("Context: visitMul Val: " + ctx.getText());
        visit(ctx.simpleExpression(0));
        visit(ctx.simpleExpression(1));
        return null;
    }

    @Override
    public String visitDiv(picoCParser.DivContext ctx) {
        System.out.println("Context: visitDiv Val: " + ctx.getText());
        visit(ctx.simpleExpression(0));
        visit(ctx.simpleExpression(1));
        return null;
    }

    @Override
    public String visitId(picoCParser.IdContext ctx) {
        System.out.println("Context: visitID Val: " + ctx.getText());
        return null;
    }
    
    
    

    @Override
    public String visitInt(picoCParser.IntContext ctx) {
        System.out.println("Context: visitInt Val: " + ctx.getText());
        return null;
    }

    @Override
    public String visitParens(picoCParser.ParensContext ctx) {
        visit(ctx.simpleExpression());
        return null;
    }
    
    
    
    
    
    
    
}
