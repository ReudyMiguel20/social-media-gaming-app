package org.socialmedia.socialmediaapp.auth.service;

import jakarta.servlet.http.HttpSession;
import org.socialmedia.socialmediaapp.auth.model.dto.AuthenticationToken;
import org.socialmedia.socialmediaapp.auth.model.dto.LoginRequest;
import org.socialmedia.socialmediaapp.user.model.dto.UserRegistrationDto;

public interface AuthenticationService {

    AuthenticationToken register(UserRegistrationDto request);

    AuthenticationToken generateToken(LoginRequest request, HttpSession session);

}
