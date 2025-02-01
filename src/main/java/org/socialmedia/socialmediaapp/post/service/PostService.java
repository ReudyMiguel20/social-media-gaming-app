package org.socialmedia.socialmediaapp.post.service;

import org.socialmedia.socialmediaapp.common.model.dto.StatusMessage;
import org.socialmedia.socialmediaapp.post.model.dto.NewPostRequest;
import org.socialmedia.socialmediaapp.post.model.entity.Post;

public interface PostService {

    void savePost(Post postToSave);
    StatusMessage createPost(NewPostRequest newPostRequest, String authHeader);
    void likePost(Long postId, String authHeader);
}
