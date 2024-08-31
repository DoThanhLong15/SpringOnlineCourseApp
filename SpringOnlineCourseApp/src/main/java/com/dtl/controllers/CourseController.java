/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.controllers;

import com.dtl.pojo.Course;
import com.dtl.services.CategoryService;
import com.dtl.services.CourseService;
import com.dtl.services.UserService;
import java.util.ArrayList;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author LONG
 */
@Controller
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public String courseList(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("categories", this.categoryService.getCategories(null));
        model.addAttribute("courses", this.courseService.getCourse(params));

        return "courseList";
    }

    @GetMapping("/form")
    public String courseForm(Model model) {
        Course course = new Course();
        course.setCourseTagForm(new ArrayList<>());
        model.addAttribute("course", course);

        populateModelForCourseForm(model);

        return "courseForm";
    }

    @PostMapping("/form/save")
    public String courseSave(Model model, @ModelAttribute(value = "course") @Valid Course course,
            BindingResult rs) {

        System.out.println(course.getCourseTagForm().get(0).getCourseId());
        System.out.println(course.getCourseTagForm().get(0).getTagId());

        if (rs.hasErrors()) {
            populateModelForCourseForm(model);

            return "courseForm";
        }

        try {
//            this.courseService.addOrUpdateCourse(course);

            return "redirect:/courses/list";
        } catch (Exception ex) {
            model.addAttribute("errMsg", ex.getMessage());
        }

        populateModelForCourseForm(model);

        return "courseForm";
    }

    private void populateModelForCourseForm(Model model) {
        model.addAttribute("categories", this.categoryService.getCategories(null));
        model.addAttribute("users", this.userService.getUsers(null).stream()
                .filter(user -> "ROLE_LECTURER".equals(user.getUserRoleId().getRole())).toArray());
    }
}
