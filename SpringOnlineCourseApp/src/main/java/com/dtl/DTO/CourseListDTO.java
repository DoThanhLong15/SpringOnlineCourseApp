/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.DTO;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author LONG
 */
public class CourseListDTO {
    private Integer id;
    private String title;
    private BigDecimal rating;
    private int ratingCount;
    private int price;
    private String image;
    private Date createdDate;
    private Date updatedDate;
    private int participantCount;
    
    public CourseListDTO(){
        
    }
    
    public CourseListDTO(CourseListDTO course){
        this.id = course.getId();
        this.title = course.getTitle();
        this.rating = course.getRating();
        this.ratingCount = course.getRatingCount();
        this.price = course.getPrice();
        this.image = course.getImage();
        this.createdDate = course.getCreatedDate();
        this.updatedDate = course.getUpdatedDate();
        this.participantCount = course.getParticipantCount();
    }
    
    public CourseListDTO(Integer id, String title, BigDecimal rating, int ratingCount, int price, String image, 
            Date createdDate, Date updatedDate, int participantCount) {
        this.id = id;
        this.title = title;
        this.rating = rating;
        this.ratingCount = ratingCount;
        this.price = price;
        this.image = image;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.participantCount = participantCount;
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
     * @return the rating
     */
    public BigDecimal getRating() {
        return rating;
    }

    /**
     * @param rating the rating to set
     */
    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }

    /**
     * @return the price
     */
    public int getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * @return the ratingCount
     */
    public int getRatingCount() {
        return ratingCount;
    }

    /**
     * @param ratingCount the ratingCount to set
     */
    public void setRatingCount(int ratingCount) {
        this.ratingCount = ratingCount;
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
     * @return the participantCount
     */
    public int getParticipantCount() {
        return participantCount;
    }

    /**
     * @param participantCount the participantCount to set
     */
    public void setParticipantCount(int participantCount) {
        this.participantCount = participantCount;
    }
}
