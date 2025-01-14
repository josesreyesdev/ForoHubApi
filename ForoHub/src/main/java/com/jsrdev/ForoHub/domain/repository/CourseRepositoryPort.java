package com.jsrdev.ForoHub.domain.repository;

import com.jsrdev.ForoHub.domain.model.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CourseRepositoryPort {

    Course save(Course course);

    Page<Course> findByActiveTrue(Pageable pagination);

    Course findByCourseId(String courseId);

    Course findByCourseIdAndActiveTrue(String courseId);

    Course update(Course update);
}
