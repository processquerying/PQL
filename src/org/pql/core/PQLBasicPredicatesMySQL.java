package org.pql.core;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.deckfour.xes.model.XLog;
import org.jbpt.persist.MySQLConnection;
import org.jbpt.petri.IFlow;
import org.jbpt.petri.IMarking;
import org.jbpt.petri.INetSystem;
import org.jbpt.petri.INode;
import org.jbpt.petri.IPlace;
import org.jbpt.petri.ITransition;
import org.jbpt.petri.persist.AbstractPetriNetPersistenceLayerMySQL;
import org.jbpt.petri.persist.IPetriNetPersistenceLayer;
import org.pql.alignment.AbstractReplayer;
import org.pql.alignment.PQLAlignment;
import org.pql.alignment.Replayer;
import org.pql.alignment.AlignmentAPI;
import org.pql.label.ILabelManager;
import org.pql.label.LabelManagerLevenshtein;
import org.pql.logic.IThreeValuedLogic;
import org.pql.logic.ThreeValuedLogicValue;
import org.pql.petri.AbstractLabelUnificationTransformation;
import org.pql.petri.AbstractNetSystemTransformationManager;
import org.pql.petri.AbstractTraceExecutionWithWildcardCharactersTesterTransformation;
import org.pql.petri.ILabelUnificationTransformation;
import org.pql.petri.TransformationLog;
import org.processmining.models.graphbased.directed.petrinet.PetrinetGraph;
import org.processmining.models.graphbased.directed.petrinet.impl.PetrinetFactory;

/**
 * @author Artem Polyvyanyy
 */
public class PQLBasicPredicatesMySQL<F extends IFlow<N>, N extends INode, P extends IPlace, T extends ITransition, M extends IMarking<F,N,P,T>> //A.P. <...>added 
				extends MySQLConnection
				implements IPQLBasicPredicatesOnTasks
				{
	
	protected String PETRI_NET_IDENTIFIER_TO_ID = "{? = CALL pql.jbpt_petri_nets_get_internal_id(?)}";
	protected String PQL_CAN_OCCUR		= "{? = CALL pql.pql_can_occur(?,?)}";
	protected String PQL_ALWAYS_OCCURS	= "{? = CALL pql.pql_always_occurs(?,?)}";
	protected String PQL_CAN_CONFLICT	= "{? = CALL pql.pql_can_conflict(?,?,?)}";
	protected String PQL_CAN_COOCCUR	= "{? = CALL pql.pql_can_cooccur(?,?,?)}";
	protected String PQL_TOTAL_CAUSAL	= "{? = CALL pql.pql_total_causal(?,?,?)}";
	protected String PQL_TOTAL_CONCUR	= "{? = CALL pql.pql_total_concur(?,?,?)}";
		
	private IThreeValuedLogic			logic = null;
	private String						identifier = null;	
	private int							netID = 0;
	
	private IPetriNetPersistenceLayer<F,N,P,T,M> 				PL = new AbstractPetriNetPersistenceLayerMySQL<F,N,P,T,M>(this.mysqlURL,this.mysqlUser,this.mysqlPassword);//A.P.
	private ILabelManager 										LM = new LabelManagerLevenshtein(this.mysqlURL,this.mysqlUser,this.mysqlPassword, 1.0, new HashSet<Double>());//A.P.
	private AbstractNetSystemTransformationManager<F,N,P,T,M> 	TM = null;//A.P.
		
	public PQLBasicPredicatesMySQL(String mysqlURL, String mysqlUser, String mysqlPassword, IThreeValuedLogic logic) throws ClassNotFoundException, SQLException {
		super(mysqlURL,mysqlUser,mysqlPassword);

		this.logic = logic;
	}
	
	private ThreeValuedLogicValue checkUnaryPredicate(String call, PQLTask task) {
		try {
			
			CallableStatement cs = connection.prepareCall(call);
		
			cs.registerOutParameter(1, java.sql.Types.TINYINT);
			cs.setInt(2,this.netID);
			cs.setInt(3,task.getID());
						
			cs.execute();
			
			ThreeValuedLogicValue result = null;
			
			if (cs.getInt(1)==0)
				result = ThreeValuedLogicValue.FALSE;
			else if (cs.getInt(1)==1) 
				result = ThreeValuedLogicValue.TRUE;
			else
				return ThreeValuedLogicValue.UNKNOWN;
			
			cs.close();
			
			return result;
		}
		catch (SQLException e) {
			return ThreeValuedLogicValue.UNKNOWN;
		}
	}
	
	private ThreeValuedLogicValue checkBinaryPredicate(String call, PQLTask taskA, PQLTask taskB) {
		try {
			CallableStatement cs = connection.prepareCall(call);
		
			cs.registerOutParameter(1, java.sql.Types.TINYINT);
			cs.setInt(2,this.netID);
			cs.setInt(3,taskA.getID());
			cs.setInt(4,taskB.getID());
			
			cs.execute();
			
			ThreeValuedLogicValue result = null;
			
			if (cs.getInt(1)==0)
				result = ThreeValuedLogicValue.FALSE;
			else if (cs.getInt(1)==1) 
				result = ThreeValuedLogicValue.TRUE;
			else
				return ThreeValuedLogicValue.UNKNOWN;
			
			cs.close();
			
			return result;
		}
		catch (SQLException e) {
			return ThreeValuedLogicValue.UNKNOWN;
		}
	}
	
	@Override
	public void configure(Object obj) {
		this.identifier = (String) obj;
		
		try {
			CallableStatement cs = connection.prepareCall(this.PETRI_NET_IDENTIFIER_TO_ID);
			
			cs.registerOutParameter(1, java.sql.Types.INTEGER);
			cs.setString(2, identifier);
			
			cs.execute();
			
			this.netID = cs.getInt(1);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ThreeValuedLogicValue canOccur(PQLTask task) {
		return this.checkUnaryPredicate(this.PQL_CAN_OCCUR, task);
	}
	
	@Override
	public ThreeValuedLogicValue alwaysOccurs(PQLTask task) {
		return this.checkUnaryPredicate(this.PQL_ALWAYS_OCCURS, task);
	}

	@Override
	public ThreeValuedLogicValue canConflict(PQLTask taskA, PQLTask taskB) {
		return this.checkBinaryPredicate(this.PQL_CAN_CONFLICT, taskA, taskB);
	}

	@Override
	public ThreeValuedLogicValue canCooccur(PQLTask taskA, PQLTask taskB) {
		return this.checkBinaryPredicate(this.PQL_CAN_COOCCUR, taskA, taskB);
	}

	/**
	 * See Def. 4.2. in Artem Polyvyanyy, Matthias Weidlich, Raffaele Conforti, Marcello La Rosa, Arthur H. M. ter Hofstede: The 4C Spectrum of Fundamental Behavioral Relations for Concurrent Systems. Petri Nets 2014:210-232
	 */
	@Override
	public ThreeValuedLogicValue conflict(PQLTask taskA, PQLTask taskB) {
		return logic.AND(logic.AND(this.canConflict(taskA, taskB), this.canConflict(taskB, taskA)), logic.NOT(this.canCooccur(taskA,taskB)));
	}

	/**
	 * See Def. 4.2. in Artem Polyvyanyy, Matthias Weidlich, Raffaele Conforti, Marcello La Rosa, Arthur H. M. ter Hofstede: The 4C Spectrum of Fundamental Behavioral Relations for Concurrent Systems. Petri Nets 2014:210-232
	 */
	@Override
	public ThreeValuedLogicValue cooccur(PQLTask taskA, PQLTask taskB) {
		return logic.AND(logic.AND(logic.NOT(this.canConflict(taskA, taskB)),logic.NOT(this.canConflict(taskB, taskA))), this.canCooccur(taskA,taskB));
	}

	@Override
	public ThreeValuedLogicValue totalCausal(PQLTask taskA, PQLTask taskB) {
		return this.checkBinaryPredicate(this.PQL_TOTAL_CAUSAL, taskA, taskB);
	}

	@Override
	public ThreeValuedLogicValue totalConcur(PQLTask taskA, PQLTask taskB) {
		return this.checkBinaryPredicate(this.PQL_TOTAL_CONCUR, taskA, taskB);
	}
	
	//A.P.
	@Override
	public ThreeValuedLogicValue executes(PQLTrace trace) {
		ThreeValuedLogicValue 		result 				= null;
		XLog 						log					= trace.getTraceLog(); 
		PetrinetGraph				net 				= PetrinetFactory.newPetrinet("PNML");
		
		  		try {
	  			
		  	//check if net contains trace labels 		
		  	String eNetID = PL.getExternalID(this.netID);
		  	Set<String> netLabels = LM.getAllLabels(eNetID);
		  	
		  	if(trace.hasAsterisk())
		  	{
			  	for(int i=1; i<trace.getTrace().size()-1; i++)
			  	{
			  		
			  		PQLTask nextTask = trace.getTrace().elementAt(i);
			  		
			  		if(!nextTask.isAsterisk())
			  		{
				  		boolean netHasLabel = false;
				  		for(String t: nextTask.getSimilarLabels())
				  		{
				  			if (netLabels.contains(t)) netHasLabel = true;
				  		}
				  		if (!netHasLabel) 
				  		return ThreeValuedLogicValue.FALSE;
				  	}
			  	}
		  	}
		  	else
		  	{
		  		for(int i=0; i<trace.getTrace().size(); i++)
			  	{
			  			PQLTask nextTask = trace.getTrace().elementAt(i);
			  			
				  		boolean netHasLabel = false;
				  		for(String t: nextTask.getSimilarLabels())
				  		{
				  			if (netLabels.contains(t)) netHasLabel = true;
				  		}
				  		if (!netHasLabel) 
				  		return ThreeValuedLogicValue.FALSE;
				  	
			  	}
		  		
		  	}	
		  	
			INetSystem<F,N,P,T,M> netSystem = PL.restoreNetSystem(this.netID);
			netSystem.loadNaturalMarking();
	  				  	
	  		//System.out.println("net: " + this.netID + " - " + eNetID);
	  		//trace.print();
	  		//IOUtils.invokeDOT("./pics", eNetID+"-0-Original.png", netSystem.toDOT());
	  		
			AbstractTraceExecutionWithWildcardCharactersTesterTransformation<F,N,P,T,M> wct = new AbstractTraceExecutionWithWildcardCharactersTesterTransformation<F,N,P,T,M>(netSystem); 
			AlignmentAPI<F,N,P,T,M> api = new AlignmentAPI<F,N,P,T,M>(this.mysqlURL, this.mysqlUser, this.mysqlPassword, netSystem);
			AbstractReplayer replayer = new Replayer<F,N,P,T,M>(api);
	  		
	  		//add Start and End transitions if trace has *
	  		if(trace.hasAsterisk())
			{
	  			wct.addStartEnd(trace);
	 		}
	   		
	   		//get set of tasks for label unification
	  		Set<PQLTask> tasks = new HashSet<PQLTask>();
	  		for(int i=0; i<trace.getTrace().size(); i++)
	  		{
	  			PQLTask task = trace.getTrace().elementAt(i);
	  			if(!task.isAsterisk())
		     	{tasks.add(task);}
		    }
	  		
	  		//map for unified transitions
	  		Map<String, ITransition> transitionMap = new HashMap<String, ITransition>();
	 		
	  		//label unification
	  		this.TM = new AbstractNetSystemTransformationManager<F,N,P,T,M>(netSystem);
	  		TransformationLog<F,N,P,T,M> trlog = new TransformationLog<F,N,P,T,M>();
	  		
	  		Iterator<PQLTask> it = tasks.iterator();
	  		while (it.hasNext()) 
	  		{
	  		    			
		    		PQLTask task = it.next();
		    		
		    		ILabelUnificationTransformation<F,N,P,T,M> lut = new AbstractLabelUnificationTransformation<F,N,P,T,M>(netSystem,task.getSimilarLabels());
		    		trlog.add(lut);
		    		this.TM.transform(trlog);
		    		
		    		if (lut.getUnifiedTransition()==null) return ThreeValuedLogicValue.UNKNOWN;
		    		lut.getUnifiedTransition().setLabel(task.getLabel());
		    		
		    		transitionMap.put(task.getLabel(), lut.getUnifiedTransition());
		 	}
	  		
	  		//System.out.println("transitionMap: "+transitionMap);
	   		//IOUtils.invokeDOT("./pics", eid+"-2-AfterLU.png", netSystem.toDOT());
	   			
			
				if(!trace.hasAsterisk())
				{
									
					// convert Net System to PetrinetGraph
					net = api.constructPetrinetGraph(net);
					
					// get an optimal alignment
					PQLAlignment alignment = replayer.getAlignment(net, log);
					//alignment.print();
								
					// get alignment cost
					int alignmentCost = alignment.getAlignmentCost();
								
					if(alignmentCost == 0)
					{result = ThreeValuedLogicValue.TRUE;}else{result = ThreeValuedLogicValue.FALSE;}
				}else
				{
					
		    		//transform the system
					wct.applyWildcardTransformation(trace,transitionMap);
			 		//IOUtils.invokeDOT("./pics", eNetID+"-3-AfterST.png", netSystem.toDOT());
		    		
		    		//create PNML - for tests
		    		//PNMLSerializer PNML = new PNMLSerializer();
					//String pnmlNS = PNML.serializePetriNet((NetSystem) netSystem);
					//System.out.println(pnmlNS);
						
		    		// convert Net System to PetrinetGraph
					net = api.constructPetrinetGraph(net);
					
			 		// get an optimal alignment
					PQLAlignment alignment = replayer.getAlignmentWithAsterisk(net, log);
					
					// get alignment cost
					int alignmentCost = alignment.getAlignmentCostForAsterisk();
								
					if(alignmentCost == 0)
					{result = ThreeValuedLogicValue.TRUE;}else{result = ThreeValuedLogicValue.FALSE;}
		
				}	
				
				}catch(Exception e){return ThreeValuedLogicValue.UNKNOWN;}
							
		 
		return result;
	}

}
