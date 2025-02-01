package com.jsrdev.ForoHub.infrastructure.database.mysql.repository;

import com.jsrdev.ForoHub.infrastructure.database.mysql.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
    Page<UserEntity> findByActiveTrue(Pageable pagination);

    @Query("""
            SELECT u FROM User u
            JOIN FETCH u.profiles p
            WHERE u.active = true
            """)
    Page<UserEntity> findByActiveTrueWithProfiles(Pageable pagination);

    @Query("""
            SELECT u FROM User u
            JOIN FETCH u.profiles p
            WHERE u.userId = :userId
            AND u.active = true
            """)
    Optional<UserEntity> findByUserIdAndActiveTrueWithProfiles(String userId);

    Optional<UserEntity> findByUserId(String userId);
}
