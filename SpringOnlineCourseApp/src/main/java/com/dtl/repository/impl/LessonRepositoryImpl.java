/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.repository.impl;

import com.dtl.pojo.Lesson;
import com.dtl.repository.LessonRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
    public List<Lesson> getLessons(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Lesson> q = b.createQuery(Lesson.class);
        Root root = q.from(Lesson.class);
        q.select(root);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();

            String title = params.get("title");
            if (title != null && !title.isEmpty()) {
                Predicate p = b.like(root.get("title"), String.format("%%%s%%", title));
                predicates.add(p);
            }

            String courseId = params.get("courseId");
            if (courseId != null && !courseId.isEmpty()) {
                Predicate p = b.equal(root.get("courseId"), Integer.parseInt(courseId));
                predicates.add(p);
            }

            q.where(predicates.toArray(Predicate[]::new));
        }

        return s.createQuery(q).getResultList();
    }

    @Override
    public Lesson getLessonById(int id) {
        Session s = this.factory.getObject().getCurrentSession();

        Lesson lesson = s.get(Lesson.class, id);
        
        if(lesson == null){
            throw new EntityNotFoundException("lesson.notFound.errMsg");
        }

        return lesson;
    }

    @Override
    public void saveLesson(Lesson lesson) {  
        Session s = this.factory.getObject().getCurrentSession();
        if (lesson.getId() != null) {
            s.update(lesson);
        } else {
            s.save(lesson);
        }
    }
}
