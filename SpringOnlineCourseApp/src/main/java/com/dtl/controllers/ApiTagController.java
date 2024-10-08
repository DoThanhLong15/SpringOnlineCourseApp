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
    public ResponseEntity<Object> list(@RequestParam Map<String, String> params, Locale locale) {

        Map<String, Object> response = new HashMap<>();
        List<Tag> tags = this.tagService.getTags(params);
        
        String page = "1";
        String keyword = "";
        if (params != null && !params.isEmpty()) {
            String paramPage = params.get("page");
            if (paramPage != null && !paramPage.isEmpty()) {
                page = paramPage;
            }
            
            String paramKeyword = params.get("q");
            if (paramKeyword != null && !paramKeyword.isEmpty()) {
                keyword = paramKeyword;
            }
        }
        
        int totalTag = this.tagService.countTags();
        
        response.put("data", tags);
        response.put("page", page);
        response.put("count", tags.size());
        response.put("keyword", keyword);
        response.put("total", totalTag);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}
