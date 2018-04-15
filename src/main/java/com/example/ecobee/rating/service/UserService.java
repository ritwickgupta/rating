package com.example.ecobee.rating.service;

import com.example.ecobee.rating.exception.InvalidInputException;
import com.example.ecobee.rating.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {

    private static Logger log = LoggerFactory.getLogger(UserService.class);

    public String processData(String data) {
        String result = "";
        ArrayList<User> userObjects = new ArrayList<User>();
        Boolean flag = Boolean.FALSE;

        String[] users = data.split(System.getProperty("line.separator"));

        for(String user : users) {

            if(flag == Boolean.FALSE) {

                if(user.isEmpty()) {
                    flag = Boolean.TRUE;
                    continue;
                }

                String[] userSplitted = user.replace("\"", "").split("\\s+");

                //Check to see if input is complete
                if (userSplitted.length != 4 || userSplitted[2].split("/").length != 3) {
                    throw new InvalidInputException("One or more of the user information is incomplete");
                }

                User userObject = new User(userSplitted);
                log.info("Adding User: " + userObject.toString());
                userObjects.add(userObject);
            } else {
                log.info("Processing: " + user);
            }
        }

        return result;
    }
}
