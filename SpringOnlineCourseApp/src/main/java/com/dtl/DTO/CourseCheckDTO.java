/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.DTO;

/**
 *
 * @author LONG
 */
public class CourseCheckDTO {
    private Integer courseId;
    private Integer lessonId;
    private Integer lessonContentId;
    
    public CourseCheckDTO(){
        
    }

    /**
     * @return the courseId
     */
    public Integer getCourseId() {
        return courseId;
    }

    /**
     * @param courseId the courseId to set
     */
    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    /**
     * @return the lessonId
     */
    public Integer getLessonId() {
        return lessonId;
    }

    /**
     * @param lessonId the lessonId to set
     */
    public void setLessonId(Integer lessonId) {
        this.lessonId = lessonId;
    }

    /**
     * @return the lessonContentId
     */
    public Integer getLessonContentId() {
        return lessonContentId;
    }

    /**
     * @param lessonContentId the lessonContentId to set
     */
    public void setLessonContentId(Integer lessonContentId) {
        this.lessonContentId = lessonContentId;
    }
}
