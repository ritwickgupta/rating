package com.example.ecobee.rating;

import com.example.ecobee.rating.Util.ErrorMessage;
import com.example.ecobee.rating.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void testNoLastName() {
        ArrayList<String> data = new ArrayList<>();
        data.add("\"John\" \"Canada/Ontario/Toronto\" 1.5\n");
        String result = userService.processData(data);
        Assert.assertEquals(ErrorMessage.NameError, result);
    }

    @Test
    public void testNoName() {
        ArrayList<String> data = new ArrayList<>();
        data.add("\"\" \"Canada/Ontario/Toronto\" 1.5\n");
        String result = userService.processData(data);
        Assert.assertEquals(ErrorMessage.NameError, result);
    }

    @Test
    public void testNoCity() {
        ArrayList<String> data = new ArrayList<>();
        data.add("\"John Doe\" \"Canada/Ontario/\" 1.5\n");
        String result = userService.processData(data);
        Assert.assertEquals(ErrorMessage.AddressError, result);
    }

    @Test
    public void testNoProvince() {
        ArrayList<String> data = new ArrayList<>();
        data.add("\"John Doe\" \"Canada/Toronto\" 1.5\n");
        String result = userService.processData(data);
        Assert.assertEquals(ErrorMessage.AddressError, result);
    }

    @Test
    public void testNoRValue() {
        ArrayList<String> data = new ArrayList<>();
        data.add("\"John Doe\" \"Canada/Ontario/City\" \n");
        String result = userService.processData(data);
        Assert.assertEquals(ErrorMessage.RValueError, result);
    }

    @Test
    public void testRValueNotValid() {
        ArrayList<String> data = new ArrayList<>();
        data.add("\"John Doe\" \"Canada/Ontario/City\" What\n");
        String result = userService.processData(data);
        Assert.assertEquals(ErrorMessage.RValueError, result);
    }

    @Test
    public void testRValueOutOfRange() {
        ArrayList<String> data = new ArrayList<>();
        data.add("\"John Doe\" \"Canada/Ontario/City\" 56.7\n");
        String result = userService.processData(data);
        Assert.assertEquals(ErrorMessage.RValueRangeError, result);
    }

//    @Test
//    public void testQueryError() {
//        ArrayList<String> data = new ArrayList<>();
//        data.add("\"John Doe\" \"Canada/Ontario/Toronto\" 1.5\n" +
//                "\"Samanta Smith\" \"Canada/Ontario/London\" 3.7\n" +
//                "\"Adam Xin\" \"Canada/British Columbia/Vancouver\" 2.110\n" +
//                "\"Monica Taylor\" \"Canada/Ontario/Toronto\" 2.110\n" +
//                "\"Alicia Yazzie\" \"US/Arizona/Phoenix\" 5.532\n" +
//                "\"Mohammed Zadeh\" \"Canada/Ontario/Toronto\" 1.43\n" +
//                "\n" +
//                "\"John Doe\"\n" +
//                "\"John Doe\" \"Canada/Ontario\"\n" +
//                "\"Alicia Yazzie\" \"US/Arizona\"");
//        String result = userService.processData(data);
//        Assert.assertEquals(ErrorMessage.AddressQueryError, result);
//    }
}
