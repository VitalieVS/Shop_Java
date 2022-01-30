package com.example.shop_java.login.model;

import java.util.List;

public class User {

    private String surname;

    private String name;

    private Address address;

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    private List<Order> orders;
    private float totalSpentMoney;

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public float getTotalSpentMoney() {
        return totalSpentMoney;
    }

    public void setTotalSpentMoney(float totalSpentMoney) {
        this.totalSpentMoney = totalSpentMoney;
    }

}
