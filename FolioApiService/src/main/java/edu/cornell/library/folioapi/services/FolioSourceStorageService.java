package edu.cornell.library.folioapi.services; 
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;

 
public class FolioSourceStorageService extends FolioBaseService {
    protected final Log logger = LogFactory.getLog(getClass());
    
    public FolioSourceStorageService() {
        // TODO Auto-generated constructor stub
    }
    
    public JSONObject getAllSourceStorage() { 
        String endpoint = getBaseOkapEndpoint() + "/source-storage/records?limit=30";
        try { 
            String response = getApiService().callApiGet(endpoint, getToken());
            JSONObject jsonObject = new JSONObject(response);
            return jsonObject;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    } 
    
    public JSONObject getSourceStorageById(String vendorId) {
        // String permsetUUID = "42d47919-457f-494a-a654-0c62c820213b ";
        String endpoint = getBaseOkapEndpoint() + "/source-storage/records/"+ vendorId;
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
