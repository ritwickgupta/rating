package com.example.ecobee.rating.Util;

public interface ErrorMessage {

    String NameError = "Name should be 'Firstname Lastname'";
    String AddressError = "Address should be 'Country/Province/City'";
    String RValueError = "R-Value cannot be parsed";
    String RValueRangeError = "R-Value must be between 0 and 50";
    String AddressQueryError = "Address in Query is incorrect";
}
