package org.socialmedia.socialmediaapp.user.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequest {

    @NotNull
    @NotBlank
    private String email;

    @JsonProperty("first_name")
    @NotNull
    @NotBlank
    private String firstName;

    @JsonProperty("last_name")
    @NotNull
    @NotBlank
    private String lastName;

    @JsonProperty("phone_number")
    @NotNull
    @NotBlank
    private String phoneNumber;

    @NotNull
    @NotBlank
    private String address;
}
