package com.jsrdev.ForoHub.usecase.topic.validations.add;

import com.jsrdev.ForoHub.infrastructure.rest.dto.topic.TopicRequest;

public interface TopicValidator {
    void validate(TopicRequest topicRequest);
}
