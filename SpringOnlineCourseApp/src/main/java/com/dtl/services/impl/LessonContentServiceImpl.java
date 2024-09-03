/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.services.impl;

import com.dtl.pojo.LessonContent;
import com.dtl.repository.LessonContentRepository;
import com.dtl.services.LessonContentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author LONG
 */
@Service
public class LessonContentServiceImpl implements LessonContentService{
    
    @Autowired
    private LessonContentRepository lessonContentRepo;

    @Override
    public List<LessonContent> getLessonContent(int lessonId) {
        return this.lessonContentRepo.getLessonContent(lessonId);
    }

    @Override
    public LessonContent getLessonContentById(int id) {
        return this.lessonContentRepo.getLessonContentById(id);
    }

    @Override
    public void saveLessonContent(LessonContent lessonContent) {
        this.lessonContentRepo.saveLessonContent(lessonContent);
    }

    @Override
    public boolean hasLessonContent(int lessonId, String title) {
        return this.lessonContentRepo.hasLessonContent(lessonId, title);
    }
    
}
