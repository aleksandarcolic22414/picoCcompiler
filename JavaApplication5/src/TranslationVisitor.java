
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
public class TranslationVisitor extends picoCBaseVisitor<Integer> 
{

    @Override
    public Integer visitCompilationUnit(picoCParser.CompilationUnitContext ctx) 
    {
        visit(ctx.main());
        return 0;
    }

    @Override
    public Integer visitMain(picoCParser.MainContext ctx) 
    {
        Writers.emitText("\tglobal main");
        Writers.emitText("main:");
        Writers.emitInstruction(Constants.FUNCTION_ENTRY);
        visit(ctx.functionBody());
        Writers.emitInstruction(Constants.FUNCTION_EXIT);
        return 0;
    }

    @Override
    public Integer visitFunctionBody(picoCParser.FunctionBodyContext ctx) 
    {
        visit(ctx.statements());
        return 0;
    }

    /* Not well implemented yet. :) */
    @Override
    public Integer visitStatements(picoCParser.StatementsContext ctx) 
    {
        List<picoCParser.StatementContext> statementList = ctx.statement();
        for (picoCParser.StatementContext statement : statementList) {
            statement.declaration();
        }
        visit(ctx.statement(0));
        return 0;
    }

    
    
    @Override
    public Integer visitStatement(picoCParser.StatementContext ctx) 
    {
        return super.visitStatement(ctx); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    @Override
    public Integer visitInitialization(picoCParser.InitializationContext ctx) 
    {
        return 0;
    }

    @Override
    public Integer visitReturnStat(picoCParser.ReturnStatContext ctx) 
    {
        return 0;
    }
    
    
    
    
    
}
