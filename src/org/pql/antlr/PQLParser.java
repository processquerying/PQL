// Generated from PQL.g4 by ANTLR 4.1
package org.pql.antlr;
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
		public TerminalNode EOS() { return getToken(PQLParser.EOS, 0); }
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
			enterOuterAlt(_localctx, 1);
			{
			setState(130);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				{
				setState(128); selectQuery();
				}
				break;

			case 2:
				{
				setState(129); insertQuery();
				}
				break;
			}
			setState(132); match(EOS);
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
			setState(134); variables();
			setState(135); match(SELECT);
			setState(136); attributes();
			setState(137); match(FROM);
			setState(138); locations();
			setState(141);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(139); match(WHERE);
				setState(140); predicate();
				}
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
			enterOuterAlt(_localctx, 1);
			{
			setState(179); location();
			setState(184);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SEP) {
				{
				{
				setState(180); match(SEP);
				setState(181); location();
				}
				}
				setState(186);
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

	public static class LocationContext extends ParserRuleContext {
		public NestedQueryContext nestedQuery() {
			return getRuleContext(NestedQueryContext.class,0);
		}
		public LocationPathContext locationPath() {
			return getRuleContext(LocationPathContext.class,0);
		}
		public UniverseContext universe() {
			return getRuleContext(UniverseContext.class,0);
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
			setState(190);
			switch (_input.LA(1)) {
			case UNIVERSE:
				enterOuterAlt(_localctx, 1);
				{
				setState(187); universe();
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 2);
				{
				setState(188); locationPath();
				}
				break;
			case LP:
				enterOuterAlt(_localctx, 3);
				{
				setState(189); nestedQuery();
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
			setState(192); match(UNIVERSE);
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
			setState(194); match(STRING);
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
			setState(198); match(LP);
			setState(201);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				{
				setState(199); selectQuery();
				}
				break;

			case 2:
				{
				setState(200); insertQuery();
				}
				break;
			}
			setState(203); match(RP);
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
			setState(209);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(205); tasks();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(206); union();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(207); intersection();
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(208); difference();
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
			setState(216);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(211); varName();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(212); setOfAllTasks();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(213); setOfTasksLiteral();
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(214); setOfTasksConstruction();
				}
				break;

			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(215); setOfTasksParentheses();
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
			setState(218); match(GET_TASKS);
			setState(219); match(LP);
			setState(220); match(RP);
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
			setState(222); match(LB);
			setState(231);
			_la = _input.LA(1);
			if (_la==STRING || _la==TILDE) {
				{
				setState(223); task();
				setState(228);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SEP) {
					{
					{
					setState(224); match(SEP);
					setState(225); task();
					}
					}
					setState(230);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(233); match(RB);
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
			setState(235); match(LTB);
			setState(244);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << UNIVERSE) | (1L << STRING) | (1L << TILDE))) != 0)) {
				{
				setState(236); event();
				setState(241);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SEP) {
					{
					{
					setState(237); match(SEP);
					setState(238); event();
					}
					}
					setState(243);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(246); match(RTB);
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
			setState(250);
			switch (_input.LA(1)) {
			case UNIVERSE:
				enterOuterAlt(_localctx, 1);
				{
				setState(248); universe();
				}
				break;
			case STRING:
			case TILDE:
				enterOuterAlt(_localctx, 2);
				{
				setState(249); task();
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
			setState(262);
			switch (_input.LA(1)) {
			case TILDE:
				enterOuterAlt(_localctx, 1);
				{
				setState(252); approximate();
				setState(253); label();
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 2);
				{
				setState(255); label();
				setState(260);
				_la = _input.LA(1);
				if (_la==LSB) {
					{
					setState(256); match(LSB);
					setState(257); similarity();
					setState(258); match(RSB);
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
			setState(264); match(TILDE);
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
			setState(266); match(STRING);
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
			setState(268); match(SIMILARITY);
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
			setState(272);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(270); unaryPredicateConstruction();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(271); binaryPredicateConstruction();
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
			setState(274); match(GET_TASKS);
			}
			setState(275); unaryPredicateName();
			setState(276); match(LP);
			setState(277); setOfTasks();
			setState(278); match(RP);
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
			setState(280); match(GET_TASKS);
			}
			setState(281); binaryPredicateName();
			setState(282); match(LP);
			setState(283); setOfTasks();
			setState(284); match(SEP);
			setState(285); setOfTasks();
			setState(286); match(SEP);
			setState(287); anyAll();
			setState(288); match(RP);
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
			setState(290);
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
			setState(292);
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
			setState(294); match(EXECUTES);
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
			setState(296);
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
			setState(302);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(298); proposition();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(299); conjunction();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(300); disjunction();
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(301); logicalTest();
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
			setState(313);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(304); unaryPredicate();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(305); binaryPredicate();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(306); unaryTracePredicate();
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(307); unaryPredicateMacro();
				}
				break;

			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(308); binaryPredicateMacro();
				}
				break;

			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(309); setPredicate();
				}
				break;

			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(310); truthValue();
				}
				break;

			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(311); parentheses();
				}
				break;

			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(312); negation();
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
			setState(315); unaryPredicateName();
			setState(316); match(LP);
			setState(317); task();
			setState(318); match(RP);
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
			setState(320); binaryPredicateName();
			setState(321); match(LP);
			setState(322); task();
			setState(323); match(SEP);
			setState(324); task();
			setState(325); match(RP);
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
			setState(327); unaryTracePredicateName();
			setState(328); match(LP);
			setState(329); trace();
			setState(330); match(RP);
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
			setState(332); unaryPredicateName();
			setState(333); match(LP);
			setState(334); setOfTasks();
			setState(335); match(SEP);
			setState(336); anyAll();
			setState(337); match(RP);
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
			setState(341);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(339); binaryPredicateMacroTaskSet();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(340); binaryPredicateMacroSetSet();
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
			setState(343); binaryPredicateName();
			setState(344); match(LP);
			setState(345); task();
			setState(346); match(SEP);
			setState(347); setOfTasks();
			setState(348); match(SEP);
			setState(349); anyAll();
			setState(350); match(RP);
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
			setState(352); binaryPredicateName();
			setState(353); match(LP);
			setState(354); setOfTasks();
			setState(355); match(SEP);
			setState(356); setOfTasks();
			setState(357); match(SEP);
			setState(358); anySomeEachAll();
			setState(359); match(RP);
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
			setState(361);
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
			setState(365);
			switch (_input.LA(1)) {
			case STRING:
			case TILDE:
				enterOuterAlt(_localctx, 1);
				{
				setState(363); taskInSetOfTasks();
				}
				break;
			case VARIABLE_NAME:
			case LP:
			case LB:
			case GET_TASKS:
				enterOuterAlt(_localctx, 2);
				{
				setState(364); setComparison();
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
			setState(367); task();
			setState(368); match(IN);
			setState(369); setOfTasks();
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
			setState(371); setOfTasks();
			setState(372); setComparisonOperator();
			setState(373); setOfTasks();
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
			setState(380);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(375); identical();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(376); different();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(377); overlapsWith();
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(378); subsetOf();
				}
				break;

			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(379); properSubsetOf();
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
			setState(382);
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
			setState(388);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(384); isTrue();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(385); isNotTrue();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(386); isFalse();
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(387); isNotFalse();
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
			setState(393);
			switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
			case 1:
				{
				setState(390); tasks();
				}
				break;

			case 2:
				{
				setState(391); difference();
				}
				break;

			case 3:
				{
				setState(392); intersection();
				}
				break;
			}
			setState(395); match(UNION);
			setState(399);
			switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
			case 1:
				{
				setState(396); tasks();
				}
				break;

			case 2:
				{
				setState(397); difference();
				}
				break;

			case 3:
				{
				setState(398); intersection();
				}
				break;
			}
			setState(409);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==UNION) {
				{
				{
				setState(401); match(UNION);
				setState(405);
				switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
				case 1:
					{
					setState(402); tasks();
					}
					break;

				case 2:
					{
					setState(403); difference();
					}
					break;

				case 3:
					{
					setState(404); intersection();
					}
					break;
				}
				}
				}
				setState(411);
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
			setState(414);
			switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
			case 1:
				{
				setState(412); tasks();
				}
				break;

			case 2:
				{
				setState(413); difference();
				}
				break;
			}
			setState(416); match(INTERSECTION);
			setState(419);
			switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
			case 1:
				{
				setState(417); tasks();
				}
				break;

			case 2:
				{
				setState(418); difference();
				}
				break;
			}
			setState(428);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==INTERSECTION) {
				{
				{
				setState(421); match(INTERSECTION);
				setState(424);
				switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
				case 1:
					{
					setState(422); tasks();
					}
					break;

				case 2:
					{
					setState(423); difference();
					}
					break;
				}
				}
				}
				setState(430);
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
			setState(439);
			switch ( getInterpreter().adaptivePredict(_input,33,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(431); tasks();
				setState(432); match(DIFFERENCE);
				setState(433); tasks();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(435); tasks();
				setState(436); match(DIFFERENCE);
				setState(437); difference();
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
			setState(441); match(NOT);
			setState(442); proposition();
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
			setState(444); proposition();
			setState(445); match(IS);
			setState(446); match(TRUE);
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
			setState(448); proposition();
			setState(449); match(IS);
			setState(450); match(NOT);
			setState(451); match(TRUE);
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
			setState(453); proposition();
			setState(454); match(IS);
			setState(455); match(FALSE);
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
			setState(457); proposition();
			setState(458); match(IS);
			setState(459); match(NOT);
			setState(460); match(FALSE);
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
			setState(465);
			switch ( getInterpreter().adaptivePredict(_input,34,_ctx) ) {
			case 1:
				{
				setState(462); proposition();
				}
				break;

			case 2:
				{
				setState(463); logicalTest();
				}
				break;

			case 3:
				{
				setState(464); conjunction();
				}
				break;
			}
			setState(467); match(OR);
			setState(471);
			switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
			case 1:
				{
				setState(468); proposition();
				}
				break;

			case 2:
				{
				setState(469); logicalTest();
				}
				break;

			case 3:
				{
				setState(470); conjunction();
				}
				break;
			}
			setState(481);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR) {
				{
				{
				setState(473); match(OR);
				setState(477);
				switch ( getInterpreter().adaptivePredict(_input,36,_ctx) ) {
				case 1:
					{
					setState(474); proposition();
					}
					break;

				case 2:
					{
					setState(475); logicalTest();
					}
					break;

				case 3:
					{
					setState(476); conjunction();
					}
					break;
				}
				}
				}
				setState(483);
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
			setState(486);
			switch ( getInterpreter().adaptivePredict(_input,38,_ctx) ) {
			case 1:
				{
				setState(484); proposition();
				}
				break;

			case 2:
				{
				setState(485); logicalTest();
				}
				break;
			}
			setState(488); match(AND);
			setState(491);
			switch ( getInterpreter().adaptivePredict(_input,39,_ctx) ) {
			case 1:
				{
				setState(489); proposition();
				}
				break;

			case 2:
				{
				setState(490); logicalTest();
				}
				break;
			}
			setState(500);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AND) {
				{
				{
				setState(493); match(AND);
				setState(496);
				switch ( getInterpreter().adaptivePredict(_input,40,_ctx) ) {
				case 1:
					{
					setState(494); proposition();
					}
					break;

				case 2:
					{
					setState(495); logicalTest();
					}
					break;
				}
				}
				}
				setState(502);
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
			setState(519);
			switch ( getInterpreter().adaptivePredict(_input,42,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(503); match(LP);
				setState(504); proposition();
				setState(505); match(RP);
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(507); match(LP);
				setState(508); conjunction();
				setState(509); match(RP);
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(511); match(LP);
				setState(512); disjunction();
				setState(513); match(RP);
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(515); match(LP);
				setState(516); logicalTest();
				setState(517); match(RP);
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
			setState(553);
			switch ( getInterpreter().adaptivePredict(_input,43,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(521); match(LP);
				setState(522); varName();
				setState(523); match(RP);
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(525); match(LP);
				setState(526); universe();
				setState(527); match(RP);
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(529); match(LP);
				setState(530); setOfTasksLiteral();
				setState(531); match(RP);
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(533); match(LP);
				setState(534); setOfTasksConstruction();
				setState(535); match(RP);
				}
				break;

			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(537); match(LP);
				setState(538); union();
				setState(539); match(RP);
				}
				break;

			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(541); match(LP);
				setState(542); difference();
				setState(543); match(RP);
				}
				break;

			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(545); match(LP);
				setState(546); intersection();
				setState(547); match(RP);
				}
				break;

			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(549); match(LP);
				setState(550); setOfTasksParentheses();
				setState(551); match(RP);
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
			setState(555); match(EQUALS);
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
			setState(557); match(NOT);
			setState(558); match(EQUALS);
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
			setState(560); match(OVERLAPS);
			setState(561); match(WITH);
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
			setState(563); match(IS);
			setState(564); match(SUBSET);
			setState(565); match(OF);
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
			setState(567); match(IS);
			setState(568); match(PROPER);
			setState(569); match(SUBSET);
			setState(570); match(OF);
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
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\3;\u023f\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\3\2\3\2\5\2\u0085\n\2\3\2\3\2\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\5\3\u0090\n\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4\u0099\n\4\3"+
		"\4\3\4\3\5\7\5\u009e\n\5\f\5\16\5\u00a1\13\5\3\6\3\6\3\6\3\6\3\6\3\7\3"+
		"\7\3\b\3\b\3\b\7\b\u00ad\n\b\f\b\16\b\u00b0\13\b\3\t\3\t\5\t\u00b4\n\t"+
		"\3\n\3\n\3\n\7\n\u00b9\n\n\f\n\16\n\u00bc\13\n\3\13\3\13\3\13\5\13\u00c1"+
		"\n\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\17\5\17\u00cc\n\17\3\17\3"+
		"\17\3\20\3\20\3\20\3\20\5\20\u00d4\n\20\3\21\3\21\3\21\3\21\3\21\5\21"+
		"\u00db\n\21\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\7\23\u00e5\n\23\f"+
		"\23\16\23\u00e8\13\23\5\23\u00ea\n\23\3\23\3\23\3\24\3\24\3\24\3\24\7"+
		"\24\u00f2\n\24\f\24\16\24\u00f5\13\24\5\24\u00f7\n\24\3\24\3\24\3\25\3"+
		"\25\5\25\u00fd\n\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\5\26\u0107"+
		"\n\26\5\26\u0109\n\26\3\27\3\27\3\30\3\30\3\31\3\31\3\32\3\32\5\32\u0113"+
		"\n\32\3\33\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\34\3\34\3\34"+
		"\3\34\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3\37\3 \3 \3!\3!\3!\3!\5!\u0131"+
		"\n!\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\5\"\u013c\n\"\3#\3#\3#\3#\3#\3"+
		"$\3$\3$\3$\3$\3$\3$\3%\3%\3%\3%\3%\3&\3&\3&\3&\3&\3&\3&\3\'\3\'\5\'\u0158"+
		"\n\'\3(\3(\3(\3(\3(\3(\3(\3(\3(\3)\3)\3)\3)\3)\3)\3)\3)\3)\3*\3*\3+\3"+
		"+\5+\u0170\n+\3,\3,\3,\3,\3-\3-\3-\3-\3.\3.\3.\3.\3.\5.\u017f\n.\3/\3"+
		"/\3\60\3\60\3\60\3\60\5\60\u0187\n\60\3\61\3\61\3\61\5\61\u018c\n\61\3"+
		"\61\3\61\3\61\3\61\5\61\u0192\n\61\3\61\3\61\3\61\3\61\5\61\u0198\n\61"+
		"\7\61\u019a\n\61\f\61\16\61\u019d\13\61\3\62\3\62\5\62\u01a1\n\62\3\62"+
		"\3\62\3\62\5\62\u01a6\n\62\3\62\3\62\3\62\5\62\u01ab\n\62\7\62\u01ad\n"+
		"\62\f\62\16\62\u01b0\13\62\3\63\3\63\3\63\3\63\3\63\3\63\3\63\3\63\5\63"+
		"\u01ba\n\63\3\64\3\64\3\64\3\65\3\65\3\65\3\65\3\66\3\66\3\66\3\66\3\66"+
		"\3\67\3\67\3\67\3\67\38\38\38\38\38\39\39\39\59\u01d4\n9\39\39\39\39\5"+
		"9\u01da\n9\39\39\39\39\59\u01e0\n9\79\u01e2\n9\f9\169\u01e5\139\3:\3:"+
		"\5:\u01e9\n:\3:\3:\3:\5:\u01ee\n:\3:\3:\3:\5:\u01f3\n:\7:\u01f5\n:\f:"+
		"\16:\u01f8\13:\3;\3;\3;\3;\3;\3;\3;\3;\3;\3;\3;\3;\3;\3;\3;\3;\5;\u020a"+
		"\n;\3<\3<\3<\3<\3<\3<\3<\3<\3<\3<\3<\3<\3<\3<\3<\3<\3<\3<\3<\3<\3<\3<"+
		"\3<\3<\3<\3<\3<\3<\3<\3<\3<\3<\5<\u022c\n<\3=\3=\3>\3>\3>\3?\3?\3?\3@"+
		"\3@\3@\3@\3A\3A\3A\3A\3A\3A\2B\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36"+
		" \"$&(*,.\60\62\64\668:<>@BDFHJLNPRTVXZ\\^`bdfhjlnprtvxz|~\u0080\2\7\4"+
		"\2\'\'**\3\2\63\64\3\2\66;\3\2\'*\3\2./\u024c\2\u0084\3\2\2\2\4\u0088"+
		"\3\2\2\2\6\u0091\3\2\2\2\b\u009f\3\2\2\2\n\u00a2\3\2\2\2\f\u00a7\3\2\2"+
		"\2\16\u00a9\3\2\2\2\20\u00b3\3\2\2\2\22\u00b5\3\2\2\2\24\u00c0\3\2\2\2"+
		"\26\u00c2\3\2\2\2\30\u00c4\3\2\2\2\32\u00c6\3\2\2\2\34\u00c8\3\2\2\2\36"+
		"\u00d3\3\2\2\2 \u00da\3\2\2\2\"\u00dc\3\2\2\2$\u00e0\3\2\2\2&\u00ed\3"+
		"\2\2\2(\u00fc\3\2\2\2*\u0108\3\2\2\2,\u010a\3\2\2\2.\u010c\3\2\2\2\60"+
		"\u010e\3\2\2\2\62\u0112\3\2\2\2\64\u0114\3\2\2\2\66\u011a\3\2\2\28\u0124"+
		"\3\2\2\2:\u0126\3\2\2\2<\u0128\3\2\2\2>\u012a\3\2\2\2@\u0130\3\2\2\2B"+
		"\u013b\3\2\2\2D\u013d\3\2\2\2F\u0142\3\2\2\2H\u0149\3\2\2\2J\u014e\3\2"+
		"\2\2L\u0157\3\2\2\2N\u0159\3\2\2\2P\u0162\3\2\2\2R\u016b\3\2\2\2T\u016f"+
		"\3\2\2\2V\u0171\3\2\2\2X\u0175\3\2\2\2Z\u017e\3\2\2\2\\\u0180\3\2\2\2"+
		"^\u0186\3\2\2\2`\u018b\3\2\2\2b\u01a0\3\2\2\2d\u01b9\3\2\2\2f\u01bb\3"+
		"\2\2\2h\u01be\3\2\2\2j\u01c2\3\2\2\2l\u01c7\3\2\2\2n\u01cb\3\2\2\2p\u01d3"+
		"\3\2\2\2r\u01e8\3\2\2\2t\u0209\3\2\2\2v\u022b\3\2\2\2x\u022d\3\2\2\2z"+
		"\u022f\3\2\2\2|\u0232\3\2\2\2~\u0235\3\2\2\2\u0080\u0239\3\2\2\2\u0082"+
		"\u0085\5\4\3\2\u0083\u0085\5\6\4\2\u0084\u0082\3\2\2\2\u0084\u0083\3\2"+
		"\2\2\u0085\u0086\3\2\2\2\u0086\u0087\7\20\2\2\u0087\3\3\2\2\2\u0088\u0089"+
		"\5\b\5\2\u0089\u008a\7\31\2\2\u008a\u008b\5\16\b\2\u008b\u008c\7\34\2"+
		"\2\u008c\u008f\5\22\n\2\u008d\u008e\7\35\2\2\u008e\u0090\5@!\2\u008f\u008d"+
		"\3\2\2\2\u008f\u0090\3\2\2\2\u0090\5\3\2\2\2\u0091\u0092\5\b\5\2\u0092"+
		"\u0093\7\32\2\2\u0093\u0094\5&\24\2\u0094\u0095\7\33\2\2\u0095\u0098\5"+
		"\22\n\2\u0096\u0097\7\35\2\2\u0097\u0099\5@!\2\u0098\u0096\3\2\2\2\u0098"+
		"\u0099\3\2\2\2\u0099\u009a\3\2\2\2\u009a\u009b\7\20\2\2\u009b\7\3\2\2"+
		"\2\u009c\u009e\5\n\6\2\u009d\u009c\3\2\2\2\u009e\u00a1\3\2\2\2\u009f\u009d"+
		"\3\2\2\2\u009f\u00a0\3\2\2\2\u00a0\t\3\2\2\2\u00a1\u009f\3\2\2\2\u00a2"+
		"\u00a3\5\f\7\2\u00a3\u00a4\7\22\2\2\u00a4\u00a5\5\36\20\2\u00a5\u00a6"+
		"\7\20\2\2\u00a6\13\3\2\2\2\u00a7\u00a8\7\5\2\2\u00a8\r\3\2\2\2\u00a9\u00ae"+
		"\5\20\t\2\u00aa\u00ab\7\21\2\2\u00ab\u00ad\5\20\t\2\u00ac\u00aa\3\2\2"+
		"\2\u00ad\u00b0\3\2\2\2\u00ae\u00ac\3\2\2\2\u00ae\u00af\3\2\2\2\u00af\17"+
		"\3\2\2\2\u00b0\u00ae\3\2\2\2\u00b1\u00b4\5\26\f\2\u00b2\u00b4\5\30\r\2"+
		"\u00b3\u00b1\3\2\2\2\u00b3\u00b2\3\2\2\2\u00b4\21\3\2\2\2\u00b5\u00ba"+
		"\5\24\13\2\u00b6\u00b7\7\21\2\2\u00b7\u00b9\5\24\13\2\u00b8\u00b6\3\2"+
		"\2\2\u00b9\u00bc\3\2\2\2\u00ba\u00b8\3\2\2\2\u00ba\u00bb\3\2\2\2\u00bb"+
		"\23\3\2\2\2\u00bc\u00ba\3\2\2\2\u00bd\u00c1\5\26\f\2\u00be\u00c1\5\32"+
		"\16\2\u00bf\u00c1\5\34\17\2\u00c0\u00bd\3\2\2\2\u00c0\u00be\3\2\2\2\u00c0"+
		"\u00bf\3\2\2\2\u00c1\25\3\2\2\2\u00c2\u00c3\7\3\2\2\u00c3\27\3\2\2\2\u00c4"+
		"\u00c5\7\4\2\2\u00c5\31\3\2\2\2\u00c6\u00c7\7\4\2\2\u00c7\33\3\2\2\2\u00c8"+
		"\u00cb\7\7\2\2\u00c9\u00cc\5\4\3\2\u00ca\u00cc\5\6\4\2\u00cb\u00c9\3\2"+
		"\2\2\u00cb\u00ca\3\2\2\2\u00cc\u00cd\3\2\2\2\u00cd\u00ce\7\b\2\2\u00ce"+
		"\35\3\2\2\2\u00cf\u00d4\5 \21\2\u00d0\u00d4\5`\61\2\u00d1\u00d4\5b\62"+
		"\2\u00d2\u00d4\5d\63\2\u00d3\u00cf\3\2\2\2\u00d3\u00d0\3\2\2\2\u00d3\u00d1"+
		"\3\2\2\2\u00d3\u00d2\3\2\2\2\u00d4\37\3\2\2\2\u00d5\u00db\5\f\7\2\u00d6"+
		"\u00db\5\"\22\2\u00d7\u00db\5$\23\2\u00d8\u00db\5\62\32\2\u00d9\u00db"+
		"\5v<\2\u00da\u00d5\3\2\2\2\u00da\u00d6\3\2\2\2\u00da\u00d7\3\2\2\2\u00da"+
		"\u00d8\3\2\2\2\u00da\u00d9\3\2\2\2\u00db!\3\2\2\2\u00dc\u00dd\7#\2\2\u00dd"+
		"\u00de\7\7\2\2\u00de\u00df\7\b\2\2\u00df#\3\2\2\2\u00e0\u00e9\7\t\2\2"+
		"\u00e1\u00e6\5*\26\2\u00e2\u00e3\7\21\2\2\u00e3\u00e5\5*\26\2\u00e4\u00e2"+
		"\3\2\2\2\u00e5\u00e8\3\2\2\2\u00e6\u00e4\3\2\2\2\u00e6\u00e7\3\2\2\2\u00e7"+
		"\u00ea\3\2\2\2\u00e8\u00e6\3\2\2\2\u00e9\u00e1\3\2\2\2\u00e9\u00ea\3\2"+
		"\2\2\u00ea\u00eb\3\2\2\2\u00eb\u00ec\7\n\2\2\u00ec%\3\2\2\2\u00ed\u00f6"+
		"\7\r\2\2\u00ee\u00f3\5(\25\2\u00ef\u00f0\7\21\2\2\u00f0\u00f2\5(\25\2"+
		"\u00f1\u00ef\3\2\2\2\u00f2\u00f5\3\2\2\2\u00f3\u00f1\3\2\2\2\u00f3\u00f4"+
		"\3\2\2\2\u00f4\u00f7\3\2\2\2\u00f5\u00f3\3\2\2\2\u00f6\u00ee\3\2\2\2\u00f6"+
		"\u00f7\3\2\2\2\u00f7\u00f8\3\2\2\2\u00f8\u00f9\7\16\2\2\u00f9\'\3\2\2"+
		"\2\u00fa\u00fd\5\26\f\2\u00fb\u00fd\5*\26\2\u00fc\u00fa\3\2\2\2\u00fc"+
		"\u00fb\3\2\2\2\u00fd)\3\2\2\2\u00fe\u00ff\5,\27\2\u00ff\u0100\5.\30\2"+
		"\u0100\u0109\3\2\2\2\u0101\u0106\5.\30\2\u0102\u0103\7\13\2\2\u0103\u0104"+
		"\5\60\31\2\u0104\u0105\7\f\2\2\u0105\u0107\3\2\2\2\u0106\u0102\3\2\2\2"+
		"\u0106\u0107\3\2\2\2\u0107\u0109\3\2\2\2\u0108\u00fe\3\2\2\2\u0108\u0101"+
		"\3\2\2\2\u0109+\3\2\2\2\u010a\u010b\7\23\2\2\u010b-\3\2\2\2\u010c\u010d"+
		"\7\4\2\2\u010d/\3\2\2\2\u010e\u010f\7\6\2\2\u010f\61\3\2\2\2\u0110\u0113"+
		"\5\64\33\2\u0111\u0113\5\66\34\2\u0112\u0110\3\2\2\2\u0112\u0111\3\2\2"+
		"\2\u0113\63\3\2\2\2\u0114\u0115\7#\2\2\u0115\u0116\5:\36\2\u0116\u0117"+
		"\7\7\2\2\u0117\u0118\5\36\20\2\u0118\u0119\7\b\2\2\u0119\65\3\2\2\2\u011a"+
		"\u011b\7#\2\2\u011b\u011c\5> \2\u011c\u011d\7\7\2\2\u011d\u011e\5\36\20"+
		"\2\u011e\u011f\7\21\2\2\u011f\u0120\5\36\20\2\u0120\u0121\7\21\2\2\u0121"+
		"\u0122\58\35\2\u0122\u0123\7\b\2\2\u0123\67\3\2\2\2\u0124\u0125\t\2\2"+
		"\2\u01259\3\2\2\2\u0126\u0127\t\3\2\2\u0127;\3\2\2\2\u0128\u0129\7\65"+
		"\2\2\u0129=\3\2\2\2\u012a\u012b\t\4\2\2\u012b?\3\2\2\2\u012c\u0131\5B"+
		"\"\2\u012d\u0131\5r:\2\u012e\u0131\5p9\2\u012f\u0131\5^\60\2\u0130\u012c"+
		"\3\2\2\2\u0130\u012d\3\2\2\2\u0130\u012e\3\2\2\2\u0130\u012f\3\2\2\2\u0131"+
		"A\3\2\2\2\u0132\u013c\5D#\2\u0133\u013c\5F$\2\u0134\u013c\5H%\2\u0135"+
		"\u013c\5J&\2\u0136\u013c\5L\'\2\u0137\u013c\5T+\2\u0138\u013c\5\\/\2\u0139"+
		"\u013c\5t;\2\u013a\u013c\5f\64\2\u013b\u0132\3\2\2\2\u013b\u0133\3\2\2"+
		"\2\u013b\u0134\3\2\2\2\u013b\u0135\3\2\2\2\u013b\u0136\3\2\2\2\u013b\u0137"+
		"\3\2\2\2\u013b\u0138\3\2\2\2\u013b\u0139\3\2\2\2\u013b\u013a\3\2\2\2\u013c"+
		"C\3\2\2\2\u013d\u013e\5:\36\2\u013e\u013f\7\7\2\2\u013f\u0140\5*\26\2"+
		"\u0140\u0141\7\b\2\2\u0141E\3\2\2\2\u0142\u0143\5> \2\u0143\u0144\7\7"+
		"\2\2\u0144\u0145\5*\26\2\u0145\u0146\7\21\2\2\u0146\u0147\5*\26\2\u0147"+
		"\u0148\7\b\2\2\u0148G\3\2\2\2\u0149\u014a\5<\37\2\u014a\u014b\7\7\2\2"+
		"\u014b\u014c\5&\24\2\u014c\u014d\7\b\2\2\u014dI\3\2\2\2\u014e\u014f\5"+
		":\36\2\u014f\u0150\7\7\2\2\u0150\u0151\5\36\20\2\u0151\u0152\7\21\2\2"+
		"\u0152\u0153\58\35\2\u0153\u0154\7\b\2\2\u0154K\3\2\2\2\u0155\u0158\5"+
		"N(\2\u0156\u0158\5P)\2\u0157\u0155\3\2\2\2\u0157\u0156\3\2\2\2\u0158M"+
		"\3\2\2\2\u0159\u015a\5> \2\u015a\u015b\7\7\2\2\u015b\u015c\5*\26\2\u015c"+
		"\u015d\7\21\2\2\u015d\u015e\5\36\20\2\u015e\u015f\7\21\2\2\u015f\u0160"+
		"\58\35\2\u0160\u0161\7\b\2\2\u0161O\3\2\2\2\u0162\u0163\5> \2\u0163\u0164"+
		"\7\7\2\2\u0164\u0165\5\36\20\2\u0165\u0166\7\21\2\2\u0166\u0167\5\36\20"+
		"\2\u0167\u0168\7\21\2\2\u0168\u0169\5R*\2\u0169\u016a\7\b\2\2\u016aQ\3"+
		"\2\2\2\u016b\u016c\t\5\2\2\u016cS\3\2\2\2\u016d\u0170\5V,\2\u016e\u0170"+
		"\5X-\2\u016f\u016d\3\2\2\2\u016f\u016e\3\2\2\2\u0170U\3\2\2\2\u0171\u0172"+
		"\5*\26\2\u0172\u0173\7+\2\2\u0173\u0174\5\36\20\2\u0174W\3\2\2\2\u0175"+
		"\u0176\5\36\20\2\u0176\u0177\5Z.\2\u0177\u0178\5\36\20\2\u0178Y\3\2\2"+
		"\2\u0179\u017f\5x=\2\u017a\u017f\5z>\2\u017b\u017f\5|?\2\u017c\u017f\5"+
		"~@\2\u017d\u017f\5\u0080A\2\u017e\u0179\3\2\2\2\u017e\u017a\3\2\2\2\u017e"+
		"\u017b\3\2\2\2\u017e\u017c\3\2\2\2\u017e\u017d\3\2\2\2\u017f[\3\2\2\2"+
		"\u0180\u0181\t\6\2\2\u0181]\3\2\2\2\u0182\u0187\5h\65\2\u0183\u0187\5"+
		"j\66\2\u0184\u0187\5l\67\2\u0185\u0187\5n8\2\u0186\u0182\3\2\2\2\u0186"+
		"\u0183\3\2\2\2\u0186\u0184\3\2\2\2\u0186\u0185\3\2\2\2\u0187_\3\2\2\2"+
		"\u0188\u018c\5 \21\2\u0189\u018c\5d\63\2\u018a\u018c\5b\62\2\u018b\u0188"+
		"\3\2\2\2\u018b\u0189\3\2\2\2\u018b\u018a\3\2\2\2\u018c\u018d\3\2\2\2\u018d"+
		"\u0191\7\60\2\2\u018e\u0192\5 \21\2\u018f\u0192\5d\63\2\u0190\u0192\5"+
		"b\62\2\u0191\u018e\3\2\2\2\u0191\u018f\3\2\2\2\u0191\u0190\3\2\2\2\u0192"+
		"\u019b\3\2\2\2\u0193\u0197\7\60\2\2\u0194\u0198\5 \21\2\u0195\u0198\5"+
		"d\63\2\u0196\u0198\5b\62\2\u0197\u0194\3\2\2\2\u0197\u0195\3\2\2\2\u0197"+
		"\u0196\3\2\2\2\u0198\u019a\3\2\2\2\u0199\u0193\3\2\2\2\u019a\u019d\3\2"+
		"\2\2\u019b\u0199\3\2\2\2\u019b\u019c\3\2\2\2\u019ca\3\2\2\2\u019d\u019b"+
		"\3\2\2\2\u019e\u01a1\5 \21\2\u019f\u01a1\5d\63\2\u01a0\u019e\3\2\2\2\u01a0"+
		"\u019f\3\2\2\2\u01a1\u01a2\3\2\2\2\u01a2\u01a5\7\61\2\2\u01a3\u01a6\5"+
		" \21\2\u01a4\u01a6\5d\63\2\u01a5\u01a3\3\2\2\2\u01a5\u01a4\3\2\2\2\u01a6"+
		"\u01ae\3\2\2\2\u01a7\u01aa\7\61\2\2\u01a8\u01ab\5 \21\2\u01a9\u01ab\5"+
		"d\63\2\u01aa\u01a8\3\2\2\2\u01aa\u01a9\3\2\2\2\u01ab\u01ad\3\2\2\2\u01ac"+
		"\u01a7\3\2\2\2\u01ad\u01b0\3\2\2\2\u01ae\u01ac\3\2\2\2\u01ae\u01af\3\2"+
		"\2\2\u01afc\3\2\2\2\u01b0\u01ae\3\2\2\2\u01b1\u01b2\5 \21\2\u01b2\u01b3"+
		"\7\62\2\2\u01b3\u01b4\5 \21\2\u01b4\u01ba\3\2\2\2\u01b5\u01b6\5 \21\2"+
		"\u01b6\u01b7\7\62\2\2\u01b7\u01b8\5d\63\2\u01b8\u01ba\3\2\2\2\u01b9\u01b1"+
		"\3\2\2\2\u01b9\u01b5\3\2\2\2\u01bae\3\2\2\2\u01bb\u01bc\7$\2\2\u01bc\u01bd"+
		"\5B\"\2\u01bdg\3\2\2\2\u01be\u01bf\5B\"\2\u01bf\u01c0\7,\2\2\u01c0\u01c1"+
		"\7.\2\2\u01c1i\3\2\2\2\u01c2\u01c3\5B\"\2\u01c3\u01c4\7,\2\2\u01c4\u01c5"+
		"\7$\2\2\u01c5\u01c6\7.\2\2\u01c6k\3\2\2\2\u01c7\u01c8\5B\"\2\u01c8\u01c9"+
		"\7,\2\2\u01c9\u01ca\7/\2\2\u01cam\3\2\2\2\u01cb\u01cc\5B\"\2\u01cc\u01cd"+
		"\7,\2\2\u01cd\u01ce\7$\2\2\u01ce\u01cf\7/\2\2\u01cfo\3\2\2\2\u01d0\u01d4"+
		"\5B\"\2\u01d1\u01d4\5^\60\2\u01d2\u01d4\5r:\2\u01d3\u01d0\3\2\2\2\u01d3"+
		"\u01d1\3\2\2\2\u01d3\u01d2\3\2\2\2\u01d4\u01d5\3\2\2\2\u01d5\u01d9\7&"+
		"\2\2\u01d6\u01da\5B\"\2\u01d7\u01da\5^\60\2\u01d8\u01da\5r:\2\u01d9\u01d6"+
		"\3\2\2\2\u01d9\u01d7\3\2\2\2\u01d9\u01d8\3\2\2\2\u01da\u01e3\3\2\2\2\u01db"+
		"\u01df\7&\2\2\u01dc\u01e0\5B\"\2\u01dd\u01e0\5^\60\2\u01de\u01e0\5r:\2"+
		"\u01df\u01dc\3\2\2\2\u01df\u01dd\3\2\2\2\u01df\u01de\3\2\2\2\u01e0\u01e2"+
		"\3\2\2\2\u01e1\u01db\3\2\2\2\u01e2\u01e5\3\2\2\2\u01e3\u01e1\3\2\2\2\u01e3"+
		"\u01e4\3\2\2\2\u01e4q\3\2\2\2\u01e5\u01e3\3\2\2\2\u01e6\u01e9\5B\"\2\u01e7"+
		"\u01e9\5^\60\2\u01e8\u01e6\3\2\2\2\u01e8\u01e7\3\2\2\2\u01e9\u01ea\3\2"+
		"\2\2\u01ea\u01ed\7%\2\2\u01eb\u01ee\5B\"\2\u01ec\u01ee\5^\60\2\u01ed\u01eb"+
		"\3\2\2\2\u01ed\u01ec\3\2\2\2\u01ee\u01f6\3\2\2\2\u01ef\u01f2\7%\2\2\u01f0"+
		"\u01f3\5B\"\2\u01f1\u01f3\5^\60\2\u01f2\u01f0\3\2\2\2\u01f2\u01f1\3\2"+
		"\2\2\u01f3\u01f5\3\2\2\2\u01f4\u01ef\3\2\2\2\u01f5\u01f8\3\2\2\2\u01f6"+
		"\u01f4\3\2\2\2\u01f6\u01f7\3\2\2\2\u01f7s\3\2\2\2\u01f8\u01f6\3\2\2\2"+
		"\u01f9\u01fa\7\7\2\2\u01fa\u01fb\5B\"\2\u01fb\u01fc\7\b\2\2\u01fc\u020a"+
		"\3\2\2\2\u01fd\u01fe\7\7\2\2\u01fe\u01ff\5r:\2\u01ff\u0200\7\b\2\2\u0200"+
		"\u020a\3\2\2\2\u0201\u0202\7\7\2\2\u0202\u0203\5p9\2\u0203\u0204\7\b\2"+
		"\2\u0204\u020a\3\2\2\2\u0205\u0206\7\7\2\2\u0206\u0207\5^\60\2\u0207\u0208"+
		"\7\b\2\2\u0208\u020a\3\2\2\2\u0209\u01f9\3\2\2\2\u0209\u01fd\3\2\2\2\u0209"+
		"\u0201\3\2\2\2\u0209\u0205\3\2\2\2\u020au\3\2\2\2\u020b\u020c\7\7\2\2"+
		"\u020c\u020d\5\f\7\2\u020d\u020e\7\b\2\2\u020e\u022c\3\2\2\2\u020f\u0210"+
		"\7\7\2\2\u0210\u0211\5\26\f\2\u0211\u0212\7\b\2\2\u0212\u022c\3\2\2\2"+
		"\u0213\u0214\7\7\2\2\u0214\u0215\5$\23\2\u0215\u0216\7\b\2\2\u0216\u022c"+
		"\3\2\2\2\u0217\u0218\7\7\2\2\u0218\u0219\5\62\32\2\u0219\u021a\7\b\2\2"+
		"\u021a\u022c\3\2\2\2\u021b\u021c\7\7\2\2\u021c\u021d\5`\61\2\u021d\u021e"+
		"\7\b\2\2\u021e\u022c\3\2\2\2\u021f\u0220\7\7\2\2\u0220\u0221\5d\63\2\u0221"+
		"\u0222\7\b\2\2\u0222\u022c\3\2\2\2\u0223\u0224\7\7\2\2\u0224\u0225\5b"+
		"\62\2\u0225\u0226\7\b\2\2\u0226\u022c\3\2\2\2\u0227\u0228\7\7\2\2\u0228"+
		"\u0229\5v<\2\u0229\u022a\7\b\2\2\u022a\u022c\3\2\2\2\u022b\u020b\3\2\2"+
		"\2\u022b\u020f\3\2\2\2\u022b\u0213\3\2\2\2\u022b\u0217\3\2\2\2\u022b\u021b"+
		"\3\2\2\2\u022b\u021f\3\2\2\2\u022b\u0223\3\2\2\2\u022b\u0227\3\2\2\2\u022c"+
		"w\3\2\2\2\u022d\u022e\7\36\2\2\u022ey\3\2\2\2\u022f\u0230\7$\2\2\u0230"+
		"\u0231\7\36\2\2\u0231{\3\2\2\2\u0232\u0233\7\37\2\2\u0233\u0234\7 \2\2"+
		"\u0234}\3\2\2\2\u0235\u0236\7,\2\2\u0236\u0237\7!\2\2\u0237\u0238\7-\2"+
		"\2\u0238\177\3\2\2\2\u0239\u023a\7,\2\2\u023a\u023b\7\"\2\2\u023b\u023c"+
		"\7!\2\2\u023c\u023d\7-\2\2\u023d\u0081\3\2\2\2.\u0084\u008f\u0098\u009f"+
		"\u00ae\u00b3\u00ba\u00c0\u00cb\u00d3\u00da\u00e6\u00e9\u00f3\u00f6\u00fc"+
		"\u0106\u0108\u0112\u0130\u013b\u0157\u016f\u017e\u0186\u018b\u0191\u0197"+
		"\u019b\u01a0\u01a5\u01aa\u01ae\u01b9\u01d3\u01d9\u01df\u01e3\u01e8\u01ed"+
		"\u01f2\u01f6\u0209\u022b";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}