package net.fereira.solr;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import net.fereira.util.MatchUtils;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.embedded.EmbeddedSolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.apache.solr.core.CoreContainer;
import org.apache.solr.core.SolrCore;
import org.xml.sax.SAXException; 

public class VitroSolrSearchTest {
	
	private static String solrHome = "/usr/local/vitro/home/solr";
	private String serviceURL = "http://jaf30-dev.library.cornell.edu:8080/vitrosolr";
	SolrServer solrServer;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		VitroSolrSearchTest app = new VitroSolrSearchTest();
		app.run();

	}
	
	public void run() {
		//File home = new File(solrHome);
	    //File f = new File( home, "solr.xml" );
		try {
		       this.solrServer = new HttpSolrServer(this.serviceURL);
		} catch (Exception ex) {
		    	ex.printStackTrace();
		} 
		 
	    String query = new String("fao");
	    String result = querySolr(query);
	    MatchUtils matcher = new MatchUtils();
	    System.out.println("result: "+ result);
	    System.out.println("result match: "+ matcher.fuzzyMatch(query, result));
	}
	
	/**
	 * @param term
	 * @return
	 */
	private String querySolr(String term) {
		 
		//System.out.println("Query solr...");
		String preferredTerm = new String(); 
		String returnFields = "*";
	    String filterQuery = "*:*";
	    
	     
		SolrDocumentList docs = getSolrDocuments(this.solrServer, term, filterQuery, returnFields);
		if (docs.size() == 0) {
			System.out.println("No docs matched");
			return term;
		}
	    //System.out.println("Num docs matched: "+ docs.size()); 
	    Iterator<SolrDocument> iter = docs.iterator();
	    MatchUtils matcher = new MatchUtils();
	    String prefLabel = new String();
	    String bestAltLabel = new String();
	    Double bestAltValue = 0.0;
	    String bestPrefLabel = new String();
	    Double bestPrefValue = 0.0;
	    Double altMatchValue = 0.0;
	    Double prefMatchValue = 0.0;
	    while (iter.hasNext()) {	    	
		   
		   SolrDocument doc = iter.next(); 
		   String uri = (String) doc.getFieldValue("URI");
		   List<String> altList = (List<String>) doc.getFieldValue("altLabel_text");
		   List<String> prefList = (List<String>) doc.getFieldValue("prefLabel_text");
		   if (prefList != null && prefList.size() > 0) {
		       prefLabel = prefList.get(0);
		   }
		   
		   
		   //System.out.println("uri: "+uri);
		   if (term.equalsIgnoreCase(prefLabel)) {
			   System.out.println("exact match: "+ prefLabel);
			   //System.out.println();
			   preferredTerm = term;
			   return term;
			   //break;
		   }
		   
		   if (altList != null && altList.size() > 0) {
			   for (String altLabel : altList) {
				  altMatchValue = matcher.fuzzyMatch(term, altLabel);
			      //System.out.println(altLabel+": "+altMatchValue);
				  if (altMatchValue > bestAltValue) {
					 bestAltLabel = altLabel;  
				     bestAltValue = altMatchValue;
			       
			      }
			   }
		   } else if (prefLabel.length() > 0) {
			   System.out.println("prefLabel: "+prefLabel);
			   prefMatchValue = matcher.fuzzyMatch(term, prefLabel);
			   //System.out.println("match: "+ matcher.fuzzyMatch(term, prefLabel));
			   if (prefMatchValue > bestPrefValue) {
					 bestPrefLabel = prefLabel;  
				     bestPrefValue = prefMatchValue;
			   }
			    
		   } else {
			   bestAltValue = 0.0;
			   bestPrefValue = 0.0;
		   } 
		   
	    }
	    
	    if (bestAltValue > .4) {
	    	System.out.println("\nterm: "+ term);
		    System.out.println("bestAltMatch: "+ bestAltLabel);
			System.out.println("altMatchValue: "+ bestAltValue);
			preferredTerm = prefLabel;
	    } else if (bestPrefValue > .4 ) {
	    	System.out.println("\nterm: "+ term);
		    System.out.println("bestPrefMatch: "+ bestPrefLabel);
			System.out.println("prefMatchValue: "+ bestPrefValue);
			preferredTerm = bestPrefLabel;
	    } else {
	    	preferredTerm = term;
	    }
	    	
	    return preferredTerm;	
	     
	}
	
	public SolrDocumentList getSolrDocuments(SolrServer server, String q, String fq, String fl) {
		//System.out.println("get solr documents"); 
		SolrQuery query = new SolrQuery();
		 
		query.setQuery(q);
		query.addFilterQuery("altLabel_text", "prefLabel_text");
		ModifiableSolrParams solrParams = new ModifiableSolrParams();
		solrParams.set("q", q);
		solrParams.set("rows", Integer.MAX_VALUE);
		solrParams.set("fl", fl);
		//solrParams.set("fq", fq);
		 
		QueryResponse rsp = null;
		try {
			rsp = server.query(solrParams);
			//System.out.println("Got result, status: "+rsp.getStatus()); 
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SolrDocumentList docs = rsp.getResults();
		return docs;
		
	}
	
	 

}
