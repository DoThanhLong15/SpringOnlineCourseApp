/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.pojo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

/**
 *
 * @author LONG
 */
@Entity
@Table(name = "register_order")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RegisterOrder.findAll", query = "SELECT r FROM RegisterOrder r"),
    @NamedQuery(name = "RegisterOrder.findById", query = "SELECT r FROM RegisterOrder r WHERE r.id = :id"),
    @NamedQuery(name = "RegisterOrder.findByCreatedDate", query = "SELECT r FROM RegisterOrder r WHERE r.createdDate = :createdDate"),
    @NamedQuery(name = "RegisterOrder.findByUpdatedDate", query = "SELECT r FROM RegisterOrder r WHERE r.updatedDate = :updatedDate")})
@DynamicInsert
@DynamicUpdate
public class RegisterOrder implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "created_date", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createdDate;
    @Column(name = "updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updatedDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "registerOrderId")
    private Collection<RegisterDetail> registerDetailCollection;
    @JoinColumn(name = "learner_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private User learnerId;

    public RegisterOrder() {
    }

    public RegisterOrder(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    @XmlTransient
    public Collection<RegisterDetail> getRegisterDetailCollection() {
        return registerDetailCollection;
    }

    public void setRegisterDetailCollection(Collection<RegisterDetail> registerDetailCollection) {
        this.registerDetailCollection = registerDetailCollection;
    }

    public User getLearnerId() {
        return learnerId;
    }

    public void setLearnerId(User learnerId) {
        this.learnerId = learnerId;
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
        if (!(object instanceof RegisterOrder)) {
            return false;
        }
        RegisterOrder other = (RegisterOrder) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dtl.pojo.RegisterOrder[ id=" + id + " ]";
    }
    
}
