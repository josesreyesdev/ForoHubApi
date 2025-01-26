package com.jsrdev.ForoHub.usecase.topic;

import com.jsrdev.ForoHub.domain.repository.TopicRepositoryPort;

public class TopicInteractor implements ITopic {

    private final TopicRepositoryPort topicRepositoryPort;

    public TopicInteractor(TopicRepositoryPort topicRepositoryPort) {
        this.topicRepositoryPort = topicRepositoryPort;
    }
}
