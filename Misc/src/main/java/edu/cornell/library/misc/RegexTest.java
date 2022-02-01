package edu.cornell.library.misc;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;

public class RegexTest {

	public RegexTest() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args)  {
        RegexTest app = new RegexTest();
        app.run();
    }
	
	public  void run() {
	   System.out.println("feb2011 > "+parseIssued("feb2011"));
	   System.out.println("2012 > "+parseIssued("2012"));
	   
	}
	
	protected String parseIssued(String issued) { 
    	final String PATTERN_YEAR = ".*(\\d{4}).*";
    	Pattern pattern = Pattern.compile(PATTERN_YEAR);
        Matcher matcher = pattern.matcher(issued);
        if (matcher.find()) {
        	return matcher.group(1);
        } else {
        	return "";
        }
	}

}
