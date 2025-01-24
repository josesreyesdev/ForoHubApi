package com.jsrdev.ForoHub.infrastructure.rest.mapper;

import com.jsrdev.ForoHub.domain.model.Profile;
import com.jsrdev.ForoHub.domain.model.User;
import com.jsrdev.ForoHub.infrastructure.rest.dto.profile.ProfileResponse;
import com.jsrdev.ForoHub.infrastructure.rest.dto.user.UserRequest;
import com.jsrdev.ForoHub.infrastructure.rest.dto.user.UserResponse;

import java.util.List;

public class UserMapper {

    public static UserResponse toResponse(User user) {
        return new UserResponse(
                user.getUserId(),
                user.getName(),
                user.getEmail(),
                getProfiles(user.getProfiles())
        );
    }

    private static List<ProfileResponse> getProfiles(List<Profile> profiles) {
        return profiles.stream()
                .filter(Profile::getActive)
                .map(ProfileMapper::toResponse)
                .toList();
    }

    public static User toModel(UserRequest userRequest, List<Profile> profiles) {
        return new User(
                userRequest.userId(),
                userRequest.name(),
                userRequest.email(),
                userRequest.password(),
                profiles,
                true
        );
    }
}
