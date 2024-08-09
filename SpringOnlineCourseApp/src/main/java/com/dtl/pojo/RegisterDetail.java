/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author LONG
 */
@Entity
@Table(name = "register_detail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RegisterDetail.findAll", query = "SELECT r FROM RegisterDetail r"),
    @NamedQuery(name = "RegisterDetail.findById", query = "SELECT r FROM RegisterDetail r WHERE r.id = :id"),
    @NamedQuery(name = "RegisterDetail.findByPrice", query = "SELECT r FROM RegisterDetail r WHERE r.price = :price")})
public class RegisterDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private BigDecimal price;
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    @ManyToOne
    private Course courseId;
    @JoinColumn(name = "register_order_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private RegisterOrder registerOrderId;

    public RegisterDetail() {
    }

    public RegisterDetail(Integer id) {
        this.id = id;
    }

    public RegisterDetail(Integer id, BigDecimal price) {
        this.id = id;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Course getCourseId() {
        return courseId;
    }

    public void setCourseId(Course courseId) {
        this.courseId = courseId;
    }

    public RegisterOrder getRegisterOrderId() {
        return registerOrderId;
    }

    public void setRegisterOrderId(RegisterOrder registerOrderId) {
        this.registerOrderId = registerOrderId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RegisterDetail)) {
            return false;
        }
        RegisterDetail other = (RegisterDetail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dtl.pojo.RegisterDetail[ id=" + id + " ]";
    }
    
}
