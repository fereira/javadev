package edu.cornell.library.misc.solr;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery; 
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.embedded.EmbeddedSolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.apache.solr.core.CoreContainer;
import org.apache.solr.core.SolrCore;
import org.xml.sax.SAXException; 

public class SolrSearchTest {
	
	//private static String solrHome = "/usr/local/solr";
	private static String solrServerUrl = "http://localhost:8983/solr/collection1";
    //private static String serviceURL = "http://jaf30-dev-new.library.cornell.edu:8080/vitrosolr";
    
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
		 
		
	    String query = new String("*");
	    this.search( query);
	}
	
	private void search( String queryString) {
		 
	    String returnFields = "*";
	    String filterQuery = "*:*";
	    
		SolrDocumentList docs = getSolrDocuments( queryString, filterQuery, returnFields);
	     
	    Iterator<SolrDocument> iter = docs.iterator();
	    while (iter.hasNext()) {	    	
		   
		   SolrDocument doc = iter.next();
		    
		   Map map = doc.getFieldValuesMap();
		   Iterator iter2 = map.keySet().iterator();
		   while (iter2.hasNext()) {
			  Object key = iter2.next();
			  Object val = map.get(key);
			  System.out.println(key +" : "+ val);				
		   } 
	    }
	}
	
	public SolrDocumentList getSolrDocuments(String q, String fq, String fl) {
	    SolrClient client = getSolrClient();
	    
		SolrQuery query = new SolrQuery();
		query.setQuery(q); 
		ModifiableSolrParams solrParams = new ModifiableSolrParams();
		solrParams.set("q", q);
		solrParams.set("rows", Integer.MAX_VALUE);
		solrParams.set("fl", fl);
		solrParams.set("fq", fq);
		solrParams.set("qt", "standard");
		QueryResponse rsp = null;
        try {
            rsp =  client.query(solrParams); 
        } catch (SolrServerException | IOException e) {
            // TODO Auto-generated catch block
        } finally {
            //
        }
		SolrDocumentList docs = rsp.getResults();
		return docs;
		
	}
	
	protected HttpSolrClient getSolrClient() {
        HttpSolrClient.Builder builder = new HttpSolrClient.Builder();
        builder.withBaseSolrUrl(this.solrServerUrl);

        HttpSolrClient httpSolrClient = builder.build();
        return httpSolrClient;
    }
	
	 

}
