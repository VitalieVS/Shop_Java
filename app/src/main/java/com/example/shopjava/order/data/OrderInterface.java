package com.example.shopjava.order.data;

import com.example.shopjava.order.request.OrderRequest;
import com.example.shopjava.order.request.OrderResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface OrderInterface {

    @POST("createOrder")
    Call<OrderResponse> createOrder(@Header("Authorization") String token,
                                    @Body OrderRequest orderRequest);
}
