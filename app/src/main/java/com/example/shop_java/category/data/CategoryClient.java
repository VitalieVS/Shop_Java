package com.example.shop_java.category.data;


import com.example.shop_java.category.model.CategoryModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CategoryClient {

    private static final String BASE_URL = "http://10.0.2.2:4546";  //
    private static CategoryClient INSTANCE;
    private CategoryInterface categoryInterface;

    public CategoryClient() {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        categoryInterface = retrofit.create(CategoryInterface.class);
    }

    public static CategoryClient getInstance() {

        if (null == INSTANCE) {
            INSTANCE = new CategoryClient();
        }
        return INSTANCE;
    }

    public Call<List<CategoryModel>> getCategories() {

        return categoryInterface.getCategories();
    }
}
