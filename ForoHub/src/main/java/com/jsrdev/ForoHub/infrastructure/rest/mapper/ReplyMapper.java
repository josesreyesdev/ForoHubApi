package com.jsrdev.ForoHub.infrastructure.rest.mapper;

import com.jsrdev.ForoHub.domain.model.Reply;
import com.jsrdev.ForoHub.domain.model.Topic;
import com.jsrdev.ForoHub.domain.model.User;
import com.jsrdev.ForoHub.infrastructure.rest.dto.reply.ReplyRequest;
import com.jsrdev.ForoHub.infrastructure.rest.dto.reply.ReplyResponse;

import java.time.LocalDateTime;
import java.util.UUID;

public class ReplyMapper {
    public static Reply toModel(ReplyRequest replyRequest, User user, Topic topic) {
        return new Reply(
                true,
                user,
                LocalDateTime.now(),
                replyRequest.message(),
                UUID.randomUUID().toString(),
                false,
                topic
        );
    }

    public static ReplyResponse toResponse(Reply reply) {
        return new ReplyResponse(
                reply.getReplyId(),
                reply.getMessage(),
                reply.getCreationDate(),
                reply.getSolution(),
                reply.getAuthor().getUserId(),
                reply.getTopic().getTopicId()
        );
    }
}
