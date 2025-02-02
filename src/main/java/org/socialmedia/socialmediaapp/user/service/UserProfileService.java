package org.socialmedia.socialmediaapp.user.service;

import org.socialmedia.socialmediaapp.common.model.dto.StatusMessage;
import org.socialmedia.socialmediaapp.user.model.dto.UserProfileDTO;
import org.socialmedia.socialmediaapp.user.model.entity.User;
import org.socialmedia.socialmediaapp.user.model.entity.UserProfile;

public interface UserProfileService {
    void initiateUserProfile(User user);
    UserProfile getUserProfile(Long userId);
    StatusMessage updateUserProfile(Long userId, UserProfileDTO userProfileDTO);
}
