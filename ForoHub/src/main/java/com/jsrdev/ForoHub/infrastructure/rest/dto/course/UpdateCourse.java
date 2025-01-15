package com.jsrdev.ForoHub.infrastructure.rest.dto.course;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jsrdev.ForoHub.common.CourseCategory;
import jakarta.validation.constraints.NotBlank;

public record UpdateCourse(
        @JsonAlias({"courseId", "course_id"})
        @NotBlank(message = "CourseId is required")
        String courseId,
        String name,
        @JsonDeserialize(using = CourseCategoryDeserializer.class)
        CourseCategory category
) {
}
