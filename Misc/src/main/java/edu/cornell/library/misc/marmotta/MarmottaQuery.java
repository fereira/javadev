package edu.cornell.library.misc.marmotta;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

 
import org.apache.marmotta.client.ClientConfiguration;
import org.apache.marmotta.client.clients.ImportClient; 
import org.apache.marmotta.client.clients.SPARQLClient;
import org.apache.marmotta.client.exception.MarmottaClientException;
import org.apache.marmotta.client.model.sparql.SPARQLResult;
import org.openrdf.rio.RDFFormat;
import org.openrdf.rio.Rio;
import org.openrdf.OpenRDFException;


import edu.cornell.library.misc.solr.SolrSearchTest;

public class MarmottaQuery {
	
	private String marmottaHost = "http://jaf30-dev.library.cornell.edu:8080/marmotta";
	private String context = "http://example.org/context";
	
	public MarmottaQuery() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		MarmottaQuery app = new MarmottaQuery();
		app.run();

	}
	
	public void run() {
		String term = "FAO";
		
		StringBuilder sb = new StringBuilder();
		sb.append("PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> ");
		sb.append("PREFIX rdfs:  <http://www.w3.org/2000/01/rdf-schema#> ");
		sb.append("PREFIX skos: <http://www.w3.org/2004/02/skos/core#> ");
		sb.append("PREFIX foaf: <http://xmlns.com/foaf/0.1/> ");
		sb.append("SELECT ?orgName ");
		
		sb.append("WHERE { ");
		sb.append("?org a foaf:Organization . ");
		sb.append("?org skos:altLabel ?orgLabel ");
		sb.append("} LIMIT 10 ");
		
		String query = sb.toString();
		
		String output = "xml";
		 
		ClientConfiguration configuration = new ClientConfiguration(marmottaHost);
		 
		configuration.setMarmottaContext(context);
		SPARQLClient sparqlClient = new SPARQLClient(configuration);
		
		try {			
			SPARQLResult result = sparqlClient.select(query);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MarmottaClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
