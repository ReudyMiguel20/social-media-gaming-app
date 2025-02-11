package org.socialmedia.socialmediaapp.notifications.service.impl;

import lombok.RequiredArgsConstructor;
import org.socialmedia.socialmediaapp.notifications.model.entity.Notification;
import org.socialmedia.socialmediaapp.notifications.repository.NotificationRepository;
import org.socialmedia.socialmediaapp.notifications.service.NotificationService;
import org.socialmedia.socialmediaapp.user.model.entity.User;
import org.socialmedia.socialmediaapp.user.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserService userService;


    @Override
    public void createNotification(Long userId, String message) {
        User user = userService.getUserById(userId);
        Notification notification = new Notification(user, message);
        notificationRepository.save(notification);
    }

    @Override
    public List<Notification> getNotifications(Long userId) {
        return notificationRepository.findByUserId(userId);
    }
}
