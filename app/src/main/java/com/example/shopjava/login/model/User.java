package com.example.shopjava.login.model;

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

    private List<Order> ordersList;
    private float totalSpentMoney;

    public List<Order> getOrders() {
        return ordersList;
    }

    public void setOrders(List<Order> orders) {
        this.ordersList = orders;
    }

    public float getTotalSpentMoney() {
        return totalSpentMoney;
    }

    public void setTotalSpentMoney(float totalSpentMoney) {
        this.totalSpentMoney = totalSpentMoney;
    }

}
