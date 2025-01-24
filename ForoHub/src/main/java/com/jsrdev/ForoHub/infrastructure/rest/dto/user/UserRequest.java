package com.jsrdev.ForoHub.infrastructure.rest.dto.user;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record UserRequest(
        @JsonAlias({"userId", "user_id"})
        @NotBlank(message = "UserId is required")
        String userId,
        @NotBlank(message = "Name is required")
        String name,
        @NotBlank(message = "Email is required")
        @Email(message = "Email must be a valid format.")
        String email,
        @NotBlank(message = "Password is required")
        String password,
        @NotNull(message = "Profiles list cannot be null")
        @Size(min = 1, message = "Profiles list must contain at least one profile")
        List<String> profiles
) {
}
