package edu.cornell.library.esdemo.service;

 
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpHost; 
import org.apache.http.client.config.RequestConfig;
import org.elasticsearch.action.admin.indices.settings.get.GetSettingsRequest;
import org.elasticsearch.action.admin.indices.settings.get.GetSettingsResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse; 
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.AdminClient;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.ElasticsearchClient;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.client.IndicesClient;
import org.elasticsearch.client.RequestOptions; 
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.tasks.ElasticsearchException;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.transport.Transport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;

import com.carrotsearch.hppc.cursors.ObjectObjectCursor;
import com.google.gson.Gson;

import edu.cornell.library.esdemo.bo.TestCourse; 

@Service
public class IndexService implements IIndexService {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	Gson gson = new Gson();
	
	final String COURSE_INDEX = "testcourses";
	 
	final String TYPE = "_doc"; 
	final String POST = "Post";
	final String DELETE = "Delete";
	
	RequestOptions options = RequestOptions.DEFAULT;
	
	final int MAXSIZE = 10000; 
	 
	//private RestHighLevelClient client = getClient();
	 
	
	@Autowired
	private RestHighLevelClient client;
	
	public RestHighLevelClient getClient() {
		 RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(
				    new HttpHost("localhost", 9200))
				    .setRequestConfigCallback(
				        new RestClientBuilder.RequestConfigCallback() {
				            @Override
				            public RequestConfig.Builder customizeRequestConfig(
				                    RequestConfig.Builder requestConfigBuilder) {
				                return requestConfigBuilder
				                    .setConnectTimeout(5000)
				                    .setSocketTimeout(60000);
				            }
				        }));
		 
		 return client;
	}
	
	public RestClient getRestClient() {
	    RestClientBuilder builder = RestClient.builder(
	            new HttpHost("localhost", 9200, "http"));
	    builder.setRequestConfigCallback(
	        new RestClientBuilder.RequestConfigCallback() {
	            @Override
	            public RequestConfig.Builder customizeRequestConfig(
	                    RequestConfig.Builder requestConfigBuilder) {
	                return requestConfigBuilder.setSocketTimeout(10000); 
	            }
	        }); 
        return builder.build();
    } 
	
	
    
	
	public boolean createCourseIndex() {
	    CreateIndexRequest createIndexRequest = new CreateIndexRequest("testcourses");
	    createIndexRequest.settings(Settings.builder()
	        .put("number_of_shards", 1)
	        .put("number_of_replicas", 0)
	        .put("index.requests.cache.enable", false)
	        .build());
	    Map<String, Map<String, String>> mappings = new HashMap<>();

	    mappings.put("courseName", Collections.singletonMap("type", "text"));
	    mappings.put("courseNumber", Collections.singletonMap("type", "keyword"));
	     
	    createIndexRequest.mapping(Collections.singletonMap("properties", mappings));
	    try {
	      CreateIndexResponse createIndexResponse = client.indices()
	          .create(createIndexRequest, RequestOptions.DEFAULT);
	      return createIndexResponse.isAcknowledged();
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    return false;
	  }
	
	 
	// Indexing methods
	
	public IndexResponse indexCourse(TestCourse course) throws Exception { 
		String json = gson.toJson(course);
		
		String id = String.valueOf(course.getId());
		deleteCourse(id);
		IndexRequest indexRequest = new IndexRequest(COURSE_INDEX);
		
		indexRequest.id(String.valueOf(course.getId()));
		indexRequest.source(json, XContentType.JSON);
		
		IndexResponse response = client.index(indexRequest, options);
	    return response;
		
	}
	
	public DeleteResponse deleteCourse(String id) throws Exception {
		//logger.debug("deleting course from index");
		DeleteRequest request = new DeleteRequest(
		        COURSE_INDEX,    
		        id);
		DeleteResponse deleteResponse = client.delete(request, options);
		return deleteResponse;

	}
	
	public String getElasticsearchIndexSetting(String indexName, String settingName) throws Exception {

	   
	    GetSettingsRequest request = new GetSettingsRequest().indices(indexName).names(settingName).includeDefaults(true);
	    GetSettingsResponse settingsResponse = client.indices().getSettings(request, RequestOptions.DEFAULT);

	    return settingsResponse.getSetting(indexName, settingName);
	    
	}
	 
}
