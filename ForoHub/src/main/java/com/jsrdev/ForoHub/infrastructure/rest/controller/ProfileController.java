package com.jsrdev.ForoHub.infrastructure.rest.controller;

import com.jsrdev.ForoHub.domain.model.Profile;
import com.jsrdev.ForoHub.infrastructure.rest.dto.profile.ProfileRequest;
import com.jsrdev.ForoHub.infrastructure.rest.dto.profile.ProfileResponse;
import com.jsrdev.ForoHub.infrastructure.rest.mapper.ControllerProfileMapper;
import com.jsrdev.ForoHub.usecase.profile.ProfileInteractor;
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

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<ProfileResponse>>> getProfiles(
            @PageableDefault(size = 15) Pageable pagination,
            PagedResourcesAssembler<ProfileResponse> assembler
    ) {
        Page<Profile> profilesPage = profileInteractor.findByActiveTrue(pagination);

        List<ProfileResponse> profileResponse = profilesPage
                .getContent()
                .stream()
                .map(ControllerProfileMapper::fromProfileToProfileResponse)
                .toList();

        Page<ProfileResponse> profileResponsePage = new PageImpl<>(
                profileResponse,
                pagination,
                profilesPage.getTotalElements()
        );

        return ResponseEntity.ok(assembler.toModel(profileResponsePage));
    }

    @GetMapping("/{profileId}")
    public ResponseEntity<ProfileResponse> getProfile(@PathVariable String profileId) {
        Profile profile = profileInteractor.findByProfileIdAndActiveTrue(profileId);
        if (profile == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Profile not found or inactive.");
        }
        return ResponseEntity.ok(ControllerProfileMapper.fromProfileToProfileResponse(profile));
    }
}
