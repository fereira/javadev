package net.fereira.orcid;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import net.fereira.bo.OrcidPojo; 

public class GetOrcidXml {
	protected final Log logger = LogFactory.getLog(getClass());
	private final String LIVE_PUBLIC_URI_V12 = "http://pub.orcid.org/v1.2";
	private final String SANDBOX_PUBLIC_URI_V12 = "http://pub.sandbox.orcid.org/v1.2";
	
	public GetOrcidXml() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GetOrcidXml app = new GetOrcidXml();
		System.out.println("Running...");
		app.run();
        System.out.println("Done.");
	}
	
	protected void run() {
	   try {
		  List<String> lines = FileUtils.readLines(new File("/usr/local/src/javadev/data/orcidlist.txt"));
		  for (String orcid: lines) {
		     //System.out.println("orcid: "+orcid);
		     String xml = getOrcidXml(orcid);
		     System.out.println(xml);
		  }
	   } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	   }
	   
	    
	}
	
	public String getOrcidXml(String orcid) {
		 
		 
		 
		String url = LIVE_PUBLIC_URI_V12 +"/"+ orcid  +"/orcid-profile";
		 
		String contentType = "application/orcid+xml";
		
		//System.out.println("orcid url: "+ url);
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		String result = new String();
        try { 
        	 
    		 
    		CloseableHttpClient httpclient = HttpClients.createDefault();
    		Header header = new BasicHeader(HttpHeaders.ACCEPT, contentType);
    		try {
    			HttpGet httpGet = new HttpGet(url);
    			httpGet.addHeader(header);
    			CloseableHttpResponse response = httpclient.execute(httpGet);
    			try {
    				//System.out.println(response.getStatusLine());
    				HttpEntity entity = response.getEntity();
    				InputStream is = entity.getContent();
    				
    				try {
    					IOUtils.copy(is, bout);

    				} finally {
    					is.close();
    				}

    			} finally {
    				response.close();
    			}

    		} catch (ClientProtocolException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} finally {
    			httpclient.close(); 
    		} 
    		result = bout.toString("UTF-8");
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return "";
        }
        return result;
        
	}
	
	 
	
	/**
	    * @param o
	    */
	   public static void printBusinessObject(Object o) {
	      Field[] fields = o.getClass().getDeclaredFields();

	      for (int i = 0; i < fields.length; i++) {
	         Field field = fields[i];
	         try {
	            field.setAccessible(true);
	            System.out.println(field.getName()+": "+field.get(o));
	         }
	         catch (IllegalAccessException e) {
	            System.err.println("Illegal access exception");
	         } catch (NullPointerException e) {
	            System.err.println("Nullpointer Exception");
	         }
	      }
	      System.out.println();
	   }

}
