package com.jsrdev.ForoHub.infrastructure.rest.controller;

import com.jsrdev.ForoHub.usecase.topic.TopicInteractor;

public class TopicController {

    private final TopicInteractor topicInteractor;

    public TopicController(TopicInteractor topicInteractor) {
        this.topicInteractor = topicInteractor;
    }
}
