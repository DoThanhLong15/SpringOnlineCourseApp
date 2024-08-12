/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.controllers;

import com.dtl.pojo.Course;
import com.dtl.services.CategoryService;
import com.dtl.services.CourseService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author LONG
 */
@Controller
@RequestMapping("/course")
public class CourseController {
    
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CourseService courseService;
    
    @GetMapping("/list")
    public String courseList(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("categories", this.categoryService.getCategories());
        model.addAttribute("courses", this.courseService.getCourse(params));
        
        return "courseList";
    }
    
    @GetMapping("/form")
    public String courseForm(Model model) {
        model.addAttribute("categories", this.categoryService.getCategories());
        model.addAttribute("course", new Course());
        
        return "courseForm";
    }
}
