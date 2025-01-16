package com.jsrdev.ForoHub.usecase.profile;

import com.jsrdev.ForoHub.domain.model.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProfile {
    Profile create(Profile profile);

    Page<Profile> findByActiveTrue(Pageable pagination);

    Profile findByProfileIdAndActiveTrue(String profileId);

    Profile update(Profile update);
}
