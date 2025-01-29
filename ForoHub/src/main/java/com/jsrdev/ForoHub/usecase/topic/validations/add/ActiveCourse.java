package com.jsrdev.ForoHub.usecase.topic.validations.add;

import com.jsrdev.ForoHub.domain.model.Course;
import com.jsrdev.ForoHub.domain.repository.CourseRepositoryPort;
import com.jsrdev.ForoHub.infrastructure.exceptions.ValidationIntegrity;
import com.jsrdev.ForoHub.infrastructure.rest.dto.topic.TopicRequest;
import org.springframework.stereotype.Component;

@Component
public class ActiveCourse implements TopicValidator{

    private final CourseRepositoryPort courseRepositoryPort;

    public ActiveCourse(CourseRepositoryPort courseRepositoryPort) {
        this.courseRepositoryPort = courseRepositoryPort;
    }

    @Override
    public void validate(TopicRequest topicRequest) {
        Course course = courseRepositoryPort.findByCourseIdAndActiveTrue(topicRequest.courseId());

        if (course == null) {
            throw new ValidationIntegrity("Course not found or inactive: " + topicRequest.courseId());
        }
    }
}
