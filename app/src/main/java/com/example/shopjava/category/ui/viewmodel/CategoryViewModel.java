package com.example.shopjava.category.ui.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.shopjava.category.data.CategoryClient;
import com.example.shopjava.category.model.Category;
import com.example.shopjava.models.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

                for (Category category : Objects.requireNonNull(response.body())) {
                    for (Product product : category.getProductList()) {
                        product.setPriceCopy(product.getPrice());
                    }
                }

                categoriesMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<Category>> call, @NonNull Throwable t) {

                categoriesMutableLiveData.setValue(EMPTY_LIST);
            }

        });
    }
}
