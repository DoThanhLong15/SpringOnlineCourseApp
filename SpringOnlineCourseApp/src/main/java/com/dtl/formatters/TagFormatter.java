/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.formatters;

import com.dtl.pojo.Tag;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author LONG
 */
public class TagFormatter implements Formatter<Tag> {

    @Override
    public String print(Tag tag, Locale locale) {
        return String.valueOf(tag.getId());
    }

    @Override
    public Tag parse(String tagId, Locale locale) throws ParseException {
        Tag tag = new Tag();
        tag.setId(Integer.parseInt(tagId));

        return tag;
    }

}
