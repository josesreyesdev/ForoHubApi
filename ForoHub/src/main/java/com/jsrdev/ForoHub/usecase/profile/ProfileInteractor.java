package com.jsrdev.ForoHub.usecase.profile;

import com.jsrdev.ForoHub.domain.model.Profile;
import com.jsrdev.ForoHub.domain.repository.ProfileRepositoryPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class ProfileInteractor implements IProfile {

    private final ProfileRepositoryPort profileRepositoryPort;

    public ProfileInteractor(ProfileRepositoryPort profileRepositoryPort) {
        this.profileRepositoryPort = profileRepositoryPort;
    }

    @Override
    public Profile create(Profile profile) {
        return profileRepositoryPort.create(profile);
    }

    @Override
    public Page<Profile> findByActiveTrue(Pageable pagination) {
        return profileRepositoryPort.findByActiveTrue(pagination);
    }

    @Override
    public Profile findByProfileIdAndActiveTrue(String profileId) {
        return profileRepositoryPort.findByProfileIdAndActiveTrue(profileId);
    }

    @Override
    public Profile update(Profile update) {
        return profileRepositoryPort.update(update);
    }
}