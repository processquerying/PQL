package org.pql.bot;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.pql.api.PQLAPI;
import org.pql.ini.PQLIniFile;

/**
 * PQL bot
 * 
 * @author Artem Polyvyanyy
 */
public class PQLBot {
	final private static String	version	= "1.0";
	
	public static void main(String[] args) throws InterruptedException, ClassNotFoundException, SQLException {
		System.out.println("===============================================================");
		System.out.println(String.format(" Process Query Language (PQL) bot ver. %s by Artem Polyvyanyy", PQLBot.version));
		System.out.println("===============================================================");
		
		String botName = null;
		
		// read parameters from the ini file
		PQLIniFile iniFile = new PQLIniFile();
		if (!iniFile.load()) { 
			iniFile.create();
			if (!iniFile.load()) {
				System.out.println("ERROR: Cannot load PQL ini file. PQL bot stopped.");
				System.out.println("===============================================================");
				return;
			}
		}
		
		int sleepTime = iniFile.getDefaultBotSleepTime();
		int indexTime = iniFile.getDefaultBotMaxIndexTime();
		
		// build API object
		PQLAPI pqlAPI = new PQLAPI(iniFile.getMySQLURL(), iniFile.getMySQLUser(), iniFile.getMySQLPassword(),
				iniFile.getPostgreSQLHost(), iniFile.getPostgreSQLName(), iniFile.getPostgreSQLUser(), iniFile.getPostgreSQLPassword(),
				iniFile.getLolaPath(), 
				iniFile.getThreeValuedLogicType(),  
				iniFile.getIndexType(),
				iniFile.getLabelManagerType(),
				iniFile.getDefaultLabelSimilarity(),
				iniFile.getIndexedLabelSimilarities(),
				iniFile.getNumberOfQueryThreads());
		
		// read parameters from the CLI
		CommandLineParser parser = new DefaultParser();
		
	    try {
	    	// create Options object
	    	Options options = new Options();
	    	
	    	// create options
	    	Option helpOption	= Option.builder("h").longOpt("help").optionalArg(true).desc("print this message").hasArg(false).build();
	    	Option nameOption	= Option.builder("n").longOpt("name").hasArg().optionalArg(true).desc("name of this bot (maximum 36 characters)").valueSeparator().argName("botName").build();
	    	Option sleepOption	= Option.builder("s").longOpt("sleep").hasArg().optionalArg(true).desc("time to sleep between indexing jobs (in seconds)").valueSeparator().argName("sleepTime").build();
	    	Option indexOption	= Option.builder("i").longOpt("index").hasArg().optionalArg(true).desc("maximal indexing time (in seconds)").valueSeparator().argName("indexTime").build();
	    	
	    	// add options
	    	options.addOption(helpOption);
	    	options.addOption(nameOption);
	    	options.addOption(sleepOption);
	    	options.addOption(indexOption);
	    	
	        // parse the command line arguments
	        CommandLine cmd = parser.parse(options, args);
	        
	        // handle help
	        if(cmd.hasOption("h")) {
	        	HelpFormatter formatter = new HelpFormatter();
	        	formatter.printHelp("PQL",options);
	        	return;
	        }
	        
	        // handle name
	        botName = cmd.getOptionValue("n");
	        if (botName==null) botName = UUID.randomUUID().toString();
	        else if (botName.length()>36) {
	        	System.out.println("ERROR: Bot name exceeds maximum allowed length of 36 characters. PQL bot stopped.");
				System.out.println("===============================================================");
				return;
	        }
	        // handle sleep
	        try { sleepTime = Integer.parseInt(cmd.getOptionValue("s")); } catch (NumberFormatException e) {}
	        
	        // handle index
	        try { indexTime = Integer.parseInt(cmd.getOptionValue("i")); } catch (NumberFormatException e) {}
	    }
	    catch (ParseException exp) {
	        // oops, something went wrong
	        System.err.println("CLI parsing failed. Reason: " + exp.getMessage());
	    }
	    
	    // output final parameters
	    System.out.println(String.format("Bot name:\t\t%s", botName));
	    System.out.println(String.format("Sleep time:\t\t%ss", sleepTime));
	    System.out.println(String.format("Max. index time:\t%ss", indexTime));
	    System.out.println("===============================================================");
	    
	    // create Bot persistence object
	    PQLBotPersistenceLayerMySQL botPersist = null;
		try {
			botPersist = new PQLBotPersistenceLayerMySQL(iniFile.getMySQLURL(), iniFile.getMySQLUser(), iniFile.getMySQLPassword());
		} catch (ClassNotFoundException | SQLException e1) {
			System.out.println("ERROR: Cannot connect to the PQL database. PQL bot stopped.");
			System.out.println("===============================================================");
			return;
		}
		
		// check if bot with the proposed name is already alive
		if (botPersist.isAlive(botName)) {
			System.out.println("ERROR: PQL bot with the proposed name is already alive. PQL bot stopped.");
			System.out.println("===============================================================");
			return;
		}
				
		// Start regular service thread
		class BOTRegularServiceThread extends Thread {
			PQLBotPersistenceLayerMySQL botPersist = null;
			private PQLAPI api = null;
			String botName = null;
			
			BOTRegularServiceThread(PQLBotPersistenceLayerMySQL botPersist, PQLAPI api, String botName) {
				this.botPersist	= botPersist;
				this.api		= api;				
				this.botName	= botName;
			}

		    public void run() {
		    	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		    	
		    	while (true) {
		    		// send alive message
		    		try {
		    			botPersist.alive(this.botName);
		    			System.out.println(String.format("%s> Bot %s sent alive message.", dateFormat.format(new Date()), botName));
					} catch (SQLException e) {
						System.out.println(String.format("%s> Bot %s FAILED to send alive message.", dateFormat.format(new Date()), botName));
					}
		    		
		    		// sleep for .5 hour
		    		try { Thread.sleep(1800*1000); } catch (InterruptedException e) {}
		    		
		    		// cleanup index
		    		try {
		    			this.api.cleanupIndex();
		    			System.out.println(String.format("%s> Bot %s successfully requested index cleanup.", dateFormat.format(new Date()), botName));
					} catch (SQLException e) {
						System.out.println(String.format("%s> Bot %s FAILED to request index cleanup.", dateFormat.format(new Date()), botName));
					}
		    		
		    		// sleep for .5 hour
		    		try { Thread.sleep(1800*1000); } catch (InterruptedException e) {}
		    	}
		    }
		};
		
		class IndexThread extends Thread {
			private PQLAPI api = null;
			private int jobID = 0;
			private String botName = null; 
			private PQLBotPersistenceLayerMySQL botPersist = null;
			
			private boolean result = false;
			private boolean completed = false;
			
			IndexThread(PQLAPI api, int jobID, String botName, PQLBotPersistenceLayerMySQL botPersist) {
				this.api = api;
				this.jobID = jobID;
				this.botName = botName;
				this.botPersist = botPersist;
			}

			@Override
			public void run() {
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				try {
					this.result = this.api.index(jobID);
					
					if (this.result) {
						botPersist.finishIndexJob(jobID, botName);
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
		
		BOTRegularServiceThread botService = new BOTRegularServiceThread(botPersist,pqlAPI,botName);
		botService.start();

	    // TIME TO INDEX ...
	    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        while (true) {
        	try {
        		// get next index job
            	int jobID = botPersist.getNextIndexJobID();
            	if (jobID<=0) System.out.println(String.format("%s> There are no pending jobs.", dateFormat.format(new Date())));
            	else {
            		System.out.println(String.format("%s> Bot %s fetched new job with ID %s.", dateFormat.format(new Date()), botName, jobID));
            		
            		// claim job
            		botPersist.claimIndexJob(jobID, botName);
            		System.out.println(String.format("%s> Bot %s claims job with ID %s.", dateFormat.format(new Date()), botName, jobID));
                	
                	// start job
                	boolean start = botPersist.startIndexJob(jobID, botName);
                	if (!start) System.out.println(String.format("%s> Bot %s FAILED to claim job with ID %s.", dateFormat.format(new Date()), botName, jobID));
                	else {
                		System.out.println(String.format("%s> Bot %s claimed job with ID %s.", dateFormat.format(new Date()), botName, jobID));
                		
                		// check if model can be indexed
                		boolean check = pqlAPI.checkNetSystem(jobID);
                		
                		if (check) {
                			System.out.println(String.format("%s> Bot %s reports that the model for job with ID %s can be indexed.", dateFormat.format(new Date()), botName, jobID));
                			
                			// index
                    		System.out.println(String.format("%s> Bot %s starts working on indexing job with ID %s.", dateFormat.format(new Date()), botName, jobID));
                    		
                    		IndexThread indexThread = new IndexThread(pqlAPI,jobID,botName,botPersist);
                    		indexThread.start();
                			
                    		long startTime = System.currentTimeMillis();
                    		while (indexThread.isAlive() && ((System.currentTimeMillis()-startTime) < (indexTime * 1000L))) {
                    			Thread.sleep(10000L);
                    		}
                    		if (indexThread.isAlive()) {
                    			indexThread.interrupt();
                    			System.out.println(String.format("%s> Bot %s interrupted job with ID %s.", dateFormat.format(new Date()), botName, jobID));
                    		}
                    				                				
                			boolean index = indexThread.hasCompleted() ? indexThread.getResult() : false;
                    		
                			// for debug purposes
                    		//boolean index = pqlAPI.index(jobID);
                        	
                        	// remove incomplete index
                        	if (!index) {
                        		pqlAPI.deleteIndex(jobID);
                        		System.out.println(String.format("%s> Bot %s FAILED to finish job with ID %s.", dateFormat.format(new Date()), botName, jobID));
                        	}	
                		}
                		else {
                			System.out.println(String.format("%s> Bot %s reports that the model for job with ID %s cannot be indexed.", dateFormat.format(new Date()), botName, jobID));
                		}
                	}
            	}
            	
            	System.out.println("---------------------------------------------------------------");
            	
            	// time to sleep before the next job
            	System.out.println(String.format("%s> Bot %s will sleep for %s seconds.", dateFormat.format(new Date()), botName, sleepTime));
            	Thread.sleep(sleepTime*1000);
            	System.out.println(String.format("%s> Bot %s woke up.", dateFormat.format(new Date()), botName));
        	}
        	catch (Exception e) {
        		e.printStackTrace();
        	}
        	
        }
	}

}
