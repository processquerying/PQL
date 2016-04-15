package org.pql.label;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;


/**
 * @author Artem Polyvyanyy
 */
public class LabelManagerLevenshtein extends AbstractLabelManagerMySQL {
	
	protected String PQL_LEVENSHTEIN_SEARCH					= "{CALL pql.pql_levenshtein_label_sim_search(?)}";
	protected CallableStatement PQL_LEVENSHTEIN_SEARCH_CS 	= null;
	
	public LabelManagerLevenshtein(Connection con,	double defaultSim, Set<Double> indexedSims) throws ClassNotFoundException, SQLException {
		super(con,defaultSim,indexedSims);
	}
	
	@Override
	public Set<LabelScore> getSimilarLabels(String searchString, int n) {
		Set<LabelScore> result = new HashSet<LabelScore>();
		
		try {
			if(PQL_LEVENSHTEIN_SEARCH_CS == null)
			PQL_LEVENSHTEIN_SEARCH_CS = connection.prepareCall(PQL_LEVENSHTEIN_SEARCH);
		
			PQL_LEVENSHTEIN_SEARCH_CS.setString(1, searchString);
			
			ResultSet res = PQL_LEVENSHTEIN_SEARCH_CS.executeQuery();
			
			int count = 0;
			while (res.next()) {
				if (count<n)
					result.add(new LabelScore(res.getString(1), res.getDouble(2)));
				else
					break;
				
				count++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public Set<LabelScore> getSimilarLabels(String searchString, double sim) {
		Set<LabelScore> result = new HashSet<LabelScore>();
		
		try {
			if(PQL_LEVENSHTEIN_SEARCH_CS == null) 
			PQL_LEVENSHTEIN_SEARCH_CS = connection.prepareCall(PQL_LEVENSHTEIN_SEARCH);
		
			PQL_LEVENSHTEIN_SEARCH_CS.setString(1, searchString);
			
			ResultSet res = PQL_LEVENSHTEIN_SEARCH_CS.executeQuery();
			
			while (res.next()) {
				if (sim <= res.getDouble(2)) {
					result.add(new LabelScore(res.getString(1), res.getDouble(2)));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	@Override
	public int indexLabel(String label) throws SQLException {		
		return this.createLabel(label);
	}

}
