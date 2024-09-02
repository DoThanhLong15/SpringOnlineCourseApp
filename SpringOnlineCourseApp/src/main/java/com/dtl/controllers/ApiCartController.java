/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.controllers;

import com.dtl.DTO.LessonDetailDTO;
import com.dtl.pojo.Cart;
import com.dtl.pojo.Lesson;
import com.dtl.pojo.User;
import com.dtl.services.CartService;
import java.security.Principal;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author LONG
 */
@RestController
@RequestMapping("/api/courses")
public class ApiCartController {
    
    @Autowired
    private CartService cartService;
    @Autowired
    private MessageSource messageSource;

    @GetMapping("/{lessonId}")
    @CrossOrigin
    public ResponseEntity<Object> createLesson(Locale locale, Principal user, @PathVariable(value = "lessonId") int id) {
        Map<String, Object> response = new HashMap<>();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/")
    @CrossOrigin
    public ResponseEntity<Object> addToCart(@Valid @RequestBody int courseId,
            BindingResult bindingResult, Locale locale, Principal user) {

        Map<String, String> response = new HashMap<>();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    
}
