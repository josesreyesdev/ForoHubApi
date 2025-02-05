package com.jsrdev.ForoHub.infrastructure.rest.dto.reply;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;

public record UpdateReply(
        @JsonAlias({"replyId", "reply_id"})
        @NotBlank(message = "ReplyId is required")
        String replyId,
        String message,
        Boolean solution
) {
}
