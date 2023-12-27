package com.example.ordermanagement.model;

import java.util.Queue;

public class NotificationManager {
    private Queue<MessageTemplate> queue;
    public NotificationManager() {
        queue = new java.util.LinkedList<>();
    }
    public Queue<MessageTemplate> getqueue() {
        return  queue;
    }
}
