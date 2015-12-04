package net.fereira;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;

public class StringTest {

	public StringTest() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args)  {
        StringTest app = new StringTest();
        app.run();
    }
	
	public  void run() {
	   String str = "John Fereira";
	   
	   System.out.println("before: "+ str);
	    
	    try {
		   String after = URLEncoder.encode(str, "UTF-8");
		   System.out.println("after: "+ after);
	    } catch (UnsupportedEncodingException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
	    }
	    System.out.println("key: "+getJournalIssueKey("foo/bar/one"));
	   
	}
	
	public String getJournalIssueKey(String path) {
		   
		   String[] parts = StringUtils.split(path, '/');
		   String key = parts[0] +"-"+parts[1];
		   return key;
		}

}
