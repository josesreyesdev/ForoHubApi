package com.jsrdev.ForoHub.infrastructure.rest.dto;

import com.jsrdev.ForoHub.common.CourseCategory;

public record CourseResponse(
        String courseId,
        String name,
        CourseCategory category
) {
}
