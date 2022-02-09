package edu.cornell.library.misc.jena;

import org.apache.jena.graph.Node;
import org.apache.jena.graph.Triple;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.OWL;

public class SameAsTest {

	public SameAsTest() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SameAsTest app = new SameAsTest();
		app.run();

	}
	
	public void run() {
		Model model = ModelFactory.createDefaultModel();
		
		Node subj = null;
		Node obj = null;;
		String oldUri = "http://agriperfiles.agri-d.net/individual/n8084";
		String newUri = "http://vivo.agriprofiles.net/individual/n12345";
		Resource newRes = model.createResource(newUri);
		Resource oldRes = model.createResource(oldUri);
		
		newRes.addProperty(OWL.sameAs, oldUri);
		 
		Triple triple = Triple.create(subj, newRes.asNode(), obj);
		
		
	}

}
