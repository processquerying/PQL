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
		UNIVERSE=1, ATTRIBUTE_NAME=2, STRING=3, VARIABLE_NAME=4, SIMILARITY=5, 
		LP=6, RP=7, LB=8, RB=9, LSB=10, RSB=11, LTB=12, RTB=13, DQ=14, EOS=15, 
		SEP=16, ASSIGN=17, TILDE=18, ESC_SEQ=19, UNICODE_ESC=20, HEX_DIGIT=21, 
		WS=22, LINE_COMMENT=23, SELECT=24, INSERT=25, INTO=26, FROM=27, WHERE=28, 
		EQUALS=29, OVERLAPS=30, WITH=31, SUBSET=32, PROPER=33, GET_TASKS=34, NOT=35, 
		AND=36, OR=37, ANY=38, EACH=39, ALL=40, IN=41, IS=42, OF=43, TRUE=44, 
		FALSE=45, UNION=46, INTERSECTION=47, DIFFERENCE=48, CAN_OCCUR=49, ALWAYS_OCCURS=50, 
		EXECUTES=51, CAN_CONFLICT=52, CAN_COOCCUR=53, CONFLICT=54, COOCCUR=55, 
		TOTAL_CAUSAL=56, TOTAL_CONCUR=57;
	public static final String[] tokenNames = {
		"<INVALID>", "'*'", "ATTRIBUTE_NAME", "STRING", "VARIABLE_NAME", "SIMILARITY", 
		"'('", "')'", "'{'", "'}'", "'['", "']'", "'<'", "'>'", "'\"'", "';'", 
		"','", "'='", "'~'", "ESC_SEQ", "UNICODE_ESC", "HEX_DIGIT", "WS", "LINE_COMMENT", 
		"'SELECT'", "'INSERT'", "'INTO'", "'FROM'", "'WHERE'", "'EQUALS'", "'OVERLAPS'", 
		"'WITH'", "'SUBSET'", "'PROPER'", "'GetTasks'", "'NOT'", "'AND'", "'OR'", 
		"'ANY'", "'EACH'", "'ALL'", "'IN'", "'IS'", "'OF'", "'TRUE'", "'FALSE'", 
		"'UNION'", "'INTERSECT'", "'EXCEPT'", "'CanOccur'", "'AlwaysOccurs'", 
		"'Executes'", "'CanConflict'", "'CanCooccur'", "'Conflict'", "'Cooccur'", 
		"'TotalCausal'", "'TotalConcurrent'"
	};
	public static final int
		RULE_query = 0, RULE_selectQuery = 1, RULE_insertQuery = 2, RULE_variables = 3, 
		RULE_variable = 4, RULE_varName = 5, RULE_attributes = 6, RULE_attribute = 7, 
		RULE_locations = 8, RULE_location = 9, RULE_universe = 10, RULE_attributeName = 11, 
		RULE_locationPath = 12, RULE_setOfTasks = 13, RULE_tasks = 14, RULE_setOfTasksLiteral = 15, 
		RULE_trace = 16, RULE_event = 17, RULE_task = 18, RULE_approximate = 19, 
		RULE_label = 20, RULE_similarity = 21, RULE_setOfTasksConstruction = 22, 
		RULE_unaryPredicateConstruction = 23, RULE_binaryPredicateConstruction = 24, 
		RULE_anyAll = 25, RULE_unaryPredicateName = 26, RULE_unaryTracePredicateName = 27, 
		RULE_binaryPredicateName = 28, RULE_predicate = 29, RULE_proposition = 30, 
		RULE_unaryPredicate = 31, RULE_binaryPredicate = 32, RULE_unaryTracePredicate = 33, 
		RULE_unaryPredicateMacro = 34, RULE_binaryPredicateMacro = 35, RULE_binaryPredicateMacroTaskSet = 36, 
		RULE_binaryPredicateMacroSetSet = 37, RULE_anyEachAll = 38, RULE_setPredicate = 39, 
		RULE_taskInSetOfTasks = 40, RULE_setComparison = 41, RULE_setComparisonOperator = 42, 
		RULE_truthValue = 43, RULE_logicalTest = 44, RULE_union = 45, RULE_intersection = 46, 
		RULE_difference = 47, RULE_negation = 48, RULE_isTrue = 49, RULE_isNotTrue = 50, 
		RULE_isFalse = 51, RULE_isNotFalse = 52, RULE_disjunction = 53, RULE_conjunction = 54, 
		RULE_parentheses = 55, RULE_setOfTasksParentheses = 56, RULE_identical = 57, 
		RULE_different = 58, RULE_overlapsWith = 59, RULE_subsetOf = 60, RULE_properSubsetOf = 61;
	public static final String[] ruleNames = {
		"query", "selectQuery", "insertQuery", "variables", "variable", "varName", 
		"attributes", "attribute", "locations", "location", "universe", "attributeName", 
		"locationPath", "setOfTasks", "tasks", "setOfTasksLiteral", "trace", "event", 
		"task", "approximate", "label", "similarity", "setOfTasksConstruction", 
		"unaryPredicateConstruction", "binaryPredicateConstruction", "anyAll", 
		"unaryPredicateName", "unaryTracePredicateName", "binaryPredicateName", 
		"predicate", "proposition", "unaryPredicate", "binaryPredicate", "unaryTracePredicate", 
		"unaryPredicateMacro", "binaryPredicateMacro", "binaryPredicateMacroTaskSet", 
		"binaryPredicateMacroSetSet", "anyEachAll", "setPredicate", "taskInSetOfTasks", 
		"setComparison", "setComparisonOperator", "truthValue", "logicalTest", 
		"union", "intersection", "difference", "negation", "isTrue", "isNotTrue", 
		"isFalse", "isNotFalse", "disjunction", "conjunction", "parentheses", 
		"setOfTasksParentheses", "identical", "different", "overlapsWith", "subsetOf", 
		"properSubsetOf"
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
			setState(126);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(124); selectQuery();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(125); insertQuery();
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
		public TerminalNode WHERE() { return getToken(PQLParser.WHERE, 0); }
		public AttributesContext attributes() {
			return getRuleContext(AttributesContext.class,0);
		}
		public VariablesContext variables() {
			return getRuleContext(VariablesContext.class,0);
		}
		public PredicateContext predicate() {
			return getRuleContext(PredicateContext.class,0);
		}
		public TerminalNode EOS() { return getToken(PQLParser.EOS, 0); }
		public LocationsContext locations() {
			return getRuleContext(LocationsContext.class,0);
		}
		public TerminalNode SELECT() { return getToken(PQLParser.SELECT, 0); }
		public TerminalNode FROM() { return getToken(PQLParser.FROM, 0); }
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
			setState(128); variables();
			setState(129); match(SELECT);
			setState(130); attributes();
			setState(131); match(FROM);
			setState(132); locations();
			setState(135);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(133); match(WHERE);
				setState(134); predicate();
				}
			}

			setState(137); match(EOS);
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
		public TerminalNode WHERE() { return getToken(PQLParser.WHERE, 0); }
		public TraceContext trace() {
			return getRuleContext(TraceContext.class,0);
		}
		public VariablesContext variables() {
			return getRuleContext(VariablesContext.class,0);
		}
		public PredicateContext predicate() {
			return getRuleContext(PredicateContext.class,0);
		}
		public TerminalNode INTO() { return getToken(PQLParser.INTO, 0); }
		public TerminalNode INSERT() { return getToken(PQLParser.INSERT, 0); }
		public TerminalNode EOS() { return getToken(PQLParser.EOS, 0); }
		public LocationsContext locations() {
			return getRuleContext(LocationsContext.class,0);
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
			setState(139); variables();
			setState(140); match(INSERT);
			setState(141); trace();
			setState(142); match(INTO);
			setState(143); locations();
			setState(146);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(144); match(WHERE);
				setState(145); predicate();
				}
			}

			setState(148); match(EOS);
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
		public List<VariableContext> variable() {
			return getRuleContexts(VariableContext.class);
		}
		public VariableContext variable(int i) {
			return getRuleContext(VariableContext.class,i);
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
			setState(153);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==VARIABLE_NAME) {
				{
				{
				setState(150); variable();
				}
				}
				setState(155);
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
		public SetOfTasksContext setOfTasks() {
			return getRuleContext(SetOfTasksContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(PQLParser.ASSIGN, 0); }
		public TerminalNode EOS() { return getToken(PQLParser.EOS, 0); }
		public VarNameContext varName() {
			return getRuleContext(VarNameContext.class,0);
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
			setState(156); varName();
			setState(157); match(ASSIGN);
			setState(158); setOfTasks();
			setState(159); match(EOS);
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
			setState(161); match(VARIABLE_NAME);
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
		public TerminalNode SEP(int i) {
			return getToken(PQLParser.SEP, i);
		}
		public List<TerminalNode> SEP() { return getTokens(PQLParser.SEP); }
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
			setState(163); attribute();
			setState(168);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SEP) {
				{
				{
				setState(164); match(SEP);
				setState(165); attribute();
				}
				}
				setState(170);
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
		public UniverseContext universe() {
			return getRuleContext(UniverseContext.class,0);
		}
		public AttributeNameContext attributeName() {
			return getRuleContext(AttributeNameContext.class,0);
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
			setState(173);
			switch (_input.LA(1)) {
			case UNIVERSE:
				enterOuterAlt(_localctx, 1);
				{
				setState(171); universe();
				}
				break;
			case ATTRIBUTE_NAME:
				enterOuterAlt(_localctx, 2);
				{
				setState(172); attributeName();
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
		public LocationContext location(int i) {
			return getRuleContext(LocationContext.class,i);
		}
		public List<LocationContext> location() {
			return getRuleContexts(LocationContext.class);
		}
		public TerminalNode SEP(int i) {
			return getToken(PQLParser.SEP, i);
		}
		public List<TerminalNode> SEP() { return getTokens(PQLParser.SEP); }
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
			setState(175); location();
			setState(180);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SEP) {
				{
				{
				setState(176); match(SEP);
				setState(177); location();
				}
				}
				setState(182);
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
		public UniverseContext universe() {
			return getRuleContext(UniverseContext.class,0);
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
			setState(185);
			switch (_input.LA(1)) {
			case UNIVERSE:
				enterOuterAlt(_localctx, 1);
				{
				setState(183); universe();
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 2);
				{
				setState(184); locationPath();
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
			setState(187); match(UNIVERSE);
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
		public TerminalNode ATTRIBUTE_NAME() { return getToken(PQLParser.ATTRIBUTE_NAME, 0); }
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
			setState(189); match(ATTRIBUTE_NAME);
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
			setState(191); match(STRING);
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
		public DifferenceContext difference() {
			return getRuleContext(DifferenceContext.class,0);
		}
		public IntersectionContext intersection() {
			return getRuleContext(IntersectionContext.class,0);
		}
		public TasksContext tasks() {
			return getRuleContext(TasksContext.class,0);
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
		enterRule(_localctx, 26, RULE_setOfTasks);
		try {
			setState(197);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(193); tasks();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(194); union();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(195); intersection();
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(196); difference();
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
		public UniverseContext universe() {
			return getRuleContext(UniverseContext.class,0);
		}
		public SetOfTasksLiteralContext setOfTasksLiteral() {
			return getRuleContext(SetOfTasksLiteralContext.class,0);
		}
		public SetOfTasksConstructionContext setOfTasksConstruction() {
			return getRuleContext(SetOfTasksConstructionContext.class,0);
		}
		public SetOfTasksParenthesesContext setOfTasksParentheses() {
			return getRuleContext(SetOfTasksParenthesesContext.class,0);
		}
		public VarNameContext varName() {
			return getRuleContext(VarNameContext.class,0);
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
		enterRule(_localctx, 28, RULE_tasks);
		try {
			setState(206);
			switch (_input.LA(1)) {
			case UNIVERSE:
			case VARIABLE_NAME:
				enterOuterAlt(_localctx, 1);
				{
				setState(201);
				switch (_input.LA(1)) {
				case VARIABLE_NAME:
					{
					setState(199); varName();
					}
					break;
				case UNIVERSE:
					{
					setState(200); universe();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			case LB:
				enterOuterAlt(_localctx, 2);
				{
				setState(203); setOfTasksLiteral();
				}
				break;
			case GET_TASKS:
				enterOuterAlt(_localctx, 3);
				{
				setState(204); setOfTasksConstruction();
				}
				break;
			case LP:
				enterOuterAlt(_localctx, 4);
				{
				setState(205); setOfTasksParentheses();
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

	public static class SetOfTasksLiteralContext extends ParserRuleContext {
		public TerminalNode LB() { return getToken(PQLParser.LB, 0); }
		public List<TaskContext> task() {
			return getRuleContexts(TaskContext.class);
		}
		public TerminalNode RB() { return getToken(PQLParser.RB, 0); }
		public TaskContext task(int i) {
			return getRuleContext(TaskContext.class,i);
		}
		public TerminalNode SEP(int i) {
			return getToken(PQLParser.SEP, i);
		}
		public List<TerminalNode> SEP() { return getTokens(PQLParser.SEP); }
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
		enterRule(_localctx, 30, RULE_setOfTasksLiteral);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(208); match(LB);
			setState(217);
			_la = _input.LA(1);
			if (_la==STRING || _la==TILDE) {
				{
				setState(209); task();
				setState(214);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SEP) {
					{
					{
					setState(210); match(SEP);
					setState(211); task();
					}
					}
					setState(216);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(219); match(RB);
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
		public EventContext event(int i) {
			return getRuleContext(EventContext.class,i);
		}
		public TerminalNode SEP(int i) {
			return getToken(PQLParser.SEP, i);
		}
		public List<TerminalNode> SEP() { return getTokens(PQLParser.SEP); }
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
		enterRule(_localctx, 32, RULE_trace);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(221); match(LTB);
			setState(230);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << UNIVERSE) | (1L << STRING) | (1L << TILDE))) != 0)) {
				{
				setState(222); event();
				setState(227);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SEP) {
					{
					{
					setState(223); match(SEP);
					setState(224); event();
					}
					}
					setState(229);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(232); match(RTB);
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
		public UniverseContext universe() {
			return getRuleContext(UniverseContext.class,0);
		}
		public TaskContext task() {
			return getRuleContext(TaskContext.class,0);
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
		enterRule(_localctx, 34, RULE_event);
		try {
			setState(236);
			switch (_input.LA(1)) {
			case UNIVERSE:
				enterOuterAlt(_localctx, 1);
				{
				setState(234); universe();
				}
				break;
			case STRING:
			case TILDE:
				enterOuterAlt(_localctx, 2);
				{
				setState(235); task();
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
		public SimilarityContext similarity() {
			return getRuleContext(SimilarityContext.class,0);
		}
		public TerminalNode RSB() { return getToken(PQLParser.RSB, 0); }
		public LabelContext label() {
			return getRuleContext(LabelContext.class,0);
		}
		public ApproximateContext approximate() {
			return getRuleContext(ApproximateContext.class,0);
		}
		public TerminalNode LSB() { return getToken(PQLParser.LSB, 0); }
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
		enterRule(_localctx, 36, RULE_task);
		int _la;
		try {
			setState(248);
			switch (_input.LA(1)) {
			case TILDE:
				enterOuterAlt(_localctx, 1);
				{
				setState(238); approximate();
				setState(239); label();
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 2);
				{
				setState(241); label();
				setState(246);
				_la = _input.LA(1);
				if (_la==LSB) {
					{
					setState(242); match(LSB);
					setState(243); similarity();
					setState(244); match(RSB);
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
		enterRule(_localctx, 38, RULE_approximate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(250); match(TILDE);
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
		enterRule(_localctx, 40, RULE_label);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(252); match(STRING);
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
		enterRule(_localctx, 42, RULE_similarity);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(254); match(SIMILARITY);
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
		enterRule(_localctx, 44, RULE_setOfTasksConstruction);
		try {
			setState(258);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(256); unaryPredicateConstruction();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(257); binaryPredicateConstruction();
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
		public SetOfTasksContext setOfTasks() {
			return getRuleContext(SetOfTasksContext.class,0);
		}
		public TerminalNode LP() { return getToken(PQLParser.LP, 0); }
		public TerminalNode GET_TASKS() { return getToken(PQLParser.GET_TASKS, 0); }
		public TerminalNode RP() { return getToken(PQLParser.RP, 0); }
		public UnaryPredicateNameContext unaryPredicateName() {
			return getRuleContext(UnaryPredicateNameContext.class,0);
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
		enterRule(_localctx, 46, RULE_unaryPredicateConstruction);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(260); match(GET_TASKS);
			}
			setState(261); unaryPredicateName();
			setState(262); match(LP);
			setState(263); setOfTasks();
			setState(264); match(RP);
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
		public List<SetOfTasksContext> setOfTasks() {
			return getRuleContexts(SetOfTasksContext.class);
		}
		public AnyAllContext anyAll() {
			return getRuleContext(AnyAllContext.class,0);
		}
		public TerminalNode LP() { return getToken(PQLParser.LP, 0); }
		public TerminalNode GET_TASKS() { return getToken(PQLParser.GET_TASKS, 0); }
		public TerminalNode RP() { return getToken(PQLParser.RP, 0); }
		public SetOfTasksContext setOfTasks(int i) {
			return getRuleContext(SetOfTasksContext.class,i);
		}
		public TerminalNode SEP(int i) {
			return getToken(PQLParser.SEP, i);
		}
		public List<TerminalNode> SEP() { return getTokens(PQLParser.SEP); }
		public BinaryPredicateNameContext binaryPredicateName() {
			return getRuleContext(BinaryPredicateNameContext.class,0);
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
		enterRule(_localctx, 48, RULE_binaryPredicateConstruction);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(266); match(GET_TASKS);
			}
			setState(267); binaryPredicateName();
			setState(268); match(LP);
			setState(269); setOfTasks();
			setState(270); match(SEP);
			setState(271); setOfTasks();
			setState(272); match(SEP);
			setState(273); anyAll();
			setState(274); match(RP);
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
		public TerminalNode ALL() { return getToken(PQLParser.ALL, 0); }
		public TerminalNode ANY() { return getToken(PQLParser.ANY, 0); }
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
		enterRule(_localctx, 50, RULE_anyAll);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(276);
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
		public TerminalNode ALWAYS_OCCURS() { return getToken(PQLParser.ALWAYS_OCCURS, 0); }
		public TerminalNode CAN_OCCUR() { return getToken(PQLParser.CAN_OCCUR, 0); }
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
		enterRule(_localctx, 52, RULE_unaryPredicateName);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(278);
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
		enterRule(_localctx, 54, RULE_unaryTracePredicateName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(280); match(EXECUTES);
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
		public TerminalNode COOCCUR() { return getToken(PQLParser.COOCCUR, 0); }
		public TerminalNode TOTAL_CONCUR() { return getToken(PQLParser.TOTAL_CONCUR, 0); }
		public TerminalNode CAN_CONFLICT() { return getToken(PQLParser.CAN_CONFLICT, 0); }
		public TerminalNode TOTAL_CAUSAL() { return getToken(PQLParser.TOTAL_CAUSAL, 0); }
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
		enterRule(_localctx, 56, RULE_binaryPredicateName);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(282);
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
		public DisjunctionContext disjunction() {
			return getRuleContext(DisjunctionContext.class,0);
		}
		public ConjunctionContext conjunction() {
			return getRuleContext(ConjunctionContext.class,0);
		}
		public PropositionContext proposition() {
			return getRuleContext(PropositionContext.class,0);
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
		enterRule(_localctx, 58, RULE_predicate);
		try {
			setState(288);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(284); proposition();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(285); conjunction();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(286); disjunction();
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(287); logicalTest();
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
		public ParenthesesContext parentheses() {
			return getRuleContext(ParenthesesContext.class,0);
		}
		public UnaryPredicateContext unaryPredicate() {
			return getRuleContext(UnaryPredicateContext.class,0);
		}
		public SetPredicateContext setPredicate() {
			return getRuleContext(SetPredicateContext.class,0);
		}
		public TruthValueContext truthValue() {
			return getRuleContext(TruthValueContext.class,0);
		}
		public UnaryPredicateMacroContext unaryPredicateMacro() {
			return getRuleContext(UnaryPredicateMacroContext.class,0);
		}
		public BinaryPredicateContext binaryPredicate() {
			return getRuleContext(BinaryPredicateContext.class,0);
		}
		public BinaryPredicateMacroContext binaryPredicateMacro() {
			return getRuleContext(BinaryPredicateMacroContext.class,0);
		}
		public UnaryTracePredicateContext unaryTracePredicate() {
			return getRuleContext(UnaryTracePredicateContext.class,0);
		}
		public NegationContext negation() {
			return getRuleContext(NegationContext.class,0);
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
		enterRule(_localctx, 60, RULE_proposition);
		try {
			setState(299);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(290); unaryPredicate();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(291); binaryPredicate();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(292); unaryTracePredicate();
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(293); unaryPredicateMacro();
				}
				break;

			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(294); binaryPredicateMacro();
				}
				break;

			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(295); setPredicate();
				}
				break;

			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(296); truthValue();
				}
				break;

			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(297); parentheses();
				}
				break;

			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(298); negation();
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
		public TaskContext task() {
			return getRuleContext(TaskContext.class,0);
		}
		public TerminalNode LP() { return getToken(PQLParser.LP, 0); }
		public TerminalNode RP() { return getToken(PQLParser.RP, 0); }
		public UnaryPredicateNameContext unaryPredicateName() {
			return getRuleContext(UnaryPredicateNameContext.class,0);
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
		enterRule(_localctx, 62, RULE_unaryPredicate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(301); unaryPredicateName();
			setState(302); match(LP);
			setState(303); task();
			setState(304); match(RP);
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
		public List<TaskContext> task() {
			return getRuleContexts(TaskContext.class);
		}
		public TerminalNode LP() { return getToken(PQLParser.LP, 0); }
		public TerminalNode RP() { return getToken(PQLParser.RP, 0); }
		public TaskContext task(int i) {
			return getRuleContext(TaskContext.class,i);
		}
		public TerminalNode SEP() { return getToken(PQLParser.SEP, 0); }
		public BinaryPredicateNameContext binaryPredicateName() {
			return getRuleContext(BinaryPredicateNameContext.class,0);
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
		enterRule(_localctx, 64, RULE_binaryPredicate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(306); binaryPredicateName();
			setState(307); match(LP);
			setState(308); task();
			setState(309); match(SEP);
			setState(310); task();
			setState(311); match(RP);
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
		public TraceContext trace() {
			return getRuleContext(TraceContext.class,0);
		}
		public TerminalNode LP() { return getToken(PQLParser.LP, 0); }
		public TerminalNode RP() { return getToken(PQLParser.RP, 0); }
		public UnaryTracePredicateNameContext unaryTracePredicateName() {
			return getRuleContext(UnaryTracePredicateNameContext.class,0);
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
		enterRule(_localctx, 66, RULE_unaryTracePredicate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(313); unaryTracePredicateName();
			setState(314); match(LP);
			setState(315); trace();
			setState(316); match(RP);
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
		public SetOfTasksContext setOfTasks() {
			return getRuleContext(SetOfTasksContext.class,0);
		}
		public AnyAllContext anyAll() {
			return getRuleContext(AnyAllContext.class,0);
		}
		public TerminalNode LP() { return getToken(PQLParser.LP, 0); }
		public TerminalNode RP() { return getToken(PQLParser.RP, 0); }
		public TerminalNode SEP() { return getToken(PQLParser.SEP, 0); }
		public UnaryPredicateNameContext unaryPredicateName() {
			return getRuleContext(UnaryPredicateNameContext.class,0);
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
		enterRule(_localctx, 68, RULE_unaryPredicateMacro);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(318); unaryPredicateName();
			setState(319); match(LP);
			setState(320); setOfTasks();
			setState(321); match(SEP);
			setState(322); anyAll();
			setState(323); match(RP);
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
		enterRule(_localctx, 70, RULE_binaryPredicateMacro);
		try {
			setState(327);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(325); binaryPredicateMacroTaskSet();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(326); binaryPredicateMacroSetSet();
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
		public SetOfTasksContext setOfTasks() {
			return getRuleContext(SetOfTasksContext.class,0);
		}
		public AnyAllContext anyAll() {
			return getRuleContext(AnyAllContext.class,0);
		}
		public TaskContext task() {
			return getRuleContext(TaskContext.class,0);
		}
		public TerminalNode LP() { return getToken(PQLParser.LP, 0); }
		public TerminalNode RP() { return getToken(PQLParser.RP, 0); }
		public TerminalNode SEP(int i) {
			return getToken(PQLParser.SEP, i);
		}
		public List<TerminalNode> SEP() { return getTokens(PQLParser.SEP); }
		public BinaryPredicateNameContext binaryPredicateName() {
			return getRuleContext(BinaryPredicateNameContext.class,0);
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
		enterRule(_localctx, 72, RULE_binaryPredicateMacroTaskSet);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(329); binaryPredicateName();
			setState(330); match(LP);
			setState(331); task();
			setState(332); match(SEP);
			setState(333); setOfTasks();
			setState(334); match(SEP);
			setState(335); anyAll();
			setState(336); match(RP);
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
		public List<SetOfTasksContext> setOfTasks() {
			return getRuleContexts(SetOfTasksContext.class);
		}
		public AnyEachAllContext anyEachAll() {
			return getRuleContext(AnyEachAllContext.class,0);
		}
		public TerminalNode LP() { return getToken(PQLParser.LP, 0); }
		public TerminalNode RP() { return getToken(PQLParser.RP, 0); }
		public SetOfTasksContext setOfTasks(int i) {
			return getRuleContext(SetOfTasksContext.class,i);
		}
		public TerminalNode SEP(int i) {
			return getToken(PQLParser.SEP, i);
		}
		public List<TerminalNode> SEP() { return getTokens(PQLParser.SEP); }
		public BinaryPredicateNameContext binaryPredicateName() {
			return getRuleContext(BinaryPredicateNameContext.class,0);
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
		enterRule(_localctx, 74, RULE_binaryPredicateMacroSetSet);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(338); binaryPredicateName();
			setState(339); match(LP);
			setState(340); setOfTasks();
			setState(341); match(SEP);
			setState(342); setOfTasks();
			setState(343); match(SEP);
			setState(344); anyEachAll();
			setState(345); match(RP);
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

	public static class AnyEachAllContext extends ParserRuleContext {
		public TerminalNode ALL() { return getToken(PQLParser.ALL, 0); }
		public TerminalNode ANY() { return getToken(PQLParser.ANY, 0); }
		public TerminalNode EACH() { return getToken(PQLParser.EACH, 0); }
		public AnyEachAllContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_anyEachAll; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).enterAnyEachAll(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).exitAnyEachAll(this);
		}
	}

	public final AnyEachAllContext anyEachAll() throws RecognitionException {
		AnyEachAllContext _localctx = new AnyEachAllContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_anyEachAll);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(347);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ANY) | (1L << EACH) | (1L << ALL))) != 0)) ) {
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
		public SetComparisonContext setComparison() {
			return getRuleContext(SetComparisonContext.class,0);
		}
		public TaskInSetOfTasksContext taskInSetOfTasks() {
			return getRuleContext(TaskInSetOfTasksContext.class,0);
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
		enterRule(_localctx, 78, RULE_setPredicate);
		try {
			setState(351);
			switch (_input.LA(1)) {
			case STRING:
			case TILDE:
				enterOuterAlt(_localctx, 1);
				{
				setState(349); taskInSetOfTasks();
				}
				break;
			case UNIVERSE:
			case VARIABLE_NAME:
			case LP:
			case LB:
			case GET_TASKS:
				enterOuterAlt(_localctx, 2);
				{
				setState(350); setComparison();
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
		public SetOfTasksContext setOfTasks() {
			return getRuleContext(SetOfTasksContext.class,0);
		}
		public TerminalNode IN() { return getToken(PQLParser.IN, 0); }
		public TaskContext task() {
			return getRuleContext(TaskContext.class,0);
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
		enterRule(_localctx, 80, RULE_taskInSetOfTasks);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(353); task();
			setState(354); match(IN);
			setState(355); setOfTasks();
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
		public List<SetOfTasksContext> setOfTasks() {
			return getRuleContexts(SetOfTasksContext.class);
		}
		public SetComparisonOperatorContext setComparisonOperator() {
			return getRuleContext(SetComparisonOperatorContext.class,0);
		}
		public SetOfTasksContext setOfTasks(int i) {
			return getRuleContext(SetOfTasksContext.class,i);
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
		enterRule(_localctx, 82, RULE_setComparison);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(357); setOfTasks();
			setState(358); setComparisonOperator();
			setState(359); setOfTasks();
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
		public IdenticalContext identical() {
			return getRuleContext(IdenticalContext.class,0);
		}
		public ProperSubsetOfContext properSubsetOf() {
			return getRuleContext(ProperSubsetOfContext.class,0);
		}
		public DifferentContext different() {
			return getRuleContext(DifferentContext.class,0);
		}
		public SubsetOfContext subsetOf() {
			return getRuleContext(SubsetOfContext.class,0);
		}
		public OverlapsWithContext overlapsWith() {
			return getRuleContext(OverlapsWithContext.class,0);
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
		enterRule(_localctx, 84, RULE_setComparisonOperator);
		try {
			setState(366);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(361); identical();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(362); different();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(363); overlapsWith();
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(364); subsetOf();
				}
				break;

			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(365); properSubsetOf();
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
		public TerminalNode TRUE() { return getToken(PQLParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(PQLParser.FALSE, 0); }
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
		enterRule(_localctx, 86, RULE_truthValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(368);
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
		public IsNotFalseContext isNotFalse() {
			return getRuleContext(IsNotFalseContext.class,0);
		}
		public IsNotTrueContext isNotTrue() {
			return getRuleContext(IsNotTrueContext.class,0);
		}
		public IsTrueContext isTrue() {
			return getRuleContext(IsTrueContext.class,0);
		}
		public IsFalseContext isFalse() {
			return getRuleContext(IsFalseContext.class,0);
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
		enterRule(_localctx, 88, RULE_logicalTest);
		try {
			setState(374);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(370); isTrue();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(371); isNotTrue();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(372); isFalse();
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(373); isNotFalse();
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
		public List<IntersectionContext> intersection() {
			return getRuleContexts(IntersectionContext.class);
		}
		public List<DifferenceContext> difference() {
			return getRuleContexts(DifferenceContext.class);
		}
		public List<TerminalNode> UNION() { return getTokens(PQLParser.UNION); }
		public IntersectionContext intersection(int i) {
			return getRuleContext(IntersectionContext.class,i);
		}
		public TasksContext tasks(int i) {
			return getRuleContext(TasksContext.class,i);
		}
		public List<TasksContext> tasks() {
			return getRuleContexts(TasksContext.class);
		}
		public DifferenceContext difference(int i) {
			return getRuleContext(DifferenceContext.class,i);
		}
		public TerminalNode UNION(int i) {
			return getToken(PQLParser.UNION, i);
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
		enterRule(_localctx, 90, RULE_union);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(379);
			switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
			case 1:
				{
				setState(376); tasks();
				}
				break;

			case 2:
				{
				setState(377); difference();
				}
				break;

			case 3:
				{
				setState(378); intersection();
				}
				break;
			}
			setState(381); match(UNION);
			setState(385);
			switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
			case 1:
				{
				setState(382); tasks();
				}
				break;

			case 2:
				{
				setState(383); difference();
				}
				break;

			case 3:
				{
				setState(384); intersection();
				}
				break;
			}
			setState(395);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==UNION) {
				{
				{
				setState(387); match(UNION);
				setState(391);
				switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
				case 1:
					{
					setState(388); tasks();
					}
					break;

				case 2:
					{
					setState(389); difference();
					}
					break;

				case 3:
					{
					setState(390); intersection();
					}
					break;
				}
				}
				}
				setState(397);
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
		public List<DifferenceContext> difference() {
			return getRuleContexts(DifferenceContext.class);
		}
		public TasksContext tasks(int i) {
			return getRuleContext(TasksContext.class,i);
		}
		public List<TasksContext> tasks() {
			return getRuleContexts(TasksContext.class);
		}
		public DifferenceContext difference(int i) {
			return getRuleContext(DifferenceContext.class,i);
		}
		public List<TerminalNode> INTERSECTION() { return getTokens(PQLParser.INTERSECTION); }
		public TerminalNode INTERSECTION(int i) {
			return getToken(PQLParser.INTERSECTION, i);
		}
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
		enterRule(_localctx, 92, RULE_intersection);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(400);
			switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
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
			}
			setState(402); match(INTERSECTION);
			setState(405);
			switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
			case 1:
				{
				setState(403); tasks();
				}
				break;

			case 2:
				{
				setState(404); difference();
				}
				break;
			}
			setState(414);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==INTERSECTION) {
				{
				{
				setState(407); match(INTERSECTION);
				setState(410);
				switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
				case 1:
					{
					setState(408); tasks();
					}
					break;

				case 2:
					{
					setState(409); difference();
					}
					break;
				}
				}
				}
				setState(416);
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
		public DifferenceContext difference() {
			return getRuleContext(DifferenceContext.class,0);
		}
		public TerminalNode DIFFERENCE() { return getToken(PQLParser.DIFFERENCE, 0); }
		public TasksContext tasks(int i) {
			return getRuleContext(TasksContext.class,i);
		}
		public List<TasksContext> tasks() {
			return getRuleContexts(TasksContext.class);
		}
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
		enterRule(_localctx, 94, RULE_difference);
		try {
			setState(425);
			switch ( getInterpreter().adaptivePredict(_input,33,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(417); tasks();
				setState(418); match(DIFFERENCE);
				setState(419); tasks();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(421); tasks();
				setState(422); match(DIFFERENCE);
				setState(423); difference();
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
		enterRule(_localctx, 96, RULE_negation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(427); match(NOT);
			setState(428); proposition();
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
		public TerminalNode TRUE() { return getToken(PQLParser.TRUE, 0); }
		public PropositionContext proposition() {
			return getRuleContext(PropositionContext.class,0);
		}
		public TerminalNode IS() { return getToken(PQLParser.IS, 0); }
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
		enterRule(_localctx, 98, RULE_isTrue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(430); proposition();
			setState(431); match(IS);
			setState(432); match(TRUE);
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
		public TerminalNode TRUE() { return getToken(PQLParser.TRUE, 0); }
		public TerminalNode NOT() { return getToken(PQLParser.NOT, 0); }
		public PropositionContext proposition() {
			return getRuleContext(PropositionContext.class,0);
		}
		public TerminalNode IS() { return getToken(PQLParser.IS, 0); }
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
		enterRule(_localctx, 100, RULE_isNotTrue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(434); proposition();
			setState(435); match(IS);
			setState(436); match(NOT);
			setState(437); match(TRUE);
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
		public PropositionContext proposition() {
			return getRuleContext(PropositionContext.class,0);
		}
		public TerminalNode IS() { return getToken(PQLParser.IS, 0); }
		public TerminalNode FALSE() { return getToken(PQLParser.FALSE, 0); }
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
		enterRule(_localctx, 102, RULE_isFalse);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(439); proposition();
			setState(440); match(IS);
			setState(441); match(FALSE);
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
		public PropositionContext proposition() {
			return getRuleContext(PropositionContext.class,0);
		}
		public TerminalNode IS() { return getToken(PQLParser.IS, 0); }
		public TerminalNode FALSE() { return getToken(PQLParser.FALSE, 0); }
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
		enterRule(_localctx, 104, RULE_isNotFalse);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(443); proposition();
			setState(444); match(IS);
			setState(445); match(NOT);
			setState(446); match(FALSE);
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
		public LogicalTestContext logicalTest(int i) {
			return getRuleContext(LogicalTestContext.class,i);
		}
		public List<ConjunctionContext> conjunction() {
			return getRuleContexts(ConjunctionContext.class);
		}
		public PropositionContext proposition(int i) {
			return getRuleContext(PropositionContext.class,i);
		}
		public List<TerminalNode> OR() { return getTokens(PQLParser.OR); }
		public List<PropositionContext> proposition() {
			return getRuleContexts(PropositionContext.class);
		}
		public ConjunctionContext conjunction(int i) {
			return getRuleContext(ConjunctionContext.class,i);
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
		enterRule(_localctx, 106, RULE_disjunction);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(451);
			switch ( getInterpreter().adaptivePredict(_input,34,_ctx) ) {
			case 1:
				{
				setState(448); proposition();
				}
				break;

			case 2:
				{
				setState(449); logicalTest();
				}
				break;

			case 3:
				{
				setState(450); conjunction();
				}
				break;
			}
			setState(453); match(OR);
			setState(457);
			switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
			case 1:
				{
				setState(454); proposition();
				}
				break;

			case 2:
				{
				setState(455); logicalTest();
				}
				break;

			case 3:
				{
				setState(456); conjunction();
				}
				break;
			}
			setState(467);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR) {
				{
				{
				setState(459); match(OR);
				setState(463);
				switch ( getInterpreter().adaptivePredict(_input,36,_ctx) ) {
				case 1:
					{
					setState(460); proposition();
					}
					break;

				case 2:
					{
					setState(461); logicalTest();
					}
					break;

				case 3:
					{
					setState(462); conjunction();
					}
					break;
				}
				}
				}
				setState(469);
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
		public List<LogicalTestContext> logicalTest() {
			return getRuleContexts(LogicalTestContext.class);
		}
		public LogicalTestContext logicalTest(int i) {
			return getRuleContext(LogicalTestContext.class,i);
		}
		public List<TerminalNode> AND() { return getTokens(PQLParser.AND); }
		public PropositionContext proposition(int i) {
			return getRuleContext(PropositionContext.class,i);
		}
		public List<PropositionContext> proposition() {
			return getRuleContexts(PropositionContext.class);
		}
		public TerminalNode AND(int i) {
			return getToken(PQLParser.AND, i);
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
		enterRule(_localctx, 108, RULE_conjunction);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(472);
			switch ( getInterpreter().adaptivePredict(_input,38,_ctx) ) {
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
			}
			setState(474); match(AND);
			setState(477);
			switch ( getInterpreter().adaptivePredict(_input,39,_ctx) ) {
			case 1:
				{
				setState(475); proposition();
				}
				break;

			case 2:
				{
				setState(476); logicalTest();
				}
				break;
			}
			setState(486);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AND) {
				{
				{
				setState(479); match(AND);
				setState(482);
				switch ( getInterpreter().adaptivePredict(_input,40,_ctx) ) {
				case 1:
					{
					setState(480); proposition();
					}
					break;

				case 2:
					{
					setState(481); logicalTest();
					}
					break;
				}
				}
				}
				setState(488);
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
		public LogicalTestContext logicalTest() {
			return getRuleContext(LogicalTestContext.class,0);
		}
		public DisjunctionContext disjunction() {
			return getRuleContext(DisjunctionContext.class,0);
		}
		public TerminalNode LP() { return getToken(PQLParser.LP, 0); }
		public ConjunctionContext conjunction() {
			return getRuleContext(ConjunctionContext.class,0);
		}
		public TerminalNode RP() { return getToken(PQLParser.RP, 0); }
		public PropositionContext proposition() {
			return getRuleContext(PropositionContext.class,0);
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
		enterRule(_localctx, 110, RULE_parentheses);
		try {
			setState(505);
			switch ( getInterpreter().adaptivePredict(_input,42,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(489); match(LP);
				setState(490); proposition();
				setState(491); match(RP);
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(493); match(LP);
				setState(494); conjunction();
				setState(495); match(RP);
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(497); match(LP);
				setState(498); disjunction();
				setState(499); match(RP);
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(501); match(LP);
				setState(502); logicalTest();
				setState(503); match(RP);
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
		public IntersectionContext intersection() {
			return getRuleContext(IntersectionContext.class,0);
		}
		public DifferenceContext difference() {
			return getRuleContext(DifferenceContext.class,0);
		}
		public UniverseContext universe() {
			return getRuleContext(UniverseContext.class,0);
		}
		public SetOfTasksLiteralContext setOfTasksLiteral() {
			return getRuleContext(SetOfTasksLiteralContext.class,0);
		}
		public SetOfTasksConstructionContext setOfTasksConstruction() {
			return getRuleContext(SetOfTasksConstructionContext.class,0);
		}
		public TerminalNode LP() { return getToken(PQLParser.LP, 0); }
		public UnionContext union() {
			return getRuleContext(UnionContext.class,0);
		}
		public TerminalNode RP() { return getToken(PQLParser.RP, 0); }
		public SetOfTasksParenthesesContext setOfTasksParentheses() {
			return getRuleContext(SetOfTasksParenthesesContext.class,0);
		}
		public VarNameContext varName() {
			return getRuleContext(VarNameContext.class,0);
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
		enterRule(_localctx, 112, RULE_setOfTasksParentheses);
		try {
			setState(539);
			switch ( getInterpreter().adaptivePredict(_input,43,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(507); match(LP);
				setState(508); varName();
				setState(509); match(RP);
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(511); match(LP);
				setState(512); universe();
				setState(513); match(RP);
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(515); match(LP);
				setState(516); setOfTasksLiteral();
				setState(517); match(RP);
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(519); match(LP);
				setState(520); setOfTasksConstruction();
				setState(521); match(RP);
				}
				break;

			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(523); match(LP);
				setState(524); union();
				setState(525); match(RP);
				}
				break;

			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(527); match(LP);
				setState(528); difference();
				setState(529); match(RP);
				}
				break;

			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(531); match(LP);
				setState(532); intersection();
				setState(533); match(RP);
				}
				break;

			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(535); match(LP);
				setState(536); setOfTasksParentheses();
				setState(537); match(RP);
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
		enterRule(_localctx, 114, RULE_identical);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(541); match(EQUALS);
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
		public TerminalNode EQUALS() { return getToken(PQLParser.EQUALS, 0); }
		public TerminalNode NOT() { return getToken(PQLParser.NOT, 0); }
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
		enterRule(_localctx, 116, RULE_different);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(543); match(NOT);
			setState(544); match(EQUALS);
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
		enterRule(_localctx, 118, RULE_overlapsWith);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(546); match(OVERLAPS);
			setState(547); match(WITH);
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
		public TerminalNode OF() { return getToken(PQLParser.OF, 0); }
		public TerminalNode SUBSET() { return getToken(PQLParser.SUBSET, 0); }
		public TerminalNode IS() { return getToken(PQLParser.IS, 0); }
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
		enterRule(_localctx, 120, RULE_subsetOf);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(549); match(IS);
			setState(550); match(SUBSET);
			setState(551); match(OF);
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
		public TerminalNode PROPER() { return getToken(PQLParser.PROPER, 0); }
		public TerminalNode OF() { return getToken(PQLParser.OF, 0); }
		public TerminalNode SUBSET() { return getToken(PQLParser.SUBSET, 0); }
		public TerminalNode IS() { return getToken(PQLParser.IS, 0); }
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
		enterRule(_localctx, 122, RULE_properSubsetOf);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(553); match(IS);
			setState(554); match(PROPER);
			setState(555); match(SUBSET);
			setState(556); match(OF);
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
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\3;\u0231\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\3\2\3\2\5\2\u0081\n\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3\u008a"+
		"\n\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4\u0095\n\4\3\4\3\4\3\5\7\5"+
		"\u009a\n\5\f\5\16\5\u009d\13\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\b\3\b\3\b"+
		"\7\b\u00a9\n\b\f\b\16\b\u00ac\13\b\3\t\3\t\5\t\u00b0\n\t\3\n\3\n\3\n\7"+
		"\n\u00b5\n\n\f\n\16\n\u00b8\13\n\3\13\3\13\5\13\u00bc\n\13\3\f\3\f\3\r"+
		"\3\r\3\16\3\16\3\17\3\17\3\17\3\17\5\17\u00c8\n\17\3\20\3\20\5\20\u00cc"+
		"\n\20\3\20\3\20\3\20\5\20\u00d1\n\20\3\21\3\21\3\21\3\21\7\21\u00d7\n"+
		"\21\f\21\16\21\u00da\13\21\5\21\u00dc\n\21\3\21\3\21\3\22\3\22\3\22\3"+
		"\22\7\22\u00e4\n\22\f\22\16\22\u00e7\13\22\5\22\u00e9\n\22\3\22\3\22\3"+
		"\23\3\23\5\23\u00ef\n\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\5\24"+
		"\u00f9\n\24\5\24\u00fb\n\24\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\5"+
		"\30\u0105\n\30\3\31\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32"+
		"\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36\3\37"+
		"\3\37\3\37\3\37\5\37\u0123\n\37\3 \3 \3 \3 \3 \3 \3 \3 \3 \5 \u012e\n"+
		" \3!\3!\3!\3!\3!\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3#\3#\3#\3#\3#\3$\3$\3$\3"+
		"$\3$\3$\3$\3%\3%\5%\u014a\n%\3&\3&\3&\3&\3&\3&\3&\3&\3&\3\'\3\'\3\'\3"+
		"\'\3\'\3\'\3\'\3\'\3\'\3(\3(\3)\3)\5)\u0162\n)\3*\3*\3*\3*\3+\3+\3+\3"+
		"+\3,\3,\3,\3,\3,\5,\u0171\n,\3-\3-\3.\3.\3.\3.\5.\u0179\n.\3/\3/\3/\5"+
		"/\u017e\n/\3/\3/\3/\3/\5/\u0184\n/\3/\3/\3/\3/\5/\u018a\n/\7/\u018c\n"+
		"/\f/\16/\u018f\13/\3\60\3\60\5\60\u0193\n\60\3\60\3\60\3\60\5\60\u0198"+
		"\n\60\3\60\3\60\3\60\5\60\u019d\n\60\7\60\u019f\n\60\f\60\16\60\u01a2"+
		"\13\60\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\61\5\61\u01ac\n\61\3\62\3"+
		"\62\3\62\3\63\3\63\3\63\3\63\3\64\3\64\3\64\3\64\3\64\3\65\3\65\3\65\3"+
		"\65\3\66\3\66\3\66\3\66\3\66\3\67\3\67\3\67\5\67\u01c6\n\67\3\67\3\67"+
		"\3\67\3\67\5\67\u01cc\n\67\3\67\3\67\3\67\3\67\5\67\u01d2\n\67\7\67\u01d4"+
		"\n\67\f\67\16\67\u01d7\13\67\38\38\58\u01db\n8\38\38\38\58\u01e0\n8\3"+
		"8\38\38\58\u01e5\n8\78\u01e7\n8\f8\168\u01ea\138\39\39\39\39\39\39\39"+
		"\39\39\39\39\39\39\39\39\39\59\u01fc\n9\3:\3:\3:\3:\3:\3:\3:\3:\3:\3:"+
		"\3:\3:\3:\3:\3:\3:\3:\3:\3:\3:\3:\3:\3:\3:\3:\3:\3:\3:\3:\3:\3:\3:\5:"+
		"\u021e\n:\3;\3;\3<\3<\3<\3=\3=\3=\3>\3>\3>\3>\3?\3?\3?\3?\3?\3?\2@\2\4"+
		"\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@BDFHJLNP"+
		"RTVXZ\\^`bdfhjlnprtvxz|\2\7\4\2((**\3\2\63\64\3\2\66;\3\2(*\3\2./\u023e"+
		"\2\u0080\3\2\2\2\4\u0082\3\2\2\2\6\u008d\3\2\2\2\b\u009b\3\2\2\2\n\u009e"+
		"\3\2\2\2\f\u00a3\3\2\2\2\16\u00a5\3\2\2\2\20\u00af\3\2\2\2\22\u00b1\3"+
		"\2\2\2\24\u00bb\3\2\2\2\26\u00bd\3\2\2\2\30\u00bf\3\2\2\2\32\u00c1\3\2"+
		"\2\2\34\u00c7\3\2\2\2\36\u00d0\3\2\2\2 \u00d2\3\2\2\2\"\u00df\3\2\2\2"+
		"$\u00ee\3\2\2\2&\u00fa\3\2\2\2(\u00fc\3\2\2\2*\u00fe\3\2\2\2,\u0100\3"+
		"\2\2\2.\u0104\3\2\2\2\60\u0106\3\2\2\2\62\u010c\3\2\2\2\64\u0116\3\2\2"+
		"\2\66\u0118\3\2\2\28\u011a\3\2\2\2:\u011c\3\2\2\2<\u0122\3\2\2\2>\u012d"+
		"\3\2\2\2@\u012f\3\2\2\2B\u0134\3\2\2\2D\u013b\3\2\2\2F\u0140\3\2\2\2H"+
		"\u0149\3\2\2\2J\u014b\3\2\2\2L\u0154\3\2\2\2N\u015d\3\2\2\2P\u0161\3\2"+
		"\2\2R\u0163\3\2\2\2T\u0167\3\2\2\2V\u0170\3\2\2\2X\u0172\3\2\2\2Z\u0178"+
		"\3\2\2\2\\\u017d\3\2\2\2^\u0192\3\2\2\2`\u01ab\3\2\2\2b\u01ad\3\2\2\2"+
		"d\u01b0\3\2\2\2f\u01b4\3\2\2\2h\u01b9\3\2\2\2j\u01bd\3\2\2\2l\u01c5\3"+
		"\2\2\2n\u01da\3\2\2\2p\u01fb\3\2\2\2r\u021d\3\2\2\2t\u021f\3\2\2\2v\u0221"+
		"\3\2\2\2x\u0224\3\2\2\2z\u0227\3\2\2\2|\u022b\3\2\2\2~\u0081\5\4\3\2\177"+
		"\u0081\5\6\4\2\u0080~\3\2\2\2\u0080\177\3\2\2\2\u0081\3\3\2\2\2\u0082"+
		"\u0083\5\b\5\2\u0083\u0084\7\32\2\2\u0084\u0085\5\16\b\2\u0085\u0086\7"+
		"\35\2\2\u0086\u0089\5\22\n\2\u0087\u0088\7\36\2\2\u0088\u008a\5<\37\2"+
		"\u0089\u0087\3\2\2\2\u0089\u008a\3\2\2\2\u008a\u008b\3\2\2\2\u008b\u008c"+
		"\7\21\2\2\u008c\5\3\2\2\2\u008d\u008e\5\b\5\2\u008e\u008f\7\33\2\2\u008f"+
		"\u0090\5\"\22\2\u0090\u0091\7\34\2\2\u0091\u0094\5\22\n\2\u0092\u0093"+
		"\7\36\2\2\u0093\u0095\5<\37\2\u0094\u0092\3\2\2\2\u0094\u0095\3\2\2\2"+
		"\u0095\u0096\3\2\2\2\u0096\u0097\7\21\2\2\u0097\7\3\2\2\2\u0098\u009a"+
		"\5\n\6\2\u0099\u0098\3\2\2\2\u009a\u009d\3\2\2\2\u009b\u0099\3\2\2\2\u009b"+
		"\u009c\3\2\2\2\u009c\t\3\2\2\2\u009d\u009b\3\2\2\2\u009e\u009f\5\f\7\2"+
		"\u009f\u00a0\7\23\2\2\u00a0\u00a1\5\34\17\2\u00a1\u00a2\7\21\2\2\u00a2"+
		"\13\3\2\2\2\u00a3\u00a4\7\6\2\2\u00a4\r\3\2\2\2\u00a5\u00aa\5\20\t\2\u00a6"+
		"\u00a7\7\22\2\2\u00a7\u00a9\5\20\t\2\u00a8\u00a6\3\2\2\2\u00a9\u00ac\3"+
		"\2\2\2\u00aa\u00a8\3\2\2\2\u00aa\u00ab\3\2\2\2\u00ab\17\3\2\2\2\u00ac"+
		"\u00aa\3\2\2\2\u00ad\u00b0\5\26\f\2\u00ae\u00b0\5\30\r\2\u00af\u00ad\3"+
		"\2\2\2\u00af\u00ae\3\2\2\2\u00b0\21\3\2\2\2\u00b1\u00b6\5\24\13\2\u00b2"+
		"\u00b3\7\22\2\2\u00b3\u00b5\5\24\13\2\u00b4\u00b2\3\2\2\2\u00b5\u00b8"+
		"\3\2\2\2\u00b6\u00b4\3\2\2\2\u00b6\u00b7\3\2\2\2\u00b7\23\3\2\2\2\u00b8"+
		"\u00b6\3\2\2\2\u00b9\u00bc\5\26\f\2\u00ba\u00bc\5\32\16\2\u00bb\u00b9"+
		"\3\2\2\2\u00bb\u00ba\3\2\2\2\u00bc\25\3\2\2\2\u00bd\u00be\7\3\2\2\u00be"+
		"\27\3\2\2\2\u00bf\u00c0\7\4\2\2\u00c0\31\3\2\2\2\u00c1\u00c2\7\5\2\2\u00c2"+
		"\33\3\2\2\2\u00c3\u00c8\5\36\20\2\u00c4\u00c8\5\\/\2\u00c5\u00c8\5^\60"+
		"\2\u00c6\u00c8\5`\61\2\u00c7\u00c3\3\2\2\2\u00c7\u00c4\3\2\2\2\u00c7\u00c5"+
		"\3\2\2\2\u00c7\u00c6\3\2\2\2\u00c8\35\3\2\2\2\u00c9\u00cc\5\f\7\2\u00ca"+
		"\u00cc\5\26\f\2\u00cb\u00c9\3\2\2\2\u00cb\u00ca\3\2\2\2\u00cc\u00d1\3"+
		"\2\2\2\u00cd\u00d1\5 \21\2\u00ce\u00d1\5.\30\2\u00cf\u00d1\5r:\2\u00d0"+
		"\u00cb\3\2\2\2\u00d0\u00cd\3\2\2\2\u00d0\u00ce\3\2\2\2\u00d0\u00cf\3\2"+
		"\2\2\u00d1\37\3\2\2\2\u00d2\u00db\7\n\2\2\u00d3\u00d8\5&\24\2\u00d4\u00d5"+
		"\7\22\2\2\u00d5\u00d7\5&\24\2\u00d6\u00d4\3\2\2\2\u00d7\u00da\3\2\2\2"+
		"\u00d8\u00d6\3\2\2\2\u00d8\u00d9\3\2\2\2\u00d9\u00dc\3\2\2\2\u00da\u00d8"+
		"\3\2\2\2\u00db\u00d3\3\2\2\2\u00db\u00dc\3\2\2\2\u00dc\u00dd\3\2\2\2\u00dd"+
		"\u00de\7\13\2\2\u00de!\3\2\2\2\u00df\u00e8\7\16\2\2\u00e0\u00e5\5$\23"+
		"\2\u00e1\u00e2\7\22\2\2\u00e2\u00e4\5$\23\2\u00e3\u00e1\3\2\2\2\u00e4"+
		"\u00e7\3\2\2\2\u00e5\u00e3\3\2\2\2\u00e5\u00e6\3\2\2\2\u00e6\u00e9\3\2"+
		"\2\2\u00e7\u00e5\3\2\2\2\u00e8\u00e0\3\2\2\2\u00e8\u00e9\3\2\2\2\u00e9"+
		"\u00ea\3\2\2\2\u00ea\u00eb\7\17\2\2\u00eb#\3\2\2\2\u00ec\u00ef\5\26\f"+
		"\2\u00ed\u00ef\5&\24\2\u00ee\u00ec\3\2\2\2\u00ee\u00ed\3\2\2\2\u00ef%"+
		"\3\2\2\2\u00f0\u00f1\5(\25\2\u00f1\u00f2\5*\26\2\u00f2\u00fb\3\2\2\2\u00f3"+
		"\u00f8\5*\26\2\u00f4\u00f5\7\f\2\2\u00f5\u00f6\5,\27\2\u00f6\u00f7\7\r"+
		"\2\2\u00f7\u00f9\3\2\2\2\u00f8\u00f4\3\2\2\2\u00f8\u00f9\3\2\2\2\u00f9"+
		"\u00fb\3\2\2\2\u00fa\u00f0\3\2\2\2\u00fa\u00f3\3\2\2\2\u00fb\'\3\2\2\2"+
		"\u00fc\u00fd\7\24\2\2\u00fd)\3\2\2\2\u00fe\u00ff\7\5\2\2\u00ff+\3\2\2"+
		"\2\u0100\u0101\7\7\2\2\u0101-\3\2\2\2\u0102\u0105\5\60\31\2\u0103\u0105"+
		"\5\62\32\2\u0104\u0102\3\2\2\2\u0104\u0103\3\2\2\2\u0105/\3\2\2\2\u0106"+
		"\u0107\7$\2\2\u0107\u0108\5\66\34\2\u0108\u0109\7\b\2\2\u0109\u010a\5"+
		"\34\17\2\u010a\u010b\7\t\2\2\u010b\61\3\2\2\2\u010c\u010d\7$\2\2\u010d"+
		"\u010e\5:\36\2\u010e\u010f\7\b\2\2\u010f\u0110\5\34\17\2\u0110\u0111\7"+
		"\22\2\2\u0111\u0112\5\34\17\2\u0112\u0113\7\22\2\2\u0113\u0114\5\64\33"+
		"\2\u0114\u0115\7\t\2\2\u0115\63\3\2\2\2\u0116\u0117\t\2\2\2\u0117\65\3"+
		"\2\2\2\u0118\u0119\t\3\2\2\u0119\67\3\2\2\2\u011a\u011b\7\65\2\2\u011b"+
		"9\3\2\2\2\u011c\u011d\t\4\2\2\u011d;\3\2\2\2\u011e\u0123\5> \2\u011f\u0123"+
		"\5n8\2\u0120\u0123\5l\67\2\u0121\u0123\5Z.\2\u0122\u011e\3\2\2\2\u0122"+
		"\u011f\3\2\2\2\u0122\u0120\3\2\2\2\u0122\u0121\3\2\2\2\u0123=\3\2\2\2"+
		"\u0124\u012e\5@!\2\u0125\u012e\5B\"\2\u0126\u012e\5D#\2\u0127\u012e\5"+
		"F$\2\u0128\u012e\5H%\2\u0129\u012e\5P)\2\u012a\u012e\5X-\2\u012b\u012e"+
		"\5p9\2\u012c\u012e\5b\62\2\u012d\u0124\3\2\2\2\u012d\u0125\3\2\2\2\u012d"+
		"\u0126\3\2\2\2\u012d\u0127\3\2\2\2\u012d\u0128\3\2\2\2\u012d\u0129\3\2"+
		"\2\2\u012d\u012a\3\2\2\2\u012d\u012b\3\2\2\2\u012d\u012c\3\2\2\2\u012e"+
		"?\3\2\2\2\u012f\u0130\5\66\34\2\u0130\u0131\7\b\2\2\u0131\u0132\5&\24"+
		"\2\u0132\u0133\7\t\2\2\u0133A\3\2\2\2\u0134\u0135\5:\36\2\u0135\u0136"+
		"\7\b\2\2\u0136\u0137\5&\24\2\u0137\u0138\7\22\2\2\u0138\u0139\5&\24\2"+
		"\u0139\u013a\7\t\2\2\u013aC\3\2\2\2\u013b\u013c\58\35\2\u013c\u013d\7"+
		"\b\2\2\u013d\u013e\5\"\22\2\u013e\u013f\7\t\2\2\u013fE\3\2\2\2\u0140\u0141"+
		"\5\66\34\2\u0141\u0142\7\b\2\2\u0142\u0143\5\34\17\2\u0143\u0144\7\22"+
		"\2\2\u0144\u0145\5\64\33\2\u0145\u0146\7\t\2\2\u0146G\3\2\2\2\u0147\u014a"+
		"\5J&\2\u0148\u014a\5L\'\2\u0149\u0147\3\2\2\2\u0149\u0148\3\2\2\2\u014a"+
		"I\3\2\2\2\u014b\u014c\5:\36\2\u014c\u014d\7\b\2\2\u014d\u014e\5&\24\2"+
		"\u014e\u014f\7\22\2\2\u014f\u0150\5\34\17\2\u0150\u0151\7\22\2\2\u0151"+
		"\u0152\5\64\33\2\u0152\u0153\7\t\2\2\u0153K\3\2\2\2\u0154\u0155\5:\36"+
		"\2\u0155\u0156\7\b\2\2\u0156\u0157\5\34\17\2\u0157\u0158\7\22\2\2\u0158"+
		"\u0159\5\34\17\2\u0159\u015a\7\22\2\2\u015a\u015b\5N(\2\u015b\u015c\7"+
		"\t\2\2\u015cM\3\2\2\2\u015d\u015e\t\5\2\2\u015eO\3\2\2\2\u015f\u0162\5"+
		"R*\2\u0160\u0162\5T+\2\u0161\u015f\3\2\2\2\u0161\u0160\3\2\2\2\u0162Q"+
		"\3\2\2\2\u0163\u0164\5&\24\2\u0164\u0165\7+\2\2\u0165\u0166\5\34\17\2"+
		"\u0166S\3\2\2\2\u0167\u0168\5\34\17\2\u0168\u0169\5V,\2\u0169\u016a\5"+
		"\34\17\2\u016aU\3\2\2\2\u016b\u0171\5t;\2\u016c\u0171\5v<\2\u016d\u0171"+
		"\5x=\2\u016e\u0171\5z>\2\u016f\u0171\5|?\2\u0170\u016b\3\2\2\2\u0170\u016c"+
		"\3\2\2\2\u0170\u016d\3\2\2\2\u0170\u016e\3\2\2\2\u0170\u016f\3\2\2\2\u0171"+
		"W\3\2\2\2\u0172\u0173\t\6\2\2\u0173Y\3\2\2\2\u0174\u0179\5d\63\2\u0175"+
		"\u0179\5f\64\2\u0176\u0179\5h\65\2\u0177\u0179\5j\66\2\u0178\u0174\3\2"+
		"\2\2\u0178\u0175\3\2\2\2\u0178\u0176\3\2\2\2\u0178\u0177\3\2\2\2\u0179"+
		"[\3\2\2\2\u017a\u017e\5\36\20\2\u017b\u017e\5`\61\2\u017c\u017e\5^\60"+
		"\2\u017d\u017a\3\2\2\2\u017d\u017b\3\2\2\2\u017d\u017c\3\2\2\2\u017e\u017f"+
		"\3\2\2\2\u017f\u0183\7\60\2\2\u0180\u0184\5\36\20\2\u0181\u0184\5`\61"+
		"\2\u0182\u0184\5^\60\2\u0183\u0180\3\2\2\2\u0183\u0181\3\2\2\2\u0183\u0182"+
		"\3\2\2\2\u0184\u018d\3\2\2\2\u0185\u0189\7\60\2\2\u0186\u018a\5\36\20"+
		"\2\u0187\u018a\5`\61\2\u0188\u018a\5^\60\2\u0189\u0186\3\2\2\2\u0189\u0187"+
		"\3\2\2\2\u0189\u0188\3\2\2\2\u018a\u018c\3\2\2\2\u018b\u0185\3\2\2\2\u018c"+
		"\u018f\3\2\2\2\u018d\u018b\3\2\2\2\u018d\u018e\3\2\2\2\u018e]\3\2\2\2"+
		"\u018f\u018d\3\2\2\2\u0190\u0193\5\36\20\2\u0191\u0193\5`\61\2\u0192\u0190"+
		"\3\2\2\2\u0192\u0191\3\2\2\2\u0193\u0194\3\2\2\2\u0194\u0197\7\61\2\2"+
		"\u0195\u0198\5\36\20\2\u0196\u0198\5`\61\2\u0197\u0195\3\2\2\2\u0197\u0196"+
		"\3\2\2\2\u0198\u01a0\3\2\2\2\u0199\u019c\7\61\2\2\u019a\u019d\5\36\20"+
		"\2\u019b\u019d\5`\61\2\u019c\u019a\3\2\2\2\u019c\u019b\3\2\2\2\u019d\u019f"+
		"\3\2\2\2\u019e\u0199\3\2\2\2\u019f\u01a2\3\2\2\2\u01a0\u019e\3\2\2\2\u01a0"+
		"\u01a1\3\2\2\2\u01a1_\3\2\2\2\u01a2\u01a0\3\2\2\2\u01a3\u01a4\5\36\20"+
		"\2\u01a4\u01a5\7\62\2\2\u01a5\u01a6\5\36\20\2\u01a6\u01ac\3\2\2\2\u01a7"+
		"\u01a8\5\36\20\2\u01a8\u01a9\7\62\2\2\u01a9\u01aa\5`\61\2\u01aa\u01ac"+
		"\3\2\2\2\u01ab\u01a3\3\2\2\2\u01ab\u01a7\3\2\2\2\u01aca\3\2\2\2\u01ad"+
		"\u01ae\7%\2\2\u01ae\u01af\5> \2\u01afc\3\2\2\2\u01b0\u01b1\5> \2\u01b1"+
		"\u01b2\7,\2\2\u01b2\u01b3\7.\2\2\u01b3e\3\2\2\2\u01b4\u01b5\5> \2\u01b5"+
		"\u01b6\7,\2\2\u01b6\u01b7\7%\2\2\u01b7\u01b8\7.\2\2\u01b8g\3\2\2\2\u01b9"+
		"\u01ba\5> \2\u01ba\u01bb\7,\2\2\u01bb\u01bc\7/\2\2\u01bci\3\2\2\2\u01bd"+
		"\u01be\5> \2\u01be\u01bf\7,\2\2\u01bf\u01c0\7%\2\2\u01c0\u01c1\7/\2\2"+
		"\u01c1k\3\2\2\2\u01c2\u01c6\5> \2\u01c3\u01c6\5Z.\2\u01c4\u01c6\5n8\2"+
		"\u01c5\u01c2\3\2\2\2\u01c5\u01c3\3\2\2\2\u01c5\u01c4\3\2\2\2\u01c6\u01c7"+
		"\3\2\2\2\u01c7\u01cb\7\'\2\2\u01c8\u01cc\5> \2\u01c9\u01cc\5Z.\2\u01ca"+
		"\u01cc\5n8\2\u01cb\u01c8\3\2\2\2\u01cb\u01c9\3\2\2\2\u01cb\u01ca\3\2\2"+
		"\2\u01cc\u01d5\3\2\2\2\u01cd\u01d1\7\'\2\2\u01ce\u01d2\5> \2\u01cf\u01d2"+
		"\5Z.\2\u01d0\u01d2\5n8\2\u01d1\u01ce\3\2\2\2\u01d1\u01cf\3\2\2\2\u01d1"+
		"\u01d0\3\2\2\2\u01d2\u01d4\3\2\2\2\u01d3\u01cd\3\2\2\2\u01d4\u01d7\3\2"+
		"\2\2\u01d5\u01d3\3\2\2\2\u01d5\u01d6\3\2\2\2\u01d6m\3\2\2\2\u01d7\u01d5"+
		"\3\2\2\2\u01d8\u01db\5> \2\u01d9\u01db\5Z.\2\u01da\u01d8\3\2\2\2\u01da"+
		"\u01d9\3\2\2\2\u01db\u01dc\3\2\2\2\u01dc\u01df\7&\2\2\u01dd\u01e0\5> "+
		"\2\u01de\u01e0\5Z.\2\u01df\u01dd\3\2\2\2\u01df\u01de\3\2\2\2\u01e0\u01e8"+
		"\3\2\2\2\u01e1\u01e4\7&\2\2\u01e2\u01e5\5> \2\u01e3\u01e5\5Z.\2\u01e4"+
		"\u01e2\3\2\2\2\u01e4\u01e3\3\2\2\2\u01e5\u01e7\3\2\2\2\u01e6\u01e1\3\2"+
		"\2\2\u01e7\u01ea\3\2\2\2\u01e8\u01e6\3\2\2\2\u01e8\u01e9\3\2\2\2\u01e9"+
		"o\3\2\2\2\u01ea\u01e8\3\2\2\2\u01eb\u01ec\7\b\2\2\u01ec\u01ed\5> \2\u01ed"+
		"\u01ee\7\t\2\2\u01ee\u01fc\3\2\2\2\u01ef\u01f0\7\b\2\2\u01f0\u01f1\5n"+
		"8\2\u01f1\u01f2\7\t\2\2\u01f2\u01fc\3\2\2\2\u01f3\u01f4\7\b\2\2\u01f4"+
		"\u01f5\5l\67\2\u01f5\u01f6\7\t\2\2\u01f6\u01fc\3\2\2\2\u01f7\u01f8\7\b"+
		"\2\2\u01f8\u01f9\5Z.\2\u01f9\u01fa\7\t\2\2\u01fa\u01fc\3\2\2\2\u01fb\u01eb"+
		"\3\2\2\2\u01fb\u01ef\3\2\2\2\u01fb\u01f3\3\2\2\2\u01fb\u01f7\3\2\2\2\u01fc"+
		"q\3\2\2\2\u01fd\u01fe\7\b\2\2\u01fe\u01ff\5\f\7\2\u01ff\u0200\7\t\2\2"+
		"\u0200\u021e\3\2\2\2\u0201\u0202\7\b\2\2\u0202\u0203\5\26\f\2\u0203\u0204"+
		"\7\t\2\2\u0204\u021e\3\2\2\2\u0205\u0206\7\b\2\2\u0206\u0207\5 \21\2\u0207"+
		"\u0208\7\t\2\2\u0208\u021e\3\2\2\2\u0209\u020a\7\b\2\2\u020a\u020b\5."+
		"\30\2\u020b\u020c\7\t\2\2\u020c\u021e\3\2\2\2\u020d\u020e\7\b\2\2\u020e"+
		"\u020f\5\\/\2\u020f\u0210\7\t\2\2\u0210\u021e\3\2\2\2\u0211\u0212\7\b"+
		"\2\2\u0212\u0213\5`\61\2\u0213\u0214\7\t\2\2\u0214\u021e\3\2\2\2\u0215"+
		"\u0216\7\b\2\2\u0216\u0217\5^\60\2\u0217\u0218\7\t\2\2\u0218\u021e\3\2"+
		"\2\2\u0219\u021a\7\b\2\2\u021a\u021b\5r:\2\u021b\u021c\7\t\2\2\u021c\u021e"+
		"\3\2\2\2\u021d\u01fd\3\2\2\2\u021d\u0201\3\2\2\2\u021d\u0205\3\2\2\2\u021d"+
		"\u0209\3\2\2\2\u021d\u020d\3\2\2\2\u021d\u0211\3\2\2\2\u021d\u0215\3\2"+
		"\2\2\u021d\u0219\3\2\2\2\u021es\3\2\2\2\u021f\u0220\7\37\2\2\u0220u\3"+
		"\2\2\2\u0221\u0222\7%\2\2\u0222\u0223\7\37\2\2\u0223w\3\2\2\2\u0224\u0225"+
		"\7 \2\2\u0225\u0226\7!\2\2\u0226y\3\2\2\2\u0227\u0228\7,\2\2\u0228\u0229"+
		"\7\"\2\2\u0229\u022a\7-\2\2\u022a{\3\2\2\2\u022b\u022c\7,\2\2\u022c\u022d"+
		"\7#\2\2\u022d\u022e\7\"\2\2\u022e\u022f\7-\2\2\u022f}\3\2\2\2.\u0080\u0089"+
		"\u0094\u009b\u00aa\u00af\u00b6\u00bb\u00c7\u00cb\u00d0\u00d8\u00db\u00e5"+
		"\u00e8\u00ee\u00f8\u00fa\u0104\u0122\u012d\u0149\u0161\u0170\u0178\u017d"+
		"\u0183\u0189\u018d\u0192\u0197\u019c\u01a0\u01ab\u01c5\u01cb\u01d1\u01d5"+
		"\u01da\u01df\u01e4\u01e8\u01fb\u021d";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}