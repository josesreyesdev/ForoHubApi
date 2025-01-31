package com.jsrdev.ForoHub.infrastructure.database.mysql.adapter;

import com.jsrdev.ForoHub.domain.model.Course;
import com.jsrdev.ForoHub.domain.repository.CourseRepositoryPort;
import com.jsrdev.ForoHub.infrastructure.database.mysql.entity.CourseEntity;
import com.jsrdev.ForoHub.infrastructure.database.mysql.mapper.CourseEntityMapper;
import com.jsrdev.ForoHub.infrastructure.database.mysql.repository.CourseJpaRepository;
import com.jsrdev.ForoHub.infrastructure.exceptions.ValidationIntegrity;
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
                .save(CourseEntityMapper.toEntity(course));
        return CourseEntityMapper.toModel(courseEntity);
    }

    @Override
    public Page<Course> findByActiveTrue(Pageable pagination) {

        Page<CourseEntity> courseEntityPage = courseJpaRepository.findByActiveTrue(pagination);

        List<Course> courses = courseEntityPage
                .getContent()
                .stream()
                .map(CourseEntityMapper::toModel)
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
        return courseEntity.map(CourseEntityMapper::toModel).orElse(null);
    }

    @Override
    public Course findByCourseIdAndActiveTrue(String courseId) {
        CourseEntity courseEntity = findByCourseIdAndActiveTrueEntity(courseId);

        return CourseEntityMapper.toModel(courseEntity);
    }

    @Override
    public Course update(Course update) {
        CourseEntity courseEntity = findByCourseIdAndActiveTrueEntity(update.getCourseId());
        courseEntity.update(update.getName(), update.getCategory());

        return CourseEntityMapper.toModel(courseEntity);
    }

    @Override
    public Boolean delete(String courseId) {
        Optional<CourseEntity> optionalCourse = courseJpaRepository.findByCourseId(courseId);
        if (optionalCourse.isEmpty()) {
            return false;
        }

        return optionalCourse.get().delete();
    }

    public CourseEntity findByCourseIdAndActiveTrueEntity(String courseId) {
        Optional<CourseEntity> optionalCourseEntity = courseJpaRepository
                .findByCourseIdAndActiveTrue(courseId);
        if (optionalCourseEntity.isEmpty()) {
            throw new ValidationIntegrity("Course not found or inactive: " + courseId);
        }
        return optionalCourseEntity.get();
    }
}
