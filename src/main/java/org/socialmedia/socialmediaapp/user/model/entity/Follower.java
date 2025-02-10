package org.socialmedia.socialmediaapp.user.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "followers", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"follower_id", "followed_id"})
})
public class Follower {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "follower_id", nullable = false)
    private User follower;

    @ManyToOne
    @JoinColumn(name = "followed_id", nullable = false)
    private User followed;

    public Follower(User follower, User followed) {
        this.follower = follower;
        this.followed = followed;
    }
}
