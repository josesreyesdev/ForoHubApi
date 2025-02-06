package com.jsrdev.ForoHub.infrastructure.rest.dto.reply;

import java.time.LocalDateTime;

public record ReplyResponse(
        String replyId,
        String message,
        LocalDateTime creationDate,
        Boolean solution,
        String authorId,
        String topicId
) {
}
