package org.pql.query;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.misc.Utils;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.tree.Tree;
import org.pql.antlr.PQLLexer;
import org.pql.antlr.PQLParser;
import org.pql.api.PQLErrorListener;
import org.pql.core.PQLAttribute;
import org.pql.core.PQLLocation;
import org.pql.core.PQLQuantifier;
import org.pql.core.PQLTask;
import org.pql.core.PQLTrace;
import org.pql.label.ILabelManager;
import org.pql.logic.IThreeValuedLogic;
import org.pql.logic.ThreeValuedLogicValue;

/**
 * An abstract implementation of the {@link IPQLQuery}} interface.
 * 
 * @author Artem Polyvyanyy
 */
public abstract class AbstractPQLQuery implements IPQLQuery {
	
	protected HashMap<String,Set<PQLTask>>	variables	= new HashMap<String,Set<PQLTask>>();
	protected HashSet<PQLAttribute>			attributes	= new HashSet<PQLAttribute>();
	protected Set<PQLLocation>				locations	= new HashSet<PQLLocation>();
	
	protected ParserRuleContext				parseTree		 = null;
	protected IThreeValuedLogic				threeValuedLogic = null;
	protected ILabelManager					labelMngr		 = null;
	
	protected Map<PQLTask,PQLTask>			task2task		 = null;
	
	PQLErrorListener						listener		 = null;
	
	protected abstract ThreeValuedLogicValue interpretUnaryPredicate(Token op, PQLTask a);
	
	protected abstract ThreeValuedLogicValue interpretUnaryTracePredicate(Token op, PQLTrace a); //A.P.
	
	protected abstract ThreeValuedLogicValue interpretBinaryPredicate(Token op, PQLTask a, PQLTask b);
	
	protected abstract Set<PQLTask> getAllTasks();
	
	/**
	 * Constructor of abstract PQL query objects.
	 * 
	 * @param query A PQL query given as a string or a path to a file with a query string. 
	 * @param logic An implementation of the {@link IThreeValuedLogic} interface to use to interpret this query.
	 * @param labelMngr An implementation of the {@link ILabelManager} interface to use to interpret this query.
	 */
	public AbstractPQLQuery(String query, IThreeValuedLogic logic, ILabelManager labelMngr) {
		if (logic==null || labelMngr==null) return;
		
		this.threeValuedLogic = logic;
		this.labelMngr  = labelMngr;
		
		this.task2task = new HashMap<PQLTask,PQLTask>();
		
		try { query = this.readFile(query); } catch (IOException e) {}
		
		StringReader sr = new StringReader(query);
		ANTLRInputStream input = null;
		try {
			input = new ANTLRInputStream(sr);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		PQLLexer lexer = new PQLLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		tokens.fill();
		 
		PQLParser parser = new PQLParser(null);
		parser.setBuildParseTree(true);
		parser.setTokenStream(tokens);
        parser.setTrace(false);
        
        parser.removeErrorListeners();
        this.listener = new PQLErrorListener();
        parser.addErrorListener(listener);
        
        this.parseTree = parser.query();
       
	}

	protected String readFile(String fileName) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
		
		StringBuffer stringBuffer = new StringBuffer();
		String line = null;
		
		while((line =bufferedReader.readLine())!=null)
			stringBuffer.append(line).append("\n");
		
		String result = stringBuffer.toString();
		bufferedReader.close();
		return result;
	}

	protected ThreeValuedLogicValue interpret() {
		if (this.getNumberOfParseErrors()>0) return ThreeValuedLogicValue.UNKNOWN;
		ParseTree predicate = null;
		
		ParseTree queryType = parseTree.getChild(0); //A.P. getting query type: select, insert, etc.
		
			for (int i = 0; i < queryType.getChildCount(); i++) { //A.P. parseTree replaced with queryType
			ParseTree child = queryType.getChild(i); //A.P. parseTree replaced with queryType
			
			if(child instanceof RuleNode) {
                int ruleIndex = ((RuleNode)child).getRuleContext().getRuleIndex();
                
                switch (ruleIndex) {
	                case PQLParser.RULE_variables:
	                	interpretVariables(child);
	                	break;
	                case PQLParser.RULE_attributes:
	                	interpretAttributes(child);
	                	break;
	                case PQLParser.RULE_locations:
	                	interpretLocations(child);
	                	break;
	                case PQLParser.RULE_predicate:
	                	predicate = child;
	                	break;
                }
            }
		} 
		
		return interpretPredicate(predicate);
	}
	
	protected void interpretVariables(ParseTree tree) {
		
		for (int i = 0; i < tree.getChildCount(); i++) {
			ParseTree child = tree.getChild(i);
			
			if(child instanceof RuleNode) {
                int ruleIndex = ((RuleNode)child).getRuleContext().getRuleIndex();
                
                if (ruleIndex == PQLParser.RULE_variable)
                	interpretVariable(child);
            }
        }
	}
	
	protected void interpretVariable(ParseTree tree) {
		String			varName  = null;
		Set<PQLTask>	varValue = null;
		
		for (int i = 0; i < tree.getChildCount(); i++) {
			ParseTree child = tree.getChild(i);
			
			if(child instanceof RuleNode) {
                int ruleIndex = ((RuleNode)child).getRuleContext().getRuleIndex();
                
                switch (ruleIndex) {
                	case PQLParser.RULE_varName:
                		varName = interpretVarName(child);
                		break;
                	case PQLParser.RULE_setOfTasks:
                		varValue = interpretSetOfTasks(child);
                		break;
                }
            }
        }
		
		this.variables.put(varName,varValue);
	}
	
	protected String interpretVarName(ParseTree tree) {
		return tree.getChild(0).getText();
	}
	
	protected void interpretAttributes(ParseTree tree) {
		for (int i = 0; i < tree.getChildCount(); i++) {
			ParseTree child = tree.getChild(i);
			
			if(child instanceof RuleNode) {
                int ruleIndex = ((RuleNode)child).getRuleContext().getRuleIndex();
                
                if (ruleIndex == PQLParser.RULE_attribute)
                	interpretAttribute(child);
            }
        }
	}
	
	protected void interpretAttribute(ParseTree tree) {
		ParseTree child = tree.getChild(0); 
		
		if (child instanceof RuleNode) {
            int ruleIndex = ((RuleNode)child).getRuleContext().getRuleIndex();
            
            switch (ruleIndex) {
	            case PQLParser.RULE_universe :
	            	this.attributes.add(PQLAttribute.UNIVERSE);
	            	break;
	            case PQLParser.RULE_attributeID :
	            	this.attributes.add(PQLAttribute.ID);
	            	break;
	            case PQLParser.RULE_attributeName :
	            	this.attributes.add(PQLAttribute.NAME);
	            	break;
	            case PQLParser.RULE_attributeModel :
	            	this.attributes.add(PQLAttribute.MODEL);
	            	break;
            }
        }
	}
	
	protected void interpretLocations(ParseTree tree) {
		for (int i = 0; i < tree.getChildCount(); i++) {
			ParseTree child = tree.getChild(i);
			
			if(child instanceof RuleNode) {
                int ruleIndex = ((RuleNode)child).getRuleContext().getRuleIndex();
                
                if (ruleIndex == PQLParser.RULE_location)
                	interpretLocation(child);
            }
        }
	}
	
	protected void interpretLocation(ParseTree tree) {	
		ParseTree child = tree.getChild(0); 
		
		if (child instanceof RuleNode) {
            int ruleIndex = ((RuleNode)child).getRuleContext().getRuleIndex();
            
            switch (ruleIndex) {
	            case PQLParser.RULE_universe :
	            	this.locations.add(new PQLLocation());
	            	break;
	            case PQLParser.RULE_locationDirectory :
	            	this.locations.add(new PQLLocation(this.interpretString(child), true));
	            	break;
	            case PQLParser.RULE_locationID :
	            	this.locations.add(new PQLLocation(child.getText(),false));
	            	break;
            }
        }
	}

	protected ThreeValuedLogicValue interpretPredicate(ParseTree tree) {
		if (tree==null) return ThreeValuedLogicValue.TRUE; // WHERE clause is not specified for this query!
		
		ThreeValuedLogicValue result = ThreeValuedLogicValue.FALSE;
		
		ParseTree child = tree.getChild(0); 
		int ruleIndex = ((RuleNode)child).getRuleContext().getRuleIndex();
	
		switch (ruleIndex) {
			case PQLParser.RULE_proposition:
				result = interpretProposition(child);
				break;
			case PQLParser.RULE_conjunction:
				result = interpretConjunction(child);
				break;
			case PQLParser.RULE_disjunction:
				result = interpretDisjunction(child);
				break;
			case PQLParser.RULE_logicalTest:
				result = interpretLogicalTest(child);
				break;
		}
		
		return result;
	}
	
	protected ThreeValuedLogicValue interpretProposition(ParseTree tree) {
		ThreeValuedLogicValue result = ThreeValuedLogicValue.FALSE;
		
		ParseTree child = tree.getChild(0);
		int ruleIndex = ((RuleNode)child).getRuleContext().getRuleIndex();
	
		switch (ruleIndex) {
			case PQLParser.RULE_unaryPredicate:
				result = interpretUnaryPredicate(child);
				break;
			case PQLParser.RULE_unaryTracePredicate: //A.P.
				result = interpretUnaryTracePredicate(child);
				break;
			case PQLParser.RULE_binaryPredicate:
				result = interpretBinaryPredicate(child);
				break;
			case PQLParser.RULE_unaryPredicateMacro:
				result = interpretUnaryPredicateMacro(child);
				break;
			case PQLParser.RULE_binaryPredicateMacro:
				result = interpretBinaryPredicateMacro(child);
				break;
			case PQLParser.RULE_setPredicate:
				result = interpretSetPredicate(child);
				break;
			case PQLParser.RULE_truthValue:
				result = interpretTruthValue(child);
				break;
			case PQLParser.RULE_parentheses:
				result = interpretParentheses(child);
				break;
			case PQLParser.RULE_negation:
				result = interpretNegation(child);
				break;
		}
		
		return result;
	}
	
	protected ThreeValuedLogicValue interpretUnaryPredicate(ParseTree tree) {
		ParseTree nameChild = tree.getChild(0).getChild(0);
		ParseTree taskChild = tree.getChild(2);
		
		Token token = ((TerminalNode)nameChild).getSymbol();
		
		return this.interpretUnaryPredicate(token, this.interpretTask(taskChild));
	}
	
	//A.P.
	protected ThreeValuedLogicValue interpretUnaryTracePredicate(ParseTree tree) {
		ParseTree nameChild = tree.getChild(0).getChild(0);
		ParseTree traceChild = tree.getChild(2);
		
		Token token = ((TerminalNode)nameChild).getSymbol();
		PQLTrace trace = this.interpretTrace(traceChild);
		return this.interpretUnaryTracePredicate(token, trace);
		
	}
	
	protected ThreeValuedLogicValue interpretBinaryPredicate(ParseTree tree) {
		ParseTree nameChild = tree.getChild(0).getChild(0);
		ParseTree taskChildA = tree.getChild(2);
		ParseTree taskChildB = tree.getChild(4);
		
		Token token = ((TerminalNode)nameChild).getSymbol();
		
		return this.interpretBinaryPredicate(token, this.interpretTask(taskChildA), this.interpretTask(taskChildB));
	}
	
	protected ThreeValuedLogicValue interpretUnaryPredicateMacro(ParseTree tree) {
		Token op		= ((TerminalNode)tree.getChild(0)).getSymbol();
		Set<PQLTask> tasks	= this.interpretSetOfTasks(tree.getChild(2));
		PQLQuantifier Q	= this.interpretAnyEachAll(tree.getChild(4));
		
		for (PQLTask task : tasks) {
			if (this.interpretUnaryPredicate(op, task)==ThreeValuedLogicValue.TRUE) {
				if (Q==PQLQuantifier.ANY)
					return ThreeValuedLogicValue.TRUE;
			}
			else {
				if (Q==PQLQuantifier.ALL)
					return ThreeValuedLogicValue.FALSE;
			}
		}

		if (Q==PQLQuantifier.ANY) return ThreeValuedLogicValue.FALSE;
		if (Q==PQLQuantifier.ALL) return ThreeValuedLogicValue.TRUE;
		
		return ThreeValuedLogicValue.UNKNOWN;
	}

	protected ThreeValuedLogicValue interpretBinaryPredicateMacro(ParseTree tree) {
		ParseTree child = tree.getChild(0);
		int ruleIndex = ((RuleNode)child).getRuleContext().getRuleIndex();
	
		switch (ruleIndex) {
			case PQLParser.RULE_binaryPredicateMacroTaskSet:
				return  interpretBinaryPredicateMacroTaskSet(child);
			case PQLParser.RULE_binaryPredicateMacroSetSet:
				return interpretBinaryPredicateMacroSetSet(child);
		}
		
		return ThreeValuedLogicValue.UNKNOWN;
	}
	
	protected ThreeValuedLogicValue interpretSetPredicate(ParseTree tree) {		
		ParseTree child = tree.getChild(0);
		int ruleIndex = ((RuleNode)child).getRuleContext().getRuleIndex();
	
		switch (ruleIndex) {
			case PQLParser.RULE_taskInSetOfTasks:
				return interpretTaskInSetOfTasks(child);
			case PQLParser.RULE_setComparison:
				return interpretSetComparison(child);
		}
		
		return ThreeValuedLogicValue.UNKNOWN;
	}

	protected ThreeValuedLogicValue interpretSetComparison(ParseTree tree) {		
		Set<PQLTask> A = interpretSetOfTasks(tree.getChild(0));
		Set<PQLTask> B = interpretSetOfTasks(tree.getChild(2));
		
		ParseTree child = tree.getChild(1).getChild(0);
		int ruleIndex = ((RuleNode)child).getRuleContext().getRuleIndex();
	
		switch (ruleIndex) {
			case PQLParser.RULE_identical:
				return A.equals(B) ? ThreeValuedLogicValue.TRUE : ThreeValuedLogicValue.FALSE;
			case PQLParser.RULE_different:
				return A.equals(B) ? ThreeValuedLogicValue.FALSE : ThreeValuedLogicValue.TRUE;
			case PQLParser.RULE_overlapsWith:
				for (PQLTask task : A) 
					if (B.contains(task)) return ThreeValuedLogicValue.TRUE;
				
				return ThreeValuedLogicValue.FALSE;
			case PQLParser.RULE_subsetOf:
				return B.containsAll(A) ? ThreeValuedLogicValue.TRUE : ThreeValuedLogicValue.FALSE;
			case PQLParser.RULE_properSubsetOf:
				return B.containsAll(A) && !A.equals(B) ? ThreeValuedLogicValue.TRUE : ThreeValuedLogicValue.FALSE;
		}
		
		return ThreeValuedLogicValue.UNKNOWN;
	}
	
	protected ThreeValuedLogicValue interpretNegation(ParseTree tree) {
		ThreeValuedLogicValue result = ThreeValuedLogicValue.FALSE;
		
		ParseTree child = tree.getChild(1);
		int ruleIndex = ((RuleNode)child).getRuleContext().getRuleIndex();
		
		if (ruleIndex==PQLParser.RULE_proposition) {
			result = interpretProposition(child);
		}

		return this.threeValuedLogic.NOT(result);
	}

	protected ThreeValuedLogicValue interpretTruthValue(ParseTree tree) {
		ParseTree child = tree.getChild(0);
		
		if (child instanceof TerminalNode) {
            Token token = ((TerminalNode)child).getSymbol();
            
            switch (token.getType()) {
	            case PQLLexer.TRUE :
	            	return ThreeValuedLogicValue.TRUE;
	            case PQLLexer.FALSE :
	            	return ThreeValuedLogicValue.FALSE;
	            case PQLLexer.UNKNOWN :
	            	return ThreeValuedLogicValue.UNKNOWN;
            }
        }
		
		return ThreeValuedLogicValue.UNKNOWN;
	}

	protected PQLTask interpretTask(ParseTree tree) {
		String label = "";
		double similarity = 1.0;
		
		for (int i = 0; i < tree.getChildCount(); i++) {
			ParseTree child = tree.getChild(i);
			
			if (child instanceof RuleNode) {
				int ruleIndex = ((RuleNode)child).getRuleContext().getRuleIndex();
                
                switch (ruleIndex) {
                	case PQLParser.RULE_approximate:
                		similarity = labelMngr.getDefaultLabelSimilarityThreshold();
                		break;
                	case PQLParser.RULE_label:
                		label = this.interpretLabel(child);
                		break;
                	case PQLParser.RULE_similarity:
                		similarity = this.interpretSimilarity(child);
                		break;
                }
            }
        }
		
		return new PQLTask(label, similarity);
	}
	
	//A.P.
	protected PQLTrace interpretTrace(ParseTree tree) {

		PQLTrace trace = new PQLTrace();
		
		for (int i = 0; i < tree.getChildCount(); i++) 
		{
			ParseTree child = tree.getChild(i).getChild(0); //getting a task or '*'
			
			if(child instanceof RuleNode) 
				{
					int ruleIndex = ((RuleNode)child).getRuleContext().getRuleIndex();
					
					if (ruleIndex == PQLParser.RULE_task) //is task
					{
						trace.addTask(this.interpretTask(child));
					}else // is '*'
					{
						trace.addTask(this.interpretTraceUniverse());
						trace.setHasAsterisk(true);
					} 
				}
		}
		
	if(trace.hasAsterisk())
	{trace.addStartEnd(this.hashCode());}
	
	return trace;
	
	}

	//A.P.
	protected PQLTask interpretTraceUniverse() {
		
		PQLTask task = new PQLTask("Universe"+this.hashCode(), 1.0);
		task.setAsterisk(true);
		return task;
	}


	protected String interpretLabel(ParseTree tree) {
		return interpretString(tree.getChild(0));
	}
	
	protected double interpretSimilarity(ParseTree tree) {
		return new Double(tree.getChild(0).getText()).doubleValue();
	}

	protected String interpretString(ParseTree tree) {
		String result = tree.getText();
		return result.substring(1, result.length()-1).trim();
	}
	
	protected int interpretInteger(ParseTree tree) {
		String result = tree.getText();
		return new Integer(result);
	}

	protected ThreeValuedLogicValue interpretParentheses(ParseTree tree) {
		ParseTree child = tree.getChild(1); 
		int ruleIndex = ((RuleNode)child).getRuleContext().getRuleIndex();
	
		switch (ruleIndex) {
			case PQLParser.RULE_proposition:
				return interpretProposition(child);
			case PQLParser.RULE_conjunction:
				return interpretConjunction(child);
			case PQLParser.RULE_disjunction:
				return interpretDisjunction(child);
			case PQLParser.RULE_logicalTest:
				return interpretLogicalTest(child);
		}
		
		return ThreeValuedLogicValue.UNKNOWN;
	}

	protected ThreeValuedLogicValue interpretLogicalTest(ParseTree tree) {
		ParseTree child = tree.getChild(0); 
		int ruleIndex = ((RuleNode)child).getRuleContext().getRuleIndex();
	
		switch (ruleIndex) {
			case PQLParser.RULE_isTrue:
				return interpretLogicalTest(child,true,ThreeValuedLogicValue.TRUE);
			case PQLParser.RULE_isNotTrue:
				return interpretLogicalTest(child,false,ThreeValuedLogicValue.TRUE);
			case PQLParser.RULE_isFalse:
				return interpretLogicalTest(child,true,ThreeValuedLogicValue.FALSE);
			case PQLParser.RULE_isNotFalse:
				return interpretLogicalTest(child,false,ThreeValuedLogicValue.FALSE);
			case PQLParser.RULE_isUnknown:
				return interpretLogicalTest(child,true,ThreeValuedLogicValue.UNKNOWN);
			case PQLParser.RULE_isNotUnknown:
				return interpretLogicalTest(child,false,ThreeValuedLogicValue.UNKNOWN);
		}
		
		return ThreeValuedLogicValue.UNKNOWN;
	}

	protected ThreeValuedLogicValue interpretLogicalTest(ParseTree tree, boolean test, ThreeValuedLogicValue value) {
		ParseTree child = tree.getChild(0);
		ThreeValuedLogicValue pValue = this.interpretProposition(child);
		
		if (test && value.equals(ThreeValuedLogicValue.TRUE)) {
			if (pValue.equals(ThreeValuedLogicValue.TRUE))
				return ThreeValuedLogicValue.TRUE;
			else
				return ThreeValuedLogicValue.FALSE;
		}
		else if (!test && value.equals(ThreeValuedLogicValue.TRUE)) {
			if (!pValue.equals(ThreeValuedLogicValue.TRUE))
				return ThreeValuedLogicValue.TRUE;
			else
				return ThreeValuedLogicValue.FALSE;
		}
		else if (test && value.equals(ThreeValuedLogicValue.FALSE)) {
			if (pValue.equals(ThreeValuedLogicValue.FALSE))
				return ThreeValuedLogicValue.TRUE;
			else
				return ThreeValuedLogicValue.FALSE;
		}
		else if (!test && value.equals(ThreeValuedLogicValue.FALSE)) {
			if (!pValue.equals(ThreeValuedLogicValue.FALSE))
				return ThreeValuedLogicValue.TRUE;
			else
				return ThreeValuedLogicValue.FALSE;
		}
		else if (test && value.equals(ThreeValuedLogicValue.UNKNOWN)) {
			if (pValue.equals(ThreeValuedLogicValue.UNKNOWN))
				return ThreeValuedLogicValue.TRUE;
			else
				return ThreeValuedLogicValue.FALSE;
		}
		else if (!test && value.equals(ThreeValuedLogicValue.UNKNOWN)) {
			if (!pValue.equals(ThreeValuedLogicValue.UNKNOWN))
				return ThreeValuedLogicValue.TRUE;
			else
				return ThreeValuedLogicValue.FALSE;
		}
		
		return ThreeValuedLogicValue.UNKNOWN;
	}

	protected Set<PQLTask> interpretSetOfTasks(ParseTree tree) {		
		ParseTree child = tree.getChild(0); 
		int ruleIndex = ((RuleNode)child).getRuleContext().getRuleIndex();
	
		switch (ruleIndex) {
			case PQLParser.RULE_tasks:
				return interpretTasks(child);
			case PQLParser.RULE_union:
				return interpretUnion(child);
			case PQLParser.RULE_difference:
				return interpretDifference(child);
			case PQLParser.RULE_intersection:
				return interpretIntersection(child);
		}
		
		return new HashSet<PQLTask>();
	}
	
	protected Set<PQLTask> interpretTasks(ParseTree tree) {
		ParseTree child = tree.getChild(0);
		
		if (child instanceof RuleNode) {
			int ruleIndex = ((RuleNode)child).getRuleContext().getRuleIndex();
			
			switch (ruleIndex) {
				case PQLParser.RULE_universe:
					return this.interpretUniverse(child);
				case PQLParser.RULE_varName:
					return this.interpretVarNameSetOfTasks(child);
				case PQLParser.RULE_setOfTasksLiteral:
					return interpretSetOfTasksLiteral(child);
				case PQLParser.RULE_setOfTasksConstruction:
					return interpretSetOfTasksConstruction(child);
				case PQLParser.RULE_setOfTasksParentheses:
					return interpretSetOfTasksParentheses(child);
			}	
		}
		
		return new HashSet<PQLTask>();
	}

	protected Set<PQLTask> interpretSetOfTasksConstruction(ParseTree tree) {
		ParseTree child = tree.getChild(0);
		
		if (child instanceof RuleNode) {
			int ruleIndex = ((RuleNode)child).getRuleContext().getRuleIndex();
			
			switch (ruleIndex) {
				case PQLParser.RULE_unaryPredicateConstruction:
					return interpretUnaryPredicateConstruction(child);
				case PQLParser.RULE_binaryPredicateConstruction:
					return interpretBinaryPredicateConstruction(child);
			}	
		}
		
		return new HashSet<PQLTask>();
	}

	protected Set<PQLTask> interpretUnaryPredicateConstruction(ParseTree tree) {
		Set<PQLTask> result = new HashSet<PQLTask>(); 
		
		Token op = ((TerminalNode)tree.getChild(1).getChild(0)).getSymbol();
		Set<PQLTask> tasks = this.interpretSetOfTasks(tree.getChild(3));
		
		for (PQLTask task : tasks) {
			if (this.interpretUnaryPredicate(op,task)==ThreeValuedLogicValue.TRUE)
				result.add(task);
		}
		
		return result;
	}

	protected Set<PQLTask> interpretSetOfTasksLiteral(ParseTree tree) {
		Set<PQLTask> result = new HashSet<PQLTask>();
		
		for (int i = 0; i < tree.getChildCount(); i++) {
			ParseTree child = tree.getChild(i);
			
			if(child instanceof RuleNode) {
				int ruleIndex = ((RuleNode)child).getRuleContext().getRuleIndex();
				
				if (ruleIndex == PQLParser.RULE_task) {
					result.add(this.interpretTask(child));
				}
			}
		}
		
		return result;
	}

	protected Set<PQLTask> interpretUniverse(ParseTree child) {
		return this.getAllTasks();
	}

	protected Set<PQLTask> interpretUnion(ParseTree tree) {
		Set<PQLTask> result = new HashSet<PQLTask>();
		
		for (int i = 0; i < tree.getChildCount(); i++) {
			ParseTree child = tree.getChild(i);
			
			if(child instanceof RuleNode) {
                int ruleIndex = ((RuleNode)child).getRuleContext().getRuleIndex();
                
                switch (ruleIndex) {
	                case PQLParser.RULE_tasks:
	        			result.addAll(interpretTasks(child));
	        			break;
	                case PQLParser.RULE_difference:
	        			result.addAll(interpretDifference(child));
	        			break;
	                case PQLParser.RULE_intersection:
	        			result.addAll(interpretIntersection(child));
	        			break; 
                }
            }
        }
		
		return result;
	}
	
	protected Set<PQLTask> interpretIntersection(ParseTree tree) {
		Set<PQLTask> result = null;
		
		for (int i = 0; i < tree.getChildCount(); i++) {
			ParseTree child = tree.getChild(i);
			
			if(child instanceof RuleNode) {
                int ruleIndex = ((RuleNode)child).getRuleContext().getRuleIndex();
                
                switch (ruleIndex) {
	                case PQLParser.RULE_tasks:
	                	if (result==null) result = this.interpretTasks(child);
	                	else result.retainAll(this.interpretTasks(child));
	        			break;
	                case PQLParser.RULE_difference:
	                	if (result==null) result = this.interpretDifference(child);
	                	else result.retainAll(this.interpretDifference(child));
	        			break;
                }
            }
        }
		
		return result;
	}
	
	protected Set<PQLTask> interpretSetOfTasksParentheses(ParseTree tree) {	
		ParseTree child = tree.getChild(1);
		
		if (child instanceof RuleNode) {
			int ruleIndex = ((RuleNode)child).getRuleContext().getRuleIndex();
			
			switch (ruleIndex) {
				case PQLParser.RULE_setOfTasksLiteral:
					return interpretSetOfTasksLiteral(child);
				case PQLParser.RULE_setOfTasksConstruction:
					return interpretSetOfTasksConstruction(child);
				case PQLParser.RULE_union:
					return interpretUnion(child);
				case PQLParser.RULE_difference:
					return interpretDifference(child);
				case PQLParser.RULE_intersection:
					return interpretIntersection(child);
				case PQLParser.RULE_setOfTasksParentheses:
					return interpretSetOfTasksParentheses(child);
			}
		}
		else if (child instanceof TerminalNode) {
			int tokenIndex =  ((TerminalNode)tree).getSymbol().getTokenIndex();
			
			switch (tokenIndex) {
				case PQLLexer.VARIABLE_NAME:
					return this.interpretVarNameSetOfTasks(child);
				case PQLLexer.UNIVERSE:
					return this.interpretUniverse(child);
			}
		}
		
		return new HashSet<PQLTask>();
	}
	
	protected Set<PQLTask> interpretDifference(ParseTree tree) {
		ParseTree child = tree.getChild(2);
		int ruleIndex = ((RuleNode)child).getRuleContext().getRuleIndex();
		
		Set<PQLTask> set1 = this.interpretTasks(tree.getChild(0));
		Set<PQLTask> set2 = new HashSet<PQLTask>();

		switch (ruleIndex) {
	        case PQLParser.RULE_tasks:
	        	set2 = interpretTasks(child);
				break;
	        case PQLParser.RULE_difference:
	        	set2 = interpretDifference(child);
				break;
        }
		
		set1.removeAll(set2);
		
		return set1;
	}
	
	protected Set<PQLTask> interpretBinaryPredicateConstruction(ParseTree tree) {
		Set<PQLTask> result = new HashSet<PQLTask>();

		Token op = ((TerminalNode)tree.getChild(1).getChild(0)).getSymbol();
		Set<PQLTask> set1	= this.interpretSetOfTasks(tree.getChild(3));
		Set<PQLTask> set2	= this.interpretSetOfTasks(tree.getChild(5));
		PQLQuantifier Q	= this.interpretAnyEachAll(tree.getChild(7));

		for (PQLTask task2 : set2) {
			boolean flag = true;
			for (PQLTask task1 : set1) {
				ThreeValuedLogicValue value = this.interpretBinaryPredicate(op, task1, task2);
				if (value==ThreeValuedLogicValue.TRUE && Q==PQLQuantifier.ANY) {
					result.add(task2);
					break;
				}
				else if (value!=ThreeValuedLogicValue.TRUE && Q==PQLQuantifier.ALL) {
					flag = false;
					break;
				}
			}
			
			if (flag && Q==PQLQuantifier.ALL)
				result.add(task2);
		}
		
		return result;
	}
	
	protected Set<PQLTask> interpretVarNameSetOfTasks(ParseTree tree) {
		String var = interpretVarName(tree);
		
		if (!this.variables.containsKey(var))
			throw new RuntimeException("ERROR: Variable '" + var + "' is not defined!");
		
		return this.variables.get(var);
	}
	
	protected ThreeValuedLogicValue interpretBinaryPredicateMacroSetSet(ParseTree tree) {
		ParseTree nameChild = tree.getChild(0).getChild(0);
		Token op = ((TerminalNode)nameChild).getSymbol();
		Set<PQLTask> set1	= this.interpretSetOfTasks(tree.getChild(2));
		Set<PQLTask> set2	= this.interpretSetOfTasks(tree.getChild(4));
		PQLQuantifier Q	= this.interpretAnyEachAll(tree.getChild(6));
		
		return interpretBinaryPredicateMacroSetSet(op,set1,set2,Q);
	}
	
	protected ThreeValuedLogicValue interpretBinaryPredicateMacroSetSet(Token op, Set<PQLTask> set1, Set<PQLTask> set2, PQLQuantifier Q) {
		for (PQLTask task1 : set1) {
			boolean flag = false;
			
			for (PQLTask task2 : set2) {
				if (this.interpretBinaryPredicate(op,task1,task2)==ThreeValuedLogicValue.TRUE) {
					if (Q==PQLQuantifier.ANY) return ThreeValuedLogicValue.TRUE;
					else if (Q==PQLQuantifier.EACH) {
						flag = true;
						break;
					}
				}
				
				if (this.interpretBinaryPredicate(op,task1,task2)!=ThreeValuedLogicValue.TRUE) {
					if (Q==PQLQuantifier.ALL) return ThreeValuedLogicValue.FALSE;
				}
			}
			
			if (Q==PQLQuantifier.EACH && !flag)
				return ThreeValuedLogicValue.FALSE;
		}
		
		if (Q==PQLQuantifier.ANY) return ThreeValuedLogicValue.FALSE;
		if (Q==PQLQuantifier.EACH) return ThreeValuedLogicValue.TRUE;
		if (Q==PQLQuantifier.ALL) return ThreeValuedLogicValue.TRUE;
		
		return ThreeValuedLogicValue.UNKNOWN;
	}

	protected ThreeValuedLogicValue interpretBinaryPredicateMacroTaskSet(ParseTree tree) {
		ParseTree nameChild = tree.getChild(0).getChild(0);
		Token op = ((TerminalNode)nameChild).getSymbol();
		
		PQLTask task		= this.interpretTask(tree.getChild(2));
		Set<PQLTask> set1	= new HashSet<PQLTask>(); set1.add(task);
		Set<PQLTask> set2	= this.interpretSetOfTasks(tree.getChild(4));
		
		PQLQuantifier Q	= this.interpretAnyEachAll(tree.getChild(6));
		
		return interpretBinaryPredicateMacroSetSet(op,set1,set2,Q);
	}

	protected PQLQuantifier interpretAnyEachAll(ParseTree tree) {
		
		Token token = ((TerminalNode)tree.getChild(0)).getSymbol();
		
		switch(token.getType()) {
			case PQLLexer.ANY:
				return PQLQuantifier.ANY;
			case PQLLexer.EACH:
				return PQLQuantifier.EACH;
			case PQLLexer.ALL:
				return PQLQuantifier.ALL;
		}
		
		return null;
	}

	protected ThreeValuedLogicValue interpretTaskInSetOfTasks(ParseTree tree) {
		ParseTree taskTree = tree.getChild(0);
		ParseTree setTree = tree.getChild(2);
		
		PQLTask task = interpretTask(taskTree);
		Set<PQLTask> set = interpretSetOfTasks(setTree);
		
		return set.contains(task) ? ThreeValuedLogicValue.TRUE : ThreeValuedLogicValue.FALSE;
	}

	protected ThreeValuedLogicValue interpretConjunction(ParseTree tree) {
		ThreeValuedLogicValue result = ThreeValuedLogicValue.TRUE;
		
		for (int i = 0; i < tree.getChildCount(); i++) {
			ParseTree child = tree.getChild(i);
			
			if(child instanceof RuleNode) {
                int ruleIndex = ((RuleNode)child).getRuleContext().getRuleIndex();
                
                switch (ruleIndex) {
	                case PQLParser.RULE_proposition:
	        			result = this.threeValuedLogic.AND(result,interpretProposition(child));
	        			break;
	        		case PQLParser.RULE_logicalTest:
	        			result = this.threeValuedLogic.AND(result,interpretLogicalTest(child));
	        			break;
	        		case PQLParser.RULE_disjunction:
	        			result = this.threeValuedLogic.AND(result,interpretDisjunction(child));
	        			break;
                }
                
                if (result==ThreeValuedLogicValue.FALSE) return result;
            }
        }
		
		return result;
	}
	
	protected ThreeValuedLogicValue interpretDisjunction(ParseTree tree) {
		ThreeValuedLogicValue result = ThreeValuedLogicValue.FALSE;
		
		for (int i = 0; i < tree.getChildCount(); i++) {
			ParseTree child = tree.getChild(i);
			
			if(child instanceof RuleNode) {
                int ruleIndex = ((RuleNode)child).getRuleContext().getRuleIndex();
                
                switch (ruleIndex) {
	                case PQLParser.RULE_proposition:
	        			result = this.threeValuedLogic.OR(result,interpretProposition(child));
	        			break;
	        		case PQLParser.RULE_logicalTest:
	        			result = this.threeValuedLogic.OR(result,interpretLogicalTest(child));
	        			break;
	        	}
                
                if (result==ThreeValuedLogicValue.TRUE) return result;
            }
        }
		
		return result;
	}

	public String toStringTree(ParserRuleContext tree, PQLParser parser) {
		String ruleNames[] = parser == null ? null : parser.getRuleNames();
		List<String> ruleNamesList = ruleNames == null ? null : Arrays.asList(ruleNames);
		return toStringTree(tree, ruleNamesList);
	}

	private String toStringTree(Tree tree, List<String> ruleNames) {
        String s = Utils.escapeWhitespace(getNodeText(tree, ruleNames), false);
        if(tree.getChildCount() == 0) return s;
        
        StringBuilder buf = new StringBuilder();
        buf.append("(");
        s = Utils.escapeWhitespace(getNodeText(tree, ruleNames), false);
        buf.append(s);
        buf.append(' ');
        for(int i = 0; i < tree.getChildCount(); i++)
        {
            if(i > 0)
                buf.append(' ');
            buf.append(toStringTree(tree.getChild(i), ruleNames));
        }

        buf.append(")");
        return buf.toString();
    }
	
	protected String getNodeText(Tree tree, List<String> ruleNames) {
    	if (ruleNames != null) {
            if(tree instanceof RuleNode) {
                int ruleIndex = ((RuleNode)tree).getRuleContext().getRuleIndex();
                String ruleName = (String)ruleNames.get(ruleIndex);
                return ruleName;
            }
            
            if(tree instanceof ErrorNode)
                return tree.toString();
            
            if(tree instanceof TerminalNode) {
                Token symbol = ((TerminalNode)tree).getSymbol();
                if(symbol != null) {
                    String s = symbol.getText();
                    return s;
                }
            }
        }
    	
    	Object payload = tree.getPayload();
    	
    	if(payload instanceof Token)
            return ((Token)payload).getText();
        else
            return tree.getPayload().toString();
    }
	
	@Override
	public HashMap<String, Set<PQLTask>> getVariables() {
		return this.variables;
	}
	
	@Override
	public Set<PQLAttribute> getAttributes() {
		return this.attributes;
	}
	
	@Override
	public Set<PQLLocation> getLocations() {
		return this.locations;
	}
	
	@Override
	public Map<PQLTask, PQLTask> getTaskMap() {
		return this.task2task;
	}
	
	@Override
	public int getNumberOfParseErrors() {
		return listener.getNumberOfErrors();
	}

	@Override
	public List<String> getParseErrorMessages() {
		return listener.getErrorMessages();
	}
}
