/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.repository.impl;

import com.dtl.pojo.CourseTag;
import com.dtl.repository.CourseTagRepository;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityNotFoundException;
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
public class CourseTagRepositoryImpl implements CourseTagRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<CourseTag> getCourseTags(int courseId, int tagId) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<CourseTag> q = b.createQuery(CourseTag.class);
        Root root = q.from(CourseTag.class);
        q.select(root);

        List<Predicate> predicates = new ArrayList<>();
        if (courseId != -1) {
            Predicate p = b.equal(root.get("courseId"), courseId);
            predicates.add(p);

        }
        if (tagId != -1) {
            Predicate p = b.equal(root.get("tagId"), tagId);
            predicates.add(p);
        }

        q.where(predicates.toArray(Predicate[]::new));

        return s.createQuery(q).getResultList();
    }

    @Override
    public boolean hasCourseTag(int courseId, int tagId) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Long> q = b.createQuery(Long.class);
        Root root = q.from(CourseTag.class);
        
        q.select(b.count(root));
        
        List<Predicate> predicates = new ArrayList<>();
        if (courseId != -1) {
            Predicate p = b.equal(root.get("courseId"), courseId);
            predicates.add(p);

        }
        if (tagId != -1) {
            Predicate p = b.equal(root.get("tagId"), tagId);
            predicates.add(p);
        }
        
        q.where(predicates.toArray(Predicate[]::new));

        Long count = s.createQuery(q).getSingleResult();
        return count > 0;
    }

}
