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
		MULTY_LINE_COMMENT=17, SINGLE_LINE_COMMENT=18, MUL=19, DIV=20, ADD=21, 
		SUB=22, EQUAL=23, NOT_EQUAL=24, LESS=25, LESS_EQUAL=26, GREATER=27, GREATER_EQUAL=28, 
		LOGICAL_AND=29, LOGICAL_OR=30;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "ID", "INT", "WS", "STRING_LITERAL", "ESC", 
		"MULTY_LINE_COMMENT", "SINGLE_LINE_COMMENT", "MUL", "DIV", "ADD", "SUB", 
		"EQUAL", "NOT_EQUAL", "LESS", "LESS_EQUAL", "GREATER", "GREATER_EQUAL", 
		"LOGICAL_AND", "LOGICAL_OR", "VOIDTYPE", "INTTYPE"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "';'", "','", "'('", "')'", "'int'", "'void'", "'{'", "'}'", "'if'", 
		"'else'", "'return'", "'='", null, null, null, null, null, null, "'*'", 
		"'/'", "'+'", "'-'", "'=='", "'!='", "'<'", "'<='", "'>'", "'>='", "'&&'", 
		"'||'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, "ID", "INT", "WS", "STRING_LITERAL", "MULTY_LINE_COMMENT", "SINGLE_LINE_COMMENT", 
		"MUL", "DIV", "ADD", "SUB", "EQUAL", "NOT_EQUAL", "LESS", "LESS_EQUAL", 
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2 \u00dc\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\6\3\6\3\7\3\7\3"+
		"\7\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\16\3\16\6\16n\n\16\r\16\16\16o\3\16\6"+
		"\16s\n\16\r\16\16\16t\7\16w\n\16\f\16\16\16z\13\16\3\17\6\17}\n\17\r\17"+
		"\16\17~\3\20\6\20\u0082\n\20\r\20\16\20\u0083\3\20\3\20\3\21\3\21\3\21"+
		"\7\21\u008b\n\21\f\21\16\21\u008e\13\21\3\21\3\21\3\22\3\22\3\22\3\22"+
		"\5\22\u0096\n\22\3\23\3\23\3\23\3\23\7\23\u009c\n\23\f\23\16\23\u009f"+
		"\13\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\7\24\u00aa\n\24\f"+
		"\24\16\24\u00ad\13\24\3\24\5\24\u00b0\n\24\3\24\3\24\3\24\3\24\3\25\3"+
		"\25\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3\31\3\32\3\32\3\32\3\33\3"+
		"\33\3\34\3\34\3\34\3\35\3\35\3\36\3\36\3\36\3\37\3\37\3\37\3 \3 \3 \3"+
		"!\3!\3!\3!\3!\3\"\3\"\3\"\3\"\5\u008c\u009d\u00ab\2#\3\3\5\4\7\5\t\6\13"+
		"\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\2%\23\'"+
		"\24)\25+\26-\27/\30\61\31\63\32\65\33\67\349\35;\36=\37? A\2C\2\3\2\6"+
		"\5\2C\\aac|\4\2C\\c|\3\2\62;\5\2\13\f\17\17\"\"\u00e4\2\3\3\2\2\2\2\5"+
		"\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2"+
		"\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33"+
		"\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2"+
		")\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2"+
		"\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\3"+
		"E\3\2\2\2\5G\3\2\2\2\7I\3\2\2\2\tK\3\2\2\2\13M\3\2\2\2\rQ\3\2\2\2\17V"+
		"\3\2\2\2\21X\3\2\2\2\23Z\3\2\2\2\25]\3\2\2\2\27b\3\2\2\2\31i\3\2\2\2\33"+
		"k\3\2\2\2\35|\3\2\2\2\37\u0081\3\2\2\2!\u0087\3\2\2\2#\u0095\3\2\2\2%"+
		"\u0097\3\2\2\2\'\u00a5\3\2\2\2)\u00b5\3\2\2\2+\u00b7\3\2\2\2-\u00b9\3"+
		"\2\2\2/\u00bb\3\2\2\2\61\u00bd\3\2\2\2\63\u00c0\3\2\2\2\65\u00c3\3\2\2"+
		"\2\67\u00c5\3\2\2\29\u00c8\3\2\2\2;\u00ca\3\2\2\2=\u00cd\3\2\2\2?\u00d0"+
		"\3\2\2\2A\u00d3\3\2\2\2C\u00d8\3\2\2\2EF\7=\2\2F\4\3\2\2\2GH\7.\2\2H\6"+
		"\3\2\2\2IJ\7*\2\2J\b\3\2\2\2KL\7+\2\2L\n\3\2\2\2MN\7k\2\2NO\7p\2\2OP\7"+
		"v\2\2P\f\3\2\2\2QR\7x\2\2RS\7q\2\2ST\7k\2\2TU\7f\2\2U\16\3\2\2\2VW\7}"+
		"\2\2W\20\3\2\2\2XY\7\177\2\2Y\22\3\2\2\2Z[\7k\2\2[\\\7h\2\2\\\24\3\2\2"+
		"\2]^\7g\2\2^_\7n\2\2_`\7u\2\2`a\7g\2\2a\26\3\2\2\2bc\7t\2\2cd\7g\2\2d"+
		"e\7v\2\2ef\7w\2\2fg\7t\2\2gh\7p\2\2h\30\3\2\2\2ij\7?\2\2j\32\3\2\2\2k"+
		"x\t\2\2\2ln\t\3\2\2ml\3\2\2\2no\3\2\2\2om\3\2\2\2op\3\2\2\2pw\3\2\2\2"+
		"qs\t\4\2\2rq\3\2\2\2st\3\2\2\2tr\3\2\2\2tu\3\2\2\2uw\3\2\2\2vm\3\2\2\2"+
		"vr\3\2\2\2wz\3\2\2\2xv\3\2\2\2xy\3\2\2\2y\34\3\2\2\2zx\3\2\2\2{}\t\4\2"+
		"\2|{\3\2\2\2}~\3\2\2\2~|\3\2\2\2~\177\3\2\2\2\177\36\3\2\2\2\u0080\u0082"+
		"\t\5\2\2\u0081\u0080\3\2\2\2\u0082\u0083\3\2\2\2\u0083\u0081\3\2\2\2\u0083"+
		"\u0084\3\2\2\2\u0084\u0085\3\2\2\2\u0085\u0086\b\20\2\2\u0086 \3\2\2\2"+
		"\u0087\u008c\7$\2\2\u0088\u008b\5#\22\2\u0089\u008b\13\2\2\2\u008a\u0088"+
		"\3\2\2\2\u008a\u0089\3\2\2\2\u008b\u008e\3\2\2\2\u008c\u008d\3\2\2\2\u008c"+
		"\u008a\3\2\2\2\u008d\u008f\3\2\2\2\u008e\u008c\3\2\2\2\u008f\u0090\7$"+
		"\2\2\u0090\"\3\2\2\2\u0091\u0092\7^\2\2\u0092\u0096\7$\2\2\u0093\u0094"+
		"\7^\2\2\u0094\u0096\7^\2\2\u0095\u0091\3\2\2\2\u0095\u0093\3\2\2\2\u0096"+
		"$\3\2\2\2\u0097\u0098\7\61\2\2\u0098\u0099\7,\2\2\u0099\u009d\3\2\2\2"+
		"\u009a\u009c\13\2\2\2\u009b\u009a\3\2\2\2\u009c\u009f\3\2\2\2\u009d\u009e"+
		"\3\2\2\2\u009d\u009b\3\2\2\2\u009e\u00a0\3\2\2\2\u009f\u009d\3\2\2\2\u00a0"+
		"\u00a1\7,\2\2\u00a1\u00a2\7\61\2\2\u00a2\u00a3\3\2\2\2\u00a3\u00a4\b\23"+
		"\2\2\u00a4&\3\2\2\2\u00a5\u00a6\7\61\2\2\u00a6\u00a7\7\61\2\2\u00a7\u00ab"+
		"\3\2\2\2\u00a8\u00aa\13\2\2\2\u00a9\u00a8\3\2\2\2\u00aa\u00ad\3\2\2\2"+
		"\u00ab\u00ac\3\2\2\2\u00ab\u00a9\3\2\2\2\u00ac\u00af\3\2\2\2\u00ad\u00ab"+
		"\3\2\2\2\u00ae\u00b0\7\17\2\2\u00af\u00ae\3\2\2\2\u00af\u00b0\3\2\2\2"+
		"\u00b0\u00b1\3\2\2\2\u00b1\u00b2\7\f\2\2\u00b2\u00b3\3\2\2\2\u00b3\u00b4"+
		"\b\24\2\2\u00b4(\3\2\2\2\u00b5\u00b6\7,\2\2\u00b6*\3\2\2\2\u00b7\u00b8"+
		"\7\61\2\2\u00b8,\3\2\2\2\u00b9\u00ba\7-\2\2\u00ba.\3\2\2\2\u00bb\u00bc"+
		"\7/\2\2\u00bc\60\3\2\2\2\u00bd\u00be\7?\2\2\u00be\u00bf\7?\2\2\u00bf\62"+
		"\3\2\2\2\u00c0\u00c1\7#\2\2\u00c1\u00c2\7?\2\2\u00c2\64\3\2\2\2\u00c3"+
		"\u00c4\7>\2\2\u00c4\66\3\2\2\2\u00c5\u00c6\7>\2\2\u00c6\u00c7\7?\2\2\u00c7"+
		"8\3\2\2\2\u00c8\u00c9\7@\2\2\u00c9:\3\2\2\2\u00ca\u00cb\7@\2\2\u00cb\u00cc"+
		"\7?\2\2\u00cc<\3\2\2\2\u00cd\u00ce\7(\2\2\u00ce\u00cf\7(\2\2\u00cf>\3"+
		"\2\2\2\u00d0\u00d1\7~\2\2\u00d1\u00d2\7~\2\2\u00d2@\3\2\2\2\u00d3\u00d4"+
		"\7x\2\2\u00d4\u00d5\7q\2\2\u00d5\u00d6\7k\2\2\u00d6\u00d7\7f\2\2\u00d7"+
		"B\3\2\2\2\u00d8\u00d9\7k\2\2\u00d9\u00da\7p\2\2\u00da\u00db\7v\2\2\u00db"+
		"D\3\2\2\2\17\2otvx~\u0083\u008a\u008c\u0095\u009d\u00ab\u00af\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}