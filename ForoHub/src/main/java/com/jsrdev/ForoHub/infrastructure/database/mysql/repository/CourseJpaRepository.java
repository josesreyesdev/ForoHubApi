package com.jsrdev.ForoHub.infrastructure.database.mysql.repository;

import com.jsrdev.ForoHub.infrastructure.database.mysql.entity.CourseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseJpaRepository extends JpaRepository<CourseEntity, Long> {

    Page<CourseEntity> findByActiveTrue(Pageable pagination);

    Optional<CourseEntity> findByCourseId(String courseId);

    Optional<CourseEntity> findByCourseIdAndActiveTrue(String courseId);
}
