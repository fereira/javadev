package net.fereira;

import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.graph.Triple;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.OWL;

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
