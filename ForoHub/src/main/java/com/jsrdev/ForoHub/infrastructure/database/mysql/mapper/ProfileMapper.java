package com.jsrdev.ForoHub.infrastructure.database.mysql.mapper;

import com.jsrdev.ForoHub.domain.model.Profile;
import com.jsrdev.ForoHub.infrastructure.database.mysql.entity.ProfileEntity;

public class ProfileMapper {
    public static ProfileEntity fromProfileToProfileEntity(Profile profile) {
        return new ProfileEntity(
                profile.getName(),
                profile.getProfileId(),
                profile.getActive()
        );
    }

    public static Profile fromProfileEntityToProfile(ProfileEntity profileEntity) {
        return new Profile(
                profileEntity.getProfileId(),
                profileEntity.getName(),
                profileEntity.getActive()
        );
    }
}
