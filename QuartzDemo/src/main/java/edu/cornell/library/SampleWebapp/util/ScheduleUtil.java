package edu.cornell.library.SampleWebapp.util;

import org.quartz.SimpleScheduleBuilder;
import org.quartz.spi.MutableTrigger;

public class ScheduleUtil {
	
	/*
	 * public static MutableTrigger getMultiHourlySchedule(int hours) { return
	 * SimpleScheduleBuilder.simpleSchedule() .withIntervalInHours(hours)
	 * .repeatForever().build(); }
	 * 
	 * public static MutableTrigger getMinuteSchedule(int minutes) { return
	 * SimpleScheduleBuilder.simpleSchedule() .withIntervalInMinutes(minutes)
	 * .repeatForever().build(); }
	 * 
	 * public static MutableTrigger getHourlySchedule(int refreshInterval) { return
	 * SimpleScheduleBuilder.repeatHourlyForever().build(); }
	 */
	
	public static SimpleScheduleBuilder daily() {
		return SimpleScheduleBuilder.simpleSchedule().withIntervalInHours(24).repeatForever();
	}
	
	public static SimpleScheduleBuilder hourly() {
		return SimpleScheduleBuilder.simpleSchedule().withIntervalInHours(1).repeatForever();
	}
	
	public static SimpleScheduleBuilder everyMinute() {
		return SimpleScheduleBuilder.simpleSchedule().withIntervalInMinutes(1).repeatForever();
	}
	
	public static SimpleScheduleBuilder minutesSchedule(int minutes) {
		return SimpleScheduleBuilder.simpleSchedule().withIntervalInMinutes(minutes).repeatForever();
	}
	
	public static SimpleScheduleBuilder hoursSchedule(int hours) {
		return SimpleScheduleBuilder.simpleSchedule().withIntervalInHours(hours).repeatForever();
	}  

}
