package net.fereira;

import static com.hp.hpl.jena.rdf.model.ResourceFactory.createProperty;
import static com.hp.hpl.jena.rdf.model.ResourceFactory.createResource;

import com.hp.hpl.jena.shared.InvalidPropertyURIException;

public class UriTest {

	public UriTest() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 String uriStr = "http://foo.bar/123";
		 
		String localName = uriStr.substring(uriStr.lastIndexOf("/") + 1);
		//if local name is only numbers, this is not a valid uri for a property
		// ResourceFactory.createProperty will throw an exception so don't call it
		// and just return false;
		
		if(localName.matches("\\d+")) {
			System.out.println("local name is all digits");
		} else {
			System.out.println("localName is valid");
		}
		createResource(uriStr);
		try {
		   System.out.println(createProperty(uriStr));
		} catch (InvalidPropertyURIException ex) {
			System.out.println("uri is invalid");	
		}
	}

}
