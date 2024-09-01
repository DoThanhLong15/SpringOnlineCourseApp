/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.controllers;

import com.dtl.components.JwtService;
import com.dtl.pojo.User;
import com.dtl.services.UserRoleService;
import com.dtl.services.UserService;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author LONG
 */
@RestController
@RequestMapping("/api")
public class ApiUserController {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private MessageSource messageSource;

    @PostMapping("/login")
    @CrossOrigin
    public ResponseEntity<String> login(@RequestBody User user) {
        if (this.userService.authUser(user.getUsername(), user.getPassword()) == true) {
            String token = this.jwtService.generateTokenLogin(user.getUsername());

            return new ResponseEntity<>(token, HttpStatus.OK);
        }

        return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/register")
    @CrossOrigin
    public ResponseEntity<Object> register(@Valid @RequestBody User user, BindingResult bindingResult, Locale locale) {
        Map<String, String> errors = new HashMap<>();
        
        if (bindingResult.hasErrors()) {
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }

            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        try {
            if(this.userService.getUserByUsername(user.getUsername()) != null){
                errors.put("username", messageSource.getMessage("user.username.exist.errMsg", null, locale));
                return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
            }
            
            // userRoleId = 2 -> ROLE_LEARNER
            user.setUserRoleId(this.userRoleService.getUserRoleById(2));
            System.out.println(user.getAvatar());
            this.userService.addOrUpdateUser(user);
            
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            errors.put("error", messageSource.getMessage("system.errMsg", null, locale));
            return new ResponseEntity<>(messageSource.getMessage("system.errMsg", null, locale), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
