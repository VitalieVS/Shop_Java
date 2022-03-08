package com.example.shopjava.register.model;

import com.example.shopjava.login.model.Address;

public class RegisterRequest {

    private String name;

    private String surname;

    private String email;

    private String phone;

    private String password;

    private Address address;

    private String secondPassword;

    public RegisterRequest() {
    }

    public RegisterRequest(String name, String surname, String email,
                           String password, Address address,
                           String phone) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phone = phone;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void afterNameChanged(CharSequence s) {

        this.name = s.toString();
    }

    public void afterSurnameChanged(CharSequence s) {

        this.surname = s.toString();
    }

    public void afterEmailChanged(CharSequence s) {

        this.email = s.toString();
    }

    public void afterPasswordChanged(CharSequence s) {

        this.password = s.toString();
    }

    public void afterPhoneChanged(CharSequence s) {

        this.phone = s.toString();
    }

    public void afterSecondPasswordChanged(CharSequence s) {

        this.secondPassword = s.toString();
    }

    public String getSecondPassword() {
        return secondPassword;
    }

    public void setSecondPassword(String secondPassword) {
        this.secondPassword = secondPassword;
    }

}
