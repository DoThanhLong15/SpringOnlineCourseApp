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
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
        int pageSize = this.courseService.getPageSize();

        model.addAttribute("courses", this.courseService.getCourse(params));

        long totalCourse = this.courseService.countCourses();
        int totalPages = (int) Math.ceil((double) totalCourse / pageSize);
        model.addAttribute("q", params.get("q") != null ? params.get("q") : null);
        model.addAttribute("currentPage", params.get("page") != null ? params.get("page") : 1);
        model.addAttribute("totalPages", totalPages);
        
        model.addAttribute("categories", this.categoryService.getCategories(null));

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
    
    @GetMapping("/form/{courseId}")
    public String courseForm(Model model, @PathVariable(value = "courseId") int id) {
        Course course = this.courseService.getCourseById(id);
        course.setCourseTagForm(new ArrayList<>());
        model.addAttribute("course", course);

        populateModelForCourseForm(model);

        return "courseForm";
    }

    @PostMapping("/form/save")
    public String courseSave(Model model, @ModelAttribute(value = "course") @Valid Course course,
            BindingResult rs) {

        if (rs.hasErrors()) {
            populateModelForCourseForm(model);

            return "courseForm";
        }

        try {
            this.courseService.addOrUpdateCourse(course);

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
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") int id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        try {
            this.courseService.deleteCourse(id);

            return new ResponseEntity<>("Xóa thành công", headers, HttpStatus.OK);
        } catch (EntityNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), headers, HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), headers, HttpStatus.BAD_REQUEST);
        }
    }
}
