/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.repository.impl;

import com.dtl.pojo.UserRole;
import com.dtl.repository.UserRoleRepository;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
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
public class UserRoleRepositoryImpl implements UserRoleRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public UserRole getUserRoleById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("UserRole.findById");
        q.setParameter("id", id);
        
        UserRole userRole = (UserRole) q.getSingleResult();
        if(userRole == null) {
            throw new EntityNotFoundException("lesson.notFound.errMsg");
        }

        return userRole;
    }

    @Override
    public List<UserRole> getUserRoles() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("From UserRole");

        return q.getResultList();
    }

}
