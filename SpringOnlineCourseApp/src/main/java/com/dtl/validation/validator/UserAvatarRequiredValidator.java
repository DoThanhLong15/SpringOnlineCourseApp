/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.validation.validator;

import com.dtl.pojo.User;
import com.dtl.validation.annotation.UserAvatarRequired;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author LONG
 */
public class UserAvatarRequiredValidator implements ConstraintValidator<UserAvatarRequired, User> {

    @Override
    public void initialize(UserAvatarRequired constraintAnnotation) {

    }

    @Override
    public boolean isValid(User user, ConstraintValidatorContext context) {
        if ((user.getAvatar()== null || user.getAvatar().isEmpty()) && (user.getFile() == null || user.getFile().isEmpty())) {
            return false;
        }

        return true;
    }

}
