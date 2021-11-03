package edu.cornell.library.folioapi.services; 
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;

 
public class FolioItemStorageService extends FolioBaseService {
    protected final Log logger = LogFactory.getLog(getClass());
    
    public FolioItemStorageService() {
        // TODO Auto-generated constructor stub
    }
    
    public JSONObject getItemStorage(String itemId) {
        String endpoint = getBaseOkapEndpoint() + "item-storage/items/"  + itemId;
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
