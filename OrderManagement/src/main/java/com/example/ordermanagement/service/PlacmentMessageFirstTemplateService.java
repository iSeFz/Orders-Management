package com.example.ordermanagement.service;

import org.springframework.stereotype.Service;

@Service
public class PlacmentMessageFirstTemplateService implements PlacmentMessageTemplateService {
    @Override
    public String SendMessage() {
        return "Great news! Your order is confirmed and on its way. Thank you for choosing us! #HappyShopping 🚚🛍️";
    }
}