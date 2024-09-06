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
    private boolean complete;
    
    public CourseProgressDTO(){
        
    }
    
    public CourseProgressDTO(Integer totalCourseContent, Integer completeContent, boolean complete){
        this.totalCourseContent = totalCourseContent;
        this.completeContent = completeContent;
        this.complete = complete;
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

    /**
     * @return the complete
     */
    public boolean isComplete() {
        return complete;
    }

    /**
     * @param complete the complete to set
     */
    public void setComplete(boolean complete) {
        this.complete = complete;
    }
}
