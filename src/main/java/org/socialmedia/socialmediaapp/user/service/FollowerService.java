package org.socialmedia.socialmediaapp.user.service;

import org.socialmedia.socialmediaapp.common.model.dto.StatusMessage;
import org.socialmedia.socialmediaapp.user.model.dto.FollowNewUserDTO;
import org.socialmedia.socialmediaapp.user.model.entity.Follower;

import java.util.List;

public interface FollowerService {

    StatusMessage followUser(FollowNewUserDTO followNewUserDTO, String userToken);

    List<String> getFollowing(Long userId);

    List<String> getFollowers(Long userId);

    StatusMessage unfollowUser(FollowNewUserDTO followNewUserDTO, String authHeader);
}
