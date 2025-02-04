package com.jsrdev.ForoHub.infrastructure.database.mysql.repository;

import com.jsrdev.ForoHub.infrastructure.database.mysql.entity.ReplyEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReplyJpaRepository extends JpaRepository<ReplyEntity, Long> {
    @EntityGraph(attributePaths = {"topic", "topic.author", "topic.author.profiles", "topic.course", "author", "author.profiles"})
    Page<ReplyEntity> findAllByActiveTrue(Pageable pagination);

    @EntityGraph(attributePaths = {"topic", "topic.author", "topic.author.profiles", "topic.course", "author", "author.profiles"})
    Optional<ReplyEntity> findByReplyIdAndActiveTrue(String replyId);
}
