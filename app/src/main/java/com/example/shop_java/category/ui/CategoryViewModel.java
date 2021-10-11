package com.example.shop_java.category.ui;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.shop_java.category.data.CategoryClient;
import com.example.shop_java.category.model.CategoryModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryViewModel extends ViewModel {
    public static final MutableLiveData<List<CategoryModel>> categoriesMutableLiveData =
            new MutableLiveData<>();

    private static final ArrayList<CategoryModel> EMPTY_LIST = new ArrayList<>();

    public void getCategories() {
        CategoryClient.getInstance().getCategories().enqueue(new Callback<List<CategoryModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<CategoryModel>> call,
                                   @NonNull Response<List<CategoryModel>> response) {
                categoriesMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<CategoryModel>> call, Throwable t) {
                categoriesMutableLiveData.setValue(EMPTY_LIST);
            }

        });
    }
}