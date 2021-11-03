package edu.cornell.library.folioapi.services; 
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;

 
public class FolioServicePointsService extends FolioBaseService {
    protected final Log logger = LogFactory.getLog(getClass());
    
    public FolioServicePointsService() {
        // TODO Auto-generated constructor stub
    }
    
    public JSONObject getServicePoints() {
        String endpoint = getBaseOkapEndpoint() + "service-points?limit=10000";
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
