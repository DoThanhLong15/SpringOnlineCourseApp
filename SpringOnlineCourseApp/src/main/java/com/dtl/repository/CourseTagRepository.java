/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dtl.repository;

import com.dtl.pojo.CourseTag;
import java.util.List;
import java.util.Map;

/**
 *
 * @author LONG
 */
public interface CourseTagRepository {

    List<CourseTag> getCourseTags(int courseId, int tagId);
    
    boolean hasCourseTag(int courseId, int tagId);
}
