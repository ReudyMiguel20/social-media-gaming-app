package org.socialmedia.socialmediaapp.user.service;

import org.socialmedia.socialmediaapp.common.model.dto.StatusMessage;
import org.socialmedia.socialmediaapp.user.model.dto.FollowNewUserDTO;

public interface FollowerService {

    StatusMessage followUser(FollowNewUserDTO followNewUserDTO, String userToken);
}
