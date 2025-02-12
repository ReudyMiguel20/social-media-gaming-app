package org.socialmedia.socialmediaapp.post.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.socialmedia.socialmediaapp.common.jwt.JwtService;
import org.socialmedia.socialmediaapp.common.model.dto.StatusMessage;
import org.socialmedia.socialmediaapp.post.model.dto.NewPostRequest;
import org.socialmedia.socialmediaapp.post.model.entity.Post;
import org.socialmedia.socialmediaapp.post.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
//    private final JwtService jwtService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public StatusMessage createPost(@RequestBody NewPostRequest newPostRequest, @RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7);

        return postService.createPost(newPostRequest, token);
    }


    @GetMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public Post getPostById(@PathVariable Long postId) {
        return postService.getPostById(postId);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

//    @PostMapping("/getUsername")
//    @ResponseStatus(HttpStatus.OK)
//    public String getUsername(@RequestHeader("Authorization") String authHeader) {
//        return jwtService.extractUsername(authHeader.substring(7));
//    }

    @PostMapping("/like/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public void likePost(@PathVariable Long postId, @RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7);
        postService.likePost(postId, token);
    }

    @DeleteMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public StatusMessage deletePost(@PathVariable Long postId, @RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7);
        return postService.deletePost(postId, token);
    }

//    @PutMapping("/update/{postId}")
//    @ResponseStatus(HttpStatus.OK)
//    public StatusMessage updatePost(@PathVariable Long postId, @RequestBody UpdatePostRequest updatePostRequest, @RequestHeader("Authorization") String authHeader) {
//        String token = authHeader.substring(7);
//        return postService.updatePost(postId, updatePostRequest, token);
//    }





}
