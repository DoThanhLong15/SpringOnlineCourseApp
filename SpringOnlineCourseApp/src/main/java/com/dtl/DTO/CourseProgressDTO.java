/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.DTO;

/**
 *
 * @author LONG
 */
public class CourseProgressDTO {
    private Integer totalCourseContent;
    private Integer completeContent;
    
    public CourseProgressDTO(){
        
    }
    
    public CourseProgressDTO(Integer totalCourseContent, Integer completeContent){
        this.totalCourseContent = totalCourseContent;
        this.completeContent = completeContent;
    }

    /**
     * @return the completeContent
     */
    public Integer getCompleteContent() {
        return completeContent;
    }

    /**
     * @param completeContent the completeContent to set
     */
    public void setCompleteContent(Integer completeContent) {
        this.completeContent = completeContent;
    }

    /**
     * @return the totalCourseContent
     */
    public Integer getTotalCourseContent() {
        return totalCourseContent;
    }

    /**
     * @param totalCourseContent the totalCourseContent to set
     */
    public void setTotalCourseContent(Integer totalCourseContent) {
        this.totalCourseContent = totalCourseContent;
    }
}
