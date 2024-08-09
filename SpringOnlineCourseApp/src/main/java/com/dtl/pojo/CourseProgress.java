/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.pojo;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author LONG
 */
@Entity
@Table(name = "course_progress")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CourseProgress.findAll", query = "SELECT c FROM CourseProgress c"),
    @NamedQuery(name = "CourseProgress.findById", query = "SELECT c FROM CourseProgress c WHERE c.id = :id"),
    @NamedQuery(name = "CourseProgress.findByLessonCompleteCount", query = "SELECT c FROM CourseProgress c WHERE c.lessonCompleteCount = :lessonCompleteCount")})
public class CourseProgress implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "lesson_complete_count")
    private Integer lessonCompleteCount;
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Course courseId;
    @JoinColumn(name = "learner_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User learnerId;

    public CourseProgress() {
    }

    public CourseProgress(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLessonCompleteCount() {
        return lessonCompleteCount;
    }

    public void setLessonCompleteCount(Integer lessonCompleteCount) {
        this.lessonCompleteCount = lessonCompleteCount;
    }

    public Course getCourseId() {
        return courseId;
    }

    public void setCourseId(Course courseId) {
        this.courseId = courseId;
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
        if (!(object instanceof CourseProgress)) {
            return false;
        }
        CourseProgress other = (CourseProgress) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dtl.pojo.CourseProgress[ id=" + id + " ]";
    }
    
}
