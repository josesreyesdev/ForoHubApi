package com.jsrdev.ForoHub.usecase.course;

import com.jsrdev.ForoHub.domain.model.Course;
import com.jsrdev.ForoHub.domain.repository.CourseRepositoryPort;
import com.jsrdev.ForoHub.infrastructure.rest.dto.course.CourseRequest;
import com.jsrdev.ForoHub.infrastructure.rest.dto.course.UpdateCourse;
import com.jsrdev.ForoHub.infrastructure.rest.mapper.CourseMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor
public class CourseInteractor implements ICourse {

    private final CourseRepositoryPort courseRepositoryPort;

    public CourseInteractor(CourseRepositoryPort courseRepositoryPort) {
        this.courseRepositoryPort = courseRepositoryPort;
    }

    @Override
    public Course save(CourseRequest courseRequest) {
        Course course = CourseMapper.toModel(courseRequest);
        return courseRepositoryPort.save(course);
    }

    @Override
    public Page<Course> findByActiveTrue(Pageable pagination) {
        return courseRepositoryPort.findByActiveTrue(pagination);
    }

    @Override
    public Course findByCourseId(String courseId) {
        return courseRepositoryPort.findByCourseId(courseId);
    }

    @Override
    public Course findByCourseIdAndActiveTrue(String courseId) {
        return courseRepositoryPort.findByCourseIdAndActiveTrue(courseId);
    }

    @Override
    public Course update(Course course, UpdateCourse update) {
        course.update(course, update.name(), update.category());
        return courseRepositoryPort.update(course);
    }

    @Override
    public Boolean delete(Course course) {
        course.delete(course);
        return courseRepositoryPort.delete(course.getCourseId());
    }
}
