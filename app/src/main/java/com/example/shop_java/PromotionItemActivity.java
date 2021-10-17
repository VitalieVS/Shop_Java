package com.example.shop_java;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.example.shop_java.category.model.CategoryModel;

import java.util.Objects;

public class PromotionItemActivity extends AppCompatActivity {

    private static final String TAG = "PromotionItemActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_promotion_item);

        getPromotionID();
    }

    private CategoryModel getPromotionID() {
        CategoryModel promotionID;

        promotionID = (CategoryModel) getIntent().getSerializableExtra("TaskData");


        return promotionID;
    }
}