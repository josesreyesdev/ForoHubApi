package com.jsrdev.ForoHub.usecase.profile;

import com.jsrdev.ForoHub.domain.model.Profile;
import com.jsrdev.ForoHub.infrastructure.rest.dto.profile.ProfileRequest;
import com.jsrdev.ForoHub.infrastructure.rest.dto.profile.UpdateProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProfile {
    Profile create(ProfileRequest profileRequest);

    Page<Profile> findByActiveTrue(Pageable pagination);

    Profile findByProfileIdAndActiveTrue(String profileId);

    Profile update(Profile profile, UpdateProfile update);

    Boolean delete(Profile profile);
}
