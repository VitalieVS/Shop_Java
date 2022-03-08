package com.example.shopjava.register.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.shopjava.register.data.RegisterClient;
import com.example.shopjava.register.model.RegisterRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterViewModel extends ViewModel {

    public static MutableLiveData<RegisterStatus> REGISTER_RESPONSE =
            new MutableLiveData<>();

    public void register(RegisterRequest registerRequest) {

        RegisterClient.getInstance().register(registerRequest)
                .enqueue(new Callback<Boolean>() {

                    @Override
                    public void onResponse(@NonNull Call<Boolean> call,
                                           @NonNull Response<Boolean> response) {

                        if (response.body() != null && response.body()) {

                            REGISTER_RESPONSE.setValue(RegisterStatus.REGISTERED);
                        } else {

                            REGISTER_RESPONSE.setValue(RegisterStatus.ERROR);
                        }

                    }

                    @Override
                    public void onFailure(Call<Boolean> call, Throwable t) {

                        REGISTER_RESPONSE.setValue(RegisterStatus.NO_INTERNET);
                    }

                });
    }

    public void resetResponse() {

        REGISTER_RESPONSE = new MutableLiveData<>();
    }
}
