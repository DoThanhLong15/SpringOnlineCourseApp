/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.components;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 *
 * @author LONG
 */
@Component
public class ErrorResponseUtil {

    @Autowired
    private MessageSource messageSource;

    public ResponseEntity<Object> buildErrorResponse(String messageKey, Locale locale) {
        Map<String, Object> response = new HashMap<>();
        response.put("error", messageSource.getMessage(messageKey, null, locale));
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    
    public ResponseEntity<Object> buildErrorResponse(Map<String, Object> response, Locale locale) {
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
