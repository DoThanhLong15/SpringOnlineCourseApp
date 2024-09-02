/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.repository.impl;

import com.dtl.pojo.Lesson;
import com.dtl.repository.LessonRepository;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
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
public class LessonRepositoryImpl implements LessonRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Lesson> getLessons(int courseId) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Lesson> q = b.createQuery(Lesson.class);
        Root root = q.from(Lesson.class);
        q.select(root);

        Predicate predicate = b.equal(root.get("courseId"), courseId);

        q.where(predicate);

        return s.createQuery(q).getResultList();
    }

    @Override
    public Lesson getLessonById(int id) {
        Session s = this.factory.getObject().getCurrentSession();

        Lesson lesson = s.get(Lesson.class, id);

        if (lesson == null) {
            throw new EntityNotFoundException("Không tìm thấy lesson: " + id);
        }

        return lesson;
    }
}
