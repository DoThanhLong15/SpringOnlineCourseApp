/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dtl.repository;

import com.dtl.pojo.Category;
import java.util.List;
import java.util.Map;

/**
 *
 * @author LONG
 */

public interface CategoryRepository {

    List<Category> getCategories(Map<String, String> params, int pageSize);
    
    void addOrUpdateCourse(Category category);
    
    long countCategories();
}
