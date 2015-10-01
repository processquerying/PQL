package org.jbpt.petri.persist;

import java.sql.SQLException;

import org.jbpt.petri.Flow;
import org.jbpt.petri.Marking;
import org.jbpt.petri.Node;
import org.jbpt.petri.Place;
import org.jbpt.petri.Transition;

/**
 * An implementation of the {@link IPetriNetPersistenceLayer} interface using MySQL database.
 * 
 * @author Artem Polyvyanyy
 */
public class PetriNetPersistenceLayerMySQL extends AbstractPetriNetPersistenceLayerMySQL<Flow,Node,Place,Transition,Marking> {

	public PetriNetPersistenceLayerMySQL(String url, String user, String password) throws ClassNotFoundException, SQLException {
		super(url,user,password);
	}

}
