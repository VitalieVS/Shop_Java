package com.example.shopjava.register.data;

import com.example.shopjava.register.model.RegisterRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RegisterInterface {

    @POST("register")
    Call<Boolean> register(@Body RegisterRequest registerRequest);
}
