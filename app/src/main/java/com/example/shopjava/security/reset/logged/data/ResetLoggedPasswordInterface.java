package com.example.shopjava.security.reset.logged.data;

import com.example.shopjava.security.reset.anonymous.model.ResetResponse;
import com.example.shopjava.security.reset.logged.model.PasswordLoggedResetRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ResetLoggedPasswordInterface {

    @POST("user/logged/resetPassword")
    Call<ResetResponse> resetPassword(@Header("Authorization") String token,
                                      @Body PasswordLoggedResetRequest passwordLoggedResetRequest);
}
