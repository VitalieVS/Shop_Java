package com.example.shop_java.promotion_activity.view_model;

import androidx.lifecycle.ViewModel;

import com.example.shop_java.promotion.model.PromotionModel;

public class SinglePromotionViewModel extends ViewModel {

    private PromotionModel promotionModel;

    public void setItem(PromotionModel promotionModel) {
        this.promotionModel = promotionModel;
    }
}

