package edu.cornell.library.esdemo.service;

 
 
import org.apache.http.HttpHost; 
import org.apache.http.client.config.RequestConfig; 
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthRequest;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse; 
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse; 
import org.elasticsearch.client.RequestOptions; 
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient; 
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.ScoreSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service; 
import edu.cornell.library.esdemo.bo.SystemStatus; 

@Service
public class SearchService implements ISearchService {
    
	public SearchService() {
        super();
        // TODO Auto-generated constructor stub
    }


    protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	
    final String COURSE_INDEX = "testcourses";
    final String ITEM_INDEX = "testitems";
	 
	final String TYPE = "_doc";
	final String GET = "Get";
	final String POST = "Post";
	final String DELETE = "Delete";
	RequestOptions options = RequestOptions.DEFAULT;
	
	final int MAXSIZE = 10000; 
	
	@Autowired
	private RestHighLevelClient client;
	
	/**
	 * @return
	 */
	public RestClient getRestClient() {
		RestClientBuilder builder = RestClient.builder(
				new HttpHost("localhost", 9200, "https"),
		        new HttpHost("localhost", 9200, "http"),
		        new HttpHost("localhost", 9250, "http"));
		builder.setRequestConfigCallback(
		    new RestClientBuilder.RequestConfigCallback() {
		        @Override
		        public RequestConfig.Builder customizeRequestConfig(
		                RequestConfig.Builder requestConfigBuilder) {
		            return requestConfigBuilder
		            		.setSocketTimeout(20000)
		            		.setConnectionRequestTimeout(60000)
		            		; 
		        }
		    });
		return builder.build();
	}
	
	 
	
	/* (non-Javadoc)
	 * @see edu.cornell.library.coursereserves.service.ISearchService#findAllCourses()
	 */
	@Override
	public SearchResponse findAllCourses() throws Exception {
		SearchRequest searchRequest = new SearchRequest(COURSE_INDEX); 
		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
		sourceBuilder.from(0);
		sourceBuilder.size(MAXSIZE);
		sourceBuilder.query(QueryBuilders.matchAllQuery()); 
		searchRequest.source(sourceBuilder);
		SearchResponse response = client.search(searchRequest, this.options);
		return response;
	}

	@Override
	public SearchResponse findCourses(String term) throws Exception {
		
		SearchRequest searchRequest = new SearchRequest(COURSE_INDEX); 
		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
		QueryBuilder qb = QueryBuilders.multiMatchQuery(
			    term,     // Text you are looking for
			    "courseName"           // Fields you query on
			    );
		sourceBuilder.from(0);
		sourceBuilder.size(MAXSIZE);
		sourceBuilder.query(qb);
		
		sourceBuilder.sort(new ScoreSortBuilder().order(SortOrder.DESC)); 
		searchRequest.source(sourceBuilder);
		
		
		SearchResponse response = client.search(searchRequest, this.options);
		return response;
	}  
	
	 
	 
	
	@Override
	public SearchResponse findCoursesByName(String courseName) throws Exception {
	    logger.info("Searching for: "+ courseName);
		SearchRequest searchRequest = new SearchRequest(COURSE_INDEX); 
		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
		QueryBuilder qb = QueryBuilders.matchQuery("courseName", courseName );
		sourceBuilder.from(0);
		sourceBuilder.size(MAXSIZE);
		sourceBuilder.query(qb);
		
		sourceBuilder.sort(new ScoreSortBuilder().order(SortOrder.DESC)); 
		searchRequest.source(sourceBuilder);
		SearchResponse response = client.search(searchRequest, this.options);
		logger.info(response.toString());
		return response;
	}
	
	@Override
    public SearchResponse findCoursesByNumber(String courseNumber) throws Exception {
	    logger.info("Searching for: "+ courseNumber);
        SearchRequest searchRequest = new SearchRequest(COURSE_INDEX); 
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        QueryBuilder qb = QueryBuilders.termQuery("courseNumber", courseNumber );
        
        sourceBuilder.from(0);
        sourceBuilder.size(MAXSIZE);
        sourceBuilder.query(qb);
        
        sourceBuilder.sort(new ScoreSortBuilder().order(SortOrder.DESC)); 
        searchRequest.source(sourceBuilder);
        SearchResponse response = client.search(searchRequest, this.options);
        logger.info(response.toString());
        return response;
    } 
	
	 
	@Override
	public SystemStatus getHealth() throws Exception {
		SystemStatus systemStatus = new SystemStatus();
		systemStatus.setName("ElasticSearch");
		ClusterHealthRequest request = new ClusterHealthRequest("testcourses");
		ClusterHealthResponse health = client.cluster().health(request, options);
		if (health == null) {
			systemStatus.setStatus("DOWN");
			systemStatus.setDetail("Could not get response");
		}
		JSONObject root = new JSONObject(health);
		JSONObject statusNode = (JSONObject) root.get("status");
		if (statusNode == null) {
			systemStatus.setStatus("DOWN");
			systemStatus.setDetail("Could not get health status");	
		}
		String status = statusNode.toString();
		if (status.equalsIgnoreCase("green") || status.equalsIgnoreCase("yellow")) {
		   systemStatus.setStatus("OK");
		} else {
			systemStatus.setStatus("DOWN");
			systemStatus.setDetail("system is up but health is not okay");
		}
		return systemStatus;
	}

}
