package edu.cornell.library.fcrepodemo;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.fcrepo.client.FcrepoClient;
import org.fcrepo.client.FcrepoOperationFailedException;
import org.fcrepo.client.FcrepoResponse;
import org.fcrepo.client.GetBuilder;

public class ReadDemo {

    public ReadDemo() {
        // TODO Auto-generated constructor stub
    }
    
    public static void main(String[] args) {
        ReadDemo app = new ReadDemo();

        try {
             app.run();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    public void run() {
        
        final FcrepoClient client = FcrepoClient.client().build();
         
        
        URI uri = null;
        try {
            //uri = new URI("http://localhost:8984/rest/dev/am/s_/ag/en/ams_agency");
            //uri = new URI("http://aws-108-191.internal.library.cornell.edu:8080/fedora/rest/stg");
            uri = new URI("http://aws-108-191.internal.library.cornell.edu:8080/fedora/rest/stg/am/s_/lp/s_/ams_lps_division");
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        List<URI> includes = Arrays.asList(
                URI.create("http://fedora.info/definitions/v4/repository#InboundReferences"));

        List<URI> omits = Arrays.asList(
                URI.create("http://www.w3.org/ns/ldp#PreferMembership"),
                URI.create("http://www.w3.org/ns/ldp#PreferContainment"));
         
        System.out.println("Got client");
        try (FcrepoResponse response = new GetBuilder(uri, client)
                .accept("application/rdf+xml")
                .preferRepresentation(includes, omits)
                .perform()) {
          String turtleContent = IOUtils.toString(response.getBody(), "UTF-8");
          System.out.println(turtleContent);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (FcrepoOperationFailedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("Done");
    }
  
}
