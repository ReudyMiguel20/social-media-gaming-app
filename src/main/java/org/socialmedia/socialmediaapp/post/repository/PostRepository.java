package org.socialmedia.socialmediaapp.post.repository;

import org.socialmedia.socialmediaapp.post.model.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findByTitleContaining(String title, Pageable pageable);
//    List<Post> findByUsername(Long userId);

}
