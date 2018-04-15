package com.example.ecobee.rating;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RatingApplication {

	private static Logger log = LoggerFactory.getLogger(RatingApplication.class);
	public static void main(String[] args) {

		log.info("Starting Rating Application");
		SpringApplication.run(RatingApplication.class, args);
		log.info("Rating Application Started");
	}
}
