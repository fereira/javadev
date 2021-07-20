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

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

import edu.cornell.library.SampleWebapp.jobs.SimpleJob;
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
    public JobDetailFactoryBean simpleJobDetail() { 
        return QuartzConfig.createJobDetail(SimpleJob.class, "simple Job");
    }

    @Bean(name = "simpleJobTrigger")
    public SimpleTriggerFactoryBean triggerJob(JobDetail jobDetail) {
        long frequencyMS = everyMinute;
        return QuartzConfig.createTrigger(jobDetail, startNow(), frequencyMS, "Simple Job Trigger");
    }
    
    @Bean(name = "cronJobTrigger")
    public CronTriggerFactoryBean triggerCronJob(JobDetail jobDetail) {
        return QuartzConfig.createCronTrigger(jobDetail, CRON_EVERY_FIVE_MINUTES, "Cron job Trigger");
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
    
 
}
