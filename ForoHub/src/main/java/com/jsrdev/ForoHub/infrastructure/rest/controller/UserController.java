package com.jsrdev.ForoHub.infrastructure.rest.controller;

import com.jsrdev.ForoHub.domain.model.User;
import com.jsrdev.ForoHub.infrastructure.rest.dto.user.UserRequest;
import com.jsrdev.ForoHub.infrastructure.rest.dto.user.UserResponse;
import com.jsrdev.ForoHub.infrastructure.rest.mapper.UserMapper;
import com.jsrdev.ForoHub.usecase.user.UserInteractor;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserInteractor userInteractor;

    public UserController(UserInteractor userInteractor) {
        this.userInteractor = userInteractor;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<UserResponse> create(
            @Valid @RequestBody UserRequest userRequest,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        User user = userInteractor.createUserWithProfiles(userRequest);

        URI uri = uriComponentsBuilder.path("/api/users/{id}")
                .buildAndExpand(user.getUserId()).toUri();

        return ResponseEntity.created(uri).body(UserMapper.toResponse(user));
    }
}
