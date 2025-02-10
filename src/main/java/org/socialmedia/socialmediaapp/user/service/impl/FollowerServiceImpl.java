package org.socialmedia.socialmediaapp.user.service.impl;

import lombok.RequiredArgsConstructor;
import org.socialmedia.socialmediaapp.common.model.dto.StatusMessage;
import org.socialmedia.socialmediaapp.user.model.dto.FollowNewUserDTO;
import org.socialmedia.socialmediaapp.user.model.entity.Follower;
import org.socialmedia.socialmediaapp.user.model.entity.User;
import org.socialmedia.socialmediaapp.user.repository.FollowerRepository;
import org.socialmedia.socialmediaapp.user.service.FollowerService;
import org.socialmedia.socialmediaapp.user.service.UserService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FollowerServiceImpl implements FollowerService {

    private final FollowerRepository followerRepository;
    private final UserService userService;

    @Override
    public StatusMessage followUser(FollowNewUserDTO followNewUserDTO, String userToken) {
        User user = userService.getUserByToken(userToken);
        User userToFollow = userService.getUserById(followNewUserDTO.getUserToFollowId());

        // Checks that the user is not already following the specified user to follow
//        try {
//            isAlreadyFollowing(user, userToFollow);
//        } catch (IllegalArgumentException e) {
//            return new StatusMessage(e.getMessage());
//        }

//        if (followerRepository.existsByFollowedIdAndFollowerId(user.getId(), userToFollow.getId())) {
//            return new StatusMessage("User is already following the specified user");
//        }

        isAlreadyFollowing(user, userToFollow);

        Follower newFollower = new Follower(user, userToFollow);
        followerRepository.save(newFollower);

        return isAlreadyFollowing(user, userToFollow);
    }

    // If the user is already following the specified user, an IllegalArgumentException is thrown with a message
    // indicating that the user is already following the specified user
    private StatusMessage isAlreadyFollowing(User user, User userToFollow) {
        try {
            if (followerRepository.existsByFollowedIdAndFollowerId(userToFollow.getId(), user.getId())) {
                return new StatusMessage("User is already following the specified user");
            }

            Follower newFollower = new Follower(user, userToFollow);
            followerRepository.save(newFollower);
            return new StatusMessage("User followed successfully");
        } catch (DataIntegrityViolationException e) {
            return new StatusMessage("User is already following the specified user");
        }
    }

}
