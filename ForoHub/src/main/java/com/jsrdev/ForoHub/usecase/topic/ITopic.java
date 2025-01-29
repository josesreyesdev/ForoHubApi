package com.jsrdev.ForoHub.usecase.topic;

import com.jsrdev.ForoHub.domain.model.Topic;
import com.jsrdev.ForoHub.infrastructure.rest.dto.topic.TopicRequest;

public interface ITopic {
    Topic create(TopicRequest topicRequest);
}
