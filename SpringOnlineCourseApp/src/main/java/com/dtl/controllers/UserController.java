/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.controllers;

import com.dtl.pojo.User;
import com.dtl.services.UserRoleService;
import com.dtl.services.UserService;
import java.util.Locale;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;
//    @Autowired
//    private MessageSource messageSource;

    @ModelAttribute
    public void commonAttribure(Model model) {
        model.addAttribute("userRoles", this.userRoleService.getUserRoles());
    }

    @GetMapping("/list")
    public String userList(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("users", this.userService.getUsers(params));

        return "userList";
    }

    @GetMapping("/form")
    public String userForm(Model model) {
        model.addAttribute("user", new User());

        return "userForm";
    }

    @GetMapping("/form/{userId}")
    public String detailsView(Model model, @PathVariable(value = "userId") int id) {
        model.addAttribute("user", this.userService.getUserById(id));

        return "userForm";
    }

    @PostMapping("/form")
    public String userForm(Model model, @ModelAttribute(value = "user") @Valid User user,
            BindingResult rs) {
        if (rs.hasErrors()) {
            return "userForm";
        }

        try {
//            if(user.getUserRoleId().getId() == 1) {
//                throw new Exception(messageSource.getMessage("userRole.role.admin", null, Locale.ROOT));
//            }
            this.userService.addOrUpdateUser(user);
            
            return "redirect:/user/list";
        } catch (Exception ex) {
            model.addAttribute("errMsg", ex.getMessage());
        }

        return "userForm";
    }
}
