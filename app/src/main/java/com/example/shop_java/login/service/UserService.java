package com.example.shop_java.login.service;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.fragment.app.FragmentActivity;

import com.example.shop_java.R;
import com.example.shop_java.address.LoggedAddress;
import com.example.shop_java.address.viewmodel.AddressViewModel;
import com.example.shop_java.login.model.Address;
import com.example.shop_java.login.model.User;
import com.example.shop_java.login.viewmodel.LoginViewModel;
import com.example.shop_java.menu.LoginFragment;
import com.example.shop_java.security.reset.logged.fragment.LoggedResetPassword;


public class UserService {

    private static UserService INSTANCE;

    private Context context;

    private FragmentActivity fragmentActivity;

    private AddressViewModel addressViewModel;

    public void setFragmentActivity(FragmentActivity fragmentActivity) {

        this.fragmentActivity = fragmentActivity;
    }

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

    public void openResetPassword() {

        fragmentActivity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new LoggedResetPassword()).commit();

    }

    public void openAddress(User user) {

        fragmentActivity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new LoggedAddress(user.getAddress())).commit();
    }

    public void changeAddress(Address address) {

        addressViewModel.changeAddress(this.getToken(), address);
    }

    public String getToken() {

        SharedPreferences settings = context.getSharedPreferences(Context.ACCOUNT_SERVICE, 0);

        return "Bearer_" + settings.getString("token", "");
    }

    public void logout() {

        SharedPreferences settings = context.getSharedPreferences(Context.ACCOUNT_SERVICE, 0);

        SharedPreferences.Editor editor = settings.edit();

        editor.remove("login");
        editor.remove("authorized");
        editor.remove("token");

        editor.putBoolean("remember", false);

        editor.apply();

        LoginViewModel.resetStatus();

        fragmentActivity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new LoginFragment()).commit();
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

    public void setAddressViewModel(AddressViewModel addressViewModel) {

        this.addressViewModel = addressViewModel;
    }
}
