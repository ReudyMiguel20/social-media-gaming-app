package org.socialmedia.socialmediaapp.post.service;

import org.socialmedia.socialmediaapp.common.model.dto.StatusMessage;
import org.socialmedia.socialmediaapp.post.model.dto.NewPostRequest;
import org.socialmedia.socialmediaapp.post.model.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface PostService {

    void savePost(Post postToSave);
    StatusMessage createPost(NewPostRequest newPostRequest, String authHeader);
    void likePost(Long postId, String authHeader);

    Post findPostById(Long id);

    Page<Post> searchPosts(String keyword, Pageable pageable);
}
