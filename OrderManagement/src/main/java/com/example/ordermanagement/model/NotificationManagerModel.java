package com.example.ordermanagement.model;

import com.example.ordermanagement.service.NotificationService;

import java.util.Queue;

public class NotificationManagerModel {
    Queue<NotificationService> queue;
    public NotificationManagerModel() {
        queue = new java.util.LinkedList<>();
    }
    public void setqueue(Queue<NotificationService> queue) {
        this.queue = queue;
    }
    public Queue<NotificationService> getqueue() {
        return queue;
    }
}
