package org.socialmedia.socialmediaapp.auth.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.socialmedia.socialmediaapp.auth.model.dto.AuthenticationToken;
import org.socialmedia.socialmediaapp.auth.model.dto.LoginRequest;
import org.socialmedia.socialmediaapp.auth.service.AuthenticationService;
import org.socialmedia.socialmediaapp.auth.service.impl.AuthenticationServiceImpl;
import org.socialmedia.socialmediaapp.user.model.dto.UserRegistrationDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationServiceImpl authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationToken> registeringNewUser(@Valid @RequestBody UserRegistrationDto userRegistrationDto) {
        return ResponseEntity.ok(authenticationService.register(userRegistrationDto));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationToken> authenticatingExistingUser(@Valid @RequestBody LoginRequest request, HttpSession session) {
        return ResponseEntity.ok(authenticationService.generateToken(request, session));
    }
}
