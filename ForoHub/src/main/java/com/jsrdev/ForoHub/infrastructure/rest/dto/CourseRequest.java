package com.jsrdev.ForoHub.infrastructure.rest.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jsrdev.ForoHub.common.CourseCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CourseRequest(
        @NotBlank(message = "courseId is required")
        String courseId,
        @NotBlank(message = "name is required")
        String name,
        @NotNull(message = "Category is required")
        @JsonDeserialize(using = CourseCategoryDeserializer.class)
        CourseCategory category
) {
}
