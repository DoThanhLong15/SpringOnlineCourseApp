/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.pojo;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 *
 * @author LONG
 */
@Entity
@Table(name = "content_learn")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ContentLearn.findAll", query = "SELECT c FROM ContentLearn c"),
    @NamedQuery(name = "ContentLearn.findById", query = "SELECT c FROM ContentLearn c WHERE c.id = :id"),
    @NamedQuery(name = "ContentLearn.findByCreatedDate", query = "SELECT c FROM ContentLearn c WHERE c.createdDate = :createdDate")})
@DynamicInsert
@DynamicUpdate
public class ContentLearn implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createdDate;
    @JoinColumn(name = "lesson_content_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private LessonContent lessonContentId;
    @JoinColumn(name = "learner_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User learnerId;

    public ContentLearn() {
    }

    public ContentLearn(Integer id) {
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

    public LessonContent getLessonContentId() {
        return lessonContentId;
    }

    public void setLessonContentId(LessonContent lessonContentId) {
        this.lessonContentId = lessonContentId;
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
        if (!(object instanceof ContentLearn)) {
            return false;
        }
        ContentLearn other = (ContentLearn) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dtl.pojo.ContentLearn[ id=" + id + " ]";
    }
    
}
