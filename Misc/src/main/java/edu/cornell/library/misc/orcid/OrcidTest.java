package edu.cornell.library.misc.orcid;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.ObjectUtils;
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

import edu.cornell.library.misc.bo.OrcidPojo; 

public class OrcidTest {
	protected final Log logger = LogFactory.getLog(getClass());
	private final String LIVE_PUBLIC_URI_V12 = "http://pub.orcid.org/v1.2";
	private final String SANDBOX_PUBLIC_URI_V12 = "http://pub.sandbox.orcid.org/v1.2";
	
	public OrcidTest() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		OrcidTest app = new OrcidTest();
		System.out.println("Running...");
		app.run();
        System.out.println("Done.");
	}
	
	protected void run() {
	   String orcid = "0000-0002-5982-8983";
	   String json = getOrcidJson(orcid);
	   //System.out.println(json);
	   parseJson(json);
	}
	
	public String getOrcidJson(String orcid) {
		 
		 
		 
		String url = LIVE_PUBLIC_URI_V12 +"/"+ orcid  +"/orcid-profile";
		 
		String contentType = "application/orcid+json";
		
		System.out.println("orcid url: "+ url);
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
	
	protected Map<String, String> parseJson(String json) {
		JSONParser parser = new JSONParser();
		Map<String, String> resultMap = new HashMap<String, String>(); 
		Object obj = new Object(); 
		JSONObject jsonObject = new JSONObject(); 
		OrcidPojo profile = new OrcidPojo();
		
		try {
			 
			obj = parser.parse(json);
			jsonObject = (JSONObject) obj; 
			 
			JSONObject orcidProfile = (JSONObject) jsonObject.get("orcid-profile"); // jsonObject
			if (orcidProfile == null) return resultMap;
			 
			if (orcidProfile.containsKey("orcid-activities")) { 
				JSONObject orcidActivities = (JSONObject) orcidProfile.get("orcid-activities"); 
				
				if (orcidActivities.containsKey("orcid-works")) { 
					JSONObject orcidWorks = (JSONObject) orcidActivities.get("orcid-works"); 
					if (orcidWorks.containsKey("orcid-work")) {
						JSONArray orcidWorkArray  =  (JSONArray) orcidWorks.get("orcid-work");
						Iterator<?> workIter = orcidWorkArray.iterator();
						while (workIter.hasNext()) {
							JSONObject work = (JSONObject) workIter.next();
							//if (work != null) printIterator(work);
							if (work != null && work.containsKey("work-title")) {
							   JSONObject workTitle = (JSONObject) work.get("work-title");
							   JSONObject title = (JSONObject) workTitle.get("title");
							   profile.setTitle(title.get("value").toString());	
							}
							if (work.containsKey("work-type")) { 
							   profile.setDocType(work.get("work-type").toString());	
							}
							 
							
						}
						 
					}
				}
				
			}
			printBusinessObject(profile);
			
			 

		} catch (ParseException e) {
			e.printStackTrace();
			System.err.println(json);
		} catch (NullPointerException npe) {
			npe.printStackTrace();
			System.err.println(json);	
		}
		return resultMap;
	}
	
	public void printIterator(JSONObject obj) {
		Iterator iter = obj.keySet().iterator();
		while (iter.hasNext()) {
			System.out.println("key: "+ iter.next());
		}
		System.out.println();
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
