// Generated from PQL.g4 by ANTLR 4.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class PQLParser extends Parser {
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
	public static final String[] tokenNames = {
		"<INVALID>", "'*'", "STRING", "VARIABLE_NAME", "SIMILARITY", "'('", "')'", 
		"'{'", "'}'", "'['", "']'", "'<'", "'>'", "'\"'", "';'", "','", "'='", 
		"'~'", "ESC_SEQ", "UNICODE_ESC", "HEX_DIGIT", "WS", "LINE_COMMENT", "'SELECT'", 
		"'INSERT'", "'INTO'", "'FROM'", "'WHERE'", "'EQUALS'", "'OVERLAPS'", "'WITH'", 
		"'SUBSET'", "'PROPER'", "'GetTasks'", "'NOT'", "'AND'", "'OR'", "'ANY'", 
		"'SOME'", "'EACH'", "'ALL'", "'IN'", "'IS'", "'OF'", "'TRUE'", "'FALSE'", 
		"'UNION'", "'INTERSECT'", "'EXCEPT'", "'CanOccur'", "'AlwaysOccurs'", 
		"'Executes'", "'CanConflict'", "'CanCooccur'", "'Conflict'", "'Cooccur'", 
		"'TotalCausal'", "'TotalConcurrent'"
	};
	public static final int
		RULE_query = 0, RULE_selectQuery = 1, RULE_insertQuery = 2, RULE_variables = 3, 
		RULE_variable = 4, RULE_varName = 5, RULE_attributes = 6, RULE_attribute = 7, 
		RULE_locations = 8, RULE_location = 9, RULE_universe = 10, RULE_attributeName = 11, 
		RULE_locationPath = 12, RULE_nestedQuery = 13, RULE_setOfTasks = 14, RULE_tasks = 15, 
		RULE_setOfAllTasks = 16, RULE_setOfTasksLiteral = 17, RULE_trace = 18, 
		RULE_event = 19, RULE_task = 20, RULE_approximate = 21, RULE_label = 22, 
		RULE_similarity = 23, RULE_setOfTasksConstruction = 24, RULE_unaryPredicateConstruction = 25, 
		RULE_binaryPredicateConstruction = 26, RULE_anyAll = 27, RULE_unaryPredicateName = 28, 
		RULE_unaryTracePredicateName = 29, RULE_binaryPredicateName = 30, RULE_predicate = 31, 
		RULE_proposition = 32, RULE_unaryPredicate = 33, RULE_binaryPredicate = 34, 
		RULE_unaryTracePredicate = 35, RULE_unaryPredicateMacro = 36, RULE_binaryPredicateMacro = 37, 
		RULE_binaryPredicateMacroTaskSet = 38, RULE_binaryPredicateMacroSetSet = 39, 
		RULE_anySomeEachAll = 40, RULE_setPredicate = 41, RULE_taskInSetOfTasks = 42, 
		RULE_setComparison = 43, RULE_setComparisonOperator = 44, RULE_truthValue = 45, 
		RULE_logicalTest = 46, RULE_union = 47, RULE_intersection = 48, RULE_difference = 49, 
		RULE_negation = 50, RULE_isTrue = 51, RULE_isNotTrue = 52, RULE_isFalse = 53, 
		RULE_isNotFalse = 54, RULE_disjunction = 55, RULE_conjunction = 56, RULE_parentheses = 57, 
		RULE_setOfTasksParentheses = 58, RULE_identical = 59, RULE_different = 60, 
		RULE_overlapsWith = 61, RULE_subsetOf = 62, RULE_properSubsetOf = 63;
	public static final String[] ruleNames = {
		"query", "selectQuery", "insertQuery", "variables", "variable", "varName", 
		"attributes", "attribute", "locations", "location", "universe", "attributeName", 
		"locationPath", "nestedQuery", "setOfTasks", "tasks", "setOfAllTasks", 
		"setOfTasksLiteral", "trace", "event", "task", "approximate", "label", 
		"similarity", "setOfTasksConstruction", "unaryPredicateConstruction", 
		"binaryPredicateConstruction", "anyAll", "unaryPredicateName", "unaryTracePredicateName", 
		"binaryPredicateName", "predicate", "proposition", "unaryPredicate", "binaryPredicate", 
		"unaryTracePredicate", "unaryPredicateMacro", "binaryPredicateMacro", 
		"binaryPredicateMacroTaskSet", "binaryPredicateMacroSetSet", "anySomeEachAll", 
		"setPredicate", "taskInSetOfTasks", "setComparison", "setComparisonOperator", 
		"truthValue", "logicalTest", "union", "intersection", "difference", "negation", 
		"isTrue", "isNotTrue", "isFalse", "isNotFalse", "disjunction", "conjunction", 
		"parentheses", "setOfTasksParentheses", "identical", "different", "overlapsWith", 
		"subsetOf", "properSubsetOf"
	};

	@Override
	public String getGrammarFileName() { return "PQL.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public PQLParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class QueryContext extends ParserRuleContext {
		public InsertQueryContext insertQuery() {
			return getRuleContext(InsertQueryContext.class,0);
		}
		public SelectQueryContext selectQuery() {
			return getRuleContext(SelectQueryContext.class,0);
		}
		public QueryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_query; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).enterQuery(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).exitQuery(this);
		}
	}

	public final QueryContext query() throws RecognitionException {
		QueryContext _localctx = new QueryContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_query);
		try {
			setState(130);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(128); selectQuery();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(129); insertQuery();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SelectQueryContext extends ParserRuleContext {
		public VariablesContext variables() {
			return getRuleContext(VariablesContext.class,0);
		}
		public AttributesContext attributes() {
			return getRuleContext(AttributesContext.class,0);
		}
		public LocationsContext locations() {
			return getRuleContext(LocationsContext.class,0);
		}
		public TerminalNode EOS() { return getToken(PQLParser.EOS, 0); }
		public TerminalNode WHERE() { return getToken(PQLParser.WHERE, 0); }
		public TerminalNode FROM() { return getToken(PQLParser.FROM, 0); }
		public TerminalNode SELECT() { return getToken(PQLParser.SELECT, 0); }
		public PredicateContext predicate() {
			return getRuleContext(PredicateContext.class,0);
		}
		public SelectQueryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selectQuery; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).enterSelectQuery(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).exitSelectQuery(this);
		}
	}

	public final SelectQueryContext selectQuery() throws RecognitionException {
		SelectQueryContext _localctx = new SelectQueryContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_selectQuery);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(132); variables();
			setState(133); match(SELECT);
			setState(134); attributes();
			setState(135); match(FROM);
			setState(136); locations();
			setState(139);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(137); match(WHERE);
				setState(138); predicate();
				}
			}

			setState(141); match(EOS);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InsertQueryContext extends ParserRuleContext {
		public VariablesContext variables() {
			return getRuleContext(VariablesContext.class,0);
		}
		public LocationsContext locations() {
			return getRuleContext(LocationsContext.class,0);
		}
		public TerminalNode EOS() { return getToken(PQLParser.EOS, 0); }
		public TerminalNode WHERE() { return getToken(PQLParser.WHERE, 0); }
		public TraceContext trace() {
			return getRuleContext(TraceContext.class,0);
		}
		public TerminalNode INSERT() { return getToken(PQLParser.INSERT, 0); }
		public TerminalNode INTO() { return getToken(PQLParser.INTO, 0); }
		public PredicateContext predicate() {
			return getRuleContext(PredicateContext.class,0);
		}
		public InsertQueryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_insertQuery; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).enterInsertQuery(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).exitInsertQuery(this);
		}
	}

	public final InsertQueryContext insertQuery() throws RecognitionException {
		InsertQueryContext _localctx = new InsertQueryContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_insertQuery);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(143); variables();
			setState(144); match(INSERT);
			setState(145); trace();
			setState(146); match(INTO);
			setState(147); locations();
			setState(150);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(148); match(WHERE);
				setState(149); predicate();
				}
			}

			setState(152); match(EOS);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariablesContext extends ParserRuleContext {
		public VariableContext variable(int i) {
			return getRuleContext(VariableContext.class,i);
		}
		public List<VariableContext> variable() {
			return getRuleContexts(VariableContext.class);
		}
		public VariablesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variables; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).enterVariables(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).exitVariables(this);
		}
	}

	public final VariablesContext variables() throws RecognitionException {
		VariablesContext _localctx = new VariablesContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_variables);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(157);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==VARIABLE_NAME) {
				{
				{
				setState(154); variable();
				}
				}
				setState(159);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableContext extends ParserRuleContext {
		public TerminalNode ASSIGN() { return getToken(PQLParser.ASSIGN, 0); }
		public VarNameContext varName() {
			return getRuleContext(VarNameContext.class,0);
		}
		public TerminalNode EOS() { return getToken(PQLParser.EOS, 0); }
		public SetOfTasksContext setOfTasks() {
			return getRuleContext(SetOfTasksContext.class,0);
		}
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).enterVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).exitVariable(this);
		}
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_variable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(160); varName();
			setState(161); match(ASSIGN);
			setState(162); setOfTasks();
			setState(163); match(EOS);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarNameContext extends ParserRuleContext {
		public TerminalNode VARIABLE_NAME() { return getToken(PQLParser.VARIABLE_NAME, 0); }
		public VarNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).enterVarName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).exitVarName(this);
		}
	}

	public final VarNameContext varName() throws RecognitionException {
		VarNameContext _localctx = new VarNameContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_varName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(165); match(VARIABLE_NAME);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AttributesContext extends ParserRuleContext {
		public List<AttributeContext> attribute() {
			return getRuleContexts(AttributeContext.class);
		}
		public AttributeContext attribute(int i) {
			return getRuleContext(AttributeContext.class,i);
		}
		public List<TerminalNode> SEP() { return getTokens(PQLParser.SEP); }
		public TerminalNode SEP(int i) {
			return getToken(PQLParser.SEP, i);
		}
		public AttributesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attributes; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).enterAttributes(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).exitAttributes(this);
		}
	}

	public final AttributesContext attributes() throws RecognitionException {
		AttributesContext _localctx = new AttributesContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_attributes);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(167); attribute();
			setState(172);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SEP) {
				{
				{
				setState(168); match(SEP);
				setState(169); attribute();
				}
				}
				setState(174);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AttributeContext extends ParserRuleContext {
		public AttributeNameContext attributeName() {
			return getRuleContext(AttributeNameContext.class,0);
		}
		public UniverseContext universe() {
			return getRuleContext(UniverseContext.class,0);
		}
		public AttributeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attribute; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).enterAttribute(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).exitAttribute(this);
		}
	}

	public final AttributeContext attribute() throws RecognitionException {
		AttributeContext _localctx = new AttributeContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_attribute);
		try {
			setState(177);
			switch (_input.LA(1)) {
			case UNIVERSE:
				enterOuterAlt(_localctx, 1);
				{
				setState(175); universe();
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 2);
				{
				setState(176); attributeName();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LocationsContext extends ParserRuleContext {
		public List<LocationContext> location() {
			return getRuleContexts(LocationContext.class);
		}
		public List<TerminalNode> SEP() { return getTokens(PQLParser.SEP); }
		public LocationContext location(int i) {
			return getRuleContext(LocationContext.class,i);
		}
		public TerminalNode SEP(int i) {
			return getToken(PQLParser.SEP, i);
		}
		public UniverseContext universe() {
			return getRuleContext(UniverseContext.class,0);
		}
		public LocationsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_locations; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).enterLocations(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).exitLocations(this);
		}
	}

	public final LocationsContext locations() throws RecognitionException {
		LocationsContext _localctx = new LocationsContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_locations);
		int _la;
		try {
			setState(188);
			switch (_input.LA(1)) {
			case UNIVERSE:
				enterOuterAlt(_localctx, 1);
				{
				setState(179); universe();
				}
				break;
			case STRING:
			case LP:
				enterOuterAlt(_localctx, 2);
				{
				setState(180); location();
				setState(185);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SEP) {
					{
					{
					setState(181); match(SEP);
					setState(182); location();
					}
					}
					setState(187);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LocationContext extends ParserRuleContext {
		public NestedQueryContext nestedQuery() {
			return getRuleContext(NestedQueryContext.class,0);
		}
		public LocationPathContext locationPath() {
			return getRuleContext(LocationPathContext.class,0);
		}
		public LocationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_location; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).enterLocation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).exitLocation(this);
		}
	}

	public final LocationContext location() throws RecognitionException {
		LocationContext _localctx = new LocationContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_location);
		try {
			setState(192);
			switch (_input.LA(1)) {
			case STRING:
				enterOuterAlt(_localctx, 1);
				{
				setState(190); locationPath();
				}
				break;
			case LP:
				enterOuterAlt(_localctx, 2);
				{
				setState(191); nestedQuery();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UniverseContext extends ParserRuleContext {
		public TerminalNode UNIVERSE() { return getToken(PQLParser.UNIVERSE, 0); }
		public UniverseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_universe; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).enterUniverse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).exitUniverse(this);
		}
	}

	public final UniverseContext universe() throws RecognitionException {
		UniverseContext _localctx = new UniverseContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_universe);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(194); match(UNIVERSE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AttributeNameContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(PQLParser.STRING, 0); }
		public AttributeNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attributeName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).enterAttributeName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).exitAttributeName(this);
		}
	}

	public final AttributeNameContext attributeName() throws RecognitionException {
		AttributeNameContext _localctx = new AttributeNameContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_attributeName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(196); match(STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LocationPathContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(PQLParser.STRING, 0); }
		public LocationPathContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_locationPath; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).enterLocationPath(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).exitLocationPath(this);
		}
	}

	public final LocationPathContext locationPath() throws RecognitionException {
		LocationPathContext _localctx = new LocationPathContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_locationPath);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(198); match(STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NestedQueryContext extends ParserRuleContext {
		public TerminalNode RP() { return getToken(PQLParser.RP, 0); }
		public InsertQueryContext insertQuery() {
			return getRuleContext(InsertQueryContext.class,0);
		}
		public TerminalNode LP() { return getToken(PQLParser.LP, 0); }
		public SelectQueryContext selectQuery() {
			return getRuleContext(SelectQueryContext.class,0);
		}
		public NestedQueryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nestedQuery; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).enterNestedQuery(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).exitNestedQuery(this);
		}
	}

	public final NestedQueryContext nestedQuery() throws RecognitionException {
		NestedQueryContext _localctx = new NestedQueryContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_nestedQuery);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(200); match(LP);
			setState(203);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				{
				setState(201); selectQuery();
				}
				break;

			case 2:
				{
				setState(202); insertQuery();
				}
				break;
			}
			setState(205); match(RP);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SetOfTasksContext extends ParserRuleContext {
		public IntersectionContext intersection() {
			return getRuleContext(IntersectionContext.class,0);
		}
		public TasksContext tasks() {
			return getRuleContext(TasksContext.class,0);
		}
		public DifferenceContext difference() {
			return getRuleContext(DifferenceContext.class,0);
		}
		public UnionContext union() {
			return getRuleContext(UnionContext.class,0);
		}
		public SetOfTasksContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_setOfTasks; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).enterSetOfTasks(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).exitSetOfTasks(this);
		}
	}

	public final SetOfTasksContext setOfTasks() throws RecognitionException {
		SetOfTasksContext _localctx = new SetOfTasksContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_setOfTasks);
		try {
			setState(211);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(207); tasks();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(208); union();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(209); intersection();
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(210); difference();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TasksContext extends ParserRuleContext {
		public SetOfAllTasksContext setOfAllTasks() {
			return getRuleContext(SetOfAllTasksContext.class,0);
		}
		public SetOfTasksParenthesesContext setOfTasksParentheses() {
			return getRuleContext(SetOfTasksParenthesesContext.class,0);
		}
		public VarNameContext varName() {
			return getRuleContext(VarNameContext.class,0);
		}
		public SetOfTasksLiteralContext setOfTasksLiteral() {
			return getRuleContext(SetOfTasksLiteralContext.class,0);
		}
		public SetOfTasksConstructionContext setOfTasksConstruction() {
			return getRuleContext(SetOfTasksConstructionContext.class,0);
		}
		public TasksContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tasks; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).enterTasks(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).exitTasks(this);
		}
	}

	public final TasksContext tasks() throws RecognitionException {
		TasksContext _localctx = new TasksContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_tasks);
		try {
			setState(218);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(213); varName();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(214); setOfAllTasks();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(215); setOfTasksLiteral();
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(216); setOfTasksConstruction();
				}
				break;

			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(217); setOfTasksParentheses();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SetOfAllTasksContext extends ParserRuleContext {
		public TerminalNode RP() { return getToken(PQLParser.RP, 0); }
		public TerminalNode GET_TASKS() { return getToken(PQLParser.GET_TASKS, 0); }
		public TerminalNode LP() { return getToken(PQLParser.LP, 0); }
		public SetOfAllTasksContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_setOfAllTasks; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).enterSetOfAllTasks(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).exitSetOfAllTasks(this);
		}
	}

	public final SetOfAllTasksContext setOfAllTasks() throws RecognitionException {
		SetOfAllTasksContext _localctx = new SetOfAllTasksContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_setOfAllTasks);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(220); match(GET_TASKS);
			setState(221); match(LP);
			setState(222); match(RP);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SetOfTasksLiteralContext extends ParserRuleContext {
		public TaskContext task(int i) {
			return getRuleContext(TaskContext.class,i);
		}
		public List<TerminalNode> SEP() { return getTokens(PQLParser.SEP); }
		public TerminalNode RB() { return getToken(PQLParser.RB, 0); }
		public TerminalNode LB() { return getToken(PQLParser.LB, 0); }
		public List<TaskContext> task() {
			return getRuleContexts(TaskContext.class);
		}
		public TerminalNode SEP(int i) {
			return getToken(PQLParser.SEP, i);
		}
		public SetOfTasksLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_setOfTasksLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).enterSetOfTasksLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).exitSetOfTasksLiteral(this);
		}
	}

	public final SetOfTasksLiteralContext setOfTasksLiteral() throws RecognitionException {
		SetOfTasksLiteralContext _localctx = new SetOfTasksLiteralContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_setOfTasksLiteral);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(224); match(LB);
			setState(233);
			_la = _input.LA(1);
			if (_la==STRING || _la==TILDE) {
				{
				setState(225); task();
				setState(230);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SEP) {
					{
					{
					setState(226); match(SEP);
					setState(227); task();
					}
					}
					setState(232);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(235); match(RB);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TraceContext extends ParserRuleContext {
		public TerminalNode RTB() { return getToken(PQLParser.RTB, 0); }
		public TerminalNode LTB() { return getToken(PQLParser.LTB, 0); }
		public List<TerminalNode> SEP() { return getTokens(PQLParser.SEP); }
		public EventContext event(int i) {
			return getRuleContext(EventContext.class,i);
		}
		public TerminalNode SEP(int i) {
			return getToken(PQLParser.SEP, i);
		}
		public List<EventContext> event() {
			return getRuleContexts(EventContext.class);
		}
		public TraceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_trace; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).enterTrace(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).exitTrace(this);
		}
	}

	public final TraceContext trace() throws RecognitionException {
		TraceContext _localctx = new TraceContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_trace);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(237); match(LTB);
			setState(246);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << UNIVERSE) | (1L << STRING) | (1L << TILDE))) != 0)) {
				{
				setState(238); event();
				setState(243);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SEP) {
					{
					{
					setState(239); match(SEP);
					setState(240); event();
					}
					}
					setState(245);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(248); match(RTB);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EventContext extends ParserRuleContext {
		public TaskContext task() {
			return getRuleContext(TaskContext.class,0);
		}
		public UniverseContext universe() {
			return getRuleContext(UniverseContext.class,0);
		}
		public EventContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_event; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).enterEvent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).exitEvent(this);
		}
	}

	public final EventContext event() throws RecognitionException {
		EventContext _localctx = new EventContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_event);
		try {
			setState(252);
			switch (_input.LA(1)) {
			case UNIVERSE:
				enterOuterAlt(_localctx, 1);
				{
				setState(250); universe();
				}
				break;
			case STRING:
			case TILDE:
				enterOuterAlt(_localctx, 2);
				{
				setState(251); task();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TaskContext extends ParserRuleContext {
		public ApproximateContext approximate() {
			return getRuleContext(ApproximateContext.class,0);
		}
		public TerminalNode RSB() { return getToken(PQLParser.RSB, 0); }
		public LabelContext label() {
			return getRuleContext(LabelContext.class,0);
		}
		public TerminalNode LSB() { return getToken(PQLParser.LSB, 0); }
		public SimilarityContext similarity() {
			return getRuleContext(SimilarityContext.class,0);
		}
		public TaskContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_task; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).enterTask(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).exitTask(this);
		}
	}

	public final TaskContext task() throws RecognitionException {
		TaskContext _localctx = new TaskContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_task);
		int _la;
		try {
			setState(264);
			switch (_input.LA(1)) {
			case TILDE:
				enterOuterAlt(_localctx, 1);
				{
				setState(254); approximate();
				setState(255); label();
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 2);
				{
				setState(257); label();
				setState(262);
				_la = _input.LA(1);
				if (_la==LSB) {
					{
					setState(258); match(LSB);
					setState(259); similarity();
					setState(260); match(RSB);
					}
				}

				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ApproximateContext extends ParserRuleContext {
		public TerminalNode TILDE() { return getToken(PQLParser.TILDE, 0); }
		public ApproximateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_approximate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).enterApproximate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).exitApproximate(this);
		}
	}

	public final ApproximateContext approximate() throws RecognitionException {
		ApproximateContext _localctx = new ApproximateContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_approximate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(266); match(TILDE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LabelContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(PQLParser.STRING, 0); }
		public LabelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_label; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).enterLabel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).exitLabel(this);
		}
	}

	public final LabelContext label() throws RecognitionException {
		LabelContext _localctx = new LabelContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_label);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(268); match(STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SimilarityContext extends ParserRuleContext {
		public TerminalNode SIMILARITY() { return getToken(PQLParser.SIMILARITY, 0); }
		public SimilarityContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_similarity; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).enterSimilarity(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).exitSimilarity(this);
		}
	}

	public final SimilarityContext similarity() throws RecognitionException {
		SimilarityContext _localctx = new SimilarityContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_similarity);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(270); match(SIMILARITY);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SetOfTasksConstructionContext extends ParserRuleContext {
		public UnaryPredicateConstructionContext unaryPredicateConstruction() {
			return getRuleContext(UnaryPredicateConstructionContext.class,0);
		}
		public BinaryPredicateConstructionContext binaryPredicateConstruction() {
			return getRuleContext(BinaryPredicateConstructionContext.class,0);
		}
		public SetOfTasksConstructionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_setOfTasksConstruction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).enterSetOfTasksConstruction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).exitSetOfTasksConstruction(this);
		}
	}

	public final SetOfTasksConstructionContext setOfTasksConstruction() throws RecognitionException {
		SetOfTasksConstructionContext _localctx = new SetOfTasksConstructionContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_setOfTasksConstruction);
		try {
			setState(274);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(272); unaryPredicateConstruction();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(273); binaryPredicateConstruction();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UnaryPredicateConstructionContext extends ParserRuleContext {
		public TerminalNode RP() { return getToken(PQLParser.RP, 0); }
		public TerminalNode GET_TASKS() { return getToken(PQLParser.GET_TASKS, 0); }
		public UnaryPredicateNameContext unaryPredicateName() {
			return getRuleContext(UnaryPredicateNameContext.class,0);
		}
		public TerminalNode LP() { return getToken(PQLParser.LP, 0); }
		public SetOfTasksContext setOfTasks() {
			return getRuleContext(SetOfTasksContext.class,0);
		}
		public UnaryPredicateConstructionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unaryPredicateConstruction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).enterUnaryPredicateConstruction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).exitUnaryPredicateConstruction(this);
		}
	}

	public final UnaryPredicateConstructionContext unaryPredicateConstruction() throws RecognitionException {
		UnaryPredicateConstructionContext _localctx = new UnaryPredicateConstructionContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_unaryPredicateConstruction);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(276); match(GET_TASKS);
			}
			setState(277); unaryPredicateName();
			setState(278); match(LP);
			setState(279); setOfTasks();
			setState(280); match(RP);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BinaryPredicateConstructionContext extends ParserRuleContext {
		public SetOfTasksContext setOfTasks(int i) {
			return getRuleContext(SetOfTasksContext.class,i);
		}
		public TerminalNode RP() { return getToken(PQLParser.RP, 0); }
		public TerminalNode GET_TASKS() { return getToken(PQLParser.GET_TASKS, 0); }
		public TerminalNode LP() { return getToken(PQLParser.LP, 0); }
		public BinaryPredicateNameContext binaryPredicateName() {
			return getRuleContext(BinaryPredicateNameContext.class,0);
		}
		public List<TerminalNode> SEP() { return getTokens(PQLParser.SEP); }
		public AnyAllContext anyAll() {
			return getRuleContext(AnyAllContext.class,0);
		}
		public TerminalNode SEP(int i) {
			return getToken(PQLParser.SEP, i);
		}
		public List<SetOfTasksContext> setOfTasks() {
			return getRuleContexts(SetOfTasksContext.class);
		}
		public BinaryPredicateConstructionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binaryPredicateConstruction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).enterBinaryPredicateConstruction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).exitBinaryPredicateConstruction(this);
		}
	}

	public final BinaryPredicateConstructionContext binaryPredicateConstruction() throws RecognitionException {
		BinaryPredicateConstructionContext _localctx = new BinaryPredicateConstructionContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_binaryPredicateConstruction);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(282); match(GET_TASKS);
			}
			setState(283); binaryPredicateName();
			setState(284); match(LP);
			setState(285); setOfTasks();
			setState(286); match(SEP);
			setState(287); setOfTasks();
			setState(288); match(SEP);
			setState(289); anyAll();
			setState(290); match(RP);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AnyAllContext extends ParserRuleContext {
		public TerminalNode ANY() { return getToken(PQLParser.ANY, 0); }
		public TerminalNode ALL() { return getToken(PQLParser.ALL, 0); }
		public AnyAllContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_anyAll; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).enterAnyAll(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).exitAnyAll(this);
		}
	}

	public final AnyAllContext anyAll() throws RecognitionException {
		AnyAllContext _localctx = new AnyAllContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_anyAll);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(292);
			_la = _input.LA(1);
			if ( !(_la==ANY || _la==ALL) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UnaryPredicateNameContext extends ParserRuleContext {
		public TerminalNode CAN_OCCUR() { return getToken(PQLParser.CAN_OCCUR, 0); }
		public TerminalNode ALWAYS_OCCURS() { return getToken(PQLParser.ALWAYS_OCCURS, 0); }
		public UnaryPredicateNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unaryPredicateName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).enterUnaryPredicateName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).exitUnaryPredicateName(this);
		}
	}

	public final UnaryPredicateNameContext unaryPredicateName() throws RecognitionException {
		UnaryPredicateNameContext _localctx = new UnaryPredicateNameContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_unaryPredicateName);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(294);
			_la = _input.LA(1);
			if ( !(_la==CAN_OCCUR || _la==ALWAYS_OCCURS) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UnaryTracePredicateNameContext extends ParserRuleContext {
		public TerminalNode EXECUTES() { return getToken(PQLParser.EXECUTES, 0); }
		public UnaryTracePredicateNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unaryTracePredicateName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).enterUnaryTracePredicateName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).exitUnaryTracePredicateName(this);
		}
	}

	public final UnaryTracePredicateNameContext unaryTracePredicateName() throws RecognitionException {
		UnaryTracePredicateNameContext _localctx = new UnaryTracePredicateNameContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_unaryTracePredicateName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(296); match(EXECUTES);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BinaryPredicateNameContext extends ParserRuleContext {
		public TerminalNode CONFLICT() { return getToken(PQLParser.CONFLICT, 0); }
		public TerminalNode CAN_CONFLICT() { return getToken(PQLParser.CAN_CONFLICT, 0); }
		public TerminalNode TOTAL_CONCUR() { return getToken(PQLParser.TOTAL_CONCUR, 0); }
		public TerminalNode TOTAL_CAUSAL() { return getToken(PQLParser.TOTAL_CAUSAL, 0); }
		public TerminalNode COOCCUR() { return getToken(PQLParser.COOCCUR, 0); }
		public TerminalNode CAN_COOCCUR() { return getToken(PQLParser.CAN_COOCCUR, 0); }
		public BinaryPredicateNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binaryPredicateName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).enterBinaryPredicateName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).exitBinaryPredicateName(this);
		}
	}

	public final BinaryPredicateNameContext binaryPredicateName() throws RecognitionException {
		BinaryPredicateNameContext _localctx = new BinaryPredicateNameContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_binaryPredicateName);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(298);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << CAN_CONFLICT) | (1L << CAN_COOCCUR) | (1L << CONFLICT) | (1L << COOCCUR) | (1L << TOTAL_CAUSAL) | (1L << TOTAL_CONCUR))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PredicateContext extends ParserRuleContext {
		public LogicalTestContext logicalTest() {
			return getRuleContext(LogicalTestContext.class,0);
		}
		public PropositionContext proposition() {
			return getRuleContext(PropositionContext.class,0);
		}
		public ConjunctionContext conjunction() {
			return getRuleContext(ConjunctionContext.class,0);
		}
		public DisjunctionContext disjunction() {
			return getRuleContext(DisjunctionContext.class,0);
		}
		public PredicateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_predicate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).enterPredicate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).exitPredicate(this);
		}
	}

	public final PredicateContext predicate() throws RecognitionException {
		PredicateContext _localctx = new PredicateContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_predicate);
		try {
			setState(304);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(300); proposition();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(301); conjunction();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(302); disjunction();
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(303); logicalTest();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PropositionContext extends ParserRuleContext {
		public BinaryPredicateContext binaryPredicate() {
			return getRuleContext(BinaryPredicateContext.class,0);
		}
		public UnaryPredicateMacroContext unaryPredicateMacro() {
			return getRuleContext(UnaryPredicateMacroContext.class,0);
		}
		public TruthValueContext truthValue() {
			return getRuleContext(TruthValueContext.class,0);
		}
		public NegationContext negation() {
			return getRuleContext(NegationContext.class,0);
		}
		public UnaryTracePredicateContext unaryTracePredicate() {
			return getRuleContext(UnaryTracePredicateContext.class,0);
		}
		public BinaryPredicateMacroContext binaryPredicateMacro() {
			return getRuleContext(BinaryPredicateMacroContext.class,0);
		}
		public UnaryPredicateContext unaryPredicate() {
			return getRuleContext(UnaryPredicateContext.class,0);
		}
		public SetPredicateContext setPredicate() {
			return getRuleContext(SetPredicateContext.class,0);
		}
		public ParenthesesContext parentheses() {
			return getRuleContext(ParenthesesContext.class,0);
		}
		public PropositionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_proposition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).enterProposition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).exitProposition(this);
		}
	}

	public final PropositionContext proposition() throws RecognitionException {
		PropositionContext _localctx = new PropositionContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_proposition);
		try {
			setState(315);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(306); unaryPredicate();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(307); binaryPredicate();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(308); unaryTracePredicate();
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(309); unaryPredicateMacro();
				}
				break;

			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(310); binaryPredicateMacro();
				}
				break;

			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(311); setPredicate();
				}
				break;

			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(312); truthValue();
				}
				break;

			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(313); parentheses();
				}
				break;

			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(314); negation();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UnaryPredicateContext extends ParserRuleContext {
		public TerminalNode RP() { return getToken(PQLParser.RP, 0); }
		public UnaryPredicateNameContext unaryPredicateName() {
			return getRuleContext(UnaryPredicateNameContext.class,0);
		}
		public TerminalNode LP() { return getToken(PQLParser.LP, 0); }
		public TaskContext task() {
			return getRuleContext(TaskContext.class,0);
		}
		public UnaryPredicateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unaryPredicate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).enterUnaryPredicate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).exitUnaryPredicate(this);
		}
	}

	public final UnaryPredicateContext unaryPredicate() throws RecognitionException {
		UnaryPredicateContext _localctx = new UnaryPredicateContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_unaryPredicate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(317); unaryPredicateName();
			setState(318); match(LP);
			setState(319); task();
			setState(320); match(RP);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BinaryPredicateContext extends ParserRuleContext {
		public TerminalNode RP() { return getToken(PQLParser.RP, 0); }
		public TaskContext task(int i) {
			return getRuleContext(TaskContext.class,i);
		}
		public TerminalNode LP() { return getToken(PQLParser.LP, 0); }
		public BinaryPredicateNameContext binaryPredicateName() {
			return getRuleContext(BinaryPredicateNameContext.class,0);
		}
		public TerminalNode SEP() { return getToken(PQLParser.SEP, 0); }
		public List<TaskContext> task() {
			return getRuleContexts(TaskContext.class);
		}
		public BinaryPredicateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binaryPredicate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).enterBinaryPredicate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).exitBinaryPredicate(this);
		}
	}

	public final BinaryPredicateContext binaryPredicate() throws RecognitionException {
		BinaryPredicateContext _localctx = new BinaryPredicateContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_binaryPredicate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(322); binaryPredicateName();
			setState(323); match(LP);
			setState(324); task();
			setState(325); match(SEP);
			setState(326); task();
			setState(327); match(RP);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UnaryTracePredicateContext extends ParserRuleContext {
		public TerminalNode RP() { return getToken(PQLParser.RP, 0); }
		public UnaryTracePredicateNameContext unaryTracePredicateName() {
			return getRuleContext(UnaryTracePredicateNameContext.class,0);
		}
		public TerminalNode LP() { return getToken(PQLParser.LP, 0); }
		public TraceContext trace() {
			return getRuleContext(TraceContext.class,0);
		}
		public UnaryTracePredicateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unaryTracePredicate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).enterUnaryTracePredicate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).exitUnaryTracePredicate(this);
		}
	}

	public final UnaryTracePredicateContext unaryTracePredicate() throws RecognitionException {
		UnaryTracePredicateContext _localctx = new UnaryTracePredicateContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_unaryTracePredicate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(329); unaryTracePredicateName();
			setState(330); match(LP);
			setState(331); trace();
			setState(332); match(RP);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UnaryPredicateMacroContext extends ParserRuleContext {
		public TerminalNode RP() { return getToken(PQLParser.RP, 0); }
		public UnaryPredicateNameContext unaryPredicateName() {
			return getRuleContext(UnaryPredicateNameContext.class,0);
		}
		public TerminalNode LP() { return getToken(PQLParser.LP, 0); }
		public TerminalNode SEP() { return getToken(PQLParser.SEP, 0); }
		public AnyAllContext anyAll() {
			return getRuleContext(AnyAllContext.class,0);
		}
		public SetOfTasksContext setOfTasks() {
			return getRuleContext(SetOfTasksContext.class,0);
		}
		public UnaryPredicateMacroContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unaryPredicateMacro; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).enterUnaryPredicateMacro(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).exitUnaryPredicateMacro(this);
		}
	}

	public final UnaryPredicateMacroContext unaryPredicateMacro() throws RecognitionException {
		UnaryPredicateMacroContext _localctx = new UnaryPredicateMacroContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_unaryPredicateMacro);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(334); unaryPredicateName();
			setState(335); match(LP);
			setState(336); setOfTasks();
			setState(337); match(SEP);
			setState(338); anyAll();
			setState(339); match(RP);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BinaryPredicateMacroContext extends ParserRuleContext {
		public BinaryPredicateMacroSetSetContext binaryPredicateMacroSetSet() {
			return getRuleContext(BinaryPredicateMacroSetSetContext.class,0);
		}
		public BinaryPredicateMacroTaskSetContext binaryPredicateMacroTaskSet() {
			return getRuleContext(BinaryPredicateMacroTaskSetContext.class,0);
		}
		public BinaryPredicateMacroContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binaryPredicateMacro; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).enterBinaryPredicateMacro(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).exitBinaryPredicateMacro(this);
		}
	}

	public final BinaryPredicateMacroContext binaryPredicateMacro() throws RecognitionException {
		BinaryPredicateMacroContext _localctx = new BinaryPredicateMacroContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_binaryPredicateMacro);
		try {
			setState(343);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(341); binaryPredicateMacroTaskSet();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(342); binaryPredicateMacroSetSet();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BinaryPredicateMacroTaskSetContext extends ParserRuleContext {
		public TerminalNode RP() { return getToken(PQLParser.RP, 0); }
		public TerminalNode LP() { return getToken(PQLParser.LP, 0); }
		public BinaryPredicateNameContext binaryPredicateName() {
			return getRuleContext(BinaryPredicateNameContext.class,0);
		}
		public List<TerminalNode> SEP() { return getTokens(PQLParser.SEP); }
		public AnyAllContext anyAll() {
			return getRuleContext(AnyAllContext.class,0);
		}
		public TaskContext task() {
			return getRuleContext(TaskContext.class,0);
		}
		public TerminalNode SEP(int i) {
			return getToken(PQLParser.SEP, i);
		}
		public SetOfTasksContext setOfTasks() {
			return getRuleContext(SetOfTasksContext.class,0);
		}
		public BinaryPredicateMacroTaskSetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binaryPredicateMacroTaskSet; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).enterBinaryPredicateMacroTaskSet(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).exitBinaryPredicateMacroTaskSet(this);
		}
	}

	public final BinaryPredicateMacroTaskSetContext binaryPredicateMacroTaskSet() throws RecognitionException {
		BinaryPredicateMacroTaskSetContext _localctx = new BinaryPredicateMacroTaskSetContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_binaryPredicateMacroTaskSet);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(345); binaryPredicateName();
			setState(346); match(LP);
			setState(347); task();
			setState(348); match(SEP);
			setState(349); setOfTasks();
			setState(350); match(SEP);
			setState(351); anyAll();
			setState(352); match(RP);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BinaryPredicateMacroSetSetContext extends ParserRuleContext {
		public SetOfTasksContext setOfTasks(int i) {
			return getRuleContext(SetOfTasksContext.class,i);
		}
		public TerminalNode RP() { return getToken(PQLParser.RP, 0); }
		public TerminalNode LP() { return getToken(PQLParser.LP, 0); }
		public BinaryPredicateNameContext binaryPredicateName() {
			return getRuleContext(BinaryPredicateNameContext.class,0);
		}
		public List<TerminalNode> SEP() { return getTokens(PQLParser.SEP); }
		public AnySomeEachAllContext anySomeEachAll() {
			return getRuleContext(AnySomeEachAllContext.class,0);
		}
		public TerminalNode SEP(int i) {
			return getToken(PQLParser.SEP, i);
		}
		public List<SetOfTasksContext> setOfTasks() {
			return getRuleContexts(SetOfTasksContext.class);
		}
		public BinaryPredicateMacroSetSetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binaryPredicateMacroSetSet; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).enterBinaryPredicateMacroSetSet(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).exitBinaryPredicateMacroSetSet(this);
		}
	}

	public final BinaryPredicateMacroSetSetContext binaryPredicateMacroSetSet() throws RecognitionException {
		BinaryPredicateMacroSetSetContext _localctx = new BinaryPredicateMacroSetSetContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_binaryPredicateMacroSetSet);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(354); binaryPredicateName();
			setState(355); match(LP);
			setState(356); setOfTasks();
			setState(357); match(SEP);
			setState(358); setOfTasks();
			setState(359); match(SEP);
			setState(360); anySomeEachAll();
			setState(361); match(RP);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AnySomeEachAllContext extends ParserRuleContext {
		public TerminalNode EACH() { return getToken(PQLParser.EACH, 0); }
		public TerminalNode ANY() { return getToken(PQLParser.ANY, 0); }
		public TerminalNode SOME() { return getToken(PQLParser.SOME, 0); }
		public TerminalNode ALL() { return getToken(PQLParser.ALL, 0); }
		public AnySomeEachAllContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_anySomeEachAll; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).enterAnySomeEachAll(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).exitAnySomeEachAll(this);
		}
	}

	public final AnySomeEachAllContext anySomeEachAll() throws RecognitionException {
		AnySomeEachAllContext _localctx = new AnySomeEachAllContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_anySomeEachAll);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(363);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ANY) | (1L << SOME) | (1L << EACH) | (1L << ALL))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SetPredicateContext extends ParserRuleContext {
		public TaskInSetOfTasksContext taskInSetOfTasks() {
			return getRuleContext(TaskInSetOfTasksContext.class,0);
		}
		public SetComparisonContext setComparison() {
			return getRuleContext(SetComparisonContext.class,0);
		}
		public SetPredicateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_setPredicate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).enterSetPredicate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).exitSetPredicate(this);
		}
	}

	public final SetPredicateContext setPredicate() throws RecognitionException {
		SetPredicateContext _localctx = new SetPredicateContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_setPredicate);
		try {
			setState(367);
			switch (_input.LA(1)) {
			case STRING:
			case TILDE:
				enterOuterAlt(_localctx, 1);
				{
				setState(365); taskInSetOfTasks();
				}
				break;
			case VARIABLE_NAME:
			case LP:
			case LB:
			case GET_TASKS:
				enterOuterAlt(_localctx, 2);
				{
				setState(366); setComparison();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TaskInSetOfTasksContext extends ParserRuleContext {
		public TaskContext task() {
			return getRuleContext(TaskContext.class,0);
		}
		public TerminalNode IN() { return getToken(PQLParser.IN, 0); }
		public SetOfTasksContext setOfTasks() {
			return getRuleContext(SetOfTasksContext.class,0);
		}
		public TaskInSetOfTasksContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_taskInSetOfTasks; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).enterTaskInSetOfTasks(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).exitTaskInSetOfTasks(this);
		}
	}

	public final TaskInSetOfTasksContext taskInSetOfTasks() throws RecognitionException {
		TaskInSetOfTasksContext _localctx = new TaskInSetOfTasksContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_taskInSetOfTasks);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(369); task();
			setState(370); match(IN);
			setState(371); setOfTasks();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SetComparisonContext extends ParserRuleContext {
		public SetOfTasksContext setOfTasks(int i) {
			return getRuleContext(SetOfTasksContext.class,i);
		}
		public SetComparisonOperatorContext setComparisonOperator() {
			return getRuleContext(SetComparisonOperatorContext.class,0);
		}
		public List<SetOfTasksContext> setOfTasks() {
			return getRuleContexts(SetOfTasksContext.class);
		}
		public SetComparisonContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_setComparison; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).enterSetComparison(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).exitSetComparison(this);
		}
	}

	public final SetComparisonContext setComparison() throws RecognitionException {
		SetComparisonContext _localctx = new SetComparisonContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_setComparison);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(373); setOfTasks();
			setState(374); setComparisonOperator();
			setState(375); setOfTasks();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SetComparisonOperatorContext extends ParserRuleContext {
		public OverlapsWithContext overlapsWith() {
			return getRuleContext(OverlapsWithContext.class,0);
		}
		public SubsetOfContext subsetOf() {
			return getRuleContext(SubsetOfContext.class,0);
		}
		public IdenticalContext identical() {
			return getRuleContext(IdenticalContext.class,0);
		}
		public DifferentContext different() {
			return getRuleContext(DifferentContext.class,0);
		}
		public ProperSubsetOfContext properSubsetOf() {
			return getRuleContext(ProperSubsetOfContext.class,0);
		}
		public SetComparisonOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_setComparisonOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).enterSetComparisonOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).exitSetComparisonOperator(this);
		}
	}

	public final SetComparisonOperatorContext setComparisonOperator() throws RecognitionException {
		SetComparisonOperatorContext _localctx = new SetComparisonOperatorContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_setComparisonOperator);
		try {
			setState(382);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(377); identical();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(378); different();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(379); overlapsWith();
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(380); subsetOf();
				}
				break;

			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(381); properSubsetOf();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TruthValueContext extends ParserRuleContext {
		public TerminalNode FALSE() { return getToken(PQLParser.FALSE, 0); }
		public TerminalNode TRUE() { return getToken(PQLParser.TRUE, 0); }
		public TruthValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_truthValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).enterTruthValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).exitTruthValue(this);
		}
	}

	public final TruthValueContext truthValue() throws RecognitionException {
		TruthValueContext _localctx = new TruthValueContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_truthValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(384);
			_la = _input.LA(1);
			if ( !(_la==TRUE || _la==FALSE) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LogicalTestContext extends ParserRuleContext {
		public IsFalseContext isFalse() {
			return getRuleContext(IsFalseContext.class,0);
		}
		public IsNotFalseContext isNotFalse() {
			return getRuleContext(IsNotFalseContext.class,0);
		}
		public IsNotTrueContext isNotTrue() {
			return getRuleContext(IsNotTrueContext.class,0);
		}
		public IsTrueContext isTrue() {
			return getRuleContext(IsTrueContext.class,0);
		}
		public LogicalTestContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logicalTest; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).enterLogicalTest(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).exitLogicalTest(this);
		}
	}

	public final LogicalTestContext logicalTest() throws RecognitionException {
		LogicalTestContext _localctx = new LogicalTestContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_logicalTest);
		try {
			setState(390);
			switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(386); isTrue();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(387); isNotTrue();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(388); isFalse();
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(389); isNotFalse();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UnionContext extends ParserRuleContext {
		public List<TerminalNode> UNION() { return getTokens(PQLParser.UNION); }
		public DifferenceContext difference(int i) {
			return getRuleContext(DifferenceContext.class,i);
		}
		public IntersectionContext intersection(int i) {
			return getRuleContext(IntersectionContext.class,i);
		}
		public List<IntersectionContext> intersection() {
			return getRuleContexts(IntersectionContext.class);
		}
		public TerminalNode UNION(int i) {
			return getToken(PQLParser.UNION, i);
		}
		public List<TasksContext> tasks() {
			return getRuleContexts(TasksContext.class);
		}
		public TasksContext tasks(int i) {
			return getRuleContext(TasksContext.class,i);
		}
		public List<DifferenceContext> difference() {
			return getRuleContexts(DifferenceContext.class);
		}
		public UnionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_union; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).enterUnion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).exitUnion(this);
		}
	}

	public final UnionContext union() throws RecognitionException {
		UnionContext _localctx = new UnionContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_union);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(395);
			switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
			case 1:
				{
				setState(392); tasks();
				}
				break;

			case 2:
				{
				setState(393); difference();
				}
				break;

			case 3:
				{
				setState(394); intersection();
				}
				break;
			}
			setState(397); match(UNION);
			setState(401);
			switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
			case 1:
				{
				setState(398); tasks();
				}
				break;

			case 2:
				{
				setState(399); difference();
				}
				break;

			case 3:
				{
				setState(400); intersection();
				}
				break;
			}
			setState(411);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==UNION) {
				{
				{
				setState(403); match(UNION);
				setState(407);
				switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
				case 1:
					{
					setState(404); tasks();
					}
					break;

				case 2:
					{
					setState(405); difference();
					}
					break;

				case 3:
					{
					setState(406); intersection();
					}
					break;
				}
				}
				}
				setState(413);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IntersectionContext extends ParserRuleContext {
		public TerminalNode INTERSECTION(int i) {
			return getToken(PQLParser.INTERSECTION, i);
		}
		public DifferenceContext difference(int i) {
			return getRuleContext(DifferenceContext.class,i);
		}
		public List<TasksContext> tasks() {
			return getRuleContexts(TasksContext.class);
		}
		public TasksContext tasks(int i) {
			return getRuleContext(TasksContext.class,i);
		}
		public List<DifferenceContext> difference() {
			return getRuleContexts(DifferenceContext.class);
		}
		public List<TerminalNode> INTERSECTION() { return getTokens(PQLParser.INTERSECTION); }
		public IntersectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_intersection; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).enterIntersection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).exitIntersection(this);
		}
	}

	public final IntersectionContext intersection() throws RecognitionException {
		IntersectionContext _localctx = new IntersectionContext(_ctx, getState());
		enterRule(_localctx, 96, RULE_intersection);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(416);
			switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
			case 1:
				{
				setState(414); tasks();
				}
				break;

			case 2:
				{
				setState(415); difference();
				}
				break;
			}
			setState(418); match(INTERSECTION);
			setState(421);
			switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
			case 1:
				{
				setState(419); tasks();
				}
				break;

			case 2:
				{
				setState(420); difference();
				}
				break;
			}
			setState(430);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==INTERSECTION) {
				{
				{
				setState(423); match(INTERSECTION);
				setState(426);
				switch ( getInterpreter().adaptivePredict(_input,32,_ctx) ) {
				case 1:
					{
					setState(424); tasks();
					}
					break;

				case 2:
					{
					setState(425); difference();
					}
					break;
				}
				}
				}
				setState(432);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DifferenceContext extends ParserRuleContext {
		public List<TasksContext> tasks() {
			return getRuleContexts(TasksContext.class);
		}
		public TasksContext tasks(int i) {
			return getRuleContext(TasksContext.class,i);
		}
		public DifferenceContext difference() {
			return getRuleContext(DifferenceContext.class,0);
		}
		public TerminalNode DIFFERENCE() { return getToken(PQLParser.DIFFERENCE, 0); }
		public DifferenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_difference; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).enterDifference(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).exitDifference(this);
		}
	}

	public final DifferenceContext difference() throws RecognitionException {
		DifferenceContext _localctx = new DifferenceContext(_ctx, getState());
		enterRule(_localctx, 98, RULE_difference);
		try {
			setState(441);
			switch ( getInterpreter().adaptivePredict(_input,34,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(433); tasks();
				setState(434); match(DIFFERENCE);
				setState(435); tasks();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(437); tasks();
				setState(438); match(DIFFERENCE);
				setState(439); difference();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NegationContext extends ParserRuleContext {
		public TerminalNode NOT() { return getToken(PQLParser.NOT, 0); }
		public PropositionContext proposition() {
			return getRuleContext(PropositionContext.class,0);
		}
		public NegationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_negation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).enterNegation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).exitNegation(this);
		}
	}

	public final NegationContext negation() throws RecognitionException {
		NegationContext _localctx = new NegationContext(_ctx, getState());
		enterRule(_localctx, 100, RULE_negation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(443); match(NOT);
			setState(444); proposition();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IsTrueContext extends ParserRuleContext {
		public TerminalNode IS() { return getToken(PQLParser.IS, 0); }
		public TerminalNode TRUE() { return getToken(PQLParser.TRUE, 0); }
		public PropositionContext proposition() {
			return getRuleContext(PropositionContext.class,0);
		}
		public IsTrueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_isTrue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).enterIsTrue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).exitIsTrue(this);
		}
	}

	public final IsTrueContext isTrue() throws RecognitionException {
		IsTrueContext _localctx = new IsTrueContext(_ctx, getState());
		enterRule(_localctx, 102, RULE_isTrue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(446); proposition();
			setState(447); match(IS);
			setState(448); match(TRUE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IsNotTrueContext extends ParserRuleContext {
		public TerminalNode NOT() { return getToken(PQLParser.NOT, 0); }
		public TerminalNode IS() { return getToken(PQLParser.IS, 0); }
		public TerminalNode TRUE() { return getToken(PQLParser.TRUE, 0); }
		public PropositionContext proposition() {
			return getRuleContext(PropositionContext.class,0);
		}
		public IsNotTrueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_isNotTrue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).enterIsNotTrue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).exitIsNotTrue(this);
		}
	}

	public final IsNotTrueContext isNotTrue() throws RecognitionException {
		IsNotTrueContext _localctx = new IsNotTrueContext(_ctx, getState());
		enterRule(_localctx, 104, RULE_isNotTrue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(450); proposition();
			setState(451); match(IS);
			setState(452); match(NOT);
			setState(453); match(TRUE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IsFalseContext extends ParserRuleContext {
		public TerminalNode IS() { return getToken(PQLParser.IS, 0); }
		public TerminalNode FALSE() { return getToken(PQLParser.FALSE, 0); }
		public PropositionContext proposition() {
			return getRuleContext(PropositionContext.class,0);
		}
		public IsFalseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_isFalse; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).enterIsFalse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).exitIsFalse(this);
		}
	}

	public final IsFalseContext isFalse() throws RecognitionException {
		IsFalseContext _localctx = new IsFalseContext(_ctx, getState());
		enterRule(_localctx, 106, RULE_isFalse);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(455); proposition();
			setState(456); match(IS);
			setState(457); match(FALSE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IsNotFalseContext extends ParserRuleContext {
		public TerminalNode NOT() { return getToken(PQLParser.NOT, 0); }
		public TerminalNode IS() { return getToken(PQLParser.IS, 0); }
		public TerminalNode FALSE() { return getToken(PQLParser.FALSE, 0); }
		public PropositionContext proposition() {
			return getRuleContext(PropositionContext.class,0);
		}
		public IsNotFalseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_isNotFalse; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).enterIsNotFalse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).exitIsNotFalse(this);
		}
	}

	public final IsNotFalseContext isNotFalse() throws RecognitionException {
		IsNotFalseContext _localctx = new IsNotFalseContext(_ctx, getState());
		enterRule(_localctx, 108, RULE_isNotFalse);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(459); proposition();
			setState(460); match(IS);
			setState(461); match(NOT);
			setState(462); match(FALSE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DisjunctionContext extends ParserRuleContext {
		public List<LogicalTestContext> logicalTest() {
			return getRuleContexts(LogicalTestContext.class);
		}
		public List<PropositionContext> proposition() {
			return getRuleContexts(PropositionContext.class);
		}
		public List<ConjunctionContext> conjunction() {
			return getRuleContexts(ConjunctionContext.class);
		}
		public LogicalTestContext logicalTest(int i) {
			return getRuleContext(LogicalTestContext.class,i);
		}
		public ConjunctionContext conjunction(int i) {
			return getRuleContext(ConjunctionContext.class,i);
		}
		public List<TerminalNode> OR() { return getTokens(PQLParser.OR); }
		public PropositionContext proposition(int i) {
			return getRuleContext(PropositionContext.class,i);
		}
		public TerminalNode OR(int i) {
			return getToken(PQLParser.OR, i);
		}
		public DisjunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_disjunction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).enterDisjunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).exitDisjunction(this);
		}
	}

	public final DisjunctionContext disjunction() throws RecognitionException {
		DisjunctionContext _localctx = new DisjunctionContext(_ctx, getState());
		enterRule(_localctx, 110, RULE_disjunction);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(467);
			switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
			case 1:
				{
				setState(464); proposition();
				}
				break;

			case 2:
				{
				setState(465); logicalTest();
				}
				break;

			case 3:
				{
				setState(466); conjunction();
				}
				break;
			}
			setState(469); match(OR);
			setState(473);
			switch ( getInterpreter().adaptivePredict(_input,36,_ctx) ) {
			case 1:
				{
				setState(470); proposition();
				}
				break;

			case 2:
				{
				setState(471); logicalTest();
				}
				break;

			case 3:
				{
				setState(472); conjunction();
				}
				break;
			}
			setState(483);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR) {
				{
				{
				setState(475); match(OR);
				setState(479);
				switch ( getInterpreter().adaptivePredict(_input,37,_ctx) ) {
				case 1:
					{
					setState(476); proposition();
					}
					break;

				case 2:
					{
					setState(477); logicalTest();
					}
					break;

				case 3:
					{
					setState(478); conjunction();
					}
					break;
				}
				}
				}
				setState(485);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConjunctionContext extends ParserRuleContext {
		public TerminalNode AND(int i) {
			return getToken(PQLParser.AND, i);
		}
		public List<LogicalTestContext> logicalTest() {
			return getRuleContexts(LogicalTestContext.class);
		}
		public List<PropositionContext> proposition() {
			return getRuleContexts(PropositionContext.class);
		}
		public List<TerminalNode> AND() { return getTokens(PQLParser.AND); }
		public LogicalTestContext logicalTest(int i) {
			return getRuleContext(LogicalTestContext.class,i);
		}
		public PropositionContext proposition(int i) {
			return getRuleContext(PropositionContext.class,i);
		}
		public ConjunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conjunction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).enterConjunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).exitConjunction(this);
		}
	}

	public final ConjunctionContext conjunction() throws RecognitionException {
		ConjunctionContext _localctx = new ConjunctionContext(_ctx, getState());
		enterRule(_localctx, 112, RULE_conjunction);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(488);
			switch ( getInterpreter().adaptivePredict(_input,39,_ctx) ) {
			case 1:
				{
				setState(486); proposition();
				}
				break;

			case 2:
				{
				setState(487); logicalTest();
				}
				break;
			}
			setState(490); match(AND);
			setState(493);
			switch ( getInterpreter().adaptivePredict(_input,40,_ctx) ) {
			case 1:
				{
				setState(491); proposition();
				}
				break;

			case 2:
				{
				setState(492); logicalTest();
				}
				break;
			}
			setState(502);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AND) {
				{
				{
				setState(495); match(AND);
				setState(498);
				switch ( getInterpreter().adaptivePredict(_input,41,_ctx) ) {
				case 1:
					{
					setState(496); proposition();
					}
					break;

				case 2:
					{
					setState(497); logicalTest();
					}
					break;
				}
				}
				}
				setState(504);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParenthesesContext extends ParserRuleContext {
		public TerminalNode RP() { return getToken(PQLParser.RP, 0); }
		public LogicalTestContext logicalTest() {
			return getRuleContext(LogicalTestContext.class,0);
		}
		public TerminalNode LP() { return getToken(PQLParser.LP, 0); }
		public PropositionContext proposition() {
			return getRuleContext(PropositionContext.class,0);
		}
		public ConjunctionContext conjunction() {
			return getRuleContext(ConjunctionContext.class,0);
		}
		public DisjunctionContext disjunction() {
			return getRuleContext(DisjunctionContext.class,0);
		}
		public ParenthesesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parentheses; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).enterParentheses(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).exitParentheses(this);
		}
	}

	public final ParenthesesContext parentheses() throws RecognitionException {
		ParenthesesContext _localctx = new ParenthesesContext(_ctx, getState());
		enterRule(_localctx, 114, RULE_parentheses);
		try {
			setState(521);
			switch ( getInterpreter().adaptivePredict(_input,43,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(505); match(LP);
				setState(506); proposition();
				setState(507); match(RP);
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(509); match(LP);
				setState(510); conjunction();
				setState(511); match(RP);
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(513); match(LP);
				setState(514); disjunction();
				setState(515); match(RP);
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(517); match(LP);
				setState(518); logicalTest();
				setState(519); match(RP);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SetOfTasksParenthesesContext extends ParserRuleContext {
		public TerminalNode RP() { return getToken(PQLParser.RP, 0); }
		public SetOfTasksParenthesesContext setOfTasksParentheses() {
			return getRuleContext(SetOfTasksParenthesesContext.class,0);
		}
		public TerminalNode LP() { return getToken(PQLParser.LP, 0); }
		public VarNameContext varName() {
			return getRuleContext(VarNameContext.class,0);
		}
		public IntersectionContext intersection() {
			return getRuleContext(IntersectionContext.class,0);
		}
		public SetOfTasksLiteralContext setOfTasksLiteral() {
			return getRuleContext(SetOfTasksLiteralContext.class,0);
		}
		public DifferenceContext difference() {
			return getRuleContext(DifferenceContext.class,0);
		}
		public SetOfTasksConstructionContext setOfTasksConstruction() {
			return getRuleContext(SetOfTasksConstructionContext.class,0);
		}
		public UnionContext union() {
			return getRuleContext(UnionContext.class,0);
		}
		public UniverseContext universe() {
			return getRuleContext(UniverseContext.class,0);
		}
		public SetOfTasksParenthesesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_setOfTasksParentheses; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).enterSetOfTasksParentheses(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).exitSetOfTasksParentheses(this);
		}
	}

	public final SetOfTasksParenthesesContext setOfTasksParentheses() throws RecognitionException {
		SetOfTasksParenthesesContext _localctx = new SetOfTasksParenthesesContext(_ctx, getState());
		enterRule(_localctx, 116, RULE_setOfTasksParentheses);
		try {
			setState(555);
			switch ( getInterpreter().adaptivePredict(_input,44,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(523); match(LP);
				setState(524); varName();
				setState(525); match(RP);
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(527); match(LP);
				setState(528); universe();
				setState(529); match(RP);
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(531); match(LP);
				setState(532); setOfTasksLiteral();
				setState(533); match(RP);
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(535); match(LP);
				setState(536); setOfTasksConstruction();
				setState(537); match(RP);
				}
				break;

			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(539); match(LP);
				setState(540); union();
				setState(541); match(RP);
				}
				break;

			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(543); match(LP);
				setState(544); difference();
				setState(545); match(RP);
				}
				break;

			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(547); match(LP);
				setState(548); intersection();
				setState(549); match(RP);
				}
				break;

			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(551); match(LP);
				setState(552); setOfTasksParentheses();
				setState(553); match(RP);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IdenticalContext extends ParserRuleContext {
		public TerminalNode EQUALS() { return getToken(PQLParser.EQUALS, 0); }
		public IdenticalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identical; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).enterIdentical(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).exitIdentical(this);
		}
	}

	public final IdenticalContext identical() throws RecognitionException {
		IdenticalContext _localctx = new IdenticalContext(_ctx, getState());
		enterRule(_localctx, 118, RULE_identical);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(557); match(EQUALS);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DifferentContext extends ParserRuleContext {
		public TerminalNode NOT() { return getToken(PQLParser.NOT, 0); }
		public TerminalNode EQUALS() { return getToken(PQLParser.EQUALS, 0); }
		public DifferentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_different; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).enterDifferent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).exitDifferent(this);
		}
	}

	public final DifferentContext different() throws RecognitionException {
		DifferentContext _localctx = new DifferentContext(_ctx, getState());
		enterRule(_localctx, 120, RULE_different);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(559); match(NOT);
			setState(560); match(EQUALS);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OverlapsWithContext extends ParserRuleContext {
		public TerminalNode WITH() { return getToken(PQLParser.WITH, 0); }
		public TerminalNode OVERLAPS() { return getToken(PQLParser.OVERLAPS, 0); }
		public OverlapsWithContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_overlapsWith; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).enterOverlapsWith(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).exitOverlapsWith(this);
		}
	}

	public final OverlapsWithContext overlapsWith() throws RecognitionException {
		OverlapsWithContext _localctx = new OverlapsWithContext(_ctx, getState());
		enterRule(_localctx, 122, RULE_overlapsWith);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(562); match(OVERLAPS);
			setState(563); match(WITH);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SubsetOfContext extends ParserRuleContext {
		public TerminalNode IS() { return getToken(PQLParser.IS, 0); }
		public TerminalNode OF() { return getToken(PQLParser.OF, 0); }
		public TerminalNode SUBSET() { return getToken(PQLParser.SUBSET, 0); }
		public SubsetOfContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_subsetOf; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).enterSubsetOf(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).exitSubsetOf(this);
		}
	}

	public final SubsetOfContext subsetOf() throws RecognitionException {
		SubsetOfContext _localctx = new SubsetOfContext(_ctx, getState());
		enterRule(_localctx, 124, RULE_subsetOf);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(565); match(IS);
			setState(566); match(SUBSET);
			setState(567); match(OF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ProperSubsetOfContext extends ParserRuleContext {
		public TerminalNode IS() { return getToken(PQLParser.IS, 0); }
		public TerminalNode OF() { return getToken(PQLParser.OF, 0); }
		public TerminalNode PROPER() { return getToken(PQLParser.PROPER, 0); }
		public TerminalNode SUBSET() { return getToken(PQLParser.SUBSET, 0); }
		public ProperSubsetOfContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_properSubsetOf; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).enterProperSubsetOf(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).exitProperSubsetOf(this);
		}
	}

	public final ProperSubsetOfContext properSubsetOf() throws RecognitionException {
		ProperSubsetOfContext _localctx = new ProperSubsetOfContext(_ctx, getState());
		enterRule(_localctx, 126, RULE_properSubsetOf);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(569); match(IS);
			setState(570); match(PROPER);
			setState(571); match(SUBSET);
			setState(572); match(OF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\3;\u0241\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\3\2\3\2\5\2\u0085\n\2\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\5\3\u008e\n\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4\u0099\n\4\3"+
		"\4\3\4\3\5\7\5\u009e\n\5\f\5\16\5\u00a1\13\5\3\6\3\6\3\6\3\6\3\6\3\7\3"+
		"\7\3\b\3\b\3\b\7\b\u00ad\n\b\f\b\16\b\u00b0\13\b\3\t\3\t\5\t\u00b4\n\t"+
		"\3\n\3\n\3\n\3\n\7\n\u00ba\n\n\f\n\16\n\u00bd\13\n\5\n\u00bf\n\n\3\13"+
		"\3\13\5\13\u00c3\n\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\17\5\17\u00ce"+
		"\n\17\3\17\3\17\3\20\3\20\3\20\3\20\5\20\u00d6\n\20\3\21\3\21\3\21\3\21"+
		"\3\21\5\21\u00dd\n\21\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\7\23\u00e7"+
		"\n\23\f\23\16\23\u00ea\13\23\5\23\u00ec\n\23\3\23\3\23\3\24\3\24\3\24"+
		"\3\24\7\24\u00f4\n\24\f\24\16\24\u00f7\13\24\5\24\u00f9\n\24\3\24\3\24"+
		"\3\25\3\25\5\25\u00ff\n\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\5\26"+
		"\u0109\n\26\5\26\u010b\n\26\3\27\3\27\3\30\3\30\3\31\3\31\3\32\3\32\5"+
		"\32\u0115\n\32\3\33\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\34"+
		"\3\34\3\34\3\34\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3\37\3 \3 \3!\3!\3"+
		"!\3!\5!\u0133\n!\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\5\"\u013e\n\"\3#"+
		"\3#\3#\3#\3#\3$\3$\3$\3$\3$\3$\3$\3%\3%\3%\3%\3%\3&\3&\3&\3&\3&\3&\3&"+
		"\3\'\3\'\5\'\u015a\n\'\3(\3(\3(\3(\3(\3(\3(\3(\3(\3)\3)\3)\3)\3)\3)\3"+
		")\3)\3)\3*\3*\3+\3+\5+\u0172\n+\3,\3,\3,\3,\3-\3-\3-\3-\3.\3.\3.\3.\3"+
		".\5.\u0181\n.\3/\3/\3\60\3\60\3\60\3\60\5\60\u0189\n\60\3\61\3\61\3\61"+
		"\5\61\u018e\n\61\3\61\3\61\3\61\3\61\5\61\u0194\n\61\3\61\3\61\3\61\3"+
		"\61\5\61\u019a\n\61\7\61\u019c\n\61\f\61\16\61\u019f\13\61\3\62\3\62\5"+
		"\62\u01a3\n\62\3\62\3\62\3\62\5\62\u01a8\n\62\3\62\3\62\3\62\5\62\u01ad"+
		"\n\62\7\62\u01af\n\62\f\62\16\62\u01b2\13\62\3\63\3\63\3\63\3\63\3\63"+
		"\3\63\3\63\3\63\5\63\u01bc\n\63\3\64\3\64\3\64\3\65\3\65\3\65\3\65\3\66"+
		"\3\66\3\66\3\66\3\66\3\67\3\67\3\67\3\67\38\38\38\38\38\39\39\39\59\u01d6"+
		"\n9\39\39\39\39\59\u01dc\n9\39\39\39\39\59\u01e2\n9\79\u01e4\n9\f9\16"+
		"9\u01e7\139\3:\3:\5:\u01eb\n:\3:\3:\3:\5:\u01f0\n:\3:\3:\3:\5:\u01f5\n"+
		":\7:\u01f7\n:\f:\16:\u01fa\13:\3;\3;\3;\3;\3;\3;\3;\3;\3;\3;\3;\3;\3;"+
		"\3;\3;\3;\5;\u020c\n;\3<\3<\3<\3<\3<\3<\3<\3<\3<\3<\3<\3<\3<\3<\3<\3<"+
		"\3<\3<\3<\3<\3<\3<\3<\3<\3<\3<\3<\3<\3<\3<\3<\3<\5<\u022e\n<\3=\3=\3>"+
		"\3>\3>\3?\3?\3?\3@\3@\3@\3@\3A\3A\3A\3A\3A\3A\2B\2\4\6\b\n\f\16\20\22"+
		"\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@BDFHJLNPRTVXZ\\^`bdfhjlnp"+
		"rtvxz|~\u0080\2\7\4\2\'\'**\3\2\63\64\3\2\66;\3\2\'*\3\2./\u024e\2\u0084"+
		"\3\2\2\2\4\u0086\3\2\2\2\6\u0091\3\2\2\2\b\u009f\3\2\2\2\n\u00a2\3\2\2"+
		"\2\f\u00a7\3\2\2\2\16\u00a9\3\2\2\2\20\u00b3\3\2\2\2\22\u00be\3\2\2\2"+
		"\24\u00c2\3\2\2\2\26\u00c4\3\2\2\2\30\u00c6\3\2\2\2\32\u00c8\3\2\2\2\34"+
		"\u00ca\3\2\2\2\36\u00d5\3\2\2\2 \u00dc\3\2\2\2\"\u00de\3\2\2\2$\u00e2"+
		"\3\2\2\2&\u00ef\3\2\2\2(\u00fe\3\2\2\2*\u010a\3\2\2\2,\u010c\3\2\2\2."+
		"\u010e\3\2\2\2\60\u0110\3\2\2\2\62\u0114\3\2\2\2\64\u0116\3\2\2\2\66\u011c"+
		"\3\2\2\28\u0126\3\2\2\2:\u0128\3\2\2\2<\u012a\3\2\2\2>\u012c\3\2\2\2@"+
		"\u0132\3\2\2\2B\u013d\3\2\2\2D\u013f\3\2\2\2F\u0144\3\2\2\2H\u014b\3\2"+
		"\2\2J\u0150\3\2\2\2L\u0159\3\2\2\2N\u015b\3\2\2\2P\u0164\3\2\2\2R\u016d"+
		"\3\2\2\2T\u0171\3\2\2\2V\u0173\3\2\2\2X\u0177\3\2\2\2Z\u0180\3\2\2\2\\"+
		"\u0182\3\2\2\2^\u0188\3\2\2\2`\u018d\3\2\2\2b\u01a2\3\2\2\2d\u01bb\3\2"+
		"\2\2f\u01bd\3\2\2\2h\u01c0\3\2\2\2j\u01c4\3\2\2\2l\u01c9\3\2\2\2n\u01cd"+
		"\3\2\2\2p\u01d5\3\2\2\2r\u01ea\3\2\2\2t\u020b\3\2\2\2v\u022d\3\2\2\2x"+
		"\u022f\3\2\2\2z\u0231\3\2\2\2|\u0234\3\2\2\2~\u0237\3\2\2\2\u0080\u023b"+
		"\3\2\2\2\u0082\u0085\5\4\3\2\u0083\u0085\5\6\4\2\u0084\u0082\3\2\2\2\u0084"+
		"\u0083\3\2\2\2\u0085\3\3\2\2\2\u0086\u0087\5\b\5\2\u0087\u0088\7\31\2"+
		"\2\u0088\u0089\5\16\b\2\u0089\u008a\7\34\2\2\u008a\u008d\5\22\n\2\u008b"+
		"\u008c\7\35\2\2\u008c\u008e\5@!\2\u008d\u008b\3\2\2\2\u008d\u008e\3\2"+
		"\2\2\u008e\u008f\3\2\2\2\u008f\u0090\7\20\2\2\u0090\5\3\2\2\2\u0091\u0092"+
		"\5\b\5\2\u0092\u0093\7\32\2\2\u0093\u0094\5&\24\2\u0094\u0095\7\33\2\2"+
		"\u0095\u0098\5\22\n\2\u0096\u0097\7\35\2\2\u0097\u0099\5@!\2\u0098\u0096"+
		"\3\2\2\2\u0098\u0099\3\2\2\2\u0099\u009a\3\2\2\2\u009a\u009b\7\20\2\2"+
		"\u009b\7\3\2\2\2\u009c\u009e\5\n\6\2\u009d\u009c\3\2\2\2\u009e\u00a1\3"+
		"\2\2\2\u009f\u009d\3\2\2\2\u009f\u00a0\3\2\2\2\u00a0\t\3\2\2\2\u00a1\u009f"+
		"\3\2\2\2\u00a2\u00a3\5\f\7\2\u00a3\u00a4\7\22\2\2\u00a4\u00a5\5\36\20"+
		"\2\u00a5\u00a6\7\20\2\2\u00a6\13\3\2\2\2\u00a7\u00a8\7\5\2\2\u00a8\r\3"+
		"\2\2\2\u00a9\u00ae\5\20\t\2\u00aa\u00ab\7\21\2\2\u00ab\u00ad\5\20\t\2"+
		"\u00ac\u00aa\3\2\2\2\u00ad\u00b0\3\2\2\2\u00ae\u00ac\3\2\2\2\u00ae\u00af"+
		"\3\2\2\2\u00af\17\3\2\2\2\u00b0\u00ae\3\2\2\2\u00b1\u00b4\5\26\f\2\u00b2"+
		"\u00b4\5\30\r\2\u00b3\u00b1\3\2\2\2\u00b3\u00b2\3\2\2\2\u00b4\21\3\2\2"+
		"\2\u00b5\u00bf\5\26\f\2\u00b6\u00bb\5\24\13\2\u00b7\u00b8\7\21\2\2\u00b8"+
		"\u00ba\5\24\13\2\u00b9\u00b7\3\2\2\2\u00ba\u00bd\3\2\2\2\u00bb\u00b9\3"+
		"\2\2\2\u00bb\u00bc\3\2\2\2\u00bc\u00bf\3\2\2\2\u00bd\u00bb\3\2\2\2\u00be"+
		"\u00b5\3\2\2\2\u00be\u00b6\3\2\2\2\u00bf\23\3\2\2\2\u00c0\u00c3\5\32\16"+
		"\2\u00c1\u00c3\5\34\17\2\u00c2\u00c0\3\2\2\2\u00c2\u00c1\3\2\2\2\u00c3"+
		"\25\3\2\2\2\u00c4\u00c5\7\3\2\2\u00c5\27\3\2\2\2\u00c6\u00c7\7\4\2\2\u00c7"+
		"\31\3\2\2\2\u00c8\u00c9\7\4\2\2\u00c9\33\3\2\2\2\u00ca\u00cd\7\7\2\2\u00cb"+
		"\u00ce\5\4\3\2\u00cc\u00ce\5\6\4\2\u00cd\u00cb\3\2\2\2\u00cd\u00cc\3\2"+
		"\2\2\u00ce\u00cf\3\2\2\2\u00cf\u00d0\7\b\2\2\u00d0\35\3\2\2\2\u00d1\u00d6"+
		"\5 \21\2\u00d2\u00d6\5`\61\2\u00d3\u00d6\5b\62\2\u00d4\u00d6\5d\63\2\u00d5"+
		"\u00d1\3\2\2\2\u00d5\u00d2\3\2\2\2\u00d5\u00d3\3\2\2\2\u00d5\u00d4\3\2"+
		"\2\2\u00d6\37\3\2\2\2\u00d7\u00dd\5\f\7\2\u00d8\u00dd\5\"\22\2\u00d9\u00dd"+
		"\5$\23\2\u00da\u00dd\5\62\32\2\u00db\u00dd\5v<\2\u00dc\u00d7\3\2\2\2\u00dc"+
		"\u00d8\3\2\2\2\u00dc\u00d9\3\2\2\2\u00dc\u00da\3\2\2\2\u00dc\u00db\3\2"+
		"\2\2\u00dd!\3\2\2\2\u00de\u00df\7#\2\2\u00df\u00e0\7\7\2\2\u00e0\u00e1"+
		"\7\b\2\2\u00e1#\3\2\2\2\u00e2\u00eb\7\t\2\2\u00e3\u00e8\5*\26\2\u00e4"+
		"\u00e5\7\21\2\2\u00e5\u00e7\5*\26\2\u00e6\u00e4\3\2\2\2\u00e7\u00ea\3"+
		"\2\2\2\u00e8\u00e6\3\2\2\2\u00e8\u00e9\3\2\2\2\u00e9\u00ec\3\2\2\2\u00ea"+
		"\u00e8\3\2\2\2\u00eb\u00e3\3\2\2\2\u00eb\u00ec\3\2\2\2\u00ec\u00ed\3\2"+
		"\2\2\u00ed\u00ee\7\n\2\2\u00ee%\3\2\2\2\u00ef\u00f8\7\r\2\2\u00f0\u00f5"+
		"\5(\25\2\u00f1\u00f2\7\21\2\2\u00f2\u00f4\5(\25\2\u00f3\u00f1\3\2\2\2"+
		"\u00f4\u00f7\3\2\2\2\u00f5\u00f3\3\2\2\2\u00f5\u00f6\3\2\2\2\u00f6\u00f9"+
		"\3\2\2\2\u00f7\u00f5\3\2\2\2\u00f8\u00f0\3\2\2\2\u00f8\u00f9\3\2\2\2\u00f9"+
		"\u00fa\3\2\2\2\u00fa\u00fb\7\16\2\2\u00fb\'\3\2\2\2\u00fc\u00ff\5\26\f"+
		"\2\u00fd\u00ff\5*\26\2\u00fe\u00fc\3\2\2\2\u00fe\u00fd\3\2\2\2\u00ff)"+
		"\3\2\2\2\u0100\u0101\5,\27\2\u0101\u0102\5.\30\2\u0102\u010b\3\2\2\2\u0103"+
		"\u0108\5.\30\2\u0104\u0105\7\13\2\2\u0105\u0106\5\60\31\2\u0106\u0107"+
		"\7\f\2\2\u0107\u0109\3\2\2\2\u0108\u0104\3\2\2\2\u0108\u0109\3\2\2\2\u0109"+
		"\u010b\3\2\2\2\u010a\u0100\3\2\2\2\u010a\u0103\3\2\2\2\u010b+\3\2\2\2"+
		"\u010c\u010d\7\23\2\2\u010d-\3\2\2\2\u010e\u010f\7\4\2\2\u010f/\3\2\2"+
		"\2\u0110\u0111\7\6\2\2\u0111\61\3\2\2\2\u0112\u0115\5\64\33\2\u0113\u0115"+
		"\5\66\34\2\u0114\u0112\3\2\2\2\u0114\u0113\3\2\2\2\u0115\63\3\2\2\2\u0116"+
		"\u0117\7#\2\2\u0117\u0118\5:\36\2\u0118\u0119\7\7\2\2\u0119\u011a\5\36"+
		"\20\2\u011a\u011b\7\b\2\2\u011b\65\3\2\2\2\u011c\u011d\7#\2\2\u011d\u011e"+
		"\5> \2\u011e\u011f\7\7\2\2\u011f\u0120\5\36\20\2\u0120\u0121\7\21\2\2"+
		"\u0121\u0122\5\36\20\2\u0122\u0123\7\21\2\2\u0123\u0124\58\35\2\u0124"+
		"\u0125\7\b\2\2\u0125\67\3\2\2\2\u0126\u0127\t\2\2\2\u01279\3\2\2\2\u0128"+
		"\u0129\t\3\2\2\u0129;\3\2\2\2\u012a\u012b\7\65\2\2\u012b=\3\2\2\2\u012c"+
		"\u012d\t\4\2\2\u012d?\3\2\2\2\u012e\u0133\5B\"\2\u012f\u0133\5r:\2\u0130"+
		"\u0133\5p9\2\u0131\u0133\5^\60\2\u0132\u012e\3\2\2\2\u0132\u012f\3\2\2"+
		"\2\u0132\u0130\3\2\2\2\u0132\u0131\3\2\2\2\u0133A\3\2\2\2\u0134\u013e"+
		"\5D#\2\u0135\u013e\5F$\2\u0136\u013e\5H%\2\u0137\u013e\5J&\2\u0138\u013e"+
		"\5L\'\2\u0139\u013e\5T+\2\u013a\u013e\5\\/\2\u013b\u013e\5t;\2\u013c\u013e"+
		"\5f\64\2\u013d\u0134\3\2\2\2\u013d\u0135\3\2\2\2\u013d\u0136\3\2\2\2\u013d"+
		"\u0137\3\2\2\2\u013d\u0138\3\2\2\2\u013d\u0139\3\2\2\2\u013d\u013a\3\2"+
		"\2\2\u013d\u013b\3\2\2\2\u013d\u013c\3\2\2\2\u013eC\3\2\2\2\u013f\u0140"+
		"\5:\36\2\u0140\u0141\7\7\2\2\u0141\u0142\5*\26\2\u0142\u0143\7\b\2\2\u0143"+
		"E\3\2\2\2\u0144\u0145\5> \2\u0145\u0146\7\7\2\2\u0146\u0147\5*\26\2\u0147"+
		"\u0148\7\21\2\2\u0148\u0149\5*\26\2\u0149\u014a\7\b\2\2\u014aG\3\2\2\2"+
		"\u014b\u014c\5<\37\2\u014c\u014d\7\7\2\2\u014d\u014e\5&\24\2\u014e\u014f"+
		"\7\b\2\2\u014fI\3\2\2\2\u0150\u0151\5:\36\2\u0151\u0152\7\7\2\2\u0152"+
		"\u0153\5\36\20\2\u0153\u0154\7\21\2\2\u0154\u0155\58\35\2\u0155\u0156"+
		"\7\b\2\2\u0156K\3\2\2\2\u0157\u015a\5N(\2\u0158\u015a\5P)\2\u0159\u0157"+
		"\3\2\2\2\u0159\u0158\3\2\2\2\u015aM\3\2\2\2\u015b\u015c\5> \2\u015c\u015d"+
		"\7\7\2\2\u015d\u015e\5*\26\2\u015e\u015f\7\21\2\2\u015f\u0160\5\36\20"+
		"\2\u0160\u0161\7\21\2\2\u0161\u0162\58\35\2\u0162\u0163\7\b\2\2\u0163"+
		"O\3\2\2\2\u0164\u0165\5> \2\u0165\u0166\7\7\2\2\u0166\u0167\5\36\20\2"+
		"\u0167\u0168\7\21\2\2\u0168\u0169\5\36\20\2\u0169\u016a\7\21\2\2\u016a"+
		"\u016b\5R*\2\u016b\u016c\7\b\2\2\u016cQ\3\2\2\2\u016d\u016e\t\5\2\2\u016e"+
		"S\3\2\2\2\u016f\u0172\5V,\2\u0170\u0172\5X-\2\u0171\u016f\3\2\2\2\u0171"+
		"\u0170\3\2\2\2\u0172U\3\2\2\2\u0173\u0174\5*\26\2\u0174\u0175\7+\2\2\u0175"+
		"\u0176\5\36\20\2\u0176W\3\2\2\2\u0177\u0178\5\36\20\2\u0178\u0179\5Z."+
		"\2\u0179\u017a\5\36\20\2\u017aY\3\2\2\2\u017b\u0181\5x=\2\u017c\u0181"+
		"\5z>\2\u017d\u0181\5|?\2\u017e\u0181\5~@\2\u017f\u0181\5\u0080A\2\u0180"+
		"\u017b\3\2\2\2\u0180\u017c\3\2\2\2\u0180\u017d\3\2\2\2\u0180\u017e\3\2"+
		"\2\2\u0180\u017f\3\2\2\2\u0181[\3\2\2\2\u0182\u0183\t\6\2\2\u0183]\3\2"+
		"\2\2\u0184\u0189\5h\65\2\u0185\u0189\5j\66\2\u0186\u0189\5l\67\2\u0187"+
		"\u0189\5n8\2\u0188\u0184\3\2\2\2\u0188\u0185\3\2\2\2\u0188\u0186\3\2\2"+
		"\2\u0188\u0187\3\2\2\2\u0189_\3\2\2\2\u018a\u018e\5 \21\2\u018b\u018e"+
		"\5d\63\2\u018c\u018e\5b\62\2\u018d\u018a\3\2\2\2\u018d\u018b\3\2\2\2\u018d"+
		"\u018c\3\2\2\2\u018e\u018f\3\2\2\2\u018f\u0193\7\60\2\2\u0190\u0194\5"+
		" \21\2\u0191\u0194\5d\63\2\u0192\u0194\5b\62\2\u0193\u0190\3\2\2\2\u0193"+
		"\u0191\3\2\2\2\u0193\u0192\3\2\2\2\u0194\u019d\3\2\2\2\u0195\u0199\7\60"+
		"\2\2\u0196\u019a\5 \21\2\u0197\u019a\5d\63\2\u0198\u019a\5b\62\2\u0199"+
		"\u0196\3\2\2\2\u0199\u0197\3\2\2\2\u0199\u0198\3\2\2\2\u019a\u019c\3\2"+
		"\2\2\u019b\u0195\3\2\2\2\u019c\u019f\3\2\2\2\u019d\u019b\3\2\2\2\u019d"+
		"\u019e\3\2\2\2\u019ea\3\2\2\2\u019f\u019d\3\2\2\2\u01a0\u01a3\5 \21\2"+
		"\u01a1\u01a3\5d\63\2\u01a2\u01a0\3\2\2\2\u01a2\u01a1\3\2\2\2\u01a3\u01a4"+
		"\3\2\2\2\u01a4\u01a7\7\61\2\2\u01a5\u01a8\5 \21\2\u01a6\u01a8\5d\63\2"+
		"\u01a7\u01a5\3\2\2\2\u01a7\u01a6\3\2\2\2\u01a8\u01b0\3\2\2\2\u01a9\u01ac"+
		"\7\61\2\2\u01aa\u01ad\5 \21\2\u01ab\u01ad\5d\63\2\u01ac\u01aa\3\2\2\2"+
		"\u01ac\u01ab\3\2\2\2\u01ad\u01af\3\2\2\2\u01ae\u01a9\3\2\2\2\u01af\u01b2"+
		"\3\2\2\2\u01b0\u01ae\3\2\2\2\u01b0\u01b1\3\2\2\2\u01b1c\3\2\2\2\u01b2"+
		"\u01b0\3\2\2\2\u01b3\u01b4\5 \21\2\u01b4\u01b5\7\62\2\2\u01b5\u01b6\5"+
		" \21\2\u01b6\u01bc\3\2\2\2\u01b7\u01b8\5 \21\2\u01b8\u01b9\7\62\2\2\u01b9"+
		"\u01ba\5d\63\2\u01ba\u01bc\3\2\2\2\u01bb\u01b3\3\2\2\2\u01bb\u01b7\3\2"+
		"\2\2\u01bce\3\2\2\2\u01bd\u01be\7$\2\2\u01be\u01bf\5B\"\2\u01bfg\3\2\2"+
		"\2\u01c0\u01c1\5B\"\2\u01c1\u01c2\7,\2\2\u01c2\u01c3\7.\2\2\u01c3i\3\2"+
		"\2\2\u01c4\u01c5\5B\"\2\u01c5\u01c6\7,\2\2\u01c6\u01c7\7$\2\2\u01c7\u01c8"+
		"\7.\2\2\u01c8k\3\2\2\2\u01c9\u01ca\5B\"\2\u01ca\u01cb\7,\2\2\u01cb\u01cc"+
		"\7/\2\2\u01ccm\3\2\2\2\u01cd\u01ce\5B\"\2\u01ce\u01cf\7,\2\2\u01cf\u01d0"+
		"\7$\2\2\u01d0\u01d1\7/\2\2\u01d1o\3\2\2\2\u01d2\u01d6\5B\"\2\u01d3\u01d6"+
		"\5^\60\2\u01d4\u01d6\5r:\2\u01d5\u01d2\3\2\2\2\u01d5\u01d3\3\2\2\2\u01d5"+
		"\u01d4\3\2\2\2\u01d6\u01d7\3\2\2\2\u01d7\u01db\7&\2\2\u01d8\u01dc\5B\""+
		"\2\u01d9\u01dc\5^\60\2\u01da\u01dc\5r:\2\u01db\u01d8\3\2\2\2\u01db\u01d9"+
		"\3\2\2\2\u01db\u01da\3\2\2\2\u01dc\u01e5\3\2\2\2\u01dd\u01e1\7&\2\2\u01de"+
		"\u01e2\5B\"\2\u01df\u01e2\5^\60\2\u01e0\u01e2\5r:\2\u01e1\u01de\3\2\2"+
		"\2\u01e1\u01df\3\2\2\2\u01e1\u01e0\3\2\2\2\u01e2\u01e4\3\2\2\2\u01e3\u01dd"+
		"\3\2\2\2\u01e4\u01e7\3\2\2\2\u01e5\u01e3\3\2\2\2\u01e5\u01e6\3\2\2\2\u01e6"+
		"q\3\2\2\2\u01e7\u01e5\3\2\2\2\u01e8\u01eb\5B\"\2\u01e9\u01eb\5^\60\2\u01ea"+
		"\u01e8\3\2\2\2\u01ea\u01e9\3\2\2\2\u01eb\u01ec\3\2\2\2\u01ec\u01ef\7%"+
		"\2\2\u01ed\u01f0\5B\"\2\u01ee\u01f0\5^\60\2\u01ef\u01ed\3\2\2\2\u01ef"+
		"\u01ee\3\2\2\2\u01f0\u01f8\3\2\2\2\u01f1\u01f4\7%\2\2\u01f2\u01f5\5B\""+
		"\2\u01f3\u01f5\5^\60\2\u01f4\u01f2\3\2\2\2\u01f4\u01f3\3\2\2\2\u01f5\u01f7"+
		"\3\2\2\2\u01f6\u01f1\3\2\2\2\u01f7\u01fa\3\2\2\2\u01f8\u01f6\3\2\2\2\u01f8"+
		"\u01f9\3\2\2\2\u01f9s\3\2\2\2\u01fa\u01f8\3\2\2\2\u01fb\u01fc\7\7\2\2"+
		"\u01fc\u01fd\5B\"\2\u01fd\u01fe\7\b\2\2\u01fe\u020c\3\2\2\2\u01ff\u0200"+
		"\7\7\2\2\u0200\u0201\5r:\2\u0201\u0202\7\b\2\2\u0202\u020c\3\2\2\2\u0203"+
		"\u0204\7\7\2\2\u0204\u0205\5p9\2\u0205\u0206\7\b\2\2\u0206\u020c\3\2\2"+
		"\2\u0207\u0208\7\7\2\2\u0208\u0209\5^\60\2\u0209\u020a\7\b\2\2\u020a\u020c"+
		"\3\2\2\2\u020b\u01fb\3\2\2\2\u020b\u01ff\3\2\2\2\u020b\u0203\3\2\2\2\u020b"+
		"\u0207\3\2\2\2\u020cu\3\2\2\2\u020d\u020e\7\7\2\2\u020e\u020f\5\f\7\2"+
		"\u020f\u0210\7\b\2\2\u0210\u022e\3\2\2\2\u0211\u0212\7\7\2\2\u0212\u0213"+
		"\5\26\f\2\u0213\u0214\7\b\2\2\u0214\u022e\3\2\2\2\u0215\u0216\7\7\2\2"+
		"\u0216\u0217\5$\23\2\u0217\u0218\7\b\2\2\u0218\u022e\3\2\2\2\u0219\u021a"+
		"\7\7\2\2\u021a\u021b\5\62\32\2\u021b\u021c\7\b\2\2\u021c\u022e\3\2\2\2"+
		"\u021d\u021e\7\7\2\2\u021e\u021f\5`\61\2\u021f\u0220\7\b\2\2\u0220\u022e"+
		"\3\2\2\2\u0221\u0222\7\7\2\2\u0222\u0223\5d\63\2\u0223\u0224\7\b\2\2\u0224"+
		"\u022e\3\2\2\2\u0225\u0226\7\7\2\2\u0226\u0227\5b\62\2\u0227\u0228\7\b"+
		"\2\2\u0228\u022e\3\2\2\2\u0229\u022a\7\7\2\2\u022a\u022b\5v<\2\u022b\u022c"+
		"\7\b\2\2\u022c\u022e\3\2\2\2\u022d\u020d\3\2\2\2\u022d\u0211\3\2\2\2\u022d"+
		"\u0215\3\2\2\2\u022d\u0219\3\2\2\2\u022d\u021d\3\2\2\2\u022d\u0221\3\2"+
		"\2\2\u022d\u0225\3\2\2\2\u022d\u0229\3\2\2\2\u022ew\3\2\2\2\u022f\u0230"+
		"\7\36\2\2\u0230y\3\2\2\2\u0231\u0232\7$\2\2\u0232\u0233\7\36\2\2\u0233"+
		"{\3\2\2\2\u0234\u0235\7\37\2\2\u0235\u0236\7 \2\2\u0236}\3\2\2\2\u0237"+
		"\u0238\7,\2\2\u0238\u0239\7!\2\2\u0239\u023a\7-\2\2\u023a\177\3\2\2\2"+
		"\u023b\u023c\7,\2\2\u023c\u023d\7\"\2\2\u023d\u023e\7!\2\2\u023e\u023f"+
		"\7-\2\2\u023f\u0081\3\2\2\2/\u0084\u008d\u0098\u009f\u00ae\u00b3\u00bb"+
		"\u00be\u00c2\u00cd\u00d5\u00dc\u00e8\u00eb\u00f5\u00f8\u00fe\u0108\u010a"+
		"\u0114\u0132\u013d\u0159\u0171\u0180\u0188\u018d\u0193\u0199\u019d\u01a2"+
		"\u01a7\u01ac\u01b0\u01bb\u01d5\u01db\u01e1\u01e5\u01ea\u01ef\u01f4\u01f8"+
		"\u020b\u022d";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}