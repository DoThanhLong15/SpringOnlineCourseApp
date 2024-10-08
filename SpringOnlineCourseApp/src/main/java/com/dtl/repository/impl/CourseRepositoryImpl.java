/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.repository.impl;

import com.dtl.pojo.Course;
import com.dtl.pojo.Lesson;
import com.dtl.pojo.LessonContent;
import com.dtl.repository.CourseRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author LONG
 */
@Repository
@Transactional
public class CourseRepositoryImpl implements CourseRepository {

    private int countCourses;

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Course> getCourse(Map<String, String> params, int pageSize) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Course> q = b.createQuery(Course.class);
        Root root = q.from(Course.class);
        q.select(root);

        String page = "1";
        if (params != null && !params.isEmpty()) {
            List<Predicate> predicates = new ArrayList<>();
            String kw = params.get("q");
            if (kw != null && !kw.isEmpty()) {
                Predicate p1 = b.like(root.get("title"), String.format("%%%s%%", kw));
                predicates.add(p1);
            }

            String fromPrice = params.get("fromPrice");
            if (fromPrice != null && !fromPrice.isEmpty()) {
                Predicate p2 = b.greaterThanOrEqualTo(root.get("price"), Double.parseDouble(fromPrice));
                predicates.add(p2);
            }

            String toPrice = params.get("toPrice");
            if (toPrice != null && !toPrice.isEmpty()) {
                Predicate p3 = b.lessThanOrEqualTo(root.get("price"), Double.parseDouble(toPrice));
                predicates.add(p3);
            }

            String cateId = params.get("cateId");
            if (cateId != null && !cateId.isEmpty()) {
                Predicate p4 = b.equal(root.get("categoryId"), Integer.parseInt(cateId));
                predicates.add(p4);
            }
            
            String paramPage = params.get("page");
            if (paramPage != null && !paramPage.isEmpty()) {
                page = paramPage;
            }

            q.where(predicates.toArray(Predicate[]::new));
        }

        Query query = s.createQuery(q);

        countCourses = query.getResultList().size();

        int p = Integer.parseInt(page);
        int start = (p - 1) * pageSize;

        query.setFirstResult(start);
        query.setMaxResults(pageSize);

        return query.getResultList();
    }

    @Override
    public void addOrUpdateCourse(Course course) {
        Session s = this.factory.getObject().getCurrentSession();
        if (course.getId() != null) {
            s.update(course);
        } else {
            s.save(course);
        }
    }

    @Override
    public Course getCourseById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Course course = s.get(Course.class, id);

        if (course == null) {
            throw new EntityNotFoundException("course.notFound.errMsg");
        }

        return course;
    }

    @Override
    public void deleteCourse(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Course course = this.getCourseById(id);

        if (course == null) {
            throw new EntityNotFoundException("Không tìm thấy khóa học: " + id);
        }
        s.delete(course);
    }

    @Override
    public int countCourses() {
        return this.countCourses;
    }

    @Override
    public Integer countContentInCourse(int courseId) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();

        CriteriaQuery<Long> query = b.createQuery(Long.class);
        Root<Course> root = query.from(Course.class);

        Join<Course, Lesson> lessonJoin = root.join("lessonCollection");
        Join<Lesson, LessonContent> contentJoin = lessonJoin.join("lessonContentCollection");

        query.where(b.equal(root.get("id"), courseId));

        query.select(b.count(contentJoin));

        Long contentCount = s.createQuery(query).getSingleResult();

        return contentCount != null ? contentCount.intValue() : 0;
    }
}
