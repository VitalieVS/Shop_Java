package com.example.shop_java.address.data;

import com.example.shop_java.address.model.AddressResponse;
import com.example.shop_java.login.model.Address;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.PUT;

public interface AddressInterface {

    @PUT("user/address")
    Call<AddressResponse> changeAddress(@Header("Authorization") String token,
                                        @Body Address address);
}
