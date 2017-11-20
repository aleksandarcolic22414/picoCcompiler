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
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, VOIDTYPE=23, INTTYPE=24, 
		CHARTYPE=25, ASSIGN=26, ASSIGN_ADD=27, ASSIGN_SUB=28, ASSIGN_MUL=29, ASSIGN_DIV=30, 
		ASSIGN_MOD=31, MUL=32, DIV=33, MOD=34, ADD=35, SUB=36, EQUAL=37, NOT_EQUAL=38, 
		LESS=39, LESS_EQUAL=40, GREATER=41, GREATER_EQUAL=42, LOGICAL_AND=43, 
		LOGICAL_OR=44, ID=45, INT=46, CHAR=47, STRING_LITERAL=48, MULTY_LINE_COMMENT=49, 
		SINGLE_LINE_COMMENT=50, WHITE_SPACE=51;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
		"T__17", "T__18", "T__19", "T__20", "T__21", "VOIDTYPE", "INTTYPE", "CHARTYPE", 
		"ASSIGN", "ASSIGN_ADD", "ASSIGN_SUB", "ASSIGN_MUL", "ASSIGN_DIV", "ASSIGN_MOD", 
		"MUL", "DIV", "MOD", "ADD", "SUB", "EQUAL", "NOT_EQUAL", "LESS", "LESS_EQUAL", 
		"GREATER", "GREATER_EQUAL", "LOGICAL_AND", "LOGICAL_OR", "ID", "INT", 
		"CHAR", "STRING_LITERAL", "ESC", "MULTY_LINE_COMMENT", "SINGLE_LINE_COMMENT", 
		"WHITE_SPACE"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "';'", "'('", "')'", "'++'", "'--'", "'!'", "'&'", "'?'", "':'", 
		"','", "'['", "']'", "'return'", "'break'", "'continue'", "'{'", "'}'", 
		"'if'", "'else'", "'for'", "'while'", "'do'", "'void'", "'int'", "'char'", 
		"'='", "'+='", "'-='", "'*='", "'/='", "'%='", "'*'", "'/'", "'%'", "'+'", 
		"'-'", "'=='", "'!='", "'<'", "'<='", "'>'", "'>='", "'&&'", "'||'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, "VOIDTYPE", 
		"INTTYPE", "CHARTYPE", "ASSIGN", "ASSIGN_ADD", "ASSIGN_SUB", "ASSIGN_MUL", 
		"ASSIGN_DIV", "ASSIGN_MOD", "MUL", "DIV", "MOD", "ADD", "SUB", "EQUAL", 
		"NOT_EQUAL", "LESS", "LESS_EQUAL", "GREATER", "GREATER_EQUAL", "LOGICAL_AND", 
		"LOGICAL_OR", "ID", "INT", "CHAR", "STRING_LITERAL", "MULTY_LINE_COMMENT", 
		"SINGLE_LINE_COMMENT", "WHITE_SPACE"
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\65\u0147\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64"+
		"\t\64\4\65\t\65\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3\6\3\6\3\6\3\7\3"+
		"\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\16\3"+
		"\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3"+
		"\20\3\20\3\20\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\23\3\24\3\24\3"+
		"\24\3\24\3\24\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26\3\27\3"+
		"\27\3\27\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3"+
		"\32\3\32\3\33\3\33\3\34\3\34\3\34\3\35\3\35\3\35\3\36\3\36\3\36\3\37\3"+
		"\37\3\37\3 \3 \3 \3!\3!\3\"\3\"\3#\3#\3$\3$\3%\3%\3&\3&\3&\3\'\3\'\3\'"+
		"\3(\3(\3)\3)\3)\3*\3*\3+\3+\3+\3,\3,\3,\3-\3-\3-\3.\3.\6.\u00f6\n.\r."+
		"\16.\u00f7\3.\6.\u00fb\n.\r.\16.\u00fc\7.\u00ff\n.\f.\16.\u0102\13.\3"+
		"/\6/\u0105\n/\r/\16/\u0106\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60\5\60"+
		"\u0111\n\60\3\61\3\61\3\61\7\61\u0116\n\61\f\61\16\61\u0119\13\61\3\61"+
		"\3\61\3\62\3\62\3\62\3\62\5\62\u0121\n\62\3\63\3\63\3\63\3\63\7\63\u0127"+
		"\n\63\f\63\16\63\u012a\13\63\3\63\3\63\3\63\3\63\3\63\3\64\3\64\3\64\3"+
		"\64\7\64\u0135\n\64\f\64\16\64\u0138\13\64\3\64\5\64\u013b\n\64\3\64\3"+
		"\64\3\64\3\64\3\65\6\65\u0142\n\65\r\65\16\65\u0143\3\65\3\65\5\u0117"+
		"\u0128\u0136\2\66\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31"+
		"\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65"+
		"\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60_\61a\62c\2e\63"+
		"g\64i\65\3\2\6\5\2C\\aac|\4\2C\\c|\3\2\62;\5\2\13\f\17\17\"\"\u0152\2"+
		"\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2"+
		"\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2"+
		"\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2"+
		"\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2"+
		"\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2"+
		"\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2"+
		"\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U"+
		"\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2"+
		"\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\3k\3\2\2\2\5m\3\2\2\2\7o\3\2\2\2"+
		"\tq\3\2\2\2\13t\3\2\2\2\rw\3\2\2\2\17y\3\2\2\2\21{\3\2\2\2\23}\3\2\2\2"+
		"\25\177\3\2\2\2\27\u0081\3\2\2\2\31\u0083\3\2\2\2\33\u0085\3\2\2\2\35"+
		"\u008c\3\2\2\2\37\u0092\3\2\2\2!\u009b\3\2\2\2#\u009d\3\2\2\2%\u009f\3"+
		"\2\2\2\'\u00a2\3\2\2\2)\u00a7\3\2\2\2+\u00ab\3\2\2\2-\u00b1\3\2\2\2/\u00b4"+
		"\3\2\2\2\61\u00b9\3\2\2\2\63\u00bd\3\2\2\2\65\u00c2\3\2\2\2\67\u00c4\3"+
		"\2\2\29\u00c7\3\2\2\2;\u00ca\3\2\2\2=\u00cd\3\2\2\2?\u00d0\3\2\2\2A\u00d3"+
		"\3\2\2\2C\u00d5\3\2\2\2E\u00d7\3\2\2\2G\u00d9\3\2\2\2I\u00db\3\2\2\2K"+
		"\u00dd\3\2\2\2M\u00e0\3\2\2\2O\u00e3\3\2\2\2Q\u00e5\3\2\2\2S\u00e8\3\2"+
		"\2\2U\u00ea\3\2\2\2W\u00ed\3\2\2\2Y\u00f0\3\2\2\2[\u00f3\3\2\2\2]\u0104"+
		"\3\2\2\2_\u0110\3\2\2\2a\u0112\3\2\2\2c\u0120\3\2\2\2e\u0122\3\2\2\2g"+
		"\u0130\3\2\2\2i\u0141\3\2\2\2kl\7=\2\2l\4\3\2\2\2mn\7*\2\2n\6\3\2\2\2"+
		"op\7+\2\2p\b\3\2\2\2qr\7-\2\2rs\7-\2\2s\n\3\2\2\2tu\7/\2\2uv\7/\2\2v\f"+
		"\3\2\2\2wx\7#\2\2x\16\3\2\2\2yz\7(\2\2z\20\3\2\2\2{|\7A\2\2|\22\3\2\2"+
		"\2}~\7<\2\2~\24\3\2\2\2\177\u0080\7.\2\2\u0080\26\3\2\2\2\u0081\u0082"+
		"\7]\2\2\u0082\30\3\2\2\2\u0083\u0084\7_\2\2\u0084\32\3\2\2\2\u0085\u0086"+
		"\7t\2\2\u0086\u0087\7g\2\2\u0087\u0088\7v\2\2\u0088\u0089\7w\2\2\u0089"+
		"\u008a\7t\2\2\u008a\u008b\7p\2\2\u008b\34\3\2\2\2\u008c\u008d\7d\2\2\u008d"+
		"\u008e\7t\2\2\u008e\u008f\7g\2\2\u008f\u0090\7c\2\2\u0090\u0091\7m\2\2"+
		"\u0091\36\3\2\2\2\u0092\u0093\7e\2\2\u0093\u0094\7q\2\2\u0094\u0095\7"+
		"p\2\2\u0095\u0096\7v\2\2\u0096\u0097\7k\2\2\u0097\u0098\7p\2\2\u0098\u0099"+
		"\7w\2\2\u0099\u009a\7g\2\2\u009a \3\2\2\2\u009b\u009c\7}\2\2\u009c\"\3"+
		"\2\2\2\u009d\u009e\7\177\2\2\u009e$\3\2\2\2\u009f\u00a0\7k\2\2\u00a0\u00a1"+
		"\7h\2\2\u00a1&\3\2\2\2\u00a2\u00a3\7g\2\2\u00a3\u00a4\7n\2\2\u00a4\u00a5"+
		"\7u\2\2\u00a5\u00a6\7g\2\2\u00a6(\3\2\2\2\u00a7\u00a8\7h\2\2\u00a8\u00a9"+
		"\7q\2\2\u00a9\u00aa\7t\2\2\u00aa*\3\2\2\2\u00ab\u00ac\7y\2\2\u00ac\u00ad"+
		"\7j\2\2\u00ad\u00ae\7k\2\2\u00ae\u00af\7n\2\2\u00af\u00b0\7g\2\2\u00b0"+
		",\3\2\2\2\u00b1\u00b2\7f\2\2\u00b2\u00b3\7q\2\2\u00b3.\3\2\2\2\u00b4\u00b5"+
		"\7x\2\2\u00b5\u00b6\7q\2\2\u00b6\u00b7\7k\2\2\u00b7\u00b8\7f\2\2\u00b8"+
		"\60\3\2\2\2\u00b9\u00ba\7k\2\2\u00ba\u00bb\7p\2\2\u00bb\u00bc\7v\2\2\u00bc"+
		"\62\3\2\2\2\u00bd\u00be\7e\2\2\u00be\u00bf\7j\2\2\u00bf\u00c0\7c\2\2\u00c0"+
		"\u00c1\7t\2\2\u00c1\64\3\2\2\2\u00c2\u00c3\7?\2\2\u00c3\66\3\2\2\2\u00c4"+
		"\u00c5\7-\2\2\u00c5\u00c6\7?\2\2\u00c68\3\2\2\2\u00c7\u00c8\7/\2\2\u00c8"+
		"\u00c9\7?\2\2\u00c9:\3\2\2\2\u00ca\u00cb\7,\2\2\u00cb\u00cc\7?\2\2\u00cc"+
		"<\3\2\2\2\u00cd\u00ce\7\61\2\2\u00ce\u00cf\7?\2\2\u00cf>\3\2\2\2\u00d0"+
		"\u00d1\7\'\2\2\u00d1\u00d2\7?\2\2\u00d2@\3\2\2\2\u00d3\u00d4\7,\2\2\u00d4"+
		"B\3\2\2\2\u00d5\u00d6\7\61\2\2\u00d6D\3\2\2\2\u00d7\u00d8\7\'\2\2\u00d8"+
		"F\3\2\2\2\u00d9\u00da\7-\2\2\u00daH\3\2\2\2\u00db\u00dc\7/\2\2\u00dcJ"+
		"\3\2\2\2\u00dd\u00de\7?\2\2\u00de\u00df\7?\2\2\u00dfL\3\2\2\2\u00e0\u00e1"+
		"\7#\2\2\u00e1\u00e2\7?\2\2\u00e2N\3\2\2\2\u00e3\u00e4\7>\2\2\u00e4P\3"+
		"\2\2\2\u00e5\u00e6\7>\2\2\u00e6\u00e7\7?\2\2\u00e7R\3\2\2\2\u00e8\u00e9"+
		"\7@\2\2\u00e9T\3\2\2\2\u00ea\u00eb\7@\2\2\u00eb\u00ec\7?\2\2\u00ecV\3"+
		"\2\2\2\u00ed\u00ee\7(\2\2\u00ee\u00ef\7(\2\2\u00efX\3\2\2\2\u00f0\u00f1"+
		"\7~\2\2\u00f1\u00f2\7~\2\2\u00f2Z\3\2\2\2\u00f3\u0100\t\2\2\2\u00f4\u00f6"+
		"\t\3\2\2\u00f5\u00f4\3\2\2\2\u00f6\u00f7\3\2\2\2\u00f7\u00f5\3\2\2\2\u00f7"+
		"\u00f8\3\2\2\2\u00f8\u00ff\3\2\2\2\u00f9\u00fb\t\4\2\2\u00fa\u00f9\3\2"+
		"\2\2\u00fb\u00fc\3\2\2\2\u00fc\u00fa\3\2\2\2\u00fc\u00fd\3\2\2\2\u00fd"+
		"\u00ff\3\2\2\2\u00fe\u00f5\3\2\2\2\u00fe\u00fa\3\2\2\2\u00ff\u0102\3\2"+
		"\2\2\u0100\u00fe\3\2\2\2\u0100\u0101\3\2\2\2\u0101\\\3\2\2\2\u0102\u0100"+
		"\3\2\2\2\u0103\u0105\t\4\2\2\u0104\u0103\3\2\2\2\u0105\u0106\3\2\2\2\u0106"+
		"\u0104\3\2\2\2\u0106\u0107\3\2\2\2\u0107^\3\2\2\2\u0108\u0109\7)\2\2\u0109"+
		"\u010a\13\2\2\2\u010a\u0111\7)\2\2\u010b\u010c\7)\2\2\u010c\u010d\7^\2"+
		"\2\u010d\u010e\3\2\2\2\u010e\u010f\13\2\2\2\u010f\u0111\7)\2\2\u0110\u0108"+
		"\3\2\2\2\u0110\u010b\3\2\2\2\u0111`\3\2\2\2\u0112\u0117\7$\2\2\u0113\u0116"+
		"\5c\62\2\u0114\u0116\13\2\2\2\u0115\u0113\3\2\2\2\u0115\u0114\3\2\2\2"+
		"\u0116\u0119\3\2\2\2\u0117\u0118\3\2\2\2\u0117\u0115\3\2\2\2\u0118\u011a"+
		"\3\2\2\2\u0119\u0117\3\2\2\2\u011a\u011b\7$\2\2\u011bb\3\2\2\2\u011c\u011d"+
		"\7^\2\2\u011d\u0121\7$\2\2\u011e\u011f\7^\2\2\u011f\u0121\7^\2\2\u0120"+
		"\u011c\3\2\2\2\u0120\u011e\3\2\2\2\u0121d\3\2\2\2\u0122\u0123\7\61\2\2"+
		"\u0123\u0124\7,\2\2\u0124\u0128\3\2\2\2\u0125\u0127\13\2\2\2\u0126\u0125"+
		"\3\2\2\2\u0127\u012a\3\2\2\2\u0128\u0129\3\2\2\2\u0128\u0126\3\2\2\2\u0129"+
		"\u012b\3\2\2\2\u012a\u0128\3\2\2\2\u012b\u012c\7,\2\2\u012c\u012d\7\61"+
		"\2\2\u012d\u012e\3\2\2\2\u012e\u012f\b\63\2\2\u012ff\3\2\2\2\u0130\u0131"+
		"\7\61\2\2\u0131\u0132\7\61\2\2\u0132\u0136\3\2\2\2\u0133\u0135\13\2\2"+
		"\2\u0134\u0133\3\2\2\2\u0135\u0138\3\2\2\2\u0136\u0137\3\2\2\2\u0136\u0134"+
		"\3\2\2\2\u0137\u013a\3\2\2\2\u0138\u0136\3\2\2\2\u0139\u013b\7\17\2\2"+
		"\u013a\u0139\3\2\2\2\u013a\u013b\3\2\2\2\u013b\u013c\3\2\2\2\u013c\u013d"+
		"\7\f\2\2\u013d\u013e\3\2\2\2\u013e\u013f\b\64\2\2\u013fh\3\2\2\2\u0140"+
		"\u0142\t\5\2\2\u0141\u0140\3\2\2\2\u0142\u0143\3\2\2\2\u0143\u0141\3\2"+
		"\2\2\u0143\u0144\3\2\2\2\u0144\u0145\3\2\2\2\u0145\u0146\b\65\2\2\u0146"+
		"j\3\2\2\2\20\2\u00f7\u00fc\u00fe\u0100\u0106\u0110\u0115\u0117\u0120\u0128"+
		"\u0136\u013a\u0143\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}