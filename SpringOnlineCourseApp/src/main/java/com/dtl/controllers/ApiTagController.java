/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.controllers;

import com.dtl.pojo.Tag;
import com.dtl.services.TagService;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
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
@RequestMapping("/api/tags")
@CrossOrigin
public class ApiTagController {

    @Autowired
    private TagService tagService;
    @Autowired
    private MessageSource messageSource;

    @GetMapping("/")
    public ResponseEntity<Object> list(@RequestParam Map<String, String> params, Locale locale) {
        Map<String, Object> response = new HashMap<>();

        try {
            List<Tag> tags = this.tagService.getTags(params);

            return new ResponseEntity<>(tags, HttpStatus.OK);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            response.put("error", messageSource.getMessage("system.errMsg", null, locale));
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
