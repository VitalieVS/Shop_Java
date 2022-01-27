package com.example.shop_java.login.data;

import com.example.shop_java.login.model.LoginRequest;
import com.example.shop_java.login.model.LoginResponse;
import com.example.shop_java.login.model.User;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginClient {

    private static final String BASE_URL = "http://10.0.2.2:4546";  // This IP address is used only on EMULATOR
    private static LoginClient INSTANCE;
    private final LoginInterface loginInterface;

    public LoginClient() {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        loginInterface = retrofit.create(LoginInterface.class);
    }

    public static LoginClient getInstance() {

        if (null == INSTANCE) {
            INSTANCE = new LoginClient();
        }
        return INSTANCE;
    }

    public Call<LoginResponse> login(LoginRequest loginRequest) {
        return loginInterface.login(loginRequest);
    }

    public Call<User> getUser(String token, String login) {
        return loginInterface.getUser(login, token);
    }
}


