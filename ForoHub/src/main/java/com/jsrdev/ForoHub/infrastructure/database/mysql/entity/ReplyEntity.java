package com.jsrdev.ForoHub.infrastructure.database.mysql.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name = "Reply")
@Table(name = "replies")
public class ReplyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String replyId;
    private String message;
    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;
    private Boolean solution;
    private Boolean active;
    @ManyToOne
    private UserEntity author;
    @ManyToOne
    private TopicEntity topic;

    public ReplyEntity() {
    }

    public ReplyEntity(
            Boolean active,
            UserEntity author,
            LocalDateTime creationDate,
            String message,
            String replyId,
            Boolean solution,
            TopicEntity topic
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

    public UserEntity getAuthor() {
        return author;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public Long getId() {
        return id;
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

    public TopicEntity getTopic() {
        return topic;
    }
}
