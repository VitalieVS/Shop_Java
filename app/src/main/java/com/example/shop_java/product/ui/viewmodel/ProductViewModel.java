package com.example.shop_java.product.ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.shop_java.category.model.Category;
import com.example.shop_java.models.Product;

import java.util.List;


public class ProductViewModel extends ViewModel {

    public static final MutableLiveData<List<Product>> productMutableLiveData =
            new MutableLiveData<>();


    public void setProducts(Category categoryModel) {
        productMutableLiveData.setValue(categoryModel.getProductList());
    }

}
