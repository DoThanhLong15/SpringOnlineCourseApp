/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.controllers;

import com.dtl.DTO.LessonDetailDTO;
import com.dtl.pojo.Course;
import com.dtl.pojo.Lesson;
import com.dtl.pojo.User;
import com.dtl.services.CourseService;
import com.dtl.services.LessonService;
import com.dtl.services.UserService;
import java.security.Principal;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    private MessageSource messageSource;

    @GetMapping("/{lessonId}")
    @CrossOrigin
    public ResponseEntity<Object> getLessonDetail(Locale locale, Principal user,
            @PathVariable(value = "lessonId") int lessonId,
            @PathVariable(value = "courseId") int courseId) {
        Map<String, Object> response = new HashMap<>();

        User userDetail = this.userService.getUserByUsername(user.getName());
        
        Course course = this.courseService.getCourseById(courseId);
        if(course == null){
            response.put("error", messageSource.getMessage("course.notFound.errMsg", null, locale));
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        if (!this.courseService.isCourseLecturer(course, userDetail)
                && !this.courseService.hasEnrolled(course, userDetail)) {
            response.put("error", messageSource.getMessage("user.permission.deny", null, locale));
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            Lesson lesson = this.lessonService.getLessonById(lessonId);
            response.put("data", new LessonDetailDTO(lesson));

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (EntityNotFoundException ex) {
            System.out.println(ex.getMessage());
            response.put("error", messageSource.getMessage("lesson.notFound.errMsg", null, locale));
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            response.put("error", messageSource.getMessage("system.errMsg", null, locale));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    @CrossOrigin
    public ResponseEntity<Object> createLesson(@Valid @RequestBody Lesson lesson,
            BindingResult bindingResult, Locale locale, Principal user,
            @PathVariable(value = "courseId") int courseId) {

        Map<String, Object> response = new HashMap<>();
        User userDetail = this.userService.getUserByUsername(user.getName());
        
        Course course = this.courseService.getCourseById(courseId);
        if(course == null){
            response.put("error", messageSource.getMessage("course.notFound.errMsg", null, locale));
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        if (!this.courseService.isCourseLecturer(course, userDetail)) {
            response.put("error", messageSource.getMessage("user.permission.deny", null, locale));
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        if (bindingResult.hasErrors()) {
            response.put("error", new HashMap<String, String>());
            Map<String, String> errors = (Map<String, String>) response.get("error");

            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            Map<String, String> params = new HashMap<>();
            params.put("title", lesson.getTitle());
            params.put("courseId", String.valueOf(courseId));
            if (!this.lessonService.getLessons(params).isEmpty()) {
                response.put("error", messageSource.getMessage("lesson.title.exist.errMsg", null, locale));
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }

            this.lessonService.saveLesson(lesson);

            return new ResponseEntity<>(HttpStatus.CREATED);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            response.put("error", messageSource.getMessage("system.errMsg", null, locale));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
