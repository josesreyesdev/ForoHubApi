package com.jsrdev.ForoHub.usecase.course;

import com.jsrdev.ForoHub.domain.model.Course;
import com.jsrdev.ForoHub.infrastructure.rest.dto.course.CourseRequest;
import com.jsrdev.ForoHub.infrastructure.rest.dto.course.UpdateCourse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICourse {

    Course save(CourseRequest courseRequest);

    Page<Course> findByActiveTrue(Pageable pagination);

    Course findByCourseId(String courseId);

    Course findByCourseIdAndActiveTrue(String courseId);

    Course update(Course course, UpdateCourse update);

    Boolean delete(Course course);
}
