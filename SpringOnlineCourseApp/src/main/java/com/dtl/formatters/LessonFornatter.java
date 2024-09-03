/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.formatters;

import com.dtl.pojo.Lesson;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author LONG
 */
public class LessonFornatter implements Formatter<Lesson> {

    @Override
    public String print(Lesson lesson, Locale locale) {
        return String.valueOf(lesson.getId());
    }

    @Override
    public Lesson parse(String lessonId, Locale locale) throws ParseException {
        Lesson lesson = new Lesson();
        lesson.setId(Integer.parseInt(lessonId));

        return lesson;
    }

}
