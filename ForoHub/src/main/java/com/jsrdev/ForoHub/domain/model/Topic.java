package com.jsrdev.ForoHub.domain.model;

import com.jsrdev.ForoHub.common.TopicStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public class Topic {
    private UUID topicId;
    private String title;
    private String message;
    private LocalDateTime creationDate;
    private TopicStatus status;
    private Boolean active;
    private User author;
    private Course course;
    //private List<Reply> replies;

    public Topic(
            Boolean active,
            User author,
            Course course,
            LocalDateTime creationDate,
            String message,
            TopicStatus status,
            String title,
            UUID topicId
    ) {
        this.active = active;
        this.author = author;
        this.course = course;
        this.creationDate = creationDate;
        this.message = message;
        this.status = status;
        this.title = title;
        this.topicId = topicId;
    }

    public Boolean getActive() {
        return active;
    }

    public User getAuthor() {
        return author;
    }

    public Course getCourse() {
        return course;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public String getMessage() {
        return message;
    }

    public TopicStatus getStatus() {
        return status;
    }

    public String getTitle() {
        return title;
    }

    public UUID getTopicId() {
        return topicId;
    }
}
