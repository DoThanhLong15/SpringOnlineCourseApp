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
    @Autowired
    private LessonService lessonService;
    @Autowired
    private CourseTagService courseTagService;

    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<Object> getCoursesList(Map<String, String> params) {
        Map<String, Object> response = new HashMap<>();

        String page = params.get("page");
        response.put("page", page == null || page.isEmpty() ? 1 : page);

        List<CourseListDTO> coursesList = this.courseService.getCourse(params).stream().
                map(course -> courseList(course)
        ).collect(Collectors.toList());
        response.put("data", coursesList);
        response.put("count", coursesList.size());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/{courseId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<Object> getCourseDetail(@PathVariable(value = "courseId") int id) {
        Map<String, Object> response = new HashMap<>();

        Course course = this.courseService.getCourseById(id);
        CourseDetailDTO courseDetail = courseDetail(course);
        
        response.put("data", courseDetail);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private CourseListDTO courseList(Course course) {
        return new CourseListDTO(
                course.getId(),
                course.getTitle(),
                course.getRating(),
                course.getRatingCount(),
                course.getPrice(),
                course.getImage(),
                course.getCreatedDate(),
                course.getUpdatedDate(),
                course.getParticipantCount()
        );
    }

    private CourseDetailDTO courseDetail(Course course) {
        List<LessonListDTO> lessonList = this.lessonService.getLessons(course.getId()).stream()
                .map(lesson -> lessonList(lesson))
                .collect(Collectors.toList());
        
        List<Tag> tagList = this.courseTagService.getCourseTags(course.getId(), -1).stream()
                .map(courseTag -> courseTag.getTagId())
                .collect(Collectors.toList());
        
        User user = course.getLecturerId();
        UserDTO lecturer = new UserDTO(
                user.getId(), 
                user.getFirstName(),
                user.getLastName(),
                user.getAvatar()
        );
        
        CourseDetailDTO temp = new CourseDetailDTO(
                courseList(course),
                tagList,
                lessonList,
                lecturer
        );
        
        return temp;
    }
    
    private LessonListDTO lessonList(Lesson lesson) {
        return new LessonListDTO(
                lesson.getId(),
                lesson.getTitle(),
                lesson.getCreatedDate(),
                lesson.getUpdatedDate()
        );
    }
}
