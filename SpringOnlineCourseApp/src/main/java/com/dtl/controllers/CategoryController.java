/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.controllers;

import com.dtl.pojo.Category;
import com.dtl.services.CategoryService;
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
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService cateService;

    @GetMapping("/list")
    public String categoryList(Model model, @RequestParam Map<String, String> params) {
        int pageSize = this.cateService.getPageSize();

        model.addAttribute("categories", this.cateService.getCategories(params));

        long totalCategories = this.cateService.countCategories();
        int totalPages = (int) Math.ceil((double) totalCategories / pageSize);

        model.addAttribute("q", params.get("q") != null ? params.get("q") : null);
        model.addAttribute("currentPage", params.get("page") != null ? params.get("page") : 1);
        model.addAttribute("totalPages", totalPages);

        return "categoryList";
    }

    @GetMapping("/form")
    public String categoryForm(Model model) {
        model.addAttribute("category", new Category());

        return "categoryForm";
    }

    @GetMapping("/form/{cateId}")
    public String categoryForm(Model model, @PathVariable(value = "cateId") int id) {
        model.addAttribute("category", this.cateService.getCategoryById(id));

        return "categoryForm";
    }

    @PostMapping("/form/save")
    public String categorySave(Model model, @ModelAttribute(value = "category") @Valid Category category,
            BindingResult rs) {

        if (rs.hasErrors()) {

            return "categoryForm";
        }

        try {
            this.cateService.addOrUpdateCourse(category);

            return "redirect:/categories/list";
        } catch (Exception ex) {
            model.addAttribute("errMsg", ex.getMessage());
        }

        return "categoryForm";
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") int id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        try {
            this.cateService.deleteCategory(id);

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
