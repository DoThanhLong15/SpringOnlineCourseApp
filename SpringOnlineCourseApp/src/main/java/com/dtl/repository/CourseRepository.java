/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.repository;

import com.dtl.pojo.Course;
import java.util.List;
import java.util.Map;

/**
 *
 * @author LONG
 */
public interface CourseRepository {

    List<Course> getCourse(Map<String, String> params);

    void addOrUpdateCourse(Course course);

    Course getCourseById(int id);

    void deleteCourse(int id);
}
