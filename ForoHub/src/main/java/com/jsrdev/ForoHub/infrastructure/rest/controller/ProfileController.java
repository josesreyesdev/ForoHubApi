package com.jsrdev.ForoHub.infrastructure.rest.controller;

import com.jsrdev.ForoHub.domain.model.Profile;
import com.jsrdev.ForoHub.infrastructure.rest.dto.profile.ProfileRequest;
import com.jsrdev.ForoHub.infrastructure.rest.dto.profile.ProfileResponse;
import com.jsrdev.ForoHub.infrastructure.rest.mapper.ControllerProfileMapper;
import com.jsrdev.ForoHub.usecase.profile.ProfileInteractor;
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
@RequestMapping("/api/profiles")
public class ProfileController {

    private final ProfileInteractor profileInteractor;

    public ProfileController(ProfileInteractor profileInteractor) {
        this.profileInteractor = profileInteractor;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ProfileResponse> create(
            @Valid @RequestBody ProfileRequest profileRequest,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        Profile profile = profileInteractor.create(new Profile(profileRequest));
        URI uri = uriComponentsBuilder.path("/api/profiles/{id}")
                .buildAndExpand(profile.getProfileId()).toUri();

        return ResponseEntity.created(uri)
                .body(ControllerProfileMapper.fromProfileToProfileResponse(profile));
    }
}
