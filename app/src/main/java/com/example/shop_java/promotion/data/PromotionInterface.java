package com.example.shop_java.promotion.data;

import com.example.shop_java.promotion.model.Promotion;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PromotionInterface {

    @GET("promotions")
    Call<List<Promotion>> getPromotions();
}
