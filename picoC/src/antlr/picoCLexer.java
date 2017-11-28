// Generated from /home/aleksandar/NetBeansProjects/Clone/picoCcompilerCloneRemote/picoC/grammar/picoC.g4 by ANTLR 4.6
package antlr;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class picoCLexer extends Lexer {
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
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
		"T__17", "T__18", "T__19", "T__20", "T__21", "T__22", "T__23", "T__24", 
		"VOIDTYPE", "INTTYPE", "CHARTYPE", "ASSIGN", "ASSIGN_ADD", "ASSIGN_SUB", 
		"ASSIGN_MUL", "ASSIGN_DIV", "ASSIGN_MOD", "MUL", "DIV", "MOD", "ADD", 
		"SUB", "EQUAL", "NOT_EQUAL", "LESS", "LESS_EQUAL", "GREATER", "GREATER_EQUAL", 
		"LOGICAL_AND", "LOGICAL_OR", "ID", "INT", "CHAR", "STRING_LITERAL", "ESC", 
		"MULTY_LINE_COMMENT", "SINGLE_LINE_COMMENT", "WHITE_SPACE"
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


	public picoCLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "picoC.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\28\u0153\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3"+
		"\5\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3"+
		"\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\30"+
		"\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\33\3\33"+
		"\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\36\3\36"+
		"\3\37\3\37\3\37\3 \3 \3 \3!\3!\3!\3\"\3\"\3\"\3#\3#\3#\3$\3$\3%\3%\3&"+
		"\3&\3\'\3\'\3(\3(\3)\3)\3)\3*\3*\3*\3+\3+\3,\3,\3,\3-\3-\3.\3.\3.\3/\3"+
		"/\3/\3\60\3\60\3\60\3\61\3\61\6\61\u0102\n\61\r\61\16\61\u0103\3\61\6"+
		"\61\u0107\n\61\r\61\16\61\u0108\7\61\u010b\n\61\f\61\16\61\u010e\13\61"+
		"\3\62\6\62\u0111\n\62\r\62\16\62\u0112\3\63\3\63\3\63\3\63\3\63\3\63\3"+
		"\63\3\63\5\63\u011d\n\63\3\64\3\64\3\64\7\64\u0122\n\64\f\64\16\64\u0125"+
		"\13\64\3\64\3\64\3\65\3\65\3\65\3\65\5\65\u012d\n\65\3\66\3\66\3\66\3"+
		"\66\7\66\u0133\n\66\f\66\16\66\u0136\13\66\3\66\3\66\3\66\3\66\3\66\3"+
		"\67\3\67\3\67\3\67\7\67\u0141\n\67\f\67\16\67\u0144\13\67\3\67\5\67\u0147"+
		"\n\67\3\67\3\67\3\67\3\67\38\68\u014e\n8\r8\168\u014f\38\38\5\u0123\u0134"+
		"\u0142\29\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33"+
		"\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67"+
		"\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60_\61a\62c\63e\64g\65"+
		"i\2k\66m\67o8\3\2\6\5\2C\\aac|\4\2C\\c|\3\2\62;\5\2\13\f\17\17\"\"\u015e"+
		"\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2"+
		"\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2"+
		"\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2"+
		"\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2"+
		"\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3"+
		"\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2"+
		"\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2"+
		"U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3"+
		"\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2k\3\2\2\2\2m\3\2\2\2\2o\3\2\2"+
		"\2\3q\3\2\2\2\5s\3\2\2\2\7u\3\2\2\2\tw\3\2\2\2\13y\3\2\2\2\r{\3\2\2\2"+
		"\17~\3\2\2\2\21\u0081\3\2\2\2\23\u0083\3\2\2\2\25\u0085\3\2\2\2\27\u0087"+
		"\3\2\2\2\31\u0089\3\2\2\2\33\u008b\3\2\2\2\35\u008d\3\2\2\2\37\u008f\3"+
		"\2\2\2!\u0091\3\2\2\2#\u0098\3\2\2\2%\u009e\3\2\2\2\'\u00a7\3\2\2\2)\u00a9"+
		"\3\2\2\2+\u00ab\3\2\2\2-\u00ae\3\2\2\2/\u00b3\3\2\2\2\61\u00b7\3\2\2\2"+
		"\63\u00bd\3\2\2\2\65\u00c0\3\2\2\2\67\u00c5\3\2\2\29\u00c9\3\2\2\2;\u00ce"+
		"\3\2\2\2=\u00d0\3\2\2\2?\u00d3\3\2\2\2A\u00d6\3\2\2\2C\u00d9\3\2\2\2E"+
		"\u00dc\3\2\2\2G\u00df\3\2\2\2I\u00e1\3\2\2\2K\u00e3\3\2\2\2M\u00e5\3\2"+
		"\2\2O\u00e7\3\2\2\2Q\u00e9\3\2\2\2S\u00ec\3\2\2\2U\u00ef\3\2\2\2W\u00f1"+
		"\3\2\2\2Y\u00f4\3\2\2\2[\u00f6\3\2\2\2]\u00f9\3\2\2\2_\u00fc\3\2\2\2a"+
		"\u00ff\3\2\2\2c\u0110\3\2\2\2e\u011c\3\2\2\2g\u011e\3\2\2\2i\u012c\3\2"+
		"\2\2k\u012e\3\2\2\2m\u013c\3\2\2\2o\u014d\3\2\2\2qr\7=\2\2r\4\3\2\2\2"+
		"st\7*\2\2t\6\3\2\2\2uv\7+\2\2v\b\3\2\2\2wx\7]\2\2x\n\3\2\2\2yz\7_\2\2"+
		"z\f\3\2\2\2{|\7-\2\2|}\7-\2\2}\16\3\2\2\2~\177\7/\2\2\177\u0080\7/\2\2"+
		"\u0080\20\3\2\2\2\u0081\u0082\7#\2\2\u0082\22\3\2\2\2\u0083\u0084\7(\2"+
		"\2\u0084\24\3\2\2\2\u0085\u0086\7\u0080\2\2\u0086\26\3\2\2\2\u0087\u0088"+
		"\7`\2\2\u0088\30\3\2\2\2\u0089\u008a\7~\2\2\u008a\32\3\2\2\2\u008b\u008c"+
		"\7A\2\2\u008c\34\3\2\2\2\u008d\u008e\7<\2\2\u008e\36\3\2\2\2\u008f\u0090"+
		"\7.\2\2\u0090 \3\2\2\2\u0091\u0092\7t\2\2\u0092\u0093\7g\2\2\u0093\u0094"+
		"\7v\2\2\u0094\u0095\7w\2\2\u0095\u0096\7t\2\2\u0096\u0097\7p\2\2\u0097"+
		"\"\3\2\2\2\u0098\u0099\7d\2\2\u0099\u009a\7t\2\2\u009a\u009b\7g\2\2\u009b"+
		"\u009c\7c\2\2\u009c\u009d\7m\2\2\u009d$\3\2\2\2\u009e\u009f\7e\2\2\u009f"+
		"\u00a0\7q\2\2\u00a0\u00a1\7p\2\2\u00a1\u00a2\7v\2\2\u00a2\u00a3\7k\2\2"+
		"\u00a3\u00a4\7p\2\2\u00a4\u00a5\7w\2\2\u00a5\u00a6\7g\2\2\u00a6&\3\2\2"+
		"\2\u00a7\u00a8\7}\2\2\u00a8(\3\2\2\2\u00a9\u00aa\7\177\2\2\u00aa*\3\2"+
		"\2\2\u00ab\u00ac\7k\2\2\u00ac\u00ad\7h\2\2\u00ad,\3\2\2\2\u00ae\u00af"+
		"\7g\2\2\u00af\u00b0\7n\2\2\u00b0\u00b1\7u\2\2\u00b1\u00b2\7g\2\2\u00b2"+
		".\3\2\2\2\u00b3\u00b4\7h\2\2\u00b4\u00b5\7q\2\2\u00b5\u00b6\7t\2\2\u00b6"+
		"\60\3\2\2\2\u00b7\u00b8\7y\2\2\u00b8\u00b9\7j\2\2\u00b9\u00ba\7k\2\2\u00ba"+
		"\u00bb\7n\2\2\u00bb\u00bc\7g\2\2\u00bc\62\3\2\2\2\u00bd\u00be\7f\2\2\u00be"+
		"\u00bf\7q\2\2\u00bf\64\3\2\2\2\u00c0\u00c1\7x\2\2\u00c1\u00c2\7q\2\2\u00c2"+
		"\u00c3\7k\2\2\u00c3\u00c4\7f\2\2\u00c4\66\3\2\2\2\u00c5\u00c6\7k\2\2\u00c6"+
		"\u00c7\7p\2\2\u00c7\u00c8\7v\2\2\u00c88\3\2\2\2\u00c9\u00ca\7e\2\2\u00ca"+
		"\u00cb\7j\2\2\u00cb\u00cc\7c\2\2\u00cc\u00cd\7t\2\2\u00cd:\3\2\2\2\u00ce"+
		"\u00cf\7?\2\2\u00cf<\3\2\2\2\u00d0\u00d1\7-\2\2\u00d1\u00d2\7?\2\2\u00d2"+
		">\3\2\2\2\u00d3\u00d4\7/\2\2\u00d4\u00d5\7?\2\2\u00d5@\3\2\2\2\u00d6\u00d7"+
		"\7,\2\2\u00d7\u00d8\7?\2\2\u00d8B\3\2\2\2\u00d9\u00da\7\61\2\2\u00da\u00db"+
		"\7?\2\2\u00dbD\3\2\2\2\u00dc\u00dd\7\'\2\2\u00dd\u00de\7?\2\2\u00deF\3"+
		"\2\2\2\u00df\u00e0\7,\2\2\u00e0H\3\2\2\2\u00e1\u00e2\7\61\2\2\u00e2J\3"+
		"\2\2\2\u00e3\u00e4\7\'\2\2\u00e4L\3\2\2\2\u00e5\u00e6\7-\2\2\u00e6N\3"+
		"\2\2\2\u00e7\u00e8\7/\2\2\u00e8P\3\2\2\2\u00e9\u00ea\7?\2\2\u00ea\u00eb"+
		"\7?\2\2\u00ebR\3\2\2\2\u00ec\u00ed\7#\2\2\u00ed\u00ee\7?\2\2\u00eeT\3"+
		"\2\2\2\u00ef\u00f0\7>\2\2\u00f0V\3\2\2\2\u00f1\u00f2\7>\2\2\u00f2\u00f3"+
		"\7?\2\2\u00f3X\3\2\2\2\u00f4\u00f5\7@\2\2\u00f5Z\3\2\2\2\u00f6\u00f7\7"+
		"@\2\2\u00f7\u00f8\7?\2\2\u00f8\\\3\2\2\2\u00f9\u00fa\7(\2\2\u00fa\u00fb"+
		"\7(\2\2\u00fb^\3\2\2\2\u00fc\u00fd\7~\2\2\u00fd\u00fe\7~\2\2\u00fe`\3"+
		"\2\2\2\u00ff\u010c\t\2\2\2\u0100\u0102\t\3\2\2\u0101\u0100\3\2\2\2\u0102"+
		"\u0103\3\2\2\2\u0103\u0101\3\2\2\2\u0103\u0104\3\2\2\2\u0104\u010b\3\2"+
		"\2\2\u0105\u0107\t\4\2\2\u0106\u0105\3\2\2\2\u0107\u0108\3\2\2\2\u0108"+
		"\u0106\3\2\2\2\u0108\u0109\3\2\2\2\u0109\u010b\3\2\2\2\u010a\u0101\3\2"+
		"\2\2\u010a\u0106\3\2\2\2\u010b\u010e\3\2\2\2\u010c\u010a\3\2\2\2\u010c"+
		"\u010d\3\2\2\2\u010db\3\2\2\2\u010e\u010c\3\2\2\2\u010f\u0111\t\4\2\2"+
		"\u0110\u010f\3\2\2\2\u0111\u0112\3\2\2\2\u0112\u0110\3\2\2\2\u0112\u0113"+
		"\3\2\2\2\u0113d\3\2\2\2\u0114\u0115\7)\2\2\u0115\u0116\13\2\2\2\u0116"+
		"\u011d\7)\2\2\u0117\u0118\7)\2\2\u0118\u0119\7^\2\2\u0119\u011a\3\2\2"+
		"\2\u011a\u011b\13\2\2\2\u011b\u011d\7)\2\2\u011c\u0114\3\2\2\2\u011c\u0117"+
		"\3\2\2\2\u011df\3\2\2\2\u011e\u0123\7$\2\2\u011f\u0122\5i\65\2\u0120\u0122"+
		"\13\2\2\2\u0121\u011f\3\2\2\2\u0121\u0120\3\2\2\2\u0122\u0125\3\2\2\2"+
		"\u0123\u0124\3\2\2\2\u0123\u0121\3\2\2\2\u0124\u0126\3\2\2\2\u0125\u0123"+
		"\3\2\2\2\u0126\u0127\7$\2\2\u0127h\3\2\2\2\u0128\u0129\7^\2\2\u0129\u012d"+
		"\7$\2\2\u012a\u012b\7^\2\2\u012b\u012d\7^\2\2\u012c\u0128\3\2\2\2\u012c"+
		"\u012a\3\2\2\2\u012dj\3\2\2\2\u012e\u012f\7\61\2\2\u012f\u0130\7,\2\2"+
		"\u0130\u0134\3\2\2\2\u0131\u0133\13\2\2\2\u0132\u0131\3\2\2\2\u0133\u0136"+
		"\3\2\2\2\u0134\u0135\3\2\2\2\u0134\u0132\3\2\2\2\u0135\u0137\3\2\2\2\u0136"+
		"\u0134\3\2\2\2\u0137\u0138\7,\2\2\u0138\u0139\7\61\2\2\u0139\u013a\3\2"+
		"\2\2\u013a\u013b\b\66\2\2\u013bl\3\2\2\2\u013c\u013d\7\61\2\2\u013d\u013e"+
		"\7\61\2\2\u013e\u0142\3\2\2\2\u013f\u0141\13\2\2\2\u0140\u013f\3\2\2\2"+
		"\u0141\u0144\3\2\2\2\u0142\u0143\3\2\2\2\u0142\u0140\3\2\2\2\u0143\u0146"+
		"\3\2\2\2\u0144\u0142\3\2\2\2\u0145\u0147\7\17\2\2\u0146\u0145\3\2\2\2"+
		"\u0146\u0147\3\2\2\2\u0147\u0148\3\2\2\2\u0148\u0149\7\f\2\2\u0149\u014a"+
		"\3\2\2\2\u014a\u014b\b\67\2\2\u014bn\3\2\2\2\u014c\u014e\t\5\2\2\u014d"+
		"\u014c\3\2\2\2\u014e\u014f\3\2\2\2\u014f\u014d\3\2\2\2\u014f\u0150\3\2"+
		"\2\2\u0150\u0151\3\2\2\2\u0151\u0152\b8\2\2\u0152p\3\2\2\2\20\2\u0103"+
		"\u0108\u010a\u010c\u0112\u011c\u0121\u0123\u012c\u0134\u0142\u0146\u014f"+
		"\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}