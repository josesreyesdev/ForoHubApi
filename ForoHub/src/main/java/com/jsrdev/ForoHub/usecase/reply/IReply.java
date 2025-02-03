package com.jsrdev.ForoHub.usecase.reply;

import com.jsrdev.ForoHub.domain.model.Reply;
import com.jsrdev.ForoHub.infrastructure.rest.dto.reply.ReplyRequest;

public interface IReply {
    Reply save(ReplyRequest replyRequest);
}
