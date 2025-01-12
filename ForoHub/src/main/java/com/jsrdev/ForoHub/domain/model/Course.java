package com.jsrdev.ForoHub.domain.model;

import com.jsrdev.ForoHub.common.CourseCategory;
import com.jsrdev.ForoHub.infrastructure.rest.dto.CourseRequest;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;

//@Getter
//@AllArgsConstructor
@NoArgsConstructor(force = false)
public class Course {
    private String courseId;
    private String name;
    private CourseCategory category;
    private Boolean active;

    public Course(Boolean active, CourseCategory category, String courseId, String name) {
        this.courseId = courseId;
        this.name = name;
        this.category = category;
        this.active = active;
    }

    public Course(@Valid CourseRequest courseRequest) {
        this.courseId = courseRequest.courseId();
        this.name = courseRequest.name();
        this.category = courseRequest.category();
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

    public String getName() {
        return name;
    }
}
