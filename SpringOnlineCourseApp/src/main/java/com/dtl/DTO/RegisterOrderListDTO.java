/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.DTO;

import com.dtl.pojo.RegisterOrder;
import java.util.Date;

/**
 *
 * @author LONG
 */
public class RegisterOrderListDTO {

    private int id;
    private Date createdDate;
    private Date updatedDate;
    private Integer learnerId;

    public RegisterOrderListDTO() {

    }

    public RegisterOrderListDTO(RegisterOrder order) {
        if (order != null) {
            this.id = order.getId();
            this.createdDate = order.getCreatedDate();
            this.updatedDate = order.getUpdatedDate();
            this.learnerId = order.getLearnerId().getId();
        }
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
     * @return the learnerId
     */
    public Integer getLearnerId() {
        return learnerId;
    }

    /**
     * @param learnerId the learnerId to set
     */
    public void setLearnerId(Integer learnerId) {
        this.learnerId = learnerId;
    }
}
