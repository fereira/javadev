package edu.cornell.library.misc;

import java.io.File;
import java.io.UnsupportedEncodingException; 
import org.apache.commons.lang.StringEscapeUtils; 
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
	
	private void processString(String s) {
		//String newstr =  StringEscapeUtils.unescapeHtml(s);
		String newstr = normalizeString(s);
	    //newstr = WordUtils.capitalize(newstr.toLowerCase());
		
	    System.out.println("str: "+ s + " > "+ newstr);
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
