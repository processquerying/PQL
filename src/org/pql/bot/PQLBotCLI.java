package org.pql.bot;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.UUID;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.jbpt.persist.MySQLConnection;
import org.pql.core.PQLBasicPredicatesMC;
import org.pql.index.IndexType;
import org.pql.index.PQLIndexMySQL;
import org.pql.ini.PQLIniFile;
import org.pql.label.ILabelManager;
import org.pql.label.LabelManagerLevenshtein;
import org.pql.label.LabelManagerLuceneVSM;
import org.pql.label.LabelManagerThemisVSM;
import org.pql.mc.LoLA2ModelChecker;

/**
 * Implementation of the PQL Bot command line interface.
 * 
 * @version 1.3.*
 * @since 15-01-2015
 * 
 * @author Artem Polyvyanyy
 */
public class PQLBotCLI {
	final private static String	version	= "1.3";
	
	public static void main(String[] args) throws AbstractPQLBot.NameInUseException, InterruptedException, ClassNotFoundException, SQLException, IOException {
		// read parameters from the CLI
		CommandLineParser parser = new DefaultParser();
		
		Connection connection = null;
		
		PQLIniFile	iniFile = null;
		String 		botName = null;
		int 		sleepTime = 0;
		int 		indexTime = 0;
		
	    try {
	    	// create Options object
	    	Options options = new Options();
	    	
	    	// create options
	    	Option helpOption		= Option.builder("h").longOpt("help").optionalArg(true).desc("print this message").hasArg(false).build();
	    	Option versionOption	= Option.builder("v").longOpt("version").optionalArg(true).desc("get version of this tool").hasArg(false).build();
	    	Option nameOption		= Option.builder("n").longOpt("name").hasArg().optionalArg(true).desc("name of this bot (maximum 36 characters)").valueSeparator().argName("string").build();
	    	Option sleepOption		= Option.builder("s").longOpt("sleep").hasArg().optionalArg(true).desc("time to sleep between indexing jobs (in seconds)").valueSeparator().argName("number").build();
	    	Option indexOption		= Option.builder("i").longOpt("index").hasArg().optionalArg(true).desc("maximal indexing time (in seconds)").valueSeparator().argName("number").build();
	    	
	    	// add options
	    	options.addOption(helpOption);
	    	options.addOption(versionOption);
	    	options.addOption(nameOption);
	    	options.addOption(sleepOption);
	    	options.addOption(indexOption);
	    	
	        // parse the command line arguments
	        CommandLine cmd = parser.parse(options, args);
	        
	        // handle version
	        if(cmd.hasOption("v")) {
	        	System.out.println(PQLBotCLI.version);
	        	return;
	        }
	        
	        System.out.println("===============================================================================");
			System.out.println(String.format(" Process Query Language (PQL) Bot ver. %s by Artem Polyvyanyy", PQLBotCLI.version));
			System.out.println("===============================================================================");
			
			// handle help
	        if(cmd.hasOption("h")) {
	        	HelpFormatter formatter = new HelpFormatter();
	        	formatter.printHelp("PQL",options);
	        	System.out.println("===============================================================================");
	        	return;
	        }
			
			// read parameters from the ini file
			iniFile = new PQLIniFile();
			if (!iniFile.load()) { 
				iniFile.create();
				if (!iniFile.load()) {
					System.out.println("ERROR: Cannot load PQL ini file. PQL Bot stopped.");
					System.out.println("===============================================================================");
					return;
				}
			}
			
			connection = (new MySQLConnection(iniFile.getMySQLURL(),iniFile.getMySQLUser(),iniFile.getMySQLPassword())).getConnection();
			
			sleepTime = iniFile.getDefaultBotSleepTime();
			indexTime = iniFile.getDefaultBotMaxIndexTime();
	        
	        // handle name
	        botName = cmd.getOptionValue("n");
	        if (botName==null) botName = UUID.randomUUID().toString();
	        else if (botName.length()>36) {
	        	System.out.println("ERROR: Bot name exceeds maximum allowed length of 36 characters. PQL Bot stopped.");
				System.out.println("===============================================================================");
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
	    System.out.println("===============================================================================");
	    
	    // TODO: develop a factory to generate objects from iniFile (use here and for API)
	    
	    ILabelManager labelMngr = null;
	    
	    switch (iniFile.getLabelManagerType()) {
			case THEMIS_VSM:
				labelMngr = new LabelManagerThemisVSM(connection,
						iniFile.getPostgreSQLHost(),iniFile.getPostgreSQLName(),iniFile.getPostgreSQLUser(),iniFile.getPostgreSQLPassword(),
						iniFile.getDefaultLabelSimilarityThreshold(),iniFile.getIndexedLabelSimilarityThresholds());
				break;
			case LUCENE:
				labelMngr = new LabelManagerLuceneVSM(connection,
						iniFile.getDefaultLabelSimilarityThreshold(),iniFile.getIndexedLabelSimilarityThresholds(),iniFile.getLabelSimilaritySeacrhConfiguration());
				break;
			default:
				labelMngr = new LabelManagerLevenshtein(connection,
						iniFile.getDefaultLabelSimilarityThreshold(),iniFile.getIndexedLabelSimilarityThresholds());
				break;
	    }
	    
	    LoLA2ModelChecker		mc	= new LoLA2ModelChecker(iniFile.getLoLA2Path());

		PQLBasicPredicatesMC    bp	= new PQLBasicPredicatesMC(mc);
		PQLIndexMySQL			index = new PQLIndexMySQL(connection,bp,labelMngr,mc,
				iniFile.getDefaultLabelSimilarityThreshold(),iniFile.getIndexedLabelSimilarityThresholds(),
				iniFile.getIndexType(), iniFile.getDefaultBotMaxIndexTime(), iniFile.getDefaultBotSleepTime());
	    
	    PQLBot bot = new PQLBot(connection, 
							botName, index, mc, IndexType.PREDICATES, indexTime, sleepTime);
	    bot.run();
	   
	    
	}

}
