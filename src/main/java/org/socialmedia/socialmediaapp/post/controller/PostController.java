package org.socialmedia.socialmediaapp.post.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.socialmedia.socialmediaapp.post.service.PostService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;


}
