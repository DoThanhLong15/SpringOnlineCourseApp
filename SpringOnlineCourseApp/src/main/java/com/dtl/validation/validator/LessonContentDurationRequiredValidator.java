/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.validation.validator;

import com.dtl.pojo.LessonContent;
import com.dtl.validation.annotation.LessonContentDurationRequired;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author LONG
 */
public class LessonContentDurationRequiredValidator implements ConstraintValidator<LessonContentDurationRequired, LessonContent> {

    @Override
    public void initialize(LessonContentDurationRequired constraintAnnotation) {

    }

    @Override
    public boolean isValid(LessonContent lessonContent, ConstraintValidatorContext context) {
        if (lessonContent.getContentTypeId().getIsRequired()) {
            if (lessonContent.getDuration() == null || lessonContent.getDuration() <= 0) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                        .addPropertyNode("duration")
                        .addConstraintViolation();
                return false;
            }
        }

        return true;
    }

}
