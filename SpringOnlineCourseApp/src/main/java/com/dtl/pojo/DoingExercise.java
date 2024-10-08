/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

/**
 *
 * @author LONG
 */
@Entity
@Table(name = "doing_exercise")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DoingExercise.findAll", query = "SELECT d FROM DoingExercise d"),
    @NamedQuery(name = "DoingExercise.findById", query = "SELECT d FROM DoingExercise d WHERE d.id = :id"),
    @NamedQuery(name = "DoingExercise.findByScore", query = "SELECT d FROM DoingExercise d WHERE d.score = :score"),
    @NamedQuery(name = "DoingExercise.findByCreatedDate", query = "SELECT d FROM DoingExercise d WHERE d.createdDate = :createdDate"),
    @NamedQuery(name = "DoingExercise.findByUpdatedDate", query = "SELECT d FROM DoingExercise d WHERE d.updatedDate = :updatedDate")})
@DynamicInsert
@DynamicUpdate
public class DoingExercise implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "score")
    private BigDecimal score;
    @Size(max = 65535)
    @Column(name = "content")
    private String content;
    @Column(name = "created_date", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createdDate;
    @Column(name = "updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updatedDate;
    @JoinColumn(name = "exercise_status_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private ExerciseStatus exerciseStatusId;
    @JoinColumn(name = "lesson_content_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private LessonContent lessonContentId;
    @JoinColumn(name = "learner_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User learnerId;
    @Size(min = 1, max = 50)
    @Column(name = "lecturer_comment")
    private String lecturerComment;
    @Column(name = "score_date")
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date scoreDate;

    public DoingExercise() {
    }

    public DoingExercise(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public ExerciseStatus getExerciseStatusId() {
        return exerciseStatusId;
    }

    public void setExerciseStatusId(ExerciseStatus exerciseStatusId) {
        this.exerciseStatusId = exerciseStatusId;
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
        if (!(object instanceof DoingExercise)) {
            return false;
        }
        DoingExercise other = (DoingExercise) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dtl.pojo.DoingExercise[ id=" + id + " ]";
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
     * @return the scoreDate
     */
    public Date getScoreDate() {
        return scoreDate;
    }

    /**
     * @param scoreDate the scoreDate to set
     */
    public void setScoreDate(Date scoreDate) {
        this.scoreDate = scoreDate;
    }

}
