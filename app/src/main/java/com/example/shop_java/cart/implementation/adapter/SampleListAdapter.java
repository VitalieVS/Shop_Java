package com.example.shop_java.cart.implementation.adapter;


import com.example.shop_java.cart.binder.ListBindAdapter;
import com.example.shop_java.cart.implementation.binder.ProductBinder;
import com.example.shop_java.cart.implementation.binder.PromotionBinder;
import com.example.shop_java.models.Product;
import com.example.shop_java.promotion.model.Promotion;

import java.util.List;

public class SampleListAdapter extends ListBindAdapter {

    public SampleListAdapter() {

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
