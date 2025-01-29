package com.jsrdev.ForoHub.usecase.topic.validations.add;

import com.jsrdev.ForoHub.domain.model.User;
import com.jsrdev.ForoHub.domain.repository.UserRepositoryPort;
import com.jsrdev.ForoHub.infrastructure.exceptions.ValidationIntegrity;
import com.jsrdev.ForoHub.infrastructure.rest.dto.topic.TopicRequest;
import org.springframework.stereotype.Component;

@Component
public class ActiveUser implements TopicValidator {

    private final UserRepositoryPort userRepositoryPort;

    public ActiveUser(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public void validate(TopicRequest topicRequest) {
        User user = userRepositoryPort
                .findByUserIdAndActiveTrue(topicRequest.authorId());

        if (user == null) {
            throw new ValidationIntegrity("User not found or inactive: " + topicRequest.authorId());
        }
    }
}
