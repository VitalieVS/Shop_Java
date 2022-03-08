package com.example.shopjava.order.request;

import com.example.shopjava.login.model.Address;
import com.example.shopjava.models.Product;
import com.example.shopjava.promotion.model.Promotion;

import java.util.List;

public class OrderRequest {

    private final String orderStatus = "CREATED";
    private Address shippingAddress;
    private List<Product> productList;
    private List<Promotion> promotionList;

    public OrderRequest(Address shippingAddress,
                        List<Product> productList,
                        List<Promotion> promotionList) {

        this.shippingAddress = shippingAddress;
        this.productList = productList;
        this.promotionList = promotionList;
    }

    public Address getShippingAddress() {

        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {

        this.shippingAddress = shippingAddress;
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
}
