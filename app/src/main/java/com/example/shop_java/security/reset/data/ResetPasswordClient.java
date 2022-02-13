package com.example.shop_java.security.reset.data;

import com.example.shop_java.security.reset.model.ResetResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ResetPasswordClient {

    private static final String BASE_URL = "http://10.0.2.2:4546";  // This IP address is used only on EMULATOR
    private static ResetPasswordClient INSTANCE;
    private final ResetPasswordInterface resetPasswordInterface;

    public ResetPasswordClient() {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        resetPasswordInterface = retrofit.create(ResetPasswordInterface.class);
    }

    public static ResetPasswordClient getInstance() {

        if (null == INSTANCE) {
            INSTANCE = new ResetPasswordClient();
        }
        return INSTANCE;
    }

    public Call<ResetResponse> postReset(String email) {

        return resetPasswordInterface.resetPassword(email);
    }
}