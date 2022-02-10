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
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.SystemUtils;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder; 
import org.springframework.boot.test.context.SpringBootTest; 
 

@SpringBootTest 
@TestMethodOrder(OrderAnnotation.class)
public class PropTest { 

    @Test
    @Order(1) 
    public void testSetProperties() {
         System.out.println("Testing setProps");
           
         DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm a"); 
         LocalDateTime ldt = LocalDateTime.now(); 

         Properties p = System.getProperties();
         p.put("LAST_INDEX", ldt.format(dtf));
         System.setProperties(p);
          
          
    }
    
    @Test
    @Order(2) 
    public void testGetEnv() {
         System.out.println("Testing getProperties");      
         System.out.println("LAST_INDEX: "+ System.getProperty("LAST_INDEX"));
         Properties props = System.getProperties();
         Iterator iter = props.keySet().iterator();
         while (iter.hasNext()) {
             String key = (String) iter.next();
            
             System.out.println(key + ": "+ props.getProperty(key));
         }
          
    }
    
     

}
