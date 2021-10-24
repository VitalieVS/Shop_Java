package com.example.shop_java.category.data;

import com.example.shop_java.category.model.CategoryModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoryInterface {

    @GET("categories")
    Call<List<CategoryModel>> getPromotions();
}
