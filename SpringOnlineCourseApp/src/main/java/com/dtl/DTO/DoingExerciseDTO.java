/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.DTO;

import com.dtl.pojo.DoingExercise;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author LONG
 */
public class DoingExerciseDTO{
    private int id;
    private String content;
    private BigDecimal score;
    private String lecturerComment;
    private Date createdDate;
    private Date updatedDate;
    private String status;
    
    
    public DoingExerciseDTO(){
    }
    
    public DoingExerciseDTO(DoingExercise doingExercise){
        this.id = doingExercise.getId();
        this.content = doingExercise.getContent();
        this.score = doingExercise.getScore();
        this.lecturerComment = doingExercise.getLecturerComment();
        this.createdDate = doingExercise.getCreatedDate();
        this.updatedDate = doingExercise.getUpdatedDate();
        this.status = doingExercise.getExerciseStatusId().getStatus();
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the Content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param Content the Content to set
     */
    public void setContent(String Content) {
        this.content = Content;
    }

    /**
     * @return the score
     */
    public BigDecimal getScore() {
        return score;
    }

    /**
     * @param score the score to set
     */
    public void setScore(BigDecimal score) {
        this.score = score;
    }

    /**
     * @return the lecturerComment
     */
    public String getLecturerComment() {
        return lecturerComment;
    }

    /**
     * @param lecturerComment the lecturerComment to set
     */
    public void setLecturerComment(String lecturerComment) {
        this.lecturerComment = lecturerComment;
    }

    /**
     * @return the createdDate
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * @param createdDate the createdDate to set
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * @return the updatedDate
     */
    public Date getUpdatedDate() {
        return updatedDate;
    }

    /**
     * @param updatedDate the updatedDate to set
     */
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }
}
