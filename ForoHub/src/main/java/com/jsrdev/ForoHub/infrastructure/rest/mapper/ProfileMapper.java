package com.jsrdev.ForoHub.infrastructure.rest.mapper;

import com.jsrdev.ForoHub.domain.model.Profile;
import com.jsrdev.ForoHub.infrastructure.rest.dto.profile.ProfileRequest;
import com.jsrdev.ForoHub.infrastructure.rest.dto.profile.ProfileResponse;

public class ProfileMapper {
    public static ProfileResponse toResponse(Profile profile) {
        return new ProfileResponse(
                profile.getProfileId(),
                profile.getName()
        );
    }

    public static Profile toModel(ProfileRequest profileRequest) {
        return new Profile(
                profileRequest.profileId(),
                profileRequest.name()
        );
    }
}
