package com.jsrdev.ForoHub.infrastructure.database.mysql.adapter;

import com.jsrdev.ForoHub.domain.model.Course;
import com.jsrdev.ForoHub.domain.repository.CourseRepositoryPort;
import com.jsrdev.ForoHub.infrastructure.database.mysql.entity.CourseEntity;
import com.jsrdev.ForoHub.infrastructure.database.mysql.mapper.CourseMapper;
import com.jsrdev.ForoHub.infrastructure.database.mysql.repository.CourseJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

//@RequiredArgsConstructor
@Component
public class CourseRepositoryAdapter implements CourseRepositoryPort {

    private final CourseJpaRepository courseJpaRepository;

    public CourseRepositoryAdapter(CourseJpaRepository courseJpaRepository) {
        this.courseJpaRepository = courseJpaRepository;
    }

    @Override
    public Course save(Course course) {
        CourseEntity courseEntity = courseJpaRepository
                .save(CourseMapper.fromCourseToCourseEntity(course));
        return CourseMapper.fromCourseEntityToCourse(courseEntity);
    }

    @Override
    public Page<Course> findByActiveTrue(Pageable pagination) {

        Page<CourseEntity> courseEntityPage = courseJpaRepository.findByActiveTrue(pagination);

        List<Course> courses = courseEntityPage
                .getContent()
                .stream()
                .map(CourseMapper::fromCourseEntityToCourse)
                .toList();

        return new PageImpl<>(
                courses,
                pagination,
                courseEntityPage.getTotalElements()
        );
    }

    @Override
    public Course findByCourseId(String courseId) {
        Optional<CourseEntity> courseEntity = courseJpaRepository.findByCourseId(courseId);
        return courseEntity.map(CourseMapper::fromCourseEntityToCourse).orElse(null);
    }

    @Override
    public Course findByCourseIdAndActiveTrue(String courseId) {
        return courseJpaRepository.findByCourseIdAndActiveTrue(courseId)
                .map(CourseMapper::fromCourseEntityToCourse).orElse(null);
    }

    @Override
    public Course update(Course update) {
        Optional<CourseEntity> optionalCourse = courseJpaRepository.findByCourseId(update.getCourseId());

        if (optionalCourse.isEmpty()) { return null; }

        CourseEntity courseEntity = optionalCourse.get();
        courseEntity.update(update.getName(), update.getCategory());
        return CourseMapper.fromCourseEntityToCourse(courseEntity);
    }
}
