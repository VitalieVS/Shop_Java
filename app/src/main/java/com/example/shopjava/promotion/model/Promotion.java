package com.example.shopjava.promotion.model;

import java.io.Serializable;

public class Promotion implements Serializable {

    private int id;

    private String title;

    private String body;

    private String image;

    private int price;

    private String foodType;

    public void setImage(String image) {
        this.image = image;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getFoodType() {
        return foodType;
    }

    public int getPromotionId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getImage() {
        return image;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }
}
