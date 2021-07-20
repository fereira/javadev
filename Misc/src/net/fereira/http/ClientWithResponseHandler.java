package net.fereira.http;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.util.EntityUtils;

/**
 * This example demonstrates the use of the {@link ResponseHandler} to simplify
 * the process of processing the HTTP response and releasing associated resources.
 */
public class ClientWithResponseHandler {

    public final static void main(String[] args) throws Exception {
        //CloseableHttpClient httpclient = HttpClients.createDefault();
        RequestConfig requestConfig = RequestConfig.custom()
	            .setSocketTimeout(600000) 
	            .setConnectTimeout(600000).build();
	    CloseableHttpClient httpclient = HttpClients.custom()
	            .setDefaultRequestConfig(requestConfig)
	            .build();
        try {
        	String url = "http://api.library.cornell.edu/CourseReserves/bulkindexReserveItems";
            HttpGet httpget = new HttpGet(url);

            System.out.println(getTime()+"Executing request " + httpget.getRequestLine());

            // Create a custom response handler
            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

                @Override
                public String handleResponse(
                        final HttpResponse response) throws ClientProtocolException, IOException {
                    int status = response.getStatusLine().getStatusCode();
                    if (status >= 200 && status < 300) {
                        HttpEntity entity = response.getEntity();
                        return entity != null ? EntityUtils.toString(entity) : null;
                    } else if (status == 503) {
                    	System.err.println(response.getStatusLine());
                    	throw new IOException();
                    } else {
                     
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                }

            };
            String responseBody = httpclient.execute(httpget, responseHandler);
            System.out.println(getTime()+"----------------------------------------");
            System.out.println(getTime()+responseBody);
        } catch (Exception ex) {
           ex.printStackTrace();	
        }
        finally {
         
            httpclient.close();
        }
    }
    
    public static String getTime() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss ");  
		LocalDateTime now = LocalDateTime.now();  
		return dtf.format(now);
	}

}

