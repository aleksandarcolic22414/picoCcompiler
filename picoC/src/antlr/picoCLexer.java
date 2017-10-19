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
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, ID=16, INT=17, 
		WS=18, STRING_LITERAL=19, MULTY_LINE_COMMENT=20, SINGLE_LINE_COMMENT=21, 
		MUL=22, DIV=23, MOD=24, ADD=25, SUB=26, EQUAL=27, NOT_EQUAL=28, LESS=29, 
		LESS_EQUAL=30, GREATER=31, GREATER_EQUAL=32, LOGICAL_AND=33, LOGICAL_OR=34;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "ID", "INT", "WS", 
		"STRING_LITERAL", "ESC", "MULTY_LINE_COMMENT", "SINGLE_LINE_COMMENT", 
		"MUL", "DIV", "MOD", "ADD", "SUB", "EQUAL", "NOT_EQUAL", "LESS", "LESS_EQUAL", 
		"GREATER", "GREATER_EQUAL", "LOGICAL_AND", "LOGICAL_OR", "VOIDTYPE", "INTTYPE"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "';'", "','", "'('", "')'", "'int'", "'void'", "'{'", "'}'", "'if'", 
		"'else'", "'for'", "'return'", "'++'", "'--'", "'='", null, null, null, 
		null, null, null, "'*'", "'/'", "'%'", "'+'", "'-'", "'=='", "'!='", "'<'", 
		"'<='", "'>'", "'>='", "'&&'", "'||'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, "ID", "INT", "WS", "STRING_LITERAL", "MULTY_LINE_COMMENT", 
		"SINGLE_LINE_COMMENT", "MUL", "DIV", "MOD", "ADD", "SUB", "EQUAL", "NOT_EQUAL", 
		"LESS", "LESS_EQUAL", "GREATER", "GREATER_EQUAL", "LOGICAL_AND", "LOGICAL_OR"
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2$\u00f0\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3"+
		"\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\n\3\13\3"+
		"\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3"+
		"\16\3\16\3\17\3\17\3\17\3\20\3\20\3\21\3\21\6\21\u0080\n\21\r\21\16\21"+
		"\u0081\3\21\6\21\u0085\n\21\r\21\16\21\u0086\7\21\u0089\n\21\f\21\16\21"+
		"\u008c\13\21\3\22\6\22\u008f\n\22\r\22\16\22\u0090\3\23\6\23\u0094\n\23"+
		"\r\23\16\23\u0095\3\23\3\23\3\24\3\24\3\24\7\24\u009d\n\24\f\24\16\24"+
		"\u00a0\13\24\3\24\3\24\3\25\3\25\3\25\3\25\5\25\u00a8\n\25\3\26\3\26\3"+
		"\26\3\26\7\26\u00ae\n\26\f\26\16\26\u00b1\13\26\3\26\3\26\3\26\3\26\3"+
		"\26\3\27\3\27\3\27\3\27\7\27\u00bc\n\27\f\27\16\27\u00bf\13\27\3\27\5"+
		"\27\u00c2\n\27\3\27\3\27\3\27\3\27\3\30\3\30\3\31\3\31\3\32\3\32\3\33"+
		"\3\33\3\34\3\34\3\35\3\35\3\35\3\36\3\36\3\36\3\37\3\37\3 \3 \3 \3!\3"+
		"!\3\"\3\"\3\"\3#\3#\3#\3$\3$\3$\3%\3%\3%\3%\3%\3&\3&\3&\3&\5\u009e\u00af"+
		"\u00bd\2\'\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33"+
		"\17\35\20\37\21!\22#\23%\24\'\25)\2+\26-\27/\30\61\31\63\32\65\33\67\34"+
		"9\35;\36=\37? A!C\"E#G$I\2K\2\3\2\6\5\2C\\aac|\4\2C\\c|\3\2\62;\5\2\13"+
		"\f\17\17\"\"\u00f8\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13"+
		"\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2"+
		"\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2"+
		"!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3"+
		"\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2"+
		"\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G"+
		"\3\2\2\2\3M\3\2\2\2\5O\3\2\2\2\7Q\3\2\2\2\tS\3\2\2\2\13U\3\2\2\2\rY\3"+
		"\2\2\2\17^\3\2\2\2\21`\3\2\2\2\23b\3\2\2\2\25e\3\2\2\2\27j\3\2\2\2\31"+
		"n\3\2\2\2\33u\3\2\2\2\35x\3\2\2\2\37{\3\2\2\2!}\3\2\2\2#\u008e\3\2\2\2"+
		"%\u0093\3\2\2\2\'\u0099\3\2\2\2)\u00a7\3\2\2\2+\u00a9\3\2\2\2-\u00b7\3"+
		"\2\2\2/\u00c7\3\2\2\2\61\u00c9\3\2\2\2\63\u00cb\3\2\2\2\65\u00cd\3\2\2"+
		"\2\67\u00cf\3\2\2\29\u00d1\3\2\2\2;\u00d4\3\2\2\2=\u00d7\3\2\2\2?\u00d9"+
		"\3\2\2\2A\u00dc\3\2\2\2C\u00de\3\2\2\2E\u00e1\3\2\2\2G\u00e4\3\2\2\2I"+
		"\u00e7\3\2\2\2K\u00ec\3\2\2\2MN\7=\2\2N\4\3\2\2\2OP\7.\2\2P\6\3\2\2\2"+
		"QR\7*\2\2R\b\3\2\2\2ST\7+\2\2T\n\3\2\2\2UV\7k\2\2VW\7p\2\2WX\7v\2\2X\f"+
		"\3\2\2\2YZ\7x\2\2Z[\7q\2\2[\\\7k\2\2\\]\7f\2\2]\16\3\2\2\2^_\7}\2\2_\20"+
		"\3\2\2\2`a\7\177\2\2a\22\3\2\2\2bc\7k\2\2cd\7h\2\2d\24\3\2\2\2ef\7g\2"+
		"\2fg\7n\2\2gh\7u\2\2hi\7g\2\2i\26\3\2\2\2jk\7h\2\2kl\7q\2\2lm\7t\2\2m"+
		"\30\3\2\2\2no\7t\2\2op\7g\2\2pq\7v\2\2qr\7w\2\2rs\7t\2\2st\7p\2\2t\32"+
		"\3\2\2\2uv\7-\2\2vw\7-\2\2w\34\3\2\2\2xy\7/\2\2yz\7/\2\2z\36\3\2\2\2{"+
		"|\7?\2\2| \3\2\2\2}\u008a\t\2\2\2~\u0080\t\3\2\2\177~\3\2\2\2\u0080\u0081"+
		"\3\2\2\2\u0081\177\3\2\2\2\u0081\u0082\3\2\2\2\u0082\u0089\3\2\2\2\u0083"+
		"\u0085\t\4\2\2\u0084\u0083\3\2\2\2\u0085\u0086\3\2\2\2\u0086\u0084\3\2"+
		"\2\2\u0086\u0087\3\2\2\2\u0087\u0089\3\2\2\2\u0088\177\3\2\2\2\u0088\u0084"+
		"\3\2\2\2\u0089\u008c\3\2\2\2\u008a\u0088\3\2\2\2\u008a\u008b\3\2\2\2\u008b"+
		"\"\3\2\2\2\u008c\u008a\3\2\2\2\u008d\u008f\t\4\2\2\u008e\u008d\3\2\2\2"+
		"\u008f\u0090\3\2\2\2\u0090\u008e\3\2\2\2\u0090\u0091\3\2\2\2\u0091$\3"+
		"\2\2\2\u0092\u0094\t\5\2\2\u0093\u0092\3\2\2\2\u0094\u0095\3\2\2\2\u0095"+
		"\u0093\3\2\2\2\u0095\u0096\3\2\2\2\u0096\u0097\3\2\2\2\u0097\u0098\b\23"+
		"\2\2\u0098&\3\2\2\2\u0099\u009e\7$\2\2\u009a\u009d\5)\25\2\u009b\u009d"+
		"\13\2\2\2\u009c\u009a\3\2\2\2\u009c\u009b\3\2\2\2\u009d\u00a0\3\2\2\2"+
		"\u009e\u009f\3\2\2\2\u009e\u009c\3\2\2\2\u009f\u00a1\3\2\2\2\u00a0\u009e"+
		"\3\2\2\2\u00a1\u00a2\7$\2\2\u00a2(\3\2\2\2\u00a3\u00a4\7^\2\2\u00a4\u00a8"+
		"\7$\2\2\u00a5\u00a6\7^\2\2\u00a6\u00a8\7^\2\2\u00a7\u00a3\3\2\2\2\u00a7"+
		"\u00a5\3\2\2\2\u00a8*\3\2\2\2\u00a9\u00aa\7\61\2\2\u00aa\u00ab\7,\2\2"+
		"\u00ab\u00af\3\2\2\2\u00ac\u00ae\13\2\2\2\u00ad\u00ac\3\2\2\2\u00ae\u00b1"+
		"\3\2\2\2\u00af\u00b0\3\2\2\2\u00af\u00ad\3\2\2\2\u00b0\u00b2\3\2\2\2\u00b1"+
		"\u00af\3\2\2\2\u00b2\u00b3\7,\2\2\u00b3\u00b4\7\61\2\2\u00b4\u00b5\3\2"+
		"\2\2\u00b5\u00b6\b\26\2\2\u00b6,\3\2\2\2\u00b7\u00b8\7\61\2\2\u00b8\u00b9"+
		"\7\61\2\2\u00b9\u00bd\3\2\2\2\u00ba\u00bc\13\2\2\2\u00bb\u00ba\3\2\2\2"+
		"\u00bc\u00bf\3\2\2\2\u00bd\u00be\3\2\2\2\u00bd\u00bb\3\2\2\2\u00be\u00c1"+
		"\3\2\2\2\u00bf\u00bd\3\2\2\2\u00c0\u00c2\7\17\2\2\u00c1\u00c0\3\2\2\2"+
		"\u00c1\u00c2\3\2\2\2\u00c2\u00c3\3\2\2\2\u00c3\u00c4\7\f\2\2\u00c4\u00c5"+
		"\3\2\2\2\u00c5\u00c6\b\27\2\2\u00c6.\3\2\2\2\u00c7\u00c8\7,\2\2\u00c8"+
		"\60\3\2\2\2\u00c9\u00ca\7\61\2\2\u00ca\62\3\2\2\2\u00cb\u00cc\7\'\2\2"+
		"\u00cc\64\3\2\2\2\u00cd\u00ce\7-\2\2\u00ce\66\3\2\2\2\u00cf\u00d0\7/\2"+
		"\2\u00d08\3\2\2\2\u00d1\u00d2\7?\2\2\u00d2\u00d3\7?\2\2\u00d3:\3\2\2\2"+
		"\u00d4\u00d5\7#\2\2\u00d5\u00d6\7?\2\2\u00d6<\3\2\2\2\u00d7\u00d8\7>\2"+
		"\2\u00d8>\3\2\2\2\u00d9\u00da\7>\2\2\u00da\u00db\7?\2\2\u00db@\3\2\2\2"+
		"\u00dc\u00dd\7@\2\2\u00ddB\3\2\2\2\u00de\u00df\7@\2\2\u00df\u00e0\7?\2"+
		"\2\u00e0D\3\2\2\2\u00e1\u00e2\7(\2\2\u00e2\u00e3\7(\2\2\u00e3F\3\2\2\2"+
		"\u00e4\u00e5\7~\2\2\u00e5\u00e6\7~\2\2\u00e6H\3\2\2\2\u00e7\u00e8\7x\2"+
		"\2\u00e8\u00e9\7q\2\2\u00e9\u00ea\7k\2\2\u00ea\u00eb\7f\2\2\u00ebJ\3\2"+
		"\2\2\u00ec\u00ed\7k\2\2\u00ed\u00ee\7p\2\2\u00ee\u00ef\7v\2\2\u00efL\3"+
		"\2\2\2\17\2\u0081\u0086\u0088\u008a\u0090\u0095\u009c\u009e\u00a7\u00af"+
		"\u00bd\u00c1\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}