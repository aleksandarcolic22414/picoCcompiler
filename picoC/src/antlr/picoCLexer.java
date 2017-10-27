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
		T__17=18, T__18=19, ID=20, INT=21, WS=22, STRING_LITERAL=23, MULTY_LINE_COMMENT=24, 
		SINGLE_LINE_COMMENT=25, MUL=26, DIV=27, MOD=28, ADD=29, SUB=30, ASSIGN=31, 
		ASSIGN_ADD=32, ASSIGN_SUB=33, ASSIGN_MUL=34, ASSIGN_DIV=35, ASSIGN_MOD=36, 
		EQUAL=37, NOT_EQUAL=38, LESS=39, LESS_EQUAL=40, GREATER=41, GREATER_EQUAL=42, 
		LOGICAL_AND=43, LOGICAL_OR=44;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
		"T__17", "T__18", "ID", "INT", "WS", "STRING_LITERAL", "ESC", "MULTY_LINE_COMMENT", 
		"SINGLE_LINE_COMMENT", "MUL", "DIV", "MOD", "ADD", "SUB", "ASSIGN", "ASSIGN_ADD", 
		"ASSIGN_SUB", "ASSIGN_MUL", "ASSIGN_DIV", "ASSIGN_MOD", "EQUAL", "NOT_EQUAL", 
		"LESS", "LESS_EQUAL", "GREATER", "GREATER_EQUAL", "LOGICAL_AND", "LOGICAL_OR", 
		"VOIDTYPE", "INTTYPE"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "';'", "','", "'('", "')'", "'int'", "'void'", "'return'", "'break'", 
		"'continue'", "'{'", "'}'", "'if'", "'else'", "'for'", "'++'", "'--'", 
		"'!'", "'?'", "':'", null, null, null, null, null, null, "'*'", "'/'", 
		"'%'", "'+'", "'-'", "'='", "'+='", "'-='", "'*='", "'/='", "'%='", "'=='", 
		"'!='", "'<'", "'<='", "'>'", "'>='", "'&&'", "'||'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, "ID", "INT", "WS", "STRING_LITERAL", 
		"MULTY_LINE_COMMENT", "SINGLE_LINE_COMMENT", "MUL", "DIV", "MOD", "ADD", 
		"SUB", "ASSIGN", "ASSIGN_ADD", "ASSIGN_SUB", "ASSIGN_MUL", "ASSIGN_DIV", 
		"ASSIGN_MOD", "EQUAL", "NOT_EQUAL", "LESS", "LESS_EQUAL", "GREATER", "GREATER_EQUAL", 
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2.\u0128\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3"+
		"\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\f\3\f\3\r"+
		"\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3"+
		"\21\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\6\25\u00a7\n\25"+
		"\r\25\16\25\u00a8\3\25\6\25\u00ac\n\25\r\25\16\25\u00ad\7\25\u00b0\n\25"+
		"\f\25\16\25\u00b3\13\25\3\26\6\26\u00b6\n\26\r\26\16\26\u00b7\3\27\6\27"+
		"\u00bb\n\27\r\27\16\27\u00bc\3\27\3\27\3\30\3\30\3\30\7\30\u00c4\n\30"+
		"\f\30\16\30\u00c7\13\30\3\30\3\30\3\31\3\31\3\31\3\31\5\31\u00cf\n\31"+
		"\3\32\3\32\3\32\3\32\7\32\u00d5\n\32\f\32\16\32\u00d8\13\32\3\32\3\32"+
		"\3\32\3\32\3\32\3\33\3\33\3\33\3\33\7\33\u00e3\n\33\f\33\16\33\u00e6\13"+
		"\33\3\33\5\33\u00e9\n\33\3\33\3\33\3\33\3\33\3\34\3\34\3\35\3\35\3\36"+
		"\3\36\3\37\3\37\3 \3 \3!\3!\3\"\3\"\3\"\3#\3#\3#\3$\3$\3$\3%\3%\3%\3&"+
		"\3&\3&\3\'\3\'\3\'\3(\3(\3(\3)\3)\3*\3*\3*\3+\3+\3,\3,\3,\3-\3-\3-\3."+
		"\3.\3.\3/\3/\3/\3/\3/\3\60\3\60\3\60\3\60\5\u00c5\u00d6\u00e4\2\61\3\3"+
		"\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21"+
		"!\22#\23%\24\'\25)\26+\27-\30/\31\61\2\63\32\65\33\67\349\35;\36=\37?"+
		" A!C\"E#G$I%K&M\'O(Q)S*U+W,Y-[.]\2_\2\3\2\6\5\2C\\aac|\4\2C\\c|\3\2\62"+
		";\5\2\13\f\17\17\"\"\u0130\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2"+
		"\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2"+
		"\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3"+
		"\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2"+
		"\2\2\2-\3\2\2\2\2/\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3"+
		"\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2"+
		"\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2"+
		"S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\3a\3\2\2\2\5c\3"+
		"\2\2\2\7e\3\2\2\2\tg\3\2\2\2\13i\3\2\2\2\rm\3\2\2\2\17r\3\2\2\2\21y\3"+
		"\2\2\2\23\177\3\2\2\2\25\u0088\3\2\2\2\27\u008a\3\2\2\2\31\u008c\3\2\2"+
		"\2\33\u008f\3\2\2\2\35\u0094\3\2\2\2\37\u0098\3\2\2\2!\u009b\3\2\2\2#"+
		"\u009e\3\2\2\2%\u00a0\3\2\2\2\'\u00a2\3\2\2\2)\u00a4\3\2\2\2+\u00b5\3"+
		"\2\2\2-\u00ba\3\2\2\2/\u00c0\3\2\2\2\61\u00ce\3\2\2\2\63\u00d0\3\2\2\2"+
		"\65\u00de\3\2\2\2\67\u00ee\3\2\2\29\u00f0\3\2\2\2;\u00f2\3\2\2\2=\u00f4"+
		"\3\2\2\2?\u00f6\3\2\2\2A\u00f8\3\2\2\2C\u00fa\3\2\2\2E\u00fd\3\2\2\2G"+
		"\u0100\3\2\2\2I\u0103\3\2\2\2K\u0106\3\2\2\2M\u0109\3\2\2\2O\u010c\3\2"+
		"\2\2Q\u010f\3\2\2\2S\u0111\3\2\2\2U\u0114\3\2\2\2W\u0116\3\2\2\2Y\u0119"+
		"\3\2\2\2[\u011c\3\2\2\2]\u011f\3\2\2\2_\u0124\3\2\2\2ab\7=\2\2b\4\3\2"+
		"\2\2cd\7.\2\2d\6\3\2\2\2ef\7*\2\2f\b\3\2\2\2gh\7+\2\2h\n\3\2\2\2ij\7k"+
		"\2\2jk\7p\2\2kl\7v\2\2l\f\3\2\2\2mn\7x\2\2no\7q\2\2op\7k\2\2pq\7f\2\2"+
		"q\16\3\2\2\2rs\7t\2\2st\7g\2\2tu\7v\2\2uv\7w\2\2vw\7t\2\2wx\7p\2\2x\20"+
		"\3\2\2\2yz\7d\2\2z{\7t\2\2{|\7g\2\2|}\7c\2\2}~\7m\2\2~\22\3\2\2\2\177"+
		"\u0080\7e\2\2\u0080\u0081\7q\2\2\u0081\u0082\7p\2\2\u0082\u0083\7v\2\2"+
		"\u0083\u0084\7k\2\2\u0084\u0085\7p\2\2\u0085\u0086\7w\2\2\u0086\u0087"+
		"\7g\2\2\u0087\24\3\2\2\2\u0088\u0089\7}\2\2\u0089\26\3\2\2\2\u008a\u008b"+
		"\7\177\2\2\u008b\30\3\2\2\2\u008c\u008d\7k\2\2\u008d\u008e\7h\2\2\u008e"+
		"\32\3\2\2\2\u008f\u0090\7g\2\2\u0090\u0091\7n\2\2\u0091\u0092\7u\2\2\u0092"+
		"\u0093\7g\2\2\u0093\34\3\2\2\2\u0094\u0095\7h\2\2\u0095\u0096\7q\2\2\u0096"+
		"\u0097\7t\2\2\u0097\36\3\2\2\2\u0098\u0099\7-\2\2\u0099\u009a\7-\2\2\u009a"+
		" \3\2\2\2\u009b\u009c\7/\2\2\u009c\u009d\7/\2\2\u009d\"\3\2\2\2\u009e"+
		"\u009f\7#\2\2\u009f$\3\2\2\2\u00a0\u00a1\7A\2\2\u00a1&\3\2\2\2\u00a2\u00a3"+
		"\7<\2\2\u00a3(\3\2\2\2\u00a4\u00b1\t\2\2\2\u00a5\u00a7\t\3\2\2\u00a6\u00a5"+
		"\3\2\2\2\u00a7\u00a8\3\2\2\2\u00a8\u00a6\3\2\2\2\u00a8\u00a9\3\2\2\2\u00a9"+
		"\u00b0\3\2\2\2\u00aa\u00ac\t\4\2\2\u00ab\u00aa\3\2\2\2\u00ac\u00ad\3\2"+
		"\2\2\u00ad\u00ab\3\2\2\2\u00ad\u00ae\3\2\2\2\u00ae\u00b0\3\2\2\2\u00af"+
		"\u00a6\3\2\2\2\u00af\u00ab\3\2\2\2\u00b0\u00b3\3\2\2\2\u00b1\u00af\3\2"+
		"\2\2\u00b1\u00b2\3\2\2\2\u00b2*\3\2\2\2\u00b3\u00b1\3\2\2\2\u00b4\u00b6"+
		"\t\4\2\2\u00b5\u00b4\3\2\2\2\u00b6\u00b7\3\2\2\2\u00b7\u00b5\3\2\2\2\u00b7"+
		"\u00b8\3\2\2\2\u00b8,\3\2\2\2\u00b9\u00bb\t\5\2\2\u00ba\u00b9\3\2\2\2"+
		"\u00bb\u00bc\3\2\2\2\u00bc\u00ba\3\2\2\2\u00bc\u00bd\3\2\2\2\u00bd\u00be"+
		"\3\2\2\2\u00be\u00bf\b\27\2\2\u00bf.\3\2\2\2\u00c0\u00c5\7$\2\2\u00c1"+
		"\u00c4\5\61\31\2\u00c2\u00c4\13\2\2\2\u00c3\u00c1\3\2\2\2\u00c3\u00c2"+
		"\3\2\2\2\u00c4\u00c7\3\2\2\2\u00c5\u00c6\3\2\2\2\u00c5\u00c3\3\2\2\2\u00c6"+
		"\u00c8\3\2\2\2\u00c7\u00c5\3\2\2\2\u00c8\u00c9\7$\2\2\u00c9\60\3\2\2\2"+
		"\u00ca\u00cb\7^\2\2\u00cb\u00cf\7$\2\2\u00cc\u00cd\7^\2\2\u00cd\u00cf"+
		"\7^\2\2\u00ce\u00ca\3\2\2\2\u00ce\u00cc\3\2\2\2\u00cf\62\3\2\2\2\u00d0"+
		"\u00d1\7\61\2\2\u00d1\u00d2\7,\2\2\u00d2\u00d6\3\2\2\2\u00d3\u00d5\13"+
		"\2\2\2\u00d4\u00d3\3\2\2\2\u00d5\u00d8\3\2\2\2\u00d6\u00d7\3\2\2\2\u00d6"+
		"\u00d4\3\2\2\2\u00d7\u00d9\3\2\2\2\u00d8\u00d6\3\2\2\2\u00d9\u00da\7,"+
		"\2\2\u00da\u00db\7\61\2\2\u00db\u00dc\3\2\2\2\u00dc\u00dd\b\32\2\2\u00dd"+
		"\64\3\2\2\2\u00de\u00df\7\61\2\2\u00df\u00e0\7\61\2\2\u00e0\u00e4\3\2"+
		"\2\2\u00e1\u00e3\13\2\2\2\u00e2\u00e1\3\2\2\2\u00e3\u00e6\3\2\2\2\u00e4"+
		"\u00e5\3\2\2\2\u00e4\u00e2\3\2\2\2\u00e5\u00e8\3\2\2\2\u00e6\u00e4\3\2"+
		"\2\2\u00e7\u00e9\7\17\2\2\u00e8\u00e7\3\2\2\2\u00e8\u00e9\3\2\2\2\u00e9"+
		"\u00ea\3\2\2\2\u00ea\u00eb\7\f\2\2\u00eb\u00ec\3\2\2\2\u00ec\u00ed\b\33"+
		"\2\2\u00ed\66\3\2\2\2\u00ee\u00ef\7,\2\2\u00ef8\3\2\2\2\u00f0\u00f1\7"+
		"\61\2\2\u00f1:\3\2\2\2\u00f2\u00f3\7\'\2\2\u00f3<\3\2\2\2\u00f4\u00f5"+
		"\7-\2\2\u00f5>\3\2\2\2\u00f6\u00f7\7/\2\2\u00f7@\3\2\2\2\u00f8\u00f9\7"+
		"?\2\2\u00f9B\3\2\2\2\u00fa\u00fb\7-\2\2\u00fb\u00fc\7?\2\2\u00fcD\3\2"+
		"\2\2\u00fd\u00fe\7/\2\2\u00fe\u00ff\7?\2\2\u00ffF\3\2\2\2\u0100\u0101"+
		"\7,\2\2\u0101\u0102\7?\2\2\u0102H\3\2\2\2\u0103\u0104\7\61\2\2\u0104\u0105"+
		"\7?\2\2\u0105J\3\2\2\2\u0106\u0107\7\'\2\2\u0107\u0108\7?\2\2\u0108L\3"+
		"\2\2\2\u0109\u010a\7?\2\2\u010a\u010b\7?\2\2\u010bN\3\2\2\2\u010c\u010d"+
		"\7#\2\2\u010d\u010e\7?\2\2\u010eP\3\2\2\2\u010f\u0110\7>\2\2\u0110R\3"+
		"\2\2\2\u0111\u0112\7>\2\2\u0112\u0113\7?\2\2\u0113T\3\2\2\2\u0114\u0115"+
		"\7@\2\2\u0115V\3\2\2\2\u0116\u0117\7@\2\2\u0117\u0118\7?\2\2\u0118X\3"+
		"\2\2\2\u0119\u011a\7(\2\2\u011a\u011b\7(\2\2\u011bZ\3\2\2\2\u011c\u011d"+
		"\7~\2\2\u011d\u011e\7~\2\2\u011e\\\3\2\2\2\u011f\u0120\7x\2\2\u0120\u0121"+
		"\7q\2\2\u0121\u0122\7k\2\2\u0122\u0123\7f\2\2\u0123^\3\2\2\2\u0124\u0125"+
		"\7k\2\2\u0125\u0126\7p\2\2\u0126\u0127\7v\2\2\u0127`\3\2\2\2\17\2\u00a8"+
		"\u00ad\u00af\u00b1\u00b7\u00bc\u00c3\u00c5\u00ce\u00d6\u00e4\u00e8\3\b"+
		"\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}