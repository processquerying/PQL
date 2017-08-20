package org.pql.tool;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionGroup;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.pql.api.PQLAPI;
import org.pql.core.PQLTask;
import org.pql.ini.PQLIniFile;
import org.pql.query.PQLQueryResult;

/**
 * Implementation of the PQL Tool command line interface.
 * 
 * @version 1.2.*
 * @since 15-01-2015
 * 
 * @author Artem Polyvyanyy 
 */ 
public final class PQLToolCLI {
	final private static String	version	= "1.2";
	
	private static PQLAPI pqlAPI = null;
		
	public static void main(String[] args) throws ClassNotFoundException, SQLException, InterruptedException {
		// check ini file
		// read parameters from the ini file
		PQLIniFile iniFile = new PQLIniFile();
		if (!iniFile.load()) { 
			iniFile.create();
			if (!iniFile.load()) {
				System.out.println("ERROR: Cannot load PQL ini file.");
				return;
			}
		}
		
		// read parameters from the CLI
		CommandLineParser parser = new DefaultParser();
		
		Options options = null;
	    try {
	    	// create Options object
	    	options = new Options();
	    	
	    	OptionGroup cmdGroup = new OptionGroup();
	    	
	    	// create options
	    	Option helpOption		= Option.builder("h").longOpt("help").numberOfArgs(0).required(false).desc("print this message").hasArg(false).build();
	    	Option versionOption	= Option.builder("v").longOpt("version").numberOfArgs(0).required(false).desc("get version of this tool").hasArg(false).build();
	    	Option resetOption		= Option.builder("r").longOpt("reset").numberOfArgs(0).required(false).desc("reset this PQL instance").hasArg(false).build();
	    	Option storeOption		= Option.builder("s").longOpt("store").numberOfArgs(0).required(false).desc("store model").hasArg(false).build();
	    	Option parseOption		= Option.builder("p").longOpt("parse").numberOfArgs(0).required(false).desc("show PQL query parse tree").hasArg(false).build();
	    	Option indexOption		= Option.builder("i").longOpt("index").numberOfArgs(0).required(false).desc("index model").hasArg(false).build();
	    	Option checkOption		= Option.builder("c").longOpt("check").numberOfArgs(0).required(false).desc("check if model can be indexed").hasArg(false).build();
	    	Option queryOption		= Option.builder("q").longOpt("query").numberOfArgs(0).required(false).desc("execute PQL query").hasArg(false).build();
	    	Option deleteOption		= Option.builder("d").longOpt("delete").numberOfArgs(0).required(false).desc("delete model (and its index)").hasArg(false).build();
	    	Option moveOption		= Option.builder("m").longOpt("move").numberOfArgs(0).required(false).desc("move model(s) to target").hasArg(false).build();
	    	
	    	// TODO: Option retrieveOption	= Option.builder("r").longOpt("retrieve").numberOfArgs(0).required(false).desc("retrieve model").hasArg(false).build();
	    	
	    	Option pnmlOption		= Option.builder("pnml").longOpt("pnmlPath").hasArg(true).optionalArg(false).valueSeparator('=').argName("path").required(false).desc("PNML path").build();
	    	Option pqlOption		= Option.builder("pql").longOpt("pqlPath").hasArg(true).optionalArg(false).valueSeparator('=').argName("path").required(false).desc("PQL path").build();
	    	Option idOption			= Option.builder("id").longOpt("identifier").hasArg(true).optionalArg(false).valueSeparator('=').argName("string").required(false).desc("model identifier").build();
	    	Option folderOption			= Option.builder("folder").longOpt("folderID").hasArg(true).optionalArg(false).valueSeparator('=').argName("string").required(false).desc("folder identifier").build();
	    	Option targetOption			= Option.builder("target").longOpt("targetFolder").hasArg(true).optionalArg(false).valueSeparator('=').argName("string").required(false).desc("target folder").build();
	    	
	    	// add options
	    	cmdGroup.addOption(helpOption);
	    	cmdGroup.addOption(versionOption);
	    	cmdGroup.addOption(resetOption);
	    	cmdGroup.addOption(storeOption);
	    	cmdGroup.addOption(parseOption);
	    	cmdGroup.addOption(indexOption);
	    	cmdGroup.addOption(deleteOption);
	    	cmdGroup.addOption(checkOption);
	    	cmdGroup.addOption(queryOption);
	    	cmdGroup.addOption(moveOption);
	    	
	    	// cmdGroup.addOption(retrieveOption);
	    	
	    	cmdGroup.setRequired(true);
	    	
	    	options.addOptionGroup(cmdGroup);
	    	
	    	options.addOption(pnmlOption);
	    	options.addOption(pqlOption);
	    	options.addOption(idOption);
	    	options.addOption(folderOption);
	    	options.addOption(targetOption);
	    	
	        // parse the command line arguments
	        CommandLine cmd = parser.parse(options, args);
	        
	        PQLToolCLI.pqlAPI = new PQLAPI(iniFile.getMySQLURL(), iniFile.getMySQLUser(), iniFile.getMySQLPassword(),
	        									iniFile.getPostgreSQLHost(), iniFile.getPostgreSQLName(), iniFile.getPostgreSQLUser(), iniFile.getPostgreSQLPassword(),
	        									iniFile.getLoLA2Path(),
	        									iniFile.getLabelSimilaritySeacrhConfiguration(),  
	        									iniFile.getIndexType(),
	        									iniFile.getLabelManagerType(),
	        									iniFile.getDefaultLabelSimilarityThreshold(),
	        									iniFile.getIndexedLabelSimilarityThresholds(),
	        									iniFile.getNumberOfQueryThreads(),
	        									iniFile.getDefaultBotMaxIndexTime(),
	        									iniFile.getDefaultBotSleepTime());
	        
	        // handle help
	        if(cmd.hasOption("h") || cmd.getOptions().length==0) {
	        	showHelp(options);
	        	return;
	        }
	        
	        // handle version
	        if(cmd.hasOption("v")) {
	        	System.out.println(PQLToolCLI.version);
	        	return;
	        }
	        
	        // handle reset
	        if(cmd.hasOption("r")) {
	        	System.out.print("Do you want to reset the PQL index (Y/N): ");
	        	
	            @SuppressWarnings("resource")
				String reset = new Scanner(System.in).next().trim().toLowerCase();
	            
	            if (reset.equals("y")) {
	            	PQLToolCLI.pqlAPI.reset();
					System.out.println("The index was reset!");
	            }
	            else System.out.println("The index was not reset!");
	        }
	        
	        // handle store
	        if (cmd.hasOption("s")) {
	        	if (cmd.hasOption("pnml")) {
	        		String pnmlPath = cmd.getOptionValue("pnml");
	        		File pnmlFile = new File(pnmlPath);
	        		
	        		if (pnmlFile.isFile()) {
	        			if (cmd.hasOption("id")) {
	        				if (cmd.hasOption("target")) {
	        					PQLToolCLI.store(pnmlFile,cmd.getOptionValue("id"),cmd.getOptionValue("target"));
	        				}
	        			}
	        			else throw new ParseException("-s and -pnml option requires -id option");
	        		}
	        		else if (pnmlFile.isDirectory()) {
	        			if (cmd.hasOption("target")) {
	        				PQLToolCLI.storeDirectory(pnmlFile,cmd.getOptionValue("target"));
	        			}
	        		}
	        	}
	        	else throw new ParseException("-s option requires -pnml option");
	        	
	        	return;
	        }
	        
	        /* -----------------------
	    	 * LOCATIONS CAPSTONE EDIT
	    	 * -----------------------
	    	*/
	        
	        // handle move
	        if (cmd.hasOption("m")) {
	        	if (cmd.hasOption("id")) {
	        		String id_name = cmd.getOptionValue("id");
	        		if (cmd.hasOption("target")) {
	        			String targetFolder = cmd.getOptionValue("target");
	        			PQLToolCLI.moveModel(id_name,targetFolder);
	        		}
	        		else throw new ParseException("-id option requires -target option");
	        	}
	        	else if (cmd.hasOption("folder")) {
	        		String movingFolder = cmd.getOptionValue("folder");
	        		if (cmd.hasOption("target")) {
	        			String targetFolder = cmd.getOptionValue("target");
	        			PQLToolCLI.moveFolder(movingFolder,targetFolder);
	        		}
	        		else throw new ParseException("-folder option requires -target option");
	        	}
	        	else throw new ParseException("-m option requires -id or -folder option");
	        }
	        
	        // handle parse
	        if (cmd.hasOption("p")) {
	        	if (cmd.hasOption("pql")) {
	        		String pqlPath = cmd.getOptionValue("pql");
	        		
	        		try {
	        			PQLToolCLI.pqlAPI.parsePQLQuery(pqlPath);
	        		}
	        		catch (Exception e) {
	        			System.out.println("invalid -pql argument");
	        		}
	        	}
	        	else throw new ParseException("-p option requires -pql option");
	        	
	        	return;
	        }
	        
	        // handle index
	        if (cmd.hasOption("i")) {
	        	if (cmd.hasOption("id")) {
	        		String id = cmd.getOptionValue("id");
	        		
	        		int internalID = PQLToolCLI.pqlAPI.getInternalID(id);
	        		
	        		if (internalID>0) {
	        			pqlAPI.index(internalID);
	        			System.out.println("finished indexing model with ID "+internalID);
	        		}
	        		else
	        			System.out.println("specified identifier is not associated with any model");
	        	}
	        	else throw new ParseException("-i option requires -id option");
	        	
	        	return;
	        }
	        
	        // handle delete
	        if (cmd.hasOption("d")) {
	        	if (cmd.hasOption("id")) {
	        		String id = cmd.getOptionValue("id");
	        		
	        		int internalID = PQLToolCLI.pqlAPI.getInternalID(id);
	        		
	        		if (internalID>0) {
	        			pqlAPI.deleteModel(internalID);
	        			System.out.println("model with ID "+internalID+" (and its index) were deleted");
	        		}
	        		else
	        			System.out.println("specified identifier is not associated with any model");
	        	}
	        	else throw new ParseException("-d option requires -id option");
	        	
	        	return;
	        }
	        
	        // handle check
	        if (cmd.hasOption("c")) {
	        	if (cmd.hasOption("id")) {
	        		String id = cmd.getOptionValue("id");
	        		
	        		int internalID = PQLToolCLI.pqlAPI.getInternalID(id);
	        		
	        		if (internalID>0) {
	        			boolean result = PQLToolCLI.pqlAPI.checkModel(internalID);
	        			if (result)
	        				System.out.println("model can be indexed");
	        			else
	        				System.out.println("model cannot be indexed");
	        		}
	        		else
	        			System.out.println("specified identifier is not associated with any model");
	        	}
	        	else throw new ParseException("-c option requires -id option");
	        	
	        	return;
	        }
	        
	        // handle query
	        if (cmd.hasOption("q")) {
	        	if (cmd.hasOption("pql")) {
	        		String pqlPath = cmd.getOptionValue("pql");
	        		File file = new File(pqlPath);
	        		
	        		String pqlQuery = "";
	        		if (file.isFile() && file.canRead()) {
	        			pqlQuery = new String(Files.readAllBytes(Paths.get(pqlPath)));
	        		}
	        		else {
	        			System.out.println("invalid -pql argument");
	        			return;
	        		}
	        		
	        		PQLQueryResult queryResult = null;
	        		if (cmd.hasOption("id")) {
	        			String id = cmd.getOptionValue("id");
	        			Set<String> ids = new HashSet<String>();
	        			ids.add(id);
	        			queryResult = PQLToolCLI.pqlAPI.query(pqlQuery,ids);
	        		}
	        		else {
	        			queryResult = PQLToolCLI.pqlAPI.query(pqlQuery);
	        		}
	        		
	        		if (queryResult.getNumberOfParseErrors()==0) {
	        			System.out.println("------------------------------------------------------------");
	        			System.out.println("PQL query:\t"+ pqlQuery);
	        			if (!queryResult.getVariables().isEmpty()) 
	        				System.out.println("------------------------------------------------------------");
		        		for (Map.Entry<String,Set<PQLTask>> var: queryResult.getVariables().entrySet()) {
		        			System.out.println("Variable:\t"+ var.getKey() + " = " + var.getValue());
		        		}
		        		System.out.println("------------------------------------------------------------");
		        		System.out.println("Attributes:\t"+ queryResult.getAttributes());
		        		System.out.println("------------------------------------------------------------");
		        		System.out.println("Locations:\t"+ queryResult.getLocations());
		        		if (!queryResult.getTaskMap().isEmpty()) 
	        				System.out.println("------------------------------------------------------------");
		        		for (Map.Entry<PQLTask,PQLTask> map: queryResult.getTaskMap().entrySet()) {
		        			System.out.println("Task:\t"+ map.getKey() + " -> " + map.getValue());
		        		}
		        		System.out.println("------------------------------------------------------------");
		        		System.out.println("Result:\t\t"+ queryResult.getSearchResults());
		        		System.out.println("------------------------------------------------------------");
	        		}
	        		else {
	        			System.out.println("------------------------------------------------------------");
	        			for (String msg : queryResult.getParseErrorMessages()) {
	        				System.out.println("Parse error:\t"+msg);
	        				System.out.println("------------------------------------------------------------");
	        			}
	        		}
	        	}
	        	else throw new ParseException("-q option requires -pql option");
	        	
	        	return;
	        }
	        
	    }
	    catch (ParseException | IOException exp) {
	        // oops, something went wrong
	        System.err.println("CLI parsing failed. Reason: " + exp.getMessage() + "\n");
	        showHelp(options);
	        return;
	    }
	}
	
	/* -----------------------
	 * LOCATIONS CAPSTONE EDIT
	 * -----------------------
	*/
	
	private static void moveFolder(String movingFolder, String targetFolder) throws SQLException {
		if (movingFolder==null || targetFolder==null) {
			System.out.println("Cannot move folder.");
			return;
		}
		
		int result = PQLToolCLI.pqlAPI.moveFolder(movingFolder, targetFolder);
		
		if (result>0) 
			System.out.println(String.format("Folder %s moved to %s.", movingFolder, targetFolder));
		else
			System.out.println(String.format("Folder %s cannot be moved to %s.", movingFolder, targetFolder));
	}

	private static void moveModel(String id_name, String targetFolder) throws SQLException {
		if (id_name==null || targetFolder==null) {
			System.out.println("Cannot move model.");
			return;
		}
		
		int result = PQLToolCLI.pqlAPI.moveModel(id_name, targetFolder);
		
		if (result>0) 
			System.out.println(String.format("Model %s moved to %s.", id_name, targetFolder));
		else
			System.out.println(String.format("Model %s cannot be moved to %s.", id_name, targetFolder));
	}

	private static void store(File pnmlFile, String identifier, String target) throws SQLException {
		if (pnmlFile==null || identifier==null || target==null) {
			System.out.println("Cannot store model.");
			return;
		}
		
		int result = PQLToolCLI.pqlAPI.storeModel(pnmlFile, identifier, target);
		
		if (result>0) 
			System.out.println(String.format("Model %s stored under unique identifier %s in folder %s.", pnmlFile.getAbsolutePath(), identifier, target));
		else
			System.out.println(String.format("Model %s cannot be stored under identifier %s in folder %s.", pnmlFile.getAbsolutePath(), identifier, target));
	}

	private static void storeDirectory(File pnmlDir, String target) throws SQLException {
		for (File file : pnmlDir.listFiles()) {
			if (!file.isFile()) continue;
			if (!file.getName().endsWith(".pnml")) continue;
			
			PQLToolCLI.store(file, file.getName(), target);
		}
	}

	private static void showHelp(Options options) {
		HelpFormatter formatter = new HelpFormatter();
    	formatter.printHelp(80, "java -jar PQL.jar <options>", 
    							String.format("===============================================================================\n"+
    										   " Process Query Language (PQL) Tool ver. %s by Artem Polyvyanyy\n"+
    										  "===============================================================================\n", PQLToolCLI.version), 
    							options, 
    							"===============================================================================\n");
	}
}
