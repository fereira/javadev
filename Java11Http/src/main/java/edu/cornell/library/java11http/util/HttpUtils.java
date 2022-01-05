package edu.cornell.library.java11http.util;

import java.io.IOException;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpRequest.Builder;
import java.net.http.HttpResponse.BodyHandlers;
import java.net.http.HttpResponse.PushPromiseHandler;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory; 

public class HttpUtils {
    
    protected final Log logger = LogFactory.getLog(getClass());

    public HttpUtils() {
        // TODO Auto-generated constructor stub
    }
    
    public HttpResponse<String> httpGetRequest(String uri) throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .uri(URI.create(uri))
            .headers("Accept-Enconding", "gzip, deflate")
            .build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

        String responseBody = response.body();
        int responseStatusCode = response.statusCode();

        logger.info("httpGetRequest: " + responseBody);
        logger.info("httpGetRequest status code: " + responseStatusCode);
        return response;
    }
    
    public HttpResponse<String> httpAuthenticatedGetRequest(String uri, String user, String password) throws URISyntaxException, IOException, InterruptedException {
        
         HttpClient client = HttpClient.newBuilder()
                .authenticator(new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(user,password.toCharArray());
                    }

                })
                .connectTimeout(Duration.ofSeconds(10))
                .build();
        
        HttpRequest request = HttpRequest.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .uri(URI.create(uri))
            .headers("Accept-Enconding", "gzip, deflate")
            .build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

        String responseBody = response.body();
        int responseStatusCode = response.statusCode();

        logger.info("httpGetRequest: " + responseBody);
        logger.info("httpGetRequest status code: " + responseStatusCode);
        return response;
    }
    
    public HttpResponse<String> httpCustomGetRequest(String uri, Optional<Map<String, String>> headers) throws URISyntaxException, IOException, InterruptedException {
        
       HttpClient client = HttpClient.newHttpClient();
       Builder builder = requestBuilder(new URI(uri), headers); 
       HttpRequest request = builder.build(); 
       HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

       String responseBody = response.body();
       int responseStatusCode = response.statusCode();

       logger.info("httpGetRequest: " + responseBody);
       logger.info("httpGetRequest status code: " + responseStatusCode);
       return response;
   }

    public HttpResponse<String> httpPostRequest(String uri, String body) throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();
        HttpRequest request = HttpRequest.newBuilder(new URI(uri))
            .version(HttpClient.Version.HTTP_2)
            .POST(BodyPublishers.ofString(body))
            .build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String responseBody = response.body();
        logger.info("httpPostRequest : " + responseBody);
        return response;
    }
    
    public HttpResponse<String> httpPostFormRequest(String uri, Map<Object, Object> data) throws URISyntaxException, IOException, InterruptedException {
       
        HttpClient client = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();
        
        HttpRequest request = HttpRequest.newBuilder(new URI(uri))
            .version(HttpClient.Version.HTTP_2)
            .POST(ofFormData(data))
            .setHeader("User-Agent", "Java 11 HttpClient") // add request header
            .header("Content-Type", "application/x-www-form-urlencoded")
            .build();
        
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String responseBody = response.body();
        logger.info("httpPostRequest : " + responseBody);
        return response;
    }
    
    public HttpResponse<String> httpPostJsonRequest(String uri, String json) throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();
        
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .uri(URI.create(uri))
                .setHeader("User-Agent", "Java 11 HttpClient") // add request header
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
       
        String responseBody = response.body();
        //logger.info("httpPostJsonRequest : " + responseBody);
        return response;
    }

    public String asynchronousGetRequest(String uri, int timeout) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        URI httpURI = new URI(uri);
        HttpRequest request = HttpRequest.newBuilder(httpURI)
            .version(HttpClient.Version.HTTP_2)
            .build();
        CompletableFuture<HttpResponse<String>> response =
                client.sendAsync(request, HttpResponse.BodyHandlers.ofString());

        String result = (String) response.thenApply(HttpResponse::body).get(5, TimeUnit.SECONDS);
        return result;
    }

    

    public void pushRequest(String uri) throws URISyntaxException, InterruptedException {
        logger.info("Running HTTP/2 Server Push example...");

        HttpClient httpClient = HttpClient.newBuilder()
            .version(Version.HTTP_2)
            .build();

        HttpRequest pageRequest = HttpRequest.newBuilder()
            .uri(URI.create(uri))
            .build();

        // Interface HttpResponse.PushPromiseHandler<T>
        // void applyPushPromise​(HttpRequest initiatingRequest, HttpRequest pushPromiseRequest, Function<HttpResponse.BodyHandler<T>,​CompletableFuture<HttpResponse<T>>> acceptor)
        httpClient.sendAsync(pageRequest, BodyHandlers.ofString(), pushPromiseHandler())
            .thenAccept(pageResponse -> {
                logger.info("Page response status code: " + pageResponse.statusCode());
                logger.info("Page response headers: " + pageResponse.headers());
                String responseBody = pageResponse.body();
                logger.info(responseBody);
            }).join();

        Thread.sleep(1000); // waiting for full response
    }

    private PushPromiseHandler<String> pushPromiseHandler() {
        return (HttpRequest initiatingRequest, 
            HttpRequest pushPromiseRequest, 
            Function<HttpResponse.BodyHandler<String>, 
            CompletableFuture<HttpResponse<String>>> acceptor) -> {
            acceptor.apply(BodyHandlers.ofString())
                .thenAccept(resp -> {
                    logger.info(" Pushed response: " + resp.uri() + ", headers: " + resp.headers());
                });
            logger.info("Promise request: " + pushPromiseRequest.uri());
            logger.info("Promise request: " + pushPromiseRequest.headers());
        };
    }
    
    protected HttpRequest.BodyPublisher ofFormData(Map<Object, Object> data) {
        var builder = new StringBuilder();
        for (Map.Entry<Object, Object> entry : data.entrySet()) {
            if (builder.length() > 0) {
                builder.append("&");
            }
            builder.append(URLEncoder.encode(entry.getKey().toString(), StandardCharsets.UTF_8));
            builder.append("=");
            builder.append(URLEncoder.encode(entry.getValue().toString(), StandardCharsets.UTF_8));
        }
        return HttpRequest.BodyPublishers.ofString(builder.toString());
    }
    
    protected Builder requestBuilder(URI uri,  Optional<Map<String, String>> additionalHeaders) {
        
        Builder builder =  HttpRequest.newBuilder()
            .uri(uri)
            .timeout(Duration.ofMinutes(1))
            .header("Content-Type", "application/json");
        if (additionalHeaders.isPresent()) {
          additionalHeaders.get().forEach((k, v) -> builder.header(k, v));
        }
        return builder;
    }
}
