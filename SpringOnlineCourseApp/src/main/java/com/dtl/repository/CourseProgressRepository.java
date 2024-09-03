/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dtl.repository;

import com.dtl.pojo.CourseProgress;

/**
 *
 * @author LONG
 */
public interface CourseProgressRepository {

    void saveCourseProgress(CourseProgress courseProgress);

    CourseProgress getCourseProgressById(int progressId);

    CourseProgress getCourseProgress(int userId, int courseId);
}
