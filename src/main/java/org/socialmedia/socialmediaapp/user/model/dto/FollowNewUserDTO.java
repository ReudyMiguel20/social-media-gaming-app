package org.socialmedia.socialmediaapp.user.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FollowNewUserDTO {

    @JsonProperty("user_to_follow_id")
    private Long userToFollowId;

}
