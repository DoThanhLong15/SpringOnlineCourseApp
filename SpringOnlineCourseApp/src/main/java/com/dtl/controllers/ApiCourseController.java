/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.controllers;

import com.dtl.DTO.CourseDetailDTO;
import com.dtl.DTO.CourseListDTO;
import com.dtl.DTO.CourseProgressDTO;
import com.dtl.components.ErrorResponseUtil;
import com.dtl.pojo.Course;
import com.dtl.pojo.CourseProgress;
import com.dtl.pojo.User;
import com.dtl.services.CourseProgressService;
import com.dtl.services.CourseService;
import com.dtl.services.CourseTagService;
import com.dtl.services.LessonService;
import com.dtl.services.UserService;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
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
import org.springframework.web.bind.annotation.RequestParam;
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
    private CourseProgressService courseProgressService;
    @Autowired
    private LessonService lessonService;
    @Autowired
    private CourseTagService courseTagService;
    @Autowired
    private ErrorResponseUtil errorResponseUtil;

    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<Object> getCoursesList(@RequestParam Map<String, String> params, Principal user, Locale locale) {
        Map<String, Object> response = new HashMap<>();

        User userDetail = user == null ? null : this.userService.getUserByUsername(user.getName());

        String page = "1";
        String keyword = "";
        if (params != null && !params.isEmpty()) {
            String paramPage = params.get("page");
            if (paramPage != null && !paramPage.isEmpty()) {
                page = paramPage;
            }

            String paramKeyword = params.get("q");
            if (paramKeyword != null && !paramKeyword.isEmpty()) {
                keyword = paramKeyword;
            }
        }

        List<CourseListDTO> coursesList = this.courseService.getCourse(params).stream()
                .map(course -> {
                    boolean isEnrolled = false;
                    if (userDetail != null) {
                        System.out.println(course);
                        isEnrolled = this.courseService.hasEnrolled(course, userDetail);
                    }
                    return new CourseListDTO(course, isEnrolled);
                }).collect(Collectors.toList());

        int totalCourse = this.courseService.countCourses();

        response.put("data", coursesList);
        response.put("page", page);
        response.put("count", coursesList.size());
        response.put("keyword", keyword);
        response.put("total", totalCourse);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/{courseId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<Object> getCourseDetail(@PathVariable(value = "courseId") int id, Principal user, Locale locale) {

        Map<String, Object> response = new HashMap<>();

        User userDetail = user == null ? null : this.userService.getUserByUsername(user.getName());
        Course course = this.courseService.getCourseById(id);
        boolean isEnrolled = false;
        if (userDetail != null && userDetail.getId() != null) {
            isEnrolled = this.courseService.hasEnrolled(course, userDetail);
        }

        Map<String, String> params = new HashMap<>();
        params.put("courseId", String.valueOf(id));
        course.setLessonCollection(this.lessonService.getLessons(params));
        course.setCourseTagCollection(this.courseTagService.getCourseTags(id, -1));

        CourseDetailDTO courseDetail = new CourseDetailDTO(course, isEnrolled);

        response.put("data", courseDetail);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/{courseId}/progress", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<Object> getCourseProgress(@PathVariable(value = "courseId") int courseId, Principal user, Locale locale) {

        Map<String, Object> response = new HashMap<>();

        User userDetail = this.userService.getUserByUsername(user.getName());
        Course course = this.courseService.getCourseById(courseId);
        CourseProgress progress = this.courseProgressService.getCourseProgress(userDetail.getId(), courseId);

        if (progress == null) {
            return this.errorResponseUtil.buildErrorResponse("user.permission.deny", locale);
        }

        Integer totalCourseContent = this.courseService.countContentInCourse(courseId);
        this.courseService.checkCourseProgress(course, userDetail, progress, totalCourseContent);

        boolean complete = false;
        if (progress.getIsComplete()) {
            complete = true;
        }
        response.put("data", new CourseProgressDTO(totalCourseContent, progress.getLessonCompleteCount(), complete));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
