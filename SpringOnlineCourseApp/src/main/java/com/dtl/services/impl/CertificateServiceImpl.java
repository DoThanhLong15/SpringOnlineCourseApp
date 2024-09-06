/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.services.impl;

import com.dtl.pojo.Certificate;
import com.dtl.repository.CertificateRepository;
import com.dtl.services.CertificateService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author LONG
 */
@Service
public class CertificateServiceImpl implements CertificateService{
    
    @Autowired
    private CertificateRepository certificateRepo;

    @Override
    public List<Certificate> getCertificateList(Map<String, String> params) {
        return this.certificateRepo.getCertificateList(params);
    }

    @Override
    public Certificate getCertificateById(int certificateId) {
        return this.certificateRepo.getCertificateById(certificateId);
    }

    @Override
    public void saveCertificate(Certificate certificate) {
        this.certificateRepo.saveCertificate(certificate);
    }
    
}
