package org.socialmedia.socialmediaapp.post.repository;

import org.socialmedia.socialmediaapp.post.model.entity.Like;
import org.socialmedia.socialmediaapp.post.model.entity.Post;
import org.socialmedia.socialmediaapp.user.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Like findByPostAndUser(Post post, User user);
}
