// Generated from /home/aleksandar/NetBeansProjects/Clone/picoCcompilerCloneRemote/picoC/grammar/picoC.g4 by ANTLR 4.6
package antlr;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class picoCParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.6", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, VOIDTYPE=26, INTTYPE=27, CHARTYPE=28, ASSIGN=29, ASSIGN_ADD=30, 
		ASSIGN_SUB=31, ASSIGN_MUL=32, ASSIGN_DIV=33, ASSIGN_MOD=34, MUL=35, DIV=36, 
		MOD=37, ADD=38, SUB=39, EQUAL=40, NOT_EQUAL=41, LESS=42, LESS_EQUAL=43, 
		GREATER=44, GREATER_EQUAL=45, LOGICAL_AND=46, LOGICAL_OR=47, ID=48, INT=49, 
		CHAR=50, STRING_LITERAL=51, MULTY_LINE_COMMENT=52, SINGLE_LINE_COMMENT=53, 
		WHITE_SPACE=54;
	public static final int
		RULE_compilationUnit = 0, RULE_translationUnit = 1, RULE_externalDeclaration = 2, 
		RULE_functionDefinition = 3, RULE_primaryExpression = 4, RULE_postfixExpression = 5, 
		RULE_unaryExpression = 6, RULE_castExpression = 7, RULE_multiplicativeExpression = 8, 
		RULE_additiveExpression = 9, RULE_relationalExpression = 10, RULE_equalityExpression = 11, 
		RULE_andExpression = 12, RULE_exclusiveOrExpression = 13, RULE_inclusiveOrExpression = 14, 
		RULE_logicalAndExpression = 15, RULE_logicalOrExpression = 16, RULE_conditionalExpression = 17, 
		RULE_assignmentExpression = 18, RULE_assignmentOperator = 19, RULE_expression = 20, 
		RULE_declaration = 21, RULE_typeSpecifier = 22, RULE_initDeclarationList = 23, 
		RULE_initDeclarator = 24, RULE_declarator = 25, RULE_pointer = 26, RULE_directDeclarator = 27, 
		RULE_parameterList = 28, RULE_parameter = 29, RULE_functionBody = 30, 
		RULE_statement = 31, RULE_jumpStatement = 32, RULE_compoundStatement = 33, 
		RULE_blockItemList = 34, RULE_blockItem = 35, RULE_expressionStatement = 36, 
		RULE_selectionStatement = 37, RULE_iterationStatement = 38, RULE_forInit = 39, 
		RULE_forCheck = 40, RULE_forInc = 41, RULE_whileCheck = 42, RULE_argumentList = 43, 
		RULE_argument = 44, RULE_constant = 45;
	public static final String[] ruleNames = {
		"compilationUnit", "translationUnit", "externalDeclaration", "functionDefinition", 
		"primaryExpression", "postfixExpression", "unaryExpression", "castExpression", 
		"multiplicativeExpression", "additiveExpression", "relationalExpression", 
		"equalityExpression", "andExpression", "exclusiveOrExpression", "inclusiveOrExpression", 
		"logicalAndExpression", "logicalOrExpression", "conditionalExpression", 
		"assignmentExpression", "assignmentOperator", "expression", "declaration", 
		"typeSpecifier", "initDeclarationList", "initDeclarator", "declarator", 
		"pointer", "directDeclarator", "parameterList", "parameter", "functionBody", 
		"statement", "jumpStatement", "compoundStatement", "blockItemList", "blockItem", 
		"expressionStatement", "selectionStatement", "iterationStatement", "forInit", 
		"forCheck", "forInc", "whileCheck", "argumentList", "argument", "constant"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "';'", "'('", "')'", "'['", "']'", "'++'", "'--'", "'!'", "'&'", 
		"'~'", "'^'", "'|'", "'?'", "':'", "','", "'return'", "'break'", "'continue'", 
		"'{'", "'}'", "'if'", "'else'", "'for'", "'while'", "'do'", "'void'", 
		"'int'", "'char'", "'='", "'+='", "'-='", "'*='", "'/='", "'%='", "'*'", 
		"'/'", "'%'", "'+'", "'-'", "'=='", "'!='", "'<'", "'<='", "'>'", "'>='", 
		"'&&'", "'||'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, "VOIDTYPE", "INTTYPE", "CHARTYPE", "ASSIGN", "ASSIGN_ADD", 
		"ASSIGN_SUB", "ASSIGN_MUL", "ASSIGN_DIV", "ASSIGN_MOD", "MUL", "DIV", 
		"MOD", "ADD", "SUB", "EQUAL", "NOT_EQUAL", "LESS", "LESS_EQUAL", "GREATER", 
		"GREATER_EQUAL", "LOGICAL_AND", "LOGICAL_OR", "ID", "INT", "CHAR", "STRING_LITERAL", 
		"MULTY_LINE_COMMENT", "SINGLE_LINE_COMMENT", "WHITE_SPACE"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "picoC.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public picoCParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class CompilationUnitContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(picoCParser.EOF, 0); }
		public TranslationUnitContext translationUnit() {
			return getRuleContext(TranslationUnitContext.class,0);
		}
		public CompilationUnitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compilationUnit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).enterCompilationUnit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).exitCompilationUnit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof picoCVisitor ) return ((picoCVisitor<? extends T>)visitor).visitCompilationUnit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CompilationUnitContext compilationUnit() throws RecognitionException {
		CompilationUnitContext _localctx = new CompilationUnitContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_compilationUnit);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(93);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << VOIDTYPE) | (1L << INTTYPE) | (1L << CHARTYPE))) != 0)) {
				{
				setState(92);
				translationUnit(0);
				}
			}

			setState(95);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TranslationUnitContext extends ParserRuleContext {
		public ExternalDeclarationContext externalDeclaration() {
			return getRuleContext(ExternalDeclarationContext.class,0);
		}
		public TranslationUnitContext translationUnit() {
			return getRuleContext(TranslationUnitContext.class,0);
		}
		public TranslationUnitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_translationUnit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).enterTranslationUnit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).exitTranslationUnit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof picoCVisitor ) return ((picoCVisitor<? extends T>)visitor).visitTranslationUnit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TranslationUnitContext translationUnit() throws RecognitionException {
		return translationUnit(0);
	}

	private TranslationUnitContext translationUnit(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		TranslationUnitContext _localctx = new TranslationUnitContext(_ctx, _parentState);
		TranslationUnitContext _prevctx = _localctx;
		int _startState = 2;
		enterRecursionRule(_localctx, 2, RULE_translationUnit, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(98);
			externalDeclaration();
			}
			_ctx.stop = _input.LT(-1);
			setState(104);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new TranslationUnitContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_translationUnit);
					setState(100);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(101);
					externalDeclaration();
					}
					} 
				}
				setState(106);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ExternalDeclarationContext extends ParserRuleContext {
		public FunctionDefinitionContext functionDefinition() {
			return getRuleContext(FunctionDefinitionContext.class,0);
		}
		public DeclarationContext declaration() {
			return getRuleContext(DeclarationContext.class,0);
		}
		public ExternalDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_externalDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).enterExternalDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).exitExternalDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof picoCVisitor ) return ((picoCVisitor<? extends T>)visitor).visitExternalDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExternalDeclarationContext externalDeclaration() throws RecognitionException {
		ExternalDeclarationContext _localctx = new ExternalDeclarationContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_externalDeclaration);
		try {
			setState(110);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(107);
				functionDefinition();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(108);
				declaration();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(109);
				match(T__0);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionDefinitionContext extends ParserRuleContext {
		public TypeSpecifierContext typeSpecifier() {
			return getRuleContext(TypeSpecifierContext.class,0);
		}
		public DeclaratorContext declarator() {
			return getRuleContext(DeclaratorContext.class,0);
		}
		public FunctionBodyContext functionBody() {
			return getRuleContext(FunctionBodyContext.class,0);
		}
		public ParameterListContext parameterList() {
			return getRuleContext(ParameterListContext.class,0);
		}
		public FunctionDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionDefinition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).enterFunctionDefinition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).exitFunctionDefinition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof picoCVisitor ) return ((picoCVisitor<? extends T>)visitor).visitFunctionDefinition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionDefinitionContext functionDefinition() throws RecognitionException {
		FunctionDefinitionContext _localctx = new FunctionDefinitionContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_functionDefinition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(112);
			typeSpecifier();
			setState(113);
			declarator();
			setState(114);
			match(T__1);
			setState(116);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << VOIDTYPE) | (1L << INTTYPE) | (1L << CHARTYPE))) != 0)) {
				{
				setState(115);
				parameterList();
				}
			}

			setState(118);
			match(T__2);
			setState(119);
			functionBody();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PrimaryExpressionContext extends ParserRuleContext {
		public PrimaryExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primaryExpression; }
	 
		public PrimaryExpressionContext() { }
		public void copyFrom(PrimaryExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class StrContext extends PrimaryExpressionContext {
		public TerminalNode STRING_LITERAL() { return getToken(picoCParser.STRING_LITERAL, 0); }
		public StrContext(PrimaryExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).enterStr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).exitStr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof picoCVisitor ) return ((picoCVisitor<? extends T>)visitor).visitStr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ParensContext extends PrimaryExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ParensContext(PrimaryExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).enterParens(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).exitParens(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof picoCVisitor ) return ((picoCVisitor<? extends T>)visitor).visitParens(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ConstContext extends PrimaryExpressionContext {
		public ConstantContext constant() {
			return getRuleContext(ConstantContext.class,0);
		}
		public ConstContext(PrimaryExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).enterConst(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).exitConst(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof picoCVisitor ) return ((picoCVisitor<? extends T>)visitor).visitConst(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IdContext extends PrimaryExpressionContext {
		public TerminalNode ID() { return getToken(picoCParser.ID, 0); }
		public IdContext(PrimaryExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).enterId(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).exitId(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof picoCVisitor ) return ((picoCVisitor<? extends T>)visitor).visitId(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrimaryExpressionContext primaryExpression() throws RecognitionException {
		PrimaryExpressionContext _localctx = new PrimaryExpressionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_primaryExpression);
		try {
			setState(128);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				_localctx = new IdContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(121);
				match(ID);
				}
				break;
			case INT:
			case CHAR:
				_localctx = new ConstContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(122);
				constant();
				}
				break;
			case STRING_LITERAL:
				_localctx = new StrContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(123);
				match(STRING_LITERAL);
				}
				break;
			case T__1:
				_localctx = new ParensContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(124);
				match(T__1);
				setState(125);
				expression(0);
				setState(126);
				match(T__2);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PostfixExpressionContext extends ParserRuleContext {
		public PostfixExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_postfixExpression; }
	 
		public PostfixExpressionContext() { }
		public void copyFrom(PostfixExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class FuncCallContext extends PostfixExpressionContext {
		public PostfixExpressionContext postfixExpression() {
			return getRuleContext(PostfixExpressionContext.class,0);
		}
		public ArgumentListContext argumentList() {
			return getRuleContext(ArgumentListContext.class,0);
		}
		public FuncCallContext(PostfixExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).enterFuncCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).exitFuncCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof picoCVisitor ) return ((picoCVisitor<? extends T>)visitor).visitFuncCall(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PostDecContext extends PostfixExpressionContext {
		public PostfixExpressionContext postfixExpression() {
			return getRuleContext(PostfixExpressionContext.class,0);
		}
		public PostDecContext(PostfixExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).enterPostDec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).exitPostDec(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof picoCVisitor ) return ((picoCVisitor<? extends T>)visitor).visitPostDec(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PostIncContext extends PostfixExpressionContext {
		public PostfixExpressionContext postfixExpression() {
			return getRuleContext(PostfixExpressionContext.class,0);
		}
		public PostIncContext(PostfixExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).enterPostInc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).exitPostInc(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof picoCVisitor ) return ((picoCVisitor<? extends T>)visitor).visitPostInc(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SubscriptContext extends PostfixExpressionContext {
		public PostfixExpressionContext postfixExpression() {
			return getRuleContext(PostfixExpressionContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public SubscriptContext(PostfixExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).enterSubscript(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).exitSubscript(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof picoCVisitor ) return ((picoCVisitor<? extends T>)visitor).visitSubscript(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DropPostfixContext extends PostfixExpressionContext {
		public PrimaryExpressionContext primaryExpression() {
			return getRuleContext(PrimaryExpressionContext.class,0);
		}
		public DropPostfixContext(PostfixExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).enterDropPostfix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).exitDropPostfix(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof picoCVisitor ) return ((picoCVisitor<? extends T>)visitor).visitDropPostfix(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PostfixExpressionContext postfixExpression() throws RecognitionException {
		return postfixExpression(0);
	}

	private PostfixExpressionContext postfixExpression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		PostfixExpressionContext _localctx = new PostfixExpressionContext(_ctx, _parentState);
		PostfixExpressionContext _prevctx = _localctx;
		int _startState = 10;
		enterRecursionRule(_localctx, 10, RULE_postfixExpression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			_localctx = new DropPostfixContext(_localctx);
			_ctx = _localctx;
			_prevctx = _localctx;

			setState(131);
			primaryExpression();
			}
			_ctx.stop = _input.LT(-1);
			setState(150);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(148);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
					case 1:
						{
						_localctx = new SubscriptContext(new PostfixExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_postfixExpression);
						setState(133);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(134);
						match(T__3);
						setState(135);
						expression(0);
						setState(136);
						match(T__4);
						}
						break;
					case 2:
						{
						_localctx = new FuncCallContext(new PostfixExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_postfixExpression);
						setState(138);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(139);
						match(T__1);
						setState(141);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << MUL) | (1L << ADD) | (1L << SUB) | (1L << ID) | (1L << INT) | (1L << CHAR) | (1L << STRING_LITERAL))) != 0)) {
							{
							setState(140);
							argumentList();
							}
						}

						setState(143);
						match(T__2);
						}
						break;
					case 3:
						{
						_localctx = new PostIncContext(new PostfixExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_postfixExpression);
						setState(144);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(145);
						match(T__5);
						}
						break;
					case 4:
						{
						_localctx = new PostDecContext(new PostfixExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_postfixExpression);
						setState(146);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(147);
						match(T__6);
						}
						break;
					}
					} 
				}
				setState(152);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class UnaryExpressionContext extends ParserRuleContext {
		public UnaryExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unaryExpression; }
	 
		public UnaryExpressionContext() { }
		public void copyFrom(UnaryExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class PreIncContext extends UnaryExpressionContext {
		public UnaryExpressionContext unaryExpression() {
			return getRuleContext(UnaryExpressionContext.class,0);
		}
		public PreIncContext(UnaryExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).enterPreInc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).exitPreInc(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof picoCVisitor ) return ((picoCVisitor<? extends T>)visitor).visitPreInc(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NegationContext extends UnaryExpressionContext {
		public CastExpressionContext castExpression() {
			return getRuleContext(CastExpressionContext.class,0);
		}
		public NegationContext(UnaryExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).enterNegation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).exitNegation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof picoCVisitor ) return ((picoCVisitor<? extends T>)visitor).visitNegation(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AddressContext extends UnaryExpressionContext {
		public CastExpressionContext castExpression() {
			return getRuleContext(CastExpressionContext.class,0);
		}
		public AddressContext(UnaryExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).enterAddress(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).exitAddress(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof picoCVisitor ) return ((picoCVisitor<? extends T>)visitor).visitAddress(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PreDecContext extends UnaryExpressionContext {
		public UnaryExpressionContext unaryExpression() {
			return getRuleContext(UnaryExpressionContext.class,0);
		}
		public PreDecContext(UnaryExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).enterPreDec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).exitPreDec(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof picoCVisitor ) return ((picoCVisitor<? extends T>)visitor).visitPreDec(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DropUnaryContext extends UnaryExpressionContext {
		public PostfixExpressionContext postfixExpression() {
			return getRuleContext(PostfixExpressionContext.class,0);
		}
		public DropUnaryContext(UnaryExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).enterDropUnary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).exitDropUnary(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof picoCVisitor ) return ((picoCVisitor<? extends T>)visitor).visitDropUnary(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ComplementContext extends UnaryExpressionContext {
		public CastExpressionContext castExpression() {
			return getRuleContext(CastExpressionContext.class,0);
		}
		public ComplementContext(UnaryExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).enterComplement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).exitComplement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof picoCVisitor ) return ((picoCVisitor<? extends T>)visitor).visitComplement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PlusContext extends UnaryExpressionContext {
		public CastExpressionContext castExpression() {
			return getRuleContext(CastExpressionContext.class,0);
		}
		public PlusContext(UnaryExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).enterPlus(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).exitPlus(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof picoCVisitor ) return ((picoCVisitor<? extends T>)visitor).visitPlus(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MinusContext extends UnaryExpressionContext {
		public CastExpressionContext castExpression() {
			return getRuleContext(CastExpressionContext.class,0);
		}
		public MinusContext(UnaryExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).enterMinus(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).exitMinus(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof picoCVisitor ) return ((picoCVisitor<? extends T>)visitor).visitMinus(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DerefContext extends UnaryExpressionContext {
		public CastExpressionContext castExpression() {
			return getRuleContext(CastExpressionContext.class,0);
		}
		public DerefContext(UnaryExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).enterDeref(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).exitDeref(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof picoCVisitor ) return ((picoCVisitor<? extends T>)visitor).visitDeref(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnaryExpressionContext unaryExpression() throws RecognitionException {
		UnaryExpressionContext _localctx = new UnaryExpressionContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_unaryExpression);
		try {
			setState(170);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__1:
			case ID:
			case INT:
			case CHAR:
			case STRING_LITERAL:
				_localctx = new DropUnaryContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(153);
				postfixExpression(0);
				}
				break;
			case T__5:
				_localctx = new PreIncContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(154);
				match(T__5);
				setState(155);
				unaryExpression();
				}
				break;
			case T__6:
				_localctx = new PreDecContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(156);
				match(T__6);
				setState(157);
				unaryExpression();
				}
				break;
			case MUL:
				_localctx = new DerefContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(158);
				match(MUL);
				setState(159);
				castExpression();
				}
				break;
			case T__7:
				_localctx = new NegationContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(160);
				match(T__7);
				setState(161);
				castExpression();
				}
				break;
			case T__8:
				_localctx = new AddressContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(162);
				match(T__8);
				setState(163);
				castExpression();
				}
				break;
			case T__9:
				_localctx = new ComplementContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(164);
				match(T__9);
				setState(165);
				castExpression();
				}
				break;
			case SUB:
				_localctx = new MinusContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(166);
				match(SUB);
				setState(167);
				castExpression();
				}
				break;
			case ADD:
				_localctx = new PlusContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(168);
				match(ADD);
				setState(169);
				castExpression();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CastExpressionContext extends ParserRuleContext {
		public CastExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_castExpression; }
	 
		public CastExpressionContext() { }
		public void copyFrom(CastExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class CastContext extends CastExpressionContext {
		public TypeSpecifierContext typeSpecifier() {
			return getRuleContext(TypeSpecifierContext.class,0);
		}
		public CastExpressionContext castExpression() {
			return getRuleContext(CastExpressionContext.class,0);
		}
		public PointerContext pointer() {
			return getRuleContext(PointerContext.class,0);
		}
		public CastContext(CastExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).enterCast(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).exitCast(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof picoCVisitor ) return ((picoCVisitor<? extends T>)visitor).visitCast(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DropCastContext extends CastExpressionContext {
		public UnaryExpressionContext unaryExpression() {
			return getRuleContext(UnaryExpressionContext.class,0);
		}
		public DropCastContext(CastExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).enterDropCast(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).exitDropCast(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof picoCVisitor ) return ((picoCVisitor<? extends T>)visitor).visitDropCast(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CastExpressionContext castExpression() throws RecognitionException {
		CastExpressionContext _localctx = new CastExpressionContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_castExpression);
		int _la;
		try {
			setState(181);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				_localctx = new DropCastContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(172);
				unaryExpression();
				}
				break;
			case 2:
				_localctx = new CastContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(173);
				match(T__1);
				setState(174);
				typeSpecifier();
				setState(176);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MUL) {
					{
					setState(175);
					pointer(0);
					}
				}

				setState(178);
				match(T__2);
				setState(179);
				castExpression();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MultiplicativeExpressionContext extends ParserRuleContext {
		public MultiplicativeExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiplicativeExpression; }
	 
		public MultiplicativeExpressionContext() { }
		public void copyFrom(MultiplicativeExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class MulDivModContext extends MultiplicativeExpressionContext {
		public Token op;
		public MultiplicativeExpressionContext multiplicativeExpression() {
			return getRuleContext(MultiplicativeExpressionContext.class,0);
		}
		public UnaryExpressionContext unaryExpression() {
			return getRuleContext(UnaryExpressionContext.class,0);
		}
		public MulDivModContext(MultiplicativeExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).enterMulDivMod(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).exitMulDivMod(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof picoCVisitor ) return ((picoCVisitor<? extends T>)visitor).visitMulDivMod(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DropMulDivModContext extends MultiplicativeExpressionContext {
		public CastExpressionContext castExpression() {
			return getRuleContext(CastExpressionContext.class,0);
		}
		public DropMulDivModContext(MultiplicativeExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).enterDropMulDivMod(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).exitDropMulDivMod(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof picoCVisitor ) return ((picoCVisitor<? extends T>)visitor).visitDropMulDivMod(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MultiplicativeExpressionContext multiplicativeExpression() throws RecognitionException {
		return multiplicativeExpression(0);
	}

	private MultiplicativeExpressionContext multiplicativeExpression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		MultiplicativeExpressionContext _localctx = new MultiplicativeExpressionContext(_ctx, _parentState);
		MultiplicativeExpressionContext _prevctx = _localctx;
		int _startState = 16;
		enterRecursionRule(_localctx, 16, RULE_multiplicativeExpression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			_localctx = new DropMulDivModContext(_localctx);
			_ctx = _localctx;
			_prevctx = _localctx;

			setState(184);
			castExpression();
			}
			_ctx.stop = _input.LT(-1);
			setState(191);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new MulDivModContext(new MultiplicativeExpressionContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_multiplicativeExpression);
					setState(186);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(187);
					((MulDivModContext)_localctx).op = _input.LT(1);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MUL) | (1L << DIV) | (1L << MOD))) != 0)) ) {
						((MulDivModContext)_localctx).op = (Token)_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(188);
					unaryExpression();
					}
					} 
				}
				setState(193);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class AdditiveExpressionContext extends ParserRuleContext {
		public AdditiveExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_additiveExpression; }
	 
		public AdditiveExpressionContext() { }
		public void copyFrom(AdditiveExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class AddSubContext extends AdditiveExpressionContext {
		public Token op;
		public AdditiveExpressionContext additiveExpression() {
			return getRuleContext(AdditiveExpressionContext.class,0);
		}
		public MultiplicativeExpressionContext multiplicativeExpression() {
			return getRuleContext(MultiplicativeExpressionContext.class,0);
		}
		public AddSubContext(AdditiveExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).enterAddSub(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).exitAddSub(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof picoCVisitor ) return ((picoCVisitor<? extends T>)visitor).visitAddSub(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DropAddSubContext extends AdditiveExpressionContext {
		public MultiplicativeExpressionContext multiplicativeExpression() {
			return getRuleContext(MultiplicativeExpressionContext.class,0);
		}
		public DropAddSubContext(AdditiveExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).enterDropAddSub(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).exitDropAddSub(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof picoCVisitor ) return ((picoCVisitor<? extends T>)visitor).visitDropAddSub(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AdditiveExpressionContext additiveExpression() throws RecognitionException {
		return additiveExpression(0);
	}

	private AdditiveExpressionContext additiveExpression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		AdditiveExpressionContext _localctx = new AdditiveExpressionContext(_ctx, _parentState);
		AdditiveExpressionContext _prevctx = _localctx;
		int _startState = 18;
		enterRecursionRule(_localctx, 18, RULE_additiveExpression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			_localctx = new DropAddSubContext(_localctx);
			_ctx = _localctx;
			_prevctx = _localctx;

			setState(195);
			multiplicativeExpression(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(202);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new AddSubContext(new AdditiveExpressionContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_additiveExpression);
					setState(197);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(198);
					((AddSubContext)_localctx).op = _input.LT(1);
					_la = _input.LA(1);
					if ( !(_la==ADD || _la==SUB) ) {
						((AddSubContext)_localctx).op = (Token)_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(199);
					multiplicativeExpression(0);
					}
					} 
				}
				setState(204);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class RelationalExpressionContext extends ParserRuleContext {
		public RelationalExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relationalExpression; }
	 
		public RelationalExpressionContext() { }
		public void copyFrom(RelationalExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class DropRelationalContext extends RelationalExpressionContext {
		public AdditiveExpressionContext additiveExpression() {
			return getRuleContext(AdditiveExpressionContext.class,0);
		}
		public DropRelationalContext(RelationalExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).enterDropRelational(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).exitDropRelational(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof picoCVisitor ) return ((picoCVisitor<? extends T>)visitor).visitDropRelational(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class RelationContext extends RelationalExpressionContext {
		public Token rel;
		public RelationalExpressionContext relationalExpression() {
			return getRuleContext(RelationalExpressionContext.class,0);
		}
		public AdditiveExpressionContext additiveExpression() {
			return getRuleContext(AdditiveExpressionContext.class,0);
		}
		public RelationContext(RelationalExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).enterRelation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).exitRelation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof picoCVisitor ) return ((picoCVisitor<? extends T>)visitor).visitRelation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RelationalExpressionContext relationalExpression() throws RecognitionException {
		return relationalExpression(0);
	}

	private RelationalExpressionContext relationalExpression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		RelationalExpressionContext _localctx = new RelationalExpressionContext(_ctx, _parentState);
		RelationalExpressionContext _prevctx = _localctx;
		int _startState = 20;
		enterRecursionRule(_localctx, 20, RULE_relationalExpression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			_localctx = new DropRelationalContext(_localctx);
			_ctx = _localctx;
			_prevctx = _localctx;

			setState(206);
			additiveExpression(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(213);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new RelationContext(new RelationalExpressionContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_relationalExpression);
					setState(208);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(209);
					((RelationContext)_localctx).rel = _input.LT(1);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LESS) | (1L << LESS_EQUAL) | (1L << GREATER) | (1L << GREATER_EQUAL))) != 0)) ) {
						((RelationContext)_localctx).rel = (Token)_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(210);
					additiveExpression(0);
					}
					} 
				}
				setState(215);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class EqualityExpressionContext extends ParserRuleContext {
		public EqualityExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_equalityExpression; }
	 
		public EqualityExpressionContext() { }
		public void copyFrom(EqualityExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class DropEqualityContext extends EqualityExpressionContext {
		public RelationalExpressionContext relationalExpression() {
			return getRuleContext(RelationalExpressionContext.class,0);
		}
		public DropEqualityContext(EqualityExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).enterDropEquality(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).exitDropEquality(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof picoCVisitor ) return ((picoCVisitor<? extends T>)visitor).visitDropEquality(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class EqualityContext extends EqualityExpressionContext {
		public Token rel;
		public EqualityExpressionContext equalityExpression() {
			return getRuleContext(EqualityExpressionContext.class,0);
		}
		public RelationalExpressionContext relationalExpression() {
			return getRuleContext(RelationalExpressionContext.class,0);
		}
		public EqualityContext(EqualityExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).enterEquality(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).exitEquality(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof picoCVisitor ) return ((picoCVisitor<? extends T>)visitor).visitEquality(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EqualityExpressionContext equalityExpression() throws RecognitionException {
		return equalityExpression(0);
	}

	private EqualityExpressionContext equalityExpression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		EqualityExpressionContext _localctx = new EqualityExpressionContext(_ctx, _parentState);
		EqualityExpressionContext _prevctx = _localctx;
		int _startState = 22;
		enterRecursionRule(_localctx, 22, RULE_equalityExpression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			_localctx = new DropEqualityContext(_localctx);
			_ctx = _localctx;
			_prevctx = _localctx;

			setState(217);
			relationalExpression(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(224);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new EqualityContext(new EqualityExpressionContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_equalityExpression);
					setState(219);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(220);
					((EqualityContext)_localctx).rel = _input.LT(1);
					_la = _input.LA(1);
					if ( !(_la==EQUAL || _la==NOT_EQUAL) ) {
						((EqualityContext)_localctx).rel = (Token)_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(221);
					relationalExpression(0);
					}
					} 
				}
				setState(226);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class AndExpressionContext extends ParserRuleContext {
		public AndExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_andExpression; }
	 
		public AndExpressionContext() { }
		public void copyFrom(AndExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class AndContext extends AndExpressionContext {
		public AndExpressionContext andExpression() {
			return getRuleContext(AndExpressionContext.class,0);
		}
		public EqualityExpressionContext equalityExpression() {
			return getRuleContext(EqualityExpressionContext.class,0);
		}
		public AndContext(AndExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).enterAnd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).exitAnd(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof picoCVisitor ) return ((picoCVisitor<? extends T>)visitor).visitAnd(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DropAndContext extends AndExpressionContext {
		public EqualityExpressionContext equalityExpression() {
			return getRuleContext(EqualityExpressionContext.class,0);
		}
		public DropAndContext(AndExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).enterDropAnd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).exitDropAnd(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof picoCVisitor ) return ((picoCVisitor<? extends T>)visitor).visitDropAnd(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AndExpressionContext andExpression() throws RecognitionException {
		return andExpression(0);
	}

	private AndExpressionContext andExpression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		AndExpressionContext _localctx = new AndExpressionContext(_ctx, _parentState);
		AndExpressionContext _prevctx = _localctx;
		int _startState = 24;
		enterRecursionRule(_localctx, 24, RULE_andExpression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			_localctx = new DropAndContext(_localctx);
			_ctx = _localctx;
			_prevctx = _localctx;

			setState(228);
			equalityExpression(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(235);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new AndContext(new AndExpressionContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_andExpression);
					setState(230);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(231);
					match(T__8);
					setState(232);
					equalityExpression(0);
					}
					} 
				}
				setState(237);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ExclusiveOrExpressionContext extends ParserRuleContext {
		public ExclusiveOrExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exclusiveOrExpression; }
	 
		public ExclusiveOrExpressionContext() { }
		public void copyFrom(ExclusiveOrExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class DropExclusiveOrContext extends ExclusiveOrExpressionContext {
		public AndExpressionContext andExpression() {
			return getRuleContext(AndExpressionContext.class,0);
		}
		public DropExclusiveOrContext(ExclusiveOrExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).enterDropExclusiveOr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).exitDropExclusiveOr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof picoCVisitor ) return ((picoCVisitor<? extends T>)visitor).visitDropExclusiveOr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExclusiveOrContext extends ExclusiveOrExpressionContext {
		public ExclusiveOrExpressionContext exclusiveOrExpression() {
			return getRuleContext(ExclusiveOrExpressionContext.class,0);
		}
		public AndExpressionContext andExpression() {
			return getRuleContext(AndExpressionContext.class,0);
		}
		public ExclusiveOrContext(ExclusiveOrExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).enterExclusiveOr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).exitExclusiveOr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof picoCVisitor ) return ((picoCVisitor<? extends T>)visitor).visitExclusiveOr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExclusiveOrExpressionContext exclusiveOrExpression() throws RecognitionException {
		return exclusiveOrExpression(0);
	}

	private ExclusiveOrExpressionContext exclusiveOrExpression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExclusiveOrExpressionContext _localctx = new ExclusiveOrExpressionContext(_ctx, _parentState);
		ExclusiveOrExpressionContext _prevctx = _localctx;
		int _startState = 26;
		enterRecursionRule(_localctx, 26, RULE_exclusiveOrExpression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			_localctx = new DropExclusiveOrContext(_localctx);
			_ctx = _localctx;
			_prevctx = _localctx;

			setState(239);
			andExpression(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(246);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ExclusiveOrContext(new ExclusiveOrExpressionContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_exclusiveOrExpression);
					setState(241);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(242);
					match(T__10);
					setState(243);
					andExpression(0);
					}
					} 
				}
				setState(248);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class InclusiveOrExpressionContext extends ParserRuleContext {
		public InclusiveOrExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_inclusiveOrExpression; }
	 
		public InclusiveOrExpressionContext() { }
		public void copyFrom(InclusiveOrExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class DropInclusiveOrContext extends InclusiveOrExpressionContext {
		public ExclusiveOrExpressionContext exclusiveOrExpression() {
			return getRuleContext(ExclusiveOrExpressionContext.class,0);
		}
		public DropInclusiveOrContext(InclusiveOrExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).enterDropInclusiveOr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).exitDropInclusiveOr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof picoCVisitor ) return ((picoCVisitor<? extends T>)visitor).visitDropInclusiveOr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class InclusiveOrContext extends InclusiveOrExpressionContext {
		public InclusiveOrExpressionContext inclusiveOrExpression() {
			return getRuleContext(InclusiveOrExpressionContext.class,0);
		}
		public ExclusiveOrExpressionContext exclusiveOrExpression() {
			return getRuleContext(ExclusiveOrExpressionContext.class,0);
		}
		public InclusiveOrContext(InclusiveOrExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).enterInclusiveOr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).exitInclusiveOr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof picoCVisitor ) return ((picoCVisitor<? extends T>)visitor).visitInclusiveOr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InclusiveOrExpressionContext inclusiveOrExpression() throws RecognitionException {
		return inclusiveOrExpression(0);
	}

	private InclusiveOrExpressionContext inclusiveOrExpression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		InclusiveOrExpressionContext _localctx = new InclusiveOrExpressionContext(_ctx, _parentState);
		InclusiveOrExpressionContext _prevctx = _localctx;
		int _startState = 28;
		enterRecursionRule(_localctx, 28, RULE_inclusiveOrExpression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			_localctx = new DropInclusiveOrContext(_localctx);
			_ctx = _localctx;
			_prevctx = _localctx;

			setState(250);
			exclusiveOrExpression(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(257);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new InclusiveOrContext(new InclusiveOrExpressionContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_inclusiveOrExpression);
					setState(252);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(253);
					match(T__11);
					setState(254);
					exclusiveOrExpression(0);
					}
					} 
				}
				setState(259);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class LogicalAndExpressionContext extends ParserRuleContext {
		public LogicalAndExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logicalAndExpression; }
	 
		public LogicalAndExpressionContext() { }
		public void copyFrom(LogicalAndExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class LogicalANDContext extends LogicalAndExpressionContext {
		public LogicalAndExpressionContext logicalAndExpression() {
			return getRuleContext(LogicalAndExpressionContext.class,0);
		}
		public EqualityExpressionContext equalityExpression() {
			return getRuleContext(EqualityExpressionContext.class,0);
		}
		public LogicalANDContext(LogicalAndExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).enterLogicalAND(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).exitLogicalAND(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof picoCVisitor ) return ((picoCVisitor<? extends T>)visitor).visitLogicalAND(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DropLogicalANDContext extends LogicalAndExpressionContext {
		public InclusiveOrExpressionContext inclusiveOrExpression() {
			return getRuleContext(InclusiveOrExpressionContext.class,0);
		}
		public DropLogicalANDContext(LogicalAndExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).enterDropLogicalAND(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).exitDropLogicalAND(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof picoCVisitor ) return ((picoCVisitor<? extends T>)visitor).visitDropLogicalAND(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LogicalAndExpressionContext logicalAndExpression() throws RecognitionException {
		return logicalAndExpression(0);
	}

	private LogicalAndExpressionContext logicalAndExpression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		LogicalAndExpressionContext _localctx = new LogicalAndExpressionContext(_ctx, _parentState);
		LogicalAndExpressionContext _prevctx = _localctx;
		int _startState = 30;
		enterRecursionRule(_localctx, 30, RULE_logicalAndExpression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			_localctx = new DropLogicalANDContext(_localctx);
			_ctx = _localctx;
			_prevctx = _localctx;

			setState(261);
			inclusiveOrExpression(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(268);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new LogicalANDContext(new LogicalAndExpressionContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_logicalAndExpression);
					setState(263);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(264);
					match(LOGICAL_AND);
					setState(265);
					equalityExpression(0);
					}
					} 
				}
				setState(270);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class LogicalOrExpressionContext extends ParserRuleContext {
		public LogicalOrExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logicalOrExpression; }
	 
		public LogicalOrExpressionContext() { }
		public void copyFrom(LogicalOrExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class DropLogicalORContext extends LogicalOrExpressionContext {
		public LogicalAndExpressionContext logicalAndExpression() {
			return getRuleContext(LogicalAndExpressionContext.class,0);
		}
		public DropLogicalORContext(LogicalOrExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).enterDropLogicalOR(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).exitDropLogicalOR(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof picoCVisitor ) return ((picoCVisitor<? extends T>)visitor).visitDropLogicalOR(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LogicalORContext extends LogicalOrExpressionContext {
		public LogicalOrExpressionContext logicalOrExpression() {
			return getRuleContext(LogicalOrExpressionContext.class,0);
		}
		public LogicalAndExpressionContext logicalAndExpression() {
			return getRuleContext(LogicalAndExpressionContext.class,0);
		}
		public LogicalORContext(LogicalOrExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).enterLogicalOR(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).exitLogicalOR(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof picoCVisitor ) return ((picoCVisitor<? extends T>)visitor).visitLogicalOR(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LogicalOrExpressionContext logicalOrExpression() throws RecognitionException {
		return logicalOrExpression(0);
	}

	private LogicalOrExpressionContext logicalOrExpression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		LogicalOrExpressionContext _localctx = new LogicalOrExpressionContext(_ctx, _parentState);
		LogicalOrExpressionContext _prevctx = _localctx;
		int _startState = 32;
		enterRecursionRule(_localctx, 32, RULE_logicalOrExpression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			_localctx = new DropLogicalORContext(_localctx);
			_ctx = _localctx;
			_prevctx = _localctx;

			setState(272);
			logicalAndExpression(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(279);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new LogicalORContext(new LogicalOrExpressionContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_logicalOrExpression);
					setState(274);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(275);
					match(LOGICAL_OR);
					setState(276);
					logicalAndExpression(0);
					}
					} 
				}
				setState(281);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ConditionalExpressionContext extends ParserRuleContext {
		public ConditionalExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conditionalExpression; }
	 
		public ConditionalExpressionContext() { }
		public void copyFrom(ConditionalExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class DropConditionalContext extends ConditionalExpressionContext {
		public LogicalOrExpressionContext logicalOrExpression() {
			return getRuleContext(LogicalOrExpressionContext.class,0);
		}
		public DropConditionalContext(ConditionalExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).enterDropConditional(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).exitDropConditional(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof picoCVisitor ) return ((picoCVisitor<? extends T>)visitor).visitDropConditional(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ConditionalContext extends ConditionalExpressionContext {
		public LogicalOrExpressionContext logicalOrExpression() {
			return getRuleContext(LogicalOrExpressionContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ConditionalExpressionContext conditionalExpression() {
			return getRuleContext(ConditionalExpressionContext.class,0);
		}
		public ConditionalContext(ConditionalExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).enterConditional(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).exitConditional(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof picoCVisitor ) return ((picoCVisitor<? extends T>)visitor).visitConditional(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConditionalExpressionContext conditionalExpression() throws RecognitionException {
		ConditionalExpressionContext _localctx = new ConditionalExpressionContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_conditionalExpression);
		try {
			setState(289);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				_localctx = new DropConditionalContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(282);
				logicalOrExpression(0);
				}
				break;
			case 2:
				_localctx = new ConditionalContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(283);
				logicalOrExpression(0);
				setState(284);
				match(T__12);
				setState(285);
				expression(0);
				setState(286);
				match(T__13);
				setState(287);
				conditionalExpression();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssignmentExpressionContext extends ParserRuleContext {
		public AssignmentExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignmentExpression; }
	 
		public AssignmentExpressionContext() { }
		public void copyFrom(AssignmentExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class DropAssignContext extends AssignmentExpressionContext {
		public ConditionalExpressionContext conditionalExpression() {
			return getRuleContext(ConditionalExpressionContext.class,0);
		}
		public DropAssignContext(AssignmentExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).enterDropAssign(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).exitDropAssign(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof picoCVisitor ) return ((picoCVisitor<? extends T>)visitor).visitDropAssign(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AssignContext extends AssignmentExpressionContext {
		public UnaryExpressionContext unaryExpression() {
			return getRuleContext(UnaryExpressionContext.class,0);
		}
		public AssignmentOperatorContext assignmentOperator() {
			return getRuleContext(AssignmentOperatorContext.class,0);
		}
		public AssignmentExpressionContext assignmentExpression() {
			return getRuleContext(AssignmentExpressionContext.class,0);
		}
		public AssignContext(AssignmentExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).enterAssign(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).exitAssign(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof picoCVisitor ) return ((picoCVisitor<? extends T>)visitor).visitAssign(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignmentExpressionContext assignmentExpression() throws RecognitionException {
		AssignmentExpressionContext _localctx = new AssignmentExpressionContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_assignmentExpression);
		try {
			setState(296);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				_localctx = new DropAssignContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(291);
				conditionalExpression();
				}
				break;
			case 2:
				_localctx = new AssignContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(292);
				unaryExpression();
				setState(293);
				assignmentOperator();
				setState(294);
				assignmentExpression();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssignmentOperatorContext extends ParserRuleContext {
		public Token op;
		public AssignmentOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignmentOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).enterAssignmentOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).exitAssignmentOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof picoCVisitor ) return ((picoCVisitor<? extends T>)visitor).visitAssignmentOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignmentOperatorContext assignmentOperator() throws RecognitionException {
		AssignmentOperatorContext _localctx = new AssignmentOperatorContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_assignmentOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(298);
			((AssignmentOperatorContext)_localctx).op = _input.LT(1);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ASSIGN) | (1L << ASSIGN_ADD) | (1L << ASSIGN_SUB) | (1L << ASSIGN_MUL) | (1L << ASSIGN_DIV) | (1L << ASSIGN_MOD))) != 0)) ) {
				((AssignmentOperatorContext)_localctx).op = (Token)_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public AssignmentExpressionContext assignmentExpression() {
			return getRuleContext(AssignmentExpressionContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).exitExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof picoCVisitor ) return ((picoCVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 40;
		enterRecursionRule(_localctx, 40, RULE_expression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(301);
			assignmentExpression();
			}
			_ctx.stop = _input.LT(-1);
			setState(308);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ExpressionContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_expression);
					setState(303);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(304);
					match(T__14);
					setState(305);
					assignmentExpression();
					}
					} 
				}
				setState(310);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class DeclarationContext extends ParserRuleContext {
		public TypeSpecifierContext typeSpecifier() {
			return getRuleContext(TypeSpecifierContext.class,0);
		}
		public InitDeclarationListContext initDeclarationList() {
			return getRuleContext(InitDeclarationListContext.class,0);
		}
		public DeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).enterDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).exitDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof picoCVisitor ) return ((picoCVisitor<? extends T>)visitor).visitDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclarationContext declaration() throws RecognitionException {
		DeclarationContext _localctx = new DeclarationContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_declaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(311);
			typeSpecifier();
			setState(312);
			initDeclarationList(0);
			setState(313);
			match(T__0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeSpecifierContext extends ParserRuleContext {
		public Token type;
		public TypeSpecifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeSpecifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).enterTypeSpecifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).exitTypeSpecifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof picoCVisitor ) return ((picoCVisitor<? extends T>)visitor).visitTypeSpecifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeSpecifierContext typeSpecifier() throws RecognitionException {
		TypeSpecifierContext _localctx = new TypeSpecifierContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_typeSpecifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(315);
			((TypeSpecifierContext)_localctx).type = _input.LT(1);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << VOIDTYPE) | (1L << INTTYPE) | (1L << CHARTYPE))) != 0)) ) {
				((TypeSpecifierContext)_localctx).type = (Token)_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InitDeclarationListContext extends ParserRuleContext {
		public InitDeclaratorContext initDeclarator() {
			return getRuleContext(InitDeclaratorContext.class,0);
		}
		public InitDeclarationListContext initDeclarationList() {
			return getRuleContext(InitDeclarationListContext.class,0);
		}
		public InitDeclarationListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_initDeclarationList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).enterInitDeclarationList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).exitInitDeclarationList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof picoCVisitor ) return ((picoCVisitor<? extends T>)visitor).visitInitDeclarationList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InitDeclarationListContext initDeclarationList() throws RecognitionException {
		return initDeclarationList(0);
	}

	private InitDeclarationListContext initDeclarationList(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		InitDeclarationListContext _localctx = new InitDeclarationListContext(_ctx, _parentState);
		InitDeclarationListContext _prevctx = _localctx;
		int _startState = 46;
		enterRecursionRule(_localctx, 46, RULE_initDeclarationList, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(318);
			initDeclarator();
			}
			_ctx.stop = _input.LT(-1);
			setState(325);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new InitDeclarationListContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_initDeclarationList);
					setState(320);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(321);
					match(T__14);
					setState(322);
					initDeclarator();
					}
					} 
				}
				setState(327);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class InitDeclaratorContext extends ParserRuleContext {
		public InitDeclaratorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_initDeclarator; }
	 
		public InitDeclaratorContext() { }
		public void copyFrom(InitDeclaratorContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class DeclWithInitContext extends InitDeclaratorContext {
		public DeclaratorContext declarator() {
			return getRuleContext(DeclaratorContext.class,0);
		}
		public AssignmentExpressionContext assignmentExpression() {
			return getRuleContext(AssignmentExpressionContext.class,0);
		}
		public DeclWithInitContext(InitDeclaratorContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).enterDeclWithInit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).exitDeclWithInit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof picoCVisitor ) return ((picoCVisitor<? extends T>)visitor).visitDeclWithInit(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DeclContext extends InitDeclaratorContext {
		public DeclaratorContext declarator() {
			return getRuleContext(DeclaratorContext.class,0);
		}
		public DeclContext(InitDeclaratorContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).enterDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).exitDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof picoCVisitor ) return ((picoCVisitor<? extends T>)visitor).visitDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InitDeclaratorContext initDeclarator() throws RecognitionException {
		InitDeclaratorContext _localctx = new InitDeclaratorContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_initDeclarator);
		try {
			setState(333);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
			case 1:
				_localctx = new DeclContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(328);
				declarator();
				}
				break;
			case 2:
				_localctx = new DeclWithInitContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(329);
				declarator();
				setState(330);
				match(ASSIGN);
				setState(331);
				assignmentExpression();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclaratorContext extends ParserRuleContext {
		public DirectDeclaratorContext directDeclarator() {
			return getRuleContext(DirectDeclaratorContext.class,0);
		}
		public PointerContext pointer() {
			return getRuleContext(PointerContext.class,0);
		}
		public DeclaratorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declarator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).enterDeclarator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).exitDeclarator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof picoCVisitor ) return ((picoCVisitor<? extends T>)visitor).visitDeclarator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclaratorContext declarator() throws RecognitionException {
		DeclaratorContext _localctx = new DeclaratorContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_declarator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(336);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==MUL) {
				{
				setState(335);
				pointer(0);
				}
			}

			setState(338);
			directDeclarator(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PointerContext extends ParserRuleContext {
		public PointerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pointer; }
	 
		public PointerContext() { }
		public void copyFrom(PointerContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class MultiplePrtContext extends PointerContext {
		public PointerContext pointer() {
			return getRuleContext(PointerContext.class,0);
		}
		public MultiplePrtContext(PointerContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).enterMultiplePrt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).exitMultiplePrt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof picoCVisitor ) return ((picoCVisitor<? extends T>)visitor).visitMultiplePrt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SimplePtrContext extends PointerContext {
		public SimplePtrContext(PointerContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).enterSimplePtr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).exitSimplePtr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof picoCVisitor ) return ((picoCVisitor<? extends T>)visitor).visitSimplePtr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PointerContext pointer() throws RecognitionException {
		return pointer(0);
	}

	private PointerContext pointer(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		PointerContext _localctx = new PointerContext(_ctx, _parentState);
		PointerContext _prevctx = _localctx;
		int _startState = 52;
		enterRecursionRule(_localctx, 52, RULE_pointer, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			_localctx = new SimplePtrContext(_localctx);
			_ctx = _localctx;
			_prevctx = _localctx;

			setState(341);
			match(MUL);
			}
			_ctx.stop = _input.LT(-1);
			setState(347);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new MultiplePrtContext(new PointerContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_pointer);
					setState(343);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(344);
					match(MUL);
					}
					} 
				}
				setState(349);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class DirectDeclaratorContext extends ParserRuleContext {
		public DirectDeclaratorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_directDeclarator; }
	 
		public DirectDeclaratorContext() { }
		public void copyFrom(DirectDeclaratorContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class DirDeclContext extends DirectDeclaratorContext {
		public TerminalNode ID() { return getToken(picoCParser.ID, 0); }
		public DirDeclContext(DirectDeclaratorContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).enterDirDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).exitDirDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof picoCVisitor ) return ((picoCVisitor<? extends T>)visitor).visitDirDecl(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ArrayDeclContext extends DirectDeclaratorContext {
		public DirectDeclaratorContext directDeclarator() {
			return getRuleContext(DirectDeclaratorContext.class,0);
		}
		public ConstantContext constant() {
			return getRuleContext(ConstantContext.class,0);
		}
		public ArrayDeclContext(DirectDeclaratorContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).enterArrayDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).exitArrayDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof picoCVisitor ) return ((picoCVisitor<? extends T>)visitor).visitArrayDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DirectDeclaratorContext directDeclarator() throws RecognitionException {
		return directDeclarator(0);
	}

	private DirectDeclaratorContext directDeclarator(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		DirectDeclaratorContext _localctx = new DirectDeclaratorContext(_ctx, _parentState);
		DirectDeclaratorContext _prevctx = _localctx;
		int _startState = 54;
		enterRecursionRule(_localctx, 54, RULE_directDeclarator, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			_localctx = new DirDeclContext(_localctx);
			_ctx = _localctx;
			_prevctx = _localctx;

			setState(351);
			match(ID);
			}
			_ctx.stop = _input.LT(-1);
			setState(361);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ArrayDeclContext(new DirectDeclaratorContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_directDeclarator);
					setState(353);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(354);
					match(T__3);
					setState(356);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==INT || _la==CHAR) {
						{
						setState(355);
						constant();
						}
					}

					setState(358);
					match(T__4);
					}
					} 
				}
				setState(363);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ParameterListContext extends ParserRuleContext {
		public List<ParameterContext> parameter() {
			return getRuleContexts(ParameterContext.class);
		}
		public ParameterContext parameter(int i) {
			return getRuleContext(ParameterContext.class,i);
		}
		public ParameterListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameterList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).enterParameterList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).exitParameterList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof picoCVisitor ) return ((picoCVisitor<? extends T>)visitor).visitParameterList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParameterListContext parameterList() throws RecognitionException {
		ParameterListContext _localctx = new ParameterListContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_parameterList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(364);
			parameter();
			setState(369);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__14) {
				{
				{
				setState(365);
				match(T__14);
				setState(366);
				parameter();
				}
				}
				setState(371);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParameterContext extends ParserRuleContext {
		public TypeSpecifierContext typeSpecifier() {
			return getRuleContext(TypeSpecifierContext.class,0);
		}
		public DeclaratorContext declarator() {
			return getRuleContext(DeclaratorContext.class,0);
		}
		public ParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).enterParameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).exitParameter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof picoCVisitor ) return ((picoCVisitor<? extends T>)visitor).visitParameter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParameterContext parameter() throws RecognitionException {
		ParameterContext _localctx = new ParameterContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_parameter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(372);
			typeSpecifier();
			setState(373);
			declarator();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionBodyContext extends ParserRuleContext {
		public CompoundStatementContext compoundStatement() {
			return getRuleContext(CompoundStatementContext.class,0);
		}
		public FunctionBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).enterFunctionBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).exitFunctionBody(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof picoCVisitor ) return ((picoCVisitor<? extends T>)visitor).visitFunctionBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionBodyContext functionBody() throws RecognitionException {
		FunctionBodyContext _localctx = new FunctionBodyContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_functionBody);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(375);
			compoundStatement();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public CompoundStatementContext compoundStatement() {
			return getRuleContext(CompoundStatementContext.class,0);
		}
		public ExpressionStatementContext expressionStatement() {
			return getRuleContext(ExpressionStatementContext.class,0);
		}
		public SelectionStatementContext selectionStatement() {
			return getRuleContext(SelectionStatementContext.class,0);
		}
		public IterationStatementContext iterationStatement() {
			return getRuleContext(IterationStatementContext.class,0);
		}
		public JumpStatementContext jumpStatement() {
			return getRuleContext(JumpStatementContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).exitStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof picoCVisitor ) return ((picoCVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_statement);
		try {
			setState(382);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__18:
				enterOuterAlt(_localctx, 1);
				{
				setState(377);
				compoundStatement();
				}
				break;
			case T__0:
			case T__1:
			case T__5:
			case T__6:
			case T__7:
			case T__8:
			case T__9:
			case MUL:
			case ADD:
			case SUB:
			case ID:
			case INT:
			case CHAR:
			case STRING_LITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(378);
				expressionStatement();
				}
				break;
			case T__20:
				enterOuterAlt(_localctx, 3);
				{
				setState(379);
				selectionStatement();
				}
				break;
			case T__22:
			case T__23:
			case T__24:
				enterOuterAlt(_localctx, 4);
				{
				setState(380);
				iterationStatement();
				}
				break;
			case T__15:
			case T__16:
			case T__17:
				enterOuterAlt(_localctx, 5);
				{
				setState(381);
				jumpStatement();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class JumpStatementContext extends ParserRuleContext {
		public JumpStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_jumpStatement; }
	 
		public JumpStatementContext() { }
		public void copyFrom(JumpStatementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ReturnContext extends JumpStatementContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ReturnContext(JumpStatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).enterReturn(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).exitReturn(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof picoCVisitor ) return ((picoCVisitor<? extends T>)visitor).visitReturn(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BreakContext extends JumpStatementContext {
		public BreakContext(JumpStatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).enterBreak(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).exitBreak(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof picoCVisitor ) return ((picoCVisitor<? extends T>)visitor).visitBreak(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ContinueContext extends JumpStatementContext {
		public ContinueContext(JumpStatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).enterContinue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).exitContinue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof picoCVisitor ) return ((picoCVisitor<? extends T>)visitor).visitContinue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final JumpStatementContext jumpStatement() throws RecognitionException {
		JumpStatementContext _localctx = new JumpStatementContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_jumpStatement);
		int _la;
		try {
			setState(393);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__15:
				_localctx = new ReturnContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(384);
				match(T__15);
				setState(386);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << MUL) | (1L << ADD) | (1L << SUB) | (1L << ID) | (1L << INT) | (1L << CHAR) | (1L << STRING_LITERAL))) != 0)) {
					{
					setState(385);
					expression(0);
					}
				}

				setState(388);
				match(T__0);
				}
				break;
			case T__16:
				_localctx = new BreakContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(389);
				match(T__16);
				setState(390);
				match(T__0);
				}
				break;
			case T__17:
				_localctx = new ContinueContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(391);
				match(T__17);
				setState(392);
				match(T__0);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CompoundStatementContext extends ParserRuleContext {
		public BlockItemListContext blockItemList() {
			return getRuleContext(BlockItemListContext.class,0);
		}
		public CompoundStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compoundStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).enterCompoundStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).exitCompoundStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof picoCVisitor ) return ((picoCVisitor<? extends T>)visitor).visitCompoundStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CompoundStatementContext compoundStatement() throws RecognitionException {
		CompoundStatementContext _localctx = new CompoundStatementContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_compoundStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(395);
			match(T__18);
			setState(397);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__20) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << VOIDTYPE) | (1L << INTTYPE) | (1L << CHARTYPE) | (1L << MUL) | (1L << ADD) | (1L << SUB) | (1L << ID) | (1L << INT) | (1L << CHAR) | (1L << STRING_LITERAL))) != 0)) {
				{
				setState(396);
				blockItemList(0);
				}
			}

			setState(399);
			match(T__19);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlockItemListContext extends ParserRuleContext {
		public BlockItemContext blockItem() {
			return getRuleContext(BlockItemContext.class,0);
		}
		public BlockItemListContext blockItemList() {
			return getRuleContext(BlockItemListContext.class,0);
		}
		public BlockItemListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_blockItemList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).enterBlockItemList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).exitBlockItemList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof picoCVisitor ) return ((picoCVisitor<? extends T>)visitor).visitBlockItemList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockItemListContext blockItemList() throws RecognitionException {
		return blockItemList(0);
	}

	private BlockItemListContext blockItemList(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		BlockItemListContext _localctx = new BlockItemListContext(_ctx, _parentState);
		BlockItemListContext _prevctx = _localctx;
		int _startState = 68;
		enterRecursionRule(_localctx, 68, RULE_blockItemList, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(402);
			blockItem();
			}
			_ctx.stop = _input.LT(-1);
			setState(408);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,34,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new BlockItemListContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_blockItemList);
					setState(404);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(405);
					blockItem();
					}
					} 
				}
				setState(410);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,34,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class BlockItemContext extends ParserRuleContext {
		public DeclarationContext declaration() {
			return getRuleContext(DeclarationContext.class,0);
		}
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public BlockItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_blockItem; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).enterBlockItem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).exitBlockItem(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof picoCVisitor ) return ((picoCVisitor<? extends T>)visitor).visitBlockItem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockItemContext blockItem() throws RecognitionException {
		BlockItemContext _localctx = new BlockItemContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_blockItem);
		try {
			setState(413);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case VOIDTYPE:
			case INTTYPE:
			case CHARTYPE:
				enterOuterAlt(_localctx, 1);
				{
				setState(411);
				declaration();
				}
				break;
			case T__0:
			case T__1:
			case T__5:
			case T__6:
			case T__7:
			case T__8:
			case T__9:
			case T__15:
			case T__16:
			case T__17:
			case T__18:
			case T__20:
			case T__22:
			case T__23:
			case T__24:
			case MUL:
			case ADD:
			case SUB:
			case ID:
			case INT:
			case CHAR:
			case STRING_LITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(412);
				statement();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionStatementContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExpressionStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).enterExpressionStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).exitExpressionStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof picoCVisitor ) return ((picoCVisitor<? extends T>)visitor).visitExpressionStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionStatementContext expressionStatement() throws RecognitionException {
		ExpressionStatementContext _localctx = new ExpressionStatementContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_expressionStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(416);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << MUL) | (1L << ADD) | (1L << SUB) | (1L << ID) | (1L << INT) | (1L << CHAR) | (1L << STRING_LITERAL))) != 0)) {
				{
				setState(415);
				expression(0);
				}
			}

			setState(418);
			match(T__0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SelectionStatementContext extends ParserRuleContext {
		public AssignmentExpressionContext assignmentExpression() {
			return getRuleContext(AssignmentExpressionContext.class,0);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public SelectionStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selectionStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).enterSelectionStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).exitSelectionStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof picoCVisitor ) return ((picoCVisitor<? extends T>)visitor).visitSelectionStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SelectionStatementContext selectionStatement() throws RecognitionException {
		SelectionStatementContext _localctx = new SelectionStatementContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_selectionStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(420);
			match(T__20);
			setState(421);
			match(T__1);
			setState(422);
			assignmentExpression();
			setState(423);
			match(T__2);
			setState(424);
			statement();
			setState(427);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,37,_ctx) ) {
			case 1:
				{
				setState(425);
				match(T__21);
				setState(426);
				statement();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IterationStatementContext extends ParserRuleContext {
		public IterationStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_iterationStatement; }
	 
		public IterationStatementContext() { }
		public void copyFrom(IterationStatementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ForLoopContext extends IterationStatementContext {
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public ForInitContext forInit() {
			return getRuleContext(ForInitContext.class,0);
		}
		public ForCheckContext forCheck() {
			return getRuleContext(ForCheckContext.class,0);
		}
		public ForIncContext forInc() {
			return getRuleContext(ForIncContext.class,0);
		}
		public ForLoopContext(IterationStatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).enterForLoop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).exitForLoop(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof picoCVisitor ) return ((picoCVisitor<? extends T>)visitor).visitForLoop(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DoWhileLoopContext extends IterationStatementContext {
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public WhileCheckContext whileCheck() {
			return getRuleContext(WhileCheckContext.class,0);
		}
		public DoWhileLoopContext(IterationStatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).enterDoWhileLoop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).exitDoWhileLoop(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof picoCVisitor ) return ((picoCVisitor<? extends T>)visitor).visitDoWhileLoop(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class WhileLoopContext extends IterationStatementContext {
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public WhileCheckContext whileCheck() {
			return getRuleContext(WhileCheckContext.class,0);
		}
		public WhileLoopContext(IterationStatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).enterWhileLoop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).exitWhileLoop(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof picoCVisitor ) return ((picoCVisitor<? extends T>)visitor).visitWhileLoop(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IterationStatementContext iterationStatement() throws RecognitionException {
		IterationStatementContext _localctx = new IterationStatementContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_iterationStatement);
		int _la;
		try {
			setState(461);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__22:
				_localctx = new ForLoopContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(429);
				match(T__22);
				setState(430);
				match(T__1);
				setState(432);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << MUL) | (1L << ADD) | (1L << SUB) | (1L << ID) | (1L << INT) | (1L << CHAR) | (1L << STRING_LITERAL))) != 0)) {
					{
					setState(431);
					forInit();
					}
				}

				setState(434);
				match(T__0);
				setState(436);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << MUL) | (1L << ADD) | (1L << SUB) | (1L << ID) | (1L << INT) | (1L << CHAR) | (1L << STRING_LITERAL))) != 0)) {
					{
					setState(435);
					forCheck();
					}
				}

				setState(438);
				match(T__0);
				setState(440);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << MUL) | (1L << ADD) | (1L << SUB) | (1L << ID) | (1L << INT) | (1L << CHAR) | (1L << STRING_LITERAL))) != 0)) {
					{
					setState(439);
					forInc();
					}
				}

				setState(442);
				match(T__2);
				setState(443);
				statement();
				}
				break;
			case T__23:
				_localctx = new WhileLoopContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(444);
				match(T__23);
				setState(445);
				match(T__1);
				setState(447);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << MUL) | (1L << ADD) | (1L << SUB) | (1L << ID) | (1L << INT) | (1L << CHAR) | (1L << STRING_LITERAL))) != 0)) {
					{
					setState(446);
					whileCheck();
					}
				}

				setState(449);
				match(T__2);
				setState(450);
				statement();
				}
				break;
			case T__24:
				_localctx = new DoWhileLoopContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(451);
				match(T__24);
				setState(452);
				statement();
				setState(453);
				match(T__23);
				setState(454);
				match(T__1);
				setState(456);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << MUL) | (1L << ADD) | (1L << SUB) | (1L << ID) | (1L << INT) | (1L << CHAR) | (1L << STRING_LITERAL))) != 0)) {
					{
					setState(455);
					whileCheck();
					}
				}

				setState(458);
				match(T__2);
				setState(459);
				match(T__0);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ForInitContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ForInitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forInit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).enterForInit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).exitForInit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof picoCVisitor ) return ((picoCVisitor<? extends T>)visitor).visitForInit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForInitContext forInit() throws RecognitionException {
		ForInitContext _localctx = new ForInitContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_forInit);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(463);
			expression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ForCheckContext extends ParserRuleContext {
		public AssignmentExpressionContext assignmentExpression() {
			return getRuleContext(AssignmentExpressionContext.class,0);
		}
		public ForCheckContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forCheck; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).enterForCheck(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).exitForCheck(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof picoCVisitor ) return ((picoCVisitor<? extends T>)visitor).visitForCheck(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForCheckContext forCheck() throws RecognitionException {
		ForCheckContext _localctx = new ForCheckContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_forCheck);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(465);
			assignmentExpression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ForIncContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ForIncContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forInc; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).enterForInc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).exitForInc(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof picoCVisitor ) return ((picoCVisitor<? extends T>)visitor).visitForInc(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForIncContext forInc() throws RecognitionException {
		ForIncContext _localctx = new ForIncContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_forInc);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(467);
			expression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class WhileCheckContext extends ParserRuleContext {
		public AssignmentExpressionContext assignmentExpression() {
			return getRuleContext(AssignmentExpressionContext.class,0);
		}
		public WhileCheckContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whileCheck; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).enterWhileCheck(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).exitWhileCheck(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof picoCVisitor ) return ((picoCVisitor<? extends T>)visitor).visitWhileCheck(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WhileCheckContext whileCheck() throws RecognitionException {
		WhileCheckContext _localctx = new WhileCheckContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_whileCheck);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(469);
			assignmentExpression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArgumentListContext extends ParserRuleContext {
		public List<ArgumentContext> argument() {
			return getRuleContexts(ArgumentContext.class);
		}
		public ArgumentContext argument(int i) {
			return getRuleContext(ArgumentContext.class,i);
		}
		public ArgumentListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argumentList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).enterArgumentList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).exitArgumentList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof picoCVisitor ) return ((picoCVisitor<? extends T>)visitor).visitArgumentList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgumentListContext argumentList() throws RecognitionException {
		ArgumentListContext _localctx = new ArgumentListContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_argumentList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(471);
			argument();
			setState(476);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__14) {
				{
				{
				setState(472);
				match(T__14);
				setState(473);
				argument();
				}
				}
				setState(478);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArgumentContext extends ParserRuleContext {
		public AssignmentExpressionContext assignmentExpression() {
			return getRuleContext(AssignmentExpressionContext.class,0);
		}
		public ArgumentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argument; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).enterArgument(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).exitArgument(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof picoCVisitor ) return ((picoCVisitor<? extends T>)visitor).visitArgument(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgumentContext argument() throws RecognitionException {
		ArgumentContext _localctx = new ArgumentContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_argument);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(479);
			assignmentExpression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstantContext extends ParserRuleContext {
		public ConstantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constant; }
	 
		public ConstantContext() { }
		public void copyFrom(ConstantContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class CharContext extends ConstantContext {
		public TerminalNode CHAR() { return getToken(picoCParser.CHAR, 0); }
		public CharContext(ConstantContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).enterChar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).exitChar(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof picoCVisitor ) return ((picoCVisitor<? extends T>)visitor).visitChar(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IntContext extends ConstantContext {
		public TerminalNode INT() { return getToken(picoCParser.INT, 0); }
		public IntContext(ConstantContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).enterInt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof picoCListener ) ((picoCListener)listener).exitInt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof picoCVisitor ) return ((picoCVisitor<? extends T>)visitor).visitInt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstantContext constant() throws RecognitionException {
		ConstantContext _localctx = new ConstantContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_constant);
		try {
			setState(483);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT:
				_localctx = new IntContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(481);
				match(INT);
				}
				break;
			case CHAR:
				_localctx = new CharContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(482);
				match(CHAR);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 1:
			return translationUnit_sempred((TranslationUnitContext)_localctx, predIndex);
		case 5:
			return postfixExpression_sempred((PostfixExpressionContext)_localctx, predIndex);
		case 8:
			return multiplicativeExpression_sempred((MultiplicativeExpressionContext)_localctx, predIndex);
		case 9:
			return additiveExpression_sempred((AdditiveExpressionContext)_localctx, predIndex);
		case 10:
			return relationalExpression_sempred((RelationalExpressionContext)_localctx, predIndex);
		case 11:
			return equalityExpression_sempred((EqualityExpressionContext)_localctx, predIndex);
		case 12:
			return andExpression_sempred((AndExpressionContext)_localctx, predIndex);
		case 13:
			return exclusiveOrExpression_sempred((ExclusiveOrExpressionContext)_localctx, predIndex);
		case 14:
			return inclusiveOrExpression_sempred((InclusiveOrExpressionContext)_localctx, predIndex);
		case 15:
			return logicalAndExpression_sempred((LogicalAndExpressionContext)_localctx, predIndex);
		case 16:
			return logicalOrExpression_sempred((LogicalOrExpressionContext)_localctx, predIndex);
		case 20:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		case 23:
			return initDeclarationList_sempred((InitDeclarationListContext)_localctx, predIndex);
		case 26:
			return pointer_sempred((PointerContext)_localctx, predIndex);
		case 27:
			return directDeclarator_sempred((DirectDeclaratorContext)_localctx, predIndex);
		case 34:
			return blockItemList_sempred((BlockItemListContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean translationUnit_sempred(TranslationUnitContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean postfixExpression_sempred(PostfixExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 4);
		case 2:
			return precpred(_ctx, 3);
		case 3:
			return precpred(_ctx, 2);
		case 4:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean multiplicativeExpression_sempred(MultiplicativeExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 5:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean additiveExpression_sempred(AdditiveExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 6:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean relationalExpression_sempred(RelationalExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 7:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean equalityExpression_sempred(EqualityExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 8:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean andExpression_sempred(AndExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 9:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean exclusiveOrExpression_sempred(ExclusiveOrExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 10:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean inclusiveOrExpression_sempred(InclusiveOrExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 11:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean logicalAndExpression_sempred(LogicalAndExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 12:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean logicalOrExpression_sempred(LogicalOrExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 13:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 14:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean initDeclarationList_sempred(InitDeclarationListContext _localctx, int predIndex) {
		switch (predIndex) {
		case 15:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean pointer_sempred(PointerContext _localctx, int predIndex) {
		switch (predIndex) {
		case 16:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean directDeclarator_sempred(DirectDeclaratorContext _localctx, int predIndex) {
		switch (predIndex) {
		case 17:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean blockItemList_sempred(BlockItemListContext _localctx, int predIndex) {
		switch (predIndex) {
		case 18:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\38\u01e8\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\3\2\5\2`\n\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\7\3i\n"+
		"\3\f\3\16\3l\13\3\3\4\3\4\3\4\5\4q\n\4\3\5\3\5\3\5\3\5\5\5w\n\5\3\5\3"+
		"\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6\u0083\n\6\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\3\7\3\7\5\7\u0090\n\7\3\7\3\7\3\7\3\7\3\7\7\7\u0097\n\7"+
		"\f\7\16\7\u009a\13\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\5\b\u00ad\n\b\3\t\3\t\3\t\3\t\5\t\u00b3\n\t\3\t\3\t"+
		"\3\t\5\t\u00b8\n\t\3\n\3\n\3\n\3\n\3\n\3\n\7\n\u00c0\n\n\f\n\16\n\u00c3"+
		"\13\n\3\13\3\13\3\13\3\13\3\13\3\13\7\13\u00cb\n\13\f\13\16\13\u00ce\13"+
		"\13\3\f\3\f\3\f\3\f\3\f\3\f\7\f\u00d6\n\f\f\f\16\f\u00d9\13\f\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\7\r\u00e1\n\r\f\r\16\r\u00e4\13\r\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\7\16\u00ec\n\16\f\16\16\16\u00ef\13\16\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\7\17\u00f7\n\17\f\17\16\17\u00fa\13\17\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\7\20\u0102\n\20\f\20\16\20\u0105\13\20\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\7\21\u010d\n\21\f\21\16\21\u0110\13\21\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\7\22\u0118\n\22\f\22\16\22\u011b\13\22\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\5\23\u0124\n\23\3\24\3\24\3\24\3\24\3\24\5\24\u012b\n"+
		"\24\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26\7\26\u0135\n\26\f\26\16\26"+
		"\u0138\13\26\3\27\3\27\3\27\3\27\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3"+
		"\31\7\31\u0146\n\31\f\31\16\31\u0149\13\31\3\32\3\32\3\32\3\32\3\32\5"+
		"\32\u0150\n\32\3\33\5\33\u0153\n\33\3\33\3\33\3\34\3\34\3\34\3\34\3\34"+
		"\7\34\u015c\n\34\f\34\16\34\u015f\13\34\3\35\3\35\3\35\3\35\3\35\3\35"+
		"\5\35\u0167\n\35\3\35\7\35\u016a\n\35\f\35\16\35\u016d\13\35\3\36\3\36"+
		"\3\36\7\36\u0172\n\36\f\36\16\36\u0175\13\36\3\37\3\37\3\37\3 \3 \3!\3"+
		"!\3!\3!\3!\5!\u0181\n!\3\"\3\"\5\"\u0185\n\"\3\"\3\"\3\"\3\"\3\"\5\"\u018c"+
		"\n\"\3#\3#\5#\u0190\n#\3#\3#\3$\3$\3$\3$\3$\7$\u0199\n$\f$\16$\u019c\13"+
		"$\3%\3%\5%\u01a0\n%\3&\5&\u01a3\n&\3&\3&\3\'\3\'\3\'\3\'\3\'\3\'\3\'\5"+
		"\'\u01ae\n\'\3(\3(\3(\5(\u01b3\n(\3(\3(\5(\u01b7\n(\3(\3(\5(\u01bb\n("+
		"\3(\3(\3(\3(\3(\5(\u01c2\n(\3(\3(\3(\3(\3(\3(\3(\5(\u01cb\n(\3(\3(\3("+
		"\5(\u01d0\n(\3)\3)\3*\3*\3+\3+\3,\3,\3-\3-\3-\7-\u01dd\n-\f-\16-\u01e0"+
		"\13-\3.\3.\3/\3/\5/\u01e6\n/\3/\2\22\4\f\22\24\26\30\32\34\36 \"*\60\66"+
		"8F\60\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>"+
		"@BDFHJLNPRTVXZ\\\2\b\3\2%\'\3\2()\3\2,/\3\2*+\3\2\37$\3\2\34\36\u01f8"+
		"\2_\3\2\2\2\4c\3\2\2\2\6p\3\2\2\2\br\3\2\2\2\n\u0082\3\2\2\2\f\u0084\3"+
		"\2\2\2\16\u00ac\3\2\2\2\20\u00b7\3\2\2\2\22\u00b9\3\2\2\2\24\u00c4\3\2"+
		"\2\2\26\u00cf\3\2\2\2\30\u00da\3\2\2\2\32\u00e5\3\2\2\2\34\u00f0\3\2\2"+
		"\2\36\u00fb\3\2\2\2 \u0106\3\2\2\2\"\u0111\3\2\2\2$\u0123\3\2\2\2&\u012a"+
		"\3\2\2\2(\u012c\3\2\2\2*\u012e\3\2\2\2,\u0139\3\2\2\2.\u013d\3\2\2\2\60"+
		"\u013f\3\2\2\2\62\u014f\3\2\2\2\64\u0152\3\2\2\2\66\u0156\3\2\2\28\u0160"+
		"\3\2\2\2:\u016e\3\2\2\2<\u0176\3\2\2\2>\u0179\3\2\2\2@\u0180\3\2\2\2B"+
		"\u018b\3\2\2\2D\u018d\3\2\2\2F\u0193\3\2\2\2H\u019f\3\2\2\2J\u01a2\3\2"+
		"\2\2L\u01a6\3\2\2\2N\u01cf\3\2\2\2P\u01d1\3\2\2\2R\u01d3\3\2\2\2T\u01d5"+
		"\3\2\2\2V\u01d7\3\2\2\2X\u01d9\3\2\2\2Z\u01e1\3\2\2\2\\\u01e5\3\2\2\2"+
		"^`\5\4\3\2_^\3\2\2\2_`\3\2\2\2`a\3\2\2\2ab\7\2\2\3b\3\3\2\2\2cd\b\3\1"+
		"\2de\5\6\4\2ej\3\2\2\2fg\f\3\2\2gi\5\6\4\2hf\3\2\2\2il\3\2\2\2jh\3\2\2"+
		"\2jk\3\2\2\2k\5\3\2\2\2lj\3\2\2\2mq\5\b\5\2nq\5,\27\2oq\7\3\2\2pm\3\2"+
		"\2\2pn\3\2\2\2po\3\2\2\2q\7\3\2\2\2rs\5.\30\2st\5\64\33\2tv\7\4\2\2uw"+
		"\5:\36\2vu\3\2\2\2vw\3\2\2\2wx\3\2\2\2xy\7\5\2\2yz\5> \2z\t\3\2\2\2{\u0083"+
		"\7\62\2\2|\u0083\5\\/\2}\u0083\7\65\2\2~\177\7\4\2\2\177\u0080\5*\26\2"+
		"\u0080\u0081\7\5\2\2\u0081\u0083\3\2\2\2\u0082{\3\2\2\2\u0082|\3\2\2\2"+
		"\u0082}\3\2\2\2\u0082~\3\2\2\2\u0083\13\3\2\2\2\u0084\u0085\b\7\1\2\u0085"+
		"\u0086\5\n\6\2\u0086\u0098\3\2\2\2\u0087\u0088\f\6\2\2\u0088\u0089\7\6"+
		"\2\2\u0089\u008a\5*\26\2\u008a\u008b\7\7\2\2\u008b\u0097\3\2\2\2\u008c"+
		"\u008d\f\5\2\2\u008d\u008f\7\4\2\2\u008e\u0090\5X-\2\u008f\u008e\3\2\2"+
		"\2\u008f\u0090\3\2\2\2\u0090\u0091\3\2\2\2\u0091\u0097\7\5\2\2\u0092\u0093"+
		"\f\4\2\2\u0093\u0097\7\b\2\2\u0094\u0095\f\3\2\2\u0095\u0097\7\t\2\2\u0096"+
		"\u0087\3\2\2\2\u0096\u008c\3\2\2\2\u0096\u0092\3\2\2\2\u0096\u0094\3\2"+
		"\2\2\u0097\u009a\3\2\2\2\u0098\u0096\3\2\2\2\u0098\u0099\3\2\2\2\u0099"+
		"\r\3\2\2\2\u009a\u0098\3\2\2\2\u009b\u00ad\5\f\7\2\u009c\u009d\7\b\2\2"+
		"\u009d\u00ad\5\16\b\2\u009e\u009f\7\t\2\2\u009f\u00ad\5\16\b\2\u00a0\u00a1"+
		"\7%\2\2\u00a1\u00ad\5\20\t\2\u00a2\u00a3\7\n\2\2\u00a3\u00ad\5\20\t\2"+
		"\u00a4\u00a5\7\13\2\2\u00a5\u00ad\5\20\t\2\u00a6\u00a7\7\f\2\2\u00a7\u00ad"+
		"\5\20\t\2\u00a8\u00a9\7)\2\2\u00a9\u00ad\5\20\t\2\u00aa\u00ab\7(\2\2\u00ab"+
		"\u00ad\5\20\t\2\u00ac\u009b\3\2\2\2\u00ac\u009c\3\2\2\2\u00ac\u009e\3"+
		"\2\2\2\u00ac\u00a0\3\2\2\2\u00ac\u00a2\3\2\2\2\u00ac\u00a4\3\2\2\2\u00ac"+
		"\u00a6\3\2\2\2\u00ac\u00a8\3\2\2\2\u00ac\u00aa\3\2\2\2\u00ad\17\3\2\2"+
		"\2\u00ae\u00b8\5\16\b\2\u00af\u00b0\7\4\2\2\u00b0\u00b2\5.\30\2\u00b1"+
		"\u00b3\5\66\34\2\u00b2\u00b1\3\2\2\2\u00b2\u00b3\3\2\2\2\u00b3\u00b4\3"+
		"\2\2\2\u00b4\u00b5\7\5\2\2\u00b5\u00b6\5\20\t\2\u00b6\u00b8\3\2\2\2\u00b7"+
		"\u00ae\3\2\2\2\u00b7\u00af\3\2\2\2\u00b8\21\3\2\2\2\u00b9\u00ba\b\n\1"+
		"\2\u00ba\u00bb\5\20\t\2\u00bb\u00c1\3\2\2\2\u00bc\u00bd\f\3\2\2\u00bd"+
		"\u00be\t\2\2\2\u00be\u00c0\5\16\b\2\u00bf\u00bc\3\2\2\2\u00c0\u00c3\3"+
		"\2\2\2\u00c1\u00bf\3\2\2\2\u00c1\u00c2\3\2\2\2\u00c2\23\3\2\2\2\u00c3"+
		"\u00c1\3\2\2\2\u00c4\u00c5\b\13\1\2\u00c5\u00c6\5\22\n\2\u00c6\u00cc\3"+
		"\2\2\2\u00c7\u00c8\f\3\2\2\u00c8\u00c9\t\3\2\2\u00c9\u00cb\5\22\n\2\u00ca"+
		"\u00c7\3\2\2\2\u00cb\u00ce\3\2\2\2\u00cc\u00ca\3\2\2\2\u00cc\u00cd\3\2"+
		"\2\2\u00cd\25\3\2\2\2\u00ce\u00cc\3\2\2\2\u00cf\u00d0\b\f\1\2\u00d0\u00d1"+
		"\5\24\13\2\u00d1\u00d7\3\2\2\2\u00d2\u00d3\f\3\2\2\u00d3\u00d4\t\4\2\2"+
		"\u00d4\u00d6\5\24\13\2\u00d5\u00d2\3\2\2\2\u00d6\u00d9\3\2\2\2\u00d7\u00d5"+
		"\3\2\2\2\u00d7\u00d8\3\2\2\2\u00d8\27\3\2\2\2\u00d9\u00d7\3\2\2\2\u00da"+
		"\u00db\b\r\1\2\u00db\u00dc\5\26\f\2\u00dc\u00e2\3\2\2\2\u00dd\u00de\f"+
		"\3\2\2\u00de\u00df\t\5\2\2\u00df\u00e1\5\26\f\2\u00e0\u00dd\3\2\2\2\u00e1"+
		"\u00e4\3\2\2\2\u00e2\u00e0\3\2\2\2\u00e2\u00e3\3\2\2\2\u00e3\31\3\2\2"+
		"\2\u00e4\u00e2\3\2\2\2\u00e5\u00e6\b\16\1\2\u00e6\u00e7\5\30\r\2\u00e7"+
		"\u00ed\3\2\2\2\u00e8\u00e9\f\3\2\2\u00e9\u00ea\7\13\2\2\u00ea\u00ec\5"+
		"\30\r\2\u00eb\u00e8\3\2\2\2\u00ec\u00ef\3\2\2\2\u00ed\u00eb\3\2\2\2\u00ed"+
		"\u00ee\3\2\2\2\u00ee\33\3\2\2\2\u00ef\u00ed\3\2\2\2\u00f0\u00f1\b\17\1"+
		"\2\u00f1\u00f2\5\32\16\2\u00f2\u00f8\3\2\2\2\u00f3\u00f4\f\3\2\2\u00f4"+
		"\u00f5\7\r\2\2\u00f5\u00f7\5\32\16\2\u00f6\u00f3\3\2\2\2\u00f7\u00fa\3"+
		"\2\2\2\u00f8\u00f6\3\2\2\2\u00f8\u00f9\3\2\2\2\u00f9\35\3\2\2\2\u00fa"+
		"\u00f8\3\2\2\2\u00fb\u00fc\b\20\1\2\u00fc\u00fd\5\34\17\2\u00fd\u0103"+
		"\3\2\2\2\u00fe\u00ff\f\3\2\2\u00ff\u0100\7\16\2\2\u0100\u0102\5\34\17"+
		"\2\u0101\u00fe\3\2\2\2\u0102\u0105\3\2\2\2\u0103\u0101\3\2\2\2\u0103\u0104"+
		"\3\2\2\2\u0104\37\3\2\2\2\u0105\u0103\3\2\2\2\u0106\u0107\b\21\1\2\u0107"+
		"\u0108\5\36\20\2\u0108\u010e\3\2\2\2\u0109\u010a\f\3\2\2\u010a\u010b\7"+
		"\60\2\2\u010b\u010d\5\30\r\2\u010c\u0109\3\2\2\2\u010d\u0110\3\2\2\2\u010e"+
		"\u010c\3\2\2\2\u010e\u010f\3\2\2\2\u010f!\3\2\2\2\u0110\u010e\3\2\2\2"+
		"\u0111\u0112\b\22\1\2\u0112\u0113\5 \21\2\u0113\u0119\3\2\2\2\u0114\u0115"+
		"\f\3\2\2\u0115\u0116\7\61\2\2\u0116\u0118\5 \21\2\u0117\u0114\3\2\2\2"+
		"\u0118\u011b\3\2\2\2\u0119\u0117\3\2\2\2\u0119\u011a\3\2\2\2\u011a#\3"+
		"\2\2\2\u011b\u0119\3\2\2\2\u011c\u0124\5\"\22\2\u011d\u011e\5\"\22\2\u011e"+
		"\u011f\7\17\2\2\u011f\u0120\5*\26\2\u0120\u0121\7\20\2\2\u0121\u0122\5"+
		"$\23\2\u0122\u0124\3\2\2\2\u0123\u011c\3\2\2\2\u0123\u011d\3\2\2\2\u0124"+
		"%\3\2\2\2\u0125\u012b\5$\23\2\u0126\u0127\5\16\b\2\u0127\u0128\5(\25\2"+
		"\u0128\u0129\5&\24\2\u0129\u012b\3\2\2\2\u012a\u0125\3\2\2\2\u012a\u0126"+
		"\3\2\2\2\u012b\'\3\2\2\2\u012c\u012d\t\6\2\2\u012d)\3\2\2\2\u012e\u012f"+
		"\b\26\1\2\u012f\u0130\5&\24\2\u0130\u0136\3\2\2\2\u0131\u0132\f\3\2\2"+
		"\u0132\u0133\7\21\2\2\u0133\u0135\5&\24\2\u0134\u0131\3\2\2\2\u0135\u0138"+
		"\3\2\2\2\u0136\u0134\3\2\2\2\u0136\u0137\3\2\2\2\u0137+\3\2\2\2\u0138"+
		"\u0136\3\2\2\2\u0139\u013a\5.\30\2\u013a\u013b\5\60\31\2\u013b\u013c\7"+
		"\3\2\2\u013c-\3\2\2\2\u013d\u013e\t\7\2\2\u013e/\3\2\2\2\u013f\u0140\b"+
		"\31\1\2\u0140\u0141\5\62\32\2\u0141\u0147\3\2\2\2\u0142\u0143\f\3\2\2"+
		"\u0143\u0144\7\21\2\2\u0144\u0146\5\62\32\2\u0145\u0142\3\2\2\2\u0146"+
		"\u0149\3\2\2\2\u0147\u0145\3\2\2\2\u0147\u0148\3\2\2\2\u0148\61\3\2\2"+
		"\2\u0149\u0147\3\2\2\2\u014a\u0150\5\64\33\2\u014b\u014c\5\64\33\2\u014c"+
		"\u014d\7\37\2\2\u014d\u014e\5&\24\2\u014e\u0150\3\2\2\2\u014f\u014a\3"+
		"\2\2\2\u014f\u014b\3\2\2\2\u0150\63\3\2\2\2\u0151\u0153\5\66\34\2\u0152"+
		"\u0151\3\2\2\2\u0152\u0153\3\2\2\2\u0153\u0154\3\2\2\2\u0154\u0155\58"+
		"\35\2\u0155\65\3\2\2\2\u0156\u0157\b\34\1\2\u0157\u0158\7%\2\2\u0158\u015d"+
		"\3\2\2\2\u0159\u015a\f\3\2\2\u015a\u015c\7%\2\2\u015b\u0159\3\2\2\2\u015c"+
		"\u015f\3\2\2\2\u015d\u015b\3\2\2\2\u015d\u015e\3\2\2\2\u015e\67\3\2\2"+
		"\2\u015f\u015d\3\2\2\2\u0160\u0161\b\35\1\2\u0161\u0162\7\62\2\2\u0162"+
		"\u016b\3\2\2\2\u0163\u0164\f\3\2\2\u0164\u0166\7\6\2\2\u0165\u0167\5\\"+
		"/\2\u0166\u0165\3\2\2\2\u0166\u0167\3\2\2\2\u0167\u0168\3\2\2\2\u0168"+
		"\u016a\7\7\2\2\u0169\u0163\3\2\2\2\u016a\u016d\3\2\2\2\u016b\u0169\3\2"+
		"\2\2\u016b\u016c\3\2\2\2\u016c9\3\2\2\2\u016d\u016b\3\2\2\2\u016e\u0173"+
		"\5<\37\2\u016f\u0170\7\21\2\2\u0170\u0172\5<\37\2\u0171\u016f\3\2\2\2"+
		"\u0172\u0175\3\2\2\2\u0173\u0171\3\2\2\2\u0173\u0174\3\2\2\2\u0174;\3"+
		"\2\2\2\u0175\u0173\3\2\2\2\u0176\u0177\5.\30\2\u0177\u0178\5\64\33\2\u0178"+
		"=\3\2\2\2\u0179\u017a\5D#\2\u017a?\3\2\2\2\u017b\u0181\5D#\2\u017c\u0181"+
		"\5J&\2\u017d\u0181\5L\'\2\u017e\u0181\5N(\2\u017f\u0181\5B\"\2\u0180\u017b"+
		"\3\2\2\2\u0180\u017c\3\2\2\2\u0180\u017d\3\2\2\2\u0180\u017e\3\2\2\2\u0180"+
		"\u017f\3\2\2\2\u0181A\3\2\2\2\u0182\u0184\7\22\2\2\u0183\u0185\5*\26\2"+
		"\u0184\u0183\3\2\2\2\u0184\u0185\3\2\2\2\u0185\u0186\3\2\2\2\u0186\u018c"+
		"\7\3\2\2\u0187\u0188\7\23\2\2\u0188\u018c\7\3\2\2\u0189\u018a\7\24\2\2"+
		"\u018a\u018c\7\3\2\2\u018b\u0182\3\2\2\2\u018b\u0187\3\2\2\2\u018b\u0189"+
		"\3\2\2\2\u018cC\3\2\2\2\u018d\u018f\7\25\2\2\u018e\u0190\5F$\2\u018f\u018e"+
		"\3\2\2\2\u018f\u0190\3\2\2\2\u0190\u0191\3\2\2\2\u0191\u0192\7\26\2\2"+
		"\u0192E\3\2\2\2\u0193\u0194\b$\1\2\u0194\u0195\5H%\2\u0195\u019a\3\2\2"+
		"\2\u0196\u0197\f\3\2\2\u0197\u0199\5H%\2\u0198\u0196\3\2\2\2\u0199\u019c"+
		"\3\2\2\2\u019a\u0198\3\2\2\2\u019a\u019b\3\2\2\2\u019bG\3\2\2\2\u019c"+
		"\u019a\3\2\2\2\u019d\u01a0\5,\27\2\u019e\u01a0\5@!\2\u019f\u019d\3\2\2"+
		"\2\u019f\u019e\3\2\2\2\u01a0I\3\2\2\2\u01a1\u01a3\5*\26\2\u01a2\u01a1"+
		"\3\2\2\2\u01a2\u01a3\3\2\2\2\u01a3\u01a4\3\2\2\2\u01a4\u01a5\7\3\2\2\u01a5"+
		"K\3\2\2\2\u01a6\u01a7\7\27\2\2\u01a7\u01a8\7\4\2\2\u01a8\u01a9\5&\24\2"+
		"\u01a9\u01aa\7\5\2\2\u01aa\u01ad\5@!\2\u01ab\u01ac\7\30\2\2\u01ac\u01ae"+
		"\5@!\2\u01ad\u01ab\3\2\2\2\u01ad\u01ae\3\2\2\2\u01aeM\3\2\2\2\u01af\u01b0"+
		"\7\31\2\2\u01b0\u01b2\7\4\2\2\u01b1\u01b3\5P)\2\u01b2\u01b1\3\2\2\2\u01b2"+
		"\u01b3\3\2\2\2\u01b3\u01b4\3\2\2\2\u01b4\u01b6\7\3\2\2\u01b5\u01b7\5R"+
		"*\2\u01b6\u01b5\3\2\2\2\u01b6\u01b7\3\2\2\2\u01b7\u01b8\3\2\2\2\u01b8"+
		"\u01ba\7\3\2\2\u01b9\u01bb\5T+\2\u01ba\u01b9\3\2\2\2\u01ba\u01bb\3\2\2"+
		"\2\u01bb\u01bc\3\2\2\2\u01bc\u01bd\7\5\2\2\u01bd\u01d0\5@!\2\u01be\u01bf"+
		"\7\32\2\2\u01bf\u01c1\7\4\2\2\u01c0\u01c2\5V,\2\u01c1\u01c0\3\2\2\2\u01c1"+
		"\u01c2\3\2\2\2\u01c2\u01c3\3\2\2\2\u01c3\u01c4\7\5\2\2\u01c4\u01d0\5@"+
		"!\2\u01c5\u01c6\7\33\2\2\u01c6\u01c7\5@!\2\u01c7\u01c8\7\32\2\2\u01c8"+
		"\u01ca\7\4\2\2\u01c9\u01cb\5V,\2\u01ca\u01c9\3\2\2\2\u01ca\u01cb\3\2\2"+
		"\2\u01cb\u01cc\3\2\2\2\u01cc\u01cd\7\5\2\2\u01cd\u01ce\7\3\2\2\u01ce\u01d0"+
		"\3\2\2\2\u01cf\u01af\3\2\2\2\u01cf\u01be\3\2\2\2\u01cf\u01c5\3\2\2\2\u01d0"+
		"O\3\2\2\2\u01d1\u01d2\5*\26\2\u01d2Q\3\2\2\2\u01d3\u01d4\5&\24\2\u01d4"+
		"S\3\2\2\2\u01d5\u01d6\5*\26\2\u01d6U\3\2\2\2\u01d7\u01d8\5&\24\2\u01d8"+
		"W\3\2\2\2\u01d9\u01de\5Z.\2\u01da\u01db\7\21\2\2\u01db\u01dd\5Z.\2\u01dc"+
		"\u01da\3\2\2\2\u01dd\u01e0\3\2\2\2\u01de\u01dc\3\2\2\2\u01de\u01df\3\2"+
		"\2\2\u01dfY\3\2\2\2\u01e0\u01de\3\2\2\2\u01e1\u01e2\5&\24\2\u01e2[\3\2"+
		"\2\2\u01e3\u01e6\7\63\2\2\u01e4\u01e6\7\64\2\2\u01e5\u01e3\3\2\2\2\u01e5"+
		"\u01e4\3\2\2\2\u01e6]\3\2\2\2\60_jpv\u0082\u008f\u0096\u0098\u00ac\u00b2"+
		"\u00b7\u00c1\u00cc\u00d7\u00e2\u00ed\u00f8\u0103\u010e\u0119\u0123\u012a"+
		"\u0136\u0147\u014f\u0152\u015d\u0166\u016b\u0173\u0180\u0184\u018b\u018f"+
		"\u019a\u019f\u01a2\u01ad\u01b2\u01b6\u01ba\u01c1\u01ca\u01cf\u01de\u01e5";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}