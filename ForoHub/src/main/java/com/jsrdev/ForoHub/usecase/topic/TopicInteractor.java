package com.jsrdev.ForoHub.usecase.topic;

import com.jsrdev.ForoHub.domain.model.Course;
import com.jsrdev.ForoHub.domain.model.Topic;
import com.jsrdev.ForoHub.domain.model.User;
import com.jsrdev.ForoHub.domain.repository.CourseRepositoryPort;
import com.jsrdev.ForoHub.domain.repository.TopicRepositoryPort;
import com.jsrdev.ForoHub.domain.repository.UserRepositoryPort;
import com.jsrdev.ForoHub.infrastructure.rest.dto.topic.TopicRequest;
import com.jsrdev.ForoHub.infrastructure.rest.mapper.TopicMapper;
import com.jsrdev.ForoHub.usecase.topic.validations.add.TopicValidator;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TopicInteractor implements ITopic {

    private final TopicRepositoryPort topicRepositoryPort;
    private final List<TopicValidator> validators;
    private final UserRepositoryPort userRepositoryPort;
    private final CourseRepositoryPort courseRepositoryPort;

    public TopicInteractor(
            CourseRepositoryPort courseRepositoryPort,
            TopicRepositoryPort topicRepositoryPort,
            List<TopicValidator> validators,
            UserRepositoryPort userRepositoryPort
    ) {
        this.courseRepositoryPort = courseRepositoryPort;
        this.topicRepositoryPort = topicRepositoryPort;
        this.validators = validators;
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public Topic create(TopicRequest topicRequest) {
        validators.forEach(v -> v.validate(topicRequest));

        User user = userRepositoryPort.findByUserId(topicRequest.authorId());
        Course course = courseRepositoryPort.findByCourseId(topicRequest.courseId());
        Topic topic = TopicMapper.toModel(topicRequest, user, course);

        return topicRepositoryPort.save(topic);
    }
}
