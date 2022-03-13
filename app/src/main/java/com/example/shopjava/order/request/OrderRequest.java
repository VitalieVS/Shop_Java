package com.example.shopjava.order.request;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.shopjava.BR;
import com.example.shopjava.models.Product;
import com.example.shopjava.promotion.model.Promotion;

import java.util.List;

public class OrderRequest extends BaseObservable {

    private final String orderStatus = "CREATED";

    private List<Product> productList;

    private String paymentMethod;

    private List<Promotion> promotionList;

    private float cashBackApplied;

    public OrderRequest(List<Product> productList,
                        List<Promotion> promotionList) {

        this.productList = productList;
        this.promotionList = promotionList;
    }

    public List<Product> getProductList() {

        return productList;
    }

    public void setProductList(List<Product> productList) {

        this.productList = productList;
    }

    public List<Promotion> getPromotionList() {

        return promotionList;
    }

    public void setPromotionList(List<Promotion> promotionList) {

        this.promotionList = promotionList;
    }

    public String getPaymentMethod() {

        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {

        this.paymentMethod = paymentMethod;
    }

    @Bindable
    public float getCashBackApplied() {

        return cashBackApplied;
    }

    public void setCashBackApplied(float cashBackApplied) {

        this.cashBackApplied = cashBackApplied;
        notifyPropertyChanged(BR.cashBackApplied);
    }
}
