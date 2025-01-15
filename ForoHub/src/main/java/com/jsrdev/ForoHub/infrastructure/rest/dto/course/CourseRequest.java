package com.jsrdev.ForoHub.infrastructure.rest.dto.course;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jsrdev.ForoHub.common.CourseCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CourseRequest(
        @JsonAlias({"courseId", "course_id"})
        @NotBlank(message = "CourseId is required")
        String courseId,
        @NotBlank(message = "Name is required")
        String name,
        @NotNull(message = "Category is required")
        @JsonDeserialize(using = CourseCategoryDeserializer.class)
        CourseCategory category
) {
}
