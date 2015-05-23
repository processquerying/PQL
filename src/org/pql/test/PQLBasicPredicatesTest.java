package org.pql.test;

import java.io.IOException;

import junit.framework.TestCase;

import org.jbpt.petri.NetSystem;
import org.jbpt.petri.Place;
import org.jbpt.petri.Transition;
import org.junit.Test;

public class PQLBasicPredicatesTest extends TestCase {

	@Test
	public void test() throws IOException {
		Place p1 = new Place("p1");
		Place p2 = new Place("p2");
		Place p3 = new Place("p3");
		Place p4 = new Place("p4");
		Place p5 = new Place("p5");
		Place p6 = new Place("p6");
		Place p7 = new Place("p7");
		Place p8 = new Place("p8");
		Place p9 = new Place("p9");
		
		Transition t1 = new Transition("t1");
		Transition t2 = new Transition("t2");
		Transition t3 = new Transition("t3");
		Transition t4 = new Transition("t4");
		Transition t5 = new Transition("t5");
		Transition t6 = new Transition("t6");
		Transition t7 = new Transition("t7");
		Transition t8 = new Transition("t8");
		Transition t9 = new Transition("t9");
		
		NetSystem sys = new NetSystem();
		
		sys.addFlow(p1, t1);
		sys.addFlow(t1, p2);
		sys.addFlow(p2, t2);
		sys.addFlow(t2, p3);
		sys.addFlow(p3, t3);
		sys.addFlow(t3, p4);
		sys.addFlow(p4, t9);
		sys.addFlow(p1, t4);
		sys.addFlow(t4, p5);
		sys.addFlow(p5, t5);
		sys.addFlow(t5, p6);
		sys.addFlow(p6, t6);
		sys.addFlow(t6, p7);
		sys.addFlow(p7, t7);
		sys.addFlow(t7, p8);
		sys.addFlow(p8, t9);
		sys.addFlow(t9, p9);
		sys.addFlow(t1, p6);
		sys.addFlow(t4, p2);
		sys.addFlow(p8, t8);
		sys.addFlow(t8, p5);
		
		sys.loadNaturalMarking();
		
		//IOUtils.invokeDOT(".", "sys.png", sys.toDOT());
		
		//AbstractPQLBasicPredicates<Flow,Node,Place,Transition,Marking> PQL = new AbstractPQLBasicPredicates<Flow,Node,Place,Transition,Marking>(sys,"mac");

		// can occur (posoccur)
		/*assertEquals(ThreeValuedLogicValue.TRUE,PQL.canOccur(t1));
		assertEquals(ThreeValuedLogicValue.TRUE,PQL.canOccur(t2));
		assertEquals(ThreeValuedLogicValue.TRUE,PQL.canOccur(t3));
		assertEquals(ThreeValuedLogicValue.TRUE,PQL.canOccur(t4));
		assertEquals(ThreeValuedLogicValue.TRUE,PQL.canOccur(t5));
		assertEquals(ThreeValuedLogicValue.TRUE,PQL.canOccur(t6));
		assertEquals(ThreeValuedLogicValue.TRUE,PQL.canOccur(t7));
		assertEquals(ThreeValuedLogicValue.TRUE,PQL.canOccur(t8));
		assertEquals(ThreeValuedLogicValue.TRUE,PQL.canOccur(t9));*/
		
		// always occurs
		/*assertEquals(ThreeValuedLogicValue.FALSE,PQL.alwaysOccurs(t1));
		assertEquals(ThreeValuedLogicValue.TRUE,PQL.alwaysOccurs(t2));
		assertEquals(ThreeValuedLogicValue.TRUE,PQL.alwaysOccurs(t3));
		assertEquals(ThreeValuedLogicValue.FALSE,PQL.alwaysOccurs(t4));
		assertEquals(ThreeValuedLogicValue.FALSE,PQL.alwaysOccurs(t5));
		assertEquals(ThreeValuedLogicValue.TRUE,PQL.alwaysOccurs(t6));
		assertEquals(ThreeValuedLogicValue.TRUE,PQL.alwaysOccurs(t7));
		assertEquals(ThreeValuedLogicValue.FALSE,PQL.alwaysOccurs(t8));
		assertEquals(ThreeValuedLogicValue.TRUE,PQL.alwaysOccurs(t9));*/
		
		// TODO update all tests that follow
		
		/*// can occur and is eventually succeeded
		assertFalse(PQL.canOccurAndIsEventuallySucceeded(t1,t1));
		assertTrue(PQL.canOccurAndIsEventuallySucceeded(t1,t2));
		assertTrue(PQL.canOccurAndIsEventuallySucceeded(t1,t3));
		assertFalse(PQL.canOccurAndIsEventuallySucceeded(t1,t4));
		assertTrue(PQL.canOccurAndIsEventuallySucceeded(t1,t5));
		assertTrue(PQL.canOccurAndIsEventuallySucceeded(t1,t6));
		assertTrue(PQL.canOccurAndIsEventuallySucceeded(t1,t7));
		assertTrue(PQL.canOccurAndIsEventuallySucceeded(t1,t8));
		assertTrue(PQL.canOccurAndIsEventuallySucceeded(t1,t9));

		assertFalse(PQL.canOccurAndIsEventuallySucceeded(t2,t1));
		assertFalse(PQL.canOccurAndIsEventuallySucceeded(t2,t2));
		assertTrue(PQL.canOccurAndIsEventuallySucceeded(t2,t3));
		assertFalse(PQL.canOccurAndIsEventuallySucceeded(t2,t4));
		assertTrue(PQL.canOccurAndIsEventuallySucceeded(t2,t5));
		assertTrue(PQL.canOccurAndIsEventuallySucceeded(t2,t6));
		assertTrue(PQL.canOccurAndIsEventuallySucceeded(t2,t7));
		assertTrue(PQL.canOccurAndIsEventuallySucceeded(t2,t8));
		assertTrue(PQL.canOccurAndIsEventuallySucceeded(t2,t9));
		
		assertFalse(PQL.canOccurAndIsEventuallySucceeded(t3,t1));
		assertFalse(PQL.canOccurAndIsEventuallySucceeded(t3,t2));
		assertFalse(PQL.canOccurAndIsEventuallySucceeded(t3,t3));
		assertFalse(PQL.canOccurAndIsEventuallySucceeded(t3,t4));
		assertTrue(PQL.canOccurAndIsEventuallySucceeded(t3,t5));
		assertTrue(PQL.canOccurAndIsEventuallySucceeded(t3,t6));
		assertTrue(PQL.canOccurAndIsEventuallySucceeded(t3,t7));
		assertTrue(PQL.canOccurAndIsEventuallySucceeded(t3,t8));
		assertTrue(PQL.canOccurAndIsEventuallySucceeded(t3,t9));
		
		assertFalse(PQL.canOccurAndIsEventuallySucceeded(t4,t1));
		assertTrue(PQL.canOccurAndIsEventuallySucceeded(t4,t2));
		assertTrue(PQL.canOccurAndIsEventuallySucceeded(t4,t3));
		assertFalse(PQL.canOccurAndIsEventuallySucceeded(t4,t4));
		assertTrue(PQL.canOccurAndIsEventuallySucceeded(t4,t5));
		assertTrue(PQL.canOccurAndIsEventuallySucceeded(t4,t6));
		assertTrue(PQL.canOccurAndIsEventuallySucceeded(t4,t7));
		assertTrue(PQL.canOccurAndIsEventuallySucceeded(t4,t8));
		assertTrue(PQL.canOccurAndIsEventuallySucceeded(t4,t9));
		
		assertFalse(PQL.canOccurAndIsEventuallySucceeded(t5,t1));
		assertTrue(PQL.canOccurAndIsEventuallySucceeded(t5,t2));
		assertTrue(PQL.canOccurAndIsEventuallySucceeded(t5,t3));
		assertFalse(PQL.canOccurAndIsEventuallySucceeded(t5,t4));
		assertTrue(PQL.canOccurAndIsEventuallySucceeded(t5,t5));
		assertTrue(PQL.canOccurAndIsEventuallySucceeded(t5,t6));
		assertTrue(PQL.canOccurAndIsEventuallySucceeded(t5,t7));
		assertTrue(PQL.canOccurAndIsEventuallySucceeded(t5,t8));
		assertTrue(PQL.canOccurAndIsEventuallySucceeded(t5,t9));
		
		assertFalse(PQL.canOccurAndIsEventuallySucceeded(t6,t1));
		assertTrue(PQL.canOccurAndIsEventuallySucceeded(t6,t2));
		assertTrue(PQL.canOccurAndIsEventuallySucceeded(t6,t3));
		assertFalse(PQL.canOccurAndIsEventuallySucceeded(t6,t4));
		assertTrue(PQL.canOccurAndIsEventuallySucceeded(t6,t5));
		assertTrue(PQL.canOccurAndIsEventuallySucceeded(t6,t6));
		assertTrue(PQL.canOccurAndIsEventuallySucceeded(t6,t7));
		assertTrue(PQL.canOccurAndIsEventuallySucceeded(t6,t8));
		assertTrue(PQL.canOccurAndIsEventuallySucceeded(t6,t9));
		
		assertFalse(PQL.canOccurAndIsEventuallySucceeded(t7,t1));
		assertTrue(PQL.canOccurAndIsEventuallySucceeded(t7,t2));
		assertTrue(PQL.canOccurAndIsEventuallySucceeded(t7,t3));
		assertFalse(PQL.canOccurAndIsEventuallySucceeded(t7,t4));
		assertTrue(PQL.canOccurAndIsEventuallySucceeded(t7,t5));
		assertTrue(PQL.canOccurAndIsEventuallySucceeded(t7,t6));
		assertTrue(PQL.canOccurAndIsEventuallySucceeded(t7,t7));
		assertTrue(PQL.canOccurAndIsEventuallySucceeded(t7,t8));
		assertTrue(PQL.canOccurAndIsEventuallySucceeded(t7,t9));
		
		assertFalse(PQL.canOccurAndIsEventuallySucceeded(t8,t1));
		assertTrue(PQL.canOccurAndIsEventuallySucceeded(t8,t2));
		assertTrue(PQL.canOccurAndIsEventuallySucceeded(t8,t3));
		assertFalse(PQL.canOccurAndIsEventuallySucceeded(t8,t4));
		assertTrue(PQL.canOccurAndIsEventuallySucceeded(t8,t5));
		assertTrue(PQL.canOccurAndIsEventuallySucceeded(t8,t6));
		assertTrue(PQL.canOccurAndIsEventuallySucceeded(t8,t7));
		assertTrue(PQL.canOccurAndIsEventuallySucceeded(t8,t8));
		assertTrue(PQL.canOccurAndIsEventuallySucceeded(t8,t9));
		
		assertFalse(PQL.canOccurAndIsEventuallySucceeded(t9,t1));
		assertFalse(PQL.canOccurAndIsEventuallySucceeded(t9,t2));
		assertFalse(PQL.canOccurAndIsEventuallySucceeded(t9,t3));
		assertFalse(PQL.canOccurAndIsEventuallySucceeded(t9,t4));
		assertFalse(PQL.canOccurAndIsEventuallySucceeded(t9,t5));
		assertFalse(PQL.canOccurAndIsEventuallySucceeded(t9,t6));
		assertFalse(PQL.canOccurAndIsEventuallySucceeded(t9,t7));
		assertFalse(PQL.canOccurAndIsEventuallySucceeded(t9,t8));
		assertFalse(PQL.canOccurAndIsEventuallySucceeded(t9,t9));
		
		// can occur and is immediately succeeded (exists isucc_any)
		assertFalse(PQL.canOccurAndIsImmediatelySucceeded(t1,t1));
		assertTrue(PQL.canOccurAndIsImmediatelySucceeded(t1,t2));
		assertFalse(PQL.canOccurAndIsImmediatelySucceeded(t1,t3));
		assertFalse(PQL.canOccurAndIsImmediatelySucceeded(t1,t4));
		assertFalse(PQL.canOccurAndIsImmediatelySucceeded(t1,t5));
		assertTrue(PQL.canOccurAndIsImmediatelySucceeded(t1,t6));
		assertFalse(PQL.canOccurAndIsImmediatelySucceeded(t1,t7));
		assertFalse(PQL.canOccurAndIsImmediatelySucceeded(t1,t8));
		assertFalse(PQL.canOccurAndIsImmediatelySucceeded(t1,t9));

		assertFalse(PQL.canOccurAndIsImmediatelySucceeded(t2,t1));
		assertFalse(PQL.canOccurAndIsImmediatelySucceeded(t2,t2));
		assertTrue(PQL.canOccurAndIsImmediatelySucceeded(t2,t3));
		assertFalse(PQL.canOccurAndIsImmediatelySucceeded(t2,t4));
		assertTrue(PQL.canOccurAndIsImmediatelySucceeded(t2,t5));
		assertTrue(PQL.canOccurAndIsImmediatelySucceeded(t2,t6));
		assertTrue(PQL.canOccurAndIsImmediatelySucceeded(t2,t7));
		assertTrue(PQL.canOccurAndIsImmediatelySucceeded(t2,t8));
		assertFalse(PQL.canOccurAndIsImmediatelySucceeded(t2,t9));
		
		assertFalse(PQL.canOccurAndIsImmediatelySucceeded(t3,t1));
		assertFalse(PQL.canOccurAndIsImmediatelySucceeded(t3,t2));
		assertFalse(PQL.canOccurAndIsImmediatelySucceeded(t3,t3));
		assertFalse(PQL.canOccurAndIsImmediatelySucceeded(t3,t4));
		assertTrue(PQL.canOccurAndIsImmediatelySucceeded(t3,t5));
		assertTrue(PQL.canOccurAndIsImmediatelySucceeded(t3,t6));
		assertTrue(PQL.canOccurAndIsImmediatelySucceeded(t3,t7));
		assertTrue(PQL.canOccurAndIsImmediatelySucceeded(t3,t8));
		assertTrue(PQL.canOccurAndIsImmediatelySucceeded(t3,t9));
		
		assertFalse(PQL.canOccurAndIsImmediatelySucceeded(t4,t1));
		assertTrue(PQL.canOccurAndIsImmediatelySucceeded(t4,t2));
		assertFalse(PQL.canOccurAndIsImmediatelySucceeded(t4,t3));
		assertFalse(PQL.canOccurAndIsImmediatelySucceeded(t4,t4));
		assertTrue(PQL.canOccurAndIsImmediatelySucceeded(t4,t5));
		assertFalse(PQL.canOccurAndIsImmediatelySucceeded(t4,t6));
		assertFalse(PQL.canOccurAndIsImmediatelySucceeded(t4,t7));
		assertFalse(PQL.canOccurAndIsImmediatelySucceeded(t4,t8));
		assertFalse(PQL.canOccurAndIsImmediatelySucceeded(t4,t9));
		
		assertFalse(PQL.canOccurAndIsImmediatelySucceeded(t5,t1));
		assertTrue(PQL.canOccurAndIsImmediatelySucceeded(t5,t2));
		assertTrue(PQL.canOccurAndIsImmediatelySucceeded(t5,t3));
		assertFalse(PQL.canOccurAndIsImmediatelySucceeded(t5,t4));
		assertFalse(PQL.canOccurAndIsImmediatelySucceeded(t5,t5));
		assertTrue(PQL.canOccurAndIsImmediatelySucceeded(t5,t6));
		assertFalse(PQL.canOccurAndIsImmediatelySucceeded(t5,t7));
		assertFalse(PQL.canOccurAndIsImmediatelySucceeded(t5,t8));
		assertFalse(PQL.canOccurAndIsImmediatelySucceeded(t5,t9));
		
		assertFalse(PQL.canOccurAndIsImmediatelySucceeded(t6,t1));
		assertTrue(PQL.canOccurAndIsImmediatelySucceeded(t6,t2));
		assertTrue(PQL.canOccurAndIsImmediatelySucceeded(t6,t3));
		assertFalse(PQL.canOccurAndIsImmediatelySucceeded(t6,t4));
		assertFalse(PQL.canOccurAndIsImmediatelySucceeded(t6,t5));
		assertFalse(PQL.canOccurAndIsImmediatelySucceeded(t6,t6));
		assertTrue(PQL.canOccurAndIsImmediatelySucceeded(t6,t7));
		assertFalse(PQL.canOccurAndIsImmediatelySucceeded(t6,t8));
		assertFalse(PQL.canOccurAndIsImmediatelySucceeded(t6,t9));
		
		assertFalse(PQL.canOccurAndIsImmediatelySucceeded(t7,t1));
		assertTrue(PQL.canOccurAndIsImmediatelySucceeded(t7,t2));
		assertTrue(PQL.canOccurAndIsImmediatelySucceeded(t7,t3));
		assertFalse(PQL.canOccurAndIsImmediatelySucceeded(t7,t4));
		assertFalse(PQL.canOccurAndIsImmediatelySucceeded(t7,t5));
		assertFalse(PQL.canOccurAndIsImmediatelySucceeded(t7,t6));
		assertFalse(PQL.canOccurAndIsImmediatelySucceeded(t7,t7));
		assertTrue(PQL.canOccurAndIsImmediatelySucceeded(t7,t8));
		assertTrue(PQL.canOccurAndIsImmediatelySucceeded(t7,t9));
		
		assertFalse(PQL.canOccurAndIsImmediatelySucceeded(t8,t1));
		assertTrue(PQL.canOccurAndIsImmediatelySucceeded(t8,t2));
		assertTrue(PQL.canOccurAndIsImmediatelySucceeded(t8,t3));
		assertFalse(PQL.canOccurAndIsImmediatelySucceeded(t8,t4));
		assertTrue(PQL.canOccurAndIsImmediatelySucceeded(t8,t5));
		assertFalse(PQL.canOccurAndIsImmediatelySucceeded(t8,t6));
		assertFalse(PQL.canOccurAndIsImmediatelySucceeded(t8,t7));
		assertFalse(PQL.canOccurAndIsImmediatelySucceeded(t8,t8));
		assertFalse(PQL.canOccurAndIsImmediatelySucceeded(t8,t9));
		
		assertFalse(PQL.canOccurAndIsImmediatelySucceeded(t9,t1));
		assertFalse(PQL.canOccurAndIsImmediatelySucceeded(t9,t2));
		assertFalse(PQL.canOccurAndIsImmediatelySucceeded(t9,t3));
		assertFalse(PQL.canOccurAndIsImmediatelySucceeded(t9,t4));
		assertFalse(PQL.canOccurAndIsImmediatelySucceeded(t9,t5));
		assertFalse(PQL.canOccurAndIsImmediatelySucceeded(t9,t6));
		assertFalse(PQL.canOccurAndIsImmediatelySucceeded(t9,t7));
		assertFalse(PQL.canOccurAndIsImmediatelySucceeded(t9,t8));
		assertFalse(PQL.canOccurAndIsImmediatelySucceeded(t9,t9));
				
		// can occur and is immediately preceded (exists ipred_any)
		assertFalse(PQL.canOccurAndIsImmediatelyPreceded(t1,t1));
		assertFalse(PQL.canOccurAndIsImmediatelyPreceded(t1,t2));
		assertFalse(PQL.canOccurAndIsImmediatelyPreceded(t1,t3));
		assertFalse(PQL.canOccurAndIsImmediatelyPreceded(t1,t4));
		assertFalse(PQL.canOccurAndIsImmediatelyPreceded(t1,t5));
		assertFalse(PQL.canOccurAndIsImmediatelyPreceded(t1,t6));
		assertFalse(PQL.canOccurAndIsImmediatelyPreceded(t1,t7));
		assertFalse(PQL.canOccurAndIsImmediatelyPreceded(t1,t8));
		assertFalse(PQL.canOccurAndIsImmediatelyPreceded(t1,t9));

		assertTrue(PQL.canOccurAndIsImmediatelyPreceded(t2,t1));
		assertFalse(PQL.canOccurAndIsImmediatelyPreceded(t2,t2));
		assertFalse(PQL.canOccurAndIsImmediatelyPreceded(t2,t3));
		assertTrue(PQL.canOccurAndIsImmediatelyPreceded(t2,t4));
		assertTrue(PQL.canOccurAndIsImmediatelyPreceded(t2,t5));
		assertTrue(PQL.canOccurAndIsImmediatelyPreceded(t2,t6));
		assertTrue(PQL.canOccurAndIsImmediatelyPreceded(t2,t7));
		assertTrue(PQL.canOccurAndIsImmediatelyPreceded(t2,t8));
		assertFalse(PQL.canOccurAndIsImmediatelyPreceded(t2,t9));
		
		assertFalse(PQL.canOccurAndIsImmediatelyPreceded(t3,t1));
		assertTrue(PQL.canOccurAndIsImmediatelyPreceded(t3,t2));
		assertFalse(PQL.canOccurAndIsImmediatelyPreceded(t3,t3));
		assertFalse(PQL.canOccurAndIsImmediatelyPreceded(t3,t4));
		assertTrue(PQL.canOccurAndIsImmediatelyPreceded(t3,t5));
		assertTrue(PQL.canOccurAndIsImmediatelyPreceded(t3,t6));
		assertTrue(PQL.canOccurAndIsImmediatelyPreceded(t3,t7));
		assertTrue(PQL.canOccurAndIsImmediatelyPreceded(t3,t8));
		assertFalse(PQL.canOccurAndIsImmediatelyPreceded(t3,t9));
		
		assertFalse(PQL.canOccurAndIsImmediatelyPreceded(t4,t1));
		assertFalse(PQL.canOccurAndIsImmediatelyPreceded(t4,t2));
		assertFalse(PQL.canOccurAndIsImmediatelyPreceded(t4,t3));
		assertFalse(PQL.canOccurAndIsImmediatelyPreceded(t4,t4));
		assertFalse(PQL.canOccurAndIsImmediatelyPreceded(t4,t5));
		assertFalse(PQL.canOccurAndIsImmediatelyPreceded(t4,t6));
		assertFalse(PQL.canOccurAndIsImmediatelyPreceded(t4,t7));
		assertFalse(PQL.canOccurAndIsImmediatelyPreceded(t4,t8));
		assertFalse(PQL.canOccurAndIsImmediatelyPreceded(t4,t9));
		
		assertFalse(PQL.canOccurAndIsImmediatelyPreceded(t5,t1));
		assertTrue(PQL.canOccurAndIsImmediatelyPreceded(t5,t2));
		assertTrue(PQL.canOccurAndIsImmediatelyPreceded(t5,t3));
		assertTrue(PQL.canOccurAndIsImmediatelyPreceded(t5,t4));
		assertFalse(PQL.canOccurAndIsImmediatelyPreceded(t5,t5));
		assertFalse(PQL.canOccurAndIsImmediatelyPreceded(t5,t6));
		assertFalse(PQL.canOccurAndIsImmediatelyPreceded(t5,t7));
		assertTrue(PQL.canOccurAndIsImmediatelyPreceded(t5,t8));
		assertFalse(PQL.canOccurAndIsImmediatelyPreceded(t5,t9));
		
		assertTrue(PQL.canOccurAndIsImmediatelyPreceded(t6,t1));
		assertTrue(PQL.canOccurAndIsImmediatelyPreceded(t6,t2));
		assertTrue(PQL.canOccurAndIsImmediatelyPreceded(t6,t3));
		assertFalse(PQL.canOccurAndIsImmediatelyPreceded(t6,t4));
		assertTrue(PQL.canOccurAndIsImmediatelyPreceded(t6,t5));
		assertFalse(PQL.canOccurAndIsImmediatelyPreceded(t6,t6));
		assertFalse(PQL.canOccurAndIsImmediatelyPreceded(t6,t7));
		assertFalse(PQL.canOccurAndIsImmediatelyPreceded(t6,t8));
		assertFalse(PQL.canOccurAndIsImmediatelyPreceded(t6,t9));
		
		assertFalse(PQL.canOccurAndIsImmediatelyPreceded(t7,t1));
		assertTrue(PQL.canOccurAndIsImmediatelyPreceded(t7,t2));
		assertTrue(PQL.canOccurAndIsImmediatelyPreceded(t7,t3));
		assertFalse(PQL.canOccurAndIsImmediatelyPreceded(t7,t4));
		assertFalse(PQL.canOccurAndIsImmediatelyPreceded(t7,t5));
		assertTrue(PQL.canOccurAndIsImmediatelyPreceded(t7,t6));
		assertFalse(PQL.canOccurAndIsImmediatelyPreceded(t7,t7));
		assertFalse(PQL.canOccurAndIsImmediatelyPreceded(t7,t8));
		assertFalse(PQL.canOccurAndIsImmediatelyPreceded(t7,t9));
		
		assertFalse(PQL.canOccurAndIsImmediatelyPreceded(t8,t1));
		assertTrue(PQL.canOccurAndIsImmediatelyPreceded(t8,t2));
		assertTrue(PQL.canOccurAndIsImmediatelyPreceded(t8,t3));
		assertFalse(PQL.canOccurAndIsImmediatelyPreceded(t8,t4));
		assertFalse(PQL.canOccurAndIsImmediatelyPreceded(t8,t5));
		assertFalse(PQL.canOccurAndIsImmediatelyPreceded(t8,t6));
		assertTrue(PQL.canOccurAndIsImmediatelyPreceded(t8,t7));
		assertFalse(PQL.canOccurAndIsImmediatelyPreceded(t8,t8));
		assertFalse(PQL.canOccurAndIsImmediatelyPreceded(t8,t9));
		
		assertFalse(PQL.canOccurAndIsImmediatelyPreceded(t9,t1));
		assertFalse(PQL.canOccurAndIsImmediatelyPreceded(t9,t2));
		assertTrue(PQL.canOccurAndIsImmediatelyPreceded(t9,t3));
		assertFalse(PQL.canOccurAndIsImmediatelyPreceded(t9,t4));
		assertFalse(PQL.canOccurAndIsImmediatelyPreceded(t9,t5));
		assertFalse(PQL.canOccurAndIsImmediatelyPreceded(t9,t6));
		assertTrue(PQL.canOccurAndIsImmediatelyPreceded(t9,t7));
		assertFalse(PQL.canOccurAndIsImmediatelyPreceded(t9,t8));
		assertFalse(PQL.canOccurAndIsImmediatelyPreceded(t9,t9));
		
		// always occurs and every occurrence is immediately succeeded
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelySucceeded(t1,t1));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelySucceeded(t1,t2));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelySucceeded(t1,t3));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelySucceeded(t1,t4));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelySucceeded(t1,t5));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelySucceeded(t1,t6));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelySucceeded(t1,t7));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelySucceeded(t1,t8));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelySucceeded(t1,t9));

		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelySucceeded(t2,t1));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelySucceeded(t2,t2));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelySucceeded(t2,t3));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelySucceeded(t2,t4));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelySucceeded(t2,t5));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelySucceeded(t2,t6));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelySucceeded(t2,t7));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelySucceeded(t2,t8));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelySucceeded(t2,t9));
		
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelySucceeded(t3,t1));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelySucceeded(t3,t2));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelySucceeded(t3,t3));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelySucceeded(t3,t4));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelySucceeded(t3,t5));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelySucceeded(t3,t6));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelySucceeded(t3,t7));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelySucceeded(t3,t8));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelySucceeded(t3,t9));
		
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelySucceeded(t4,t1));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelySucceeded(t4,t2));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelySucceeded(t4,t3));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelySucceeded(t4,t4));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelySucceeded(t4,t5));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelySucceeded(t4,t6));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelySucceeded(t4,t7));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelySucceeded(t4,t8));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelySucceeded(t4,t9));
		
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelySucceeded(t5,t1));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelySucceeded(t5,t2));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelySucceeded(t5,t3));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelySucceeded(t5,t4));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelySucceeded(t5,t5));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelySucceeded(t5,t6));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelySucceeded(t5,t7));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelySucceeded(t5,t8));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelySucceeded(t5,t9));
		
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelySucceeded(t6,t1));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelySucceeded(t6,t2));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelySucceeded(t6,t3));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelySucceeded(t6,t4));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelySucceeded(t6,t5));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelySucceeded(t6,t6));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelySucceeded(t6,t7));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelySucceeded(t6,t8));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelySucceeded(t6,t9));
		
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelySucceeded(t7,t1));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelySucceeded(t7,t2));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelySucceeded(t7,t3));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelySucceeded(t7,t4));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelySucceeded(t7,t5));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelySucceeded(t7,t6));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelySucceeded(t7,t7));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelySucceeded(t7,t8));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelySucceeded(t7,t9));
		
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelySucceeded(t8,t1));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelySucceeded(t8,t2));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelySucceeded(t8,t3));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelySucceeded(t8,t4));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelySucceeded(t8,t5));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelySucceeded(t8,t6));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelySucceeded(t8,t7));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelySucceeded(t8,t8));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelySucceeded(t8,t9));
		
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelySucceeded(t9,t1));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelySucceeded(t9,t2));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelySucceeded(t9,t3));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelySucceeded(t9,t4));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelySucceeded(t9,t5));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelySucceeded(t9,t6));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelySucceeded(t9,t7));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelySucceeded(t9,t8));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelySucceeded(t9,t9));
		
		// always occurs and every occurrence is immediately preceded
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelyPreceded(t1,t1));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelyPreceded(t1,t2));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelyPreceded(t1,t3));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelyPreceded(t1,t4));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelyPreceded(t1,t5));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelyPreceded(t1,t6));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelyPreceded(t1,t7));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelyPreceded(t1,t8));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelyPreceded(t1,t9));

		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelyPreceded(t2,t1));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelyPreceded(t2,t2));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelyPreceded(t2,t3));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelyPreceded(t2,t4));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelyPreceded(t2,t5));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelyPreceded(t2,t6));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelyPreceded(t2,t7));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelyPreceded(t2,t8));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelyPreceded(t2,t9));
		
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelyPreceded(t3,t1));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelyPreceded(t3,t2));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelyPreceded(t3,t3));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelyPreceded(t3,t4));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelyPreceded(t3,t5));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelyPreceded(t3,t6));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelyPreceded(t3,t7));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelyPreceded(t3,t8));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelyPreceded(t3,t9));
		
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelyPreceded(t4,t1));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelyPreceded(t4,t2));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelyPreceded(t4,t3));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelyPreceded(t4,t4));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelyPreceded(t4,t5));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelyPreceded(t4,t6));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelyPreceded(t4,t7));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelyPreceded(t4,t8));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelyPreceded(t4,t9));
		
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelyPreceded(t5,t1));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelyPreceded(t5,t2));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelyPreceded(t5,t3));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelyPreceded(t5,t4));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelyPreceded(t5,t5));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelyPreceded(t5,t6));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelyPreceded(t5,t7));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelyPreceded(t5,t8));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelyPreceded(t5,t9));
		
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelyPreceded(t6,t1));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelyPreceded(t6,t2));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelyPreceded(t6,t3));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelyPreceded(t6,t4));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelyPreceded(t6,t5));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelyPreceded(t6,t6));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelyPreceded(t6,t7));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelyPreceded(t6,t8));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelyPreceded(t6,t9));
		
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelyPreceded(t7,t1));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelyPreceded(t7,t2));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelyPreceded(t7,t3));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelyPreceded(t7,t4));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelyPreceded(t7,t5));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelyPreceded(t7,t6));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelyPreceded(t7,t7));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelyPreceded(t7,t8));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelyPreceded(t7,t9));
		
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelyPreceded(t8,t1));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelyPreceded(t8,t2));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelyPreceded(t8,t3));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelyPreceded(t8,t4));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelyPreceded(t8,t5));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelyPreceded(t8,t6));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelyPreceded(t8,t7));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelyPreceded(t8,t8));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelyPreceded(t8,t9));
		
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelyPreceded(t9,t1));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelyPreceded(t9,t2));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelyPreceded(t9,t3));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelyPreceded(t9,t4));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelyPreceded(t9,t5));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelyPreceded(t9,t6));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelyPreceded(t9,t7));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelyPreceded(t9,t8));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsImmediatelyPreceded(t9,t9));
		
		// can occur and every occurrence is eventually succeeded
		assertFalse(PQL.canOccurAndEveryOccurrenceIsEventuallySucceeded(t1,t1));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallySucceeded(t1,t2));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallySucceeded(t1,t3));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsEventuallySucceeded(t1,t4));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallySucceeded(t1,t5));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallySucceeded(t1,t6));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallySucceeded(t1,t7));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallySucceeded(t1,t8));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallySucceeded(t1,t9));

		assertFalse(PQL.canOccurAndEveryOccurrenceIsEventuallySucceeded(t2,t1));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsEventuallySucceeded(t2,t2));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallySucceeded(t2,t3));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsEventuallySucceeded(t2,t4));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallySucceeded(t2,t5));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallySucceeded(t2,t6));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallySucceeded(t2,t7));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallySucceeded(t2,t8));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallySucceeded(t2,t9));
		
		assertFalse(PQL.canOccurAndEveryOccurrenceIsEventuallySucceeded(t3,t1));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsEventuallySucceeded(t3,t2));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsEventuallySucceeded(t3,t3));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsEventuallySucceeded(t3,t4));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallySucceeded(t3,t5));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallySucceeded(t3,t6));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallySucceeded(t3,t7));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallySucceeded(t3,t8));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallySucceeded(t3,t9));
		
		assertFalse(PQL.canOccurAndEveryOccurrenceIsEventuallySucceeded(t4,t1));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallySucceeded(t4,t2));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallySucceeded(t4,t3));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsEventuallySucceeded(t4,t4));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallySucceeded(t4,t5));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallySucceeded(t4,t6));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallySucceeded(t4,t7));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallySucceeded(t4,t8));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallySucceeded(t4,t9));
		
		assertFalse(PQL.canOccurAndEveryOccurrenceIsEventuallySucceeded(t5,t1));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallySucceeded(t5,t2));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallySucceeded(t5,t3));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsEventuallySucceeded(t5,t4));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallySucceeded(t5,t5));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallySucceeded(t5,t6));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallySucceeded(t5,t7));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsEventuallySucceeded(t5,t8));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallySucceeded(t5,t9));
		
		assertFalse(PQL.canOccurAndEveryOccurrenceIsEventuallySucceeded(t6,t1));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallySucceeded(t6,t2));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallySucceeded(t6,t3));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsEventuallySucceeded(t6,t4));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsEventuallySucceeded(t6,t5));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallySucceeded(t6,t6));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallySucceeded(t6,t7));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsEventuallySucceeded(t6,t8));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallySucceeded(t6,t9));
		
		assertFalse(PQL.canOccurAndEveryOccurrenceIsEventuallySucceeded(t7,t1));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallySucceeded(t7,t2));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallySucceeded(t7,t3));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsEventuallySucceeded(t7,t4));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsEventuallySucceeded(t7,t5));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsEventuallySucceeded(t7,t6));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallySucceeded(t7,t7));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsEventuallySucceeded(t7,t8));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallySucceeded(t7,t9));
		
		assertFalse(PQL.canOccurAndEveryOccurrenceIsEventuallySucceeded(t8,t1));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallySucceeded(t8,t2));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallySucceeded(t8,t3));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsEventuallySucceeded(t8,t4));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallySucceeded(t8,t5));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallySucceeded(t8,t6));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallySucceeded(t8,t7));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallySucceeded(t8,t8));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallySucceeded(t8,t9));
		
		assertFalse(PQL.canOccurAndEveryOccurrenceIsEventuallySucceeded(t9,t1));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsEventuallySucceeded(t9,t2));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsEventuallySucceeded(t9,t3));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsEventuallySucceeded(t9,t4));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsEventuallySucceeded(t9,t5));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsEventuallySucceeded(t9,t6));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsEventuallySucceeded(t9,t7));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsEventuallySucceeded(t9,t8));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsEventuallySucceeded(t9,t9));
		
		// can occur and every occurrence is eventually preceded
		assertFalse(PQL.canOccurAndEveryOccurrenceIsEventuallyPreceded(t1,t1));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsEventuallyPreceded(t1,t2));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsEventuallyPreceded(t1,t3));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsEventuallyPreceded(t1,t4));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsEventuallyPreceded(t1,t5));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsEventuallyPreceded(t1,t6));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsEventuallyPreceded(t1,t7));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsEventuallyPreceded(t1,t8));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsEventuallyPreceded(t1,t9));

		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallyPreceded(t2,t1));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsEventuallyPreceded(t2,t2));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsEventuallyPreceded(t2,t3));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallyPreceded(t2,t4));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallyPreceded(t2,t5));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallyPreceded(t2,t6));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallyPreceded(t2,t7));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallyPreceded(t2,t8));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsEventuallyPreceded(t2,t9));
		
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallyPreceded(t3,t1));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallyPreceded(t3,t2));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsEventuallyPreceded(t3,t3));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallyPreceded(t3,t4));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallyPreceded(t3,t5));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallyPreceded(t3,t6));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallyPreceded(t3,t7));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallyPreceded(t3,t8));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsEventuallyPreceded(t3,t9));
		
		assertFalse(PQL.canOccurAndEveryOccurrenceIsEventuallyPreceded(t4,t1));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsEventuallyPreceded(t4,t2));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsEventuallyPreceded(t4,t3));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsEventuallyPreceded(t4,t4));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsEventuallyPreceded(t4,t5));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsEventuallyPreceded(t4,t6));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsEventuallyPreceded(t4,t7));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsEventuallyPreceded(t4,t8));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsEventuallyPreceded(t4,t9));
		
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallyPreceded(t5,t1));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallyPreceded(t5,t2));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallyPreceded(t5,t3));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallyPreceded(t5,t4));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallyPreceded(t5,t5));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallyPreceded(t5,t6));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallyPreceded(t5,t7));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallyPreceded(t5,t8));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsEventuallyPreceded(t5,t9));
		
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallyPreceded(t6,t1));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallyPreceded(t6,t2));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallyPreceded(t6,t3));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallyPreceded(t6,t4));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallyPreceded(t6,t5));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallyPreceded(t6,t6));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsEventuallyPreceded(t6,t7));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsEventuallyPreceded(t6,t8));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsEventuallyPreceded(t6,t9));
		
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallyPreceded(t7,t1));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallyPreceded(t7,t2));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallyPreceded(t7,t3));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallyPreceded(t7,t4));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallyPreceded(t7,t5));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallyPreceded(t7,t6));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallyPreceded(t7,t7));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsEventuallyPreceded(t7,t8));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsEventuallyPreceded(t7,t9));
		
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallyPreceded(t8,t1));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallyPreceded(t8,t2));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallyPreceded(t8,t3));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallyPreceded(t8,t4));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallyPreceded(t8,t5));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallyPreceded(t8,t6));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallyPreceded(t8,t7));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallyPreceded(t8,t8));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsEventuallyPreceded(t8,t9));
		
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallyPreceded(t9,t1));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallyPreceded(t9,t2));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallyPreceded(t9,t3));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallyPreceded(t9,t4));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallyPreceded(t9,t5));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallyPreceded(t9,t6));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallyPreceded(t9,t7));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsEventuallyPreceded(t9,t8));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsEventuallyPreceded(t9,t9));
		
		// always exclusive (exclusive)
		assertFalse(PQL.exclusive(t1,t1));
		assertFalse(PQL.exclusive(t1,t2));
		assertFalse(PQL.exclusive(t1,t3));
		assertTrue(PQL.exclusive(t1,t4));
		assertFalse(PQL.exclusive(t1,t5));
		assertFalse(PQL.exclusive(t1,t6));
		assertFalse(PQL.exclusive(t1,t7));
		assertFalse(PQL.exclusive(t1,t8));
		assertFalse(PQL.exclusive(t1,t9));

		assertFalse(PQL.exclusive(t2,t1));
		assertFalse(PQL.exclusive(t2,t2));
		assertFalse(PQL.exclusive(t2,t3));
		assertFalse(PQL.exclusive(t2,t4));
		assertFalse(PQL.exclusive(t2,t5));
		assertFalse(PQL.exclusive(t2,t6));
		assertFalse(PQL.exclusive(t2,t7));
		assertFalse(PQL.exclusive(t2,t8));
		assertFalse(PQL.exclusive(t2,t9));
		
		assertFalse(PQL.exclusive(t3,t1));
		assertFalse(PQL.exclusive(t3,t2));
		assertFalse(PQL.exclusive(t3,t3));
		assertFalse(PQL.exclusive(t3,t4));
		assertFalse(PQL.exclusive(t3,t5));
		assertFalse(PQL.exclusive(t3,t6));
		assertFalse(PQL.exclusive(t3,t7));
		assertFalse(PQL.exclusive(t3,t8));
		assertFalse(PQL.exclusive(t3,t9));
		
		assertTrue(PQL.exclusive(t4,t1));
		assertFalse(PQL.exclusive(t4,t2));
		assertFalse(PQL.exclusive(t4,t3));
		assertFalse(PQL.exclusive(t4,t4));
		assertFalse(PQL.exclusive(t4,t5));
		assertFalse(PQL.exclusive(t4,t6));
		assertFalse(PQL.exclusive(t4,t7));
		assertFalse(PQL.exclusive(t4,t8));
		assertFalse(PQL.exclusive(t4,t9));
		
		assertFalse(PQL.exclusive(t5,t1));
		assertFalse(PQL.exclusive(t5,t2));
		assertFalse(PQL.exclusive(t5,t3));
		assertFalse(PQL.exclusive(t5,t4));
		assertFalse(PQL.exclusive(t5,t5));
		assertFalse(PQL.exclusive(t5,t6));
		assertFalse(PQL.exclusive(t5,t7));
		assertFalse(PQL.exclusive(t5,t8));
		assertFalse(PQL.exclusive(t5,t9));
		
		assertFalse(PQL.exclusive(t6,t1));
		assertFalse(PQL.exclusive(t6,t2));
		assertFalse(PQL.exclusive(t6,t3));
		assertFalse(PQL.exclusive(t6,t4));
		assertFalse(PQL.exclusive(t6,t5));
		assertFalse(PQL.exclusive(t6,t6));
		assertFalse(PQL.exclusive(t6,t7));
		assertFalse(PQL.exclusive(t6,t8));
		assertFalse(PQL.exclusive(t6,t9));
		
		assertFalse(PQL.exclusive(t7,t1));
		assertFalse(PQL.exclusive(t7,t2));
		assertFalse(PQL.exclusive(t7,t3));
		assertFalse(PQL.exclusive(t7,t4));
		assertFalse(PQL.exclusive(t7,t5));
		assertFalse(PQL.exclusive(t7,t6));
		assertFalse(PQL.exclusive(t7,t7));
		assertFalse(PQL.exclusive(t7,t8));
		assertFalse(PQL.exclusive(t7,t9));
		
		assertFalse(PQL.exclusive(t8,t1));
		assertFalse(PQL.exclusive(t8,t2));
		assertFalse(PQL.exclusive(t8,t3));
		assertFalse(PQL.exclusive(t8,t4));
		assertFalse(PQL.exclusive(t8,t5));
		assertFalse(PQL.exclusive(t8,t6));
		assertFalse(PQL.exclusive(t8,t7));
		assertFalse(PQL.exclusive(t8,t8));
		assertFalse(PQL.exclusive(t8,t9));
		
		assertFalse(PQL.exclusive(t9,t1));
		assertFalse(PQL.exclusive(t9,t2));
		assertFalse(PQL.exclusive(t9,t3));
		assertFalse(PQL.exclusive(t9,t4));
		assertFalse(PQL.exclusive(t9,t5));
		assertFalse(PQL.exclusive(t9,t6));
		assertFalse(PQL.exclusive(t9,t7));
		assertFalse(PQL.exclusive(t9,t8));
		assertFalse(PQL.exclusive(t9,t9));
		
		// concur
		assertFalse(PQL.concurrent(t1,t1));
		assertFalse(PQL.concurrent(t1,t2));
		assertFalse(PQL.concurrent(t1,t3));
		assertFalse(PQL.concurrent(t1,t4));
		assertFalse(PQL.concurrent(t1,t5));
		assertFalse(PQL.concurrent(t1,t6));
		assertFalse(PQL.concurrent(t1,t7));
		assertFalse(PQL.concurrent(t1,t8));
		assertFalse(PQL.concurrent(t1,t9));
		
		assertFalse(PQL.concurrent(t2,t1));
		assertFalse(PQL.concurrent(t2,t2));
		assertFalse(PQL.concurrent(t2,t3));
		assertFalse(PQL.concurrent(t2,t4));
		assertFalse(PQL.concurrent(t2,t5));
		assertTrue(PQL.concurrent(t2,t6));
		assertTrue(PQL.concurrent(t2,t7));
		assertFalse(PQL.concurrent(t2,t8));
		assertFalse(PQL.concurrent(t2,t9));
		
		assertFalse(PQL.concurrent(t3,t1));
		assertFalse(PQL.concurrent(t3,t2));
		assertFalse(PQL.concurrent(t3,t3));
		assertFalse(PQL.concurrent(t3,t4));
		assertFalse(PQL.concurrent(t3,t5));
		assertTrue(PQL.concurrent(t3,t6));
		assertTrue(PQL.concurrent(t3,t7));
		assertFalse(PQL.concurrent(t3,t8));
		assertFalse(PQL.concurrent(t3,t9));
		
		assertFalse(PQL.concurrent(t4,t1));
		assertFalse(PQL.concurrent(t4,t2));
		assertFalse(PQL.concurrent(t4,t3));
		assertFalse(PQL.concurrent(t4,t4));
		assertFalse(PQL.concurrent(t4,t5));
		assertFalse(PQL.concurrent(t4,t6));
		assertFalse(PQL.concurrent(t4,t7));
		assertFalse(PQL.concurrent(t4,t8));
		assertFalse(PQL.concurrent(t4,t9));
		
		assertFalse(PQL.concurrent(t5,t1));
		assertFalse(PQL.concurrent(t5,t2));
		assertFalse(PQL.concurrent(t5,t3));
		assertFalse(PQL.concurrent(t5,t4));
		assertFalse(PQL.concurrent(t5,t5));
		assertFalse(PQL.concurrent(t5,t6));
		assertFalse(PQL.concurrent(t5,t7));
		assertFalse(PQL.concurrent(t5,t8));
		assertFalse(PQL.concurrent(t5,t9));
		
		assertFalse(PQL.concurrent(t6,t1));
		assertTrue(PQL.concurrent(t6,t2));
		assertTrue(PQL.concurrent(t6,t3));
		assertFalse(PQL.concurrent(t6,t4));
		assertFalse(PQL.concurrent(t6,t5));
		assertFalse(PQL.concurrent(t6,t6));
		assertFalse(PQL.concurrent(t6,t7));
		assertFalse(PQL.concurrent(t6,t8));
		assertFalse(PQL.concurrent(t6,t9));
		
		assertFalse(PQL.concurrent(t7,t1));
		assertTrue(PQL.concurrent(t7,t2));
		assertTrue(PQL.concurrent(t7,t3));
		assertFalse(PQL.concurrent(t7,t4));
		assertFalse(PQL.concurrent(t7,t5));
		assertFalse(PQL.concurrent(t7,t6));
		assertFalse(PQL.concurrent(t7,t7));
		assertFalse(PQL.concurrent(t7,t8));
		assertFalse(PQL.concurrent(t7,t9));
		
		assertFalse(PQL.concurrent(t8,t1));
		assertFalse(PQL.concurrent(t8,t2));
		assertFalse(PQL.concurrent(t8,t3));
		assertFalse(PQL.concurrent(t8,t4));
		assertFalse(PQL.concurrent(t8,t5));
		assertFalse(PQL.concurrent(t8,t6));
		assertFalse(PQL.concurrent(t8,t7));
		assertFalse(PQL.concurrent(t8,t8));
		assertFalse(PQL.concurrent(t8,t9));
		
		assertFalse(PQL.concurrent(t9,t1));
		assertFalse(PQL.concurrent(t9,t2));
		assertFalse(PQL.concurrent(t9,t3));
		assertFalse(PQL.concurrent(t9,t4));
		assertFalse(PQL.concurrent(t9,t5));
		assertFalse(PQL.concurrent(t9,t6));
		assertFalse(PQL.concurrent(t9,t7));
		assertFalse(PQL.concurrent(t9,t8));
		assertFalse(PQL.concurrent(t9,t9));
		
		// Can occur and every occurrence is immediately succeeded
		assertFalse(PQL.canOccurAndEveryOccurrenceIsImmediatelySucceeded(t1,t1));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsImmediatelySucceeded(t1,t2));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsImmediatelySucceeded(t1,t3));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsImmediatelySucceeded(t1,t4));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsImmediatelySucceeded(t1,t5));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsImmediatelySucceeded(t1,t6));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsImmediatelySucceeded(t1,t7));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsImmediatelySucceeded(t1,t8));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsImmediatelySucceeded(t1,t9));
		
		assertFalse(PQL.canOccurAndEveryOccurrenceIsImmediatelySucceeded(t2,t1));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsImmediatelySucceeded(t2,t2));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsImmediatelySucceeded(t2,t3));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsImmediatelySucceeded(t2,t4));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsImmediatelySucceeded(t2,t5));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsImmediatelySucceeded(t2,t6));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsImmediatelySucceeded(t2,t7));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsImmediatelySucceeded(t2,t8));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsImmediatelySucceeded(t2,t9));
		
		assertFalse(PQL.canOccurAndEveryOccurrenceIsImmediatelySucceeded(t3,t1));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsImmediatelySucceeded(t3,t2));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsImmediatelySucceeded(t3,t3));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsImmediatelySucceeded(t3,t4));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsImmediatelySucceeded(t3,t5));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsImmediatelySucceeded(t3,t6));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsImmediatelySucceeded(t3,t7));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsImmediatelySucceeded(t3,t8));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsImmediatelySucceeded(t3,t9));
		
		assertFalse(PQL.canOccurAndEveryOccurrenceIsImmediatelySucceeded(t4,t1));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsImmediatelySucceeded(t4,t2));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsImmediatelySucceeded(t4,t3));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsImmediatelySucceeded(t4,t4));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsImmediatelySucceeded(t4,t5));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsImmediatelySucceeded(t4,t6));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsImmediatelySucceeded(t4,t7));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsImmediatelySucceeded(t4,t8));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsImmediatelySucceeded(t4,t9));
		
		assertFalse(PQL.canOccurAndEveryOccurrenceIsImmediatelySucceeded(t5,t1));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsImmediatelySucceeded(t5,t2));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsImmediatelySucceeded(t5,t3));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsImmediatelySucceeded(t5,t4));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsImmediatelySucceeded(t5,t5));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsImmediatelySucceeded(t5,t6));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsImmediatelySucceeded(t5,t7));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsImmediatelySucceeded(t5,t8));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsImmediatelySucceeded(t5,t9));
		
		assertFalse(PQL.canOccurAndEveryOccurrenceIsImmediatelySucceeded(t6,t1));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsImmediatelySucceeded(t6,t2));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsImmediatelySucceeded(t6,t3));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsImmediatelySucceeded(t6,t4));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsImmediatelySucceeded(t6,t5));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsImmediatelySucceeded(t6,t6));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsImmediatelySucceeded(t6,t7));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsImmediatelySucceeded(t6,t8));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsImmediatelySucceeded(t6,t9));
		
		assertFalse(PQL.canOccurAndEveryOccurrenceIsImmediatelySucceeded(t7,t1));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsImmediatelySucceeded(t7,t2));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsImmediatelySucceeded(t7,t3));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsImmediatelySucceeded(t7,t4));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsImmediatelySucceeded(t7,t5));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsImmediatelySucceeded(t7,t6));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsImmediatelySucceeded(t7,t7));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsImmediatelySucceeded(t7,t8));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsImmediatelySucceeded(t7,t9));
		
		assertFalse(PQL.canOccurAndEveryOccurrenceIsImmediatelySucceeded(t8,t1));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsImmediatelySucceeded(t8,t2));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsImmediatelySucceeded(t8,t3));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsImmediatelySucceeded(t8,t4));
		assertTrue(PQL.canOccurAndEveryOccurrenceIsImmediatelySucceeded(t8,t5));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsImmediatelySucceeded(t8,t6));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsImmediatelySucceeded(t8,t7));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsImmediatelySucceeded(t8,t8));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsImmediatelySucceeded(t8,t9));
		
		assertFalse(PQL.canOccurAndEveryOccurrenceIsImmediatelySucceeded(t9,t1));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsImmediatelySucceeded(t9,t2));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsImmediatelySucceeded(t9,t3));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsImmediatelySucceeded(t9,t4));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsImmediatelySucceeded(t9,t5));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsImmediatelySucceeded(t9,t6));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsImmediatelySucceeded(t9,t7));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsImmediatelySucceeded(t9,t8));
		assertFalse(PQL.canOccurAndEveryOccurrenceIsImmediatelySucceeded(t9,t9));
		
		// always occurs and is eventually succeeded
		assertFalse(PQL.alwaysOccursAndIsEventuallySucceeded(t1,t1));
		assertFalse(PQL.alwaysOccursAndIsEventuallySucceeded(t1,t2));
		assertFalse(PQL.alwaysOccursAndIsEventuallySucceeded(t1,t3));
		assertFalse(PQL.alwaysOccursAndIsEventuallySucceeded(t1,t4));
		assertFalse(PQL.alwaysOccursAndIsEventuallySucceeded(t1,t5));
		assertFalse(PQL.alwaysOccursAndIsEventuallySucceeded(t1,t6));
		assertFalse(PQL.alwaysOccursAndIsEventuallySucceeded(t1,t7));
		assertFalse(PQL.alwaysOccursAndIsEventuallySucceeded(t1,t8));
		assertFalse(PQL.alwaysOccursAndIsEventuallySucceeded(t1,t9));
		
		assertFalse(PQL.alwaysOccursAndIsEventuallySucceeded(t2,t1));
		assertFalse(PQL.alwaysOccursAndIsEventuallySucceeded(t2,t2));
		assertTrue(PQL.alwaysOccursAndIsEventuallySucceeded(t2,t3));
		assertFalse(PQL.alwaysOccursAndIsEventuallySucceeded(t2,t4));
		assertFalse(PQL.alwaysOccursAndIsEventuallySucceeded(t2,t5));
		assertFalse(PQL.alwaysOccursAndIsEventuallySucceeded(t2,t6));
		assertFalse(PQL.alwaysOccursAndIsEventuallySucceeded(t2,t7));
		assertFalse(PQL.alwaysOccursAndIsEventuallySucceeded(t2,t8));
		assertTrue(PQL.alwaysOccursAndIsEventuallySucceeded(t2,t9));
		
		assertFalse(PQL.alwaysOccursAndIsEventuallySucceeded(t3,t1));
		assertFalse(PQL.alwaysOccursAndIsEventuallySucceeded(t3,t2));
		assertFalse(PQL.alwaysOccursAndIsEventuallySucceeded(t3,t3));
		assertFalse(PQL.alwaysOccursAndIsEventuallySucceeded(t3,t4));
		assertFalse(PQL.alwaysOccursAndIsEventuallySucceeded(t3,t5));
		assertFalse(PQL.alwaysOccursAndIsEventuallySucceeded(t3,t6));
		assertFalse(PQL.alwaysOccursAndIsEventuallySucceeded(t3,t7));
		assertFalse(PQL.alwaysOccursAndIsEventuallySucceeded(t3,t8));
		assertTrue(PQL.alwaysOccursAndIsEventuallySucceeded(t3,t9));
		
		assertFalse(PQL.alwaysOccursAndIsEventuallySucceeded(t4,t1));
		assertFalse(PQL.alwaysOccursAndIsEventuallySucceeded(t4,t2));
		assertFalse(PQL.alwaysOccursAndIsEventuallySucceeded(t4,t3));
		assertFalse(PQL.alwaysOccursAndIsEventuallySucceeded(t4,t4));
		assertFalse(PQL.alwaysOccursAndIsEventuallySucceeded(t4,t5));
		assertFalse(PQL.alwaysOccursAndIsEventuallySucceeded(t4,t6));
		assertFalse(PQL.alwaysOccursAndIsEventuallySucceeded(t4,t7));
		assertFalse(PQL.alwaysOccursAndIsEventuallySucceeded(t4,t8));
		assertFalse(PQL.alwaysOccursAndIsEventuallySucceeded(t4,t9));
		
		assertFalse(PQL.alwaysOccursAndIsEventuallySucceeded(t5,t1));
		assertFalse(PQL.alwaysOccursAndIsEventuallySucceeded(t5,t2));
		assertFalse(PQL.alwaysOccursAndIsEventuallySucceeded(t5,t3));
		assertFalse(PQL.alwaysOccursAndIsEventuallySucceeded(t5,t4));
		assertFalse(PQL.alwaysOccursAndIsEventuallySucceeded(t5,t5));
		assertFalse(PQL.alwaysOccursAndIsEventuallySucceeded(t5,t6));
		assertFalse(PQL.alwaysOccursAndIsEventuallySucceeded(t5,t7));
		assertFalse(PQL.alwaysOccursAndIsEventuallySucceeded(t5,t8));
		assertFalse(PQL.alwaysOccursAndIsEventuallySucceeded(t5,t9));
		
		assertFalse(PQL.alwaysOccursAndIsEventuallySucceeded(t6,t1));
		assertFalse(PQL.alwaysOccursAndIsEventuallySucceeded(t6,t2));
		assertFalse(PQL.alwaysOccursAndIsEventuallySucceeded(t6,t3));
		assertFalse(PQL.alwaysOccursAndIsEventuallySucceeded(t6,t4));
		assertFalse(PQL.alwaysOccursAndIsEventuallySucceeded(t6,t5));
		assertFalse(PQL.alwaysOccursAndIsEventuallySucceeded(t6,t6));
		assertTrue(PQL.alwaysOccursAndIsEventuallySucceeded(t6,t7));
		assertFalse(PQL.alwaysOccursAndIsEventuallySucceeded(t6,t8));
		assertTrue(PQL.alwaysOccursAndIsEventuallySucceeded(t6,t9));
		
		assertFalse(PQL.alwaysOccursAndIsEventuallySucceeded(t7,t1));
		assertFalse(PQL.alwaysOccursAndIsEventuallySucceeded(t7,t2));
		assertFalse(PQL.alwaysOccursAndIsEventuallySucceeded(t7,t3));
		assertFalse(PQL.alwaysOccursAndIsEventuallySucceeded(t7,t4));
		assertFalse(PQL.alwaysOccursAndIsEventuallySucceeded(t7,t5));
		assertFalse(PQL.alwaysOccursAndIsEventuallySucceeded(t7,t6));
		assertFalse(PQL.alwaysOccursAndIsEventuallySucceeded(t7,t7));
		assertFalse(PQL.alwaysOccursAndIsEventuallySucceeded(t7,t8));
		assertTrue(PQL.alwaysOccursAndIsEventuallySucceeded(t7,t9));
		
		assertFalse(PQL.alwaysOccursAndIsEventuallySucceeded(t8,t1));
		assertFalse(PQL.alwaysOccursAndIsEventuallySucceeded(t8,t2));
		assertFalse(PQL.alwaysOccursAndIsEventuallySucceeded(t8,t3));
		assertFalse(PQL.alwaysOccursAndIsEventuallySucceeded(t8,t4));
		assertFalse(PQL.alwaysOccursAndIsEventuallySucceeded(t8,t5));
		assertFalse(PQL.alwaysOccursAndIsEventuallySucceeded(t8,t6));
		assertFalse(PQL.alwaysOccursAndIsEventuallySucceeded(t8,t7));
		assertFalse(PQL.alwaysOccursAndIsEventuallySucceeded(t8,t8));
		assertFalse(PQL.alwaysOccursAndIsEventuallySucceeded(t8,t9));
		
		assertFalse(PQL.alwaysOccursAndIsEventuallySucceeded(t9,t1));
		assertFalse(PQL.alwaysOccursAndIsEventuallySucceeded(t9,t2));
		assertFalse(PQL.alwaysOccursAndIsEventuallySucceeded(t9,t3));
		assertFalse(PQL.alwaysOccursAndIsEventuallySucceeded(t9,t4));
		assertFalse(PQL.alwaysOccursAndIsEventuallySucceeded(t9,t5));
		assertFalse(PQL.alwaysOccursAndIsEventuallySucceeded(t9,t6));
		assertFalse(PQL.alwaysOccursAndIsEventuallySucceeded(t9,t7));
		assertFalse(PQL.alwaysOccursAndIsEventuallySucceeded(t9,t8));
		assertFalse(PQL.alwaysOccursAndIsEventuallySucceeded(t9,t9));
		
		// always occurs and is immediately succeeded
		assertFalse(PQL.alwaysOccursAndIsImmediatelySucceeded(t1,t1));
		assertFalse(PQL.alwaysOccursAndIsImmediatelySucceeded(t1,t2));
		assertFalse(PQL.alwaysOccursAndIsImmediatelySucceeded(t1,t3));
		assertFalse(PQL.alwaysOccursAndIsImmediatelySucceeded(t1,t4));
		assertFalse(PQL.alwaysOccursAndIsImmediatelySucceeded(t1,t5));
		assertFalse(PQL.alwaysOccursAndIsImmediatelySucceeded(t1,t6));
		assertFalse(PQL.alwaysOccursAndIsImmediatelySucceeded(t1,t7));
		assertFalse(PQL.alwaysOccursAndIsImmediatelySucceeded(t1,t8));
		assertFalse(PQL.alwaysOccursAndIsImmediatelySucceeded(t1,t9));
		
		assertFalse(PQL.alwaysOccursAndIsImmediatelySucceeded(t2,t1));
		assertFalse(PQL.alwaysOccursAndIsImmediatelySucceeded(t2,t2));
		assertFalse(PQL.alwaysOccursAndIsImmediatelySucceeded(t2,t3));
		assertFalse(PQL.alwaysOccursAndIsImmediatelySucceeded(t2,t4));
		assertFalse(PQL.alwaysOccursAndIsImmediatelySucceeded(t2,t5));
		assertFalse(PQL.alwaysOccursAndIsImmediatelySucceeded(t2,t6));
		assertFalse(PQL.alwaysOccursAndIsImmediatelySucceeded(t2,t7));
		assertFalse(PQL.alwaysOccursAndIsImmediatelySucceeded(t2,t8));
		assertFalse(PQL.alwaysOccursAndIsImmediatelySucceeded(t2,t9));
		
		assertFalse(PQL.alwaysOccursAndIsImmediatelySucceeded(t3,t1));
		assertFalse(PQL.alwaysOccursAndIsImmediatelySucceeded(t3,t2));
		assertFalse(PQL.alwaysOccursAndIsImmediatelySucceeded(t3,t3));
		assertFalse(PQL.alwaysOccursAndIsImmediatelySucceeded(t3,t4));
		assertFalse(PQL.alwaysOccursAndIsImmediatelySucceeded(t3,t5));
		assertFalse(PQL.alwaysOccursAndIsImmediatelySucceeded(t3,t6));
		assertFalse(PQL.alwaysOccursAndIsImmediatelySucceeded(t3,t7));
		assertFalse(PQL.alwaysOccursAndIsImmediatelySucceeded(t3,t8));
		assertFalse(PQL.alwaysOccursAndIsImmediatelySucceeded(t3,t9));
		
		assertFalse(PQL.alwaysOccursAndIsImmediatelySucceeded(t4,t1));
		assertFalse(PQL.alwaysOccursAndIsImmediatelySucceeded(t4,t2));
		assertFalse(PQL.alwaysOccursAndIsImmediatelySucceeded(t4,t3));
		assertFalse(PQL.alwaysOccursAndIsImmediatelySucceeded(t4,t4));
		assertFalse(PQL.alwaysOccursAndIsImmediatelySucceeded(t4,t5));
		assertFalse(PQL.alwaysOccursAndIsImmediatelySucceeded(t4,t6));
		assertFalse(PQL.alwaysOccursAndIsImmediatelySucceeded(t4,t7));
		assertFalse(PQL.alwaysOccursAndIsImmediatelySucceeded(t4,t8));
		assertFalse(PQL.alwaysOccursAndIsImmediatelySucceeded(t4,t9));
		
		assertFalse(PQL.alwaysOccursAndIsImmediatelySucceeded(t5,t1));
		assertFalse(PQL.alwaysOccursAndIsImmediatelySucceeded(t5,t2));
		assertFalse(PQL.alwaysOccursAndIsImmediatelySucceeded(t5,t3));
		assertFalse(PQL.alwaysOccursAndIsImmediatelySucceeded(t5,t4));
		assertFalse(PQL.alwaysOccursAndIsImmediatelySucceeded(t5,t5));
		assertFalse(PQL.alwaysOccursAndIsImmediatelySucceeded(t5,t6));
		assertFalse(PQL.alwaysOccursAndIsImmediatelySucceeded(t5,t7));
		assertFalse(PQL.alwaysOccursAndIsImmediatelySucceeded(t5,t8));
		assertFalse(PQL.alwaysOccursAndIsImmediatelySucceeded(t5,t9));
		
		assertFalse(PQL.alwaysOccursAndIsImmediatelySucceeded(t6,t1));
		assertFalse(PQL.alwaysOccursAndIsImmediatelySucceeded(t6,t2));
		assertFalse(PQL.alwaysOccursAndIsImmediatelySucceeded(t6,t3));
		assertFalse(PQL.alwaysOccursAndIsImmediatelySucceeded(t6,t4));
		assertFalse(PQL.alwaysOccursAndIsImmediatelySucceeded(t6,t5));
		assertFalse(PQL.alwaysOccursAndIsImmediatelySucceeded(t6,t6));
		assertFalse(PQL.alwaysOccursAndIsImmediatelySucceeded(t6,t7));
		assertFalse(PQL.alwaysOccursAndIsImmediatelySucceeded(t6,t8));
		assertFalse(PQL.alwaysOccursAndIsImmediatelySucceeded(t6,t9));
		
		assertFalse(PQL.alwaysOccursAndIsImmediatelySucceeded(t7,t1));
		assertFalse(PQL.alwaysOccursAndIsImmediatelySucceeded(t7,t2));
		assertFalse(PQL.alwaysOccursAndIsImmediatelySucceeded(t7,t3));
		assertFalse(PQL.alwaysOccursAndIsImmediatelySucceeded(t7,t4));
		assertFalse(PQL.alwaysOccursAndIsImmediatelySucceeded(t7,t5));
		assertFalse(PQL.alwaysOccursAndIsImmediatelySucceeded(t7,t6));
		assertFalse(PQL.alwaysOccursAndIsImmediatelySucceeded(t7,t7));
		assertFalse(PQL.alwaysOccursAndIsImmediatelySucceeded(t7,t8));
		assertFalse(PQL.alwaysOccursAndIsImmediatelySucceeded(t7,t9));
		
		assertFalse(PQL.alwaysOccursAndIsImmediatelySucceeded(t8,t1));
		assertFalse(PQL.alwaysOccursAndIsImmediatelySucceeded(t8,t2));
		assertFalse(PQL.alwaysOccursAndIsImmediatelySucceeded(t8,t3));
		assertFalse(PQL.alwaysOccursAndIsImmediatelySucceeded(t8,t4));
		assertFalse(PQL.alwaysOccursAndIsImmediatelySucceeded(t8,t5));
		assertFalse(PQL.alwaysOccursAndIsImmediatelySucceeded(t8,t6));
		assertFalse(PQL.alwaysOccursAndIsImmediatelySucceeded(t8,t7));
		assertFalse(PQL.alwaysOccursAndIsImmediatelySucceeded(t8,t8));
		assertFalse(PQL.alwaysOccursAndIsImmediatelySucceeded(t8,t9));
		
		assertFalse(PQL.alwaysOccursAndIsImmediatelySucceeded(t9,t1));
		assertFalse(PQL.alwaysOccursAndIsImmediatelySucceeded(t9,t2));
		assertFalse(PQL.alwaysOccursAndIsImmediatelySucceeded(t9,t3));
		assertFalse(PQL.alwaysOccursAndIsImmediatelySucceeded(t9,t4));
		assertFalse(PQL.alwaysOccursAndIsImmediatelySucceeded(t9,t5));
		assertFalse(PQL.alwaysOccursAndIsImmediatelySucceeded(t9,t6));
		assertFalse(PQL.alwaysOccursAndIsImmediatelySucceeded(t9,t7));
		assertFalse(PQL.alwaysOccursAndIsImmediatelySucceeded(t9,t8));
		assertFalse(PQL.alwaysOccursAndIsImmediatelySucceeded(t9,t9));
		
		// always occurs and every occurrence is eventually succeeded
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallySucceeded(t1,t1));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallySucceeded(t1,t2));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallySucceeded(t1,t3));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallySucceeded(t1,t4));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallySucceeded(t1,t5));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallySucceeded(t1,t6));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallySucceeded(t1,t7));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallySucceeded(t1,t8));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallySucceeded(t1,t9));
		
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallySucceeded(t2,t1));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallySucceeded(t2,t2));
		assertTrue(PQL.alwaysOccursAndEveryOccurrenceIsEventuallySucceeded(t2,t3));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallySucceeded(t2,t4));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallySucceeded(t2,t5));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallySucceeded(t2,t6));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallySucceeded(t2,t7));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallySucceeded(t2,t8));
		assertTrue(PQL.alwaysOccursAndEveryOccurrenceIsEventuallySucceeded(t2,t9));
		
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallySucceeded(t3,t1));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallySucceeded(t3,t2));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallySucceeded(t3,t3));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallySucceeded(t3,t4));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallySucceeded(t3,t5));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallySucceeded(t3,t6));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallySucceeded(t3,t7));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallySucceeded(t3,t8));
		assertTrue(PQL.alwaysOccursAndEveryOccurrenceIsEventuallySucceeded(t3,t9));
		
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallySucceeded(t4,t1));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallySucceeded(t4,t2));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallySucceeded(t4,t3));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallySucceeded(t4,t4));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallySucceeded(t4,t5));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallySucceeded(t4,t6));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallySucceeded(t4,t7));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallySucceeded(t4,t8));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallySucceeded(t4,t9));
		
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallySucceeded(t5,t1));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallySucceeded(t5,t2));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallySucceeded(t5,t3));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallySucceeded(t5,t4));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallySucceeded(t5,t5));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallySucceeded(t5,t6));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallySucceeded(t5,t7));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallySucceeded(t5,t8));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallySucceeded(t5,t9));
		
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallySucceeded(t6,t1));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallySucceeded(t6,t2));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallySucceeded(t6,t3));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallySucceeded(t6,t4));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallySucceeded(t6,t5));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallySucceeded(t6,t6));
		assertTrue(PQL.alwaysOccursAndEveryOccurrenceIsEventuallySucceeded(t6,t7));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallySucceeded(t6,t8));
		assertTrue(PQL.alwaysOccursAndEveryOccurrenceIsEventuallySucceeded(t6,t9));
		
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallySucceeded(t7,t1));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallySucceeded(t7,t2));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallySucceeded(t7,t3));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallySucceeded(t7,t4));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallySucceeded(t7,t5));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallySucceeded(t7,t6));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallySucceeded(t7,t7));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallySucceeded(t7,t8));
		assertTrue(PQL.alwaysOccursAndEveryOccurrenceIsEventuallySucceeded(t7,t9));
		
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallySucceeded(t8,t1));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallySucceeded(t8,t2));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallySucceeded(t8,t3));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallySucceeded(t8,t4));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallySucceeded(t8,t5));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallySucceeded(t8,t6));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallySucceeded(t8,t7));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallySucceeded(t8,t8));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallySucceeded(t8,t9));
		
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallySucceeded(t9,t1));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallySucceeded(t9,t2));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallySucceeded(t9,t3));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallySucceeded(t9,t4));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallySucceeded(t9,t5));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallySucceeded(t9,t6));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallySucceeded(t9,t7));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallySucceeded(t9,t8));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallySucceeded(t9,t9));
		
		// always occurs and every occurrence is eventually preceded
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallyPreceded(t1,t1));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallyPreceded(t1,t2));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallyPreceded(t1,t3));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallyPreceded(t1,t4));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallyPreceded(t1,t5));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallyPreceded(t1,t6));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallyPreceded(t1,t7));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallyPreceded(t1,t8));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallyPreceded(t1,t9));
		
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallyPreceded(t2,t1));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallyPreceded(t2,t2));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallyPreceded(t2,t3));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallyPreceded(t2,t4));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallyPreceded(t2,t5));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallyPreceded(t2,t6));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallyPreceded(t2,t7));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallyPreceded(t2,t8));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallyPreceded(t2,t9));
		
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallyPreceded(t3,t1));
		assertTrue(PQL.alwaysOccursAndEveryOccurrenceIsEventuallyPreceded(t3,t2));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallyPreceded(t3,t3));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallyPreceded(t3,t4));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallyPreceded(t3,t5));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallyPreceded(t3,t6));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallyPreceded(t3,t7));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallyPreceded(t3,t8));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallyPreceded(t3,t9));
		
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallyPreceded(t4,t1));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallyPreceded(t4,t2));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallyPreceded(t4,t3));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallyPreceded(t4,t4));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallyPreceded(t4,t5));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallyPreceded(t4,t6));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallyPreceded(t4,t7));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallyPreceded(t4,t8));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallyPreceded(t4,t9));
		
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallyPreceded(t5,t1));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallyPreceded(t5,t2));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallyPreceded(t5,t3));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallyPreceded(t5,t4));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallyPreceded(t5,t5));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallyPreceded(t5,t6));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallyPreceded(t5,t7));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallyPreceded(t5,t8));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallyPreceded(t5,t9));
		
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallyPreceded(t6,t1));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallyPreceded(t6,t2));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallyPreceded(t6,t3));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallyPreceded(t6,t4));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallyPreceded(t6,t5));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallyPreceded(t6,t6));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallyPreceded(t6,t7));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallyPreceded(t6,t8));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallyPreceded(t6,t9));
		
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallyPreceded(t7,t1));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallyPreceded(t7,t2));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallyPreceded(t7,t3));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallyPreceded(t7,t4));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallyPreceded(t7,t5));
		assertTrue(PQL.alwaysOccursAndEveryOccurrenceIsEventuallyPreceded(t7,t6));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallyPreceded(t7,t7));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallyPreceded(t7,t8));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallyPreceded(t7,t9));
		
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallyPreceded(t8,t1));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallyPreceded(t8,t2));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallyPreceded(t8,t3));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallyPreceded(t8,t4));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallyPreceded(t8,t5));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallyPreceded(t8,t6));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallyPreceded(t8,t7));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallyPreceded(t8,t8));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallyPreceded(t8,t9));
		
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallyPreceded(t9,t1));
		assertTrue(PQL.alwaysOccursAndEveryOccurrenceIsEventuallyPreceded(t9,t2));
		assertTrue(PQL.alwaysOccursAndEveryOccurrenceIsEventuallyPreceded(t9,t3));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallyPreceded(t9,t4));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallyPreceded(t9,t5));
		assertTrue(PQL.alwaysOccursAndEveryOccurrenceIsEventuallyPreceded(t9,t6));
		assertTrue(PQL.alwaysOccursAndEveryOccurrenceIsEventuallyPreceded(t9,t7));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallyPreceded(t9,t8));
		assertFalse(PQL.alwaysOccursAndEveryOccurrenceIsEventuallyPreceded(t9,t9));*/
	}

}
