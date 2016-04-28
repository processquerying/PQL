package org.pql.core;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

import org.deckfour.xes.model.XLog;
import org.jbpt.petri.IFlow;
import org.jbpt.petri.IMarking;
import org.jbpt.petri.INetSystem;
import org.jbpt.petri.INode;
import org.jbpt.petri.IPlace;
import org.jbpt.petri.ITransition;
import org.jbpt.petri.persist.AbstractPetriNetPersistenceLayerMySQL;
import org.jbpt.petri.persist.IPetriNetPersistenceLayer;
import org.jbpt.utils.IOUtils;
import org.json.JSONArray;
import org.pql.alignment.AbstractReplayer;
import org.pql.alignment.AlignmentAPI;
import org.pql.alignment.InsertMove;
import org.pql.alignment.PQLAlignment;
import org.pql.alignment.Replayer;
import org.pql.alignment.SkipMove;
import org.pql.label.ILabelManager;
import org.pql.petri.AbstractLabelUnificationTransformation;
import org.pql.petri.AbstractNetSystemTransformationManager;
import org.pql.petri.AbstractTraceExecutionWithWildcardCharactersTesterTransformation;
import org.pql.petri.ILabelUnificationTransformation;
import org.pql.petri.TransformationLog;
import org.processmining.models.graphbased.directed.petrinet.PetrinetGraph;
import org.processmining.models.graphbased.directed.petrinet.elements.Transition;
import org.processmining.models.graphbased.directed.petrinet.impl.PetrinetFactory;


/**
 * @author Artem Polyvyanyy
 */
public class PQLBasicPredicatesMySQL<F extends IFlow<N>, N extends INode, P extends IPlace, T extends ITransition, M extends IMarking<F,N,P,T>> //A.P. <...>added 
				implements IPQLBasicPredicatesOnTasks
				{
	public AtomicInteger filteredModels = new AtomicInteger(); //A.P.
	private String repairedID = null;
	
	protected String PETRI_NET_IDENTIFIER_TO_ID 			= "{? = CALL pql.jbpt_petri_nets_get_internal_id(?)}";
	protected final String PQL_CAN_OCCUR					= "{? = CALL pql.pql_can_occur(?,?)}";
	protected final String PQL_ALWAYS_OCCURS				= "{? = CALL pql.pql_always_occurs(?,?)}";
	protected final String PQL_CAN_CONFLICT					= "{? = CALL pql.pql_can_conflict(?,?,?)}";
	protected final String PQL_CAN_COOCCUR					= "{? = CALL pql.pql_can_cooccur(?,?,?)}";
	protected final String PQL_TOTAL_CAUSAL					= "{? = CALL pql.pql_total_causal(?,?,?)}";
	protected final String PQL_TOTAL_CONCUR					= "{? = CALL pql.pql_total_concur(?,?,?)}";
	
	//A.P.
	protected String PQL_CHECK_UNARY_PREDICATE_MACRO		= "{CALL pql.pql_check_unary_predicate_macro(?,?,?,?)}";
	protected String PQL_CHECK_BINARY_PREDICATE_MACRO		= "{CALL pql.pql_check_binary_predicate_macro(?,?,?,?,?)}";

	//A.P.
	protected CallableStatement PETRI_NET_IDENTIFIER_TO_ID_CS 			= null;
	protected CallableStatement PQL_CAN_OCCUR_CS						= null;
	protected CallableStatement PQL_ALWAYS_OCCURS_CS					= null;
	protected CallableStatement PQL_CAN_CONFLICT_CS						= null;
	protected CallableStatement PQL_CAN_COOCCUR_CS						= null;
	protected CallableStatement PQL_TOTAL_CAUSAL_CS						= null;
	protected CallableStatement PQL_TOTAL_CONCUR_CS						= null;
	protected CallableStatement PQL_CHECK_UNARY_PREDICATE_MACRO_CS		= null;
	protected CallableStatement PQL_CHECK_BINARY_PREDICATE_MACRO_CS		= null;
	
	private String						identifier = null;	
	private int							netID = 0;
	private Connection 					connection = null;
	private IPetriNetPersistenceLayer<F,N,P,T,M> 				PL = null;
	private AbstractNetSystemTransformationManager<F,N,P,T,M> 	TM = null;//A.P.
	private ILabelManager 										LM = null;//A.P.
		
	public PQLBasicPredicatesMySQL(Connection con, ILabelManager labelMngr, AtomicInteger filteredModels) throws ClassNotFoundException, SQLException {
		this.connection = con;
		this.PL = new AbstractPetriNetPersistenceLayerMySQL<F,N,P,T,M>(this.connection);//A.P.
		this.filteredModels = filteredModels;
		this.LM = labelMngr;
	}
	
	private boolean checkUnaryPredicate(String call, PQLTask t) {
		try {
			
			CallableStatement cs = null;
			switch(call)
			{
			case(PQL_CAN_OCCUR) : 
				if(PQL_CAN_OCCUR_CS == null){cs = connection.prepareCall(call);}else{cs=PQL_CAN_OCCUR_CS;}
			case(PQL_ALWAYS_OCCURS) : 
				if(PQL_ALWAYS_OCCURS_CS == null){cs = connection.prepareCall(call);}else{cs=PQL_ALWAYS_OCCURS_CS;}
			}
		
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
	
	//A.P. v2
			@Override
			public boolean checkBinaryPredicateMacro(String op, String q, JSONArray ids1, JSONArray ids2) {
				try {
					CallableStatement cs = null;
					String call = PQL_CHECK_BINARY_PREDICATE_MACRO;
					if(PQL_CHECK_BINARY_PREDICATE_MACRO_CS == null){cs = connection.prepareCall(call);}
					else{cs = PQL_CHECK_BINARY_PREDICATE_MACRO_CS;}
				
					cs.setInt(1,this.netID);
					cs.setString(2, op);
					cs.setString(3, q);
					cs.setObject(4, ids1.toString());
					cs.setObject(5, ids2.toString());
					cs.execute();
					ResultSet rs = cs.getResultSet();	
					rs.next();
					boolean result = rs.getBoolean(1);
					return result;
						
				}
				catch (SQLException e) {
					e.printStackTrace();
					return false;
				} 
			}

	
	//A.P.
		@Override
		public boolean checkUnaryPredicateMacroV2(String op, String q, JSONArray ids) {
			try {
				CallableStatement cs = null;
				String call = PQL_CHECK_UNARY_PREDICATE_MACRO;
				if(PQL_CHECK_UNARY_PREDICATE_MACRO_CS == null){cs = connection.prepareCall(call);}
				else{cs = PQL_CHECK_UNARY_PREDICATE_MACRO_CS;}
		
				cs.setInt(1,this.netID);
				cs.setString(2, op);
				cs.setString(3, q);
				cs.setObject(4, ids.toString());
				cs.execute();
				ResultSet rs = cs.getResultSet();	
				rs.next();
				boolean result = rs.getBoolean(1);
				return result;
					
			}
			catch (SQLException e) {
				e.printStackTrace();
				return false;
			} 
		}
	
	
	private boolean checkBinaryPredicate(String call, PQLTask t1, PQLTask t2) {
		try {
			
			CallableStatement cs = null;
			switch(call)
			{
			case(PQL_CAN_CONFLICT) : 
				if(PQL_CAN_CONFLICT_CS == null){cs = connection.prepareCall(call);}else{cs=PQL_CAN_CONFLICT_CS;}
			case(PQL_CAN_COOCCUR) : 
				if(PQL_CAN_COOCCUR_CS == null){cs = connection.prepareCall(call);}else{cs=PQL_CAN_COOCCUR_CS;}
			case(PQL_TOTAL_CAUSAL) : 
				if(PQL_TOTAL_CAUSAL_CS == null){cs = connection.prepareCall(call);}else{cs=PQL_TOTAL_CAUSAL_CS;}
			case(PQL_TOTAL_CONCUR) : 
				if(PQL_TOTAL_CONCUR_CS == null){cs = connection.prepareCall(call);}else{cs=PQL_TOTAL_CONCUR_CS;}
		
			}
			
		
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
			if (PETRI_NET_IDENTIFIER_TO_ID_CS == null)
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
			
			//check if net contains all trace labels 
		  	Set<String> netLabels = LM.getAllLabels(this.netID);
			 
		   	if(!PL.netHasAllTraceLabels(trace, netLabels)) 
		   	{
		   		this.filteredModels.incrementAndGet(); 
		   		return false;
		   	}
			
			INetSystem<F,N,P,T,M> netSystem = PL.restoreNetSystem(this.netID);
		  	netSystem.loadNaturalMarking();
			//IOUtils.invokeDOT("./pics", this.netID+"-original-from-select.png", netSystem.toDOT());
			
		  	AlignmentAPI<F,N,P,T,M> api = new AlignmentAPI<F,N,P,T,M>(netSystem);
			
		  	//check enabled transitions for empty trace
		  	if(trace.getTrace().isEmpty())
		  	{if(!api.netStartsWithSilentTransitions()) return false;}
				 		
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
	  		
	  		//IOUtils.invokeDOT("./pics", this.netID+"-1-AfterLU.png", netSystem.toDOT());
	 		
				if(!trace.hasAsterisk())
				{
					// convert Net System to PetrinetGraph
					net = api.constructPetrinetGraph(net);
					
					// get an optimal alignment
					PQLAlignment alignment = replayer.getAlignment(net, log);
									
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
			 	//IOUtils.invokeDOT("./pics", this.netID+"-2-AfterWildcardTransformation.png", netSystem.toDOT());
		    		
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
	
	//A.P.
	public IPetriNetPersistenceLayer<F,N,P,T,M> getPL()
	{
		return this.PL;
	}
	
	//A.P.
	@SuppressWarnings("unchecked")
	@Override
	public boolean repairNet(PQLTrace trace)
	{
		INetSystem<F,N,P,T,M> netSystem = null;
		INetSystem<F,N,P,T,M> clonedNetSystem = null;
		
		Map<N,N> n2n = new HashMap<N,N>();
		Map<T,T> clonedT2T = new HashMap<T,T>();
		Map<Transition,T> transition2unifiedT = new HashMap<Transition,T>();
		Map<T,Set<T>> UT2T = new HashMap<T,Set<T>>();
		
		try {
			
			netSystem = PL.restoreNetSystem(this.netID);
			netSystem.loadNaturalMarking();
			
			//IOUtils.invokeDOT("./pics", this.identifier+"-original.png", netSystem.toDOT());
			
			clonedNetSystem = netSystem.clone(n2n);
			for(N n : n2n.keySet())
			{if(n instanceof ITransition){T clonedT = (T) n2n.get(n); T t = (T) n; clonedT2T.put(clonedT, t);}}
				
			//get set of tasks for label unification
	  		Set<PQLTask> tasks = new HashSet<PQLTask>();
	  		for(int i=0; i<trace.getTrace().size(); i++) 
	  		{
	  			PQLTask task = trace.getTrace().elementAt(i);
	  			if(!task.isAsterisk() && task.getSimilarity() < 1.0)
		     	tasks.add(task);
		    }
	  	
	  		//label unification
	  		if(tasks.size() > 0)
	  		{
	  		
		  		this.TM = new AbstractNetSystemTransformationManager<F,N,P,T,M>(clonedNetSystem);
		  		TransformationLog<F,N,P,T,M> trlog = new TransformationLog<F,N,P,T,M>();
			  		
		  		Iterator<PQLTask> it = tasks.iterator();
		  		while (it.hasNext()) 
		  		{
		    		PQLTask task = it.next();
		    		ILabelUnificationTransformation<F,N,P,T,M> lut = new AbstractLabelUnificationTransformation<F,N,P,T,M>(clonedNetSystem,task.getSimilarLabels());
		    		trlog.add(lut);
		    		this.TM.transform(trlog);
		    		
		    		if (lut.getUnifiedTransition() != null)
		    		{
		    			lut.getUnifiedTransition().setLabel(task.getLabel());
		     			UT2T.putAll(lut.getUT2T());
		    		}
			 	}
	  		}
	   		
	  		//IOUtils.invokeDOT("./pics",this.identifier+"-1-cloned-afterLU.png", clonedNetSystem.toDOT());
	 		 
	  		// get an optimal alignment
	  		AlignmentAPI<F,N,P,T,M> replayAPI 	= new AlignmentAPI<F,N,P,T,M>(clonedNetSystem);
	  		AlignmentAPI<F,N,P,T,M> repairAPI 	= new AlignmentAPI<F,N,P,T,M>(netSystem);
		  	XLog 					log		 	= trace.getTraceLog(); 
			PetrinetGraph			net		 	= PetrinetFactory.newPetrinet("PNML");
			net = replayAPI.constructPetrinetGraphForInsert(net,transition2unifiedT);
	  		AbstractReplayer 		replayer 	= new Replayer<F,N,P,T,M>(replayAPI);
	  		
			PQLAlignment alignment = replayer.getInsertAlignment(net, log);
			alignment = repairAPI.transformAlignment(alignment, transition2unifiedT, UT2T, clonedT2T);
			int alignmentCost = alignment.getInsertAlignmentCost();
		
			P sourcePlace = netSystem.getSourcePlaces().iterator().next();
			P sinkPlace = netSystem.getSinkPlaces().iterator().next();
			
			if(alignmentCost > 0)
			{
				//get all insert moves
				Vector<InsertMove> insertMoves = new Vector<InsertMove>();
				insertMoves.addAll(repairAPI.getInsertMoves(alignment,trace));
				
				//add self-loops to netSystem
				repairAPI.addInsertMoves(insertMoves,alignment);
				repairAPI.updateInsertMovesInAlignment(alignment);
				//IOUtils.invokeDOT("./pics", this.identifier+"-2-TasksAdded.png", netSystem.toDOT());
			
			}
			
			//skips
				Vector<SkipMove> skipMoves = new Vector<SkipMove>();
				skipMoves.addAll(repairAPI.getSkipMoves(alignment,trace,sourcePlace,sinkPlace));
				
				if(alignmentCost == 0 && skipMoves.size() == 0)
				{
				return false;
				}else
				{
					repairAPI.addSkips(skipMoves); //add skips to net
					//IOUtils.invokeDOT("./pics", this.identifier+"-repaired.png", netSystem.toDOT());
				}
			
				
				
			for(T t : netSystem.getTransitions())
			if(t.isSilent()){t.setName("");	t.setLabel("");}else{t.setName(t.getLabel());}
				
		  	//write transformed net to DB with a new external ID
			this.repairedID = this.identifier+"_"+netSystem.hashCode();	
			//PL.storeNetSystem(netSystem, this.repairedID);
			
			//int intID = PL.getInternalID(this.repairedID); //for PQLTestInsertSelect
			//PL.changeNetIndexStatus(intID); //for PQLTestInsertSelect
			
			} catch (SQLException e) {e.printStackTrace();} catch (ClassNotFoundException e) {e.printStackTrace();}
			
		return true;
	}

	//A.P.
	@Override
	public String getRepairedID() {
	return this.repairedID;
	}
	
	//A.P.
	@Override
	public boolean checkCooccurMacro(String q, JSONArray ids1, JSONArray ids2) {
		// TODO Auto-generated method stub
		return false;
	}
	
	//A.P.
	@Override
	public boolean checkConflictMacro(String q, JSONArray ids1, JSONArray ids2) {
		// TODO Auto-generated method stub
		return false;
	}
	
	//A.P.
	@Override
	public boolean checkCanConflictMacro(String q, JSONArray ids1, JSONArray ids2) {
		// TODO Auto-generated method stub
		return false;
	}
	
	//A.P.
	@Override
	public boolean checkTotalCausalMacro(String q, JSONArray ids1, JSONArray ids2) {
		// TODO Auto-generated method stub
		return false;
	}
	
	//A.P.
	@Override
	public boolean checkTotalConcurMacro(String q, JSONArray ids1, JSONArray ids2) {
		// TODO Auto-generated method stub
		return false;
	}
	
	//A.P.
	@Override
	public boolean canOccur(PQLTask t, Set<Process> p) {
		
		return canOccur(t);
	}
	
	//A.P.
	@Override
	public boolean alwaysOccurs(PQLTask t, Set<Process> p) {
		
		return alwaysOccurs(t);
	}
    //A.P.
	@Override
	public boolean canCooccur(PQLTask t1, PQLTask t2, Set<Process> p) {
		
		return canCooccur(t1, t2);
	}
	 //A.P.
	@Override
	public boolean canConflict(PQLTask t1, PQLTask t2, Set<Process> p) {
		
		return canConflict(t1, t2);
	}
	//A.P.
	@Override
	public boolean totalCausal(PQLTask t1, PQLTask t2, Set<Process> p) {
		
		return totalCausal(t1, t2);
	}
	
	
}
