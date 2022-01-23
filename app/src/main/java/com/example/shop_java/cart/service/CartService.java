package com.example.shop_java.cart.service;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.shop_java.cart.viewmodel.CartViewModel;
import com.example.shop_java.models.Product;
import com.example.shop_java.models.State;
import com.example.shop_java.promotion.model.Promotion;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CartService {

    private static CartService cartService;
    private final List<Product> productList = new ArrayList<>();
    private final List<Promotion> promotionList = new ArrayList<>();
    private CartViewModel cartViewModel;

    public static CartService getInstance() {

        if (cartService == null) {
            cartService = new CartService();
        }

        return cartService;
    }

    public void addToProductCart(Product product) {

        productList.add(product);

        cartViewModel.setProductLiveDataValue(productList);
    }

    public void increaseProductQuantity(Product product) {

        product.increaseQuantity();

        Optional<Product> searchProduct =
                productList.stream().filter(item -> item.getId() == product.getId()).findFirst();

        searchProduct.ifPresent(value -> value.setQuantity(product.getQuantity()));

        productList.removeIf(item -> item.getId() == product.getId());

        productList.add(product);
        cartViewModel.setProductLiveDataValue(productList);
    }

    public void addToPromotionsCart(View view, Promotion promotionModel) {

        if (promotionExists(promotionModel)) {

            Toast.makeText(view.getContext(), "Can't add more than 1 promotional item!",
                    Toast.LENGTH_SHORT).show();
        } else {

            promotionList.add(promotionModel);
            cartViewModel.setPromotionLiveDataValue(promotionList);
        }
    }

    public void removeProductFromCart(Product product) {

        productList.removeIf(item -> item.getId() == product.getId());

        if (productList.isEmpty()) {
            cartViewModel.setStateMutableLiveData(State.EMPTY_CART);
        } else {
            cartViewModel.setStateMutableLiveData(State.CART_ITEMS);
        }

        cartViewModel.setProductLiveDataValue(productList);

    }

    public void removePromotionFromCart(Promotion promotion) {

        promotionList.removeIf(item -> item.getPromotionId() == promotion.getPromotionId());

        if (promotionList.isEmpty()) {
            cartViewModel.setStateMutableLiveData(State.EMPTY_CART);
        } else {
            cartViewModel.setStateMutableLiveData(State.CART_ITEMS);
        }

        cartViewModel.setPromotionLiveDataValue(promotionList);
    }

    private boolean promotionExists(Promotion promotionModel) {

        return promotionList.stream().anyMatch(
                item -> item.getPromotionId() == promotionModel.getPromotionId());
    }

    public boolean productExists(Product product) {

        return productList.stream().anyMatch(item -> item.getId() == product.getId());
    }

    public void setContext(Context context) {

        this.cartViewModel =
                new ViewModelProvider((FragmentActivity) context).get(CartViewModel.class);
    }

    public List<Product> getProductList() {
        return this.productList;
    }

    public List<Promotion> getPromotionList() {
        return this.promotionList;
    }
}