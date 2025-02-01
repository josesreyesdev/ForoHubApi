package com.jsrdev.ForoHub.domain.repository;

import com.jsrdev.ForoHub.domain.model.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TopicRepositoryPort {
    Topic save(Topic topic);

    Page<Topic> findAllByActiveTrue(Pageable pagination);

    Topic findByTopicIdAndActiveTrue(String topicId);

    Topic update(Topic updated);

    Topic delete(Topic topic);
}
