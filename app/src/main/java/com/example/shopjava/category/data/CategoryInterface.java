package com.example.shopjava.category.data;

import com.example.shopjava.category.model.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoryInterface {

    @GET("categories")
    Call<List<Category>> getCategories();
}
