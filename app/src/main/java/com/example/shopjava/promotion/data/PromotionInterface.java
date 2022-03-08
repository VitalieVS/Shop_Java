package com.example.shopjava.promotion.data;

import com.example.shopjava.promotion.model.Promotion;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PromotionInterface {

    @GET("promotions")
    Call<List<Promotion>> getPromotions();
}
