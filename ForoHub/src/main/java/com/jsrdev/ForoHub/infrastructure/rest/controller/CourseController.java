package com.jsrdev.ForoHub.infrastructure.rest.controller;

import com.jsrdev.ForoHub.domain.model.Course;
import com.jsrdev.ForoHub.infrastructure.rest.dto.course.CourseRequest;
import com.jsrdev.ForoHub.infrastructure.rest.dto.course.CourseResponse;
import com.jsrdev.ForoHub.infrastructure.rest.dto.DeleteResponse;
import com.jsrdev.ForoHub.infrastructure.rest.dto.course.UpdateCourse;
import com.jsrdev.ForoHub.infrastructure.rest.mapper.CourseMapper;
import com.jsrdev.ForoHub.usecase.course.CourseInteractor;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

//@RequiredArgsConstructor
@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseInteractor courseInteractor;

    public CourseController(CourseInteractor courseInteractor) {
        this.courseInteractor = courseInteractor;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<CourseResponse> create(
            @Valid @RequestBody CourseRequest courseRequest,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        Course course = courseInteractor.save(courseRequest);

        URI uri = uriComponentsBuilder.path("/api/courses/{id}").buildAndExpand(course.getCourseId()).toUri();

        return ResponseEntity.created(uri).body(CourseMapper.toResponse(course));
    }

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<CourseResponse>>> getCourses(
            @PageableDefault(size = 15) Pageable pagination,
            PagedResourcesAssembler<CourseResponse> assembler
    ) {
        Page<Course> coursesPage = courseInteractor.findByActiveTrue(pagination);

        List<CourseResponse> courseResponses = coursesPage
                .getContent()
                .stream()
                .map(CourseMapper::toResponse)
                .toList();

        Page<CourseResponse> courseResponsePage = new PageImpl<>(
                courseResponses,
                pagination,
                coursesPage.getTotalElements()
        );

        return ResponseEntity.ok(assembler.toModel(courseResponsePage));
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<CourseResponse> getCourse(@PathVariable String courseId) {
        Course course = findByCourseIdAndActive(courseId);

        return ResponseEntity.ok(CourseMapper.toResponse(course));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<CourseResponse> update(@Valid @RequestBody UpdateCourse update) {
        Course course = findByCourseIdAndActive(update.courseId());

        Course updated = courseInteractor.update(course, update);

        return ResponseEntity.ok(CourseMapper.toResponse(updated));
    }

    @DeleteMapping("/{courseId}")
    @Transactional
    public ResponseEntity<DeleteResponse> delete(@PathVariable String courseId) {
        Course course = findByCourseIdAndActive(courseId);

        Boolean isDeleted = courseInteractor.delete(course);

        String message = isDeleted ? "Course successfully deleted." : "Failed to delete course.";
        DeleteResponse response = new DeleteResponse(isDeleted, message);

        return ResponseEntity.ok(response);
    }

    private Course findByCourseIdAndActive(String courseId) {
        Course course = courseInteractor.findByCourseIdAndActiveTrue(courseId);
        if (course == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found or inactive.");
        }
        return course;
    }
}
