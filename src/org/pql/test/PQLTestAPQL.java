package org.pql.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

import junit.framework.TestCase;

import org.ini4j.Ini;
import org.jbpt.petri.NetSystem;
import org.jbpt.petri.Place;
import org.jbpt.petri.Transition;
import org.jbpt.petri.io.PNMLSerializer;
import org.jbpt.petri.persist.PetriNetMySQL;
import org.pql.api.PQLAPI;
import org.pql.core.IPQLBasicPredicatesOnTasks;
import org.pql.core.PQLBasicPredicatesMC;
import org.pql.index.PQLIndexMySQL;
import org.pql.label.ILabelManager;
import org.pql.label.LabelManagerLevenshtein;
import org.pql.logic.IThreeValuedLogic;
import org.pql.logic.KleeneLogic;
import org.pql.mc.LoLAModelChecker;

public class PQLTestAPQL extends TestCase {
	final private static String	iniFile	 = "PQL.test.ini";
	
	private static String msg			 = null;
	//private static String dir			 = ".//pnml//apql";
	private static String dir			 = ".//pnml//sap_good";
	
	private static NetSystem sys		 = null;
	
	private static String mysqlURL		 = null;
	private static String mysqlUser		 = null;
	private static String mysqlPassword	 = null;
	private static String lolaPath 		 = null;
	
	private static double 		defaultLabelSimilarity		= 1.0;
	private static Set<Double>	indexedLabelSimilarities	= null;
	
	private static PetriNetMySQL	pnMySQL		= null;
	private static PQLIndexMySQL	pqlMySQL	= null;
	
	private static IThreeValuedLogic logic		= null;	// three-valued logic to use
	private static ILabelManager	 labelMngr	= null;
	
	private static LoLAModelChecker lolaModelChecker = null;
	private static PQLAPI 			pqlAPI			 = null;
	
	private static IPQLBasicPredicatesOnTasks basicPredicates = null;
	
	private static boolean flag = false;
	
	public PQLTestAPQL() throws ClassNotFoundException, SQLException {
		if (flag) return;
		flag = true;
		
		// check initialisation file
		if (!PQLTestAPQL.checkFile(PQLTestAPQL.iniFile)) {
			System.out.println(PQLTestAPQL.msg);
			PQLTestAPQL.createINIFile();
			System.out.println(String.format("Fresh %s file created.", PQLTestAPQL.iniFile));
			return;
		}
		
		// load initialisation file
		if (!PQLTestAPQL.loadINIFile()) {
			System.out.println(PQLTestAPQL.msg);
			return;
		}
		
		// initialise objects
		PQLTestAPQL.logic			 	= new KleeneLogic();
		PQLTestAPQL.lolaModelChecker	= new LoLAModelChecker(PQLTestAPQL.lolaPath);
		PQLTestAPQL.pnMySQL				= new PetriNetMySQL(PQLTestAPQL.mysqlURL,PQLTestAPQL.mysqlUser,PQLTestAPQL.mysqlPassword);
		PQLTestAPQL.labelMngr			= new LabelManagerLevenshtein(mysqlURL,mysqlUser,mysqlPassword,defaultLabelSimilarity,indexedLabelSimilarities);
		PQLTestAPQL.basicPredicates 	= new PQLBasicPredicatesMC(PQLTestAPQL.lolaModelChecker,PQLTestAPQL.logic);
		PQLTestAPQL.pqlMySQL		 	= new PQLIndexMySQL(mysqlURL,mysqlUser,mysqlPassword,basicPredicates,logic,defaultLabelSimilarity,indexedLabelSimilarities);
		
		PQLTestAPQL.pqlAPI				= new PQLAPI(mysqlURL,mysqlUser,mysqlPassword,lolaModelChecker,logic,pnMySQL,pqlMySQL,labelMngr);
		
		// index net systems
		System.out.println("Indexing started.");
		//PQLTestAPQL.indexNetSystems(PQLTestAPQL.dir);
		System.out.println("Indexing completed.");
	}

	public void test001() throws ClassNotFoundException, SQLException {
		PQLTestAPQL.pqlAPI.prepareQuery("SELECT * FROM *");
		assertEquals(1,PQLTestAPQL.pqlAPI.getLastNumberOfParseErrors());
		assertEquals(0,PQLTestAPQL.pqlAPI.checkLastQuery().size());
	}
	
	public void test002() throws ClassNotFoundException, SQLException {
		PQLTestAPQL.pqlAPI.prepareQuery("SELECT * FROM *;");
		assertEquals(0,PQLTestAPQL.pqlAPI.getLastNumberOfParseErrors());
		assertEquals(10,PQLTestAPQL.pqlAPI.checkLastQuery().size());
	}
	
	public void test003() throws ClassNotFoundException, SQLException {
		PQLTestAPQL.pqlAPI.prepareQuery("SELECT * FROM * WHERE CanOccur(\"A\") AND AlwaysOccurs(\"B\");");
		assertEquals(0,PQLTestAPQL.pqlAPI.getLastNumberOfParseErrors());
		Set<String> res = PQLTestAPQL.pqlAPI.checkLastQuery();
		assertEquals(7,res.size());
		
		assertEquals(true, res.contains("1.pnml"));
		assertEquals(true, res.contains("2.pnml"));
		assertEquals(true, res.contains("3.pnml"));
		assertEquals(true, res.contains("5.pnml"));
		assertEquals(true, res.contains("6.pnml"));
		assertEquals(true, res.contains("7.pnml"));
		assertEquals(true, res.contains("9.pnml"));
	}
	
	public void test004() throws ClassNotFoundException, SQLException {
		PQLTestAPQL.pqlAPI.prepareQuery("SELECT * FROM * WHERE Conflict(\"B\",\"C\");");
		assertEquals(0,PQLTestAPQL.pqlAPI.getLastNumberOfParseErrors());
		Set<String> res = PQLTestAPQL.pqlAPI.checkLastQuery();
		assertEquals(2,res.size());
		
		assertEquals(true, res.contains("4.pnml"));
		assertEquals(true, res.contains("8.pnml"));
	}
	
	public void test005() throws ClassNotFoundException, SQLException {
		PQLTestAPQL.pqlAPI.prepareQuery("SELECT * FROM * WHERE CanConflict(\"B\",\"C\");");
		assertEquals(0,PQLTestAPQL.pqlAPI.getLastNumberOfParseErrors());
		Set<String> res = PQLTestAPQL.pqlAPI.checkLastQuery();
		assertEquals(3,res.size());
		
		assertEquals(true, res.contains("4.pnml"));
		assertEquals(true, res.contains("8.pnml"));
		assertEquals(true, res.contains("10.pnml"));
	}
	
	public void test006() throws ClassNotFoundException, SQLException {
		PQLTestAPQL.pqlAPI.prepareQuery("SELECT * FROM * WHERE CanConflict(\"B\",\"C\") OR (CanOccur(\"B\") AND NOT \"C\" IN *);");
		assertEquals(0,PQLTestAPQL.pqlAPI.getLastNumberOfParseErrors());
		Set<String> res = PQLTestAPQL.pqlAPI.checkLastQuery();
		assertEquals(5,res.size());
		
		assertEquals(true, res.contains("1.pnml"));
		assertEquals(true, res.contains("2.pnml"));
		assertEquals(true, res.contains("4.pnml"));
		assertEquals(true, res.contains("8.pnml"));
		assertEquals(true, res.contains("10.pnml"));
	}
	
	public void test007() throws ClassNotFoundException, SQLException {
		PQLTestAPQL.pqlAPI.prepareQuery("SELECT * FROM * WHERE CanConflict(\"B\",\"C\") OR CanConflict(\"B\",\"C\") IS UNKNOWN;");
		assertEquals(0,PQLTestAPQL.pqlAPI.getLastNumberOfParseErrors());
		Set<String> res = PQLTestAPQL.pqlAPI.checkLastQuery();
		assertEquals(5,res.size());
		
		assertEquals(true, res.contains("1.pnml"));
		assertEquals(true, res.contains("2.pnml"));
		assertEquals(true, res.contains("4.pnml"));
		assertEquals(true, res.contains("8.pnml"));
		assertEquals(true, res.contains("10.pnml"));
	}
	
	public void test008() throws ClassNotFoundException, SQLException {
		PQLTestAPQL.pqlAPI.prepareQuery("SELECT * FROM * WHERE Cooccur(\"A\",\"B\");");
		assertEquals(0,PQLTestAPQL.pqlAPI.getLastNumberOfParseErrors());
		Set<String> res = PQLTestAPQL.pqlAPI.checkLastQuery();
		assertEquals(7,res.size());
		
		assertEquals(true, res.contains("1.pnml"));
		assertEquals(true, res.contains("2.pnml"));
		assertEquals(true, res.contains("3.pnml"));
		assertEquals(true, res.contains("5.pnml"));
		assertEquals(true, res.contains("6.pnml"));
		assertEquals(true, res.contains("7.pnml"));
		assertEquals(true, res.contains("9.pnml"));
	}
	
	public void test009() throws ClassNotFoundException, SQLException {
		PQLTestAPQL.pqlAPI.prepareQuery("SELECT * FROM * WHERE CanCooccur(\"A\",\"B\");");
		assertEquals(0,PQLTestAPQL.pqlAPI.getLastNumberOfParseErrors());
		Set<String> res = PQLTestAPQL.pqlAPI.checkLastQuery();
		assertEquals(10,res.size());
	}
	
	public void test010() throws ClassNotFoundException, SQLException {
		PQLTestAPQL.pqlAPI.prepareQuery("SELECT * FROM * WHERE CanCooccur(\"I\",\"H\");");
		assertEquals(0,PQLTestAPQL.pqlAPI.getLastNumberOfParseErrors());
		Set<String> res = PQLTestAPQL.pqlAPI.checkLastQuery();
		assertEquals(4,res.size());
		
		assertEquals(true, res.contains("7.pnml"));
		assertEquals(true, res.contains("8.pnml"));
		assertEquals(true, res.contains("9.pnml"));
		assertEquals(true, res.contains("10.pnml"));
	}
	
	public void test011() throws ClassNotFoundException, SQLException {
		PQLTestAPQL.pqlAPI.prepareQuery("SELECT * FROM * WHERE TotalCausal(\"A\",\"B\");");
		assertEquals(0,PQLTestAPQL.pqlAPI.getLastNumberOfParseErrors());
		Set<String> res = PQLTestAPQL.pqlAPI.checkLastQuery();
		assertEquals(6,res.size());
		
		assertEquals(true, res.contains("3.pnml"));
		assertEquals(true, res.contains("4.pnml"));
		assertEquals(true, res.contains("7.pnml"));
		assertEquals(true, res.contains("8.pnml"));
		assertEquals(true, res.contains("9.pnml"));
		assertEquals(true, res.contains("10.pnml"));
	}
	
	public void test012() throws ClassNotFoundException, SQLException {
		PQLTestAPQL.pqlAPI.prepareQuery("SELECT * FROM * WHERE TotalCausal(\"A\",\"E\");");
		assertEquals(0,PQLTestAPQL.pqlAPI.getLastNumberOfParseErrors());
		Set<String> res = PQLTestAPQL.pqlAPI.checkLastQuery();
		assertEquals(3,res.size());
		
		assertEquals(true, res.contains("7.pnml"));
		assertEquals(true, res.contains("8.pnml"));
		assertEquals(true, res.contains("9.pnml"));
	}
	
	public void test013() throws ClassNotFoundException, SQLException {
		PQLTestAPQL.pqlAPI.prepareQuery("SELECT * FROM * WHERE TotalCausal({\"A\",\"B\",\"C\"},{\"K\"},ALL);");
		assertEquals(0,PQLTestAPQL.pqlAPI.getLastNumberOfParseErrors());
		Set<String> res = PQLTestAPQL.pqlAPI.checkLastQuery();
		assertEquals(2,res.size());
		
		assertEquals(true, res.contains("6.pnml"));
		assertEquals(true, res.contains("9.pnml"));
	}
	
	public void test014() throws ClassNotFoundException, SQLException {
		PQLTestAPQL.pqlAPI.prepareQuery("SELECT * FROM * WHERE Cooccur(\"A\",{\"F\",\"H\"},ANY);");
		assertEquals(0,PQLTestAPQL.pqlAPI.getLastNumberOfParseErrors());
		Set<String> res = PQLTestAPQL.pqlAPI.checkLastQuery();
		assertEquals(4,res.size());
		
		assertEquals(true, res.contains("5.pnml"));
		assertEquals(true, res.contains("6.pnml"));
		assertEquals(true, res.contains("7.pnml"));
		assertEquals(true, res.contains("9.pnml"));
	}
	
	public void test015() throws ClassNotFoundException, SQLException {
		PQLTestAPQL.pqlAPI.prepareQuery("SELECT * FROM * WHERE Cooccur(\"A\",{\"F\",\"H\"},ALL);");
		assertEquals(0,PQLTestAPQL.pqlAPI.getLastNumberOfParseErrors());
		Set<String> res = PQLTestAPQL.pqlAPI.checkLastQuery();
		assertEquals(3,res.size());
		
		assertEquals(true, res.contains("6.pnml"));
		assertEquals(true, res.contains("7.pnml"));
		assertEquals(true, res.contains("9.pnml"));
	}
	
	public void test016() throws ClassNotFoundException, SQLException {
		PQLTestAPQL.pqlAPI.prepareQuery("SELECT * FROM * WHERE TotalConcurrent(\"B\",\"D\");");
		assertEquals(0,PQLTestAPQL.pqlAPI.getLastNumberOfParseErrors());
		Set<String> res = PQLTestAPQL.pqlAPI.checkLastQuery();
		assertEquals(1,res.size());
		
		assertEquals(true, res.contains("5.pnml"));
	}
	
	public void test017() throws ClassNotFoundException, SQLException {
		PQLTestAPQL.pqlAPI.prepareQuery("SELECT * FROM * WHERE TotalConcurrent(\"B\",\"C\");");
		assertEquals(0,PQLTestAPQL.pqlAPI.getLastNumberOfParseErrors());
		Set<String> res = PQLTestAPQL.pqlAPI.checkLastQuery();
		assertEquals(5,res.size());
		
		assertEquals(true, res.contains("3.pnml"));
		assertEquals(true, res.contains("4.pnml"));
		assertEquals(true, res.contains("5.pnml"));
		assertEquals(true, res.contains("8.pnml"));
		assertEquals(true, res.contains("9.pnml"));
	}
	
	public void test018() throws ClassNotFoundException, SQLException {
		PQLTestAPQL.pqlAPI.prepareQuery("SELECT * FROM * WHERE (((TotalConcurrent(\"B\",\"C\")))) AND CanCooccur(\"B\",\"C\");");
		assertEquals(0,PQLTestAPQL.pqlAPI.getLastNumberOfParseErrors());
		Set<String> res = PQLTestAPQL.pqlAPI.checkLastQuery();
		assertEquals(3,res.size());
		
		assertEquals(true, res.contains("3.pnml"));
		assertEquals(true, res.contains("5.pnml"));
		assertEquals(true, res.contains("9.pnml"));
	}
	
	public void test019() throws ClassNotFoundException, SQLException {
		PQLTestAPQL.pqlAPI.prepareQuery("SELECT * FROM * WHERE TotalCausal(\"A\",\"C\") AND TotalConcurrent({\"C\"},{\"K\",\"D\"},ALL);");
		assertEquals(0,PQLTestAPQL.pqlAPI.getLastNumberOfParseErrors());
		Set<String> res = PQLTestAPQL.pqlAPI.checkLastQuery();
		assertEquals(1,res.size());
		
		assertEquals(true, res.contains("7.pnml"));
	}
	
	public void test020() throws ClassNotFoundException, SQLException {
		PQLTestAPQL.pqlAPI.prepareQuery("SELECT * FROM * WHERE TotalCausal(\"A\",\"C\") OR TotalConcurrent({\"C\"},{\"K\",\"D\"},ALL);");
		assertEquals(0,PQLTestAPQL.pqlAPI.getLastNumberOfParseErrors());
		Set<String> res = PQLTestAPQL.pqlAPI.checkLastQuery();
		assertEquals(4,res.size());
		
		assertEquals(true, res.contains("3.pnml"));
		assertEquals(true, res.contains("4.pnml"));
		assertEquals(true, res.contains("7.pnml"));
		assertEquals(true, res.contains("9.pnml"));
	}
	
	public void test021() throws ClassNotFoundException, SQLException {
		PQLTestAPQL.pqlAPI.prepareQuery("SELECT * FROM * WHERE TotalCausal(\"A\",\"C\") OR TotalConcurrent({\"C\"},{\"K\",\"D\"},ANY);");
		assertEquals(0,PQLTestAPQL.pqlAPI.getLastNumberOfParseErrors());
		Set<String> res = PQLTestAPQL.pqlAPI.checkLastQuery();
		assertEquals(6,res.size());
		
		assertEquals(true, res.contains("3.pnml"));
		assertEquals(true, res.contains("4.pnml"));
		assertEquals(true, res.contains("7.pnml"));
		assertEquals(true, res.contains("8.pnml"));
		assertEquals(true, res.contains("9.pnml"));
		assertEquals(true, res.contains("10.pnml"));
	}
	
	public void test022() throws ClassNotFoundException, SQLException {
		PQLTestAPQL.pqlAPI.prepareQuery("SELECT * FROM * WHERE TotalCausal(\"A\",\"C\") OR (TotalConcurrent({\"C\"},{\"K\",\"D\"},ANY) AND CanCooccur({\"C\"},{\"K\",\"D\"},ALL));");
		assertEquals(0,PQLTestAPQL.pqlAPI.getLastNumberOfParseErrors());
		Set<String> res = PQLTestAPQL.pqlAPI.checkLastQuery();
		assertEquals(4,res.size());
		
		assertEquals(true, res.contains("3.pnml"));
		assertEquals(true, res.contains("4.pnml"));
		assertEquals(true, res.contains("7.pnml"));
		assertEquals(true, res.contains("9.pnml"));
	}
	
	public void test023() throws ClassNotFoundException, SQLException {
		PQLTestAPQL.pqlAPI.prepareQuery("SELECT * FROM * WHERE {\"A\",\"C\"} IS SUBSET OF GetTasksAlwaysOccurs(*);");
		assertEquals(0,PQLTestAPQL.pqlAPI.getLastNumberOfParseErrors());
		Set<String> res = PQLTestAPQL.pqlAPI.checkLastQuery();
		assertEquals(5,res.size());
		
		assertEquals(true, res.contains("3.pnml"));
		assertEquals(true, res.contains("5.pnml"));
		assertEquals(true, res.contains("6.pnml"));
		assertEquals(true, res.contains("7.pnml"));
		assertEquals(true, res.contains("9.pnml"));
	}
	
	public void test024() throws ClassNotFoundException, SQLException {
		PQLTestAPQL.pqlAPI.prepareQuery("x = GetTasksAlwaysOccurs(*); SELECT * FROM * WHERE {\"A\",\"C\"} IS SUBSET OF x;");
		assertEquals(0,PQLTestAPQL.pqlAPI.getLastNumberOfParseErrors());
		Set<String> res = PQLTestAPQL.pqlAPI.checkLastQuery();
		assertEquals(5,res.size());
		
		assertEquals(true, res.contains("3.pnml"));
		assertEquals(true, res.contains("5.pnml"));
		assertEquals(true, res.contains("6.pnml"));
		assertEquals(true, res.contains("7.pnml"));
		assertEquals(true, res.contains("9.pnml"));
	}
	
	public void test025() throws ClassNotFoundException, SQLException {
		PQLTestAPQL.pqlAPI.prepareQuery("x = GetTasksAlwaysOccurs(*); SELECT * FROM * WHERE ({\"A\",\"C\"} IS SUBSET OF x) IS NOT TRUE;");
		assertEquals(0,PQLTestAPQL.pqlAPI.getLastNumberOfParseErrors());
		Set<String> res = PQLTestAPQL.pqlAPI.checkLastQuery();
		assertEquals(5,res.size());
		
		assertEquals(true, res.contains("1.pnml"));
		assertEquals(true, res.contains("2.pnml"));
		assertEquals(true, res.contains("4.pnml"));
		assertEquals(true, res.contains("8.pnml"));
		assertEquals(true, res.contains("10.pnml"));
	}

	public void test026() throws ClassNotFoundException, SQLException {
		PQLTestAPQL.pqlAPI.prepareQuery("SELECT * FROM * WHERE TRUE;");
		assertEquals(0,PQLTestAPQL.pqlAPI.getLastNumberOfParseErrors());
		Set<String> res = PQLTestAPQL.pqlAPI.checkLastQuery();
		assertEquals(10,res.size());
	}
	
	/**
	 * Create a configuration file, if one does not exist.
	 */
	private static void createINIFile() {
		BufferedWriter out;
		try {
			out = new BufferedWriter(new FileWriter(PQLTestAPQL.iniFile));
			out.write("[mysql]\n");
			out.write("url = jdbc:mysql://localhost:3306/mysql\n");
			out.write("user = root\n");
			out.write("password = password\n");
			out.write("\n");
			out.write("[postgresql]\n");
			out.write("host = localhost\n");
			out.write("name = themis\n");
			out.write("user = postgres\n");
			out.write("password = password\n");
			out.write("\n");
			out.write("[lola]\n");
			out.write("lolaPath = .\\lola2\\win\\lola.exe\n");
			out.write("\n");
			out.write("[pql]\n");
			out.write("labelSimilaritySearch = levenshtein");
			out.write("defaultLabelSimilarity = 0.75\n");
			out.write("indexedLabelSimilarities = 0.75,1.0\n");
			out.close();
			PQLTestAPQL.msg += String.format("A sample %s file created.", PQLTestAPQL.iniFile);
		} catch (Exception e) {
			PQLTestAPQL.msg += String.format("An exception was thrown when creating a sample %s file: %s", PQLTestAPQL.iniFile, e.getStackTrace().toString());
		}
	}
	
	private static boolean loadINIFile() {
		try {
			File file = new File(PQLTestAPQL.iniFile);
			
			// read configuration from file
			Ini ini = new Ini();
			ini.load(new FileReader(file));
			
			// load lola section
	        Ini.Section section = ini.get("lola");
	        PQLTestAPQL.lolaPath = section.get("lolaPath");
	        
	        // load pql section
	        section = ini.get("pql");
	        
	        PQLTestAPQL.defaultLabelSimilarity = Double.parseDouble(section.get("defaultLabelSimilarity"));
	        String similarities = section.get("indexedLabelSimilarities");
	        StringTokenizer st = new StringTokenizer(similarities,",");
	        
	        PQLTestAPQL.indexedLabelSimilarities = new HashSet<Double>();
	        while (st.hasMoreTokens()) 
	        	PQLTestAPQL.indexedLabelSimilarities.add(Double.parseDouble(st.nextToken()));
	        
	        PQLTestAPQL.indexedLabelSimilarities.add(1.0);
	        PQLTestAPQL.indexedLabelSimilarities.add(PQLTestAPQL.defaultLabelSimilarity);
	        
	        
	        // load mysql section
	        section = ini.get("mysql");
	        PQLTestAPQL.mysqlURL = section.get("url");
	        PQLTestAPQL.mysqlUser = section.get("user");
	        PQLTestAPQL.mysqlPassword = section.get("password");
	        
	        return true;
		} catch (IOException e) {
			PQLTestAPQL.msg = String.format("Cannot read %s.", PQLTestAPQL.iniFile);
			return false;
		} catch (NumberFormatException e) {
			PQLTestAPQL.msg = String.format("Wrong number format for 'defaultLabelSimilarity' or 'indexedLabelSimilarities'.", e.getStackTrace().toString());
			return false;
		} catch (Exception e) {
			PQLTestAPQL.msg = String.format("An unhandled exception was thrown: %s", e.getStackTrace().toString());
			return false;
		}
	}
	
	private static void indexNetSystems(String pnmlDirectory) throws SQLException {
		File dir = new File(pnmlDirectory);
		
		for (File file : dir.listFiles()) {
			if (!file.isFile()) continue;
			if (!file.getName().endsWith(".pnml")) continue;
			
			PQLTestAPQL.indexNetSystem(file.getAbsolutePath(), file.getName());
		}
	}
	
	private static int indexNetSystem(String pnmlFile, String identifier) throws SQLException {
		// load PNML file
		// TODO handle exceptions
		PQLTestAPQL.loadPNMLFile(pnmlFile);		
		PQLTestAPQL.sys.loadNaturalMarking();
		
		// check net system
		if (!PQLTestAPQL.checkNetSystem()) {
			System.out.println(pnmlFile + ": " + PQLTestAPQL.msg);
			return -1;
		}
		
		System.out.print(String.format("System with identifier '%s' ", identifier));
		long start = System.nanoTime();
		int netID = PQLTestAPQL.pqlAPI.indexNetSystem(PQLTestAPQL.sys,identifier,PQLTestAPQL.indexedLabelSimilarities);
		long end = System.nanoTime();
		
		if (netID>0)
			System.out.println(String.format("indexed successfully in %sns.", end-start));
		else if (netID==0)
			System.out.println(String.format("cannot be indexed. The Identifier is already associated with some other system.", identifier)); 
		else 
			System.out.println(String.format("cannot be indexed. Cannot connect to database. Check %s file.", PQLTestAPQL.iniFile));
		
		return netID;
	}
	
	private static void loadPNMLFile(String pnmlFile) {
		PNMLSerializer PNML = new PNMLSerializer();
		PQLTestAPQL.sys = PNML.parse(pnmlFile);
		
		int pi,ti;
		pi = ti = 1;
		
		for (Place p : sys.getPlaces()) {
			p.setName("p"+pi++);
		}
		
		for (Transition t : sys.getTransitions()) {
			t.setName("t"+ti++);
		}
		
	}
	
	private static boolean checkNetSystem() {
		boolean result = PQLTestAPQL.pqlAPI.checkNetSystem(PQLTestAPQL.sys);
		
		if (result)
			PQLTestAPQL.msg = "Given system can be indexed, i.e., it is a sound workflow net.";
		else
			PQLTestAPQL.msg = "Given system cannot be indexed, i.e., it is not a sound workflow net.";
		
		return result;
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
						PQLTestAPQL.msg = String.format("%s file exists, but cannot be read.", fileName);
						return false;
					}
				}
				else {
					PQLTestAPQL.msg = String.format("%s exists, but is not a normal file.", fileName);
					return false;
				}
			}
			else {
				PQLTestAPQL.msg = String.format("%s does not exist.", fileName);
				return false;
			}
		} catch (Exception e) {
			PQLTestAPQL.msg = String.format("An unhandled exception was thrown: %s", e.getStackTrace().toString());
			return false;
		}
	}
}
