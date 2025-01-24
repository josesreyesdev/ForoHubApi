package com.jsrdev.ForoHub.infrastructure.database.mysql.mapper;

import com.jsrdev.ForoHub.domain.model.Course;
import com.jsrdev.ForoHub.infrastructure.database.mysql.entity.CourseEntity;

public class CourseEntityMapper {

    public static CourseEntity toEntity(Course course) {
        return new CourseEntity(
                course.getCourseId(),
                course.getName(),
                course.getCategory()
        );
    }

    public static Course toModel(CourseEntity courseEntity) {
        return new Course(
                courseEntity.getCourseId(),
                courseEntity.getName(),
                courseEntity.getCategory()
        );
    }
}
