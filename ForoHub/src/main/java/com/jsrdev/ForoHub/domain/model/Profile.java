package com.jsrdev.ForoHub.domain.model;

import lombok.NoArgsConstructor;

@NoArgsConstructor()
public class Profile {
    private String profileId;
    private String name;
    private Boolean active;

    public Profile(String profileId, String name) {
        this.profileId = profileId.toUpperCase().trim();
        this.name = name.toUpperCase().trim();
        this.active = true;
    }

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

    public Profile update(String name) {
        if (name != null) {
            this.name = name.trim().toUpperCase();
        }
        return this;
    }

    public void delete() {
        this.active = false;
    }
}
