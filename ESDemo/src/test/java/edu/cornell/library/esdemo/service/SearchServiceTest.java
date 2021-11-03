package edu.cornell.library.esdemo.service; 

 
import org.elasticsearch.action.get.GetResponse; 
import org.elasticsearch.action.search.SearchResponse;
 
import org.elasticsearch.search.SearchHits;
import org.json.JSONObject;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

 

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SearchServiceTest { 
	
	@Autowired
	SearchService searchService;
	
	
	@Test
	public void testFindAllCourses() {
		System.out.println("testFindAllCourses");
        
		try {	
			SearchResponse response = searchService.findAllCourses();
            System.out.println(response.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Disabled
    public void testFindCourses() {
        System.out.println("testFindCourses");
        String term = "COURSE";
        try {   
            SearchResponse response = searchService.findCourses(term);
            System.out.println(response.toString());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
	
	@Test
    public void testFindCoursesByName() {
        System.out.println("testFindCoursesByName");
        String term = "Test";
        try {   
            SearchResponse response = searchService.findCoursesByName(term);
            System.out.println(response.toString());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    } 
	
	@Test
    public void testFindCoursesByNumber() {
        System.out.println("testFindCoursesByNumber");
        String courseNumber= "COURSE 001";
        try {   
            SearchResponse response = searchService.findCoursesByNumber(courseNumber);
            System.out.println(response.toString());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    } 


}

