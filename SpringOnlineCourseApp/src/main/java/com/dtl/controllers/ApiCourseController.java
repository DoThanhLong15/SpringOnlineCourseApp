/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.controllers;

import com.dtl.DTO.CourseDetailDTO;
import com.dtl.DTO.CourseListDTO;
import com.dtl.DTO.LessonListDTO;
import com.dtl.DTO.UserDTO;
import com.dtl.pojo.Course;
import com.dtl.pojo.Lesson;
import com.dtl.pojo.Tag;
import com.dtl.pojo.User;
import com.dtl.services.CourseService;
import com.dtl.services.CourseTagService;
import com.dtl.services.LessonService;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<Object> getCoursesList(Map<String, String> params) {
        Map<String, Object> response = new HashMap<>();

        String page = params.get("page");
        response.put("page", page == null || page.isEmpty() ? 1 : page);

        List<CourseListDTO> coursesList = this.courseService.getCourse(params).stream().
                map(course -> new CourseListDTO(course)
        ).collect(Collectors.toList());
        response.put("data", coursesList);
        response.put("count", coursesList.size());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/{courseId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<Object> getCourseDetail(@PathVariable(value = "courseId") int id, Principal user) {
        
        Map<String, Object> response = new HashMap<>();

        Course course = this.courseService.getCourseById(id);
        CourseDetailDTO courseDetail = new CourseDetailDTO(course);
        
        response.put("data", courseDetail);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
