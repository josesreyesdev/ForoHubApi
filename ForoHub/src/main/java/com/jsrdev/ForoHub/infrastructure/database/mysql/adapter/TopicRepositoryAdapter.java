package com.jsrdev.ForoHub.infrastructure.database.mysql.adapter;

import com.jsrdev.ForoHub.domain.model.Topic;
import com.jsrdev.ForoHub.domain.repository.TopicRepositoryPort;
import com.jsrdev.ForoHub.infrastructure.database.mysql.entity.CourseEntity;
import com.jsrdev.ForoHub.infrastructure.database.mysql.entity.TopicEntity;
import com.jsrdev.ForoHub.infrastructure.database.mysql.entity.UserEntity;
import com.jsrdev.ForoHub.infrastructure.database.mysql.mapper.TopicEntityMapper;
import com.jsrdev.ForoHub.infrastructure.database.mysql.repository.CourseJpaRepository;
import com.jsrdev.ForoHub.infrastructure.database.mysql.repository.TopicJpaRepository;
import com.jsrdev.ForoHub.infrastructure.database.mysql.repository.UserJpaRepository;
import com.jsrdev.ForoHub.infrastructure.exceptions.ValidationIntegrity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TopicRepositoryAdapter implements TopicRepositoryPort {

    private final TopicJpaRepository topicJpaRepository;
    private final UserJpaRepository userJpaRepository;
    private final CourseJpaRepository courseJpaRepository;

    public TopicRepositoryAdapter(
            CourseJpaRepository courseJpaRepository,
            TopicJpaRepository topicJpaRepository,
            UserJpaRepository userJpaRepository
    ) {
        this.courseJpaRepository = courseJpaRepository;
        this.topicJpaRepository = topicJpaRepository;
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public Topic save(Topic topic) {
        Optional<CourseEntity> courseEntity = courseJpaRepository.findByCourseId(topic.getCourse().getCourseId());
        if (courseEntity.isEmpty()) {
            throw new ValidationIntegrity("Course not found or inactive: " + topic.getTopicId());
        }

        Optional<UserEntity> userEntity = userJpaRepository.findByUserId(topic.getAuthor().getUserId());
        if (userEntity.isEmpty()) {
            throw new ValidationIntegrity("User not found or inactive: " + topic.getTopicId());
        }

        TopicEntity savedTopicEntity = topicJpaRepository
                .save(TopicEntityMapper.toEntity(topic, userEntity.get(), courseEntity.get()));
        return TopicEntityMapper.toModel(savedTopicEntity);
    }
}