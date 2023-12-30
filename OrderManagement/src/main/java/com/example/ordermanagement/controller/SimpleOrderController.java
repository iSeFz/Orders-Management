package com.example.ordermanagement.controller;

import com.example.ordermanagement.model.SimpleOrder;
import com.example.ordermanagement.service.SimpleOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleOrderController {
    @Autowired
    private SimpleOrderService simpleOrderService= new SimpleOrderService();
    @GetMapping("/orderDetails")
    public String listDetails(@RequestParam int id){
        return simpleOrderService.listDetails(id);
    }
}
