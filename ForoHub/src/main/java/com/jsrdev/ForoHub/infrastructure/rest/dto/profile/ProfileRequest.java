package com.jsrdev.ForoHub.infrastructure.rest.dto.profile;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;

public record ProfileRequest(
        @JsonAlias({"courseId", "course_id"})
        @NotBlank(message = "CourseId is required")
        String profileId,
        @NotBlank(message = "Name is required")
        String name
) { }
