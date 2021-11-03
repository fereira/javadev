package edu.cornell.library.esdemo;

import org.elasticsearch.action.search.SearchResponse;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;

import edu.cornell.library.esdemo.service.SearchService; 
public class ShowCourses {
    
    @Autowired
    SearchService searchService;

    public ShowCourses() {
        // TODO Auto-generated constructor stub
    }
    
    public static void main(String[] args) {
        ShowCourses app = new ShowCourses();
        
        try { 
             app.run();
            
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public void run() {
        
        
        try {
            SearchResponse response = searchService.findAllCourses();
            System.out.println(response);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

}
