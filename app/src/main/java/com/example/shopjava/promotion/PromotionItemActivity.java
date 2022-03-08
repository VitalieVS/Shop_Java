package com.example.shopjava.promotion;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.shopjava.R;
import com.example.shopjava.cart.service.CartService;
import com.example.shopjava.databinding.ActivityPromotionItemBinding;
import com.example.shopjava.promotion.model.Promotion;

import java.util.Objects;

public class PromotionItemActivity extends AppCompatActivity {


    private CartService cartService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();

        Promotion promotionModel =
                (Promotion) getIntent().getSerializableExtra("SelectedPromotion");

        ActivityPromotionItemBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_promotion_item);

        binding.setPromotionModel(promotionModel);
        cartService = CartService.getInstance();
        cartService.setContext(this);
        binding.setCartService(cartService);


        ImageView backView = findViewById(R.id.backButton);

        backView.setOnClickListener(v -> finish());

    }
}