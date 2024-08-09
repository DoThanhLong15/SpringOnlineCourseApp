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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author LONG
 */
@Entity
@Table(name = "score_learner_exercise")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ScoreLearnerExercise.findAll", query = "SELECT s FROM ScoreLearnerExercise s"),
    @NamedQuery(name = "ScoreLearnerExercise.findById", query = "SELECT s FROM ScoreLearnerExercise s WHERE s.id = :id"),
    @NamedQuery(name = "ScoreLearnerExercise.findByCreatedDate", query = "SELECT s FROM ScoreLearnerExercise s WHERE s.createdDate = :createdDate"),
    @NamedQuery(name = "ScoreLearnerExercise.findByUpdatedDate", query = "SELECT s FROM ScoreLearnerExercise s WHERE s.updatedDate = :updatedDate")})
public class ScoreLearnerExercise implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Lob
    @Size(max = 65535)
    @Column(name = "comment")
    private String comment;
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;
    @JoinColumn(name = "doing_exercise_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private DoingExercise doingExerciseId;

    public ScoreLearnerExercise() {
    }

    public ScoreLearnerExercise(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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

    public DoingExercise getDoingExerciseId() {
        return doingExerciseId;
    }

    public void setDoingExerciseId(DoingExercise doingExerciseId) {
        this.doingExerciseId = doingExerciseId;
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
        if (!(object instanceof ScoreLearnerExercise)) {
            return false;
        }
        ScoreLearnerExercise other = (ScoreLearnerExercise) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dtl.pojo.ScoreLearnerExercise[ id=" + id + " ]";
    }
    
}
