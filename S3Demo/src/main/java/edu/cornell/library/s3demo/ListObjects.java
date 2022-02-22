package edu.cornell.library.s3demo;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.SystemPropertiesCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3ObjectSummary;

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
public class ListObjects {
    
    private String bucketName;
    private boolean debug;
    
    CompositeConfiguration config = new CompositeConfiguration();
    private AWSCredentials awsCreds;
    private AmazonS3 s3Client;
    
    
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
        ListObjects app = new ListObjects();
        app.getArgs(args); 
        app.init();
        
        
        app.s3Client = new AmazonS3Client(new SystemPropertiesCredentialsProvider());
        
        
        
        
        System.out.format("Objects in S3 bucket %s:\n", app.getBucketName());
         
        
        ListObjectsV2Result result = app.s3Client.listObjectsV2(app.getBucketName());
        List<S3ObjectSummary> objects = result.getObjectSummaries();
        for (S3ObjectSummary os : objects) {
            System.out.println("* " + os.getKey());
        }
    }
    
    public  void getArgs(String[] args) {
        String appName = this.getClass().getSimpleName();
        Options options = new Options();
        options.addOption(new Option("b", "bucket", true, "bucket name")); 
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
     