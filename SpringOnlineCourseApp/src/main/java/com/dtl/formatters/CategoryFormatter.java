/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.formatters;

import com.dtl.pojo.Category;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author LONG
 */
public class CategoryFormatter implements Formatter<Category> {

    @Override
    public String print(Category category, Locale locale) {
        return String.valueOf(category.getId());
    }

    @Override
    public Category parse(String cateId, Locale locale) throws ParseException {
        Category c = new Category();
        c.setId(Integer.parseInt(cateId));
        
        return c;
    }

}
