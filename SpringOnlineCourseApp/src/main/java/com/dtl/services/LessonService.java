/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dtl.services;

import com.dtl.pojo.Lesson;
import java.util.List;

/**
 *
 * @author LONG
 */
public interface LessonService {

    List<Lesson> getLessons(int courseId);

    Lesson getLessonById(int id);
}
