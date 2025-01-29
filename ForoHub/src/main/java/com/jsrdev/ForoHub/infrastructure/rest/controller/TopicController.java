package com.jsrdev.ForoHub.infrastructure.rest.controller;

import com.jsrdev.ForoHub.domain.model.Topic;
import com.jsrdev.ForoHub.infrastructure.rest.dto.topic.TopicRequest;
import com.jsrdev.ForoHub.infrastructure.rest.dto.topic.TopicResponse;
import com.jsrdev.ForoHub.infrastructure.rest.mapper.TopicMapper;
import com.jsrdev.ForoHub.usecase.topic.TopicInteractor;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topics")
public class TopicController {

    private final TopicInteractor topicInteractor;

    public TopicController(TopicInteractor topicInteractor) {
        this.topicInteractor = topicInteractor;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<TopicResponse> create(
            @Valid @RequestBody TopicRequest topicRequest,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        Topic topic = topicInteractor.create(topicRequest);

        URI uri = uriComponentsBuilder.path("/api/topics/{id}")
                .buildAndExpand(topic.getTopicId()).toUri();

        return ResponseEntity.created(uri).body(TopicMapper.toResponse(topic));
    }
}