package com.jsrdev.ForoHub.infrastructure.database.mysql.adapter;

import com.jsrdev.ForoHub.domain.model.Reply;
import com.jsrdev.ForoHub.domain.repository.ReplyRepositoryPort;
import com.jsrdev.ForoHub.infrastructure.database.mysql.entity.ReplyEntity;
import com.jsrdev.ForoHub.infrastructure.database.mysql.entity.TopicEntity;
import com.jsrdev.ForoHub.infrastructure.database.mysql.entity.UserEntity;
import com.jsrdev.ForoHub.infrastructure.database.mysql.mapper.ReplyEntityMapper;
import com.jsrdev.ForoHub.infrastructure.database.mysql.repository.ReplyJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReplyRepositoryAdapter implements ReplyRepositoryPort {

    private final ReplyJpaRepository replyJpaRepository;
    private final UserRepositoryAdapter userRepositoryAdapter;
    private final TopicRepositoryAdapter topicRepositoryAdapter;

    public ReplyRepositoryAdapter(
            ReplyJpaRepository replyJpaRepository,
            UserRepositoryAdapter userRepositoryAdapter,
            TopicRepositoryAdapter topicRepositoryAdapter
    ) {
        this.replyJpaRepository = replyJpaRepository;
        this.userRepositoryAdapter = userRepositoryAdapter;
        this.topicRepositoryAdapter = topicRepositoryAdapter;
    }

    @Override
    public Reply save(Reply reply) {
        UserEntity userEntity = userRepositoryAdapter
                .findByUserIdAndActiveTrueWithProfiles(reply.getAuthor().getUserId());
        TopicEntity topicEntity = topicRepositoryAdapter
                .findByTopicIdEntity(reply.getTopic().getTopicId());
        ReplyEntity replyEntity = replyJpaRepository
                .save(ReplyEntityMapper.toEntity(reply, userEntity, topicEntity));

        return ReplyEntityMapper.toModel(replyEntity);
    }

    @Override
    public Page<Reply> findAllByActiveTrue(Pageable pagination) {
        Page<ReplyEntity> repliesPage = replyJpaRepository.findAllByActiveTrue(pagination);

        List<Reply> replies = repliesPage.getContent().stream()
                .map(ReplyEntityMapper::toModel)
                .toList();

        return new PageImpl<>(
                replies,
                pagination,
                repliesPage.getTotalElements()
        );
    }
}
