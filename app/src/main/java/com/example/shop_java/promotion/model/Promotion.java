package com.example.shop_java.promotion.model;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import java.io.Serializable;

public class Promotion implements Serializable {

    private int promotionId;
    private String title;
    private String body;
    private String image;
    private int price;
    private String foodType;

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

    public void setPromotionId(int promotionId) {
        this.promotionId = promotionId;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getFoodType() {
        return foodType;
    }

    public int getPromotionId() {
        return promotionId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getImage() {
        return image;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }
}
