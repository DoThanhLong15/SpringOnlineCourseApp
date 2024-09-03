/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.repository.impl;

import com.dtl.pojo.CourseProgress;
import com.dtl.repository.CourseProgressRepository;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
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
public class CourseProgressRepositoryImpl implements CourseProgressRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public void saveCourseProgress(CourseProgress courseProgress) {
        Session s = this.factory.getObject().getCurrentSession();
        if (courseProgress.getId() != null) {
            s.update(courseProgress);
        } else {
            s.save(courseProgress);
        }
    }

    @Override
    public CourseProgress getCourseProgressById(int progressId) {
        Session s = this.factory.getObject().getCurrentSession();

        CourseProgress progress = s.get(CourseProgress.class, progressId);

        if (progress == null) {
            throw new EntityNotFoundException("Không tìm thấy tiến trính khóa học: " + progressId);
        }

        return progress;
    }

    @Override
    public CourseProgress getCourseProgress(int userId, int courseId) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<CourseProgress> q = b.createQuery(CourseProgress.class);
        Root root = q.from(CourseProgress.class);
        q.select(root);

        List<Predicate> predicates = new ArrayList<>();

        if (userId != -1) {
            Predicate p = b.equal(root.get("learnerId"), userId);
            predicates.add(p);
        }

        if (courseId != -1) {
            Predicate p = b.equal(root.get("courseId"), courseId);
            predicates.add(p);
        }

        q.where(predicates.toArray(Predicate[]::new));

        try {
            return s.createQuery(q).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
