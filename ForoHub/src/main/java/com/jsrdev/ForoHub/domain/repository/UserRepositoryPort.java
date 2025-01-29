package com.jsrdev.ForoHub.domain.repository;

import com.jsrdev.ForoHub.domain.model.Profile;
import com.jsrdev.ForoHub.domain.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserRepositoryPort {
    User createUserWithProfiles(User user);

    List<Profile> validateAndFindProfiles(List<String> profileIds);

    Page<User> findByActiveTrueWithProfiles(Pageable pagination);

    User findByUserIdAndActiveTrue(String userId);

    User update(User updated);

    Boolean delete(String userId);

    User findByUserId(String userId);
}
