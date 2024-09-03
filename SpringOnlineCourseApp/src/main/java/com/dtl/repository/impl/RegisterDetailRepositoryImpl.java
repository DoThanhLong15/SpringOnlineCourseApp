/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.repository.impl;

import com.dtl.pojo.RegisterDetail;
import com.dtl.repository.RegisterDetailRepository;
import java.util.ArrayList;
import java.util.List;
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
public class RegisterDetailRepositoryImpl implements RegisterDetailRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<RegisterDetail> getRegisterDetail(int orderId) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<RegisterDetail> q = b.createQuery(RegisterDetail.class);
        Root root = q.from(RegisterDetail.class);
        q.select(root);

        List<Predicate> predicates = new ArrayList<>();

        if (orderId < 0) {
            return null;
        }

        Predicate predicate = b.equal(root.get("registerOrderId"), orderId);
        predicates.add(predicate);

        q.where(predicates.toArray(Predicate[]::new));

        return s.createQuery(q).getResultList();
    }

}
