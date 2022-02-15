package com.example.shop_java.register.data;

import com.example.shop_java.register.model.RegisterRequest;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterClient {

    private static final String BASE_URL = "http://10.0.2.2:4546";  // This IP address is used only on EMULATOR
    private static RegisterClient INSTANCE;
    private final RegisterInterface registerInterface;

    public RegisterClient() {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        registerInterface = retrofit.create(RegisterInterface.class);
    }

    public static RegisterClient getInstance() {

        if (null == INSTANCE) {
            INSTANCE = new RegisterClient();
        }
        return INSTANCE;
    }

    public Call<Boolean> register(RegisterRequest registerRequest) {
        return registerInterface.register(registerRequest);
    }
}

