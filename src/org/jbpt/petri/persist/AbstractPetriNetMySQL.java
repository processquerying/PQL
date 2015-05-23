package org.jbpt.petri.persist;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.jbpt.persist.MySQLConnection;
import org.jbpt.petri.IFlow;
import org.jbpt.petri.IMarking;
import org.jbpt.petri.INetSystem;
import org.jbpt.petri.INode;
import org.jbpt.petri.IPlace;
import org.jbpt.petri.ITransition;

/**
 * @author Artem Polyvyanyy
 */
public class AbstractPetriNetMySQL<F extends IFlow<N>, N extends INode, P extends IPlace, T extends ITransition, M extends IMarking<F,N,P,T>> 
		extends MySQLConnection implements IPetriNetPersistenceLayer<F,N,P,T,M> {

	private String PETRI_NET_CREATE				= "{? = CALL pql.jbpt_petri_nets_create(?,?,?,?)}";
	private String PETRI_NET_DELETE				= "{? = CALL pql.jbpt_petri_nets_delete(?)}";
	private String PETRI_NET_GET_ALL_IDS		= "{CALL pql.jbpt_get_all_net_identifiers()}";
	private String PETRI_NODE_CREATE			= "{? = CALL pql.jbpt_petri_nodes_create(?,?,?,?,?,?)}";
	private String PETRI_FLOW_CREATE			= "{? = CALL pql.jbpt_petri_flow_create(?,?,?,?)}";
	private String PETRI_MARKINGS_CREATE		= "{? = CALL pql.jbpt_petri_markings_create(?,?)}";
	private String PETRI_NET_IDENTIFIER_TO_ID	= "{? = CALL pql.jbpt_petri_nets_identifier2id(?)}";
	private String PETRI_NET_UUID_TO_ID			= "{? = CALL pql.jbpt_petri_nets_uuid2id(?)}";
	private String PETRI_NET_RESET				= "{CALL pql.clear()}";
	
	protected AbstractPetriNetMySQL(String url, String user, String password) throws ClassNotFoundException, SQLException {
		super(url, user, password);
	}

	@Override
	@SuppressWarnings("unchecked")
	public int storeNetSystem(INetSystem<F,N,P,T,M> sys, String identifier) throws SQLException {
		// create net record
		CallableStatement cs = connection.prepareCall(this.PETRI_NET_CREATE);
		
		cs.registerOutParameter(1, java.sql.Types.INTEGER);
		cs.setString(2, sys.getId());
		cs.setString(3, sys.getName());
		cs.setString(4, sys.getDescription());
		cs.setString(5, identifier);
		
		cs.execute();
		
		int net_id = cs.getInt(1);
		
		Map<N,Integer> n2id = new HashMap<N, Integer>(); 
		
		// create node records
		cs = connection.prepareCall(this.PETRI_NODE_CREATE);
		for(P p : sys.getPlaces()) {
			cs.registerOutParameter(1, java.sql.Types.INTEGER);
			cs.setInt(2, net_id);
			cs.setString(3, p.getId());
			cs.setString(4, p.getName());
			cs.setString(5, p.getDescription());
			cs.setString(6, "");
			cs.setBoolean(7, false);
			
			cs.execute();
			int id = cs.getInt(1);
			n2id.put((N)p,id);
		}
		
		for(T t : sys.getTransitions()) {
			cs = connection.prepareCall(this.PETRI_NODE_CREATE);
			cs.registerOutParameter(1, java.sql.Types.INTEGER);
			cs.setInt(2, net_id);
			cs.setString(3, t.getId());
			cs.setString(4, t.getName());
			cs.setString(5, t.getDescription());
			cs.setString(6, t.isObservable() ? t.getLabel() : "");
			cs.setBoolean(7, true);
			
			cs.execute();
			int id = cs.getInt(1);
			n2id.put((N)t,id);
		}
		
		// create flow records
		for(F f : sys.getFlow()) {
			cs = connection.prepareCall(this.PETRI_FLOW_CREATE);
			cs.registerOutParameter(1, java.sql.Types.BOOLEAN);
			cs.setInt(2, n2id.get(f.getSource()));
			cs.setInt(3, n2id.get(f.getTarget()));
			cs.setString(4, f.getName());
			cs.setString(5, f.getDescription());
			
			cs.execute();
		}
		
		// create marking records
		for (Map.Entry<P,Integer> entry : sys.getMarking().entrySet()) {
			cs = connection.prepareCall(this.PETRI_MARKINGS_CREATE);
			cs.registerOutParameter(1, java.sql.Types.BOOLEAN);
			cs.setInt(2, n2id.get(entry.getKey()));
			cs.setInt(3, entry.getValue());
			
			cs.execute();
		}

		return net_id;
	}
	
	@Override
	public int deleteNetSystem(String identifier) throws SQLException {
		CallableStatement cs = connection.prepareCall(this.PETRI_NET_DELETE);
		
		cs.registerOutParameter(1, java.sql.Types.INTEGER);
		cs.setString(2, identifier);
		
		cs.execute();
		
		int net_id = cs.getInt(1);
		
		return net_id;
	}
	
	/* (non-Javadoc)
	 * @see org.jbpt.petri.mysql.IPetriNetPersistenceLayer#identifier2NetID(java.lang.String)
	 */
	@Override
	public int identifier2NetID(String identifier) throws SQLException {
		CallableStatement cs = connection.prepareCall(this.PETRI_NET_IDENTIFIER_TO_ID);
		
		cs.registerOutParameter(1, java.sql.Types.INTEGER);
		cs.setString(2, identifier);
		
		cs.execute();
		
		return cs.getInt(1);
	}
	
	/* (non-Javadoc)
	 * @see org.jbpt.petri.mysql.IPetriNetPersistenceLayer#UUID2NetID(java.lang.String)
	 */
	@Override
	public int UUID2NetID(String uuid) throws SQLException {
		CallableStatement cs = connection.prepareCall(this.PETRI_NET_UUID_TO_ID);
		
		cs.registerOutParameter(1, java.sql.Types.INTEGER);
		cs.setString(2, uuid);
		
		cs.execute();
		
		return cs.getInt(1);
	}
	
	/* (non-Javadoc)
	 * @see org.jbpt.petri.mysql.IPetriNetPersistenceLayer#loadNetSystem(int)
	 */
	@Override
	public INetSystem<F,N,P,T,M> restoreNetSystem(int ID) {
		// TODO implement
		return null;
	}

	@Override
	public Set<String> getAllIdentifiers() throws SQLException {
		Set<String> result = new HashSet<String>();
		
		CallableStatement cs = connection.prepareCall(this.PETRI_NET_GET_ALL_IDS);
		
		ResultSet res = cs.executeQuery();
		
		while (res.next()) {
			result.add(res.getString(1));
		}
		
		return result;
	}

	@Override
	public void reset() throws SQLException {
		CallableStatement cs = connection.prepareCall(this.PETRI_NET_RESET);
		cs.executeQuery();		
	}

}
