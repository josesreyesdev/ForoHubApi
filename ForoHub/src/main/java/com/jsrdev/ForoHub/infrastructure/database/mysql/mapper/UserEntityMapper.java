package com.jsrdev.ForoHub.infrastructure.database.mysql.mapper;

import com.jsrdev.ForoHub.domain.model.Profile;
import com.jsrdev.ForoHub.domain.model.User;
import com.jsrdev.ForoHub.infrastructure.database.mysql.entity.ProfileEntity;
import com.jsrdev.ForoHub.infrastructure.database.mysql.entity.UserEntity;

import java.util.List;

public class UserEntityMapper {
    public static UserEntity toEntity(User user, List<ProfileEntity> profileEntityList) {
        return new UserEntity(
                user.getUserId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getActive(),
                profileEntityList
        );
    }

    public static User toModel(UserEntity userEntity) {
        return new User(
                userEntity.getUserId(),
                userEntity.getName(),
                userEntity.getEmail(),
                userEntity.getPassword(),
                getProfiles(userEntity.getProfiles()),
                userEntity.getActive()
        );
    }

    private static List<Profile> getProfiles(List<ProfileEntity> profilesEntity) {
        return profilesEntity.stream()
                .map(ProfileEntityMapper::toModel)
                .toList();
    }
}
