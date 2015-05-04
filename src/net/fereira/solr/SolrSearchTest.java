package net.fereira.solr;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

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

public class SolrSearchTest {
	
	private static String solrHome = "/usr/local/solr";
	private static String solrServer = "http://jaf30-dev.library.cornell.edu:8080/solr/agrovoc/";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SolrSearchTest app = new SolrSearchTest();
		app.run();

	}
	
	public void run() {
		//File home = new File(solrHome);
	    //File f = new File( home, "solr.xml" );
		SolrServer server = new HttpSolrServer(solrServer);
	    String query = new String("swine");
	    this.search(server, query);
	}
	
	private void search(SolrServer server, String queryString) {
		 
	    String returnFields = "@en/skos:prefLabel/, @en/skos:altLabel/ ";
	    String filterQuery = "*:*";
	    
		SolrDocumentList docs = getSolrDocuments(server, queryString, filterQuery, returnFields);
	     
	    Iterator<SolrDocument> iter = docs.iterator();
	    while (iter.hasNext()) {	    	
		   
		   SolrDocument doc = iter.next();
		   List<String> labels = (List<String>) doc.getFieldValue("@en/skos:prefLabel/");
		   for (String label: labels) {
		      System.out.println(queryString+": "+label);
	       }
		   Map map = doc.getFieldValuesMap();
		   Iterator iter2 = map.keySet().iterator();
		   while (iter2.hasNext()) {
			  Object key = iter2.next();
			  Object val = map.get(key);
			  System.out.println("key: "+ key +", val: "+ val);				
		   } 
	    }
	}
	
	public SolrDocumentList getSolrDocuments(SolrServer server, String q, String fq, String fl) {
		 
		SolrQuery query = new SolrQuery();
		query.setQuery(q); 
		ModifiableSolrParams solrParams = new ModifiableSolrParams();
		solrParams.set("q", q);
		solrParams.set("rows", Integer.MAX_VALUE);
		solrParams.set("fl", fl);
		solrParams.set("fq", fq);
		QueryResponse rsp = null;
		try {
			rsp = server.query(solrParams);
			 
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SolrDocumentList docs = rsp.getResults();
		return docs;
		
	}
	
	 

}
