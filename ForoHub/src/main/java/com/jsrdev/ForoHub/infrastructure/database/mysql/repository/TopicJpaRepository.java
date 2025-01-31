package com.jsrdev.ForoHub.infrastructure.database.mysql.repository;

import com.jsrdev.ForoHub.infrastructure.database.mysql.entity.TopicEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicJpaRepository extends JpaRepository<TopicEntity, Long> {
    /*@Query("""
            SELECT t FROM Topic t
            JOIN FETCH t.author a
                JOIN FETCH a.profiles p
            JOIN FETCH t.course c
            WHERE t.active = true
            """)*/
    @EntityGraph(attributePaths = {"author", "author.profiles", "course"})
    Page<TopicEntity> findAllByActiveTrue(Pageable pagination);
}
