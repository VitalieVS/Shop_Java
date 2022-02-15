package com.example.shop_java.register.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.shop_java.login.model.Address;
import com.example.shop_java.register.data.RegisterClient;
import com.example.shop_java.register.model.RegisterRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterViewModel extends ViewModel {


    public static final MutableLiveData<Boolean> REGISTER_RESPONSE =
            new MutableLiveData<>();

    public void register(RegisterRequest registerRequest, Address address) {

        registerRequest.setAddress(address);

        RegisterClient.getInstance().register(registerRequest)
                .enqueue(new Callback<Boolean>() {

                    @Override
                    public void onResponse(@NonNull Call<Boolean> call,
                                           @NonNull Response<Boolean> response) {

                        REGISTER_RESPONSE.setValue(response.body());
                    }

                    @Override
                    public void onFailure(Call<Boolean> call, Throwable t) {

                        REGISTER_RESPONSE.setValue(false);
                    }

                });
    }
}
