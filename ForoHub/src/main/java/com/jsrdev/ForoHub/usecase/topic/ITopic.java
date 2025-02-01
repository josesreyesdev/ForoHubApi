package com.jsrdev.ForoHub.usecase.topic;

import com.jsrdev.ForoHub.domain.model.Topic;
import com.jsrdev.ForoHub.infrastructure.rest.dto.topic.TopicRequest;
import com.jsrdev.ForoHub.infrastructure.rest.dto.topic.UpdateTopic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ITopic {
    Topic create(TopicRequest topicRequest);

    Page<Topic> findAllByActiveTrue(Pageable pagination);

    Topic findByTopicIdAndActiveTrue(String topicId);

    Topic update(Topic topic, UpdateTopic updateTopic);

    Topic delete(Topic topic);
}
