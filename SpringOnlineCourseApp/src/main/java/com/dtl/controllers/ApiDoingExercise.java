/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.controllers;

import com.dtl.DTO.DoingExerciseDTO;
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
import org.springframework.web.bind.annotation.CrossOrigin;
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
@RequestMapping("/api/courses/{courseId}/lessons/{lessonId}/contents/{contentId}/do-exercise")
public class ApiDoingExercise {

    @Autowired
    private CourseService courseService;
    @Autowired
    private LessonService lessonService;
    @Autowired
    private LessonContentService lessonContentService;
    @Autowired
    private UserService userService;
    @Autowired
    private DoingExerciseService doingExerciseService;
    @Autowired
    private CourseProgressService courseProgressService;
    @Autowired
    private ErrorResponseUtil errorResponseUtil;

    @GetMapping("/{doExerciseId}")
    @CrossOrigin
    public ResponseEntity<Object> getDoingExercise(Locale locale, Principal user,
            @PathVariable(value = "courseId") int courseId,
            @PathVariable(value = "lessonId") int lessonId,
            @PathVariable(value = "contentId") int contentId,
            @PathVariable(value = "doExerciseId") int doExerciseId) {

        Map<String, Object> response = new HashMap<>();
        User userDetail = this.userService.getUserByUsername(user.getName());
        Course course = this.courseService.getCourseById(courseId);
        if (!this.courseService.hasEnrolled(course, userDetail)
                && !this.courseService.isCourseLecturer(course, userDetail)) {
            return this.errorResponseUtil.buildErrorResponse("user.permission.deny", locale);
        }

        Lesson lesson = this.lessonService.getLessonById(lessonId);
        if (lesson.getCourseId().getId() != course.getId()) {
            return this.errorResponseUtil.buildErrorResponse("lesson.notInCourse.errMsg", locale);
        }

        LessonContent lessonContent = this.lessonContentService.getLessonContentById(contentId);
        if (lessonContent.getLessonId().getId() != lesson.getId()) {
            return this.errorResponseUtil.buildErrorResponse("lessonContent.notInLesson.errMsg", locale);
        }

        if (lessonContent.getContentTypeId().getType().equals("LESSON")) {
            return this.errorResponseUtil.buildErrorResponse("contentType.notSubmit.errMsg", locale);
        }

        DoingExercise doingExercise = this.doingExerciseService.getDoingExerciseById(doExerciseId);
        response.put("data", new DoingExerciseDTO(doingExercise));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{doExerciseId}")
    @CrossOrigin
    public ResponseEntity<Object> scoreLearnerExercise(Locale locale,
            Principal user,
            @Valid @RequestBody DoingExerciseDTO doingExerciseForm,
            @PathVariable(value = "courseId") int courseId,
            @PathVariable(value = "lessonId") int lessonId,
            @PathVariable(value = "contentId") int contentId,
            @PathVariable(value = "doExerciseId") int doExerciseId) {

        User userDetail = this.userService.getUserByUsername(user.getName());
        Course course = this.courseService.getCourseById(courseId);

        if (!this.courseService.isCourseLecturer(course, userDetail)) {
            return this.errorResponseUtil.buildErrorResponse("user.permission.deny", locale);
        }

        Lesson lesson = this.lessonService.getLessonById(lessonId);
        if (lesson.getCourseId().getId() != course.getId()) {
            return this.errorResponseUtil.buildErrorResponse("lesson.notInCourse.errMsg", locale);
        }

        LessonContent lessonContent = this.lessonContentService.getLessonContentById(contentId);
        if (lessonContent.getLessonId().getId() != lesson.getId()) {
            return this.errorResponseUtil.buildErrorResponse("lessonContent.notInLesson.errMsg", locale);
        }

        if (lessonContent.getContentTypeId().getType().equals("LESSON")) {
            return this.errorResponseUtil.buildErrorResponse("contentType.notSubmit.errMsg", locale);
        }

        BigDecimal score = doingExerciseForm.getScore();
        if (score == null || score.compareTo(BigDecimal.valueOf(10.00)) > 0
                || score.compareTo(BigDecimal.ZERO) < 0) {
            return this.errorResponseUtil.buildErrorResponse("doingExercise.score.inValid.errMsg", locale);
        }

        String lecturerComment = doingExerciseForm.getLecturerComment();
        if (lecturerComment == null || lecturerComment.isEmpty()) {
            return this.errorResponseUtil.buildErrorResponse("doingExercise.lecturerComment.notNull.errMsg", locale);
        }

        DoingExercise doingExercise = this.doingExerciseService.getDoingExerciseById(doExerciseId);
        if (!doingExercise.getExerciseStatusId().getStatus().equals("DONE")) {
            User learner = doingExercise.getLearnerId();
            CourseProgress courseProgress = this.courseProgressService.getCourseProgress(learner.getId(), course.getId());
            if (courseProgress != null) {
                courseProgress.setLessonCompleteCount(courseProgress.getLessonCompleteCount() + 1);
                this.courseService.checkCourseProgress(course, learner, courseProgress, 0);
            }
        }

        doingExercise.setScore(score);
        doingExercise.setLecturerComment(lecturerComment);
        doingExercise.setExerciseStatusId(new ExerciseStatus(3)); // DONE
        this.doingExerciseService.saveDoingExercise(doingExercise);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/")
    @CrossOrigin
    public ResponseEntity<Object> doingExercise(@Valid @RequestBody DoingExerciseDTO doingExercise,
            BindingResult bindingResult, Principal user, Locale locale,
            @PathVariable(value = "courseId") int courseId,
            @PathVariable(value = "lessonId") int lessonId,
            @PathVariable(value = "contentId") int contentId) {

        User userDetail = this.userService.getUserByUsername(user.getName());
        Course course = this.courseService.getCourseById(courseId);
        if (!this.courseService.hasEnrolled(course, userDetail)) {
            return this.errorResponseUtil.buildErrorResponse("user.permission.deny", locale);
        }

        Lesson lesson = this.lessonService.getLessonById(lessonId);
        if (lesson.getCourseId().getId() != course.getId()) {
            return this.errorResponseUtil.buildErrorResponse("lesson.notInCourse.errMsg", locale);
        }

        LessonContent lessonContent = this.lessonContentService.getLessonContentById(contentId);
        if (lessonContent.getLessonId().getId() != lesson.getId()) {
            return this.errorResponseUtil.buildErrorResponse("lessonContent.notInLesson.errMsg", locale);
        }

        if (lessonContent.getContentTypeId().getType().equals("LESSON")) {
            return this.errorResponseUtil.buildErrorResponse("contentType.notSubmit.errMsg", locale);
        }

        DoingExercise learnerDoExercise = this.doingExerciseService.getDoingExercise(userDetail.getId(), lessonContent.getId());
        if (learnerDoExercise != null) {
            return this.errorResponseUtil.buildErrorResponse("doingExercise.hasDo.errMsg", locale);
        }

        String content = doingExercise.getContent();
        if (content == null || content.isEmpty()) {
            return this.errorResponseUtil.buildErrorResponse("doingExercise.content.notNull.errMsg", locale);
        }

        learnerDoExercise = new DoingExercise();
        learnerDoExercise.setContent(content);
        learnerDoExercise.setLearnerId(userDetail);
        learnerDoExercise.setExerciseStatusId(new ExerciseStatus(2)); // DOING
        learnerDoExercise.setLessonContentId(lessonContent);
        learnerDoExercise.setScore(BigDecimal.ZERO);
        this.doingExerciseService.saveDoingExercise(learnerDoExercise);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
