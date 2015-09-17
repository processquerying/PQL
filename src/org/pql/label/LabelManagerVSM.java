package org.pql.label;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jbpt.persist.MySQLConnection;
import org.pql.core.PQLTask;
import org.themis.ir.Document;
import org.themis.ir.vsm.VSM;
import org.themis.util.LETTERCASE;
import org.themis.util.PREPROCESS;
import org.themis.util.STEMMER;

/**
 * @author Artem Polyvyanyy
 */
public class LabelManagerVSM extends MySQLConnection implements ILabelManager {
	private double		defaultSim	= 1.0;
	private Set<Double> indexedSims = null;
	private double		minSim		= 1.0;
	
	private VSM			vsm			= null;
	
	protected String JBPT_LABELS_CREATE = "{? = CALL pql.jbpt_labels_create(?)}";
	
	protected String PQL_TASKS_CREATE			= "{? = CALL pql.pql_tasks_create(?,?)}";
	protected String PQL_TASKS_SIM_CREATE		= "{? = CALL pql.pql_tasks_sim_create(?,?,?)}";
	protected String PQL_TASKS_GET_SIM			= "{CALL pql.pql_tasks_get_sim(?)}";
	protected String PETRI_NET_GET_NET_LABELS	= "{CALL pql.jbpt_get_net_labels(?)}";
	
	public LabelManagerVSM(String mysqlURL, String mysqlUser, String mysqlPassword,
			String pgHost, String pgName, String pgUser, String pgPassword, 
			double defaultSim, Set<Double> indexedSims) throws ClassNotFoundException, SQLException {
		super(mysqlURL,mysqlUser,mysqlPassword);
		
		this.defaultSim	 = defaultSim;
		this.indexedSims = indexedSims;
	
		// set minimal similarity threshold
		for (Double d : this.indexedSims) {
			if (d<=0.0 || d>1.0) continue;
			if (d < this.minSim) this.minSim = d;
		}
		
		this.vsm = new VSM(pgHost,pgName,pgUser,pgPassword);
		this.vsm.setParameter(PREPROCESS.LETTERCASE.toString(), LETTERCASE.UPPER.toString());
		this.vsm.setParameter(PREPROCESS.STEMMER.toString(), STEMMER.PORTER.toString());
        
        // configure stop words
		this.loadStopWords();
	}
	
	public boolean loadTask(PQLTask task, Set<Double> similarities) throws SQLException {
		String label = "";
		if (task.getLabel()!=null) label = task.getLabel();
		double s = task.getSimilarity(); 
		if (s<0.0) s=0.0;
		if (s>1.0) s=1.0;
		
		// label
		List<Document> search = this.vsm.searchFull(label,0,1);
		String newLabel = label;
		boolean flag = false;
		if (search!=null && !search.isEmpty()) {
			Document doc = search.iterator().next();
			newLabel = doc.getContent();
			if (doc.getSimilarity()<0.8)	// TODO: improve handling of similarity threshold
				flag = true;
		}
		else flag = true;
		
		if (flag) {
			task.setSimilarity(s);
			Set<String> labels = new HashSet<String>();
			labels.add(label);
			task.setLabels(labels);
			return false;
		}
		
		// similarity
		double newS = s;
		double minDelta = Double.MAX_VALUE;
		for (Double sim : similarities) {
			double delta = Math.abs(sim-s);
			if (delta < minDelta) {
				newS = sim;
				minDelta = delta;
			}
			else if (delta == minDelta && sim<newS)
				newS = sim;
		}
		s = newS;
		if (s<0.0) s=0.0;
		if (s>1.0) s=1.0;
		
		int taskID = this.getTaskID(newLabel,s);
		if (taskID<1) {
			task.setSimilarity(s);
			Set<String> labels = new HashSet<String>();
			labels.add(label);
			labels.add(newLabel);
			task.setLabels(labels);
			return false;
		}
		
		// update task
		task.setID(taskID);
		task.setSimilarity(s);
		Set<String> labels = this.getLabels(taskID);
		labels.add(label);
		task.setLabels(labels);
		
		return true;
	}

	@Override
	public double getDefaultLabelSimilarityThreshold() {
		return this.defaultSim;
	}

	@Override
	public int indexLabel(String label) throws SQLException {
		int labelID =  this.createLabel(label);
		vsm.addDocument("LABEL UDDI: " + Integer.toString(labelID), label, false);
		
		return labelID;
	}
	
	private int createLabel(String label) throws SQLException {
		CallableStatement cs = connection.prepareCall(JBPT_LABELS_CREATE);
		
		cs.registerOutParameter(1, java.sql.Types.TINYINT);
		cs.setString(2,label);
		
		cs.execute();
		
		int result = cs.getInt(1);
		
		cs.close();
		
		return result;
	}

	@Override
	public int indexTask(String label) throws SQLException {
		for (Double d : indexedSims) {
			this.createTask(label,d);
		}
		
		List<Document> searchResults = vsm.searchFull(label, 0, 10);
		
		for (Document doc : searchResults) {
			for (Double d : indexedSims) {
				if (d <= doc.getSimilarity()) {
					this.createTaskSim(label,doc.getContent(),d);
				}
			}
		}
		
		
		return 0;
	}
	
	private int createTaskSim(String labelA, String labelB, Double similarity) throws SQLException {
		CallableStatement cs = connection.prepareCall(PQL_TASKS_SIM_CREATE);
		
		cs.registerOutParameter(1,java.sql.Types.TINYINT);
		cs.setString(2,labelA);
		cs.setString(3,labelB);
		cs.setDouble(4,similarity);
		
		cs.execute();
		
		int result = cs.getInt(1);
		
		cs.close();
		
		return result;
		
	}

	private int createTask(String label, Double similarity) throws SQLException {
		CallableStatement cs = connection.prepareCall(PQL_TASKS_CREATE);
		
		cs.registerOutParameter(1, java.sql.Types.TINYINT);
		cs.setString(2,label);
		cs.setDouble(3,similarity);
		
		cs.execute();
		
		int result = cs.getInt(1);
		
		cs.close();
		
		return result;
	}

	private void loadStopWords() throws SQLException {
		try {
			/*vsm.addStopword("a");
			vsm.addStopword("about");
			vsm.addStopword("above");
			vsm.addStopword("across");
			vsm.addStopword("after");
			vsm.addStopword("afterwards");
			vsm.addStopword("again");
			vsm.addStopword("against");
			vsm.addStopword("all");
			vsm.addStopword("almost");
			vsm.addStopword("alone");
			vsm.addStopword("along");
			vsm.addStopword("already");
			vsm.addStopword("also");
			vsm.addStopword("although");
			vsm.addStopword("always");
			vsm.addStopword("am");
			vsm.addStopword("among");
			vsm.addStopword("amongst");
			vsm.addStopword("amoungst");
			vsm.addStopword("amount");
			vsm.addStopword("an");
			vsm.addStopword("and");
			vsm.addStopword("another");
			vsm.addStopword("any");
			vsm.addStopword("anyhow");
			vsm.addStopword("anyone");
			vsm.addStopword("anything");
			vsm.addStopword("anyway");
			vsm.addStopword("anywhere");
			vsm.addStopword("are");
			vsm.addStopword("around");
			vsm.addStopword("as");
			vsm.addStopword("at");
			vsm.addStopword("back");
			vsm.addStopword("be");
			vsm.addStopword("became");
			vsm.addStopword("because");
			vsm.addStopword("become");
			vsm.addStopword("becomes");
			vsm.addStopword("becoming");
			vsm.addStopword("been");
			vsm.addStopword("before");
			vsm.addStopword("beforehand");
			vsm.addStopword("behind");
			vsm.addStopword("being");
			vsm.addStopword("below");
			vsm.addStopword("beside");
			vsm.addStopword("besides");
			vsm.addStopword("between");
			vsm.addStopword("beyond");
			vsm.addStopword("bill");
			vsm.addStopword("both");
			vsm.addStopword("bottom");
			vsm.addStopword("but");
			vsm.addStopword("by");
			vsm.addStopword("call");
			vsm.addStopword("can");
			vsm.addStopword("cannot");
			vsm.addStopword("cant");
			vsm.addStopword("co");
			vsm.addStopword("computer");
			vsm.addStopword("con");
			vsm.addStopword("could");
			vsm.addStopword("couldnt");
			vsm.addStopword("cry");
			vsm.addStopword("de");
			vsm.addStopword("describe");
			vsm.addStopword("detail");
			vsm.addStopword("do");
			vsm.addStopword("done");
			vsm.addStopword("down");
			vsm.addStopword("due");
			vsm.addStopword("during");
			vsm.addStopword("each");
			vsm.addStopword("eg");
			vsm.addStopword("eight");
			vsm.addStopword("either");
			vsm.addStopword("eleven");
			vsm.addStopword("else");
			vsm.addStopword("elsewhere");
			vsm.addStopword("empty");
			vsm.addStopword("enough");
			vsm.addStopword("etc");
			vsm.addStopword("even");
			vsm.addStopword("ever");
			vsm.addStopword("every");
			vsm.addStopword("everyone");
			vsm.addStopword("everything");
			vsm.addStopword("everywhere");
			vsm.addStopword("except");
			vsm.addStopword("few");
			vsm.addStopword("fifteen");
			vsm.addStopword("fify");
			vsm.addStopword("fill");
			vsm.addStopword("find");
			vsm.addStopword("fire");
			vsm.addStopword("first");
			vsm.addStopword("five");
			vsm.addStopword("for");
			vsm.addStopword("former");
			vsm.addStopword("formerly");
			vsm.addStopword("forty");
			vsm.addStopword("found");
			vsm.addStopword("four");
			vsm.addStopword("from");
			vsm.addStopword("front");
			vsm.addStopword("full");
			vsm.addStopword("further");
			vsm.addStopword("get");
			vsm.addStopword("give");
			vsm.addStopword("go");
			vsm.addStopword("had");
			vsm.addStopword("has");
			vsm.addStopword("hasnt");
			vsm.addStopword("have");
			vsm.addStopword("he");
			vsm.addStopword("hence");
			vsm.addStopword("her");
			vsm.addStopword("here");
			vsm.addStopword("hereafter");
			vsm.addStopword("hereby");
			vsm.addStopword("herein");
			vsm.addStopword("hereupon");
			vsm.addStopword("hers");
			vsm.addStopword("herself");
			vsm.addStopword("him");
			vsm.addStopword("himself");
			vsm.addStopword("his");
			vsm.addStopword("how");
			vsm.addStopword("however");
			vsm.addStopword("hundred");
			vsm.addStopword("i");
			vsm.addStopword("ie");
			vsm.addStopword("if");
			vsm.addStopword("in");
			vsm.addStopword("inc");
			vsm.addStopword("indeed");
			vsm.addStopword("interest");
			vsm.addStopword("into");
			vsm.addStopword("is");
			vsm.addStopword("it");
			vsm.addStopword("its");
			vsm.addStopword("itself");
			vsm.addStopword("keep");
			vsm.addStopword("last");
			vsm.addStopword("latter");
			vsm.addStopword("latterly");
			vsm.addStopword("least");
			vsm.addStopword("less");
			vsm.addStopword("ltd");
			vsm.addStopword("made");
			vsm.addStopword("many");
			vsm.addStopword("may");
			vsm.addStopword("me");
			vsm.addStopword("meanwhile");
			vsm.addStopword("might");
			vsm.addStopword("mill");
			vsm.addStopword("mine");
			vsm.addStopword("more");
			vsm.addStopword("moreover");
			vsm.addStopword("most");
			vsm.addStopword("mostly");
			vsm.addStopword("move");
			vsm.addStopword("much");
			vsm.addStopword("must");
			vsm.addStopword("my");
			vsm.addStopword("myself");
			vsm.addStopword("name");
			vsm.addStopword("namely");
			vsm.addStopword("neither");
			vsm.addStopword("never");
			vsm.addStopword("nevertheless");
			vsm.addStopword("next");
			vsm.addStopword("nine");
			vsm.addStopword("no");
			vsm.addStopword("nobody");
			vsm.addStopword("none");
			vsm.addStopword("noone");
			vsm.addStopword("nor");
			vsm.addStopword("not");
			vsm.addStopword("nothing");
			vsm.addStopword("now");
			vsm.addStopword("nowhere");
			vsm.addStopword("of");
			vsm.addStopword("off");
			vsm.addStopword("often");
			vsm.addStopword("on");
			vsm.addStopword("once");
			vsm.addStopword("one");
			vsm.addStopword("only");
			vsm.addStopword("onto");
			vsm.addStopword("or");
			vsm.addStopword("other");
			vsm.addStopword("others");
			vsm.addStopword("otherwise");
			vsm.addStopword("our");
			vsm.addStopword("ours");
			vsm.addStopword("ourselves");
			vsm.addStopword("out");
			vsm.addStopword("over");
			vsm.addStopword("own");
			vsm.addStopword("part");
			vsm.addStopword("per");
			vsm.addStopword("perhaps");
			vsm.addStopword("please");
			vsm.addStopword("put");
			vsm.addStopword("rather");
			vsm.addStopword("re");
			vsm.addStopword("same");
			vsm.addStopword("see");
			vsm.addStopword("seem");
			vsm.addStopword("seemed");
			vsm.addStopword("seeming");
			vsm.addStopword("seems");
			vsm.addStopword("serious");
			vsm.addStopword("several");
			vsm.addStopword("she");
			vsm.addStopword("should");
			vsm.addStopword("show");
			vsm.addStopword("side");
			vsm.addStopword("since");
			vsm.addStopword("sincere");
			vsm.addStopword("six");
			vsm.addStopword("sixty");
			vsm.addStopword("so");
			vsm.addStopword("some");
			vsm.addStopword("somehow");
			vsm.addStopword("someone");
			vsm.addStopword("something");
			vsm.addStopword("sometime");
			vsm.addStopword("sometimes");
			vsm.addStopword("somewhere");
			vsm.addStopword("still");
			vsm.addStopword("such");
			vsm.addStopword("system");
			vsm.addStopword("take");
			vsm.addStopword("ten");
			vsm.addStopword("than");
			vsm.addStopword("that");
			vsm.addStopword("the");
			vsm.addStopword("their");
			vsm.addStopword("them");
			vsm.addStopword("themselves");
			vsm.addStopword("then");
			vsm.addStopword("thence");
			vsm.addStopword("there");
			vsm.addStopword("thereafter");
			vsm.addStopword("thereby");
			vsm.addStopword("therefore");
			vsm.addStopword("therein");
			vsm.addStopword("thereupon");
			vsm.addStopword("these");
			vsm.addStopword("they");
			vsm.addStopword("thick");
			vsm.addStopword("thin");
			vsm.addStopword("third");
			vsm.addStopword("this");
			vsm.addStopword("those");
			vsm.addStopword("though");
			vsm.addStopword("three");
			vsm.addStopword("through");
			vsm.addStopword("throughout");
			vsm.addStopword("thru");
			vsm.addStopword("thus");
			vsm.addStopword("to");
			vsm.addStopword("together");
			vsm.addStopword("too");
			vsm.addStopword("top");
			vsm.addStopword("toward");
			vsm.addStopword("towards");
			vsm.addStopword("twelve");
			vsm.addStopword("twenty");
			vsm.addStopword("two");
			vsm.addStopword("un");
			vsm.addStopword("under");
			vsm.addStopword("until");
			vsm.addStopword("up");
			vsm.addStopword("upon");
			vsm.addStopword("us");
			vsm.addStopword("very");
			vsm.addStopword("via");
			vsm.addStopword("was");
			vsm.addStopword("we");
			vsm.addStopword("well");
			vsm.addStopword("were");
			vsm.addStopword("what");
			vsm.addStopword("whatever");
			vsm.addStopword("when");
			vsm.addStopword("whence");
			vsm.addStopword("whenever");
			vsm.addStopword("where");
			vsm.addStopword("whereafter");
			vsm.addStopword("whereas");
			vsm.addStopword("whereby");
			vsm.addStopword("wherein");
			vsm.addStopword("whereupon");
			vsm.addStopword("wherever");
			vsm.addStopword("whether");
			vsm.addStopword("which");
			vsm.addStopword("while");
			vsm.addStopword("whither");
			vsm.addStopword("who");
			vsm.addStopword("whoever");
			vsm.addStopword("whole");
			vsm.addStopword("whom");
			vsm.addStopword("whose");
			vsm.addStopword("why");
			vsm.addStopword("will");
			vsm.addStopword("with");
			vsm.addStopword("within");
			vsm.addStopword("without");
			vsm.addStopword("would");
			vsm.addStopword("yet");
			vsm.addStopword("you");
			vsm.addStopword("your");
			vsm.addStopword("yours");
			vsm.addStopword("yourself");
			vsm.addStopword("yourselves");*/
		}
		catch (Exception e) {
		}
	}

	@Override
	public int getTaskID(String label, double similarity) throws SQLException {
		return this.createTask(label, similarity);
	}

	@Override
	public Set<String> getLabels(int taskID) throws SQLException {
		Set<String> result = new HashSet<String>();
		
		CallableStatement cs = connection.prepareCall(PQL_TASKS_GET_SIM);
		cs.setInt(1,taskID);
		
		ResultSet res = cs.executeQuery();
		
		while (res.next()) {
			result.add(res.getString(1));
		}
		
		return result;
	}

	@Override
	public Set<Double> getIndexedSimilarities() {
		return this.indexedSims;
	}
	
	@Override
	public Set<String> getAllLabels(String netIdentifier) throws SQLException {
		Set<String> result = new HashSet<String>();
		
		CallableStatement cs = connection.prepareCall(this.PETRI_NET_GET_NET_LABELS);
		cs.setString(1, netIdentifier);
		
		ResultSet res = cs.executeQuery();
		
		while (res.next()) {
			result.add(res.getString(1));
		}
		
		return result;
	}
}
