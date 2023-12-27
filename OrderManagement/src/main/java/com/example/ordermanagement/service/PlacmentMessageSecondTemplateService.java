package com.example.ordermanagement.service;
import com.example.ordermanagement.model.*;
import org.springframework.stereotype.Service;
@Service
public class PlacmentMessageSecondTemplateService implements PlacmentMessageTemplate {
    @Override
    public String SendMessage() {
        return "Dear Customer, your order has been confirmed. We will notify you when your order ships. Thank you for shopping with us!";
    }
}