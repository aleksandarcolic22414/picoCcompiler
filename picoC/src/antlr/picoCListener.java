// Generated from /home/aleksandar/NetBeansProjects/Clone/picoCcompilerCloneRemote/picoC/grammar/picoC.g4 by ANTLR 4.6
package antlr;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link picoCParser}.
 */
public interface picoCListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link picoCParser#compilationUnit}.
	 * @param ctx the parse tree
	 */
	void enterCompilationUnit(picoCParser.CompilationUnitContext ctx);
	/**
	 * Exit a parse tree produced by {@link picoCParser#compilationUnit}.
	 * @param ctx the parse tree
	 */
	void exitCompilationUnit(picoCParser.CompilationUnitContext ctx);
	/**
	 * Enter a parse tree produced by {@link picoCParser#translationUnit}.
	 * @param ctx the parse tree
	 */
	void enterTranslationUnit(picoCParser.TranslationUnitContext ctx);
	/**
	 * Exit a parse tree produced by {@link picoCParser#translationUnit}.
	 * @param ctx the parse tree
	 */
	void exitTranslationUnit(picoCParser.TranslationUnitContext ctx);
	/**
	 * Enter a parse tree produced by {@link picoCParser#externalDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterExternalDeclaration(picoCParser.ExternalDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link picoCParser#externalDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitExternalDeclaration(picoCParser.ExternalDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link picoCParser#declarationList}.
	 * @param ctx the parse tree
	 */
	void enterDeclarationList(picoCParser.DeclarationListContext ctx);
	/**
	 * Exit a parse tree produced by {@link picoCParser#declarationList}.
	 * @param ctx the parse tree
	 */
	void exitDeclarationList(picoCParser.DeclarationListContext ctx);
	/**
	 * Enter a parse tree produced by {@link picoCParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterDeclaration(picoCParser.DeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link picoCParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitDeclaration(picoCParser.DeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link picoCParser#functionDefinition}.
	 * @param ctx the parse tree
	 */
	void enterFunctionDefinition(picoCParser.FunctionDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link picoCParser#functionDefinition}.
	 * @param ctx the parse tree
	 */
	void exitFunctionDefinition(picoCParser.FunctionDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link picoCParser#typeSpecifier}.
	 * @param ctx the parse tree
	 */
	void enterTypeSpecifier(picoCParser.TypeSpecifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link picoCParser#typeSpecifier}.
	 * @param ctx the parse tree
	 */
	void exitTypeSpecifier(picoCParser.TypeSpecifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link picoCParser#parameterList}.
	 * @param ctx the parse tree
	 */
	void enterParameterList(picoCParser.ParameterListContext ctx);
	/**
	 * Exit a parse tree produced by {@link picoCParser#parameterList}.
	 * @param ctx the parse tree
	 */
	void exitParameterList(picoCParser.ParameterListContext ctx);
	/**
	 * Enter a parse tree produced by {@link picoCParser#parameter}.
	 * @param ctx the parse tree
	 */
	void enterParameter(picoCParser.ParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link picoCParser#parameter}.
	 * @param ctx the parse tree
	 */
	void exitParameter(picoCParser.ParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link picoCParser#functionBody}.
	 * @param ctx the parse tree
	 */
	void enterFunctionBody(picoCParser.FunctionBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link picoCParser#functionBody}.
	 * @param ctx the parse tree
	 */
	void exitFunctionBody(picoCParser.FunctionBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link picoCParser#statements}.
	 * @param ctx the parse tree
	 */
	void enterStatements(picoCParser.StatementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link picoCParser#statements}.
	 * @param ctx the parse tree
	 */
	void exitStatements(picoCParser.StatementsContext ctx);
	/**
	 * Enter a parse tree produced by {@link picoCParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(picoCParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link picoCParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(picoCParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link picoCParser#returnStat}.
	 * @param ctx the parse tree
	 */
	void enterReturnStat(picoCParser.ReturnStatContext ctx);
	/**
	 * Exit a parse tree produced by {@link picoCParser#returnStat}.
	 * @param ctx the parse tree
	 */
	void exitReturnStat(picoCParser.ReturnStatContext ctx);
	/**
	 * Enter a parse tree produced by {@link picoCParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCall(picoCParser.FunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link picoCParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCall(picoCParser.FunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link picoCParser#functionName}.
	 * @param ctx the parse tree
	 */
	void enterFunctionName(picoCParser.FunctionNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link picoCParser#functionName}.
	 * @param ctx the parse tree
	 */
	void exitFunctionName(picoCParser.FunctionNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link picoCParser#argumentList}.
	 * @param ctx the parse tree
	 */
	void enterArgumentList(picoCParser.ArgumentListContext ctx);
	/**
	 * Exit a parse tree produced by {@link picoCParser#argumentList}.
	 * @param ctx the parse tree
	 */
	void exitArgumentList(picoCParser.ArgumentListContext ctx);
	/**
	 * Enter a parse tree produced by {@link picoCParser#argument}.
	 * @param ctx the parse tree
	 */
	void enterArgument(picoCParser.ArgumentContext ctx);
	/**
	 * Exit a parse tree produced by {@link picoCParser#argument}.
	 * @param ctx the parse tree
	 */
	void exitArgument(picoCParser.ArgumentContext ctx);
	/**
	 * Enter a parse tree produced by {@link picoCParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(picoCParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link picoCParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(picoCParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FuncCall}
	 * labeled alternative in {@link picoCParser#simpleExpression}.
	 * @param ctx the parse tree
	 */
	void enterFuncCall(picoCParser.FuncCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FuncCall}
	 * labeled alternative in {@link picoCParser#simpleExpression}.
	 * @param ctx the parse tree
	 */
	void exitFuncCall(picoCParser.FuncCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Negation}
	 * labeled alternative in {@link picoCParser#simpleExpression}.
	 * @param ctx the parse tree
	 */
	void enterNegation(picoCParser.NegationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Negation}
	 * labeled alternative in {@link picoCParser#simpleExpression}.
	 * @param ctx the parse tree
	 */
	void exitNegation(picoCParser.NegationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MulDiv}
	 * labeled alternative in {@link picoCParser#simpleExpression}.
	 * @param ctx the parse tree
	 */
	void enterMulDiv(picoCParser.MulDivContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MulDiv}
	 * labeled alternative in {@link picoCParser#simpleExpression}.
	 * @param ctx the parse tree
	 */
	void exitMulDiv(picoCParser.MulDivContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AddSub}
	 * labeled alternative in {@link picoCParser#simpleExpression}.
	 * @param ctx the parse tree
	 */
	void enterAddSub(picoCParser.AddSubContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AddSub}
	 * labeled alternative in {@link picoCParser#simpleExpression}.
	 * @param ctx the parse tree
	 */
	void exitAddSub(picoCParser.AddSubContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Parens}
	 * labeled alternative in {@link picoCParser#simpleExpression}.
	 * @param ctx the parse tree
	 */
	void enterParens(picoCParser.ParensContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Parens}
	 * labeled alternative in {@link picoCParser#simpleExpression}.
	 * @param ctx the parse tree
	 */
	void exitParens(picoCParser.ParensContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Assign}
	 * labeled alternative in {@link picoCParser#simpleExpression}.
	 * @param ctx the parse tree
	 */
	void enterAssign(picoCParser.AssignContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Assign}
	 * labeled alternative in {@link picoCParser#simpleExpression}.
	 * @param ctx the parse tree
	 */
	void exitAssign(picoCParser.AssignContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Id}
	 * labeled alternative in {@link picoCParser#simpleExpression}.
	 * @param ctx the parse tree
	 */
	void enterId(picoCParser.IdContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Id}
	 * labeled alternative in {@link picoCParser#simpleExpression}.
	 * @param ctx the parse tree
	 */
	void exitId(picoCParser.IdContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Int}
	 * labeled alternative in {@link picoCParser#simpleExpression}.
	 * @param ctx the parse tree
	 */
	void enterInt(picoCParser.IntContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Int}
	 * labeled alternative in {@link picoCParser#simpleExpression}.
	 * @param ctx the parse tree
	 */
	void exitInt(picoCParser.IntContext ctx);
	/**
	 * Enter a parse tree produced by the {@code simple}
	 * labeled alternative in {@link picoCParser#relationalExpression}.
	 * @param ctx the parse tree
	 */
	void enterSimple(picoCParser.SimpleContext ctx);
	/**
	 * Exit a parse tree produced by the {@code simple}
	 * labeled alternative in {@link picoCParser#relationalExpression}.
	 * @param ctx the parse tree
	 */
	void exitSimple(picoCParser.SimpleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code equality}
	 * labeled alternative in {@link picoCParser#relationalExpression}.
	 * @param ctx the parse tree
	 */
	void enterEquality(picoCParser.EqualityContext ctx);
	/**
	 * Exit a parse tree produced by the {@code equality}
	 * labeled alternative in {@link picoCParser#relationalExpression}.
	 * @param ctx the parse tree
	 */
	void exitEquality(picoCParser.EqualityContext ctx);
	/**
	 * Enter a parse tree produced by the {@code relation}
	 * labeled alternative in {@link picoCParser#relationalExpression}.
	 * @param ctx the parse tree
	 */
	void enterRelation(picoCParser.RelationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code relation}
	 * labeled alternative in {@link picoCParser#relationalExpression}.
	 * @param ctx the parse tree
	 */
	void exitRelation(picoCParser.RelationContext ctx);
	/**
	 * Enter a parse tree produced by {@link picoCParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(picoCParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link picoCParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(picoCParser.AssignmentContext ctx);
}