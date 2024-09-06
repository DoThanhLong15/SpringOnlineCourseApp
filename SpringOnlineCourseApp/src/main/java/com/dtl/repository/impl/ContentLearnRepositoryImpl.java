/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.repository.impl;

import com.dtl.pojo.ContentLearn;
import com.dtl.pojo.CourseProgress;
import com.dtl.repository.ContentLearnRepository;
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
public class ContentLearnRepositoryImpl implements ContentLearnRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public void saveContentLearn(ContentLearn contentLearn) {
        Session s = this.factory.getObject().getCurrentSession();
        if (contentLearn.getId() != null) {
            s.update(contentLearn);
        } else {
            s.save(contentLearn);
        }
    }

    @Override
    public ContentLearn getContentLearn(int lessonContentId, int leanerId) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<ContentLearn> q = b.createQuery(ContentLearn.class);
        Root root = q.from(ContentLearn.class);
        q.select(root);

        List<Predicate> predicates = new ArrayList<>();

        if (leanerId > 0) {
            Predicate p = b.equal(root.get("learnerId"), leanerId);
            predicates.add(p);
        }

        if (lessonContentId > 0) {
            Predicate p = b.equal(root.get("lessonContentId"), lessonContentId);
            predicates.add(p);
        }

        q.where(predicates.toArray(Predicate[]::new));

        try {
            return s.createQuery(q).getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public ContentLearn getContentLearnById(int contentLearnId) {
        Session s = this.factory.getObject().getCurrentSession();

        ContentLearn contentLearn = s.get(ContentLearn.class, contentLearnId);
        if (contentLearn == null) {
            throw new EntityNotFoundException("contentLearn.notFound.errMsg");
        }

        return contentLearn;
    }

}
