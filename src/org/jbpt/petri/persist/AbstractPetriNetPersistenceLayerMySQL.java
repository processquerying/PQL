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
import java.util.StringTokenizer;
import java.sql.Connection;
import org.jbpt.petri.IFlow;
import org.jbpt.petri.IMarking;
import org.jbpt.petri.INetSystem;
import org.jbpt.petri.INode;
import org.jbpt.petri.IPlace;
import org.jbpt.petri.ITransition;
import org.jbpt.petri.NetSystem;
import org.jbpt.petri.io.PNMLSerializer;
import org.jbpt.throwable.SerializationException;
import org.pql.core.PQLTask;
import org.pql.core.PQLTrace;


/**
 * An abstract implementation of the {@link IPetriNetPersistenceLayer} interface using MySQL database.
 * 
 * @author Artem Polyvyanyy
 */
public class AbstractPetriNetPersistenceLayerMySQL<F extends IFlow<N>, N extends INode, P extends IPlace, T extends ITransition, M extends IMarking<F,N,P,T>> 
	implements IPetriNetPersistenceLayer<F,N,P,T,M> {
	
	private Connection connection = null;
	private String PETRI_NET_CREATE						= "{? = CALL jbpt_petri_nets_create(?,?,?,?,?)}";
	private CallableStatement PETRI_NET_CREATE_CS 		= null;
	
	private String PETRI_NET_GET_INT_ID					= "{? = CALL jbpt_petri_nets_get_internal_id(?)}";
	private CallableStatement PETRI_NET_GET_INT_ID_CS 	= null;
	private String PETRI_NET_GET_EXT_ID					= "{? = CALL jbpt_petri_nets_get_external_id(?)}";
	private CallableStatement PETRI_NET_GET_EXT_ID_CS 	= null;
	private String PETRI_NET_GET_PNML					= "{? = CALL jbpt_petri_nets_get_pnml_content(?)}";
	private CallableStatement PETRI_NET_GET_PNML_CS 	= null;
	private String PETRI_NET_DELETE						= "{? = CALL jbpt_petri_nets_delete(?)}";
	private CallableStatement PETRI_NET_DELETE_CS 		= null;
	private String PETRI_NET_GET_INT_IDS				= "{CALL jbpt_petri_nets_get_internal_ids()}";
	private CallableStatement PETRI_NET_GET_INT_IDS_CS 	= null;
	private String PETRI_NODE_CREATE					= "{? = CALL jbpt_petri_nodes_create(?,?,?,?,?,?)}";
	private CallableStatement PETRI_NODE_CREATE_CS		= null;
	private String PETRI_FLOW_CREATE					= "{? = CALL jbpt_petri_flow_create(?,?,?,?)}";
	private CallableStatement PETRI_FLOW_CREATE_CS 		= null;
	private String PETRI_MARKINGS_CREATE				= "{? = CALL jbpt_petri_markings_create(?,?)}";
	private CallableStatement PETRI_MARKINGS_CREATE_CS 	= null;
	
	private String PETRI_NET_RESET						= "{CALL reset()}";
	private CallableStatement PETRI_NET_RESET_CS 		= null;
	
	/* -----------------------
	 * LOCATIONS CAPSTONE EDIT
	 * -----------------------
	*/
	
	private String PETRI_LOCATIONS_CREATE				= "{? = CALL fldr_id_create(?,?)}";
	private CallableStatement PETRI_LOCATIONS_CREATE_CS 	= null;
	private String PETRI_MODEL_MOVE						= "{? = CALL pql_id_move(?,?)}";
	private CallableStatement PETRI_MODEL_MOVE_CS 	= null;
	private String PETRI_FOLDER_MOVE						= "{? = CALL pql_folder_move(?,?)}";
	private CallableStatement PETRI_FOLDER_MOVE_CS 	= null;
	//make sure im right
	private String PETRI_FOLDER_CREATE						= "{? = CALL pql_folder_create(?,?)}";
	private CallableStatement PETRI_FOLDER_CREATE_CS 	= null;
	private String PETRI_FOLDER_DELETE                     = "{? = CALL pql_folder_delete(?)}";
    private CallableStatement PETRI_FOLDER_DELETE_CS    = null;
	
	public AbstractPetriNetPersistenceLayerMySQL(Connection con) throws ClassNotFoundException, SQLException {
		this.connection = con;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public int storeNetSystem(String pnmlFilePath, String externalID, String target) throws SQLException {
		if (pnmlFilePath==null || externalID==null) return 0;
		
		File pnmlFile = new File(pnmlFilePath);
		
		if (pnmlFile.exists() && pnmlFile.isFile() && pnmlFile.canRead()) {
			try {
				byte[] encoded = Files.readAllBytes(Paths.get(pnmlFilePath));
				String pnmlContent =new String(encoded, StandardCharsets.UTF_8); 
				
				PNMLSerializer PNML = new PNMLSerializer();
				INetSystem<F,N,P,T,M> sys = (INetSystem<F,N,P,T,M>) PNML.parse(pnmlFilePath);
				
				return this.storeNetSystem(pnmlContent, sys, externalID, target);
			} catch (IOException e) {
				return 0;
			}
		}
		else return 0;
	}
	
	/* -----------------------
	 * LOCATIONS CAPSTONE EDIT
	 * -----------------------
	*/
	
	@SuppressWarnings("javadoc")
    public static int tabCounter = 0;
	
	@Override
	@SuppressWarnings("unchecked")
	public void listNetSystem() throws SQLException {
		
		int folder_id = 1;
		ChildPrinter(folder_id);
	    
	}
	
	private void ChildPrinter(int folder_id) throws SQLException {
        CallableStatement cs = connection.prepareCall("{CALL id_to_foldername(" + folder_id + ")}");
        ResultSet res = cs.executeQuery();
        res.next();
        
        for(int i = 0; i < AbstractPetriNetPersistenceLayerMySQL.tabCounter; i++){
            System.out.print("====<>");
        }
        
        System.out.println(res.getString(1));
        
        CallableStatement cs2 = connection.prepareCall("{CALL id_to_children(" + folder_id + ")}");
        ResultSet res2 = cs2.executeQuery();
        
        while(res2.next()){
            AbstractPetriNetPersistenceLayerMySQL.tabCounter++;
            ChildPrinter(res2.getInt(1));
        }
        AbstractPetriNetPersistenceLayerMySQL.tabCounter--;
	}

    @Override
	@SuppressWarnings("unchecked")
	public int moveNetSystem(String id_name, String targetFolder) throws SQLException {
		if (id_name==null || targetFolder==null) return 0;
		
		StringTokenizer folders = new StringTokenizer(targetFolder, "/");
		int targetId = 1;
        CallableStatement cs = null;
        
        while (folders.hasMoreElements()) {
            cs = connection.prepareCall("{CALL return_id(" + targetId + ", \"" + folders.nextElement().toString() + "\")}");
            ResultSet res = cs.executeQuery();
            res.next();
            targetId = res.getInt(1);
        }
		
		
		int folder_id = 1;
		StringTokenizer folders2 = new StringTokenizer(id_name, "/");
        int counter =0;
        
        while (folders2.hasMoreElements()) {
            folders2.nextElement();
            counter++;
        }
        
        //limits to the final element
        counter = (counter - 1);
        
        //retokenises temp
        StringTokenizer foldertofile = new StringTokenizer(id_name, "/");
        
        while(counter > 0) {
            CallableStatement cs2 = connection.prepareCall("{CALL return_id(" + folder_id + ", \"" + foldertofile.nextElement().toString() + "\")}");
            ResultSet res2 = cs2.executeQuery();
            res2.next();
            folder_id = res2.getInt(1);
            counter--;
        }
        
        String lastPos = foldertofile.nextElement().toString();
        CallableStatement cs2 = connection.prepareCall("{CALL double_up_file(\"" + lastPos + "\", " + folder_id + ")}");
        ResultSet res2 = cs2.executeQuery();
        res2.next();
        int fileCheck = res2.getInt(1);
            
        if(fileCheck == 1) {
            if(PETRI_MODEL_MOVE_CS == null)
                PETRI_MODEL_MOVE_CS = connection.prepareCall(this.PETRI_MODEL_MOVE);
                
                PETRI_MODEL_MOVE_CS.registerOutParameter(1, java.sql.Types.INTEGER);
                PETRI_MODEL_MOVE_CS.setString(2, lastPos);
                PETRI_MODEL_MOVE_CS.setInt(3, targetId);
                    
                PETRI_MODEL_MOVE_CS.execute();
        }
		
		return 1;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public int moveFolderNetSystem(String movingFolder, String targetFolder) throws SQLException {
		if (movingFolder==null || targetFolder==null) return 0;
		
		StringTokenizer folders2 = new StringTokenizer(movingFolder, "/");
        int folder_id2 = 1;
        CallableStatement cs2 = null;
        
        while (folders2.hasMoreElements()) {
            cs2 = connection.prepareCall("{CALL return_id(" + folder_id2 + ", \"" + folders2.nextElement().toString() + "\")}");
            ResultSet res2 = cs2.executeQuery();
            res2.next();
            folder_id2 = res2.getInt(1);
        }
		
		StringTokenizer folders = new StringTokenizer(targetFolder, "/");
        int folder_id = 1;
        CallableStatement cs = null;
        
        while (folders.hasMoreElements()) {
            cs = connection.prepareCall("{CALL return_id(" + folder_id + ", \"" + folders.nextElement().toString() + "\")}");
            ResultSet res = cs.executeQuery();
            res.next();
            folder_id = res.getInt(1);
            if(folder_id == folder_id2){
                return 0;
            }
        }
        
        StringTokenizer folders3 = new StringTokenizer(movingFolder, "/");
        int counter =0;
        int folder_id3 = 1;
        
        while (folders3.hasMoreElements()) {
            folders3.nextElement();
            counter++;
        }
        
        //limits to the final element
        counter = (counter - 1);
        
        //retokenises temp
        StringTokenizer foldertofile = new StringTokenizer(movingFolder, "/");
        
        while(counter > 0) {
            CallableStatement cs3 = connection.prepareCall("{CALL return_id(" + folder_id3 + ", \"" + foldertofile.nextElement().toString() + "\")}");
            ResultSet res3 = cs3.executeQuery();
            res3.next();
            folder_id3 = res3.getInt(1);
            counter--;
        }
        
        String lastPos = foldertofile.nextElement().toString();
        int folderCheck;
        
        cs = connection.prepareCall("{CALL double_up(\"" + lastPos + "\", " + folder_id + ")}");
        ResultSet res = cs.executeQuery();
        res.next();
        folderCheck = res.getInt(1);
        
        if(folderCheck == 1) {
            return 0;
        }
        
        cs2 = connection.prepareCall("{CALL double_up_file(\"" + lastPos + "\", " + folder_id + ")}");
        ResultSet res2 = cs2.executeQuery();
        res2.next();
        int fileCheck = res2.getInt(1);
        
        if(fileCheck == 1) {
            return 0;
        }
        
		if(PETRI_FOLDER_MOVE_CS == null)
			PETRI_FOLDER_MOVE_CS = connection.prepareCall(this.PETRI_FOLDER_MOVE);
			
			PETRI_FOLDER_MOVE_CS.registerOutParameter(1, java.sql.Types.INTEGER);
			PETRI_FOLDER_MOVE_CS.setInt(2, folder_id2);
			PETRI_FOLDER_MOVE_CS.setInt(3, folder_id);
				
			PETRI_FOLDER_MOVE_CS.execute();
		
		return 1;
	}
	
	//====================
    //current todo for PQL
    //====================
	
	@Override
	@SuppressWarnings("unchecked")
	public int createFolderNetSystem(String folderName, String targetFolder) throws SQLException {
		if (folderName==null || targetFolder==null) return 0;
		//what do
		
		StringTokenizer folders = new StringTokenizer(targetFolder, "/");
        int folder_id = 1;
        CallableStatement cs = null;
        
        while (folders.hasMoreElements()) {
            cs = connection.prepareCall("{CALL return_id(" + folder_id + ", \"" + folders.nextElement().toString() + "\")}");
            ResultSet res = cs.executeQuery();
            res.next();
            folder_id = res.getInt(1);
        }
        
        int folderCheck;
        
        cs = connection.prepareCall("{CALL double_up(\"" + folderName + "\", " + folder_id + ")}");
        ResultSet res = cs.executeQuery();
        res.next();
        folderCheck = res.getInt(1);
        
        if(folderCheck == 1) {
            return 0;
        }
        
        CallableStatement cs2 = connection.prepareCall("{CALL double_up_file(\"" + folderName + "\", " + folder_id + ")}");
        ResultSet res2 = cs2.executeQuery();
        res2.next();
        int fileCheck = res2.getInt(1);
        
        if(fileCheck == 1) {
            return 0;
        }
		
		if(PETRI_FOLDER_CREATE_CS == null)
			PETRI_FOLDER_CREATE_CS = connection.prepareCall(this.PETRI_FOLDER_CREATE);
			//================
			//fix dis shit
			PETRI_FOLDER_CREATE_CS.registerOutParameter(1, java.sql.Types.INTEGER);
			PETRI_FOLDER_CREATE_CS.setInt(2, folder_id);
			PETRI_FOLDER_CREATE_CS.setString(3, folderName);
			//=================	
			PETRI_FOLDER_CREATE_CS.execute();
		
		return 1;
	}
	
	@Override
    @SuppressWarnings("unchecked")
    public int deleteFolderNetSystem(String folderName) throws SQLException {
        if (folderName==null) return 0;
        
        StringTokenizer folders = new StringTokenizer(folderName, "/");
        int folder_id = 1;
        CallableStatement cs = null;
        
        while (folders.hasMoreElements()) {
            cs = connection.prepareCall("{CALL return_id(" + folder_id + ", \"" + folders.nextElement().toString() + "\")}");
            ResultSet res = cs.executeQuery();
            res.next();
            folder_id = res.getInt(1);
        }
        
        //what do
        if(PETRI_FOLDER_DELETE_CS == null)
            PETRI_FOLDER_DELETE_CS = connection.prepareCall(this.PETRI_FOLDER_DELETE);
            //================
            //fix dis shit
            PETRI_FOLDER_DELETE_CS.registerOutParameter(1, java.sql.Types.INTEGER);
            PETRI_FOLDER_DELETE_CS.setInt(2, folder_id);
            //================= 
            PETRI_FOLDER_DELETE_CS.execute();
        
        return 1;
    }
	

	@Override
	@SuppressWarnings("unchecked")
	public int storeNetSystem(File pnmlFile, String externalID, String target) throws SQLException {
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
				
				return this.storeNetSystem(pnmlContent, sys, externalID, target);
			} catch (IOException e) {
				return 0;
			}
		}
		else return 0;
	}

	@Override
	@SuppressWarnings("unchecked")
	public int storeNetSystem(byte[] pnmlByteContent, String externalID, String target) throws SQLException {
		if (pnmlByteContent==null || externalID==null) return 0;

		String pnmlContent =new String(pnmlByteContent, StandardCharsets.UTF_8); 
		
		PNMLSerializer PNML = new PNMLSerializer();
		INetSystem<F,N,P,T,M> sys = (INetSystem<F,N,P,T,M>) PNML.parse(pnmlByteContent);
		
		return this.storeNetSystem(pnmlContent, sys, externalID, target);
	}

	@Override
	public int storeNetSystem(INetSystem<F,N,P,T,M> sys, String externalID, String target) throws SQLException {
		// TODO: check why PNMLSerializer.serializePetriNet throws SerializationException
		// TODO: implement SerializationException for interfaces
		try {
			String pnmlContent = PNMLSerializer.serializePetriNet((NetSystem) sys);
			
			return this.storeNetSystem(pnmlContent,sys,externalID, target);
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
	private int storeNetSystem(String pnmlContent, INetSystem<F,N,P,T,M> sys, String externalID, String target) throws SQLException {
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
		if(PETRI_NET_CREATE_CS == null)
		PETRI_NET_CREATE_CS = connection.prepareCall(this.PETRI_NET_CREATE);
		
		PETRI_NET_CREATE_CS.registerOutParameter(1, java.sql.Types.INTEGER);
		PETRI_NET_CREATE_CS.setString(2, sys.getId());
		PETRI_NET_CREATE_CS.setString(3, sys.getName());
		PETRI_NET_CREATE_CS.setString(4, sys.getDescription());
		PETRI_NET_CREATE_CS.setString(5, externalID);
		PETRI_NET_CREATE_CS.setString(6, pnmlContent);
		
		PETRI_NET_CREATE_CS.execute();
		
		int result = PETRI_NET_CREATE_CS.getInt(1);
		if (result==0) return result;
		
		/* -----------------------
		 * LOCATIONS CAPSTONE EDIT
		 * -----------------------
		*/
		
		StringTokenizer folders = new StringTokenizer(target, "/");
        int folder_id = 1;
        CallableStatement cs = null;
        
        while (folders.hasMoreElements()) {
            cs = connection.prepareCall("{CALL return_id(" + folder_id + ", \"" + folders.nextElement().toString() + "\")}");
            ResultSet res = cs.executeQuery();
            res.next();
            folder_id = res.getInt(1);
        }
		
		// create location records
				if(PETRI_LOCATIONS_CREATE_CS == null)
					PETRI_LOCATIONS_CREATE_CS = connection.prepareCall(this.PETRI_LOCATIONS_CREATE);
				    PETRI_LOCATIONS_CREATE_CS.registerOutParameter(1, java.sql.Types.INTEGER);//must have a place for the return call in sql
					PETRI_LOCATIONS_CREATE_CS.setString(2, externalID);
					PETRI_LOCATIONS_CREATE_CS.setInt(3, folder_id);
					
					PETRI_LOCATIONS_CREATE_CS.execute();
		
		Map<N,Integer> n2id = new HashMap<N, Integer>(); 
		
		// create node records
		if(PETRI_NODE_CREATE_CS == null)
		PETRI_NODE_CREATE_CS = connection.prepareCall(this.PETRI_NODE_CREATE);
		
		for(P p : sys.getPlaces()) {
			PETRI_NODE_CREATE_CS.registerOutParameter(1, java.sql.Types.INTEGER);
			PETRI_NODE_CREATE_CS.setInt(2, result);
			PETRI_NODE_CREATE_CS.setString(3, p.getId());
			PETRI_NODE_CREATE_CS.setString(4, p.getName());
			PETRI_NODE_CREATE_CS.setString(5, p.getDescription());
			PETRI_NODE_CREATE_CS.setString(6, "");
			PETRI_NODE_CREATE_CS.setBoolean(7, false);
			
			PETRI_NODE_CREATE_CS.execute();
			int id = PETRI_NODE_CREATE_CS.getInt(1);
			n2id.put((N)p,id);
		}
		
		for(T t : sys.getTransitions()) {
			if(PETRI_NODE_CREATE_CS == null)
			PETRI_NODE_CREATE_CS = connection.prepareCall(this.PETRI_NODE_CREATE);
			
			PETRI_NODE_CREATE_CS.registerOutParameter(1, java.sql.Types.INTEGER);
			PETRI_NODE_CREATE_CS.setInt(2, result);
			PETRI_NODE_CREATE_CS.setString(3, t.getId());
			//PETRI_NODE_CREATE_CS.setString(4, t.getLabel());
			PETRI_NODE_CREATE_CS.setString(4, t.getName());
			PETRI_NODE_CREATE_CS.setString(5, t.getDescription());
			PETRI_NODE_CREATE_CS.setString(6, t.isObservable() ? t.getLabel() : "");
			PETRI_NODE_CREATE_CS.setBoolean(7, true);
			
			PETRI_NODE_CREATE_CS.execute();
			int id = PETRI_NODE_CREATE_CS.getInt(1);
			n2id.put((N)t,id);
		}
		
		// create flow records
		for(F f : sys.getFlow()) {
			if(PETRI_FLOW_CREATE_CS == null)
			PETRI_FLOW_CREATE_CS = connection.prepareCall(this.PETRI_FLOW_CREATE);
			
			PETRI_FLOW_CREATE_CS.registerOutParameter(1, java.sql.Types.BOOLEAN);
			PETRI_FLOW_CREATE_CS.setInt(2, n2id.get(f.getSource()));
			PETRI_FLOW_CREATE_CS.setInt(3, n2id.get(f.getTarget()));
			PETRI_FLOW_CREATE_CS.setString(4, f.getName());
			PETRI_FLOW_CREATE_CS.setString(5, f.getDescription());
			
			PETRI_FLOW_CREATE_CS.execute();
		}
		
		// create marking records
		for (Map.Entry<P,Integer> entry : sys.getMarking().entrySet()) {
			if(PETRI_MARKINGS_CREATE_CS == null)
			PETRI_MARKINGS_CREATE_CS = connection.prepareCall(this.PETRI_MARKINGS_CREATE);
			
			PETRI_MARKINGS_CREATE_CS.registerOutParameter(1, java.sql.Types.BOOLEAN);
			PETRI_MARKINGS_CREATE_CS.setInt(2, n2id.get(entry.getKey()));
			PETRI_MARKINGS_CREATE_CS.setInt(3, entry.getValue());
			
			PETRI_MARKINGS_CREATE_CS.execute();
		}

		return result;
	}
	
	@Override
	public int getInternalID(String externalID) throws SQLException {
		if(PETRI_NET_GET_INT_ID_CS == null)
		PETRI_NET_GET_INT_ID_CS = connection.prepareCall(this.PETRI_NET_GET_INT_ID);
		
		PETRI_NET_GET_INT_ID_CS.registerOutParameter(1, java.sql.Types.INTEGER);
		PETRI_NET_GET_INT_ID_CS.setString(2, externalID);
		
		PETRI_NET_GET_INT_ID_CS.execute();
		
		return PETRI_NET_GET_INT_ID_CS.getInt(1);
	}
	
	@Override
	public String getExternalID(int internalID) throws SQLException {
		if(PETRI_NET_GET_EXT_ID_CS == null)
		PETRI_NET_GET_EXT_ID_CS = connection.prepareCall(this.PETRI_NET_GET_EXT_ID);
		
		PETRI_NET_GET_EXT_ID_CS.registerOutParameter(1, java.sql.Types.VARCHAR);
		PETRI_NET_GET_EXT_ID_CS.setInt(2, internalID);
		
		PETRI_NET_GET_EXT_ID_CS.execute();
		
		return PETRI_NET_GET_EXT_ID_CS.getString(1);
	}
	
	@Override
	public int deleteNetSystem(int internalID) throws SQLException {
		if(PETRI_NET_DELETE_CS == null)
		PETRI_NET_DELETE_CS = connection.prepareCall(this.PETRI_NET_DELETE);
		
		PETRI_NET_DELETE_CS.registerOutParameter(1, java.sql.Types.INTEGER);
		PETRI_NET_DELETE_CS.setInt(2, internalID);
		
		PETRI_NET_DELETE_CS.execute();
		
		return PETRI_NET_DELETE_CS.getInt(1);
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
		
		if(PETRI_NET_GET_INT_IDS_CS == null)
		PETRI_NET_GET_INT_IDS_CS = connection.prepareCall(this.PETRI_NET_GET_INT_IDS);
		
		ResultSet res = PETRI_NET_GET_INT_IDS_CS.executeQuery();
		
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
		
		if(PETRI_NET_GET_PNML_CS == null)
		PETRI_NET_GET_PNML_CS = connection.prepareCall(this.PETRI_NET_GET_PNML);
		
		PETRI_NET_GET_PNML_CS.registerOutParameter(1, java.sql.Types.VARCHAR);
		PETRI_NET_GET_PNML_CS.setInt(2, internalID);
		
		PETRI_NET_GET_PNML_CS.execute();
		
		String result = PETRI_NET_GET_PNML_CS.getString(1);
		
		return result;
	}

	@Override
	public void reset() throws SQLException {
		if(PETRI_NET_RESET_CS == null)
		PETRI_NET_RESET_CS = connection.prepareCall(this.PETRI_NET_RESET);
		PETRI_NET_RESET_CS.executeQuery();		
	}
	
	//A.P.
	public int getNumberOfPlaces(String externalID)
	{
		int result = 0;
		int internalID = 0;
		INetSystem<F,N,P,T,M> sys = null;
		
		try {
		
			internalID = this.getInternalID(externalID);
			sys = this.restoreNetSystem(internalID);
		
			} catch (SQLException e) {e.printStackTrace();}
		
		result =  sys.getPlaces().size();
		
		return result;
	}
	
	//A.P.
	public int getNumberOfTransitions(String externalID)
	{
		int result = 0;
		
		int internalID = 0;
		INetSystem<F,N,P,T,M> sys = null;
		
		try {
		
			internalID = this.getInternalID(externalID);
			sys = this.restoreNetSystem(internalID);
		
			} catch (SQLException e) {e.printStackTrace();}
		
		result =  sys.getTransitions().size();
		
		return result;
	}
		
	//A.P. 
	public int getNumberOfFlowArcs(String externalID)
	{
		int result = 0;
		
		int internalID = 0;
		INetSystem<F,N,P,T,M> sys = null;
		
		try {
		
			internalID = this.getInternalID(externalID);
			sys = this.restoreNetSystem(internalID);
		
			} catch (SQLException e) {e.printStackTrace();}
		
		result =  sys.getFlow().size();
		
		return result;
	}
	
	//A.P.
	public boolean netHasAllTraceLabels(PQLTrace trace, Set<String> netLabels)
	{
		Set<String> lowerCaseLabels = new HashSet<String>();
		for(String s : netLabels)
		lowerCaseLabels.add(s.toLowerCase());
		
		if(trace.hasAsterisk())
	  	{
		  	for(int i=1; i<trace.getTrace().size()-1; i++)
		  	{
		  		
		  		PQLTask task = trace.getTrace().elementAt(i);
		  		
		  		if(!task.isAsterisk())
		  		{
			  		boolean netHasLabel = false;
			  		for(String t: task.getSimilarLabels())
			  		{
			  			if (lowerCaseLabels.contains(t.toLowerCase())) {netHasLabel = true; break;}
			  		}
			  		if (!netHasLabel) 
			  		return false;
			  	}
		  	}
	  	}
	  	else
	  	{
	  		for(int i=0; i<trace.getTrace().size(); i++)
		  	{
		  			PQLTask task = trace.getTrace().elementAt(i);
		  			
			  		boolean netHasLabel = false;
			  		for(String t: task.getSimilarLabels())
			  		{
			  			if (lowerCaseLabels.contains(t.toLowerCase())) {netHasLabel = true; break;}
			  		}
			  		if (!netHasLabel) 
			  		return false;
			  	
		  	}
	  		
	  	}	
		
		return true;
		
	}

}
