package com.jsrdev.ForoHub.infrastructure.database.mysql.mapper;

import com.jsrdev.ForoHub.domain.model.Profile;
import com.jsrdev.ForoHub.infrastructure.database.mysql.entity.ProfileEntity;

public class ProfileEntityMapper {
    public static ProfileEntity toEntity(Profile profile) {
        return new ProfileEntity(
                profile.getName(),
                profile.getProfileId(),
                profile.getActive()
        );
    }

    public static Profile toModel(ProfileEntity profileEntity) {
        return new Profile(
                profileEntity.getProfileId(),
                profileEntity.getName(),
                profileEntity.getActive()
        );
    }
}
