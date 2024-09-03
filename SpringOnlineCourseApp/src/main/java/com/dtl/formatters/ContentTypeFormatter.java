/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.formatters;

import com.dtl.pojo.ContentType;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author LONG
 */
public class ContentTypeFormatter implements Formatter<ContentType> {

    @Override
    public String print(ContentType contentType, Locale locale) {
        return String.valueOf(contentType.getId());
    }

    @Override
    public ContentType parse(String contentTypeId, Locale locale) throws ParseException {
        ContentType contentType = new ContentType();
        contentType.setId(Integer.parseInt(contentTypeId));

        return contentType;
    }

}
