/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.repository.impl;

import com.dtl.pojo.CourseProgress;
import com.dtl.pojo.DoingExercise;
import com.dtl.repository.DoingExerciseRepository;
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
public class DoingExerciseRepositoryImpl implements DoingExerciseRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public void saveDoingExercise(DoingExercise exercise) {
        Session s = this.factory.getObject().getCurrentSession();
        if (exercise.getId() != null) {
            s.update(exercise);
        } else {
            s.save(exercise);
        }
    }

    @Override
    public DoingExercise getDoingExerciseById(int id) {
        Session s = this.factory.getObject().getCurrentSession();

        DoingExercise exercise = s.get(DoingExercise.class, id);
        if (exercise == null) {
            throw new EntityNotFoundException("doingExercise.notFound.errMsg");
        }

        return exercise;
    }

    @Override
    public DoingExercise getDoingExercise(int learnerId, int contentId) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<DoingExercise> q = b.createQuery(DoingExercise.class);
        Root root = q.from(DoingExercise.class);
        q.select(root);

        List<Predicate> predicates = new ArrayList<>();

        if (learnerId != -1) {
            Predicate p = b.equal(root.get("learnerId"), learnerId);
            predicates.add(p);
        }

        if (contentId != -1) {
            Predicate p = b.equal(root.get("lessonContentId"), contentId);
            predicates.add(p);
        }

        q.where(predicates.toArray(Predicate[]::new));

        try {
            return s.createQuery(q).getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }
}
