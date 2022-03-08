package com.example.shopjava.address.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.shopjava.address.data.AddressClient;
import com.example.shopjava.address.model.AddressResponse;
import com.example.shopjava.login.model.Address;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddressViewModel extends ViewModel {

    public static MutableLiveData<AddressResponse> ADDRESS_RESPONSE = new MutableLiveData<>();

    public void changeAddress(String token, Address address) {

        AddressClient.getInstance().changeAddress(token, address).enqueue(new Callback<AddressResponse>() {
            @Override
            public void onResponse(@NonNull Call<AddressResponse> call,
                                   @NonNull Response<AddressResponse> response) {


                ADDRESS_RESPONSE.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<AddressResponse> call, @NonNull Throwable t) {

                ADDRESS_RESPONSE.setValue(null);
            }

        });
    }

    public void resetAddress() {

        ADDRESS_RESPONSE = new MutableLiveData<>();
    }
}
