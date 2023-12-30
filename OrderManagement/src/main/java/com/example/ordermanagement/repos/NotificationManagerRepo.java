package com.example.ordermanagement.repos;
import com.example.ordermanagement.service.NotificationService;
import java.util.Queue;
import org.springframework.stereotype.Repository;
@Repository
public class NotificationManagerRepo {
    Queue<NotificationService> queue;
    public NotificationManagerRepo() {
        queue = new java.util.LinkedList<>();
    }
    public void setqueue(Queue<NotificationService> queue) {
        this.queue = queue;
    }
    public Queue<NotificationService> getqueue() {
        return queue;
    }
}
