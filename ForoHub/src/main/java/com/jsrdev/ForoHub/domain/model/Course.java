package com.jsrdev.ForoHub.domain.model;

import com.jsrdev.ForoHub.common.CourseCategory;
import com.jsrdev.ForoHub.infrastructure.rest.dto.course.CourseRequest;
import com.jsrdev.ForoHub.infrastructure.rest.dto.course.UpdateCourse;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;

//@Getter
//@AllArgsConstructor
@NoArgsConstructor()
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

    public Course update(Course course, @Valid UpdateCourse update) {
        if (update.name() != null) {
            course.name = update.name();
        }
        if (update.category() != null) {
            course.category = update.category();
        }
        return course;
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

    public void delete(Course course) {
        course.active = false;
    }
}
