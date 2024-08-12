/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.services.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.dtl.pojo.Course;
import com.dtl.repository.CourseRepository;
import com.dtl.services.CourseService;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author LONG
 */
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepo;
    @Autowired
    private Cloudinary cloudinary;

    @Override
    public List<Course> getCourse(Map<String, String> params) {
        return this.courseRepo.getCourse(params);
    }

    @Override
    public void addOrUpdateCourse(Course course) {
        if (!course.getFile().isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(course.getFile().getBytes(),
                            ObjectUtils.asMap("resource_type", "auto"));
                
                course.setImage(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(CourseServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.courseRepo.addOrUpdateCourse(course);
    }

    @Override
    public Course getCourseById(int id) {
        return this.courseRepo.getCourseById(id);
    }

    @Override
    public void deleteCourse(int id) {
        this.courseRepo.deleteCourse(id);
    }
}
