package org.socialmedia.socialmediaapp.user.controller;


import lombok.RequiredArgsConstructor;
import org.socialmedia.socialmediaapp.common.model.dto.StatusMessage;
import org.socialmedia.socialmediaapp.user.model.dto.FollowNewUserDTO;
import org.socialmedia.socialmediaapp.user.service.FollowerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/follow")
@CrossOrigin(origins = "http://localhost:3000")
public class FollowController {

    private final FollowerService followerService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public StatusMessage followUser(@RequestBody FollowNewUserDTO followNewUserDTO, @RequestHeader("Authorization") String authHeader) {

        return followerService.followUser(followNewUserDTO, authHeader);
    }
}
