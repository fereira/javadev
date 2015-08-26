package net.fereira.agris;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;

public class AgrisSparql {
    private final String endpoint = "http://202.45.139.84:10035/catalogs/fao/repositories/agris";
    //private final String endpoint = "http://ring.ciard.net/sparql1";
    
	public AgrisSparql() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AgrisSparql app = new AgrisSparql();
		String term = new String();
		if (args.length > 0) {
		  term = args[0];	
		} else {
		   System.err.println("Usage: you must provide a term");
		   System.exit(1);
		}
        app.doQuery(term);
	}
	
	protected void doQuery(String term) {
		
		StringBuilder sb = new StringBuilder();
		sb.append(getPrefixes());
		sb.append("SELECT ?s ?p ?o "); 
		sb.append("WHERE { ?s ?p ?o } ");		 
		sb.append("LIMIT 20 ");
		
		String qs= getPrefixes() +
		                   "SELECT DISTINCT ?person ?name ?o WHERE {"+
				            "?person a foaf:Person . "+
		                    "?person foaf:name ?name ." +
		                    "} LIMIT 300";
	    
		String queryString = sb.toString();
		System.out.println("Executing Query");
		
        Query query = QueryFactory.create(qs); 
        QueryExecution qexec = QueryExecutionFactory.sparqlService(endpoint, query);
        try {
        	int resultSetSize = 0;
         
            ResultSet results = qexec.execSelect();
            ResultSetFormatter.out(System.out, results, query);
            
        } finally {
            qexec.close();
        }
		System.out.println("Done.");
	}
	
	protected String getPrefixes() {
		StringBuilder sb = new StringBuilder();
		sb.append("PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> ");
		sb.append("PREFIX rdfs:  <http://www.w3.org/2000/01/rdf-schema#> ");
		sb.append("PREFIX skos: <http://www.w3.org/2004/02/skos/core#> ");
		sb.append("PREFIX foaf: <http://xmlns.com/foaf/0.1/> ");
		sb.append("PREFIX dc: <http://purl.org/dc/terms/> ");
		sb.append("PREFIX dcterms:	<http://purl.org/terms> ");
		sb.append("PREFIX dcat: <http://www.w3.org/ns/dcat#> ");
		return sb.toString();
	}

}
