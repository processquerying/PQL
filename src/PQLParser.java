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
		IN=44, IS=45, OF=46, TRUE=47, FALSE=48, UNION=49, INTERSECTION=50, DIFFERENCE=51, 
		CAN_OCCUR=52, ALWAYS_OCCURS=53, EXECUTES=54, CAN_CONFLICT=55, CAN_COOCCUR=56, 
		CONFLICT=57, COOCCUR=58, TOTAL_CAUSAL=59, TOTAL_CONCUR=60;
	public static final String[] tokenNames = {
		"<INVALID>", "'*'", "'id'", "'name'", "'model'", "STRING", "INTEGER", 
		"VARIABLE_NAME", "SIMILARITY", "'('", "')'", "'{'", "'}'", "'['", "']'", 
		"'<'", "'>'", "'\"'", "';'", "','", "'='", "'~'", "ESC_SEQ", "UNICODE_ESC", 
		"HEX_DIGIT", "WS", "LINE_COMMENT", "'SELECT'", "'INSERT'", "'INTO'", "'FROM'", 
		"'WHERE'", "'EQUALS'", "'OVERLAPS'", "'WITH'", "'SUBSET'", "'PROPER'", 
		"'GetTasks'", "'NOT'", "'AND'", "'OR'", "'ANY'", "'EACH'", "'ALL'", "'IN'", 
		"'IS'", "'OF'", "'TRUE'", "'FALSE'", "'UNION'", "'INTERSECT'", "'EXCEPT'", 
		"'CanOccur'", "'AlwaysOccurs'", "'Executes'", "'CanConflict'", "'CanCooccur'", 
		"'Conflict'", "'Cooccur'", "'TotalCausal'", "'TotalConcurrent'"
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
		RULE_isFalse = 54, RULE_isNotFalse = 55, RULE_disjunction = 56, RULE_conjunction = 57, 
		RULE_parentheses = 58, RULE_setOfTasksParentheses = 59, RULE_identical = 60, 
		RULE_different = 61, RULE_overlapsWith = 62, RULE_subsetOf = 63, RULE_properSubsetOf = 64;
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
			setState(132);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(130); selectQuery();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(131); insertQuery();
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

			setState(143); match(EOS);
			}
		}
		catch (RecognitionException re) {
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
			setState(145); variables();
			setState(146); match(INSERT);
			setState(147); trace();
			setState(148); match(INTO);
			setState(149); locations();
			setState(152);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(150); match(WHERE);
				setState(151); predicate();
				}
			}

			setState(154); match(EOS);
			}
		}
		catch (RecognitionException re) {
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
			setState(159);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==VARIABLE_NAME) {
				{
				{
				setState(156); variable();
				}
				}
				setState(161);
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
			setState(162); varName();
			setState(163); match(ASSIGN);
			setState(164); setOfTasks();
			setState(165); match(EOS);
			}
		}
		catch (RecognitionException re) {
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
			setState(167); match(VARIABLE_NAME);
			}
		}
		catch (RecognitionException re) {
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
			setState(169); attribute();
			setState(174);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SEP) {
				{
				{
				setState(170); match(SEP);
				setState(171); attribute();
				}
				}
				setState(176);
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
			setState(181);
			switch (_input.LA(1)) {
			case UNIVERSE:
				enterOuterAlt(_localctx, 1);
				{
				setState(177); universe();
				}
				break;
			case ATTRIBUTE_ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(178); attributeID();
				}
				break;
			case ATTRIBUTE_NAME:
				enterOuterAlt(_localctx, 3);
				{
				setState(179); attributeName();
				}
				break;
			case ATTRIBUTE_MODEL:
				enterOuterAlt(_localctx, 4);
				{
				setState(180); attributeModel();
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
			setState(183); location();
			setState(188);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SEP) {
				{
				{
				setState(184); match(SEP);
				setState(185); location();
				}
				}
				setState(190);
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
			setState(194);
			switch (_input.LA(1)) {
			case UNIVERSE:
				enterOuterAlt(_localctx, 1);
				{
				setState(191); universe();
				}
				break;
			case INTEGER:
				enterOuterAlt(_localctx, 2);
				{
				setState(192); locationID();
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 3);
				{
				setState(193); locationDirectory();
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
			setState(196); match(UNIVERSE);
			}
		}
		catch (RecognitionException re) {
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
			setState(198); match(ATTRIBUTE_ID);
			}
		}
		catch (RecognitionException re) {
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
			setState(200); match(ATTRIBUTE_NAME);
			}
		}
		catch (RecognitionException re) {
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
			setState(202); match(ATTRIBUTE_MODEL);
			}
		}
		catch (RecognitionException re) {
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
			setState(204); match(INTEGER);
			}
		}
		catch (RecognitionException re) {
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
			setState(206); match(STRING);
			}
		}
		catch (RecognitionException re) {
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
			setState(212);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(208); tasks();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(209); union();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(210); intersection();
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(211); difference();
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
			setState(221);
			switch (_input.LA(1)) {
			case UNIVERSE:
			case VARIABLE_NAME:
				enterOuterAlt(_localctx, 1);
				{
				setState(216);
				switch (_input.LA(1)) {
				case VARIABLE_NAME:
					{
					setState(214); varName();
					}
					break;
				case UNIVERSE:
					{
					setState(215); universe();
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
				setState(218); setOfTasksLiteral();
				}
				break;
			case GET_TASKS:
				enterOuterAlt(_localctx, 3);
				{
				setState(219); setOfTasksConstruction();
				}
				break;
			case LP:
				enterOuterAlt(_localctx, 4);
				{
				setState(220); setOfTasksParentheses();
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
			setState(223); match(LB);
			setState(232);
			_la = _input.LA(1);
			if (_la==STRING || _la==TILDE) {
				{
				setState(224); task();
				setState(229);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SEP) {
					{
					{
					setState(225); match(SEP);
					setState(226); task();
					}
					}
					setState(231);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(234); match(RB);
			}
		}
		catch (RecognitionException re) {
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
			setState(236); match(LTB);
			setState(245);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << UNIVERSE) | (1L << STRING) | (1L << TILDE))) != 0)) {
				{
				setState(237); event();
				setState(242);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SEP) {
					{
					{
					setState(238); match(SEP);
					setState(239); event();
					}
					}
					setState(244);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(247); match(RTB);
			}
		}
		catch (RecognitionException re) {
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
			setState(251);
			switch (_input.LA(1)) {
			case UNIVERSE:
				enterOuterAlt(_localctx, 1);
				{
				setState(249); universe();
				}
				break;
			case STRING:
			case TILDE:
				enterOuterAlt(_localctx, 2);
				{
				setState(250); task();
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
			setState(263);
			switch (_input.LA(1)) {
			case TILDE:
				enterOuterAlt(_localctx, 1);
				{
				setState(253); approximate();
				setState(254); label();
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 2);
				{
				setState(256); label();
				setState(261);
				_la = _input.LA(1);
				if (_la==LSB) {
					{
					setState(257); match(LSB);
					setState(258); similarity();
					setState(259); match(RSB);
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
			setState(265); match(TILDE);
			}
		}
		catch (RecognitionException re) {
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
			setState(267); match(STRING);
			}
		}
		catch (RecognitionException re) {
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
			setState(269); match(SIMILARITY);
			}
		}
		catch (RecognitionException re) {
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
			setState(273);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(271); unaryPredicateConstruction();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(272); binaryPredicateConstruction();
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
			setState(275); match(GET_TASKS);
			}
			setState(276); unaryPredicateName();
			setState(277); match(LP);
			setState(278); setOfTasks();
			setState(279); match(RP);
			}
		}
		catch (RecognitionException re) {
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
			setState(281); match(GET_TASKS);
			}
			setState(282); binaryPredicateName();
			setState(283); match(LP);
			setState(284); setOfTasks();
			setState(285); match(SEP);
			setState(286); setOfTasks();
			setState(287); match(SEP);
			setState(288); anyAll();
			setState(289); match(RP);
			}
		}
		catch (RecognitionException re) {
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
			setState(291);
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
			setState(293);
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
			setState(295); match(EXECUTES);
			}
		}
		catch (RecognitionException re) {
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
			setState(297);
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
			setState(303);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(299); proposition();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(300); conjunction();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(301); disjunction();
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(302); logicalTest();
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
			setState(314);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(305); unaryPredicate();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(306); binaryPredicate();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(307); unaryTracePredicate();
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(308); unaryPredicateMacro();
				}
				break;

			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(309); binaryPredicateMacro();
				}
				break;

			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(310); setPredicate();
				}
				break;

			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(311); truthValue();
				}
				break;

			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(312); parentheses();
				}
				break;

			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(313); negation();
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
			setState(316); unaryPredicateName();
			setState(317); match(LP);
			setState(318); task();
			setState(319); match(RP);
			}
		}
		catch (RecognitionException re) {
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
			setState(321); binaryPredicateName();
			setState(322); match(LP);
			setState(323); task();
			setState(324); match(SEP);
			setState(325); task();
			setState(326); match(RP);
			}
		}
		catch (RecognitionException re) {
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
			setState(328); unaryTracePredicateName();
			setState(329); match(LP);
			setState(330); trace();
			setState(331); match(RP);
			}
		}
		catch (RecognitionException re) {
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
			setState(333); unaryPredicateName();
			setState(334); match(LP);
			setState(335); setOfTasks();
			setState(336); match(SEP);
			setState(337); anyAll();
			setState(338); match(RP);
			}
		}
		catch (RecognitionException re) {
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
			setState(342);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(340); binaryPredicateMacroTaskSet();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(341); binaryPredicateMacroSetSet();
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
			setState(344); binaryPredicateName();
			setState(345); match(LP);
			setState(346); task();
			setState(347); match(SEP);
			setState(348); setOfTasks();
			setState(349); match(SEP);
			setState(350); anyAll();
			setState(351); match(RP);
			}
		}
		catch (RecognitionException re) {
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
			setState(353); binaryPredicateName();
			setState(354); match(LP);
			setState(355); setOfTasks();
			setState(356); match(SEP);
			setState(357); setOfTasks();
			setState(358); match(SEP);
			setState(359); anyEachAll();
			setState(360); match(RP);
			}
		}
		catch (RecognitionException re) {
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
			setState(362);
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
			setState(366);
			switch (_input.LA(1)) {
			case STRING:
			case TILDE:
				enterOuterAlt(_localctx, 1);
				{
				setState(364); taskInSetOfTasks();
				}
				break;
			case UNIVERSE:
			case VARIABLE_NAME:
			case LP:
			case LB:
			case GET_TASKS:
				enterOuterAlt(_localctx, 2);
				{
				setState(365); setComparison();
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
			setState(368); task();
			setState(369); match(IN);
			setState(370); setOfTasks();
			}
		}
		catch (RecognitionException re) {
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
			setState(372); setOfTasks();
			setState(373); setComparisonOperator();
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
			setState(381);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(376); identical();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(377); different();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(378); overlapsWith();
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(379); subsetOf();
				}
				break;

			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(380); properSubsetOf();
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
		enterRule(_localctx, 92, RULE_truthValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(383);
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
		enterRule(_localctx, 94, RULE_logicalTest);
		try {
			setState(389);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(385); isTrue();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(386); isNotTrue();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(387); isFalse();
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(388); isNotFalse();
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
			setState(394);
			switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
			case 1:
				{
				setState(391); tasks();
				}
				break;

			case 2:
				{
				setState(392); difference();
				}
				break;

			case 3:
				{
				setState(393); intersection();
				}
				break;
			}
			setState(396); match(UNION);
			setState(400);
			switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
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
			setState(410);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==UNION) {
				{
				{
				setState(402); match(UNION);
				setState(406);
				switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
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
				}
				}
				setState(412);
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
			setState(415);
			switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
			case 1:
				{
				setState(413); tasks();
				}
				break;

			case 2:
				{
				setState(414); difference();
				}
				break;
			}
			setState(417); match(INTERSECTION);
			setState(420);
			switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
			case 1:
				{
				setState(418); tasks();
				}
				break;

			case 2:
				{
				setState(419); difference();
				}
				break;
			}
			setState(429);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==INTERSECTION) {
				{
				{
				setState(422); match(INTERSECTION);
				setState(425);
				switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
				case 1:
					{
					setState(423); tasks();
					}
					break;

				case 2:
					{
					setState(424); difference();
					}
					break;
				}
				}
				}
				setState(431);
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
			setState(440);
			switch ( getInterpreter().adaptivePredict(_input,33,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(432); tasks();
				setState(433); match(DIFFERENCE);
				setState(434); tasks();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(436); tasks();
				setState(437); match(DIFFERENCE);
				setState(438); difference();
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
			setState(442); match(NOT);
			setState(443); proposition();
			}
		}
		catch (RecognitionException re) {
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
			setState(445); proposition();
			setState(446); match(IS);
			setState(447); match(TRUE);
			}
		}
		catch (RecognitionException re) {
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
			setState(449); proposition();
			setState(450); match(IS);
			setState(451); match(NOT);
			setState(452); match(TRUE);
			}
		}
		catch (RecognitionException re) {
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
			setState(454); proposition();
			setState(455); match(IS);
			setState(456); match(FALSE);
			}
		}
		catch (RecognitionException re) {
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
			setState(458); proposition();
			setState(459); match(IS);
			setState(460); match(NOT);
			setState(461); match(FALSE);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 112, RULE_disjunction);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(466);
			switch ( getInterpreter().adaptivePredict(_input,34,_ctx) ) {
			case 1:
				{
				setState(463); proposition();
				}
				break;

			case 2:
				{
				setState(464); logicalTest();
				}
				break;

			case 3:
				{
				setState(465); conjunction();
				}
				break;
			}
			setState(468); match(OR);
			setState(472);
			switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
			case 1:
				{
				setState(469); proposition();
				}
				break;

			case 2:
				{
				setState(470); logicalTest();
				}
				break;

			case 3:
				{
				setState(471); conjunction();
				}
				break;
			}
			setState(482);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR) {
				{
				{
				setState(474); match(OR);
				setState(478);
				switch ( getInterpreter().adaptivePredict(_input,36,_ctx) ) {
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

				case 3:
					{
					setState(477); conjunction();
					}
					break;
				}
				}
				}
				setState(484);
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
		enterRule(_localctx, 114, RULE_conjunction);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(487);
			switch ( getInterpreter().adaptivePredict(_input,38,_ctx) ) {
			case 1:
				{
				setState(485); proposition();
				}
				break;

			case 2:
				{
				setState(486); logicalTest();
				}
				break;
			}
			setState(489); match(AND);
			setState(492);
			switch ( getInterpreter().adaptivePredict(_input,39,_ctx) ) {
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
			}
			setState(501);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AND) {
				{
				{
				setState(494); match(AND);
				setState(497);
				switch ( getInterpreter().adaptivePredict(_input,40,_ctx) ) {
				case 1:
					{
					setState(495); proposition();
					}
					break;

				case 2:
					{
					setState(496); logicalTest();
					}
					break;
				}
				}
				}
				setState(503);
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
		enterRule(_localctx, 116, RULE_parentheses);
		try {
			setState(520);
			switch ( getInterpreter().adaptivePredict(_input,42,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(504); match(LP);
				setState(505); proposition();
				setState(506); match(RP);
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(508); match(LP);
				setState(509); conjunction();
				setState(510); match(RP);
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(512); match(LP);
				setState(513); disjunction();
				setState(514); match(RP);
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(516); match(LP);
				setState(517); logicalTest();
				setState(518); match(RP);
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
		enterRule(_localctx, 118, RULE_setOfTasksParentheses);
		try {
			setState(554);
			switch ( getInterpreter().adaptivePredict(_input,43,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(522); match(LP);
				setState(523); varName();
				setState(524); match(RP);
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(526); match(LP);
				setState(527); universe();
				setState(528); match(RP);
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(530); match(LP);
				setState(531); setOfTasksLiteral();
				setState(532); match(RP);
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(534); match(LP);
				setState(535); setOfTasksConstruction();
				setState(536); match(RP);
				}
				break;

			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(538); match(LP);
				setState(539); union();
				setState(540); match(RP);
				}
				break;

			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(542); match(LP);
				setState(543); difference();
				setState(544); match(RP);
				}
				break;

			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(546); match(LP);
				setState(547); intersection();
				setState(548); match(RP);
				}
				break;

			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(550); match(LP);
				setState(551); setOfTasksParentheses();
				setState(552); match(RP);
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
		enterRule(_localctx, 120, RULE_identical);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(556); match(EQUALS);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 122, RULE_different);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(558); match(NOT);
			setState(559); match(EQUALS);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 124, RULE_overlapsWith);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(561); match(OVERLAPS);
			setState(562); match(WITH);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 126, RULE_subsetOf);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(564); match(IS);
			setState(565); match(SUBSET);
			setState(566); match(OF);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 128, RULE_properSubsetOf);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(568); match(IS);
			setState(569); match(PROPER);
			setState(570); match(SUBSET);
			setState(571); match(OF);
			}
		}
		catch (RecognitionException re) {
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
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\3>\u0240\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\3\2\3\2\5\2\u0087\n\2\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\5\3\u0090\n\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4\u009b"+
		"\n\4\3\4\3\4\3\5\7\5\u00a0\n\5\f\5\16\5\u00a3\13\5\3\6\3\6\3\6\3\6\3\6"+
		"\3\7\3\7\3\b\3\b\3\b\7\b\u00af\n\b\f\b\16\b\u00b2\13\b\3\t\3\t\3\t\3\t"+
		"\5\t\u00b8\n\t\3\n\3\n\3\n\7\n\u00bd\n\n\f\n\16\n\u00c0\13\n\3\13\3\13"+
		"\3\13\5\13\u00c5\n\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3"+
		"\21\3\21\3\22\3\22\3\22\3\22\5\22\u00d7\n\22\3\23\3\23\5\23\u00db\n\23"+
		"\3\23\3\23\3\23\5\23\u00e0\n\23\3\24\3\24\3\24\3\24\7\24\u00e6\n\24\f"+
		"\24\16\24\u00e9\13\24\5\24\u00eb\n\24\3\24\3\24\3\25\3\25\3\25\3\25\7"+
		"\25\u00f3\n\25\f\25\16\25\u00f6\13\25\5\25\u00f8\n\25\3\25\3\25\3\26\3"+
		"\26\5\26\u00fe\n\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\5\27\u0108"+
		"\n\27\5\27\u010a\n\27\3\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33\5\33\u0114"+
		"\n\33\3\34\3\34\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\35\3\35"+
		"\3\35\3\35\3\35\3\36\3\36\3\37\3\37\3 \3 \3!\3!\3\"\3\"\3\"\3\"\5\"\u0132"+
		"\n\"\3#\3#\3#\3#\3#\3#\3#\3#\3#\5#\u013d\n#\3$\3$\3$\3$\3$\3%\3%\3%\3"+
		"%\3%\3%\3%\3&\3&\3&\3&\3&\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3(\3(\5(\u0159\n"+
		"(\3)\3)\3)\3)\3)\3)\3)\3)\3)\3*\3*\3*\3*\3*\3*\3*\3*\3*\3+\3+\3,\3,\5"+
		",\u0171\n,\3-\3-\3-\3-\3.\3.\3.\3.\3/\3/\3/\3/\3/\5/\u0180\n/\3\60\3\60"+
		"\3\61\3\61\3\61\3\61\5\61\u0188\n\61\3\62\3\62\3\62\5\62\u018d\n\62\3"+
		"\62\3\62\3\62\3\62\5\62\u0193\n\62\3\62\3\62\3\62\3\62\5\62\u0199\n\62"+
		"\7\62\u019b\n\62\f\62\16\62\u019e\13\62\3\63\3\63\5\63\u01a2\n\63\3\63"+
		"\3\63\3\63\5\63\u01a7\n\63\3\63\3\63\3\63\5\63\u01ac\n\63\7\63\u01ae\n"+
		"\63\f\63\16\63\u01b1\13\63\3\64\3\64\3\64\3\64\3\64\3\64\3\64\3\64\5\64"+
		"\u01bb\n\64\3\65\3\65\3\65\3\66\3\66\3\66\3\66\3\67\3\67\3\67\3\67\3\67"+
		"\38\38\38\38\39\39\39\39\39\3:\3:\3:\5:\u01d5\n:\3:\3:\3:\3:\5:\u01db"+
		"\n:\3:\3:\3:\3:\5:\u01e1\n:\7:\u01e3\n:\f:\16:\u01e6\13:\3;\3;\5;\u01ea"+
		"\n;\3;\3;\3;\5;\u01ef\n;\3;\3;\3;\5;\u01f4\n;\7;\u01f6\n;\f;\16;\u01f9"+
		"\13;\3<\3<\3<\3<\3<\3<\3<\3<\3<\3<\3<\3<\3<\3<\3<\3<\5<\u020b\n<\3=\3"+
		"=\3=\3=\3=\3=\3=\3=\3=\3=\3=\3=\3=\3=\3=\3=\3=\3=\3=\3=\3=\3=\3=\3=\3"+
		"=\3=\3=\3=\3=\3=\3=\3=\5=\u022d\n=\3>\3>\3?\3?\3?\3@\3@\3@\3A\3A\3A\3"+
		"A\3B\3B\3B\3B\3B\3B\2C\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,"+
		".\60\62\64\668:<>@BDFHJLNPRTVXZ\\^`bdfhjlnprtvxz|~\u0080\u0082\2\7\4\2"+
		"++--\3\2\66\67\3\29>\3\2+-\3\2\61\62\u024d\2\u0086\3\2\2\2\4\u0088\3\2"+
		"\2\2\6\u0093\3\2\2\2\b\u00a1\3\2\2\2\n\u00a4\3\2\2\2\f\u00a9\3\2\2\2\16"+
		"\u00ab\3\2\2\2\20\u00b7\3\2\2\2\22\u00b9\3\2\2\2\24\u00c4\3\2\2\2\26\u00c6"+
		"\3\2\2\2\30\u00c8\3\2\2\2\32\u00ca\3\2\2\2\34\u00cc\3\2\2\2\36\u00ce\3"+
		"\2\2\2 \u00d0\3\2\2\2\"\u00d6\3\2\2\2$\u00df\3\2\2\2&\u00e1\3\2\2\2(\u00ee"+
		"\3\2\2\2*\u00fd\3\2\2\2,\u0109\3\2\2\2.\u010b\3\2\2\2\60\u010d\3\2\2\2"+
		"\62\u010f\3\2\2\2\64\u0113\3\2\2\2\66\u0115\3\2\2\28\u011b\3\2\2\2:\u0125"+
		"\3\2\2\2<\u0127\3\2\2\2>\u0129\3\2\2\2@\u012b\3\2\2\2B\u0131\3\2\2\2D"+
		"\u013c\3\2\2\2F\u013e\3\2\2\2H\u0143\3\2\2\2J\u014a\3\2\2\2L\u014f\3\2"+
		"\2\2N\u0158\3\2\2\2P\u015a\3\2\2\2R\u0163\3\2\2\2T\u016c\3\2\2\2V\u0170"+
		"\3\2\2\2X\u0172\3\2\2\2Z\u0176\3\2\2\2\\\u017f\3\2\2\2^\u0181\3\2\2\2"+
		"`\u0187\3\2\2\2b\u018c\3\2\2\2d\u01a1\3\2\2\2f\u01ba\3\2\2\2h\u01bc\3"+
		"\2\2\2j\u01bf\3\2\2\2l\u01c3\3\2\2\2n\u01c8\3\2\2\2p\u01cc\3\2\2\2r\u01d4"+
		"\3\2\2\2t\u01e9\3\2\2\2v\u020a\3\2\2\2x\u022c\3\2\2\2z\u022e\3\2\2\2|"+
		"\u0230\3\2\2\2~\u0233\3\2\2\2\u0080\u0236\3\2\2\2\u0082\u023a\3\2\2\2"+
		"\u0084\u0087\5\4\3\2\u0085\u0087\5\6\4\2\u0086\u0084\3\2\2\2\u0086\u0085"+
		"\3\2\2\2\u0087\3\3\2\2\2\u0088\u0089\5\b\5\2\u0089\u008a\7\35\2\2\u008a"+
		"\u008b\5\16\b\2\u008b\u008c\7 \2\2\u008c\u008f\5\22\n\2\u008d\u008e\7"+
		"!\2\2\u008e\u0090\5B\"\2\u008f\u008d\3\2\2\2\u008f\u0090\3\2\2\2\u0090"+
		"\u0091\3\2\2\2\u0091\u0092\7\24\2\2\u0092\5\3\2\2\2\u0093\u0094\5\b\5"+
		"\2\u0094\u0095\7\36\2\2\u0095\u0096\5(\25\2\u0096\u0097\7\37\2\2\u0097"+
		"\u009a\5\22\n\2\u0098\u0099\7!\2\2\u0099\u009b\5B\"\2\u009a\u0098\3\2"+
		"\2\2\u009a\u009b\3\2\2\2\u009b\u009c\3\2\2\2\u009c\u009d\7\24\2\2\u009d"+
		"\7\3\2\2\2\u009e\u00a0\5\n\6\2\u009f\u009e\3\2\2\2\u00a0\u00a3\3\2\2\2"+
		"\u00a1\u009f\3\2\2\2\u00a1\u00a2\3\2\2\2\u00a2\t\3\2\2\2\u00a3\u00a1\3"+
		"\2\2\2\u00a4\u00a5\5\f\7\2\u00a5\u00a6\7\26\2\2\u00a6\u00a7\5\"\22\2\u00a7"+
		"\u00a8\7\24\2\2\u00a8\13\3\2\2\2\u00a9\u00aa\7\t\2\2\u00aa\r\3\2\2\2\u00ab"+
		"\u00b0\5\20\t\2\u00ac\u00ad\7\25\2\2\u00ad\u00af\5\20\t\2\u00ae\u00ac"+
		"\3\2\2\2\u00af\u00b2\3\2\2\2\u00b0\u00ae\3\2\2\2\u00b0\u00b1\3\2\2\2\u00b1"+
		"\17\3\2\2\2\u00b2\u00b0\3\2\2\2\u00b3\u00b8\5\26\f\2\u00b4\u00b8\5\30"+
		"\r\2\u00b5\u00b8\5\32\16\2\u00b6\u00b8\5\34\17\2\u00b7\u00b3\3\2\2\2\u00b7"+
		"\u00b4\3\2\2\2\u00b7\u00b5\3\2\2\2\u00b7\u00b6\3\2\2\2\u00b8\21\3\2\2"+
		"\2\u00b9\u00be\5\24\13\2\u00ba\u00bb\7\25\2\2\u00bb\u00bd\5\24\13\2\u00bc"+
		"\u00ba\3\2\2\2\u00bd\u00c0\3\2\2\2\u00be\u00bc\3\2\2\2\u00be\u00bf\3\2"+
		"\2\2\u00bf\23\3\2\2\2\u00c0\u00be\3\2\2\2\u00c1\u00c5\5\26\f\2\u00c2\u00c5"+
		"\5\36\20\2\u00c3\u00c5\5 \21\2\u00c4\u00c1\3\2\2\2\u00c4\u00c2\3\2\2\2"+
		"\u00c4\u00c3\3\2\2\2\u00c5\25\3\2\2\2\u00c6\u00c7\7\3\2\2\u00c7\27\3\2"+
		"\2\2\u00c8\u00c9\7\4\2\2\u00c9\31\3\2\2\2\u00ca\u00cb\7\5\2\2\u00cb\33"+
		"\3\2\2\2\u00cc\u00cd\7\6\2\2\u00cd\35\3\2\2\2\u00ce\u00cf\7\b\2\2\u00cf"+
		"\37\3\2\2\2\u00d0\u00d1\7\7\2\2\u00d1!\3\2\2\2\u00d2\u00d7\5$\23\2\u00d3"+
		"\u00d7\5b\62\2\u00d4\u00d7\5d\63\2\u00d5\u00d7\5f\64\2\u00d6\u00d2\3\2"+
		"\2\2\u00d6\u00d3\3\2\2\2\u00d6\u00d4\3\2\2\2\u00d6\u00d5\3\2\2\2\u00d7"+
		"#\3\2\2\2\u00d8\u00db\5\f\7\2\u00d9\u00db\5\26\f\2\u00da\u00d8\3\2\2\2"+
		"\u00da\u00d9\3\2\2\2\u00db\u00e0\3\2\2\2\u00dc\u00e0\5&\24\2\u00dd\u00e0"+
		"\5\64\33\2\u00de\u00e0\5x=\2\u00df\u00da\3\2\2\2\u00df\u00dc\3\2\2\2\u00df"+
		"\u00dd\3\2\2\2\u00df\u00de\3\2\2\2\u00e0%\3\2\2\2\u00e1\u00ea\7\r\2\2"+
		"\u00e2\u00e7\5,\27\2\u00e3\u00e4\7\25\2\2\u00e4\u00e6\5,\27\2\u00e5\u00e3"+
		"\3\2\2\2\u00e6\u00e9\3\2\2\2\u00e7\u00e5\3\2\2\2\u00e7\u00e8\3\2\2\2\u00e8"+
		"\u00eb\3\2\2\2\u00e9\u00e7\3\2\2\2\u00ea\u00e2\3\2\2\2\u00ea\u00eb\3\2"+
		"\2\2\u00eb\u00ec\3\2\2\2\u00ec\u00ed\7\16\2\2\u00ed\'\3\2\2\2\u00ee\u00f7"+
		"\7\21\2\2\u00ef\u00f4\5*\26\2\u00f0\u00f1\7\25\2\2\u00f1\u00f3\5*\26\2"+
		"\u00f2\u00f0\3\2\2\2\u00f3\u00f6\3\2\2\2\u00f4\u00f2\3\2\2\2\u00f4\u00f5"+
		"\3\2\2\2\u00f5\u00f8\3\2\2\2\u00f6\u00f4\3\2\2\2\u00f7\u00ef\3\2\2\2\u00f7"+
		"\u00f8\3\2\2\2\u00f8\u00f9\3\2\2\2\u00f9\u00fa\7\22\2\2\u00fa)\3\2\2\2"+
		"\u00fb\u00fe\5\26\f\2\u00fc\u00fe\5,\27\2\u00fd\u00fb\3\2\2\2\u00fd\u00fc"+
		"\3\2\2\2\u00fe+\3\2\2\2\u00ff\u0100\5.\30\2\u0100\u0101\5\60\31\2\u0101"+
		"\u010a\3\2\2\2\u0102\u0107\5\60\31\2\u0103\u0104\7\17\2\2\u0104\u0105"+
		"\5\62\32\2\u0105\u0106\7\20\2\2\u0106\u0108\3\2\2\2\u0107\u0103\3\2\2"+
		"\2\u0107\u0108\3\2\2\2\u0108\u010a\3\2\2\2\u0109\u00ff\3\2\2\2\u0109\u0102"+
		"\3\2\2\2\u010a-\3\2\2\2\u010b\u010c\7\27\2\2\u010c/\3\2\2\2\u010d\u010e"+
		"\7\7\2\2\u010e\61\3\2\2\2\u010f\u0110\7\n\2\2\u0110\63\3\2\2\2\u0111\u0114"+
		"\5\66\34\2\u0112\u0114\58\35\2\u0113\u0111\3\2\2\2\u0113\u0112\3\2\2\2"+
		"\u0114\65\3\2\2\2\u0115\u0116\7\'\2\2\u0116\u0117\5<\37\2\u0117\u0118"+
		"\7\13\2\2\u0118\u0119\5\"\22\2\u0119\u011a\7\f\2\2\u011a\67\3\2\2\2\u011b"+
		"\u011c\7\'\2\2\u011c\u011d\5@!\2\u011d\u011e\7\13\2\2\u011e\u011f\5\""+
		"\22\2\u011f\u0120\7\25\2\2\u0120\u0121\5\"\22\2\u0121\u0122\7\25\2\2\u0122"+
		"\u0123\5:\36\2\u0123\u0124\7\f\2\2\u01249\3\2\2\2\u0125\u0126\t\2\2\2"+
		"\u0126;\3\2\2\2\u0127\u0128\t\3\2\2\u0128=\3\2\2\2\u0129\u012a\78\2\2"+
		"\u012a?\3\2\2\2\u012b\u012c\t\4\2\2\u012cA\3\2\2\2\u012d\u0132\5D#\2\u012e"+
		"\u0132\5t;\2\u012f\u0132\5r:\2\u0130\u0132\5`\61\2\u0131\u012d\3\2\2\2"+
		"\u0131\u012e\3\2\2\2\u0131\u012f\3\2\2\2\u0131\u0130\3\2\2\2\u0132C\3"+
		"\2\2\2\u0133\u013d\5F$\2\u0134\u013d\5H%\2\u0135\u013d\5J&\2\u0136\u013d"+
		"\5L\'\2\u0137\u013d\5N(\2\u0138\u013d\5V,\2\u0139\u013d\5^\60\2\u013a"+
		"\u013d\5v<\2\u013b\u013d\5h\65\2\u013c\u0133\3\2\2\2\u013c\u0134\3\2\2"+
		"\2\u013c\u0135\3\2\2\2\u013c\u0136\3\2\2\2\u013c\u0137\3\2\2\2\u013c\u0138"+
		"\3\2\2\2\u013c\u0139\3\2\2\2\u013c\u013a\3\2\2\2\u013c\u013b\3\2\2\2\u013d"+
		"E\3\2\2\2\u013e\u013f\5<\37\2\u013f\u0140\7\13\2\2\u0140\u0141\5,\27\2"+
		"\u0141\u0142\7\f\2\2\u0142G\3\2\2\2\u0143\u0144\5@!\2\u0144\u0145\7\13"+
		"\2\2\u0145\u0146\5,\27\2\u0146\u0147\7\25\2\2\u0147\u0148\5,\27\2\u0148"+
		"\u0149\7\f\2\2\u0149I\3\2\2\2\u014a\u014b\5> \2\u014b\u014c\7\13\2\2\u014c"+
		"\u014d\5(\25\2\u014d\u014e\7\f\2\2\u014eK\3\2\2\2\u014f\u0150\5<\37\2"+
		"\u0150\u0151\7\13\2\2\u0151\u0152\5\"\22\2\u0152\u0153\7\25\2\2\u0153"+
		"\u0154\5:\36\2\u0154\u0155\7\f\2\2\u0155M\3\2\2\2\u0156\u0159\5P)\2\u0157"+
		"\u0159\5R*\2\u0158\u0156\3\2\2\2\u0158\u0157\3\2\2\2\u0159O\3\2\2\2\u015a"+
		"\u015b\5@!\2\u015b\u015c\7\13\2\2\u015c\u015d\5,\27\2\u015d\u015e\7\25"+
		"\2\2\u015e\u015f\5\"\22\2\u015f\u0160\7\25\2\2\u0160\u0161\5:\36\2\u0161"+
		"\u0162\7\f\2\2\u0162Q\3\2\2\2\u0163\u0164\5@!\2\u0164\u0165\7\13\2\2\u0165"+
		"\u0166\5\"\22\2\u0166\u0167\7\25\2\2\u0167\u0168\5\"\22\2\u0168\u0169"+
		"\7\25\2\2\u0169\u016a\5T+\2\u016a\u016b\7\f\2\2\u016bS\3\2\2\2\u016c\u016d"+
		"\t\5\2\2\u016dU\3\2\2\2\u016e\u0171\5X-\2\u016f\u0171\5Z.\2\u0170\u016e"+
		"\3\2\2\2\u0170\u016f\3\2\2\2\u0171W\3\2\2\2\u0172\u0173\5,\27\2\u0173"+
		"\u0174\7.\2\2\u0174\u0175\5\"\22\2\u0175Y\3\2\2\2\u0176\u0177\5\"\22\2"+
		"\u0177\u0178\5\\/\2\u0178\u0179\5\"\22\2\u0179[\3\2\2\2\u017a\u0180\5"+
		"z>\2\u017b\u0180\5|?\2\u017c\u0180\5~@\2\u017d\u0180\5\u0080A\2\u017e"+
		"\u0180\5\u0082B\2\u017f\u017a\3\2\2\2\u017f\u017b\3\2\2\2\u017f\u017c"+
		"\3\2\2\2\u017f\u017d\3\2\2\2\u017f\u017e\3\2\2\2\u0180]\3\2\2\2\u0181"+
		"\u0182\t\6\2\2\u0182_\3\2\2\2\u0183\u0188\5j\66\2\u0184\u0188\5l\67\2"+
		"\u0185\u0188\5n8\2\u0186\u0188\5p9\2\u0187\u0183\3\2\2\2\u0187\u0184\3"+
		"\2\2\2\u0187\u0185\3\2\2\2\u0187\u0186\3\2\2\2\u0188a\3\2\2\2\u0189\u018d"+
		"\5$\23\2\u018a\u018d\5f\64\2\u018b\u018d\5d\63\2\u018c\u0189\3\2\2\2\u018c"+
		"\u018a\3\2\2\2\u018c\u018b\3\2\2\2\u018d\u018e\3\2\2\2\u018e\u0192\7\63"+
		"\2\2\u018f\u0193\5$\23\2\u0190\u0193\5f\64\2\u0191\u0193\5d\63\2\u0192"+
		"\u018f\3\2\2\2\u0192\u0190\3\2\2\2\u0192\u0191\3\2\2\2\u0193\u019c\3\2"+
		"\2\2\u0194\u0198\7\63\2\2\u0195\u0199\5$\23\2\u0196\u0199\5f\64\2\u0197"+
		"\u0199\5d\63\2\u0198\u0195\3\2\2\2\u0198\u0196\3\2\2\2\u0198\u0197\3\2"+
		"\2\2\u0199\u019b\3\2\2\2\u019a\u0194\3\2\2\2\u019b\u019e\3\2\2\2\u019c"+
		"\u019a\3\2\2\2\u019c\u019d\3\2\2\2\u019dc\3\2\2\2\u019e\u019c\3\2\2\2"+
		"\u019f\u01a2\5$\23\2\u01a0\u01a2\5f\64\2\u01a1\u019f\3\2\2\2\u01a1\u01a0"+
		"\3\2\2\2\u01a2\u01a3\3\2\2\2\u01a3\u01a6\7\64\2\2\u01a4\u01a7\5$\23\2"+
		"\u01a5\u01a7\5f\64\2\u01a6\u01a4\3\2\2\2\u01a6\u01a5\3\2\2\2\u01a7\u01af"+
		"\3\2\2\2\u01a8\u01ab\7\64\2\2\u01a9\u01ac\5$\23\2\u01aa\u01ac\5f\64\2"+
		"\u01ab\u01a9\3\2\2\2\u01ab\u01aa\3\2\2\2\u01ac\u01ae\3\2\2\2\u01ad\u01a8"+
		"\3\2\2\2\u01ae\u01b1\3\2\2\2\u01af\u01ad\3\2\2\2\u01af\u01b0\3\2\2\2\u01b0"+
		"e\3\2\2\2\u01b1\u01af\3\2\2\2\u01b2\u01b3\5$\23\2\u01b3\u01b4\7\65\2\2"+
		"\u01b4\u01b5\5$\23\2\u01b5\u01bb\3\2\2\2\u01b6\u01b7\5$\23\2\u01b7\u01b8"+
		"\7\65\2\2\u01b8\u01b9\5f\64\2\u01b9\u01bb\3\2\2\2\u01ba\u01b2\3\2\2\2"+
		"\u01ba\u01b6\3\2\2\2\u01bbg\3\2\2\2\u01bc\u01bd\7(\2\2\u01bd\u01be\5D"+
		"#\2\u01bei\3\2\2\2\u01bf\u01c0\5D#\2\u01c0\u01c1\7/\2\2\u01c1\u01c2\7"+
		"\61\2\2\u01c2k\3\2\2\2\u01c3\u01c4\5D#\2\u01c4\u01c5\7/\2\2\u01c5\u01c6"+
		"\7(\2\2\u01c6\u01c7\7\61\2\2\u01c7m\3\2\2\2\u01c8\u01c9\5D#\2\u01c9\u01ca"+
		"\7/\2\2\u01ca\u01cb\7\62\2\2\u01cbo\3\2\2\2\u01cc\u01cd\5D#\2\u01cd\u01ce"+
		"\7/\2\2\u01ce\u01cf\7(\2\2\u01cf\u01d0\7\62\2\2\u01d0q\3\2\2\2\u01d1\u01d5"+
		"\5D#\2\u01d2\u01d5\5`\61\2\u01d3\u01d5\5t;\2\u01d4\u01d1\3\2\2\2\u01d4"+
		"\u01d2\3\2\2\2\u01d4\u01d3\3\2\2\2\u01d5\u01d6\3\2\2\2\u01d6\u01da\7*"+
		"\2\2\u01d7\u01db\5D#\2\u01d8\u01db\5`\61\2\u01d9\u01db\5t;\2\u01da\u01d7"+
		"\3\2\2\2\u01da\u01d8\3\2\2\2\u01da\u01d9\3\2\2\2\u01db\u01e4\3\2\2\2\u01dc"+
		"\u01e0\7*\2\2\u01dd\u01e1\5D#\2\u01de\u01e1\5`\61\2\u01df\u01e1\5t;\2"+
		"\u01e0\u01dd\3\2\2\2\u01e0\u01de\3\2\2\2\u01e0\u01df\3\2\2\2\u01e1\u01e3"+
		"\3\2\2\2\u01e2\u01dc\3\2\2\2\u01e3\u01e6\3\2\2\2\u01e4\u01e2\3\2\2\2\u01e4"+
		"\u01e5\3\2\2\2\u01e5s\3\2\2\2\u01e6\u01e4\3\2\2\2\u01e7\u01ea\5D#\2\u01e8"+
		"\u01ea\5`\61\2\u01e9\u01e7\3\2\2\2\u01e9\u01e8\3\2\2\2\u01ea\u01eb\3\2"+
		"\2\2\u01eb\u01ee\7)\2\2\u01ec\u01ef\5D#\2\u01ed\u01ef\5`\61\2\u01ee\u01ec"+
		"\3\2\2\2\u01ee\u01ed\3\2\2\2\u01ef\u01f7\3\2\2\2\u01f0\u01f3\7)\2\2\u01f1"+
		"\u01f4\5D#\2\u01f2\u01f4\5`\61\2\u01f3\u01f1\3\2\2\2\u01f3\u01f2\3\2\2"+
		"\2\u01f4\u01f6\3\2\2\2\u01f5\u01f0\3\2\2\2\u01f6\u01f9\3\2\2\2\u01f7\u01f5"+
		"\3\2\2\2\u01f7\u01f8\3\2\2\2\u01f8u\3\2\2\2\u01f9\u01f7\3\2\2\2\u01fa"+
		"\u01fb\7\13\2\2\u01fb\u01fc\5D#\2\u01fc\u01fd\7\f\2\2\u01fd\u020b\3\2"+
		"\2\2\u01fe\u01ff\7\13\2\2\u01ff\u0200\5t;\2\u0200\u0201\7\f\2\2\u0201"+
		"\u020b\3\2\2\2\u0202\u0203\7\13\2\2\u0203\u0204\5r:\2\u0204\u0205\7\f"+
		"\2\2\u0205\u020b\3\2\2\2\u0206\u0207\7\13\2\2\u0207\u0208\5`\61\2\u0208"+
		"\u0209\7\f\2\2\u0209\u020b\3\2\2\2\u020a\u01fa\3\2\2\2\u020a\u01fe\3\2"+
		"\2\2\u020a\u0202\3\2\2\2\u020a\u0206\3\2\2\2\u020bw\3\2\2\2\u020c\u020d"+
		"\7\13\2\2\u020d\u020e\5\f\7\2\u020e\u020f\7\f\2\2\u020f\u022d\3\2\2\2"+
		"\u0210\u0211\7\13\2\2\u0211\u0212\5\26\f\2\u0212\u0213\7\f\2\2\u0213\u022d"+
		"\3\2\2\2\u0214\u0215\7\13\2\2\u0215\u0216\5&\24\2\u0216\u0217\7\f\2\2"+
		"\u0217\u022d\3\2\2\2\u0218\u0219\7\13\2\2\u0219\u021a\5\64\33\2\u021a"+
		"\u021b\7\f\2\2\u021b\u022d\3\2\2\2\u021c\u021d\7\13\2\2\u021d\u021e\5"+
		"b\62\2\u021e\u021f\7\f\2\2\u021f\u022d\3\2\2\2\u0220\u0221\7\13\2\2\u0221"+
		"\u0222\5f\64\2\u0222\u0223\7\f\2\2\u0223\u022d\3\2\2\2\u0224\u0225\7\13"+
		"\2\2\u0225\u0226\5d\63\2\u0226\u0227\7\f\2\2\u0227\u022d\3\2\2\2\u0228"+
		"\u0229\7\13\2\2\u0229\u022a\5x=\2\u022a\u022b\7\f\2\2\u022b\u022d\3\2"+
		"\2\2\u022c\u020c\3\2\2\2\u022c\u0210\3\2\2\2\u022c\u0214\3\2\2\2\u022c"+
		"\u0218\3\2\2\2\u022c\u021c\3\2\2\2\u022c\u0220\3\2\2\2\u022c\u0224\3\2"+
		"\2\2\u022c\u0228\3\2\2\2\u022dy\3\2\2\2\u022e\u022f\7\"\2\2\u022f{\3\2"+
		"\2\2\u0230\u0231\7(\2\2\u0231\u0232\7\"\2\2\u0232}\3\2\2\2\u0233\u0234"+
		"\7#\2\2\u0234\u0235\7$\2\2\u0235\177\3\2\2\2\u0236\u0237\7/\2\2\u0237"+
		"\u0238\7%\2\2\u0238\u0239\7\60\2\2\u0239\u0081\3\2\2\2\u023a\u023b\7/"+
		"\2\2\u023b\u023c\7&\2\2\u023c\u023d\7%\2\2\u023d\u023e\7\60\2\2\u023e"+
		"\u0083\3\2\2\2.\u0086\u008f\u009a\u00a1\u00b0\u00b7\u00be\u00c4\u00d6"+
		"\u00da\u00df\u00e7\u00ea\u00f4\u00f7\u00fd\u0107\u0109\u0113\u0131\u013c"+
		"\u0158\u0170\u017f\u0187\u018c\u0192\u0198\u019c\u01a1\u01a6\u01ab\u01af"+
		"\u01ba\u01d4\u01da\u01e0\u01e4\u01e9\u01ee\u01f3\u01f7\u020a\u022c";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}