package com.jsrdev.ForoHub.usecase.user;

import com.jsrdev.ForoHub.domain.model.Profile;
import com.jsrdev.ForoHub.domain.model.User;
import com.jsrdev.ForoHub.domain.repository.UserRepositoryPort;
import com.jsrdev.ForoHub.infrastructure.rest.dto.user.UpdateRequest;
import com.jsrdev.ForoHub.infrastructure.rest.dto.user.UserRequest;
import com.jsrdev.ForoHub.infrastructure.rest.mapper.UserMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Component
public class UserInteractor implements IUser {

    private final UserRepositoryPort userRepositoryPort;

    public UserInteractor(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public User createUserWithProfiles(UserRequest userRequest) {
        List<Profile> profiles = validateAndFindProfiles(userRequest.profiles());
        User user = UserMapper.toModel(userRequest, profiles);

        return userRepositoryPort.createUserWithProfiles(user);
    }

    @Override
    public Page<User> findByActiveTrueWithProfiles(Pageable pagination) {
        return userRepositoryPort.findByActiveTrueWithProfiles(pagination);
    }

    @Override
    public User findByUserIdAndActiveTrue(String userId) {
        return userRepositoryPort.findByUserIdAndActiveTrue(userId);
    }

    @Override
    public User update(User user, UpdateRequest update) {
        List<Profile> profiles = validateAndFindProfiles(update.profiles());
        User updated = user.update(update.email(), update.password(), profiles);

        return userRepositoryPort.update(updated);
    }

    @Override
    public Boolean delete(User user) {
        user.delete();
        return userRepositoryPort.delete(user.getUserId());
    }

    private List<Profile> validateAndFindProfiles(List<String> profiles) {
        Set<String> profilesSet = new LinkedHashSet<>(profiles);
        return userRepositoryPort.validateAndFindProfiles(profilesSet.stream().toList());
    }
}
