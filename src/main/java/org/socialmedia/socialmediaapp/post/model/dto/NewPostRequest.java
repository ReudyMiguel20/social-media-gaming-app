package org.socialmedia.socialmediaapp.post.model.dto;


import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.socialmedia.socialmediaapp.post.model.enums.PostStatus;
import org.socialmedia.socialmediaapp.post.model.enums.PostType;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewPostRequest {

    @Size(min = 2, max = 100) // Optional: Add validation constraints
    private String title;

    @Size(max = 1000) // Optional: Add validation constraints
    private String content;

    private String mediaUrl; // URL for media (image, video)

    private PostType postType; // Type of post (TEXT, IMAGE, VIDEO, LINK)

    private PostStatus postStatus; // Status of post (PUBLISHED, DRAFT, DELETED)

    @Builder.Default // Ensure tags is initialized as an empty set by default
    private Set<String> tags = new HashSet<>();

}
