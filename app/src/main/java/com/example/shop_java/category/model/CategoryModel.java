package com.example.shop_java.category.model;

import com.example.shop_java.global_models.Product;

import java.io.Serializable;
import java.util.List;

public class CategoryModel implements Serializable {

    private int categoryId;
    private String name;
    private List<Product> productList;

    public List<Product> getProductList() {
        return productList;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getName() {
        return name;
    }
}
