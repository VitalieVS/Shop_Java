package com.example.shop_java.product.ui;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.shop_java.category.model.CategoryModel;
import com.example.shop_java.global_models.Product;

import java.util.List;


public class ProductViewModel extends ViewModel {

    public static final MutableLiveData<List<Product>> productMutableLiveData =
            new MutableLiveData<>();


    public void setProducts(CategoryModel categoryModel) {
        productMutableLiveData.setValue(categoryModel.getProductList());
    }

}
