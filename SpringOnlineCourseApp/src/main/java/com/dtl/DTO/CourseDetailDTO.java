/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.DTO;

import com.dtl.pojo.Tag;
import java.util.List;

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
    
    public CourseDetailDTO(CourseListDTO course,List<Tag> tagList, List<LessonListDTO> lessonList, UserDTO lecturer) {
        super(course);
        this.tagList = tagList;
        this.lessonList = lessonList;
        this.lecturer = lecturer;
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
