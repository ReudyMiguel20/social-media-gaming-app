package org.socialmedia.socialmediaapp.user.controller;


import lombok.RequiredArgsConstructor;
import org.socialmedia.socialmediaapp.common.model.dto.StatusMessage;
import org.socialmedia.socialmediaapp.user.model.dto.FollowNewUserDTO;
import org.socialmedia.socialmediaapp.user.service.FollowerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public StatusMessage unfollowUser(@RequestBody FollowNewUserDTO followNewUserDTO, @RequestHeader("Authorization") String authHeader) {
        return followerService.unfollowUser(followNewUserDTO, authHeader);
    }

    @GetMapping("/followers/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<String> getFollowers(@PathVariable Long userId) {
        return followerService.getFollowers(userId);
    }

    @GetMapping("/following/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<String> getFollowing(@PathVariable Long userId) {
        return followerService.getFollowing(userId);
    }

}
