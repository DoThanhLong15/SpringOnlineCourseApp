/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.repository.impl;

import com.dtl.pojo.LessonContent;
import com.dtl.repository.LessonContentRepository;
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
public class LessonContentRepositoryImpl implements LessonContentRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<LessonContent> getLessonContent(int lessonId) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<LessonContent> q = b.createQuery(LessonContent.class);
        Root root = q.from(LessonContent.class);
        q.select(root);

        Predicate predicate = b.equal(root.get("lessonId"), lessonId);

        q.where(predicate);

        return s.createQuery(q).getResultList();
    }

    @Override
    public LessonContent getLessonContentById(int id) {
        Session s = this.factory.getObject().getCurrentSession();

        LessonContent lessonContent = s.get(LessonContent.class, id);

        if (lessonContent == null) {
            throw new EntityNotFoundException("Không tìm thấy bài học: " + id);
        }

        return lessonContent;
    }

    @Override
    public void saveLessonContent(LessonContent lessonContent) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
