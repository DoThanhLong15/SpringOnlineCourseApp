/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dtl.repository;

import com.dtl.pojo.Lesson;
import java.util.List;
import java.util.Map;

/**
 *
 * @author LONG
 */
public interface LessonRepository {

    List<Lesson> getLessons(Map<String, String> params);

    Lesson getLessonById(int id);

    void saveLesson(Lesson lesson);

    void deleteLesson(Lesson lesson);

    void deleteLesson(int lessonId);

}
