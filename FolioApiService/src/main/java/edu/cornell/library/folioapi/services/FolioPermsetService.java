package edu.cornell.library.folioapi.services; 
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;

 
public class FolioPermsetService extends FolioBaseService {
    protected final Log logger = LogFactory.getLog(getClass());
    
    public FolioPermsetService() {
        // TODO Auto-generated constructor stub
    }
    
    public JSONObject getAllPermset() {
        String userDisplayName = "machine-users_*";
        String endpoint = getBaseOkapEndpoint() + "perms/permissions?limit=1000&query=(displayName==" + userDisplayName + ")";
        try { 
            String response = getApiService().callApiGet(endpoint, getToken());
            JSONObject jsonObject = new JSONObject(response);
            return jsonObject;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }
    
    public JSONObject getPermset(String userDisplayName) {
        // String userDisplayName = "machine-users_order-import";
        String endpoint = getBaseOkapEndpoint() + "perms/permissions?limit=1000&query=(displayName==" + userDisplayName + ")";
        try { 
            String response = getApiService().callApiGet(endpoint, getToken());
            JSONObject jsonObject = new JSONObject(response);
            return jsonObject;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }
    
    public JSONObject getPermsetByUUID(String permsetUUID) {
        // String permsetUUID = "42d47919-457f-494a-a654-0c62c820213b ";
        String endpoint = getBaseOkapEndpoint() + "perms/permissions/" + permsetUUID;
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
