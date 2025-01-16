package com.jsrdev.ForoHub.domain.repository;

import com.jsrdev.ForoHub.domain.model.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProfileRepositoryPort {
    Profile create(Profile profile);

    Page<Profile> findByActiveTrue(Pageable pagination);
}
