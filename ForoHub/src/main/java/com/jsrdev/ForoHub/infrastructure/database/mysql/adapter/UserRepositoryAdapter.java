package com.jsrdev.ForoHub.infrastructure.database.mysql.adapter;

import com.jsrdev.ForoHub.domain.model.Profile;
import com.jsrdev.ForoHub.domain.model.User;
import com.jsrdev.ForoHub.domain.repository.UserRepositoryPort;
import com.jsrdev.ForoHub.infrastructure.database.mysql.entity.ProfileEntity;
import com.jsrdev.ForoHub.infrastructure.database.mysql.entity.UserEntity;
import com.jsrdev.ForoHub.infrastructure.database.mysql.mapper.ProfileMapper;
import com.jsrdev.ForoHub.infrastructure.database.mysql.mapper.UserEntityMapper;
import com.jsrdev.ForoHub.infrastructure.database.mysql.repository.ProfileJpaRepository;
import com.jsrdev.ForoHub.infrastructure.database.mysql.repository.UserJpaRepository;
import com.jsrdev.ForoHub.infrastructure.exceptions.ProfileNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserRepositoryAdapter implements UserRepositoryPort {

    private final UserJpaRepository userJpaRepository;
    private final ProfileJpaRepository profileJpaRepository;

    public UserRepositoryAdapter(ProfileJpaRepository profileJpaRepository, UserJpaRepository userJpaRepository) {
        this.profileJpaRepository = profileJpaRepository;
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public User createUserWithProfiles(User user) {
        List<ProfileEntity> profileEntities = user.getProfiles().stream()
                .map(profile -> profileJpaRepository.findByProfileId(profile.getProfileId())
                        .orElseThrow(() -> new ProfileNotFoundException("Profile not found: " + profile.getProfileId())))
                .toList();

        UserEntity userEntity = UserEntityMapper.toEntity(user, profileEntities);

        UserEntity savedUserEntity = userJpaRepository.save(userEntity);

        List<Profile> savedProfiles = savedUserEntity.getProfiles().stream()
                .map(ProfileMapper::fromProfileEntityToProfile)
                .toList();

        return UserEntityMapper.toModel(savedUserEntity, savedProfiles);
    }

    @Override
    public List<Profile> validateAndFindProfiles(List<String> profileIds) {
        return profileIds.stream()
                .map(profileId -> profileJpaRepository.findByProfileId(profileId)
                        .orElseThrow(() -> new ProfileNotFoundException("Profile not found: " + profileId)))
                .map(ProfileMapper::fromProfileEntityToProfile)
                .toList();
    }

}
