package org.socialmedia.socialmediaapp.user.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonPropertyOrder({
        "first_name", "last_name", "email", "password", "phone_number",
        "address"
})
public class UserRegistrationDto {

    private String email;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    private String password;

    @JsonProperty("phone_number")
    private String phoneNumber;

    private String address;
}

