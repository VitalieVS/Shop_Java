package com.example.shopjava.order.data;


import com.example.shopjava.order.request.OrderRequest;
import com.example.shopjava.order.request.OrderResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OrderClient {

    private static final String BASE_URL = "http://10.0.2.2:4546";  // This URL is used by emulator to work with API
    private static OrderClient INSTANCE;
    private final OrderInterface orderInterface;

    public OrderClient() {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        orderInterface = retrofit.create(OrderInterface.class);
    }

    public static OrderClient getInstance() {

        if (null == INSTANCE) {
            INSTANCE = new OrderClient();
        }
        return INSTANCE;
    }

    public Call<OrderResponse> createOrder(String token, OrderRequest orderRequest) {

        return orderInterface.createOrder(token, orderRequest);
    }
}
