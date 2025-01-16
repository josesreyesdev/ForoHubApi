package com.jsrdev.ForoHub.infrastructure.rest.dto.profile;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;

public record UpdateProfile(
        @JsonAlias({"courseId", "course_id"})
        @NotBlank(message = "CourseId is required")
        String profileId,
        String name
) {
}
