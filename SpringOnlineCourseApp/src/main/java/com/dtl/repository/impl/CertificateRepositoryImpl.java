/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.repository.impl;

import com.dtl.pojo.Category;
import com.dtl.pojo.Certificate;
import com.dtl.repository.CertificateRepository;
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
public class CertificateRepositoryImpl implements CertificateRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Certificate> getCertificateList(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Certificate> q = b.createQuery(Certificate.class);
        Root root = q.from(Certificate.class);
        q.select(root);

        if (params != null && !params.isEmpty()) {
            List<Predicate> predicates = new ArrayList<>();

            String kw = params.get("q");
            if (kw != null && !kw.isEmpty()) {
                Predicate p = b.like(root.get("title"), String.format("%%%s%%", kw));
                predicates.add(p);
            }
            
            String courseName = params.get("courseName");
            if (courseName != null && !courseName.isEmpty()) {
                Predicate p = b.like(root.get("courseName"), String.format("%%%s%%", courseName));
                predicates.add(p);
            }

            String learnerId = params.get("learnerId");
            if (learnerId != null && !learnerId.isEmpty()) {
                Predicate p = b.equal(root.get("learnerId"), Integer.parseInt(learnerId));
                predicates.add(p);
            }

            String learner_name = params.get("learner_name");
            if (learner_name != null && !learner_name.isEmpty()) {
                Predicate p = b.like(root.get("learner_name"), String.format("%%%s%%", learner_name));
                predicates.add(p);
            }

            q.where(predicates.toArray(Predicate[]::new));
        }

        return s.createQuery(q).getResultList();
    }

    @Override
    public Certificate getCertificateById(int certificateId) {
        Session s = this.factory.getObject().getCurrentSession();

        Certificate certificate = s.get(Certificate.class, certificateId);

        if (certificate == null) {
            throw new EntityNotFoundException("certificate.notFound.errMsg");
        }

        return certificate;
    }

    @Override
    public void saveCertificate(Certificate certificate) {
        System.out.println("repo save " + certificate);
        Session s = this.factory.getObject().getCurrentSession();
        if (certificate.getId() != null) {
            s.update(certificate);
        } else {
            s.save(certificate);
        }
    }

}
