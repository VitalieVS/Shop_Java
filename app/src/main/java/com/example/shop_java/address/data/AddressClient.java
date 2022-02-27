package com.example.shop_java.address.data;


import com.example.shop_java.address.model.AddressResponse;
import com.example.shop_java.login.model.Address;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddressClient {

    private static final String BASE_URL = "http://10.0.2.2:4546";  // This URL is used by emulator to work with API
    private static AddressClient INSTANCE;
    private final AddressInterface categoryInterface;

    public AddressClient() {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        categoryInterface = retrofit.create(AddressInterface.class);
    }

    public static AddressClient getInstance() {

        if (null == INSTANCE) {
            INSTANCE = new AddressClient();
        }
        return INSTANCE;
    }

    public Call<AddressResponse> changeAddress(String token, Address address) {

        return categoryInterface.changeAddress(token, address);
    }
}
