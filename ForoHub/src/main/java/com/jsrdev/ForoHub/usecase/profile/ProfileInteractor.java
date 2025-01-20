package com.jsrdev.ForoHub.usecase.profile;

import com.jsrdev.ForoHub.domain.model.Profile;
import com.jsrdev.ForoHub.domain.repository.ProfileRepositoryPort;
import com.jsrdev.ForoHub.infrastructure.rest.dto.profile.ProfileRequest;
import com.jsrdev.ForoHub.infrastructure.rest.mapper.ProfileMapper;
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
    public Profile create(ProfileRequest profileRequest) {
        Profile profile = ProfileMapper.toModel(profileRequest);
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

    @Override
    public Boolean delete(String profileId) {
        return profileRepositoryPort.delete(profileId);
    }
}