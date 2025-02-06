package org.socialmedia.socialmediaapp.post.service.impl;

import lombok.RequiredArgsConstructor;
import org.socialmedia.socialmediaapp.common.model.dto.StatusMessage;
import org.socialmedia.socialmediaapp.post.model.dto.CommentRequest;
import org.socialmedia.socialmediaapp.post.model.entity.Comment;
import org.socialmedia.socialmediaapp.post.model.entity.Post;
import org.socialmedia.socialmediaapp.post.repository.CommentRepository;
import org.socialmedia.socialmediaapp.post.repository.PostRepository;
import org.socialmedia.socialmediaapp.post.service.CommentService;
import org.socialmedia.socialmediaapp.post.service.PostService;
import org.socialmedia.socialmediaapp.user.model.entity.User;
import org.socialmedia.socialmediaapp.user.repository.UserRepository;
import org.socialmedia.socialmediaapp.user.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostService postService;
    private final UserRepository userRepository;
    private final UserService userService;

    @Override
    public StatusMessage addComment(CommentRequest commentRequest, String token) {
        Post post = postService.findPostById(commentRequest.getPostId());
        User user = userService.getUserByToken(token);

        Comment userComment = Comment.builder()
                .post(post)
                .user(user)
                .content(commentRequest.getContent())
                .createdAt(LocalDateTime.now())
                .build();

        commentRepository.save(userComment);

        return StatusMessage.builder()
                .message("Comment added successfully")
                .build();
    }

    @Override
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    @Override
    public void updateComment(Long commentId, String comment) {

    }

    @Override
    public List<Comment> getCommentsByPostId(Long postId) {
        return commentRepository.findAllByPostId(postId);
    }
}
