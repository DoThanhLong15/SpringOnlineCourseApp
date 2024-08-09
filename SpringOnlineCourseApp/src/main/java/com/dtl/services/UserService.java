/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dtl.services;

import com.dtl.pojo.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author LONG
 */
public interface UserService extends UserDetailsService{
    User getUserByUsername(String username);
    User addUser(User user);
}
