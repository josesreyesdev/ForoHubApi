package com.jsrdev.ForoHub.infrastructure.database.mysql.adapter;

import com.jsrdev.ForoHub.domain.model.Profile;
import com.jsrdev.ForoHub.domain.repository.ProfileRepositoryPort;
import com.jsrdev.ForoHub.infrastructure.database.mysql.entity.ProfileEntity;
import com.jsrdev.ForoHub.infrastructure.database.mysql.mapper.ProfileMapper;
import com.jsrdev.ForoHub.infrastructure.database.mysql.repository.ProfileJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

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

    @Override
    public Page<Profile> findByActiveTrue(Pageable pagination) {
        Page<ProfileEntity> profileEntityPage = profileJpaRepository.findByActiveTrue(pagination);

        List<Profile> profiles = profileEntityPage
                .getContent()
                .stream()
                .map(ProfileMapper::fromProfileEntityToProfile)
                .toList();

        return new PageImpl<>(
                profiles,
                pagination,
                profileEntityPage.getTotalElements()
        );
    }

}
