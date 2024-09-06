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
import javax.persistence.FetchType;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

/**
 *
 * @author LONG
 */
@Entity
@Table(name = "certificate")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Certificate.findAll", query = "SELECT c FROM Certificate c"),
    @NamedQuery(name = "Certificate.findById", query = "SELECT c FROM Certificate c WHERE c.id = :id"),
    @NamedQuery(name = "Certificate.findByTitle", query = "SELECT c FROM Certificate c WHERE c.title = :title"),
    @NamedQuery(name = "Certificate.findByCourseName", query = "SELECT c FROM Certificate c WHERE c.courseName = :courseName"),
    @NamedQuery(name = "Certificate.findByLearnerName", query = "SELECT c FROM Certificate c WHERE c.learnerName = :learnerName"),
    @NamedQuery(name = "Certificate.findByFinishDate", query = "SELECT c FROM Certificate c WHERE c.finishDate = :finishDate")})
@DynamicInsert
public class Certificate implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "course_name")
    private String courseName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "learner_name")
    private String learnerName;
    @Column(name = "finish_date")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date finishDate;
    @JoinColumn(name = "learner_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User learnerId;

    public Certificate() {
        
    }

    public Certificate(Integer id) {
        this.id = id;
    }

    public Certificate(Integer id, String title, String courseName, String learnerName) {
        this.id = id;
        this.title = title;
        this.courseName = courseName;
        this.learnerName = learnerName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getLearnerName() {
        return learnerName;
    }

    public void setLearnerName(String learnerName) {
        this.learnerName = learnerName;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
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
        if (!(object instanceof Certificate)) {
            return false;
        }
        Certificate other = (Certificate) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dtl.pojo.Certificate[ id=" + id + " ]";
    }
    
}
