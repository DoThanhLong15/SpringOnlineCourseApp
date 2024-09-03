/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.controllers;

import com.dtl.DTO.LessonContentDetailDTO;
import com.dtl.pojo.Course;
import com.dtl.pojo.Lesson;
import com.dtl.pojo.LessonContent;
import com.dtl.pojo.User;
import com.dtl.services.CourseService;
import com.dtl.services.LessonContentService;
import com.dtl.services.UserService;
import java.security.Principal;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
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
@RequestMapping("/api/courses/{courseId}/lessons/{lessonId}/contents")
public class ApiLessonContentController {

    @Autowired
    private LessonContentService lessonContentService;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private UserService userService;
    @Autowired
    private CourseService courseService;

    @GetMapping("/{contentId}")
    @CrossOrigin
    public ResponseEntity<Object> getContent(Locale locale, Principal user,
            @PathVariable(value = "contentId") int contentId,
            @PathVariable(value = "courseId") int courseId) {

        Map<String, Object> response = new HashMap<>();

        User userDetail = this.userService.getUserByUsername(user.getName());
        Course course = this.courseService.getCourseById(courseId);

        if (!this.courseService.hasEnrolled(course, userDetail) 
                && !this.courseService.isCourseLecturer(course, userDetail)) {
            response.put("error", messageSource.getMessage("user.permission.deny", null, locale));

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            response.put("data", new LessonContentDetailDTO(this.lessonContentService.getLessonContentById(contentId)));

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (EntityNotFoundException ex) {
            System.out.println(ex.getMessage());

            response.put("error", messageSource.getMessage("lessonContent.notFound.errMsg", null, locale));

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());

            response.put("error", messageSource.getMessage("system.errMsg", null, locale));

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/")
    @CrossOrigin
    public ResponseEntity<Object> addLessonContent(@Valid @RequestBody LessonContent lessonContent,
            BindingResult bindingResult, Principal user, Locale locale,
            @PathVariable(value = "courseId") int courseId,
            @PathVariable(value = "lessonId") int lessonId) {

        Map<String, Object> response = new HashMap<>();

        User userDetail = this.userService.getUserByUsername(user.getName());
        Course course = this.courseService.getCourseById(courseId);

        if (!this.courseService.isCourseLecturer(course, userDetail)) {
            response.put("error", messageSource.getMessage("user.permission.deny", null, locale));

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        if (bindingResult.hasErrors()) {
            response.put("error", new HashMap<>());
            Map<String, String> errors = (Map<String, String>) response.get("error");

            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        }

        if (this.lessonContentService.hasLessonContent(lessonId, lessonContent.getTitle())) {
            response.put("error", messageSource.getMessage("lessonContent.title.exist.errMsg", null, locale));

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        lessonContent.setLessonId(new Lesson());
        lessonContent.getLessonId().setId(lessonId);
        this.lessonContentService.saveLessonContent(lessonContent);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
