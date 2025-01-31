package org.socialmedia.socialmediaapp.post.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.socialmedia.socialmediaapp.post.model.enums.PostType;
import org.socialmedia.socialmediaapp.user.model.entity.User;

import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private String url;

    @Enumerated(EnumType.STRING)
    private PostType postType;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @ManyToOne
    private User user;

}
