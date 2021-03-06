package com.example.shopjava;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.shopjava.cart.service.CartService;
import com.example.shopjava.category.model.Category;
import com.example.shopjava.models.Product;
import com.example.shopjava.product.ui.adapter.ProductAdapter;

import java.util.Objects;

public class ProductActivity extends AppCompatActivity {

    private CartService cartService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();

        cartService = CartService.getInstance();

        Category categoryModel =
                (Category) getIntent().getSerializableExtra("CategoryData");

        for (Product product : categoryModel.getProductList()) {

            if (cartService.productExists(product)) {

                product.setQuantity(cartService.getProductInCart(product).getQuantity());
            }
        }


        com.example.shopjava.databinding.ActivityProductBinding activityProductBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_product);
        activityProductBinding.productListRecycler.setLayoutManager(new LinearLayoutManager(this));
        activityProductBinding.productListRecycler.setHasFixedSize(true);
        ProductAdapter productAdapter = new ProductAdapter(categoryModel.getProductList());
        activityProductBinding.productListRecycler.setAdapter(productAdapter);

        ImageView backView = findViewById(R.id.backButton);

        backView.setOnClickListener(v -> finish());
    }
}