package org.socialmedia.socialmediaapp.post.controller;

import lombok.RequiredArgsConstructor;
import org.socialmedia.socialmediaapp.common.model.dto.StatusMessage;
import org.socialmedia.socialmediaapp.post.model.dto.CommentRequest;
import org.socialmedia.socialmediaapp.post.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    @RequestMapping("/add")
    @ResponseStatus(HttpStatus.OK)
    public StatusMessage addUserCommentToPost(@RequestBody CommentRequest commentRequest, @RequestHeader("Authorization") String token) {
        return commentService.addComment(commentRequest, token);
    }
}
