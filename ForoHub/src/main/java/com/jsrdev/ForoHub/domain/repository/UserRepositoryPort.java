package com.jsrdev.ForoHub.domain.repository;

import com.jsrdev.ForoHub.domain.model.Profile;
import com.jsrdev.ForoHub.domain.model.User;

import java.util.List;

public interface UserRepositoryPort {
    User createUserWithProfiles(User user);

    List<Profile> validateAndFindProfiles(List<String> profileIds);
}
