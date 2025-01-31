package org.socialmedia.socialmediaapp.post.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.URL;
import org.socialmedia.socialmediaapp.post.model.enums.PostStatus;
import org.socialmedia.socialmediaapp.post.model.enums.PostType;
import org.socialmedia.socialmediaapp.user.model.entity.User;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

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

    @Size(min = 2, max = 100)
    private String title;

    @Size(max = 1000)
    private String content;

    // This could be used for referencing an external URL
//    @URL
//    private String url;

    //  For Photo or Video in the post
    private String mediaUrl;

    @Enumerated(EnumType.STRING)
    private PostType postType;

    @Enumerated(EnumType.STRING)
    private PostStatus postStatus;

    @CreationTimestamp
    private LocalDateTime createdAt;

    private int likeCount;

    @ElementCollection
    @Builder.Default
    private Set<String> tags = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", mediaUrl='" + mediaUrl + '\'' +
                ", postType=" + postType +
                ", createdAt=" + createdAt +
                '}';
    }

}
