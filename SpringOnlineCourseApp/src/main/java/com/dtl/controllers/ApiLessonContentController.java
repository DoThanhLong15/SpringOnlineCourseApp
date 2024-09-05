/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.controllers;

import com.dtl.DTO.LessonContentDetailDTO;
import com.dtl.components.ErrorResponseUtil;
import com.dtl.pojo.Course;
import com.dtl.pojo.CourseProgress;
import com.dtl.pojo.DoingExercise;
import com.dtl.pojo.ExerciseStatus;
import com.dtl.pojo.Lesson;
import com.dtl.pojo.LessonContent;
import com.dtl.pojo.User;
import com.dtl.services.CourseProgressService;
import com.dtl.services.CourseService;
import com.dtl.services.DoingExerciseService;
import com.dtl.services.LessonContentService;
import com.dtl.services.LessonService;
import com.dtl.services.UserService;
import java.math.BigDecimal;
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
@RequestMapping("/api/courses/{courseId}/lessons/{lessonId}/contents")
public class ApiLessonContentController {

    @Autowired
    private LessonContentService lessonContentService;
    @Autowired
    private UserService userService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private DoingExerciseService doingExerciseServise;
    @Autowired
    private CourseProgressService courseProgressService;
    @Autowired
    private LessonService lessonService;
    @Autowired
    private ErrorResponseUtil errorResponseUtil;

    @GetMapping("/{contentId}")
    @CrossOrigin
    public ResponseEntity<Object> getContent(Locale locale, Principal user,
            @PathVariable(value = "contentId") int contentId,
            @PathVariable(value = "courseId") int courseId,
            @PathVariable(value = "lessonId") int lessonId) {

        Map<String, Object> response = new HashMap<>();
        User userDetail = this.userService.getUserByUsername(user.getName());
        Course course = this.courseService.getCourseById(courseId);
        Lesson lesson = this.lessonService.getLessonById(lessonId);
        LessonContent content = this.lessonContentService.getLessonContentById(contentId);

        if (!this.courseService.hasEnrolled(course, userDetail)
                && !this.courseService.isCourseLecturer(course, userDetail)) {
            return this.errorResponseUtil.buildErrorResponse("user.permission.deny", locale);
        }

        LessonContentDetailDTO lessonContent = new LessonContentDetailDTO(content);
        lessonContent.setHasLearn(false);

        handleExercise(userDetail, content, lessonContent, course);

        response.put("data", lessonContent);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/")
    @CrossOrigin
    public ResponseEntity<Object> addLessonContent(@Valid @RequestBody LessonContent lessonContent,
            BindingResult bindingResult, Principal user, Locale locale,
            @PathVariable(value = "courseId") int courseId,
            @PathVariable(value = "lessonId") int lessonId) {

        User userDetail = this.userService.getUserByUsername(user.getName());
        Course course = this.courseService.getCourseById(courseId);
        Lesson lesson = this.lessonService.getLessonById(lessonId);

        if (!this.courseService.isCourseLecturer(course, userDetail)) {
            return this.errorResponseUtil.buildErrorResponse("user.permission.deny", locale);
        }

        Map<String, Object> response = new HashMap<>();
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            response.put("error", errors);

            return this.errorResponseUtil.buildErrorResponse(response, locale);
        }

        if (this.lessonContentService.hasLessonContent(lessonId, lessonContent.getTitle())) {
            return this.errorResponseUtil.buildErrorResponse("lessonContent.title.exist.errMsg", locale);
        }

        lessonContent.setLessonId(lesson);
        this.lessonContentService.saveLessonContent(lessonContent);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{contentId}")
    @CrossOrigin
    public ResponseEntity<Object> deleteLessonContent(Locale locale, Principal user,
            @PathVariable(value = "courseId") int courseId,
            @PathVariable(value = "lessonId") int lessonId,
            @PathVariable(value = "contentId") int contentId) {

        User userDetail = this.userService.getUserByUsername(user.getName());
        Course course = this.courseService.getCourseById(courseId);
        Lesson lesson = this.lessonService.getLessonById(lessonId);
        LessonContent content = this.lessonContentService.getLessonContentById(contentId);

        if (!this.courseService.isCourseLecturer(course, userDetail)) {
            return this.errorResponseUtil.buildErrorResponse("user.permission.deny", locale);
        }

        this.lessonContentService.deleteLessonContent(content);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @PutMapping("/{contentId}")
    @CrossOrigin
    public ResponseEntity<Object> editLessonContent(@Valid @RequestBody LessonContent newLessonContent,Locale locale, Principal user,
            @PathVariable(value = "courseId") int courseId,
            @PathVariable(value = "lessonId") int lessonId,
            @PathVariable(value = "contentId") int contentId) {

        User userDetail = this.userService.getUserByUsername(user.getName());
        Course course = this.courseService.getCourseById(courseId);
        Lesson lesson = this.lessonService.getLessonById(lessonId);
        LessonContent oldLessonContent = this.lessonContentService.getLessonContentById(contentId);
        
        if(!this.courseService.isCourseLecturer(course, userDetail)){
            return this.errorResponseUtil.buildErrorResponse("user.permission.deny", locale);
        }

        if(newLessonContent.getTitle() != null && !newLessonContent.getTitle().isEmpty()){
            oldLessonContent.setTitle(newLessonContent.getTitle());
        }
        
        if(newLessonContent.getContent() != null && !newLessonContent.getContent().isEmpty()){
            oldLessonContent.setTitle(newLessonContent.getContent());
        }
        
        this.lessonContentService.saveLessonContent(oldLessonContent);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // helper method
    private void handleExercise(User userDetail, LessonContent content, LessonContentDetailDTO lessonContent, Course course) {
        DoingExercise exercise = doingExerciseServise.getDoingExercise(userDetail.getId(), content.getId());

        if (exercise != null) {
            if ("DONE".equals(exercise.getExerciseStatusId().getStatus())) {
                lessonContent.setHasLearn(true);
            }
        } else {
            if ("LESSON".equals(lessonContent.getContentType())) {
                exercise = new DoingExercise();
                exercise.setLessonContentId(content);
                exercise.setLearnerId(userDetail);
                exercise.setContent("");
                exercise.setScore(BigDecimal.TEN);
                exercise.setExerciseStatusId(new ExerciseStatus(3)); // DONE

                doingExerciseServise.saveDoingExercise(exercise);

                CourseProgress progress = courseProgressService.getCourseProgress(userDetail.getId(), course.getId());
                progress.setLessonCompleteCount(progress.getLessonCompleteCount() + 1);
                courseProgressService.saveCourseProgress(progress);
            }
        }
    }
}
