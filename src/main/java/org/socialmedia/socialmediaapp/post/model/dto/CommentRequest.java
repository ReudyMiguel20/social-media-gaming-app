package org.socialmedia.socialmediaapp.post.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CommentRequest {

    @JsonProperty("post_id")
    private Long postId;

    private String content;

}
