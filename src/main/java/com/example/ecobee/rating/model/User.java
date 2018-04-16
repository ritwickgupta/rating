package com.example.ecobee.rating.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Size(min=1, message="FirstName should have atleast 1 character")
    private String firstName;

    @NotNull
    @Size(min=1, message="LastName should have atleast 1 character")
    private String lastName;

    @NotNull
    @Size(min=1, message="Country should have atleast 1 character")
    private String country;

    @NotNull
    @Size(min=1, message="Province should have atleast 1 character")
    private String province;

    @NotNull
    @Size(min=1, message="City should have atleast 1 character")
    private String city;

    @NotNull
    @DecimalMin("0.0")
    @DecimalMax("50.0")
    private Double rValue;

    public User(String[] name, String[] address, Double rValue) {
        firstName = name[0];
        lastName = name[1];
        country = address[0];
        province = address[1];
        city = address [2];
        this.rValue = rValue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", country='" + country + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", rValue=" + rValue +
                '}';
    }
}
