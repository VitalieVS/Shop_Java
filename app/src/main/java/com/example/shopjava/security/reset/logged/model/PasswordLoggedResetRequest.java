package com.example.shopjava.security.reset.logged.model;

public class PasswordLoggedResetRequest {

    private String currentPassword;

    private String firstChangeToPassword;

    private String secondChangeToPassword;

    public void afterFirstChangeToPasswordChanged(CharSequence s) {

        this.firstChangeToPassword = s.toString();
    }

    public void afterSecondChangeToPasswordChanged(CharSequence s) {

        this.secondChangeToPassword = s.toString();
    }

    public void afterCurrentPasswordChanged(CharSequence s) {

        this.currentPassword = s.toString();
    }

    public String getCurrentPassword() {

        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {

        this.currentPassword = currentPassword;
    }

    public String getFirstChangeToPassword() {

        return firstChangeToPassword;
    }

    public void setFirstChangeToPassword(String firstChangeToPassword) {

        this.firstChangeToPassword = firstChangeToPassword;
    }

    public String getSecondChangeToPassword() {

        return secondChangeToPassword;
    }

    public void setSecondChangeToPassword(String secondChangeToPassword) {

        this.secondChangeToPassword = secondChangeToPassword;
    }
}
