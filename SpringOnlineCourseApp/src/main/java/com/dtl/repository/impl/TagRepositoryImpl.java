/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.repository.impl;

import com.dtl.pojo.Tag;
import com.dtl.repository.TagRepository;
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
public class TagRepositoryImpl implements TagRepository {

    private int countTags;
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Tag> getTags(Map<String, String> params, int pageSize) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Tag> q = b.createQuery(Tag.class);
        Root root = q.from(Tag.class);
        q.select(root);

        String page = "1";
        if (params != null && !params.isEmpty()) {
            List<Predicate> predicates = new ArrayList<>();
            
            String kw = params.get("q");
            if (kw != null && !kw.isEmpty()) {
                Predicate p1 = b.like(root.get("name"), String.format("%%%s%%", kw));
                predicates.add(p1);
            }
            
            String paramPage = params.get("page");
            if (paramPage != null && !paramPage.isEmpty()) {
                page = paramPage;
            }

            q.where(predicates.toArray(Predicate[]::new));
        }

        Query query = s.createQuery(q);

        countTags = query.getResultList().size();

        int p = Integer.parseInt(page);
        int start = (p - 1) * pageSize;

        query.setFirstResult(start);
        query.setMaxResults(pageSize);

        return query.getResultList();
    }

    @Override
    public void saveTag(Tag tag) {
        Session s = this.factory.getObject().getCurrentSession();
        if (tag.getId() != null) {
            s.update(tag);
        } else {
            s.save(tag);
        }
    }

    @Override
    public int countTags() {
        return this.countTags;
    }

    @Override
    public void deleteTag(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Tag tag = this.getTagById(id);

        s.delete(tag);
    }

    @Override
    public Tag getTagById(int id) {
        Session s = this.factory.getObject().getCurrentSession();

        Tag tag = s.get(Tag.class, id);

        if (tag == null) {
            throw new EntityNotFoundException("tag.notFound.errMsg");
        }

        return tag;
    }

}
