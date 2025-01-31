package org.socialmedia.socialmediaapp.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.socialmedia.socialmediaapp.auth.model.dto.AuthenticationToken;
import org.socialmedia.socialmediaapp.user.model.dto.PasswordChangeRequest;
import org.socialmedia.socialmediaapp.user.model.dto.UserUpdateRequest;
import org.socialmedia.socialmediaapp.user.model.entity.User;
import org.socialmedia.socialmediaapp.user.model.enums.Role;
import org.socialmedia.socialmediaapp.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final UserService userService;

    @GetMapping("/details")
    @ResponseStatus(HttpStatus.OK)
    public Long getUserByEmail(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7);
        return userService.getUserById(token);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public AuthenticationToken updateUser(@Valid @RequestBody UserUpdateRequest userUpdateRequest, @RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7);
        return userService.updateUser(userUpdateRequest, token);
    }

    // This one uses cookies
//    @PutMapping("/update")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void updateUser(@Valid @RequestBody UserUpdateRequest userUpdateRequest, HttpSession session) {
//        userService.updateUser(userUpdateRequest, session);
//    }

    @GetMapping("/info")
    @ResponseStatus(HttpStatus.OK)
    public User getUserInfo(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7);
        return userService.getUserInfo(token);
    }

    @PutMapping("/change-password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changePassword(@RequestHeader("Authorization") String authHeader, @Valid @RequestBody PasswordChangeRequest passwordChangeRequest) {
        String token = authHeader.substring(7);

        userService.changePassword(token, passwordChangeRequest);
    }


    @GetMapping("/id")
    @ResponseStatus(HttpStatus.OK)
    public Long getUserId(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7);

        return userService.getUserIdFromToken(token);
    }

//    @GetMapping("/getUserId")
//    public ResponseEntity<Long> getUserId(HttpSession session) {
//        Long userId = (Long) session.getAttribute("userId");
//
//        if (userId == null) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
//        return ResponseEntity.ok(userId);
//    }

    @GetMapping("/role")
    @ResponseStatus(HttpStatus.OK)
    public Role getUserRole(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7);
        return userService.getUserRole(token);
    }
}
