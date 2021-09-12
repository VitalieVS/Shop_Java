package com.example.shop_java.promotion.ui;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
    public void getPromotions() {
        PromotionsClient.getInstance().getPromotions().enqueue(new Callback<List<PromotionModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<PromotionModel>> call,
                                   @NonNull Response<List<PromotionModel>> response) {
                promotionsMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(@Nullable Call<List<PromotionModel>> call,
                                  @Nullable Throwable t) {
                Log.d("FAILED", "FAILED");
                //throw new UnknownError("Internet may be missing!"); // to work here
            }
        });
    }
}
