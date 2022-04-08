package edu.cornell.library.quartz;

import org.springframework.boot.Banner.Mode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@ComponentScan
@EnableScheduling
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		//SpringApplication.run(SampleWebappApplication.class, args);
		new SpringApplicationBuilder(Application.class).bannerMode(Mode.OFF).run(args);
	}

}
