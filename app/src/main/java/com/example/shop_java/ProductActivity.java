package com.example.shop_java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.shop_java.category.model.CategoryModel;
import com.example.shop_java.databinding.ActivityProductBinding;
import com.example.shop_java.product.ui.ProductAdapter;
import com.example.shop_java.product.ui.ProductViewModel;

import java.util.Objects;

public class ProductActivity extends AppCompatActivity {

    private static final String TAG = "ProductActivity";
    private ProductViewModel productViewModel;
    private ActivityProductBinding activityProductBinding;
    private ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();

        CategoryModel categoryModel =
                (CategoryModel) getIntent().getSerializableExtra("CategoryData");

        activityProductBinding = DataBindingUtil.setContentView(this, R.layout.activity_product);
        activityProductBinding.productListRecycler.setLayoutManager(new LinearLayoutManager(this));
        activityProductBinding.productListRecycler.setHasFixedSize(true);
        assert categoryModel != null;
        productAdapter = new ProductAdapter(categoryModel.getProductList());
        activityProductBinding.productListRecycler.setAdapter(productAdapter);

        ImageView backView = findViewById(R.id.back_button);

        backView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}