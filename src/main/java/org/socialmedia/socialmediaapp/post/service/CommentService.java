package org.socialmedia.socialmediaapp.post.service;

import org.socialmedia.socialmediaapp.common.model.dto.StatusMessage;
import org.socialmedia.socialmediaapp.post.model.dto.CommentRequest;
import org.socialmedia.socialmediaapp.post.model.entity.Comment;

import java.util.List;

public interface CommentService {
    StatusMessage addComment(CommentRequest commentRequest, String token);
    void deleteComment(Long commentId);
    void updateComment(Long commentId, String comment);
    List<Comment> getCommentsByPostId(Long postId);
}
