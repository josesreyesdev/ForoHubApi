package com.jsrdev.ForoHub.usecase.course;

import com.jsrdev.ForoHub.domain.model.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICourse {

    Course save(Course course);

    Page<Course> findByActiveTrue(Pageable pagination);

    Course findByCourseId(String courseId);

    Course findByCourseIdAndActiveTrue(String courseId);

    Course update(Course update);
}
