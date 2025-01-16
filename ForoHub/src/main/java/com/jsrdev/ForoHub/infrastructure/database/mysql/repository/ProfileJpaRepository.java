package com.jsrdev.ForoHub.infrastructure.database.mysql.repository;

import com.jsrdev.ForoHub.infrastructure.database.mysql.entity.ProfileEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileJpaRepository extends JpaRepository<ProfileEntity, Long> {

    Page<ProfileEntity> findByActiveTrue(Pageable pagination);

    Optional<ProfileEntity> findByProfileIdAndActiveTrue(String profileId);
}
