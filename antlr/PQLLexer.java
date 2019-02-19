// Generated from PQL.g4 by ANTLR 4.1
package org.pql.antlr;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class PQLLexer extends Lexer {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		UNIVERSE=1, STRING=2, VARIABLE_NAME=3, SIMILARITY=4, LP=5, RP=6, LB=7, 
		RB=8, LSB=9, RSB=10, LTB=11, RTB=12, DQ=13, EOS=14, SEP=15, ASSIGN=16, 
		TILDE=17, ESC_SEQ=18, UNICODE_ESC=19, HEX_DIGIT=20, WS=21, LINE_COMMENT=22, 
		SELECT=23, INSERT=24, INTO=25, FROM=26, WHERE=27, EQUALS=28, OVERLAPS=29, 
		WITH=30, SUBSET=31, PROPER=32, GET_TASKS=33, NOT=34, AND=35, OR=36, ANY=37, 
		SOME=38, EACH=39, ALL=40, IN=41, IS=42, OF=43, TRUE=44, FALSE=45, UNION=46, 
		INTERSECTION=47, DIFFERENCE=48, CAN_OCCUR=49, ALWAYS_OCCURS=50, EXECUTES=51, 
		CAN_CONFLICT=52, CAN_COOCCUR=53, CONFLICT=54, COOCCUR=55, TOTAL_CAUSAL=56, 
		TOTAL_CONCUR=57;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'*'", "STRING", "VARIABLE_NAME", "SIMILARITY", "'('", "')'", "'{'", "'}'", 
		"'['", "']'", "'<'", "'>'", "'\"'", "';'", "','", "'='", "'~'", "ESC_SEQ", 
		"UNICODE_ESC", "HEX_DIGIT", "WS", "LINE_COMMENT", "'SELECT'", "'INSERT'", 
		"'INTO'", "'FROM'", "'WHERE'", "'EQUALS'", "'OVERLAPS'", "'WITH'", "'SUBSET'", 
		"'PROPER'", "'GetTasks'", "'NOT'", "'AND'", "'OR'", "'ANY'", "'SOME'", 
		"'EACH'", "'ALL'", "'IN'", "'IS'", "'OF'", "'TRUE'", "'FALSE'", "'UNION'", 
		"'INTERSECT'", "'EXCEPT'", "'CanOccur'", "'AlwaysOccurs'", "'Executes'", 
		"'CanConflict'", "'CanCooccur'", "'Conflict'", "'Cooccur'", "'TotalCausal'", 
		"'TotalConcurrent'"
	};
	public static final String[] ruleNames = {
		"UNIVERSE", "STRING", "VARIABLE_NAME", "SIMILARITY", "LP", "RP", "LB", 
		"RB", "LSB", "RSB", "LTB", "RTB", "DQ", "EOS", "SEP", "ASSIGN", "TILDE", 
		"ESC_SEQ", "UNICODE_ESC", "HEX_DIGIT", "WS", "LINE_COMMENT", "SELECT", 
		"INSERT", "INTO", "FROM", "WHERE", "EQUALS", "OVERLAPS", "WITH", "SUBSET", 
		"PROPER", "GET_TASKS", "NOT", "AND", "OR", "ANY", "SOME", "EACH", "ALL", 
		"IN", "IS", "OF", "TRUE", "FALSE", "UNION", "INTERSECTION", "DIFFERENCE", 
		"CAN_OCCUR", "ALWAYS_OCCURS", "EXECUTES", "CAN_CONFLICT", "CAN_COOCCUR", 
		"CONFLICT", "COOCCUR", "TOTAL_CAUSAL", "TOTAL_CONCUR"
	};


	public PQLLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "PQL.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 20: WS_action((RuleContext)_localctx, actionIndex); break;

		case 21: LINE_COMMENT_action((RuleContext)_localctx, actionIndex); break;
		}
	}
	private void LINE_COMMENT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1: skip();  break;
		}
	}
	private void WS_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0: skip();  break;
		}
	}

	public static final String _serializedATN =
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\2;\u01c8\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\3\2\3\2\3\3\3\3\3"+
		"\3\6\3{\n\3\r\3\16\3|\3\3\3\3\3\4\3\4\7\4\u0083\n\4\f\4\16\4\u0086\13"+
		"\4\3\5\3\5\3\5\3\5\6\5\u008c\n\5\r\5\16\5\u008d\5\5\u0090\n\5\3\5\3\5"+
		"\6\5\u0094\n\5\r\5\16\5\u0095\5\5\u0098\n\5\3\6\3\6\3\7\3\7\3\b\3\b\3"+
		"\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20"+
		"\3\21\3\21\3\22\3\22\3\23\3\23\3\23\5\23\u00b7\n\23\3\24\3\24\3\24\3\24"+
		"\3\24\3\24\3\24\3\25\3\25\3\26\6\26\u00c3\n\26\r\26\16\26\u00c4\3\26\3"+
		"\26\3\27\3\27\3\27\3\27\7\27\u00cd\n\27\f\27\16\27\u00d0\13\27\3\27\3"+
		"\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3"+
		"\31\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3"+
		"\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3"+
		"\36\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3\37\3 \3 \3 \3 \3 \3 \3 "+
		"\3!\3!\3!\3!\3!\3!\3!\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3#\3#\3#\3#"+
		"\3$\3$\3$\3$\3%\3%\3%\3&\3&\3&\3&\3\'\3\'\3\'\3\'\3\'\3(\3(\3(\3(\3(\3"+
		")\3)\3)\3)\3*\3*\3*\3+\3+\3+\3,\3,\3,\3-\3-\3-\3-\3-\3.\3.\3.\3.\3.\3"+
		".\3/\3/\3/\3/\3/\3/\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60"+
		"\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\62\3\62\3\62\3\62\3\62\3\62\3\62"+
		"\3\62\3\62\3\63\3\63\3\63\3\63\3\63\3\63\3\63\3\63\3\63\3\63\3\63\3\63"+
		"\3\63\3\64\3\64\3\64\3\64\3\64\3\64\3\64\3\64\3\64\3\65\3\65\3\65\3\65"+
		"\3\65\3\65\3\65\3\65\3\65\3\65\3\65\3\65\3\66\3\66\3\66\3\66\3\66\3\66"+
		"\3\66\3\66\3\66\3\66\3\66\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67"+
		"\38\38\38\38\38\38\38\38\39\39\39\39\39\39\39\39\39\39\39\39\3:\3:\3:"+
		"\3:\3:\3:\3:\3:\3:\3:\3:\3:\3:\3:\3:\3:\2;\3\3\1\5\4\1\7\5\1\t\6\1\13"+
		"\7\1\r\b\1\17\t\1\21\n\1\23\13\1\25\f\1\27\r\1\31\16\1\33\17\1\35\20\1"+
		"\37\21\1!\22\1#\23\1%\24\1\'\25\1)\26\1+\27\2-\30\3/\31\1\61\32\1\63\33"+
		"\1\65\34\1\67\35\19\36\1;\37\1= \1?!\1A\"\1C#\1E$\1G%\1I&\1K\'\1M(\1O"+
		")\1Q*\1S+\1U,\1W-\1Y.\1[/\1]\60\1_\61\1a\62\1c\63\1e\64\1g\65\1i\66\1"+
		"k\67\1m8\1o9\1q:\1s;\1\3\2\t\4\2$$^^\4\2aac|\5\2\62;aac|\n\2$$\61\61^"+
		"^ddhhppttvv\5\2\62;CHch\5\2\13\f\17\17\"\"\4\2\f\f\17\17\u01d2\2\3\3\2"+
		"\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17"+
		"\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2"+
		"\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3"+
		"\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3"+
		"\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2"+
		"=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3"+
		"\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2"+
		"\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2"+
		"c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2\2m\3\2\2\2\2o\3"+
		"\2\2\2\2q\3\2\2\2\2s\3\2\2\2\3u\3\2\2\2\5w\3\2\2\2\7\u0080\3\2\2\2\t\u0097"+
		"\3\2\2\2\13\u0099\3\2\2\2\r\u009b\3\2\2\2\17\u009d\3\2\2\2\21\u009f\3"+
		"\2\2\2\23\u00a1\3\2\2\2\25\u00a3\3\2\2\2\27\u00a5\3\2\2\2\31\u00a7\3\2"+
		"\2\2\33\u00a9\3\2\2\2\35\u00ab\3\2\2\2\37\u00ad\3\2\2\2!\u00af\3\2\2\2"+
		"#\u00b1\3\2\2\2%\u00b6\3\2\2\2\'\u00b8\3\2\2\2)\u00bf\3\2\2\2+\u00c2\3"+
		"\2\2\2-\u00c8\3\2\2\2/\u00d3\3\2\2\2\61\u00da\3\2\2\2\63\u00e1\3\2\2\2"+
		"\65\u00e6\3\2\2\2\67\u00eb\3\2\2\29\u00f1\3\2\2\2;\u00f8\3\2\2\2=\u0101"+
		"\3\2\2\2?\u0106\3\2\2\2A\u010d\3\2\2\2C\u0114\3\2\2\2E\u011d\3\2\2\2G"+
		"\u0121\3\2\2\2I\u0125\3\2\2\2K\u0128\3\2\2\2M\u012c\3\2\2\2O\u0131\3\2"+
		"\2\2Q\u0136\3\2\2\2S\u013a\3\2\2\2U\u013d\3\2\2\2W\u0140\3\2\2\2Y\u0143"+
		"\3\2\2\2[\u0148\3\2\2\2]\u014e\3\2\2\2_\u0154\3\2\2\2a\u015e\3\2\2\2c"+
		"\u0165\3\2\2\2e\u016e\3\2\2\2g\u017b\3\2\2\2i\u0184\3\2\2\2k\u0190\3\2"+
		"\2\2m\u019b\3\2\2\2o\u01a4\3\2\2\2q\u01ac\3\2\2\2s\u01b8\3\2\2\2uv\7,"+
		"\2\2v\4\3\2\2\2wz\5\33\16\2x{\5%\23\2y{\n\2\2\2zx\3\2\2\2zy\3\2\2\2{|"+
		"\3\2\2\2|z\3\2\2\2|}\3\2\2\2}~\3\2\2\2~\177\5\33\16\2\177\6\3\2\2\2\u0080"+
		"\u0084\t\3\2\2\u0081\u0083\t\4\2\2\u0082\u0081\3\2\2\2\u0083\u0086\3\2"+
		"\2\2\u0084\u0082\3\2\2\2\u0084\u0085\3\2\2\2\u0085\b\3\2\2\2\u0086\u0084"+
		"\3\2\2\2\u0087\u0098\7\63\2\2\u0088\u008f\7\62\2\2\u0089\u008b\7\60\2"+
		"\2\u008a\u008c\4\62;\2\u008b\u008a\3\2\2\2\u008c\u008d\3\2\2\2\u008d\u008b"+
		"\3\2\2\2\u008d\u008e\3\2\2\2\u008e\u0090\3\2\2\2\u008f\u0089\3\2\2\2\u008f"+
		"\u0090\3\2\2\2\u0090\u0098\3\2\2\2\u0091\u0093\7\60\2\2\u0092\u0094\4"+
		"\62;\2\u0093\u0092\3\2\2\2\u0094\u0095\3\2\2\2\u0095\u0093\3\2\2\2\u0095"+
		"\u0096\3\2\2\2\u0096\u0098\3\2\2\2\u0097\u0087\3\2\2\2\u0097\u0088\3\2"+
		"\2\2\u0097\u0091\3\2\2\2\u0098\n\3\2\2\2\u0099\u009a\7*\2\2\u009a\f\3"+
		"\2\2\2\u009b\u009c\7+\2\2\u009c\16\3\2\2\2\u009d\u009e\7}\2\2\u009e\20"+
		"\3\2\2\2\u009f\u00a0\7\177\2\2\u00a0\22\3\2\2\2\u00a1\u00a2\7]\2\2\u00a2"+
		"\24\3\2\2\2\u00a3\u00a4\7_\2\2\u00a4\26\3\2\2\2\u00a5\u00a6\7>\2\2\u00a6"+
		"\30\3\2\2\2\u00a7\u00a8\7@\2\2\u00a8\32\3\2\2\2\u00a9\u00aa\7$\2\2\u00aa"+
		"\34\3\2\2\2\u00ab\u00ac\7=\2\2\u00ac\36\3\2\2\2\u00ad\u00ae\7.\2\2\u00ae"+
		" \3\2\2\2\u00af\u00b0\7?\2\2\u00b0\"\3\2\2\2\u00b1\u00b2\7\u0080\2\2\u00b2"+
		"$\3\2\2\2\u00b3\u00b4\7^\2\2\u00b4\u00b7\t\5\2\2\u00b5\u00b7\5\'\24\2"+
		"\u00b6\u00b3\3\2\2\2\u00b6\u00b5\3\2\2\2\u00b7&\3\2\2\2\u00b8\u00b9\7"+
		"^\2\2\u00b9\u00ba\7w\2\2\u00ba\u00bb\5)\25\2\u00bb\u00bc\5)\25\2\u00bc"+
		"\u00bd\5)\25\2\u00bd\u00be\5)\25\2\u00be(\3\2\2\2\u00bf\u00c0\t\6\2\2"+
		"\u00c0*\3\2\2\2\u00c1\u00c3\t\7\2\2\u00c2\u00c1\3\2\2\2\u00c3\u00c4\3"+
		"\2\2\2\u00c4\u00c2\3\2\2\2\u00c4\u00c5\3\2\2\2\u00c5\u00c6\3\2\2\2\u00c6"+
		"\u00c7\b\26\2\2\u00c7,\3\2\2\2\u00c8\u00c9\7/\2\2\u00c9\u00ca\7/\2\2\u00ca"+
		"\u00ce\3\2\2\2\u00cb\u00cd\n\b\2\2\u00cc\u00cb\3\2\2\2\u00cd\u00d0\3\2"+
		"\2\2\u00ce\u00cc\3\2\2\2\u00ce\u00cf\3\2\2\2\u00cf\u00d1\3\2\2\2\u00d0"+
		"\u00ce\3\2\2\2\u00d1\u00d2\b\27\3\2\u00d2.\3\2\2\2\u00d3\u00d4\7U\2\2"+
		"\u00d4\u00d5\7G\2\2\u00d5\u00d6\7N\2\2\u00d6\u00d7\7G\2\2\u00d7\u00d8"+
		"\7E\2\2\u00d8\u00d9\7V\2\2\u00d9\60\3\2\2\2\u00da\u00db\7K\2\2\u00db\u00dc"+
		"\7P\2\2\u00dc\u00dd\7U\2\2\u00dd\u00de\7G\2\2\u00de\u00df\7T\2\2\u00df"+
		"\u00e0\7V\2\2\u00e0\62\3\2\2\2\u00e1\u00e2\7K\2\2\u00e2\u00e3\7P\2\2\u00e3"+
		"\u00e4\7V\2\2\u00e4\u00e5\7Q\2\2\u00e5\64\3\2\2\2\u00e6\u00e7\7H\2\2\u00e7"+
		"\u00e8\7T\2\2\u00e8\u00e9\7Q\2\2\u00e9\u00ea\7O\2\2\u00ea\66\3\2\2\2\u00eb"+
		"\u00ec\7Y\2\2\u00ec\u00ed\7J\2\2\u00ed\u00ee\7G\2\2\u00ee\u00ef\7T\2\2"+
		"\u00ef\u00f0\7G\2\2\u00f08\3\2\2\2\u00f1\u00f2\7G\2\2\u00f2\u00f3\7S\2"+
		"\2\u00f3\u00f4\7W\2\2\u00f4\u00f5\7C\2\2\u00f5\u00f6\7N\2\2\u00f6\u00f7"+
		"\7U\2\2\u00f7:\3\2\2\2\u00f8\u00f9\7Q\2\2\u00f9\u00fa\7X\2\2\u00fa\u00fb"+
		"\7G\2\2\u00fb\u00fc\7T\2\2\u00fc\u00fd\7N\2\2\u00fd\u00fe\7C\2\2\u00fe"+
		"\u00ff\7R\2\2\u00ff\u0100\7U\2\2\u0100<\3\2\2\2\u0101\u0102\7Y\2\2\u0102"+
		"\u0103\7K\2\2\u0103\u0104\7V\2\2\u0104\u0105\7J\2\2\u0105>\3\2\2\2\u0106"+
		"\u0107\7U\2\2\u0107\u0108\7W\2\2\u0108\u0109\7D\2\2\u0109\u010a\7U\2\2"+
		"\u010a\u010b\7G\2\2\u010b\u010c\7V\2\2\u010c@\3\2\2\2\u010d\u010e\7R\2"+
		"\2\u010e\u010f\7T\2\2\u010f\u0110\7Q\2\2\u0110\u0111\7R\2\2\u0111\u0112"+
		"\7G\2\2\u0112\u0113\7T\2\2\u0113B\3\2\2\2\u0114\u0115\7I\2\2\u0115\u0116"+
		"\7g\2\2\u0116\u0117\7v\2\2\u0117\u0118\7V\2\2\u0118\u0119\7c\2\2\u0119"+
		"\u011a\7u\2\2\u011a\u011b\7m\2\2\u011b\u011c\7u\2\2\u011cD\3\2\2\2\u011d"+
		"\u011e\7P\2\2\u011e\u011f\7Q\2\2\u011f\u0120\7V\2\2\u0120F\3\2\2\2\u0121"+
		"\u0122\7C\2\2\u0122\u0123\7P\2\2\u0123\u0124\7F\2\2\u0124H\3\2\2\2\u0125"+
		"\u0126\7Q\2\2\u0126\u0127\7T\2\2\u0127J\3\2\2\2\u0128\u0129\7C\2\2\u0129"+
		"\u012a\7P\2\2\u012a\u012b\7[\2\2\u012bL\3\2\2\2\u012c\u012d\7U\2\2\u012d"+
		"\u012e\7Q\2\2\u012e\u012f\7O\2\2\u012f\u0130\7G\2\2\u0130N\3\2\2\2\u0131"+
		"\u0132\7G\2\2\u0132\u0133\7C\2\2\u0133\u0134\7E\2\2\u0134\u0135\7J\2\2"+
		"\u0135P\3\2\2\2\u0136\u0137\7C\2\2\u0137\u0138\7N\2\2\u0138\u0139\7N\2"+
		"\2\u0139R\3\2\2\2\u013a\u013b\7K\2\2\u013b\u013c\7P\2\2\u013cT\3\2\2\2"+
		"\u013d\u013e\7K\2\2\u013e\u013f\7U\2\2\u013fV\3\2\2\2\u0140\u0141\7Q\2"+
		"\2\u0141\u0142\7H\2\2\u0142X\3\2\2\2\u0143\u0144\7V\2\2\u0144\u0145\7"+
		"T\2\2\u0145\u0146\7W\2\2\u0146\u0147\7G\2\2\u0147Z\3\2\2\2\u0148\u0149"+
		"\7H\2\2\u0149\u014a\7C\2\2\u014a\u014b\7N\2\2\u014b\u014c\7U\2\2\u014c"+
		"\u014d\7G\2\2\u014d\\\3\2\2\2\u014e\u014f\7W\2\2\u014f\u0150\7P\2\2\u0150"+
		"\u0151\7K\2\2\u0151\u0152\7Q\2\2\u0152\u0153\7P\2\2\u0153^\3\2\2\2\u0154"+
		"\u0155\7K\2\2\u0155\u0156\7P\2\2\u0156\u0157\7V\2\2\u0157\u0158\7G\2\2"+
		"\u0158\u0159\7T\2\2\u0159\u015a\7U\2\2\u015a\u015b\7G\2\2\u015b\u015c"+
		"\7E\2\2\u015c\u015d\7V\2\2\u015d`\3\2\2\2\u015e\u015f\7G\2\2\u015f\u0160"+
		"\7Z\2\2\u0160\u0161\7E\2\2\u0161\u0162\7G\2\2\u0162\u0163\7R\2\2\u0163"+
		"\u0164\7V\2\2\u0164b\3\2\2\2\u0165\u0166\7E\2\2\u0166\u0167\7c\2\2\u0167"+
		"\u0168\7p\2\2\u0168\u0169\7Q\2\2\u0169\u016a\7e\2\2\u016a\u016b\7e\2\2"+
		"\u016b\u016c\7w\2\2\u016c\u016d\7t\2\2\u016dd\3\2\2\2\u016e\u016f\7C\2"+
		"\2\u016f\u0170\7n\2\2\u0170\u0171\7y\2\2\u0171\u0172\7c\2\2\u0172\u0173"+
		"\7{\2\2\u0173\u0174\7u\2\2\u0174\u0175\7Q\2\2\u0175\u0176\7e\2\2\u0176"+
		"\u0177\7e\2\2\u0177\u0178\7w\2\2\u0178\u0179\7t\2\2\u0179\u017a\7u\2\2"+
		"\u017af\3\2\2\2\u017b\u017c\7G\2\2\u017c\u017d\7z\2\2\u017d\u017e\7g\2"+
		"\2\u017e\u017f\7e\2\2\u017f\u0180\7w\2\2\u0180\u0181\7v\2\2\u0181\u0182"+
		"\7g\2\2\u0182\u0183\7u\2\2\u0183h\3\2\2\2\u0184\u0185\7E\2\2\u0185\u0186"+
		"\7c\2\2\u0186\u0187\7p\2\2\u0187\u0188\7E\2\2\u0188\u0189\7q\2\2\u0189"+
		"\u018a\7p\2\2\u018a\u018b\7h\2\2\u018b\u018c\7n\2\2\u018c\u018d\7k\2\2"+
		"\u018d\u018e\7e\2\2\u018e\u018f\7v\2\2\u018fj\3\2\2\2\u0190\u0191\7E\2"+
		"\2\u0191\u0192\7c\2\2\u0192\u0193\7p\2\2\u0193\u0194\7E\2\2\u0194\u0195"+
		"\7q\2\2\u0195\u0196\7q\2\2\u0196\u0197\7e\2\2\u0197\u0198\7e\2\2\u0198"+
		"\u0199\7w\2\2\u0199\u019a\7t\2\2\u019al\3\2\2\2\u019b\u019c\7E\2\2\u019c"+
		"\u019d\7q\2\2\u019d\u019e\7p\2\2\u019e\u019f\7h\2\2\u019f\u01a0\7n\2\2"+
		"\u01a0\u01a1\7k\2\2\u01a1\u01a2\7e\2\2\u01a2\u01a3\7v\2\2\u01a3n\3\2\2"+
		"\2\u01a4\u01a5\7E\2\2\u01a5\u01a6\7q\2\2\u01a6\u01a7\7q\2\2\u01a7\u01a8"+
		"\7e\2\2\u01a8\u01a9\7e\2\2\u01a9\u01aa\7w\2\2\u01aa\u01ab\7t\2\2\u01ab"+
		"p\3\2\2\2\u01ac\u01ad\7V\2\2\u01ad\u01ae\7q\2\2\u01ae\u01af\7v\2\2\u01af"+
		"\u01b0\7c\2\2\u01b0\u01b1\7n\2\2\u01b1\u01b2\7E\2\2\u01b2\u01b3\7c\2\2"+
		"\u01b3\u01b4\7w\2\2\u01b4\u01b5\7u\2\2\u01b5\u01b6\7c\2\2\u01b6\u01b7"+
		"\7n\2\2\u01b7r\3\2\2\2\u01b8\u01b9\7V\2\2\u01b9\u01ba\7q\2\2\u01ba\u01bb"+
		"\7v\2\2\u01bb\u01bc\7c\2\2\u01bc\u01bd\7n\2\2\u01bd\u01be\7E\2\2\u01be"+
		"\u01bf\7q\2\2\u01bf\u01c0\7p\2\2\u01c0\u01c1\7e\2\2\u01c1\u01c2\7w\2\2"+
		"\u01c2\u01c3\7t\2\2\u01c3\u01c4\7t\2\2\u01c4\u01c5\7g\2\2\u01c5\u01c6"+
		"\7p\2\2\u01c6\u01c7\7v\2\2\u01c7t\3\2\2\2\r\2z|\u0084\u008d\u008f\u0095"+
		"\u0097\u00b6\u00c4\u00ce";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}