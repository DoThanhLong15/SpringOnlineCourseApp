/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.controllers;

import com.dtl.pojo.User;
import com.dtl.services.CategoryService;
import com.dtl.services.UserService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author LONG
 */
@Controller
public class HomeController {
    
    @Autowired
    private CategoryService categoryService;
    
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("categories", this.categoryService.getCategories());
        
        return "home";
    }
    
    @PostMapping("/register")
    public String createView(Model model) {
        User u = new User();
        u.setFirstName("Long");
        u.setLastName("Do Thanh");
        u.setEmail("longd8833@gmail.com");
        u.setUsername("admin");
        
        this.userService.addUser(u);
        
        return "home";
    }
}
