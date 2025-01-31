package org.socialmedia.socialmediaapp.user.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonPropertyOrder({"old_password", "new_password"})
public class PasswordChangeRequest {

    @JsonProperty("old_password")
    @NotNull
    @NotBlank
    private String oldPassword;

    @JsonProperty("new_password")
    @NotNull
    @NotBlank
    private String newPassword;

}

