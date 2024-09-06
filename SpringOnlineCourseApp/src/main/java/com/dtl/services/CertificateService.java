/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dtl.services;

import com.dtl.pojo.Certificate;
import java.util.List;
import java.util.Map;

/**
 *
 * @author LONG
 */
public interface CertificateService {

    List<Certificate> getCertificateList(Map<String, String> params);

    Certificate getCertificateById(int certificateId);

    void saveCertificate(Certificate certificate);
}
