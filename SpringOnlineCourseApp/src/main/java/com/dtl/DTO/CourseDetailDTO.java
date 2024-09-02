/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.DTO;

import com.dtl.pojo.Course;
import com.dtl.pojo.Tag;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author LONG
 */
public class CourseDetailDTO extends CourseListDTO{
    private List<Tag> tagList;
    private List<LessonListDTO> lessonList;
    private UserDTO lecturer;
    
    public CourseDetailDTO() {
        super();
    }
    
    public CourseDetailDTO(Course course) {
        super(course);
        this.tagList = course.getCourseTagCollection()
                .stream()
                .map(courseTag -> courseTag.getTagId())
                .collect(Collectors.toList());
        this.lessonList = course.getLessonCollection()
                .stream()
                .map(lesson -> new LessonListDTO(lesson))
                .collect(Collectors.toList());
        this.lecturer = new UserDTO(course.getLecturerId());
    }

    /**
     * @return the tagList
     */
    public List<Tag> getTagList() {
        return tagList;
    }

    /**
     * @param tagList the tagList to set
     */
    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }

    /**
     * @return the lessonList
     */
    public List<LessonListDTO> getLessonList() {
        return lessonList;
    }

    /**
     * @param lessonList the lessonList to set
     */
    public void setLessonList(List<LessonListDTO> lessonList) {
        this.lessonList = lessonList;
    }

    /**
     * @return the lecturer
     */
    public UserDTO getLecturer() {
        return lecturer;
    }

    /**
     * @param lecturer the lecturer to set
     */
    public void setLecturer(UserDTO lecturer) {
        this.lecturer = lecturer;
    }
}
