/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dtl.services;

import com.dtl.pojo.Course;
import com.dtl.pojo.CourseProgress;
import com.dtl.pojo.User;
import java.util.List;
import java.util.Map;

/**
 *
 * @author LONG
 */
public interface CourseService {

    List<Course> getCourse(Map<String, String> params);

    void addOrUpdateCourse(Course course);

    Course getCourseById(int id);

    void deleteCourse(int id);

    int countCourses();

    public int getPageSize();

    boolean hasEnrolled(Course course, User user);

    boolean isCourseLecturer(Course course, User user);

    Integer countContentInCourse(int courseId);

    void checkCourseProgress(Course course, User user, CourseProgress progress, int totalContent);
}
