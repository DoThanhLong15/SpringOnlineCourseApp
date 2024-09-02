/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.controllers;

import com.dtl.DTO.CourseListDTO;
import com.dtl.DTO.LessonDetailDTO;
import com.dtl.pojo.Lesson;
import com.dtl.pojo.User;
import com.dtl.services.CourseService;
import com.dtl.services.CourseTagService;
import com.dtl.services.LessonService;
import com.dtl.services.UserService;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
@RequestMapping("/api/lessons")
public class ApiLessonController {

    @Autowired
    private LessonService lessonService;
    @Autowired
    private UserService userService;
    @Autowired
    private MessageSource messageSource;

    @GetMapping("/{lessonId}")
    @CrossOrigin
    public ResponseEntity<Object> createLesson(Locale locale, Principal user, @PathVariable(value = "lessonId") int id) {
        Map<String, Object> response = new HashMap<>();

        try {
            Lesson lesson = this.lessonService.getLessonById(id);
            
            response.put("data", new LessonDetailDTO(lesson));
            
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (EntityNotFoundException ex){
            System.out.println(ex.getMessage());

            response.put("error", messageSource.getMessage("lesson.notFound.errMsg", null, locale));

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }catch (Exception ex) {
            System.out.println(ex.getMessage());

            response.put("error", messageSource.getMessage("system.errMsg", null, locale));

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/")
    @CrossOrigin
    public ResponseEntity<Object> createLesson(@Valid @RequestBody Lesson lesson,
            BindingResult bindingResult, Locale locale, Principal user) {

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Map<String, Object> response = new HashMap<>();

        response.put("error", new HashMap<String, String>());

        User u = this.userService.getUserByUsername(user.getName());

        if (Objects.equals(lesson.getCourseId().getLecturerId().getId(), u.getId())) {
            if (bindingResult.hasErrors()) {
                Map<String, String> errors = (Map<String, String>) response.get("error");

                for (FieldError error : bindingResult.getFieldErrors()) {
                    errors.put(error.getField(), error.getDefaultMessage());
                }

                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

            }

            Map<String, String> params = new HashMap<>();
            params.put("title", lesson.getTitle());
            params.put("courseId", String.valueOf(lesson.getCourseId().getId()));

            if (!this.lessonService.getLessons(params).isEmpty()) {
                response.put("error", messageSource.getMessage("lesson.title.exist.errMsg", null, locale));

                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }

            this.lessonService.saveLesson(lesson);

            return new ResponseEntity<>(HttpStatus.CREATED);
        }

        response.put("error", messageSource.getMessage("user.permission.deny", null, locale));

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
