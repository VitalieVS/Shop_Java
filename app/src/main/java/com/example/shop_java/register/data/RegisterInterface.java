package com.example.shop_java.register.data;

import com.example.shop_java.register.model.RegisterRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RegisterInterface {

    @POST("register")
    Call<Boolean> register(@Body RegisterRequest registerRequest);
}
