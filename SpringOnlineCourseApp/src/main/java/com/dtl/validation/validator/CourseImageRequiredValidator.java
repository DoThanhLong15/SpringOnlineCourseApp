/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.validation.validator;

import com.dtl.pojo.Course;
import com.dtl.validation.annotation.CourseImageRequired;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author LONG
 */
public class CourseImageRequiredValidator implements ConstraintValidator<CourseImageRequired, Course>  {

    @Override
    public void initialize(CourseImageRequired constraintAnnotation) {
        
    }

    @Override
    public boolean isValid(Course course, ConstraintValidatorContext context) {
        
        if (course.getId() == null && course.getFile() == null || course.getFile().isEmpty()) {
            return false;
        }
        
        return true;
    }
    
}
