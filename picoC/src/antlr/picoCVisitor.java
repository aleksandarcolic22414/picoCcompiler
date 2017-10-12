package antlr;

// Generated from /home/aleksandar/NetBeansProjects/Clone/picoCcompilerCloneRemote/picoC/grammar/picoC.g4 by ANTLR 4.6
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link picoCParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface picoCVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link picoCParser#compilationUnit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompilationUnit(picoCParser.CompilationUnitContext ctx);
	/**
	 * Visit a parse tree produced by {@link picoCParser#translationUnit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTranslationUnit(picoCParser.TranslationUnitContext ctx);
	/**
	 * Visit a parse tree produced by {@link picoCParser#externalDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExternalDeclaration(picoCParser.ExternalDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link picoCParser#declarationList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclarationList(picoCParser.DeclarationListContext ctx);
	/**
	 * Visit a parse tree produced by {@link picoCParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaration(picoCParser.DeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link picoCParser#functionDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionDefinition(picoCParser.FunctionDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link picoCParser#typeSpecifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeSpecifier(picoCParser.TypeSpecifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link picoCParser#parameterList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterList(picoCParser.ParameterListContext ctx);
	/**
	 * Visit a parse tree produced by {@link picoCParser#parameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameter(picoCParser.ParameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link picoCParser#functionBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionBody(picoCParser.FunctionBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link picoCParser#statements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatements(picoCParser.StatementsContext ctx);
	/**
	 * Visit a parse tree produced by {@link picoCParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(picoCParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link picoCParser#returnStat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnStat(picoCParser.ReturnStatContext ctx);
	/**
	 * Visit a parse tree produced by {@link picoCParser#functionCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionCall(picoCParser.FunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link picoCParser#functionName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionName(picoCParser.FunctionNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link picoCParser#argumentList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgumentList(picoCParser.ArgumentListContext ctx);
	/**
	 * Visit a parse tree produced by {@link picoCParser#argument}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgument(picoCParser.ArgumentContext ctx);
	/**
	 * Visit a parse tree produced by {@link picoCParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(picoCParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FuncCall}
	 * labeled alternative in {@link picoCParser#simpleExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncCall(picoCParser.FuncCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Negation}
	 * labeled alternative in {@link picoCParser#simpleExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNegation(picoCParser.NegationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MulDiv}
	 * labeled alternative in {@link picoCParser#simpleExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulDiv(picoCParser.MulDivContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AddSub}
	 * labeled alternative in {@link picoCParser#simpleExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddSub(picoCParser.AddSubContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Parens}
	 * labeled alternative in {@link picoCParser#simpleExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParens(picoCParser.ParensContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Assign}
	 * labeled alternative in {@link picoCParser#simpleExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssign(picoCParser.AssignContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Id}
	 * labeled alternative in {@link picoCParser#simpleExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitId(picoCParser.IdContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Int}
	 * labeled alternative in {@link picoCParser#simpleExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInt(picoCParser.IntContext ctx);
	/**
	 * Visit a parse tree produced by {@link picoCParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment(picoCParser.AssignmentContext ctx);
}