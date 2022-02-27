package com.example.shop_java.address.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.shop_java.address.data.AddressClient;
import com.example.shop_java.address.model.AddressResponse;
import com.example.shop_java.login.model.Address;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddressViewModel extends ViewModel {

    public static MutableLiveData<AddressResponse> ADDRESS_RESPONSE = new MutableLiveData<>();

    public void changeAddress(String token, Address address) {

        System.out.println("eu aici sunt");

        System.out.println(address.getId());
        System.out.println(address.getSecondAddress());

        AddressClient.getInstance().changeAddress(token, address).enqueue(new Callback<AddressResponse>() {
            @Override
            public void onResponse(@NonNull Call<AddressResponse> call,
                                   @NonNull Response<AddressResponse> response) {

                System.out.println(response.raw());
                System.out.println(response.message());
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
