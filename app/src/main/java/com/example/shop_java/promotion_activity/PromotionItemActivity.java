package com.example.shop_java.promotion_activity;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.example.shop_java.R;
import com.example.shop_java.databinding.ActivityPromotionItemBinding;
import com.example.shop_java.promotion.model.PromotionModel;
import com.example.shop_java.promotion_activity.view_model.SinglePromotionViewModel;

import java.util.Objects;

public class PromotionItemActivity extends AppCompatActivity {

    private SinglePromotionViewModel singlePromotionViewModel;
    private static final String TAG = "PromotionItemActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();

        singlePromotionViewModel =
                ViewModelProviders.of(this).get(SinglePromotionViewModel.class);

        PromotionModel promotionModel =
                (PromotionModel) getIntent().getSerializableExtra("SelectedPromotion");

        singlePromotionViewModel.setItem(promotionModel); // future feature

        ActivityPromotionItemBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_promotion_item);

        binding.setPromotionModel(promotionModel);


        ImageView backView = findViewById(R.id.back_button);

        backView.setOnClickListener(v -> finish());

    }
}