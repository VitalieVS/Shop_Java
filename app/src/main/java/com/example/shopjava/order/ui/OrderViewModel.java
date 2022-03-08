package com.example.shopjava.order.ui;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.shopjava.order.data.OrderClient;
import com.example.shopjava.order.request.OrderRequest;
import com.example.shopjava.order.request.OrderResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderViewModel extends ViewModel {

    public static MutableLiveData<OrderResponse> ORDER_RESPONSE = new MutableLiveData<>();

    public void createOrder(String token, OrderRequest orderRequest) {


        OrderClient.getInstance().createOrder(token, orderRequest).enqueue(new Callback<OrderResponse>() {
            @Override
            public void onResponse(@NonNull Call<OrderResponse> call,
                                   @NonNull Response<OrderResponse> response) {

                //to continue
                ORDER_RESPONSE.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<OrderResponse> call, @NonNull Throwable t) {

                ORDER_RESPONSE.setValue(null);
            }

        });
    }

    public void resetOrderResponse() {

        ORDER_RESPONSE = new MutableLiveData<>();
    }
}
