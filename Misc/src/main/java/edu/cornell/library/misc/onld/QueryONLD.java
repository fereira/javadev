package edu.cornell.library.misc.onld;

import java.util.HashMap;
import java.util.Iterator;

import org.apache.jena.query.Dataset;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.tdb.TDBFactory;
import org.apache.jena.util.FileManager;

public class QueryONLD {

	public QueryONLD() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		QueryONLD app = new QueryONLD();
		System.out.println("Query ONLD");
		String term = new String();
		if (args.length > 0) {
		  term = args[0];	
		} else {
		   System.err.println("Usage: you must provide a term");
		   System.exit(1);
		}
		
		//String tdb = "/usr/local/src/javadev/onld/tdb";
		//Dataset dataset = TDBFactory.createDataset(tdb);
		StringBuilder sb = new StringBuilder();
		sb.append("PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> ");
		sb.append("PREFIX rdfs:  <http://www.w3.org/2000/01/rdf-schema#> ");
		sb.append("PREFIX skos: <http://www.w3.org/2004/02/skos/core#> ");
		sb.append("PREFIX foaf: <http://xmlns.com/foaf/0.1/> ");
		sb.append("PREFIX text: <http://jena.apache.org/text#> ");
		
		//sb.append("SELECT * "); 
		//sb.append("WHERE { ?s ?p ?o  } LIMIT 10 "); 
		 
		sb.append("SELECT ?label "); 
		sb.append("{ ?s text:query (skos:altLabel '"+ term +"' 3) ;  ");		 
		sb.append("skos:prefLabel ?label ");
	    sb.append("}" );
		
		String queryString = sb.toString();
		//System.out.println(queryString);
		
        Query query = QueryFactory.create(queryString);
        
        //QueryExecution qexec = QueryExecutionFactory.create(query, dataset.getDefaultModel());
        //QueryExecution qexec = QueryExecutionFactory.sparqlService("http://localhost:3030/ds/sparql", query);
        QueryExecution qexec = QueryExecutionFactory.sparqlService("http://localhost:3030/ds/sparql", query);
        try {
        	int resultSetSize = 0;
         
            ResultSet results = qexec.execSelect();
            while (results.hasNext()) {
                resultSetSize++;
                QuerySolution solution = results.nextSolution();
                Iterator varnames = solution.varNames();
                HashMap<String, String> hm = new HashMap<String, String>();
                while (varnames.hasNext()) {
                   String name = (String) varnames.next();
                   RDFNode rdfnode = solution.get(name);
                   //logger.info("rdf node name, type: "+ name +", "+getRDFNodeType(rdfnode));
                   if (rdfnode.isLiteral()) {
                      Literal literal = rdfnode.asLiteral();
                      String nodeval = literal.getString();
                      hm.put(name, nodeval);
                      System.out.println(nodeval);
                   } else if (rdfnode.isResource()) {
                      Resource resource = rdfnode.asResource();
                      String nodeval = resource.toString();
                      hm.put(name, nodeval);
                      System.out.println(nodeval);
                   }
                   
                } 
            }
        } finally {
            qexec.close();
        }
		System.out.println("Done.");

	}

}
