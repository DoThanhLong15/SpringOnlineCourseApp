/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dtl.repository;

import com.dtl.pojo.LessonContent;
import java.util.List;

/**
 *
 * @author LONG
 */
public interface LessonContentRepository {

    List<LessonContent> getLessonContent(int lessonId);

    LessonContent getLessonContentById(int id);
    
    void saveLessonContent(LessonContent lessonContent);
    
    boolean hasLessonContent(int lessonId, String title);
}
