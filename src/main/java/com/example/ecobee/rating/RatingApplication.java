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
import java.util.Scanner;

@SpringBootApplication
public class RatingApplication {

	private static Logger log = LoggerFactory.getLogger(RatingApplication.class);

	public static void main(String[] args) throws IOException {

		log.info("Starting Rating Application");
		ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(RatingApplication.class, args);
		log.info("Rating Application Started. Enter Input Below:\n");

		ArrayList<String> data = new ArrayList<>();

		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNextLine()) {
			String temp = scanner.nextLine();
			data.add(temp);
			log.info(data.toString());
		}
		scanner.close();
		UserService userService = configurableApplicationContext.getBean(UserService.class);
		System.out.println(userService.processData(data));
	}
}
