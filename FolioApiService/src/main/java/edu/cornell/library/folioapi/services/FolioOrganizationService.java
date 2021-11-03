package edu.cornell.library.folioapi.services; 
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;

 
public class FolioOrganizationService extends FolioBaseService {
    protected final Log logger = LogFactory.getLog(getClass());
    
    public FolioOrganizationService() {
        // TODO Auto-generated constructor stub
    }
    
    public JSONObject getVendors() { 
        String endpoint = getBaseOkapEndpoint() + "/organizations-storage/organizations?limit=30&query=((isVendor=true))";
        try { 
            String response = getApiService().callApiGet(endpoint, getToken());
            JSONObject jsonObject = new JSONObject(response);
            return jsonObject;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }
    
    public JSONObject getOrganizationById(String orgId) { 
        String endpoint = getBaseOkapEndpoint() + "/organizations-storage/organizations/"+ orgId;
        try { 
            String response = getApiService().callApiGet(endpoint, getToken());
            JSONObject jsonObject = new JSONObject(response);
            return jsonObject;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }
    
    public JSONObject getVendorByCode(String vendorCode) {
        //String vendorCode = "AMAZON";
         
        try {
            String encodedOrgCode = URLEncoder.encode("\"" + vendorCode + "\"", StandardCharsets.UTF_8.name());
            String endpoint = getBaseOkapEndpoint() +
                    "organizations-storage/organizations?query=(code=" + encodedOrgCode + ")";
            String response = getApiService().callApiGet(endpoint, getToken());
            JSONObject jsonObject = new JSONObject(response);
            return jsonObject;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }

}
