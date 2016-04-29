package org.pql.test;

import java.io.IOException;

import junit.framework.TestCase;

import org.antlr.v4.runtime.misc.TestRig;
import org.junit.Test;

public class PQLQueryParseTest extends TestCase {

	@Test
	public void test() throws IOException, ClassNotFoundException {
		String[] input = new String[4];
		input[0] = "PQL";
		input[1] = "query";
		input[2] = "-gui";
		input[3] = "SELECT.4C.pql";
		try {
			TestRig testRig = new TestRig(input);
			testRig.process();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
