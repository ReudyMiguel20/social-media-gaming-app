package org.socialmedia.socialmediaapp.post.model.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.socialmedia.socialmediaapp.user.model.entity.User;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDateTime createdAt;
}
