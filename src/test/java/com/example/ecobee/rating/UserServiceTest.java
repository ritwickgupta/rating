package com.example.ecobee.rating;

import com.example.ecobee.rating.Util.ErrorMessage;
import com.example.ecobee.rating.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    private static Logger log = LoggerFactory.getLogger(UserServiceTest.class);

    @Autowired
    UserService userService;

    @Test
    public void testNoLastName() {
        String data = "\"John\" \"Canada/Ontario/Toronto\" 1.5\n";
        String result = userService.processData(data);
        Assert.assertEquals(ErrorMessage.NameError, result);
    }
}
