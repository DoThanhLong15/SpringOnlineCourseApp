/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.controllers;

import com.dtl.DTO.CourseDetailDTO;
import com.dtl.DTO.CourseListDTO;
import com.dtl.pojo.Course;
import com.dtl.pojo.User;
import com.dtl.services.CourseProgressService;
import com.dtl.services.CourseService;
import com.dtl.services.UserService;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author LONG
 */
@RestController
@RequestMapping("/api/courses")
public class ApiCourseController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private UserService userService;
    @Autowired
    private MessageSource messageSource;

    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<Object> getCoursesList(Map<String, String> params, Principal user, Locale locale) {
        Map<String, Object> response = new HashMap<>();

        try {
            User userDetail = user == null ? null : this.userService.getUserByUsername(user.getName());

            String page = params.get("page");
            response.put("page", page == null || page.isEmpty() ? 1 : page);

            List<CourseListDTO> coursesList = this.courseService.getCourse(params).stream()
                    .map(course -> {
                        boolean isEnrolled = false;
                        if (userDetail != null && userDetail.getId() != null) {
                            isEnrolled = this.courseService.hasEnrolled(course, userDetail);
                        }
                        return new CourseListDTO(course, isEnrolled);
                    }).collect(Collectors.toList());
            
            response.put("data", coursesList);
            response.put("count", coursesList.size());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            response.put("error", messageSource.getMessage("system.errMsg", null, locale));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/{courseId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<Object> getCourseDetail(@PathVariable(value = "courseId") int id, Principal user, Locale locale) {

        Map<String, Object> response = new HashMap<>();

        try {
            User userDetail = user == null ? new User() : this.userService.getUserByUsername(user.getName());
            Course course = this.courseService.getCourseById(id);
            boolean isEnrolled = false;
            if (userDetail != null && userDetail.getId() != null) {
                isEnrolled = this.courseService.hasEnrolled(course, userDetail);
            }

            CourseDetailDTO courseDetail = new CourseDetailDTO(course, isEnrolled);

            response.put("data", courseDetail);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (EntityNotFoundException ex) {
            System.out.println(ex.getMessage());
            response.put("error", messageSource.getMessage("course.notFound.errMsg", null, locale));
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            response.put("error", messageSource.getMessage("system.errMsg", null, locale));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
