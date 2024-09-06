/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.services.impl;

import com.dtl.pojo.ContentLearn;
import com.dtl.repository.ContentLearnRepository;
import com.dtl.services.ContentLearnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author LONG
 */
@Service
public class ContentLearnServiceImpl implements ContentLearnService {

    @Autowired
    private ContentLearnRepository contentLearnRepo;

    @Override
    public void saveContentLearn(ContentLearn contentLearn) {
        this.contentLearnRepo.saveContentLearn(contentLearn);
    }

    @Override
    public ContentLearn getContentLearn(int lessonContentId, int learnerId) {
        return this.contentLearnRepo.getContentLearn(lessonContentId, learnerId);
    }

    @Override
    public ContentLearn getContentLearnById(int contentLearnId) {
        return this.contentLearnRepo.getContentLearnById(contentLearnId);
    }

}
