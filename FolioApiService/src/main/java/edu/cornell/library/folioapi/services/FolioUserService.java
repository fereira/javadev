package edu.cornell.library.folioapi.services; 
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;

 
public class FolioUserService extends FolioBaseService {
    protected final Log logger = LogFactory.getLog(getClass());
    
    public FolioUserService() {
        // TODO Auto-generated constructor stub
    }
    
    public JSONObject getUserByName(String name) { 
        String endpoint = getBaseOkapEndpoint() + "bl-users?limit=10&query=(username=" + name +")";
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
