/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.controllers;

import com.dtl.pojo.Tag;
import com.dtl.services.TagService;
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
@RequestMapping("tags")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/list")
    public String categoryList(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("tags", this.tagService.getTags(params));

        int pageSize = this.tagService.getPageSize();
        long totalTags = this.tagService.countTags();
        int totalPages = (int) Math.ceil((double) totalTags / pageSize);

        model.addAttribute("q", params.get("q") != null ? params.get("q") : null);
        model.addAttribute("currentPage", params.get("page") != null ? params.get("page") : 1);
        model.addAttribute("totalPages", totalPages);

        return "tagList";
    }

    @GetMapping("/form")
    public String categoryForm(Model model) {
        model.addAttribute("tag", new Tag());

        return "tagForm";
    }

    @GetMapping("/form/{tagId}")
    public String categoryForm(Model model, @PathVariable(value = "tagId") int id) {
        model.addAttribute("tag", this.tagService.getTagById(id));

        return "tagForm";
    }

    @PostMapping("/form/save")
    public String categorySave(Model model, @ModelAttribute(value = "tag") @Valid Tag tag,
            BindingResult rs) {

        if (rs.hasErrors()) {

            return "tagForm";
        }

        try {
            this.tagService.saveTag(tag);

            return "redirect:/tags/list";
        } catch (Exception ex) {
            model.addAttribute("errMsg", ex.getMessage());
        }

        return "tagForm";
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") int id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        try {
            this.tagService.deleteTag(id);

            return new ResponseEntity<>("Xóa thành công", headers, HttpStatus.OK);
        } catch (EntityNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), headers, HttpStatus.BAD_REQUEST);
        } catch (IllegalStateException ex) {
            return new ResponseEntity<>(ex.getMessage(), headers, HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), headers, HttpStatus.BAD_REQUEST);
        }
    }
}
