package org.pql.test;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import org.themis.ir.Document;
import org.themis.ir.vsm.VSM;
import org.themis.util.LETTERCASE;
import org.themis.util.PREPROCESS;
import org.themis.util.STEMMER;

public class VSMTest
{
        public static void main(String[] args) throws SQLException, ClassNotFoundException
        {
                //VSM vsm = new VSM("localhost","vsm","postgres","password");
        		VSM vsm = new VSM("localhost","themis","postgres","password");
                
                vsm.clear();
                
                vsm.setParameter(PREPROCESS.LETTERCASE.toString(), LETTERCASE.UPPER.toString());
                vsm.setParameter(PREPROCESS.STEMMER.toString(), STEMMER.PORTER.toString());
                
                vsm.addStopword("a");
                vsm.addStopword("the");
                
                vsm.addDocument("URL1", "a red car", false);
                vsm.addDocument("URL2", "the red auto", false);
                vsm.addDocument("URL3", "red cars", false);
                
                List<Document> res = vsm.searchFull("red car", 0, 10);
                Iterator<Document> i = res.iterator();
                
                while (i.hasNext())
                {
                        Document doc = i.next();
                        System.out.println(String.format("%1s \t | \t %1.4f \t | \t %3s", doc.getId(), doc.getSimilarity(), doc.getContent()));
                }
        }
}
