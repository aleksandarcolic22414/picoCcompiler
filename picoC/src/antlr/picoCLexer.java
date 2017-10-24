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
		ID=18, INT=19, WS=20, STRING_LITERAL=21, MULTY_LINE_COMMENT=22, SINGLE_LINE_COMMENT=23, 
		MUL=24, DIV=25, MOD=26, ADD=27, SUB=28, EQUAL=29, NOT_EQUAL=30, LESS=31, 
		LESS_EQUAL=32, GREATER=33, GREATER_EQUAL=34, LOGICAL_AND=35, LOGICAL_OR=36;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
		"ID", "INT", "WS", "STRING_LITERAL", "ESC", "MULTY_LINE_COMMENT", "SINGLE_LINE_COMMENT", 
		"MUL", "DIV", "MOD", "ADD", "SUB", "EQUAL", "NOT_EQUAL", "LESS", "LESS_EQUAL", 
		"GREATER", "GREATER_EQUAL", "LOGICAL_AND", "LOGICAL_OR", "VOIDTYPE", "INTTYPE"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "';'", "','", "'('", "')'", "'int'", "'void'", "'return'", "'break'", 
		"'continue'", "'{'", "'}'", "'if'", "'else'", "'for'", "'++'", "'--'", 
		"'='", null, null, null, null, null, null, "'*'", "'/'", "'%'", "'+'", 
		"'-'", "'=='", "'!='", "'<'", "'<='", "'>'", "'>='", "'&&'", "'||'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, "ID", "INT", "WS", "STRING_LITERAL", 
		"MULTY_LINE_COMMENT", "SINGLE_LINE_COMMENT", "MUL", "DIV", "MOD", "ADD", 
		"SUB", "EQUAL", "NOT_EQUAL", "LESS", "LESS_EQUAL", "GREATER", "GREATER_EQUAL", 
		"LOGICAL_AND", "LOGICAL_OR"
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2&\u0103\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\3\2\3\2\3\3\3\3\3\4"+
		"\3\4\3\5\3\5\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13"+
		"\3\13\3\f\3\f\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17"+
		"\3\20\3\20\3\20\3\21\3\21\3\21\3\22\3\22\3\23\3\23\6\23\u0093\n\23\r\23"+
		"\16\23\u0094\3\23\6\23\u0098\n\23\r\23\16\23\u0099\7\23\u009c\n\23\f\23"+
		"\16\23\u009f\13\23\3\24\6\24\u00a2\n\24\r\24\16\24\u00a3\3\25\6\25\u00a7"+
		"\n\25\r\25\16\25\u00a8\3\25\3\25\3\26\3\26\3\26\7\26\u00b0\n\26\f\26\16"+
		"\26\u00b3\13\26\3\26\3\26\3\27\3\27\3\27\3\27\5\27\u00bb\n\27\3\30\3\30"+
		"\3\30\3\30\7\30\u00c1\n\30\f\30\16\30\u00c4\13\30\3\30\3\30\3\30\3\30"+
		"\3\30\3\31\3\31\3\31\3\31\7\31\u00cf\n\31\f\31\16\31\u00d2\13\31\3\31"+
		"\5\31\u00d5\n\31\3\31\3\31\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34\3\35"+
		"\3\35\3\36\3\36\3\37\3\37\3\37\3 \3 \3 \3!\3!\3\"\3\"\3\"\3#\3#\3$\3$"+
		"\3$\3%\3%\3%\3&\3&\3&\3\'\3\'\3\'\3\'\3\'\3(\3(\3(\3(\5\u00b1\u00c2\u00d0"+
		"\2)\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35"+
		"\20\37\21!\22#\23%\24\'\25)\26+\27-\2/\30\61\31\63\32\65\33\67\349\35"+
		";\36=\37? A!C\"E#G$I%K&M\2O\2\3\2\6\5\2C\\aac|\4\2C\\c|\3\2\62;\5\2\13"+
		"\f\17\17\"\"\u010b\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13"+
		"\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2"+
		"\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2"+
		"!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2/\3"+
		"\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2"+
		"\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G"+
		"\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\3Q\3\2\2\2\5S\3\2\2\2\7U\3\2\2\2\tW\3\2"+
		"\2\2\13Y\3\2\2\2\r]\3\2\2\2\17b\3\2\2\2\21i\3\2\2\2\23o\3\2\2\2\25x\3"+
		"\2\2\2\27z\3\2\2\2\31|\3\2\2\2\33\177\3\2\2\2\35\u0084\3\2\2\2\37\u0088"+
		"\3\2\2\2!\u008b\3\2\2\2#\u008e\3\2\2\2%\u0090\3\2\2\2\'\u00a1\3\2\2\2"+
		")\u00a6\3\2\2\2+\u00ac\3\2\2\2-\u00ba\3\2\2\2/\u00bc\3\2\2\2\61\u00ca"+
		"\3\2\2\2\63\u00da\3\2\2\2\65\u00dc\3\2\2\2\67\u00de\3\2\2\29\u00e0\3\2"+
		"\2\2;\u00e2\3\2\2\2=\u00e4\3\2\2\2?\u00e7\3\2\2\2A\u00ea\3\2\2\2C\u00ec"+
		"\3\2\2\2E\u00ef\3\2\2\2G\u00f1\3\2\2\2I\u00f4\3\2\2\2K\u00f7\3\2\2\2M"+
		"\u00fa\3\2\2\2O\u00ff\3\2\2\2QR\7=\2\2R\4\3\2\2\2ST\7.\2\2T\6\3\2\2\2"+
		"UV\7*\2\2V\b\3\2\2\2WX\7+\2\2X\n\3\2\2\2YZ\7k\2\2Z[\7p\2\2[\\\7v\2\2\\"+
		"\f\3\2\2\2]^\7x\2\2^_\7q\2\2_`\7k\2\2`a\7f\2\2a\16\3\2\2\2bc\7t\2\2cd"+
		"\7g\2\2de\7v\2\2ef\7w\2\2fg\7t\2\2gh\7p\2\2h\20\3\2\2\2ij\7d\2\2jk\7t"+
		"\2\2kl\7g\2\2lm\7c\2\2mn\7m\2\2n\22\3\2\2\2op\7e\2\2pq\7q\2\2qr\7p\2\2"+
		"rs\7v\2\2st\7k\2\2tu\7p\2\2uv\7w\2\2vw\7g\2\2w\24\3\2\2\2xy\7}\2\2y\26"+
		"\3\2\2\2z{\7\177\2\2{\30\3\2\2\2|}\7k\2\2}~\7h\2\2~\32\3\2\2\2\177\u0080"+
		"\7g\2\2\u0080\u0081\7n\2\2\u0081\u0082\7u\2\2\u0082\u0083\7g\2\2\u0083"+
		"\34\3\2\2\2\u0084\u0085\7h\2\2\u0085\u0086\7q\2\2\u0086\u0087\7t\2\2\u0087"+
		"\36\3\2\2\2\u0088\u0089\7-\2\2\u0089\u008a\7-\2\2\u008a \3\2\2\2\u008b"+
		"\u008c\7/\2\2\u008c\u008d\7/\2\2\u008d\"\3\2\2\2\u008e\u008f\7?\2\2\u008f"+
		"$\3\2\2\2\u0090\u009d\t\2\2\2\u0091\u0093\t\3\2\2\u0092\u0091\3\2\2\2"+
		"\u0093\u0094\3\2\2\2\u0094\u0092\3\2\2\2\u0094\u0095\3\2\2\2\u0095\u009c"+
		"\3\2\2\2\u0096\u0098\t\4\2\2\u0097\u0096\3\2\2\2\u0098\u0099\3\2\2\2\u0099"+
		"\u0097\3\2\2\2\u0099\u009a\3\2\2\2\u009a\u009c\3\2\2\2\u009b\u0092\3\2"+
		"\2\2\u009b\u0097\3\2\2\2\u009c\u009f\3\2\2\2\u009d\u009b\3\2\2\2\u009d"+
		"\u009e\3\2\2\2\u009e&\3\2\2\2\u009f\u009d\3\2\2\2\u00a0\u00a2\t\4\2\2"+
		"\u00a1\u00a0\3\2\2\2\u00a2\u00a3\3\2\2\2\u00a3\u00a1\3\2\2\2\u00a3\u00a4"+
		"\3\2\2\2\u00a4(\3\2\2\2\u00a5\u00a7\t\5\2\2\u00a6\u00a5\3\2\2\2\u00a7"+
		"\u00a8\3\2\2\2\u00a8\u00a6\3\2\2\2\u00a8\u00a9\3\2\2\2\u00a9\u00aa\3\2"+
		"\2\2\u00aa\u00ab\b\25\2\2\u00ab*\3\2\2\2\u00ac\u00b1\7$\2\2\u00ad\u00b0"+
		"\5-\27\2\u00ae\u00b0\13\2\2\2\u00af\u00ad\3\2\2\2\u00af\u00ae\3\2\2\2"+
		"\u00b0\u00b3\3\2\2\2\u00b1\u00b2\3\2\2\2\u00b1\u00af\3\2\2\2\u00b2\u00b4"+
		"\3\2\2\2\u00b3\u00b1\3\2\2\2\u00b4\u00b5\7$\2\2\u00b5,\3\2\2\2\u00b6\u00b7"+
		"\7^\2\2\u00b7\u00bb\7$\2\2\u00b8\u00b9\7^\2\2\u00b9\u00bb\7^\2\2\u00ba"+
		"\u00b6\3\2\2\2\u00ba\u00b8\3\2\2\2\u00bb.\3\2\2\2\u00bc\u00bd\7\61\2\2"+
		"\u00bd\u00be\7,\2\2\u00be\u00c2\3\2\2\2\u00bf\u00c1\13\2\2\2\u00c0\u00bf"+
		"\3\2\2\2\u00c1\u00c4\3\2\2\2\u00c2\u00c3\3\2\2\2\u00c2\u00c0\3\2\2\2\u00c3"+
		"\u00c5\3\2\2\2\u00c4\u00c2\3\2\2\2\u00c5\u00c6\7,\2\2\u00c6\u00c7\7\61"+
		"\2\2\u00c7\u00c8\3\2\2\2\u00c8\u00c9\b\30\2\2\u00c9\60\3\2\2\2\u00ca\u00cb"+
		"\7\61\2\2\u00cb\u00cc\7\61\2\2\u00cc\u00d0\3\2\2\2\u00cd\u00cf\13\2\2"+
		"\2\u00ce\u00cd\3\2\2\2\u00cf\u00d2\3\2\2\2\u00d0\u00d1\3\2\2\2\u00d0\u00ce"+
		"\3\2\2\2\u00d1\u00d4\3\2\2\2\u00d2\u00d0\3\2\2\2\u00d3\u00d5\7\17\2\2"+
		"\u00d4\u00d3\3\2\2\2\u00d4\u00d5\3\2\2\2\u00d5\u00d6\3\2\2\2\u00d6\u00d7"+
		"\7\f\2\2\u00d7\u00d8\3\2\2\2\u00d8\u00d9\b\31\2\2\u00d9\62\3\2\2\2\u00da"+
		"\u00db\7,\2\2\u00db\64\3\2\2\2\u00dc\u00dd\7\61\2\2\u00dd\66\3\2\2\2\u00de"+
		"\u00df\7\'\2\2\u00df8\3\2\2\2\u00e0\u00e1\7-\2\2\u00e1:\3\2\2\2\u00e2"+
		"\u00e3\7/\2\2\u00e3<\3\2\2\2\u00e4\u00e5\7?\2\2\u00e5\u00e6\7?\2\2\u00e6"+
		">\3\2\2\2\u00e7\u00e8\7#\2\2\u00e8\u00e9\7?\2\2\u00e9@\3\2\2\2\u00ea\u00eb"+
		"\7>\2\2\u00ebB\3\2\2\2\u00ec\u00ed\7>\2\2\u00ed\u00ee\7?\2\2\u00eeD\3"+
		"\2\2\2\u00ef\u00f0\7@\2\2\u00f0F\3\2\2\2\u00f1\u00f2\7@\2\2\u00f2\u00f3"+
		"\7?\2\2\u00f3H\3\2\2\2\u00f4\u00f5\7(\2\2\u00f5\u00f6\7(\2\2\u00f6J\3"+
		"\2\2\2\u00f7\u00f8\7~\2\2\u00f8\u00f9\7~\2\2\u00f9L\3\2\2\2\u00fa\u00fb"+
		"\7x\2\2\u00fb\u00fc\7q\2\2\u00fc\u00fd\7k\2\2\u00fd\u00fe\7f\2\2\u00fe"+
		"N\3\2\2\2\u00ff\u0100\7k\2\2\u0100\u0101\7p\2\2\u0101\u0102\7v\2\2\u0102"+
		"P\3\2\2\2\17\2\u0094\u0099\u009b\u009d\u00a3\u00a8\u00af\u00b1\u00ba\u00c2"+
		"\u00d0\u00d4\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}