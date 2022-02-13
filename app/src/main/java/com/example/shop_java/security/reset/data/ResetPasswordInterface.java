package com.example.shop_java.security.reset.data;

import com.example.shop_java.security.reset.model.ResetResponse;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ResetPasswordInterface {

    @POST("user/resetPassword")
    Call<ResetResponse> resetPassword(@Query("email") String email);
}
