/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.controllers;

import com.dtl.components.ErrorResponseUtil;
import java.util.Locale;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *
 * @author LONG
 */
@RestControllerAdvice
public class ApiGlobalController {

    @Autowired
    private ErrorResponseUtil errorResponseUtil;
    
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(Exception ex, Locale locale) {
        System.out.println(ex.getMessage());
        return errorResponseUtil.buildErrorResponse(ex.getMessage(), locale);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex, Locale locale) {
        return errorResponseUtil.buildErrorResponse("system.errMsg", locale);
    }
}
