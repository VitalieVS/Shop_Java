package com.example.shop_java.cart.service;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.shop_java.cart.viewmodel.CartViewModel;
import com.example.shop_java.models.Product;
import com.example.shop_java.models.State;
import com.example.shop_java.promotion.model.Promotion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class CartService extends BaseObservable {

    private static CartService cartService;
    private final List<Product> productList = new ArrayList<>();
    private final List<Promotion> promotionList = new ArrayList<>();
    private CartViewModel cartViewModel;

    private int totalCartPrice;

    public static CartService getInstance() {

        if (cartService == null) {
            cartService = new CartService();
        }

        return cartService;
    }

    public void bottomSheetAddToProductCart(Product product) {

        if (productExists(product)) {

            Optional<Product> productInCart =
                    productList.stream().filter(item -> item.getId() == product.getId()).findFirst();

            Objects.requireNonNull(productInCart.orElse(null))
                    .setQuantity(product.getQuantity());

        } else {
            addToProductCart(null, product);
        }

    }

    public void addToProductCart(View view, Product product) {

        if (productExists(product)) {

            Toast.makeText(view.getContext(), "Product already in cart, please change quantity!",
                    Toast.LENGTH_SHORT).show();
        } else {

            productList.add(product);

            cartViewModel.setProductLiveDataValue(productList);
            cartViewModel.setStateMutableLiveData(State.PRODUCT_ITEMS);
        }

    }

    public void increaseProductQuantity(@NonNull Product product) {

        if (product.getQuantity() + 1 < 100) {

            product.setQuantity(product.getQuantity() + 1);

            product.increaseQuantity();

            notifyPropertyChanged(BR.totalCartPrice);

        }
    }

    public void decreaseProductQuantity(@NonNull Product product) {

        if (product.getQuantity() - 1 > 0) {

            product.setQuantity(product.getQuantity() - 1);

            product.decreaseQuantity();

            notifyPropertyChanged(BR.totalCartPrice);
        }

    }

    public void addToPromotionsCart(View view, Promotion promotionModel) {

        if (promotionExists(promotionModel)) {

            Toast.makeText(view.getContext(), "Can't add more than 1 promotional item!",
                    Toast.LENGTH_SHORT).show();
        } else {

            promotionList.add(promotionModel);
            cartViewModel.setPromotionLiveDataValue(promotionList);
            cartViewModel.setStateMutableLiveData(State.PROMOTION_ITEMS);
        }
    }

    public <T> void removeFromCart(T item) {

        if (item instanceof Promotion) {
            promotionList.removeIf(x -> x.getPromotionId() == ((Promotion) item).getPromotionId());
        } else {
            productList.removeIf(x -> x.getId() == ((Product) item).getId());
        }

        Map<Boolean, Runnable> map = new HashMap<>();
        map.put(!promotionList.isEmpty(), () -> cartViewModel.setStateMutableLiveData(State.PROMOTION_ITEMS));
        map.put(!productList.isEmpty(), () -> cartViewModel.setStateMutableLiveData(State.PRODUCT_ITEMS));
        Runnable defaultAction = () -> cartViewModel.setStateMutableLiveData(State.EMPTY_CART);

        Objects.requireNonNull(map.getOrDefault(true,
                defaultAction)).run();

        cartViewModel.setProductLiveDataValue(productList);
        cartViewModel.setPromotionLiveDataValue(promotionList);
        notifyPropertyChanged(BR.totalCartPrice);
    }


    private boolean promotionExists(Promotion promotionModel) {

        return promotionList.stream().anyMatch(
                item -> item.getPromotionId() == promotionModel.getPromotionId());
    }

    public boolean productExists(Product product) {

        return productList.stream().anyMatch(item -> item.getId() == product.getId());
    }

    public Product getProductInCart(Product product) {

        return productList.stream().filter(
                item -> item.getId() == product.getId()).findFirst().orElse(null);
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


    @Bindable
    public int getTotalCartPrice() {

        return promotionList.stream().reduce(0,
                (x, y) -> x + y.getPrice(),
                Integer::sum) + productList.stream().reduce(0,
                (x, y) -> x + y.getPrice() * y.getQuantity(),
                Integer::sum);
    }

    public void setTotalCartPrice(int totalCartPrice) {
        this.totalCartPrice = totalCartPrice;
    }
}