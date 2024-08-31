/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.formatters;

import com.dtl.pojo.CourseTag;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author LONG
 */
public class CourseTagFormatter implements Formatter<CourseTag> {

    @Override
    public String print(CourseTag courseTag, Locale locale) {
        return String.valueOf(courseTag.getId());
    }

    @Override
    public CourseTag parse(String courseTagId, Locale locale) throws ParseException {
        CourseTag courseTag = new CourseTag();
        courseTag.setId(Integer.parseInt(courseTagId));

        return courseTag;
    }

}
