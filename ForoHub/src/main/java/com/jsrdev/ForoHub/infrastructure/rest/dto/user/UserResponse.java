package com.jsrdev.ForoHub.infrastructure.rest.dto.user;

import com.jsrdev.ForoHub.infrastructure.rest.dto.profile.ProfileResponse;

import java.util.List;

public record UserResponse(
        String userId,
        String name,
        String email,
        List<ProfileResponse> profiles
) {
}
