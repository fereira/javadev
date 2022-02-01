package edu.cornell.library.misc;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import edu.cornell.library.misc.util.XMLUtils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList; 

public class StringTools{
	
	private String firstNamePath = "//rdf:RDF/rdf:Description/node-person:FirstName";
	private String lastNamePath = "//rdf:RDF/rdf:Description/node-person:LastName";

	public StringTools() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args)  {
        StringTools app = new StringTools();
        app.run();
    }
	
	public  void run() {
		System.out.println("Running app");
		String str = "Acad\u00e9mie de Cr\u00e9teil Cr\u00e9teil@Edumarket FR";
		String fileName = "/usr/local/src/javadev/data/aims/raw-records/person_node_-_88";
		String dataDir = "/usr/local/src/javadev/data/aims/raw-records";
		processString(str);
		
		//processFile();
		//processDir(dataDir);
		System.out.println("Done.");
	}
	
	private void processFile(String fileName) {
		
		try {
			normalizeName(new File(fileName));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void processDir(String dataDir) {
		
		Collection<File> fileList = new ArrayList<File>();
		fileList = FileUtils.listFiles(new File(dataDir), null, false);	    
		
		try {
			for (File file: fileList) {
			   //System.out.println("Processing file: "+ file.getName());	
			   normalizeName(file);			    
			}
		} catch (Exception ex) { 
			   ex.printStackTrace();
		} finally {
			   //
		} 
		
		
	}
	
	 
	
	private void processString(String s) {
		//String newstr =  StringEscapeUtils.unescapeHtml(s);
		String newstr = normalizeString(s);
	    //newstr = WordUtils.capitalize(newstr.toLowerCase());
		
	    System.out.println("str: "+ s + " > "+ newstr);
	}
	
	private void normalizeName(File file) throws Exception {
		 
		boolean updated = false;
		 
		//Document doc = XMLUtils.getDocument(FileUtils.openInputStream(file), "ISO-8859-1");
		Document doc = XMLUtils.getDocument(FileUtils.openInputStream(file), "UTF-8");
	    Element root = doc.getDocumentElement();
	    NodeList nodelist = root.getElementsByTagName("rdf:Description");
	    Node rdfNode = nodelist.item(0);  // there is only one
	    // XMLUtils.serializeNode (rdfNode) ;
	    Node parentNode = rdfNode.getParentNode();
	    
	    Node firstNameNode = XMLUtils.getNodeWithXpath(doc, this.firstNamePath);
	    Node lastNameNode = XMLUtils.getNodeWithXpath(doc, this.lastNamePath);
	    
	    
	    if (firstNameNode == null ) {
	        
	       System.err.println("missing first name "+ file.getName());
	       return;
	    }
		if (lastNameNode == null) {
			 
			System.err.println("missing last name " + file.getName());
			return;
		}
	    
	    
 
	    String firstName = firstNameNode.getTextContent();
	    String lastName = lastNameNode.getTextContent();
	   
 
	    String newFirstName = new String();
	    String newLastName = new String();
	    
	    if (StringUtils.isNotEmpty(firstName)) {
	       		 
	       //newFirstName = fixNamePart(firstName);
	    	newFirstName = normalizeString(firstName);
	       if (! firstName.equals(newFirstName)) {		        
		       System.out.println("First: "+ firstName+ " > "+newFirstName);
	    	}
	    } else {
	    	 //System.err.println("orgString is empty");
	    }
	    if (StringUtils.isNotEmpty(lastName)) {	    	 
	    	//newLastName = fixNamePart(lastName);
	    	newLastName = normalizeString(lastName);
	    	if (! lastName.equals(newLastName)) {		        
		        System.out.println("Last: "+ lastName+ " > "+newLastName);
	    	}
	    } else {
	    	 //System.err.println("orgString is empty");
	    } 
	}
	
	public String fixNamePart(String s) {
	    String newstr =  StringEscapeUtils.unescapeHtml(s);
	    //newstr = WordUtils.capitalize(newstr.toLowerCase());
	    return newstr;
	    
	 }
	
	public static final String utf8ToString( byte[] bytes ) {
		if ( bytes == null ) {
			return "";
		}

		try	{
			return new String( bytes, "UTF-8" );
		} catch ( UnsupportedEncodingException uee ) {
			return "";
		}
	}
	
	public static String toHTML(String unicode) {
	    String output = "";

	    char[] charArray = unicode.toCharArray();
	  
	    for (int i = 0; i < charArray.length; ++i) {
	      char a = charArray[i];
	      if ((int) a > 255) {
	        output += "&#" + (int) a + ";";
	      } else {
	        output += a;
	      }
	    }
	    return output;
	  }

	  public static String toJAVA(String unicode) {
	    String output = "";

	    char[] charArray = unicode.toCharArray();

	    for (int i = 0; i < charArray.length; ++i) {
	      char a = charArray[i];
	      if ((int) a > 255) {
	        output += "\\u" + Integer.toHexString((int) a);
	      } else {
	        output += a;
	      }
	    }

	    return output;
	  }
	  
	  private String normalizeString(String original) {
		  try {
			    byte[] utf8Bytes = original.getBytes("UTF8");
			    byte[] defaultBytes = original.getBytes();

			    return  new String(utf8Bytes, "UTF8"); 
			} 
			catch (UnsupportedEncodingException e) {
			    e.printStackTrace();
			    return original;
			}
	  }
}
