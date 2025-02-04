package com.jsrdev.ForoHub.usecase.reply;

import com.jsrdev.ForoHub.domain.model.Reply;
import com.jsrdev.ForoHub.domain.model.Topic;
import com.jsrdev.ForoHub.domain.model.User;
import com.jsrdev.ForoHub.domain.repository.ReplyRepositoryPort;
import com.jsrdev.ForoHub.domain.repository.TopicRepositoryPort;
import com.jsrdev.ForoHub.domain.repository.UserRepositoryPort;
import com.jsrdev.ForoHub.infrastructure.rest.dto.reply.ReplyRequest;
import com.jsrdev.ForoHub.infrastructure.rest.mapper.ReplyMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class ReplyInteractor implements IReply {

    private final ReplyRepositoryPort replyRepositoryPort;
    private final UserRepositoryPort userRepositoryPort;
    private final TopicRepositoryPort topicRepositoryPort;

    public ReplyInteractor(
            ReplyRepositoryPort replyRepositoryPort,
            UserRepositoryPort userRepositoryPort,
            TopicRepositoryPort topicRepositoryPort
    ) {
        this.replyRepositoryPort = replyRepositoryPort;
        this.userRepositoryPort = userRepositoryPort;
        this.topicRepositoryPort = topicRepositoryPort;
    }

    @Override
    public Reply save(ReplyRequest replyRequest) {

        User user = userRepositoryPort.findByUserIdAndActiveTrue(replyRequest.authorId());
        Topic topic = topicRepositoryPort.findByTopicIdAndActiveTrue(replyRequest.topicId());
        Reply reply = ReplyMapper.toModel(replyRequest, user, topic);

        return replyRepositoryPort.save(reply);
    }

    @Override
    public Page<Reply> findAllByActiveTrue(Pageable pagination) {
        return replyRepositoryPort.findAllByActiveTrue(pagination);
    }

    @Override
    public Reply findByReplyIdAndActiveTrue(String replyId) {
        return replyRepositoryPort.findByReplyIdAndActiveTrue(replyId);
    }
}
