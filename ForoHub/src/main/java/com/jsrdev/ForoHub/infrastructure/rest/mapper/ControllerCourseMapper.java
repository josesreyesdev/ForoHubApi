package com.jsrdev.ForoHub.infrastructure.rest.mapper;

import com.jsrdev.ForoHub.domain.model.Course;
import com.jsrdev.ForoHub.infrastructure.rest.dto.CourseResponse;

public class ControllerCourseMapper {

    public static CourseResponse fromCourseToCourseResponse(Course course) {
        return new CourseResponse(
                course.getCourseId(),
                course.getName(),
                course.getCategory()
        );
    }
}
