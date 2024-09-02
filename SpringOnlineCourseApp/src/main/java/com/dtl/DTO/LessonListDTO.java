/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.DTO;

import java.util.Date;

/**
 *
 * @author LONG
 */
public class LessonListDTO {

    private Integer id;
    private String title;
    private Date createdDate;
    private Date updatedDate;

    public LessonListDTO() {

    }

    public LessonListDTO(LessonListDTO lesson) {
        this.id = lesson.id;
        this.title = lesson.title;
        this.createdDate = lesson.createdDate;
        this.updatedDate = lesson.updatedDate;
    }

    public LessonListDTO(Integer id, String title, Date createdDate, Date updatedDate) {
        this.id = id;
        this.title = title;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
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
}
