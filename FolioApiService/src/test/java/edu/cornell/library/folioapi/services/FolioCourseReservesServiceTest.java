package edu.cornell.library.folioapi.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test; 

public class FolioCourseReservesServiceTest extends ApiBaseTest {

    @Disabled
    public void testGetCourseListings() {   
        FolioCourseReservesService service = new FolioCourseReservesService(); 
        int limit = 100;
        try { 
            
            JSONObject jsonObject = service.getCourseListings(limit);
            System.out.println(jsonObject.toString(3));
            assertNotNull(jsonObject);
              
        } catch (Exception e) {
            fail(e.getMessage());
        }
         
    }
    
    @Test
    public void testGetCourseListingsByTermId() {   
        FolioCourseReservesService service = new FolioCourseReservesService();
        String termId = "55435bf1-55fb-418e-a475-835ec3acd6f2";
        int limit = 100;
        try { 
            
            JSONObject jsonObject = service.getCourseListingsByTermId(termId, limit);
            System.out.println(jsonObject.toString(3));
            assertNotNull(jsonObject);
              
        } catch (Exception e) {
            fail(e.getMessage());
        }
         
    }
    
    @Disabled
    public void testGetCourseListingsByListingId() {   
        FolioCourseReservesService service = new FolioCourseReservesService(); 
        int limit = 10;
        String listingId = "5e063522-481f-4cd4-895f-f3038b577e4d";
        try { 
            
            JSONObject jsonObject = service.getCourseListingsByListingId(listingId, limit);
            System.out.println(jsonObject.toString(3));
            assertNotNull(jsonObject);
              
        } catch (Exception e) {
            fail(e.getMessage());
        }
         
    }
    
    @Disabled
    public void testGetCourseInstrucotrsByListingId() {   
        FolioCourseReservesService service = new FolioCourseReservesService(); 
        int limit = 10;
        String listingId = "5e063522-481f-4cd4-895f-f3038b577e4d";
        try { 
            
            JSONObject jsonObject = service.getCourseInstructorsByListingId(listingId, limit);
            System.out.println(jsonObject.toString(3));
            assertNotNull(jsonObject);
              
        } catch (Exception e) {
            fail(e.getMessage());
        }
         
    }
    
    @Disabled
    public void testGetCourseReservesByListingId() {   
        FolioCourseReservesService service = new FolioCourseReservesService(); 
        int limit = 10;
        String listingId = "5e063522-481f-4cd4-895f-f3038b577e4d";
        try { 
            
            JSONObject jsonObject = service.getCourseReservesByListingId(listingId, limit);
            System.out.println(jsonObject.toString(3));
            assertNotNull(jsonObject);
              
        } catch (Exception e) {
            fail(e.getMessage());
        }
         
    }
    
    @Disabled
    public void testGetRoles() {   
        FolioCourseReservesService service = new FolioCourseReservesService(); 
        int limit = 100;
        
        try { 
            
            JSONObject jsonObject = service.getRoles(limit);
            System.out.println(jsonObject.toString(3));
            assertNotNull(jsonObject);
              
        } catch (Exception e) {
            fail(e.getMessage());
        }
         
    }
    
    @Test
    public void testGetTerms() {   
        FolioCourseReservesService service = new FolioCourseReservesService(); 
        int limit = 100;
        
        try { 
            
            JSONObject jsonObject = service.getTerms(limit);
            System.out.println(jsonObject.toString(3));
            assertNotNull(jsonObject);
              
        } catch (Exception e) {
            fail(e.getMessage());
        }
         
    }
    
    @Disabled
    public void testGetTermByName() {   
        FolioCourseReservesService service = new FolioCourseReservesService(); 
        String name = "Spring 2021";
        
        try { 
            
            JSONObject jsonObject = service.getTermByName(name);
            System.out.println(jsonObject.toString(3));
            assertNotNull(jsonObject);
              
        } catch (Exception e) {
            fail(e.getMessage());
        }
         
    }
    
    @Disabled
    public void testGetCourseTypes() {   
        FolioCourseReservesService service = new FolioCourseReservesService();
        int limit = 100;
        try { 
            
            JSONObject jsonObject = service.getCourseTypes(limit);
            System.out.println(jsonObject.toString(3));
            assertNotNull(jsonObject);
              
        } catch (Exception e) {
            fail(e.getMessage());
        }
         
    }
    
    @Disabled
    public void testGetDepartments() {   
        FolioCourseReservesService service = new FolioCourseReservesService();
        int limit = 100;
        try { 
            
            JSONObject jsonObject = service.getDepartments(limit);
            System.out.println(jsonObject.toString(3));
            assertNotNull(jsonObject);
              
        } catch (Exception e) {
            fail(e.getMessage());
        }
         
    }
    
    @Disabled
    public void testGetDepartmentByName() {   
        FolioCourseReservesService service = new FolioCourseReservesService();
        String departmentName = "Learn";
        try { 
            
            JSONObject jsonObject = service.getDepartmentByName(departmentName);
            System.out.println(jsonObject.toString(3));
            assertNotNull(jsonObject);
              
        } catch (Exception e) {
            fail(e.getMessage());
        }
         
    }
    
    @Disabled
    public void testGetProcessingStatuses() {   
        FolioCourseReservesService service = new FolioCourseReservesService();
        int limit = 100;
        try { 
            
            JSONObject jsonObject = service.getProcessingStatuses(limit);
            System.out.println(jsonObject.toString(3));
            assertNotNull(jsonObject);
              
        } catch (Exception e) {
            fail(e.getMessage());
        }
         
    }
    
    @Disabled
    public void testGetCourses() {   
        FolioCourseReservesService service = new FolioCourseReservesService();
        int limit = 100;
        try { 
            
            JSONObject jsonObject = service.getCourses(limit);
            JSONArray coursesObject = jsonObject.getJSONArray("courses");
            Iterator iter = coursesObject.iterator();
            while (iter.hasNext()) {
                JSONObject courseObject = (JSONObject) iter.next();
                String courseNumber = courseObject.getString("courseNumber");
                String courseName = courseObject.getString("name");
                String listingId = courseObject.getString("courseListingId");
                System.out.println("courseNumber: "+ courseNumber);
                System.out.println("courseName: "+ courseName);
                System.out.println("courseListingId: "+ listingId);
            }
            
            System.out.println(jsonObject.toString(3));
            assertNotNull(jsonObject);
              
        } catch (Exception e) {
            fail(e.getMessage());
        }
         
    }
    
    
    
    @Disabled
    public void testGetCourseById() {
        String courseId = "c07d9e81-789b-46bc-9cc7-178574e62a9c";
        FolioCourseReservesService service = new FolioCourseReservesService(); 
        try { 
            
            JSONObject jsonObject = service.getCourseByCourseId(courseId);
            System.out.println(jsonObject.toString(3));
            assertNotNull(jsonObject);
              
        } catch (Exception e) {
            fail(e.getMessage());
        }
         
    }
    
    @Disabled
    public void testGetCourseByCourseNumber() {
        String courseNumber =  "HIST 0001";
        FolioCourseReservesService service = new FolioCourseReservesService(); 
        try { 
            
            JSONObject jsonObject = service.getCourseByCourseNumber(courseNumber);
            System.out.println(jsonObject.toString(3));
            assertNotNull(jsonObject);
              
        } catch (Exception e) {
            fail(e.getMessage());
        }
         
    }
    
    @Disabled
    public void testGetReserves() {
        int limit = 20;
        FolioCourseReservesService service = new FolioCourseReservesService(); 
        try { 
            
            JSONObject jsonObject = service.getReserves(limit);
            System.out.println(jsonObject.toString(3));
            assertNotNull(jsonObject);
              
        } catch (Exception e) {
            fail(e.getMessage());
        }
         
    }
    
    @Disabled
    public void testGetReservesByItemId() {
        String itemId =  "8f0f2bfb-9365-111e-a053-ba1bec844b3a";
        FolioCourseReservesService service = new FolioCourseReservesService(); 
        try { 
            
            JSONObject jsonObject = service.getReservesByItemId(itemId);
            System.out.println(jsonObject.toString(3));
            assertNotNull(jsonObject);
              
        } catch (Exception e) {
            fail(e.getMessage());
        }
         
    }
    
    @Disabled
    public void testGetReservesByListingId() {
        String listingId =  "5e063522-481f-4cd4-895f-f3038b577e4d";
        FolioCourseReservesService service = new FolioCourseReservesService(); 
        try { 
            
            JSONObject jsonObject = service.getReservesByListingId(listingId);
            System.out.println(jsonObject.toString(3));
            assertNotNull(jsonObject);
              
        } catch (Exception e) {
            fail(e.getMessage());
        }
         
    }
    
    @Disabled
    public void testGetReservesByCourseNumber() {
        String courseNumber = "LEARN 0001";
        
        FolioCourseReservesService service = new FolioCourseReservesService(); 
        try { 
            JSONObject coursesObject = service.getCourseByCourseNumber(courseNumber);
            JSONArray coursesArray = coursesObject.getJSONArray("courses");
            JSONObject courseObject = coursesArray.getJSONObject(0);
            //System.out.println(courseObject.toString(3));
            String listingId = courseObject.getString("courseListingId");
             
            
            JSONObject jsonObject = service.getReservesByListingId(listingId);
            System.out.println(jsonObject.toString(3));
            assertNotNull(jsonObject);
              
        } catch (Exception e) {
            fail(e.getMessage());
        }
         
    } 

}
