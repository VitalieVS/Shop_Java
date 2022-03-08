package com.example.shopjava.security.reset.anonymous.model;

public class PasswordRequest {

    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void afterEmailChanged(CharSequence s) {

        this.email = s.toString();
    }
}
