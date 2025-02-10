package org.socialmedia.socialmediaapp.user.service.impl;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.socialmedia.socialmediaapp.auth.model.dto.AuthenticationToken;
import org.socialmedia.socialmediaapp.common.jwt.JwtService;
import org.socialmedia.socialmediaapp.user.exception.IncorrectPassword;
import org.socialmedia.socialmediaapp.user.exception.UserNotFound;
import org.socialmedia.socialmediaapp.user.model.dto.PasswordChangeRequest;
import org.socialmedia.socialmediaapp.user.model.dto.UserRegistrationDto;
import org.socialmedia.socialmediaapp.user.model.dto.UserUpdateRequest;
import org.socialmedia.socialmediaapp.user.model.entity.User;
import org.socialmedia.socialmediaapp.user.model.enums.Role;
import org.socialmedia.socialmediaapp.user.repository.UserRepository;
import org.socialmedia.socialmediaapp.user.service.UserProfileService;
import org.socialmedia.socialmediaapp.user.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserProfileService userProfileService;

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    private User mapRequestToUser(UserRegistrationDto userRegistrationDto) {
        return modelMapper.map(userRegistrationDto, User.class);
    }

    private void encodeUserPassword(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }

    @Override
    public void assignRoleToUser(User user, boolean isFirstUser) {
        if (isFirstUser) {
            user.setRole(Role.ADMIN);
            user.setEnabled(true);
        } else {
            user.setRole(Role.USER);
            user.setEnabled(true);
        }
    }

    @Override
    public User createNewUser(UserRegistrationDto userRegistrationDto) {
        User user = mapRequestToUser(userRegistrationDto);
        encodeUserPassword(user);
        boolean isFirstUser = getAllUsers().isEmpty();
        assignRoleToUser(user, isFirstUser);

        saveUser(user);

        userProfileService.initiateUserProfile(user);

        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByEmail(String username) {
        return userRepository.findByEmail(username)
                .orElseThrow(UserNotFound::new);
    }

    @Override
    public void verifyPassword(User user, String password) {
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IncorrectPassword();
        }
    }

    private void updatePassword(User user, String newPassword) {
        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedPassword);
        saveUser(user);
    }

    private void verifyPasswordChangeRequest(PasswordChangeRequest passwordChangeRequest) {
        if (passwordChangeRequest.getNewPassword().equals(passwordChangeRequest.getOldPassword())) {
            // Change this for a better descriptive exception
            throw new RuntimeException();
        }
    }

    @Override
    public void changePassword(String token, PasswordChangeRequest passwordChangeRequest) {
        User user = getUserByToken(token);

        verifyPasswordChangeRequest(passwordChangeRequest);

        verifyPassword(user, passwordChangeRequest.getOldPassword());

        updatePassword(user, passwordChangeRequest.getNewPassword());
    }


    @Override
    public AuthenticationToken updateUser(UserUpdateRequest request, String token) {
        User user = getUserByToken(token);

        modifyUserInfo(user, request);

        saveUser(user);

        String jwtToken = jwtService.generateToken(user);

        return AuthenticationToken.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public User getUserById(HttpSession session) {
        Long id = (Long) session.getAttribute("userId");

        return userRepository.findById(id)
                .orElseThrow(UserNotFound::new);
    }

    @Override
    public Long getUserById(String token) {
        User userToFind = getUserByToken(token);

        return userToFind.getId();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(UserNotFound::new);
    }

    private void modifyUserInfo(User user, UserUpdateRequest request) {
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setAddress(request.getAddress());
    }

    @Override
    public User getUserByToken(String token) {
        token = token.substring(7);

        String email = jwtService.extractUsername(token);
        return getUserByEmail(email);
    }

    @Override
    public User getUserInfo(String token) {
        String email = jwtService.extractUsername(token);
        return userRepository.findByEmail(email)
                .orElseThrow(UserNotFound::new);
    }

    @Override
    public boolean isAdmin(User user) {
        return user.getRole().equals(Role.ADMIN);
    }

    @Override
    public boolean isUser(User user) {
        return user.getRole().equals(Role.USER);
    }

    @Override
    public Long getUserIdFromToken(String token) {
        User userToGetId = getUserInfo(token);

        return userToGetId.getId();
    }

    @Override
    public Role getUserRole(String token) {
        User user = getUserByToken(token);
        return user.getRole();
    }

}
