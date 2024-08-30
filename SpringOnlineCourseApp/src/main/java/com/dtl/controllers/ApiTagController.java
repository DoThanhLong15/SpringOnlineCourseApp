/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.controllers;

import com.dtl.pojo.Tag;
import com.dtl.services.TagService;
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
@RequestMapping("/api/tags")
@CrossOrigin
public class ApiTagController {
    @Autowired
    private TagService tagService;
    
    @GetMapping("/")
    public ResponseEntity<List<Tag>> list(@RequestParam Map<String, String> params) {
        List<Tag> tags = this.tagService.getTags(params);
        
//        System.out.println(tags.get(0).getCourseTagCollection());
        
        return new ResponseEntity<>(tags, HttpStatus.OK);
    }
}
