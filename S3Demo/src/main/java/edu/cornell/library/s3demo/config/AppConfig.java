package edu.cornell.library.s3demo.config;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class AppConfig { 

    public AppConfig() {
        // TODO Auto-generated constructor stub
    }
    
    public static CompositeConfiguration getConfig() {
        CompositeConfiguration config = new CompositeConfiguration();
        PropertiesConfiguration props = new PropertiesConfiguration();
        try {
            props.load(ClassLoader.getSystemResourceAsStream("application.properties"));
            config.addConfiguration(props);
        } catch (ConfigurationException e) {
            throw new RuntimeException(e);
        }
        String s3Region = (String)  config.getProperty("AWS_REGION");
        String accessKey = (String) config.getProperty("AWS_ACCESS_KEY_ID");
        String secretKey = (String) config.getProperty("AWS_SECRET_ACCESS_KEY");
        
        System.setProperty("aws.region", s3Region);
        System.setProperty("aws.accessKeyId", accessKey);
        System.setProperty("aws.secretKey", secretKey);
        
        return config;
    }
}
