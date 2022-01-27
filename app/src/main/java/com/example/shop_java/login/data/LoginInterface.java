package com.example.shop_java.login.data;

import com.example.shop_java.login.model.LoginRequest;
import com.example.shop_java.login.model.LoginResponse;
import com.example.shop_java.login.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface LoginInterface {

    @POST("login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    @GET("user/{login}")
    Call<User> getUser(@Header("Authorization") String token, @Path("login") String login);
}
