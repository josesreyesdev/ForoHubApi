package com.jsrdev.ForoHub.infrastructure.database.mysql.adapter;

import com.jsrdev.ForoHub.domain.model.Profile;
import com.jsrdev.ForoHub.domain.model.User;
import com.jsrdev.ForoHub.domain.repository.UserRepositoryPort;
import com.jsrdev.ForoHub.infrastructure.database.mysql.entity.ProfileEntity;
import com.jsrdev.ForoHub.infrastructure.database.mysql.entity.UserEntity;
import com.jsrdev.ForoHub.infrastructure.database.mysql.mapper.ProfileEntityMapper;
import com.jsrdev.ForoHub.infrastructure.database.mysql.mapper.UserEntityMapper;
import com.jsrdev.ForoHub.infrastructure.database.mysql.repository.ProfileJpaRepository;
import com.jsrdev.ForoHub.infrastructure.database.mysql.repository.UserJpaRepository;
import com.jsrdev.ForoHub.infrastructure.exceptions.ProfileNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
        List<String> invalidProfileIds = new ArrayList<>();

        List<ProfileEntity> profileEntities = user.getProfiles().stream()
                .map(profile -> profileJpaRepository.findByProfileId(profile.getProfileId())
                        .orElseGet(() -> {
                            invalidProfileIds.add(profile.getProfileId());
                            return null;
                        }))
                .filter(Objects::nonNull) // Filter valid profiles
                .toList();

        if (!invalidProfileIds.isEmpty()) {
            throw new ProfileNotFoundException("Profiles not found or inactive: " + String.join(", ", invalidProfileIds));
        }

        UserEntity userEntity = UserEntityMapper.toEntity(user, profileEntities);

        UserEntity savedUserEntity = userJpaRepository.save(userEntity);

        return UserEntityMapper.toModel(savedUserEntity);
    }

    @Override
    public List<Profile> validateAndFindProfiles(List<String> profileIds) {
        List<String> invalidProfileIds = new ArrayList<>();

        List<Profile> profiles = profileIds.stream()
                .map(profileId -> profileJpaRepository.findByProfileIdAndActiveTrue(profileId)
                        .orElseGet(() -> {
                            invalidProfileIds.add(profileId);
                            return null;
                        }))
                .filter(Objects::nonNull) // Filter valid profiles
                .map(ProfileEntityMapper::toModel)
                .toList();

        if (!invalidProfileIds.isEmpty()) {
            throw new ProfileNotFoundException("Profile(s) not found or inactive: " + String.join(", ", invalidProfileIds));
        }

        return profiles;
    }

    @Override
    public Page<User> findByActiveTrueWithProfiles(Pageable pagination) {
        Page<UserEntity> userEntityPage = userJpaRepository
                .findByActiveTrueWithProfiles(pagination);

        List<User> users = userEntityPage.getContent().stream()
                .map(UserEntityMapper::toModel)
                .toList();

        return new PageImpl<>(
                users,
                pagination,
                userEntityPage.getTotalElements()
        );
    }

    @Override
    public User findByUserIdAndActiveTrue(String userId) {
        Optional<UserEntity> optionalUserEntity = userJpaRepository
                .findByUserIdAndActiveTrueWithProfiles(userId);
        if (optionalUserEntity.isEmpty()) {
            throw new ProfileNotFoundException("Profile not found or inactive");
        }

        return UserEntityMapper.toModel(optionalUserEntity.get());
    }
}
