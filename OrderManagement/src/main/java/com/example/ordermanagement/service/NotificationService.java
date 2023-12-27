package com.example.ordermanagement.service;
import com.example.ordermanagement.model.*;
public class NotificationService {
    Notification notification;
    NotificationService(Notification notification) {
        this.notification = notification;
    }
    public void setTemplate(Notification notification) {
        this.notification = notification;
    }
    public Notification getTemplate() {
        return notification;
    }
    public String doSendNotifcation() {
        return notification.getTemplate().SendMessage();
    }
}