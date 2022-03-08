package com.example.shopjava.address.data;

import com.example.shopjava.address.model.AddressResponse;
import com.example.shopjava.login.model.Address;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.PUT;

public interface AddressInterface {

    @PUT("user/address")
    Call<AddressResponse> changeAddress(@Header("Authorization") String token,
                                        @Body Address address);
}
