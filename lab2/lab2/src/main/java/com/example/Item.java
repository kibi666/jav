package com.example;

import java.util.Calendar;
import java.util.Date;

public class Item {
    private String name;
    private String image;
    private double price;
    private double discount;
    private Date date;

    public Item() {
        this.date = new Date();
    }

    public Item(String name, String image, double price, double discount, int day) {
        this.name = name;
        this.image = image;
        this.price = price;
        this.discount = discount;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.DAY_OF_MONTH, day);
        this.date = calendar.getTime();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
