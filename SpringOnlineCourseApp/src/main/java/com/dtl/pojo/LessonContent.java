/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.pojo;

import com.dtl.validation.annotation.LessonContentDurationRequired;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
@Table(name = "lesson_content")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LessonContent.findAll", query = "SELECT l FROM LessonContent l"),
    @NamedQuery(name = "LessonContent.findById", query = "SELECT l FROM LessonContent l WHERE l.id = :id"),
    @NamedQuery(name = "LessonContent.findByTitle", query = "SELECT l FROM LessonContent l WHERE l.title = :title"),
    @NamedQuery(name = "LessonContent.findByDuration", query = "SELECT l FROM LessonContent l WHERE l.duration = :duration"),
    @NamedQuery(name = "LessonContent.findByCreatedDate", query = "SELECT l FROM LessonContent l WHERE l.createdDate = :createdDate"),
    @NamedQuery(name = "LessonContent.findByUpdatedDate", query = "SELECT l FROM LessonContent l WHERE l.updatedDate = :updatedDate")})
@DynamicInsert
@DynamicUpdate
@LessonContentDurationRequired(message = "{lessonContent.duration.notNull.errMsg}")
public class LessonContent implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lessonContentId")
    private Collection<ContentLearn> contentLearnCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull(message = "{lessonContent.title.notNull.errMsg}")
    @Size(min = 1, max = 50, message = "{lessonContent.title.notNull.errMsg}")
    @Column(name = "title")
    private String title;
    @Column(name = "duration")
    private Integer duration;
    @Basic(optional = false)
    @NotNull(message = "{lessonContent.content.notNull.errMsg}")
    @Size(min = 1, max = 65535)
    @Column(name = "content")
    private String content;
    @CreationTimestamp
    @Column(name = "created_date", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @UpdateTimestamp
    @Column(name = "updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;
    @JoinColumn(name = "content_type_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    @NotNull(message = "{lessonContent.contentTypeId.notNull.errMsg}")
    private ContentType contentTypeId;
    @JoinColumn(name = "lesson_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Lesson lessonId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lessonContentId")
    @JsonIgnore
    private Collection<DoingExercise> doingExerciseCollection;

    public LessonContent() {
    }

    public LessonContent(Integer id) {
        this.id = id;
    }

    public LessonContent(Integer id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
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

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
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

    public ContentType getContentTypeId() {
        return contentTypeId;
    }

    public void setContentTypeId(ContentType contentTypeId) {
        this.contentTypeId = contentTypeId;
    }

    public Lesson getLessonId() {
        return lessonId;
    }

    public void setLessonId(Lesson lessonId) {
        this.lessonId = lessonId;
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
        if (!(object instanceof LessonContent)) {
            return false;
        }
        LessonContent other = (LessonContent) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dtl.pojo.LessonContent[ id=" + id + " ]";
    }

    @XmlTransient
    public Collection<ContentLearn> getContentLearnCollection() {
        return contentLearnCollection;
    }

    public void setContentLearnCollection(Collection<ContentLearn> contentLearnCollection) {
        this.contentLearnCollection = contentLearnCollection;
    }
    
}
