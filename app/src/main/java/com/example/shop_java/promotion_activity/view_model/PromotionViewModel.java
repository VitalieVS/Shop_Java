package com.example.shop_java.promotion_activity.view_model;

import androidx.lifecycle.ViewModel;

import com.example.shop_java.promotion.model.Promotion;

public class PromotionViewModel extends ViewModel {

    private Promotion promotionModel;

    public void setItem(Promotion promotionModel) {
        this.promotionModel = promotionModel;
    }
}

