package edu.cornell.library.SampleWebapp.config;

 
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

 
import org.quartz.JobDetail; 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

import edu.cornell.library.SampleWebapp.jobs.SimpleJob;
import edu.cornell.library.SampleWebapp.jobs.SimpleJob2;
import edu.cornell.library.SampleWebapp.util.ScheduleUtil; 

@Configuration
public class QuartzSubmitJobs {
    
    final long everyDay = TimeUnit.DAYS.toMillis(1);     
    final long everyMinute = TimeUnit.MINUTES.toMillis(1); 
    final long everyHour = TimeUnit.HOURS.toMillis(1);  
    
    Logger logger = LoggerFactory.getLogger(getClass());
    private static final String CRON_EVERY_FIVE_MINUTES = "0 0/5 * ? * * *";
    
    @PostConstruct
    public void init() {
        logger.info("Hello world from QuartzSubmitJobs...");
    }

    @Bean(name = "simpleJobDetail")
    @Primary
    public JobDetailFactoryBean simpleJobDetail() { 
        return QuartzConfig.createJobDetail(SimpleJob.class, "simple Job");
    }
    
    @Bean(name = "simpleJobDetail2")
    public JobDetailFactoryBean simpleJobDetail2() { 
        return QuartzConfig.createJobDetail(SimpleJob2.class, "simple Job2");
    }

    @Bean 
    public SimpleTriggerFactoryBean triggerJob(@Qualifier("simpleJobDetail")JobDetail jobDetail) {
        long frequencyMS = everyMinute;
        return QuartzConfig.createTrigger(jobDetail, startNow(), frequencyMS, "Simple Job Trigger");
    }
    
    @Bean
    public SimpleTriggerFactoryBean triggerJob2(@Qualifier("simpleJobDetail2")JobDetail jobDetail) {
        long frequencyMS = minutesToMilliseconds(5);
        return QuartzConfig.createTrigger(jobDetail, startNow(), frequencyMS, "Simple Job Trigger2");
    }
    
    static Date startNow() {
        LocalDateTime ldt = LocalDateTime.now();
        return Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
    }
    
    /*
     * set start date time Set plus to 1L to start tomorrow
     */
    static Date getStartDateTime(int hour, int minute, long plus) {
        LocalDateTime ldt = LocalDateTime.now().withHour(hour).withMinute(minute).withSecond(0).withNano(0);
        ldt = ldt.plusDays(plus);
        return Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
    }
    
    static long daysToMilliseconds(int days) {
        return TimeUnit.DAYS.toMillis(days);    
    }
    
    static long hoursToMilliseconds(int hours) {
        return TimeUnit.HOURS.toMillis(hours);    
    }
    
    static long minutesToMilliseconds(int minutes) {
        return TimeUnit.MINUTES.toMillis(minutes);    
    }
    
 
}
