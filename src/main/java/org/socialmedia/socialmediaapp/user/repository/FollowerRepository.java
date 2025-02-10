package org.socialmedia.socialmediaapp.user.repository;

import org.socialmedia.socialmediaapp.user.model.entity.Follower;
import org.socialmedia.socialmediaapp.user.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowerRepository extends JpaRepository<Follower, Long> {
    boolean existsByFollowerIdAndFollowedId(Long followerId, Long followedId);

    boolean existsByFollowedIdAndFollowerId(Long id, Long id1);
}
