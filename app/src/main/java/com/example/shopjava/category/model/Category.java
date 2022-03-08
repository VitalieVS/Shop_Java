package com.example.shopjava.category.model;

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
import com.example.shopjava.models.Product;

import java.io.Serializable;
import java.util.List;

public class Category implements Serializable {


    private String name;

    private String imageURL;

    private List<Product> productList;

    public List<Product> getProductList() {
        return productList;
    }

    public String getName() {
        return name;
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

    public String getImageURL() {
        return imageURL;
    }
}
