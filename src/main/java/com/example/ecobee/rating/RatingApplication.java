package com.example.ecobee.rating;

import com.example.ecobee.rating.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.ArrayList;
import java.util.Scanner;

@SpringBootApplication
public class RatingApplication {

	private static Logger log = LoggerFactory.getLogger(RatingApplication.class);

	public static void main(String[] args) {

		log.info("Starting Rating Application");
		ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(RatingApplication.class, args);
		log.info("Rating Application Started. Enter Input Below:\n");
		ArrayList<String> data = new ArrayList<>();
		Scanner scanner = new Scanner(System.in);
		Integer i=0;

		while (scanner.hasNextLine()) {
			String temp = scanner.nextLine();
			if(temp.isEmpty()) {
				i++;
				if (i > 1) {
					break;
				}
			}
			data.add(temp);
		}
		scanner.close();
		UserService userService = configurableApplicationContext.getBean(UserService.class);
		System.out.println(userService.processData(data));
	}
}
