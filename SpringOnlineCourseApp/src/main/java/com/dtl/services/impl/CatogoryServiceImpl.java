/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.services.impl;

import com.dtl.pojo.Category;
import com.dtl.pojo.Course;
import com.dtl.repository.CategoryRepository;
import com.dtl.repository.CourseRepository;
import com.dtl.services.CategoryService;
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
public class CatogoryServiceImpl implements CategoryService {

    private static final int PAGE_SIZE = 10;
    @Autowired
    private CategoryRepository categoryRepo;
    @Autowired
    private CourseRepository courseRepo;

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

    @Override
    public void deleteCategory(int id) {
        Map<String, String> param = new Hashtable<>();
        param.put("cateId", String.valueOf(id));

        List<Course> courses = this.courseRepo.getCourse(param);
        if (!courses.isEmpty()) {
            throw new IllegalStateException("Tồn tại sản phẩm trong danh mục này!");

        }
        
        this.categoryRepo.deleteCategory(id);
    }

    @Override
    public Category getCategoryById(int id) {
        return this.categoryRepo.getCategoryById(id);
    }

}
