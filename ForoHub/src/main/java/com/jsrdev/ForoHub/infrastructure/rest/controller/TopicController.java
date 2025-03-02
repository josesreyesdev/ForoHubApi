package com.jsrdev.ForoHub.infrastructure.rest.controller;

import com.jsrdev.ForoHub.domain.model.Topic;
import com.jsrdev.ForoHub.infrastructure.rest.dto.DeleteResponse;
import com.jsrdev.ForoHub.infrastructure.rest.dto.topic.TopicRequest;
import com.jsrdev.ForoHub.infrastructure.rest.dto.topic.TopicResponse;
import com.jsrdev.ForoHub.infrastructure.rest.dto.topic.UpdateTopic;
import com.jsrdev.ForoHub.infrastructure.rest.mapper.TopicMapper;
import com.jsrdev.ForoHub.usecase.topic.TopicInteractor;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

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

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<TopicResponse>>> getTopics(
            @PageableDefault(size = 15) Pageable pagination,
            PagedResourcesAssembler<TopicResponse> assembler
    ) {
        Page<Topic> topicsPage = topicInteractor.findAllByActiveTrue(pagination);

        List<TopicResponse> topics = topicsPage.getContent().stream()
                .map(TopicMapper::toResponse)
                .toList();

        Page<TopicResponse> responsePage = new PageImpl<>(
                topics,
                pagination,
                topicsPage.getTotalElements()
        );

        return ResponseEntity.ok(assembler.toModel(responsePage));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicResponse> getTopic(@PathVariable String id) {
        Topic topic = findByTopicIdAndActiveTrue(id);
        return ResponseEntity.ok(TopicMapper.toResponse(topic));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicResponse> update(
            @PathVariable String id,
            @Valid @RequestBody UpdateTopic updateTopic
    ) {
        Topic topic = findByTopicIdAndActiveTrue(id);
        if (!topic.getAuthor().getUserId().equals(updateTopic.authorId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not authorized to update this topic.");
        }
        Topic updated = topicInteractor.update(topic, updateTopic);

        return ResponseEntity.ok(TopicMapper.toResponse(updated));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<DeleteResponse> delete(@PathVariable String id) {
        Topic topic = findByTopicIdAndActiveTrue(id);

        Topic deleted = topicInteractor.delete(topic);

        boolean isDeleted = deleted != null;
        String message = isDeleted ? "Topic successfully deleted." : "Failed to delete topic.";
        DeleteResponse response = new DeleteResponse(isDeleted, message);

        return ResponseEntity.ok(response);
    }

    private Topic findByTopicIdAndActiveTrue(String topicId) {
        Topic topic = topicInteractor.findByTopicIdAndActiveTrue(topicId);
        if (topic == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Topic not found or inactive: " + topicId);
        }
        return topic;
    }
}
