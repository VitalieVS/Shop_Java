package com.example.shop_java.promotion.ui.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.shop_java.promotion.data.PromotionsClient;
import com.example.shop_java.promotion.model.Promotion;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PromotionViewModel extends ViewModel {

    public static final MutableLiveData<List<Promotion>> promotionMutableLiveData =
            new MutableLiveData<>();

    private static final ArrayList<Promotion> EMPTY_LIST = new ArrayList<>();

    public void getPromotions() {

        PromotionsClient.getInstance().getPromotions()
                .enqueue(new Callback<List<Promotion>>() {

                    @Override
                    public void onResponse(@NonNull Call<List<Promotion>> call,
                                           @NonNull Response<List<Promotion>> response) {
                        promotionMutableLiveData.setValue(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<Promotion>> call, Throwable t) {
                        promotionMutableLiveData.setValue(EMPTY_LIST);
                    }

        });
    }

}
