package com.jsrdev.ForoHub.domain.model;

import com.jsrdev.ForoHub.infrastructure.rest.dto.profile.ProfileRequest;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;

@NoArgsConstructor()
public class Profile {
    private String profileId;
    private String name;
    private Boolean active;

    public Profile(@Valid ProfileRequest profileRequest) {
        this.profileId = profileRequest.profileId().toUpperCase().trim();
        this.name = profileRequest.name().toUpperCase().trim();
        this.active = true;
    }

    public Profile(String profileId, String name, Boolean isActive) {
        this.profileId = profileId;
        this.name = name;
        this.active = isActive;
    }

    public Boolean getActive() {
        return active;
    }

    public String getName() {
        return name;
    }

    public String getProfileId() {
        return profileId;
    }
}
