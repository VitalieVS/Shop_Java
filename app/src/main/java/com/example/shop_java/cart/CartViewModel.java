package com.example.shop_java.cart;

import android.util.Log;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.shop_java.global_models.Product;
import com.example.shop_java.promotion.model.PromotionModel;

import java.util.ArrayList;
import java.util.List;

public class CartViewModel extends ViewModel {

    public static final MutableLiveData<List<Product>> cartMutableLiveData =
            new MutableLiveData<>();
    private final static List<Product> productCart = new ArrayList<>();
    private final static List<PromotionModel> promotionCart = new ArrayList<>();

    public static void addToProductCart(Product product) {
        productCart.add(product);
        Log.d("HERE", "added:" + product.getTitle());
    }

    public static void addToPromotionsCart(View view, PromotionModel promotionModel) {
        promotionCart.add(promotionModel);
        Log.d("HERE", "added:" + promotionModel.getTitle());
    }


}

