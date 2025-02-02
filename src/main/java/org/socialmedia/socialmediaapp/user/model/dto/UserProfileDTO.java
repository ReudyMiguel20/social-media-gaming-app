package org.socialmedia.socialmediaapp.user.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class UserProfileDTO {
    private String bio;
    private String profilePictureUrl;
    private String favoriteGame;
    private String status;
    private String country;
}
