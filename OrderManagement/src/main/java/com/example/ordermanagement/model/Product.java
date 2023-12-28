package com.example.ordermanagement.model;

public class Product {
    private int serialNum;
    private String name;
    private String vendor;
    private double price;
//    private Category category;
    private String categoryName;
    private int remainingCount;

    public Product(int serialNum, String name, String vendor, double price, String category, int remainingCount) {
        this.serialNum = serialNum;
        this.name = name;
        this.vendor = vendor;
        this.price = price;
        this.categoryName = category;
        this.remainingCount = remainingCount;
    }

    public int getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(int serialNum) {
        this.serialNum = serialNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public Category getCategory() {
//        return category;
//    }
    public String getCategoryName(){return categoryName;}

//    public void setCategory(Category category) {
//        this.category = category;
//    }

    public int getRemainingCount() {
        return remainingCount;
    }

    public void setRemainingCount(int remainingCount) {
        this.remainingCount = remainingCount;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
