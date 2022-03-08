package com.example.shopjava.order.request;

public class OrderResponse {

    private boolean created;

    private String date;

    private String message;

    public boolean isCreated() {

        return created;
    }

    public void setCreated(boolean created) {

        this.created = created;
    }

    public String getDate() {

        return date;
    }

    public void setDate(String date) {

        this.date = date;
    }

    public String getMessage() {

        return message;
    }

    public void setMessage(String message) {

        this.message = message;
    }
}
