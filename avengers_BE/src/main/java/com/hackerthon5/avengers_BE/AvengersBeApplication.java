package com.hackerthon5.avengers_BE;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class AvengersBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(AvengersBeApplication.class, args);
	}

}
