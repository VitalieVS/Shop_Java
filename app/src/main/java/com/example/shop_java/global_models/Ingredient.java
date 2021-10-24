package com.example.shop_java.global_models;

import java.io.Serializable;

public class Ingredient implements Serializable {

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
}
