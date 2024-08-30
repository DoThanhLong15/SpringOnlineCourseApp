/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.repository.impl;

import com.dtl.pojo.Category;
import com.dtl.repository.CategoryRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
public class CategoryRepositoryImpl implements CategoryRepository {

    private int countCategory;
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Category> getCategories(Map<String, String> params, int pageSize) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Category> q = b.createQuery(Category.class);
        Root root = q.from(Category.class);
        q.select(root);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();
            String kw = params.get("q");

            if (kw != null && !kw.isEmpty()) {
                Predicate p1 = b.like(root.get("name"), String.format("%%%s%%", kw));
                predicates.add(p1);
            }

            q.where(predicates.toArray(Predicate[]::new));
        }

        Query query = s.createQuery(q);

        countCategory = query.getResultList().size();

        if (params != null) {
            String page = params.get("page") != null && !params.get("page").isEmpty() ? params.get("page") : "1";
            
            int p = Integer.parseInt(page);
            int start = (p - 1) * pageSize;

            query.setFirstResult(start);
            query.setMaxResults(pageSize);
        }

        return query.getResultList();
    }

    @Override
    public void addOrUpdateCourse(Category category) {
        Session s = this.factory.getObject().getCurrentSession();
        if (category.getId() != null) {
            s.update(category);
        } else {
            s.save(category);
        }
    }

    @Override
    public long countCategories() {
        return this.countCategory;
    }
}
