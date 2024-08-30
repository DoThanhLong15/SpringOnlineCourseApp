/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.services.impl;

import com.dtl.pojo.Category;
import com.dtl.repository.CategoryRepository;
import com.dtl.services.CategoryService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author LONG
 */
@Service
public class CatogoryServiceImpl implements CategoryService{
    
    private static final int PAGE_SIZE = 10;
    @Autowired
    private CategoryRepository categoryRepo;

    @Override
    public List<Category> getCategories(Map<String, String> params) {
        return this.categoryRepo.getCategories(params, PAGE_SIZE);
    }

    @Override
    public void addOrUpdateCourse(Category category) {
        this.categoryRepo.addOrUpdateCourse(category);
    }

    @Override
    public long countCategories() {
        return this.categoryRepo.countCategories();
    }

    @Override
    public int getPageSize() {
        return CatogoryServiceImpl.PAGE_SIZE;
    }
    
}
