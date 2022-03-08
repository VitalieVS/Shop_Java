package com.example.shopjava.cart.implementation.adapter;


import com.example.shopjava.cart.binder.ListBindAdapter;
import com.example.shopjava.cart.implementation.binder.ProductBinder;
import com.example.shopjava.cart.implementation.binder.PromotionBinder;
import com.example.shopjava.models.Product;
import com.example.shopjava.promotion.model.Promotion;

import java.util.List;

public class ProductPromotionList extends ListBindAdapter {

    public ProductPromotionList() {

        addAllBinder(new ProductBinder(this),
                new PromotionBinder(this));
    }

    public void setPromotionDataSet(List<Promotion> dataSet) {

        ((PromotionBinder) getDataBinder(1)).addAll(dataSet);
    }

    public void setProductDataSet(List<Product> dataSet) {

        ((ProductBinder) getDataBinder(0)).addAll(dataSet);
    }
}
