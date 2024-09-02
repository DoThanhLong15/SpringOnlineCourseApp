/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.services.impl;

import com.dtl.pojo.CourseTag;
import com.dtl.repository.CourseTagRepository;
import com.dtl.services.CourseTagService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author LONG
 */
@Service
public class CourseTagServiceImpl implements CourseTagService{
    
    @Autowired
    private CourseTagRepository courseTagRepo;

    @Override
    public List<CourseTag> getCourseTags(int courseId, int tagId) {
        return this.courseTagRepo.getCourseTags(courseId, tagId);
    }
    
}
