package com.jsrdev.ForoHub.infrastructure.rest.mapper;

import com.jsrdev.ForoHub.domain.model.Profile;
import com.jsrdev.ForoHub.infrastructure.rest.dto.profile.ProfileResponse;

public class ControllerProfileMapper {
    public static ProfileResponse fromProfileToProfileResponse(Profile profile) {
        return new ProfileResponse(
                profile.getProfileId(),
                profile.getName()
        );
    }
}
