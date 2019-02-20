package org.pql.test;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
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
 * @author Artem Polyvyanyy
 */
public class PQLTest {
	private static PQLAPI	pqlAPI	= null;

	@BeforeClass
	public static void beforeClass() throws ClassNotFoundException, SQLException, IOException {
		PQLIniFile iniFile = new PQLIniFile();
		if (!iniFile.load()) {
			System.out.println("ERROR: Cannot load PQL ini file.");
			return;
		}

		PQLTest.pqlAPI = new PQLAPI(iniFile.getMySQLURL(), iniFile.getMySQLUser(), iniFile.getMySQLPassword(),
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

		PQLTest.pqlAPI.reset();

		System.out.println("Reset done!");
		PQLTest.pqlAPI.storeModel(new File("./pnml/pql/1.pnml"),"1.pnml", "/");
		System.out.println("1.pnml stored");
		PQLTest.pqlAPI.storeModel(new File("./pnml/pql/2.pnml"),"2.pnml", "/");
		System.out.println("2.pnml stored");
		PQLTest.pqlAPI.storeModel(new File("./pnml/pql/3.pnml"),"3.pnml", "/");
		System.out.println("3.pnml stored");
		PQLTest.pqlAPI.storeModel(new File("./pnml/pql/4.pnml"),"4.pnml", "/");
		System.out.println("4.pnml stored");
		PQLTest.pqlAPI.storeModel(new File("./pnml/pql/5.pnml"),"5.pnml", "/");
		System.out.println("5.pnml stored");
		PQLTest.pqlAPI.storeModel(new File("./pnml/pql/6.pnml"),"6.pnml", "/");
		System.out.println("6.pnml stored");
		PQLTest.pqlAPI.storeModel(new File("./pnml/pql/7.pnml"),"7.pnml", "/");
		System.out.println("7.pnml stored");
		PQLTest.pqlAPI.storeModel(new File("./pnml/pql/8.pnml"),"8.pnml", "/");
		System.out.println("8.pnml stored");
		PQLTest.pqlAPI.storeModel(new File("./pnml/pql/9.pnml"),"9.pnml", "/");
		System.out.println("9.pnml stored");
		PQLTest.pqlAPI.storeModel(new File("./pnml/pql/10.pnml"),"10.pnml", "/");
		System.out.println("10.pnml stored");

		PQLTest.pqlAPI.index(PQLTest.pqlAPI.getInternalID("1.pnml"));
		System.out.println("1.pnml indexed");
		PQLTest.pqlAPI.index(PQLTest.pqlAPI.getInternalID("2.pnml"));
		System.out.println("2.pnml indexed");
		PQLTest.pqlAPI.index(PQLTest.pqlAPI.getInternalID("3.pnml"));
		System.out.println("3.pnml indexed");
		PQLTest.pqlAPI.index(PQLTest.pqlAPI.getInternalID("4.pnml"));
		System.out.println("4.pnml indexed");
		PQLTest.pqlAPI.index(PQLTest.pqlAPI.getInternalID("5.pnml"));
		System.out.println("5.pnml indexed");
		PQLTest.pqlAPI.index(PQLTest.pqlAPI.getInternalID("6.pnml"));
		System.out.println("6.pnml indexed");
		PQLTest.pqlAPI.index(PQLTest.pqlAPI.getInternalID("7.pnml"));
		System.out.println("7.pnml indexed");
		PQLTest.pqlAPI.index(PQLTest.pqlAPI.getInternalID("8.pnml"));
		System.out.println("8.pnml indexed");
		PQLTest.pqlAPI.index(PQLTest.pqlAPI.getInternalID("9.pnml"));
		System.out.println("9.pnml indexed");
		PQLTest.pqlAPI.index(PQLTest.pqlAPI.getInternalID("10.pnml"));
		System.out.println("10.pnml indexed");

	}

	@Test
	public void test001() throws ClassNotFoundException, SQLException {
		PQLQueryResult queryResult = PQLTest.pqlAPI.query("SELECT * FROM * WHERE CanOccur(\"D\") AND Conflict(\"D\", \"E\");");
		assertEquals(0,queryResult.getNumberOfParseErrors());
		assertEquals(2,queryResult.getSearchResults().size());
		Set<String> res = queryResult.getSearchResults();
		assertEquals(true, res.contains("1.pnml"));
		assertEquals(true, res.contains("5.pnml"));
		System.out.println("[TEST] Search Query 001 - OK ");
	}

	@Test
	public void test002() throws ClassNotFoundException, SQLException {
		PQLQueryResult queryResult = PQLTest.pqlAPI.query("SELECT * FROM * WHERE AlwaysOccurs(\"C\") OR Cooccur(\"B\", \"C\");");
		assertEquals(0,queryResult.getNumberOfParseErrors());
		assertEquals(6,queryResult.getSearchResults().size());
		Set<String> res = queryResult.getSearchResults();
		assertEquals(true, res.contains("1.pnml"));
		assertEquals(true, res.contains("4.pnml"));
		assertEquals(true, res.contains("6.pnml"));
		assertEquals(true, res.contains("7.pnml"));
		assertEquals(true, res.contains("9.pnml"));
		assertEquals(true, res.contains("10.pnml"));

		System.out.println("[TEST] Search Query 002 - OK ");
	}

	@Test
	public void test003() throws ClassNotFoundException, SQLException {
		PQLQueryResult queryResult = PQLTest.pqlAPI.query("SELECT * FROM * WHERE (CanOccur(\"G\") AND (NOT Conflict(\"E\", \"G\"))) OR (TotalConcurrent(\"C\", \"D\") AND AlwaysOccurs(\"D\"));");
		assertEquals(0,queryResult.getNumberOfParseErrors());
		assertEquals(5,queryResult.getSearchResults().size());
		Set<String> res = queryResult.getSearchResults();
		assertEquals(true, res.contains("4.pnml"));
		assertEquals(true, res.contains("6.pnml"));
		assertEquals(true, res.contains("7.pnml"));
		assertEquals(true, res.contains("9.pnml"));
		assertEquals(true, res.contains("10.pnml"));

		System.out.println("[TEST] Search Query 003 - OK ");
	}

	@Test
	public void test004() throws ClassNotFoundException, SQLException {
		PQLQueryResult queryResult = PQLTest.pqlAPI.query("SELECT * FROM * WHERE CanOccur({\"F\", \"G\"}, ALL) AND AlwaysOccurs({\"F\", \"G\"}, ANY);");
		assertEquals(0,queryResult.getNumberOfParseErrors());
		assertEquals(3,queryResult.getSearchResults().size());
		Set<String> res = queryResult.getSearchResults();
		assertEquals(true, res.contains("6.pnml"));
		assertEquals(true, res.contains("9.pnml"));
		assertEquals(true, res.contains("10.pnml"));

		System.out.println("[TEST] Search Query 004 - OK ");
	}

	@Test
	public void test005() throws ClassNotFoundException, SQLException {
		PQLQueryResult queryResult = PQLTest.pqlAPI.query("SELECT * FROM * WHERE Cooccur(\"B\", {\"C\", \"D\"}, ALL) AND TotalConcurrent(\"B\", {\"C\", \"D\"}, ANY);");
		assertEquals(0,queryResult.getNumberOfParseErrors());
		assertEquals(3,queryResult.getSearchResults().size());
		Set<String> res = queryResult.getSearchResults();
		assertEquals(true, res.contains("4.pnml"));
		assertEquals(true, res.contains("9.pnml"));
		assertEquals(true, res.contains("10.pnml"));

		System.out.println("[TEST] Search Query 005 - OK ");
	}

	@Test
	public void test006() throws ClassNotFoundException, SQLException {
		PQLQueryResult queryResult = PQLTest.pqlAPI.query("SELECT * FROM * WHERE Conflict({\"A\", \"B\"}, {\"E\", \"F\"}, ANY) OR (Cooccur({\"A\", \"B\"}, {\"E\", \"F\"}, EACH) AND TotalCausal({\"A\", \"B\"}, {\"E\", \"F\"}, ALL));");
		assertEquals(0,queryResult.getNumberOfParseErrors());
		assertEquals(3,queryResult.getSearchResults().size());
		Set<String> res = queryResult.getSearchResults();
		assertEquals(true, res.contains("1.pnml"));
		assertEquals(true, res.contains("2.pnml"));
		assertEquals(true, res.contains("3.pnml"));

		System.out.println("[TEST] Search Query 006 - OK ");
	}

	@Test
	public void test007() throws ClassNotFoundException, SQLException {
		PQLQueryResult queryResult = PQLTest.pqlAPI.query("SELECT * FROM * WHERE \"C\" IN (GetTasksAlwaysOccurs({\"C\"}) UNION GetTasksTotalCausal({\"C\"}, {\"B\", \"D\"}, ALL));");
		assertEquals(0,queryResult.getNumberOfParseErrors());
		assertEquals(7,queryResult.getSearchResults().size());
		Set<String> res = queryResult.getSearchResults();
		assertEquals(true, res.contains("1.pnml"));
		assertEquals(true, res.contains("2.pnml"));
		assertEquals(true, res.contains("3.pnml"));
		assertEquals(true, res.contains("4.pnml"));
		assertEquals(true, res.contains("7.pnml"));
		assertEquals(true, res.contains("9.pnml"));
		assertEquals(true, res.contains("10.pnml"));

		System.out.println("[TEST] Search Query 007 - OK ");

	}

	@Test
	public void test008() throws ClassNotFoundException, SQLException {
		PQLQueryResult queryResult = PQLTest.pqlAPI.query("SELECT * FROM * WHERE \"G\" IN (GetTasksCanOccur({\"G\"}) INTERSECT GetTasksConflict({\"G\"}, {\"D\", \"E\", \"F\"}, ANY));");
		assertEquals(0,queryResult.getNumberOfParseErrors());
		assertEquals(1,queryResult.getSearchResults().size());
		Set<String> res = queryResult.getSearchResults();
		assertEquals(true, res.contains("8.pnml"));

		System.out.println("[TEST] Search Query 008 - OK ");
	}


	@Test
	public void test009() throws ClassNotFoundException, SQLException {
		PQLQueryResult queryResult = PQLTest.pqlAPI.query("SELECT * FROM * WHERE GetTasksCooccur({\"A\", \"B\", \"C\"}, {\"D\", \"E\"}, ANY) NOT EQUALS GetTasksTotalConcurrent({\"A\", \"B\", \"C\"}, {\"D\", \"E\"}, ANY);");
		assertEquals(0,queryResult.getNumberOfParseErrors());
		assertEquals(7,queryResult.getSearchResults().size());
		Set<String> res = queryResult.getSearchResults();
		assertEquals(true, res.contains("3.pnml"));
		assertEquals(true, res.contains("4.pnml"));
		assertEquals(true, res.contains("5.pnml"));
		assertEquals(true, res.contains("6.pnml"));
		assertEquals(true, res.contains("7.pnml"));
		assertEquals(true, res.contains("9.pnml"));
		assertEquals(true, res.contains("10.pnml"));

		System.out.println("[TEST] Search Query 009 - OK ");

	}


	@Test
	public void test010() throws ClassNotFoundException, SQLException {
		PQLQueryResult queryResult = PQLTest.pqlAPI.query("SELECT * FROM * WHERE ({\"A\", \"B\", \"E\", \"F\"} EXCEPT GetTasksCooccur({\"A\", \"B\", \"E\", \"F\"}, {\"C\", \"D\"}, ALL)) OVERLAPS WITH GetTasksConflict({\"A\", \"B\", \"E\", \"F\"}, {\"C\", \"D\"}, ANY);");
		assertEquals(0,queryResult.getNumberOfParseErrors());
		assertEquals(5,queryResult.getSearchResults().size());
		Set<String> res = queryResult.getSearchResults();
		assertEquals(true, res.contains("1.pnml"));
		assertEquals(true, res.contains("2.pnml"));
		assertEquals(true, res.contains("3.pnml"));
		assertEquals(true, res.contains("5.pnml"));
		assertEquals(true, res.contains("6.pnml"));

		System.out.println("[TEST] Search Query 010 - OK ");
	}

	@Test
	public void test011() throws ClassNotFoundException, SQLException {
		PQLQueryResult queryResult = PQLTest.pqlAPI.query("SELECT * FROM (SELECT * FROM * WHERE CanCooccur(~\"A\",\"B\") AND AlwaysOccurs(~\"C\")) ;");
		assertEquals(0,queryResult.getNumberOfParseErrors());
		assertEquals(10,queryResult.getSearchResults().size());
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
		assertEquals(true, res.contains("10.pnml"));
		System.out.println("[TEST] Search SubQuery 011 - OK");
	}

	@Test
	public void test012() throws ClassNotFoundException, SQLException {
		PQLQueryResult queryResult = PQLTest.pqlAPI.query("SELECT * FROM (SELECT * FROM \"/\");");
		assertEquals(0,queryResult.getNumberOfParseErrors());
		assertEquals(10,queryResult.getSearchResults().size());
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
		assertEquals(true, res.contains("10.pnml"));
		System.out.println("[TEST] Search SubQuery 012 - OK");
	}
	@Test
	public void test013() throws ClassNotFoundException, SQLException {
		PQLQueryResult queryResult = PQLTest.pqlAPI.query("SELECT * FROM \"/\";");
		assertEquals(0,queryResult.getNumberOfParseErrors());
		assertEquals(10,queryResult.getSearchResults().size());
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
		assertEquals(true, res.contains("10.pnml"));
		System.out.println("[TEST] Folder Query 013 - OK");
	}

	@Test
	public void test014() throws ClassNotFoundException, SQLException {
		PQLTest.pqlAPI.createFolder("A", "/");
		PQLTest.pqlAPI.moveModel("/1.pnml", "/A/");
		PQLQueryResult queryResult = PQLTest.pqlAPI.query("SELECT * FROM \"/A/\";");
		assertEquals(0,queryResult.getNumberOfParseErrors());
		assertEquals(1,queryResult.getSearchResults().size());
		Set<String> res = queryResult.getSearchResults();
		assertEquals(true, res.contains("1.pnml"));
		System.out.println("[TEST] Folder Query 014 - OK");
	}

	@Test
	public void test015() throws ClassNotFoundException, SQLException {
		PQLTest.pqlAPI.createFolder("B", "/");
		PQLTest.pqlAPI.moveFolder("B", "/A/");
		PQLTest.pqlAPI.moveModel("/2.pnml", "/A/B/");
		PQLQueryResult queryResult = PQLTest.pqlAPI.query("SELECT * FROM \"/A/B/\";");
		assertEquals(0,queryResult.getNumberOfParseErrors());
		assertEquals(1,queryResult.getSearchResults().size());
		Set<String> res = queryResult.getSearchResults();
		assertEquals(true, res.contains("2.pnml"));
		System.out.println("[TEST] Folder Query 015 - OK");

	}

	@Test
	public void test016() throws ClassNotFoundException, SQLException {
		PQLTest.pqlAPI.createFolder("C", "/");
		PQLTest.pqlAPI.moveModel("/3.pnml", "/C/");
		PQLTest.pqlAPI.moveModel("/4.pnml", "/C/");
		PQLQueryResult queryResult = PQLTest.pqlAPI.query("SELECT * FROM \"/C/\";");
		assertEquals(0,queryResult.getNumberOfParseErrors());
		assertEquals(2,queryResult.getSearchResults().size());
		Set<String> res = queryResult.getSearchResults();
		assertEquals(true,res.contains("3.pnml"));
		assertEquals(true,res.contains("4.pnml"));
		System.out.println("[TEST] Folder Query 016 - OK");
	}

	@Test
	public void test017() throws ClassNotFoundException, SQLException {
		PQLTest.pqlAPI.createFolder("D", "/C/");
		PQLTest.pqlAPI.moveModel("/C/3.pnml", "/C/D/");
		PQLQueryResult queryResult = PQLTest.pqlAPI.query("SELECT * FROM \"/C/D/\";");
		assertEquals(0,queryResult.getNumberOfParseErrors());
		assertEquals(1,queryResult.getSearchResults().size());
		Set<String> res = queryResult.getSearchResults();
//		System.out.println("Result is: " + res);
		assertEquals(true,res.contains("3.pnml"));
		System.out.println("[TEST] Folder Query 017 - OK");
	}

	@Test
	public void test018() throws ClassNotFoundException, SQLException {
		PQLTest.pqlAPI.createFolder("E", "/C/D");
		PQLTest.pqlAPI.moveModel("/5.pnml", "/C/D/E");
		PQLQueryResult queryResult = PQLTest.pqlAPI.query("SELECT * FROM \"/C/D/E\";");
		assertEquals(0,queryResult.getNumberOfParseErrors());
		assertEquals(1,queryResult.getSearchResults().size());
		Set<String> res = queryResult.getSearchResults();
//		System.out.println("Result is: " + res);
		assertEquals(true,res.contains("5.pnml"));
		System.out.println("[TEST] Folder Query 018 - OK");
	}

	@Test
	public void test019() throws ClassNotFoundException, SQLException {
		PQLTest.pqlAPI.createFolder("F", "/C/");
		PQLTest.pqlAPI.moveModel("/6.pnml", "/C/F/");
		PQLQueryResult queryResult = PQLTest.pqlAPI.query("SELECT * FROM \"/C/F/\";");
		assertEquals(0,queryResult.getNumberOfParseErrors());
		assertEquals(1,queryResult.getSearchResults().size());
		Set<String> res = queryResult.getSearchResults();
//		System.out.println("Result is: " + res);
		assertEquals(true,res.contains("6.pnml"));
		System.out.println("[TEST] Folder Query 019 - OK");
	}

	@Test
	public void test020() throws ClassNotFoundException, SQLException {
		PQLTest.pqlAPI.createFolder("I", "/A/B/");
		PQLTest.pqlAPI.createFolder("J", "/A/B/I");
		PQLTest.pqlAPI.moveModel("/7.pnml", "/A/B");
		PQLQueryResult queryResult = PQLTest.pqlAPI.query("SELECT * FROM \"/A/B/\";");
		assertEquals(0,queryResult.getNumberOfParseErrors());
		assertEquals(2,queryResult.getSearchResults().size());
		Set<String> res = queryResult.getSearchResults();
//		System.out.println("Result is: " + res);
		assertEquals(true,res.contains("7.pnml"));
		System.out.println("[TEST] Folder Query 020 - OK");
	}

	@Test
	public void test021() throws ClassNotFoundException, SQLException {
		PQLTest.pqlAPI.createFolder("G", "/A/B/");
		PQLTest.pqlAPI.moveModel("/8.pnml", "/A/B/G");
		PQLQueryResult queryResult = PQLTest.pqlAPI.query("SELECT * FROM \"/A/B/G/\";");
		assertEquals(0,queryResult.getNumberOfParseErrors());
		assertEquals(1,queryResult.getSearchResults().size());
		Set<String> res = queryResult.getSearchResults();
//		System.out.println("Result is: " + res);
		assertEquals(true,res.contains("8.pnml"));
		System.out.println("[TEST] Folder Query 021 - OK");
	}
	@Test
	public void test022() throws ClassNotFoundException, SQLException {
			PQLTest.pqlAPI.listFolders();
			PQLTest.pqlAPI.moveModel("/A/1.pnml", "/");
			PQLTest.pqlAPI.moveModel("/A/B/2.pnml", "/");
			PQLTest.pqlAPI.moveModel("/C/D/3.pnml", "/");
			PQLTest.pqlAPI.moveModel("/C/4.pnml", "/");
			PQLTest.pqlAPI.moveModel("/C/D/E/5.pnml", "/");
			PQLTest.pqlAPI.moveModel("/C/F/6.pnml", "/");
			PQLTest.pqlAPI.moveModel("/A/B/7.pnml", "/");
			PQLTest.pqlAPI.moveModel("/A/B/G/8.pnml", "/");
			PQLTest.pqlAPI.deleteFolder("/A/");
			PQLTest.pqlAPI.deleteFolder("/C/");
			PQLQueryResult queryResult = PQLTest.pqlAPI.query("SELECT * FROM \"/\";");
			assertEquals(0,queryResult.getNumberOfParseErrors());
			assertEquals(10,queryResult.getSearchResults().size());
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
			assertEquals(true, res.contains("10.pnml"));

		System.out.println("\n*** [TEST] Folder Query Reset 022 - OK ***\n");
	}
}
