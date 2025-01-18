package com.jsrdev.ForoHub.infrastructure.rest.dto.user;

import java.util.List;

public record UserResponse(
        String userId,
        String name,
        String email,
        List<String> profiles
) {
}
