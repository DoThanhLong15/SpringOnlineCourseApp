/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.formatters;

import com.dtl.pojo.UserRole;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author LONG
 */
public class UserRoleFormatter implements Formatter<UserRole> {

    @Override
    public String print(UserRole userRole, Locale locale) {
        return String.valueOf(userRole.getId());
    }

    @Override
    public UserRole parse(String userRoleId, Locale locale) throws ParseException {
        UserRole role = new UserRole();
        role.setId(Integer.parseInt(userRoleId));

        return role;
    }

}
