/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.validation.validator;

import com.dtl.pojo.Course;
import com.dtl.validation.annotation.CourseTagRequired;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


/**
 *
 * @author LONG
 */

public class CourseTagRequiredValidator implements ConstraintValidator<CourseTagRequired, Course> {

    @Override
    public void initialize(CourseTagRequired constraintAnnotation) {
        
    }

    @Override
    public boolean isValid(Course course, ConstraintValidatorContext context) {
        if ((course.getCourseTagCollection() == null || course.getCourseTagCollection().isEmpty()) && 
                (course.getCourseTagForm() == null || course.getCourseTagForm().isEmpty() )) {
            return false;
        }
        
        return true;
    }
    
}
