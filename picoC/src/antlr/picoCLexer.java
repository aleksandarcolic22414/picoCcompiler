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
		T__9=10, ID=11, INT=12, WS=13, STRING_LITERAL=14, MULTY_LINE_COMMENT=15, 
		SINGLE_LINE_COMMENT=16, MUL=17, DIV=18, ADD=19, SUB=20, EQUAL=21, NOT_EQUAL=22, 
		LESS=23, LESS_EQUAL=24, GREATER=25, GREATER_EQUAL=26, LOGICAL_AND=27, 
		LOGICAL_OR=28;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "ID", "INT", "WS", "STRING_LITERAL", "ESC", "MULTY_LINE_COMMENT", 
		"SINGLE_LINE_COMMENT", "MUL", "DIV", "ADD", "SUB", "EQUAL", "NOT_EQUAL", 
		"LESS", "LESS_EQUAL", "GREATER", "GREATER_EQUAL", "LOGICAL_AND", "LOGICAL_OR", 
		"VOIDTYPE", "INTTYPE"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "';'", "','", "'('", "')'", "'int'", "'void'", "'{'", "'}'", "'return'", 
		"'='", null, null, null, null, null, null, "'*'", "'/'", "'+'", "'-'", 
		"'=='", "'!='", "'<'", "'<='", "'>'", "'>='", "'&&'", "'||'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, "ID", 
		"INT", "WS", "STRING_LITERAL", "MULTY_LINE_COMMENT", "SINGLE_LINE_COMMENT", 
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\36\u00d0\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7"+
		"\3\b\3\b\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\f\3\f\6\fb\n"+
		"\f\r\f\16\fc\3\f\6\fg\n\f\r\f\16\fh\7\fk\n\f\f\f\16\fn\13\f\3\r\6\rq\n"+
		"\r\r\r\16\rr\3\16\6\16v\n\16\r\16\16\16w\3\16\3\16\3\17\3\17\3\17\7\17"+
		"\177\n\17\f\17\16\17\u0082\13\17\3\17\3\17\3\20\3\20\3\20\3\20\5\20\u008a"+
		"\n\20\3\21\3\21\3\21\3\21\7\21\u0090\n\21\f\21\16\21\u0093\13\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\7\22\u009e\n\22\f\22\16\22\u00a1"+
		"\13\22\3\22\5\22\u00a4\n\22\3\22\3\22\3\22\3\22\3\23\3\23\3\24\3\24\3"+
		"\25\3\25\3\26\3\26\3\27\3\27\3\27\3\30\3\30\3\30\3\31\3\31\3\32\3\32\3"+
		"\32\3\33\3\33\3\34\3\34\3\34\3\35\3\35\3\35\3\36\3\36\3\36\3\37\3\37\3"+
		"\37\3\37\3\37\3 \3 \3 \3 \5\u0080\u0091\u009f\2!\3\3\5\4\7\5\t\6\13\7"+
		"\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\2!\21#\22%\23\'\24"+
		")\25+\26-\27/\30\61\31\63\32\65\33\67\349\35;\36=\2?\2\3\2\6\5\2C\\aa"+
		"c|\4\2C\\c|\3\2\62;\5\2\13\f\17\17\"\"\u00d8\2\3\3\2\2\2\2\5\3\2\2\2\2"+
		"\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2"+
		"\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2"+
		"\2\35\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2"+
		"\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2"+
		"\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\3A\3\2\2\2\5C\3\2\2\2\7E\3\2\2\2"+
		"\tG\3\2\2\2\13I\3\2\2\2\rM\3\2\2\2\17R\3\2\2\2\21T\3\2\2\2\23V\3\2\2\2"+
		"\25]\3\2\2\2\27_\3\2\2\2\31p\3\2\2\2\33u\3\2\2\2\35{\3\2\2\2\37\u0089"+
		"\3\2\2\2!\u008b\3\2\2\2#\u0099\3\2\2\2%\u00a9\3\2\2\2\'\u00ab\3\2\2\2"+
		")\u00ad\3\2\2\2+\u00af\3\2\2\2-\u00b1\3\2\2\2/\u00b4\3\2\2\2\61\u00b7"+
		"\3\2\2\2\63\u00b9\3\2\2\2\65\u00bc\3\2\2\2\67\u00be\3\2\2\29\u00c1\3\2"+
		"\2\2;\u00c4\3\2\2\2=\u00c7\3\2\2\2?\u00cc\3\2\2\2AB\7=\2\2B\4\3\2\2\2"+
		"CD\7.\2\2D\6\3\2\2\2EF\7*\2\2F\b\3\2\2\2GH\7+\2\2H\n\3\2\2\2IJ\7k\2\2"+
		"JK\7p\2\2KL\7v\2\2L\f\3\2\2\2MN\7x\2\2NO\7q\2\2OP\7k\2\2PQ\7f\2\2Q\16"+
		"\3\2\2\2RS\7}\2\2S\20\3\2\2\2TU\7\177\2\2U\22\3\2\2\2VW\7t\2\2WX\7g\2"+
		"\2XY\7v\2\2YZ\7w\2\2Z[\7t\2\2[\\\7p\2\2\\\24\3\2\2\2]^\7?\2\2^\26\3\2"+
		"\2\2_l\t\2\2\2`b\t\3\2\2a`\3\2\2\2bc\3\2\2\2ca\3\2\2\2cd\3\2\2\2dk\3\2"+
		"\2\2eg\t\4\2\2fe\3\2\2\2gh\3\2\2\2hf\3\2\2\2hi\3\2\2\2ik\3\2\2\2ja\3\2"+
		"\2\2jf\3\2\2\2kn\3\2\2\2lj\3\2\2\2lm\3\2\2\2m\30\3\2\2\2nl\3\2\2\2oq\t"+
		"\4\2\2po\3\2\2\2qr\3\2\2\2rp\3\2\2\2rs\3\2\2\2s\32\3\2\2\2tv\t\5\2\2u"+
		"t\3\2\2\2vw\3\2\2\2wu\3\2\2\2wx\3\2\2\2xy\3\2\2\2yz\b\16\2\2z\34\3\2\2"+
		"\2{\u0080\7$\2\2|\177\5\37\20\2}\177\13\2\2\2~|\3\2\2\2~}\3\2\2\2\177"+
		"\u0082\3\2\2\2\u0080\u0081\3\2\2\2\u0080~\3\2\2\2\u0081\u0083\3\2\2\2"+
		"\u0082\u0080\3\2\2\2\u0083\u0084\7$\2\2\u0084\36\3\2\2\2\u0085\u0086\7"+
		"^\2\2\u0086\u008a\7$\2\2\u0087\u0088\7^\2\2\u0088\u008a\7^\2\2\u0089\u0085"+
		"\3\2\2\2\u0089\u0087\3\2\2\2\u008a \3\2\2\2\u008b\u008c\7\61\2\2\u008c"+
		"\u008d\7,\2\2\u008d\u0091\3\2\2\2\u008e\u0090\13\2\2\2\u008f\u008e\3\2"+
		"\2\2\u0090\u0093\3\2\2\2\u0091\u0092\3\2\2\2\u0091\u008f\3\2\2\2\u0092"+
		"\u0094\3\2\2\2\u0093\u0091\3\2\2\2\u0094\u0095\7,\2\2\u0095\u0096\7\61"+
		"\2\2\u0096\u0097\3\2\2\2\u0097\u0098\b\21\2\2\u0098\"\3\2\2\2\u0099\u009a"+
		"\7\61\2\2\u009a\u009b\7\61\2\2\u009b\u009f\3\2\2\2\u009c\u009e\13\2\2"+
		"\2\u009d\u009c\3\2\2\2\u009e\u00a1\3\2\2\2\u009f\u00a0\3\2\2\2\u009f\u009d"+
		"\3\2\2\2\u00a0\u00a3\3\2\2\2\u00a1\u009f\3\2\2\2\u00a2\u00a4\7\17\2\2"+
		"\u00a3\u00a2\3\2\2\2\u00a3\u00a4\3\2\2\2\u00a4\u00a5\3\2\2\2\u00a5\u00a6"+
		"\7\f\2\2\u00a6\u00a7\3\2\2\2\u00a7\u00a8\b\22\2\2\u00a8$\3\2\2\2\u00a9"+
		"\u00aa\7,\2\2\u00aa&\3\2\2\2\u00ab\u00ac\7\61\2\2\u00ac(\3\2\2\2\u00ad"+
		"\u00ae\7-\2\2\u00ae*\3\2\2\2\u00af\u00b0\7/\2\2\u00b0,\3\2\2\2\u00b1\u00b2"+
		"\7?\2\2\u00b2\u00b3\7?\2\2\u00b3.\3\2\2\2\u00b4\u00b5\7#\2\2\u00b5\u00b6"+
		"\7?\2\2\u00b6\60\3\2\2\2\u00b7\u00b8\7>\2\2\u00b8\62\3\2\2\2\u00b9\u00ba"+
		"\7>\2\2\u00ba\u00bb\7?\2\2\u00bb\64\3\2\2\2\u00bc\u00bd\7@\2\2\u00bd\66"+
		"\3\2\2\2\u00be\u00bf\7@\2\2\u00bf\u00c0\7?\2\2\u00c08\3\2\2\2\u00c1\u00c2"+
		"\7(\2\2\u00c2\u00c3\7(\2\2\u00c3:\3\2\2\2\u00c4\u00c5\7~\2\2\u00c5\u00c6"+
		"\7~\2\2\u00c6<\3\2\2\2\u00c7\u00c8\7x\2\2\u00c8\u00c9\7q\2\2\u00c9\u00ca"+
		"\7k\2\2\u00ca\u00cb\7f\2\2\u00cb>\3\2\2\2\u00cc\u00cd\7k\2\2\u00cd\u00ce"+
		"\7p\2\2\u00ce\u00cf\7v\2\2\u00cf@\3\2\2\2\17\2chjlrw~\u0080\u0089\u0091"+
		"\u009f\u00a3\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}