package edu.cornell.library.esdemo;

import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.common.settings.Settings;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	/**
	@Bean
	public boolean createTestIndex(RestHighLevelClient restHighLevelClient) throws Exception {
		
	    try {
	        System.out.println("Deleting testcourse index");
			DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest("testcourses");
			restHighLevelClient.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT); // 1
		} catch (Exception ignored) {
		}
	    
	    System.out.println("Creating testcourse index"); 
		CreateIndexRequest createIndexRequest = new CreateIndexRequest("testcourses");
		createIndexRequest.settings(
				Settings.builder().put("index.number_of_shards", 1)
						.put("index.number_of_replicas", 0));
		
		restHighLevelClient.indices().create(createIndexRequest, RequestOptions.DEFAULT); // 2

		return true;
	}
	*/
}
