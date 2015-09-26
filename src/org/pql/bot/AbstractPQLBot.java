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

public class AbstractPQLBot<F extends IFlow<N>, N extends INode, P extends IPlace, T extends ITransition, M extends IMarking<F,N,P,T>> 
	extends MySQLConnection implements Runnable, IPQLBotAlive{

	protected IPQLIndex<F,N,P,T,M>		index	= null;
	protected IModelChecker<F,N,P,T,M>	MC		= null;
	
	protected String	botName		= null;
	protected boolean	verbose		= false;
	protected IndexType indexType	= null;
	protected long		indexTime	= 86400;
	protected long		sleepTime	= 300;
	
	protected boolean	isActive	= false;
	
	protected PQLBotRegularServiceThread regularService = null;

	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
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
			if (this.verbose) System.out.println("ERROR: PQL bot with the proposed name is already alive. PQL bot stopped.");
			if (this.verbose) System.out.println("===============================================================");
			return;
		}
		
		// TODO: ensure name is not hijacked between isAlive and alive
		this.alive(this.botName);
		
		this.regularService = new PQLBotRegularServiceThread(this.botName, this.index, this, this.verbose);
		this.regularService.start();
		
    	this.isActive = true;
	}
	
	public boolean performJob(int jobID) throws SQLException, InterruptedException {
		if (!this.isActive) return false;
		
		IndexStatus status = this.index.getIndexStatus(jobID);
		
		if (status==IndexStatus.UNINDEXED) {
			this.index.requestIndexing(jobID, this.botName);
			
			boolean start = this.index.startIndexing(jobID, this.botName);
			if (start) {
				PQLBotIndexThread indexThread = new PQLBotIndexThread(jobID,this.botName,this.MC,this.index,this.indexType,this.verbose);
        		indexThread.start();
				
				long startTime = System.currentTimeMillis();
        		while (indexThread.isAlive() && ((System.currentTimeMillis()-startTime) < (this.indexTime * 1000L))) {
        			Thread.sleep(1000L);
        		}
        		
        		if (indexThread.isAlive()) {
        			indexThread.interrupt();
        			if (this.verbose) System.out.println(String.format("%s> Bot %s interrupted job with ID %s.", dateFormat.format(new Date()), botName, jobID));
        		}
        				                				
    			boolean result = indexThread.hasCompleted() ? indexThread.getResult() : false;
				
    			if (result) {
    				this.index.finishIndexing(jobID, this.botName);
    				if (this.verbose) System.out.println("model indexed successfully");
    				return true;
    			}
    			else {
    				if (this.verbose) System.out.println("model not indexed");
    				this.index.deleteIndex(jobID);
    				return false;
    			}
        	}
			else {
				if (this.verbose) System.out.println(String.format("failed to start indexing of the model with identifier %s.", jobID));
				return false;
			}
		}
		else if (status==IndexStatus.INDEXING) {
			if (this.verbose) System.out.println("indexing of the model with the specified identifier is already in progress");
			return false;
		}
		else if (status==IndexStatus.INDEXED) {
			if (this.verbose) System.out.println("model with the specified identifier is already indexed");
			return false;
		}
		else {
			if (this.verbose) System.out.println("model with the specified identifier cannot be indexed");
			return true;
		}
	}

	@Override
	public void run() {
		// TIME TO INDEX ...
        while (true) {
        	try {
        		// get next index job
            	int jobID = this.index.getNextIndexingJob();
            	if (jobID<=0 && this.verbose) System.out.println(String.format("%s> There are no pending jobs.", dateFormat.format(new Date())));
            	else {
            		if (this.verbose) System.out.println(String.format("%s> Bot %s fetched new job with ID %s.", dateFormat.format(new Date()), this.botName, jobID));
            		
            		// claim job
            		//this.index.requestIndexing(jobID, this.botName);
            		//if (this.verbose) System.out.println(String.format("%s> Bot %s claims job with ID %s.", dateFormat.format(new Date()), this.botName, jobID));
                	
                	// start job
                	//boolean start = this.index.startIndexing(jobID, this.botName);
                	//if (!start && this.verbose) System.out.println(String.format("%s> Bot %s FAILED to claim job with ID %s.", dateFormat.format(new Date()), this.botName, jobID));
                	//else {
                		//if (this.verbose) System.out.println(String.format("%s> Bot %s claimed job with ID %s.", dateFormat.format(new Date()), this.botName, jobID));
                		
                		this.performJob(jobID);
                	//}
            	}
            	
            	if (this.verbose) System.out.println("---------------------------------------------------------------");
            	
            	// time to sleep before the next job
            	if (this.verbose) System.out.println(String.format("%s> Bot %s will sleep for %s seconds.", dateFormat.format(new Date()), botName, sleepTime));
            	Thread.sleep(sleepTime*1000);
            	if (this.verbose) System.out.println(String.format("%s> Bot %s woke up.", dateFormat.format(new Date()), botName));
        	}
        	catch (Exception e) {
        		e.printStackTrace();
        	}
        	
        }
	}
	
	class PQLBotRegularServiceThread extends Thread {
		protected IPQLIndex<F,N,P,T,M>	index = null;
		protected IPQLBotAlive 			alive = null;
		
		protected String botName 	= null;
		protected boolean verbose	= false;
		
		PQLBotRegularServiceThread(String botName, IPQLIndex<F,N,P,T,M> index, IPQLBotAlive alive, boolean verbose) {
			this.botName = botName;
			this.verbose = verbose;
			
			this.index = index;
			this.alive = alive;
		}

	    public void run() {
	    	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	    	
	    	while (true) {
	    		// send alive message
	    		try {
	    			this.alive.alive(this.botName);
	    			if (verbose) System.out.println(String.format("%s> Bot %s sent alive message.", dateFormat.format(new Date()), botName));
				} catch (SQLException e) {
					System.out.println(String.format("%s> Bot %s FAILED to send alive message.", dateFormat.format(new Date()), botName));
				}
	    		
	    		// sleep for 30 minutes
	    		try { Thread.sleep(1800*1000); } catch (InterruptedException e) {}
	    		
	    		// cleanup index
	    		try {
	    			this.index.cleanupIndex();
	    			if (verbose) System.out.println(String.format("%s> Bot %s successfully requested index cleanup.", dateFormat.format(new Date()), botName));
				} catch (SQLException e) {
					System.out.println(String.format("%s> Bot %s FAILED to request index cleanup.", dateFormat.format(new Date()), botName));
				}
	    		
	    		// sleep for 30 minutes
	    		try { Thread.sleep(1800*1000); } catch (InterruptedException e) {}
	    	}
	    }
	};
	
	class PQLBotIndexThread extends Thread {
		protected IPQLIndex<F,N,P,T,M>	index = null;
		protected IModelChecker<F,N,P,T,M> MC = null;
		
		protected String botName 	= null;
		protected int jobID 		= 0;
		protected boolean verbose	= false;
		
		private boolean result = false;
		private boolean completed = false;
		
		protected IndexType indexType	= null;
		
		PQLBotIndexThread(int jobID, String botName, IModelChecker<F,N,P,T,M> mc, IPQLIndex<F,N,P,T,M> index, IndexType indexType, boolean verbose) {
			this.botName = botName;
			this.jobID = jobID;
			this.verbose = verbose;
			
			this.index		= index;
			this.MC 		= mc;
			this.indexType	= indexType;
		}

		@Override
		public void run() {
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			try {
				// check if model can be indexed
				if (this.verbose) System.out.println("checking model");
        		boolean check = this.index.checkNetSystem(jobID);
        		
        		if (check) {
        			if (this.verbose) System.out.println("indexing started");
        			this.result = this.index.constructIndex(jobID, this.indexType);
        		}
        		else {
        			if (this.verbose) System.out.println(String.format("the model with identifier %s cannot be indexed", jobID));
        		}
				
				this.result = this.index.constructIndex(this.jobID, this.indexType);
				
				if (this.result) {
					this.index.finishIndexing(this.jobID, this.botName);
            		System.out.println(String.format("%s> Bot %s finished job with ID %s.", dateFormat.format(new Date()), this.botName, this.jobID));
				}
				
				this.completed = true;
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
