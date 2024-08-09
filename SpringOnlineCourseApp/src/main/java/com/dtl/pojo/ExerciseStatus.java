/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.pojo;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author LONG
 */
@Entity
@Table(name = "exercise_status")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ExerciseStatus.findAll", query = "SELECT e FROM ExerciseStatus e"),
    @NamedQuery(name = "ExerciseStatus.findById", query = "SELECT e FROM ExerciseStatus e WHERE e.id = :id"),
    @NamedQuery(name = "ExerciseStatus.findByStatus", query = "SELECT e FROM ExerciseStatus e WHERE e.status = :status")})
public class ExerciseStatus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "status")
    private String status;
    @OneToMany(mappedBy = "exerciseStatusId")
    private Collection<DoingExercise> doingExerciseCollection;

    public ExerciseStatus() {
    }

    public ExerciseStatus(Integer id) {
        this.id = id;
    }

    public ExerciseStatus(Integer id, String status) {
        this.id = id;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @XmlTransient
    public Collection<DoingExercise> getDoingExerciseCollection() {
        return doingExerciseCollection;
    }

    public void setDoingExerciseCollection(Collection<DoingExercise> doingExerciseCollection) {
        this.doingExerciseCollection = doingExerciseCollection;
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
        if (!(object instanceof ExerciseStatus)) {
            return false;
        }
        ExerciseStatus other = (ExerciseStatus) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dtl.pojo.ExerciseStatus[ id=" + id + " ]";
    }
    
}
