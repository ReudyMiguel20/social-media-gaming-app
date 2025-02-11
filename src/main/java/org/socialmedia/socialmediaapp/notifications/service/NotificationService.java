package org.socialmedia.socialmediaapp.notifications.service;

import org.socialmedia.socialmediaapp.notifications.model.entity.Notification;
import java.util.List;

public interface NotificationService {
    void createNotification(Long userId, String message);
    List<Notification> getNotifications(Long userId);
}
