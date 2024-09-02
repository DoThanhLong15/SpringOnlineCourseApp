/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.services.impl;

import com.dtl.pojo.Lesson;
import com.dtl.repository.LessonRepository;
import com.dtl.services.LessonService;
import java.util.List;
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
    public List<Lesson> getLessons(int courseId) {
        return this.lessonRepo.getLessons(courseId);
    }

    @Override
    public Lesson getLessonById(int id) {
        return this.lessonRepo.getLessonById(id);
    }
    
}
