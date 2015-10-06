package org.pql.label;

import java.io.IOException;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.util.CharArraySet;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.similarities.DefaultSimilarity;
import org.apache.lucene.search.similarities.Similarity;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.LockObtainFailedException;

/**
 * An implementation of {@link ILabelManager} using Lucene.
 * 
 * For details see https://lucene.apache.org/. 
 * 
 * @author Artem Polyvyanyy
 */
public class LabelManagerLuceneVSM extends AbstractLabelManagerMySQL {
	
	private String labelSimilarityConfig = null;
	private StandardAnalyzer analyzer = null;	 
	private Similarity similarity = new LabelManagerLuceneVSM.VSMSimilarity();
	
	public LabelManagerLuceneVSM(String mysqlURL, String mysqlUser, String mysqlPassword, 
			double defaultSim, Set<Double> indexedSims, String labelSimilarityConfig) throws ClassNotFoundException, SQLException, IOException {
		super(mysqlURL,mysqlUser,mysqlPassword,defaultSim,indexedSims);
		
		this.labelSimilarityConfig = labelSimilarityConfig;
		
		CharArraySet stopWords = new CharArraySet(Collections.EMPTY_SET, true);
		this.analyzer = new StandardAnalyzer(stopWords);
	}

	@Override
	public int indexLabel(String label) {
		try {
			int labelID = this.createLabel(label);
			
			Directory directory = FSDirectory.open(Paths.get(this.labelSimilarityConfig));
			
			IndexWriterConfig config = new IndexWriterConfig(this.analyzer);
			config.setSimilarity(this.similarity);
			
			try {
				IndexWriter iwriter = new IndexWriter(directory,config);
				
			    Document doc = new Document();
			    String indexedLabel = QueryParser.escape(label);
			    doc.add(new Field("label", indexedLabel, TextField.TYPE_STORED));
			    
			    iwriter.addDocument(doc);
			    iwriter.close();
			    
			} catch (LockObtainFailedException e) {
				// TODO: this slows down multi-treaded indexing a lot, IndexWriter is thread safe!
				try {
					Thread.sleep(1000L);
				} catch (InterruptedException e1) {
				}
				this.indexLabel(label);
			}
		    
		    directory.close();
		    
			return labelID;
		} catch (SQLException | IOException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	@Override
	public Set<LabelScore> search(String searchString, int n) {
		Set<LabelScore> result = new HashSet<LabelScore>();
		
		try {
			Directory directory = FSDirectory.open(Paths.get(this.labelSimilarityConfig));
			
			DirectoryReader ireader = DirectoryReader.open(directory);
		    IndexSearcher isearcher = new IndexSearcher(ireader);
		    isearcher.setSimilarity(this.similarity);
		    
		    QueryParser parser = new QueryParser("label", this.analyzer);
		    Query query = parser.parse(QueryParser.escape(searchString));
		    TopDocs topDocs = isearcher.search(query, n);
		    float maxScore = topDocs.getMaxScore();
		    ScoreDoc[] hits = topDocs.scoreDocs;
		    
		    for (int i=0; i<hits.length; i++) {
		    	String label = isearcher.doc(hits[i].doc).get("label");
		    	float s = hits[i].score / maxScore;
		    	result.add(new LabelScore(label,s));
		    }
		    
		    ireader.close();
		    directory.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	    
	    return result;
	}

	@Override
	public Set<LabelScore> search(String searchString, double sim) {
		Set<LabelScore> result = new HashSet<LabelScore>();
		
		try {
			Directory directory = FSDirectory.open(Paths.get(this.labelSimilarityConfig));
			
			DirectoryReader ireader = DirectoryReader.open(directory);
		    IndexSearcher isearcher = new IndexSearcher(ireader);
		    isearcher.setSimilarity(this.similarity);
		    
		    QueryParser parser = new QueryParser("label", this.analyzer);
		    Query query = parser.parse(QueryParser.escape(searchString));
		    TopDocs topDocs = isearcher.search(query, 10);
		    float maxScore = topDocs.getMaxScore();
		    ScoreDoc[] hits = topDocs.scoreDocs;
		    
		    for (int i=0; i<hits.length; i++) {
		    	if (hits[i].score >= sim) {
		    		String label = isearcher.doc(hits[i].doc).get("label");
		    		float s = hits[i].score / maxScore;
		    		result.add(new LabelScore(label,s));
		    	}
		    }
		    
		    ireader.close();
		    directory.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	    
	    //directory.close();
	    
	    return result;
	}
	
	public class VSMSimilarity extends DefaultSimilarity {

		@Override
		public float idf(long docFreq, long numDocs) {
			return 1;
		}

	}
}
