package com.example.shop_java.promotion;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.example.shop_java.R;
import com.example.shop_java.cart.CartViewModel;
import com.example.shop_java.databinding.ActivityPromotionItemBinding;
import com.example.shop_java.promotion.model.Promotion;
import com.example.shop_java.promotion.ui.PromotionViewModel;

import java.util.Objects;

public class PromotionItemActivity extends AppCompatActivity {

    private PromotionViewModel promotionViewModel;
    private static final String TAG = "PromotionItemActivity";
    private CartViewModel cartViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();

        promotionViewModel =
                ViewModelProviders.of(this).get(PromotionViewModel.class);

        cartViewModel =
                ViewModelProviders.of(this).get(CartViewModel.class);

        Promotion promotionModel =
                (Promotion) getIntent().getSerializableExtra("SelectedPromotion");

        ActivityPromotionItemBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_promotion_item);

        binding.setPromotionModel(promotionModel);
        binding.setCartViewModel(cartViewModel);

        ImageView backView = findViewById(R.id.backButton);

        backView.setOnClickListener(v -> finish());

    }
}