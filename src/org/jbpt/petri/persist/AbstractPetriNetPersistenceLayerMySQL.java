package org.jbpt.petri.persist;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
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
import org.jbpt.petri.NetSystem;
import org.jbpt.petri.io.PNMLSerializer;
import org.jbpt.throwable.SerializationException;

/**
 * An abstract implementation of the {@link IPetriNetPersistenceLayer} interface using MySQL database.
 * 
 * @author Artem Polyvyanyy
 */
public class AbstractPetriNetPersistenceLayerMySQL<F extends IFlow<N>, N extends INode, P extends IPlace, T extends ITransition, M extends IMarking<F,N,P,T>> 
		extends MySQLConnection 
		implements IPetriNetPersistenceLayer<F,N,P,T,M> {

	private String PETRI_NET_CREATE				= "{? = CALL pql.jbpt_petri_nets_create(?,?,?,?,?)}";
	private String PETRI_NET_GET_INT_ID			= "{? = CALL pql.jbpt_petri_nets_get_internal_id(?)}";
	private String PETRI_NET_GET_EXT_ID			= "{? = CALL pql.jbpt_petri_nets_get_external_id(?)}";
	private String PETRI_NET_GET_PNML			= "{? = CALL pql.jbpt_petri_nets_get_pnml_content(?)}";
	private String PETRI_NET_DELETE				= "{? = CALL pql.jbpt_petri_nets_delete(?)}";
	
	private String PETRI_NET_GET_INT_IDS		= "{CALL pql.jbpt_petri_nets_get_internal_ids()}";
	
	private String PETRI_NODE_CREATE			= "{? = CALL pql.jbpt_petri_nodes_create(?,?,?,?,?,?)}";
	private String PETRI_FLOW_CREATE			= "{? = CALL pql.jbpt_petri_flow_create(?,?,?,?)}";
	private String PETRI_MARKINGS_CREATE		= "{? = CALL pql.jbpt_petri_markings_create(?,?)}";
	
	private String PETRI_NET_RESET				= "{CALL pql.reset()}";
	
	public AbstractPetriNetPersistenceLayerMySQL(String url, String user, String password) throws ClassNotFoundException, SQLException {
		super(url,user,password);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public int storeNetSystem(String pnmlFilePath, String externalID) throws SQLException {
		if (pnmlFilePath==null || externalID==null) return 0;
		
		File pnmlFile = new File(pnmlFilePath);
		
		if (pnmlFile.exists() && pnmlFile.isFile() && pnmlFile.canRead()) {
			try {
				byte[] encoded = Files.readAllBytes(Paths.get(pnmlFilePath));
				String pnmlContent =new String(encoded, StandardCharsets.UTF_8); 
				
				PNMLSerializer PNML = new PNMLSerializer();
				INetSystem<F,N,P,T,M> sys = (INetSystem<F,N,P,T,M>) PNML.parse(pnmlFilePath);
				
				return this.storeNetSystem(pnmlContent, sys, externalID);
			} catch (IOException e) {
				return 0;
			}
		}
		else return 0;
	}

	@Override
	@SuppressWarnings("unchecked")
	public int storeNetSystem(File pnmlFile, String externalID) throws SQLException {
		if (pnmlFile==null || externalID==null) return 0;
		
		String pnmlFilePath = null;
		try {
			pnmlFilePath = pnmlFile.getCanonicalPath();
		} catch (IOException e) {
			return 0;
		}
		
		if (pnmlFile.exists() && pnmlFile.isFile() && pnmlFile.canRead()) {
			try {
				byte[] encoded = Files.readAllBytes(Paths.get(pnmlFilePath));
				String pnmlContent =new String(encoded, StandardCharsets.UTF_8); 
				
				PNMLSerializer PNML = new PNMLSerializer();
				INetSystem<F,N,P,T,M> sys = (INetSystem<F,N,P,T,M>) PNML.parse(pnmlFilePath);
				
				return this.storeNetSystem(pnmlContent, sys, externalID);
			} catch (IOException e) {
				return 0;
			}
		}
		else return 0;
	}

	@Override
	@SuppressWarnings("unchecked")
	public int storeNetSystem(byte[] pnmlByteContent, String externalID) throws SQLException {
		if (pnmlByteContent==null || externalID==null) return 0;

		String pnmlContent =new String(pnmlByteContent, StandardCharsets.UTF_8); 
		
		PNMLSerializer PNML = new PNMLSerializer();
		INetSystem<F,N,P,T,M> sys = (INetSystem<F,N,P,T,M>) PNML.parse(pnmlByteContent);
		
		return this.storeNetSystem(pnmlContent, sys, externalID);
	}

	@Override
	public int storeNetSystem(INetSystem<F,N,P,T,M> sys, String externalID) throws SQLException {
		// TODO: check why PNMLSerializer.serializePetriNet throws SerializationException
		// TODO: implement SerializationException for interfaces
		try {
			String pnmlContent = PNMLSerializer.serializePetriNet((NetSystem) sys);
			
			return this.storeNetSystem(pnmlContent,sys,externalID);
		} catch (SerializationException e) {
			return 0;
		}
	}
	
	/**
	 * Store net system in the database.
	 * 
	 * TODO: ensure that net system is either stored in full or not stored. 
	 */
	@SuppressWarnings("unchecked")
	private int storeNetSystem(String pnmlContent, INetSystem<F,N,P,T,M> sys, String externalID) throws SQLException {
		if (pnmlContent==null || sys==null || externalID==null) return 0;
		
		// set proper names
		int pi,ti;
		pi = ti = 1;
		
		for (P p : sys.getPlaces()) {
			p.setName("p"+pi++);
		}
		
		for (T t : sys.getTransitions()) {
			t.setName("t"+ti++);
		}
		
		// create net record
		CallableStatement cs = connection.prepareCall(this.PETRI_NET_CREATE);
		
		cs.registerOutParameter(1, java.sql.Types.INTEGER);
		cs.setString(2, sys.getId());
		cs.setString(3, sys.getName());
		cs.setString(4, sys.getDescription());
		cs.setString(5, externalID);
		cs.setString(6, pnmlContent);
		
		cs.execute();
		
		int result = cs.getInt(1);
		if (result==0) return result;
		
		Map<N,Integer> n2id = new HashMap<N, Integer>(); 
		
		// create node records
		cs = connection.prepareCall(this.PETRI_NODE_CREATE);
		for(P p : sys.getPlaces()) {
			cs.registerOutParameter(1, java.sql.Types.INTEGER);
			cs.setInt(2, result);
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
			cs.setInt(2, result);
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

		return result;
	}
	
	@Override
	public int getInternalID(String externalID) throws SQLException {
		CallableStatement cs = connection.prepareCall(this.PETRI_NET_GET_INT_ID);
		
		cs.registerOutParameter(1, java.sql.Types.INTEGER);
		cs.setString(2, externalID);
		
		cs.execute();
		
		return cs.getInt(1);
	}
	
	@Override
	public String getExternalID(int internalID) throws SQLException {
		CallableStatement cs = connection.prepareCall(this.PETRI_NET_GET_EXT_ID);
		
		cs.registerOutParameter(1, java.sql.Types.VARCHAR);
		cs.setInt(2, internalID);
		
		cs.execute();
		
		return cs.getString(1);
	}
	
	@Override
	public int deleteNetSystem(int internalID) throws SQLException {
		CallableStatement cs = connection.prepareCall(this.PETRI_NET_DELETE);
		
		cs.registerOutParameter(1, java.sql.Types.INTEGER);
		cs.setInt(2, internalID);
		
		cs.execute();
		
		return cs.getInt(1);
	}
	
	@Override
	public int deleteNetSystem(String externalID) throws SQLException {
		int internalID = this.getInternalID(externalID);
		
		if (internalID>0) {
			return this.deleteNetSystem(internalID);
		}
		return 0;
	}

	@Override
	public Set<Integer> getAllInternalIDs() throws SQLException {
		Set<Integer> result = new HashSet<Integer>();
		
		CallableStatement cs = connection.prepareCall(this.PETRI_NET_GET_INT_IDS);
		
		ResultSet res = cs.executeQuery();
		
		while (res.next()) {
			result.add(new Integer(res.getInt(1)));
		}
		
		return result;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public INetSystem<F,N,P,T,M> restoreNetSystem(int internalID) throws SQLException {
		String pnmlContent = this.restorePNMLContent(internalID);
		
		if (pnmlContent == null) return null;
		
		PNMLSerializer PNML = new PNMLSerializer();
		INetSystem<F,N,P,T,M> result = (INetSystem<F,N,P,T,M>)PNML.parse(pnmlContent.getBytes());
		
		// set proper names
		int pi,ti;
		pi = ti = 1;
		
		for (P p : result.getPlaces()) {
			p.setName("p"+pi++);
		}
		
		for (T t : result.getTransitions()) {
			t.setName("t"+ti++);
		}
		
		return result;
	}
	
	@Override
	public String restorePNMLContent(int internalID) throws SQLException {
		CallableStatement cs = connection.prepareCall(this.PETRI_NET_GET_PNML);
		
		cs.registerOutParameter(1, java.sql.Types.VARCHAR);
		cs.setInt(2, internalID);
		
		cs.execute();
		
		String result = cs.getString(1);
		
		cs.close();
		
		return result;
	}

	@Override
	public void reset() throws SQLException {
		CallableStatement cs = connection.prepareCall(this.PETRI_NET_RESET);
		cs.executeQuery();		
	}
}
