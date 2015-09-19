package org.pql.api;

import java.sql.SQLException;
import java.util.Set;

import org.jbpt.petri.Flow;
import org.jbpt.petri.Marking;
import org.jbpt.petri.Node;
import org.jbpt.petri.Place;
import org.jbpt.petri.Transition;
import org.pql.index.IndexType;
import org.pql.label.LabelManagerType;
import org.pql.logic.ThreeValuedLogicType;

/**
 * Artem Polyvyanyy
 */
public class PQLAPI extends AbstractPQLAPI<Flow,Node,Place,Transition,Marking> {

	public PQLAPI(String mySQLURL, String mySQLUser, String mySQLPassword,
			String postgreSQLHost, String postgreSQLName,
			String postgreSQLUser, String postgreSQLPassword, String lolaPath,
			ThreeValuedLogicType threeValuedLogicType, IndexType indexType,
			LabelManagerType labelManagerType, Double defaultLabelSimilarity,
			Set<Double> indexedLabelSimilarities,
			int numberOfQueryThreads)
			throws ClassNotFoundException, SQLException {
		super(mySQLURL, mySQLUser, mySQLPassword, postgreSQLHost, postgreSQLName,
				postgreSQLUser, postgreSQLPassword, lolaPath, threeValuedLogicType,
				indexType, labelManagerType, defaultLabelSimilarity,
				indexedLabelSimilarities, numberOfQueryThreads);
	}


}
