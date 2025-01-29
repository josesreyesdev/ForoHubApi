package com.jsrdev.ForoHub.infrastructure.rest.dto.topic;

import com.jsrdev.ForoHub.common.TopicStatus;

import java.time.LocalDateTime;

public record TopicResponse(
        String topicId,
        String title,
        String message,
        LocalDateTime creationDate,
        TopicStatus status,
        String authorId,
        String courseId
        //List<ReplyResponse> replies
) {
}
