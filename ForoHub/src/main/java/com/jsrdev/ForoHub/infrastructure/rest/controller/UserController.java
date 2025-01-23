package com.jsrdev.ForoHub.infrastructure.rest.controller;

import com.jsrdev.ForoHub.domain.model.User;
import com.jsrdev.ForoHub.infrastructure.rest.dto.user.UserRequest;
import com.jsrdev.ForoHub.infrastructure.rest.dto.user.UserResponse;
import com.jsrdev.ForoHub.infrastructure.rest.mapper.UserMapper;
import com.jsrdev.ForoHub.usecase.user.UserInteractor;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

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

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<UserResponse>>> getUsers(
            @PageableDefault(size = 15) Pageable pagination,
            PagedResourcesAssembler<UserResponse> assembler
    ) {
        Page<User> usersPage = userInteractor.findByActiveTrueWithProfiles(pagination);

        List<UserResponse> userResponse = usersPage
                .getContent()
                .stream()
                .map(UserMapper::toResponse)
                .toList();

        Page<UserResponse> userResponsePage = new PageImpl<>(
                userResponse,
                pagination,
                usersPage.getTotalElements()
        );

        return ResponseEntity.ok(assembler.toModel(userResponsePage));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUser(@PathVariable String userId) {
        User user = findByUserIdAndActiveTrue(userId);

        return ResponseEntity.ok(UserMapper.toResponse(user));
    }

    private User findByUserIdAndActiveTrue(String userId) {
        User user = userInteractor.findByUserIdAndActiveTrue(userId);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found or inactive.");
        }

        return user;
    }
}
