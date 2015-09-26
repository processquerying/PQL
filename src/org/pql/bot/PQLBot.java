package org.pql.bot;

import java.sql.SQLException;

import org.jbpt.petri.Flow;
import org.jbpt.petri.Marking;
import org.jbpt.petri.Node;
import org.jbpt.petri.Place;
import org.jbpt.petri.Transition;
import org.pql.index.IPQLIndex;
import org.pql.index.IndexType;
import org.pql.mc.IModelChecker;

public class PQLBot extends AbstractPQLBot<Flow, Node, Place, Transition, Marking> {

	public PQLBot(String mysqlURL, String mysqlUser, String mysqlPassword,
			String botName,
			IPQLIndex<Flow, Node, Place, Transition, Marking> index,
			IModelChecker<Flow, Node, Place, Transition, Marking> mc,
			IndexType indexType, long indexTime, long sleepTime, boolean verbose)
			throws ClassNotFoundException, SQLException {
		super(mysqlURL, mysqlUser, mysqlPassword, botName, index, mc, indexType,
				indexTime, sleepTime, verbose);
	}

}
