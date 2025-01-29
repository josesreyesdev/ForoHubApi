package com.jsrdev.ForoHub.domain.repository;

import com.jsrdev.ForoHub.domain.model.Topic;

public interface TopicRepositoryPort {
    Topic save(Topic topic);
}
