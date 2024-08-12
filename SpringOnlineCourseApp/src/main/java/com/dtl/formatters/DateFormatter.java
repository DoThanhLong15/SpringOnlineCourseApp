/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.formatters;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author LONG
 */
public class DateFormatter implements Formatter<Date> {

    private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.S";
    private final SimpleDateFormat dateFormat;

    public DateFormatter() {
        this.dateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
    }

    @Override
    public String print(Date object, Locale locale) {
        return (object != null ? dateFormat.format(object) : "");
    }

    @Override
    public Date parse(String text, Locale locale) throws ParseException {
        if (text == null || text.trim().isEmpty()) {
            return null; // Return null for empty strings
        }
        try {
            return dateFormat.parse(text.trim());
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format. Please use " + DEFAULT_DATE_FORMAT, e);
        }
    }
}
