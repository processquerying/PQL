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
		UNIVERSE=1, ATTRIBUTE_ID=2, ATTRIBUTE_NAME=3, ATTRIBUTE_MODEL=4, STRING=5, 
		INTEGER=6, VARIABLE_NAME=7, SIMILARITY=8, LP=9, RP=10, LB=11, RB=12, LSB=13, 
		RSB=14, LTB=15, RTB=16, DQ=17, EOS=18, SEP=19, ASSIGN=20, TILDE=21, ESC_SEQ=22, 
		UNICODE_ESC=23, HEX_DIGIT=24, WS=25, LINE_COMMENT=26, SELECT=27, INSERT=28, 
		INTO=29, FROM=30, WHERE=31, EQUALS=32, OVERLAPS=33, WITH=34, SUBSET=35, 
		PROPER=36, GET_TASKS=37, NOT=38, AND=39, OR=40, ANY=41, EACH=42, ALL=43, 
		IN=44, IS=45, OF=46, TRUE=47, FALSE=48, UNKNOWN=49, UNION=50, INTERSECTION=51, 
		DIFFERENCE=52, CAN_OCCUR=53, ALWAYS_OCCURS=54, EXECUTES=55, CAN_CONFLICT=56, 
		CAN_COOCCUR=57, CONFLICT=58, COOCCUR=59, TOTAL_CAUSAL=60, TOTAL_CONCUR=61;
	public static final String[] tokenNames = {
		"<INVALID>", "'*'", "'id'", "'name'", "'model'", "STRING", "INTEGER", 
		"VARIABLE_NAME", "SIMILARITY", "'('", "')'", "'{'", "'}'", "'['", "']'", 
		"'<'", "'>'", "'\"'", "';'", "','", "'='", "'~'", "ESC_SEQ", "UNICODE_ESC", 
		"HEX_DIGIT", "WS", "LINE_COMMENT", "'SELECT'", "'INSERT'", "'INTO'", "'FROM'", 
		"'WHERE'", "'EQUALS'", "'OVERLAPS'", "'WITH'", "'SUBSET'", "'PROPER'", 
		"'GetTasks'", "'NOT'", "'AND'", "'OR'", "'ANY'", "'EACH'", "'ALL'", "'IN'", 
		"'IS'", "'OF'", "'TRUE'", "'FALSE'", "'UNKNOWN'", "'UNION'", "'INTERSECT'", 
		"'EXCEPT'", "'CanOccur'", "'AlwaysOccurs'", "'Executes'", "'CanConflict'", 
		"'CanCooccur'", "'Conflict'", "'Cooccur'", "'TotalCausal'", "'TotalConcurrent'"
	};
	public static final int
		RULE_query = 0, RULE_selectQuery = 1, RULE_insertQuery = 2, RULE_variables = 3, 
		RULE_variable = 4, RULE_varName = 5, RULE_attributes = 6, RULE_attribute = 7, 
		RULE_locations = 8, RULE_location = 9, RULE_universe = 10, RULE_attributeID = 11, 
		RULE_attributeName = 12, RULE_attributeModel = 13, RULE_locationID = 14, 
		RULE_locationDirectory = 15, RULE_setOfTasks = 16, RULE_tasks = 17, RULE_setOfTasksLiteral = 18, 
		RULE_trace = 19, RULE_event = 20, RULE_task = 21, RULE_approximate = 22, 
		RULE_label = 23, RULE_similarity = 24, RULE_setOfTasksConstruction = 25, 
		RULE_unaryPredicateConstruction = 26, RULE_binaryPredicateConstruction = 27, 
		RULE_anyAll = 28, RULE_unaryPredicateName = 29, RULE_unaryTracePredicateName = 30, 
		RULE_binaryPredicateName = 31, RULE_predicate = 32, RULE_proposition = 33, 
		RULE_unaryPredicate = 34, RULE_binaryPredicate = 35, RULE_unaryTracePredicate = 36, 
		RULE_unaryPredicateMacro = 37, RULE_binaryPredicateMacro = 38, RULE_binaryPredicateMacroTaskSet = 39, 
		RULE_binaryPredicateMacroSetSet = 40, RULE_anyEachAll = 41, RULE_setPredicate = 42, 
		RULE_taskInSetOfTasks = 43, RULE_setComparison = 44, RULE_setComparisonOperator = 45, 
		RULE_truthValue = 46, RULE_logicalTest = 47, RULE_union = 48, RULE_intersection = 49, 
		RULE_difference = 50, RULE_negation = 51, RULE_isTrue = 52, RULE_isNotTrue = 53, 
		RULE_isFalse = 54, RULE_isNotFalse = 55, RULE_isUnknown = 56, RULE_isNotUnknown = 57, 
		RULE_disjunction = 58, RULE_conjunction = 59, RULE_parentheses = 60, RULE_setOfTasksParentheses = 61, 
		RULE_identical = 62, RULE_different = 63, RULE_overlapsWith = 64, RULE_subsetOf = 65, 
		RULE_properSubsetOf = 66;
	public static final String[] ruleNames = {
		"query", "selectQuery", "insertQuery", "variables", "variable", "varName", 
		"attributes", "attribute", "locations", "location", "universe", "attributeID", 
		"attributeName", "attributeModel", "locationID", "locationDirectory", 
		"setOfTasks", "tasks", "setOfTasksLiteral", "trace", "event", "task", 
		"approximate", "label", "similarity", "setOfTasksConstruction", "unaryPredicateConstruction", 
		"binaryPredicateConstruction", "anyAll", "unaryPredicateName", "unaryTracePredicateName", 
		"binaryPredicateName", "predicate", "proposition", "unaryPredicate", "binaryPredicate", 
		"unaryTracePredicate", "unaryPredicateMacro", "binaryPredicateMacro", 
		"binaryPredicateMacroTaskSet", "binaryPredicateMacroSetSet", "anyEachAll", 
		"setPredicate", "taskInSetOfTasks", "setComparison", "setComparisonOperator", 
		"truthValue", "logicalTest", "union", "intersection", "difference", "negation", 
		"isTrue", "isNotTrue", "isFalse", "isNotFalse", "isUnknown", "isNotUnknown", 
		"disjunction", "conjunction", "parentheses", "setOfTasksParentheses", 
		"identical", "different", "overlapsWith", "subsetOf", "properSubsetOf"
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
			setState(136);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(134); selectQuery();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(135); insertQuery();
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
			setState(138); variables();
			setState(139); match(SELECT);
			setState(140); attributes();
			setState(141); match(FROM);
			setState(142); locations();
			setState(145);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(143); match(WHERE);
				setState(144); predicate();
				}
			}

			setState(147); match(EOS);
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
			setState(149); variables();
			setState(150); match(INSERT);
			setState(151); trace();
			setState(152); match(INTO);
			setState(153); locations();
			setState(156);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(154); match(WHERE);
				setState(155); predicate();
				}
			}

			setState(158); match(EOS);
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
			setState(163);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==VARIABLE_NAME) {
				{
				{
				setState(160); variable();
				}
				}
				setState(165);
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
			setState(166); varName();
			setState(167); match(ASSIGN);
			setState(168); setOfTasks();
			setState(169); match(EOS);
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
			setState(171); match(VARIABLE_NAME);
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
			setState(173); attribute();
			setState(178);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SEP) {
				{
				{
				setState(174); match(SEP);
				setState(175); attribute();
				}
				}
				setState(180);
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
		public AttributeIDContext attributeID() {
			return getRuleContext(AttributeIDContext.class,0);
		}
		public AttributeNameContext attributeName() {
			return getRuleContext(AttributeNameContext.class,0);
		}
		public AttributeModelContext attributeModel() {
			return getRuleContext(AttributeModelContext.class,0);
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
			setState(185);
			switch (_input.LA(1)) {
			case UNIVERSE:
				enterOuterAlt(_localctx, 1);
				{
				setState(181); universe();
				}
				break;
			case ATTRIBUTE_ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(182); attributeID();
				}
				break;
			case ATTRIBUTE_NAME:
				enterOuterAlt(_localctx, 3);
				{
				setState(183); attributeName();
				}
				break;
			case ATTRIBUTE_MODEL:
				enterOuterAlt(_localctx, 4);
				{
				setState(184); attributeModel();
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
			setState(187); location();
			setState(192);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SEP) {
				{
				{
				setState(188); match(SEP);
				setState(189); location();
				}
				}
				setState(194);
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
		public LocationIDContext locationID() {
			return getRuleContext(LocationIDContext.class,0);
		}
		public LocationDirectoryContext locationDirectory() {
			return getRuleContext(LocationDirectoryContext.class,0);
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
			setState(198);
			switch (_input.LA(1)) {
			case UNIVERSE:
				enterOuterAlt(_localctx, 1);
				{
				setState(195); universe();
				}
				break;
			case INTEGER:
				enterOuterAlt(_localctx, 2);
				{
				setState(196); locationID();
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 3);
				{
				setState(197); locationDirectory();
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
			setState(200); match(UNIVERSE);
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

	public static class AttributeIDContext extends ParserRuleContext {
		public TerminalNode ATTRIBUTE_ID() { return getToken(PQLParser.ATTRIBUTE_ID, 0); }
		public AttributeIDContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attributeID; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).enterAttributeID(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).exitAttributeID(this);
		}
	}

	public final AttributeIDContext attributeID() throws RecognitionException {
		AttributeIDContext _localctx = new AttributeIDContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_attributeID);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(202); match(ATTRIBUTE_ID);
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
		enterRule(_localctx, 24, RULE_attributeName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(204); match(ATTRIBUTE_NAME);
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

	public static class AttributeModelContext extends ParserRuleContext {
		public TerminalNode ATTRIBUTE_MODEL() { return getToken(PQLParser.ATTRIBUTE_MODEL, 0); }
		public AttributeModelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attributeModel; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).enterAttributeModel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).exitAttributeModel(this);
		}
	}

	public final AttributeModelContext attributeModel() throws RecognitionException {
		AttributeModelContext _localctx = new AttributeModelContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_attributeModel);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(206); match(ATTRIBUTE_MODEL);
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

	public static class LocationIDContext extends ParserRuleContext {
		public TerminalNode INTEGER() { return getToken(PQLParser.INTEGER, 0); }
		public LocationIDContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_locationID; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).enterLocationID(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).exitLocationID(this);
		}
	}

	public final LocationIDContext locationID() throws RecognitionException {
		LocationIDContext _localctx = new LocationIDContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_locationID);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(208); match(INTEGER);
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

	public static class LocationDirectoryContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(PQLParser.STRING, 0); }
		public LocationDirectoryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_locationDirectory; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).enterLocationDirectory(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).exitLocationDirectory(this);
		}
	}

	public final LocationDirectoryContext locationDirectory() throws RecognitionException {
		LocationDirectoryContext _localctx = new LocationDirectoryContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_locationDirectory);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(210); match(STRING);
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
		enterRule(_localctx, 32, RULE_setOfTasks);
		try {
			setState(216);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(212); tasks();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(213); union();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(214); intersection();
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(215); difference();
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
		enterRule(_localctx, 34, RULE_tasks);
		try {
			setState(225);
			switch (_input.LA(1)) {
			case UNIVERSE:
			case VARIABLE_NAME:
				enterOuterAlt(_localctx, 1);
				{
				setState(220);
				switch (_input.LA(1)) {
				case VARIABLE_NAME:
					{
					setState(218); varName();
					}
					break;
				case UNIVERSE:
					{
					setState(219); universe();
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
				setState(222); setOfTasksLiteral();
				}
				break;
			case GET_TASKS:
				enterOuterAlt(_localctx, 3);
				{
				setState(223); setOfTasksConstruction();
				}
				break;
			case LP:
				enterOuterAlt(_localctx, 4);
				{
				setState(224); setOfTasksParentheses();
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
		enterRule(_localctx, 36, RULE_setOfTasksLiteral);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(227); match(LB);
			setState(236);
			_la = _input.LA(1);
			if (_la==STRING || _la==TILDE) {
				{
				setState(228); task();
				setState(233);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SEP) {
					{
					{
					setState(229); match(SEP);
					setState(230); task();
					}
					}
					setState(235);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(238); match(RB);
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
		enterRule(_localctx, 38, RULE_trace);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(240); match(LTB);
			setState(249);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << UNIVERSE) | (1L << STRING) | (1L << TILDE))) != 0)) {
				{
				setState(241); event();
				setState(246);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SEP) {
					{
					{
					setState(242); match(SEP);
					setState(243); event();
					}
					}
					setState(248);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(251); match(RTB);
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
		enterRule(_localctx, 40, RULE_event);
		try {
			setState(255);
			switch (_input.LA(1)) {
			case UNIVERSE:
				enterOuterAlt(_localctx, 1);
				{
				setState(253); universe();
				}
				break;
			case STRING:
			case TILDE:
				enterOuterAlt(_localctx, 2);
				{
				setState(254); task();
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
		enterRule(_localctx, 42, RULE_task);
		int _la;
		try {
			setState(267);
			switch (_input.LA(1)) {
			case TILDE:
				enterOuterAlt(_localctx, 1);
				{
				setState(257); approximate();
				setState(258); label();
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 2);
				{
				setState(260); label();
				setState(265);
				_la = _input.LA(1);
				if (_la==LSB) {
					{
					setState(261); match(LSB);
					setState(262); similarity();
					setState(263); match(RSB);
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
		enterRule(_localctx, 44, RULE_approximate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(269); match(TILDE);
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
		enterRule(_localctx, 46, RULE_label);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(271); match(STRING);
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
		enterRule(_localctx, 48, RULE_similarity);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(273); match(SIMILARITY);
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
		enterRule(_localctx, 50, RULE_setOfTasksConstruction);
		try {
			setState(277);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(275); unaryPredicateConstruction();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(276); binaryPredicateConstruction();
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
		enterRule(_localctx, 52, RULE_unaryPredicateConstruction);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(279); match(GET_TASKS);
			}
			setState(280); unaryPredicateName();
			setState(281); match(LP);
			setState(282); setOfTasks();
			setState(283); match(RP);
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
		enterRule(_localctx, 54, RULE_binaryPredicateConstruction);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(285); match(GET_TASKS);
			}
			setState(286); binaryPredicateName();
			setState(287); match(LP);
			setState(288); setOfTasks();
			setState(289); match(SEP);
			setState(290); setOfTasks();
			setState(291); match(SEP);
			setState(292); anyAll();
			setState(293); match(RP);
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
		enterRule(_localctx, 56, RULE_anyAll);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(295);
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
		enterRule(_localctx, 58, RULE_unaryPredicateName);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(297);
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
		enterRule(_localctx, 60, RULE_unaryTracePredicateName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(299); match(EXECUTES);
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
		enterRule(_localctx, 62, RULE_binaryPredicateName);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(301);
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
		enterRule(_localctx, 64, RULE_predicate);
		try {
			setState(307);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(303); proposition();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(304); conjunction();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(305); disjunction();
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(306); logicalTest();
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
		enterRule(_localctx, 66, RULE_proposition);
		try {
			setState(318);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(309); unaryPredicate();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(310); binaryPredicate();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(311); unaryTracePredicate();
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(312); unaryPredicateMacro();
				}
				break;

			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(313); binaryPredicateMacro();
				}
				break;

			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(314); setPredicate();
				}
				break;

			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(315); truthValue();
				}
				break;

			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(316); parentheses();
				}
				break;

			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(317); negation();
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
		enterRule(_localctx, 68, RULE_unaryPredicate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(320); unaryPredicateName();
			setState(321); match(LP);
			setState(322); task();
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
		enterRule(_localctx, 70, RULE_binaryPredicate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(325); binaryPredicateName();
			setState(326); match(LP);
			setState(327); task();
			setState(328); match(SEP);
			setState(329); task();
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
		enterRule(_localctx, 72, RULE_unaryTracePredicate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(332); unaryTracePredicateName();
			setState(333); match(LP);
			setState(334); trace();
			setState(335); match(RP);
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
		enterRule(_localctx, 74, RULE_unaryPredicateMacro);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(337); unaryPredicateName();
			setState(338); match(LP);
			setState(339); setOfTasks();
			setState(340); match(SEP);
			setState(341); anyAll();
			setState(342); match(RP);
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
		enterRule(_localctx, 76, RULE_binaryPredicateMacro);
		try {
			setState(346);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(344); binaryPredicateMacroTaskSet();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(345); binaryPredicateMacroSetSet();
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
		enterRule(_localctx, 78, RULE_binaryPredicateMacroTaskSet);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(348); binaryPredicateName();
			setState(349); match(LP);
			setState(350); task();
			setState(351); match(SEP);
			setState(352); setOfTasks();
			setState(353); match(SEP);
			setState(354); anyAll();
			setState(355); match(RP);
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
		enterRule(_localctx, 80, RULE_binaryPredicateMacroSetSet);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(357); binaryPredicateName();
			setState(358); match(LP);
			setState(359); setOfTasks();
			setState(360); match(SEP);
			setState(361); setOfTasks();
			setState(362); match(SEP);
			setState(363); anyEachAll();
			setState(364); match(RP);
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
		enterRule(_localctx, 82, RULE_anyEachAll);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(366);
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
		enterRule(_localctx, 84, RULE_setPredicate);
		try {
			setState(370);
			switch (_input.LA(1)) {
			case STRING:
			case TILDE:
				enterOuterAlt(_localctx, 1);
				{
				setState(368); taskInSetOfTasks();
				}
				break;
			case UNIVERSE:
			case VARIABLE_NAME:
			case LP:
			case LB:
			case GET_TASKS:
				enterOuterAlt(_localctx, 2);
				{
				setState(369); setComparison();
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
		enterRule(_localctx, 86, RULE_taskInSetOfTasks);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(372); task();
			setState(373); match(IN);
			setState(374); setOfTasks();
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
		enterRule(_localctx, 88, RULE_setComparison);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(376); setOfTasks();
			setState(377); setComparisonOperator();
			setState(378); setOfTasks();
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
		enterRule(_localctx, 90, RULE_setComparisonOperator);
		try {
			setState(385);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(380); identical();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(381); different();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(382); overlapsWith();
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(383); subsetOf();
				}
				break;

			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(384); properSubsetOf();
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
		public TerminalNode UNKNOWN() { return getToken(PQLParser.UNKNOWN, 0); }
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
		enterRule(_localctx, 92, RULE_truthValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(387);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TRUE) | (1L << FALSE) | (1L << UNKNOWN))) != 0)) ) {
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
		public IsNotUnknownContext isNotUnknown() {
			return getRuleContext(IsNotUnknownContext.class,0);
		}
		public IsUnknownContext isUnknown() {
			return getRuleContext(IsUnknownContext.class,0);
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
		enterRule(_localctx, 94, RULE_logicalTest);
		try {
			setState(395);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(389); isTrue();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(390); isNotTrue();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(391); isFalse();
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(392); isNotFalse();
				}
				break;

			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(393); isUnknown();
				}
				break;

			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(394); isNotUnknown();
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
		enterRule(_localctx, 96, RULE_union);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(400);
			switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
			case 1:
				{
				setState(397); tasks();
				}
				break;

			case 2:
				{
				setState(398); difference();
				}
				break;

			case 3:
				{
				setState(399); intersection();
				}
				break;
			}
			setState(402); match(UNION);
			setState(406);
			switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
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

			case 3:
				{
				setState(405); intersection();
				}
				break;
			}
			setState(416);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==UNION) {
				{
				{
				setState(408); match(UNION);
				setState(412);
				switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
				case 1:
					{
					setState(409); tasks();
					}
					break;

				case 2:
					{
					setState(410); difference();
					}
					break;

				case 3:
					{
					setState(411); intersection();
					}
					break;
				}
				}
				}
				setState(418);
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
		enterRule(_localctx, 98, RULE_intersection);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(421);
			switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
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
			setState(423); match(INTERSECTION);
			setState(426);
			switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
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
			setState(435);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==INTERSECTION) {
				{
				{
				setState(428); match(INTERSECTION);
				setState(431);
				switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
				case 1:
					{
					setState(429); tasks();
					}
					break;

				case 2:
					{
					setState(430); difference();
					}
					break;
				}
				}
				}
				setState(437);
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
		enterRule(_localctx, 100, RULE_difference);
		try {
			setState(446);
			switch ( getInterpreter().adaptivePredict(_input,33,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(438); tasks();
				setState(439); match(DIFFERENCE);
				setState(440); tasks();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(442); tasks();
				setState(443); match(DIFFERENCE);
				setState(444); difference();
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
		enterRule(_localctx, 102, RULE_negation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(448); match(NOT);
			setState(449); proposition();
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
		enterRule(_localctx, 104, RULE_isTrue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(451); proposition();
			setState(452); match(IS);
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
		enterRule(_localctx, 106, RULE_isNotTrue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(455); proposition();
			setState(456); match(IS);
			setState(457); match(NOT);
			setState(458); match(TRUE);
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
		enterRule(_localctx, 108, RULE_isFalse);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(460); proposition();
			setState(461); match(IS);
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
		enterRule(_localctx, 110, RULE_isNotFalse);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(464); proposition();
			setState(465); match(IS);
			setState(466); match(NOT);
			setState(467); match(FALSE);
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

	public static class IsUnknownContext extends ParserRuleContext {
		public PropositionContext proposition() {
			return getRuleContext(PropositionContext.class,0);
		}
		public TerminalNode IS() { return getToken(PQLParser.IS, 0); }
		public TerminalNode UNKNOWN() { return getToken(PQLParser.UNKNOWN, 0); }
		public IsUnknownContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_isUnknown; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).enterIsUnknown(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).exitIsUnknown(this);
		}
	}

	public final IsUnknownContext isUnknown() throws RecognitionException {
		IsUnknownContext _localctx = new IsUnknownContext(_ctx, getState());
		enterRule(_localctx, 112, RULE_isUnknown);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(469); proposition();
			setState(470); match(IS);
			setState(471); match(UNKNOWN);
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

	public static class IsNotUnknownContext extends ParserRuleContext {
		public TerminalNode NOT() { return getToken(PQLParser.NOT, 0); }
		public PropositionContext proposition() {
			return getRuleContext(PropositionContext.class,0);
		}
		public TerminalNode IS() { return getToken(PQLParser.IS, 0); }
		public TerminalNode UNKNOWN() { return getToken(PQLParser.UNKNOWN, 0); }
		public IsNotUnknownContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_isNotUnknown; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).enterIsNotUnknown(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PQLListener ) ((PQLListener)listener).exitIsNotUnknown(this);
		}
	}

	public final IsNotUnknownContext isNotUnknown() throws RecognitionException {
		IsNotUnknownContext _localctx = new IsNotUnknownContext(_ctx, getState());
		enterRule(_localctx, 114, RULE_isNotUnknown);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(473); proposition();
			setState(474); match(IS);
			setState(475); match(NOT);
			setState(476); match(UNKNOWN);
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
		enterRule(_localctx, 116, RULE_disjunction);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(481);
			switch ( getInterpreter().adaptivePredict(_input,34,_ctx) ) {
			case 1:
				{
				setState(478); proposition();
				}
				break;

			case 2:
				{
				setState(479); logicalTest();
				}
				break;

			case 3:
				{
				setState(480); conjunction();
				}
				break;
			}
			setState(483); match(OR);
			setState(487);
			switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
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

			case 3:
				{
				setState(486); conjunction();
				}
				break;
			}
			setState(497);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR) {
				{
				{
				setState(489); match(OR);
				setState(493);
				switch ( getInterpreter().adaptivePredict(_input,36,_ctx) ) {
				case 1:
					{
					setState(490); proposition();
					}
					break;

				case 2:
					{
					setState(491); logicalTest();
					}
					break;

				case 3:
					{
					setState(492); conjunction();
					}
					break;
				}
				}
				}
				setState(499);
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
		enterRule(_localctx, 118, RULE_conjunction);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(502);
			switch ( getInterpreter().adaptivePredict(_input,38,_ctx) ) {
			case 1:
				{
				setState(500); proposition();
				}
				break;

			case 2:
				{
				setState(501); logicalTest();
				}
				break;
			}
			setState(504); match(AND);
			setState(507);
			switch ( getInterpreter().adaptivePredict(_input,39,_ctx) ) {
			case 1:
				{
				setState(505); proposition();
				}
				break;

			case 2:
				{
				setState(506); logicalTest();
				}
				break;
			}
			setState(516);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AND) {
				{
				{
				setState(509); match(AND);
				setState(512);
				switch ( getInterpreter().adaptivePredict(_input,40,_ctx) ) {
				case 1:
					{
					setState(510); proposition();
					}
					break;

				case 2:
					{
					setState(511); logicalTest();
					}
					break;
				}
				}
				}
				setState(518);
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
		enterRule(_localctx, 120, RULE_parentheses);
		try {
			setState(535);
			switch ( getInterpreter().adaptivePredict(_input,42,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(519); match(LP);
				setState(520); proposition();
				setState(521); match(RP);
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(523); match(LP);
				setState(524); conjunction();
				setState(525); match(RP);
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(527); match(LP);
				setState(528); disjunction();
				setState(529); match(RP);
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(531); match(LP);
				setState(532); logicalTest();
				setState(533); match(RP);
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
		enterRule(_localctx, 122, RULE_setOfTasksParentheses);
		try {
			setState(569);
			switch ( getInterpreter().adaptivePredict(_input,43,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(537); match(LP);
				setState(538); varName();
				setState(539); match(RP);
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(541); match(LP);
				setState(542); universe();
				setState(543); match(RP);
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(545); match(LP);
				setState(546); setOfTasksLiteral();
				setState(547); match(RP);
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(549); match(LP);
				setState(550); setOfTasksConstruction();
				setState(551); match(RP);
				}
				break;

			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(553); match(LP);
				setState(554); union();
				setState(555); match(RP);
				}
				break;

			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(557); match(LP);
				setState(558); difference();
				setState(559); match(RP);
				}
				break;

			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(561); match(LP);
				setState(562); intersection();
				setState(563); match(RP);
				}
				break;

			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(565); match(LP);
				setState(566); setOfTasksParentheses();
				setState(567); match(RP);
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
		enterRule(_localctx, 124, RULE_identical);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(571); match(EQUALS);
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
		enterRule(_localctx, 126, RULE_different);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(573); match(NOT);
			setState(574); match(EQUALS);
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
		enterRule(_localctx, 128, RULE_overlapsWith);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(576); match(OVERLAPS);
			setState(577); match(WITH);
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
		enterRule(_localctx, 130, RULE_subsetOf);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(579); match(IS);
			setState(580); match(SUBSET);
			setState(581); match(OF);
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
		enterRule(_localctx, 132, RULE_properSubsetOf);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(583); match(IS);
			setState(584); match(PROPER);
			setState(585); match(SUBSET);
			setState(586); match(OF);
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
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\3?\u024f\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\3\2\3\2\5\2\u008b\n\2\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\5\3\u0094\n\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\5\4\u009f\n\4\3\4\3\4\3\5\7\5\u00a4\n\5\f\5\16\5\u00a7\13\5\3\6\3\6"+
		"\3\6\3\6\3\6\3\7\3\7\3\b\3\b\3\b\7\b\u00b3\n\b\f\b\16\b\u00b6\13\b\3\t"+
		"\3\t\3\t\3\t\5\t\u00bc\n\t\3\n\3\n\3\n\7\n\u00c1\n\n\f\n\16\n\u00c4\13"+
		"\n\3\13\3\13\3\13\5\13\u00c9\n\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17"+
		"\3\20\3\20\3\21\3\21\3\22\3\22\3\22\3\22\5\22\u00db\n\22\3\23\3\23\5\23"+
		"\u00df\n\23\3\23\3\23\3\23\5\23\u00e4\n\23\3\24\3\24\3\24\3\24\7\24\u00ea"+
		"\n\24\f\24\16\24\u00ed\13\24\5\24\u00ef\n\24\3\24\3\24\3\25\3\25\3\25"+
		"\3\25\7\25\u00f7\n\25\f\25\16\25\u00fa\13\25\5\25\u00fc\n\25\3\25\3\25"+
		"\3\26\3\26\5\26\u0102\n\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\5\27"+
		"\u010c\n\27\5\27\u010e\n\27\3\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33\5"+
		"\33\u0118\n\33\3\34\3\34\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35"+
		"\3\35\3\35\3\35\3\35\3\35\3\36\3\36\3\37\3\37\3 \3 \3!\3!\3\"\3\"\3\""+
		"\3\"\5\"\u0136\n\"\3#\3#\3#\3#\3#\3#\3#\3#\3#\5#\u0141\n#\3$\3$\3$\3$"+
		"\3$\3%\3%\3%\3%\3%\3%\3%\3&\3&\3&\3&\3&\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3"+
		"(\3(\5(\u015d\n(\3)\3)\3)\3)\3)\3)\3)\3)\3)\3*\3*\3*\3*\3*\3*\3*\3*\3"+
		"*\3+\3+\3,\3,\5,\u0175\n,\3-\3-\3-\3-\3.\3.\3.\3.\3/\3/\3/\3/\3/\5/\u0184"+
		"\n/\3\60\3\60\3\61\3\61\3\61\3\61\3\61\3\61\5\61\u018e\n\61\3\62\3\62"+
		"\3\62\5\62\u0193\n\62\3\62\3\62\3\62\3\62\5\62\u0199\n\62\3\62\3\62\3"+
		"\62\3\62\5\62\u019f\n\62\7\62\u01a1\n\62\f\62\16\62\u01a4\13\62\3\63\3"+
		"\63\5\63\u01a8\n\63\3\63\3\63\3\63\5\63\u01ad\n\63\3\63\3\63\3\63\5\63"+
		"\u01b2\n\63\7\63\u01b4\n\63\f\63\16\63\u01b7\13\63\3\64\3\64\3\64\3\64"+
		"\3\64\3\64\3\64\3\64\5\64\u01c1\n\64\3\65\3\65\3\65\3\66\3\66\3\66\3\66"+
		"\3\67\3\67\3\67\3\67\3\67\38\38\38\38\39\39\39\39\39\3:\3:\3:\3:\3;\3"+
		";\3;\3;\3;\3<\3<\3<\5<\u01e4\n<\3<\3<\3<\3<\5<\u01ea\n<\3<\3<\3<\3<\5"+
		"<\u01f0\n<\7<\u01f2\n<\f<\16<\u01f5\13<\3=\3=\5=\u01f9\n=\3=\3=\3=\5="+
		"\u01fe\n=\3=\3=\3=\5=\u0203\n=\7=\u0205\n=\f=\16=\u0208\13=\3>\3>\3>\3"+
		">\3>\3>\3>\3>\3>\3>\3>\3>\3>\3>\3>\3>\5>\u021a\n>\3?\3?\3?\3?\3?\3?\3"+
		"?\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?\3"+
		"?\3?\3?\5?\u023c\n?\3@\3@\3A\3A\3A\3B\3B\3B\3C\3C\3C\3C\3D\3D\3D\3D\3"+
		"D\3D\2E\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:"+
		"<>@BDFHJLNPRTVXZ\\^`bdfhjlnprtvxz|~\u0080\u0082\u0084\u0086\2\7\4\2++"+
		"--\3\2\678\3\2:?\3\2+-\3\2\61\63\u025c\2\u008a\3\2\2\2\4\u008c\3\2\2\2"+
		"\6\u0097\3\2\2\2\b\u00a5\3\2\2\2\n\u00a8\3\2\2\2\f\u00ad\3\2\2\2\16\u00af"+
		"\3\2\2\2\20\u00bb\3\2\2\2\22\u00bd\3\2\2\2\24\u00c8\3\2\2\2\26\u00ca\3"+
		"\2\2\2\30\u00cc\3\2\2\2\32\u00ce\3\2\2\2\34\u00d0\3\2\2\2\36\u00d2\3\2"+
		"\2\2 \u00d4\3\2\2\2\"\u00da\3\2\2\2$\u00e3\3\2\2\2&\u00e5\3\2\2\2(\u00f2"+
		"\3\2\2\2*\u0101\3\2\2\2,\u010d\3\2\2\2.\u010f\3\2\2\2\60\u0111\3\2\2\2"+
		"\62\u0113\3\2\2\2\64\u0117\3\2\2\2\66\u0119\3\2\2\28\u011f\3\2\2\2:\u0129"+
		"\3\2\2\2<\u012b\3\2\2\2>\u012d\3\2\2\2@\u012f\3\2\2\2B\u0135\3\2\2\2D"+
		"\u0140\3\2\2\2F\u0142\3\2\2\2H\u0147\3\2\2\2J\u014e\3\2\2\2L\u0153\3\2"+
		"\2\2N\u015c\3\2\2\2P\u015e\3\2\2\2R\u0167\3\2\2\2T\u0170\3\2\2\2V\u0174"+
		"\3\2\2\2X\u0176\3\2\2\2Z\u017a\3\2\2\2\\\u0183\3\2\2\2^\u0185\3\2\2\2"+
		"`\u018d\3\2\2\2b\u0192\3\2\2\2d\u01a7\3\2\2\2f\u01c0\3\2\2\2h\u01c2\3"+
		"\2\2\2j\u01c5\3\2\2\2l\u01c9\3\2\2\2n\u01ce\3\2\2\2p\u01d2\3\2\2\2r\u01d7"+
		"\3\2\2\2t\u01db\3\2\2\2v\u01e3\3\2\2\2x\u01f8\3\2\2\2z\u0219\3\2\2\2|"+
		"\u023b\3\2\2\2~\u023d\3\2\2\2\u0080\u023f\3\2\2\2\u0082\u0242\3\2\2\2"+
		"\u0084\u0245\3\2\2\2\u0086\u0249\3\2\2\2\u0088\u008b\5\4\3\2\u0089\u008b"+
		"\5\6\4\2\u008a\u0088\3\2\2\2\u008a\u0089\3\2\2\2\u008b\3\3\2\2\2\u008c"+
		"\u008d\5\b\5\2\u008d\u008e\7\35\2\2\u008e\u008f\5\16\b\2\u008f\u0090\7"+
		" \2\2\u0090\u0093\5\22\n\2\u0091\u0092\7!\2\2\u0092\u0094\5B\"\2\u0093"+
		"\u0091\3\2\2\2\u0093\u0094\3\2\2\2\u0094\u0095\3\2\2\2\u0095\u0096\7\24"+
		"\2\2\u0096\5\3\2\2\2\u0097\u0098\5\b\5\2\u0098\u0099\7\36\2\2\u0099\u009a"+
		"\5(\25\2\u009a\u009b\7\37\2\2\u009b\u009e\5\22\n\2\u009c\u009d\7!\2\2"+
		"\u009d\u009f\5B\"\2\u009e\u009c\3\2\2\2\u009e\u009f\3\2\2\2\u009f\u00a0"+
		"\3\2\2\2\u00a0\u00a1\7\24\2\2\u00a1\7\3\2\2\2\u00a2\u00a4\5\n\6\2\u00a3"+
		"\u00a2\3\2\2\2\u00a4\u00a7\3\2\2\2\u00a5\u00a3\3\2\2\2\u00a5\u00a6\3\2"+
		"\2\2\u00a6\t\3\2\2\2\u00a7\u00a5\3\2\2\2\u00a8\u00a9\5\f\7\2\u00a9\u00aa"+
		"\7\26\2\2\u00aa\u00ab\5\"\22\2\u00ab\u00ac\7\24\2\2\u00ac\13\3\2\2\2\u00ad"+
		"\u00ae\7\t\2\2\u00ae\r\3\2\2\2\u00af\u00b4\5\20\t\2\u00b0\u00b1\7\25\2"+
		"\2\u00b1\u00b3\5\20\t\2\u00b2\u00b0\3\2\2\2\u00b3\u00b6\3\2\2\2\u00b4"+
		"\u00b2\3\2\2\2\u00b4\u00b5\3\2\2\2\u00b5\17\3\2\2\2\u00b6\u00b4\3\2\2"+
		"\2\u00b7\u00bc\5\26\f\2\u00b8\u00bc\5\30\r\2\u00b9\u00bc\5\32\16\2\u00ba"+
		"\u00bc\5\34\17\2\u00bb\u00b7\3\2\2\2\u00bb\u00b8\3\2\2\2\u00bb\u00b9\3"+
		"\2\2\2\u00bb\u00ba\3\2\2\2\u00bc\21\3\2\2\2\u00bd\u00c2\5\24\13\2\u00be"+
		"\u00bf\7\25\2\2\u00bf\u00c1\5\24\13\2\u00c0\u00be\3\2\2\2\u00c1\u00c4"+
		"\3\2\2\2\u00c2\u00c0\3\2\2\2\u00c2\u00c3\3\2\2\2\u00c3\23\3\2\2\2\u00c4"+
		"\u00c2\3\2\2\2\u00c5\u00c9\5\26\f\2\u00c6\u00c9\5\36\20\2\u00c7\u00c9"+
		"\5 \21\2\u00c8\u00c5\3\2\2\2\u00c8\u00c6\3\2\2\2\u00c8\u00c7\3\2\2\2\u00c9"+
		"\25\3\2\2\2\u00ca\u00cb\7\3\2\2\u00cb\27\3\2\2\2\u00cc\u00cd\7\4\2\2\u00cd"+
		"\31\3\2\2\2\u00ce\u00cf\7\5\2\2\u00cf\33\3\2\2\2\u00d0\u00d1\7\6\2\2\u00d1"+
		"\35\3\2\2\2\u00d2\u00d3\7\b\2\2\u00d3\37\3\2\2\2\u00d4\u00d5\7\7\2\2\u00d5"+
		"!\3\2\2\2\u00d6\u00db\5$\23\2\u00d7\u00db\5b\62\2\u00d8\u00db\5d\63\2"+
		"\u00d9\u00db\5f\64\2\u00da\u00d6\3\2\2\2\u00da\u00d7\3\2\2\2\u00da\u00d8"+
		"\3\2\2\2\u00da\u00d9\3\2\2\2\u00db#\3\2\2\2\u00dc\u00df\5\f\7\2\u00dd"+
		"\u00df\5\26\f\2\u00de\u00dc\3\2\2\2\u00de\u00dd\3\2\2\2\u00df\u00e4\3"+
		"\2\2\2\u00e0\u00e4\5&\24\2\u00e1\u00e4\5\64\33\2\u00e2\u00e4\5|?\2\u00e3"+
		"\u00de\3\2\2\2\u00e3\u00e0\3\2\2\2\u00e3\u00e1\3\2\2\2\u00e3\u00e2\3\2"+
		"\2\2\u00e4%\3\2\2\2\u00e5\u00ee\7\r\2\2\u00e6\u00eb\5,\27\2\u00e7\u00e8"+
		"\7\25\2\2\u00e8\u00ea\5,\27\2\u00e9\u00e7\3\2\2\2\u00ea\u00ed\3\2\2\2"+
		"\u00eb\u00e9\3\2\2\2\u00eb\u00ec\3\2\2\2\u00ec\u00ef\3\2\2\2\u00ed\u00eb"+
		"\3\2\2\2\u00ee\u00e6\3\2\2\2\u00ee\u00ef\3\2\2\2\u00ef\u00f0\3\2\2\2\u00f0"+
		"\u00f1\7\16\2\2\u00f1\'\3\2\2\2\u00f2\u00fb\7\21\2\2\u00f3\u00f8\5*\26"+
		"\2\u00f4\u00f5\7\25\2\2\u00f5\u00f7\5*\26\2\u00f6\u00f4\3\2\2\2\u00f7"+
		"\u00fa\3\2\2\2\u00f8\u00f6\3\2\2\2\u00f8\u00f9\3\2\2\2\u00f9\u00fc\3\2"+
		"\2\2\u00fa\u00f8\3\2\2\2\u00fb\u00f3\3\2\2\2\u00fb\u00fc\3\2\2\2\u00fc"+
		"\u00fd\3\2\2\2\u00fd\u00fe\7\22\2\2\u00fe)\3\2\2\2\u00ff\u0102\5\26\f"+
		"\2\u0100\u0102\5,\27\2\u0101\u00ff\3\2\2\2\u0101\u0100\3\2\2\2\u0102+"+
		"\3\2\2\2\u0103\u0104\5.\30\2\u0104\u0105\5\60\31\2\u0105\u010e\3\2\2\2"+
		"\u0106\u010b\5\60\31\2\u0107\u0108\7\17\2\2\u0108\u0109\5\62\32\2\u0109"+
		"\u010a\7\20\2\2\u010a\u010c\3\2\2\2\u010b\u0107\3\2\2\2\u010b\u010c\3"+
		"\2\2\2\u010c\u010e\3\2\2\2\u010d\u0103\3\2\2\2\u010d\u0106\3\2\2\2\u010e"+
		"-\3\2\2\2\u010f\u0110\7\27\2\2\u0110/\3\2\2\2\u0111\u0112\7\7\2\2\u0112"+
		"\61\3\2\2\2\u0113\u0114\7\n\2\2\u0114\63\3\2\2\2\u0115\u0118\5\66\34\2"+
		"\u0116\u0118\58\35\2\u0117\u0115\3\2\2\2\u0117\u0116\3\2\2\2\u0118\65"+
		"\3\2\2\2\u0119\u011a\7\'\2\2\u011a\u011b\5<\37\2\u011b\u011c\7\13\2\2"+
		"\u011c\u011d\5\"\22\2\u011d\u011e\7\f\2\2\u011e\67\3\2\2\2\u011f\u0120"+
		"\7\'\2\2\u0120\u0121\5@!\2\u0121\u0122\7\13\2\2\u0122\u0123\5\"\22\2\u0123"+
		"\u0124\7\25\2\2\u0124\u0125\5\"\22\2\u0125\u0126\7\25\2\2\u0126\u0127"+
		"\5:\36\2\u0127\u0128\7\f\2\2\u01289\3\2\2\2\u0129\u012a\t\2\2\2\u012a"+
		";\3\2\2\2\u012b\u012c\t\3\2\2\u012c=\3\2\2\2\u012d\u012e\79\2\2\u012e"+
		"?\3\2\2\2\u012f\u0130\t\4\2\2\u0130A\3\2\2\2\u0131\u0136\5D#\2\u0132\u0136"+
		"\5x=\2\u0133\u0136\5v<\2\u0134\u0136\5`\61\2\u0135\u0131\3\2\2\2\u0135"+
		"\u0132\3\2\2\2\u0135\u0133\3\2\2\2\u0135\u0134\3\2\2\2\u0136C\3\2\2\2"+
		"\u0137\u0141\5F$\2\u0138\u0141\5H%\2\u0139\u0141\5J&\2\u013a\u0141\5L"+
		"\'\2\u013b\u0141\5N(\2\u013c\u0141\5V,\2\u013d\u0141\5^\60\2\u013e\u0141"+
		"\5z>\2\u013f\u0141\5h\65\2\u0140\u0137\3\2\2\2\u0140\u0138\3\2\2\2\u0140"+
		"\u0139\3\2\2\2\u0140\u013a\3\2\2\2\u0140\u013b\3\2\2\2\u0140\u013c\3\2"+
		"\2\2\u0140\u013d\3\2\2\2\u0140\u013e\3\2\2\2\u0140\u013f\3\2\2\2\u0141"+
		"E\3\2\2\2\u0142\u0143\5<\37\2\u0143\u0144\7\13\2\2\u0144\u0145\5,\27\2"+
		"\u0145\u0146\7\f\2\2\u0146G\3\2\2\2\u0147\u0148\5@!\2\u0148\u0149\7\13"+
		"\2\2\u0149\u014a\5,\27\2\u014a\u014b\7\25\2\2\u014b\u014c\5,\27\2\u014c"+
		"\u014d\7\f\2\2\u014dI\3\2\2\2\u014e\u014f\5> \2\u014f\u0150\7\13\2\2\u0150"+
		"\u0151\5(\25\2\u0151\u0152\7\f\2\2\u0152K\3\2\2\2\u0153\u0154\5<\37\2"+
		"\u0154\u0155\7\13\2\2\u0155\u0156\5\"\22\2\u0156\u0157\7\25\2\2\u0157"+
		"\u0158\5:\36\2\u0158\u0159\7\f\2\2\u0159M\3\2\2\2\u015a\u015d\5P)\2\u015b"+
		"\u015d\5R*\2\u015c\u015a\3\2\2\2\u015c\u015b\3\2\2\2\u015dO\3\2\2\2\u015e"+
		"\u015f\5@!\2\u015f\u0160\7\13\2\2\u0160\u0161\5,\27\2\u0161\u0162\7\25"+
		"\2\2\u0162\u0163\5\"\22\2\u0163\u0164\7\25\2\2\u0164\u0165\5:\36\2\u0165"+
		"\u0166\7\f\2\2\u0166Q\3\2\2\2\u0167\u0168\5@!\2\u0168\u0169\7\13\2\2\u0169"+
		"\u016a\5\"\22\2\u016a\u016b\7\25\2\2\u016b\u016c\5\"\22\2\u016c\u016d"+
		"\7\25\2\2\u016d\u016e\5T+\2\u016e\u016f\7\f\2\2\u016fS\3\2\2\2\u0170\u0171"+
		"\t\5\2\2\u0171U\3\2\2\2\u0172\u0175\5X-\2\u0173\u0175\5Z.\2\u0174\u0172"+
		"\3\2\2\2\u0174\u0173\3\2\2\2\u0175W\3\2\2\2\u0176\u0177\5,\27\2\u0177"+
		"\u0178\7.\2\2\u0178\u0179\5\"\22\2\u0179Y\3\2\2\2\u017a\u017b\5\"\22\2"+
		"\u017b\u017c\5\\/\2\u017c\u017d\5\"\22\2\u017d[\3\2\2\2\u017e\u0184\5"+
		"~@\2\u017f\u0184\5\u0080A\2\u0180\u0184\5\u0082B\2\u0181\u0184\5\u0084"+
		"C\2\u0182\u0184\5\u0086D\2\u0183\u017e\3\2\2\2\u0183\u017f\3\2\2\2\u0183"+
		"\u0180\3\2\2\2\u0183\u0181\3\2\2\2\u0183\u0182\3\2\2\2\u0184]\3\2\2\2"+
		"\u0185\u0186\t\6\2\2\u0186_\3\2\2\2\u0187\u018e\5j\66\2\u0188\u018e\5"+
		"l\67\2\u0189\u018e\5n8\2\u018a\u018e\5p9\2\u018b\u018e\5r:\2\u018c\u018e"+
		"\5t;\2\u018d\u0187\3\2\2\2\u018d\u0188\3\2\2\2\u018d\u0189\3\2\2\2\u018d"+
		"\u018a\3\2\2\2\u018d\u018b\3\2\2\2\u018d\u018c\3\2\2\2\u018ea\3\2\2\2"+
		"\u018f\u0193\5$\23\2\u0190\u0193\5f\64\2\u0191\u0193\5d\63\2\u0192\u018f"+
		"\3\2\2\2\u0192\u0190\3\2\2\2\u0192\u0191\3\2\2\2\u0193\u0194\3\2\2\2\u0194"+
		"\u0198\7\64\2\2\u0195\u0199\5$\23\2\u0196\u0199\5f\64\2\u0197\u0199\5"+
		"d\63\2\u0198\u0195\3\2\2\2\u0198\u0196\3\2\2\2\u0198\u0197\3\2\2\2\u0199"+
		"\u01a2\3\2\2\2\u019a\u019e\7\64\2\2\u019b\u019f\5$\23\2\u019c\u019f\5"+
		"f\64\2\u019d\u019f\5d\63\2\u019e\u019b\3\2\2\2\u019e\u019c\3\2\2\2\u019e"+
		"\u019d\3\2\2\2\u019f\u01a1\3\2\2\2\u01a0\u019a\3\2\2\2\u01a1\u01a4\3\2"+
		"\2\2\u01a2\u01a0\3\2\2\2\u01a2\u01a3\3\2\2\2\u01a3c\3\2\2\2\u01a4\u01a2"+
		"\3\2\2\2\u01a5\u01a8\5$\23\2\u01a6\u01a8\5f\64\2\u01a7\u01a5\3\2\2\2\u01a7"+
		"\u01a6\3\2\2\2\u01a8\u01a9\3\2\2\2\u01a9\u01ac\7\65\2\2\u01aa\u01ad\5"+
		"$\23\2\u01ab\u01ad\5f\64\2\u01ac\u01aa\3\2\2\2\u01ac\u01ab\3\2\2\2\u01ad"+
		"\u01b5\3\2\2\2\u01ae\u01b1\7\65\2\2\u01af\u01b2\5$\23\2\u01b0\u01b2\5"+
		"f\64\2\u01b1\u01af\3\2\2\2\u01b1\u01b0\3\2\2\2\u01b2\u01b4\3\2\2\2\u01b3"+
		"\u01ae\3\2\2\2\u01b4\u01b7\3\2\2\2\u01b5\u01b3\3\2\2\2\u01b5\u01b6\3\2"+
		"\2\2\u01b6e\3\2\2\2\u01b7\u01b5\3\2\2\2\u01b8\u01b9\5$\23\2\u01b9\u01ba"+
		"\7\66\2\2\u01ba\u01bb\5$\23\2\u01bb\u01c1\3\2\2\2\u01bc\u01bd\5$\23\2"+
		"\u01bd\u01be\7\66\2\2\u01be\u01bf\5f\64\2\u01bf\u01c1\3\2\2\2\u01c0\u01b8"+
		"\3\2\2\2\u01c0\u01bc\3\2\2\2\u01c1g\3\2\2\2\u01c2\u01c3\7(\2\2\u01c3\u01c4"+
		"\5D#\2\u01c4i\3\2\2\2\u01c5\u01c6\5D#\2\u01c6\u01c7\7/\2\2\u01c7\u01c8"+
		"\7\61\2\2\u01c8k\3\2\2\2\u01c9\u01ca\5D#\2\u01ca\u01cb\7/\2\2\u01cb\u01cc"+
		"\7(\2\2\u01cc\u01cd\7\61\2\2\u01cdm\3\2\2\2\u01ce\u01cf\5D#\2\u01cf\u01d0"+
		"\7/\2\2\u01d0\u01d1\7\62\2\2\u01d1o\3\2\2\2\u01d2\u01d3\5D#\2\u01d3\u01d4"+
		"\7/\2\2\u01d4\u01d5\7(\2\2\u01d5\u01d6\7\62\2\2\u01d6q\3\2\2\2\u01d7\u01d8"+
		"\5D#\2\u01d8\u01d9\7/\2\2\u01d9\u01da\7\63\2\2\u01das\3\2\2\2\u01db\u01dc"+
		"\5D#\2\u01dc\u01dd\7/\2\2\u01dd\u01de\7(\2\2\u01de\u01df\7\63\2\2\u01df"+
		"u\3\2\2\2\u01e0\u01e4\5D#\2\u01e1\u01e4\5`\61\2\u01e2\u01e4\5x=\2\u01e3"+
		"\u01e0\3\2\2\2\u01e3\u01e1\3\2\2\2\u01e3\u01e2\3\2\2\2\u01e4\u01e5\3\2"+
		"\2\2\u01e5\u01e9\7*\2\2\u01e6\u01ea\5D#\2\u01e7\u01ea\5`\61\2\u01e8\u01ea"+
		"\5x=\2\u01e9\u01e6\3\2\2\2\u01e9\u01e7\3\2\2\2\u01e9\u01e8\3\2\2\2\u01ea"+
		"\u01f3\3\2\2\2\u01eb\u01ef\7*\2\2\u01ec\u01f0\5D#\2\u01ed\u01f0\5`\61"+
		"\2\u01ee\u01f0\5x=\2\u01ef\u01ec\3\2\2\2\u01ef\u01ed\3\2\2\2\u01ef\u01ee"+
		"\3\2\2\2\u01f0\u01f2\3\2\2\2\u01f1\u01eb\3\2\2\2\u01f2\u01f5\3\2\2\2\u01f3"+
		"\u01f1\3\2\2\2\u01f3\u01f4\3\2\2\2\u01f4w\3\2\2\2\u01f5\u01f3\3\2\2\2"+
		"\u01f6\u01f9\5D#\2\u01f7\u01f9\5`\61\2\u01f8\u01f6\3\2\2\2\u01f8\u01f7"+
		"\3\2\2\2\u01f9\u01fa\3\2\2\2\u01fa\u01fd\7)\2\2\u01fb\u01fe\5D#\2\u01fc"+
		"\u01fe\5`\61\2\u01fd\u01fb\3\2\2\2\u01fd\u01fc\3\2\2\2\u01fe\u0206\3\2"+
		"\2\2\u01ff\u0202\7)\2\2\u0200\u0203\5D#\2\u0201\u0203\5`\61\2\u0202\u0200"+
		"\3\2\2\2\u0202\u0201\3\2\2\2\u0203\u0205\3\2\2\2\u0204\u01ff\3\2\2\2\u0205"+
		"\u0208\3\2\2\2\u0206\u0204\3\2\2\2\u0206\u0207\3\2\2\2\u0207y\3\2\2\2"+
		"\u0208\u0206\3\2\2\2\u0209\u020a\7\13\2\2\u020a\u020b\5D#\2\u020b\u020c"+
		"\7\f\2\2\u020c\u021a\3\2\2\2\u020d\u020e\7\13\2\2\u020e\u020f\5x=\2\u020f"+
		"\u0210\7\f\2\2\u0210\u021a\3\2\2\2\u0211\u0212\7\13\2\2\u0212\u0213\5"+
		"v<\2\u0213\u0214\7\f\2\2\u0214\u021a\3\2\2\2\u0215\u0216\7\13\2\2\u0216"+
		"\u0217\5`\61\2\u0217\u0218\7\f\2\2\u0218\u021a\3\2\2\2\u0219\u0209\3\2"+
		"\2\2\u0219\u020d\3\2\2\2\u0219\u0211\3\2\2\2\u0219\u0215\3\2\2\2\u021a"+
		"{\3\2\2\2\u021b\u021c\7\13\2\2\u021c\u021d\5\f\7\2\u021d\u021e\7\f\2\2"+
		"\u021e\u023c\3\2\2\2\u021f\u0220\7\13\2\2\u0220\u0221\5\26\f\2\u0221\u0222"+
		"\7\f\2\2\u0222\u023c\3\2\2\2\u0223\u0224\7\13\2\2\u0224\u0225\5&\24\2"+
		"\u0225\u0226\7\f\2\2\u0226\u023c\3\2\2\2\u0227\u0228\7\13\2\2\u0228\u0229"+
		"\5\64\33\2\u0229\u022a\7\f\2\2\u022a\u023c\3\2\2\2\u022b\u022c\7\13\2"+
		"\2\u022c\u022d\5b\62\2\u022d\u022e\7\f\2\2\u022e\u023c\3\2\2\2\u022f\u0230"+
		"\7\13\2\2\u0230\u0231\5f\64\2\u0231\u0232\7\f\2\2\u0232\u023c\3\2\2\2"+
		"\u0233\u0234\7\13\2\2\u0234\u0235\5d\63\2\u0235\u0236\7\f\2\2\u0236\u023c"+
		"\3\2\2\2\u0237\u0238\7\13\2\2\u0238\u0239\5|?\2\u0239\u023a\7\f\2\2\u023a"+
		"\u023c\3\2\2\2\u023b\u021b\3\2\2\2\u023b\u021f\3\2\2\2\u023b\u0223\3\2"+
		"\2\2\u023b\u0227\3\2\2\2\u023b\u022b\3\2\2\2\u023b\u022f\3\2\2\2\u023b"+
		"\u0233\3\2\2\2\u023b\u0237\3\2\2\2\u023c}\3\2\2\2\u023d\u023e\7\"\2\2"+
		"\u023e\177\3\2\2\2\u023f\u0240\7(\2\2\u0240\u0241\7\"\2\2\u0241\u0081"+
		"\3\2\2\2\u0242\u0243\7#\2\2\u0243\u0244\7$\2\2\u0244\u0083\3\2\2\2\u0245"+
		"\u0246\7/\2\2\u0246\u0247\7%\2\2\u0247\u0248\7\60\2\2\u0248\u0085\3\2"+
		"\2\2\u0249\u024a\7/\2\2\u024a\u024b\7&\2\2\u024b\u024c\7%\2\2\u024c\u024d"+
		"\7\60\2\2\u024d\u0087\3\2\2\2.\u008a\u0093\u009e\u00a5\u00b4\u00bb\u00c2"+
		"\u00c8\u00da\u00de\u00e3\u00eb\u00ee\u00f8\u00fb\u0101\u010b\u010d\u0117"+
		"\u0135\u0140\u015c\u0174\u0183\u018d\u0192\u0198\u019e\u01a2\u01a7\u01ac"+
		"\u01b1\u01b5\u01c0\u01e3\u01e9\u01ef\u01f3\u01f8\u01fd\u0202\u0206\u0219"+
		"\u023b";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}