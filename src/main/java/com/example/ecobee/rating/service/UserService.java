package com.example.ecobee.rating.service;

import com.example.ecobee.rating.exception.InvalidInputException;
import com.example.ecobee.rating.model.User;
import com.example.ecobee.rating.persistence.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserRepository userRepository;

    public String processData(String data) {
        String result = "";
        Boolean flag = Boolean.FALSE;

        String[] users = data.split(System.getProperty("line.separator"));

        for (String user : users) {

            if (user.isEmpty()) {
                flag = Boolean.TRUE;
                continue;
            }

            if (flag == Boolean.FALSE) {
                Integer position;
                Double rValueDouble;
                String name = user.substring(position = (user.indexOf("\"") + 1), position = user.indexOf("\"", position));
                String address = user.substring(position = (user.indexOf("\"", position + 1) + 1), position = user.indexOf("\"", position));
                String rValue = user.substring(user.indexOf(" ", position) + 1, user.length());
                String[] nameSplitted = name.split(" ");
                String[] addressSplitted = address.split("/");

                String[] userSplitted = user.replace("\"", "").split("\\s+");
                if (nameSplitted.length != 2) {
                    throw new InvalidInputException("Name should be 'Firstname Lastname'");
                }

                if (addressSplitted.length != 3) {
                    throw new InvalidInputException("Address should be 'Country/Province/City'");
                }

                try {
                    rValueDouble = Double.parseDouble(rValue);
                } catch (Exception e) {
                    throw new InvalidInputException("R-Value cannot be parsed");
                }

                User userObject = new User(nameSplitted, addressSplitted, rValueDouble);
                log.info("Adding User: " + userObject.toString());
                userRepository.save(userObject);
            } else {
                log.info("Processing: " + user);

            }

        }

        return result;
    }
}
