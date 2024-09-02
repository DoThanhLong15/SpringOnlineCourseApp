/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.services.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.dtl.pojo.Course;
import com.dtl.pojo.CourseTag;
import com.dtl.repository.CourseRepository;
import com.dtl.repository.CourseTagRepository;
import com.dtl.repository.TagRepository;
import com.dtl.services.CourseService;
import java.io.IOException;
import java.util.ArrayList;
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

    private static final int PAGE_SIZE = 5;
    @Autowired
    private CourseRepository courseRepo;
    @Autowired
    private TagRepository tagRepo;
    @Autowired
    private CourseTagRepository courseTagRepo;
    @Autowired
    private Cloudinary cloudinary;

    @Override
    public List<Course> getCourse(Map<String, String> params) {
        return this.courseRepo.getCourse(params, PAGE_SIZE);
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

        if (course.getCourseTagForm() != null && !course.getCourseTagForm().isEmpty()) {
            course.setCourseTagCollection(new ArrayList<>());

            course.getCourseTagForm().stream()
                    .forEach(formValue -> {
                        CourseTag courseTag = new CourseTag();
                        courseTag.setCourseId(course);
                        courseTag.setTagId(this.tagRepo.getTagById(formValue.getTagId()));

                        course.getCourseTagCollection().add(courseTag);
                    });
        }

        this.courseRepo.addOrUpdateCourse(course);
    }

    @Override
    public Course getCourseById(int id) {
        Course course = this.courseRepo.getCourseById(id);

        course.setCourseTagCollection(this.courseTagRepo.getCourseTags(id, -1));

        return course;
    }

    @Override
    public void deleteCourse(int id) {
        this.courseRepo.deleteCourse(id);
    }

    @Override
    public int countCourses() {
        return this.courseRepo.countCourses();
    }
    
    @Override
    public int getPageSize(){
        return CourseServiceImpl.PAGE_SIZE;
    }
}
