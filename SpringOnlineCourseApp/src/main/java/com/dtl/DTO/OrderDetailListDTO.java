/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.DTO;

import com.dtl.pojo.Course;

/**
 *
 * @author LONG
 */
public class OrderDetailListDTO {
    private Integer id;
    private Integer ownerId;
    private CourseListDTO course;

    public OrderDetailListDTO() {

    }

    public OrderDetailListDTO(Integer id, Integer ownerId, Course course) {
        this.id = id;
        this.ownerId = ownerId;
        this.course = new CourseListDTO(course, true);
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
     * @return the ownerId
     */
    public Integer getOwnerId() {
        return ownerId;
    }

    /**
     * @param ownerId the ownerId to set
     */
    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    /**
     * @return the course
     */
    public CourseListDTO getCourse() {
        return course;
    }

    /**
     * @param course the course to set
     */
    public void setCourse(CourseListDTO course) {
        this.course = course;
    }
}
