package com.jsrdev.ForoHub.infrastructure.rest.controller;

import com.jsrdev.ForoHub.domain.model.Course;
import com.jsrdev.ForoHub.infrastructure.rest.dto.CourseRequest;
import com.jsrdev.ForoHub.infrastructure.rest.dto.CourseResponse;
import com.jsrdev.ForoHub.infrastructure.rest.dto.UpdateCourse;
import com.jsrdev.ForoHub.infrastructure.rest.mapper.ControllerCourseMapper;
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
@RequestMapping("/api/courses")
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
        Course course = courseInteractor.save(new Course(courseRequest));
        URI uri = uriComponentsBuilder.path("/api/courses/{id}").buildAndExpand(course.getCourseId()).toUri();

        return ResponseEntity.created(uri).body(ControllerCourseMapper.fromCourseToCourseResponse(course));
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
                .map(ControllerCourseMapper::fromCourseToCourseResponse)
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
        Course course = courseInteractor.findByCourseIdAndActiveTrue(courseId);
        if (course == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found or inactive.");
        }
        return ResponseEntity.ok(ControllerCourseMapper.fromCourseToCourseResponse(course));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<CourseResponse> update(@Valid @RequestBody UpdateCourse update) {
        Course course = courseInteractor.findByCourseIdAndActiveTrue(update.courseId());
        if (course == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found or inactive.");
        }

        Course updated = courseInteractor.update(course.update(course, update));
        return ResponseEntity.ok(ControllerCourseMapper.fromCourseToCourseResponse(updated));
    }
}
