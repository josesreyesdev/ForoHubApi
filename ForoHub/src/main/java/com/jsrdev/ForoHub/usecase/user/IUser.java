package com.jsrdev.ForoHub.usecase.user;

import com.jsrdev.ForoHub.domain.model.User;
import com.jsrdev.ForoHub.infrastructure.rest.dto.user.UserRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUser {
    User createUserWithProfiles(UserRequest userRequest);

    Page<User> findByActiveTrueWithProfiles(Pageable pagination);
}
