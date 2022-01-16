package com.example.shop_java.cart;

import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.shop_java.models.Product;
import com.example.shop_java.models.State;
import com.example.shop_java.promotion.model.Promotion;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CartViewModel extends ViewModel {

    public static final MutableLiveData<List<Product>> productMutableLiveData =
            new MutableLiveData<>();

    public static final MutableLiveData<List<Promotion>> promotionMutableLiveData =
            new MutableLiveData<>();

    private final static List<Product> productCart = new ArrayList<>();
    private final static List<Promotion> promotionCart = new ArrayList<>();

    public static final MutableLiveData<State> stateMutableLiveData = new MutableLiveData<>();

    public void addToProductCart(View view, Product product) {

        productCart.add(product);
        productMutableLiveData.setValue(productCart);
    }

    public void increaseProductQuantity(Product product) {

        product.increaseQuantity();

        Optional<Product> searchProduct =
                productCart.stream().filter(item -> item.getId() == product.getId()).findFirst();

        searchProduct.ifPresent(value -> value.setQuantity(product.getQuantity()));

        productCart.removeIf(item -> item.getId() == product.getId());

        productCart.add(product);
        productMutableLiveData.setValue(productCart);

    }

    public void addToPromotionsCart(View view, Promotion promotionModel) {

        if (promotionExists(promotionModel)) {

            Toast.makeText(view.getContext(), "Can't add more than 1 promotional item!",
                    Toast.LENGTH_SHORT).show();
        } else {

            promotionCart.add(promotionModel);
            promotionMutableLiveData.setValue(promotionCart);
        }
    }

    public void removeProductFromCart(Product product) {

        productCart.removeIf(item -> item.getId() == product.getId());

        if (productCart.isEmpty()) {
            stateMutableLiveData.postValue(State.EMPTY_CART);
        } else {
            stateMutableLiveData.postValue(State.CART_ITEMS);
        }

        productMutableLiveData.setValue(productCart);

    }

    private boolean promotionExists(Promotion promotionModel) {

        return promotionCart.stream().anyMatch(
                item -> item.getPromotionId() == promotionModel.getPromotionId());
    }

    public boolean productExists(Product product) {

        return productCart.stream().anyMatch(item -> item.getId() == product.getId());
    }

    public List<Product> getProductCart() {
        return productCart;
    }

    public List<Promotion> getPromotionCart() {
        return promotionCart;
    }


}

