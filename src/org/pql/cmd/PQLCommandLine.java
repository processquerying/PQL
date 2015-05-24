package org.pql.cmd;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import org.ini4j.Ini;
import org.jbpt.petri.NetSystem;
import org.jbpt.petri.Place;
import org.jbpt.petri.Transition;
import org.jbpt.petri.io.PNMLSerializer;
import org.jbpt.petri.persist.PetriNetMySQL;
import org.pql.api.PQLAPI;
import org.pql.core.IPQLBasicPredicatesOnTasks;
import org.pql.core.PQLBasicPredicatesMC;
import org.pql.core.PQLTask;
import org.pql.index.PQLIndexMySQL;
import org.pql.label.ILabelManager;
import org.pql.label.LabelManagerLevenshtein;
import org.pql.label.LabelManagerVSM;
import org.pql.logic.IThreeValuedLogic;
import org.pql.logic.KleeneLogic;
import org.pql.logic.ThreeValuedLogicValue;
import org.pql.mc.LoLAModelChecker;
import org.pql.query.IPQLQuery;

/**
 * @author Artem Polyvyanyy
 * 
 * TODO: when indexing check identifier first 
 */ 
public final class PQLCommandLine {
	final private static String		iniFile	= "PQL.ini";
	
	private static String mysqlURL		 = null;
	private static String mysqlUser		 = null;
	private static String mysqlPassword	 = null;
	private static String pgHost		 = null;
	private static String pgName		 = null;
	private static String pgUser		 = null;
	private static String pgPassword	 = null;
	private static String lolaPath 		 = null;	
	private static String labelSimSearch = null;
	
	private static double 		defaultLabelSimilarity		= 1.0;
	private static Set<Double>	indexedLabelSimilarities	= null;
	
	private static NetSystem		sys			= null;
	private static String			msg			= null;
	private static PetriNetMySQL	pnMySQL		= null;
	private static PQLIndexMySQL	pqlMySQL	= null;
	
	private static IThreeValuedLogic logic		= null;	// three-valued logic to use
	private static ILabelManager	 labelMngr	= null;
	
	private static LoLAModelChecker lolaModelChecker = null;
	private static PQLAPI 			pqlAPI			 = null;
	
	private static IPQLBasicPredicatesOnTasks basicPredicates = null;
	
	/**
	 * Main interface to the command line tool.
	 * 
	 * @param args Command line arguments. 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// check ini file
		if (!PQLCommandLine.checkFile(PQLCommandLine.iniFile)) {
			System.out.println(PQLCommandLine.msg);
			PQLCommandLine.createINIFile();
			System.out.println(String.format("Fresh %s file created.", PQLCommandLine.iniFile));
			return;
		}
		
		// load ini file
		if (!PQLCommandLine.loadINIFile()) {
			System.out.println(PQLCommandLine.msg);
			return;
		}
		
		// initialise objects
		PQLCommandLine.logic			 = new KleeneLogic();
		PQLCommandLine.lolaModelChecker	 = new LoLAModelChecker(PQLCommandLine.lolaPath);
		PQLCommandLine.pnMySQL			 = new PetriNetMySQL(mysqlURL,mysqlUser,mysqlPassword);
		
		if (PQLCommandLine.labelSimSearch.trim().toLowerCase().equals("levenshtein"))
			PQLCommandLine.labelMngr		= new LabelManagerLevenshtein(mysqlURL,mysqlUser,mysqlPassword,defaultLabelSimilarity,indexedLabelSimilarities);
		else
			PQLCommandLine.labelMngr		= new LabelManagerVSM(mysqlURL,mysqlUser,mysqlPassword,pgHost,pgName,pgUser,pgPassword,defaultLabelSimilarity,indexedLabelSimilarities);
		
		PQLCommandLine.basicPredicates 	= new PQLBasicPredicatesMC(PQLCommandLine.lolaModelChecker,PQLCommandLine.logic);
		
		PQLCommandLine.pqlMySQL		 	 = new PQLIndexMySQL(mysqlURL,mysqlUser,mysqlPassword,PQLCommandLine.basicPredicates,PQLCommandLine.logic,defaultLabelSimilarity,indexedLabelSimilarities);
		
		PQLCommandLine.pqlAPI			 = new PQLAPI(mysqlURL,mysqlUser,mysqlPassword,lolaModelChecker,logic,pnMySQL,pqlMySQL,labelMngr);
		
		
		String identifier = null;
		switch (args.length) {
			case 1:	// one argument
				switch (args[0]) {
					case "-?":
					case "--help":
						PQLCommandLine.showHelp();
						break;
					case "-reset":
						pqlAPI.reset();
						System.out.println("The PQL system has been reset!");
						break;
					default:
						PQLCommandLine.showError();
				}
				break;
			case 2: // two arguments
				switch (args[0]) {
					case "-c": // check if a given net system can be indexed
						// check PNML file
						String pnmlFile = args[1];
						if (!PQLCommandLine.checkFile(pnmlFile)) {
							System.out.println(PQLCommandLine.msg);
							return;
						}
						
						// load PNML file
						PQLCommandLine.loadPNMLFile(pnmlFile);	// TODO handle exceptions
						
						// check net system
						PQLCommandLine.checkNetSystem();
						System.out.println(PQLCommandLine.msg);
						break;
					case "-i":
						String pnmlDirectory = args[1];
						if (!PQLCommandLine.checkDirectory(pnmlDirectory)) {
							System.out.println(PQLCommandLine.msg);
							return;
						}
						
						PQLCommandLine.indexNetSystems(pnmlDirectory);
						break;
					case "-q":
						String q = args[1];
						
						pqlAPI.prepareQuery(q);
						
						if (pqlAPI.getLastNumberOfParseErrors()==0) {
							System.out.println(pqlAPI.checkLastQuery());
						} 
						else {
							for (String error: pqlAPI.getLastParseErrorMessages())
								System.out.println(error);	
						}
						break;
					case "-v": // visualize query parse tree
						String query = args[1];
						pqlAPI.visualiseQuery(query);
						break;
					case "-d": // delete index
						identifier = args[1];
						if (pqlAPI.deleteNetSystem(identifier)==0)
							System.out.println(String.format("Net system with identifier '%s' was not deleted!", identifier));
						else
							System.out.println(String.format("Net system with identifier '%s' and its index were deleted!", identifier));
						break;
					default:
						PQLCommandLine.showError();
				}
				break;
			case 3: // three arguments
				switch (args[0]) {
					case "-i": // index net system
						// check PNML file
						String pnmlFile	= args[1];
						if (!PQLCommandLine.checkFile(pnmlFile)) {
							System.out.println(PQLCommandLine.msg);
							return;
						}
						
						identifier = args[2];
						PQLCommandLine.indexNetSystem(pnmlFile,identifier);
						break;
					case "-q":
						identifier = args[1];
						String query = args[2];
						ThreeValuedLogicValue result = pqlAPI.checkQuery(query,identifier);
						
						if (pqlAPI.getLastNumberOfParseErrors()==0) {
							IPQLQuery q = pqlAPI.getLastQuery();
							PQLCommandLine.reportQueryResult(q,result);
						}
						else {
							for (String error: pqlAPI.getLastParseErrorMessages())
								System.out.println(error);	
						}
						break;
					default:
						PQLCommandLine.showError();
				}
				break;
			default:
				PQLCommandLine.showError();
		}
	}
	
	private static void reportQueryResult(IPQLQuery q, ThreeValuedLogicValue result) {
		System.out.println("QUERY RESULT: "+result);
		System.out.println("---------------------------------------");
		System.out.println("TASKS:");
		for (Map.Entry<PQLTask,PQLTask> entry : q.getTaskMap().entrySet()) {
			System.out.print(String.format("%s[%.2f] = %s[%.2f]%s", entry.getKey().getLabel(),entry.getKey().getSimilarity(),
					entry.getValue().getLabel(),entry.getValue().getSimilarity(),entry.getValue().getSimilarLabels()));
			
			System.out.println();
		}
		System.out.println("---------------------------------------");
		System.out.println("VARIABLES: " + q.getVariables());
		System.out.println("---------------------------------------");
		System.out.println("ATTRIBUTES: " + q.getAttributes());
		System.out.println("---------------------------------------");
		System.out.println("LOCATIONS: " + q.getLocations());
		System.out.println("---------------------------------------");
	}

	private static boolean checkNetSystem() {
		boolean result = PQLCommandLine.pqlAPI.checkNetSystem(PQLCommandLine.sys);
		
		if (result)
			PQLCommandLine.msg = "Given system can be indexed, i.e., it is a sound workflow net.";
		else
			PQLCommandLine.msg = "Given system cannot be indexed, i.e., it is not a sound workflow net.";
		
		return result;
	}
	
	private static int indexNetSystem(String pnmlFile, String identifier) throws SQLException {
		// load PNML file
		// TODO handle exceptions
		PQLCommandLine.loadPNMLFile(pnmlFile);		
		PQLCommandLine.sys.loadNaturalMarking();
		
		// check net system
		if (!PQLCommandLine.checkNetSystem()) {
			System.out.println(pnmlFile + ": " + PQLCommandLine.msg);
			return -1;
		}
		
		System.out.print(String.format("System with identifier '%s' ", identifier));
		long start = System.nanoTime();
		int netID = PQLCommandLine.pqlAPI.indexNetSystem(PQLCommandLine.sys,identifier,PQLCommandLine.indexedLabelSimilarities);
		long end = System.nanoTime();
		
		if (netID>0)
			System.out.println(String.format("indexed successfully in %sns.", end-start));
		else if (netID==0)
			System.out.println(String.format("cannot be indexed. The Identifier is already associated with some other system.", identifier)); 
		else 
			System.out.println(String.format("cannot be indexed. Cannot connect to database. Check %s file.", PQLCommandLine.iniFile));
		
		return netID;
	}
	
	private static void indexNetSystems(String pnmlDirectory) throws SQLException {
		File dir = new File(pnmlDirectory);
		
		for (File file : dir.listFiles()) {
			if (!file.isFile()) continue;
			if (!file.getName().endsWith(".pnml")) continue;
			
			PQLCommandLine.indexNetSystem(file.getAbsolutePath(), file.getName());
		}
	}

	private static boolean checkFile(String fileName) {
		try {
			File file = new File(fileName);
			
			if (file.exists()) {
				if (file.isFile()) {
					if (file.canRead()) {
				        return true;
					}
					else {
						PQLCommandLine.msg = String.format("%s file exists, but cannot be read.", fileName);
						return false;
					}
				}
				else {
					PQLCommandLine.msg = String.format("%s exists, but is not a normal file.", fileName);
					return false;
				}
			}
			else {
				PQLCommandLine.msg = String.format("%s does not exist.", fileName);
				return false;
			}
		} catch (Exception e) {
			PQLCommandLine.msg = String.format("An unhandled exception was thrown: %s", e.getStackTrace().toString());
			return false;
		}
	}
	
	private static boolean checkDirectory(String dirName) {
		try {
			File dir = new File(dirName);
			
			if (dir.exists()) {
				if (dir.isDirectory()) {
					if (dir.canRead()) {
				        return true;
					}
					else {
						PQLCommandLine.msg = String.format("%s directory exists, but cannot be read.", dirName);
						return false;
					}
				}
				else {
					PQLCommandLine.msg = String.format("%s exists, but is not a normal directory.", dirName);
					return false;
				}
			}
			else {
				PQLCommandLine.msg = String.format("%s does not exist.", dirName);
				return false;
			}
		} catch (Exception e) {
			PQLCommandLine.msg = String.format("An unhandled exception was thrown: %s", e.getStackTrace().toString());
			return false;
		}
	}

	private static boolean loadINIFile() {
		try {
			File file = new File(PQLCommandLine.iniFile);
			
			// read configuration from file
			Ini ini = new Ini();
			ini.load(new FileReader(file));
			
			// load lola section
	        Ini.Section section = ini.get("lola");
	        PQLCommandLine.lolaPath = section.get("lolaPath");
	        
	        // load pql section
	        section = ini.get("pql");
	        
	        PQLCommandLine.labelSimSearch = section.get("labelSimilaritySearch");
	        
	        PQLCommandLine.defaultLabelSimilarity = Double.parseDouble(section.get("defaultLabelSimilarity"));
	        String similarities = section.get("indexedLabelSimilarities");
	        StringTokenizer st = new StringTokenizer(similarities,",");
	        
	        PQLCommandLine.indexedLabelSimilarities = new HashSet<Double>();
	        while (st.hasMoreTokens()) 
	        	PQLCommandLine.indexedLabelSimilarities.add(Double.parseDouble(st.nextToken()));
	        
	        PQLCommandLine.indexedLabelSimilarities.add(1.0);
	        PQLCommandLine.indexedLabelSimilarities.add(PQLCommandLine.defaultLabelSimilarity);
	        
	        // load postgresql section
	        section = ini.get("postgresql");
	        PQLCommandLine.pgHost = section.get("host");
	        PQLCommandLine.pgName = section.get("name");
	        PQLCommandLine.pgUser = section.get("user");
	        PQLCommandLine.pgPassword = section.get("password");
	        
	        // load mysql section
	        section = ini.get("mysql");
	        PQLCommandLine.mysqlURL = section.get("url");
	        PQLCommandLine.mysqlUser = section.get("user");
	        PQLCommandLine.mysqlPassword = section.get("password");
	        
	        return true;
		} catch (IOException e) {
			PQLCommandLine.msg = String.format("Cannot read %s.", PQLCommandLine.iniFile);
			return false;
		} catch (NumberFormatException e) {
			PQLCommandLine.msg = String.format("Wrong number format for 'defaultLabelSimilarity' or 'indexedLabelSimilarities'.", e.getStackTrace().toString());
			return false;
		} catch (Exception e) {
			PQLCommandLine.msg = String.format("An unhandled exception was thrown: %s", e.getStackTrace().toString());
			return false;
		}
	}

	/**
	 * Create a configuration file, if one does not exist.
	 */
	private static void createINIFile() {
		BufferedWriter out;
		try {
			out = new BufferedWriter(new FileWriter(PQLCommandLine.iniFile));
			out.write("[mysql]\n");
			out.write("url = jdbc:mysql://localhost:3306/mysql\n");
			out.write("user = user\n");
			out.write("password = password\n");
			out.write("\n");
			out.write("[postgresql]\n");
			out.write("host = localhost\n");
			out.write("name = themis\n");
			out.write("user = user\n");
			out.write("password = password\n");
			out.write("\n");
			out.write("[lola]\n");
			out.write("lolaPath = .\\\\lola2\\\\win\\\\lola.exe\n");
			out.write("\n");
			out.write("[pql]\n");
			out.write("labelSimilaritySearch = levenshtein\n");
			out.write("defaultLabelSimilarity = 0.75\n");
			out.write("indexedLabelSimilarities = 0.5,0.75,1.0\n");
			out.close();
			PQLCommandLine.msg += String.format("A sample %s file created.", PQLCommandLine.iniFile);
		} catch (Exception e) {
			PQLCommandLine.msg += String.format("An exception was thrown when creating a sample %s file: %s", PQLCommandLine.iniFile, e.getStackTrace().toString());
		}
	}	
	
	private static void loadPNMLFile(String pnmlFile) {
		PNMLSerializer PNML = new PNMLSerializer();
		PQLCommandLine.sys = PNML.parse(pnmlFile);
		
		int pi,ti;
		pi = ti = 1;
		
		for (Place p : sys.getPlaces()) {
			p.setName("p"+pi++);
		}
		
		for (Transition t : sys.getTransitions()) {
			t.setName("t"+ti++);
		}
		
	}

	/**
	 * Print the error message to the standard output.
	 */
	private static void showError() {
		System.out.println("Supplied arguments are not recognized as valid!");
		System.out.println("Use 'java -jar PQL.jar -?' for help.");
	}

	/**
	 * Print the help message to the standard output.
	 */
	private static void showHelp() {
		System.out.println("Copyright (c), 2013-2014. Artem Polyvyanyy.");
		System.out.println("All rights reserved.");
		System.out.println();
		System.out.println("Usage: java -jar PQL.jar [-?|--help]");
		System.out.println("       (to print this help message)");
		System.out.println();
		System.out.println("   or  java -jar PQL.jar -reset");
		System.out.println("       (to reset the system)");
		System.out.println();
		System.out.println("   or  java -jar PQL.jar -c pnmlFile");
		System.out.println("       (to check if the net system stroed in 'pnmlFile' can be indexed)");
		System.out.println();
		System.out.println("   or  java -jar PQL.jar -v pqlFile");
		System.out.println("       (to visualize parse tree of the query stored in 'pqlFile')");
		System.out.println();
		System.out.println("   or  java -jar PQL.jar -i pnmlDirectory");
		System.out.println("       (to index all net systems stored in 'pnmlDirectory'; only *.pnml files are considered)");
		System.out.println();
		System.out.println("   or  java -jar PQL.jar -i pnmlFile identifier");
		System.out.println("       (to index the net system stored in 'pnmlFile' under 'identifier')");
		System.out.println();
		System.out.println("   or  java -jar PQL.jar -d File identifier");
		System.out.println("       (to delete the net system stored under 'identifier' and its index)");
		System.out.println();
		System.out.println("   or  java -jar PQL.jar -q pqlFile");
		System.out.println("       (to discover all identifiers of net systems that match a query stored in 'pqlFile')");
		System.out.println();
		System.out.println("   or  java -jar PQL.jar -q identifier pqlFile");
		System.out.println("       (to test if the net system indexed under 'identifier' matches a query stored in 'pqlFile')");
	}
}
