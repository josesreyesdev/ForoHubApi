package com.jsrdev.ForoHub.domain.model;

import com.jsrdev.ForoHub.infrastructure.rest.dto.profile.UpdateProfile;
import lombok.NoArgsConstructor;

@NoArgsConstructor()
public class Profile {
    private String profileId;
    private String name;
    private Boolean active;

    public Profile(String profileId, String name, Boolean isActive) {
        this.profileId = profileId.toUpperCase().trim();
        this.name = name.toUpperCase().trim();
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

    public Profile update(Profile profile, String name) {
        if (name != null) {
            profile.name = name.trim().toUpperCase();
        }
        return profile;
    }

    public void delete(Profile profile) {
        profile.active = false;
    }
}
