package com.example.shop_java.promotion.ui;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.shop_java.promotion.data.PromotionsClient;
import com.example.shop_java.promotion.model.PromotionModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PromotionViewModel extends ViewModel {
    public MutableLiveData<List<PromotionModel>> promotionsMutableLiveData = new MutableLiveData<>();
    MutableLiveData<String> posts = new MutableLiveData<>();
    public void getPromotions() {
        PromotionsClient.getInstance().getPromotions().enqueue(new Callback<List<PromotionModel>>() {
            @Override
            public void onResponse(Call<List<PromotionModel>> call, Response<List<PromotionModel>> response) {
                System.out.println("RESPONSE:");
                System.out.println(response);
                promotionsMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<PromotionModel>> call, Throwable t) {
                posts.setValue("EROR IN PULA MEA");
            }
        });
    }
}
