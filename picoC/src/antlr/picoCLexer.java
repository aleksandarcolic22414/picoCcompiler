package antlr;

// Generated from /home/aleksandar/NetBeansProjects/Clone/picoCcompilerCloneRemote/picoC/grammar/picoC.g4 by ANTLR 4.6
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
		T__9=10, ID=11, INT=12, WS=13, STRING_LITERAL=14, MULTY_LINE_COMMENT=15, 
		SINGLE_LINE_COMMENT=16, MUL=17, DIV=18, ADD=19, SUB=20;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "ID", "INT", "WS", "STRING_LITERAL", "ESC", "MULTY_LINE_COMMENT", 
		"SINGLE_LINE_COMMENT", "MUL", "DIV", "ADD", "SUB", "VOIDTYPE", "INTTYPE"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "';'", "','", "'('", "')'", "'int'", "'void'", "'{'", "'}'", "'return'", 
		"'='", null, null, null, null, null, null, "'*'", "'/'", "'+'", "'-'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, "ID", 
		"INT", "WS", "STRING_LITERAL", "MULTY_LINE_COMMENT", "SINGLE_LINE_COMMENT", 
		"MUL", "DIV", "ADD", "SUB"
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\26\u00aa\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\3\2"+
		"\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\b\3"+
		"\b\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\f\3\f\6\fR\n\f\r\f"+
		"\16\fS\3\f\6\fW\n\f\r\f\16\fX\7\f[\n\f\f\f\16\f^\13\f\3\r\6\ra\n\r\r\r"+
		"\16\rb\3\16\6\16f\n\16\r\16\16\16g\3\16\3\16\3\17\3\17\3\17\7\17o\n\17"+
		"\f\17\16\17r\13\17\3\17\3\17\3\20\3\20\3\20\3\20\5\20z\n\20\3\21\3\21"+
		"\3\21\3\21\7\21\u0080\n\21\f\21\16\21\u0083\13\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\22\3\22\3\22\3\22\7\22\u008e\n\22\f\22\16\22\u0091\13\22\3\22"+
		"\5\22\u0094\n\22\3\22\3\22\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26"+
		"\3\26\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\5p\u0081\u008f\2\31"+
		"\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20"+
		"\37\2!\21#\22%\23\'\24)\25+\26-\2/\2\3\2\6\5\2C\\aac|\4\2C\\c|\3\2\62"+
		";\5\2\13\f\17\17\"\"\u00b2\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2"+
		"\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2"+
		"\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2!\3\2"+
		"\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\3\61\3\2"+
		"\2\2\5\63\3\2\2\2\7\65\3\2\2\2\t\67\3\2\2\2\139\3\2\2\2\r=\3\2\2\2\17"+
		"B\3\2\2\2\21D\3\2\2\2\23F\3\2\2\2\25M\3\2\2\2\27O\3\2\2\2\31`\3\2\2\2"+
		"\33e\3\2\2\2\35k\3\2\2\2\37y\3\2\2\2!{\3\2\2\2#\u0089\3\2\2\2%\u0099\3"+
		"\2\2\2\'\u009b\3\2\2\2)\u009d\3\2\2\2+\u009f\3\2\2\2-\u00a1\3\2\2\2/\u00a6"+
		"\3\2\2\2\61\62\7=\2\2\62\4\3\2\2\2\63\64\7.\2\2\64\6\3\2\2\2\65\66\7*"+
		"\2\2\66\b\3\2\2\2\678\7+\2\28\n\3\2\2\29:\7k\2\2:;\7p\2\2;<\7v\2\2<\f"+
		"\3\2\2\2=>\7x\2\2>?\7q\2\2?@\7k\2\2@A\7f\2\2A\16\3\2\2\2BC\7}\2\2C\20"+
		"\3\2\2\2DE\7\177\2\2E\22\3\2\2\2FG\7t\2\2GH\7g\2\2HI\7v\2\2IJ\7w\2\2J"+
		"K\7t\2\2KL\7p\2\2L\24\3\2\2\2MN\7?\2\2N\26\3\2\2\2O\\\t\2\2\2PR\t\3\2"+
		"\2QP\3\2\2\2RS\3\2\2\2SQ\3\2\2\2ST\3\2\2\2T[\3\2\2\2UW\t\4\2\2VU\3\2\2"+
		"\2WX\3\2\2\2XV\3\2\2\2XY\3\2\2\2Y[\3\2\2\2ZQ\3\2\2\2ZV\3\2\2\2[^\3\2\2"+
		"\2\\Z\3\2\2\2\\]\3\2\2\2]\30\3\2\2\2^\\\3\2\2\2_a\t\4\2\2`_\3\2\2\2ab"+
		"\3\2\2\2b`\3\2\2\2bc\3\2\2\2c\32\3\2\2\2df\t\5\2\2ed\3\2\2\2fg\3\2\2\2"+
		"ge\3\2\2\2gh\3\2\2\2hi\3\2\2\2ij\b\16\2\2j\34\3\2\2\2kp\7$\2\2lo\5\37"+
		"\20\2mo\13\2\2\2nl\3\2\2\2nm\3\2\2\2or\3\2\2\2pq\3\2\2\2pn\3\2\2\2qs\3"+
		"\2\2\2rp\3\2\2\2st\7$\2\2t\36\3\2\2\2uv\7^\2\2vz\7$\2\2wx\7^\2\2xz\7^"+
		"\2\2yu\3\2\2\2yw\3\2\2\2z \3\2\2\2{|\7\61\2\2|}\7,\2\2}\u0081\3\2\2\2"+
		"~\u0080\13\2\2\2\177~\3\2\2\2\u0080\u0083\3\2\2\2\u0081\u0082\3\2\2\2"+
		"\u0081\177\3\2\2\2\u0082\u0084\3\2\2\2\u0083\u0081\3\2\2\2\u0084\u0085"+
		"\7,\2\2\u0085\u0086\7\61\2\2\u0086\u0087\3\2\2\2\u0087\u0088\b\21\2\2"+
		"\u0088\"\3\2\2\2\u0089\u008a\7\61\2\2\u008a\u008b\7\61\2\2\u008b\u008f"+
		"\3\2\2\2\u008c\u008e\13\2\2\2\u008d\u008c\3\2\2\2\u008e\u0091\3\2\2\2"+
		"\u008f\u0090\3\2\2\2\u008f\u008d\3\2\2\2\u0090\u0093\3\2\2\2\u0091\u008f"+
		"\3\2\2\2\u0092\u0094\7\17\2\2\u0093\u0092\3\2\2\2\u0093\u0094\3\2\2\2"+
		"\u0094\u0095\3\2\2\2\u0095\u0096\7\f\2\2\u0096\u0097\3\2\2\2\u0097\u0098"+
		"\b\22\2\2\u0098$\3\2\2\2\u0099\u009a\7,\2\2\u009a&\3\2\2\2\u009b\u009c"+
		"\7\61\2\2\u009c(\3\2\2\2\u009d\u009e\7-\2\2\u009e*\3\2\2\2\u009f\u00a0"+
		"\7/\2\2\u00a0,\3\2\2\2\u00a1\u00a2\7x\2\2\u00a2\u00a3\7q\2\2\u00a3\u00a4"+
		"\7k\2\2\u00a4\u00a5\7f\2\2\u00a5.\3\2\2\2\u00a6\u00a7\7k\2\2\u00a7\u00a8"+
		"\7p\2\2\u00a8\u00a9\7v\2\2\u00a9\60\3\2\2\2\17\2SXZ\\bgnpy\u0081\u008f"+
		"\u0093\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}