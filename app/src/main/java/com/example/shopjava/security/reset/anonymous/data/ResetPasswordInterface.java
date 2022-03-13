package com.example.shopjava.security.reset.anonymous.data;

import com.example.shopjava.security.reset.anonymous.model.ResetResponse;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ResetPasswordInterface {

    @POST("reset/password")
    Call<ResetResponse> resetPassword(@Query("email") String email);
}
