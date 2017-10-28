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
	 * Enter a parse tree produced by the {@code Return}
	 * labeled alternative in {@link picoCParser#jumpStatement}.
	 * @param ctx the parse tree
	 */
	void enterReturn(picoCParser.ReturnContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Return}
	 * labeled alternative in {@link picoCParser#jumpStatement}.
	 * @param ctx the parse tree
	 */
	void exitReturn(picoCParser.ReturnContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Break}
	 * labeled alternative in {@link picoCParser#jumpStatement}.
	 * @param ctx the parse tree
	 */
	void enterBreak(picoCParser.BreakContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Break}
	 * labeled alternative in {@link picoCParser#jumpStatement}.
	 * @param ctx the parse tree
	 */
	void exitBreak(picoCParser.BreakContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Continue}
	 * labeled alternative in {@link picoCParser#jumpStatement}.
	 * @param ctx the parse tree
	 */
	void enterContinue(picoCParser.ContinueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Continue}
	 * labeled alternative in {@link picoCParser#jumpStatement}.
	 * @param ctx the parse tree
	 */
	void exitContinue(picoCParser.ContinueContext ctx);
	/**
	 * Enter a parse tree produced by {@link picoCParser#compoundStatement}.
	 * @param ctx the parse tree
	 */
	void enterCompoundStatement(picoCParser.CompoundStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link picoCParser#compoundStatement}.
	 * @param ctx the parse tree
	 */
	void exitCompoundStatement(picoCParser.CompoundStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link picoCParser#blockItemList}.
	 * @param ctx the parse tree
	 */
	void enterBlockItemList(picoCParser.BlockItemListContext ctx);
	/**
	 * Exit a parse tree produced by {@link picoCParser#blockItemList}.
	 * @param ctx the parse tree
	 */
	void exitBlockItemList(picoCParser.BlockItemListContext ctx);
	/**
	 * Enter a parse tree produced by {@link picoCParser#blockItem}.
	 * @param ctx the parse tree
	 */
	void enterBlockItem(picoCParser.BlockItemContext ctx);
	/**
	 * Exit a parse tree produced by {@link picoCParser#blockItem}.
	 * @param ctx the parse tree
	 */
	void exitBlockItem(picoCParser.BlockItemContext ctx);
	/**
	 * Enter a parse tree produced by {@link picoCParser#selectionStatement}.
	 * @param ctx the parse tree
	 */
	void enterSelectionStatement(picoCParser.SelectionStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link picoCParser#selectionStatement}.
	 * @param ctx the parse tree
	 */
	void exitSelectionStatement(picoCParser.SelectionStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link picoCParser#iterationStatement}.
	 * @param ctx the parse tree
	 */
	void enterIterationStatement(picoCParser.IterationStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link picoCParser#iterationStatement}.
	 * @param ctx the parse tree
	 */
	void exitIterationStatement(picoCParser.IterationStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Id}
	 * labeled alternative in {@link picoCParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterId(picoCParser.IdContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Id}
	 * labeled alternative in {@link picoCParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitId(picoCParser.IdContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Int}
	 * labeled alternative in {@link picoCParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterInt(picoCParser.IntContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Int}
	 * labeled alternative in {@link picoCParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitInt(picoCParser.IntContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Parens}
	 * labeled alternative in {@link picoCParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterParens(picoCParser.ParensContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Parens}
	 * labeled alternative in {@link picoCParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitParens(picoCParser.ParensContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FuncCall}
	 * labeled alternative in {@link picoCParser#postfixExpression}.
	 * @param ctx the parse tree
	 */
	void enterFuncCall(picoCParser.FuncCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FuncCall}
	 * labeled alternative in {@link picoCParser#postfixExpression}.
	 * @param ctx the parse tree
	 */
	void exitFuncCall(picoCParser.FuncCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PostDec}
	 * labeled alternative in {@link picoCParser#postfixExpression}.
	 * @param ctx the parse tree
	 */
	void enterPostDec(picoCParser.PostDecContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PostDec}
	 * labeled alternative in {@link picoCParser#postfixExpression}.
	 * @param ctx the parse tree
	 */
	void exitPostDec(picoCParser.PostDecContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PostInc}
	 * labeled alternative in {@link picoCParser#postfixExpression}.
	 * @param ctx the parse tree
	 */
	void enterPostInc(picoCParser.PostIncContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PostInc}
	 * labeled alternative in {@link picoCParser#postfixExpression}.
	 * @param ctx the parse tree
	 */
	void exitPostInc(picoCParser.PostIncContext ctx);
	/**
	 * Enter a parse tree produced by the {@code DropPostfix}
	 * labeled alternative in {@link picoCParser#postfixExpression}.
	 * @param ctx the parse tree
	 */
	void enterDropPostfix(picoCParser.DropPostfixContext ctx);
	/**
	 * Exit a parse tree produced by the {@code DropPostfix}
	 * labeled alternative in {@link picoCParser#postfixExpression}.
	 * @param ctx the parse tree
	 */
	void exitDropPostfix(picoCParser.DropPostfixContext ctx);
	/**
	 * Enter a parse tree produced by the {@code DropUnary}
	 * labeled alternative in {@link picoCParser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterDropUnary(picoCParser.DropUnaryContext ctx);
	/**
	 * Exit a parse tree produced by the {@code DropUnary}
	 * labeled alternative in {@link picoCParser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitDropUnary(picoCParser.DropUnaryContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Minus}
	 * labeled alternative in {@link picoCParser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterMinus(picoCParser.MinusContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Minus}
	 * labeled alternative in {@link picoCParser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitMinus(picoCParser.MinusContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Plus}
	 * labeled alternative in {@link picoCParser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterPlus(picoCParser.PlusContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Plus}
	 * labeled alternative in {@link picoCParser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitPlus(picoCParser.PlusContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PreInc}
	 * labeled alternative in {@link picoCParser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterPreInc(picoCParser.PreIncContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PreInc}
	 * labeled alternative in {@link picoCParser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitPreInc(picoCParser.PreIncContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PreDec}
	 * labeled alternative in {@link picoCParser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterPreDec(picoCParser.PreDecContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PreDec}
	 * labeled alternative in {@link picoCParser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitPreDec(picoCParser.PreDecContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Negation}
	 * labeled alternative in {@link picoCParser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterNegation(picoCParser.NegationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Negation}
	 * labeled alternative in {@link picoCParser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitNegation(picoCParser.NegationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MulDivMod}
	 * labeled alternative in {@link picoCParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 */
	void enterMulDivMod(picoCParser.MulDivModContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MulDivMod}
	 * labeled alternative in {@link picoCParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 */
	void exitMulDivMod(picoCParser.MulDivModContext ctx);
	/**
	 * Enter a parse tree produced by the {@code DropMulDivMod}
	 * labeled alternative in {@link picoCParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 */
	void enterDropMulDivMod(picoCParser.DropMulDivModContext ctx);
	/**
	 * Exit a parse tree produced by the {@code DropMulDivMod}
	 * labeled alternative in {@link picoCParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 */
	void exitDropMulDivMod(picoCParser.DropMulDivModContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AddSub}
	 * labeled alternative in {@link picoCParser#additiveExpression}.
	 * @param ctx the parse tree
	 */
	void enterAddSub(picoCParser.AddSubContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AddSub}
	 * labeled alternative in {@link picoCParser#additiveExpression}.
	 * @param ctx the parse tree
	 */
	void exitAddSub(picoCParser.AddSubContext ctx);
	/**
	 * Enter a parse tree produced by the {@code DropAddSub}
	 * labeled alternative in {@link picoCParser#additiveExpression}.
	 * @param ctx the parse tree
	 */
	void enterDropAddSub(picoCParser.DropAddSubContext ctx);
	/**
	 * Exit a parse tree produced by the {@code DropAddSub}
	 * labeled alternative in {@link picoCParser#additiveExpression}.
	 * @param ctx the parse tree
	 */
	void exitDropAddSub(picoCParser.DropAddSubContext ctx);
	/**
	 * Enter a parse tree produced by the {@code DropRelational}
	 * labeled alternative in {@link picoCParser#relationalExpression}.
	 * @param ctx the parse tree
	 */
	void enterDropRelational(picoCParser.DropRelationalContext ctx);
	/**
	 * Exit a parse tree produced by the {@code DropRelational}
	 * labeled alternative in {@link picoCParser#relationalExpression}.
	 * @param ctx the parse tree
	 */
	void exitDropRelational(picoCParser.DropRelationalContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Relation}
	 * labeled alternative in {@link picoCParser#relationalExpression}.
	 * @param ctx the parse tree
	 */
	void enterRelation(picoCParser.RelationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Relation}
	 * labeled alternative in {@link picoCParser#relationalExpression}.
	 * @param ctx the parse tree
	 */
	void exitRelation(picoCParser.RelationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code DropEquality}
	 * labeled alternative in {@link picoCParser#equalityExpression}.
	 * @param ctx the parse tree
	 */
	void enterDropEquality(picoCParser.DropEqualityContext ctx);
	/**
	 * Exit a parse tree produced by the {@code DropEquality}
	 * labeled alternative in {@link picoCParser#equalityExpression}.
	 * @param ctx the parse tree
	 */
	void exitDropEquality(picoCParser.DropEqualityContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Equality}
	 * labeled alternative in {@link picoCParser#equalityExpression}.
	 * @param ctx the parse tree
	 */
	void enterEquality(picoCParser.EqualityContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Equality}
	 * labeled alternative in {@link picoCParser#equalityExpression}.
	 * @param ctx the parse tree
	 */
	void exitEquality(picoCParser.EqualityContext ctx);
	/**
	 * Enter a parse tree produced by the {@code LogicalAND}
	 * labeled alternative in {@link picoCParser#logicalAndExpression}.
	 * @param ctx the parse tree
	 */
	void enterLogicalAND(picoCParser.LogicalANDContext ctx);
	/**
	 * Exit a parse tree produced by the {@code LogicalAND}
	 * labeled alternative in {@link picoCParser#logicalAndExpression}.
	 * @param ctx the parse tree
	 */
	void exitLogicalAND(picoCParser.LogicalANDContext ctx);
	/**
	 * Enter a parse tree produced by the {@code DropLogicalAND}
	 * labeled alternative in {@link picoCParser#logicalAndExpression}.
	 * @param ctx the parse tree
	 */
	void enterDropLogicalAND(picoCParser.DropLogicalANDContext ctx);
	/**
	 * Exit a parse tree produced by the {@code DropLogicalAND}
	 * labeled alternative in {@link picoCParser#logicalAndExpression}.
	 * @param ctx the parse tree
	 */
	void exitDropLogicalAND(picoCParser.DropLogicalANDContext ctx);
	/**
	 * Enter a parse tree produced by the {@code DropLogicalOR}
	 * labeled alternative in {@link picoCParser#logicalOrExpression}.
	 * @param ctx the parse tree
	 */
	void enterDropLogicalOR(picoCParser.DropLogicalORContext ctx);
	/**
	 * Exit a parse tree produced by the {@code DropLogicalOR}
	 * labeled alternative in {@link picoCParser#logicalOrExpression}.
	 * @param ctx the parse tree
	 */
	void exitDropLogicalOR(picoCParser.DropLogicalORContext ctx);
	/**
	 * Enter a parse tree produced by the {@code LogicalOR}
	 * labeled alternative in {@link picoCParser#logicalOrExpression}.
	 * @param ctx the parse tree
	 */
	void enterLogicalOR(picoCParser.LogicalORContext ctx);
	/**
	 * Exit a parse tree produced by the {@code LogicalOR}
	 * labeled alternative in {@link picoCParser#logicalOrExpression}.
	 * @param ctx the parse tree
	 */
	void exitLogicalOR(picoCParser.LogicalORContext ctx);
	/**
	 * Enter a parse tree produced by the {@code DropConditional}
	 * labeled alternative in {@link picoCParser#conditionalExpression}.
	 * @param ctx the parse tree
	 */
	void enterDropConditional(picoCParser.DropConditionalContext ctx);
	/**
	 * Exit a parse tree produced by the {@code DropConditional}
	 * labeled alternative in {@link picoCParser#conditionalExpression}.
	 * @param ctx the parse tree
	 */
	void exitDropConditional(picoCParser.DropConditionalContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Conditional}
	 * labeled alternative in {@link picoCParser#conditionalExpression}.
	 * @param ctx the parse tree
	 */
	void enterConditional(picoCParser.ConditionalContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Conditional}
	 * labeled alternative in {@link picoCParser#conditionalExpression}.
	 * @param ctx the parse tree
	 */
	void exitConditional(picoCParser.ConditionalContext ctx);
	/**
	 * Enter a parse tree produced by the {@code DropAssign}
	 * labeled alternative in {@link picoCParser#assignmentExpression}.
	 * @param ctx the parse tree
	 */
	void enterDropAssign(picoCParser.DropAssignContext ctx);
	/**
	 * Exit a parse tree produced by the {@code DropAssign}
	 * labeled alternative in {@link picoCParser#assignmentExpression}.
	 * @param ctx the parse tree
	 */
	void exitDropAssign(picoCParser.DropAssignContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Assign}
	 * labeled alternative in {@link picoCParser#assignmentExpression}.
	 * @param ctx the parse tree
	 */
	void enterAssign(picoCParser.AssignContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Assign}
	 * labeled alternative in {@link picoCParser#assignmentExpression}.
	 * @param ctx the parse tree
	 */
	void exitAssign(picoCParser.AssignContext ctx);
	/**
	 * Enter a parse tree produced by {@link picoCParser#assignmentOperator}.
	 * @param ctx the parse tree
	 */
	void enterAssignmentOperator(picoCParser.AssignmentOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link picoCParser#assignmentOperator}.
	 * @param ctx the parse tree
	 */
	void exitAssignmentOperator(picoCParser.AssignmentOperatorContext ctx);
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
	 * Enter a parse tree produced by {@link picoCParser#expressionStatement}.
	 * @param ctx the parse tree
	 */
	void enterExpressionStatement(picoCParser.ExpressionStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link picoCParser#expressionStatement}.
	 * @param ctx the parse tree
	 */
	void exitExpressionStatement(picoCParser.ExpressionStatementContext ctx);
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
}