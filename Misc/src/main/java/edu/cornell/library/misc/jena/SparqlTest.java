package edu.cornell.library.misc.jena;

import java.util.HashMap;
import java.util.Iterator;

import com.hp.hpl.jena.sparql.resultset.ResultsFormat; 

public class SparqlTest {
	
	protected static HashMap<String, ResultsFormat> formatSymbols = new HashMap<String, ResultsFormat>();
    static { 
    	    
            formatSymbols.put(ResultsFormat.FMT_RS_XML.getSymbol(), ResultsFormat.FMT_RS_XML);
            formatSymbols.put(ResultsFormat.FMT_RDF_XML.getSymbol(), ResultsFormat.FMT_RDF_XML);
            formatSymbols.put(ResultsFormat.FMT_RDF_N3.getSymbol(), ResultsFormat.FMT_RDF_N3);
            formatSymbols.put(ResultsFormat.FMT_RS_CSV.getSymbol(), ResultsFormat.FMT_RS_CSV);
            formatSymbols.put(ResultsFormat.FMT_TEXT.getSymbol(), ResultsFormat.FMT_TEXT);
            formatSymbols.put(ResultsFormat.FMT_RS_JSON.getSymbol(), ResultsFormat.FMT_RS_JSON);
    }
    
     

	public SparqlTest() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		SparqlTest app = new SparqlTest();
		app.run();
	}
	
	public void run() {
		Iterator iter = formatSymbols.keySet().iterator();
		while (iter.hasNext()) {
			String key = (String) iter.next();
			System.out.println(key+": "+formatSymbols.get(key));
			
		}
	}

}
