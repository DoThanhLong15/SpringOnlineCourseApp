/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.services.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.dtl.pojo.Certificate;
import com.dtl.pojo.Course;
import com.dtl.pojo.CourseProgress;
import com.dtl.pojo.CourseTag;
import com.dtl.pojo.User;
import com.dtl.repository.CertificateRepository;
import com.dtl.repository.CourseProgressRepository;
import com.dtl.repository.CourseRepository;
import com.dtl.repository.TagRepository;
import com.dtl.services.CourseService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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
    private CourseProgressRepository courseProgressRepo;
    @Autowired
    private CertificateRepository certificateRepo;
    @Autowired
    private Cloudinary cloudinary;

    @Override
    public List<Course> getCourse(Map<String, String> params) {
        return this.courseRepo.getCourse(params, PAGE_SIZE);
    }

    @Override
    public void addOrUpdateCourse(Course course) {
        if (course.getFile() != null && !course.getFile().isEmpty()) {
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
    public int getPageSize() {
        return CourseServiceImpl.PAGE_SIZE;
    }

    @Override
    public boolean hasEnrolled(Course course, User user) {
        return this.courseProgressRepo.getCourseProgress(user.getId(), course.getId()) != null;
    }

    @Override
    public boolean isCourseLecturer(Course course, User user) {
        return Objects.equals(course.getLecturerId().getId(), user.getId());
    }

    @Override
    public Integer countContentInCourse(int courseId) {
        return this.courseRepo.countContentInCourse(courseId);
    }

    @Override
    public void checkCourseProgress(Course course, User user, CourseProgress progress, int totalContent) {

        int total = totalContent;
        if (total <= 0) {
            total = this.countContentInCourse(course.getId());
        }

        if (!progress.getIsComplete() && progress.getLessonCompleteCount() >= total) {
            Map<String, String> params = new HashMap<>();
            params.put("courseName", course.getTitle());
            params.put("learnerId", String.valueOf(user.getId()));
            List<Certificate> certificateList = this.certificateRepo.getCertificateList(params);

            if (certificateList == null || certificateList.isEmpty()) {
                System.out.println("new Certificate");

                Certificate certificate = new Certificate();
                certificate.setCourseName(course.getTitle());
                certificate.setLearnerId(user);
                certificate.setLearnerName(user.getLastName() + " " + user.getFirstName());
                certificate.setTitle("Chứng chỉ " + course.getTitle());

                this.certificateRepo.saveCertificate(certificate);
            }

            progress.setIsComplete(true);
        }

        this.courseProgressRepo.saveCourseProgress(progress);
    }
}
