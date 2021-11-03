package edu.cornell.library.folioapi.services; 
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;

 
public class FolioLocationService extends FolioBaseService {
    protected final Log logger = LogFactory.getLog(getClass());
    
    public FolioLocationService() {
        // TODO Auto-generated constructor stub
    }
    
    public JSONObject getLocations() {
        String endpoint = getBaseOkapEndpoint() + "locations?limit=10000";
        try { 
            String response = getApiService().callApiGet(endpoint, getToken());
            JSONObject jsonObject = new JSONObject(response);
            return jsonObject;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }
    
    public JSONObject getLocationById(String locationId) {
        String endpoint = getBaseOkapEndpoint() + "locations/" + locationId;
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
