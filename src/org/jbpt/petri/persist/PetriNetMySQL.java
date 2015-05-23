package org.jbpt.petri.persist;

import java.sql.SQLException;

import org.jbpt.petri.Flow;
import org.jbpt.petri.Marking;
import org.jbpt.petri.Node;
import org.jbpt.petri.Place;
import org.jbpt.petri.Transition;

/**
 * @author Artem Polyvyanyy
 */
public class PetriNetMySQL extends AbstractPetriNetMySQL<Flow,Node,Place,Transition,Marking> {

	public PetriNetMySQL(String url, String user, String password) throws ClassNotFoundException, SQLException {
		super(url,user,password);
	}

}
