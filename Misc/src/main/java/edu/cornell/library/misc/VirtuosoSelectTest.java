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
package edu.cornell.library.misc;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils; 

import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFactory;
import com.hp.hpl.jena.query.ResultSetFormatter; 
public class VirtuosoSelectTest {

    public static void main(String[] args) throws Exception {
    	
    	String readEndpointURI = "http://128.253.87.162:8890/sparql";
    	 
    	String resultSetFormat = "application/rdf+xml";
    	
        CloseableHttpClient httpclient = HttpClients.createDefault();
        System.out.println("Test SELECT query");
        try {
            //String queryStr = "SELECT DISTINCT ?g WHERE { GRAPH ?g { ?s ?p ?o } } ORDER BY ?g";
            String queryStr = "SELECT ?s ?p ?o WHERE {  ?s ?p ?o } ";
            HttpGet httpGet = new HttpGet(new URIBuilder(readEndpointURI).addParameter("query", queryStr).build());
            CloseableHttpResponse response1 = httpclient.execute(httpGet);
            // The underlying HTTP connection is still held by the response object
            // to allow the response content to be streamed directly from the network socket.
            // In order to ensure correct deallocation of system resources
            // the user MUST call CloseableHttpResponse#close() from a finally clause.
            // Please note that if response content is not fully consumed the underlying
            // connection cannot be safely re-used and will be shut down and discarded
            // by the connection manager.
            
            try {
                int statusCode = response1.getStatusLine().getStatusCode(); 
                System.out.println("response " + statusCode + " for select. \n");                   
                InputStream in = response1.getEntity().getContent(); 
	            ResultSet resultSet = ResultSetFactory.fromXML(in);
	        	ByteArrayOutputStream outputStream = new ByteArrayOutputStream();	        	
	        	 
	        	//ResultSetFormatter.outputAsXML(outputStream, resultSet);
	        	 
	            System.out.println(ResultSetFormatter.asXMLString(resultSet));
	        	//InputStream result = new ByteArrayInputStream(outputStream.toByteArray());
            } finally {
                response1.close();
            } 
        } catch (Exception e) {
        	e.printStackTrace();
        }
        System.out.println("Done.");
             
    }

}
