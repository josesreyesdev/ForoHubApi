package com.jsrdev.ForoHub.infrastructure.database.mysql.adapter;

import com.jsrdev.ForoHub.domain.model.Profile;
import com.jsrdev.ForoHub.domain.repository.ProfileRepositoryPort;
import com.jsrdev.ForoHub.infrastructure.database.mysql.entity.ProfileEntity;
import com.jsrdev.ForoHub.infrastructure.database.mysql.mapper.ProfileMapper;
import com.jsrdev.ForoHub.infrastructure.database.mysql.repository.ProfileJpaRepository;
import org.springframework.stereotype.Component;

@Component
public class ProfileRepositoryAdapter implements ProfileRepositoryPort {

    private final ProfileJpaRepository profileJpaRepository;

    public ProfileRepositoryAdapter(ProfileJpaRepository profileJpaRepository) {
        this.profileJpaRepository = profileJpaRepository;
    }

    @Override
    public Profile create(Profile profile) {
        ProfileEntity profileEntity = profileJpaRepository
                .save(ProfileMapper.fromProfileToProfileEntity(profile));
        return ProfileMapper.fromProfileEntityToProfile(profileEntity);
    }
}
