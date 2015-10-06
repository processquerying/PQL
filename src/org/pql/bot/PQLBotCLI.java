package org.pql.bot;

import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.pql.core.PQLBasicPredicatesMC;
import org.pql.index.IndexType;
import org.pql.index.PQLIndexMySQL;
import org.pql.ini.PQLIniFile;
import org.pql.label.ILabelManager;
import org.pql.label.LabelManagerLevenshtein;
import org.pql.label.LabelManagerLuceneVSM;
import org.pql.label.LabelManagerThemisVSM;
import org.pql.logic.IThreeValuedLogic;
import org.pql.logic.KleeneLogic;
import org.pql.mc.LoLA2ModelChecker;

/**
 * Implementation of the PQL Bot comman line interface.
 * 
 * @author Artem Polyvyanyy
 */
public class PQLBotCLI {
	final private static String	version	= "1.0";
	
	public static void main(String[] args) throws InterruptedException, ClassNotFoundException, SQLException, IOException {
		System.out.println("===============================================================");
		System.out.println(String.format(" Process Query Language (PQL) Bot ver. %s by Artem Polyvyanyy", PQLBotCLI.version));
		System.out.println("===============================================================");
		
		String botName = null;
		
		// read parameters from the ini file
		PQLIniFile iniFile = new PQLIniFile();
		if (!iniFile.load()) { 
			iniFile.create();
			if (!iniFile.load()) {
				System.out.println("ERROR: Cannot load PQL ini file. PQL Bot stopped.");
				System.out.println("===============================================================");
				return;
			}
		}
		
		int sleepTime = iniFile.getDefaultBotSleepTime();
		int indexTime = iniFile.getDefaultBotMaxIndexTime();
				
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
	        	System.out.println("ERROR: Bot name exceeds maximum allowed length of 36 characters. PQL Bot stopped.");
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
	    
	    // TODO: develop a factory to generate objects from iniFile (use here and for API)
	    
	    IThreeValuedLogic		logic = null;
	    
	    switch (iniFile.getThreeValuedLogicType()) {
	    	case KLEENE:
		    default:
		    	logic = new KleeneLogic();
	    }
	    
	    ILabelManager labelMngr = null;
	    
	    switch (iniFile.getLabelManagerType()) {
			case THEMIS_VSM:
				labelMngr = new LabelManagerThemisVSM(iniFile.getMySQLURL(),iniFile.getMySQLUser(),iniFile.getMySQLPassword(),
						iniFile.getPostgreSQLHost(),iniFile.getPostgreSQLName(),iniFile.getPostgreSQLUser(),iniFile.getPostgreSQLPassword(),
						iniFile.getDefaultLabelSimilarityThreshold(),iniFile.getIndexedLabelSimilarityThresholds());
				break;
			case LUCENE:
				labelMngr = new LabelManagerLuceneVSM(iniFile.getMySQLURL(),iniFile.getMySQLUser(),iniFile.getMySQLPassword(),
						iniFile.getDefaultLabelSimilarityThreshold(),iniFile.getIndexedLabelSimilarityThresholds(),iniFile.getLabelSimilaritySeacrhConfiguration());
				break;
			default:
				labelMngr = new LabelManagerLevenshtein(iniFile.getMySQLURL(),iniFile.getMySQLUser(),iniFile.getMySQLPassword(),
						iniFile.getDefaultLabelSimilarityThreshold(),iniFile.getIndexedLabelSimilarityThresholds());
				break;
	    }
	    
	    LoLA2ModelChecker		mc	= new LoLA2ModelChecker(iniFile.getLoLA2Path());
		PQLBasicPredicatesMC    bp	= new PQLBasicPredicatesMC(mc,logic);
		PQLIndexMySQL			index = new PQLIndexMySQL(iniFile.getMySQLURL(),iniFile.getMySQLUser(),iniFile.getMySQLPassword(),bp,
				labelMngr,mc,logic,iniFile.getDefaultLabelSimilarityThreshold(),iniFile.getIndexedLabelSimilarityThresholds(),
				iniFile.getIndexType(), iniFile.getDefaultBotMaxIndexTime(), iniFile.getDefaultBotSleepTime());
		
	    
	    PQLBot bot = new PQLBot(iniFile.getMySQLURL(), iniFile.getMySQLUser(), iniFile.getMySQLPassword(), 
	    						botName, index, mc, IndexType.PREDICATES, indexTime, sleepTime, true);
	    bot.run();
	    
	}

}
