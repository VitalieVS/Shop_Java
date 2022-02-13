package com.example.shop_java.security.reset.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.shop_java.security.reset.data.ResetPasswordClient;
import com.example.shop_java.security.reset.model.ResetResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResetPasswordViewModel extends ViewModel {


    public static final MutableLiveData<ResetResponse> RESET_RESPONSE = new MutableLiveData<>();

    public void resetPassword(String email) {

        ResetPasswordClient.getInstance().postReset(email)
                .enqueue(new Callback<ResetResponse>() {

                    @Override
                    public void onResponse(@NonNull Call<ResetResponse> call,
                                           @NonNull Response<ResetResponse> response) {

                        if (response.body() != null) {

                            RESET_RESPONSE.setValue(response.body());

                        }


                    }

                    @Override
                    public void onFailure(Call<ResetResponse> call, Throwable t) {


                    }

                });
    }
}
