package com.jsrdev.ForoHub.infrastructure.rest.mapper;

import com.jsrdev.ForoHub.domain.model.Course;
import com.jsrdev.ForoHub.infrastructure.rest.dto.course.CourseResponse;

public class CourseMapper {

    public static CourseResponse toResponse(Course course) {
        return new CourseResponse(
                course.getCourseId(),
                course.getName(),
                course.getCategory()
        );
    }
}
