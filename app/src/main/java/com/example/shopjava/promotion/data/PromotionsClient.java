package com.example.shopjava.promotion.data;

import com.example.shopjava.promotion.model.Promotion;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PromotionsClient {

    private static final String BASE_URL = "http://10.0.2.2:4546";  // This IP address is used only on EMULATOR
    private final PromotionInterface promotionInterface;
    private static PromotionsClient INSTANCE;

    public PromotionsClient() {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        promotionInterface = retrofit.create(PromotionInterface.class);
    }

    public static PromotionsClient getInstance() {

        if (null == INSTANCE) {

            INSTANCE = new PromotionsClient();
        }
        return INSTANCE;
    }

    public Call<List<Promotion>> getPromotions() {
        return promotionInterface.getPromotions();
    }
}
