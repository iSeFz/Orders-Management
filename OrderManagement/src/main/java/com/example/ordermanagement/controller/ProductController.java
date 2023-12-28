package com.example.ordermanagement.controller;

import com.example.ordermanagement.model.Category;
import com.example.ordermanagement.model.Product;
import com.example.ordermanagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService = new ProductService();
    @GetMapping("/get_products")
    public List<Category> getAllProducts(){
        return productService.getProducts();
    }
    @PostMapping("/addProduct")
//    public boolean addProduct(@RequestBody Product p){
//        return productService.addProduct(p);
//    }
    public boolean addProduct(@RequestParam Integer sn, @RequestParam String name, @RequestParam String vendor, @RequestParam double price,@RequestParam String cname, @RequestParam Integer rm){

        Product p = new Product(sn,name,vendor,price,new Category(cname),rm);
        return productService.addProduct(p);
    }
}
