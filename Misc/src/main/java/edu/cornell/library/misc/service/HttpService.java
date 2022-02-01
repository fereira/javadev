package edu.cornell.library.misc.service;

import java.io.InputStream;
import java.io.StringWriter;

 
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;

public class HttpService {

	public HttpService() {
		// TODO Auto-generated constructor stub
	}
	
	public  String getString(String uri, String contentType) throws Exception {
		Header header = new BasicHeader(HttpHeaders.ACCEPT, contentType);
		CloseableHttpClient httpclient = HttpClients.createDefault();
		 
		StringWriter writer = new StringWriter();
		String results = new String();
		try {
			HttpGet get = new HttpGet(uri);
			if (StringUtils.isNotEmpty(contentType)) {
			   get.addHeader(header);
			}
			//List <NameValuePair> nvps = new ArrayList <NameValuePair>(); 
		    //nvps.add(new BasicNameValuePair("email", "jaf30@cornell.edu"));
		    //nvps.add(new BasicNameValuePair("password", "vivoadmin"));
		    
			
			CloseableHttpResponse response = httpclient.execute(get);
			try {
				if( response == null )
	                throw new Exception("HTTP response for " +uri+ " was null.");
	            if( response != null &&
	                response.getStatusLine() != null &&
	                response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
	                throw new Exception("could not get HTTP for " + uri +
	                                    " status: " + response.getStatusLine() );
	            }
				HttpEntity entity = response.getEntity();
				InputStream is = entity.getContent();
				try {
					IOUtils.copy(is, writer, "UTF-8");
					results = writer.toString();
				} finally {
					is.close();
				}
				
			} finally {
				response.close();
			}
			
		} finally {
			httpclient.close();
		}
		return results;
	}

}
