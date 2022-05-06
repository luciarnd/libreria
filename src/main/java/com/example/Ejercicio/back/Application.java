package com.example.Ejercicio.back;

import com.example.Ejercicio.back.Job.EmailJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

import static org.quartz.TriggerBuilder.newTrigger;

@EnableScheduling
@SpringBootApplication
public class Application {


	public static void main(String[] args) throws SchedulerException {
		SpringApplication.run(Application.class, args);

		Scheduler scheduler = new StdSchedulerFactory().getScheduler();

		JobKey jobKey = new JobKey("TestJob", "grupo1");
		JobDetail job = scheduler.getJobDetail(jobKey);

		if(job == null) {
			job = JobBuilder.newJob(EmailJob.class)
					.withIdentity(jobKey)
					.requestRecovery(true)
					.storeDurably(true)
					.build();
			try {
				scheduler.addJob(job, true);
			} catch (SchedulerException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Job: " + jobKey.getGroup() + "." + jobKey.getName() + " already exist!");
		}

		TriggerKey triggerKey = new TriggerKey("TriggerByMinutes", "grupo1");
		Trigger trigger = scheduler.getTrigger(triggerKey);

		if(trigger == null) {
			trigger = newTrigger()
					.withIdentity(triggerKey)
					.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInMinutes(5).repeatForever())
					.forJob(job)
					.build();
			try {
				scheduler.scheduleJob(trigger);
			} catch (SchedulerException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Trigger: " + triggerKey.getGroup() + "." + triggerKey.getName() + " already exist!");
		}

		if((job != null) && (trigger != null)) {
			try {
				scheduler.start();
			} catch (SchedulerException e) {
				e.printStackTrace();
			}
		} else {
			try {
				scheduler.scheduleJob(job, trigger);
				scheduler.start();
			} catch (SchedulerException e) {
				e.printStackTrace();
			}
		}
	}

	@Bean
	public CorsFilter corsFilter() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
				"Accept", "Authorization", "Origin, Accept", "X-Requested-With",
				"Access-Control-Request-Method", "Access-Control-Request-Headers"));
		corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization",
				"Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}


}
