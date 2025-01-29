package com.jsrdev.ForoHub.infrastructure.database.mysql.entity;

import com.jsrdev.ForoHub.common.TopicStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "topics")
@Entity(name = "Topic")
public class TopicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "topic_id", nullable = false, unique = true)
    private String topicId;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String message;
    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TopicStatus status;
    @Column(nullable = false)
    private Boolean active;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private UserEntity author;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private CourseEntity course;

    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ReplyEntity> replies;

    public TopicEntity() {
    }

    public TopicEntity(
            Boolean active,
            UserEntity author,
            CourseEntity course,
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

    public UserEntity getAuthor() {
        return author;
    }

    public CourseEntity getCourse() {
        return course;
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

    public List<ReplyEntity> getReplies() {
        return replies;
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
}
