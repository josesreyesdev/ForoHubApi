package com.jsrdev.ForoHub.infrastructure.rest.dto.topic;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jsrdev.ForoHub.common.TopicStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicRequest(
        /*@JsonAlias({"topicId", "topic_id"})
        @NotBlank(message = "TopicId is required")
        UUID topicId,*/
        @NotBlank(message = "Title is required")
        String title,
        @NotBlank(message = "Message is required")
        String message,
        @NotNull(message = "Status is required")
        @JsonDeserialize(using = TopicStatusDeserializer.class)
        TopicStatus status,
        @JsonAlias({"authorId", "author_id"})
        @NotBlank(message = "AuthorId is required")
        String authorId,
        @JsonAlias({"courseId", "course_id"})
        @NotNull(message = "CourseId is required")
        String courseId
) {
}
