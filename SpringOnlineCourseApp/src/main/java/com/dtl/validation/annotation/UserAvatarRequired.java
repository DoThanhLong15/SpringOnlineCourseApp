/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dtl.validation.annotation;

import com.dtl.validation.validator.UserAvatarRequiredValidator;
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
@Constraint(validatedBy = UserAvatarRequiredValidator.class)
public @interface UserAvatarRequired {
    String message() default "Ảnh người dùng không được để trống";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
