package org.socialmedia.socialmediaapp.post.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.socialmedia.socialmediaapp.common.jwt.JwtService;
import org.socialmedia.socialmediaapp.common.model.dto.StatusMessage;
import org.socialmedia.socialmediaapp.post.exception.PostNotFound;
import org.socialmedia.socialmediaapp.post.model.dto.NewPostRequest;
import org.socialmedia.socialmediaapp.post.model.entity.Post;
import org.socialmedia.socialmediaapp.post.repository.LikeRepository;
import org.socialmedia.socialmediaapp.post.repository.PostRepository;
import org.socialmedia.socialmediaapp.post.service.PostService;
import org.socialmedia.socialmediaapp.user.model.entity.User;
import org.socialmedia.socialmediaapp.user.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.socialmedia.socialmediaapp.post.model.entity.Like;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final JwtService jwtService;
    private final LikeRepository likeRepository;

    @Override
    public void savePost(Post postToSave) {
        postRepository.save(postToSave);
    }

    @Override
    public StatusMessage createPost(NewPostRequest newPostRequest, String authHeader) {
        Post post = modelMapper.map(newPostRequest, Post.class);

        post.setCreatedAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        post.setLikeCount(0);
        post.setUser(userService.getUserByToken(authHeader));

        postRepository.save(post);
        return StatusMessage.builder()
                .message("Post created successfully")
                .build();
    }

    @Override
    public void likePost(Long postId, String authHeader) {
        Post post = postRepository.findById(postId).orElseThrow(PostNotFound::new);
        User user = userService.getUserByToken(authHeader);
        Like like = likeRepository.findByPostAndUser(post, user);

        manageLikeOnPost(like, post, user);

        postRepository.save(post);
    }

    @Override
    public Post findPostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(PostNotFound::new);
    }

    @Override
    public Page<Post> searchPosts(String keyword, Pageable pageable) {
        return postRepository.findByTitleContaining(keyword, pageable);
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post getPostById(Long postId) {
        return postRepository.findById(postId).orElseThrow(PostNotFound::new);
    }

    @Override
    public StatusMessage deletePost(Long postId, String token) {
        User user = userService.getUserByToken(token);

        Post postToDelete = postRepository.findById(postId)
                .orElseThrow(PostNotFound::new);

        verifyPostOwnership(user, postToDelete);

        return new StatusMessage("Post deleted successfully");
    }


    // This method is used to manage the like on a post by a user and update the post like count accordingly 
    private void manageLikeOnPost(Like like, Post post, User user) {
        if (like != null) {
            post.setLikeCount(post.getLikeCount() - 1);
            likeRepository.delete(like);
        } else {
            post.setLikeCount(post.getLikeCount() + 1);
            like = new Like();
            like.setPost(post);
            like.setUser(user);
            likeRepository.save(like);
        }
    }

    private void verifyPostOwnership(User user, Post post) {
        if (Objects.equals(user.getId(), post.getUser().getId())) {
            postRepository.delete(post);
        } else {
            throw new RuntimeException("Not the post owner");
        }
    }

}
