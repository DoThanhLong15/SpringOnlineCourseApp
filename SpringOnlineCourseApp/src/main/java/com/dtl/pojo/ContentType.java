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
@Table(name = "content_type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ContentType.findAll", query = "SELECT c FROM ContentType c"),
    @NamedQuery(name = "ContentType.findById", query = "SELECT c FROM ContentType c WHERE c.id = :id"),
    @NamedQuery(name = "ContentType.findByType", query = "SELECT c FROM ContentType c WHERE c.type = :type"),
    @NamedQuery(name = "ContentType.findByIsRequired", query = "SELECT c FROM ContentType c WHERE c.isRequired = :isRequired")})
public class ContentType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "type")
    private String type;
    @Column(name = "is_required")
    private Boolean isRequired;
    @OneToMany(mappedBy = "contentTypeId")
    private Collection<LessonContent> lessonContentCollection;

    public ContentType() {
    }

    public ContentType(Integer id) {
        this.id = id;
    }

    public ContentType(Integer id, String type) {
        this.id = id;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getIsRequired() {
        return isRequired;
    }

    public void setIsRequired(Boolean isRequired) {
        this.isRequired = isRequired;
    }

    @XmlTransient
    public Collection<LessonContent> getLessonContentCollection() {
        return lessonContentCollection;
    }

    public void setLessonContentCollection(Collection<LessonContent> lessonContentCollection) {
        this.lessonContentCollection = lessonContentCollection;
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
        if (!(object instanceof ContentType)) {
            return false;
        }
        ContentType other = (ContentType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dtl.pojo.ContentType[ id=" + id + " ]";
    }
    
}
