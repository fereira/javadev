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

import virtuoso.jena.driver.VirtGraph;
import virtuoso.jena.driver.VirtuosoUpdateFactory;
import virtuoso.jena.driver.VirtuosoUpdateRequest;

import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFactory;
import com.hp.hpl.jena.query.ResultSetFormatter; 
public class VirtuosoInsertTest {

    public static void main(String[] args) throws Exception {
    	
    	String readEndpointURI = "http://128.253.87.162:8890/sparql";
    	String updateEndpointURI = "jdbc:virtuoso://128.253.87.162:1111";
    	String resultSetFormat = "application/rdf+xml";
    	 
        System.out.println("Test SELECT query");
        
             
        System.out.println("Test SPARQL INSERT");
        String updateString = "INSERT { GRAPH <http://vitro.mannlib.cornell.edu/default/inferred-tbox> { <http://vivoweb.org/ontology/core#Division> <http://www.w3.org/2002/07/owl#equivalentClass> <http://vivoweb.org/ontology/core#Division> . }}";
        VirtGraph set = new VirtGraph (updateEndpointURI, "dba", "skikt22");
        
        VirtuosoUpdateRequest vur = VirtuosoUpdateFactory.create(updateString, set);
        vur.exec(); 
        System.out.println("Done");     
         
    }

}
