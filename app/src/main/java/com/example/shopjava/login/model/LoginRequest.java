package com.example.shopjava.login.model;

public class LoginRequest {

    private String login;

    private String password;

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void afterLoginChanged(CharSequence s) {

        this.login = s.toString();
    }

    public void afterPasswordChange(CharSequence s) {
        this.password = s.toString();
    }


}
