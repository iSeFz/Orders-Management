package com.example.ordermanagement;

import com.example.ordermanagement.model.Category;
import com.example.ordermanagement.model.Customer;
import com.example.ordermanagement.model.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Common {
    public List<Product> productList;
    public List<Customer> customerList;
    public List<Category> categories;
    public Common(){
        productList = new ArrayList<>();
        customerList = new ArrayList<>();
        categories = new ArrayList<>();
        Product p1 =new Product(1,"milk","sasa",189,"alban",10);
        Product p2 =new Product(2,"cheese","oppad",22,"alban",90);
        Product p3 =new Product(3,"potato maklya","syfe",25,"alban",90);
        Product p4 =new Product(4,"basal","syfe",40,"alban",5);
        Product p5 =new Product(5,"kabsa","yousef",1800,"alban",96);
        productList.addAll(Arrays.asList(p1,p2,p3,p4,p5));
        for(Product p : productList){
            boolean status = false;
            for(Category c : categories){
                if(c.getName() == p.getCategoryName()){
                    c.addProduct(p);
                    status = true;
                    break;
                }
            }
            if(!status){
                categories.add(new Category(p.getCategoryName()));
//                categories.getLast().addProduct(p);
            }
        }
        Customer c1 = new Customer("mostaf","mostafa@gmail.com","12345",90000);
        Customer c2 = new Customer("oppad","oppad@gmail.com","67890",1000);
        Customer c3 = new Customer("syfe","syfe@gmail.com","23456",300);
        Customer c4 = new Customer("yousef","mostafa@gmail.com","78901",600);
        Customer c5 = new Customer("ossama","osos@gmail.com","12345",7000);
        customerList.addAll(Arrays.asList(c1,c2,c3,c4,c5));
    }
}
