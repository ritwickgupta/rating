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

            Integer position;
            Double rValueDouble;
            String name = user.substring(position = (user.indexOf("\"") + 1), position = user.indexOf("\"", position));
            String address = user.substring(position = (user.indexOf("\"", position + 1) + 1), position = user.indexOf("\"", position));
            String[] nameSplitted = name.split(" ");
            String[] addressSplitted = address.split("/");

            if (nameSplitted.length != 2) {
                throw new InvalidInputException("Name should be 'Firstname Lastname'");
            }

            if (flag == Boolean.FALSE) {

                if (addressSplitted.length != 3) {
                    throw new InvalidInputException("Address should be 'Country/Province/City'");
                }

                String rValue = user.substring(user.indexOf(" ", position) + 1, user.length());

                try {
                    rValueDouble = Double.parseDouble(rValue);
                } catch (Exception e) {
                    throw new InvalidInputException("R-Value cannot be parsed");
                }

                if(rValueDouble > 50 || rValueDouble < 0.0) {
                    throw new InvalidInputException("R-Value must be between 0 and 50");
                }

                User userObject = new User(nameSplitted, addressSplitted, rValueDouble);
                log.info("Adding User: " + userObject.toString());
                userRepository.save(userObject);
            } else {
                log.info("Processing: " + user);
                Integer countBetter, totalCount;
                if (addressSplitted.length < 1 || addressSplitted.length > 3) {
                    throw new InvalidInputException("Address in Query is incorrect");
                }

                if(addressSplitted.length == 1) {
                   countBetter = userRepository.findBetter(nameSplitted[0], nameSplitted[1], addressSplitted[0]);
                   totalCount = userRepository.findCount(addressSplitted[0]);
                   result = result + user + " " + String.valueOf((countBetter * 10)/ totalCount) + "\n";
                }

                if(addressSplitted.length == 2) {
                    countBetter = userRepository.findBetter(nameSplitted[0], nameSplitted[1], addressSplitted[0], addressSplitted[1]);
                    totalCount = userRepository.findCount(addressSplitted[0], addressSplitted[1]);
                    result = result + user + " " + String.valueOf((countBetter * 10)/ totalCount) + "\n";
                }

                if(addressSplitted.length == 3) {
                    countBetter = userRepository.findBetter(nameSplitted[0], nameSplitted[1], addressSplitted[0], addressSplitted[1], addressSplitted[2]);
                    totalCount = userRepository.findCount(addressSplitted[0], addressSplitted[1], addressSplitted[2]);
                    result = result + user + " " + String.valueOf((countBetter * 10)/ totalCount) + "\n";
                }

            }

        }

        return result;
    }
}
