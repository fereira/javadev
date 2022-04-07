package edu.cornell.library.fcrepodemo;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.fcrepo.client.FcrepoClient;
import org.fcrepo.client.FcrepoOperationFailedException;
import org.fcrepo.client.FcrepoResponse;
import org.fcrepo.client.GetBuilder;
import org.springframework.beans.factory.annotation.Value;

public class ReadDemo {
    
    @Value("${fcrepo.uri}")
    private String endpoint;

    public ReadDemo() {
        // TODO Auto-generated constructor stub
    }
    
    public static void main(String[] args) {
        ReadDemo app = new ReadDemo();
        
        try (InputStream input = ReadDemo.class.getClassLoader().getResourceAsStream("application.properties")) {
            Properties prop = new Properties();
            // load a properties file
            prop.load(input);

            // get the property value and print it out
            app.endpoint=prop.getProperty("fcrepo.uri"); 

        } catch (IOException ex) {
            ex.printStackTrace();
        }


        try {
             app.run();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    public void run() {
        
        final FcrepoClient client = FcrepoClient.client().build();
        System.out.println("endpoint: "+ this.endpoint); 
        
        URI uri = null;
        try { 
            uri = new URI(endpoint);
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
