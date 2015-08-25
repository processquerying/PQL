package org.pql.index;

import java.sql.SQLException;
import java.util.Set;

import org.jbpt.petri.Flow;
import org.jbpt.petri.Marking;
import org.jbpt.petri.Node;
import org.jbpt.petri.Place;
import org.jbpt.petri.Transition;
import org.pql.core.IPQLBasicPredicatesOnTasks;
import org.pql.label.ILabelManager;
import org.pql.logic.IThreeValuedLogic;

/**
 * @author Artem Polyvyanyy
 */
public class PQLIndexMySQL extends AbstractPQLIndexMySQL<Flow,Node,Place,Transition,Marking> {

	public PQLIndexMySQL(String mysqlURL, String mysqlUser,
			String mysqlPassword, IPQLBasicPredicatesOnTasks basicPredicates,
			ILabelManager labelManager, IThreeValuedLogic logic,
			double defaultSim, Set<Double> indexedSims)
			throws ClassNotFoundException, SQLException {
		super(mysqlURL, mysqlUser, mysqlPassword, basicPredicates, labelManager, logic,
				defaultSim, indexedSims);
	}
}
