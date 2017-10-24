// Generated from /home/aleksandar/NetBeansProjects/Clone/picoCcompilerCloneRemote/picoC/grammar/picoC.g4 by ANTLR 4.6
package antlr;
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
	 * Visit a parse tree produced by {@link picoCParser#functionName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionName(picoCParser.FunctionNameContext ctx);
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
	 * Visit a parse tree produced by {@link picoCParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(picoCParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Return}
	 * labeled alternative in {@link picoCParser#jumpStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturn(picoCParser.ReturnContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Break}
	 * labeled alternative in {@link picoCParser#jumpStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBreak(picoCParser.BreakContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Continue}
	 * labeled alternative in {@link picoCParser#jumpStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContinue(picoCParser.ContinueContext ctx);
	/**
	 * Visit a parse tree produced by {@link picoCParser#compoundStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompoundStatement(picoCParser.CompoundStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link picoCParser#blockItemList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockItemList(picoCParser.BlockItemListContext ctx);
	/**
	 * Visit a parse tree produced by {@link picoCParser#blockItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockItem(picoCParser.BlockItemContext ctx);
	/**
	 * Visit a parse tree produced by {@link picoCParser#selectionStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectionStatement(picoCParser.SelectionStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link picoCParser#iterationStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIterationStatement(picoCParser.IterationStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link picoCParser#functionCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionCall(picoCParser.FunctionCallContext ctx);
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
	 * Visit a parse tree produced by the {@code Id}
	 * labeled alternative in {@link picoCParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitId(picoCParser.IdContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Int}
	 * labeled alternative in {@link picoCParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInt(picoCParser.IntContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FuncCall}
	 * labeled alternative in {@link picoCParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncCall(picoCParser.FuncCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Parens}
	 * labeled alternative in {@link picoCParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParens(picoCParser.ParensContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PostDec}
	 * labeled alternative in {@link picoCParser#postfixExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPostDec(picoCParser.PostDecContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PostInc}
	 * labeled alternative in {@link picoCParser#postfixExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPostInc(picoCParser.PostIncContext ctx);
	/**
	 * Visit a parse tree produced by the {@code DropPostfix}
	 * labeled alternative in {@link picoCParser#postfixExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDropPostfix(picoCParser.DropPostfixContext ctx);
	/**
	 * Visit a parse tree produced by the {@code DropUnary}
	 * labeled alternative in {@link picoCParser#unaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDropUnary(picoCParser.DropUnaryContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Negation}
	 * labeled alternative in {@link picoCParser#unaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNegation(picoCParser.NegationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Plus}
	 * labeled alternative in {@link picoCParser#unaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPlus(picoCParser.PlusContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PreInc}
	 * labeled alternative in {@link picoCParser#unaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPreInc(picoCParser.PreIncContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PreDec}
	 * labeled alternative in {@link picoCParser#unaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPreDec(picoCParser.PreDecContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MulDivMod}
	 * labeled alternative in {@link picoCParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulDivMod(picoCParser.MulDivModContext ctx);
	/**
	 * Visit a parse tree produced by the {@code DropMulDivMod}
	 * labeled alternative in {@link picoCParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDropMulDivMod(picoCParser.DropMulDivModContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AddSub}
	 * labeled alternative in {@link picoCParser#additiveExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddSub(picoCParser.AddSubContext ctx);
	/**
	 * Visit a parse tree produced by the {@code DropAddSub}
	 * labeled alternative in {@link picoCParser#additiveExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDropAddSub(picoCParser.DropAddSubContext ctx);
	/**
	 * Visit a parse tree produced by the {@code DropRelational}
	 * labeled alternative in {@link picoCParser#relationalExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDropRelational(picoCParser.DropRelationalContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Relation}
	 * labeled alternative in {@link picoCParser#relationalExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelation(picoCParser.RelationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code DropEquality}
	 * labeled alternative in {@link picoCParser#equalityExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDropEquality(picoCParser.DropEqualityContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Equality}
	 * labeled alternative in {@link picoCParser#equalityExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEquality(picoCParser.EqualityContext ctx);
	/**
	 * Visit a parse tree produced by the {@code LogicalAND}
	 * labeled alternative in {@link picoCParser#logicalAndExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalAND(picoCParser.LogicalANDContext ctx);
	/**
	 * Visit a parse tree produced by the {@code DropLogicalAND}
	 * labeled alternative in {@link picoCParser#logicalAndExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDropLogicalAND(picoCParser.DropLogicalANDContext ctx);
	/**
	 * Visit a parse tree produced by the {@code DropLogicalOR}
	 * labeled alternative in {@link picoCParser#logicalOrExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDropLogicalOR(picoCParser.DropLogicalORContext ctx);
	/**
	 * Visit a parse tree produced by the {@code LogicalOR}
	 * labeled alternative in {@link picoCParser#logicalOrExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalOR(picoCParser.LogicalORContext ctx);
	/**
	 * Visit a parse tree produced by the {@code DropAssign}
	 * labeled alternative in {@link picoCParser#assignmentExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDropAssign(picoCParser.DropAssignContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Assign}
	 * labeled alternative in {@link picoCParser#assignmentExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssign(picoCParser.AssignContext ctx);
	/**
	 * Visit a parse tree produced by {@link picoCParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(picoCParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link picoCParser#expressionStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionStatement(picoCParser.ExpressionStatementContext ctx);
}