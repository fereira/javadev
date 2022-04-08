package edu.cornell.library.quartz.jobs;
 
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.IntStream;

@Component
public class SimpleJob2 extends QuartzJobBean {
    
    protected final Logger log = LoggerFactory.getLogger(getClass());
    
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"); 
        log.info("SimpleJob2 Started: "+ dtf.format(LocalDateTime.now()));
        String[] alphanum = {"one", "two", "three", "four", "five"};
        
        for (int i=0; i < alphanum.length; i++) {
            log.info("Counting - {}", alphanum[i]);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                log.error(e.getMessage(), e);
            }
        };
        log.info("SimpleJob2 End................");
    }
}
