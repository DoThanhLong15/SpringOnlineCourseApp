/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.validation.annotation;

import com.dtl.validation.validator.LessonContentDurationRequiredValidator;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 *
 * @author LONG
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = LessonContentDurationRequiredValidator.class)
public @interface LessonContentDurationRequired {

    String message() default "{lessonContent.duration.notNull.errMsg}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
