package com.example.ordermanagement.model;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String username;
    private String email;
    private String password;
    private double balance;
    private List<OrderComponent> orders;

    public Customer() { }

    public Customer(String username, String email, String password, double balance) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.balance = balance;
        this.orders = new ArrayList<>();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public List<OrderComponent> getOrders() {
        return orders;
    }
}
