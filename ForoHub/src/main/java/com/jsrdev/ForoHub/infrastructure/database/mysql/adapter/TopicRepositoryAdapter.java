package com.jsrdev.ForoHub.infrastructure.database.mysql.adapter;

import com.jsrdev.ForoHub.domain.model.Topic;
import com.jsrdev.ForoHub.domain.repository.TopicRepositoryPort;
import com.jsrdev.ForoHub.infrastructure.database.mysql.entity.CourseEntity;
import com.jsrdev.ForoHub.infrastructure.database.mysql.entity.TopicEntity;
import com.jsrdev.ForoHub.infrastructure.database.mysql.entity.UserEntity;
import com.jsrdev.ForoHub.infrastructure.database.mysql.mapper.TopicEntityMapper;
import com.jsrdev.ForoHub.infrastructure.database.mysql.repository.TopicJpaRepository;
import com.jsrdev.ForoHub.infrastructure.exceptions.ValidationIntegrity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TopicRepositoryAdapter implements TopicRepositoryPort {

    private final TopicJpaRepository topicJpaRepository;
    private final UserRepositoryAdapter userRepositoryAdapter;
    private final CourseRepositoryAdapter courseRepositoryAdapter;

    public TopicRepositoryAdapter(
            CourseRepositoryAdapter courseRepositoryAdapter,
            TopicJpaRepository topicJpaRepository,
            UserRepositoryAdapter userRepositoryAdapter
    ) {
        this.topicJpaRepository = topicJpaRepository;
        this.userRepositoryAdapter = userRepositoryAdapter;
        this.courseRepositoryAdapter = courseRepositoryAdapter;
    }

    @Override
    public Topic save(Topic topic) {
        CourseEntity courseEntity = courseRepositoryAdapter
                .findByCourseIdAndActiveTrueEntity(topic.getCourse().getCourseId());

        UserEntity userEntity = userRepositoryAdapter
                .findByUserIdAndActiveTrueWithProfiles(topic.getAuthor().getUserId());

        TopicEntity savedTopicEntity = topicJpaRepository
                .save(TopicEntityMapper.toEntity(topic, userEntity, courseEntity));
        return TopicEntityMapper.toModel(savedTopicEntity);
    }

    @Override
    public Page<Topic> findAllByActiveTrue(Pageable pagination) {
        Page<TopicEntity> topicEntityPage = topicJpaRepository.findAllByActiveTrue(pagination);

        List<Topic> topics = topicEntityPage.getContent().stream()
                .map(TopicEntityMapper::toModel)
                .toList();

        return new PageImpl<>(
                topics,
                pagination,
                topicEntityPage.getTotalElements()
        );
    }

    @Override
    public Topic findByTopicIdAndActiveTrue(String topicId) {
        TopicEntity topicEntity = findByTopicIdAndActiveTrueEntity(topicId);
        return TopicEntityMapper.toModel(topicEntity);
    }

    @Override
    public Topic update(Topic topic) {
        TopicEntity topicEntity = findByTopicIdAndActiveTrueEntity(topic.getTopicId());

        CourseEntity courseEntity = courseRepositoryAdapter
                .findByCourseIdAndActiveTrueEntity(topic.getCourse().getCourseId());

        topicEntity.update(
                topic.getTitle(),
                topic.getMessage(),
                topic.getStatus(),
                courseEntity
        );

        return TopicEntityMapper.toModel(topicEntity);
    }

    @Override
    public Topic delete(Topic topic) {
        TopicEntity topicEntity = findByTopicIdAndActiveTrueEntity(topic.getTopicId());
        return TopicEntityMapper.toModel(topicEntity.delete());
    }

    private TopicEntity findByTopicIdAndActiveTrueEntity(String topicId) {
        Optional<TopicEntity> optionalTopicEntity = topicJpaRepository
                .findByTopicIdAndActiveTrue(topicId);
        if (optionalTopicEntity.isEmpty()) {
            throw new ValidationIntegrity("Topic not found or inactive: " + topicId);
        }
        return optionalTopicEntity.get();
    }
}