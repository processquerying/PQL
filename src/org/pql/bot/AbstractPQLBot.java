package org.pql.bot;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.jbpt.persist.MySQLConnection;
import org.jbpt.petri.IFlow;
import org.jbpt.petri.IMarking;
import org.jbpt.petri.INode;
import org.jbpt.petri.IPlace;
import org.jbpt.petri.ITransition;
import org.pql.index.IPQLIndex;
import org.pql.index.IndexStatus;
import org.pql.index.IndexType;
import org.pql.mc.IModelChecker;

/**
 * An abstract implementation of a PQL bot.
 * 
 * @author Artem Polyvyanyy
 */
public class AbstractPQLBot<F extends IFlow<N>, N extends INode, P extends IPlace, T extends ITransition, M extends IMarking<F,N,P,T>> 
	extends MySQLConnection implements Runnable, IPQLBotHeartBeat {

	protected IPQLIndex<F,N,P,T,M>		index	= null;
	protected IModelChecker<F,N,P,T,M>	MC		= null;
	
	protected String	botName		= null;
	protected boolean	verbose		= false;
	protected IndexType indexType	= null;
	protected long		indexTime	= 86400;
	protected long		sleepTime	= 300;
	
	protected boolean	isActive	= false;
	
	protected PQLBotRegularServiceThread regularService = null;

	private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	protected String	PQL_INDEX_BOTS_ALIVE		= "{CALL pql.pql_index_bots_alive(?)}";
	protected String	PQL_INDEX_BOTS_IS_ALIVE		= "{? = CALL pql.pql_index_bots_is_alive(?)}";
	
	public AbstractPQLBot(String mysqlURL, String mysqlUser, String mysqlPassword, 
			String botName, IPQLIndex<F,N,P,T,M> index, IModelChecker<F,N,P,T,M> mc, IndexType indexType, long indexTime, long sleepTime, boolean verbose) throws ClassNotFoundException, SQLException {
		super(mysqlURL,mysqlUser,mysqlPassword);
		
		this.botName	= (botName==null) ? UUID.randomUUID().toString() : botName;
		this.index		= index;
		this.MC			= mc;
		this.verbose	= verbose;
		this.indexType	= indexType;
		this.indexTime	= indexTime;
		this.sleepTime	= sleepTime;
		
		if (this.isAlive(this.botName)) {
			if (this.verbose) System.out.println(String.format("%s> ERROR: A PQL bot with the name %s is already alive.", this.dateFormat.format(new Date()), this.botName));
			return;
		}
		
		// TODO: ensure name is not hijacked between isAlive and alive
		this.alive(this.botName);
		
		this.regularService = new PQLBotRegularServiceThread(this.botName, this.index, this, this.verbose);
		this.regularService.start();
		
    	this.isActive = true;
	}
	
	public boolean index(int modelID) throws SQLException, InterruptedException {
		if (!this.isActive) return false;
		
		IndexStatus status = this.index.getIndexStatus(modelID);
		
		if (status==IndexStatus.UNINDEXED) {
			this.index.requestIndexing(modelID, this.botName);
			
			boolean start = this.index.startIndexing(modelID, this.botName);
			if (start) {
				PQLBotIndexThread indexThread = new PQLBotIndexThread(modelID,this.botName,this.MC,this.index,this.indexType,this.verbose);
        		indexThread.start();
				
				long startTime = System.currentTimeMillis();
        		while (indexThread.isAlive() && ((System.currentTimeMillis()-startTime) < (this.indexTime * 1000L))) {
        			Thread.sleep(1000L);
        		}
        		
        		if (indexThread.isAlive()) {
        			indexThread.interrupt();
        			if (this.verbose) System.out.println(String.format("%s> Bot %s interrupted job with ID %s because indexing took longer than %s seconds.", this.dateFormat.format(new Date()), this.botName, modelID, this.indexTime));
        		}
        				                				
    			boolean result = indexThread.hasCompleted() ? indexThread.getResult() : false;
				
    			if (result) {
    				this.index.finishIndexing(modelID, this.botName);
    				if (this.verbose) System.out.println(String.format("%s> Bot %s indexed model with ID %s.", this.dateFormat.format(new Date()), this.botName, modelID));
    				return true;
    			}
    			else {
    				if (this.verbose) System.out.println(String.format("%s> Bot %s did not index model with ID %s.", this.dateFormat.format(new Date()), this.botName, modelID));
    				this.index.deleteIndex(modelID);
    				return false;
    			}
        	}
			else {
				if (this.verbose) System.out.println(String.format("%s> Bot %s failed to start indexing the model with ID %s.", this.dateFormat.format(new Date()), this.botName, modelID));
				return false;
			}
		}
		else if (status==IndexStatus.INDEXING) {
			if (this.verbose) System.out.println(String.format("%s> Bot %s reports that indexing of the model with ID %s is already in progress.", this.dateFormat.format(new Date()), this.botName, modelID));
			return false;
		}
		else if (status==IndexStatus.INDEXED) {
			if (this.verbose) System.out.println(String.format("%s> Bot %s reports that the model with ID %s is already indexed.", this.dateFormat.format(new Date()), this.botName, modelID));
			return false;
		}
		else {
			if (this.verbose) System.out.println(String.format("%s> Bot %s reports that the model with ID %s can not be indexed at this stage.", this.dateFormat.format(new Date()), this.botName, modelID));
			return true;
		}
	}

	@Override
	public void run() {
		for (;;) { // forever
			try {
        		// get next index job
            	int modelID = this.index.getNextIndexingJob();
            	if (modelID<=0 && this.verbose) System.out.println(String.format("%s> There are no pending jobs.", dateFormat.format(new Date())));
            	else {
            		if (this.verbose) System.out.println(String.format("%s> Bot %s retrieved indexing job for the model with ID %s.", this.dateFormat.format(new Date()), this.botName, modelID));
                	this.index(modelID);
            	}
            	
            	if (this.verbose) System.out.println("---------------------------------------------------------------");
            	
            	// time to sleep before the next job
            	if (this.verbose) System.out.println(String.format("%s> Bot %s goes to sleep for %s seconds.", this.dateFormat.format(new Date()), botName, sleepTime));
            	Thread.sleep(sleepTime*1000);
            	if (this.verbose) System.out.println(String.format("%s> Bot %s woke up.", this.dateFormat.format(new Date()), botName));
        	}
        	catch (Exception e) {
        		e.printStackTrace();
        	}	
		}
	}
	
	class PQLBotRegularServiceThread extends Thread {
		protected IPQLIndex<F,N,P,T,M>	index = null;
		protected IPQLBotHeartBeat 			alive = null;
		
		protected String botName 	= null;
		protected boolean verbose	= false;
		
		PQLBotRegularServiceThread(String botName, IPQLIndex<F,N,P,T,M> index, IPQLBotHeartBeat alive, boolean verbose) {
			this.botName = botName;
			this.verbose = verbose;
			
			this.index = index;
			this.alive = alive;
		}

	    public void run() {
	    	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	    	
	    	try {
	    		for (;;) { // forever
		    		// send alive message
		    		try {
		    			this.alive.alive(this.botName);
		    			if (verbose) System.out.println(String.format("%s> Bot %s sent an alive message.", dateFormat.format(new Date()), botName));
					} catch (SQLException e) {
						System.out.println(String.format("%s> Bot %s failed to send an alive message.", dateFormat.format(new Date()), botName));
					}
		    		
		    		// sleep for 30 minutes
		    		Thread.sleep(1800*1000);
		    		
		    		// cleanup index
		    		try {
		    			this.index.cleanupIndex();
		    			if (verbose) System.out.println(String.format("%s> Bot %s has requested index cleanup.", dateFormat.format(new Date()), botName));
					} catch (SQLException e) {
						System.out.println(String.format("%s> Bot %s failed to request index cleanup.", dateFormat.format(new Date()), botName));
					}
		    		
		    		// sleep for 30 minutes
		    		Thread.sleep(1800*1000);
		    	}
	    	}
	    	catch (InterruptedException e) {}
	    }
	};
	
	class PQLBotIndexThread extends Thread {
		protected IPQLIndex<F,N,P,T,M>	index = null;
		protected IModelChecker<F,N,P,T,M> MC = null;
		
		protected String botName 	= null;
		protected int modelID 		= 0;
		protected boolean verbose	= false;
		
		private boolean result = false;
		private boolean completed = false;
		
		protected IndexType indexType	= null;
		
		PQLBotIndexThread(int modelID, String botName, IModelChecker<F,N,P,T,M> mc, IPQLIndex<F,N,P,T,M> index, IndexType indexType, boolean verbose) {
			this.botName = botName;
			this.modelID = modelID;
			this.verbose = verbose;
			
			this.index		= index;
			this.MC 		= mc;
			this.indexType	= indexType;
		}

		@Override
		public void run() {
			try {
				// check if model can be indexed
				if (this.verbose) System.out.println(String.format("%s> Bot %s starts checking model with ID %s.", dateFormat.format(new Date()), this.botName, this.modelID));
        		boolean check = this.index.checkNetSystem(modelID);
        		if (this.verbose) System.out.println(String.format("%s> Bot %s finished checking model with ID %s.", dateFormat.format(new Date()), this.botName, this.modelID));
        		
        		if (check) {
        			if (this.verbose) System.out.println(String.format("%s> Bot %s starts indexing model with ID %s.", dateFormat.format(new Date()), this.botName, this.modelID));
        			this.result = this.index.constructIndex(modelID, this.indexType);
        			
        			if (this.result) {
    					this.index.finishIndexing(this.modelID, this.botName);
    					if (this.verbose) System.out.println(String.format("%s> Bot %s finished indexing model with ID %s.", dateFormat.format(new Date()), this.botName, this.modelID));
    					this.completed = true;
    				}
        		}
        		else {
        			if (this.verbose) if (this.verbose) System.out.println(String.format("%s> Bot %s reports that the model with ID %s can not be indexed at this stage.", dateFormat.format(new Date()), this.botName, this.modelID));
        		}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		public boolean getResult() {
			return this.result;
		}
		
		public boolean hasCompleted() {
			return this.completed;
		}
	};


	@Override
	public void alive(String botName) throws SQLException {
		if (botName == null || botName.isEmpty()) return;
		
		CallableStatement cs = connection.prepareCall(this.PQL_INDEX_BOTS_ALIVE);
		
		cs.setString(1, botName);
		
		cs.execute();
		cs.close();
	}

	@Override
	public boolean isAlive(String botName) throws SQLException {
		if (botName == null || botName.isEmpty()) return false;
		
		CallableStatement cs = connection.prepareCall(this.PQL_INDEX_BOTS_IS_ALIVE);
		
		cs.registerOutParameter(1, java.sql.Types.BOOLEAN);
		cs.setString(2, botName);
		
		cs.execute();
		
		boolean result = cs.getBoolean(1);
		
		cs.close();
		
		return result;
	}

	public void terminate() {
		if (this.regularService!=null)
			this.regularService.interrupt();
	}
}
