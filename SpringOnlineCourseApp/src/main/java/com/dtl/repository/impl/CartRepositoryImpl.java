/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.repository.impl;

import com.dtl.pojo.Cart;
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
import com.dtl.repository.CartRepository;
import java.util.ArrayList;

/**
 *
 * @author LONG
 */
@Repository
@Transactional
public class CartRepositoryImpl implements CartRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public void saveCart(Cart cart) {
        Session s = this.factory.getObject().getCurrentSession();
        if (cart.getId() != null) {
            s.update(cart);
        } else {
            s.save(cart);
        }
    }

    @Override
    public void deleteCart(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Cart cart = this.getCartById(id);

        s.delete(cart);
    }

    @Override
    public void deleteCart(Cart cart) {
        Session s = this.factory.getObject().getCurrentSession();
        s.delete(cart);
    }

    @Override
    public List<Cart> getCates(int userId, int courseId) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Cart> q = b.createQuery(Cart.class);
        Root root = q.from(Cart.class);
        q.select(root);

        List<Predicate> predicates = new ArrayList<>();

        if (userId != -1) {
            Predicate predicate = b.equal(root.get("learnerId"), userId);
            predicates.add(predicate);
        }

        if (courseId != -1) {
            Predicate predicate = b.equal(root.get("learnerId"), userId);
            predicates.add(predicate);

        }

        q.where(predicates.toArray(Predicate[]::new));

        return s.createQuery(q).getResultList();
    }

    @Override
    public Cart getCartById(int id) {
        Session s = this.factory.getObject().getCurrentSession();

        Cart cart = s.get(Cart.class, id);
        if(cart == null){
            throw new EntityNotFoundException("cart.notFound.errMsg");
        }

        return cart;
    }
}
