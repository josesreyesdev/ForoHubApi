package com.jsrdev.ForoHub.infrastructure.database.mysql.mapper;

import com.jsrdev.ForoHub.domain.model.Reply;
import com.jsrdev.ForoHub.infrastructure.database.mysql.entity.ReplyEntity;
import com.jsrdev.ForoHub.infrastructure.database.mysql.entity.TopicEntity;
import com.jsrdev.ForoHub.infrastructure.database.mysql.entity.UserEntity;

public class ReplyEntityMapper {
    public static ReplyEntity toEntity(Reply reply, UserEntity userEntity, TopicEntity topicEntity) {
        return new ReplyEntity(
                reply.getActive(),
                userEntity,
                reply.getCreationDate(),
                reply.getMessage(),
                reply.getReplyId(),
                reply.getSolution(),
                topicEntity
        );
    }

    public static Reply toModel(ReplyEntity replyEntity) {
        return new Reply(
                replyEntity.getActive(),
                UserEntityMapper.toModel(replyEntity.getAuthor()),
                replyEntity.getCreationDate(),
                replyEntity.getMessage(),
                replyEntity.getReplyId(),
                replyEntity.getSolution(),
                TopicEntityMapper.toModel(replyEntity.getTopic())
        );
    }
}
