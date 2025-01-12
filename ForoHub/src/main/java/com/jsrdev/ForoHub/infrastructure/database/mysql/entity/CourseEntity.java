package com.jsrdev.ForoHub.infrastructure.database.mysql.entity;

import com.jsrdev.ForoHub.common.CourseCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Table(name = "courses")
@Entity(name = "Course")
//@Getter
//@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class CourseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "course_id")
    private String courseId;
    private String name;
    @Enumerated(EnumType.STRING)
    private CourseCategory category;
    private Boolean active;

    public CourseEntity() {}

    public CourseEntity(String courseId, String name, CourseCategory category) {
        this.courseId = courseId;
        this.name = name;
        this.category = category;
        this.active = true;
    }

    public Boolean getActive() {
        return active;
    }

    public CourseCategory getCategory() {
        return category;
    }

    public String getCourseId() {
        return courseId;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
