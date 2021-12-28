package com.example.shop_java.category.ui;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.shop_java.category.data.CategoryClient;
import com.example.shop_java.category.model.Category;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryViewModel extends ViewModel {
    private static final ArrayList<Category> EMPTY_LIST = new ArrayList<>();
    public static MutableLiveData<List<Category>> categoriesMutableLiveData =
            new MutableLiveData<>();

    public void getCategories() {

        CategoryClient.getInstance().getCategories().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(@NonNull Call<List<Category>> call,
                                   @NonNull Response<List<Category>> response) {

                categoriesMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<Category>> call, @NonNull Throwable t) {

                categoriesMutableLiveData.setValue(EMPTY_LIST);
            }

        });
    }
}
