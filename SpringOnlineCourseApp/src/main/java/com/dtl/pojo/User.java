/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.pojo;

import com.dtl.validation.annotation.UserAvatarRequired;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author LONG
 */
@Entity
@Table(name = "user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findById", query = "SELECT u FROM User u WHERE u.id = :id"),
    @NamedQuery(name = "User.findByFirstName", query = "SELECT u FROM User u WHERE u.firstName = :firstName"),
    @NamedQuery(name = "User.findByLastName", query = "SELECT u FROM User u WHERE u.lastName = :lastName"),
    @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email"),
    @NamedQuery(name = "User.findByAvatar", query = "SELECT u FROM User u WHERE u.avatar = :avatar"),
    @NamedQuery(name = "User.findByCreatedDate", query = "SELECT u FROM User u WHERE u.createdDate = :createdDate"),
    @NamedQuery(name = "User.findByUpdatedDate", query = "SELECT u FROM User u WHERE u.updatedDate = :updatedDate"),
    @NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username"),
    @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password"),
    @NamedQuery(name = "User.findByActive", query = "SELECT u FROM User u WHERE u.active = :active")})
@DynamicInsert
@DynamicUpdate
@UserAvatarRequired(message = "{user.avatar.notValid.errMsg}")
public class User implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "learnerId")
    private Collection<ContentLearn> contentLearnCollection;

    @OneToMany(mappedBy = "learnerId")
    private Collection<Certificate> certificateCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull(message = "{user.firstName.notNull.errMsg}")
    @Size(min = 1, max = 50, message = "{user.firstName.size.errMsg}")
    @Column(name = "first_name")
    private String firstName;
    @Basic(optional = false)
    @NotNull(message = "{user.lastName.notNull.errMsg}")
    @Size(min = 1, max = 50, message = "{user.lastName.size.errMsg}")
    @Column(name = "last_name")
    private String lastName;
    @Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",
            message = "{user.email.notValid.errMsg}")
    @Basic(optional = false)
    @NotNull(message = "{user.email.notNull.errMsg}")
    @Size(min = 1, max = 255, message = "{user.email.notValid.errMsg}")
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "avatar")
    private String avatar;
    @Transient
    @JsonIgnore
    private MultipartFile file;
    @Column(name = "created_date", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @JsonIgnore
    @CreationTimestamp
    private Date createdDate;
    @Column(name = "updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonIgnore
    @UpdateTimestamp
    private Date updatedDate;
    @Basic(optional = false)
    @NotNull(message = "{user.username.notNull.errMsg}")
    @Size(min = 1, max = 50, message = "{user.username.size.errMsg}")
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @NotNull(message = "{user.password.notNull.errMsg}")
    @Size(min = 1, max = 255, message = "{user.password.size.errMsg}")
    @Column(name = "password")
    private String password;
    @Column(name = "active")
    @JsonIgnore
    private Boolean active;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "learnerId")
    @JsonIgnore
    private Collection<CourseProgress> courseProgressCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "learnerId")
    @JsonIgnore
    private Collection<CourseRating> courseRatingCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "learnerId")
    @JsonIgnore
    private Collection<Cart> cartCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "learnerId")
    @JsonIgnore
    private Collection<RegisterOrder> registerOrderCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "learnerId")
    @JsonIgnore
    private Collection<DoingExercise> doingExerciseCollection;
    @OneToMany(mappedBy = "lecturerId")
    @JsonIgnore
    private Collection<Course> courseCollection;
    @JoinColumn(name = "user_role_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    @JsonIgnore
    private UserRole userRoleId;
    @Transient
    @JsonIgnore
    private String oldPassword;

    public User() {
    }

    public User(Integer id) {
        this.id = id;
    }

    public User(Integer id, String firstName, String lastName, String email, String avatar, String username, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.avatar = avatar;
        this.username = username;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
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
    public Collection<Cart> getCartCollection() {
        return cartCollection;
    }

    public void setCartCollection(Collection<Cart> cartCollection) {
        this.cartCollection = cartCollection;
    }

    @XmlTransient
    public Collection<RegisterOrder> getRegisterOrderCollection() {
        return registerOrderCollection;
    }

    public void setRegisterOrderCollection(Collection<RegisterOrder> registerOrderCollection) {
        this.registerOrderCollection = registerOrderCollection;
    }

    @XmlTransient
    public Collection<DoingExercise> getDoingExerciseCollection() {
        return doingExerciseCollection;
    }

    public void setDoingExerciseCollection(Collection<DoingExercise> doingExerciseCollection) {
        this.doingExerciseCollection = doingExerciseCollection;
    }

    @XmlTransient
    public Collection<Course> getCourseCollection() {
        return courseCollection;
    }

    public void setCourseCollection(Collection<Course> courseCollection) {
        this.courseCollection = courseCollection;
    }

    public UserRole getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(UserRole userRoleId) {
        this.userRoleId = userRoleId;
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
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dtl.pojo.User[ id=" + id + " ]";
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

    /**
     * @return the oldPassword
     */
    public String getOldPassword() {
        return oldPassword;
    }

    /**
     * @param oldPassword the oldPassword to set
     */
    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    @XmlTransient
    public Collection<Certificate> getCertificateCollection() {
        return certificateCollection;
    }

    public void setCertificateCollection(Collection<Certificate> certificateCollection) {
        this.certificateCollection = certificateCollection;
    }

    @XmlTransient
    public Collection<ContentLearn> getContentLearnCollection() {
        return contentLearnCollection;
    }

    public void setContentLearnCollection(Collection<ContentLearn> contentLearnCollection) {
        this.contentLearnCollection = contentLearnCollection;
    }
}
