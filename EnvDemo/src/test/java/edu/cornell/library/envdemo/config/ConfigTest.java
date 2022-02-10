package edu.cornell.library.envdemo.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UncheckedIOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.context.WebApplicationContext;
import static java.nio.charset.StandardCharsets.UTF_8;
 

@SpringBootTest 
public class ConfigTest { 

    @Test
    public void testMe() {
         System.out.println("Testing");
         ResourceLoader resourceLoader = new DefaultResourceLoader();
         Resource resource = resourceLoader.getResource("classpath:application.properties");
         try {
            InputStream inputStream = resource.getInputStream();
            String text = new String(inputStream.readAllBytes(),  UTF_8);
            System.out.println(text);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
          
    }
    
    public static String asString(Resource resource) {
        try (Reader reader = new InputStreamReader(resource.getInputStream(), UTF_8)) {
            return FileCopyUtils.copyToString(reader);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

}
