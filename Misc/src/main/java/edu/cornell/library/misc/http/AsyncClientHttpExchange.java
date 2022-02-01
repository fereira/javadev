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
package edu.cornell.library.misc.http;

import java.io.IOException;
import java.nio.CharBuffer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Future;

import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.nio.IOControl;
import org.apache.http.nio.client.methods.AsyncCharConsumer;
import org.apache.http.nio.client.methods.HttpAsyncMethods;
import org.apache.http.nio.conn.NHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;

/**
 * This example demonstrates a basic asynchronous HTTP request / response exchange.
 * Response content is buffered in memory for simplicity.
 */
public class AsyncClientHttpExchange {

	public static void main(final String[] args) throws Exception {
		RequestConfig requestConfig = RequestConfig.custom()
	            .setSocketTimeout(600000)
	            .setConnectTimeout(600000).build();
	    CloseableHttpAsyncClient httpclient = HttpAsyncClients.custom()
	            .setDefaultRequestConfig(requestConfig)
	            .build();
        
        try {
            httpclient.start();
            System.out.println(getTime()+"Request start");
            Future<Boolean> future = httpclient.execute(
                    HttpAsyncMethods.createGet("http://api.library.cornell.edu/CourseReserves/indexCourses"),
                    new MyResponseConsumer(), null);
            Boolean result = future.get();
            if (result != null && result.booleanValue()) {
                System.out.println(getTime()+"Request successfully executed");
            } else {
                System.out.println(getTime()+"Request failed");
            }
            System.out.println(getTime()+"Shutting down");
        } finally {
            httpclient.close();
        }
        System.out.println(getTime()+"Done");
    }
	
	 
	
	public static String getTime() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss ");  
		LocalDateTime now = LocalDateTime.now();  
		return dtf.format(now);
	}

    static class MyResponseConsumer extends AsyncCharConsumer<Boolean> {

        @Override
        protected void onResponseReceived(final HttpResponse response) {
        	System.out.println(getTime()+"Response Received");
        }

        @Override
        protected void onCharReceived(final CharBuffer buf, final IOControl ioctrl) throws IOException {
            while (buf.hasRemaining()) {
                System.out.print(buf.get());
            }
        }

        @Override
        protected void releaseResources() {
        }

        @Override
        protected Boolean buildResult(final HttpContext context) {
            return Boolean.TRUE;
        }

    }


}
