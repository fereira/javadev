package edu.cornell.library.folioapi.services; 
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONObject;

 
public class FolioCourseReservesService extends FolioBaseService {
    protected final Log logger = LogFactory.getLog(getClass());
    
    public FolioCourseReservesService() {
        // TODO Auto-generated constructor stub
    }
    
    public JSONObject getCourseListings(int limit) {
        String endpoint = getBaseOkapEndpoint() + "coursereserves/courselistings?limit=" + String.valueOf(limit);
        try { 
            String response = getApiService().callApiGet(endpoint, getToken());
            JSONObject jsonObject = new JSONObject(response);
            return jsonObject;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }
    
    public JSONObject getCourseListingsByListingId(String listingId, int limit) {
        String endpoint = getBaseOkapEndpoint() + "coursereserves/courselistings/"+ listingId +"/courses?limit=" + String.valueOf(limit);
        try { 
            String response = getApiService().callApiGet(endpoint, getToken());
            JSONObject jsonObject = new JSONObject(response);
            return jsonObject;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }
    
    public JSONObject getCourseListingsByTermId(String termId, int limit) {
        String endpoint = getBaseOkapEndpoint() + "coursereserves/courselistings?query=(termId=" + termId +")&limit=" + String.valueOf(limit);
        try {
            String response = getApiService().callApiGet(endpoint, getToken());
            JSONObject jsonObject = new JSONObject(response);
            return jsonObject;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }
    
    public JSONObject getCourseInstructorsByListingId(String listingId, int limit) {
        String endpoint = getBaseOkapEndpoint() + "coursereserves/courselistings/"+ listingId +"/instructors?limit=" + String.valueOf(limit);
        try { 
            String response = getApiService().callApiGet(endpoint, getToken());
            JSONObject jsonObject = new JSONObject(response);
            return jsonObject;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }
    
    public JSONObject getCourseReservesByListingId(String listingId, int limit) {
        String endpoint = getBaseOkapEndpoint() + "coursereserves/courselistings/"+ listingId +"/reserves?limit=" + String.valueOf(limit);
        try { 
            String response = getApiService().callApiGet(endpoint, getToken());
            JSONObject jsonObject = new JSONObject(response);
            return jsonObject;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }
    
    public JSONObject getRoles(int limit) {
        String endpoint = getBaseOkapEndpoint() + "coursereserves/roles?limit=" + String.valueOf(limit);
        try { 
            String response = getApiService().callApiGet(endpoint, getToken());
            JSONObject jsonObject = new JSONObject(response);
            return jsonObject;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }
    
    public JSONObject getTerms(int limit) {
        String endpoint = getBaseOkapEndpoint() + "coursereserves/terms?limit=" + String.valueOf(limit);
        try { 
            String response = getApiService().callApiGet(endpoint, getToken());
            JSONObject jsonObject = new JSONObject(response);
            return jsonObject;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }
    
    public JSONObject getTermByName(String term) {
        
        try {
            String encodedName = URLEncoder.encode("\"" + term + "\"", StandardCharsets.UTF_8.name());
            String endpoint = getBaseOkapEndpoint() + "coursereserves/terms?query=(name=="  + encodedName + ")";
            String response = getApiService().callApiGet(endpoint, getToken());
            JSONObject jsonObject = new JSONObject(response);
            return jsonObject;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }
    
    public JSONObject getCourseTypes(int limit) {
        String endpoint = getBaseOkapEndpoint() + "coursereserves/coursetypes?limit=" + String.valueOf(limit);
        try { 
            String response = getApiService().callApiGet(endpoint, getToken());
            JSONObject jsonObject = new JSONObject(response);
            return jsonObject;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }
    
    public JSONObject getDepartments(int limit) {
        String endpoint = getBaseOkapEndpoint() + "coursereserves/departments?limit=" + String.valueOf(limit);
        try { 
            String response = getApiService().callApiGet(endpoint, getToken());
            JSONObject jsonObject = new JSONObject(response);
            return jsonObject;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }
    
    public JSONObject getDepartmentByName(String departmentName) {
        String endpoint = getBaseOkapEndpoint() + "coursereserves/departments?query=(name=="  + departmentName + ")";
        try { 
            String response = getApiService().callApiGet(endpoint, getToken());
            JSONObject jsonObject = new JSONObject(response);
            return jsonObject;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }
    
    public JSONObject getProcessingStatuses(int limit) {
        String endpoint = getBaseOkapEndpoint() + "coursereserves/processingstatuses?limit=" + String.valueOf(limit);
        try { 
            String response = getApiService().callApiGet(endpoint, getToken());
            JSONObject jsonObject = new JSONObject(response);
            return jsonObject;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }
    
    public JSONObject getCourses(int limit) {
        String endpoint = getBaseOkapEndpoint() + "coursereserves/courses?limit=" + String.valueOf(limit);
        try { 
            String response = getApiService().callApiGet(endpoint, getToken());
            JSONObject jsonObject = new JSONObject(response);
            return jsonObject;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }
    
    public JSONObject getCourseByCourseId(String courseId) {
        String endpoint = getBaseOkapEndpoint() + "coursereserves/courses/" + courseId;
        try { 
            String response = getApiService().callApiGet(endpoint, getToken());
            JSONObject jsonObject = new JSONObject(response);
            return jsonObject;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }
    
    public JSONObject getCourseByCourseNumber(String courseNumber) {
        
        try {
            String encodedCourseNumber = URLEncoder.encode("\"" + courseNumber + "\"", StandardCharsets.UTF_8.name());
            String endpoint = getBaseOkapEndpoint() + "coursereserves/courses?query=(courseNumber=="  + encodedCourseNumber + ")";
            String response = getApiService().callApiGet(endpoint, getToken());
            JSONObject jsonObject = new JSONObject(response);
            return jsonObject;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }
    
    public JSONObject getCoursesBySemester(String semester) {

        try {
            JSONObject termsObject = getTermByName(semester);
            JSONArray termsArray = termsObject.getJSONArray("terms");
            JSONObject termObject = termsArray.getJSONObject(0);
            String termId = termObject.getString("id");
            
            String endpoint = getBaseOkapEndpoint() + "coursereserves/courses?query=(courseNumber==" + semester + ")";
            String response = getApiService().callApiGet(endpoint, getToken());
            JSONObject jsonObject = new JSONObject(response);
            return jsonObject;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }
    
    public JSONObject getCourseByCourseName(String courseName) {
        
        try {
            String encodedCourseName = URLEncoder.encode("\"" + courseName + "\"", StandardCharsets.UTF_8.name());
            String endpoint = getBaseOkapEndpoint() + "coursereserves/courses?query=(name=="  + encodedCourseName + ")";
            String response = getApiService().callApiGet(endpoint, getToken());
            JSONObject jsonObject = new JSONObject(response);
            return jsonObject;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }
    
    public JSONObject getReserves(int limit) {
        String endpoint = getBaseOkapEndpoint() + "coursereserves/reserves?limit=" + String.valueOf(limit);
        try { 
            String response = getApiService().callApiGet(endpoint, getToken());
            JSONObject jsonObject = new JSONObject(response);
            return jsonObject;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }
    
    
    public JSONObject getReservesByItemId(String itemId) {
        String endpoint = getBaseOkapEndpoint() + "coursereserves/reserves?query=(itemId==" + itemId + ")";
        try { 
            String response = getApiService().callApiGet(endpoint, getToken());
            JSONObject jsonObject = new JSONObject(response);
            return jsonObject;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }
    
    public JSONObject getReservesByListingId(String listingsId) {
        String endpoint = getBaseOkapEndpoint() + "coursereserves/reserves?query=(courseListingId=="  + listingsId + ")";
        try { 
            String response = getApiService().callApiGet(endpoint, getToken());
            JSONObject jsonObject = new JSONObject(response);
            return jsonObject;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }

}
