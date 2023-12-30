package com.example.ordermanagement.model;

// Product entity class
public class Product {
    private int serialNum;
    private String name;
    private String vendor;
    private double price;
    private String categoryName;
    private int remainingCount;

    // Default constructor
    public Product() {
        this.serialNum = 0;
        this.name = "";
        this.vendor = "";
        this.price = 0.0;
        this.categoryName = "";
        this.remainingCount = 0;
    }

    // Parameterized constructor
    public Product(int serialNum, String name, String vendor, double price, String categoryName, int remainingCount) {
        this();
        this.serialNum = serialNum;
        this.name = name;
        this.vendor = vendor;
        this.price = price;
        this.categoryName = categoryName;
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

    public String getVendor() {
        return this.vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public void setCategoryName(String category) {
        this.categoryName = category;
    }

    public int getRemainingCount() {
        return this.remainingCount;
    }

    public void setRemainingCount(int remainingCount) {
        this.remainingCount = remainingCount;
    }
    @Override
    public String toString() {
        return "Product [serialNum=" + serialNum + ", name=" + name + ", vendor=" + vendor + ", price=" + price
                + ", categoryName=" + categoryName + ", remainingCount=" + remainingCount + "]\n";
    }

}
