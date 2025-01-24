package com.jsrdev.ForoHub.infrastructure.database.mysql.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "User")
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false, unique = true)
    private String userId;

    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    private Boolean active;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "users_profiles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "profile_id")
    )
    private List<ProfileEntity> profiles;

    public UserEntity() {
    }

    public UserEntity(String userId, String name, String email, String password, Boolean active, List<ProfileEntity> profiles) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.active = active;
        this.profiles = profiles;
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

    public List<ProfileEntity> getProfiles() {
        return profiles;
    }

    public String getUserId() {
        return userId;
    }

    public UserEntity update(String email, String password, List<ProfileEntity> profiles) {
        this.email = email;
        this.password = password;
        this.profiles = profiles;
        return this;
    }
}
