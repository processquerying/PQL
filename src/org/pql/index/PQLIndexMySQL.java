package org.pql.index;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Set;

import org.jbpt.petri.Flow;
import org.jbpt.petri.Marking;
import org.jbpt.petri.Node;
import org.jbpt.petri.Place;
import org.jbpt.petri.Transition;
import org.pql.core.IPQLBasicPredicatesOnTasks;
import org.pql.label.ILabelManager;
import org.pql.mc.IModelChecker;

/**
 * @author Artem Polyvyanyy
 */
public class PQLIndexMySQL extends AbstractPQLIndexMySQL<Flow,Node,Place,Transition,Marking> {


	public PQLIndexMySQL(Connection con, IPQLBasicPredicatesOnTasks basicPredicates,
			ILabelManager labelManager, IModelChecker<Flow,Node,Place,Transition,Marking> mc,
			double defaultSim, Set<Double> indexedSims, IndexType indexType, long indexTime, long sleepTime)
			throws ClassNotFoundException, SQLException {
		super(con, basicPredicates, labelManager, mc,defaultSim, indexedSims, indexType, indexTime, sleepTime);
	}
}
