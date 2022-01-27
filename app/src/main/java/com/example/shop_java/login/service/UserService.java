package com.example.shop_java.login.service;

import android.content.Context;
import android.content.SharedPreferences;


public class UserService {

    private static UserService INSTANCE;
    private Context context;

    public static UserService getInstance() {

        if (INSTANCE == null) {
            return new UserService();
        }
        return INSTANCE;
    }

    public boolean getRememberMe() {

        SharedPreferences settings = context.getSharedPreferences(Context.ACCOUNT_SERVICE, 0);

        return settings.getBoolean("remember", false);
    }

    public void setRememberMe(boolean rememberMe, int i) {

        SharedPreferences settings = context.getSharedPreferences(Context.ACCOUNT_SERVICE, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("remember", rememberMe);

        editor.apply();
    }

    public void setAuthorised(boolean authorised, String token, String login) {

        SharedPreferences settings = context.getSharedPreferences(Context.ACCOUNT_SERVICE, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("login", login);
        editor.putBoolean("authorized", authorised);
        editor.putString("token", token);

        editor.apply();
    }

    public String getToken() {

        SharedPreferences settings = context.getSharedPreferences(Context.ACCOUNT_SERVICE, 0);

        return "Bearer_" + settings.getString("token", "");
    }

    public String getLogin() {

        SharedPreferences settings = context.getSharedPreferences(Context.ACCOUNT_SERVICE, 0);

        return settings.getString("login", "");
    }

    public boolean isAuthorised() {

        SharedPreferences settings = context.getSharedPreferences(Context.ACCOUNT_SERVICE, 0);

        return settings.getBoolean("authorized", false);

    }

    public void setContext(Context context) {
        this.context = context;
    }
}
