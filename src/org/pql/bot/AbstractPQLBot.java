package org.pql.bot;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.jbpt.petri.IFlow;
import org.jbpt.petri.IMarking;
import org.jbpt.petri.INode;
import org.jbpt.petri.IPlace;
import org.jbpt.petri.ITransition;
import org.pql.index.IPQLIndex;
import org.pql.index.IndexStatus;
import org.pql.index.IndexType;
import org.pql.mc.IModelChecker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * An abstract implementation of a PQL bot.
 * 
 * @author Artem Polyvyanyy
 */
public class AbstractPQLBot<F extends IFlow<N>, N extends INode, P extends IPlace, T extends ITransition, M extends IMarking<F,N,P,T>> 
extends Thread
implements IPQLBotHeartBeat {

	/** The name of the logger is "org.pql.bot.[name]". */
	private Logger logger;

	protected Connection connection = null;
	protected IPQLIndex<F,N,P,T,M>		index	= null;
	protected IModelChecker<F,N,P,T,M>	MC		= null;

	protected String	botName		= null;
	protected IndexType indexType	= null;
	protected long		indexTime	= 86400;
	protected long		sleepTime	= 300;

	protected boolean	isActive	= false;

	protected PQLBotRegularServiceThread regularService = null;
	protected PQLBotIndexThread	indexThread = null;

	protected String	PQL_INDEX_BOTS_ALIVE		= "{CALL pql_index_bots_alive(?)}";
	protected String	PQL_INDEX_BOTS_IS_ALIVE		= "{? = CALL pql_index_bots_is_alive(?)}";

	/**
	 * @param botName  identifies this bot in log messages; cannot be the same as any other preexisting instance;
	 * @throws NameInUseException if <var>botName</var> is in use as the identifer for another instance of this class
	 */
	public AbstractPQLBot(Connection con, String botName, IPQLIndex<F,N,P,T,M> index, IModelChecker<F,N,P,T,M> mc, IndexType indexType, long indexTime, long sleepTime) throws ClassNotFoundException, NameInUseException, SQLException {
		this.connection = con;
		this.botName	= (botName==null) ? UUID.randomUUID().toString() : botName;
		this.index		= index;
		this.MC			= mc;
		this.indexType	= indexType;
		this.indexTime	= indexTime;
		this.sleepTime	= sleepTime;

		if (this.isAlive(this.botName)) {
			throw new NameInUseException(this.botName);
		}

		this.logger = LoggerFactory.getLogger("org.pql.bot." + this.botName);

		// TODO: ensure name is not hijacked between isAlive and alive
		this.alive(this.botName);

		this.regularService = new PQLBotRegularServiceThread(this.botName, this.index, this);
		this.regularService.start();

		this.isActive = true;
	}

	/**
	 * Report of an attempt to create a bot without a unique name.
	 */
	public static class NameInUseException extends Exception {
		private static final long serialVersionUID = 1L;
		
		private final String name;
		NameInUseException(String name) { this.name = name; }
		String getName() { return name; }
	}

	public boolean index(int modelID) throws SQLException, InterruptedException {
		if (!this.isActive) return false;
		
		IndexStatus status = this.index.getIndexStatus(modelID);
		

		switch (status) {
		case UNINDEXED:
			this.index.requestIndexing(modelID, this.botName);

			boolean start = this.index.startIndexing(modelID, this.botName);
			if (start) {
				indexThread = new PQLBotIndexThread(modelID,this.botName,this.MC,this.index,this.indexType);
				indexThread.MC.setLoLAActive(true); //A.P.
				indexThread.start();

				long startTime = System.currentTimeMillis();
				while (indexThread.isAlive() && ((System.currentTimeMillis()-startTime) < (this.indexTime * 1000L))) {
					Thread.sleep(1000L);
				}
				
				if (indexThread.isAlive()) {
					
					//A.P.
					indexThread.MC.setLoLAActive(false);
					if (!indexThread.lolaProcesses.isEmpty() && indexThread.lolaProcesses.iterator().next() != null) indexThread.lolaProcesses.iterator().next().destroy();
					
					logger.warn(String.format("Interrupted job with ID %s because indexing took longer than %s seconds.", modelID, this.indexTime));
				}

				boolean result = indexThread.hasCompleted() && indexThread.getResult();
				if (result) {
					this.index.finishIndexing(modelID, this.botName);
					logger.info(String.format("Indexed model with ID %s.", modelID));
					return true;
				}
				else {
					logger.warn(String.format("Did not index model with ID %s.", modelID));
					this.index.cannnotIndex(modelID);//A.P.
					this.index.deleteIndexedRelations(modelID);//A.P.
					return false;
				}
			}
			else {
				logger.warn(String.format("Failed to start indexing the model with ID %s.", modelID));
				return false;
			}

		case INDEXING:
			logger.info(String.format("Indexing of the model with ID %s is already in progress.", modelID));
			return false;

		case INDEXED:
			logger.info(String.format("The model with ID %s is already indexed.", modelID));
			return false;

		default:
			logger.info(String.format("The model with ID %s can not be indexed at this stage.", modelID));
			return true;
		}
	}

	@Override
	public void run() {
		if (!this.isActive) return;

		for (;;) { // forever
			try {
				// get next index job
				int modelID = this.index.getNextIndexingJob();
				if (modelID<=0)
					logger.debug("There are no pending jobs.");
				else {
					logger.debug(String.format("Retrieved indexing job for the model with ID %s.", modelID));
					this.index(modelID);
				}

				// time to sleep before the next job
				logger.debug(String.format("Going to sleep for %s seconds.", sleepTime));
				Thread.sleep(sleepTime*1000);
				logger.debug("Woke up.");
			}
			catch (Exception e) {
				logger.error("Job polling was interrupted", e);
			}	
		}
	}

	class PQLBotRegularServiceThread extends Thread {
		protected IPQLIndex<F,N,P,T,M>	index = null;
		protected IPQLBotHeartBeat 			alive = null;

		protected String botName 	= null;

		PQLBotRegularServiceThread(String botName, IPQLIndex<F,N,P,T,M> index, IPQLBotHeartBeat alive) {
			this.botName = botName;
			this.index = index;
			this.alive = alive;
		}

		public void run() {
			try {
				for (;;) { // forever
					// send alive message
					try {
						this.alive.alive(this.botName);
						logger.debug("Sent an alive message.");
					} catch (SQLException e) {
						logger.error("Failed to send an alive message.", e);
					}

					// sleep for 30 minutes
					Thread.sleep(1800*1000);

					// cleanup index
					try {
						this.index.cleanupIndex();
						logger.debug("Requested index cleanup.");
					} catch (SQLException e) {
						logger.error("Unable to request index cleanup.", e);
					}

					// sleep for 30 minutes
					Thread.sleep(1800*1000);
				}
			}
			catch (InterruptedException e) {}
		}
	};

	class PQLBotIndexThread extends Thread {

		protected Set<Process> lolaProcesses = null; //A.P.
		
		protected IPQLIndex<F,N,P,T,M>	index = null;
		protected IModelChecker<F,N,P,T,M> MC = null;

		protected String botName 	= null;
		protected int modelID 		= 0;

		private boolean result = false;
		private boolean completed = false;

		protected IndexType indexType	= null;

		PQLBotIndexThread(int modelID, String botName, IModelChecker<F,N,P,T,M> mc, IPQLIndex<F,N,P,T,M> index, IndexType indexType) {
			this.botName = botName;
			this.modelID = modelID;

			this.index		= index;
			this.MC 		= mc;
			this.indexType	= indexType;
			
			this.lolaProcesses = new HashSet<Process>(); //A.P.
		}

		@Override
		public void run() {
							
			try {
				// check if model can be indexed
				logger.debug(String.format("Start checking model with ID %s.", this.modelID));
				
				boolean check = this.index.checkNetSystem(modelID,this.lolaProcesses); //A.P.
				
				logger.debug(String.format("Finished checking model with ID %s.", this.modelID));
	
				if (check) {
					logger.info(String.format("Start indexing model with ID %s.", this.modelID));
					
					this.result = this.index.constructIndex(modelID, this.indexType, this.lolaProcesses, this.MC.isLoLAActive()); //A.P.
					
					if(!this.MC.isLoLAActive().get()) {
						this.index.cannnotIndex(this.modelID);
						this.index.deleteIndexedRelations(this.modelID);
						logger.warn(String.format("The model with ID %s can not be indexed at this stage.", this.modelID));
					}
					else {
						if (this.result) {
							this.index.finishIndexing(this.modelID, this.botName);
							logger.info(String.format("Finished indexing model with ID %s.", this.modelID));
							this.completed = true;
						}
					}
				}
				else {
					this.index.cannnotIndex(this.modelID);
					logger.warn(String.format("The model with ID %s can not be indexed at this stage.", this.modelID));
				}
			} catch (SQLException e) {
				logger.error(String.format("Failed to index model with ID %s.", this.modelID), e);
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

		if (this.indexThread!=null)
			this.indexThread.interrupt();

		this.interrupt();
	}
}
