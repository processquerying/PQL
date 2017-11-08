package org.pql.test;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;
import org.pql.api.PQLAPI;
import org.pql.ini.PQLIniFile;
import org.pql.query.PQLQueryResult;

/**
 * This test is part of the core test package.
 * 
 * This test must be successful for every PQL commit!
 * 
 * @author Anastasiia Pika
 * @author Artem Polyvyanyy
 */
public class PQLTestAPQLExecutes {
	private static PQLAPI	pqlAPI	= null;
	
	@BeforeClass
	public static void beforeClass() throws ClassNotFoundException, SQLException, IOException {
		PQLIniFile iniFile = new PQLIniFile();
		if (!iniFile.load()) { 
			System.out.println("ERROR: Cannot load PQL ini file.");
			return;
		}
		
		PQLTestAPQLExecutes.pqlAPI = new PQLAPI(iniFile.getMySQLURL(), iniFile.getMySQLUser(), iniFile.getMySQLPassword(),
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
		
		// TODO check that we are connected to the right version of PQL
		
		PQLTestAPQLExecutes.pqlAPI.reset();
	
		System.out.println("Reset done!");
		PQLTestAPQLExecutes.pqlAPI.storeModel(new File("./pnml/apql/1.pnml"),"1.pnml", "/");
		System.out.println("1.pnml stored");
		PQLTestAPQLExecutes.pqlAPI.storeModel(new File("./pnml/apql/2.pnml"),"2.pnml", "/");
		System.out.println("2.pnml stored");
		PQLTestAPQLExecutes.pqlAPI.storeModel(new File("./pnml/apql/3.pnml"),"3.pnml", "/");
		System.out.println("3.pnml stored");
		PQLTestAPQLExecutes.pqlAPI.storeModel(new File("./pnml/apql/4.pnml"),"4.pnml", "/");
		System.out.println("4.pnml stored");
		PQLTestAPQLExecutes.pqlAPI.storeModel(new File("./pnml/apql/5.pnml"),"5.pnml", "/");
		System.out.println("5.pnml stored");
		PQLTestAPQLExecutes.pqlAPI.storeModel(new File("./pnml/apql/6.pnml"),"6.pnml", "/");
		System.out.println("6.pnml stored");
		PQLTestAPQLExecutes.pqlAPI.storeModel(new File("./pnml/apql/7.pnml"),"7.pnml", "/");
		System.out.println("7.pnml stored");
		PQLTestAPQLExecutes.pqlAPI.storeModel(new File("./pnml/apql/8.pnml"),"8.pnml", "/");
		System.out.println("8.pnml stored");
		PQLTestAPQLExecutes.pqlAPI.storeModel(new File("./pnml/apql/9.pnml"),"9.pnml", "/");
		System.out.println("9.pnml stored");
		PQLTestAPQLExecutes.pqlAPI.storeModel(new File("./pnml/apql/10.pnml"),"10.pnml", "/");
		System.out.println("10.pnml stored");
		
		PQLTestAPQLExecutes.pqlAPI.index(PQLTestAPQLExecutes.pqlAPI.getInternalID("1.pnml"));
		System.out.println("1.pnml indexed");
		PQLTestAPQLExecutes.pqlAPI.index(PQLTestAPQLExecutes.pqlAPI.getInternalID("2.pnml"));
		System.out.println("2.pnml indexed");
		PQLTestAPQLExecutes.pqlAPI.index(PQLTestAPQLExecutes.pqlAPI.getInternalID("3.pnml"));
		System.out.println("3.pnml indexed");
		PQLTestAPQLExecutes.pqlAPI.index(PQLTestAPQLExecutes.pqlAPI.getInternalID("4.pnml"));
		System.out.println("4.pnml indexed");
		PQLTestAPQLExecutes.pqlAPI.index(PQLTestAPQLExecutes.pqlAPI.getInternalID("5.pnml"));
		System.out.println("5.pnml indexed");
		PQLTestAPQLExecutes.pqlAPI.index(PQLTestAPQLExecutes.pqlAPI.getInternalID("6.pnml"));
		System.out.println("6.pnml indexed");
		PQLTestAPQLExecutes.pqlAPI.index(PQLTestAPQLExecutes.pqlAPI.getInternalID("7.pnml"));
		System.out.println("7.pnml indexed");
		PQLTestAPQLExecutes.pqlAPI.index(PQLTestAPQLExecutes.pqlAPI.getInternalID("8.pnml"));
		System.out.println("8.pnml indexed");
		PQLTestAPQLExecutes.pqlAPI.index(PQLTestAPQLExecutes.pqlAPI.getInternalID("9.pnml"));
		System.out.println("9.pnml indexed");
		PQLTestAPQLExecutes.pqlAPI.index(PQLTestAPQLExecutes.pqlAPI.getInternalID("10.pnml"));
		System.out.println("10.pnml indexed");
	}

	@Test
	public void test001() throws ClassNotFoundException, SQLException {
		PQLQueryResult queryResult = PQLTestAPQLExecutes.pqlAPI.query("SELECT * FROM * WHERE Executes(<\"A\",\"B\",\"C\">);");
		assertEquals(0,queryResult.getNumberOfParseErrors());
		assertEquals(1,queryResult.getSearchResults().size());
		Set<String> res = queryResult.getSearchResults();
		assertEquals(true, res.contains("3.pnml"));
	}		
	
	@Test
	public void test002() throws ClassNotFoundException, SQLException {
		PQLQueryResult queryResult = PQLTestAPQLExecutes.pqlAPI.query("SELECT * FROM * WHERE Executes(<\"A\",\"B\",*,\"C\">);");
		assertEquals(0,queryResult.getNumberOfParseErrors());
		assertEquals(1,queryResult.getSearchResults().size());
		Set<String> res = queryResult.getSearchResults();
		assertEquals(true, res.contains("3.pnml"));
	}

	@Test
	public void test003() throws ClassNotFoundException, SQLException {
		PQLQueryResult queryResult = PQLTestAPQLExecutes.pqlAPI.query("SELECT * FROM * WHERE Executes(<\"A\",\"B\",\"C\",*>);");
		assertEquals(0,queryResult.getNumberOfParseErrors());
		assertEquals(5,queryResult.getSearchResults().size());
		Set<String> res = queryResult.getSearchResults();
		assertEquals(true, res.contains("3.pnml"));
		assertEquals(true, res.contains("5.pnml"));
		assertEquals(true, res.contains("6.pnml"));
		assertEquals(true, res.contains("7.pnml"));
		assertEquals(true, res.contains("9.pnml"));	
	}

	@Test
	public void test004() throws ClassNotFoundException, SQLException {
		PQLQueryResult queryResult = PQLTestAPQLExecutes.pqlAPI.query("SELECT * FROM * WHERE Executes(<*,\"M\">);");
		assertEquals(0,queryResult.getNumberOfParseErrors());
		assertEquals(2,queryResult.getSearchResults().size());
		Set<String> res = queryResult.getSearchResults();
		assertEquals(true, res.contains("7.pnml"));
		assertEquals(true, res.contains("9.pnml"));	
	}

	@Test
	public void test005() throws ClassNotFoundException, SQLException {
			PQLQueryResult queryResult = PQLTestAPQLExecutes.pqlAPI.query("SELECT * FROM * WHERE Executes(<*,\"F\",\"A\",*>);");
			assertEquals(0,queryResult.getNumberOfParseErrors());
			assertEquals(3,queryResult.getSearchResults().size());
			Set<String> res = queryResult.getSearchResults();
			assertEquals(true, res.contains("5.pnml"));
			assertEquals(true, res.contains("6.pnml"));
			assertEquals(true, res.contains("10.pnml"));	
	}
	
	@Test
	public void test006() throws ClassNotFoundException, SQLException {
		PQLQueryResult queryResult = PQLTestAPQLExecutes.pqlAPI.query("SELECT * FROM * WHERE Executes(<\"A\",\"I\",\"G\",\"B\">);");
		assertEquals(0,queryResult.getNumberOfParseErrors());
		assertEquals(1,queryResult.getSearchResults().size());
		Set<String> res = queryResult.getSearchResults();
		assertEquals(true, res.contains("10.pnml"));
	}

	@Test
	public void test007() throws ClassNotFoundException, SQLException {
		PQLQueryResult queryResult = PQLTestAPQLExecutes.pqlAPI.query("SELECT * FROM * WHERE Executes(<\"A\",\"I\",*,\"G\",\"B\">);");
		assertEquals(0,queryResult.getNumberOfParseErrors());
		assertEquals(1,queryResult.getSearchResults().size());
		Set<String> res = queryResult.getSearchResults();
		assertEquals(true, res.contains("10.pnml"));
	}
	
	@Test
	public void test008() throws ClassNotFoundException, SQLException {
		PQLQueryResult queryResult = PQLTestAPQLExecutes.pqlAPI.query("SELECT * FROM * WHERE Executes(<*,\"N\">);");
		assertEquals(0,queryResult.getNumberOfParseErrors());
		assertEquals(0,queryResult.getSearchResults().size());
	}
	
	@Test
	public void test009() throws ClassNotFoundException, SQLException {
		PQLQueryResult queryResult = PQLTestAPQLExecutes.pqlAPI.query("SELECT * FROM * WHERE Executes(<\"N\">);");
		assertEquals(0,queryResult.getNumberOfParseErrors());
		assertEquals(0,queryResult.getSearchResults().size());
	}
	
	@Test
	public void test010() throws ClassNotFoundException, SQLException {
		PQLQueryResult queryResult = PQLTestAPQLExecutes.pqlAPI.query("SELECT * FROM * WHERE NOT Executes(<\"A\",\"B\",\"D\">);");
		assertEquals(0,queryResult.getNumberOfParseErrors());
		assertEquals(9,queryResult.getSearchResults().size());
		Set<String> res = queryResult.getSearchResults();
		assertEquals(true, res.contains("1.pnml"));
		assertEquals(true, res.contains("2.pnml"));
		assertEquals(true, res.contains("3.pnml"));
		assertEquals(true, res.contains("4.pnml"));
		assertEquals(true, res.contains("5.pnml"));
		assertEquals(true, res.contains("6.pnml"));
		assertEquals(true, res.contains("7.pnml"));
		assertEquals(true, res.contains("8.pnml"));
		assertEquals(true, res.contains("9.pnml"));
	}

	@Test
	public void test011() throws ClassNotFoundException, SQLException {
		PQLQueryResult queryResult = PQLTestAPQLExecutes.pqlAPI.query("SELECT * FROM * WHERE NOT Executes(<\"A\",*,\"B\",\"D\">);");
		assertEquals(0,queryResult.getNumberOfParseErrors());
		assertEquals(8,queryResult.getSearchResults().size());
		Set<String> res = queryResult.getSearchResults();
		assertEquals(true, res.contains("1.pnml"));
		assertEquals(true, res.contains("2.pnml"));
		assertEquals(true, res.contains("3.pnml"));
		assertEquals(true, res.contains("4.pnml"));
		assertEquals(true, res.contains("6.pnml"));
		assertEquals(true, res.contains("7.pnml"));
		assertEquals(true, res.contains("8.pnml"));
		assertEquals(true, res.contains("9.pnml"));
	}
	
	@Test
	public void test012() throws ClassNotFoundException, SQLException {
		PQLQueryResult queryResult = PQLTestAPQLExecutes.pqlAPI.query("SELECT * FROM * WHERE Executes(<*,\"F\",\"D\",\"E\",*>);");
		assertEquals(0,queryResult.getNumberOfParseErrors());
		assertEquals(5,queryResult.getSearchResults().size());
		Set<String> res = queryResult.getSearchResults();
		assertEquals(true, res.contains("5.pnml"));
		assertEquals(true, res.contains("6.pnml"));
		assertEquals(true, res.contains("7.pnml"));
		assertEquals(true, res.contains("8.pnml"));
		assertEquals(true, res.contains("9.pnml"));
	}
		
	@Test
	public void test013() throws ClassNotFoundException, SQLException {
		PQLQueryResult queryResult = PQLTestAPQLExecutes.pqlAPI.query("SELECT * FROM * WHERE Executes(<\"A\",*,\"A\",*>);");
		assertEquals(0,queryResult.getNumberOfParseErrors());
		assertEquals(6,queryResult.getSearchResults().size());
		Set<String> res = queryResult.getSearchResults();
		assertEquals(true, res.contains("1.pnml"));
		assertEquals(true, res.contains("2.pnml"));
		assertEquals(true, res.contains("5.pnml"));
		assertEquals(true, res.contains("6.pnml"));
		assertEquals(true, res.contains("8.pnml"));
		assertEquals(true, res.contains("10.pnml"));
	}
	
	@Test
	public void test014() throws ClassNotFoundException, SQLException {
		PQLQueryResult queryResult = PQLTestAPQLExecutes.pqlAPI.query("SELECT * FROM * WHERE Executes(<\"A\",\"B\",\"C\",\"D\",\"E\",\"F\",\"A\",\"B\",\"C\",\"D\",\"E\",\"F\",\"H\",\"J\",\"K\">);");
		assertEquals(0,queryResult.getNumberOfParseErrors());
		assertEquals(1,queryResult.getSearchResults().size());
		Set<String> res = queryResult.getSearchResults();
		assertEquals(true, res.contains("6.pnml"));
	}
	
	@Test
	public void test015() throws ClassNotFoundException, SQLException {
		PQLQueryResult queryResult = PQLTestAPQLExecutes.pqlAPI.query("SELECT * FROM * WHERE Executes(<*,\"A\",\"B\",\"C\",\"D\",\"E\",\"F\",\"A\",\"B\",\"C\",*,\"D\",\"E\",\"F\",\"H\",\"J\",\"K\",*>);");
		assertEquals(0,queryResult.getNumberOfParseErrors());
		assertEquals(1,queryResult.getSearchResults().size());
		Set<String> res = queryResult.getSearchResults();
		assertEquals(true, res.contains("6.pnml"));
	}

	@Test
	public void test016() throws ClassNotFoundException, SQLException {
		PQLQueryResult queryResult = PQLTestAPQLExecutes.pqlAPI.query("SELECT * FROM * WHERE Executes(<*>);");
		assertEquals(0,queryResult.getNumberOfParseErrors());
		assertEquals(10,queryResult.getSearchResults().size());
	}
	
	@Test
	public void test017() throws ClassNotFoundException, SQLException {
		PQLQueryResult queryResult = PQLTestAPQLExecutes.pqlAPI.query("SELECT * FROM * WHERE Executes(<*,*>);");
		assertEquals(0,queryResult.getNumberOfParseErrors());
		assertEquals(10,queryResult.getSearchResults().size());
	}
	
	@Test
	public void test018() throws ClassNotFoundException, SQLException {
		PQLQueryResult queryResult = PQLTestAPQLExecutes.pqlAPI.query("SELECT * FROM * WHERE NOT Executes(<*>);");
		assertEquals(0,queryResult.getNumberOfParseErrors());
		assertEquals(0,queryResult.getSearchResults().size());
	}
	
	@Test
	public void test019() throws ClassNotFoundException, SQLException {
		PQLQueryResult queryResult = PQLTestAPQLExecutes.pqlAPI.query("SELECT * FROM * WHERE Executes(<*,\"B\",\"D\",*,\"H\",*>);");
		assertEquals(0,queryResult.getNumberOfParseErrors());
		assertEquals(3,queryResult.getSearchResults().size());
		Set<String> res = queryResult.getSearchResults();
		assertEquals(true, res.contains("6.pnml"));
		assertEquals(true, res.contains("7.pnml"));
		assertEquals(true, res.contains("9.pnml"));
	}

	@Test
	public void test020() throws ClassNotFoundException, SQLException {
		PQLQueryResult queryResult = PQLTestAPQLExecutes.pqlAPI.query("SELECT * FROM * WHERE Executes(<*,*,\"B\",\"D\",*,*,*,\"H\",*,*,*>);");
		assertEquals(0,queryResult.getNumberOfParseErrors());
		assertEquals(3,queryResult.getSearchResults().size());
		Set<String> res = queryResult.getSearchResults();
		assertEquals(true, res.contains("6.pnml"));
		assertEquals(true, res.contains("7.pnml"));
		assertEquals(true, res.contains("9.pnml"));
	}
}
