package org.socialmedia.socialmediaapp.user.service;

import jakarta.servlet.http.HttpSession;
import org.socialmedia.socialmediaapp.auth.model.dto.AuthenticationToken;
import org.socialmedia.socialmediaapp.user.model.dto.PasswordChangeRequest;
import org.socialmedia.socialmediaapp.user.model.dto.UserRegistrationDto;
import org.socialmedia.socialmediaapp.user.model.dto.UserUpdateRequest;
import org.socialmedia.socialmediaapp.user.model.entity.User;
import org.socialmedia.socialmediaapp.user.model.enums.Role;

import java.util.List;
import java.util.Optional;

public interface UserService {


    void saveUser(User user);

    void assignRoleToUser(User user, boolean isFirstUser);

    User createNewUser(UserRegistrationDto userRegistrationDto);

    List<User> getAllUsers();

    User getUserByEmail(String username);

    void verifyPassword(User user, String password);

//    void updateUser(UserUpdateRequest userUpdateRequest, HttpSession session);

    void changePassword(String token, PasswordChangeRequest passwordChangeRequest);

    AuthenticationToken updateUser(UserUpdateRequest request, String token);

    User getUserById(HttpSession session);

    Long getUserById(String token);

    User getUserById(Long id);

    User getUserByToken(String token);

    User getUserInfo(String token);

    boolean isAdmin(User user);

    boolean isUser(User user);

    Long getUserIdFromToken(String token);

    Role getUserRole(String token);
}
