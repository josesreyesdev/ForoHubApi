package com.jsrdev.ForoHub.infrastructure.rest.controller;

import com.jsrdev.ForoHub.domain.model.Profile;
import com.jsrdev.ForoHub.infrastructure.rest.dto.DeleteResponse;
import com.jsrdev.ForoHub.infrastructure.rest.dto.profile.ProfileRequest;
import com.jsrdev.ForoHub.infrastructure.rest.dto.profile.ProfileResponse;
import com.jsrdev.ForoHub.infrastructure.rest.dto.profile.UpdateProfile;
import com.jsrdev.ForoHub.infrastructure.rest.mapper.ProfileMapper;
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
        Profile profile = profileInteractor.create(profileRequest);

        URI uri = uriComponentsBuilder.path("/api/profiles/{id}")
                .buildAndExpand(profile.getProfileId()).toUri();

        return ResponseEntity.created(uri)
                .body(ProfileMapper.toResponse(profile));
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
                .map(ProfileMapper::toResponse)
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
        Profile profile = findByProfileIdAndActive(profileId);

        return ResponseEntity.ok(ProfileMapper.toResponse(profile));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<ProfileResponse> update(@Valid @RequestBody UpdateProfile update) {
        Profile profile = findByProfileIdAndActive(update.profileId());

        Profile updated = profileInteractor.update(profile.update(profile, update));
        return ResponseEntity.ok(ProfileMapper.toResponse(updated));
    }

    @DeleteMapping("/{profileId}")
    @Transactional
    public ResponseEntity<DeleteResponse> delete(@PathVariable String profileId) {
        Profile profile = findByProfileIdAndActive(profileId);

        profile.delete(profile);
        Boolean isDeleted = profileInteractor.delete(profile.getProfileId());

        String message = isDeleted ? "Profile successfully deleted." : "Failed to delete profile.";
        DeleteResponse response = new DeleteResponse(isDeleted, message);

        return ResponseEntity.ok(response);
    }

    private Profile findByProfileIdAndActive(String profileId) {
        Profile profile = profileInteractor.findByProfileIdAndActiveTrue(profileId);
        if (profile == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Profile not found or inactive.");
        }
        return profile;
    }
}
