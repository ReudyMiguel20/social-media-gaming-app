package org.socialmedia.socialmediaapp.auth.service.impl;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.socialmedia.socialmediaapp.auth.model.dto.AuthenticationToken;
import org.socialmedia.socialmediaapp.auth.model.dto.LoginRequest;
import org.socialmedia.socialmediaapp.auth.service.AuthenticationService;
import org.socialmedia.socialmediaapp.common.jwt.JwtService;
import org.socialmedia.socialmediaapp.user.model.dto.UserRegistrationDto;
import org.socialmedia.socialmediaapp.user.model.entity.User;
import org.socialmedia.socialmediaapp.user.service.UserService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserService userService;
    private final JwtService jwtService;

    @Override
    public AuthenticationToken register(UserRegistrationDto request) {
        User newUser = userService.createNewUser(request);

        String jwtToken = jwtService.generateToken(newUser);

        return AuthenticationToken.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public AuthenticationToken generateToken(LoginRequest request, HttpSession session) {
        User existingUser = userService.getUserByEmail(request.getEmail());
        userService.verifyPassword(existingUser, request.getPassword());

        //Save the user ID in the session
        session.setAttribute("userId", existingUser.getId());

        String jwtToken = jwtService.generateToken(existingUser);

        return AuthenticationToken.builder()
                .token(jwtToken)
                .build();
    }
}
