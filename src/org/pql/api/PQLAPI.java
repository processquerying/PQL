package org.pql.api;

import org.jbpt.petri.Flow;
import org.jbpt.petri.Marking;
import org.jbpt.petri.Node;
import org.jbpt.petri.Place;
import org.jbpt.petri.Transition;
import org.jbpt.petri.persist.IPetriNetPersistenceLayer;
import org.pql.index.IPQLIndex;
import org.pql.label.ILabelManager;
import org.pql.logic.IThreeValuedLogic;
import org.pql.mc.IModelChecker;

/**
 * Artem Polyvyanyy
 */
public class PQLAPI extends AbstractPQLAPI<Flow,Node,Place,Transition,Marking> {

	public PQLAPI(
			String mysqlURL, String mysqlUser, String mysqlPassword, 
			IModelChecker<Flow,Node,Place,Transition,Marking> modelChecker, 
			IThreeValuedLogic logic, 
			IPetriNetPersistenceLayer<Flow,Node,Place,Transition,Marking> netPersistenceLayer, 
			IPQLIndex<Flow,Node,Place,Transition,Marking> pqlPersistenceLayer,  
			ILabelManager labelManager) {
		super(mysqlPassword, mysqlPassword, mysqlPassword, modelChecker,logic,netPersistenceLayer,pqlPersistenceLayer,labelManager);
	}

}
