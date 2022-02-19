package com.example.shop_java.register.service;


import android.content.Context;
import android.widget.Toast;

import com.example.shop_java.login.model.Address;
import com.example.shop_java.register.model.RegisterRequest;
import com.example.shop_java.register.viewmodel.RegisterViewModel;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class RegisterService {

    private static RegisterService registerService;
    private final RegisterViewModel registerViewModel = new RegisterViewModel();
    private TabLayout tabLayout;
    private Context context;

    private RegisterRequest registerRequest;

    private Address address = new Address();

    public static RegisterService getInstance() {

        if (registerService == null) {
            registerService = new RegisterService();
        }

        return registerService;
    }

    public RegisterRequest getRegisterRequest() {

        return registerRequest;
    }

    public void setRegisterRequest(RegisterRequest registerRequest) {

        this.registerRequest = registerRequest;
    }

    public Address getAddress() {

        return address;
    }

    public void setAddress(Address address) {

        this.address = address;
    }

    public void setTabLayout(TabLayout tabLayout) {

        this.tabLayout = tabLayout;
    }

    public void setContext(Context context) {

        this.context = context;
    }

    public void handleFirstPage() {

        Objects.requireNonNull(tabLayout.getTabAt(1)).select();
    }

    public void handleSecondPage(Address address) {

        Objects.requireNonNull(tabLayout.getTabAt(2)).select();
    }


    public void register() {

        if (!validate()) {

            Toast.makeText(context, "Invalid data!", Toast.LENGTH_SHORT).show();
        } else {

            registerViewModel.register(registerRequest);
        }

    }

    private boolean validate() {

        String email = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";

        boolean validatedNames = false;

        boolean validatedMail = false;

        boolean validatedPhone = false;

        boolean validatedPassword = false;

        if (
                Stream.of(registerRequest.getName(),
                        registerRequest.getSurname(),
                        registerRequest.getEmail(),
                        registerRequest.getSecondPassword(),
                        registerRequest.getPassword(),
                        registerRequest.getPhone())
                        .allMatch(Objects::nonNull)
        ) {

            registerRequest.setAddress(address);

            validatedNames = registerRequest.getName().length() > 3
                    && registerRequest.getSurname().length() > 3;

            Pattern emailPattern = Pattern.compile(email);

            Matcher emailMatcher = emailPattern.matcher(registerRequest.getEmail());

            Pattern phonePattern = Pattern.compile("[-+]?[0-9]*\\.?[0-9]+$");

            Matcher phoneMatcher = phonePattern.matcher(registerRequest.getPhone());

            validatedMail = emailMatcher.matches();

            validatedPhone = phoneMatcher.matches() && registerRequest.getPhone().length() == 8
                    || registerRequest.getPhone().length() == 7;

            validatedPassword = registerRequest.getPassword()
                    .equals(registerRequest.getSecondPassword())
                    && registerRequest.getPassword().length() > 5;
        }

        return validatedNames && validatedMail && validatedPhone && validatedPassword;

    }

}
