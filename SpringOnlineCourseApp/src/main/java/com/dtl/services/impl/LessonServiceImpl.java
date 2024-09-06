/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.services.impl;

import com.dtl.pojo.Lesson;
import com.dtl.repository.LessonRepository;
import com.dtl.services.LessonService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author LONG
 */
@Service
public class LessonServiceImpl implements LessonService{
    
    @Autowired
    private LessonRepository lessonRepo;

    @Override
    public List<Lesson> getLessons(Map<String, String> params) {
        return this.lessonRepo.getLessons(params);
    }

    @Override
    public Lesson getLessonById(int id) {
        Lesson lesson = this.lessonRepo.getLessonById(id);
        
        return lesson;
    }

    @Override
    public void saveLesson(Lesson lesson) {
        this.lessonRepo.saveLesson(lesson);
    }

    @Override
    public void deleteLesson(Lesson lesson) {
        this.lessonRepo.deleteLesson(lesson);
    }

    @Override
    public void deleteLesson(int lessonId) {
        this.lessonRepo.deleteLesson(lessonId);
    }
    
}
