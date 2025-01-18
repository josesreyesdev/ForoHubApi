package com.jsrdev.ForoHub.infrastructure.database.mysql.entity;

import jakarta.persistence.*;

@Entity(name = "Profile")
@Table(name = "profiles")
public class ProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "profile_id", nullable = false, unique = true)
    private String profileId;
    @Column(nullable = false, unique = true)
    private String name;
    private Boolean active;

    public ProfileEntity() {
    }

    public ProfileEntity(String name, String profileId, Boolean active) {
        this.name = name;
        this.profileId = profileId;
        this.active = active;
    }

    public void update(String name) {
        if (name != null) {
            this.name = name;
        }
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

    public Boolean delete() {
        this.active = false;
        return true;
    }
}
