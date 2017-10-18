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
		T__9=10, T__10=11, T__11=12, ID=13, INT=14, WS=15, STRING_LITERAL=16, 
		MULTY_LINE_COMMENT=17, SINGLE_LINE_COMMENT=18, MUL=19, DIV=20, MOD=21, 
		ADD=22, SUB=23, EQUAL=24, NOT_EQUAL=25, LESS=26, LESS_EQUAL=27, GREATER=28, 
		GREATER_EQUAL=29, LOGICAL_AND=30, LOGICAL_OR=31;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "ID", "INT", "WS", "STRING_LITERAL", "ESC", 
		"MULTY_LINE_COMMENT", "SINGLE_LINE_COMMENT", "MUL", "DIV", "MOD", "ADD", 
		"SUB", "EQUAL", "NOT_EQUAL", "LESS", "LESS_EQUAL", "GREATER", "GREATER_EQUAL", 
		"LOGICAL_AND", "LOGICAL_OR", "VOIDTYPE", "INTTYPE"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "';'", "','", "'('", "')'", "'int'", "'void'", "'{'", "'}'", "'if'", 
		"'else'", "'return'", "'='", null, null, null, null, null, null, "'*'", 
		"'/'", "'%'", "'+'", "'-'", "'=='", "'!='", "'<'", "'<='", "'>'", "'>='", 
		"'&&'", "'||'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, "ID", "INT", "WS", "STRING_LITERAL", "MULTY_LINE_COMMENT", "SINGLE_LINE_COMMENT", 
		"MUL", "DIV", "MOD", "ADD", "SUB", "EQUAL", "NOT_EQUAL", "LESS", "LESS_EQUAL", 
		"GREATER", "GREATER_EQUAL", "LOGICAL_AND", "LOGICAL_OR"
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2!\u00e0\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\6\3\6\3\7"+
		"\3\7\3\7\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\16\3\16\6\16p\n\16\r\16\16\16q"+
		"\3\16\6\16u\n\16\r\16\16\16v\7\16y\n\16\f\16\16\16|\13\16\3\17\6\17\177"+
		"\n\17\r\17\16\17\u0080\3\20\6\20\u0084\n\20\r\20\16\20\u0085\3\20\3\20"+
		"\3\21\3\21\3\21\7\21\u008d\n\21\f\21\16\21\u0090\13\21\3\21\3\21\3\22"+
		"\3\22\3\22\3\22\5\22\u0098\n\22\3\23\3\23\3\23\3\23\7\23\u009e\n\23\f"+
		"\23\16\23\u00a1\13\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\7\24"+
		"\u00ac\n\24\f\24\16\24\u00af\13\24\3\24\5\24\u00b2\n\24\3\24\3\24\3\24"+
		"\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3\32\3\32\3\32"+
		"\3\33\3\33\3\33\3\34\3\34\3\35\3\35\3\35\3\36\3\36\3\37\3\37\3\37\3 \3"+
		" \3 \3!\3!\3!\3\"\3\"\3\"\3\"\3\"\3#\3#\3#\3#\5\u008e\u009f\u00ad\2$\3"+
		"\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37"+
		"\21!\22#\2%\23\'\24)\25+\26-\27/\30\61\31\63\32\65\33\67\349\35;\36=\37"+
		"? A!C\2E\2\3\2\6\5\2C\\aac|\4\2C\\c|\3\2\62;\5\2\13\f\17\17\"\"\u00e8"+
		"\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2"+
		"\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2"+
		"\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2%\3\2"+
		"\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2"+
		"\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3"+
		"\2\2\2\2?\3\2\2\2\2A\3\2\2\2\3G\3\2\2\2\5I\3\2\2\2\7K\3\2\2\2\tM\3\2\2"+
		"\2\13O\3\2\2\2\rS\3\2\2\2\17X\3\2\2\2\21Z\3\2\2\2\23\\\3\2\2\2\25_\3\2"+
		"\2\2\27d\3\2\2\2\31k\3\2\2\2\33m\3\2\2\2\35~\3\2\2\2\37\u0083\3\2\2\2"+
		"!\u0089\3\2\2\2#\u0097\3\2\2\2%\u0099\3\2\2\2\'\u00a7\3\2\2\2)\u00b7\3"+
		"\2\2\2+\u00b9\3\2\2\2-\u00bb\3\2\2\2/\u00bd\3\2\2\2\61\u00bf\3\2\2\2\63"+
		"\u00c1\3\2\2\2\65\u00c4\3\2\2\2\67\u00c7\3\2\2\29\u00c9\3\2\2\2;\u00cc"+
		"\3\2\2\2=\u00ce\3\2\2\2?\u00d1\3\2\2\2A\u00d4\3\2\2\2C\u00d7\3\2\2\2E"+
		"\u00dc\3\2\2\2GH\7=\2\2H\4\3\2\2\2IJ\7.\2\2J\6\3\2\2\2KL\7*\2\2L\b\3\2"+
		"\2\2MN\7+\2\2N\n\3\2\2\2OP\7k\2\2PQ\7p\2\2QR\7v\2\2R\f\3\2\2\2ST\7x\2"+
		"\2TU\7q\2\2UV\7k\2\2VW\7f\2\2W\16\3\2\2\2XY\7}\2\2Y\20\3\2\2\2Z[\7\177"+
		"\2\2[\22\3\2\2\2\\]\7k\2\2]^\7h\2\2^\24\3\2\2\2_`\7g\2\2`a\7n\2\2ab\7"+
		"u\2\2bc\7g\2\2c\26\3\2\2\2de\7t\2\2ef\7g\2\2fg\7v\2\2gh\7w\2\2hi\7t\2"+
		"\2ij\7p\2\2j\30\3\2\2\2kl\7?\2\2l\32\3\2\2\2mz\t\2\2\2np\t\3\2\2on\3\2"+
		"\2\2pq\3\2\2\2qo\3\2\2\2qr\3\2\2\2ry\3\2\2\2su\t\4\2\2ts\3\2\2\2uv\3\2"+
		"\2\2vt\3\2\2\2vw\3\2\2\2wy\3\2\2\2xo\3\2\2\2xt\3\2\2\2y|\3\2\2\2zx\3\2"+
		"\2\2z{\3\2\2\2{\34\3\2\2\2|z\3\2\2\2}\177\t\4\2\2~}\3\2\2\2\177\u0080"+
		"\3\2\2\2\u0080~\3\2\2\2\u0080\u0081\3\2\2\2\u0081\36\3\2\2\2\u0082\u0084"+
		"\t\5\2\2\u0083\u0082\3\2\2\2\u0084\u0085\3\2\2\2\u0085\u0083\3\2\2\2\u0085"+
		"\u0086\3\2\2\2\u0086\u0087\3\2\2\2\u0087\u0088\b\20\2\2\u0088 \3\2\2\2"+
		"\u0089\u008e\7$\2\2\u008a\u008d\5#\22\2\u008b\u008d\13\2\2\2\u008c\u008a"+
		"\3\2\2\2\u008c\u008b\3\2\2\2\u008d\u0090\3\2\2\2\u008e\u008f\3\2\2\2\u008e"+
		"\u008c\3\2\2\2\u008f\u0091\3\2\2\2\u0090\u008e\3\2\2\2\u0091\u0092\7$"+
		"\2\2\u0092\"\3\2\2\2\u0093\u0094\7^\2\2\u0094\u0098\7$\2\2\u0095\u0096"+
		"\7^\2\2\u0096\u0098\7^\2\2\u0097\u0093\3\2\2\2\u0097\u0095\3\2\2\2\u0098"+
		"$\3\2\2\2\u0099\u009a\7\61\2\2\u009a\u009b\7,\2\2\u009b\u009f\3\2\2\2"+
		"\u009c\u009e\13\2\2\2\u009d\u009c\3\2\2\2\u009e\u00a1\3\2\2\2\u009f\u00a0"+
		"\3\2\2\2\u009f\u009d\3\2\2\2\u00a0\u00a2\3\2\2\2\u00a1\u009f\3\2\2\2\u00a2"+
		"\u00a3\7,\2\2\u00a3\u00a4\7\61\2\2\u00a4\u00a5\3\2\2\2\u00a5\u00a6\b\23"+
		"\2\2\u00a6&\3\2\2\2\u00a7\u00a8\7\61\2\2\u00a8\u00a9\7\61\2\2\u00a9\u00ad"+
		"\3\2\2\2\u00aa\u00ac\13\2\2\2\u00ab\u00aa\3\2\2\2\u00ac\u00af\3\2\2\2"+
		"\u00ad\u00ae\3\2\2\2\u00ad\u00ab\3\2\2\2\u00ae\u00b1\3\2\2\2\u00af\u00ad"+
		"\3\2\2\2\u00b0\u00b2\7\17\2\2\u00b1\u00b0\3\2\2\2\u00b1\u00b2\3\2\2\2"+
		"\u00b2\u00b3\3\2\2\2\u00b3\u00b4\7\f\2\2\u00b4\u00b5\3\2\2\2\u00b5\u00b6"+
		"\b\24\2\2\u00b6(\3\2\2\2\u00b7\u00b8\7,\2\2\u00b8*\3\2\2\2\u00b9\u00ba"+
		"\7\61\2\2\u00ba,\3\2\2\2\u00bb\u00bc\7\'\2\2\u00bc.\3\2\2\2\u00bd\u00be"+
		"\7-\2\2\u00be\60\3\2\2\2\u00bf\u00c0\7/\2\2\u00c0\62\3\2\2\2\u00c1\u00c2"+
		"\7?\2\2\u00c2\u00c3\7?\2\2\u00c3\64\3\2\2\2\u00c4\u00c5\7#\2\2\u00c5\u00c6"+
		"\7?\2\2\u00c6\66\3\2\2\2\u00c7\u00c8\7>\2\2\u00c88\3\2\2\2\u00c9\u00ca"+
		"\7>\2\2\u00ca\u00cb\7?\2\2\u00cb:\3\2\2\2\u00cc\u00cd\7@\2\2\u00cd<\3"+
		"\2\2\2\u00ce\u00cf\7@\2\2\u00cf\u00d0\7?\2\2\u00d0>\3\2\2\2\u00d1\u00d2"+
		"\7(\2\2\u00d2\u00d3\7(\2\2\u00d3@\3\2\2\2\u00d4\u00d5\7~\2\2\u00d5\u00d6"+
		"\7~\2\2\u00d6B\3\2\2\2\u00d7\u00d8\7x\2\2\u00d8\u00d9\7q\2\2\u00d9\u00da"+
		"\7k\2\2\u00da\u00db\7f\2\2\u00dbD\3\2\2\2\u00dc\u00dd\7k\2\2\u00dd\u00de"+
		"\7p\2\2\u00de\u00df\7v\2\2\u00dfF\3\2\2\2\17\2qvxz\u0080\u0085\u008c\u008e"+
		"\u0097\u009f\u00ad\u00b1\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}