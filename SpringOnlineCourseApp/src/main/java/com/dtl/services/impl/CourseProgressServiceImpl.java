/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.services.impl;

import com.dtl.pojo.CourseProgress;
import com.dtl.repository.CourseProgressRepository;
import com.dtl.services.CourseProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author LONG
 */
@Service
public class CourseProgressServiceImpl implements CourseProgressService {

    @Autowired
    private CourseProgressRepository courseProgressRepo;

    @Override
    public void saveCourseProgress(CourseProgress courseProgress) {
        this.courseProgressRepo.saveCourseProgress(courseProgress);
    }

    @Override
    public CourseProgress getCourseProgressById(int progressId) {
        return this.courseProgressRepo.getCourseProgressById(progressId);
    }

    @Override
    public CourseProgress getCourseProgress(int userId, int courseId) {
        return this.courseProgressRepo.getCourseProgress(userId, courseId);
    }

}
