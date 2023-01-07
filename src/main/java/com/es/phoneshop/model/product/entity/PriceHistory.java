package com.es.phoneshop.model.product.entity;

public class PriceHistory {
    private String date;
    private Long price;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public PriceHistory() {
    }

    public PriceHistory(String date, Long price) {
        this.date = date;
        this.price = price;
    }
}