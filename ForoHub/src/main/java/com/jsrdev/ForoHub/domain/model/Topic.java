package com.jsrdev.ForoHub.domain.model;

import com.jsrdev.ForoHub.common.TopicStatus;

import java.time.LocalDateTime;

public class Topic {
    private String topicId;
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
            String topicId
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

    public String getTopicId() {
        return topicId;
    }

    public Topic update(String title, String message, TopicStatus status, Course course) {
        this.title = (title != null) ? title : this.title;
        this.message = (message != null) ? message : this.message;
        this.status = (status != null) ? status : this.status;
        this.course = (course != null) ? course : this.course;
        return this;
    }
}
