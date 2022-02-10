package edu.cornell.library.envdemo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.apache.commons.lang3.SystemUtils;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder; 
import org.springframework.boot.test.context.SpringBootTest; 
 

@SpringBootTest 
@TestMethodOrder(OrderAnnotation.class)
public class EnvTest { 

    @Test
    @Order(1) 
    public void testSetEnv() {
         System.out.println("Testing setEnv");
         ProcessBuilder pb = new ProcessBuilder("/bin/bash"); // or any other program you want to run

         Map<String, String> envMap = pb.environment();
         DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"); 
         LocalDateTime ldt = LocalDateTime.now();
         envMap.put("LAST_INDEX", ldt.format(dtf)); 
         try {
            pb.command("bash", "-c", "echo $LAST_INDEX"); 
            Process process = pb.start();
            
            //int exitCode = process.waitFor();
         } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    @Test
    @Order(2) 
    public void testGetEnv() {
         System.out.println("Testing getEnv");
         String lastIndex = SystemUtils.getEnvironmentVariable("LAST_INDEX", "not set");
         System.out.println("LAST_INDEX = "+ lastIndex); 
          
    }
    
     

}
