package com.example.shop_java.promotion;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.shop_java.R;
import com.example.shop_java.cart.service.CartService;
import com.example.shop_java.cart.viewmodel.CartViewModel;
import com.example.shop_java.databinding.ActivityPromotionItemBinding;
import com.example.shop_java.promotion.model.Promotion;

import java.util.Objects;

public class PromotionItemActivity extends AppCompatActivity {

    private CartViewModel cartViewModel;
    private CartService cartService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();


        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);

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