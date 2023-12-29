package com.example.ordermanagement.service;
import org.springframework.stereotype.Service;
@Service
public class PlacmentMessageSecondTemplateService implements PlacmentMessageTemplateService {
    @Override
    public String SendMessage() {
        return "Dear Customer, your order has been confirmed. We will notify you when your order ships. Thank you for shopping with us!";
    }
}