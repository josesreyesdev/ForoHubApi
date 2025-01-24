package com.jsrdev.ForoHub.domain.model;

import com.jsrdev.ForoHub.common.CourseCategory;
import lombok.NoArgsConstructor;

//@Getter
//@AllArgsConstructor
@NoArgsConstructor()
public class Course {
    private String courseId;
    private String name;
    private CourseCategory category;
    private Boolean active;

    public Course(String courseId, String name, CourseCategory category) {
        this.courseId = courseId;
        this.name = name;
        this.category = category;
        this.active = true;
    }

    public Course update(Course course, String name, CourseCategory category) {
        if (name != null) {
            course.name = name;
        }
        if (category != null) {
            course.category = category;
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

    public void delete() {
        this.active = false;
    }
}
