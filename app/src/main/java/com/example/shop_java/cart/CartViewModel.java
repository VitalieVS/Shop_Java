package com.example.shop_java.cart;

import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.shop_java.global_models.Product;
import com.example.shop_java.promotion.model.Promotion;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CartViewModel extends ViewModel {

    public static final MutableLiveData<List<Product>> productMutableLiveData =
            new MutableLiveData<>();

    public static final MutableLiveData<List<Promotion>> promotionMutableLiveData =
            new MutableLiveData<>();

    private final static List<Product> productCart = new ArrayList<>();
    private final static List<Promotion> promotionCart = new ArrayList<>();

    public void addToProductCart(Product product) {

        if (productExists(product)) {
            increaseProductQuantity(product);
        } else {
            productCart.add(product);
            productMutableLiveData.setValue(productCart);
        }

    }

    public void addToPromotionsCart(View view, Promotion promotionModel) {

        if (promotionExists(promotionModel)) {
            Toast.makeText(view.getContext(), "Can't add more than 1 promotional item",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        promotionCart.add(promotionModel);
        promotionMutableLiveData.setValue(promotionCart);
    }

    private boolean promotionExists(Promotion promotionModel) {

        return promotionCart.stream().anyMatch(
                item -> item.getPromotionId() == promotionModel.getPromotionId());
    }

    private boolean productExists(Product product) {

        return productCart.stream().anyMatch(item -> item.getId() == product.getId());
    }

    private void increaseProductQuantity(Product product) {

        Objects.requireNonNull(productCart.stream()
                .filter(i -> i.getId() == product.getId())
                .findFirst().orElse(null)).setQuantity(product.getQuantity() + 1);
    }
}

