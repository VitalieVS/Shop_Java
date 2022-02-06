package com.example.shop_java.models;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.library.baseAdapters.BR;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import java.io.Serializable;
import java.util.List;

public class Product extends BaseObservable implements Serializable {

    private int id;

    private String title;

    private int price;

    private int priceCopy;

    private String imageURL;

    private int itemPrice;

    private List<Ingredient> ingredients;

    private int weight;

    private int quantity = 1;

    private boolean vegetarian;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    @Bindable
    public int getPrice() {

        return this.price;
    }

    @Bindable
    public int getItemPrice() {

        return this.priceCopy * this.quantity;
    }

    public String getImageURL() {
        return imageURL;
    }

    @Bindable
    public int getQuantity() {
        return quantity;
    }

    public void increaseQuantity() {

        notifyPropertyChanged(BR.quantity);
        notifyPropertyChanged(BR.itemPrice);
    }

    public void setQuantity(int quantity) {

        if (quantity + 1 < 100) {

            this.quantity = quantity;
            notifyPropertyChanged(BR.quantity);
        }
    }

    public void setPrice(int price) {

        this.price = price;
    }

    public void decreaseQuantity() {

        notifyPropertyChanged(BR.quantity);
        notifyPropertyChanged(BR.itemPrice);
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    @BindingAdapter("android:background")
    public static void loadImage(final ImageView imageView, String imageUrl) {

        Glide.with(imageView).load(imageUrl).into(new CustomTarget<Drawable>() {

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onResourceReady(@NonNull Drawable resource,
                                        @Nullable Transition<? super Drawable> transition) {
                imageView.setBackground(resource);
            }

            @Override
            public void onLoadCleared(@Nullable Drawable placeholder) {
                //We don't need to use this implementation
            }
        });
    }

    public String isVegetarian() {
        return (this.vegetarian) ? "VEGETARIAN FOOD" : "NON VEGETARIAN FOOD";
    }

    public void setPriceCopy(int priceCopy) {
        this.priceCopy = priceCopy;
    }

    public int getPriceCopy() {
        return this.priceCopy;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
