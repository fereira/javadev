package net.fereira;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class JavaTimeDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").withZone(ZoneId.systemDefault());;
		
		LocalDateTime ldt = LocalDateTime.now().withHour(2).withMinute(0).withSecond(0).withNano(0);
		ldt = ldt.plusDays(1L);
		System.out.println(ldt.format(dtf));

	}

}
