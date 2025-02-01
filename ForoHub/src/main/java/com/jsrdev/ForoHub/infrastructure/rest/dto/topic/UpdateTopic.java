package com.jsrdev.ForoHub.infrastructure.rest.dto.topic;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jsrdev.ForoHub.common.TopicStatus;
import jakarta.validation.constraints.NotBlank;

public record UpdateTopic(
        @JsonAlias({"authorId", "author_id"})
        @NotBlank(message = "AuthorId is required")
        String authorId,
        String title,
        String message,
        @JsonDeserialize(using = TopicStatusDeserializer.class)
        TopicStatus status,
        @JsonAlias({"courseId", "course_id"})
        String courseId
) {
}
