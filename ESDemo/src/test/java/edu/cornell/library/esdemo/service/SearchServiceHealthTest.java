package edu.cornell.library.esdemo.service; 


import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.SearchService;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension; 

import com.google.gson.Gson;

import edu.cornell.library.esdemo.bo.SystemStatus; 

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SearchServiceHealthTest {
	
    @Autowired 
	ISearchService searchService; 
	
	RequestOptions options = RequestOptions.DEFAULT;
	
	
	@Test
	public void getHealth() { 
	    
	    Gson gson = new Gson(); 
		try {
			SystemStatus health = searchService.getHealth();
			String jsonStr = gson.toJson(health);
			JSONObject healthJson = new JSONObject(jsonStr);
			 
			System.out.println(healthJson.toString(3));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			 
	} 

}

