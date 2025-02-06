package com.jsrdev.ForoHub.infrastructure.rest.dto.user;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public record UpdateUser(
        @JsonAlias({"userId", "user_id"})
        @NotBlank(message = "UserId is required")
        String userId,
        @Email(message = "Email must be a valid format.")
        String email,
        String password,
        @Size(min = 1, message = "Profiles list must contain at least one profile")
        List<String> profiles
) {
}
