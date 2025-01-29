package com.jsrdev.ForoHub.infrastructure.rest.mapper;

import com.jsrdev.ForoHub.common.TopicStatus;
import com.jsrdev.ForoHub.domain.model.Course;
import com.jsrdev.ForoHub.domain.model.Topic;
import com.jsrdev.ForoHub.domain.model.User;
import com.jsrdev.ForoHub.infrastructure.rest.dto.topic.TopicRequest;
import com.jsrdev.ForoHub.infrastructure.rest.dto.topic.TopicResponse;

import java.time.LocalDateTime;
import java.util.UUID;

public class TopicMapper {

    public static Topic toModel(TopicRequest topicRequest, User user, Course course){
        return new Topic(
                true,
                user,
                course,
                LocalDateTime.now(),
                topicRequest.message(),
                TopicStatus.OPEN,
                topicRequest.title(),
                UUID.randomUUID().toString()
        );
    }

    public static TopicResponse toResponse(Topic topic) {
        return new TopicResponse(
                topic.getTopicId(),
                topic.getTitle(),
                topic.getMessage(),
                topic.getCreationDate(),
                topic.getStatus(),
                topic.getAuthor().getUserId(),
                topic.getCourse().getCourseId()
        );
    }
}
