package com.example.ordermanagement.service;
import com.example.ordermanagement.model.*;
public class NotificationManagerService {
    NotificationManager notificationManager;
    public NotificationManagerService( NotificationManager notificationManager) {
        this.notificationManager = notificationManager;
    }
    public void SetNotificationManager(NotificationManager notificationManager) {
        this.notificationManager = notificationManager;
    }
    public NotificationManager GetNotificationManager() {
        return notificationManager;
    }
    public void addNotification(MessageTemplate messageTemplate) {
        notificationManager.getqueue().add(messageTemplate);
    }
    public void removeNotification(MessageTemplate messageTemplate) {
        notificationManager.getqueue().remove(messageTemplate);
    }
}