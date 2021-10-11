package com.example.shop_java.promotion.ui;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.shop_java.category.data.CategoryClient;
import com.example.shop_java.promotion.data.PromotionsClient;
import com.example.shop_java.promotion.model.PromotionModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PromotionViewModel extends ViewModel {

    public static final MutableLiveData<List<PromotionModel>> promotionMutableLiveData =
            new MutableLiveData<>();

    private static final ArrayList<PromotionModel> EMPTY_LIST = new ArrayList<>();

    public void getPromotions() {
        PromotionsClient.getInstance().getPromotions().enqueue(new Callback<List<PromotionModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<PromotionModel>> call,
                                   @NonNull Response<List<PromotionModel>> response) {
                promotionMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<PromotionModel>> call, Throwable t) {
                promotionMutableLiveData.setValue(EMPTY_LIST);
            }

        });
    }

}
