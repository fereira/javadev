package edu.cornell.library.s3demo;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.SystemPropertiesCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * List objects within an Amazon S3 bucket.
 * 
 * This code expects that you have AWS credentials set up per:
 * http://docs.aws.amazon.com/java-sdk/latest/developer-guide/setup-credentials.html
 */
public class DownloadObject {
    
    private String bucketName;
    private String keyName;
    private boolean debug;
    
    CompositeConfiguration config = new CompositeConfiguration();
    private AWSCredentials awsCreds;
    private AmazonS3 s3Client;
    
    
    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public CompositeConfiguration getConfig() {
        return config;
    }

    public void setConfig(CompositeConfiguration config) {
        this.config = config;
    }

    public AmazonS3 getS3Client() {
        return s3Client;
    }

    public void setS3Client(AmazonS3 s3Client) {
        this.s3Client = s3Client;
    }

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public String getBucketName() {
        return bucketName;
    }
    
    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public static void main(String[] args) { 
        DownloadObject app = new DownloadObject();
        app.getArgs(args); 
        app.init(); 
        app.run();
        
    }
    
    public  void getArgs(String[] args) {
        String appName = this.getClass().getSimpleName();
        Options options = new Options();
        options.addOption(new Option("b", "bucket", true, "bucket name"));
        options.addOption(new Option("k", "key", true, "key name"));
        //options.addOption(new Option("D","debug", false, "turn on debug output"));
         
        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        try {
            CommandLine cmd = parser.parse( options, args); 
            if (cmd.hasOption("bucket")) {
                this.setBucketName(cmd.getOptionValue("bucket"));
            } else { 
                formatter.printHelp(appName, options );
                System.exit(0);
            }
     
            if (cmd.hasOption("D") || cmd.hasOption("debug")) {
                this.setDebug(true);
            }
            
            
        } catch (ParseException e) {
            formatter.printHelp(appName, options );
            System.exit(0);
        }
    
    }
    
    public void run() {
        this.s3Client = new AmazonS3Client(new SystemPropertiesCredentialsProvider()); 
        
        try {
            S3Object o = this.s3Client.getObject(bucketName, keyName);
            S3ObjectInputStream s3is = o.getObjectContent();
            FileOutputStream fos = new FileOutputStream(new File(keyName));
            byte[] read_buf = new byte[1024];
            int read_len = 0;
            while ((read_len = s3is.read(read_buf)) > 0) {
                fos.write(read_buf, 0, read_len);
            }
            s3is.close();
            fos.close();
        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
            System.exit(1);
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        System.out.println("Done!");    
    }
    
    public void init() {
        
        URL defaultConfigLocation = null;
        defaultConfigLocation = getClass().getClassLoader().getResource("application.properties");
        try {
           PropertiesConfiguration props = new PropertiesConfiguration(defaultConfigLocation); 
           this.config.addConfiguration(props);
        } catch (ConfigurationException e) {
           throw new RuntimeException(e);
        }
        String s3Region = (String)  config.getProperty("AWS_REGION");
        String accessKey = (String) config.getProperty("AWS_ACCESS_KEY_ID");
        String secretKey = (String) config.getProperty("AWS_SECRET_ACCESS_KEY");
        
        //System.out.println("aws.region = "+ s3Region);
        //System.out.println("aws.accessKeyId = "+ accessKey);
        //System.out.println("aws.secretKey = "+ secretKey);
        
        System.setProperty("aws.region", s3Region);
        System.setProperty("aws.accessKeyId", accessKey);
        System.setProperty("aws.secretKey", secretKey);
        
    }
}   
     