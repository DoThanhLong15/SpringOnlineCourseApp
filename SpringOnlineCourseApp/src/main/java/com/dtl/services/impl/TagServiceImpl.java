/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.services.impl;

import com.dtl.pojo.CourseTag;
import com.dtl.pojo.Tag;
import com.dtl.repository.CourseTagRepository;
import com.dtl.repository.TagRepository;
import com.dtl.services.TagService;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author LONG
 */
@Service
public class TagServiceImpl implements TagService {

    private static final int PAGE_SIZE = 10;
    @Autowired
    private TagRepository tagRepo;
    @Autowired
    private CourseTagRepository courseTagRepo;

    @Override
    public List<Tag> getTags(Map<String, String> params) {
        return this.tagRepo.getTags(params, PAGE_SIZE);
    }

    @Override
    public void saveTag(Tag tag) {
        this.tagRepo.saveTag(tag);
    }

    @Override
    public int countTags() {
        return this.tagRepo.countTags();
    }

    @Override
    public void deleteTag(int id) {
        Map<String, String> param = new Hashtable<>();
        param.put("cateId", String.valueOf(id));
        
        if(this.courseTagRepo.hasCourseTag(-1, id)){
            throw new IllegalStateException("Tồn tại khóa học sử dụng thẻ này");
        }
        
        this.tagRepo.deleteTag(id);
    }

    @Override
    public Tag getTagById(int id) {
        return this.tagRepo.getTagById(id);
    }

    @Override
    public int getPageSize() {
        return TagServiceImpl.PAGE_SIZE;
    }

}
