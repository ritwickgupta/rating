package com.example.ecobee.rating;

import com.example.ecobee.rating.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

@SpringBootApplication
public class RatingApplication {

	private static Logger log = LoggerFactory.getLogger(RatingApplication.class);

	public static void main(String[] args) throws IOException {

		log.info("Starting Rating Application");
		ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(RatingApplication.class, args);
		log.info("Rating Application Started. Enter Input Below:\n");

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<String> data = new ArrayList<>();
		String temp;

		while ((temp = in.readLine()) != null) {
			data.add(temp);
			log.info(data.toString());
		}

		UserService userService = configurableApplicationContext.getBean(UserService.class);

		log.info("Sending data: " + data);
		System.out.println(userService.processData(data));
	}
}
