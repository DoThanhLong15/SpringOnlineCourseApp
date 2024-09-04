/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.repository.impl;

import com.dtl.pojo.RegisterOrder;
import com.dtl.repository.RegisterOrderRepository;
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
public class RegisterOrderRepostoryImpl implements RegisterOrderRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<RegisterOrder> getRegisterOrderList(int learnerId) {
        if (learnerId < 0) {
            return null;
        }

        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<RegisterOrder> q = b.createQuery(RegisterOrder.class);
        Root root = q.from(RegisterOrder.class);
        q.select(root);

        List<Predicate> predicates = new ArrayList<>();

        Predicate p = b.equal(root.get("learnerId"), learnerId);
        predicates.add(p);

        q.where(predicates.toArray(Predicate[]::new));

        return s.createQuery(q).getResultList();
    }

    @Override
    public RegisterOrder getRegisterOrderById(int orderId) {
        Session s = this.factory.getObject().getCurrentSession();

        RegisterOrder order = s.get(RegisterOrder.class, orderId);

        if (order == null) {
            throw new EntityNotFoundException("registerOrder.notFound.errMsg");
        }

        return order;
    }

    @Override
    public void saveRegisterOrder(RegisterOrder order) {
        Session s = this.factory.getObject().getCurrentSession();
        if (order.getId() != null) {
            s.update(order);
        } else {
            s.save(order);
        }
    }

    @Override
    public void deleteRegisterOrder(RegisterOrder order) {
        Session s = this.factory.getObject().getCurrentSession();

        s.delete(order);
    }

}
