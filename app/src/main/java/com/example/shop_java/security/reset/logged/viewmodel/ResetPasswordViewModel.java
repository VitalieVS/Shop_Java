package com.example.shop_java.security.reset.logged.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.shop_java.security.reset.anonymous.model.ResetResponse;
import com.example.shop_java.security.reset.logged.data.ResetLoggedPasswordClient;
import com.example.shop_java.security.reset.logged.model.PasswordLoggedResetRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResetPasswordViewModel extends ViewModel {

    public static MutableLiveData<ResetResponse> RESET_RESPONSE = new MutableLiveData<>();

    public void postResetLoggedPassword(String token, PasswordLoggedResetRequest passwordLoggedResetRequest) {

        ResetLoggedPasswordClient.getInstance().postResetLoggedPassword(token, passwordLoggedResetRequest)
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

                        RESET_RESPONSE.setValue(null);
                    }

                });
    }

    public void setResetResponse() {
        RESET_RESPONSE = new MutableLiveData<>();
    }
}
