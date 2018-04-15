package com.example.ecobee.rating.model;

public class User {

    private String firstName;
    private String lastName;
    private Address address;
    private Double rValue;

    public User(String[] user) {
        firstName = user[0];
        lastName = user[1];
        address = new Address(user[2]);
        rValue = Double.valueOf(user[3]);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Double getrValue() {
        return rValue;
    }

    public void setrValue(Double rValue) {
        this.rValue = rValue;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address=" + address +
                ", rValue=" + rValue +
                '}';
    }
}
