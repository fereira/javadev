package edu.cornell.library.folioapi.services;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional; 
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.cornell.library.folioapi.util.HttpUtils; 

public class ApiServiceNew {
	
	private static final Logger logger = LoggerFactory.getLogger(ApiServiceNew.class);
	private String tenant;

	public String getTenant() {
		return tenant;
	}

	public void setTenant(String tenant) {
		this.tenant = tenant;
	}

	public ApiServiceNew(String tenant) {
		this.tenant = tenant;
	}
	
    public String callApiGet(String url, String token) throws Exception, IOException, InterruptedException {
        HttpUtils httpUtils = new HttpUtils();
        Map<String, String> map = new HashMap<String, String>();  
        map.put("x-okapi-tenent", this.tenant);
        map.put("Accept", "application/json");
        map.put("x-okapi-token", token);
        Optional<Map<String, String>> headers = Optional.of(map); 
         
        HttpResponse<String> response = null;
        try {
            response = (HttpResponse<String>) httpUtils.httpCustomGetRequest(url, headers);
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String responseString = response.body();          
        int responseCode = response.statusCode();
        
        

        logger.debug("GET:");
        logger.debug(url);
        logger.debug(String.valueOf(responseCode));
        logger.debug(responseString);

        if (responseCode > 399) {
            logger.error("Failed GET");
            logger.error(responseString);
            throw new Exception(responseString);
        }
        return responseString;

    }
	
	
	
    //POST TO PO SEEMS TO WANT UTF8 (FOR SPECIAL CHARS)
	//IF UTF8 IS USED TO POST TO SOURCE RECORD STORAGE
	//SPECIAL CHARS DON'T LOOK CORRECT 
	//TODO - combine the two post methods
	public String callApiPostWithUtf8(String url, JSONObject body, String token)
			throws Exception, IOException, InterruptedException {
	    HttpUtils httpUtils = new HttpUtils();
        Map<String, String> map = new HashMap<String, String>();  
        map.put("x-okapi-tenent", this.tenant);
        map.put("Accept", "application/json");
        map.put("x-okapi-token", token);
        Optional<Map<String, String>> headers = Optional.of(map); 
 
        HttpResponse<String> response = null;
         
        try {
            response = (HttpResponse<String>) httpUtils.httpCustomPostRequest(url, toUTFString(body.toString()), headers);
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String responseString = response.body();          
        int responseCode = response.statusCode(); 

		logger.debug("POST:");
		logger.debug(body.toString());
		logger.debug(url);
		logger.debug(String.valueOf(responseCode));
		logger.debug(responseString);

		if (responseCode > 399) {
			logger.error("Failed POST");
			logger.error(responseString);
			throw new Exception(responseString);
		}

		return responseString;

	}
	
	public String callApiPut(String url, JSONObject body, String token)
			throws Exception, IOException, InterruptedException {
	    
	    HttpUtils httpUtils = new HttpUtils();
        Map<String, String> map = new HashMap<String, String>();  
        map.put("x-okapi-tenent", this.tenant);
        map.put("Accept", "application/json");
        map.put("x-okapi-token", token);
        Optional<Map<String, String>> headers = Optional.of(map); 
         
        HttpResponse<String> response = null;
        
        try {
            response = (HttpResponse<String>) httpUtils.httpCustomPutRequest(url, toUTFString(body.toString()), headers);
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String responseString = response.body();          
        int responseCode = response.statusCode(); 
        
		logger.debug("PUT:");
		logger.debug(body.toString());
		logger.debug(url);
		logger.debug(String.valueOf(responseCode));

		if (responseCode > 399) {
			logger.error("Failed PUT");
			logger.error("BODY");
			logger.error(body.toString(3)); 
			if (responseCode == 400) {
			    throw new Exception("Response: " + responseCode +" : "+ responseString);
			} else {
			    logger.error("Response: " + responseCode);
			    throw new Exception("Response: " + responseCode);
			}
		}
			
		return "ok";

	}
	
	public  String callApiAuth(String url,  JSONObject  body)
			throws Exception, IOException, InterruptedException {
	        HttpUtils httpUtils = new HttpUtils();
		    Map<String, String> map = new HashMap<String, String>();  
	        map.put("x-okapi-tenent", this.tenant);
	        map.put("Accept", "application/json");
	        
	        Optional<Map<String, String>> headers = Optional.of(map); 
		     
		    HttpResponse<String> response = null;
	        try {
	            response = (HttpResponse<String>) httpUtils.httpCustomPostRequest(url, body.toString(), headers);
	        } catch (URISyntaxException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (InterruptedException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        String responseBody = response.body();	        
	        int responseCode = response.statusCode();

			logger.info("POST:");
			logger.info(body.toString());
			logger.info(url);
			logger.info(String.valueOf(responseCode));
			logger.info(responseBody);

			if (responseCode > 399) {
				logger.error("FAILED Authn");
				throw new Exception(responseBody);
			}
			Optional<String> tok = response.headers().firstValue("x-okapi-token"); 
			return tok.get();

	}
	
	protected String toUTFString(String str) {
	    ByteBuffer buffer = StandardCharsets.UTF_8.encode(str); 

	    String utf8EncodedString = StandardCharsets.UTF_8.decode(buffer).toString();
	    return utf8EncodedString;
	}

}
