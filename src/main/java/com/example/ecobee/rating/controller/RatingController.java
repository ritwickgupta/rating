package com.example.ecobee.rating.controller;

import com.example.ecobee.rating.exception.InvalidInputException;
import com.example.ecobee.rating.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rating")
public class RatingController {

    private static Logger log = LoggerFactory.getLogger(RatingController.class);

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity postData(HttpEntity<String> httpEntity) {
    log.info("In RatingController Post Data");

    log.info(httpEntity.getBody());

    String data = "\"John Doe\" \"Canada/Ontario/Toronto\" 1.5\n" +
            "\"Samanta Smith\" \"Canada/Ontario/London\" 3.7\n" +
            "\"Adam Xin\" \"Canada/BritishColumbia/Vancouver\" 2.110\n" +
            "\"Monica Taylor\" \"Canada/Ontario/Toronto\" 2.110\n" +
            "\"Alicia Yazzie\" \"US/Arizona/Phoenix\" 5.532\n" +
            "\"Mohammed Zadeh\" \"Canada/Ontario/Toronto\" 1.43\n" +
            "\n" +
            "\"John Doe\" \"Canada\"\n" +
            "\"John Doe\" \"Canada/Ontario\"\n" +
            "\"Alicia Yazzie\" \"US/Arizona\"";

    //Test if data is null
    if(data == null) {
        throw new InvalidInputException("Data Null");
    }

    String result = userService.processData(data);

    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.TEXT_PLAIN);
    return new ResponseEntity<String>(result, httpHeaders, HttpStatus.OK);
    }

}
