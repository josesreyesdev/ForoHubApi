package com.jsrdev.ForoHub.infrastructure.database.mysql.mapper;

import com.jsrdev.ForoHub.domain.model.Topic;
import com.jsrdev.ForoHub.infrastructure.database.mysql.entity.CourseEntity;
import com.jsrdev.ForoHub.infrastructure.database.mysql.entity.TopicEntity;
import com.jsrdev.ForoHub.infrastructure.database.mysql.entity.UserEntity;

public class TopicEntityMapper {
    public static TopicEntity toEntity(
            Topic topic,
            UserEntity userEntity,
            CourseEntity courseEntity
    ) {
        return new TopicEntity(
                topic.getActive(),
                userEntity,
                courseEntity,
                topic.getCreationDate(),
                topic.getMessage(),
                topic.getStatus(),
                topic.getTitle(),
                topic.getTopicId()
        );
    }

    public static Topic toModel(TopicEntity topicEntity) {
        return new Topic(
                topicEntity.getActive(),
                UserEntityMapper.toModel(topicEntity.getAuthor()),
                CourseEntityMapper.toModel(topicEntity.getCourse()),
                topicEntity.getCreationDate(),
                topicEntity.getMessage(),
                topicEntity.getStatus(),
                topicEntity.getTitle(),
                topicEntity.getTopicId()
        );
    }
}
