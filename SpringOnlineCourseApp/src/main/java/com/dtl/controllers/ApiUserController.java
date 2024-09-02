/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.controllers;

import com.dtl.components.JwtService;
import com.dtl.pojo.User;
import com.dtl.services.UserRoleService;
import com.dtl.services.UserService;
import java.security.Principal;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
    public ResponseEntity<Object> login(@RequestBody User user, Locale locale) {
        Map<String, Object> response = new HashMap<>();

        if (this.userService.authUser(user.getUsername(), user.getPassword()) == true) {
            String token = this.jwtService.generateTokenLogin(user.getUsername());
            response.put("token", token);

            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        response.put("error", new HashMap<String, String>());
        Map<String, String> errors = (Map<String, String>) response.get("error");
        errors.put("login", messageSource.getMessage("user.login.fail.errMsg", null, locale));

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/register")
    @CrossOrigin
    public ResponseEntity<Object> register(@Valid @RequestBody User user, BindingResult bindingResult, Locale locale) {
        Map<String, Object> response = new HashMap<>();

        response.put("error", new HashMap<String, String>());
        Map<String, String> errors = (Map<String, String>) response.get("error");

        if (bindingResult.hasErrors()) {

            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            if (this.userService.getUserByUsername(user.getUsername()) != null) {
                errors.put("username", messageSource.getMessage("user.username.exist.errMsg", null, locale));

                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }

            // userRoleId = 2 -> ROLE_LEARNER
            user.setUserRoleId(this.userRoleService.getUserRoleById(2));
            this.userService.addOrUpdateUser(user);

            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            errors.put("error", messageSource.getMessage("system.errMsg", null, locale));

            return new ResponseEntity<>(messageSource.getMessage("system.errMsg", null, locale), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/users/current-user", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<User> getCurrentUser(Principal user) {
        User u = this.userService.getUserByUsername(user.getName());

        return new ResponseEntity<>(u, HttpStatus.OK);
    }
}
