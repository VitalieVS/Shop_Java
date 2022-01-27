package com.example.shop_java.login.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.shop_java.login.data.LoginClient;
import com.example.shop_java.login.model.LoginRequest;
import com.example.shop_java.login.model.LoginResponse;
import com.example.shop_java.login.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {

    public static final MutableLiveData<AuthorisationStatus> LOGIN_STATUS =
            new MutableLiveData<>();

    public static final MutableLiveData<String> TOKEN = new MutableLiveData<>();

    public static final MutableLiveData<User> USER_MUTABLE_LIVE_DATA = new MutableLiveData<>();

    public static final MutableLiveData<String> LOGIN = new MutableLiveData<>();

    public void authenticate(LoginRequest loginRequest) {

        LoginClient.getInstance().login(loginRequest)
                .enqueue(new Callback<LoginResponse>() {

                    @Override
                    public void onResponse(@NonNull Call<LoginResponse> call,
                                           @NonNull Response<LoginResponse> response) {

                        if (response.body() != null) {

                            TOKEN.setValue(response.body().getToken());
                            LOGIN.setValue(response.body().getLogin());
                            LOGIN_STATUS.setValue(AuthorisationStatus.SUCCESS);
                        }

                        if (response.errorBody() != null) {

                            LOGIN_STATUS.setValue(AuthorisationStatus.FAILED);
                        }

                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        LOGIN_STATUS.setValue(AuthorisationStatus.LOGOUT);
                    }

                });
    }

    public void getUser(String token, String user) {

        LoginClient.getInstance().getUser(user, token)
                .enqueue(new Callback<User>() {

                    @Override
                    public void onResponse(@NonNull Call<User> call,
                                           @NonNull Response<User> response) {

                        USER_MUTABLE_LIVE_DATA.setValue(response.body());
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                        USER_MUTABLE_LIVE_DATA.setValue(null);
                    }

                });

    }

}

