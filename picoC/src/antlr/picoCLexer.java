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
		T__17=18, VOIDTYPE=19, INTTYPE=20, CHARTYPE=21, MUL=22, DIV=23, MOD=24, 
		ADD=25, SUB=26, ASSIGN=27, ASSIGN_ADD=28, ASSIGN_SUB=29, ASSIGN_MUL=30, 
		ASSIGN_DIV=31, ASSIGN_MOD=32, EQUAL=33, NOT_EQUAL=34, LESS=35, LESS_EQUAL=36, 
		GREATER=37, GREATER_EQUAL=38, LOGICAL_AND=39, LOGICAL_OR=40, ID=41, INT=42, 
		WS=43, STRING_LITERAL=44, MULTY_LINE_COMMENT=45, SINGLE_LINE_COMMENT=46;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
		"T__17", "VOIDTYPE", "INTTYPE", "CHARTYPE", "MUL", "DIV", "MOD", "ADD", 
		"SUB", "ASSIGN", "ASSIGN_ADD", "ASSIGN_SUB", "ASSIGN_MUL", "ASSIGN_DIV", 
		"ASSIGN_MOD", "EQUAL", "NOT_EQUAL", "LESS", "LESS_EQUAL", "GREATER", "GREATER_EQUAL", 
		"LOGICAL_AND", "LOGICAL_OR", "ID", "INT", "WS", "STRING_LITERAL", "ESC", 
		"MULTY_LINE_COMMENT", "SINGLE_LINE_COMMENT"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "';'", "'('", "')'", "','", "'return'", "'break'", "'continue'", 
		"'{'", "'}'", "'if'", "'else'", "'for'", "'++'", "'--'", "'!'", "'&'", 
		"'?'", "':'", "'void'", "'int'", "'char'", "'*'", "'/'", "'%'", "'+'", 
		"'-'", "'='", "'+='", "'-='", "'*='", "'/='", "'%='", "'=='", "'!='", 
		"'<'", "'<='", "'>'", "'>='", "'&&'", "'||'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, "VOIDTYPE", "INTTYPE", "CHARTYPE", 
		"MUL", "DIV", "MOD", "ADD", "SUB", "ASSIGN", "ASSIGN_ADD", "ASSIGN_SUB", 
		"ASSIGN_MUL", "ASSIGN_DIV", "ASSIGN_MOD", "EQUAL", "NOT_EQUAL", "LESS", 
		"LESS_EQUAL", "GREATER", "GREATER_EQUAL", "LOGICAL_AND", "LOGICAL_OR", 
		"ID", "INT", "WS", "STRING_LITERAL", "MULTY_LINE_COMMENT", "SINGLE_LINE_COMMENT"
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\60\u0126\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3"+
		"\r\3\r\3\r\3\r\3\16\3\16\3\16\3\17\3\17\3\17\3\20\3\20\3\21\3\21\3\22"+
		"\3\22\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\26\3\26"+
		"\3\26\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33\3\34"+
		"\3\34\3\35\3\35\3\35\3\36\3\36\3\36\3\37\3\37\3\37\3 \3 \3 \3!\3!\3!\3"+
		"\"\3\"\3\"\3#\3#\3#\3$\3$\3%\3%\3%\3&\3&\3\'\3\'\3\'\3(\3(\3(\3)\3)\3"+
		")\3*\3*\6*\u00df\n*\r*\16*\u00e0\3*\6*\u00e4\n*\r*\16*\u00e5\7*\u00e8"+
		"\n*\f*\16*\u00eb\13*\3+\6+\u00ee\n+\r+\16+\u00ef\3,\6,\u00f3\n,\r,\16"+
		",\u00f4\3,\3,\3-\3-\3-\7-\u00fc\n-\f-\16-\u00ff\13-\3-\3-\3.\3.\3.\3."+
		"\5.\u0107\n.\3/\3/\3/\3/\7/\u010d\n/\f/\16/\u0110\13/\3/\3/\3/\3/\3/\3"+
		"\60\3\60\3\60\3\60\7\60\u011b\n\60\f\60\16\60\u011e\13\60\3\60\5\60\u0121"+
		"\n\60\3\60\3\60\3\60\3\60\5\u00fd\u010e\u011c\2\61\3\3\5\4\7\5\t\6\13"+
		"\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'"+
		"\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'"+
		"M(O)Q*S+U,W-Y.[\2]/_\60\3\2\6\5\2C\\aac|\4\2C\\c|\3\2\62;\5\2\13\f\17"+
		"\17\"\"\u0130\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2"+
		"\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2"+
		"\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2"+
		"\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2"+
		"\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3"+
		"\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2"+
		"\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2"+
		"S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\3a\3"+
		"\2\2\2\5c\3\2\2\2\7e\3\2\2\2\tg\3\2\2\2\13i\3\2\2\2\rp\3\2\2\2\17v\3\2"+
		"\2\2\21\177\3\2\2\2\23\u0081\3\2\2\2\25\u0083\3\2\2\2\27\u0086\3\2\2\2"+
		"\31\u008b\3\2\2\2\33\u008f\3\2\2\2\35\u0092\3\2\2\2\37\u0095\3\2\2\2!"+
		"\u0097\3\2\2\2#\u0099\3\2\2\2%\u009b\3\2\2\2\'\u009d\3\2\2\2)\u00a2\3"+
		"\2\2\2+\u00a6\3\2\2\2-\u00ab\3\2\2\2/\u00ad\3\2\2\2\61\u00af\3\2\2\2\63"+
		"\u00b1\3\2\2\2\65\u00b3\3\2\2\2\67\u00b5\3\2\2\29\u00b7\3\2\2\2;\u00ba"+
		"\3\2\2\2=\u00bd\3\2\2\2?\u00c0\3\2\2\2A\u00c3\3\2\2\2C\u00c6\3\2\2\2E"+
		"\u00c9\3\2\2\2G\u00cc\3\2\2\2I\u00ce\3\2\2\2K\u00d1\3\2\2\2M\u00d3\3\2"+
		"\2\2O\u00d6\3\2\2\2Q\u00d9\3\2\2\2S\u00dc\3\2\2\2U\u00ed\3\2\2\2W\u00f2"+
		"\3\2\2\2Y\u00f8\3\2\2\2[\u0106\3\2\2\2]\u0108\3\2\2\2_\u0116\3\2\2\2a"+
		"b\7=\2\2b\4\3\2\2\2cd\7*\2\2d\6\3\2\2\2ef\7+\2\2f\b\3\2\2\2gh\7.\2\2h"+
		"\n\3\2\2\2ij\7t\2\2jk\7g\2\2kl\7v\2\2lm\7w\2\2mn\7t\2\2no\7p\2\2o\f\3"+
		"\2\2\2pq\7d\2\2qr\7t\2\2rs\7g\2\2st\7c\2\2tu\7m\2\2u\16\3\2\2\2vw\7e\2"+
		"\2wx\7q\2\2xy\7p\2\2yz\7v\2\2z{\7k\2\2{|\7p\2\2|}\7w\2\2}~\7g\2\2~\20"+
		"\3\2\2\2\177\u0080\7}\2\2\u0080\22\3\2\2\2\u0081\u0082\7\177\2\2\u0082"+
		"\24\3\2\2\2\u0083\u0084\7k\2\2\u0084\u0085\7h\2\2\u0085\26\3\2\2\2\u0086"+
		"\u0087\7g\2\2\u0087\u0088\7n\2\2\u0088\u0089\7u\2\2\u0089\u008a\7g\2\2"+
		"\u008a\30\3\2\2\2\u008b\u008c\7h\2\2\u008c\u008d\7q\2\2\u008d\u008e\7"+
		"t\2\2\u008e\32\3\2\2\2\u008f\u0090\7-\2\2\u0090\u0091\7-\2\2\u0091\34"+
		"\3\2\2\2\u0092\u0093\7/\2\2\u0093\u0094\7/\2\2\u0094\36\3\2\2\2\u0095"+
		"\u0096\7#\2\2\u0096 \3\2\2\2\u0097\u0098\7(\2\2\u0098\"\3\2\2\2\u0099"+
		"\u009a\7A\2\2\u009a$\3\2\2\2\u009b\u009c\7<\2\2\u009c&\3\2\2\2\u009d\u009e"+
		"\7x\2\2\u009e\u009f\7q\2\2\u009f\u00a0\7k\2\2\u00a0\u00a1\7f\2\2\u00a1"+
		"(\3\2\2\2\u00a2\u00a3\7k\2\2\u00a3\u00a4\7p\2\2\u00a4\u00a5\7v\2\2\u00a5"+
		"*\3\2\2\2\u00a6\u00a7\7e\2\2\u00a7\u00a8\7j\2\2\u00a8\u00a9\7c\2\2\u00a9"+
		"\u00aa\7t\2\2\u00aa,\3\2\2\2\u00ab\u00ac\7,\2\2\u00ac.\3\2\2\2\u00ad\u00ae"+
		"\7\61\2\2\u00ae\60\3\2\2\2\u00af\u00b0\7\'\2\2\u00b0\62\3\2\2\2\u00b1"+
		"\u00b2\7-\2\2\u00b2\64\3\2\2\2\u00b3\u00b4\7/\2\2\u00b4\66\3\2\2\2\u00b5"+
		"\u00b6\7?\2\2\u00b68\3\2\2\2\u00b7\u00b8\7-\2\2\u00b8\u00b9\7?\2\2\u00b9"+
		":\3\2\2\2\u00ba\u00bb\7/\2\2\u00bb\u00bc\7?\2\2\u00bc<\3\2\2\2\u00bd\u00be"+
		"\7,\2\2\u00be\u00bf\7?\2\2\u00bf>\3\2\2\2\u00c0\u00c1\7\61\2\2\u00c1\u00c2"+
		"\7?\2\2\u00c2@\3\2\2\2\u00c3\u00c4\7\'\2\2\u00c4\u00c5\7?\2\2\u00c5B\3"+
		"\2\2\2\u00c6\u00c7\7?\2\2\u00c7\u00c8\7?\2\2\u00c8D\3\2\2\2\u00c9\u00ca"+
		"\7#\2\2\u00ca\u00cb\7?\2\2\u00cbF\3\2\2\2\u00cc\u00cd\7>\2\2\u00cdH\3"+
		"\2\2\2\u00ce\u00cf\7>\2\2\u00cf\u00d0\7?\2\2\u00d0J\3\2\2\2\u00d1\u00d2"+
		"\7@\2\2\u00d2L\3\2\2\2\u00d3\u00d4\7@\2\2\u00d4\u00d5\7?\2\2\u00d5N\3"+
		"\2\2\2\u00d6\u00d7\7(\2\2\u00d7\u00d8\7(\2\2\u00d8P\3\2\2\2\u00d9\u00da"+
		"\7~\2\2\u00da\u00db\7~\2\2\u00dbR\3\2\2\2\u00dc\u00e9\t\2\2\2\u00dd\u00df"+
		"\t\3\2\2\u00de\u00dd\3\2\2\2\u00df\u00e0\3\2\2\2\u00e0\u00de\3\2\2\2\u00e0"+
		"\u00e1\3\2\2\2\u00e1\u00e8\3\2\2\2\u00e2\u00e4\t\4\2\2\u00e3\u00e2\3\2"+
		"\2\2\u00e4\u00e5\3\2\2\2\u00e5\u00e3\3\2\2\2\u00e5\u00e6\3\2\2\2\u00e6"+
		"\u00e8\3\2\2\2\u00e7\u00de\3\2\2\2\u00e7\u00e3\3\2\2\2\u00e8\u00eb\3\2"+
		"\2\2\u00e9\u00e7\3\2\2\2\u00e9\u00ea\3\2\2\2\u00eaT\3\2\2\2\u00eb\u00e9"+
		"\3\2\2\2\u00ec\u00ee\t\4\2\2\u00ed\u00ec\3\2\2\2\u00ee\u00ef\3\2\2\2\u00ef"+
		"\u00ed\3\2\2\2\u00ef\u00f0\3\2\2\2\u00f0V\3\2\2\2\u00f1\u00f3\t\5\2\2"+
		"\u00f2\u00f1\3\2\2\2\u00f3\u00f4\3\2\2\2\u00f4\u00f2\3\2\2\2\u00f4\u00f5"+
		"\3\2\2\2\u00f5\u00f6\3\2\2\2\u00f6\u00f7\b,\2\2\u00f7X\3\2\2\2\u00f8\u00fd"+
		"\7$\2\2\u00f9\u00fc\5[.\2\u00fa\u00fc\13\2\2\2\u00fb\u00f9\3\2\2\2\u00fb"+
		"\u00fa\3\2\2\2\u00fc\u00ff\3\2\2\2\u00fd\u00fe\3\2\2\2\u00fd\u00fb\3\2"+
		"\2\2\u00fe\u0100\3\2\2\2\u00ff\u00fd\3\2\2\2\u0100\u0101\7$\2\2\u0101"+
		"Z\3\2\2\2\u0102\u0103\7^\2\2\u0103\u0107\7$\2\2\u0104\u0105\7^\2\2\u0105"+
		"\u0107\7^\2\2\u0106\u0102\3\2\2\2\u0106\u0104\3\2\2\2\u0107\\\3\2\2\2"+
		"\u0108\u0109\7\61\2\2\u0109\u010a\7,\2\2\u010a\u010e\3\2\2\2\u010b\u010d"+
		"\13\2\2\2\u010c\u010b\3\2\2\2\u010d\u0110\3\2\2\2\u010e\u010f\3\2\2\2"+
		"\u010e\u010c\3\2\2\2\u010f\u0111\3\2\2\2\u0110\u010e\3\2\2\2\u0111\u0112"+
		"\7,\2\2\u0112\u0113\7\61\2\2\u0113\u0114\3\2\2\2\u0114\u0115\b/\2\2\u0115"+
		"^\3\2\2\2\u0116\u0117\7\61\2\2\u0117\u0118\7\61\2\2\u0118\u011c\3\2\2"+
		"\2\u0119\u011b\13\2\2\2\u011a\u0119\3\2\2\2\u011b\u011e\3\2\2\2\u011c"+
		"\u011d\3\2\2\2\u011c\u011a\3\2\2\2\u011d\u0120\3\2\2\2\u011e\u011c\3\2"+
		"\2\2\u011f\u0121\7\17\2\2\u0120\u011f\3\2\2\2\u0120\u0121\3\2\2\2\u0121"+
		"\u0122\3\2\2\2\u0122\u0123\7\f\2\2\u0123\u0124\3\2\2\2\u0124\u0125\b\60"+
		"\2\2\u0125`\3\2\2\2\17\2\u00e0\u00e5\u00e7\u00e9\u00ef\u00f4\u00fb\u00fd"+
		"\u0106\u010e\u011c\u0120\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}