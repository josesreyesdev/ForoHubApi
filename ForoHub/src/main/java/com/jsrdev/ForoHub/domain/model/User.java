package com.jsrdev.ForoHub.domain.model;

import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class User {
    private String userId;
    private String name;
    private String email;
    private String password;
    private Boolean active;
    private List<Profile> profiles = new ArrayList<>();

    public User(
            String userId,
            String name,
            String email,
            String password,
            List<Profile> profiles,
            boolean active
    ) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = passwordEncoder(password);
        this.profiles = profiles != null ? profiles : new ArrayList<>();
        this.active = active;
    }

    public Boolean getActive() {
        return active;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public List<Profile> getProfiles() {
        return profiles;
    }

    public String getUserId() {
        return userId;
    }

    private String passwordEncoder(String password) {
        return password; //new PasswordEncoder().encode(password);
    }

    public User update(String email, String password, List<Profile> profiles) {
        if (email != null) {
            this.email = email;
        }
        if (password != null) {
            this.password = password;
        }
        if (!profiles.isEmpty()) {
            this.profiles = profiles;
        }
        return this;
    }

    public void delete() {
        this.active = false;
    }
}
