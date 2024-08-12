/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author LONG
 */
@Entity
@Table(name = "course")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Course.findAll", query = "SELECT c FROM Course c"),
    @NamedQuery(name = "Course.findById", query = "SELECT c FROM Course c WHERE c.id = :id"),
    @NamedQuery(name = "Course.findByTitle", query = "SELECT c FROM Course c WHERE c.title = :title"),
    @NamedQuery(name = "Course.findByCreatedDate", query = "SELECT c FROM Course c WHERE c.createdDate = :createdDate"),
    @NamedQuery(name = "Course.findByUpdatedDate", query = "SELECT c FROM Course c WHERE c.updatedDate = :updatedDate"),
    @NamedQuery(name = "Course.findByParticipantCount", query = "SELECT c FROM Course c WHERE c.participantCount = :participantCount"),
    @NamedQuery(name = "Course.findByRatingCount", query = "SELECT c FROM Course c WHERE c.ratingCount = :ratingCount"),
    @NamedQuery(name = "Course.findByRating", query = "SELECT c FROM Course c WHERE c.rating = :rating"),
    @NamedQuery(name = "Course.findByPrice", query = "SELECT c FROM Course c WHERE c.price = :price"),
    @NamedQuery(name = "Course.findByStatus", query = "SELECT c FROM Course c WHERE c.status = :status")})
public class Course implements Serializable {

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
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "description")
    private String description;
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;
    @Column(name = "participant_count")
    private Integer participantCount;
    @Column(name = "rating_count")
    private Integer ratingCount;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "rating")
    private BigDecimal rating;
    @Basic(optional = false)
    @NotNull
    @Min(value = 0)
    @Column(name = "price")
    private int price;
    @Column(name = "status")
    private Boolean status;
    @NotNull
    @Column(name = "image")
    private String image;
    @Transient
    private MultipartFile file;
    @OneToMany(mappedBy = "courseId")
    private Collection<RegisterDetail> registerDetailCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "courseId")
    private Collection<CourseProgress> courseProgressCollection;
    @OneToMany(mappedBy = "courseId")
    private Collection<CourseRating> courseRatingCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "courseId")
    private Collection<Lesson> lessonCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "courseId")
    private Collection<CourseTag> courseTagCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "courseId")
    private Collection<Cart> cartCollection;
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    @NotNull
    @ManyToOne
    private Category categoryId;
    @JoinColumn(name = "lecturer_id", referencedColumnName = "id")
    @ManyToOne
    private User lecturerId;

    public Course() {
    }

    public Course(Integer id) {
        this.id = id;
    }

    public Course(Integer id, String title, String description, int price) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Integer getParticipantCount() {
        return participantCount;
    }

    public void setParticipantCount(Integer participantCount) {
        this.participantCount = participantCount;
    }

    public Integer getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(Integer ratingCount) {
        this.ratingCount = ratingCount;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @XmlTransient
    public Collection<RegisterDetail> getRegisterDetailCollection() {
        return registerDetailCollection;
    }

    public void setRegisterDetailCollection(Collection<RegisterDetail> registerDetailCollection) {
        this.registerDetailCollection = registerDetailCollection;
    }

    @XmlTransient
    public Collection<CourseProgress> getCourseProgressCollection() {
        return courseProgressCollection;
    }

    public void setCourseProgressCollection(Collection<CourseProgress> courseProgressCollection) {
        this.courseProgressCollection = courseProgressCollection;
    }

    @XmlTransient
    public Collection<CourseRating> getCourseRatingCollection() {
        return courseRatingCollection;
    }

    public void setCourseRatingCollection(Collection<CourseRating> courseRatingCollection) {
        this.courseRatingCollection = courseRatingCollection;
    }

    @XmlTransient
    public Collection<Lesson> getLessonCollection() {
        return lessonCollection;
    }

    public void setLessonCollection(Collection<Lesson> lessonCollection) {
        this.lessonCollection = lessonCollection;
    }

    @XmlTransient
    public Collection<CourseTag> getCourseTagCollection() {
        return courseTagCollection;
    }

    public void setCourseTagCollection(Collection<CourseTag> courseTagCollection) {
        this.courseTagCollection = courseTagCollection;
    }

    @XmlTransient
    public Collection<Cart> getCartCollection() {
        return cartCollection;
    }

    public void setCartCollection(Collection<Cart> cartCollection) {
        this.cartCollection = cartCollection;
    }

    public Category getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Category categoryId) {
        this.categoryId = categoryId;
    }

    public User getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(User lecturerId) {
        this.lecturerId = lecturerId;
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
        if (!(object instanceof Course)) {
            return false;
        }
        Course other = (Course) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dtl.pojo.Course[ id=" + id + " ]";
    }

    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * @return the file
     */
    public MultipartFile getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(MultipartFile file) {
        this.file = file;
    }
    
}
