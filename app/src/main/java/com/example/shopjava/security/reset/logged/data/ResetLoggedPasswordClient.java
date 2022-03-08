package com.example.shopjava.security.reset.logged.data;

import com.example.shopjava.security.reset.anonymous.model.ResetResponse;
import com.example.shopjava.security.reset.logged.model.PasswordLoggedResetRequest;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ResetLoggedPasswordClient {

    private static final String BASE_URL = "http://10.0.2.2:4546";  // This IP address is used only on EMULATOR
    private static ResetLoggedPasswordClient INSTANCE;
    private final ResetLoggedPasswordInterface resetLoggedPasswordInterface;

    public ResetLoggedPasswordClient() {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        resetLoggedPasswordInterface = retrofit.create(ResetLoggedPasswordInterface.class);
    }

    public static ResetLoggedPasswordClient getInstance() {

        if (null == INSTANCE) {
            INSTANCE = new ResetLoggedPasswordClient();
        }
        return INSTANCE;
    }

    public Call<ResetResponse> postResetLoggedPassword(String token,
                                                       PasswordLoggedResetRequest passwordLoggedResetRequest) {

        return resetLoggedPasswordInterface.resetPassword(token, passwordLoggedResetRequest);
    }
}