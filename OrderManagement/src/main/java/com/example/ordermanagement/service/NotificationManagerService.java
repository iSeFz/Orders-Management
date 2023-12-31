package com.example.ordermanagement.service;

import com.example.ordermanagement.repos.NotificationManagerRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Iterator;

public class NotificationManagerService {
    @Autowired
    NotificationManagerRepo notificationManagerRepo;

    public NotificationManagerService(NotificationManagerRepo notificationManagerRepo) {
        this.notificationManagerRepo = notificationManagerRepo;
    }

    public void setNotificationManagerModel(NotificationManagerRepo notificationManagerRepo) {
        this.notificationManagerRepo = notificationManagerRepo;
    }

    public NotificationManagerRepo getNotificationManagerModel() {
        return notificationManagerRepo;
    }

    public void addNotification(NotificationService notificationService) {
        notificationManagerRepo.getqueue().add(notificationService);
    }

    public String getMessage(int orderId) {
        for (NotificationService notificationService : notificationManagerRepo.getqueue()) {
            if (notificationService.notificationModel.getOrderSerialNumberID() == orderId) {
                return notificationService.doSendNotifcation();
            }
        }
        return "No message found";
    }

    public void removeNotification(int orderId) {
        // remove the notification with the given order id from the queue
        for (Iterator<NotificationService> iterator = notificationManagerRepo.getqueue().iterator(); iterator
                .hasNext();) {
            NotificationService notificationService = iterator.next();
            if (notificationService.notificationModel.getOrderSerialNumberID() == orderId) {
                iterator.remove();
            }
        }
    }

    public void removeFirstNotification() {
        notificationManagerRepo.getqueue().remove();
    }

    public void removeAllNotification() {
        notificationManagerRepo.getqueue().clear();
    }

    public int getQueueSize() {
        return notificationManagerRepo.getqueue().size();
    }
}
