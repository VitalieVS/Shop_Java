package com.example.shop_java.global_models;

import java.io.Serializable;
import java.util.List;

public class Product implements Serializable {

    String title;
    int price;
    String imageURL;
    List<Ingredient> ingredients;
    private int id;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getPrice() {
        return price;
    }

    public String getImageURL() {
        return imageURL;
    }


    public List<Ingredient> getIngredients() {
        return ingredients;
    }

}
