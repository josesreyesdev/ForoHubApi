package com.jsrdev.ForoHub.infrastructure.rest.mapper;

import com.jsrdev.ForoHub.domain.model.Profile;
import com.jsrdev.ForoHub.domain.model.User;
import com.jsrdev.ForoHub.infrastructure.rest.dto.user.UserRequest;
import com.jsrdev.ForoHub.infrastructure.rest.dto.user.UserResponse;

import java.util.ArrayList;
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

    private static List<String> getProfiles(List<Profile> profiles) {
        List<String> profileStrings = new ArrayList<>();
        profiles.forEach(prof -> profileStrings.add(prof.getProfileId()));

        return profileStrings;
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
