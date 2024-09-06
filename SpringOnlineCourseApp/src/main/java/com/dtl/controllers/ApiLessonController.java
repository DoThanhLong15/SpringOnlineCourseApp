/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.controllers;

import com.dtl.DTO.LessonDetailDTO;
import com.dtl.components.ErrorResponseUtil;
import com.dtl.pojo.Course;
import com.dtl.pojo.Lesson;
import com.dtl.pojo.User;
import com.dtl.services.CourseService;
import com.dtl.services.LessonContentService;
import com.dtl.services.LessonService;
import com.dtl.services.UserService;
import java.security.Principal;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author LONG
 */
@RestController
@RequestMapping("/api/courses/{courseId}/lessons")
public class ApiLessonController {

    @Autowired
    private LessonService lessonService;
    @Autowired
    private UserService userService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private LessonContentService lessonContentService;
    @Autowired
    private ErrorResponseUtil errorResponseUtil;

    @GetMapping("/{lessonId}")
    @CrossOrigin
    public ResponseEntity<Object> getLessonDetail(Locale locale, Principal user,
            @PathVariable(value = "lessonId") int lessonId,
            @PathVariable(value = "courseId") int courseId) {
        Map<String, Object> response = new HashMap<>();

        User userDetail = this.userService.getUserByUsername(user.getName());
        Course course = this.courseService.getCourseById(courseId);

        if (!this.courseService.isCourseLecturer(course, userDetail)
                && !this.courseService.hasEnrolled(course, userDetail)) {
            return this.errorResponseUtil.buildErrorResponse("user.permission.deny", locale);
        }

        Lesson lesson = this.lessonService.getLessonById(lessonId);
        lesson.setLessonContentCollection(this.lessonContentService.getLessonContent(lesson.getId()));

        response.put("data", new LessonDetailDTO(lesson));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/")
    @CrossOrigin
    public ResponseEntity<Object> createLesson(@Valid @RequestBody Lesson lesson,
            BindingResult bindingResult, Locale locale, Principal user,
            @PathVariable(value = "courseId") int courseId) {

        Map<String, Object> response = new HashMap<>();
        User userDetail = this.userService.getUserByUsername(user.getName());
        Course course = this.courseService.getCourseById(courseId);

        if (!this.courseService.isCourseLecturer(course, userDetail)) {
            return this.errorResponseUtil.buildErrorResponse("user.permission.deny", locale);
        }

        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            response.put("error", errors);

            return this.errorResponseUtil.buildErrorResponse(response, locale);
        }

        Map<String, String> params = new HashMap<>();
        params.put("title", lesson.getTitle());
        params.put("courseId", String.valueOf(courseId));

        if (!this.lessonService.getLessons(params).isEmpty()) {
            return this.errorResponseUtil.buildErrorResponse("lesson.title.exist.errMsg", locale);
        }

        lesson.setCourseId(course);
        this.lessonService.saveLesson(lesson);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{lessonId}")
    @CrossOrigin
    public ResponseEntity<Object> editLesson(@Valid @RequestBody Lesson newLesson, 
            Locale locale, Principal user,
            @PathVariable(value = "courseId") int courseId,
            @PathVariable(value = "lessonId") int lessonId) {

        User userDetail = this.userService.getUserByUsername(user.getName());
        Course course = this.courseService.getCourseById(courseId);

        if (!this.courseService.isCourseLecturer(course, userDetail)) {
            return this.errorResponseUtil.buildErrorResponse("user.permission.deny", locale);
        }

        Lesson oldLesson = this.lessonService.getLessonById(lessonId);

        if (newLesson.getTitle() != null && !newLesson.getTitle().isEmpty()) {
            oldLesson.setTitle(newLesson.getTitle());
        }

        this.lessonService.saveLesson(oldLesson);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{lessonId}")
    @CrossOrigin
    public ResponseEntity<Object> deleteLesson(Locale locale, Principal user,
            @PathVariable(value = "courseId") int courseId,
            @PathVariable(value = "lessonId") int lessonId) {

        User userDetail = this.userService.getUserByUsername(user.getName());
        Course course = this.courseService.getCourseById(courseId);

        if (!this.courseService.isCourseLecturer(course, userDetail)) {
            return this.errorResponseUtil.buildErrorResponse("user.permission.deny", locale);
        }

        Lesson lesson = this.lessonService.getLessonById(lessonId);
        lesson.setLessonContentCollection(this.lessonContentService.getLessonContent(lesson.getId()));

        this.lessonService.deleteLesson(lesson);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
