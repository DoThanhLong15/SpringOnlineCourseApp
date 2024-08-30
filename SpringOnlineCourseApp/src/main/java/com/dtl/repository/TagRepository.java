/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dtl.repository;

import com.dtl.pojo.Tag;
import java.util.List;
import java.util.Map;

/**
 *
 * @author LONG
 */
public interface TagRepository {
    List<Tag> getTags(Map<String, String> params, int pageSize);
    
    void saveTag(Tag tag);
    
    int countTags();
    
    void deleteTag(int id);
    
    Tag getTagById(int id);
}
