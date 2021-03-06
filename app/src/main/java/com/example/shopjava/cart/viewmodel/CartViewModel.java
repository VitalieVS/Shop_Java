package com.example.shopjava.cart.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.shopjava.models.Product;
import com.example.shopjava.models.State;
import com.example.shopjava.promotion.model.Promotion;

import java.util.List;

public class CartViewModel extends ViewModel {

    public static final MutableLiveData<List<Product>> productMutableLiveData =
            new MutableLiveData<>();

    public static final MutableLiveData<List<Promotion>> promotionMutableLiveData =
            new MutableLiveData<>();

    public static final MutableLiveData<State> stateMutableLiveData = new MutableLiveData<>();

    public void setProductLiveDataValue(List<Product> value) {

        productMutableLiveData.setValue(value);
    }

    public void setPromotionLiveDataValue(List<Promotion> value) {

        promotionMutableLiveData.setValue(value);
    }

    public void setStateMutableLiveData(State value) {

        stateMutableLiveData.setValue(value);
    }


}

