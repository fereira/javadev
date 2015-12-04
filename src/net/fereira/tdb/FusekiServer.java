package net.fereira.tdb;

import org.apache.jena.fuseki.EmbeddedFusekiServer;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.sparql.core.DatasetGraph;
import com.hp.hpl.jena.sparql.core.DatasetGraphFactory;

public class FusekiServer {
	public static final int port = 3030;
	public static final String urlRoot = "http://localhost:" + port + "/";
	private static String datasetPath = "/ds";
	public static final String serviceUpdate = "http://localhost:" + port
			+ datasetPath + "/update";
	public static final String serviceQuery = "http://localhost:" + port
			+ datasetPath + "/query";
	public static final String serviceREST = "http://localhost:" + port
			+ datasetPath + "/data"; // ??????
    public static final String fileConfig = "/usr/local/src/javadev/onld/config-onld-text.ttl";
    
	private static EmbeddedFusekiServer server = null;
	
	public FusekiServer() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FusekiServer fusekiServer = new FusekiServer();
        fusekiServer.run();
	}
	
	public void run() {
		DatasetGraph dsg = DatasetGraphFactory.createMem();
		server = EmbeddedFusekiServer.configure(port, fileConfig);
		//server = EmbeddedFusekiServer.create(port, dsg, datasetPath);
		server.start();
		
		Query query = QueryFactory.create("SELECT * { ?s ?p ?o } LIMIT 10");
		QueryExecution qexec = QueryExecutionFactory.sparqlService(
				serviceQuery, query);
		ResultSet rs = qexec.execSelect();
		System.out.println(ResultSetFormatter.asText(rs));
		
		server.stop();
		
	}
	
	

}
