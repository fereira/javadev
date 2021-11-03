package edu.cornell.library.esdemo.service; 

 
import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
 
import org.elasticsearch.search.SearchHits;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import edu.cornell.library.esdemo.bo.TestCourse;

 

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class IndexServiceTest { 
	
	@Autowired
	IndexService indexService;
	
	
	@Test
	public void testIndexCourse() {
		System.out.println("testIndexCourse");
		List<TestCourse> courses = new ArrayList<TestCourse>();
        TestCourse course1 = new TestCourse();
        course1.setId(1);
        course1.setCourseName("Test Course 1");
        course1.setCourseNumber("COURSE 001");
        
        TestCourse course2 = new TestCourse();
        course2.setId(2);
        course2.setCourseName("Test Course 2");
        course2.setCourseNumber("COURSE 002");
        
        TestCourse course3 = new TestCourse();
        course3.setId(3);
        course3.setCourseName("Test Course3");
        course3.setCourseNumber("COURSE 003");
        
        courses.add(course1);
        courses.add(course2);
        courses.add(course3);
        
        for (TestCourse course: courses) {
    		try {	
    			IndexResponse response = indexService.indexCourse(course);
                System.out.println(response.toString());
                 
    		} catch (Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        }
	} 

}

