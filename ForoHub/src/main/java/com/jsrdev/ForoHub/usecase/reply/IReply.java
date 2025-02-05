package com.jsrdev.ForoHub.usecase.reply;

import com.jsrdev.ForoHub.domain.model.Reply;
import com.jsrdev.ForoHub.infrastructure.rest.dto.reply.ReplyRequest;
import com.jsrdev.ForoHub.infrastructure.rest.dto.reply.UpdateReply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IReply {
    Reply save(ReplyRequest replyRequest);

    Page<Reply> findAllByActiveTrue(Pageable pagination);

    Reply findByReplyIdAndActiveTrue(String replyId);

    Reply update(Reply reply, UpdateReply updateRequest);
}
