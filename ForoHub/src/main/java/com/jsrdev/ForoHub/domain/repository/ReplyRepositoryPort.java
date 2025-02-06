package com.jsrdev.ForoHub.domain.repository;

import com.jsrdev.ForoHub.domain.model.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReplyRepositoryPort {
    Reply save(Reply reply);

    Page<Reply> findAllByActiveTrue(Pageable pagination);

    Reply findByReplyIdAndActiveTrue(String replyId);

    Reply update(Reply reply);

    Reply delete(Reply reply);
}
