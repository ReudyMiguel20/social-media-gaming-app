package org.socialmedia.socialmediaapp.user.service.impl;

import lombok.RequiredArgsConstructor;
import org.socialmedia.socialmediaapp.common.model.dto.StatusMessage;
import org.socialmedia.socialmediaapp.user.exception.UserNotFound;
import org.socialmedia.socialmediaapp.user.model.dto.UserProfileDTO;
import org.socialmedia.socialmediaapp.user.model.entity.User;
import org.socialmedia.socialmediaapp.user.model.entity.UserProfile;
import org.socialmedia.socialmediaapp.user.repository.UserProfileRepository;
import org.socialmedia.socialmediaapp.user.service.UserProfileService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProfileImpl implements UserProfileService {

    private final UserProfileRepository userProfileRepository;


    @Override
    public void initiateUserProfile(User user) {
        UserProfile userProfile = UserProfile.builder()
                .user(user)
                .build();

        userProfileRepository.save(userProfile);
    }

    @Override
    public UserProfile getUserProfile(Long userId) {
        return userProfileRepository.findByUserId(userId)
                .orElseThrow(UserNotFound::new);
    }

    @Override
    public StatusMessage updateUserProfile(Long userId, UserProfileDTO userProfileDTO) {
        UserProfile existingProfile = getUserProfile(userId);

        existingProfile.setBio(userProfileDTO.getBio());
        existingProfile.setCountry(userProfileDTO.getCountry());
        existingProfile.setFavoriteGame(userProfileDTO.getFavoriteGame());
        existingProfile.setProfilePictureUrl(userProfileDTO.getProfilePictureUrl());
        existingProfile.setStatus(userProfileDTO.getStatus());

        userProfileRepository.save(existingProfile);

        return StatusMessage.builder()
                .message("Profile updated successfully")
                .build();
    }
}
