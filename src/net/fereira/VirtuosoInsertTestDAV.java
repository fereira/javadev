/*
 * ====================================================================
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */
package net.fereira;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils; 

import virtuoso.jena.driver.VirtGraph;
import virtuoso.jena.driver.VirtuosoUpdateFactory;
import virtuoso.jena.driver.VirtuosoUpdateRequest;

import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFactory;
import com.hp.hpl.jena.query.ResultSetFormatter; 


public class VirtuosoInsertTestDAV {
	String username = "dba";
	String password = "dba";
	String baseURI = "http://jaf30-dev.library.cornell.edu:8890";
	String readEndpointURI = "http://jaf30-dev.library.cornell.edu:8890/sparql";
	String updateEndpointURI = baseURI + "/DAV/home/" + username + "/rdf_sink/vitro_update";
	String resultSetFormat = "application/rdf+xml";
	
	protected CloseableHttpClient httpClient;
	
    public static void main(String[] args) throws Exception {
    	
    	VirtuosoInsertTestDAV app = new VirtuosoInsertTestDAV();
    	app.run();
        
         
    }
    
    public void run() { 
    	
        System.out.println("Test SPARQL INSERT");
        String updateString = "INSERT { GRAPH <http://vitro.mannlib.cornell.edu/default/inferred-tbox> { <http://vivoweb.org/ontology/core#Division> <http://www.w3.org/2002/07/owl#equivalentClass> <http://vivoweb.org/ontology/core#Division> . }}";
        try {
        	httpClient = HttpClients.createDefault();
			System.out.println("get httpClientContext");
			HttpClientContext httpClientContext = createHttpContext();
			 
			System.out.println("get post request");
			HttpPost post = createHttpRequest(updateString);
			System.out.println("get response");
			CloseableHttpResponse response = httpClient.execute(post, httpClientContext);
			
			//CloseableHttpResponse response = httpClient.execute(
			//		createHttpRequest(updateString), createHttpContext());
			System.out.println("Got response back from httpClient");
			try {
				int statusCode = response.getStatusLine().getStatusCode();
				if (statusCode > 399) {
					System.err.println("response " + response.getStatusLine()
							+ " to update. \n");
					 
					InputStream content = response.getEntity().getContent();
					for (String line : IOUtils.readLines(content)) {
						System.err.println("response-line >>" + line);
					}

					System.err.println(
							"Unable to perform SPARQL UPDATE: status code = "
									+ statusCode);
				}
			} finally {
				response.close();
			}
		} catch (Exception e) {
			System.err.println("Failed to update: ");
			e.printStackTrace();
		}
        System.out.println("Done");     	
    }
    
    private  HttpPost createHttpRequest(String updateString) {
		//log.info("Posting to updateEndpointURI: "+updateEndpointURI);
		HttpPost meth = new HttpPost(this.updateEndpointURI);
		meth.addHeader("Content-Type", "application/sparql-query");
		meth.setEntity(new StringEntity(updateString, "UTF-8"));
		return meth;
	}

	/**
	 * We need an HttpContext that will provide username and password in
	 * response to a basic authentication challenge.
	 */
	private HttpClientContext createHttpContext() {
		CredentialsProvider provider = new BasicCredentialsProvider();
		provider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(
				username, password));

		HttpClientContext context = HttpClientContext.create();
		context.setCredentialsProvider(provider);
		return context;
	}

}
