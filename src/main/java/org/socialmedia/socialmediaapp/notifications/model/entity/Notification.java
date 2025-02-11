package org.socialmedia.socialmediaapp.notifications.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.socialmedia.socialmediaapp.user.model.entity.User;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String message;
    private boolean isRead;
    private LocalDateTime createdAt;

    public Notification(User user, String message) {
        this.user = user;
        this.message = message;
        this.isRead = false;
        this.createdAt = LocalDateTime.now();
    }

}
