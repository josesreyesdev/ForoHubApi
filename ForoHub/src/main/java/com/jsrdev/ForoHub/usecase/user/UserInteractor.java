package com.jsrdev.ForoHub.usecase.user;

import com.jsrdev.ForoHub.domain.model.Profile;
import com.jsrdev.ForoHub.domain.model.User;
import com.jsrdev.ForoHub.domain.repository.UserRepositoryPort;
import com.jsrdev.ForoHub.infrastructure.rest.dto.user.UserRequest;
import com.jsrdev.ForoHub.infrastructure.rest.mapper.UserMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserInteractor implements IUser {

    private final UserRepositoryPort userRepositoryPort;

    public UserInteractor(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public User createUserWithProfiles(UserRequest userRequest) {
        List<Profile> profiles = userRepositoryPort
                .validateAndFindProfiles(userRequest.profiles());

        User user = UserMapper.toModel(userRequest, profiles);

        return userRepositoryPort.createUserWithProfiles(user);
    }
}
