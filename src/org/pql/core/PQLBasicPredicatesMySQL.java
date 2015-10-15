package org.pql.core;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.deckfour.xes.model.XLog;
import org.jbpt.petri.IFlow;
import org.jbpt.petri.IMarking;
import org.jbpt.petri.INetSystem;
import org.jbpt.petri.INode;
import org.jbpt.petri.IPlace;
import org.jbpt.petri.ITransition;
import org.jbpt.petri.persist.AbstractPetriNetPersistenceLayerMySQL;
import org.jbpt.petri.persist.IPetriNetPersistenceLayer;
import org.pql.alignment.AbstractReplayer;
import org.pql.alignment.AlignmentAPI;
import org.pql.alignment.PQLAlignment;
import org.pql.alignment.Replayer;
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
				implements IPQLBasicPredicatesOnTasks
				{
	
	protected String PETRI_NET_IDENTIFIER_TO_ID = "{? = CALL pql.jbpt_petri_nets_get_internal_id(?)}";
	protected String PQL_CAN_OCCUR				= "{? = CALL pql.pql_can_occur(?,?)}";
	protected String PQL_ALWAYS_OCCURS			= "{? = CALL pql.pql_always_occurs(?,?)}";
	protected String PQL_CAN_CONFLICT			= "{? = CALL pql.pql_can_conflict(?,?,?)}";
	protected String PQL_CAN_COOCCUR			= "{? = CALL pql.pql_can_cooccur(?,?,?)}";
	protected String PQL_TOTAL_CAUSAL			= "{? = CALL pql.pql_total_causal(?,?,?)}";
	protected String PQL_TOTAL_CONCUR			= "{? = CALL pql.pql_total_concur(?,?,?)}";
	
	//A.P.
	protected CallableStatement PETRI_NET_IDENTIFIER_TO_ID_CS = null;
	
	//A.P. TODO check if CallableStatement exists
	protected CallableStatement PQL_CAN_OCCUR_CS		= null;
	protected CallableStatement PQL_ALWAYS_OCCURS_CS	= null;
	protected CallableStatement PQL_CAN_CONFLICT_CS		= null;
	protected CallableStatement PQL_CAN_COOCCUR_CS		= null;
	protected CallableStatement PQL_TOTAL_CAUSAL_CS		= null;
	protected CallableStatement PQL_TOTAL_CONCUR_CS		= null;

		
	private String						identifier = null;	
	private int							netID = 0;
	private Connection 					connection = null;
	private IPetriNetPersistenceLayer<F,N,P,T,M> 				PL = null;
	private AbstractNetSystemTransformationManager<F,N,P,T,M> 	TM = null;//A.P.
		
	public PQLBasicPredicatesMySQL(Connection con) throws ClassNotFoundException, SQLException {
		this.connection = con;
		this.PL = new AbstractPetriNetPersistenceLayerMySQL<F,N,P,T,M>(this.connection);//A.P.
		
	}
	
	private boolean checkUnaryPredicate(String call, PQLTask t) {
		try {
			
			CallableStatement cs = connection.prepareCall(call);
		
			cs.registerOutParameter(1, java.sql.Types.BOOLEAN);
			cs.setInt(2,this.netID);
			cs.setInt(3,t.getID());
						
			cs.execute();
			
			boolean result = cs.getBoolean(1);
			
			cs.close();
			
			return result;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean checkBinaryPredicate(String call, PQLTask t1, PQLTask t2) {
		try {
			CallableStatement cs = connection.prepareCall(call);
		
			cs.registerOutParameter(1, java.sql.Types.BOOLEAN);
			cs.setInt(2,this.netID);
			cs.setInt(3,t1.getID());
			cs.setInt(4,t2.getID());
			
			cs.execute();
			
			boolean result = cs.getBoolean(1);
			
			cs.close();
			
			return result;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public void configure(Object obj) {
		this.identifier = (String) obj;
		
		try {
			if(PETRI_NET_IDENTIFIER_TO_ID_CS == null)
			PETRI_NET_IDENTIFIER_TO_ID_CS = connection.prepareCall(this.PETRI_NET_IDENTIFIER_TO_ID);
			
			PETRI_NET_IDENTIFIER_TO_ID_CS.registerOutParameter(1, java.sql.Types.INTEGER);
			PETRI_NET_IDENTIFIER_TO_ID_CS.setString(2, identifier);
			
			PETRI_NET_IDENTIFIER_TO_ID_CS.execute();
			
			this.netID = PETRI_NET_IDENTIFIER_TO_ID_CS.getInt(1);

		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean canOccur(PQLTask t) {
		return this.checkUnaryPredicate(this.PQL_CAN_OCCUR, t);
	}
	
	@Override
	public boolean alwaysOccurs(PQLTask t) {
		return this.checkUnaryPredicate(this.PQL_ALWAYS_OCCURS, t);
	}

	@Override
	public boolean canConflict(PQLTask t1, PQLTask t2) {
		return this.checkBinaryPredicate(this.PQL_CAN_CONFLICT, t1, t2) || (this.canOccur(t1) && !this.canOccur(t2));
	}

	@Override
	public boolean canCooccur(PQLTask t1, PQLTask t2) {
		return this.checkBinaryPredicate(this.PQL_CAN_COOCCUR, t1, t2);
	}

	/**
	 * See Def. 4.2. in Artem Polyvyanyy, Matthias Weidlich, Raffaele Conforti, Marcello La Rosa, Arthur H. M. ter Hofstede: The 4C Spectrum of Fundamental Behavioral Relations for Concurrent Systems. Petri Nets 2014:210-232
	 */
	@Override
	public boolean conflict(PQLTask t1, PQLTask t2) {
		return this.canConflict(t1,t2) && this.canConflict(t2,t1) && !this.canCooccur(t1,t2);
	}

	/**
	 * See Def. 4.2. in Artem Polyvyanyy, Matthias Weidlich, Raffaele Conforti, Marcello La Rosa, Arthur H. M. ter Hofstede: The 4C Spectrum of Fundamental Behavioral Relations for Concurrent Systems. Petri Nets 2014:210-232
	 */
	@Override
	public boolean cooccur(PQLTask t1, PQLTask t2) {
		return !this.canConflict(t1,t2) && !this.canConflict(t2,t1) && this.canCooccur(t1,t2);
	}

	@Override
	public boolean totalCausal(PQLTask t1, PQLTask t2) {
		return !this.canOccur(t1) || !this.canOccur(t2) || this.checkBinaryPredicate(this.PQL_TOTAL_CAUSAL, t1, t2);
	}

	@Override
	public boolean totalConcur(PQLTask t1, PQLTask t2) {
		return !this.canOccur(t1) || !this.canOccur(t2) || this.checkBinaryPredicate(this.PQL_TOTAL_CONCUR, t1, t2);
	}
	
	//A.P.
	@Override
	public boolean executes(PQLTrace trace) {
		boolean 		result	= false;
		XLog 			log		= trace.getTraceLog(); 
		PetrinetGraph	net		= PetrinetFactory.newPetrinet("PNML");
		
		try {		
			
		  	INetSystem<F,N,P,T,M> netSystem = PL.restoreNetSystem(this.netID);
		  	netSystem.loadNaturalMarking();
			//IOUtils.invokeDOT("./pics", eNetID+"-0-Original.png", netSystem.toDOT());
			
		  	AlignmentAPI<F,N,P,T,M> api = new AlignmentAPI<F,N,P,T,M>(netSystem);
			
		  	//check enabled transitions for empty trace
		  	if(trace.getTrace().isEmpty())
		  	{if(!api.netStartsWithSilentTransitions()) return false;}
		  	
		  	//check if net contains all trace labels 		
		   	Set<String> netLabels = api.getAllLabels();
		   	if(!api.netHasAllTraceLabels(trace, netLabels)) return false;
			
			AbstractTraceExecutionWithWildcardCharactersTesterTransformation<F,N,P,T,M> wct = new AbstractTraceExecutionWithWildcardCharactersTesterTransformation<F,N,P,T,M>(netSystem); 
			AbstractReplayer replayer = new Replayer<F,N,P,T,M>(api);
	  		
	  		//add Start and End transitions if trace has *
	  		if(trace.hasAsterisk()) {
	  			wct.addStartEnd(trace);
	 		}
	   		
	   		//get set of tasks for label unification
	  		Set<PQLTask> tasks = new HashSet<PQLTask>();
	  		for(int i=0; i<trace.getTrace().size(); i++) {
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
	  		while (it.hasNext()) {
	    		PQLTask task = it.next();
	    		
	    		ILabelUnificationTransformation<F,N,P,T,M> lut = new AbstractLabelUnificationTransformation<F,N,P,T,M>(netSystem,task.getSimilarLabels());
	    		trlog.add(lut);
	    		this.TM.transform(trlog);
	    		
	    		if (lut.getUnifiedTransition()==null) return false;
	    		lut.getUnifiedTransition().setLabel(task.getLabel());
	    		
	    		transitionMap.put(task.getLabel(), lut.getUnifiedTransition());
		 	}
	  		
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
					result = true;
					else
					result = false;
					
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
					result = true;
				else
					result = false;
			}	
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return result;
	}
}
