// Generated from /home/aleksandar/NetBeansProjects/Clone/picoCcompiler/picoC/grammar/picoC.g4 by ANTLR 4.6
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
	 * Visit a parse tree produced by {@link picoCParser#includeList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIncludeList(picoCParser.IncludeListContext ctx);
	/**
	 * Visit a parse tree produced by {@link picoCParser#include}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInclude(picoCParser.IncludeContext ctx);
	/**
	 * Visit a parse tree produced by {@link picoCParser#headerFile}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHeaderFile(picoCParser.HeaderFileContext ctx);
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
	 * Visit a parse tree produced by {@link picoCParser#functionDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionDefinition(picoCParser.FunctionDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Id}
	 * labeled alternative in {@link picoCParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitId(picoCParser.IdContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Const}
	 * labeled alternative in {@link picoCParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConst(picoCParser.ConstContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Str}
	 * labeled alternative in {@link picoCParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStr(picoCParser.StrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Parens}
	 * labeled alternative in {@link picoCParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParens(picoCParser.ParensContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FuncCall}
	 * labeled alternative in {@link picoCParser#postfixExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncCall(picoCParser.FuncCallContext ctx);
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
	 * Visit a parse tree produced by the {@code Subscript}
	 * labeled alternative in {@link picoCParser#postfixExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubscript(picoCParser.SubscriptContext ctx);
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
	 * Visit a parse tree produced by the {@code Deref}
	 * labeled alternative in {@link picoCParser#unaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeref(picoCParser.DerefContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Negation}
	 * labeled alternative in {@link picoCParser#unaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNegation(picoCParser.NegationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Address}
	 * labeled alternative in {@link picoCParser#unaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddress(picoCParser.AddressContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Complement}
	 * labeled alternative in {@link picoCParser#unaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComplement(picoCParser.ComplementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Minus}
	 * labeled alternative in {@link picoCParser#unaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMinus(picoCParser.MinusContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Plus}
	 * labeled alternative in {@link picoCParser#unaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPlus(picoCParser.PlusContext ctx);
	/**
	 * Visit a parse tree produced by the {@code DropCast}
	 * labeled alternative in {@link picoCParser#castExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDropCast(picoCParser.DropCastContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Cast}
	 * labeled alternative in {@link picoCParser#castExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCast(picoCParser.CastContext ctx);
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
	 * Visit a parse tree produced by the {@code Shift}
	 * labeled alternative in {@link picoCParser#shiftExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShift(picoCParser.ShiftContext ctx);
	/**
	 * Visit a parse tree produced by the {@code DropShift}
	 * labeled alternative in {@link picoCParser#shiftExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDropShift(picoCParser.DropShiftContext ctx);
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
	 * Visit a parse tree produced by the {@code And}
	 * labeled alternative in {@link picoCParser#andExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnd(picoCParser.AndContext ctx);
	/**
	 * Visit a parse tree produced by the {@code DropAnd}
	 * labeled alternative in {@link picoCParser#andExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDropAnd(picoCParser.DropAndContext ctx);
	/**
	 * Visit a parse tree produced by the {@code DropExclusiveOr}
	 * labeled alternative in {@link picoCParser#exclusiveOrExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDropExclusiveOr(picoCParser.DropExclusiveOrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExclusiveOr}
	 * labeled alternative in {@link picoCParser#exclusiveOrExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExclusiveOr(picoCParser.ExclusiveOrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code DropInclusiveOr}
	 * labeled alternative in {@link picoCParser#inclusiveOrExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDropInclusiveOr(picoCParser.DropInclusiveOrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code InclusiveOr}
	 * labeled alternative in {@link picoCParser#inclusiveOrExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInclusiveOr(picoCParser.InclusiveOrContext ctx);
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
	 * Visit a parse tree produced by the {@code DropConditional}
	 * labeled alternative in {@link picoCParser#conditionalExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDropConditional(picoCParser.DropConditionalContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Conditional}
	 * labeled alternative in {@link picoCParser#conditionalExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConditional(picoCParser.ConditionalContext ctx);
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
	 * Visit a parse tree produced by {@link picoCParser#assignmentOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignmentOperator(picoCParser.AssignmentOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link picoCParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(picoCParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link picoCParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaration(picoCParser.DeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link picoCParser#typeSpecifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeSpecifier(picoCParser.TypeSpecifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link picoCParser#initDeclarationList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInitDeclarationList(picoCParser.InitDeclarationListContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Decl}
	 * labeled alternative in {@link picoCParser#initDeclarator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecl(picoCParser.DeclContext ctx);
	/**
	 * Visit a parse tree produced by the {@code DeclWithInit}
	 * labeled alternative in {@link picoCParser#initDeclarator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclWithInit(picoCParser.DeclWithInitContext ctx);
	/**
	 * Visit a parse tree produced by {@link picoCParser#declarator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclarator(picoCParser.DeclaratorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MultiplePrt}
	 * labeled alternative in {@link picoCParser#pointer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplePrt(picoCParser.MultiplePrtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SimplePtr}
	 * labeled alternative in {@link picoCParser#pointer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimplePtr(picoCParser.SimplePtrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code DirDecl}
	 * labeled alternative in {@link picoCParser#directDeclarator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDirDecl(picoCParser.DirDeclContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ArrayDecl}
	 * labeled alternative in {@link picoCParser#directDeclarator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayDecl(picoCParser.ArrayDeclContext ctx);
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
	 * Visit a parse tree produced by {@link picoCParser#expressionStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionStatement(picoCParser.ExpressionStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link picoCParser#selectionStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectionStatement(picoCParser.SelectionStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ForLoop}
	 * labeled alternative in {@link picoCParser#iterationStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForLoop(picoCParser.ForLoopContext ctx);
	/**
	 * Visit a parse tree produced by the {@code WhileLoop}
	 * labeled alternative in {@link picoCParser#iterationStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileLoop(picoCParser.WhileLoopContext ctx);
	/**
	 * Visit a parse tree produced by the {@code DoWhileLoop}
	 * labeled alternative in {@link picoCParser#iterationStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDoWhileLoop(picoCParser.DoWhileLoopContext ctx);
	/**
	 * Visit a parse tree produced by {@link picoCParser#forInit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForInit(picoCParser.ForInitContext ctx);
	/**
	 * Visit a parse tree produced by {@link picoCParser#forCheck}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForCheck(picoCParser.ForCheckContext ctx);
	/**
	 * Visit a parse tree produced by {@link picoCParser#forInc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForInc(picoCParser.ForIncContext ctx);
	/**
	 * Visit a parse tree produced by {@link picoCParser#whileCheck}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileCheck(picoCParser.WhileCheckContext ctx);
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
	 * Visit a parse tree produced by the {@code Int}
	 * labeled alternative in {@link picoCParser#constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInt(picoCParser.IntContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Char}
	 * labeled alternative in {@link picoCParser#constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChar(picoCParser.CharContext ctx);
}