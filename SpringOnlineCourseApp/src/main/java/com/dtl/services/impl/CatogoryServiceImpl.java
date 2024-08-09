/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.services.impl;

import com.dtl.pojo.Category;
import com.dtl.repository.CategoryRepository;
import com.dtl.services.CategoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author LONG
 */
@Service
public class CatogoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepo;

    @Override
    public List<Category> getCategories() {
        return this.categoryRepo.getCategories();
    }
    
}
