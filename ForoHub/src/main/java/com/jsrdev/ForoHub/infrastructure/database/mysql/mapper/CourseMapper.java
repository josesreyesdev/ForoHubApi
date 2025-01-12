package com.jsrdev.ForoHub.infrastructure.database.mysql.mapper;

import com.jsrdev.ForoHub.domain.model.Course;
import com.jsrdev.ForoHub.infrastructure.database.mysql.entity.CourseEntity;

public class CourseMapper {

    public static CourseEntity fromCourseToCourseEntity(Course course) {
        return new CourseEntity(course.getCourseId(), course.getName(), course.getCategory());
    }

    public static Course fromCourseEntityToCourse(CourseEntity courseEntity) {
        return new Course(
                courseEntity.getActive(),
                courseEntity.getCategory(),
                courseEntity.getCourseId(),
                courseEntity.getName()
        );
    }
}
