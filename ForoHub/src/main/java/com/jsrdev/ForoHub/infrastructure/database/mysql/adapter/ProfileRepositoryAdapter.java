package com.jsrdev.ForoHub.infrastructure.database.mysql.adapter;

import com.jsrdev.ForoHub.domain.model.Profile;
import com.jsrdev.ForoHub.domain.repository.ProfileRepositoryPort;
import com.jsrdev.ForoHub.infrastructure.database.mysql.entity.ProfileEntity;
import com.jsrdev.ForoHub.infrastructure.database.mysql.mapper.ProfileEntityMapper;
import com.jsrdev.ForoHub.infrastructure.database.mysql.repository.ProfileJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProfileRepositoryAdapter implements ProfileRepositoryPort {

    private final ProfileJpaRepository profileJpaRepository;

    public ProfileRepositoryAdapter(ProfileJpaRepository profileJpaRepository) {
        this.profileJpaRepository = profileJpaRepository;
    }

    @Override
    public Profile create(Profile profile) {
        ProfileEntity profileEntity = profileJpaRepository
                .save(ProfileEntityMapper.toEntity(profile));
        return ProfileEntityMapper.toModel(profileEntity);
    }

    @Override
    public Page<Profile> findByActiveTrue(Pageable pagination) {
        Page<ProfileEntity> profileEntityPage = profileJpaRepository
                .findByActiveTrue(pagination);

        List<Profile> profiles = profileEntityPage
                .getContent()
                .stream()
                .map(ProfileEntityMapper::toModel)
                .toList();

        return new PageImpl<>(
                profiles,
                pagination,
                profileEntityPage.getTotalElements()
        );
    }

    @Override
    public Profile findByProfileIdAndActiveTrue(String profileId) {
        return profileJpaRepository.findByProfileIdAndActiveTrue(profileId)
                .map(ProfileEntityMapper::toModel).orElse(null);
    }

    @Override
    public Profile update(Profile update) {
        ProfileEntity profileEntity = findByProfileId(update.getProfileId());
        profileEntity.update(update.getName());

        return ProfileEntityMapper.toModel(profileEntity);
    }

    @Override
    public Boolean delete(String profileId) {
        ProfileEntity profileEntity = findByProfileId(profileId);
        return profileEntity.delete();
    }

    private ProfileEntity findByProfileId(String profileId) {
        Optional<ProfileEntity> optionalProfileEntity = profileJpaRepository
                .findByProfileId(profileId);

        return optionalProfileEntity.orElse(null);
    }
}
