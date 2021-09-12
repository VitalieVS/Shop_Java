package com.example.shop_java;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

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

    private int getPromotionID() {
        int promotionID;

        promotionID = getIntent().getIntExtra("promotionID", 0);

        if (promotionID == 0) {
            throw new Error("Promotion ID cannot be 0");
        }
        return promotionID;
    }
}