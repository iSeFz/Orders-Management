package com.example.ordermanagement.service;
import com.example.ordermanagement.model.*;

import java.util.Queue;

public class NotificationManagerService {
    NotificationManagerModel notificationManagerModel;
    public NotificationManagerService() {
        notificationManagerModel = new NotificationManagerModel();
    }
    public void setNotificationManagerModel(NotificationManagerModel notificationManagerModel) {
        this.notificationManagerModel = notificationManagerModel;
    }
    public NotificationManagerModel getNotificationManagerModel() {
        return notificationManagerModel;
    }
    public void addNotification(NotificationService notificationService) {
        notificationManagerModel.getqueue().add(notificationService);
    }
    public String getMessage(int orderId) {
        for (NotificationService notificationService : notificationManagerModel.getqueue()) {
            if (notificationService.notificationModel.getOrderSerialNumberID() == orderId) {
                return notificationService.doSendNotifcation();
            }
        }
        return "No message found";
    }
    public void removeNotification(int orderId) {
        for (NotificationService notificationService : notificationManagerModel.getqueue()) {
            if (notificationService.notificationModel.getOrderSerialNumberID() == orderId) {
                notificationManagerModel.getqueue().remove(notificationService);
            }
        }
    }
    public void removeFirstNotification() {
        notificationManagerModel.getqueue().remove();
    }
    public void removeAllNotification() {
        notificationManagerModel.getqueue().clear();
    }
    public int getQueueSize() {
        return notificationManagerModel.getqueue().size();
    }

}
