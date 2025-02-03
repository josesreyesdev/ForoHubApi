package com.jsrdev.ForoHub.domain.model;

import java.time.LocalDateTime;

public class Reply {
    private String replyId;
    private String message;
    private LocalDateTime creationDate;
    private Boolean solution;
    private Boolean active;
    private User author;
    private Topic topic;

    public Reply(
            Boolean active,
            User author,
            LocalDateTime creationDate,
            String message,
            String replyId,
            Boolean solution,
            Topic topic
    ) {
        this.active = active;
        this.author = author;
        this.creationDate = creationDate;
        this.message = message;
        this.replyId = replyId;
        this.solution = solution;
        this.topic = topic;
    }

    public Boolean getActive() {
        return active;
    }

    public User getAuthor() {
        return author;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public String getMessage() {
        return message;
    }

    public String getReplyId() {
        return replyId;
    }

    public Boolean getSolution() {
        return solution;
    }

    public Topic getTopic() {
        return topic;
    }
}
