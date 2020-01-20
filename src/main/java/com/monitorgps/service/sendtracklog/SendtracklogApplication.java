package com.monitorgps.service.sendtracklog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SendtracklogApplication {

	public static void main(String[] args) {
		SpringApplication.run(SendtracklogApplication.class, args);
	}

}
