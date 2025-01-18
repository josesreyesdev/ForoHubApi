package com.jsrdev.ForoHub.usecase.user;

import com.jsrdev.ForoHub.domain.model.User;
import com.jsrdev.ForoHub.infrastructure.rest.dto.user.UserRequest;

public interface IUser {
    User createUserWithProfiles(UserRequest userRequest);
}
