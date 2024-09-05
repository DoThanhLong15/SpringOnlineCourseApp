/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.controllers;

import com.dtl.pojo.Category;
import com.dtl.services.CategoryService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author LONG
 */
@RestController
@RequestMapping("/api/categories")
public class ApiCategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    @CrossOrigin
    public ResponseEntity<Object> getCategoryList(@RequestParam Map<String, String> params) {
        Map<String, Object> response = new HashMap<>();

        List<Category> categoryList = this.categoryService.getCategories(params);
        
        System.out.println(params);

        String page = "1";
        String keyword = "";
        if (params != null && !params.isEmpty()) {
            String paramPage = params.get("page");
            if (paramPage != null && !paramPage.isEmpty()) {
                page = paramPage;
            }
            
            String paramKeyword = params.get("q");
            if (paramKeyword != null && !paramKeyword.isEmpty()) {
                keyword = paramKeyword;
            }
        }

        long totalCategory = this.categoryService.countCategories();

        response.put("data", categoryList);
        response.put("page", page);
        response.put("keyword", keyword);
        response.put("count", categoryList.size());
        response.put("total", totalCategory);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
