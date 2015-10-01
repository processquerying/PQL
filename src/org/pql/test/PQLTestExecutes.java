package org.pql.test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Set;

import junit.framework.TestCase;

import org.pql.api.PQLAPI;
import org.pql.ini.PQLIniFile;
import org.pql.query.PQLQueryResult;

public class PQLTestExecutes extends TestCase {
	private static PQLAPI	pqlAPI	= null;
	
	public PQLTestExecutes() throws ClassNotFoundException, SQLException, IOException {
		PQLIniFile iniFile = new PQLIniFile();
		if (!iniFile.load()) { 
			System.out.println("ERROR: Cannot load PQL ini file.");
			return;
		}
		
		PQLTestExecutes.pqlAPI = new PQLAPI(iniFile.getMySQLURL(), iniFile.getMySQLUser(), iniFile.getMySQLPassword(),
				iniFile.getPostgreSQLHost(), iniFile.getPostgreSQLName(), iniFile.getPostgreSQLUser(), iniFile.getPostgreSQLPassword(),
				iniFile.getLoLA2Path(),
				iniFile.getLabelSimilaritySeacrhConfiguration(),
				iniFile.getThreeValuedLogicType(),  
				iniFile.getIndexType(),
				iniFile.getLabelManagerType(),
				iniFile.getDefaultLabelSimilarityThreshold(),
				iniFile.getIndexedLabelSimilarities(),
				iniFile.getNumberOfQueryThreads(),
				iniFile.getDefaultBotMaxIndexTime(),
				iniFile.getDefaultBotSleepTime());}
		
	public void test001() throws ClassNotFoundException, SQLException {
		PQLQueryResult queryResult = PQLTestExecutes.pqlAPI.query("SELECT * FROM * WHERE Executes(<\"A\",\"B\",\"C\">);");
		assertEquals(0,queryResult.getNumberOfParseErrors());
		assertEquals(1,queryResult.getSearchResults().size());
		Set<String> res = queryResult.getSearchResults();
		assertEquals(true, res.contains("3.pnml"));}
	
	public void test002() throws ClassNotFoundException, SQLException {
		PQLQueryResult queryResult = PQLTestExecutes.pqlAPI.query("SELECT * FROM * WHERE Executes(<\"A\",\"B\",*,\"C\">);");
		assertEquals(0,queryResult.getNumberOfParseErrors());
		assertEquals(1,queryResult.getSearchResults().size());
		Set<String> res = queryResult.getSearchResults();
		assertEquals(true, res.contains("3.pnml"));}


	
	public void test003() throws ClassNotFoundException, SQLException {
			PQLQueryResult queryResult = PQLTestExecutes.pqlAPI.query("SELECT * FROM * WHERE Executes(<\"A\",\"B\",\"C\",*>);");
			assertEquals(0,queryResult.getNumberOfParseErrors());
			assertEquals(5,queryResult.getSearchResults().size());
			Set<String> res = queryResult.getSearchResults();
			assertEquals(true, res.contains("3.pnml"));
			assertEquals(true, res.contains("5.pnml"));
			assertEquals(true, res.contains("6.pnml"));
			assertEquals(true, res.contains("7.pnml"));
			assertEquals(true, res.contains("9.pnml"));
			}

	public void test004() throws ClassNotFoundException, SQLException {
		PQLQueryResult queryResult = PQLTestExecutes.pqlAPI.query("SELECT * FROM * WHERE Executes(<*,\"M\">);");
		assertEquals(0,queryResult.getNumberOfParseErrors());
		assertEquals(2,queryResult.getSearchResults().size());
		Set<String> res = queryResult.getSearchResults();
		assertEquals(true, res.contains("7.pnml"));
		assertEquals(true, res.contains("9.pnml"));
		}

	public void test005() throws ClassNotFoundException, SQLException {
		PQLQueryResult queryResult = PQLTestExecutes.pqlAPI.query("SELECT * FROM * WHERE Executes(<*,\"F\",\"A\",*>);");
		assertEquals(0,queryResult.getNumberOfParseErrors());
		assertEquals(3,queryResult.getSearchResults().size());
		Set<String> res = queryResult.getSearchResults();
		assertEquals(true, res.contains("5.pnml"));
		assertEquals(true, res.contains("6.pnml"));
		assertEquals(true, res.contains("10.pnml"));
		}
	
	public void test006() throws ClassNotFoundException, SQLException {
		PQLQueryResult queryResult = PQLTestExecutes.pqlAPI.query("SELECT * FROM * WHERE Executes(<\"A\",\"I\",\"G\",\"B\">);");
		assertEquals(0,queryResult.getNumberOfParseErrors());
		assertEquals(1,queryResult.getSearchResults().size());
		Set<String> res = queryResult.getSearchResults();
		assertEquals(true, res.contains("10.pnml"));
		}

	public void test007() throws ClassNotFoundException, SQLException {
		PQLQueryResult queryResult = PQLTestExecutes.pqlAPI.query("SELECT * FROM * WHERE Executes(<\"A\",\"I\",*,\"G\",\"B\">);");
		assertEquals(0,queryResult.getNumberOfParseErrors());
		assertEquals(1,queryResult.getSearchResults().size());
		Set<String> res = queryResult.getSearchResults();
		assertEquals(true, res.contains("10.pnml"));
		}
	
	public void test008() throws ClassNotFoundException, SQLException {
		PQLQueryResult queryResult = PQLTestExecutes.pqlAPI.query("SELECT * FROM * WHERE Executes(<*,\"N\">);");
		assertEquals(0,queryResult.getNumberOfParseErrors());
		assertEquals(0,queryResult.getSearchResults().size());
		}
	
	public void test009() throws ClassNotFoundException, SQLException {
		PQLQueryResult queryResult = PQLTestExecutes.pqlAPI.query("SELECT * FROM * WHERE Executes(<\"N\">);");
		assertEquals(0,queryResult.getNumberOfParseErrors());
		assertEquals(0,queryResult.getSearchResults().size());
		}
	
	public void test010() throws ClassNotFoundException, SQLException {
		PQLQueryResult queryResult = PQLTestExecutes.pqlAPI.query("SELECT * FROM * WHERE NOT Executes(<\"A\",\"B\",\"D\">);");
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

	public void test011() throws ClassNotFoundException, SQLException {
		PQLQueryResult queryResult = PQLTestExecutes.pqlAPI.query("SELECT * FROM * WHERE NOT Executes(<\"A\",*,\"B\",\"D\">);");
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
	
	public void test012() throws ClassNotFoundException, SQLException {
		PQLQueryResult queryResult = PQLTestExecutes.pqlAPI.query("SELECT * FROM * WHERE Executes(<*,\"F\",\"D\",\"E\",*>);");
		assertEquals(0,queryResult.getNumberOfParseErrors());
		assertEquals(5,queryResult.getSearchResults().size());
		Set<String> res = queryResult.getSearchResults();
		assertEquals(true, res.contains("5.pnml"));
		assertEquals(true, res.contains("6.pnml"));
		assertEquals(true, res.contains("7.pnml"));
		assertEquals(true, res.contains("8.pnml"));
		assertEquals(true, res.contains("9.pnml"));}
		
	public void test013() throws ClassNotFoundException, SQLException {
			PQLQueryResult queryResult = PQLTestExecutes.pqlAPI.query("SELECT * FROM * WHERE Executes(<\"A\",*,\"A\",*>);");
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
	
	public void test014() throws ClassNotFoundException, SQLException {
		PQLQueryResult queryResult = PQLTestExecutes.pqlAPI.query("SELECT * FROM * WHERE Executes(<\"A\",\"B\",\"C\",\"D\",\"E\",\"F\",\"A\",\"B\",\"C\",\"D\",\"E\",\"F\",\"H\",\"J\",\"K\">);");
		assertEquals(0,queryResult.getNumberOfParseErrors());
		assertEquals(1,queryResult.getSearchResults().size());
		Set<String> res = queryResult.getSearchResults();
		assertEquals(true, res.contains("6.pnml"));
		
	}
	
	public void test015() throws ClassNotFoundException, SQLException {
		PQLQueryResult queryResult = PQLTestExecutes.pqlAPI.query("SELECT * FROM * WHERE Executes(<*,\"A\",\"B\",\"C\",\"D\",\"E\",\"F\",\"A\",\"B\",\"C\",*,\"D\",\"E\",\"F\",\"H\",\"J\",\"K\",*>);");
		assertEquals(0,queryResult.getNumberOfParseErrors());
		assertEquals(1,queryResult.getSearchResults().size());
		Set<String> res = queryResult.getSearchResults();
		assertEquals(true, res.contains("6.pnml"));
		
	}

	public void test016() throws ClassNotFoundException, SQLException {
		PQLQueryResult queryResult = PQLTestExecutes.pqlAPI.query("SELECT * FROM * WHERE Executes(<*>);");
		assertEquals(0,queryResult.getNumberOfParseErrors());
		assertEquals(10,queryResult.getSearchResults().size());
				
	}
	
	public void test017() throws ClassNotFoundException, SQLException {
		PQLQueryResult queryResult = PQLTestExecutes.pqlAPI.query("SELECT * FROM * WHERE Executes(<*,*>);");
		assertEquals(0,queryResult.getNumberOfParseErrors());
		assertEquals(10,queryResult.getSearchResults().size());
				
	}
	
	public void test018() throws ClassNotFoundException, SQLException {
		PQLQueryResult queryResult = PQLTestExecutes.pqlAPI.query("SELECT * FROM * WHERE NOT Executes(<*>);");
		assertEquals(0,queryResult.getNumberOfParseErrors());
		assertEquals(0,queryResult.getSearchResults().size());
				
	}
	
	public void test019() throws ClassNotFoundException, SQLException {
		PQLQueryResult queryResult = PQLTestExecutes.pqlAPI.query("SELECT * FROM * WHERE Executes(<*,\"B\",\"D\",*,\"H\",*>);");
		assertEquals(0,queryResult.getNumberOfParseErrors());
		assertEquals(3,queryResult.getSearchResults().size());
		Set<String> res = queryResult.getSearchResults();
		assertEquals(true, res.contains("6.pnml"));
		assertEquals(true, res.contains("7.pnml"));
		assertEquals(true, res.contains("9.pnml"));
				
	}

	public void test020() throws ClassNotFoundException, SQLException {
		PQLQueryResult queryResult = PQLTestExecutes.pqlAPI.query("SELECT * FROM * WHERE Executes(<*,*,\"B\",\"D\",*,*,*,\"H\",*,*,*>);");
		assertEquals(0,queryResult.getNumberOfParseErrors());
		assertEquals(3,queryResult.getSearchResults().size());
		Set<String> res = queryResult.getSearchResults();
		assertEquals(true, res.contains("6.pnml"));
		assertEquals(true, res.contains("7.pnml"));
		assertEquals(true, res.contains("9.pnml"));
				
	}


}
