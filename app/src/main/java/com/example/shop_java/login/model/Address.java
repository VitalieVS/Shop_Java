package com.example.shop_java.login.model;

public class Address {

    private String firstAddress;

    private String secondAddress;

    private String city;

    private String country;

    public String getFirstAddress() {
        return firstAddress;
    }

    public void setFirstAddress(String firstAddress) {
        this.firstAddress = firstAddress;
    }

    public String getSecondAddress() {
        return secondAddress;
    }

    public void setSecondAddress(String secondAddress) {
        this.secondAddress = secondAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void afterFirstAddressChanged(CharSequence s) {

        this.firstAddress = s.toString();
    }

    public void afterSecondAddressChanged(CharSequence s) {

        this.secondAddress = s.toString();
    }

    public void afterCityChanged(CharSequence s) {

        this.city = s.toString();
    }

    public void afterCountryChanged(CharSequence s) {

        this.country = s.toString();
    }
}
