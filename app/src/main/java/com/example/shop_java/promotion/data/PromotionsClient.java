package com.example.shop_java.promotion.data;

import com.example.shop_java.promotion.model.PromotionModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PromotionsClient {
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private PromotionInterface promotionInterface;
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

    public Call<List<PromotionModel>> getPromotions() {
        return promotionInterface.getPromotions();
    }
}
