package org.pql.bot;

import java.sql.Connection;
import java.sql.SQLException;

import org.jbpt.petri.Flow;
import org.jbpt.petri.Marking;
import org.jbpt.petri.Node;
import org.jbpt.petri.Place;
import org.jbpt.petri.Transition;
import org.pql.index.IPQLIndex;
import org.pql.index.IndexType;
import org.pql.mc.IModelChecker;

public class PQLBotEx extends AbstractPQLBotEx<Flow, Node, Place, Transition, Marking> {

	public PQLBotEx(Connection con,
			String botName,
			IPQLIndex<Flow, Node, Place, Transition, Marking> index,
			IModelChecker<Flow, Node, Place, Transition, Marking> mc,
			IndexType indexType, long indexTime, long sleepTime)
			throws ClassNotFoundException, NameInUseException, SQLException {
		super(con, botName, index, mc, indexType,
				indexTime, sleepTime);
	}

}
