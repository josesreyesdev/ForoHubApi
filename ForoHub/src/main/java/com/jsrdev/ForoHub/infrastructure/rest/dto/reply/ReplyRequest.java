package com.jsrdev.ForoHub.infrastructure.rest.dto.reply;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;

public record ReplyRequest(
        @NotBlank(message = "Message is required")
        String message,
        @JsonAlias({"authorId", "author_id"})
        @NotBlank(message = "AuthorId is required")
        String authorId,
        @JsonAlias({"topicId", "topic_id"})
        @NotBlank(message = "TopicId is required")
        String topicId
) {
}
